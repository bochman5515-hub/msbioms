package msbioms.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;

public class BogBlock extends LeavesBlock {

    public BogBlock(Properties properties) {
        super(0.01F, properties);

        registerDefaultState(
                stateDefinition.any()
                        .setValue(DISTANCE, 7)
                        .setValue(PERSISTENT, true)
                        .setValue(WATERLOGGED, true)
        );
    }

    @Override
    protected boolean canSurvive(
            BlockState state,
            LevelReader level,
            BlockPos pos
    ) {
        BlockState above = level.getBlockState(pos.above());

        return above.is(ModBlocks.BOG)
                || above.is(ModBlocks.BOG_PLANT);
    }

    @Override
    public MapCodec<? extends LeavesBlock> codec() {
        return null;
    }

    @Override
    protected BlockState updateShape(
            BlockState state,
            LevelReader level,
            ScheduledTickAccess ticks,
            BlockPos pos,
            Direction directionToNeighbour,
            BlockPos neighbourPos,
            BlockState neighbourState,
            RandomSource random
    ) {
        if (state.getValue(WATERLOGGED)) {
            ticks.scheduleTick(
                    pos,
                    net.minecraft.world.level.material.Fluids.WATER,
                    net.minecraft.world.level.material.Fluids.WATER.getTickDelay(level)
            );
        }

        if (directionToNeighbour == Direction.UP
                && !canSurvive(state, level, pos)) {
            return Blocks.AIR.defaultBlockState();
        }

        return state;
    }
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();

        // Если непосредственно выбранная позиция — вода
        if (level.getFluidState(clickedPos).is(FluidTags.WATER)) {
            return defaultBlockState()
                    .setValue(WATERLOGGED, true);
        }

        // Если кликаем по BOG или BOG_PLANT,
        // разрешаем поставить BOG в воду непосредственно под ним.
        BlockState clickedState = level.getBlockState(clickedPos);

        if (clickedState.is(ModBlocks.BOG)
                || clickedState.is(ModBlocks.BOG_PLANT)) {

            BlockPos below = clickedPos.below();

            if (level.getFluidState(below).is(FluidTags.WATER)) {
                return defaultBlockState()
                        .setValue(WATERLOGGED, true);
            }
        }

        return null;
    }


    @Override
    protected void spawnFallingLeavesParticle(Level level, BlockPos pos, RandomSource random) {

    }


}