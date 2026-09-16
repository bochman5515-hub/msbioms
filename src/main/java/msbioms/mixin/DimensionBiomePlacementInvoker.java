package msbioms.mixin;

import com.terraformersmc.biolith.impl.biome.DimensionBiomePlacement;
import com.terraformersmc.biolith.impl.config.BiolithState;
import net.minecraft.server.level.ServerLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(DimensionBiomePlacement.class)
public interface DimensionBiomePlacementInvoker {

    @Invoker("serverReplaced")
    void msbioms$serverReplaced(BiolithState state, ServerLevel world);
}