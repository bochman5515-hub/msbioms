package msbioms.worldgen.carver;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.CarvingMask;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.carver.CarvingContext;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import org.apache.commons.lang3.mutable.MutableBoolean;

import java.util.function.Function;

public class ChambersCarver
        extends WorldCarver<CaveCarverConfiguration> {

    public ChambersCarver(
            Codec<CaveCarverConfiguration> codec
    ) {
        super(codec);
    }

    @Override
    public boolean isStartChunk(
            CaveCarverConfiguration configuration,
            RandomSource random
    ) {
        return random.nextFloat() <= configuration.probability;
    }

    @Override
    public boolean carve(

            CarvingContext context,
            CaveCarverConfiguration configuration,
            ChunkAccess chunk,
            Function<BlockPos, Holder<Biome>> biomeGetter,
            RandomSource random,
            Aquifer aquifer,
            ChunkPos sourceChunkPos,
            CarvingMask carvingMask
    ) {
        double centerX =
                sourceChunkPos.getBlockX(random.nextInt(16));

        double centerY =
                configuration.y.sample(random, context);

        double centerZ =
                sourceChunkPos.getBlockZ(random.nextInt(16));

        /*
         * Общие размеры камеры.
         */
        double length =
                60.0D + random.nextDouble() * 20.0D;

        double maxWidth =
                22.0D + random.nextDouble() * 8.0D;

        double maxHeight =
                13.0D + random.nextDouble() * 5.0D;

        /*
         * Направление основной оси.
         */
        double angle =
                random.nextDouble() * Math.PI * 2.0D;

        double dirX = Math.cos(angle);
        double dirZ = Math.sin(angle);

        double sideX = -dirZ;
        double sideZ = dirX;

        /*
         * Seed конкретной камеры.
         */
        long seed =
                ((long) Math.floor(centerX) * 341873128712L)
                        ^ ((long) Math.floor(centerY) * 132897987541L)
                        ^ ((long) Math.floor(centerZ) * 42317861L);

        RandomSource chamberRandom =
                RandomSource.create(seed);

        /*
         * Несколько крупных волн формы.
         *
         * Они не создают отдельные комнаты.
         * Они изменяют границу одной непрерывной камеры.
         */
        double wave1 =
                chamberRandom.nextDouble() * Math.PI * 2.0D;

        double wave2 =
                chamberRandom.nextDouble() * Math.PI * 2.0D;

        double wave3 =
                chamberRandom.nextDouble() * Math.PI * 2.0D;

        double bend =
                (chamberRandom.nextDouble() - 0.5D)
                        * 0.16D;

        /*
         * Область X/Z, которую потенциально может занимать
         * камера.
         */
        int minX =
                Mth.floor(centerX - length * 0.6D);

        int maxX =
                Mth.floor(centerX + length * 0.6D);

        int minZ =
                Mth.floor(centerZ - length * 0.6D);

        int maxZ =
                Mth.floor(centerZ + length * 0.6D);

        int minY =
                Mth.floor(centerY - maxHeight * 1.5D);

        int maxY =
                Mth.floor(centerY + maxHeight * 1.5D);

        int candidates = 0;
        int insideChunk = 0;
        int carvedBlocks = 0;

        minY = Math.max(minY, context.getMinGenY());
        maxY = Math.min(
                maxY,
                context.getMinGenY() + context.getGenDepth() - 1
        );

        MutableBoolean hasGrass =
                new MutableBoolean(false);

        /*
         * Перебираем только ограничивающий прямоугольник.
         */
        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {

                /*
                 * Переводим мировую позицию в координаты
                 * относительно оси камеры.
                 */
                double dx = x - centerX;
                double dz = z - centerZ;

                double longitudinal =
                        dx * dirX + dz * dirZ;

                double lateral =
                        dx * sideX + dz * sideZ;

                /*
                 * Нормализованная координата вдоль камеры.
                 */
                double t =
                        longitudinal / (length * 0.5D);

                /*
                 * За пределами камеры ничего не делаем.
                 */
                if (Math.abs(t) > 1.12D) {
                    continue;
                }

                /*
                 * -------------------------------------------------
                 * ИЗГИБ ОСИ
                 * -------------------------------------------------
                 */

                double curve =
                        Math.sin(
                                (t + 1.0D)
                                        * Math.PI
                                        * 0.5D
                                        + wave1
                        ) * bend * length;

                double localLateral =
                        lateral - curve;

                /*
                 * -------------------------------------------------
                 * ПРОФИЛЬ ШИРИНЫ
                 * -------------------------------------------------
                 *
                 * Концы сужаются.
                 * Центр расширяется.
                 */

                double endShape =
                        Math.sqrt(
                                Math.max(
                                        0.0D,
                                        1.0D - t * t
                                )
                        );

                double width =
                        maxWidth
                                * (
                                0.48D
                                        + 0.52D * endShape
                        );

                /*
                 * Крупные неровности стен.
                 */
                double wallWave =
                        Math.sin(
                                longitudinal * 0.065D
                                        + wave2
                        );

                double wallWave2 =
                        Math.sin(
                                longitudinal * 0.115D
                                        + lateral * 0.055D
                                        + wave3
                        );

                width *=
                        1.0D
                                + wallWave * 0.13D
                                + wallWave2 * 0.07D;

                /*
                 * Несколько больших локальных выпуклостей.
                 */
                width +=
                        getLargeBulge(
                                longitudinal,
                                lateral,
                                wave1,
                                wave2
                        );

                /*
                 * -------------------------------------------------
                 * ПОПЕРЕЧНОЕ СЕЧЕНИЕ КАМЕРЫ
                 * -------------------------------------------------
                 *
                 * Стена больше не вертикальная.
                 * Ширина зависит от положения по высоте.
                 */

                double side =
                        localLateral / width;

                /*
                 * Профиль потолка и пола.
                 */
                double ceiling =
                        getCeilingHeight(
                                longitudinal,
                                maxHeight,
                                wave1,
                                wave2,
                                wave3
                        );

                double floor =
                        getFloorHeight(
                                longitudinal,
                                maxHeight,
                                wave1,
                                wave2,
                                wave3
                        );

                /*
                 * Свод становится уже возле краёв.
                 */
                double wallFactor =
                        Math.abs(side);

                double edgeCompression =
                        1.0D
                                - wallFactor * wallFactor * 0.18D;

                ceiling *= edgeCompression;
                floor *= edgeCompression;

                double localCenterY =
                        centerY
                                + getVerticalDrift(
                                longitudinal,
                                maxHeight,
                                wave2
                        );

                /*
                 * -------------------------------------------------
                 * Y
                 * -------------------------------------------------
                 */

                for (int y = minY; y <= maxY; y++) {

                    double vertical =
                            y - localCenterY;

                    /*
                     * За пределами потолка/пола.
                     */
                    if (vertical >= ceiling
                            || vertical <= -floor) {
                        continue;
                    }

                    /*
                     * Нормализованная высота:
                     *
                     * -1 = пол
                     *  0 = центр
                     * +1 = потолок
                     */
                    double normalizedY =
                            vertical >= 0.0D
                                    ? vertical / ceiling
                                    : -vertical / floor;

                    /*
                     * Базовая форма свода.
                     */
                    double verticalFactor =
                            1.0D
                                    - normalizedY * normalizedY;

                    if (verticalFactor <= 0.0D) {
                        continue;
                    }

                    /*
                     * Чем ближе к потолку/полу,
                     * тем уже становится помещение.
                     */
                    double allowedWidth =
                            width
                                    * Math.sqrt(
                                    verticalFactor
                            );

                    /*
                     * Неровность стен.
                     */
                    double wallNoise =
                            getWallNoise(
                                    x,
                                    y,
                                    z,
                                    longitudinal,
                                    normalizedY,
                                    wave1,
                                    wave2,
                                    wave3
                            );

                    allowedWidth += wallNoise;

                    if (Math.abs(localLateral) > allowedWidth) {
                        continue;
                    }

                    /*
                     * Неровность потолка/пола.
                     */
                    double verticalNoise =
                            getVerticalSurfaceNoise(
                                    x,
                                    y,
                                    z,
                                    longitudinal,
                                    side,
                                    wave1,
                                    wave2,
                                    wave3
                            );

                    if (vertical > ceiling - verticalNoise) {
                        continue;
                    }

                    if (vertical < -floor + verticalNoise) {
                        continue;
                    }

                    if (!isInsideChunk(
                            chunk,
                            x,
                            z
                    )) {
                        continue;
                    }

                    candidates++;
                    insideChunk++;

                    BlockPos.MutableBlockPos pos =
                            new BlockPos.MutableBlockPos(
                                    x,
                                    y,
                                    z
                            );

                    boolean carved =
                            carveBlock(
                                    context,
                                    configuration,
                                    chunk,
                                    biomeGetter,
                                    carvingMask,
                                    pos,
                                    new BlockPos.MutableBlockPos(),
                                    aquifer,
                                    hasGrass
                            );

                    if (carved) {
                        carvedBlocks++;
                    }
                }
                /*
                 * -------------------------------------------------
                 * ВЫРЕЗАНИЕ ПО Y
                 * -------------------------------------------------
                 */



            }
        }


        System.out.println(
                "[MSBioms CHAMBER] "
                        + "CENTER="
                        + (int) centerX
                        + ","
                        + (int) centerY
                        + ","
                        + (int) centerZ
                        + " SIZE="
                        + (int) length
                        + "x"
                        + (int) maxWidth
                        + "x"
                        + (int) maxHeight
                        + " | candidates="
                        + candidates
                        + " insideChunk="
                        + insideChunk
                        + " carved="
                        + carvedBlocks
        );

        return true;
    }


    /*
     * =========================================================
     * FUSED FLOOR ↔ CEILING FORMATION
     * =========================================================
     *
     * This follows the mathematical profile used by
     * vanilla LargeDripstoneFeature.
     */
    private double getWallNoise(
            int x, int y, int z,
            double longitudinal,
            double normalizedY,
            double wave1, double wave2, double wave3
    ) {
        double large =
                Math.sin(x * 0.035D + z * 0.028D + wave1)
                        * 4.0D;

        double medium =
                Math.sin(x * 0.085D - z * 0.065D
                        + longitudinal * 0.035D + wave2)
                        * 1.8D;

        double small =
                Math.sin(x * 0.18D + z * 0.15D
                        + y * 0.04D + wave3)
                        * 0.6D;

        double edge =
                0.35D + 0.65D * Math.pow(Math.abs(normalizedY), 0.5D);

        return (large + medium + small) * edge;
    }
    private double getLargeBulge(
            double longitudinal,
            double lateral,
            double phase1,
            double phase2
    ) {
        /*
         * Три крупных выступа.
         *
         * Они достаточно плавные, чтобы не выглядеть
         * случайными блоками.
         */

        double b1 =
                Math.exp(
                        -Math.pow(
                                (longitudinal + 32.0D) / 15.0D,
                                2.0D
                        )
                )
                        * Math.sin(
                        lateral * 0.08D + phase1
                );

        double b2 =
                Math.exp(
                        -Math.pow(
                                (longitudinal - 8.0D) / 20.0D,
                                2.0D
                        )
                )
                        * Math.sin(
                        lateral * 0.07D + phase2
                );

        double b3 =
                Math.exp(
                        -Math.pow(
                                (longitudinal - 35.0D) / 13.0D,
                                2.0D
                        )
                )
                        * Math.cos(
                        lateral * 0.10D + phase1
                );

        return (b1 + b2 + b3) * 4.0D;
    }
    private double getCeilingHeight(
            double longitudinal,
            double maxHeight,
            double wave1,
            double wave2,
            double wave3
    ) {
        double large =
                Math.sin(
                        longitudinal * 0.045D
                                + wave1
                );

        double medium =
                Math.sin(
                        longitudinal * 0.095D
                                + wave2
                );

        double small =
                Math.sin(
                        longitudinal * 0.17D
                                + wave3
                );

        return maxHeight
                * (
                0.72D
                        + large * 0.14D
                        + medium * 0.08D
                        + small * 0.035D
        );
    }
    private double getFloorHeight(
            double longitudinal,
            double maxHeight,
            double wave1,
            double wave2,
            double wave3
    ) {
        double large =
                Math.sin(
                        longitudinal * 0.052D
                                + wave3
                );

        double medium =
                Math.sin(
                        longitudinal * 0.105D
                                + wave1
                );

        double small =
                Math.sin(
                        longitudinal * 0.19D
                                + wave2
                );

        return maxHeight
                * (
                0.66D
                        + large * 0.13D
                        + medium * 0.075D
                        + small * 0.035D
        );
    }
    private double getVerticalDrift(
            double longitudinal,
            double maxHeight,
            double phase
    ) {
        return maxHeight
                * 0.12D
                * Math.sin(
                longitudinal * 0.045D
                        + phase
        );
    }
    private double getVerticalSurfaceNoise(
            int x,
            int y,
            int z,
            double longitudinal,
            double side,
            double wave1,
            double wave2,
            double wave3
    ) {
        /*
         * Крупные волны.
         */
        double large =
                Math.sin(
                        x * 0.075D
                                + z * 0.055D
                                + wave1
                );

        /*
         * Более мелкие.
         */
        double medium =
                Math.sin(
                        x * 0.16D
                                - z * 0.11D
                                + y * 0.025D
                                + wave2
                );

        /*
         * Поверхность сильнее меняется возле стен.
         */
        double edge =
                Math.pow(
                        Math.abs(side),
                        2.0D
                );

        return
                (large * 0.9D
                        + medium * 0.45D)
                        * edge;
    }

    private void placeFusedFormation(
            ChunkAccess chunk,
            CarvingContext context,
            RandomSource random,
            double x,
            double centerY,
            double z
    ) {

        int rootX =
                Mth.floor(x);

        int rootZ =
                Mth.floor(z);

        /*
         * Formation only makes sense when its root is
         * reasonably inside the chamber chunk.
         */
        if (!isInsideChunk(chunk, rootX, rootZ)) {
            return;
        }

        int searchMin =
                Math.max(
                        context.getMinGenY() + 1,
                        -127
                );

        int searchMax =
                Math.min(
                        context.getMinGenY()
                                + context.getGenDepth()
                                - 1,
                        504
                );


        /*
         * =====================================================
         * FIND FLOOR
         * =====================================================
         */

        int floorY =
                findSolidBelow(
                        chunk,
                        rootX,
                        Mth.floor(centerY),
                        rootZ,
                        searchMin
                );

        /*
         * =====================================================
         * FIND CEILING
         * =====================================================
         */

        int ceilingY =
                findSolidAbove(
                        chunk,
                        rootX,
                        Mth.floor(centerY),
                        rootZ,
                        searchMax
                );

        if (floorY == Integer.MIN_VALUE
                || ceilingY == Integer.MAX_VALUE) {
            return;
        }


        /*
         * Air gap must be large enough.
         */
        int gap =
                ceilingY - floorY - 1;

        if (gap < 8) {
            return;
        }


        /*
         * =====================================================
         * RADIUS
         * =====================================================
         *
         * Vanilla LargeDripstone limits radius according
         * to cave height.
         */

        int radius =
                Mth.clamp(
                        2 + random.nextInt(3),
                        2,
                        Math.max(
                                2,
                                gap / 8
                        )
                );

        radius =
                Math.min(radius, 4);


        /*
         * =====================================================
         * HEIGHT
         * =====================================================
         *
         * We want the two formations to actually meet.
         */

        int usableHeight =
                gap - 2;

        int lowerHeight =
                (int) (
                        usableHeight
                                * (
                                0.42D
                                        + random.nextDouble()
                                        * 0.14D
                        )
                );

        int upperHeight =
                usableHeight
                        - lowerHeight;


        /*
         * Small random vertical imbalance.
         */
        int imbalance =
                random.nextInt(3) - 1;

        lowerHeight =
                Math.max(
                        2,
                        lowerHeight + imbalance
                );

        upperHeight =
                Math.max(
                        2,
                        usableHeight - lowerHeight
                );


        /*
         * =====================================================
         * PLACE STALAGMITE
         * =====================================================
         */

        placeLargeFormation(
                chunk,
                rootX,
                floorY + 1,
                rootZ,
                radius,
                lowerHeight,
                true,
                random
        );


        /*
         * =====================================================
         * PLACE STALACTITE
         * =====================================================
         */

        placeLargeFormation(
                chunk,
                rootX,
                ceilingY - 1,
                rootZ,
                radius,
                upperHeight,
                false,
                random
        );
    }


    /*
     * =========================================================
     * VANILLA-STYLE RADIAL PROFILE
     * =========================================================
     *
     * Formula copied from the actual 26.2
     * SpeleothemUtils.getSpeleothemHeight() logic.
     */

    private double getSpeleothemHeight(
            double distanceFromCenter,
            double radius,
            double scale,
            double bluntness
    ) {

        if (distanceFromCenter < bluntness) {
            distanceFromCenter =
                    bluntness;
        }

        double r =
                distanceFromCenter
                        / radius
                        * 0.384D;

        double part1 =
                0.75D
                        * Math.pow(
                        r,
                        1.3333333333333333D
                );

        double part2 =
                Math.pow(
                        r,
                        0.6666666666666666D
                );

        double part3 =
                0.3333333333333333D
                        * Math.log(r);

        double height =
                scale
                        * (
                        part1
                                - part2
                                - part3
                );

        height =
                Math.max(
                        height,
                        0.0D
                );

        return height
                / 0.384D
                * radius;
    }


    /*
     * =========================================================
     * PLACE LARGE FORMATION
     * =========================================================
     */

    private void placeLargeFormation(
            ChunkAccess chunk,
            int rootX,
            int rootY,
            int rootZ,
            int radius,
            int height,
            boolean pointingUp,
            RandomSource random
    ) {

        double scale =
                height
                        / Math.max(
                        1.0D,
                        getSpeleothemHeight(
                                0.0D,
                                radius,
                                1.0D,
                                0.0D
                        )
                );

        double bluntness =
                radius <= 2
                        ? 0.5D
                        : 1.0D;


        for (int dx = -radius;
             dx <= radius;
             dx++) {

            for (int dz = -radius;
                 dz <= radius;
                 dz++) {

                double distance =
                        Math.sqrt(
                                dx * dx
                                        + dz * dz
                        );

                if (distance > radius) {
                    continue;
                }

                double columnHeight =
                        getSpeleothemHeight(
                                distance,
                                radius,
                                scale,
                                bluntness
                        );

                int localHeight =
                        Math.max(
                                1,
                                (int) Math.ceil(
                                        columnHeight
                                )
                        );

                for (int y = 0;
                     y < localHeight;
                     y++) {

                    int worldY =
                            pointingUp
                                    ? rootY + y
                                    : rootY - y;

                    if (isInsideChunk(
                            chunk,
                            rootX + dx,
                            rootZ + dz
                    )) {

                        BlockPos pos =
                                new BlockPos(
                                        rootX + dx,
                                        worldY,
                                        rootZ + dz
                                );

                        BlockState current =
                                chunk.getBlockState(pos);

                        /*
                         * Only replace air/water.
                         *
                         * Never destroy surrounding stone.
                         */
                        if (current.isAir()
                                || current.is(Blocks.WATER)) {

                            chunk.setBlockState(
                                    pos,
                                    getFormationBlock(
                                            worldY
                                    ),
                                    2
                            );
                        }
                    }
                }
            }
        }
    }


    /*
     * =========================================================
     * BLOCK TYPE
     * =========================================================
     *
     * Same basic idea as vanilla underground terrain:
     * deepslate below Y=0, stone above.
     */

    private BlockState getFormationBlock(
            int y
    ) {

        if (y < 0) {
            return Blocks.DEEPSLATE
                    .defaultBlockState();
        }

        return Blocks.STONE
                .defaultBlockState();
    }


    /*
     * =========================================================
     * FIND FLOOR
     * =========================================================
     */

    private int findSolidBelow(
            ChunkAccess chunk,
            int x,
            int startY,
            int z,
            int minY
    ) {

        for (int y = startY;
             y >= minY;
             y--) {

            if (!isInsideChunk(
                    chunk,
                    x,
                    z
            )) {
                return Integer.MIN_VALUE;
            }

            BlockState state =
                    chunk.getBlockState(
                            new BlockPos(
                                    x,
                                    y,
                                    z
                            )
                    );

            if (!state.isAir()
                    && state.getFluidState().isEmpty()) {

                return y;
            }
        }

        return Integer.MIN_VALUE;
    }


    /*
     * =========================================================
     * FIND CEILING
     * =========================================================
     */

    private int findSolidAbove(
            ChunkAccess chunk,
            int x,
            int startY,
            int z,
            int maxY
    ) {

        for (int y = startY;
             y <= maxY;
             y++) {

            if (!isInsideChunk(
                    chunk,
                    x,
                    z
            )) {
                return Integer.MAX_VALUE;
            }

            BlockState state =
                    chunk.getBlockState(
                            new BlockPos(
                                    x,
                                    y,
                                    z
                            )
                    );

            if (!state.isAir()
                    && state.getFluidState().isEmpty()) {

                return y;
            }
        }

        return Integer.MAX_VALUE;
    }


    /*
     * =========================================================
     * CHUNK BOUNDS
     * =========================================================
     */

    private boolean isInsideChunk(
            ChunkAccess chunk,
            int x,
            int z
    ) {

        return x >= chunk.getPos().getMinBlockX()
                && x <= chunk.getPos().getMaxBlockX()
                && z >= chunk.getPos().getMinBlockZ()
                && z <= chunk.getPos().getMaxBlockZ();
    }
}