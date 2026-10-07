package msbioms.particle;

import msbioms.MSBioms;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.Identifier;

public class ModParticles {

    public static final SimpleParticleType RED_MAPLE_LEAVES =
            FabricParticleTypes.simple();

    public static final SimpleParticleType YELLOW_MAPLE_LEAVES =
            FabricParticleTypes.simple();

    public static final SimpleParticleType FIREFLY =
            FabricParticleTypes.simple();
    public static final SimpleParticleType POPLAR_FLUFF =
            FabricParticleTypes.simple();

    public static void register() {

        Registry.register(
                BuiltInRegistries.PARTICLE_TYPE,
                Identifier.fromNamespaceAndPath(
                        MSBioms.MOD_ID,
                        "red_maple_leaves"
                ),
                RED_MAPLE_LEAVES
        );

        Registry.register(
                BuiltInRegistries.PARTICLE_TYPE,
                Identifier.fromNamespaceAndPath(
                        MSBioms.MOD_ID,
                        "yellow_maple_leaves"
                ),
                YELLOW_MAPLE_LEAVES
        );

        Registry.register(
                BuiltInRegistries.PARTICLE_TYPE,
                Identifier.fromNamespaceAndPath(
                        MSBioms.MOD_ID,
                        "firefly"
                ),
                FIREFLY
        );
        Registry.register(
                BuiltInRegistries.PARTICLE_TYPE,
                Identifier.fromNamespaceAndPath(
                        MSBioms.MOD_ID,
                        "poplar_fluff"
                ),
                POPLAR_FLUFF
        );

    }
}