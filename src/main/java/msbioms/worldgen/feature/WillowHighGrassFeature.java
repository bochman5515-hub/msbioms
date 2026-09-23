package msbioms.worldgen.feature;

import com.mojang.serialization.Codec;
import msbioms.block.ModBlocks;
import msbioms.block.TallPlantPartBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class WillowHighGrassFeature
        extends Feature<NoneFeatureConfiguration> {

    public WillowHighGrassFeature(
            Codec<NoneFeatureConfiguration> codec
    ) {
        super(codec);
    }

    @Override
    public boolean place(
            FeaturePlaceContext<NoneFeatureConfiguration> context
    ) {
        WorldGenLevel level = context.level();

        BlockPos lowerPos = context.origin();
        BlockPos upperPos = lowerPos.above();

        if (!isFullWater(level, lowerPos)) {
            return false;
        }

        if (!isValidGround(
                level.getBlockState(lowerPos.below())
        )) {
            return false;
        }

        boolean upperWater =
                isFullWater(level, upperPos);

        if (!upperWater
                && !level.getBlockState(upperPos).isAir()) {
            return false;
        }

        BlockState lowerState =
                ModBlocks.HIGH_GRASS_PLANT
                        .defaultBlockState()
                        .setValue(
                                TallPlantPartBlock.WATERLOGGED,
                                true
                        );

        BlockState upperState =
                ModBlocks.HIGH_GRASS
                        .defaultBlockState()
                        .setValue(
                                TallPlantPartBlock.WATERLOGGED,
                                upperWater
                        );

        level.setBlock(
                lowerPos,
                lowerState,
                3
        );

        level.setBlock(
                upperPos,
                upperState,
                3
        );

        return true;
    }

    /*
     * =============================================================
     * Одна попытка поставить растение.
     * =============================================================
     */

    private static boolean tryPlace(
            WorldGenLevel level,
            BlockPos lowerPos
    ) {
        BlockPos upperPos =
                lowerPos.above();

        /*
         * Нижняя часть должна находиться
         * в source water.
         */
        if (!isFullWater(
                level,
                lowerPos
        )) {
            return false;
        }

        /*
         * Под водой должен быть подходящий грунт.
         */
        if (!isValidGround(
                level.getBlockState(
                        lowerPos.below()
                )
        )) {
            return false;
        }

        /*
         * Верхняя часть должна быть водой
         * или воздухом.
         */
        boolean upperWater =
                isFullWater(
                        level,
                        upperPos
                );

        if (!upperWater
                && !level.getBlockState(
                upperPos
        ).isAir()) {
            return false;
        }

        /*
         * =====================================================
         * СОСТОЯНИЯ
         * =====================================================
         */

        BlockState lowerState =
                ModBlocks.HIGH_GRASS_PLANT
                        .defaultBlockState()
                        .setValue(
                                TallPlantPartBlock.WATERLOGGED,
                                true
                        );

        BlockState upperState =
                ModBlocks.HIGH_GRASS
                        .defaultBlockState()
                        .setValue(
                                TallPlantPartBlock.WATERLOGGED,
                                upperWater
                        );

        /*
         * =====================================================
         * УСТАНОВКА
         * =====================================================
         */

        level.setBlock(
                lowerPos,
                lowerState,
                3
        );

        level.setBlock(
                upperPos,
                upperState,
                3
        );

        return true;
    }

    private static boolean isFullWater(
            WorldGenLevel level,
            BlockPos pos
    ) {
        return level.getFluidState(pos)
                .is(FluidTags.WATER)
                && level.getFluidState(pos)
                .isSource();
    }

    private static boolean isValidGround(
            BlockState state
    ) {
        return state.is(BlockTags.DIRT)
                || state.is(Blocks.GRASS_BLOCK)
                || state.is(Blocks.CLAY)
                || state.is(Blocks.MUD)
                || state.is(Blocks.SAND)
                || state.is(Blocks.GRAVEL)
                || state.is(Blocks.MOSS_BLOCK)
                || state.is(ModBlocks.MOSS);
    }
}