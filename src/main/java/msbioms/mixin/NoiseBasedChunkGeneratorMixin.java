package msbioms.mixin;

import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NoiseBasedChunkGenerator.class)
public abstract class NoiseBasedChunkGeneratorMixin {

    @Inject(
            method = "<init>",
            at = @At("RETURN")
    )
    private void msbioms$debugSettings(
            BiomeSource biomeSource,
            Holder<NoiseGeneratorSettings> settings,
            CallbackInfo ci
    ) {
        String key = settings.unwrapKey()
                .map(Object::toString)
                .orElse("<NO KEY>");

    }

    /**
     * @author MSBioms
     * @reason Custom underground fluid levels
     */
    @Overwrite
    private static Aquifer.FluidPicker createFluidPicker(
            NoiseGeneratorSettings settings
    ) {
        Aquifer.FluidStatus lavaStatus =
                new Aquifer.FluidStatus(
                        -112,
                        Blocks.LAVA.defaultBlockState()
                );

        int seaLevel = settings.seaLevel();

        Aquifer.FluidStatus seaStatus =
                new Aquifer.FluidStatus(
                        seaLevel,
                        settings.defaultFluid()
                );

        Aquifer.FluidStatus emptyStatus =
                new Aquifer.FluidStatus(
                        -256,
                        Blocks.AIR.defaultBlockState()
                );

        return (x, y, z) -> {
            if (net.minecraft.SharedConstants.DEBUG_DISABLE_FLUID_GENERATION) {
                return emptyStatus;
            }

            return y < Math.min(-112, seaLevel)
                    ? lavaStatus
                    : seaStatus;
        };
    }
}