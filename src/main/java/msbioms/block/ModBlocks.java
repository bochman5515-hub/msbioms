package msbioms.block;

import msbioms.MSBioms;

import msbioms.mixin.BlockEntityTypeAccessor;
import msbioms.worldgen.ModConfiguredFeatures;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;

import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

import net.minecraft.data.BlockFamily;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityTypes;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import static msbioms.block.ModWoodTypes.WILLOW;
import static msbioms.block.ModWoodTypes.WILLOW_SET;
import static net.minecraft.world.level.block.Blocks.PACKED_ICE;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public class ModBlocks {

    // Willow

    public static final Block WILLOW_LOG = registerBlock(
            "willow_log",
            properties -> new RotatedPillarBlock(
                    properties
                            .strength(2f)
                            .sound(SoundType.WOOD)
            )
    );

    public static final Block WILLOW_WOOD = registerBlock(
            "willow_wood",
            properties -> new RotatedPillarBlock(
                    properties
                            .strength(2f)
                            .sound(SoundType.WOOD)
            )
    );

    public static final Block STRIPPED_WILLOW_LOG = registerBlock(
            "stripped_willow_log",
            properties -> new RotatedPillarBlock(
                    properties
                            .strength(2f)
                            .sound(SoundType.WOOD)
            )
    );

    public static final Block STRIPPED_WILLOW_WOOD = registerBlock(
            "stripped_willow_wood",
            properties -> new RotatedPillarBlock(
                    properties
                            .strength(2f)
                            .sound(SoundType.WOOD)
            )
    );
    public static final Block WILLOW_SAPLING = registerBlock(
            "willow_sapling",
            properties -> new SaplingBlock(
                    ModConfiguredFeatures.WILLOW,
                    properties
                            .mapColor(MapColor.PLANT)
                            .noOcclusion()
                            .randomTicks()
                            .instabreak()
                            .sound(SoundType.CHERRY_SAPLING)
            )
    );

    public static final Block WILLOW_PLANKS = registerBlock(
            "willow_planks",
            properties -> new Block(
                    properties
                            .strength(2f)
                            .sound(SoundType.WOOD)
            )
    );
    public static final Block WILLOW_STAIRS = registerBlock(
            "willow_stairs",
            properties -> new StairBlock(
                    WILLOW_PLANKS.defaultBlockState(),
                    properties.strength(2.0f).sound(SoundType.WOOD)
            )
    );

    public static final Block WILLOW_SLAB = registerBlock(
            "willow_slab",
            properties -> new SlabBlock(
                    properties.strength(2.0f).sound(SoundType.WOOD)
            )
    );

    //public static final Block WILLOW_LEAVES = registerBlock(
     //       "willow_leaves",
     //       properties -> new UntintedParticleLeavesBlock(
     //               0.01f,
      //              ParticleTypes.CHERRY_LEAVES,
      //              properties
      //                      .mapColor(MapColor.TERRACOTTA_GREEN)
      //                      .strength(0.2f)
      //                      .randomTicks()
      //                      .sound(SoundType.AZALEA_LEAVES)
      //                      .noOcclusion()
      //                      .isValidSpawn(Blocks::ocelotOrParrot)
      //                      .isSuffocating(Blocks::never)
      //                      .isViewBlocking(Blocks::never)
      //                      .ignitedByLava()
      //      )
    //);
    public static final Block WILLOW_LEAVES = registerBlock(
            "willow_leaves",
            properties -> new TintedParticleLeavesBlock(
                    0.01f,
                    properties
                            .mapColor(MapColor.TERRACOTTA_GREEN)
                            .strength(0.2f)
                            .randomTicks()
                            .sound(SoundType.AZALEA_LEAVES)
                            .noOcclusion()
                            .isValidSpawn(Blocks::ocelotOrParrot)
                            .isSuffocating(Blocks::never)
                            .isViewBlocking(Blocks::never)
                            .ignitedByLava()
            )
    );
    public static final Block MOSS = registerBlock(
            "moss",
            properties -> new NaturalMossBlock(
                    properties
                            .mapColor(MapColor.TERRACOTTA_GREEN)
                            .strength(0.1f)
                            .sound(SoundType.MOSS)
            )
    );
    public static final Block WILLOW_VINE = registerBlock(
            "willow_vine",
            properties -> new WillowVineBlock(
                    properties
                            .noCollision()
                            .instabreak()
                            .sound(SoundType.MOSS)
            )
    );
    public static final Block WILLOW_VINE_PLANT = registerBlockWithoutItem(
            "willow_vine_plant",
            properties -> new WillowVinePlantBlock(
                    properties
                            .noCollision()
                            .instabreak()
                            .sound(SoundType.MOSS)
            )
    );


    public static final Block MOSS_CARPET = registerBlock(
            "moss_carpet",
            properties -> new CarpetBlock(
                    properties
                            .mapColor(MapColor.TERRACOTTA_GREEN)
                            .strength(0.1f)
                            .sound(SoundType.MOSS)
                            .noOcclusion()
            )
    );

    public static final Block WILLOW_FENCE = registerBlock(
            "willow_fence",
            properties -> new FenceBlock(
                    properties
                            .strength(2f)
                            .sound(SoundType.WOOD)
            )
    );

    public static final Block WILLOW_FENCE_GATE = registerBlock(
            "willow_fence_gate",
            properties -> new FenceGateBlock(
                    WILLOW,
                    properties
                            .strength(2f)
                            .sound(SoundType.WOOD)
            )
    );
    public static final Block WILLOW_DOOR = registerBlock(
            "willow_door",
            properties -> new DoorBlock(
                    WILLOW_SET,
                    properties
                            .strength(2f)
                            .noOcclusion()
            )
    );

    public static final Block WILLOW_TRAPDOOR = registerBlock(
            "willow_trapdoor",
            properties -> new TrapDoorBlock(
                    WILLOW_SET,
                    properties
                            .strength(2f)
                            .noOcclusion()
            )
    );

    public static final Block WILLOW_PRESSURE_PLATE = registerBlock(
            "willow_pressure_plate",
            properties -> new PressurePlateBlock(
                    WILLOW_SET,
                    properties.strength(0.5f)
            )
    );

    public static final Block WILLOW_BUTTON = registerBlock(
            "willow_button",
            properties -> new ButtonBlock(
                    WILLOW_SET,
                    30,
                    properties.strength(0.5f)
            )
    );

    // Willow sign

    public static final Block WILLOW_SIGN = registerBlockWithoutItem(
            "willow_sign",
            properties -> new StandingSignBlock(
                    ModWoodTypes.WILLOW,
                    properties.strength(1f)
            )
    );

    public static final Block WILLOW_WALL_SIGN = registerBlockWithoutItem(
            "willow_wall_sign",
            properties -> new WallSignBlock(
                    ModWoodTypes.WILLOW,
                    properties.strength(1f)
            )
    );


// Willow hanging sign

    public static final Block WILLOW_HANGING_SIGN = registerBlockWithoutItem(
            "willow_hanging_sign",
            properties -> new CeilingHangingSignBlock(
                    ModWoodTypes.WILLOW,
                    properties.strength(1f)
            )
    );

    public static final Block WILLOW_WALL_HANGING_SIGN = registerBlockWithoutItem(
            "willow_wall_hanging_sign",
            properties -> new WallHangingSignBlock(
                    ModWoodTypes.WILLOW,
                    properties.strength(1f)
            )
    );


    // Existing blocks

    public static final Block DRIED_EARTH = registerBlock(
            "dried_earth",
            properties -> new Block(
                    properties.strength(0.5f)));
    public static final Block PACKED_ICE_STAIRS = registerBlock(
            "packed_ice_stairs",
            properties -> new StairBlock(
                    Blocks.PACKED_ICE.defaultBlockState(),
                    properties
                            .strength(0.5f)
                            .sound(SoundType.GLASS)
                            .friction(0.98f)
            )
    );
    public static final Block PACKED_ICE_SLAB = registerBlock(
            "packed_ice_slab",
            properties -> new SlabBlock(
                    properties
                            .strength(0.5f)
                            .sound(SoundType.GLASS)
                            .friction(0.98f)
            )
    );

    public static final Block BLUE_ICE_STAIRS = registerBlock(
            "blue_ice_stairs",
            properties -> new StairBlock(
                    Blocks.BLUE_ICE.defaultBlockState(),
                    properties
                            .strength(0.5f)
                            .sound(SoundType.GLASS)
                            .friction(1f)
            )
    );
    public static final Block BLUE_ICE_SLAB = registerBlock(
            "blue_ice_slab",
            properties -> new SlabBlock(
                    properties
                            .strength(0.5f)
                            .sound(SoundType.GLASS)
                            .friction(1f)
            )
    );

    public static final Block BLUE_ICE_WALL = registerBlock(
            "blue_ice_wall",
            properties -> new WallBlock(
                    properties
                            .strength(0.5f)
                            .sound(SoundType.GLASS)
                            .friction(1f)
            )
    );


    public static final Block CALCITE_STAIRS = registerBlock(
            "calcite_stairs",
            properties -> new StairBlock(
                    Blocks.CALCITE.defaultBlockState(),
                    properties
                            .strength(0.5f)
                            .sound(SoundType.CALCITE)
                            .mapColor(MapColor.TERRACOTTA_WHITE)
            )
    );
    public static final Block CALCITE_SLAB = registerBlock(
            "calcite_slab",
            properties -> new SlabBlock(
                    properties
                            .strength(0.5f)
                            .sound(SoundType.CALCITE)
                            .mapColor(MapColor.TERRACOTTA_WHITE)

            )
    );
    public static final Block CALCITE_WALL = registerBlock(
            "calcite_wall",
            properties -> new WallBlock(
                    properties
                            .strength(1.5f)
                            .sound(SoundType.CALCITE)
                            .mapColor(MapColor.TERRACOTTA_WHITE)
            )
    );

    public static final Block RESIN_STAIRS = registerBlock(
            "resin_stairs",
            properties -> new StairBlock(
                    Blocks.RESIN_BLOCK.defaultBlockState(),
                    properties
                            .strength(1.5f)
                            .sound(SoundType.RESIN)
                            .mapColor(MapColor.COLOR_ORANGE)
            )
    );
    public static final Block RESIN_SLAB = registerBlock(
            "resin_slab",
            properties -> new SlabBlock(
                    properties
                            .strength(1.5f)
                            .sound(SoundType.RESIN)
                            .mapColor(MapColor.COLOR_ORANGE)

            )
    );
    public static final Block RESIN_WALL = registerBlock(
            "resin_wall",
            properties -> new WallBlock(
                    properties
                            .strength(1.5f)
                            .sound(SoundType.RESIN)
                            .mapColor(MapColor.COLOR_ORANGE)
            )
    );

    public static final Block SMOOTH_BASALT_STAIRS = registerBlock(
            "smooth_basalt_stairs",
            properties -> new StairBlock(
                    Blocks.SMOOTH_BASALT.defaultBlockState(),
                    properties
                            .strength(0.5f)
                            .sound(SoundType.BASALT)
                            .mapColor(MapColor.TERRACOTTA_BLACK)
            )
    );
    public static final Block SMOOTH_BASALT_SLAB = registerBlock(
            "smooth_basalt_slab",
            properties -> new SlabBlock(
                    properties
                            .strength(0.5f)
                            .sound(SoundType.BASALT)
                            .mapColor(MapColor.TERRACOTTA_BLACK)

            )
    );
    public static final Block SMOOTH_BASALT_WALL = registerBlock(
            "smooth_basalt_wall",
            properties -> new WallBlock(
                    properties
                            .strength(2f)
                            .sound(SoundType.BASALT)
                            .mapColor(MapColor.TERRACOTTA_BLACK)
            )
    );
    public static final Block PRISMARINE_BRICKS_WALL = registerBlock(
            "prismarine_bricks_wall",
            properties -> new WallBlock(
                    properties
                            .strength(2f)
                            .sound(SoundType.STONE)
                            .mapColor(MapColor.TERRACOTTA_CYAN)
            )
    );
    public static final Block DARK_PRISMARINE_WALL = registerBlock(
            "dark_prismarine_wall",
            properties -> new WallBlock(
                    properties
                            .strength(2f)
                            .sound(SoundType.STONE)
                            .mapColor(MapColor.TERRACOTTA_CYAN)
            )
    );
    public static final Block POLISHED_ANDESITE_WALL = registerBlock(
            "polished_andesite_wall",
            properties -> new WallBlock(
                    properties
                            .strength(2f)
                            .sound(SoundType.STONE)
                            .mapColor(MapColor.COLOR_LIGHT_GRAY)));
    public static final Block POLISHED_DIORITE_WALL = registerBlock(
            "polished_diorite_wall",
            properties -> new WallBlock(
                    properties
                            .strength(2f)
                            .sound(SoundType.STONE)
                            .mapColor(MapColor.TERRACOTTA_WHITE)
            ));
    public static final Block POLISHED_GRANITE_WALL = registerBlock(
            "polished_granite_wall",
            properties -> new WallBlock(
                    properties
                            .strength(2f)
                            .sound(SoundType.STONE)
                            .mapColor(MapColor.TERRACOTTA_RED)
            ));

    public static final Block PURPUR_BLOCK_WALL = registerBlock(
            "purpur_block_wall",
            properties -> new WallBlock(
                    properties
                            .strength(2f)
                            .sound(SoundType.STONE)
                            .mapColor(MapColor.TERRACOTTA_PURPLE)
            ));
    public static final Block QUARTZ_BRICKS_WALL = registerBlock(
            "quartz_bricks_wall",
            properties -> new WallBlock(
                    properties
                            .strength(2f)
                            .sound(SoundType.STONE)
                            .mapColor(MapColor.QUARTZ)
            ));
    public static final Block SMOOTH_QUARTZ_WALL = registerBlock(
            "smooth_quartz_wall",
            properties -> new WallBlock(
                    properties
                            .strength(2f)
                            .sound(SoundType.STONE)
                            .mapColor(MapColor.QUARTZ)
            ));


    public static final Block HIGH_GRASS = registerBlockWithoutItem(
            "high_grass",
            properties -> new TallPlantPartBlock(
                    properties
                            .noCollision()
                            .instabreak()
                            .sound(SoundType.GRASS)
                            .pushReaction(PushReaction.DESTROY),
                    ResourceKey.create(
                            BuiltInRegistries.BLOCK.key(),
                            MSBioms.id("high_grass_plant")
                    ),
                    false
            )
    );

    public static final Block HIGH_GRASS_PLANT = registerBlockWithoutItem(
            "high_grass_plant",
            properties -> new TallPlantPartBlock(
                    properties
                            .noCollision()
                            .instabreak()
                            .sound(SoundType.GRASS)
                            .pushReaction(PushReaction.DESTROY),
                    ResourceKey.create(
                            BuiltInRegistries.BLOCK.key(),
                            MSBioms.id("high_grass")
                    ),
                    true
            )
    );
    public static final Block BOG_PLANT = registerBlockWithoutItem(
            "bog_plant",
            properties -> new BogPlantBlock(
                    properties
                            .instabreak()
                            .sound(SoundType.GRASS)
            )
    );
    public static final Block BOG = registerBlockWithoutItem(
            "bog",
            properties -> new BogBlock(
                    properties
                            .strength(0.5f)
                            .sound(SoundType.GRASS)
                            .noOcclusion()
                            .noCollision()
            )
    );

    public static final Block DRIPSTONE_STAIRS = registerBlock(
            "dripstone_stairs",
            properties -> new StairBlock(
                    Blocks.SMOOTH_BASALT.defaultBlockState(),
                    properties
                            .strength(2f)
                            .sound(SoundType.STONE)
                            .mapColor(MapColor.COLOR_BROWN)
            )
    );
    public static final Block DRIPSTONE_SLAB = registerBlock(
            "dripstone_slab",
            properties -> new SlabBlock(
                    properties
                            .strength(2f)
                            .sound(SoundType.STONE)
                            .mapColor(MapColor.COLOR_BROWN)

            )
    );
    public static final Block DRIPSTONE_WALL = registerBlock(
            "dripstone_wall",
            properties -> new WallBlock(
                    properties
                            .strength(2f)
                            .sound(SoundType.STONE)
                            .mapColor(MapColor.COLOR_BROWN)
            )
    );

    public static final Block PACKED_MUD_STAIRS = registerBlock(
            "packed_mud_stairs",
            properties -> new StairBlock(
                    Blocks.SMOOTH_BASALT.defaultBlockState(),
                    properties
                            .strength(2f)
                            .sound(SoundType.PACKED_MUD)
                            .mapColor(MapColor.COLOR_BROWN)
            )
    );
    public static final Block PACKED_MUD_SLAB = registerBlock(
            "packed_mud_slab",
            properties -> new SlabBlock(
                    properties
                            .strength(2f)
                            .sound(SoundType.PACKED_MUD)
                            .mapColor(MapColor.COLOR_BROWN)

            )
    );
    public static final Block PACKED_MUD_WALL = registerBlock(
            "packed_mud_wall",
            properties -> new WallBlock(
                    properties
                            .strength(2f)
                            .sound(SoundType.PACKED_MUD)
                            .mapColor(MapColor.COLOR_BROWN)
            )
    );


    public static final Block END_STONE_STAIRS = registerBlock(
            "end_stone_stairs",
            properties -> new StairBlock(
                    Blocks.SMOOTH_BASALT.defaultBlockState(),
                    properties
                            .strength(2f)
                            .sound(SoundType.STONE)
                            .mapColor(MapColor.TERRACOTTA_YELLOW)
            )
    );
    public static final Block END_STONE_SLAB = registerBlock(
            "end_stone_slab",
            properties -> new SlabBlock(
                    properties
                            .strength(2f)
                            .sound(SoundType.STONE)
                            .mapColor(MapColor.TERRACOTTA_YELLOW)

            )
    );
    public static final Block END_STONE_WALL = registerBlock(
            "end_stone_wall",
            properties -> new WallBlock(
                    properties
                            .strength(2f)
                            .sound(SoundType.STONE)
                            .mapColor(MapColor.TERRACOTTA_YELLOW)
            )
    );

    public static final Block AMETHYST_STAIRS = registerBlock(
            "amethyst_stairs",
            properties -> new StairBlock(
                    Blocks.SMOOTH_BASALT.defaultBlockState(),
                    properties
                            .strength(2f)
                            .sound(SoundType.AMETHYST)
                            .mapColor(MapColor.COLOR_PURPLE)
            )
    );
    public static final Block AMETHYST_SLAB = registerBlock(
            "amethyst_slab",
            properties -> new SlabBlock(
                    properties
                            .strength(2f)
                            .sound(SoundType.AMETHYST)
                            .mapColor(MapColor.COLOR_PURPLE)

            )
    );
    public static final Block AMETHYST_WALL = registerBlock(
            "amethyst_wall",
            properties -> new WallBlock(
                    properties
                            .strength(2f)
                            .sound(SoundType.AMETHYST)
                            .mapColor(MapColor.COLOR_PURPLE)
            )
    );


    public static final Block SMOOTH_STONE_STAIRS = registerBlock(
            "smooth_stone_stairs",
            properties -> new StairBlock(
                    Blocks.SMOOTH_STONE.defaultBlockState(),
                    properties
                            .strength(2f)
                            .sound(SoundType.STONE)
                            .mapColor(MapColor.STONE)
            )
    );
    public static final Block SMOOTH_STONE_WALL = registerBlock(
            "smooth_stone_wall",
            properties -> new WallBlock(
                    properties
                            .strength(2f)
                            .sound(SoundType.STONE)
                            .mapColor(MapColor.STONE)
            )
    );

    public static final Block SMOOTH_SANDSTONE_WALL = registerBlock(
            "smooth_sandstone_wall",
            properties -> new WallBlock(
                    properties
                            .strength(2f)
                            .sound(SoundType.STONE)
                            .mapColor(MapColor.SAND)
            )
    );
    public static final Block SMOOTH_RED_SANDSTONE_WALL = registerBlock(
            "smooth_red_sandstone_wall",
            properties -> new WallBlock(
                    properties
                            .strength(2f)
                            .sound(SoundType.STONE)
                            .mapColor(MapColor.TERRACOTTA_RED)
            )
    );

    public static final Block HONEYCOMB_STAIRS = registerBlock(
            "honeycomb_stairs",
            properties -> new StairBlock(
                    Blocks.HONEYCOMB_BLOCK.defaultBlockState(),
                    properties
                            .strength(2f)
                            .sound(SoundType.HONEY_BLOCK)
                            .mapColor(MapColor.COLOR_ORANGE)
            )
    );
    public static final Block HONEYCOMB_SLAB = registerBlock(
            "honeycomb_slab",
            properties -> new SlabBlock(
                    properties
                            .strength(2f)
                            .sound(SoundType.HONEY_BLOCK)
                            .mapColor(MapColor.COLOR_ORANGE)

            )
    );
    public static final Block HONEYCOMB_WALL = registerBlock(
            "honeycomb_wall",
            properties -> new WallBlock(
                    properties
                            .strength(2f)
                            .sound(SoundType.HONEY_BLOCK)
                            .mapColor(MapColor.COLOR_ORANGE)
            )
    );

    public static final Block CLAY_STAIRS = registerBlock(
            "clay_stairs",
            properties -> new StairBlock(
                    Blocks.CLAY.defaultBlockState(),
                    properties
                            .strength(2f)
                            .sound(SoundType.GRASS)
                            .mapColor(MapColor.CLAY)
            )
    );
    public static final Block CLAY_SLAB = registerBlock(
            "clay_slab",
            properties -> new SlabBlock(
                    properties
                            .strength(2f)
                            .sound(SoundType.GRASS)
                            .mapColor(MapColor.CLAY)

            )
    );
    public static final Block CLAY_WALL = registerBlock(
            "clay_wall",
            properties -> new WallBlock(
                    properties
                            .strength(2f)
                            .sound(SoundType.GRASS)
                            .mapColor(MapColor.CLAY)
            )
    );

    public static final Block NETHERRACK_STAIRS = registerBlock(
            "netherrack_stairs",
            properties -> new StairBlock(
                    Blocks.NETHERRACK.defaultBlockState(),
                    properties
                            .strength(2f)
                            .sound(SoundType.NETHERRACK)
                            .mapColor(MapColor.NETHER)
            )
    );
    public static final Block NETHERRACK_SLAB = registerBlock(
            "netherrack_slab",
            properties -> new SlabBlock(
                    properties
                            .strength(2f)
                            .sound(SoundType.NETHERRACK)
                            .mapColor(MapColor.NETHER)

            )
    );
    public static final Block NETHERRACK_WALL = registerBlock(
            "netherrack_wall",
            properties -> new WallBlock(
                    properties
                            .strength(2f)
                            .sound(SoundType.NETHERRACK)
                            .mapColor(MapColor.NETHER)
            )
    );


    public static final Block BONE_SLAB = registerBlock(
            "bone_slab",
            properties -> new SlabBlock(
                    properties
                            .strength(2f)
                            .sound(SoundType.GRASS)
                            .mapColor(MapColor.CLAY)
            )
    );
    public static final Block HAY_SLAB = registerBlock(
            "hay_slab",
            properties -> new SlabBlock(
                    properties
                            .strength(1.5f)
                            .sound(SoundType.GRASS)
                            .mapColor(MapColor.SAND)

            )
    );
    public static final Block MUD_SLAB = registerBlock(
            "mud_slab",
            properties -> new SlabBlock(
                    properties
                            .strength(1.5f)
                            .sound(SoundType.MUD)
                            .mapColor(MapColor.COLOR_GRAY)

            )
    );
    public static final Block BASALT_SLAB = registerBlock(
            "basalt_slab",
            properties -> new SlabBlock(
                    properties
                            .strength(1.5f)
                            .sound(SoundType.BASALT)
                            .mapColor(MapColor.COLOR_GRAY)

            )
    );







    // Block registration

    private static Block registerBlock(
            String name,
            Function<BlockBehaviour.Properties, Block> function
    ) {
        ResourceKey<Block> blockKey = ResourceKey.create(
                BuiltInRegistries.BLOCK.key(),
                Identifier.fromNamespaceAndPath(
                        MSBioms.MOD_ID,
                        name
                )
        );

        Block block = function.apply(
                BlockBehaviour.Properties.of().setId(blockKey)
        );

        registerBlockItem(name, block);

        return Registry.register(
                BuiltInRegistries.BLOCK,
                blockKey,
                block
        );
    }
    private static Block registerBlockWithoutItem(
            String name,
            Function<BlockBehaviour.Properties, Block> function
    ) {
        ResourceKey<Block> blockKey = ResourceKey.create(
                BuiltInRegistries.BLOCK.key(),
                Identifier.fromNamespaceAndPath(
                        MSBioms.MOD_ID,
                        name
                )
        );

        Block block = function.apply(
                BlockBehaviour.Properties.of().setId(blockKey)
        );

        return Registry.register(
                BuiltInRegistries.BLOCK,
                blockKey,
                block
        );
    }


    private static void registerBlockItem(
            String name,
            Block block
    ) {
        ResourceKey<Item> itemKey = ResourceKey.create(
                BuiltInRegistries.ITEM.key(),
                Identifier.fromNamespaceAndPath(
                        MSBioms.MOD_ID,
                        name
                )
        );

        Registry.register(
                BuiltInRegistries.ITEM,
                itemKey,
                new BlockItem(
                        block,
                        new Item.Properties().setId(itemKey)
                )
        );
    }
    private static void registerSignItems() {

        ResourceKey<Item> signItemKey = ResourceKey.create(
                BuiltInRegistries.ITEM.key(),
                Identifier.fromNamespaceAndPath(
                        MSBioms.MOD_ID,
                        "willow_sign"
                )
        );

        Registry.register(
                BuiltInRegistries.ITEM,
                signItemKey,
                new SignItem(
                        WILLOW_SIGN,
                        WILLOW_WALL_SIGN,
                        new Item.Properties().setId(signItemKey)
                )
        );


        ResourceKey<Item> hangingSignItemKey = ResourceKey.create(
                BuiltInRegistries.ITEM.key(),
                Identifier.fromNamespaceAndPath(
                        MSBioms.MOD_ID,
                        "willow_hanging_sign"
                )
        );

        Registry.register(
                BuiltInRegistries.ITEM,
                hangingSignItemKey,
                new HangingSignItem(
                        WILLOW_HANGING_SIGN,
                        WILLOW_WALL_HANGING_SIGN,
                        new Item.Properties().setId(hangingSignItemKey)
                )
        );
    }


    public static void registerModBlocks() {
        MSBioms.LOGGER.info(
                "Registering blocks for " + MSBioms.MOD_ID
        );

        registerStrippableBlocks();
        registerSignItems();
        registerSignBlockEntities();
    }
    private static void registerSignBlockEntities() {

        BlockEntityTypeAccessor signAccessor =
                (BlockEntityTypeAccessor) (Object) BlockEntityTypes.SIGN;

        Set<Block> signBlocks =
                new HashSet<>(signAccessor.msbioms$getValidBlocks());

        signBlocks.add(WILLOW_SIGN);
        signBlocks.add(WILLOW_WALL_SIGN);

        signAccessor.msbioms$setValidBlocks(signBlocks);


        BlockEntityTypeAccessor hangingSignAccessor =
                (BlockEntityTypeAccessor) (Object) BlockEntityTypes.HANGING_SIGN;

        Set<Block> hangingSignBlocks =
                new HashSet<>(hangingSignAccessor.msbioms$getValidBlocks());

        hangingSignBlocks.add(WILLOW_HANGING_SIGN);
        hangingSignBlocks.add(WILLOW_WALL_HANGING_SIGN);

        hangingSignAccessor.msbioms$setValidBlocks(hangingSignBlocks);
    }

    private static void registerStrippableBlocks() {

        StrippableBlockRegistry.register(
                WILLOW_LOG,
                STRIPPED_WILLOW_LOG
        );

        StrippableBlockRegistry.register(
                WILLOW_WOOD,
                STRIPPED_WILLOW_WOOD
        );
    }
    public static ResourceKey<Block> getRK(Block block) {
        return ResourceKey.create(
                BuiltInRegistries.BLOCK.key(),
                BuiltInRegistries.BLOCK.getKey(block)
        );


    }
    public static final BlockFamily WILLOW_FAMILY =
            new BlockFamily.Builder(WILLOW_PLANKS)
                    .slab(WILLOW_SLAB)
                    .stairs(WILLOW_STAIRS)
                    .fence(WILLOW_FENCE)
                    .fenceGate(WILLOW_FENCE_GATE)
                    .button(WILLOW_BUTTON)
                    .pressurePlate(WILLOW_PRESSURE_PLATE)
                    .strippedLog(WILLOW_LOG)
                    .sign(WILLOW_SIGN, WILLOW_WALL_SIGN)
                    .hangingSign(
                            WILLOW_HANGING_SIGN,
                            WILLOW_WALL_HANGING_SIGN
                    )
                    .getFamily();

}
