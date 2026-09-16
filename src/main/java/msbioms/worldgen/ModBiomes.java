package msbioms.worldgen;

import msbioms.MSBioms;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;

import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;

import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;

import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import net.minecraft.world.attribute.EnvironmentAttributes;


public class ModBiomes {

    /*
     * =========================================================
     * BIOME KEYS
     * =========================================================
     */

    /*
     * Соляной каменистый берег.
     *
     * msbioms:salt_stony_shore
     *
     * Этот биом используется как суб-биом
     * обычного minecraft:stony_shore через Biolith.
     */
    public static final ResourceKey<Biome> SALT_STONY_SHORE_KEY =
            ResourceKey.create(
                    Registries.BIOME,
                    Identifier.fromNamespaceAndPath(
                            MSBioms.MOD_ID,
                            "salt_stony_shore"
                    )
            );

    /*
     * Ивовый лес.
     *
     * msbioms:willow_forest
     *
     * Это уже полноценный самостоятельный биом.
     */
    public static final ResourceKey<Biome> WILLOW_FOREST_KEY =
            ResourceKey.create(
                    Registries.BIOME,
                    MSBioms.id("willow_forest")
            );


    public static void bootstrap(
            BootstrapContext<Biome> context
    ) {

        /*
         * =====================================================
         * REGISTRY LOOKUPS
         * =====================================================
         */

        /*
         * Доступ к Placed Features.
         *
         * Используется для генерации деревьев,
         * руды, растительности и других features.
         */
        HolderGetter<PlacedFeature> placedFeatures =
                context.lookup(Registries.PLACED_FEATURE);

        /*
         * Доступ к Configured World Carvers.
         *
         * В самих биомах MSBioms мы пока не добавляем
         * наши carvers через BiomeGenerationSettings.
         *
         * Chambers и anomalous layer подключаются
         * отдельно через ModWorldGeneration.
         */
        HolderGetter<ConfiguredWorldCarver<?>> worldCarvers =
                context.lookup(Registries.CONFIGURED_CARVER);


        /*
         * =====================================================
         * MOB SPAWNS
         * =====================================================
         *
         * Пока используем пустые настройки.
         *
         * Когда биом будет полностью рабочим,
         * сюда можно добавить:
         *
         * - лягушек;
         * - светлячков/будущих существ;
         * - обычных лесных мобов;
         * - редких существ самого MSBioms.
         */
        MobSpawnSettings mobSpawnSettings =
                new MobSpawnSettings.Builder()
                        .build();


        /*
         * =====================================================
         * SALT STONY SHORE
         * =====================================================
         *
         * Пока сохраняем существующую реализацию
         * практически без изменений.
         */

        BiomeGenerationSettings saltGeneration =
                new BiomeGenerationSettings.Builder(
                        placedFeatures,
                        worldCarvers
                )
                        .build();


        BiomeSpecialEffects saltEffects =
                new BiomeSpecialEffects.Builder()
                        .waterColor(0x4F9BB5)
                        .grassColorOverride(0x7FA39A)
                        .foliageColorOverride(0x6E9187)
                        .build();


        Biome saltStonyShore =
                new Biome.BiomeBuilder()

                        .hasPrecipitation(true)

                        .temperature(0.5F)

                        .downfall(0.5F)

                        .specialEffects(saltEffects)

                        .mobSpawnSettings(mobSpawnSettings)

                        .generationSettings(saltGeneration)

                        .setAttribute(
                                EnvironmentAttributes.WATER_FOG_COLOR,
                                0x315F70
                        )

                        .setAttribute(
                                EnvironmentAttributes.FOG_COLOR,
                                0xC5D8DC
                        )

                        .setAttribute(
                                EnvironmentAttributes.SKY_COLOR,
                                0x8FB7D9
                        )

                        .build();


        /*
         * =====================================================
         * WILLOW FOREST
         * =====================================================
         *
         * Здесь начинается собственная генерация
         * ивового леса.
         */

        BiomeGenerationSettings.Builder willowGenerationBuilder =
                new BiomeGenerationSettings.Builder(
                        placedFeatures,
                        worldCarvers
                );


        /*
         * -----------------------------------------------------
         * БАЗОВАЯ ГЕНЕРАЦИЯ OVERWORLD
         * -----------------------------------------------------
         *
         * Добавляем только базовые features.
         *
         * Carvers здесь НЕ добавляем.
         *
         * Причина:
         *
         * MSBioms использует собственную систему:
         *
         * - custom density functions;
         * - custom caves;
         * - ChambersCarver;
         * - anomalous layer.
         *
         * Поэтому не нужно заставлять сам биом
         * дополнительно подключать vanilla carvers.
         */

        BiomeDefaultFeatures.addDefaultOres(
                willowGenerationBuilder
        );

        BiomeDefaultFeatures.addDefaultSoftDisks(
                willowGenerationBuilder
        );

        BiomeDefaultFeatures.addDefaultMushrooms(
                willowGenerationBuilder
        );


        /*
         * -----------------------------------------------------
         * WILLOW TREE
         * -----------------------------------------------------
         *
         * Используем уже существующее дерево:
         *
         * ModPlacedFeatures.WILLOW_TREE_PLACED_KEY
         *
         * Само дерево генерируется через
         * Oh The Trees You'll Grow.
         *
         * Структуры:
         *
         * willow_base.nbt
         * willow_canopy.nbt
         *
         * Поэтому здесь мы только говорим:
         *
         * "В этом биоме разрешено генерировать
         *  ивовое дерево."
         */

        willowGenerationBuilder.addFeature(
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ModPlacedFeatures.WILLOW_TREE_PLACED_KEY
        );


        BiomeGenerationSettings willowGeneration =
                willowGenerationBuilder.build();


        /*
         * =====================================================
         * WILLOW FOREST VISUALS
         * =====================================================
         */

        BiomeSpecialEffects willowEffects =
                new BiomeSpecialEffects.Builder()

                        /*
                         * Вода:
                         * немного зеленее и теплее обычной ванильной.
                         */
                        .waterColor(0x4F8F78)

                        /*
                         * Трава:
                         * мягкий тёплый зелёный оттенок.
                         */
                        .grassColorOverride(0x82A85F)

                        /*
                         * Листва:
                         * более глубокая и приглушённая зелень.
                         */
                        .foliageColorOverride(0x6F9852)

                        .build();

        /*
         * =====================================================
         * WILLOW FOREST BIOME
         * =====================================================
         */
        Biome willowForest =
                new Biome.BiomeBuilder()

                        /*
                         * В ивовом лесу идут осадки.
                         */
                        .hasPrecipitation(true)

                        /*
                         * Умеренно прохладный климат.
                         */
                        .temperature(0.55F)

                        /*
                         * Высокая влажность.
                         */
                        .downfall(0.85F)
                        /*
                         * Визуальные параметры.
                         */
                        .specialEffects(willowEffects)

                        /*
                         * Спавны мобов.
                         */
                        .mobSpawnSettings(mobSpawnSettings)

                        /*
                         * Генерация.
                         */
                        .generationSettings(willowGeneration)

                        /*
                         * Подводный туман.
                         */
                        .setAttribute(
                                EnvironmentAttributes.WATER_FOG_COLOR,
                                0x315F70
                        )

                        /*
                         * Обычный туман.
                         */
                        .setAttribute(
                                EnvironmentAttributes.FOG_COLOR,
                                0xC5D8DC
                        )

                        /*
                         * Цвет неба.
                         */
                        .setAttribute(
                                EnvironmentAttributes.SKY_COLOR,
                                0x8FB7D9
                        )

                        .build();



        /*
         * =====================================================
         * REGISTRATION
         * =====================================================
         */

        /* Регистрируем ивовый лес.*/
        context.register(
                WILLOW_FOREST_KEY,
                willowForest
        );
        /* Регистрируем соляной каменистый берег.*/
        context.register(
                SALT_STONY_SHORE_KEY,
                saltStonyShore
        );
    }
}