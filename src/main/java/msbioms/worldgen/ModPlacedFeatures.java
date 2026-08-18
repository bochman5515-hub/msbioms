package msbioms.worldgen;

import msbioms.MSBioms;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;

import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> TALL_SPRUCE_PLACED_KEY =
            ResourceKey.create(
                    Registries.PLACED_FEATURE,
                    Identifier.fromNamespaceAndPath(
                            MSBioms.MOD_ID,
                            "tall_spruce"
                    )
            );

    public static void bootstrap(
            BootstrapContext<PlacedFeature> context
    ) {

        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures =
                context.lookup(Registries.CONFIGURED_FEATURE);

        context.register(
                TALL_SPRUCE_PLACED_KEY,
                new PlacedFeature(
                        configuredFeatures.getOrThrow(
                                ModConfiguredFeatures.TALL_SPRUCE_KEY
                        ),

                        VegetationPlacements.treePlacement(
                                CountPlacement.of(1),
                                Blocks.SPRUCE_SAPLING
                        )
                )
        );
    }
}