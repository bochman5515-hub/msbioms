package msbioms.worldgen;

import msbioms.MSBioms;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;


import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_SPRUCE_KEY =
            ResourceKey.create(
                    Registries.CONFIGURED_FEATURE,
                    Identifier.fromNamespaceAndPath(
                            MSBioms.MOD_ID,
                            "tall_spruce"
                    )
            );

    public static void bootstrap(
            BootstrapContext<ConfiguredFeature<?, ?>> context
    ) {

        TreeConfiguration tallSpruce =
                new TreeConfiguration.TreeConfigurationBuilder(

                        BlockStateProvider.simple(Blocks.SPRUCE_LOG),

                        new StraightTrunkPlacer(10, 4, 2),

                        BlockStateProvider.simple(Blocks.BIRCH_LEAVES),

                        new SpruceFoliagePlacer(
                                UniformInt.of(2, 3),      // радиус нижних слоёв
                                UniformInt.of(1, 2),      // радиус верхних слоёв
                                UniformInt.of(3, 5)       // высота кроны (минимум 4 блока)
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
    }
}