package msbioms.worldgen.feature;

import com.mojang.serialization.Codec;
import msbioms.block.BogPlantBlock;
import msbioms.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class BogPlantFeature extends Feature<NoneFeatureConfiguration> {

    private static final int RADIUS = 3;
    private static final int ATTEMPTS = 40;

    public BogPlantFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(
            FeaturePlaceContext<NoneFeatureConfiguration> context
    ) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        int placed = 0;

        for (int i = 0; i < ATTEMPTS; i++) {

            int xOffset =
                    random.nextInt(RADIUS * 2 + 1) - RADIUS;

            int zOffset =
                    random.nextInt(RADIUS * 2 + 1) - RADIUS;

            int x = origin.getX() + xOffset;
            int z = origin.getZ() + zOffset;

            /*
             * Ищем поверхность воды.
             */
            int surfaceY = level.getHeight(
                    Heightmap.Types.WORLD_SURFACE_WG,
                    x,
                    z
            );

            BlockPos waterPos = null;

            /*
             * Ищем ближайший source water вниз
             * от поверхности.
             */
            for (int y = surfaceY; y >= surfaceY - 8; y--) {

                BlockPos checkPos =
                        new BlockPos(x, y, z);

                if (isFullWater(level, checkPos)) {
                    waterPos = checkPos;
                    break;
                }
            }

            if (waterPos == null) {
                continue;
            }

            /*
             * Под водой должен находиться ещё один
             * водяной блок.
             *
             * Это предотвращает генерацию на мелкой
             * или неподходящей поверхности.
             */
            if (!isFullWater(
                    level,
                    waterPos.below()
            )) {
                continue;
            }

            /*
             * Ряска находится НА поверхности воды,
             * а не внутри неё.
             */
            BlockPos plantPos =
                    waterPos.above();

            /*
             * Позиция растения должна быть свободной.
             */
            if (!level.getBlockState(plantPos).isAir()) {
                continue;
            }

            /*
             * Небольшое затухание по краям пятна.
             */
            int distanceSquared =
                    xOffset * xOffset
                            + zOffset * zOffset;

            if (distanceSquared > 4
                    && random.nextFloat() < 0.55F) {
                continue;
            }

            /*
             * Выбираем случайную стадию ряски.
             *
             * AGE 0 = 55%
             * AGE 1 = 30%
             * AGE 2 = 15%
             */
            int age = getRandomAge(random);

            BlockState bogPlant =
                    ModBlocks.BOG_PLANT
                            .defaultBlockState()
                            .setValue(
                                    BogPlantBlock.AGE,
                                    age
                            )
                            .setValue(
                                    BogPlantBlock.GROWTH,
                                    0
                            )
                            .setValue(
                                    BogPlantBlock.SINKING,
                                    0
                            );

            /*
             * Проверяем собственную механику
             * выживания BogPlantBlock.
             */
            if (!bogPlant.canSurvive(
                    level,
                    plantPos
            )) {
                continue;
            }

            /*
             * Ставим ряску НА воду.
             */
            level.setBlock(
                    plantPos,
                    bogPlant,
                    3
            );

            placed++;
        }

        return placed > 0;
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

    private static int getRandomAge(
            RandomSource random
    ) {
        float roll = random.nextFloat();

        if (roll < 0.55F) {
            return 0;
        }

        if (roll < 0.85F) {
            return 1;
        }

        return 2;
    }
}