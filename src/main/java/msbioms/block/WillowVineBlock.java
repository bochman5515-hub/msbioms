package msbioms.block;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.NetherVines;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WillowVineBlock extends GrowingPlantHeadBlock {

    public static final MapCodec<WillowVineBlock> CODEC =
            simpleCodec(WillowVineBlock::new);

    private static final VoxelShape SHAPE =
            Block.box(
                    1.0, 4.0, 1.0,
                    15.0, 16.0, 15.0
            );

    @Override
    public MapCodec<WillowVineBlock> codec() {
        return CODEC;
    }

    public WillowVineBlock(BlockBehaviour.Properties properties) {
        super(
                properties,
                Direction.DOWN,
                SHAPE,
                false,
                0.1
        );
    }

    @Override
    protected int getBlocksToGrowWhenBonemealed(RandomSource random) {
        return NetherVines.getBlocksToGrowWhenBonemealed(random);
    }

    @Override
    protected Block getBodyBlock() {
        return ModBlocks.WILLOW_VINE_PLANT;
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
}