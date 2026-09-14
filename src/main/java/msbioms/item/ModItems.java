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