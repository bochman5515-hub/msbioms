package msbioms.worldgen;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.BiomeParameterTargets;
import com.terraformersmc.biolith.api.biome.sub.Criterion;
import com.terraformersmc.biolith.api.biome.sub.CriterionBuilder;
import msbioms.MSBioms;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModBiomePlacement {

    public static void register() {

        /*
         * ============================================================
         * MSBioms sub-biomes
         * ============================================================
         */

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


        /*
         * ============================================================
         * MSBioms vegetation
         * ============================================================
         *
         * Высокая водяная трава:
         * - Willow Forest
         * - обычное болото
         * - мангровое болото
         */

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(
                        Biomes.SWAMP,
                        Biomes.MANGROVE_SWAMP
                ),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ModPlacedFeatures.WILLOW_HIGH_GRASS_PLACED_KEY
        );

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(
                        Biomes.SWAMP,
                        Biomes.MANGROVE_SWAMP
                ),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ModPlacedFeatures.BOG_PLANT_PLACED_KEY
        );

        MSBioms.LOGGER.info(
                "MSBioms: registered high grass in willow forest, swamp and mangrove swamp"
        );
    }
}