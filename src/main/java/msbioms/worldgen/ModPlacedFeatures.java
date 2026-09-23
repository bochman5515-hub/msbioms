package msbioms.worldgen;

import msbioms.MSBioms;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.TrapezoidInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> TALL_SPRUCE_PLACED_KEY =
            ResourceKey.create(
                    Registries.PLACED_FEATURE,
                    Identifier.fromNamespaceAndPath(
                            MSBioms.MOD_ID,
                            "tall_spruce"
                    )
            );

    public static final ResourceKey<PlacedFeature> WILLOW_TREE_PLACED_KEY =
            ResourceKey.create(
                    Registries.PLACED_FEATURE,
                    Identifier.fromNamespaceAndPath(
                            MSBioms.MOD_ID,
                            "willow_tree"
                    )
            );

    public static final ResourceKey<PlacedFeature> CHAMBER_PLACED_KEY =
            ResourceKey.create(
                    Registries.PLACED_FEATURE,
                    MSBioms.id("chamber")
            );
    public static final ResourceKey<PlacedFeature> WILLOW_VINES_PLACED_KEY =
            ResourceKey.create(
                    Registries.PLACED_FEATURE,
                    MSBioms.id("willow_vines")
            );
    public static final ResourceKey<PlacedFeature> WILLOW_GROUND_VEGETATION_PLACED_KEY =
            ResourceKey.create(
                    Registries.PLACED_FEATURE,
                    MSBioms.id("willow_ground_vegetation")
            );
    public static final ResourceKey<PlacedFeature> WILLOW_HIGH_GRASS_PLACED_KEY =
            ResourceKey.create(
                    Registries.PLACED_FEATURE,
                    MSBioms.id("willow_high_grass")
            );
    public static final ResourceKey<PlacedFeature> BOG_PLANT_PLACED_KEY =
            ResourceKey.create(
                    Registries.PLACED_FEATURE,
                    MSBioms.id("bog_plant")
            );


    public static void bootstrap(
            BootstrapContext<PlacedFeature> context
    ) {


        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures =
                context.lookup(Registries.CONFIGURED_FEATURE);


        // =========================================================
        // Высокая ель
        // =========================================================

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
        context.register(
                WILLOW_HIGH_GRASS_PLACED_KEY,

                new PlacedFeature(
                        configuredFeatures.getOrThrow(
                                ModConfiguredFeatures.WILLOW_HIGH_GRASS_KEY
                        ),

                        List.of(
                                CountPlacement.of(64),
                                InSquarePlacement.spread(),
                                HeightmapPlacement.onHeightmap(
                                        Heightmap.Types.MOTION_BLOCKING
                                ),
                                BiomeFilter.biome(),
                                RandomOffsetPlacement.vertical(
                                        new TrapezoidInt(-3, 3, 0)
                                ),
                                BlockPredicateFilter.forPredicate(
                                        BlockPredicate.matchesBlocks(
                                                Blocks.WATER
                                        )
                                )
                        )
                )
        );

        context.register(
                WILLOW_VINES_PLACED_KEY,

                new PlacedFeature(
                        configuredFeatures.getOrThrow(
                                ModConfiguredFeatures.WILLOW_VINES_KEY
                        ),

                        List.of(
                                CountPlacement.of(100),
                                InSquarePlacement.spread(),
                                HeightmapPlacement.onHeightmap(
                                        Heightmap.Types.WORLD_SURFACE_WG
                                ),
                                BiomeFilter.biome()
                        )
                )
        );
        context.register(
                WILLOW_GROUND_VEGETATION_PLACED_KEY,
                new PlacedFeature(
                        configuredFeatures.getOrThrow(
                                ModConfiguredFeatures.WILLOW_GROUND_VEGETATION_KEY
                        ),
                        List.of(
                                CountPlacement.of(8),
                                InSquarePlacement.spread(),
                                HeightmapPlacement.onHeightmap(
                                        Heightmap.Types.WORLD_SURFACE_WG
                                ),
                                BiomeFilter.biome()
                        )
                )
        );
        context.register(
                BOG_PLANT_PLACED_KEY,

                new PlacedFeature(
                        configuredFeatures.getOrThrow(
                                ModConfiguredFeatures.BOG_PLANT_KEY
                        ),

                        List.of(
                                CountPlacement.of(10),
                                InSquarePlacement.spread(),
                                HeightmapPlacement.onHeightmap(
                                        Heightmap.Types.MOTION_BLOCKING
                                ),
                                BiomeFilter.biome()
                        )
                )
        );



        // =========================================================
        // Ива
        //
        // Используется общий natural-вариант:
        //
        // 50% → маленькая
        // 35% → средняя
        // 15% → большая
        // =========================================================

        context.register(
                WILLOW_TREE_PLACED_KEY,

                new PlacedFeature(
                        configuredFeatures.getOrThrow(
                                ModConfiguredFeatures.WILLOW_NATURAL_VARIANT_KEY
                        ),

                        VegetationPlacements.treePlacement(
                                CountPlacement.of(1),
                                Blocks.OAK_SAPLING
                        )
                )
        );

    }
}