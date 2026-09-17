package msbioms.worldgen.feature;

import com.mojang.serialization.Codec;
import msbioms.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class WillowGroundVegetationFeature
        extends Feature<NoneFeatureConfiguration> {

    private static final int SEARCH_RADIUS = 8;

    /*
     * Чем больше число, тем чаще растение выбирается.
     */
    private static final int FERN_WEIGHT = 20;
    private static final int SHORT_GRASS_WEIGHT = 9;
    private static final int MOSS_CARPET_WEIGHT = 14;
    private static final int TALL_GRASS_WEIGHT = 12;
    private static final int BUSH_WEIGHT = 10;
    private static final int LARGE_FERN_WEIGHT = 7;
    private static final int FIREFLY_BUSH_WEIGHT = 5;
    private static final int SMALL_DRIPLEAF_WEIGHT = 5;
    private static final int BLUE_ORCHID_WEIGHT = 4;
    private static final int PITCHER_PLANT_WEIGHT = 2;

    private static final int TOTAL_WEIGHT =
            FERN_WEIGHT
                    + SHORT_GRASS_WEIGHT
                    + MOSS_CARPET_WEIGHT
                    + TALL_GRASS_WEIGHT
                    + BUSH_WEIGHT
                    + LARGE_FERN_WEIGHT
                    + FIREFLY_BUSH_WEIGHT
                    + SMALL_DRIPLEAF_WEIGHT
                    + BLUE_ORCHID_WEIGHT
                    + PITCHER_PLANT_WEIGHT;

    /*
     * Количество попыток разместить растения
     * за один вызов Feature.
     */
    private static final int ATTEMPTS = 12;

    public WillowGroundVegetationFeature(
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
                    + random.nextInt(
                    SEARCH_RADIUS * 2 + 1
            )
                    - SEARCH_RADIUS;

            int z = origin.getZ()
                    + random.nextInt(
                    SEARCH_RADIUS * 2 + 1
            )
                    - SEARCH_RADIUS;

            int y = level.getHeight(
                    Heightmap.Types.WORLD_SURFACE_WG,
                    x,
                    z
            );

            BlockPos pos =
                    new BlockPos(x, y, z);

            /*
             * В выбранной позиции должно быть свободно.
             */
            if (!level.getBlockState(pos).isAir()) {
                continue;
            }

            /*
             * Под растением должен быть нормальный
             * твёрдый блок.
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

        int current = 0;

        /*
         * ПАПОРОТНИК
         */
        current += FERN_WEIGHT;

        if (value < current) {
            return placeSingle(
                    level,
                    pos,
                    Blocks.FERN
            );
        }

        /*
         * НИЗКАЯ ТРАВА
         */
        current += SHORT_GRASS_WEIGHT;

        if (value < current) {
            return placeSingle(
                    level,
                    pos,
                    Blocks.SHORT_GRASS
            );
        }

        /*
         * НАШ MOSS_CARPET
         */
        current += MOSS_CARPET_WEIGHT;

        if (value < current) {
            return placeSingle(
                    level,
                    pos,
                    ModBlocks.MOSS_CARPET
            );
        }

        /*
         * ВЫСОКАЯ ТРАВА
         */
        current += TALL_GRASS_WEIGHT;

        if (value < current) {
            return placeDouble(
                    level,
                    pos,
                    Blocks.TALL_GRASS
            );
        }

        /*
         * КУСТ
         */
        current += BUSH_WEIGHT;

        if (value < current) {
            return placeSingle(
                    level,
                    pos,
                    Blocks.BUSH
            );
        }

        /*
         * БОЛЬШОЙ ПАПОРОТНИК
         */
        current += LARGE_FERN_WEIGHT;

        if (value < current) {
            return placeDouble(
                    level,
                    pos,
                    Blocks.LARGE_FERN
            );
        }

        /*
         * КУСТ СО СВЕТЛЯЧКАМИ
         */
        current += FIREFLY_BUSH_WEIGHT;

        if (value < current) {
            return placeSingle(
                    level,
                    pos,
                    Blocks.FIREFLY_BUSH
            );
        }

        /*
         * МАЛЕНЬКИЙ ДРИПЛИФ
         */
        current += SMALL_DRIPLEAF_WEIGHT;

        if (value < current) {
            return placeDouble(
                    level,
                    pos,
                    Blocks.SMALL_DRIPLEAF
            );
        }

        /*
         * СИНЯЯ ОРХИДЕЯ
         */
        current += BLUE_ORCHID_WEIGHT;

        if (value < current) {
            return placeSingle(
                    level,
                    pos,
                    Blocks.BLUE_ORCHID
            );
        }

        /*
         * КУВШИНИЦА
         */
        return placeDouble(
                level,
                pos,
                Blocks.PITCHER_PLANT
        );
    }

    private boolean placeSingle(
            WorldGenLevel level,
            BlockPos pos,
            Block block
    ) {
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

    private boolean placeDouble(
            WorldGenLevel level,
            BlockPos pos,
            Block block
    ) {
        BlockPos upperPos =
                pos.above();

        /*
         * Оба места должны быть свободны.
         */
        if (!level.getBlockState(pos).isAir()
                || !level.getBlockState(upperPos).isAir()) {
            return false;
        }

        BlockState state =
                block.defaultBlockState();

        /*
         * Проверяем возможность нижней части.
         */
        if (!state.canSurvive(level, pos)) {
            return false;
        }

        /*
         * DoublePlantBlock сам корректно
         * создаёт нижнюю и верхнюю части.
         */
        DoublePlantBlock.placeAt(
                level,
                state,
                pos,
                2
        );

        return true;
    }
}