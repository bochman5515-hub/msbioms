package msbioms.worldgen;

import msbioms.MSBioms;
import msbioms.worldgen.feature.*;

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
    public static final Feature<NoneFeatureConfiguration> THORNY_VINES =
            Registry.register(
                    BuiltInRegistries.FEATURE,
                    Identifier.fromNamespaceAndPath(
                            MSBioms.MOD_ID,
                            "thorny_vines"
                    ),
                    new ThornyVineFeature(
                            NoneFeatureConfiguration.CODEC
                    )
            );
    public static final Feature<NoneFeatureConfiguration> GLOOMY_GROUND_VEGETATION =
            Registry.register(
                    BuiltInRegistries.FEATURE,
                    Identifier.fromNamespaceAndPath(
                            MSBioms.MOD_ID,
                            "gloomy_ground_vegetation"
                    ),
                    new GloomyGroundVegetationFeature(
                            NoneFeatureConfiguration.CODEC
                    )
            );
    public static final Feature<NoneFeatureConfiguration> GLOOMY_WATER_VEGETATION =
            Registry.register(
                    BuiltInRegistries.FEATURE,
                    Identifier.fromNamespaceAndPath(
                            MSBioms.MOD_ID,
                            "gloomy_water_vegetation"
                    ),
                    new GloomyWaterVegetationFeature(
                            NoneFeatureConfiguration.CODEC
                    )
            );






    public static void registerModFeatures() {
        MSBioms.LOGGER.info(
                "Registering features for " + MSBioms.MOD_ID
        );
    }
}