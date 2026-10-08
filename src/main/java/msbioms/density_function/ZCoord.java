package msbioms.density_function;

import com.mojang.serialization.MapCodec;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.levelgen.DensityFunction;

public class ZCoord implements DensityFunction {

    public static final MapCodec<ZCoord> MAP_CODEC =
            MapCodec.unit(new ZCoord());

    public static final KeyDispatchDataCodec<ZCoord> CODEC =
            KeyDispatchDataCodec.of(MAP_CODEC);

    @Override
    public double compute(FunctionContext context) {
        return context.blockZ();
    }

    @Override
    public void fillArray(
            double[] output,
            ContextProvider contextProvider
    ) {
        contextProvider.fillAllDirectly(output, this);
    }

    @Override
    public DensityFunction mapChildren(Visitor visitor) {
        return this;
    }

    @Override
    public double minValue() {
        return -30_000_000.0;
    }

    @Override
    public double maxValue() {
        return 30_000_000.0;
    }

    @Override
    public KeyDispatchDataCodec<? extends DensityFunction> codec() {
        return CODEC;
    }
}