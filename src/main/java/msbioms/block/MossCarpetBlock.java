package msbioms.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.WallSide;

public class MossCarpetBlock extends CarpetBlock {

    public static final EnumProperty<WallSide> NORTH =
            EnumProperty.create("north", WallSide.class);

    public static final EnumProperty<WallSide> EAST =
            EnumProperty.create("east", WallSide.class);

    public static final EnumProperty<WallSide> SOUTH =
            EnumProperty.create("south", WallSide.class);

    public static final EnumProperty<WallSide> WEST =
            EnumProperty.create("west", WallSide.class);

    public MossCarpetBlock(Properties properties) {
        super(properties);

        registerDefaultState(
                stateDefinition.any()
                        .setValue(NORTH, WallSide.NONE)
                        .setValue(EAST, WallSide.NONE)
                        .setValue(SOUTH, WallSide.NONE)
                        .setValue(WEST, WallSide.NONE)
        );
    }

    // =========================================================
    // BLOCK STATES
    // =========================================================

    @Override
    protected void createBlockStateDefinition(
            StateDefinition.Builder<Block, BlockState> builder
    ) {
        super.createBlockStateDefinition(builder);

        builder.add(
                NORTH,
                EAST,
                SOUTH,
                WEST
        );
    }

    // =========================================================
    // PLACEMENT
    // =========================================================

    @Override
    public BlockState getStateForPlacement(
            BlockPlaceContext context
    ) {
        BlockState state =
                super.getStateForPlacement(context);

        if (state == null) {
            return null;
        }

        return updateAllSides(
                state,
                context.getLevel(),
                context.getClickedPos()
        );
    }

    // =========================================================
    // NEIGHBOUR UPDATE
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
            net.minecraft.util.RandomSource random
    ) {
        state = super.updateShape(
                state,
                level,
                ticks,
                pos,
                directionToNeighbour,
                neighbourPos,
                neighbourState,
                random
        );

        if (directionToNeighbour.getAxis().isHorizontal()) {
            state = updateSide(
                    state,
                    level,
                    pos,
                    directionToNeighbour
            );
        }

        return state;
    }

    // =========================================================
    // SIDE UPDATE
    // =========================================================

    private static BlockState updateSide(
            BlockState state,
            LevelReader level,
            BlockPos pos,
            Direction direction
    ) {
        EnumProperty<WallSide> property =
                getProperty(direction);

        if (property == null) {
            return state;
        }

        BlockPos neighbourPos =
                pos.relative(direction);

        BlockState neighbourState =
                level.getBlockState(neighbourPos);

        /*
         * =====================================================
         * Блок непосредственно сбоку
         * =====================================================
         */
        if (neighbourState.is(ModBlocks.MOSS_CARPET)
                || neighbourState.isFaceSturdy(
                level,
                neighbourPos,
                direction.getOpposite()
        )) {

            return state.setValue(
                    property,
                    WallSide.NONE
            );
        }

        /*
         * =====================================================
         * Блок сбоку, но на один блок ниже
         * =====================================================
         */
        BlockPos lowerNeighbourPos =
                neighbourPos.below();

        BlockState lowerNeighbourState =
                level.getBlockState(lowerNeighbourPos);

        if (lowerNeighbourState.isFaceSturdy(
                level,
                lowerNeighbourPos,
                Direction.UP
        )) {
            return state.setValue(
                    property,
                    WallSide.NONE
            );
        }

        /*
         * =====================================================
         * Край полностью открыт
         * =====================================================
         */
        return state.setValue(
                property,
                WallSide.TALL
        );
    }

    // =========================================================
    // UPDATE ALL SIDES
    // =========================================================

    private static BlockState updateAllSides(
            BlockState state,
            LevelReader level,
            BlockPos pos
    ) {
        state = updateSide(
                state,
                level,
                pos,
                Direction.NORTH
        );

        state = updateSide(
                state,
                level,
                pos,
                Direction.EAST
        );

        state = updateSide(
                state,
                level,
                pos,
                Direction.SOUTH
        );

        state = updateSide(
                state,
                level,
                pos,
                Direction.WEST
        );

        return state;
    }

    // =========================================================
    // PROPERTY BY DIRECTION
    // =========================================================

    private static EnumProperty<WallSide> getProperty(
            Direction direction
    ) {
        return switch (direction) {
            case NORTH -> NORTH;
            case EAST -> EAST;
            case SOUTH -> SOUTH;
            case WEST -> WEST;
            default -> null;
        };
    }
}