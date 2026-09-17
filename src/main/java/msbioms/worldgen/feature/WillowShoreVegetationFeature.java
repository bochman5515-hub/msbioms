package msbioms.worldgen.feature;

import com.mojang.serialization.Codec;
import msbioms.block.ModBlocks;
import msbioms.block.TallPlantPartBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;


public class WillowShoreVegetationFeature
        extends Feature<NoneFeatureConfiguration> {

    private static final int SEARCH_RADIUS = 10;
    private static final int ATTEMPTS = 50;

    public WillowShoreVegetationFeature(
            Codec<NoneFeatureConfiguration> codec
    ) {
        super(codec);
    }

    @Override
    public boolean place(
            FeaturePlaceContext<NoneFeatureConfiguration> context
    ) {
        WorldGenLevel level = context.level();
        RandomSource random = context.random();
        BlockPos origin = context.origin();

        int placed = 0;

        for (int i = 0; i < ATTEMPTS; i++) {

            int x = origin.getX()
                    + random.nextInt(SEARCH_RADIUS * 2 + 1)
                    - SEARCH_RADIUS;

            int z = origin.getZ()
                    + random.nextInt(SEARCH_RADIUS * 2 + 1)
                    - SEARCH_RADIUS;

            int surfaceY = level.getHeight(
                    Heightmap.Types.WORLD_SURFACE_WG,
                    x,
                    z
            );

            BlockPos surface = new BlockPos(x, surfaceY, z);

            /*
             * Ищем воду на поверхности.
             */
            if (!level.getFluidState(surface).is(FluidTags.WATER)) {
                continue;
            }

            /*
             * Ищем дно водоёма.
             */
            BlockPos.MutableBlockPos groundPos = surface.mutable();

            int depth = 0;

            while (
                    depth < 10
                            && level.getFluidState(groundPos).is(FluidTags.WATER)
            ) {
                groundPos.move(Direction.DOWN);
                depth++;
            }

            if (depth == 0 || depth > 10) {
                continue;
            }

            BlockState ground = level.getBlockState(groundPos);

            if (!isValidGround(ground)) {
                continue;
            }

            /*
             * Первый блок воды над грунтом.
             */
            BlockPos waterPos = groundPos.above();

            if (!level.getFluidState(waterPos).is(FluidTags.WATER)) {
                continue;
            }

            float roll = random.nextFloat();

            /*
             * Обычная водоросль.
             */
            if (roll < 0.50F) {

                if (placeSeagrass(level, waterPos, random)) {
                    placed++;
                }

                /*
                 * Высокая водоросль.
                 */
            } else if (roll < 0.68F) {

                if (placeTallSeagrass(level, waterPos)) {
                    placed++;
                }

                /*
                 * Кувшинка.
                 */
            } else if (roll < 0.83F) {

                if (placeLilyPad(level, surface)) {
                    placed++;
                }

                /*
                 * HIGH_GRASS.
                 */
            } else {

                if (placeHighGrass(level, waterPos)) {
                    placed++;
                }
            }
        }

        return placed > 0;
    }

    private static boolean isValidGround(BlockState state) {
        return state.is(Blocks.DIRT)
                || state.is(Blocks.GRASS_BLOCK)
                || state.is(Blocks.CLAY)
                || state.is(Blocks.MUD)
                || state.is(Blocks.SAND)
                || state.is(Blocks.GRAVEL)
                || state.is(ModBlocks.MOSS);
    }

    private static boolean placeSeagrass(
            WorldGenLevel level,
            BlockPos pos,
            RandomSource random
    ) {
        /*
         * Здесь ОБЯЗАТЕЛЬНО должна быть вода.
         */
        if (!level.getFluidState(pos).is(FluidTags.WATER)) {
            return false;
        }

        /*
         * Не ставим поверх уже существующего блока.
         */
        if (!level.getBlockState(pos).isAir()) {
            return false;
        }

        level.setBlock(
                pos,
                Blocks.SEAGRASS.defaultBlockState(),
                2
        );

        return true;
    }

    private static boolean placeTallSeagrass(
            WorldGenLevel level,
            BlockPos pos
    ) {
        if (!level.getFluidState(pos).is(FluidTags.WATER)) {
            return false;
        }

        BlockPos above = pos.above();

        /*
         * Для высокой водоросли оба блока должны быть в воде.
         */
        if (!level.getFluidState(above).is(FluidTags.WATER)) {
            return false;
        }

        if (
                !level.getBlockState(pos).isAir()
                        || !level.getBlockState(above).isAir()
        ) {
            return false;
        }

        DoublePlantBlock.placeAt(
                level,
                Blocks.TALL_SEAGRASS.defaultBlockState(),
                pos,
                2
        );

        return true;
    }

    private static boolean placeLilyPad(
            WorldGenLevel level,
            BlockPos pos
    ) {
        /*
         * Кувшинка находится НА поверхности воды,
         * поэтому сама позиция должна быть воздухом.
         */
        if (!level.getBlockState(pos).isAir()) {
            return false;
        }

        if (!level.getFluidState(pos.below()).is(FluidTags.WATER)) {
            return false;
        }

        level.setBlock(
                pos,
                Blocks.LILY_PAD.defaultBlockState(),
                2
        );

        return true;
    }

    private static boolean placeHighGrass(
            WorldGenLevel level,
            BlockPos pos
    ) {
        /*
         * Нижняя часть HIGH_GRASS находится в воде.
         */
        if (!level.getFluidState(pos).is(FluidTags.WATER)) {
            return false;
        }

        BlockPos upper = pos.above();

        /*
         * Верхняя часть может находиться либо в воде,
         * либо уже в воздухе.
         */
        if (
                !level.getFluidState(upper).is(FluidTags.WATER)
                        && !level.getBlockState(upper).isAir()
        ) {
            return false;
        }

        if (!level.getBlockState(pos).isAir()) {
            return false;
        }

        if (!level.getBlockState(upper).isAir()) {
            return false;
        }

        BlockState lowerState =
                ModBlocks.HIGH_GRASS.defaultBlockState()
                        .setValue(
                                TallPlantPartBlock.WATERLOGGED,
                                true
                        );

        BlockState upperState =
                ModBlocks.HIGH_GRASS_PLANT.defaultBlockState()
                        .setValue(
                                TallPlantPartBlock.WATERLOGGED,
                                level.getFluidState(upper)
                                        .is(FluidTags.WATER)
                        );

        level.setBlock(pos, lowerState, 2);
        level.setBlock(upper, upperState, 2);

        return true;
    }
}