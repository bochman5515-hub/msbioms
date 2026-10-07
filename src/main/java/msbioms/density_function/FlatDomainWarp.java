package msbioms.density_function;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.DensityFunctions;

public record FlatDomainWarp(
        DensityFunction input,
        DensityFunction warpX,
        DensityFunction warpZ
) implements DensityFunction {

    public static final MapCodec<FlatDomainWarp> MAP_CODEC =
            RecordCodecBuilder.mapCodec(instance -> instance
                    .group(
                            DensityFunction.CODEC
                                    .fieldOf("input")
                                    .forGetter(FlatDomainWarp::input),

                            DensityFunction.CODEC
                                    .fieldOf("warp_x")
                                    .forGetter(FlatDomainWarp::warpX),

                            DensityFunction.CODEC
                                    .fieldOf("warp_z")
                                    .forGetter(FlatDomainWarp::warpZ)
                    )
                    .apply(instance, FlatDomainWarp::new)
            );

    public static final KeyDispatchDataCodec<FlatDomainWarp> CODEC =
            KeyDispatchDataCodec.of(MAP_CODEC);

    @Override
    public double compute(FunctionContext context) {
        return input.compute(
                new SinglePointContext(
                        context.blockX() + (int) warpX.compute(context),
                        context.blockY(),
                        context.blockZ() + (int) warpZ.compute(context)
                )
        );
    }

    @Override
    public void fillArray(double[] densities, ContextProvider provider) {
        provider.fillAllDirectly(densities, this);
    }

    @Override
    public DensityFunction mapAll(Visitor visitor) {
        return visitor.apply(
                new FlatDomainWarp(
                        input.mapAll(visitor),
                        warpX.mapAll(visitor),
                        warpZ.mapAll(visitor)
                )
        );
    }

    @Override
    public DensityFunction mapChildren(Visitor visitor) {
        return new FlatDomainWarp(
                visitor.apply(input),
                visitor.apply(warpX),
                visitor.apply(warpZ)
        );
    }

    @Override
    public double minValue() {
        return input.minValue();
    }

    @Override
    public double maxValue() {
        return input.maxValue();
    }

    @Override
    public KeyDispatchDataCodec<? extends DensityFunction> codec() {
        return CODEC;
    }
}