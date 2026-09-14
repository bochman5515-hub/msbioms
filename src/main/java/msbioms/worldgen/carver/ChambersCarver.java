package msbioms.worldgen.carver;

import com.mojang.serialization.Codec;
import msbioms.MSBioms;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.carver.CarvingContext;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CaveWorldCarver;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.biome.Biome;

import java.util.function.Function;

import net.minecraft.core.Holder;

public class ChambersCarver extends CaveWorldCarver {

    public ChambersCarver(Codec<CaveCarverConfiguration> codec) {
        super(codec);
    }

    @Override
    protected void createRoom(
            CarvingContext context,
            CaveCarverConfiguration configuration,
            ChunkAccess chunk,
            Function<BlockPos, Holder<Biome>> biomeGetter,
            Aquifer aquifer,
            double x,
            double y,
            double z,
            float thickness,
            double yScale,
            net.minecraft.world.level.chunk.CarvingMask carvingMask,
            WorldCarver.CarveSkipChecker skipChecker
    ) {
        /*
         * Сначала полностью создаём обычную
         * ванильную комнату CaveWorldCarver.
         */
        super.createRoom(
                context,
                configuration,
                chunk,
                biomeGetter,
                aquifer,
                x,
                y,
                z,
                thickness,
                yScale,
                carvingMask,
                skipChecker
        );

        /*
         * Декорации создаём только для достаточно
         * крупных chamber.
         */
        if (thickness < 5.0F) {
            return;
        }

        /*
         * Декорации должны быть привязаны к центру
         * конкретной комнаты.
         */
        int centerX = (int) Math.floor(x);
        int centerY = (int) Math.floor(y);
        int centerZ = (int) Math.floor(z);

        /*
         * Чтобы одна и та же комната не получала
         * разные декорации при обработке соседних chunks.
         */
        long seed =
                ((long) centerX * 341873128712L)
                        ^ ((long) centerY * 132897987541L)
                        ^ ((long) centerZ * 42317861L);

        RandomSource random =
                RandomSource.create(seed);

        /*
         * Декорируем только тот chunk,
         * в котором находится центр комнаты.
         *
         * Это предотвращает многократную генерацию.
         */
        ChunkPos chunkPos = chunk.getPos();

        if (!chunkPos.contains(
                new BlockPos(centerX, centerY, centerZ)
        )) {
            return;
        }

        BlockPos center =
                new BlockPos(
                        centerX,
                        centerY,
                        centerZ
                );

        /*
         * Примерно 65% комнат получают озеро.
         */
        if (random.nextFloat() < 0.65F) {
            placeLake(
                    chunk,
                    random,
                    center
            );
        }

        /*
         * 70% комнат получают колонны.
         */
        if (random.nextFloat() < 0.70F) {
            placeColumns(
                    chunk,
                    random,
                    center,
                    thickness
            );
        }
    }

    /*
     * =========================================================
     * LAKE
     * =========================================================
     */

    private void placeLake(
            ChunkAccess chunk,
            RandomSource random,
            BlockPos center
    ) {
        /*
         * Высота воды строго 1–3 блока.
         */
        int lakeHeight =
                1 + random.nextInt(3);

        /*
         * Размер озера.
         */
        int radiusX =
                3 + random.nextInt(4);

        int radiusZ =
                3 + random.nextInt(4);

        /*
         * Ищем настоящий пол комнаты.
         */
        int floorY =
                findFloor(
                        chunk,
                        center
                );

        if (floorY == Integer.MIN_VALUE) {
            return;
        }

        int waterY =
                floorY + 1;

        /*
         * Не создаём озеро слишком близко
         * к потолку комнаты.
         */
        if (waterY + lakeHeight >= center.getY() + 5) {
            return;
        }

        BlockState water =
                Blocks.WATER.defaultBlockState();

        for (int x = -radiusX; x <= radiusX; x++) {

            for (int z = -radiusZ; z <= radiusZ; z++) {

                double nx =
                        (double) x / radiusX;

                double nz =
                        (double) z / radiusZ;

                /*
                 * Эллиптическая форма.
                 */
                if (nx * nx + nz * nz > 1.0) {
                    continue;
                }

                /*
                 * Небольшая естественная неровность.
                 */
                if (random.nextFloat() < 0.10F) {
                    continue;
                }

                for (int y = 0; y < lakeHeight; y++) {

                    BlockPos pos =
                            new BlockPos(
                                    center.getX() + x,
                                    waterY + y,
                                    center.getZ() + z
                            );

                    /*
                     * Не выходим из текущего chunk.
                     */
                    if (!chunk.getPos().contains(pos)) {
                        continue;
                    }

                    /*
                     * Вода ставится только в воздух.
                     */
                    if (chunk.getBlockState(pos)
                            .isAir()) {

                        chunk.setBlockState(
                                pos,
                                water,
                                2
                        );
                    }
                }
            }
        }
    }

