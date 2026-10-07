package msbioms.worldgen;

import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;

import java.util.function.Consumer;

public class ModBiomeParameters {

    /**
     * Добавляет наши биомы в климатическую карту Overworld.
     *
     * Этот метод можно использовать для добавления сразу
     * нескольких собственных биомов.
     */
    public static void addCustomBiomes(
            Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> biomes
    ) {

        biomes.accept(
                Pair.of(
                        Climate.parameters(
                                Climate.Parameter.span(
                                        -0.2F,
                                        0.4F
                                ),

                                Climate.Parameter.span(
                                        -1.0F,
                                        1.0F
                                ),

                                Climate.Parameter.span(
                                        -0.25F,
                                        -0.05F
                                ),

                                Climate.Parameter.span(
                                        -1.0F,
                                        1.0F
                                ),

                                Climate.Parameter.point(
                                        0.0F
                                ),

                                Climate.Parameter.span(
                                        -1.0F,
                                        1.0F
                                ),

                                0.0F
                        ),

                        ModBiomes.SALT_STONY_SHORE_KEY
                )
        );
    }


    /**
     * Добавляет ивовый лес в климатическую карту Overworld.

     * На этом этапе используем параметры, которые уже
     * показывали появление willow_forest в обычном мире.

     * Дальше будем отдельно калибровать:

     * - частоту;
     * - соседние биомы;
     * - влажность;
     * - рельеф;
     * - расположение низин.
     */
    public static void addWillowForest(
            Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> biomes
    ) {

        biomes.accept(
                Pair.of(
                        Climate.parameters(

                                Climate.Parameter.span(0.20F, 0.55F),
                                Climate.Parameter.span(0.10F, 1.00F),
                                Climate.Parameter.span(-0.11F, 0.55F),
                                Climate.Parameter.span(-0.375F, 0.45F),
                                Climate.Parameter.point(0.0F),
                                Climate.Parameter.span(-0.40F, 0.40F),
                                0.0F
                        ),

                        ModBiomes.WILLOW_FOREST_KEY
                )
        );

    }
    public static void addGloomyForest(
            Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> biomes
    ) {
        biomes.accept(
                Pair.of(
                        Climate.parameters(
                                Climate.Parameter.span(0.10F, 0.50F),   // temperature
                                Climate.Parameter.span(0.10F, 0.80F),   // humidity
                                Climate.Parameter.span(-0.05F, 0.50F),  // continentalness
                                Climate.Parameter.span(-0.20F, 0.45F),  // erosion
                                Climate.Parameter.point(0.0F),           // depth
                                Climate.Parameter.span(-0.20F, 0.30F),         // weirdness
                                0.0F
                        ),
                        ModBiomes.GLOOMY_FOREST_KEY
                )
        );
    }
}