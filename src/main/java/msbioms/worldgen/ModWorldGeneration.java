package msbioms.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
public class ModWorldGeneration {

    public static void generateModWorldGen() {

        BiomeModifications.addFeature(
                BiomeSelectors.tag(ConventionalBiomeTags.IS_TAIGA),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ModPlacedFeatures.TALL_SPRUCE_PLACED_KEY
        );
    }
}