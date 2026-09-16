package msbioms.worldgen.carver;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
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

public class ChambersCarver extends WorldCarver<CaveCarverConfiguration> {

    public ChambersCarver(Codec<CaveCarverConfiguration> codec) {
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
        double centerX = sourceChunkPos.getBlockX(random.nextInt(16));
        double centerY = configuration.y.sample(random, context);
        double centerZ = sourceChunkPos.getBlockZ(random.nextInt(16));

        double length = 120.0D + random.nextDouble() * 30.0D;
        double width = 45.0D + random.nextDouble() * 20.0D;
        double height = 16.0D + random.nextDouble() * 7.0D;

        double angle = random.nextDouble() * Math.PI * 2.0D;

        double dirX = Math.cos(angle);
        double dirZ = Math.sin(angle);

        double sideX = -dirZ;
        double sideZ = dirX;

        double noise1 = random.nextDouble() * Math.PI * 2.0D;
        double noise2 = random.nextDouble() * Math.PI * 2.0D;
        double noise3 = random.nextDouble() * Math.PI * 2.0D;
        double noise4 = random.nextDouble() * Math.PI * 2.0D;

        int minX = Math.max(
                Mth.floor(centerX - length * 0.5D - 5.0D),
                chunk.getPos().getMinBlockX()
        );

        int maxX = Math.min(
                Mth.ceil(centerX + length * 0.5D + 5.0D),
                chunk.getPos().getMaxBlockX()
        );

        int minY = Mth.floor(centerY - height * 0.5D - 4.0D);
        int maxY = Mth.ceil(centerY + height * 0.5D + 4.0D);

        int minZ = Math.max(
                Mth.floor(centerZ - width * 0.5D - 5.0D),
                chunk.getPos().getMinBlockZ()
        );

        int maxZ = Math.min(
                Mth.ceil(centerZ + width * 0.5D + 5.0D),
                chunk.getPos().getMaxBlockZ()
        );

        BlockPos.MutableBlockPos pos =
                new BlockPos.MutableBlockPos();

        BlockPos.MutableBlockPos checkPos =
                new BlockPos.MutableBlockPos();

        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {

                double dx = x - centerX;
                double dz = z - centerZ;

                double localX =
                        dx * dirX + dz * dirZ;

                double localZ =
                        dx * sideX + dz * sideZ;

                double nx =
                        localX / (length * 0.5D);

                double nz =
                        localZ / (width * 0.5D);

                if (Math.abs(nx) > 1.05D) {
                    continue;
                }

                /*
                 * Продольный профиль камеры.
                 *
                 * Центральная часть остаётся широкой,
                 * а торцы постепенно сужаются.
                 */
                double t = Math.abs(nx);

                double taper =
                        1.0D - smoothStep(
                                0.58D,
                                1.0D,
                                t
                        );

                /*
                 * Неровность стен.
                 *
                 * Здесь специально используется несколько
                 * разных масштабов, включая вертикальный.
                 */
                for (int y = minY; y <= maxY; y++) {

                    double ny =
                            (y - centerY) /
                                    (height * 0.5D);

                    /*
                     * Большая форма пола.
                     */
                    double floor =
                            -0.78D
                                    + Math.sin(nx * 4.2D + noise1) * 0.12D
                                    + Math.cos(nz * 5.0D + noise2) * 0.10D
                                    + Math.sin(nx * 8.0D + nz * 3.0D + noise3) * 0.055D
                                    + Math.sin(
                                    localX * 0.075D
                                            + localZ * 0.055D
                                            + noise4
                            ) * 0.10D;

                    /*
                     * Большая форма потолка.
                     */
                    double ceiling =
                            0.82D
                                    + Math.sin(nx * 4.0D + noise2) * 0.13D
                                    + Math.cos(nz * 5.5D + noise3) * 0.10D
                                    + Math.sin(nx * 9.0D + nz * 4.0D + noise1) * 0.055D
                                    + Math.cos(
                                    localX * 0.065D
                                            - localZ * 0.05D
                                            + noise4
                            ) * 0.09D;

                    if (ny <= floor || ny >= ceiling) {
                        continue;
                    }

                    /*
                     * Вертикальная позиция относительно
                     * текущей формы потолка.
                     */
                    double vertical =
                            ny / ceiling;

                    /*
                     * Чем ближе к потолку/полу,
                     * тем сильнее разрешаем неровность стен.
                     */
                    double verticalFactor =
                            1.0D + Math.abs(ny) * 0.18D;

                    /*
                     * Неровность стены.
                     *
                     * В отличие от старой версии она зависит
                     * от высоты, поэтому стена не образует
                     * одну огромную вертикальную плоскость.
                     */
                    double wallNoise =
                            Math.sin(
                                    nx * 5.0D
                                            + nz * 3.0D
                                            + noise1
                            ) * 0.075D

                                    + Math.cos(
                                    nx * 9.0D
                                            - nz * 5.0D
                                            + noise2
                            ) * 0.055D

                                    + Math.sin(
                                    nz * 8.0D
                                            + ny * 4.0D
                                            + noise3
                            ) * 0.065D

                                    + Math.cos(
                                    nx * 6.0D
                                            + ny * 7.0D
                                            + nz * 3.0D
                                            + noise4
                            ) * 0.045D

                                    + Math.sin(
                                    localX * 0.12D
                                            + localZ * 0.09D
                                            + y * 0.16D
                                            + noise2
                            ) * 0.035D;

                    /*
                     * Дополнительные крупные выступы.
                     */
                    double largeRelief =
                            Math.sin(
                                    localX * 0.045D
                                            + localZ * 0.035D
                                            + noise3
                            ) * 0.10D;

                    double radius =
                            1.0D
                                    + (wallNoise + largeRelief)
                                    * verticalFactor;

                    /*
                     * Реальная граница стены.
                     *
                     * Торцы сужаются через taper,
                     * но внутри остаётся естественная
                     * неоднородность.
                     */
                    double horizontalRadius =
                            (1.0D - taper * 0.05D)
                                    * radius;

                    double distance =
                            Math.abs(nz)
                                    / Math.max(
                                    0.08D,
                                    horizontalRadius
                            );

                    /*
                     * На концах камеры форма дополнительно
                     * становится менее симметричной.
                     */
                    if (t > 0.72D) {

                        double endNoise =
                                Math.sin(
                                        localX * 0.22D
                                                + localZ * 0.11D
                                                + y * 0.19D
                                                + noise1
                                ) * 0.08D;

                        distance += endNoise;
                    }

                    /*
                     * Эллиптическое вертикальное сечение.
                     */
                    double shape =
                            distance * distance
                                    + vertical * vertical;

                    if (shape >= 1.0D) {
                        continue;
                    }

                    /*
                     * Мелкая внутренняя неоднородность.
                     */
                    double detail =
                            Math.sin(
                                    localX * 0.17D
                                            + localZ * 0.13D
                                            + y * 0.25D
                                            + noise4
                            ) * 0.018D;

                    if (shape + detail >= 1.0D) {
                        continue;
                    }

                    pos.set(x, y, z);

                    this.carveBlock(
                            context,
                            configuration,
                            chunk,
                            biomeGetter,
                            carvingMask,
                            pos,
                            checkPos,
                            aquifer,
                            new MutableBoolean(false)
                    );
                }
            }
        }

