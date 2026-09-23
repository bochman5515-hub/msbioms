package msbioms.datagen;

import msbioms.block.ModBlocks;

import msbioms.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {

    public ModBlockTagsProvider(
            FabricPackOutput output,
            CompletableFuture<HolderLookup.Provider> registriesFuture
    ) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider registries) {

        // =========================
        // Willow logs
        // =========================

        tag(BlockTags.LOGS)
                .add(ModBlocks.getRK(ModBlocks.WILLOW_LOG))
                .add(ModBlocks.getRK(ModBlocks.WILLOW_WOOD))
                .add(ModBlocks.getRK(ModBlocks.STRIPPED_WILLOW_LOG))
                .add(ModBlocks.getRK(ModBlocks.STRIPPED_WILLOW_WOOD));

        // =========================
        // Willow planks
        // =========================

        tag(BlockTags.PLANKS)
                .add(ModBlocks.getRK(ModBlocks.WILLOW_PLANKS));

        // =========================
        // Mineable with axe
        // =========================

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.getRK(ModBlocks.WILLOW_LOG))
                .add(ModBlocks.getRK(ModBlocks.WILLOW_WOOD))
                .add(ModBlocks.getRK(ModBlocks.STRIPPED_WILLOW_LOG))
                .add(ModBlocks.getRK(ModBlocks.STRIPPED_WILLOW_WOOD))
                .add(ModBlocks.getRK(ModBlocks.WILLOW_PLANKS))
                .add(ModBlocks.getRK(ModBlocks.WILLOW_STAIRS))
                .add(ModBlocks.getRK(ModBlocks.WILLOW_SLAB))
                .add(ModBlocks.getRK(ModBlocks.WILLOW_FENCE))
                .add(ModBlocks.getRK(ModBlocks.WILLOW_FENCE_GATE))
                .add(ModBlocks.getRK(ModBlocks.WILLOW_DOOR))
                .add(ModBlocks.getRK(ModBlocks.WILLOW_TRAPDOOR))
                .add(ModBlocks.getRK(ModBlocks.WILLOW_PRESSURE_PLATE))
                .add(ModBlocks.getRK(ModBlocks.WILLOW_BUTTON))
                .add(ModBlocks.getRK(ModBlocks.WILLOW_SIGN))
                .add(ModBlocks.getRK(ModBlocks.WILLOW_WALL_SIGN))
                .add(ModBlocks.getRK(ModBlocks.WILLOW_HANGING_SIGN))
                .add(ModBlocks.getRK(ModBlocks.WILLOW_WALL_HANGING_SIGN));

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.getRK(ModBlocks.MICRO_STONE))
                .add(ModBlocks.getRK(ModBlocks.MICRO_OAK_PLANKS));

        // =========================
        // Stairs / slabs
        // =========================

        tag(BlockTags.STAIRS)
                .add(ModBlocks.getRK(ModBlocks.WILLOW_STAIRS));

        tag(BlockTags.SLABS)
                .add(ModBlocks.getRK(ModBlocks.WILLOW_SLAB));

        // =========================
        // Leaves
        // =========================

        tag(BlockTags.LEAVES)
                .add(ModBlocks.getRK(ModBlocks.WILLOW_LEAVES));

        tag(BlockTags.SIGNS)
                .add(ModBlocks.getRK(ModBlocks.WILLOW_SIGN))
                .add(ModBlocks.getRK(ModBlocks.WILLOW_WALL_SIGN));

        // =========================
        // Fence
        // =========================

        tag(BlockTags.FENCES)
                .add(ModBlocks.getRK(ModBlocks.WILLOW_FENCE));

        // =========================
        // Fence gate
        // =========================

        tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.getRK(ModBlocks.WILLOW_FENCE_GATE));

        // =========================
        // Door
        // =========================

        tag(BlockTags.DOORS)
                .add(ModBlocks.getRK(ModBlocks.WILLOW_DOOR));

        // =========================
        // Trapdoor
        // =========================

        tag(BlockTags.TRAPDOORS)
                .add(ModBlocks.getRK(ModBlocks.WILLOW_TRAPDOOR));

        // =========================
        // Pressure plate
        // =========================

        tag(BlockTags.PRESSURE_PLATES)
                .add(ModBlocks.getRK(ModBlocks.WILLOW_PRESSURE_PLATE));

        // =========================
        // Button
        // =========================

        tag(BlockTags.BUTTONS)
                .add(ModBlocks.getRK(ModBlocks.WILLOW_BUTTON));

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.getRK(ModBlocks.PACKED_ICE_STAIRS))
                .add(ModBlocks.getRK(ModBlocks.PACKED_ICE_SLAB));

        tag(BlockTags.WALLS)
                .add(ModBlocks.getRK(ModBlocks.CALCITE_WALL))
                .add(ModBlocks.getRK(ModBlocks.PRISMARINE_BRICKS_WALL))
                .add(ModBlocks.getRK(ModBlocks.DARK_PRISMARINE_WALL))
                .add(ModBlocks.getRK(ModBlocks.SMOOTH_BASALT_WALL))
                .add(ModBlocks.getRK(ModBlocks.POLISHED_ANDESITE_WALL))
                .add(ModBlocks.getRK(ModBlocks.POLISHED_DIORITE_WALL))
                .add(ModBlocks.getRK(ModBlocks.RESIN_WALL))

                .add(ModBlocks.getRK(ModBlocks.PURPUR_BLOCK_WALL))
                .add(ModBlocks.getRK(ModBlocks.QUARTZ_BRICKS_WALL))
                .add(ModBlocks.getRK(ModBlocks.SMOOTH_QUARTZ_WALL))

                .add(ModBlocks.getRK(ModBlocks.DRIPSTONE_WALL))
                .add(ModBlocks.getRK(ModBlocks.PACKED_MUD_WALL))

                .add(ModBlocks.getRK(ModBlocks.SMOOTH_SANDSTONE_WALL))
                .add(ModBlocks.getRK(ModBlocks.SMOOTH_RED_SANDSTONE_WALL))

                .add(ModBlocks.getRK(ModBlocks.SMOOTH_STONE_WALL))
                .add(ModBlocks.getRK(ModBlocks.AMETHYST_WALL))

                .add(ModBlocks.getRK(ModBlocks.NETHERRACK_WALL))
                .add(ModBlocks.getRK(ModBlocks.HONEYCOMB_WALL))
                .add(ModBlocks.getRK(ModBlocks.BLUE_ICE_WALL))
                .add(ModBlocks.getRK(ModBlocks.CLAY_WALL))

                .add(ModBlocks.getRK(ModBlocks.POLISHED_GRANITE_WALL))
                .add(ModBlocks.getRK(ModBlocks.END_STONE_WALL));



    }
}