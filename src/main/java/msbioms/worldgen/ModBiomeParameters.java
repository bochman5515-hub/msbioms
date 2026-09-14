package msbioms.worldgen;

import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;

import java.util.function.Consumer;

public class ModBiomeParameters {

    /**
     * Добавляет наши биомы в климатическую карту Overworld.
     */
    public static void addCustomBiomes(
            Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> biomes
    ) {
        biomes.accept(
                Pair.of(
                        Climate.parameters(
                                Climate.Parameter.span(-0.2F, 0.4F),
                                Climate.Parameter.span(-1.0F, 1.0F),
                                Climate.Parameter.span(-0.25F, -0.05F),
                                Climate.Parameter.span(-1.0F, 1.0F),
                                Climate.Parameter.point(0.0F),
                                Climate.Parameter.span(-1.0F, 1.0F),
                                0.0F),

                        ModBiomes.SALT_STONY_SHORE_KEY));
    }
}