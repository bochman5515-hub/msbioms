package msbioms.worldgen;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.BiomeParameterTargets;
import com.terraformersmc.biolith.api.biome.sub.Criterion;
import com.terraformersmc.biolith.api.biome.sub.CriterionBuilder;
import msbioms.MSBioms;
import net.minecraft.world.level.biome.Biomes;

public class ModBiomePlacement {

    public static void register() {

        Criterion saltShoreCriterion =
                CriterionBuilder.allOf(
                        CriterionBuilder.OCEANSIDE,

                        CriterionBuilder.value(
                                BiomeParameterTargets.DEPTH,
                                -0.15F,
                                0.15F
                        )
                );

        BiomePlacement.addSubOverworld(
                Biomes.STONY_SHORE,
                ModBiomes.SALT_STONY_SHORE_KEY,
                saltShoreCriterion
        );

        MSBioms.LOGGER.info(
                "Biolith: registered salt_stony_shore as restricted sub-biome of stony_shore"
        );
    }
}