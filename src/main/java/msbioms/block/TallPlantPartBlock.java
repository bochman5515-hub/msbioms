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

    // =========================================================
    // WATERLOGGING
    // =========================================================

    @Override
    public BlockState getStateForPlacement(
            BlockPlaceContext context
    ) {
        FluidState fluidState =
                context.getLevel()
                        .getFluidState(context.getClickedPos());

        return defaultBlockState()
                .setValue(
                        WATERLOGGED,
                        fluidState.is(FluidTags.WATER)
                                && fluidState.isSource()
                );
    }

    @Override
    protected FluidState getFluidState(
            BlockState state
    ) {
        return state.getValue(WATERLOGGED)
                ? Fluids.WATER.getSource(false)
                : Fluids.EMPTY.defaultFluidState();
    }

    // =========================================================
    // SURVIVAL
    // =========================================================

    @Override
    protected boolean canSurvive(
            BlockState state,
            LevelReader level,
            BlockPos pos
    ) {

        /*
         * =====================================================
         * ВЕРХНЯЯ ЧАСТЬ
         * =====================================================
         *
         * HIGH_GRASS
         *
         * Должна иметь HIGH_GRASS_PLANT снизу.
         */
        if (!lowerPart) {
            return level.getBlockState(
                    pos.below()
            ).is(otherPart);
        }

        /*
         * =====================================================
         * НИЖНЯЯ ЧАСТЬ
         * =====================================================
         *
         * HIGH_GRASS_PLANT
         */

        BlockPos groundPos =
                pos.below();

        BlockState groundState =
                level.getBlockState(groundPos);

        /*
         * -----------------------------------------------------
         * Растение на суше.
         * -----------------------------------------------------
         */
        if (!state.getValue(WATERLOGGED)) {
            return isValidGround(groundState);
        }

        /*
         * -----------------------------------------------------
         * Растение в воде.
         *
         * HIGH_GRASS_PLANT [WATERLOGGED]
         * SAND / MOSS / DIRT
         * -----------------------------------------------------
         */
        return isFullWater(level, pos)
                && isValidGround(groundState);
    }

    // =========================================================
    // GROUND
    // =========================================================

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

    private static boolean isFullWater(
            LevelReader level,
            BlockPos pos
    ) {
        FluidState fluidState =
                level.getFluidState(pos);

        return fluidState.is(FluidTags.WATER)
                && fluidState.isSource();
    }

    // =========================================================
    // NEIGHBOUR UPDATES
    // =========================================================

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

    // =========================================================
    // BREAKING
    // =========================================================

    @Override
    public BlockState playerWillDestroy(
            Level level,
            BlockPos pos,
            BlockState state,
            Player player
    ) {
        /*
         * Если ломаем нижнюю часть —
         * удаляем верхнюю.
         *
         * Если ломаем верхнюю —
         * удаляем нижнюю.
         */
        BlockPos otherPos = lowerPart
                ? pos.above()
                : pos.below();

        if (level.getBlockState(otherPos).is(otherPart)) {
            level.destroyBlock(
                    otherPos,
                    false
            );
        }

        return super.playerWillDestroy(
                level,
                pos,
                state,
                player
        );
    }
}