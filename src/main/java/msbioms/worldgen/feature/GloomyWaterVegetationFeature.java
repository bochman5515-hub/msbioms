package msbioms.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class GloomyWaterVegetationFeature
        extends Feature<NoneFeatureConfiguration> {

    private static final int SEARCH_RADIUS = 8;

    private static final int SEAGRASS_WEIGHT = 80;
    private static final int TALL_SEAGRASS_WEIGHT = 20;

    private static final int TOTAL_WEIGHT =
            SEAGRASS_WEIGHT
                    + TALL_SEAGRASS_WEIGHT;

    private static final int ATTEMPTS = 20;

    public GloomyWaterVegetationFeature(
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

            int y = level.getHeight(
                    Heightmap.Types.OCEAN_FLOOR_WG,
                    x,
                    z
            );

            BlockPos pos =
                    new BlockPos(x, y, z);

            /*
             * На позиции должен быть твёрдый грунт.
             */
            BlockPos groundPos = pos.below();

            BlockState ground =
                    level.getBlockState(groundPos);

            if (!ground.isFaceSturdy(
                    level,
                    groundPos,
                    Direction.UP
            )) {
                continue;
            }

            /*
             * Растение должно находиться в воде.
             */
            if (!level.getFluidState(pos)
                    .isSource()) {
                continue;
            }

            if (placeRandomPlant(
                    level,
                    pos,
                    random
            )) {
                placed++;
            }
        }

        return placed > 0;
    }

    private boolean placeRandomPlant(
            WorldGenLevel level,
            BlockPos pos,
            RandomSource random
    ) {
        int value =
                random.nextInt(TOTAL_WEIGHT);

        if (value < SEAGRASS_WEIGHT) {
            return placeSingle(
                    level,
                    pos,
                    Blocks.SEAGRASS
            );
        }

        return placeTallSeagrass(
                level,
                pos
        );
    }

    private boolean placeSingle(
            WorldGenLevel level,
            BlockPos pos,
            net.minecraft.world.level.block.Block block
    ) {
        if (!level.getBlockState(pos).isAir()) {
            return false;
        }

        BlockState state =
                block.defaultBlockState();

        if (!state.canSurvive(level, pos)) {
            return false;
        }

        level.setBlock(
                pos,
                state,
                2
        );

        return true;
    }

    private boolean placeTallSeagrass(
            WorldGenLevel level,
            BlockPos pos
    ) {
        BlockPos upperPos =
                pos.above();

        /*
         * Обе позиции должны быть водой.
         */
        if (!level.getFluidState(pos).isSource()
                || !level.getFluidState(upperPos).isSource()) {
            return false;
        }

        BlockState state =
                Blocks.TALL_SEAGRASS.defaultBlockState();

        if (!state.canSurvive(level, pos)) {
            return false;
        }

        DoublePlantBlock.placeAt(
                level,
                state,
                pos,
                2
        );

        return true;
    }
}