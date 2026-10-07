package msbioms;

import msbioms.block.ModBlocks;

import msbioms.particle.FireflySpawner;
import msbioms.particle.ModParticles;
import msbioms.particle.PoplarFluffParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.client.particle.FallingLeavesParticle;
import net.minecraft.client.particle.FireflyParticle;

import java.util.List;

public class MSBiomsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        BlockColorRegistry.register(
                List.of(BlockTintSources.foliage()),
                ModBlocks.WILLOW_LEAVES,
                ModBlocks.GLOOMY_LEAVES,
                ModBlocks.GLOOMY_LEAVES_FLOVER,
                ModBlocks.MOSS,
                ModBlocks.MOSS_CARPET,
                ModBlocks.WILLOW_VINE_PLANT,
                ModBlocks.WILLOW_VINE
        );
        ParticleProviderRegistry.getInstance().register(
                ModParticles.RED_MAPLE_LEAVES,
                FallingLeavesParticle.CherryProvider::new
        );

        ParticleProviderRegistry.getInstance().register(
                ModParticles.YELLOW_MAPLE_LEAVES,
                FallingLeavesParticle.PaleOakProvider::new
        );
        ParticleProviderRegistry.getInstance().register(
                ModParticles.FIREFLY,
                FireflyParticle.FireflyProvider::new
        );
        FireflySpawner.register();

        ParticleProviderRegistry.getInstance().register(
                ModParticles.POPLAR_FLUFF,
                PoplarFluffParticle.Provider::new
        );


    }
}