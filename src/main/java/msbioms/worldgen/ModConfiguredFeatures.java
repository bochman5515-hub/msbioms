package msbioms.worldgen;

import dev.corgitaco.ohthetreesyoullgrow.world.level.levelgen.feature.TYGFeatures;
import dev.corgitaco.ohthetreesyoullgrow.world.level.levelgen.feature.configurations.TreeFromStructureNBTConfigV2;
import msbioms.MSBioms;
import msbioms.block.ModBlocks;
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
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_SPRUCE_KEY =
            ResourceKey.create(
                    Registries.CONFIGURED_FEATURE,
                    MSBioms.id("tall_spruce"));

    public static final ResourceKey<ConfiguredFeature<?, ?>> WILLOW_TREE_NBT_KEY =
            ResourceKey.create(
                    Registries.CONFIGURED_FEATURE,
                    MSBioms.id("willow_tree_nbt"));





    public static void bootstrap(
            BootstrapContext<ConfiguredFeature<?, ?>> context) {
        TreeConfiguration tallSpruce =
                new TreeConfiguration.TreeConfigurationBuilder(

                        BlockStateProvider.simple(Blocks.SPRUCE_LOG),

                        new StraightTrunkPlacer(10, 4, 2),

                        BlockStateProvider.simple(Blocks.BIRCH_LEAVES),

                        new SpruceFoliagePlacer(
                                UniformInt.of(2, 3),
                                UniformInt.of(1, 2),
                                UniformInt.of(3, 5)
                        ),

                        new TwoLayersFeatureSize(1, 1, 2),

                        BlockStateProvider.simple(Blocks.DIRT)

                ).build();

        context.register(
                TALL_SPRUCE_KEY,
                new ConfiguredFeature<>(
                        Feature.TREE,
                        tallSpruce
                )
        );


        TreeFromStructureNBTConfigV2 willowTreeConfig =
                new TreeFromStructureNBTConfigV2.Builder()
                        .baseLocation(
                                MSBioms.id("features/trees/willow/willow_base")
                        )
                        .canopyLocation(
                                MSBioms.id("features/trees/willow/willow_canopy")
                        )
                        .height(
                                UniformInt.of(1, 2)
                        )
                        .logProvider(
                                BlockStateProvider.simple(ModBlocks.WILLOW_WOOD)
                        )
                        .leavesProvider(
                                List.of(
                                        BlockStateProvider.simple(ModBlocks.WILLOW_LEAVES)
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
                        .randomRotation(false)
                        .build();

        context.register(
                WILLOW_TREE_NBT_KEY,
                new ConfiguredFeature<>(
                        TYGFeatures.TREE_FROM_NBT_V2.get(),
                        willowTreeConfig
                )
        );



    }
    public static final TreeGrower WILLOW =
            new TreeGrower(
                    "willow",
                    Optional.empty(),
                    Optional.of(WILLOW_TREE_NBT_KEY),
                    Optional.empty()
            );
}