package msbioms.datagen;

import msbioms.block.ModBlocks;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(
            FabricPackOutput output,
            CompletableFuture<HolderLookup.Provider> registriesFuture
    ) {
        super(output, registriesFuture);
    }

    @Override
    protected @NonNull RecipeProvider createRecipeProvider(
            HolderLookup.@NonNull Provider registryLookup,
            @NonNull RecipeOutput exporter
    ) {
        return new RecipeProvider(registryLookup, exporter) {

            @Override
            public void buildRecipes() {

                // Willow fence
                shaped(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.WILLOW_FENCE,
                        3)
                        .pattern("PSP")
                        .pattern("PSP")
                        .define('P', ModBlocks.WILLOW_PLANKS)
                        .define('S', Items.STICK)
                        .unlockedBy(
                                getHasName(ModBlocks.WILLOW_PLANKS),
                                has(ModBlocks.WILLOW_PLANKS))
                        .save(output);

                // Willow fence gate
                shaped(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.WILLOW_FENCE_GATE)
                        .pattern("SPS")
                        .pattern("SPS")
                        .define('P', ModBlocks.WILLOW_PLANKS)
                        .define('S', Items.STICK)
                        .unlockedBy(
                                getHasName(ModBlocks.WILLOW_PLANKS),
                                has(ModBlocks.WILLOW_PLANKS))
                        .save(output);

                stairBuilder(
                        ModBlocks.WILLOW_STAIRS,
                        Ingredient.of(ModBlocks.WILLOW_PLANKS))
                        .unlockedBy(
                                getHasName(ModBlocks.WILLOW_PLANKS),
                                has(ModBlocks.WILLOW_PLANKS))
                        .save(output);
                slabBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.WILLOW_SLAB,
                        Ingredient.of(ModBlocks.WILLOW_PLANKS))
                        .unlockedBy(
                                getHasName(ModBlocks.WILLOW_PLANKS),
                                has(ModBlocks.WILLOW_PLANKS))
                        .save(output);
                shapeless(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.WILLOW_PLANKS,
                        4)
                        .requires(ModBlocks.WILLOW_LOG)
                        .unlockedBy(
                                getHasName(ModBlocks.WILLOW_LOG),
                                has(ModBlocks.WILLOW_LOG))
                        .save(output, "msbioms:willow_planks_from_log");

                shapeless(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.WILLOW_PLANKS,
                        4)
                        .requires(ModBlocks.WILLOW_WOOD)
                        .unlockedBy(
                                getHasName(ModBlocks.WILLOW_WOOD),
                                has(ModBlocks.WILLOW_WOOD))
                        .save(output, "msbioms:willow_planks_from_wood");

                shapeless(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.WILLOW_PLANKS,
                        4)
                        .requires(ModBlocks.STRIPPED_WILLOW_LOG)
                        .unlockedBy(
                                getHasName(ModBlocks.STRIPPED_WILLOW_LOG),
                                has(ModBlocks.STRIPPED_WILLOW_LOG))
                        .save(output, "msbioms:willow_planks_from_stripped_log");

                shapeless(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.WILLOW_PLANKS,
                        4)
                        .requires(ModBlocks.STRIPPED_WILLOW_WOOD)
                        .unlockedBy(
                                getHasName(ModBlocks.STRIPPED_WILLOW_WOOD),
                                has(ModBlocks.STRIPPED_WILLOW_WOOD))
                        .save(output, "msbioms:willow_planks_from_stripped_wood");
                shaped(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.WILLOW_DOOR,
                        3)
                        .pattern("PP")
                        .pattern("PP")
                        .pattern("PP")
                        .define('P', ModBlocks.WILLOW_PLANKS)
                        .unlockedBy(
                                getHasName(ModBlocks.WILLOW_PLANKS),
                                has(ModBlocks.WILLOW_PLANKS))
                        .save(output);
                shaped(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.WILLOW_TRAPDOOR,
                        2)
                        .pattern("PPP")
                        .pattern("PPP")
                        .define('P', ModBlocks.WILLOW_PLANKS)
                        .unlockedBy(
                                getHasName(ModBlocks.WILLOW_PLANKS),
                                has(ModBlocks.WILLOW_PLANKS))
                        .save(output);
                shapeless(
                        RecipeCategory.REDSTONE,
                        ModBlocks.WILLOW_BUTTON)
                        .requires(ModBlocks.WILLOW_PLANKS)
                        .unlockedBy(
                                getHasName(ModBlocks.WILLOW_PLANKS),
                                has(ModBlocks.WILLOW_PLANKS))
                        .save(output);
                shaped(
                        RecipeCategory.REDSTONE,
                        ModBlocks.WILLOW_PRESSURE_PLATE)
                        .pattern("PP")
                        .define('P', ModBlocks.WILLOW_PLANKS)
                        .unlockedBy(
                                getHasName(ModBlocks.WILLOW_PLANKS),
                                has(ModBlocks.WILLOW_PLANKS))
                        .save(output);
                shaped(
                        RecipeCategory.DECORATIONS,
                        ModBlocks.WILLOW_SIGN,
                        3)
                        .pattern("PPP")
                        .pattern("PPP")
                        .pattern(" S ")
                        .define('P', ModBlocks.WILLOW_PLANKS)
                        .define('S', Items.STICK)
                        .unlockedBy(
                                getHasName(ModBlocks.WILLOW_PLANKS),
                                has(ModBlocks.WILLOW_PLANKS))
                        .save(output);
                shaped(
                        RecipeCategory.DECORATIONS,
                        ModBlocks.WILLOW_HANGING_SIGN,
                        6)
                        .pattern("C C")
                        .pattern("PPP")
                        .pattern("PPP")
                        .define('P', ModBlocks.STRIPPED_WILLOW_LOG)
                        .define('C', Items.IRON_CHAIN)
                        .unlockedBy(
                                getHasName(ModBlocks.STRIPPED_WILLOW_LOG),
                                has(ModBlocks.STRIPPED_WILLOW_LOG))
                        .save(output);


                stairBuilder(
                        ModBlocks.PACKED_ICE_STAIRS,
                        Ingredient.of(Blocks.PACKED_ICE))
                        .unlockedBy(
                                getHasName(Blocks.PACKED_ICE),
                                has(Blocks.PACKED_ICE))
                        .save(output);
                slabBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.PACKED_ICE_SLAB,
                        Ingredient.of(Blocks.PACKED_ICE))
                        .unlockedBy(
                                getHasName(Blocks.PACKED_ICE),
                                has(Blocks.PACKED_ICE))
                        .save(output);


                stairBuilder(
                        ModBlocks.CALCITE_STAIRS,
                        Ingredient.of(Blocks.CALCITE))
                        .unlockedBy(
                                getHasName(Blocks.CALCITE),
                                has(Blocks.CALCITE))
                        .save(output);

                slabBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.CALCITE_SLAB,
                        Ingredient.of(Blocks.CALCITE))
                        .unlockedBy(
                                getHasName(Blocks.CALCITE),
                                has(Blocks.CALCITE))
                        .save(output);

                wallBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.CALCITE_WALL,
                        Ingredient.of(Blocks.CALCITE))
                        .unlockedBy(
                                getHasName(Blocks.CALCITE),
                                has(Blocks.CALCITE))
                        .save(output);


                stairBuilder(
                        ModBlocks.SMOOTH_BASALT_STAIRS,
                        Ingredient.of(Blocks.SMOOTH_BASALT))
                        .unlockedBy(
                                getHasName(Blocks.SMOOTH_BASALT),
                                has(Blocks.SMOOTH_BASALT))
                        .save(output);

                slabBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.SMOOTH_BASALT_SLAB,
                        Ingredient.of(Blocks.SMOOTH_BASALT))
                        .unlockedBy(
                                getHasName(Blocks.SMOOTH_BASALT),
                                has(Blocks.SMOOTH_BASALT))
                        .save(output);

                wallBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.SMOOTH_BASALT_WALL,
                        Ingredient.of(Blocks.SMOOTH_BASALT))
                        .unlockedBy(
                                getHasName(Blocks.SMOOTH_BASALT),
                                has(Blocks.SMOOTH_BASALT))
                        .save(output);

                stairBuilder(
                        ModBlocks.RESIN_STAIRS,
                        Ingredient.of(Blocks.RESIN_BLOCK))
                        .unlockedBy(
                                getHasName(Blocks.RESIN_BLOCK),
                                has(Blocks.RESIN_BLOCK))
                        .save(output);

                slabBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.RESIN_SLAB,
                        Ingredient.of(Blocks.RESIN_BLOCK))
                        .unlockedBy(
                                getHasName(Blocks.RESIN_BLOCK),
                                has(Blocks.RESIN_BLOCK))
                        .save(output);

                wallBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.RESIN_WALL,
                        Ingredient.of(Blocks.RESIN_BLOCK))
                        .unlockedBy(
                                getHasName(Blocks.RESIN_BLOCK),
                                has(Blocks.RESIN_BLOCK))
                        .save(output);

                stairBuilder(
                        ModBlocks.DRIPSTONE_STAIRS,
                        Ingredient.of(Blocks.DRIPSTONE_BLOCK))
                        .unlockedBy(
                                getHasName(Blocks.DRIPSTONE_BLOCK),
                                has(Blocks.DRIPSTONE_BLOCK))
                        .save(output);

                slabBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.DRIPSTONE_SLAB,
                        Ingredient.of(Blocks.DRIPSTONE_BLOCK))
                        .unlockedBy(
                                getHasName(Blocks.DRIPSTONE_BLOCK),
                                has(Blocks.DRIPSTONE_BLOCK))
                        .save(output);

                wallBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.DRIPSTONE_WALL,
                        Ingredient.of(Blocks.DRIPSTONE_BLOCK))
                        .unlockedBy(
                                getHasName(Blocks.DRIPSTONE_BLOCK),
                                has(Blocks.DRIPSTONE_BLOCK))
                        .save(output);

                stairBuilder(
                        ModBlocks.PACKED_MUD_STAIRS,
                        Ingredient.of(Blocks.PACKED_MUD))
                        .unlockedBy(
                                getHasName(Blocks.PACKED_MUD),
                                has(Blocks.PACKED_MUD))
                        .save(output);

                slabBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.PACKED_MUD_SLAB,
                        Ingredient.of(Blocks.PACKED_MUD))
                        .unlockedBy(
                                getHasName(Blocks.PACKED_MUD),
                                has(Blocks.PACKED_MUD))
                        .save(output);

                wallBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.PACKED_MUD_WALL,
                        Ingredient.of(Blocks.PACKED_MUD))
                        .unlockedBy(
                                getHasName(Blocks.PACKED_MUD),
                                has(Blocks.PACKED_MUD))
                        .save(output);

                wallBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.PRISMARINE_BRICKS_WALL,
                        Ingredient.of(Blocks.PRISMARINE_BRICKS))
                        .unlockedBy(
                                getHasName(Blocks.PRISMARINE_BRICKS),
                                has(Blocks.PRISMARINE_BRICKS))
                        .save(output);

                wallBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.DARK_PRISMARINE_WALL,
                        Ingredient.of(Blocks.DARK_PRISMARINE))
                        .unlockedBy(
                                getHasName(Blocks.DARK_PRISMARINE),
                                has(Blocks.DARK_PRISMARINE))
                        .save(output);

                wallBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.POLISHED_ANDESITE_WALL,
                        Ingredient.of(Blocks.POLISHED_ANDESITE))
                        .unlockedBy(
                                getHasName(Blocks.POLISHED_ANDESITE),
                                has(Blocks.POLISHED_ANDESITE))
                        .save(output);
                wallBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.POLISHED_DIORITE_WALL,
                        Ingredient.of(Blocks.POLISHED_DIORITE))
                        .unlockedBy(
                                getHasName(Blocks.POLISHED_DIORITE),
                                has(Blocks.POLISHED_DIORITE))
                        .save(output);
                wallBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.POLISHED_GRANITE_WALL,
                        Ingredient.of(Blocks.POLISHED_GRANITE))
                        .unlockedBy(
                                getHasName(Blocks.POLISHED_GRANITE),
                                has(Blocks.POLISHED_GRANITE))
                        .save(output);

                wallBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.PURPUR_BLOCK_WALL,
                        Ingredient.of(Blocks.PURPUR_BLOCK))
                        .unlockedBy(
                                getHasName(Blocks.PURPUR_BLOCK),
                                has(Blocks.PURPUR_BLOCK))
                        .save(output);
                wallBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.QUARTZ_BRICKS_WALL,
                        Ingredient.of(Blocks.QUARTZ_BRICKS))
                        .unlockedBy(
                                getHasName(Blocks.QUARTZ_BRICKS),
                                has(Blocks.QUARTZ_BRICKS))
                        .save(output);
                wallBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.SMOOTH_QUARTZ_WALL,
                        Ingredient.of(Blocks.SMOOTH_QUARTZ))
                        .unlockedBy(
                                getHasName(Blocks.SMOOTH_QUARTZ),
                                has(Blocks.SMOOTH_QUARTZ))
                        .save(output);

                wallBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.SMOOTH_SANDSTONE_WALL,
                        Ingredient.of(Blocks.SMOOTH_SANDSTONE))
                        .unlockedBy(
                                getHasName(Blocks.SMOOTH_SANDSTONE),
                                has(Blocks.SMOOTH_SANDSTONE))
                        .save(output);
                wallBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.SMOOTH_RED_SANDSTONE_WALL,
                        Ingredient.of(Blocks.SMOOTH_RED_SANDSTONE))
                        .unlockedBy(
                                getHasName(Blocks.SMOOTH_RED_SANDSTONE),
                                has(Blocks.SMOOTH_RED_SANDSTONE))
                        .save(output);

                stairBuilder(
                        ModBlocks.NETHERRACK_STAIRS,
                        Ingredient.of(Blocks.NETHERRACK))
                        .unlockedBy(
                                getHasName(Blocks.NETHERRACK),
                                has(Blocks.NETHERRACK))
                        .save(output);

                slabBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.NETHERRACK_SLAB,
                        Ingredient.of(Blocks.NETHERRACK))
                        .unlockedBy(
                                getHasName(Blocks.NETHERRACK),
                                has(Blocks.NETHERRACK))
                        .save(output);

                wallBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.NETHERRACK_WALL,
                        Ingredient.of(Blocks.NETHERRACK))
                        .unlockedBy(
                                getHasName(Blocks.NETHERRACK),
                                has(Blocks.NETHERRACK))
                        .save(output);


                stairBuilder(
                        ModBlocks.HONEYCOMB_STAIRS,
                        Ingredient.of(Blocks.HONEYCOMB_BLOCK))
                        .unlockedBy(
                                getHasName(Blocks.HONEYCOMB_BLOCK),
                                has(Blocks.HONEYCOMB_BLOCK))
                        .save(output);

                slabBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.HONEYCOMB_SLAB,
                        Ingredient.of(Blocks.HONEYCOMB_BLOCK))
                        .unlockedBy(
                                getHasName(Blocks.HONEYCOMB_BLOCK),
                                has(Blocks.HONEYCOMB_BLOCK))
                        .save(output);

                wallBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.HONEYCOMB_WALL,
                        Ingredient.of(Blocks.HONEYCOMB_BLOCK))
                        .unlockedBy(
                                getHasName(Blocks.HONEYCOMB_BLOCK),
                                has(Blocks.HONEYCOMB_BLOCK))
                        .save(output);

                stairBuilder(
                        ModBlocks.CLAY_STAIRS,
                        Ingredient.of(Blocks.CLAY))
                        .unlockedBy(
                                getHasName(Blocks.CLAY),
                                has(Blocks.CLAY))
                        .save(output);

                slabBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.CLAY_SLAB,
                        Ingredient.of(Blocks.CLAY))
                        .unlockedBy(
                                getHasName(Blocks.CLAY),
                                has(Blocks.CLAY))
                        .save(output);

                wallBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.CLAY_WALL,
                        Ingredient.of(Blocks.CLAY))
                        .unlockedBy(
                                getHasName(Blocks.CLAY),
                                has(Blocks.CLAY))
                        .save(output);

                stairBuilder(
                        ModBlocks.BLUE_ICE_STAIRS,
                        Ingredient.of(Blocks.BLUE_ICE))
                        .unlockedBy(
                                getHasName(Blocks.BLUE_ICE),
                                has(Blocks.BLUE_ICE))
                        .save(output);

                slabBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.BLUE_ICE_SLAB,
                        Ingredient.of(Blocks.BLUE_ICE))
                        .unlockedBy(
                                getHasName(Blocks.BLUE_ICE),
                                has(Blocks.BLUE_ICE))
                        .save(output);

                wallBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.BLUE_ICE_WALL,
                        Ingredient.of(Blocks.BLUE_ICE))
                        .unlockedBy(
                                getHasName(Blocks.BLUE_ICE),
                                has(Blocks.BLUE_ICE))
                        .save(output);


                slabBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.BONE_SLAB,
                        Ingredient.of(Blocks.BONE_BLOCK))
                        .unlockedBy(
                                getHasName(Blocks.BONE_BLOCK),
                                has(Blocks.BONE_BLOCK))
                        .save(output);
                slabBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.HAY_SLAB,
                        Ingredient.of(Blocks.HAY_BLOCK))
                        .unlockedBy(
                                getHasName(Blocks.HAY_BLOCK),
                                has(Blocks.HAY_BLOCK))
                        .save(output);
                slabBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.BASALT_SLAB,
                        Ingredient.of(Blocks.BASALT))
                        .unlockedBy(
                                getHasName(Blocks.BASALT),
                                has(Blocks.BASALT))
                        .save(output);
                slabBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.MUD_SLAB,
                        Ingredient.of(Blocks.MUD))
                        .unlockedBy(
                                getHasName(Blocks.MUD),
                                has(Blocks.MUD))
                        .save(output);










                stairBuilder(
                        ModBlocks.END_STONE_STAIRS,
                        Ingredient.of(Blocks.END_STONE))
                        .unlockedBy(
                                getHasName(Blocks.END_STONE),
                                has(Blocks.END_STONE))
                        .save(output);

                slabBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.END_STONE_SLAB,
                        Ingredient.of(Blocks.END_STONE))
                        .unlockedBy(
                                getHasName(Blocks.END_STONE),
                                has(Blocks.END_STONE))
                        .save(output);

                wallBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.END_STONE_WALL,
                        Ingredient.of(Blocks.END_STONE))
                        .unlockedBy(
                                getHasName(Blocks.END_STONE),
                                has(Blocks.END_STONE))
                        .save(output);


                stairBuilder(
                        ModBlocks.AMETHYST_STAIRS,
                        Ingredient.of(Blocks.AMETHYST_BLOCK))
                        .unlockedBy(
                                getHasName(Blocks.AMETHYST_BLOCK),
                                has(Blocks.AMETHYST_BLOCK))
                        .save(output);

                slabBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.AMETHYST_SLAB,
                        Ingredient.of(Blocks.AMETHYST_BLOCK))
                        .unlockedBy(
                                getHasName(Blocks.AMETHYST_BLOCK),
                                has(Blocks.AMETHYST_BLOCK))
                        .save(output);

                wallBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.AMETHYST_WALL,
                        Ingredient.of(Blocks.AMETHYST_BLOCK))
                        .unlockedBy(
                                getHasName(Blocks.AMETHYST_BLOCK),
                                has(Blocks.AMETHYST_BLOCK))
                        .save(output);


                stairBuilder(
                        ModBlocks.SMOOTH_STONE_STAIRS,
                        Ingredient.of(Blocks.SMOOTH_STONE))
                        .unlockedBy(
                                getHasName(Blocks.SMOOTH_STONE),
                                has(Blocks.SMOOTH_STONE))
                        .save(output);


                wallBuilder(
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.SMOOTH_STONE_WALL,
                        Ingredient.of(Blocks.SMOOTH_STONE))
                        .unlockedBy(
                                getHasName(Blocks.SMOOTH_STONE),
                                has(Blocks.SMOOTH_STONE))
                        .save(output);



            }
        };
    }

    @Override
    public @NonNull String getName() {
        return "MSBiomsRecipeProvider";
    }
}