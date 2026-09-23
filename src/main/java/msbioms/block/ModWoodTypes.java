package msbioms.block;

import msbioms.MSBioms;

import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodTypes {

    public static final BlockSetType WILLOW_SET =
            new BlockSetTypeBuilder()
                    .register(MSBioms.id("willow"));

    public static final WoodType WILLOW =
            new WoodTypeBuilder()
                    .register(
                            MSBioms.id("willow"),
                            WILLOW_SET
                    );



    public static void register() {
        MSBioms.LOGGER.info(
                "Registering wood types for " + MSBioms.MOD_ID
        );

        FlammableBlockRegistry registry =
                FlammableBlockRegistry.getDefaultInstance();

        registry.add(ModBlocks.WILLOW_LOG, 5, 5);
        registry.add(ModBlocks.WILLOW_WOOD, 5, 5);
        registry.add(ModBlocks.STRIPPED_WILLOW_LOG, 5, 5);
        registry.add(ModBlocks.STRIPPED_WILLOW_WOOD, 5, 5);

        registry.add(ModBlocks.WILLOW_PLANKS, 5, 20);
        registry.add(ModBlocks.WILLOW_LEAVES, 30, 60);
    }

}