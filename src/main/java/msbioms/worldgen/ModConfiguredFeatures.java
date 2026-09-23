package msbioms.worldgen;

import dev.corgitaco.ohthetreesyoullgrow.world.level.levelgen.feature.TYGFeatures;
import dev.corgitaco.ohthetreesyoullgrow.world.level.levelgen.feature.configurations.TreeFromStructureNBTConfigV2;
import msbioms.MSBioms;
import msbioms.block.ModBlocks;

import msbioms.worldgen.feature.WillowVineFeature;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_SPRUCE_KEY =
            ResourceKey.create(
                    Registries.CONFIGURED_FEATURE,
                    MSBioms.id("tall_spruce")
            );

    public static final ResourceKey<ConfiguredFeature<?, ?>> WILLOW_TREE_NBT_KEY =
            ResourceKey.create(
                    Registries.CONFIGURED_FEATURE,
                    MSBioms.id("willow_tree_nbt")
            );

    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_WILLOW_TREE_NBT_KEY =
            ResourceKey.create(
                    Registries.CONFIGURED_FEATURE,
                    MSBioms.id("big_willow_tree_nbt")
            );

    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_WILLOW_TREE_NBT_KEY =
            ResourceKey.create(
                    Registries.CONFIGURED_FEATURE,
                    MSBioms.id("small_willow_tree_nbt")
            );
    public static final ResourceKey<ConfiguredFeature<?, ?>> WILLOW_NATURAL_VARIANT_KEY =
            ResourceKey.create(
                    Registries.CONFIGURED_FEATURE,
                    MSBioms.id("willow_natural_variant")
            );
    public static final ResourceKey<ConfiguredFeature<?, ?>> WILLOW_VINES_KEY =
            ResourceKey.create(
                    Registries.CONFIGURED_FEATURE,
                    MSBioms.id("willow_vines")
            );
    public static final ResourceKey<ConfiguredFeature<?, ?>> WILLOW_GROUND_VEGETATION_KEY =
            ResourceKey.create(
                    Registries.CONFIGURED_FEATURE,
                    MSBioms.id("willow_ground_vegetation")
            );
    public static final ResourceKey<ConfiguredFeature<?, ?>> WILLOW_HIGH_GRASS_KEY =
            ResourceKey.create(
                    Registries.CONFIGURED_FEATURE,
                    MSBioms.id("willow_high_grass")
            );
    public static final ResourceKey<ConfiguredFeature<?, ?>> BOG_PLANT_KEY =
            ResourceKey.create(
                    Registries.CONFIGURED_FEATURE,
                    MSBioms.id("bog_plant")
            );






    /**
     * Вторичный вариант:
     * 70% обычная ива
     * 30% большая ива
     */
    public static final ResourceKey<ConfiguredFeature<?, ?>> WILLOW_MEDIUM_BIG_VARIANT_KEY =
            ResourceKey.create(
                    Registries.CONFIGURED_FEATURE,
                    MSBioms.id("willow_medium_big_variant")
            );


    public static void bootstrap(
            BootstrapContext<ConfiguredFeature<?, ?>> context
    ) {

        // =========================================================
        // TALL SPRUCE
        // =========================================================

        var tallSpruce =
                new net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration.TreeConfigurationBuilder(

                        net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider.simple(
                                Blocks.SPRUCE_LOG),
                        new net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer(
                                10, 4, 2),
                        net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider.simple(
                                Blocks.BIRCH_LEAVES),
                        new net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer(
                                UniformInt.of(2, 3),
                                UniformInt.of(1, 2),
                                UniformInt.of(3, 5)),
                        new net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize(
                                1, 1, 2),
                        net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider.simple(
                                Blocks.DIRT)).build();
        context.register(
                TALL_SPRUCE_KEY,
                new ConfiguredFeature<>(
                        Feature.TREE,
                        tallSpruce));

        // =========================================================
        // Обычная / средняя ива
        // =========================================================
        TreeFromStructureNBTConfigV2 willowTreeConfig =
                new TreeFromStructureNBTConfigV2.Builder()
                        .baseLocation(
                                MSBioms.id(
                                        "features/trees/willow/willow_base"))
                        .canopyLocation(
                                MSBioms.id(
                                        "features/trees/willow/willow_canopy"))
                        .height(
                                UniformInt.of(1, 2))
                        .logProvider(
                                net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider.simple(
                                        ModBlocks.WILLOW_WOOD))
                        .leavesProvider(
                                List.of(
                                        net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider.simple(
                                                ModBlocks.WILLOW_LEAVES)))
                        .logTarget(
                                Set.of(Blocks.OAK_LOG))
                        .leavesTarget(
                                List.of(Blocks.OAK_LEAVES))
                        .growableOn(
                                BlockPredicate.matchesTag(
                                        BlockTags.SUBSTRATE_OVERWORLD))
                        .maxLogDepth(5)
                        .randomRotation(true)
                        .build();
        context.register(
                WILLOW_TREE_NBT_KEY,
                new ConfiguredFeature<>(
                        TYGFeatures.TREE_FROM_NBT_V2.get(),
                        willowTreeConfig));

        // =========================================================
        // Большая ива
        // =========================================================
        TreeFromStructureNBTConfigV2 bigWillowTreeConfig =
                new TreeFromStructureNBTConfigV2.Builder()
                        .baseLocation(
                                MSBioms.id(
                                        "features/trees/willow/big_willow_base"
                                )
                        )
                        .canopyLocation(
                                MSBioms.id(
                                        "features/trees/willow/big_willow_canopy"
                                )
                        )
                        .height(
                                UniformInt.of(1, 2)
                        )
                        .logProvider(
                                net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider.simple(
                                        ModBlocks.WILLOW_WOOD
                                )
                        )
                        .leavesProvider(
                                List.of(
                                        net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider.simple(
                                                ModBlocks.WILLOW_LEAVES
                                        )
                                )
                        )
                        .logTarget(
                                Set.of(Blocks.OAK_LOG)
                        )
                        .leavesTarget(
                                List.of(Blocks.OAK_LEAVES)
                        )
                        .growableOn(
                                BlockPredicate.matchesTag(
                                        BlockTags.SUBSTRATE_OVERWORLD
                                )
                        )
                        .maxLogDepth(5)
                        .randomRotation(true)
                        .build();

        context.register(
                BIG_WILLOW_TREE_NBT_KEY,
                new ConfiguredFeature<>(
                        TYGFeatures.TREE_FROM_NBT_V2.get(),
                        bigWillowTreeConfig
                )
        );
        context.register(
                WILLOW_GROUND_VEGETATION_KEY,
                new ConfiguredFeature<>(
                        ModFeatures.WILLOW_GROUND_VEGETATION,
                        NoneFeatureConfiguration.INSTANCE
                )
        );
        context.register(
                WILLOW_HIGH_GRASS_KEY,
                new ConfiguredFeature<>(
                        ModFeatures.WILLOW_HIGH_GRASS,
                        NoneFeatureConfiguration.INSTANCE
                )
        );

        context.register(
                BOG_PLANT_KEY,
                new ConfiguredFeature<>(
                        ModFeatures.BOG_PLANT,
                        NoneFeatureConfiguration.INSTANCE
                )
        );

        PlacedFeature smallWillowPlaced =
                new PlacedFeature(
                        context.lookup(Registries.CONFIGURED_FEATURE)
                                .getOrThrow(SMALL_WILLOW_TREE_NBT_KEY),
                        List.of()
                );

        PlacedFeature mediumBigVariantPlaced =
                new PlacedFeature(
                        context.lookup(Registries.CONFIGURED_FEATURE)
                                .getOrThrow(WILLOW_MEDIUM_BIG_VARIANT_KEY),
                        List.of()
                );
        context.register(
                WILLOW_VINES_KEY,
                new ConfiguredFeature<>(
                        ModFeatures.WILLOW_VINES,
                        NoneFeatureConfiguration.INSTANCE
                )
        );

        RandomFeatureConfiguration naturalWillowVariant =
                new RandomFeatureConfiguration(
                        List.of(
                                new WeightedPlacedFeature(
                                        Holder.direct(smallWillowPlaced),
                                        0.50F
                                )
                        ),
                        Holder.direct(mediumBigVariantPlaced)
                );

        context.register(
                WILLOW_NATURAL_VARIANT_KEY,
                new ConfiguredFeature<>(
                        Feature.RANDOM_SELECTOR,
                        naturalWillowVariant
                )
        );


        // =========================================================
        // Маленькая ива
        // =========================================================

        TreeFromStructureNBTConfigV2 smallWillowTreeConfig =
                new TreeFromStructureNBTConfigV2.Builder()
                        .baseLocation(
                                MSBioms.id(
                                        "features/trees/willow/small_willow_base"
                                )
                        )
                        .canopyLocation(
                                MSBioms.id(
                                        "features/trees/willow/small_willow_canopy"
                                )
                        )
                        .height(
                                UniformInt.of(1, 2)
                        )
                        .logProvider(
                                net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider.simple(
                                        ModBlocks.WILLOW_WOOD
                                )
                        )
                        .leavesProvider(
                                List.of(
                                        net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider.simple(
                                                ModBlocks.WILLOW_LEAVES
                                        )
                                )
                        )
                        .logTarget(
                                Set.of(Blocks.OAK_LOG)
                        )
                        .leavesTarget(
                                List.of(Blocks.OAK_LEAVES)
                        )
                        .growableOn(
                                BlockPredicate.matchesTag(
                                        BlockTags.SUBSTRATE_OVERWORLD
                                )
                        )
                        .maxLogDepth(5)
                        .randomRotation(true)
                        .build();

        context.register(
                SMALL_WILLOW_TREE_NBT_KEY,
                new ConfiguredFeature<>(
                        TYGFeatures.TREE_FROM_NBT_V2.get(),
                        smallWillowTreeConfig
                )
        );


        // =========================================================
        // Выбор между средней и большой ивой
        //
        // 70% → средняя
        // 30% → большая
        //
        // Если ни один weighted вариант не выпал,
        // используется defaultFeature = средняя.
        // =========================================================

        var configuredFeatures =
                context.lookup(Registries.CONFIGURED_FEATURE);

        Holder<ConfiguredFeature<?, ?>> mediumWillow =
                configuredFeatures.getOrThrow(
                        WILLOW_TREE_NBT_KEY
                );

        Holder<ConfiguredFeature<?, ?>> bigWillow =
                configuredFeatures.getOrThrow(
                        BIG_WILLOW_TREE_NBT_KEY
                );

        RandomFeatureConfiguration mediumBigVariant = getMediumBigVariant(mediumWillow, bigWillow);

        context.register(
                WILLOW_MEDIUM_BIG_VARIANT_KEY,
                new ConfiguredFeature<>(
                        Feature.RANDOM_SELECTOR,
                        mediumBigVariant
                )
        );
    }

    private static @NonNull RandomFeatureConfiguration getMediumBigVariant(Holder<ConfiguredFeature<?, ?>> mediumWillow, Holder<ConfiguredFeature<?, ?>> bigWillow) {
        PlacedFeature mediumWillowPlaced =
                new PlacedFeature(
                        mediumWillow,
                        List.of()
                );

        PlacedFeature bigWillowPlaced =
                new PlacedFeature(
                        bigWillow,
                        List.of()
                );

        Holder<PlacedFeature> mediumWillowHolder =
                Holder.direct(mediumWillowPlaced);

        Holder<PlacedFeature> bigWillowHolder =
                Holder.direct(bigWillowPlaced);

        RandomFeatureConfiguration mediumBigVariant =
                new RandomFeatureConfiguration(
                        List.of(
                                new WeightedPlacedFeature(
                                        bigWillowHolder,
                                        0.30F
                                )
                        ),
                        mediumWillowHolder
                );
        return mediumBigVariant;
    }


    // =============================================================
    // Саженец ивы
    //
    // 50% → маленькая ива
    // 50% → WILLOW_MEDIUM_BIG_VARIANT
    //             ├─ 70% средняя
    //             └─ 30% большая
    //
    // Итого:
    // 50% маленькая
    // 35% средняя
    // 15% большая
    // =============================================================

    public static final TreeGrower WILLOW =
            new TreeGrower(
                    "willow",
                    0.50F,

                    Optional.empty(),
                    Optional.empty(),

                    Optional.of(
                            SMALL_WILLOW_TREE_NBT_KEY
                    ),

                    Optional.of(
                            WILLOW_MEDIUM_BIG_VARIANT_KEY
                    ),

                    Optional.empty(),
                    Optional.empty()
            );
}