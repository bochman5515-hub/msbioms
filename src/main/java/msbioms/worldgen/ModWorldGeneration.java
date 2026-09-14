package msbioms.worldgen;

import msbioms.MSBioms;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;

public class ModWorldGeneration {


    public static final ResourceKey<ConfiguredWorldCarver<?>> ANOMALOUS_LAYER_CARVER =
            ResourceKey.create(
                    Registries.CONFIGURED_CARVER,
                    MSBioms.id("anomalous_layer")
            );
    public static final ResourceKey<ConfiguredWorldCarver<?>> CHAMBERS_CARVER =
            ResourceKey.create(
                    Registries.CONFIGURED_CARVER,
                    MSBioms.id("chambers")
            );
    public static final ResourceKey<ConfiguredWorldCarver<?>> CHAMBERS_CARVER_KEY =
            ResourceKey.create(
                    Registries.CONFIGURED_CARVER,
                    MSBioms.id("chambers")
            );


    public static void generateModWorldGen() {

        BiomeModifications.addFeature(
                BiomeSelectors.tag(ConventionalBiomeTags.IS_TAIGA),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ModPlacedFeatures.TALL_SPRUCE_PLACED_KEY
        );
        BiomeModifications.addFeature(
                BiomeSelectors.tag(ConventionalBiomeTags.IS_FOREST),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ModPlacedFeatures.WILLOW_TREE_PLACED_KEY
        );

        BiomeModifications.addCarver(
                BiomeSelectors.foundInOverworld(),
                ANOMALOUS_LAYER_CARVER
        );

        BiomeModifications.addCarver(
                BiomeSelectors.foundInOverworld(),
                CHAMBERS_CARVER
        );
        BiomeModifications.addCarver(
                BiomeSelectors.foundInOverworld(),
                CHAMBERS_CARVER_KEY
        );
    }
}