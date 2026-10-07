package msbioms.worldgen.feature;

import com.mojang.serialization.Codec;
import msbioms.block.ModBlocks;
import msbioms.block.MossCarpetBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class GloomyGroundVegetationFeature
        extends Feature<NoneFeatureConfiguration> {

    private static final int SEARCH_RADIUS = 8;

    /*
     * Чем больше число, тем чаще растение выбирается.
     */

    private static final int FERN_WEIGHT = 20;
    private static final int SHORT_GRASS_WEIGHT = 16;
    private static final int SHORT_DRY_GRASS_WEIGHT = 10;
    private static final int TALL_DRY_GRASS_WEIGHT = 8;
    private static final int BUSH_WEIGHT = 10;
    private static final int MOSS_CARPET_WEIGHT = 12;
    private static final int BROWN_MUSHROOM_WEIGHT = 5;
    private static final int CLOSED_EYEBLOSSOM_WEIGHT = 4;
    private static final int DEAD_BUSH_WEIGHT = 3;

    private static final int TOTAL_WEIGHT =
            FERN_WEIGHT
                    + SHORT_GRASS_WEIGHT
                    + SHORT_DRY_GRASS_WEIGHT
                    + TALL_DRY_GRASS_WEIGHT
                    + BUSH_WEIGHT
                    + MOSS_CARPET_WEIGHT
                    + BROWN_MUSHROOM_WEIGHT
                    + CLOSED_EYEBLOSSOM_WEIGHT
                    + DEAD_BUSH_WEIGHT;

    /*
     * Количество попыток разместить растения
     * за один вызов Feature.
     */
    private static final int ATTEMPTS = 14;

    /*
     * Отдельные редкие попытки разместить мох
     * на деревьях.
     */
    private static final int TREE_MOSS_ATTEMPTS = 2;

    public GloomyGroundVegetationFeature(
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

        /*
         * Основная растительность на земле.
         */
        for (int i = 0; i < ATTEMPTS; i++) {

            int x = origin.getX()
                    + random.nextInt(SEARCH_RADIUS * 2 + 1)
                    - SEARCH_RADIUS;

            int z = origin.getZ()
                    + random.nextInt(SEARCH_RADIUS * 2 + 1)
                    - SEARCH_RADIUS;

            int y = level.getHeight(
                    Heightmap.Types.WORLD_SURFACE_WG,
                    x,
                    z
            );

            BlockPos pos = new BlockPos(x, y, z);

            if (!level.getBlockState(pos).isAir()) {
                continue;
            }

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

        /*
         * Немного мохового коврика на деревьях.
         */
        placed += placeTreeMoss(
                level,
                origin,
                random
        );

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
         * КОРОТКАЯ ТРАВА
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
         * КОРОТКАЯ СУХАЯ ТРАВА
         */
        current += SHORT_DRY_GRASS_WEIGHT;

        if (value < current) {
            return placeSingle(
                    level,
                    pos,
                    Blocks.SHORT_DRY_GRASS
            );
        }

        /*
         * ВЫСОКАЯ СУХАЯ ТРАВА
         */
        current += TALL_DRY_GRASS_WEIGHT;

        if (value < current) {
            return placeSingle(
                    level,
                    pos,
                    Blocks.TALL_DRY_GRASS
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
         * МОХОВОЙ КОВРИК
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
         * КОРИЧНЕВЫЙ ГРИБ
         */
        current += BROWN_MUSHROOM_WEIGHT;

        if (value < current) {
            return placeSingle(
                    level,
                    pos,
                    Blocks.BROWN_MUSHROOM
            );
        }

        /*
         * ЗАКРЫТАЯ ГЛАЗАЛИЯ
         */
        current += CLOSED_EYEBLOSSOM_WEIGHT;

        if (value < current) {
            return placeSingle(
                    level,
                    pos,
                    Blocks.CLOSED_EYEBLOSSOM
            );
        }

        /*
         * МЁРТВЫЙ КУСТ
         */
        return placeSingle(
                level,
                pos,
                Blocks.DEAD_BUSH
        );
    }

    private boolean placeSingle(
            WorldGenLevel level,
            BlockPos pos,
            Block block
    ) {
        if (!level.getBlockState(pos).isAir()) {
            return false;
        }

        BlockState state =
                block.defaultBlockState();

        if (!state.canSurvive(level, pos)) {
            return false;
        }

        /*
         * Для Moss Carpet нужно вручную
         * пересчитать свисающие стороны,
         * потому что worldgen не вызывает
         * getStateForPlacement().
         */
        if (block == ModBlocks.MOSS_CARPET) {
            state = MossCarpetBlock.updateAllSides(
                    state,
                    level,
                    pos
            );
        }

        level.setBlock(
                pos,
                state,
                2
        );

        return true;
    }

    private int placeTreeMoss(
            WorldGenLevel level,
            BlockPos origin,
            RandomSource random
    ) {
        int placed = 0;

        for (int i = 0; i < TREE_MOSS_ATTEMPTS; i++) {

            int x = origin.getX()
                    + random.nextInt(SEARCH_RADIUS * 2 + 1)
                    - SEARCH_RADIUS;

            int z = origin.getZ()
                    + random.nextInt(SEARCH_RADIUS * 2 + 1)
                    - SEARCH_RADIUS;

            int y = level.getHeight(
                    Heightmap.Types.WORLD_SURFACE_WG,
                    x,
                    z
            );

            /*
             * Ищем несколько блоков вверх,
             * чтобы найти верх деревьев.
             */
            for (int dy = 0; dy <= 12; dy++) {

                BlockPos supportPos =
                        new BlockPos(x, y + dy, z);

                BlockState support =
                        level.getBlockState(supportPos);

                if (!isGloomyTreeBlock(support)) {
                    continue;
                }

                BlockPos mossPos =
                        supportPos.above();

                if (!level.getBlockState(mossPos).isAir()) {
                    continue;
                }

                if (placeSingle(
                        level,
                        mossPos,
                        ModBlocks.MOSS_CARPET
                )) {
                    placed++;
                }

                break;
            }
        }

        return placed;
    }

    private boolean isGloomyTreeBlock(
            BlockState state
    ) {
        return state.is(ModBlocks.GLOOMY_LOG)
                || state.is(ModBlocks.GLOOMY_WOOD)
                || state.is(ModBlocks.GLOOMY_LEAVES)
                || state.is(ModBlocks.GLOOMY_LEAVES_FLOVER);
    }
}