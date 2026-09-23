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

        tag(ItemTags.LOGS_THAT_BURN)
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


        // =========================
// Willow fence
// =========================

        tag(ItemTags.WOODEN_FENCES)
                .add(ModItems.getRK(ModBlocks.WILLOW_FENCE.asItem()));

// =========================
// Willow fence gate
// =========================

        tag(ItemTags.FENCE_GATES)
                .add(ModItems.getRK(ModBlocks.WILLOW_FENCE_GATE.asItem()));

// =========================
// Willow door
// =========================

        tag(ItemTags.WOODEN_DOORS)
                .add(ModItems.getRK(ModBlocks.WILLOW_DOOR.asItem()));

// =========================
// Willow trapdoor
// =========================

        tag(ItemTags.WOODEN_TRAPDOORS)
                .add(ModItems.getRK(ModBlocks.WILLOW_TRAPDOOR.asItem()));

// =========================
// Willow pressure plate
// =========================

        tag(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(ModItems.getRK(ModBlocks.WILLOW_PRESSURE_PLATE.asItem()));

// =========================
// Willow button
// =========================

        tag(ItemTags.WOODEN_BUTTONS)
                .add(ModItems.getRK(ModBlocks.WILLOW_BUTTON.asItem()));

    }
}