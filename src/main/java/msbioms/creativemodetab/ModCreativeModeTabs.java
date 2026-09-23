package msbioms.creativemodetab;

import msbioms.MSBioms;
import msbioms.block.ModBlocks;

import msbioms.item.ModItems;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {

    public static final CreativeModeTab MSBIOMS_BLOCKS =
            Registry.register(
                    BuiltInRegistries.CREATIVE_MODE_TAB,
                    Identifier.fromNamespaceAndPath(
                            MSBioms.MOD_ID,
                            "msbioms_blocks"
                    ),
                    FabricCreativeModeTab.builder()
                            .icon(() -> new ItemStack(ModBlocks.WILLOW_PLANKS))
                            .title(Component.translatable(
                                    "creativemodetab.msbioms.msbioms_blocks"
                            ))
                            .displayItems((parameters, output) -> {

                                output.accept(ModBlocks.DRIED_EARTH);
                                output.accept(ModBlocks.SALT_BLOCk);
                                output.accept(ModBlocks.MOSS);
                                output.accept(ModBlocks.MOSS_CARPET);

                                output.accept(ModBlocks.WILLOW_LOG);
                                output.accept(ModBlocks.WILLOW_WOOD);

                                output.accept(ModBlocks.STRIPPED_WILLOW_LOG);
                                output.accept(ModBlocks.STRIPPED_WILLOW_WOOD);

                                output.accept(ModBlocks.WILLOW_PLANKS);
                                output.accept(ModBlocks.WILLOW_STAIRS);
                                output.accept(ModBlocks.WILLOW_SLAB);
                                output.accept(ModBlocks.WILLOW_LEAVES);

                                output.accept(ModBlocks.WILLOW_FENCE);
                                output.accept(ModBlocks.WILLOW_FENCE_GATE);
                                output.accept(ModBlocks.WILLOW_DOOR);
                                output.accept(ModBlocks.WILLOW_TRAPDOOR);
                                output.accept(ModBlocks.WILLOW_PRESSURE_PLATE);
                                output.accept(ModBlocks.WILLOW_BUTTON);

                                output.accept(ModBlocks.WILLOW_SIGN);
                                output.accept(ModBlocks.WILLOW_HANGING_SIGN);

                                output.accept(ModItems.DEAD_BRANCH);
                                output.accept(ModBlocks.WILLOW_SAPLING);
                                output.accept(ModItems.WATERGRASS);
                                output.accept(ModItems.BOG);
                                output.accept(ModItems.HIGH_GRASS);
                                output.accept(ModBlocks.WILLOW_VINE);

                            })
                            .build()
            );
    public static final CreativeModeTab MSBIOMS_VANILLA_VARIANTS =
            Registry.register(
                    BuiltInRegistries.CREATIVE_MODE_TAB,
                    Identifier.fromNamespaceAndPath(
                            MSBioms.MOD_ID,
                            "msbioms_vanilla_variants"
                    ),
                    FabricCreativeModeTab.builder()
                            .icon(() -> new ItemStack(ModBlocks.PACKED_ICE_STAIRS))
                            .title(Component.translatable(
                                    "creativemodetab.msbioms.msbioms_vanilla_variants"
                            ))
                            .displayItems((parameters, output) -> {

                                // Packed ice
                                output.accept(ModBlocks.PACKED_ICE_STAIRS);
                                output.accept(ModBlocks.PACKED_ICE_SLAB);
                                output.accept(ModBlocks.BLUE_ICE_SLAB);
                                output.accept(ModBlocks.BLUE_ICE_STAIRS);
                                output.accept(ModBlocks.BLUE_ICE_WALL);

                                output.accept(ModBlocks.CALCITE_SLAB);
                                output.accept(ModBlocks.CALCITE_STAIRS);
                                output.accept(ModBlocks.CALCITE_WALL);
                                output.accept(ModBlocks.SMOOTH_BASALT_SLAB);
                                output.accept(ModBlocks.SMOOTH_BASALT_STAIRS);
                                output.accept(ModBlocks.SMOOTH_BASALT_WALL);
                                output.accept(ModBlocks.PRISMARINE_BRICKS_WALL);
                                output.accept(ModBlocks.DARK_PRISMARINE_WALL);

                                output.accept(ModBlocks.POLISHED_ANDESITE_WALL);
                                output.accept(ModBlocks.POLISHED_DIORITE_WALL);
                                output.accept(ModBlocks.POLISHED_GRANITE_WALL);

                                output.accept(ModBlocks.PURPUR_BLOCK_WALL);
                                output.accept(ModBlocks.QUARTZ_BRICKS_WALL);
                                output.accept(ModBlocks.SMOOTH_QUARTZ_WALL);

                                output.accept(ModBlocks.RESIN_SLAB);
                                output.accept(ModBlocks.RESIN_STAIRS);
                                output.accept(ModBlocks.RESIN_WALL);

                                output.accept(ModBlocks.DRIPSTONE_SLAB);
                                output.accept(ModBlocks.DRIPSTONE_STAIRS);
                                output.accept(ModBlocks.DRIPSTONE_WALL);

                                output.accept(ModBlocks.PACKED_MUD_SLAB);
                                output.accept(ModBlocks.PACKED_MUD_STAIRS);
                                output.accept(ModBlocks.PACKED_MUD_WALL);

                                output.accept(ModBlocks.SMOOTH_SANDSTONE_WALL);
                                output.accept(ModBlocks.SMOOTH_RED_SANDSTONE_WALL);

                                output.accept(ModBlocks.END_STONE_SLAB);
                                output.accept(ModBlocks.END_STONE_STAIRS);
                                output.accept(ModBlocks.END_STONE_WALL);

                                output.accept(ModBlocks.AMETHYST_SLAB);
                                output.accept(ModBlocks.AMETHYST_STAIRS);
                                output.accept(ModBlocks.AMETHYST_WALL);

                                output.accept(ModBlocks.SMOOTH_STONE_STAIRS);
                                output.accept(ModBlocks.SMOOTH_STONE_WALL);

                                output.accept(ModBlocks.NETHERRACK_SLAB);
                                output.accept(ModBlocks.NETHERRACK_STAIRS);
                                output.accept(ModBlocks.NETHERRACK_WALL);

                                output.accept(ModBlocks.HONEYCOMB_SLAB);
                                output.accept(ModBlocks.HONEYCOMB_STAIRS);
                                output.accept(ModBlocks.HONEYCOMB_WALL);

                                output.accept(ModBlocks.CLAY_SLAB);
                                output.accept(ModBlocks.CLAY_STAIRS);
                                output.accept(ModBlocks.CLAY_WALL);

                                output.accept(ModBlocks.MUD_SLAB);
                                output.accept(ModBlocks.BONE_SLAB);
                                output.accept(ModBlocks.HAY_SLAB);
                                output.accept(ModBlocks.BASALT_SLAB);

                                output.accept(ModItems.MICRO_STONE);

                                output.accept(ModItems.MICRO_OAK_PLANKS);
                                output.accept(ModItems.MICRO_BIRCH_PLANKS);
                                output.accept(ModItems.MICRO_DARK_OAK_PLANKS);
                                output.accept(ModItems.MICRO_ACACIA_PLANKS);
                                output.accept(ModItems.MICRO_JUNGLE_PLANKS);
                                output.accept(ModItems.MICRO_MANGROVE_PLANKS);
                                output.accept(ModItems.MICRO_CHERRY_PLANKS);
                                output.accept(ModItems.MICRO_PALE_OAK_PLANKS);
                                output.accept(ModItems.MICRO_BAMBOO_MOSAIC);
                                output.accept(ModItems.MICRO_WARPED_PLANKS);
                                output.accept(ModItems.MICRO_CRIMSON_PLANKS);
                                output.accept(ModItems.MICRO_WILLOW_PLANKS);
                                output.accept(ModItems.MICRO_SPRUCE_PLANKS);

                                output.accept(ModItems.MICRO_COBBLESTONE);
                                output.accept(ModItems.MICRO_MOSSY_COBBLESTONE);
                                output.accept(ModItems.MICRO_ANDESITE);
                                output.accept(ModItems.MICRO_GRANITE);
                                output.accept(ModItems.MICRO_DIORITE);
                                output.accept(ModItems.MICRO_COBBLED_DEEPSLATE);
                                output.accept(ModItems.MICRO_TUFF);
                                output.accept(ModItems.MICRO_TUFF_BRICKS);
                                output.accept(ModItems.MICRO_DEEPSLATE_BRICKS);
                                output.accept(ModItems.MICRO_STONE_BRICKS);
                                output.accept(ModItems.MICRO_BRICKS);
                                output.accept(ModItems.MICRO_DARK_PRISMARINE);
                                output.accept(ModItems.MICRO_PRISMARINE);
                                output.accept(ModItems.MICRO_SMOOTH_SANDSTONE);
                                output.accept(ModItems.MICRO_SMOOTH_RED_SANDSTONE);
                                output.accept(ModItems.MICRO_SMOOTH_QUARTZ);
                                output.accept(ModItems.MICRO_BLACKSTONE);
                                output.accept(ModItems.MICRO_POLISHED_BLACKSTONE_BRICKS);
                                output.accept(ModItems.MICRO_END_BRICKS);
                                output.accept(ModItems.MICRO_QUARTZ_BRICKS);
                                output.accept(ModItems.MICRO_NETHER_BRICKS);
                                output.accept(ModItems.MICRO_RED_NETHER_BRICKS);
                                output.accept(ModItems.MICRO_DRIPSTONE_BLOCK);
                                output.accept(ModItems.MICRO_SMOOTH_BASALT);
                                output.accept(ModItems.MICRO_AMETHYST_BLOCK);
                                output.accept(ModItems.MICRO_RESIN_BLOCK);
                                output.accept(ModItems.MICRO_RESIN_BRICKS);
                                output.accept(ModItems.MICRO_SULFUR);
                                output.accept(ModItems.MICRO_SULFUR_BRICKS);
                                output.accept(ModItems.MICRO_CINNABAR);
                                output.accept(ModItems.MICRO_CINNABAR_BRICKS);


                            })
                            .build()
            );

    public static void registerModCreativeModeTabs() {
        MSBioms.LOGGER.info(
                "Registering Creative Mode Tabs for " + MSBioms.MOD_ID
        );
    }
}