package msbioms.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;

public class ModSurfaceRules {

    /**
     * Поверхность нашего солёного каменного берега.

     * Пока логика максимально простая:

     * salt_stony_shore
     *        ↓
     * верхний блок камня
     *        ↓
     * кальцит
     */
    public static SurfaceRules.RuleSource makeRules(
            HolderGetter<Biome> biomes
    ) {

        /*
         * Условие:
         *
         * текущий столб находится в нашем биоме.
         */
        SurfaceRules.ConditionSource isSaltShore =
                SurfaceRules.isBiome(
                        biomes,
                        ModBiomes.SALT_STONY_SHORE_KEY
                );

        /*
         * Условие:
         *
         * текущий блок находится на поверхности.
         *
         * stoneDepthCheck:
         *
         * 0-> без дополнительного смещения
         * true -> учитываем поверхность
         * 0 -> дополнительная глубна
         * ON_FLOOR -> именно верхняя поверхность.
         */
        SurfaceRules.ConditionSource onSurface =
                SurfaceRules.stoneDepthCheck(
                        0,
                        false,
                        0,
                        net.minecraft.world.level.levelgen.placement.CaveSurface.FLOOR
                );

        /*
         * Если:
         *
         * 1. это наш биом;
         * 2. это поверхность;
         *
         * заменить блок на кальцит.
         */
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome((HolderGetter<Biome>) ModBiomes.SALT_STONY_SHORE_KEY),
                        SurfaceRules.ifTrue(
                                SurfaceRules.ON_FLOOR,
                                SurfaceRules.state(
                                        Blocks.CALCITE.defaultBlockState()
                                )
                        )
                )
        );
    }
}