package msbioms.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import msbioms.block.ModBlocks;

public class ThornyVineFeature extends Feature<NoneFeatureConfiguration> {

    private static final int SEARCH_RADIUS = 10;

    private static final int MIN_VINES = 5;
    private static final int MAX_VINES = 11;

    private static final int MIN_LENGTH = 2;
    private static final int MAX_LENGTH = 7;

    public ThornyVineFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }
    private boolean isGloomyLeaves(BlockState state) {
        return state.is(ModBlocks.GLOOMY_LEAVES)
                || state.is(ModBlocks.GLOOMY_LEAVES_FLOVER);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {

        LevelAccessor level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        int vines = MIN_VINES +
                random.nextInt(MAX_VINES - MIN_VINES + 1);

        boolean placedAny = false;

        for (int i = 0; i < vines; i++) {

            int x = origin.getX()
                    + random.nextInt(SEARCH_RADIUS * 2 + 1)
                    - SEARCH_RADIUS;

            int y = origin.getY()
                    + random.nextInt(27);

            int z = origin.getZ()
                    + random.nextInt(SEARCH_RADIUS * 2 + 1)
                    - SEARCH_RADIUS;

            BlockPos start = new BlockPos(x, y, z);

            /*
             * Ищем листья Gloomy Tree.
             */
            if (!isGloomyLeaves(level.getBlockState(start))) {
                continue;
            }

            /*
             * Иногда ищем именно нижнюю часть кроны.
             * Это уменьшает количество лиан,
             * начинающихся глубоко внутри дерева.
             */
            if (isGloomyLeaves(level.getBlockState(start.below()))) {
                continue;
            }

            int length = MIN_LENGTH +
                    random.nextInt(MAX_LENGTH - MIN_LENGTH + 1);

            BlockPos current = start.below();

            int placedLength = 0;

            for (int j = 0; j < length; j++) {

                BlockState state = level.getBlockState(current);

                /*
                 * Лиана должна висеть в воздухе.
                 */
                if (!state.isAir()) {
                    break;
                }

                /*
                 * Все промежуточные блоки —
                 * Thorny Vine Plant.
                 */
                level.setBlock(
                        current,
                        ModBlocks.THORNY_VINE_PLANT.defaultBlockState(),
                        2
                );

                placedLength++;

                current = current.below();
            }

            /*
             * Последний блок цепочки должен быть HeadBlock.
             */
            if (placedLength > 0) {

                BlockPos headPos = current.above();

                /*
                 * Последний блок цепочки становится HeadBlock.
                 */
                if (level.getBlockState(headPos)
                        .is(ModBlocks.THORNY_VINE_PLANT)) {

                    level.setBlock(
                            headPos,
                            ModBlocks.THORNY_VINE.defaultBlockState(),
                            2
                    );

                    /*
                     * Фрукт появляется сразу при генерации.
                     * Он находится непосредственно под концом лианы.
                     */
                    BlockPos fruitPos = headPos.below();

                    if (level.getBlockState(fruitPos).isAir()) {
                        level.setBlock(
                                fruitPos,
                                ModBlocks.GLOWING_FRUIT.defaultBlockState(),
                                2
                        );
                    }

                    placedAny = true;
                }
            }
        }

        return placedAny;
    }
}