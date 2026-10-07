package msbioms.density_function;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.levelgen.DensityFunction;

public record Signum(DensityFunction argument) implements DensityFunction {

    public static final MapCodec<Signum> MAP_CODEC =
            RecordCodecBuilder.mapCodec(instance -> instance
                    .group(
                            DensityFunction.CODEC.fieldOf("argument")
                                    .forGetter(Signum::argument)
                    )
                    .apply(instance, Signum::new)
            );

    public static final KeyDispatchDataCodec<Signum> CODEC =
            KeyDispatchDataCodec.of(MAP_CODEC);

    @Override
    public double compute(FunctionContext context) {
        return Math.signum(argument.compute(context));
    }

    @Override
    public void fillArray(double[] densities, ContextProvider provider) {
        provider.fillAllDirectly(densities, this);
    }

    @Override
    public DensityFunction mapAll(Visitor visitor) {
        return new Signum(argument.mapAll(visitor));
    }

    @Override
    public DensityFunction mapChildren(Visitor visitor) {
        return new Signum(visitor.apply(argument));
    }

    @Override
    public double minValue() {
        return -1.0;
    }

    @Override
    public double maxValue() {
        return 1.0;
    }

    @Override
    public KeyDispatchDataCodec<? extends DensityFunction> codec() {
        return CODEC;
    }
}