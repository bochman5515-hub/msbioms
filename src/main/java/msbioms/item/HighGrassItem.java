package msbioms.item;

import msbioms.block.ModBlocks;
import msbioms.block.TallPlantPartBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

public class HighGrassItem extends Item {

    public HighGrassItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {

        Level level = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();

        boolean water =
                level.getFluidState(clickedPos)
                        .is(Fluids.WATER);

        BlockPos lowerPos;

        if (water) {
            // В воде нижняя часть занимает саму позицию воды
            lowerPos = clickedPos;

        } else {

            // На суше растение ставится НА блок
            if (context.getClickedFace() != Direction.UP) {
                return InteractionResult.FAIL;
            }

            BlockState clickedState =
                    level.getBlockState(clickedPos);

            boolean validGround =
                    clickedState.is(BlockTags.DIRT)
                            || clickedState.is(Blocks.GRASS_BLOCK)
                            || clickedState.is(Blocks.CLAY)
                            || clickedState.is(Blocks.MUD);

            if (!validGround) {
                return InteractionResult.FAIL;
            }

            lowerPos = clickedPos.above();
        }

        BlockPos upperPos = lowerPos.above();

        // Проверяем возможность размещения обеих частей
        if (!level.getBlockState(lowerPos).canBeReplaced()) {
            return InteractionResult.FAIL;
        }

        if (!level.getBlockState(upperPos).canBeReplaced()) {
            return InteractionResult.FAIL;
        }

        if (!level.isClientSide()) {

            /*
             * Каждая часть самостоятельно проверяет,
             * находится ли она в воде.
             */
            boolean lowerWater =
                    level.getFluidState(lowerPos)
                            .is(Fluids.WATER);

            boolean upperWater =
                    level.getFluidState(upperPos)
                            .is(Fluids.WATER);

            BlockState lowerState =
                    ModBlocks.HIGH_GRASS_PLANT
                            .defaultBlockState()
                            .setValue(
                                    TallPlantPartBlock.WATERLOGGED,
                                    lowerWater
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

            if (context.getPlayer() != null
                    && !context.getPlayer()
                    .getAbilities()
                    .instabuild) {

                context.getItemInHand().shrink(1);
            }
        }

        return InteractionResult.SUCCESS;
    }
}