        decorate(
                chunk,
                centerX,
                centerY,
                centerZ,
                length,
                width,
                height,
                dirX,
                dirZ,
                sideX,
                sideZ,
                random
        );

        System.out.println(
                "[MSBioms CHAMBER] CENTER X="
                        + Math.round(centerX)
                        + " Y="
                        + Math.round(centerY)
                        + " Z="
                        + Math.round(centerZ)
                        + " | SIZE="
                        + Math.round(length)
                        + "x"
                        + Math.round(width)
                        + "x"
                        + Math.round(height)
        );

        return true;
    }

    private void decorate(
            ChunkAccess chunk,
            double cx,
            double cy,
            double cz,
            double length,
            double width,
            double height,
            double dirX,
            double dirZ,
            double sideX,
            double sideZ,
            RandomSource random
    ) {
        /*
         * Озёра оставляем.
         */
        int lakes = 3 + random.nextInt(3);

        for (int i = 0; i < lakes; i++) {

            double distance =
                    (random.nextDouble() - 0.5D)
                            * length
                            * 0.72D;

            double side =
                    (random.nextDouble() - 0.5D)
                            * width
                            * 0.48D;

            int x = Mth.floor(
                    cx
                            + dirX * distance
                            + sideX * side
            );

            int z = Mth.floor(
                    cz
                            + dirZ * distance
                            + sideZ * side
            );

            createLake(
                    chunk,
                    x,
                    z,
                    5 + random.nextInt(6),
                    4 + random.nextInt(5),
                    2 + random.nextInt(2),
                    cy,
                    height
            );
        }

        /*
         * Теперь только один тип колонн:
         * сросшиеся пол ↔ потолок.
         *
         * Они редкие.
         */
        int columns = 3 + random.nextInt(3);

        for (int i = 0; i < columns; i++) {

            double distance =
                    (random.nextDouble() - 0.5D)
                            * length
                            * 0.70D;

            double side =
                    (random.nextDouble() - 0.5D)
                            * width
                            * 0.58D;

            int x = Mth.floor(
                    cx
                            + dirX * distance
                            + sideX * side
            );

            int z = Mth.floor(
                    cz
                            + dirZ * distance
                            + sideZ * side
            );

            createFusedColumn(
                    chunk,
                    x,
                    z,
                    cy,
                    height,
                    random
            );
        }
    }

    private void createFusedColumn(
            ChunkAccess chunk,
            int cx,
            int cz,
            double cy,
            double chamberHeight,
            RandomSource random
    ) {
        if (!isInsideChunk(chunk, cx, cz)) {
            return;
        }

        int floorY = findFloor(
                chunk,
                cx,
                cz,
                Mth.floor(cy - chamberHeight * 0.05D),
                Mth.floor(cy - chamberHeight * 0.70D)
        );

        if (floorY == Integer.MIN_VALUE) {
            return;
        }

        int ceilingY = findCeiling(
                chunk,
                cx,
                cz,
                Mth.floor(cy + chamberHeight * 0.05D),
                Mth.floor(cy + chamberHeight * 0.70D)
        );

        if (ceilingY == Integer.MIN_VALUE) {
            return;
        }

        int totalHeight = ceilingY - floorY;

        /*
         * Слишком низкие образования не делаем.
         */
        if (totalHeight < 8) {
            return;
        }

        /*
         * Случайный небольшой изгиб ствола.
         */
        double bendX =
                random.nextDouble() * Math.PI * 2.0D;

        double bendZ =
                random.nextDouble() * Math.PI * 2.0D;

        /*
         * Радиус основания.
         */
        double baseRadius =
                1.5D + random.nextDouble() * 1.5D;

        BlockState stone =
                floorY <= 0
                        ? Blocks.DEEPSLATE.defaultBlockState()
                        : Blocks.STONE.defaultBlockState();

        BlockPos.MutableBlockPos pos =
                new BlockPos.MutableBlockPos();

        for (int y = floorY; y <= ceilingY; y++) {

            double progress =
                    (y - floorY)
                            / (double) totalHeight;

            /*
             * Внизу и наверху колонна широкая,
             * в центре значительно тоньше.
             *
             * Получаем форму:
             *
             * ████
             *  ███
             *   ██
             *   ██
             *  ███
             * ████
             */
            double edge =
                    Math.sin(progress * Math.PI);

            double radius =
                    baseRadius
                            * (0.48D + edge * 0.52D);

            /*
             * Небольшая природная вариация радиуса.
             */
            radius +=
                    Math.sin(
                            y * 0.55D
                                    + bendX
                    ) * 0.25D;

            radius = Math.max(
                    1.0D,
                    radius
            );

            /*
             * Лёгкий изгиб.
             */
            int offsetX = Mth.floor(
                    Math.sin(
                            progress * Math.PI
                                    + bendX
                    ) * 1.25D
            );

            int offsetZ = Mth.floor(
                    Math.cos(
                            progress * Math.PI
                                    + bendZ
                    ) * 1.25D
            );

            int centerX = cx + offsetX;
            int centerZ = cz + offsetZ;

            int radiusInt =
                    Mth.ceil(radius) + 1;

            for (int x = centerX - radiusInt;
                 x <= centerX + radiusInt;
                 x++) {

                for (int z = centerZ - radiusInt;
                     z <= centerZ + radiusInt;
                     z++) {

                    if (!isInsideChunk(chunk, x, z)) {
                        continue;
                    }

                    double dx =
                            x - centerX;

                    double dz =
                            z - centerZ;

                    /*
                     * Немного вытягиваем сечение.
                     */
                    double irregular =
                            Math.sin(
                                    x * 0.72D
                                            + z * 0.43D
                                            + y * 0.21D
                            ) * 0.18D;

                    double distance =
                            dx * dx
                                    + dz * dz;

                    double allowed =
                            radius
                                    + irregular;

                    if (distance >
                            allowed * allowed) {
                        continue;
                    }

                    pos.set(x, y, z);

                    /*
                     * Очень важно:
                     * колонна только заполняет воздух.
                     * Она не уничтожает окружающий камень.
                     */
                    if (chunk.getBlockState(pos).isAir()) {
                        chunk.setBlockState(
                                pos,
                                stone,
                                2
                        );
                    }
                }
            }
        }
    }

    private int findFloor(
            ChunkAccess chunk,
            int x,
            int z,
            int startY,
            int minY
    ) {
        if (!isInsideChunk(chunk, x, z)) {
            return Integer.MIN_VALUE;
        }

        BlockPos.MutableBlockPos pos =
                new BlockPos.MutableBlockPos();

        for (int y = startY; y >= minY; y--) {

            pos.set(x, y, z);

            if (!chunk.getBlockState(pos).isAir()) {
                continue;
            }

            pos.set(x, y - 1, z);

            BlockState below =
                    chunk.getBlockState(pos);

            if (!below.isAir()
                    && below.getFluidState().isEmpty()) {
                return y;
            }
        }

        return Integer.MIN_VALUE;
    }

    private int findCeiling(
            ChunkAccess chunk,
            int x,
            int z,
            int startY,
            int maxY
    ) {
        if (!isInsideChunk(chunk, x, z)) {
            return Integer.MIN_VALUE;
        }

        BlockPos.MutableBlockPos pos =
                new BlockPos.MutableBlockPos();

        for (int y = startY; y <= maxY; y++) {

            pos.set(x, y, z);

            if (!chunk.getBlockState(pos).isAir()) {
                return y;
            }
        }

        return Integer.MIN_VALUE;
    }

    private void createLake(
            ChunkAccess chunk,
            int cx,
            int cz,
            int rx,
            int rz,
            int depth,
            double cy,
            double chamberHeight
    ) {
        int floorY = findFloor(
                chunk,
                cx,
                cz,
                Mth.floor(
                        cy - chamberHeight * 0.10D
                ),
                Mth.floor(
                        cy - chamberHeight * 0.70D
                )
        );

        if (floorY == Integer.MIN_VALUE) {
            return;
        }

        int waterSurface =
                floorY - 1;

        BlockPos.MutableBlockPos pos =
                new BlockPos.MutableBlockPos();

        for (int x = cx - rx;
             x <= cx + rx;
             x++) {

            for (int z = cz - rz;
                 z <= cz + rz;
                 z++) {

                if (!isInsideChunk(chunk, x, z)) {
                    continue;
                }

                double dx =
                        (x - cx) / (double) rx;

                double dz =
                        (z - cz) / (double) rz;

                double edge =
                        Math.sin(
                                x * 0.63D
                                        + z * 0.31D
                        ) * 0.08D
                                + Math.cos(
                                x * 0.27D
                                        - z * 0.51D
                        ) * 0.05D;

                if (dx * dx
                        + dz * dz
                        + edge > 1.0D) {
                    continue;
                }

                double centerDistance =
                        Math.sqrt(
                                dx * dx
                                        + dz * dz
                        );

                int localDepth =
                        centerDistance > 0.65D
                                ? 1
                                : depth;

                for (
                        int y = waterSurface - localDepth + 1;
                        y <= waterSurface;
                        y++
                ) {
                    pos.set(x, y, z);

                    chunk.setBlockState(
                            pos,
                            Blocks.WATER.defaultBlockState(),
                            2
                    );
                }
            }
        }
    }

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

    private double smoothStep(
            double edge0,
            double edge1,
            double value
    ) {
        double t =
                Mth.clamp(
                        (value - edge0)
                                / (edge1 - edge0),
                        0.0D,
                        1.0D
                );

        return t * t * (3.0D - 2.0D * t);
    }
}