package msbioms.item;

import msbioms.MSBioms;
import msbioms.block.ModBlocks;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ModItems {

    public static final ResourceKey<Item> DRIED_EARTH_KEY =
            ResourceKey.create(
                    BuiltInRegistries.ITEM.key(),
                    Identifier.fromNamespaceAndPath(
                            MSBioms.MOD_ID,
                            "dried_earth"));
    public static final Item DRIED_EARTH = register(
            new BlockItem(
                    ModBlocks.DRIED_EARTH,
                    new Item.Properties().setId(DRIED_EARTH_KEY)));
    public static final ResourceKey<CreativeModeTab> MSBIOMS_TAB_KEY =
            ResourceKey.create(
                    BuiltInRegistries.CREATIVE_MODE_TAB.key(),
                    Identifier.fromNamespaceAndPath(
                            MSBioms.MOD_ID,
                            "msbioms_tab"));
    public static final CreativeModeTab MSBIOMS_TAB =
            FabricCreativeModeTab.builder()
                    .icon(() -> new ItemStack(DRIED_EARTH))
                    .title(Component.translatable("itemGroup.msbioms"))
                    .displayItems((params, output) -> {
                        output.accept(DRIED_EARTH);
                    })
                    .build();

    private static Item register(Item item) {
        return Registry.register(
                BuiltInRegistries.ITEM,
                DRIED_EARTH_KEY,
                item);}
    public static void registerModItems() {
        MSBioms.LOGGER.info(
                "Registering items for " + MSBioms.MOD_ID);

        Registry.register(
                BuiltInRegistries.CREATIVE_MODE_TAB,
                MSBIOMS_TAB_KEY,
                MSBIOMS_TAB);}
}