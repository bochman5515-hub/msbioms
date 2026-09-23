package msbioms.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import java.util.function.Supplier;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;


public class MicroBlock extends Block {

    public static final BooleanProperty CELL_0 =
            BooleanProperty.create("cell_0");

    public static final BooleanProperty CELL_1 =
            BooleanProperty.create("cell_1");

    public static final BooleanProperty CELL_2 =
            BooleanProperty.create("cell_2");

    public static final BooleanProperty CELL_3 =
            BooleanProperty.create("cell_3");

    public static final BooleanProperty CELL_4 =
            BooleanProperty.create("cell_4");

    public static final BooleanProperty CELL_5 =
            BooleanProperty.create("cell_5");

    public static final BooleanProperty CELL_6 =
            BooleanProperty.create("cell_6");

    public static final BooleanProperty CELL_7 =
            BooleanProperty.create("cell_7");

    private static final BooleanProperty[] CELLS = {
            CELL_0,
            CELL_1,
            CELL_2,
            CELL_3,
            CELL_4,
            CELL_5,
            CELL_6,
            CELL_7
    };

    private static final VoxelShape[] CELL_SHAPES = {
            Shapes.box(0, 0, 0, 0.5, 0.5, 0.5),
            Shapes.box(0.5, 0, 0, 1, 0.5, 0.5),
            Shapes.box(0, 0, 0.5, 0.5, 0.5, 1),
            Shapes.box(0.5, 0, 0.5, 1, 0.5, 1),

            Shapes.box(0, 0.5, 0, 0.5, 1, 0.5),
            Shapes.box(0.5, 0.5, 0, 1, 1, 0.5),
            Shapes.box(0, 0.5, 0.5, 0.5, 1, 1),
            Shapes.box(0.5, 0.5, 0.5, 1, 1, 1)
    };
    public static final BooleanProperty WATERLOGGED =
            BlockStateProperties.WATERLOGGED;

    public MicroBlock(
            Properties properties,
            Supplier<? extends Item> dropItem
    ) {
        super(properties);

        this.dropItem = dropItem;

        registerDefaultState(
                stateDefinition.any()
                        .setValue(CELL_0, false)
                        .setValue(CELL_1, false)
                        .setValue(CELL_2, false)
                        .setValue(CELL_3, false)
                        .setValue(CELL_4, false)
                        .setValue(CELL_5, false)
                        .setValue(CELL_6, false)
                        .setValue(CELL_7, false)
                        .setValue(WATERLOGGED, false)
        );
    }

    public static BooleanProperty getCellProperty(int cell) {
        return CELLS[cell];
    }

    public static int getMask(BlockState state) {
        int mask = 0;

        for (int cell = 0; cell < 8; cell++) {
            if (state.getValue(CELLS[cell])) {
                mask |= 1 << cell;
            }
        }

        return mask;
    }

    public static BlockState setCell(
            BlockState state,
            int cell,
            boolean value
    ) {
        return state.setValue(
                CELLS[cell],
                value
        );
    }

    public static boolean hasSupportCell(
            BlockState state,
            Direction face,
            int cell
    ) {
        int supportCell;

        switch (face) {
            case UP ->
                    supportCell = cell | 4;

            case DOWN ->
                    supportCell = cell & ~4;

            case NORTH ->
                    supportCell = cell & ~2;

            case SOUTH ->
                    supportCell = cell | 2;

            case WEST ->
                    supportCell = cell & ~1;

            case EAST ->
                    supportCell = cell | 1;

            default -> {
                return false;
            }
        }

        return state.getValue(
                CELLS[supportCell]
        );
    }

    @Override
    protected VoxelShape getShape(
            BlockState state,
            BlockGetter level,
            BlockPos pos,
            CollisionContext context
    ) {
        return createShape(state);
    }

    @Override
    protected VoxelShape getCollisionShape(
            BlockState state,
            BlockGetter level,
            BlockPos pos,
            CollisionContext context
    ) {
        return createShape(state);
    }

    @Override
    protected VoxelShape getBlockSupportShape(
            BlockState state,
            BlockGetter level,
            BlockPos pos
    ) {
        return createShape(state);
    }

    private static VoxelShape createShape(
            BlockState state
    ) {
        VoxelShape shape = Shapes.empty();

        for (int cell = 0; cell < 8; cell++) {

            if (!state.getValue(CELLS[cell])) {
                continue;
            }

            shape = Shapes.or(
                    shape,
                    CELL_SHAPES[cell]
            );
        }

        return shape;
    }

    @Override
    protected void createBlockStateDefinition(
            StateDefinition.Builder<Block, BlockState> builder
    ) {
        builder.add(
                CELL_0,
                CELL_1,
                CELL_2,
                CELL_3,
                CELL_4,
                CELL_5,
                CELL_6,
                CELL_7,
                WATERLOGGED
        );
    }

    private final Supplier<? extends Item> dropItem;

    @Override
    public void playerDestroy(
            Level level,
            Player player,
            BlockPos pos,
            BlockState state,
            net.minecraft.world.level.block.entity.BlockEntity blockEntity,
            ItemStack tool
    ) {
        if (level.isClientSide()) {
            return;
        }

        int mask = getMask(state);

        for (int cell = 0; cell < 8; cell++) {

            if ((mask & (1 << cell)) != 0) {

                popResource(
                        level,
                        pos,
                        new ItemStack(dropItem.get())
                );
            }
        }

        level.setBlock(
                pos,
                Blocks.AIR.defaultBlockState(),
                3
        );
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED)
                ? Fluids.WATER.getSource(false)
                : super.getFluidState(state);
    }
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
                        fluidState.getType() == Fluids.WATER
                );
    }

}