package msbioms.item;

import msbioms.MSBioms;

import msbioms.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class ModItems {

    public static final Item DEAD_BRANCH = registerItem(
            "dead_branch",
            Item::new
    );

    public static final Item HIGH_GRASS = registerItem(
            "high_grass",
            HighGrassItem::new
    );
    public static final Item WATERGRASS = registerItem(
            "watergrass",
            WatergrassItem::new
    );
    public static final Item BOG = registerItem(
            "bog",
            BogItem::new
    );
    public static final Item MICRO_STONE = registerItem(
            "micro_stone",
            properties -> new MicroBlockItem(
                    properties,
                    () -> ModBlocks.MICRO_STONE
            )
    );
    public static final Item MICRO_OAK_PLANKS = registerItem(
            "micro_oak_planks",
            properties -> new MicroBlockItem(
                    properties,
                    () -> ModBlocks.MICRO_OAK_PLANKS
            )
    );
    // =========================
// Micro blocks — planks
// =========================

    public static final Item MICRO_BIRCH_PLANKS =
            registerItem(
                    "micro_birch_planks",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_BIRCH_PLANKS
                    )
            );

    public static final Item MICRO_DARK_OAK_PLANKS =
            registerItem(
                    "micro_dark_oak_planks",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_DARK_OAK_PLANKS
                    )
            );

    public static final Item MICRO_ACACIA_PLANKS =
            registerItem(
                    "micro_acacia_planks",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_ACACIA_PLANKS
                    )
            );

    public static final Item MICRO_JUNGLE_PLANKS =
            registerItem(
                    "micro_jungle_planks",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_JUNGLE_PLANKS
                    )
            );

    public static final Item MICRO_MANGROVE_PLANKS =
            registerItem(
                    "micro_mangrove_planks",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_MANGROVE_PLANKS
                    )
            );

    public static final Item MICRO_CHERRY_PLANKS =
            registerItem(
                    "micro_cherry_planks",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_CHERRY_PLANKS
                    )
            );

    public static final Item MICRO_PALE_OAK_PLANKS =
            registerItem(
                    "micro_pale_oak_planks",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_PALE_OAK_PLANKS
                    )
            );

    public static final Item MICRO_BAMBOO_MOSAIC =
            registerItem(
                    "micro_bamboo_mosaic",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_BAMBOO_MOSAIC
                    )
            );

    public static final Item MICRO_WARPED_PLANKS =
            registerItem(
                    "micro_warped_planks",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_WARPED_PLANKS
                    )
            );
    public static final Item MICRO_SPRUCE_PLANKS =
            registerItem(
                    "micro_spruce_planks",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_SPRUCE_PLANKS
                    )
            );

    public static final Item MICRO_CRIMSON_PLANKS =
            registerItem(
                    "micro_crimson_planks",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_CRIMSON_PLANKS
                    )
            );

    public static final Item MICRO_WILLOW_PLANKS =
            registerItem(
                    "micro_willow_planks",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_WILLOW_PLANKS
                    )
            );
    public static final Item MICRO_COBBLESTONE =
            registerItem(
                    "micro_cobblestone",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_COBBLESTONE
                    )
            );

    public static final Item MICRO_MOSSY_COBBLESTONE =
            registerItem(
                    "micro_mossy_cobblestone",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_MOSSY_COBBLESTONE
                    )
            );

    public static final Item MICRO_ANDESITE =
            registerItem(
                    "micro_andesite",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_ANDESITE
                    )
            );

    public static final Item MICRO_GRANITE =
            registerItem(
                    "micro_granite",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_GRANITE
                    )
            );

    public static final Item MICRO_DIORITE =
            registerItem(
                    "micro_diorite",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_DIORITE
                    )
            );

    public static final Item MICRO_COBBLED_DEEPSLATE =
            registerItem(
                    "micro_cobbled_deepslate",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_COBBLED_DEEPSLATE
                    )
            );

    public static final Item MICRO_TUFF =
            registerItem(
                    "micro_tuff",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_TUFF
                    )
            );

    public static final Item MICRO_TUFF_BRICKS =
            registerItem(
                    "micro_tuff_bricks",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_TUFF_BRICKS
                    )
            );

    public static final Item MICRO_DEEPSLATE_BRICKS =
            registerItem(
                    "micro_deepslate_bricks",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_DEEPSLATE_BRICKS
                    )
            );

    public static final Item MICRO_STONE_BRICKS =
            registerItem(
                    "micro_stone_bricks",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_STONE_BRICKS
                    )
            );

    public static final Item MICRO_BRICKS =
            registerItem(
                    "micro_bricks",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_BRICKS
                    )
            );

    public static final Item MICRO_DARK_PRISMARINE =
            registerItem(
                    "micro_dark_prismarine",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_DARK_PRISMARINE
                    )
            );

    public static final Item MICRO_PRISMARINE =
            registerItem(
                    "micro_prismarine",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_PRISMARINE
                    )
            );

    public static final Item MICRO_SMOOTH_SANDSTONE =
            registerItem(
                    "micro_smooth_sandstone",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_SMOOTH_SANDSTONE
                    )
            );

    public static final Item MICRO_SMOOTH_RED_SANDSTONE =
            registerItem(
                    "micro_smooth_red_sandstone",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_SMOOTH_RED_SANDSTONE
                    )
            );

    public static final Item MICRO_SMOOTH_QUARTZ =
            registerItem(
                    "micro_smooth_quartz",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_SMOOTH_QUARTZ
                    )
            );
    public static final Item MICRO_BLACKSTONE =
            registerItem(
                    "micro_blackstone",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_BLACKSTONE
                    )
            );

    public static final Item MICRO_POLISHED_BLACKSTONE_BRICKS =
            registerItem(
                    "micro_polished_blackstone_bricks",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_POLISHED_BLACKSTONE_BRICKS
                    )
            );

    public static final Item MICRO_MAGMA =
            registerItem(
                    "micro_magma",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_MAGMA
                    )
            );

    public static final Item MICRO_QUARTZ_BRICKS =
            registerItem(
                    "micro_quartz_bricks",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_QUARTZ_BRICKS
                    )
            );

    public static final Item MICRO_NETHER_BRICKS =
            registerItem(
                    "micro_nether_bricks",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_NETHER_BRICKS
                    )
            );

    public static final Item MICRO_RED_NETHER_BRICKS =
            registerItem(
                    "micro_red_nether_bricks",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_RED_NETHER_BRICKS
                    )
            );

    public static final Item MICRO_DRIPSTONE_BLOCK =
            registerItem(
                    "micro_dripstone_block",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_DRIPSTONE_BLOCK
                    )
            );

    public static final Item MICRO_SMOOTH_BASALT =
            registerItem(
                    "micro_smooth_basalt",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_SMOOTH_BASALT
                    )
            );

    public static final Item MICRO_AMETHYST_BLOCK =
            registerItem(
                    "micro_amethyst_block",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_AMETHYST_BLOCK
                    )
            );

    public static final Item MICRO_RESIN_BLOCK =
            registerItem(
                    "micro_resin_block",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_RESIN_BLOCK
                    )
            );

    public static final Item MICRO_RESIN_BRICKS =
            registerItem(
                    "micro_resin_bricks",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_RESIN_BRICKS
                    )
            );

    public static final Item MICRO_SULFUR =
            registerItem(
                    "micro_sulfur",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_SULFUR
                    )
            );

    public static final Item MICRO_SULFUR_BRICKS =
            registerItem(
                    "micro_sulfur_bricks",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_SULFUR_BRICKS
                    )
            );

    public static final Item MICRO_CINNABAR =
            registerItem(
                    "micro_cinnabar",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_CINNABAR
                    )
            );

    public static final Item MICRO_CINNABAR_BRICKS =
            registerItem(
                    "micro_cinnabar_bricks",
                    properties -> new MicroBlockItem(
                            properties,
                            () -> ModBlocks.MICRO_CINNABAR_BRICKS
                    )
            );


    private static Item registerItem(
            String name,
            Function<Item.Properties, Item> function
    ) {
        ResourceKey<Item> itemKey = ResourceKey.create(
                BuiltInRegistries.ITEM.key(),
                Identifier.fromNamespaceAndPath(
                        MSBioms.MOD_ID,
                        name
                )
        );

        return Registry.register(
                BuiltInRegistries.ITEM,
                itemKey,
                function.apply(
                        new Item.Properties()
                                .setId(itemKey)
                )
        );

    }

    public static ResourceKey<Item> getRK(Item item) {
        return BuiltInRegistries.ITEM
                .getResourceKey(item)
                .orElseThrow();
    }

    public static void registerModItems() {
        MSBioms.LOGGER.info(
                "Registering items for " + MSBioms.MOD_ID
        );
    }

}