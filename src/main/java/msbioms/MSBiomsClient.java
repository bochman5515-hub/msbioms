package msbioms;

import msbioms.block.ModBlocks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.minecraft.client.color.block.BlockTintSources;
import net.fabricmc.fabric.api.client.rendering.v1.ColorResolverRegistry;

import java.util.List;

public class MSBiomsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        BlockColorRegistry.register(
                List.of(BlockTintSources.foliage()),
                ModBlocks.WILLOW_LEAVES,
                ModBlocks.MOSS,
                ModBlocks.MOSS_CARPET,
                ModBlocks.WILLOW_VINE_PLANT,
                ModBlocks.WILLOW_VINE
        );
    }
}