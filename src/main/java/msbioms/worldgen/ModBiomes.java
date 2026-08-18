package msbioms.worldgen;

import msbioms.MSBioms;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;

import net.minecraft.data.worldgen.BootstrapContext;

import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;

import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import net.minecraft.world.attribute.EnvironmentAttributes;


public class ModBiomes {

    /*
     * ID нашего нового биома:
     *
     * msbioms:salt_stony_shore
     */
    public static final ResourceKey<Biome> SALT_STONY_SHORE_KEY =
            ResourceKey.create(
                    Registries.BIOME,
                    Identifier.fromNamespaceAndPath(
                            MSBioms.MOD_ID,
                            "salt_stony_shore"
                    )
            );


    public static void bootstrap(
            BootstrapContext<Biome> context
    ) {

        /*
         * Получаем доступ к уже зарегистрированным
         * Placed Features.
         *
         * Они понадобятся BiomeGenerationSettings.
         */
        HolderGetter<PlacedFeature> placedFeatures =
                context.lookup(Registries.PLACED_FEATURE);


        /*
         * Получаем доступ к Configured World Carvers.
         *
         * Пока мы их не используем, но API 26.2
         * требует передать lookup в Builder.
         */
        HolderGetter<ConfiguredWorldCarver<?>> worldCarvers =
                context.lookup(Registries.CONFIGURED_CARVER);


        /*
         * ============================
         * МОБЫ
         * ============================
         *
         * Пока оставляем биом без
         * дополнительных мобов.
         */
        MobSpawnSettings mobSpawnSettings =
                new MobSpawnSettings.Builder()
                        .build();


        /*
         * ============================
         * ГЕНЕРАЦИЯ
         * ============================
         *
         * В будущем сюда будем добавлять:
         *
         * - наши деревья;
         * - каменные образования;
         * - растительность;
         * - руды;
         * - другие features.
         */
        BiomeGenerationSettings generationSettings =
                new BiomeGenerationSettings.Builder(
                        placedFeatures,
                        worldCarvers
                )
                        .build();


        /*
         * ============================
         * ВНЕШНИЙ ВИД
         * ============================
         */
        BiomeSpecialEffects specialEffects =
                new BiomeSpecialEffects.Builder()
                        .waterColor(0x4F9BB5)
                        .grassColorOverride(0x7FA39A)
                        .foliageColorOverride(0x6E9187)
                        .build();
                        /*
                         * Цвет воды.
                         */
                        /*
                         * Цвет тумана обычного воздуха.
                         */


                        /*
                         * Цвет неба.
                         */


                        /*
                         * Цвет травы.
                         *
                         * Позже можем убрать,
                         * если берег должен иметь
                         * обычную ванильную траву.
                         */

                        /*
                         * Цвет листвы.
                         */





        /*
         * ============================
         * БИОМ
         * ============================
         */
        Biome biome =
                new Biome.BiomeBuilder()

                        /*
                         * В биоме идут осадки.
                         */
                        .hasPrecipitation(true)

                        /*
                         * Температура.
                         *
                         * 0.5 = прохладный климат.
                         */
                        .temperature(0.5F)

                        /*
                         * Влажность / количество осадков.
                         *
                         * 0.5 = среднее значение.
                         */
                        .downfall(0.5F)

                        /*
                         * Дополнительные визуальные параметры.
                         */
                        .specialEffects(specialEffects)

                        /*
                         * Спавн мобов.
                         */
                        .mobSpawnSettings(mobSpawnSettings)

                        /*
                         * Генерация.
                         */
                        .generationSettings(generationSettings)

                        /*
                         * Цвет подводного тумана
                         * в 26.2 задаётся через
                         * EnvironmentAttributes,
                         * а не через Builder.
                         */
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
         * Регистрируем биом.
         */
        context.register(
                SALT_STONY_SHORE_KEY,
                biome
        );
    }
}