package msbioms.block;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import static net.minecraft.world.level.block.Blocks.AIR;

public class GlowingFruitBlock extends Block {

    public static final MapCodec<GlowingFruitBlock> CODEC =
            simpleCodec(GlowingFruitBlock::new);

    private static final VoxelShape SHAPE = Block.box(
            4, 1, 4,
            12, 14, 12
    );

    private static final FoodProperties FOOD =
            new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationModifier(0.3F)
                    .build();

    public GlowingFruitBlock(Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<GlowingFruitBlock> codec() {
        return CODEC;
    }

    @Override
    protected InteractionResult useWithoutItem(
            BlockState state,
            Level level,
            BlockPos pos,
            Player player,
            BlockHitResult hit
    ) {
        if (player.canEat(false)) {

            if (!level.isClientSide()) {
                player.getFoodData().eat(
                        FOOD.nutrition(),
                        FOOD.saturation()
                );

                level.setBlock(
                        pos,
                        AIR.defaultBlockState(),
                        3
                );
            }

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    @Override
    protected VoxelShape getShape(
            BlockState state,
            BlockGetter level,
            BlockPos pos,
            CollisionContext context
    ) {
        return SHAPE;
    }

    @Override
    protected void onProjectileHit(
            Level level,
            BlockState state,
            BlockHitResult hitResult,
            Projectile projectile
    ) {
        if (!level.isClientSide()) {

            BlockPos pos = hitResult.getBlockPos();

            level.levelEvent(
                    2001,
                    pos,
                    Block.getId(state)
            );

            level.levelEvent(
                    2001,
                    pos,
                    Block.getId(state)
            );

            level.setBlock(
                    pos,
                    AIR.defaultBlockState(),
                    3
            );
        }
    }
}