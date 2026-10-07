package msbioms.block;

import com.mojang.serialization.MapCodec;

import msbioms.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.NetherVines;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ThornyVineBlock extends GrowingPlantHeadBlock {

    public static final MapCodec<ThornyVineBlock> CODEC =
            simpleCodec(ThornyVineBlock::new);

    private static final VoxelShape SHAPE =
            Block.box(
                    1.0, 4.0, 1.0,
                    15.0, 16.0, 15.0
            );

    @Override
    public MapCodec<ThornyVineBlock> codec() {
        return CODEC;
    }

    public ThornyVineBlock(BlockBehaviour.Properties properties) {
        super(
                properties,
                Direction.DOWN,
                SHAPE,
                false,
                0.1
        );
    }
    static void dropFruitBelow(
            Level level,
            BlockPos vinePos
    ) {
        BlockPos checkPos = vinePos.below();

        while (true) {
            BlockState state = level.getBlockState(checkPos);

            if (state.is(ModBlocks.THORNY_VINE)
                    || state.is(ModBlocks.THORNY_VINE_PLANT)) {

                checkPos = checkPos.below();
                continue;
            }

            if (state.is(ModBlocks.GLOWING_FRUIT)) {

                popResource(
                        level,
                        checkPos,
                        new ItemStack(ModItems.GLOWING_FRUIT)
                );

                level.setBlock(
                        checkPos,
                        Blocks.AIR.defaultBlockState(),
                        3
                );
            }

            break;
        }
    }

    @Override
    protected int getBlocksToGrowWhenBonemealed(RandomSource random) {
        return NetherVines.getBlocksToGrowWhenBonemealed(random);
    }

    @Override
    protected Block getBodyBlock() {
        return ModBlocks.THORNY_VINE_PLANT;
    }

    @Override
    protected boolean canGrowInto(BlockState state) {
        return NetherVines.isValidGrowthState(state);
    }

    @Override
    protected boolean canSurvive(
            BlockState state,
            LevelReader level,
            BlockPos pos
    ) {
        BlockState aboveState =
                level.getBlockState(pos.above());

        return !aboveState.isAir();
    }

    @Override
    public BlockState playerWillDestroy(
            Level level,
            BlockPos pos,
            BlockState state,
            Player player
    ) {
        if (!level.isClientSide()) {
            dropFruitBelow(level, pos);
        }

        return super.playerWillDestroy(
                level,
                pos,
                state,
                player
        );
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
        if (!level.isClientSide()
                && entity.tickCount % 10 == 0) {

            entity.hurt(
                    level.damageSources().cactus(),
                    1.0F
            );
        }

        Vec3 movement = entity.getDeltaMovement();

        if (movement.y > 0.05D) {
            entity.setDeltaMovement(
                    movement.x,
                    0.05D,
                    movement.z
            );
        }

        super.entityInside(
                state,
                level,
                pos,
                entity,
                effectApplier,
                isPrecise
        );
    }



    @Override
    protected void randomTick(
            BlockState state,
            ServerLevel level,
            BlockPos pos,
            RandomSource random
    ) {
        /*
         * Запоминаем блок непосредственно под текущим
         * концом лианы до ванильного роста.
         */
        BlockPos growthPos = pos.below();
        BlockState beforeGrowth = level.getBlockState(growthPos);

        /*
         * Полностью выполняем обычную механику
         * GrowingPlantHeadBlock.
         */
        super.randomTick(state, level, pos, random);

        /*
         * Проверяем, действительно ли лиана выросла.
         */
        BlockState afterGrowth = level.getBlockState(growthPos);

        boolean grew =
                beforeGrowth.isAir()
                        && (
                        afterGrowth.is(ModBlocks.THORNY_VINE)
                                || afterGrowth.is(ModBlocks.THORNY_VINE_PLANT)
                );

        /*
         * Только после успешного роста проверяем 6%.
         */
        if (grew && random.nextFloat() < 0.06F) {

            BlockPos fruitPos = growthPos.below();

            if (level.getBlockState(fruitPos).isAir()) {
                level.setBlock(
                        fruitPos,
                        ModBlocks.GLOWING_FRUIT.defaultBlockState(),
                        3
                );
            }
        }
    }
}