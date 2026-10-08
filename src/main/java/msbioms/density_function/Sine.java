package msbioms.density_function;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.DensityFunctions;

public class Sine implements DensityFunction {

    public static final MapCodec<Sine> MAP_CODEC =
            RecordCodecBuilder.mapCodec(instance ->
                    instance.group(
                            DensityFunctions.DIRECT_CODEC
                                    .fieldOf("argument")
                                    .forGetter(Sine::argument)
                    ).apply(instance, Sine::new)
            );

    public static final KeyDispatchDataCodec<Sine> CODEC =
            KeyDispatchDataCodec.of(MAP_CODEC);

    private final DensityFunction argument;

    public Sine(DensityFunction argument) {
        this.argument = argument;
    }

    public DensityFunction argument() {
        return argument;
    }

    @Override
    public double compute(FunctionContext context) {
        return Math.sin(argument.compute(context));
    }

    @Override
    public void fillArray(
            double[] output,
            ContextProvider contextProvider
    ) {
        argument.fillArray(output, contextProvider);

        for (int i = 0; i < output.length; i++) {
            output[i] = Math.sin(output[i]);
        }
    }

    @Override
    public DensityFunction mapChildren(Visitor visitor) {
        return new Sine(argument.mapAll(visitor));
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