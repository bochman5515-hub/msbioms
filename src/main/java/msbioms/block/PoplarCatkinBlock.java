package msbioms.block;

import msbioms.particle.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.state.BlockState;

public class PoplarCatkinBlock extends AmethystClusterBlock {

    public PoplarCatkinBlock(
            float height,
            float width,
            Properties properties
    ) {
        super(height, width, properties);
    }

    @Override
    protected boolean canSurvive(
            BlockState state,
            LevelReader level,
            BlockPos pos
    ) {
        BlockPos supportPos =
                pos.relative(
                        state.getValue(FACING).getOpposite()
                );

        return level.getBlockState(supportPos)
                .is(ModBlocks.POPLAR_LEAVES);
    }

    @Override
    public void animateTick(
            BlockState state,
            Level level,
            BlockPos pos,
            RandomSource random
    ) {
        /*
         * Очень редкое опадение.
         */
        if (random.nextInt(200) != 0) {
            return;
        }

        Direction direction =
                state.getValue(FACING);

        double x =
                pos.getX() + 0.5D
                        + direction.getStepX() * 0.20D;

        double y =
                pos.getY() + 0.5D
                        + direction.getStepY() * 0.20D;

        double z =
                pos.getZ() + 0.5D
                        + direction.getStepZ() * 0.20D;

        level.addParticle(
                ModParticles.POPLAR_FLUFF,
                x,
                y,
                z,
                direction.getStepX() * 0.0005D,
                direction.getStepY() * 0.0005D,
                direction.getStepZ() * 0.0005D
        );
    }
}