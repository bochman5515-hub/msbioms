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

    public static final BlockSetType GLOOMY_SET =
            new BlockSetTypeBuilder()
                    .register(MSBioms.id("gloomy"));

    public static final WoodType GLOOMY =
            new WoodTypeBuilder()
                    .register(
                            MSBioms.id("gloomy"),
                            GLOOMY_SET
                    );

    public static final BlockSetType MAPLE_SET =
            new BlockSetTypeBuilder()
                    .register(MSBioms.id("maple"));

    public static final WoodType MAPLE =
            new WoodTypeBuilder()
                    .register(
                            MSBioms.id("maple"),
                            MAPLE_SET
                    );

    public static final BlockSetType POPLAR_SET =
            new BlockSetTypeBuilder()
                    .register(MSBioms.id("poplar"));

    public static final WoodType POPLAR =
            new WoodTypeBuilder()
                    .register(
                            MSBioms.id("poplar"),
                            POPLAR_SET
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


        registry.add(ModBlocks.GLOOMY_LOG, 5, 5);
        registry.add(ModBlocks.GLOOMY_WOOD, 5, 5);
        registry.add(ModBlocks.STRIPPED_GLOOMY_LOG, 5, 5);
        registry.add(ModBlocks.STRIPPED_GLOOMY_WOOD, 5, 5);
        registry.add(ModBlocks.GLOOMY_PLANKS, 5, 20);
        registry.add(ModBlocks.GLOOMY_LEAVES, 30, 60);
        registry.add(ModBlocks.GLOOMY_LEAVES_FLOVER, 30, 60);


        registry.add(ModBlocks.MAPLE_LOG, 5, 5);
        registry.add(ModBlocks.MAPLE_WOOD, 5, 5);
        registry.add(ModBlocks.STRIPPED_MAPLE_LOG, 5, 5);
        registry.add(ModBlocks.STRIPPED_MAPLE_WOOD, 5, 5);
        registry.add(ModBlocks.MAPLE_PLANKS, 5, 20);
        registry.add(ModBlocks.MAPLE_RED_LEAVES, 30, 60);
        registry.add(ModBlocks.MAPLE_YELLOW_LEAVES, 30, 60);

        registry.add(ModBlocks.POPLAR_LOG, 5, 5);
        registry.add(ModBlocks.POPLAR_WOOD, 5, 5);
        registry.add(ModBlocks.STRIPPED_POPLAR_LOG, 5, 5);
        registry.add(ModBlocks.STRIPPED_POPLAR_WOOD, 5, 5);
        registry.add(ModBlocks.POPLAR_PLANKS, 5, 20);
        registry.add(ModBlocks.POPLAR_LEAVES, 30, 60);
    }

}