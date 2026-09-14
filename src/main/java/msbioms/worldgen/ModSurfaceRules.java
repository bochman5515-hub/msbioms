package msbioms.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class ModSurfaceRules {

    public static SurfaceRules.RuleSource makeRules(
            HolderGetter<Biome> biomes
    ) {
        SurfaceRules.ConditionSource isSaltShore =
                SurfaceRules.isBiome(
                        biomes,
                        ModBiomes.SALT_STONY_SHORE_KEY
                );

        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        isSaltShore,
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