package msbioms.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class TallPlantPartBlock extends Block implements SimpleWaterloggedBlock {

    public static final BooleanProperty WATERLOGGED =
            BlockStateProperties.WATERLOGGED;

    private final ResourceKey<Block> otherPart;
    private final boolean lowerPart;

    public TallPlantPartBlock(
            Properties properties,
            ResourceKey<Block> otherPart,
            boolean lowerPart
    ) {
        super(properties);

        this.otherPart = otherPart;
        this.lowerPart = lowerPart;

        registerDefaultState(
                stateDefinition.any()
                        .setValue(WATERLOGGED, false)
        );
    }

    @Override
    protected void createBlockStateDefinition(
            StateDefinition.Builder<Block, BlockState> builder
    ) {
        builder.add(WATERLOGGED);
    }

    /**
     * Как у водных растений/кораллов:
     * если место содержит воду, блок становится waterlogged,
     * а вода сохраняется как часть состояния блока.
     */
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidState = context.getLevel()
                .getFluidState(context.getClickedPos());

        return defaultBlockState()
                .setValue(
                        WATERLOGGED,
                        fluidState.is(FluidTags.WATER)
                                && fluidState.isSource()
                );
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED)
                ? Fluids.WATER.getSource(false)
                : Fluids.EMPTY.defaultFluidState();
    }

    /**
     * Нижняя часть может стоять на земле или находиться в воде.
     * Верхняя часть обязана находиться над нижней.
     */
    @Override
    protected boolean canSurvive(
            BlockState state,
            LevelReader level,
            BlockPos pos
    ) {
        // Верхняя часть должна находиться непосредственно над нижней.
        if (!lowerPart) {
            return level.getBlockState(pos.below()).is(otherPart);
        }

        // Нижняя часть ВСЕГДА требует опору снизу.
        BlockState below = level.getBlockState(pos.below());

        return below.is(BlockTags.DIRT)
                || below.is(Blocks.GRASS_BLOCK)
                || below.is(Blocks.CLAY)
                || below.is(Blocks.MUD);
    }
    /**
     * Если вода появляется/исчезает рядом с блоком,
     * Minecraft обновляет waterlogged-состояние.
     */
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
                    Fluids.WATER,
                    Fluids.WATER.getTickDelay(level)
            );
        }

        if (!this.canSurvive(state, level, pos)) {
            return Blocks.AIR.defaultBlockState();
        }

        return super.updateShape(
                state,
                level,
                ticks,
                pos,
                directionToNeighbour,
                neighbourPos,
                neighbourState,
                random
        );
    }

    /**
     * При разрушении одной половины уничтожаем вторую.
     */
    @Override
    public BlockState playerWillDestroy(
            Level level,
            BlockPos pos,
            BlockState state,
            Player player
    ) {
        BlockPos otherPos = lowerPart
                ? pos.above()
                : pos.below();

        if (level.getBlockState(otherPos).is(otherPart)) {
            level.destroyBlock(otherPos, false);
        }

        return super.playerWillDestroy(
                level,
                pos,
                state,
                player
        );
    }
}