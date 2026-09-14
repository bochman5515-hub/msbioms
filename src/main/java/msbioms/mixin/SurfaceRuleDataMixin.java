package msbioms.mixin;

import msbioms.MSBioms;
import msbioms.worldgen.ModSurfaceRules;
import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.SurfaceRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.data.worldgen.SurfaceRuleData;

@Mixin(SurfaceRuleData.class)
public class SurfaceRuleDataMixin {


    @Inject(
            method = "overworld",
            at = @At("RETURN"),
            cancellable = true
    )
    private static void msbioms$addSaltShoreSurface(
            HolderGetter<Biome> biomes,
            CallbackInfoReturnable<SurfaceRules.RuleSource> cir
    ) {
        MSBioms.LOGGER.info(
                "=== MSBiOMS SURFACE MIXIN CALLED ==="
        );

        SurfaceRules.RuleSource vanillaRules = cir.getReturnValue();

        MSBioms.LOGGER.info(
                "Vanilla surface rules obtained: " + vanillaRules
        );

        SurfaceRules.RuleSource modRules =
                ModSurfaceRules.makeRules(biomes);

        MSBioms.LOGGER.info(
                "Custom salt shore surface rules created"
        );

        cir.setReturnValue(
                SurfaceRules.sequence(
                        modRules,
                        vanillaRules
                )
        );

        MSBioms.LOGGER.info(
                "Custom surface rules injected"
        );
    }
}