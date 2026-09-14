package msbioms.block;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WillowVinePlantBlock extends GrowingPlantBodyBlock {

    public static final MapCodec<WillowVinePlantBlock> CODEC =
            simpleCodec(WillowVinePlantBlock::new);

    private static final VoxelShape SHAPE =
            Block.box(
                    1.0, 0.0, 1.0,
                    15.0, 16.0, 15.0
            );

    @Override
    public MapCodec<WillowVinePlantBlock> codec() {
        return CODEC;
    }

    public WillowVinePlantBlock(BlockBehaviour.Properties properties) {
        super(
                properties,
                Direction.DOWN,
                SHAPE,
                false
        );
    }

    @Override
    protected GrowingPlantHeadBlock getHeadBlock() {
        return (GrowingPlantHeadBlock) ModBlocks.WILLOW_VINE;
    }
}