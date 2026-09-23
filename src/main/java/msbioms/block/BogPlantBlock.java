package msbioms.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.LilyPadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BogPlantBlock extends LilyPadBlock
        implements BonemealableBlock {

    public static final IntegerProperty AGE =
            BlockStateProperties.AGE_2;

    private static final int SPREAD_ATTEMPTS = 8;
    private static final int SPREAD_RADIUS = 2;
    public static final IntegerProperty GROWTH =
            IntegerProperty.create("growth", 0, 2);

    private void growBog(
            ServerLevel level,
            BlockPos pos
    ) {
        BlockPos bogPos = pos.below();

        // Ищем первую клетку ниже, где можно разместить BOG.
        while (true) {

            BlockState state =
                    level.getBlockState(bogPos);

            // Если встретили обычный блок —
            // дальше вниз не растём.
            if (!state.isAir()
                    && !state.is(Blocks.WATER)
                    && !state.is(ModBlocks.BOG)) {
                return;
            }

            // Если здесь уже BOG —
            // продолжаем искать ниже.
            if (state.is(ModBlocks.BOG)) {
                bogPos = bogPos.below();
                continue;
            }

            // Ставим BOG только в воду.
            if (!level.getFluidState(bogPos)
                    .is(FluidTags.WATER)) {
                return;
            }

            level.setBlock(
                    bogPos,
                    ModBlocks.BOG.defaultBlockState()
                            .setValue(
                                    BogBlock.WATERLOGGED,
                                    true
                            ),
                    3
            );

            return;
        }
    }

    public static final IntegerProperty SINKING =
            IntegerProperty.create("sinking", 0, 2);

    private boolean canSpreadTo(
            ServerLevel level,
            BlockPos pos
    ) {
        BlockState state = level.getBlockState(pos);

        // Свободная вода/пустая позиция
        if (state.isAir()) {
            return level.getFluidState(pos)
                    .is(FluidTags.WATER);
        }

        // Морская трава
        if (state.is(Blocks.SEAGRASS)
                || state.is(Blocks.TALL_SEAGRASS)) {
            return true;
        }

        // Ламинария
        if (state.is(Blocks.KELP)
                || state.is(Blocks.KELP_PLANT)) {
            return true;
        }

        return false;
    }
    private static final VoxelShape BOG_PLANT_COLLISION =
            Shapes.box(
                    0.0D,
                    0.0D,
                    0.0D,
                    1.0D,
                    0.125D,
                    1.0D
            );

    public BogPlantBlock(Properties properties) {
        super(properties);

        registerDefaultState(
                stateDefinition.any()
                        .setValue(AGE, 0)
                        .setValue(GROWTH, 0)
                        .setValue(SINKING, 0)
        );
    }

    @Override
    protected void createBlockStateDefinition(
            StateDefinition.Builder<Block, BlockState> builder
    ) {
        super.createBlockStateDefinition(builder);
        builder.add(
                AGE,
                GROWTH,
                SINKING
        );

    }


    @Override
    protected boolean canSurvive(
            BlockState state,
            LevelReader level,
            BlockPos pos
    ) {
        return level.getFluidState(pos.below())
                .is(FluidTags.WATER);
    }

    @Override
    public boolean isValidBonemealTarget(
            LevelReader level,
            BlockPos pos,
            BlockState state
    ) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(
            Level level,
            RandomSource random,
            BlockPos pos,
            BlockState state
    ) {
        return true;
    }

    @Override
    public void performBonemeal(
            ServerLevel level,
            RandomSource random,
            BlockPos pos,
            BlockState state
    ) {
        int age = state.getValue(AGE);

        // AGE 0 -> AGE 1
        if (age == 0) {
            level.setBlock(
                    pos,
                    state.setValue(AGE, 1),
                    3
            );
            return;
        }

        // AGE 1 -> AGE 2
        if (age == 1) {
            level.setBlock(
                    pos,
                    state
                            .setValue(AGE, 2)
                            .setValue(SINKING, 0),
                    3
            );
            return;
        }

        // AGE 2 -> распространение + накопление роста
        if (age == 2) {

            spread(level, random, pos);

            int growth = state.getValue(GROWTH);

            if (growth < 2) {

                level.setBlock(
                        pos,
                        state.setValue(
                                GROWTH,
                                growth + 1
                        ),
                        3
                );

            } else {

                growBog(level, pos);

            }
        }
    }

    private void spread(
            ServerLevel level,
            RandomSource random,
            BlockPos pos
    ) {
        for (int i = 0; i < SPREAD_ATTEMPTS; i++) {

            int x = pos.getX()
                    + random.nextIntBetweenInclusive(
                    -SPREAD_RADIUS,
                    SPREAD_RADIUS
            );

            int z = pos.getZ()
                    + random.nextIntBetweenInclusive(
                    -SPREAD_RADIUS,
                    SPREAD_RADIUS
            );

            // Позиция, где будет новая трясина
            BlockPos targetPos =
                    new BlockPos(
                            x,
                            pos.getY(),
                            z
                    );

            // Вода находится под трясиной
            BlockPos waterPos = targetPos.below();

            BlockState targetState =
                    level.getBlockState(targetPos);

            BlockState waterState =
                    level.getBlockState(waterPos);

            // Здесь уже стоит какой-то блок
            if (!targetState.isAir()) {
                continue;
            }

            // Под будущей трясиной должна быть вода
            if (!level.getFluidState(waterPos)
                    .is(FluidTags.WATER)) {
                continue;
            }

            // Если под поверхностью растёт морская трава
            if (waterState.is(Blocks.SEAGRASS)
                    || waterState.is(Blocks.TALL_SEAGRASS)
                    || waterState.is(Blocks.KELP)
                    || waterState.is(Blocks.KELP_PLANT)) {

                // Ломаем её как игрок
                level.destroyBlock(
                        waterPos,
                        false
                );
            }

            // Ставим новую трясину
            level.setBlock(
                    targetPos,
                    defaultBlockState()
                            .setValue(AGE, 0)
                            .setValue(SINKING, 0)
                            .setValue(GROWTH, 0),
                    3
            );
        }
    }
    @Override
    protected void entityInside(
            BlockState state,
            Level level,
            BlockPos pos,
            Entity entity,
            InsideBlockEffectApplier effectApplier,
            boolean isPrecise
    ) {
        int age = state.getValue(AGE);

        // Лодка
        if (entity instanceof Boat) {
            Vec3 movement = entity.getDeltaMovement();

            double slowdown = switch (age) {
                case 0 -> 0.95;
                case 1 -> 0.90;
                case 2 -> 0.85;
                default -> 1.0;
            };

            entity.setDeltaMovement(
                    movement.x * slowdown,
                    movement.y,
                    movement.z * slowdown
            );

            return;
        }

        // Игрок
        if (entity instanceof Player) {
            double slowdown = switch (age) {
                case 0 -> 0.99;
                case 1 -> 0.95;
                case 2 -> 0.88;
                default -> 1.0;
            };

            entity.makeStuckInBlock(
                    state,
                    new Vec3(slowdown, 1.0, slowdown)
            );

            // Только AGE 2 запускает погружение
            if (age == 2 && state.getValue(SINKING) == 0) {
                if (!level.isClientSide()) {
                    System.out.println("BOG START SINKING");

                    level.setBlock(
                            pos,
                            state.setValue(SINKING, 1),
                            3
                    );

                    level.scheduleTick(pos, this, 20);
                }
            }

            return;
        }
    }
        // =========================
        // ЗАМЕДЛЕНИЕ
        // =========================
    @Override
    protected VoxelShape getCollisionShape(
            BlockState state,
            BlockGetter level,
            BlockPos pos,
            CollisionContext context
    ) {
        if (context instanceof EntityCollisionContext entityContext
                && entityContext.getEntity() instanceof Boat) {
            return Shapes.empty();
        }

        if (state.getValue(AGE) < 2) {
            return Shapes.empty();
        }

        if (state.getValue(SINKING) == 2) {
            return Shapes.empty();
        }

        return BOG_PLANT_COLLISION;
    }

    @Override
    public void stepOn(
            Level level,
            BlockPos pos,
            BlockState state,
            Entity entity
    ) {
        super.stepOn(
                level,
                pos,
                state,
                entity
        );

        if (level.isClientSide()) {
            return;
        }

        // Только игрок
        if (!(entity instanceof Player)) {
            return;
        }

        // Только взрослая стадия
        if (state.getValue(AGE) != 2) {
            return;
        }

        // Уже проваливается
        if (state.getValue(SINKING) != 0) {
            return;
        }

        System.out.println("BOG START SINKING");

        // Запускаем состояние
        level.setBlock(
                pos,
                state.setValue(SINKING, 1),
                3
        );

        // Через 1 секунду проваливаемся
        level.scheduleTick(
                pos,
                this,
                20
        );
    }
    @Override
    protected void tick(
            BlockState state,
            ServerLevel level,
            BlockPos pos,
            RandomSource random
    ) {
        System.out.println(
                "BOG TICK: "
                        + state.getValue(SINKING)
        );

        if (state.getValue(SINKING) == 1) {

            System.out.println("BOG FALL");

            level.setBlock(
                    pos,
                    state.setValue(SINKING, 2),
                    3
            );

            level.scheduleTick(
                    pos,
                    this,
                    40
            );

            return;
        }

        if (state.getValue(SINKING) == 2) {

            System.out.println("BOG RESTORE");

            level.setBlock(
                    pos,
                    state.setValue(SINKING, 0),
                    3
            );
        }
    }

}
