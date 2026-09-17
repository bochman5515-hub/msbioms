package msbioms.worldgen.feature;

import com.mojang.serialization.Codec;
import msbioms.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class WillowVineFeature extends Feature<NoneFeatureConfiguration> {

    private static final int SEARCH_RADIUS = 10;

    /*
     * Основная масса лиан будет длиной 2–6 блоков.
     */
    private static final int MIN_LENGTH = 2;
    private static final int MAX_LENGTH = 6;

    /*
     * Редкие длинные лианы.
     */
    private static final float LONG_VINE_CHANCE = 0.12F;
    private static final int LONG_VINE_MAX_LENGTH = 9;

    /*
     * Сколько лиан пытается создать один вызов Feature.
     */
    private static final int VINES_PER_FEATURE_MIN = 6;
    private static final int VINES_PER_FEATURE_MAX = 12;

    /*
     * Вероятность появления ванильной лианы
     * на найденном участке кроны.
     */
    private static final float VANILLA_VINE_CHANCE = 0.75F;

    public WillowVineFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        return false;
    }

    @Override
    public boolean place(
            NoneFeatureConfiguration config,
            WorldGenLevel level,
            net.minecraft.world.level.chunk.ChunkGenerator generator,
            RandomSource random,
            BlockPos origin
    ) {
        int surfaceY = level.getHeight(
                Heightmap.Types.WORLD_SURFACE_WG,
                origin.getX(),
                origin.getZ()
        );

        int centerY = Math.max(origin.getY(), surfaceY);

        int amount =
                VINES_PER_FEATURE_MIN
                        + random.nextInt(
                        VINES_PER_FEATURE_MAX
                                - VINES_PER_FEATURE_MIN
                                + 1
                );

        int placed = 0;

        for (int i = 0; i < amount; i++) {

            int x = origin.getX()
                    + random.nextInt(SEARCH_RADIUS * 2 + 1)
                    - SEARCH_RADIUS;

            int z = origin.getZ()
                    + random.nextInt(SEARCH_RADIUS * 2 + 1)
                    - SEARCH_RADIUS;

            int minY = centerY + 1;
            int maxY = centerY + 26;

            if (maxY <= minY) {
                continue;
            }

            int y = minY + random.nextInt(maxY - minY + 1);

            BlockPos leafPos = new BlockPos(x, y, z);

            if (!level.getBlockState(leafPos)
                    .is(ModBlocks.WILLOW_LEAVES)) {
                continue;
            }

            /*
             * Иногда добавляем обычные ванильные лианы
             * непосредственно к краям кроны.
             */
            if (random.nextFloat() < VANILLA_VINE_CHANCE) {
                placeVanillaVines(level, leafPos, random);
            }

            /*
             * Начинаем свисающую ивовую лиану.
             *
             * ВАЖНО:
             * первый блок под листьями — BODY,
             * последний блок — HEAD.
             */
            BlockPos.MutableBlockPos mutable =
                    leafPos.below().mutable();

            if (!canPlaceVine(level, mutable)) {
                continue;
            }

            int length = MIN_LENGTH
                    + random.nextInt(
                    MAX_LENGTH - MIN_LENGTH + 1
            );

            if (random.nextFloat() < LONG_VINE_CHANCE) {
                length = MAX_LENGTH
                        + random.nextInt(
                        LONG_VINE_MAX_LENGTH
                                - MAX_LENGTH
                                + 1
                );
            }

            /*
             * Сначала ставим BODY.
             */
            int bodyCount = length - 1;

            for (int j = 0; j < bodyCount; j++) {

                if (!canPlaceVine(level, mutable)) {
                    break;
                }

                level.setBlock(
                        mutable,
                        ModBlocks.WILLOW_VINE_PLANT.defaultBlockState(),
                        2
                );

                mutable.move(Direction.DOWN);
            }

            /*
             * Теперь на самом кончике ставим HEAD.
             *
             * Именно этот блок будет пытаться расти вниз.
             */
            if (canPlaceVine(level, mutable)) {

                level.setBlock(
                        mutable,
                        ModBlocks.WILLOW_VINE.defaultBlockState(),
                        2
                );

                placed++;
            }
        }

        return placed > 0;
    }

    private static boolean canPlaceVine(
            WorldGenLevel level,
            BlockPos pos
    ) {
        BlockState state = level.getBlockState(pos);

        return state.isAir() && !state.liquid();
    }

    private static void placeVanillaVines(
            WorldGenLevel level,
            BlockPos leafPos,
            RandomSource random
    ) {
        /*
         * Проверяем несколько сторон, а не одну.
         * Поэтому у крупных крон ванильные лианы
         * будут заметно присутствовать.
         */
        for (Direction direction :
                Direction.Plane.HORIZONTAL) {

            /*
             * Не заполняем абсолютно все стороны.
             */
            if (random.nextFloat() > 0.35F) {
                continue;
            }

            BlockPos vinePos =
                    leafPos.relative(direction);

            if (!level.getBlockState(vinePos).isAir()) {
                continue;
            }

            BlockState vineState =
                    Blocks.VINE.defaultBlockState();

            switch (direction) {
                case NORTH -> vineState =
                        vineState.setValue(
                                VineBlock.SOUTH,
                                true
                        );

                case SOUTH -> vineState =
                        vineState.setValue(
                                VineBlock.NORTH,
                                true
                        );

                case EAST -> vineState =
                        vineState.setValue(
                                VineBlock.WEST,
                                true
                        );

                case WEST -> vineState =
                        vineState.setValue(
                                VineBlock.EAST,
                                true
                        );

                default -> {
                    continue;
                }
            }

            level.setBlock(
                    vinePos,
                    vineState,
                    2
            );
        }
    }
}