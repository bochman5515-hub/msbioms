package msbioms.mixin;

import com.mojang.datafixers.util.Pair;
import msbioms.worldgen.ModBiomeParameters;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(OverworldBiomeBuilder.class)
public class OverworldBiomeBuilderMixin {

    @Inject(
            method = "addBiomes",
            at = @At("TAIL")
    )
    private void msbioms$addCustomBiomes(
            Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> biomes,
            CallbackInfo ci
    ) {

        /*
         * Добавляем willow_forest непосредственно
         * в vanilla Overworld multi-noise biome map.
         *
         * Это важно для MSBioms, потому что наш custom
         * world preset использует:
         *
         * minecraft:multi_noise
         * preset: minecraft:overworld
         */
        ModBiomeParameters.addWillowForest(biomes);
    }
}