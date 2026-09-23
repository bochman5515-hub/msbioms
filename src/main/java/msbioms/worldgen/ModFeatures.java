package msbioms.worldgen;

import msbioms.MSBioms;
import msbioms.worldgen.feature.BogPlantFeature;
import msbioms.worldgen.feature.WillowGroundVegetationFeature;
import msbioms.worldgen.feature.WillowHighGrassFeature;
import msbioms.worldgen.feature.WillowVineFeature;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class ModFeatures {

    public static final Feature<NoneFeatureConfiguration> WILLOW_VINES =
            Registry.register(
                    BuiltInRegistries.FEATURE,
                    Identifier.fromNamespaceAndPath(
                            MSBioms.MOD_ID,
                            "willow_vines"
                    ),
                    new WillowVineFeature(
                            NoneFeatureConfiguration.CODEC
                    )
            );
    public static final Feature<NoneFeatureConfiguration> WILLOW_GROUND_VEGETATION =
            Registry.register(
                    BuiltInRegistries.FEATURE,
                    Identifier.fromNamespaceAndPath(
                            MSBioms.MOD_ID,
                            "willow_ground_vegetation"
                    ),
                    new WillowGroundVegetationFeature(
                            NoneFeatureConfiguration.CODEC
                    )
            );
    public static final Feature<NoneFeatureConfiguration> WILLOW_HIGH_GRASS =
            Registry.register(
                    BuiltInRegistries.FEATURE,
                    Identifier.fromNamespaceAndPath(
                            MSBioms.MOD_ID,
                            "willow_high_grass"
                    ),
                    new WillowHighGrassFeature(
                            NoneFeatureConfiguration.CODEC
                    )
            );
    public static final Feature<NoneFeatureConfiguration> BOG_PLANT =
            Registry.register(
                    BuiltInRegistries.FEATURE,
                    Identifier.fromNamespaceAndPath(
                            MSBioms.MOD_ID,
                            "bog_plant"
                    ),
                    new BogPlantFeature(
                            NoneFeatureConfiguration.CODEC
                    )
            );






    public static void registerModFeatures() {
        MSBioms.LOGGER.info(
                "Registering features for " + MSBioms.MOD_ID
        );
    }
}