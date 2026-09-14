package msbioms.datagen;

import msbioms.block.ModBlocks;
import msbioms.item.ModItems;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {

    public ModItemTagsProvider(
            FabricPackOutput output,
            CompletableFuture<HolderLookup.Provider> registriesFuture
    ) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider registries) {

        // =========================
        // Dead branch
        // =========================

        tag(ItemTags.DURABILITY_ENCHANTABLE)
                .add(ModItems.getRK(ModItems.DEAD_BRANCH));

        // =========================
        // Willow logs
        // =========================

        tag(ItemTags.LOGS)
                .add(ModItems.getRK(ModBlocks.WILLOW_LOG.asItem()))
                .add(ModItems.getRK(ModBlocks.WILLOW_WOOD.asItem()))
                .add(ModItems.getRK(ModBlocks.STRIPPED_WILLOW_LOG.asItem()))
                .add(ModItems.getRK(ModBlocks.STRIPPED_WILLOW_WOOD.asItem()));

        // =========================
        // Willow planks
        // =========================

        tag(ItemTags.PLANKS)
                .add(ModItems.getRK(ModBlocks.WILLOW_PLANKS.asItem()));

        // =========================
        // Willow leaves
        // =========================

        tag(ItemTags.LEAVES)
                .add(ModItems.getRK(ModBlocks.WILLOW_LEAVES.asItem()));

        // =========================
        // Willow sapling
        // =========================

        tag(ItemTags.SAPLINGS)
                .add(ModItems.getRK(ModBlocks.WILLOW_SAPLING.asItem()));

        tag(ItemTags.SIGNS)
                .add(ModItems.getRK(ModBlocks.WILLOW_SIGN.asItem()));

        tag(ItemTags.HANGING_SIGNS)
                .add(ModItems.getRK(ModBlocks.WILLOW_HANGING_SIGN.asItem()));

    }
}