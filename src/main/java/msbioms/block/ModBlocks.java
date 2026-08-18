package msbioms.block;

import msbioms.MSBioms;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class ModBlocks {

    public static Identifier ResourceLocation;
    public static final ResourceKey<Block> DRIED_EARTH_KEY =
            ResourceKey.create(
                    BuiltInRegistries.BLOCK.key(),
                    Identifier.fromNamespaceAndPath(MSBioms.MOD_ID, "dried_earth")
            );

    public static final Block DRIED_EARTH = register(
            Block::new,
            BlockBehaviour.Properties.of()
                    .strength(0.5f)
    );

    private static Block register(
            Function<BlockBehaviour.Properties, Block> blockFactory,
            BlockBehaviour.Properties properties
    ) {
        Block block = blockFactory.apply(properties.setId(ModBlocks.DRIED_EARTH_KEY));

        return Registry.register(
                BuiltInRegistries.BLOCK,
                ModBlocks.DRIED_EARTH_KEY,
                block
        );
    }

    public static void registerModBlocks() {
        MSBioms.LOGGER.info("Registering blocks for " + MSBioms.MOD_ID);
    }
}