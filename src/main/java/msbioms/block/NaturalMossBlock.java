package msbioms.block;

import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.block.BonemealableFeaturePlacerBlock;

public class NaturalMossBlock extends BonemealableFeaturePlacerBlock {

    public NaturalMossBlock(
            Properties properties
    ) {
        super(
                ResourceKey.create(
                        Registries.CONFIGURED_FEATURE,
                        Identifier.fromNamespaceAndPath(
                                "msbioms",
                                "natural_moss_bonemeal"
                        )
                ),
                properties
        );
    }
}