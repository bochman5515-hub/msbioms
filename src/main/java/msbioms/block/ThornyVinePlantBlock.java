package msbioms.block;

import com.mojang.serialization.MapCodec;

import msbioms.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ThornyVinePlantBlock extends GrowingPlantBodyBlock {

    public static final MapCodec<ThornyVinePlantBlock> CODEC =
            simpleCodec(ThornyVinePlantBlock::new);
    public static void dropFruitBelow(
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

    private static final VoxelShape SHAPE =
            Block.box(
                    1.0, 0.0, 1.0,
                    15.0, 16.0, 15.0
            );

    @Override
    public MapCodec<ThornyVinePlantBlock> codec() {
        return CODEC;
    }

    public ThornyVinePlantBlock(BlockBehaviour.Properties properties) {
        super(
                properties,
                Direction.DOWN,
                SHAPE,
                false
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
    public BlockState playerWillDestroy(
            Level level,
            BlockPos pos,
            BlockState state,
            Player player
    ) {
        if (!level.isClientSide()) {
            ThornyVineBlock.dropFruitBelow(level, pos);
        }

        return super.playerWillDestroy(
                level,
                pos,
                state,
                player
        );
    }
    @Override
    protected boolean canSurvive(
            BlockState state,
            LevelReader level,
            BlockPos pos
    ) {
        BlockState aboveState = level.getBlockState(pos.above());

        return aboveState.is(ModBlocks.GLOOMY_LEAVES)
                || aboveState.is(ModBlocks.GLOOMY_LEAVES_FLOVER)
                || aboveState.is(ModBlocks.THORNY_VINE)
                || aboveState.is(ModBlocks.THORNY_VINE_PLANT);
    }

    @Override
    protected GrowingPlantHeadBlock getHeadBlock() {
        return (GrowingPlantHeadBlock) ModBlocks.THORNY_VINE;
    }
}