    /*
     * =========================================================
     * COLUMNS
     * =========================================================
     */

    private void placeColumns(
            ChunkAccess chunk,
            RandomSource random,
            BlockPos center,
            float thickness
    ) {
        int count =
                1 + random.nextInt(3);

        for (int i = 0; i < count; i++) {

            int offsetX =
                    random.nextInt(
                            Math.max(
                                    5,
                                    (int) thickness
                            )
                    ) - 3;

            int offsetZ =
                    random.nextInt(
                            Math.max(
                                    5,
                                    (int) thickness
                            )
                    ) - 3;

            BlockPos base =
                    center.offset(
                            offsetX,
                            0,
                            offsetZ
                    );

            placeColumn(
                    chunk,
                    random,
                    base
            );
        }
    }

    private void placeColumn(
            ChunkAccess chunk,
            RandomSource random,
            BlockPos base
    ) {
        int floorY =
                findFloor(
                        chunk,
                        base
                );

        if (floorY == Integer.MIN_VALUE) {
            return;
        }

        int ceilingY =
                findCeiling(
                        chunk,
                        base
                );

        if (ceilingY == Integer.MIN_VALUE) {
            return;
        }

        int roomHeight =
                ceilingY - floorY - 1;

        /*
         * Слишком маленькие комнаты
         * не получают колонны.
         */
        if (roomHeight < 7) {
            return;
        }

        /*
         * Колонна занимает примерно
         * 35–75% высоты комнаты.
         */
        int height =
                Math.max(
                        3,
                        (int) (
                                roomHeight *
                                        (
                                                0.35 +
                                                        random.nextDouble() * 0.40
                                        )
                        )
                );

        int radius =
                1 + random.nextInt(2);

        BlockState rock =
                base.getY() < 0
                        ? Blocks.DEEPSLATE.defaultBlockState()
                        : Blocks.STONE.defaultBlockState();

        for (int y = 0; y < height; y++) {

            float progress =
                    (float) y /
                            Math.max(
                                    1,
                                    height - 1
                            );

            /*
             * Основание широкое,
             * верх постепенно сужается.
             */
            double currentRadius =
                    radius *
                            (1.0 - progress * 0.55);

            int r =
                    Math.max(
                            0,
                            (int) Math.ceil(
                                    currentRadius
                            )
                    );

            for (int x = -r; x <= r; x++) {

                for (int z = -r; z <= r; z++) {

                    if (x * x + z * z >
                            r * r + 1) {
                        continue;
                    }

                    /*
                     * Небольшая неровность.
                     */
                    if (random.nextFloat() < 0.10F) {
                        continue;
                    }

                    BlockPos pos =
                            new BlockPos(
                                    base.getX() + x,
                                    floorY + 1 + y,
                                    base.getZ() + z
                            );

                    if (!chunk.getPos().contains(pos)) {
                        continue;
                    }

                    /*
                     * Колонна не заменяет существующую
                     * воду или камень.
                     */
                    if (chunk.getBlockState(pos)
                            .isAir()) {

                        chunk.setBlockState(
                                pos,
                                rock,
                                2
                        );
                    }
                }
            }
        }
    }

    /*
     * =========================================================
     * FLOOR / CEILING
     * =========================================================
     */

    private int findFloor(
            ChunkAccess chunk,
            BlockPos start
    ) {
        for (int i = 1; i <= 20; i++) {

            int y =
                    start.getY() - i;

            if (y < chunk.getMinY()) {
                break;
            }

            BlockPos pos =
                    new BlockPos(
                            start.getX(),
                            y,
                            start.getZ()
                    );

            if (!chunk.getBlockState(pos)
                    .isAir()) {

                return y;
            }
        }

        return Integer.MIN_VALUE;
    }

    private int findCeiling(
            ChunkAccess chunk,
            BlockPos start
    ) {
        for (int i = 1; i <= 20; i++) {

            int y =
                    start.getY() + i;

            if (y >= chunk.getMaxY()) {
                break;
            }

            BlockPos pos =
                    new BlockPos(
                            start.getX(),
                            y,
                            start.getZ()
                    );

            if (!chunk.getBlockState(pos)
                    .isAir()) {

                return y;
            }
        }

        return Integer.MIN_VALUE;
    }
}