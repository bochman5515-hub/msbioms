package msbioms.mixin;

import com.terraformersmc.biolith.impl.biome.BiomeCoordinator;
import com.terraformersmc.biolith.impl.biome.InterfaceBiomeSource;
import com.terraformersmc.biolith.impl.biome.OverworldBiomePlacement;
import com.terraformersmc.biolith.impl.config.BiolithState;
import msbioms.MSBioms;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(BiomeCoordinator.class)
public class BiomeCoordinatorMixin {

    private static final ResourceKey<DimensionType> MSBIOMS_OVERWORLD =
            ResourceKey.create(
                    Registries.DIMENSION_TYPE,
                    MSBioms.id("msbioms_overworld")
            );

    @Shadow
    private static @Nullable BiolithState OVERWORLD_STATE;

    @Shadow
    @Final
    public static OverworldBiomePlacement OVERWORLD;

    @Inject(
            method = "handleWorldStarting",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void msbioms$handleCustomOverworld(
            ServerLevel world,
            CallbackInfo ci
    ) {
        Optional<ResourceKey<DimensionType>> dimensionKey =
                world.dimensionTypeRegistration().unwrapKey();

        if (dimensionKey.isEmpty() ||
                !MSBIOMS_OVERWORLD.equals(dimensionKey.get())) {
            return;
        }

        if (OVERWORLD_STATE == null) {
            OVERWORLD_STATE = world.getDataStorage()
                    .computeIfAbsent(
                            BiolithState.getPersistentStateType(world)
                    );

            ((DimensionBiomePlacementInvoker) (Object) OVERWORLD)
                    .msbioms$serverReplaced(
                            OVERWORLD_STATE,
                            world
                    );
            BiomeSource biomeSource =
                    world.getChunkSource()
                            .getGenerator()
                            .getBiomeSource();

            if (biomeSource instanceof InterfaceBiomeSource biolithBiomeSource) {
                biolithBiomeSource.biolith$setDimensionType(
                        BuiltinDimensionTypes.OVERWORLD
                );

                MSBioms.LOGGER.info(
                        "Biolith: forced MSBioms biome source to use vanilla Overworld dimension type"
                );
            }

            MSBioms.LOGGER.info(
                    "Biolith: initialized Overworld biome placement for MSBioms custom dimension"
            );
        }

        ci.cancel();
    }
}