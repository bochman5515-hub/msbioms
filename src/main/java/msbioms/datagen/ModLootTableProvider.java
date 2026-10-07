package msbioms.datagen;

import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import msbioms.block.ModBlocks;
import msbioms.item.ModItems;


import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootSubProvider {

    public ModLootTableProvider(
            FabricPackOutput output,
            CompletableFuture<HolderLookup.Provider> registriesFuture
    ) {
        super(output, registriesFuture);
    }
    private void dropDoublePlant(
            Block upper,
            Block lower,
            Item item
    ) {
        // Только нижняя часть имеет loot table.
        add(lower, block -> createSingleItemTable(item));
    }

    @Override
    public void generate() {

        // Dried Earth
        dropSelf(ModBlocks.DRIED_EARTH);
        dropSelf(ModBlocks.DRIED_EARTH);
        dropSelf(ModBlocks.SALT_BLOCk);
        dropSelf(ModBlocks.CALCITE_SLAB);
        dropSelf(ModBlocks.CALCITE_STAIRS);
        dropSelf(ModBlocks.CALCITE_WALL);
        dropSelf(ModBlocks.SMOOTH_BASALT_SLAB);
        dropSelf(ModBlocks.SMOOTH_BASALT_STAIRS);
        dropSelf(ModBlocks.SMOOTH_BASALT_WALL);
        dropSelf(ModBlocks.PRISMARINE_BRICKS_WALL);
        dropSelf(ModBlocks.DARK_PRISMARINE_WALL);

        dropSelf(ModBlocks.POLISHED_DIORITE_WALL);
        dropSelf(ModBlocks.POLISHED_GRANITE_WALL);
        dropSelf(ModBlocks.POLISHED_ANDESITE_WALL);

        dropSelf(ModBlocks.QUARTZ_BRICKS_WALL);
        dropSelf(ModBlocks.PURPUR_BLOCK_WALL);
        dropSelf(ModBlocks.SMOOTH_QUARTZ_WALL);
        dropSelf(ModBlocks.MOSS);
        dropSelf(ModBlocks.MOSS_CARPET);

        dropSelf(ModBlocks.MUD_SLAB);
        dropSelf(ModBlocks.BONE_SLAB);
        dropSelf(ModBlocks.HAY_SLAB);
        dropSelf(ModBlocks.BASALT_SLAB);

        dropSelf(ModBlocks.RESIN_SLAB);
        dropSelf(ModBlocks.RESIN_STAIRS);
        dropSelf(ModBlocks.RESIN_WALL);

        dropSelf(ModBlocks.DRIPSTONE_SLAB);
        dropSelf(ModBlocks.DRIPSTONE_STAIRS);
        dropSelf(ModBlocks.DRIPSTONE_WALL);

        dropSelf(ModBlocks.PACKED_MUD_SLAB);
        dropSelf(ModBlocks.PACKED_MUD_STAIRS);
        dropSelf(ModBlocks.PACKED_MUD_WALL);

        dropSelf(ModBlocks.END_STONE_SLAB);
        dropSelf(ModBlocks.END_STONE_STAIRS);
        dropSelf(ModBlocks.END_STONE_WALL);

        dropSelf(ModBlocks.AMETHYST_SLAB);
        dropSelf(ModBlocks.AMETHYST_STAIRS);
        dropSelf(ModBlocks.AMETHYST_WALL);

        dropSelf(ModBlocks.SMOOTH_STONE_STAIRS);
        dropSelf(ModBlocks.SMOOTH_STONE_WALL);

        dropSelf(ModBlocks.NETHERRACK_WALL);
        dropSelf(ModBlocks.NETHERRACK_SLAB);
        dropSelf(ModBlocks.NETHERRACK_STAIRS);

        dropSelf(ModBlocks.HONEYCOMB_WALL);
        dropSelf(ModBlocks.HONEYCOMB_SLAB);
        dropSelf(ModBlocks.HONEYCOMB_STAIRS);



        // Willow building blocks

        dropSelf(ModBlocks.WILLOW_LOG);
        dropSelf(ModBlocks.WILLOW_WOOD);
        dropSelf(ModBlocks.STRIPPED_WILLOW_LOG);
        dropSelf(ModBlocks.STRIPPED_WILLOW_WOOD);
        dropSelf(ModBlocks.WILLOW_PLANKS);
        dropSelf(ModBlocks.WILLOW_STAIRS);
        dropSelf(ModBlocks.WILLOW_SLAB);
        dropSelf(ModBlocks.WILLOW_FENCE);
        dropSelf(ModBlocks.WILLOW_FENCE_GATE);
        dropSelf(ModBlocks.WILLOW_PRESSURE_PLATE);
        dropSelf(ModBlocks.WILLOW_BUTTON);
        dropSelf(ModBlocks.WILLOW_TRAPDOOR);

        dropSelf(ModBlocks.GLOOMY_LOG);
        dropSelf(ModBlocks.GLOOMY_WOOD);
        dropSelf(ModBlocks.STRIPPED_GLOOMY_LOG);
        dropSelf(ModBlocks.STRIPPED_GLOOMY_WOOD);
        dropSelf(ModBlocks.GLOOMY_PLANKS);
        dropSelf(ModBlocks.GLOOMY_STAIRS);
        dropSelf(ModBlocks.GLOOMY_SLAB);
        dropSelf(ModBlocks.GLOOMY_FENCE);
        dropSelf(ModBlocks.GLOOMY_FENCE_GATE);
        dropSelf(ModBlocks.GLOOMY_PRESSURE_PLATE);
        dropSelf(ModBlocks.GLOOMY_BUTTON);
        dropSelf(ModBlocks.GLOOMY_TRAPDOOR);

        dropSelf(ModBlocks.MAPLE_LOG);
        dropSelf(ModBlocks.MAPLE_WOOD);
        dropSelf(ModBlocks.STRIPPED_MAPLE_LOG);
        dropSelf(ModBlocks.STRIPPED_MAPLE_WOOD);
        dropSelf(ModBlocks.MAPLE_PLANKS);
        dropSelf(ModBlocks.MAPLE_STAIRS);
        dropSelf(ModBlocks.MAPLE_SLAB);
        dropSelf(ModBlocks.MAPLE_FENCE);
        dropSelf(ModBlocks.MAPLE_FENCE_GATE);
        dropSelf(ModBlocks.MAPLE_PRESSURE_PLATE);
        dropSelf(ModBlocks.MAPLE_BUTTON);
        dropSelf(ModBlocks.MAPLE_TRAPDOOR);

        dropSelf(ModBlocks.POPLAR_LOG);
        dropSelf(ModBlocks.POPLAR_WOOD);
        dropSelf(ModBlocks.STRIPPED_POPLAR_LOG);
        dropSelf(ModBlocks.STRIPPED_POPLAR_WOOD);
        dropSelf(ModBlocks.POPLAR_PLANKS);
        dropSelf(ModBlocks.POPLAR_STAIRS);
        dropSelf(ModBlocks.POPLAR_SLAB);
        dropSelf(ModBlocks.POPLAR_FENCE);
        dropSelf(ModBlocks.POPLAR_FENCE_GATE);
        dropSelf(ModBlocks.POPLAR_PRESSURE_PLATE);
        dropSelf(ModBlocks.POPLAR_BUTTON);
        dropSelf(ModBlocks.POPLAR_TRAPDOOR);




        add(ModBlocks.WILLOW_DOOR, this::createDoorTable);
        add(ModBlocks.GLOOMY_DOOR, this::createDoorTable);
        add(ModBlocks.MAPLE_DOOR, this::createDoorTable);




        // Willow leaves
        add(
                ModBlocks.MICRO_STONE,
                block -> createSingleItemTable(ModItems.MICRO_STONE)
        );

        add(
                ModBlocks.MICRO_OAK_PLANKS,
                block -> createSingleItemTable(
                        ModItems.MICRO_OAK_PLANKS
                )
        );
        add(
                ModBlocks.WILLOW_LEAVES,
                block -> createSilkTouchOrShearsDispatchTable(
                        block,
                        LootItem.lootTableItem(ModItems.DEAD_BRANCH)
                                .when(LootItemRandomChanceCondition.randomChance(0.05f))
                ).withPool(
                        LootPool.lootPool()
                                .add(
                                        LootItem.lootTableItem(ModBlocks.WILLOW_SAPLING)
                                                .when(LootItemRandomChanceCondition.randomChance(0.05f))
                                                .when(doesNotHaveSilkTouch())
                                                .when(hasShears().invert())
                                )
                )
        );
        add(
                ModBlocks.GLOOMY_LEAVES,
                block -> createSilkTouchOrShearsDispatchTable(
                        block,
                        LootItem.lootTableItem(ModItems.DEAD_BRANCH)
                                .when(LootItemRandomChanceCondition.randomChance(0.05f))
                ).withPool(
                        LootPool.lootPool()
                                .add(
                                        LootItem.lootTableItem(ModBlocks.GLOOMY_SAPLING)
                                                .when(LootItemRandomChanceCondition.randomChance(0.05f))
                                                .when(doesNotHaveSilkTouch())
                                                .when(hasShears().invert())
                                )
                )
        );

        add(
                ModBlocks.MAPLE_RED_LEAVES,
                block -> createSilkTouchOrShearsDispatchTable(
                        block,
                        LootItem.lootTableItem(ModItems.DEAD_BRANCH)
                                .when(LootItemRandomChanceCondition.randomChance(0.05f))
                ).withPool(
                        LootPool.lootPool()
                                .add(
                                        LootItem.lootTableItem(ModBlocks.MAPLE_SAPLING)
                                                .when(LootItemRandomChanceCondition.randomChance(0.05f))
                                                .when(doesNotHaveSilkTouch())
                                                .when(hasShears().invert())
                                )
                )
        );

        add(
                ModBlocks.MAPLE_YELLOW_LEAVES,
                block -> createSilkTouchOrShearsDispatchTable(
                        block,
                        LootItem.lootTableItem(Items.STICK)
                                .when(LootItemRandomChanceCondition.randomChance(0.05f))
                ).withPool(
                        LootPool.lootPool()
                                .add(
                                        LootItem.lootTableItem(ModBlocks.MAPLE_SAPLING)
                                                .when(LootItemRandomChanceCondition.randomChance(0.05f))
                                                .when(doesNotHaveSilkTouch())
                                                .when(hasShears().invert())
                                )
                )
        );

        dropDoublePlant(
                ModBlocks.HIGH_GRASS,
                ModBlocks.HIGH_GRASS_PLANT,
                ModItems.HIGH_GRASS
        );


// Thorny vine
        add(
                ModBlocks.THORNY_VINE,
                block -> createSingleItemTable(
                        ModItems.THORNY_VINE
                )
        );


// Glowing fruit
        add(
                ModBlocks.GLOWING_FRUIT,
                block -> createSingleItemTable(
                        ModItems.GLOWING_FRUIT
                )
        );


        // Packed Ice variants
        add(
                ModBlocks.PACKED_ICE_STAIRS,
                this::createSilkTouchOnlyTable
        );

        add(
                ModBlocks.PACKED_ICE_SLAB,
                this::createSilkTouchOnlyTable
        );

        add(
                ModBlocks.BLUE_ICE_WALL,
                this::createSilkTouchOnlyTable
        );

        add(
                ModBlocks.BLUE_ICE_SLAB,
                this::createSilkTouchOnlyTable
        );
        add(
                ModBlocks.BLUE_ICE_STAIRS,
                this::createSilkTouchOnlyTable
        );


        add(
                ModBlocks.CLAY_STAIRS,
                this::createSilkTouchOnlyTable
        );

        add(
                ModBlocks.CLAY_SLAB,
                this::createSilkTouchOnlyTable
        );
        add(
                ModBlocks.CLAY_WALL,
                this::createSilkTouchOnlyTable
        );
    }

}