package msbioms.block;

import msbioms.MSBioms;

import msbioms.item.MicroBlockItem;
import msbioms.item.ModItems;
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










    // =========================
// Micro blocks — planks
// =========================

    public static final Block MICRO_BIRCH_PLANKS =
            registerBlockWithoutItem(
                    "micro_birch_planks",
                    properties -> new MicroBlock(
                            properties
                                    .strength(2.0F)
                                    .sound(SoundType.WOOD)
                                    .noOcclusion(),
                            () -> ModItems.MICRO_BIRCH_PLANKS
                    )
            );

    public static final Block MICRO_DARK_OAK_PLANKS =
            registerBlockWithoutItem(
                    "micro_dark_oak_planks",
                    properties -> new MicroBlock(
                            properties
                                    .strength(2.0F)
                                    .sound(SoundType.WOOD)
                                    .noOcclusion(),
                            () -> ModItems.MICRO_DARK_OAK_PLANKS
                    )
            );

    public static final Block MICRO_ACACIA_PLANKS =
            registerBlockWithoutItem(
                    "micro_acacia_planks",
                    properties -> new MicroBlock(
                            properties
                                    .strength(2.0F)
                                    .sound(SoundType.WOOD)
                                    .noOcclusion(),
                            () -> ModItems.MICRO_ACACIA_PLANKS
                    )
            );

    public static final Block MICRO_JUNGLE_PLANKS =
            registerBlockWithoutItem(
                    "micro_jungle_planks",
                    properties -> new MicroBlock(
                            properties
                                    .strength(2.0F)
                                    .sound(SoundType.WOOD)
                                    .noOcclusion(),
                            () -> ModItems.MICRO_JUNGLE_PLANKS
                    )
            );

    public static final Block MICRO_MANGROVE_PLANKS =
            registerBlockWithoutItem(
                    "micro_mangrove_planks",
                    properties -> new MicroBlock(
                            properties
                                    .strength(2.0F)
                                    .sound(SoundType.WOOD)
                                    .noOcclusion(),
                            () -> ModItems.MICRO_MANGROVE_PLANKS
                    )
            );

    public static final Block MICRO_CHERRY_PLANKS =
            registerBlockWithoutItem(
                    "micro_cherry_planks",
                    properties -> new MicroBlock(
                            properties
                                    .strength(2.0F)
                                    .sound(SoundType.WOOD)
                                    .noOcclusion(),
                            () -> ModItems.MICRO_CHERRY_PLANKS
                    )
            );

    public static final Block MICRO_PALE_OAK_PLANKS =
            registerBlockWithoutItem(
                    "micro_pale_oak_planks",
                    properties -> new MicroBlock(
                            properties
                                    .strength(2.0F)
                                    .sound(SoundType.WOOD)
                                    .noOcclusion(),
                            () -> ModItems.MICRO_PALE_OAK_PLANKS
                    )
            );

    public static final Block MICRO_BAMBOO_MOSAIC =
            registerBlockWithoutItem(
                    "micro_bamboo_mosaic",
                    properties -> new MicroBlock(
                            properties
                                    .strength(2.0F)
                                    .sound(SoundType.WOOD)
                                    .noOcclusion(),
                            () -> ModItems.MICRO_BAMBOO_MOSAIC
                    )
            );

    public static final Block MICRO_WARPED_PLANKS =
            registerBlockWithoutItem(
                    "micro_warped_planks",
                    properties -> new MicroBlock(
                            properties
                                    .strength(2.0F)
                                    .sound(SoundType.WOOD)
                                    .noOcclusion(),
                            () -> ModItems.MICRO_WARPED_PLANKS
                    )
            );

    public static final Block MICRO_CRIMSON_PLANKS =
            registerBlockWithoutItem(
                    "micro_crimson_planks",
                    properties -> new MicroBlock(
                            properties
                                    .strength(2.0F)
                                    .sound(SoundType.WOOD)
                                    .noOcclusion(),
                            () -> ModItems.MICRO_CRIMSON_PLANKS
                    )
            );

    public static final Block MICRO_WILLOW_PLANKS =
            registerBlockWithoutItem(
                    "micro_willow_planks",
                    properties -> new MicroBlock(
                            properties
                                    .strength(2.0F)
                                    .sound(SoundType.WOOD)
                                    .noOcclusion(),
                            () -> ModItems.MICRO_WILLOW_PLANKS
                    )
            );

    public static final Block MICRO_COBBLESTONE = registerBlockWithoutItem(
            "micro_cobblestone",
            properties -> new MicroBlock(
                    properties
                            .strength(2.0F)
                            .requiresCorrectToolForDrops()
                            .noOcclusion(),
                    () -> ModItems.MICRO_COBBLESTONE
            )
    );

    public static final Block MICRO_MOSSY_COBBLESTONE = registerBlockWithoutItem(
            "micro_mossy_cobblestone",
            properties -> new MicroBlock(
                    properties
                            .strength(2.0F)
                            .requiresCorrectToolForDrops()
                            .noOcclusion(),
                    () -> ModItems.MICRO_MOSSY_COBBLESTONE
            )
    );

    public static final Block MICRO_ANDESITE = registerBlockWithoutItem(
            "micro_andesite",
            properties -> new MicroBlock(
                    properties
                            .strength(1.5F)
                            .requiresCorrectToolForDrops()
                            .noOcclusion(),
                    () -> ModItems.MICRO_ANDESITE
            )
    );

    public static final Block MICRO_GRANITE = registerBlockWithoutItem(
            "micro_granite",
            properties -> new MicroBlock(
                    properties
                            .strength(1.5F)
                            .requiresCorrectToolForDrops()
                            .noOcclusion(),
                    () -> ModItems.MICRO_GRANITE
            )
    );

    public static final Block MICRO_DIORITE = registerBlockWithoutItem(
            "micro_diorite",
            properties -> new MicroBlock(
                    properties
                            .strength(1.5F)
                            .requiresCorrectToolForDrops()
                            .noOcclusion(),
                    () -> ModItems.MICRO_DIORITE
            )
    );

    public static final Block MICRO_COBBLED_DEEPSLATE = registerBlockWithoutItem(
            "micro_cobbled_deepslate",
            properties -> new MicroBlock(
                    properties
                            .strength(3.5F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.DEEPSLATE)
                            .noOcclusion(),
                    () -> ModItems.MICRO_COBBLED_DEEPSLATE
            )
    );

    public static final Block MICRO_TUFF = registerBlockWithoutItem(
            "micro_tuff",
            properties -> new MicroBlock(
                    properties
                            .strength(1.5F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.TUFF)
                            .noOcclusion(),
                    () -> ModItems.MICRO_TUFF
            )
    );

    public static final Block MICRO_TUFF_BRICKS = registerBlockWithoutItem(
            "micro_tuff_bricks",
            properties -> new MicroBlock(
                    properties
                            .strength(3.5F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.TUFF_BRICKS)
                            .noOcclusion(),
                    () -> ModItems.MICRO_TUFF_BRICKS
            )
    );

    public static final Block MICRO_DEEPSLATE_BRICKS = registerBlockWithoutItem(
            "micro_deepslate_bricks",
            properties -> new MicroBlock(
                    properties
                            .strength(3.5F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.DEEPSLATE_BRICKS)
                            .noOcclusion(),
                    () -> ModItems.MICRO_DEEPSLATE_BRICKS
            )
    );

    public static final Block MICRO_STONE_BRICKS = registerBlockWithoutItem(
            "micro_stone_bricks",
            properties -> new MicroBlock(
                    properties
                            .strength(1.5F)
                            .requiresCorrectToolForDrops()
                            .noOcclusion(),
                    () -> ModItems.MICRO_STONE_BRICKS
            )
    );

    public static final Block MICRO_BRICKS = registerBlockWithoutItem(
            "micro_bricks",
            properties -> new MicroBlock(
                    properties
                            .strength(2.0F)
                            .requiresCorrectToolForDrops()
                            .noOcclusion(),
                    () -> ModItems.MICRO_BRICKS
            )
    );

    public static final Block MICRO_DARK_PRISMARINE = registerBlockWithoutItem(
            "micro_dark_prismarine",
            properties -> new MicroBlock(
                    properties
                            .strength(1.5F)
                            .requiresCorrectToolForDrops()
                            .noOcclusion(),
                    () -> ModItems.MICRO_DARK_PRISMARINE
            )
    );

    public static final Block MICRO_PRISMARINE = registerBlockWithoutItem(
            "micro_prismarine",
            properties -> new MicroBlock(
                    properties
                            .strength(1.5F)
                            .requiresCorrectToolForDrops()
                            .noOcclusion(),
                    () -> ModItems.MICRO_PRISMARINE
            )
    );

    public static final Block MICRO_SMOOTH_SANDSTONE = registerBlockWithoutItem(
            "micro_smooth_sandstone",
            properties -> new MicroBlock(
                    properties
                            .strength(0.8F)
                            .noOcclusion(),
                    () -> ModItems.MICRO_SMOOTH_SANDSTONE
            )
    );

    public static final Block MICRO_SMOOTH_RED_SANDSTONE = registerBlockWithoutItem(
            "micro_smooth_red_sandstone",
            properties -> new MicroBlock(
                    properties
                            .strength(0.8F)
                            .noOcclusion(),
                    () -> ModItems.MICRO_SMOOTH_RED_SANDSTONE
            )
    );

    public static final Block MICRO_SMOOTH_QUARTZ = registerBlockWithoutItem(
            "micro_smooth_quartz",
            properties -> new MicroBlock(
                    properties
                            .strength(0.8F)
                            .requiresCorrectToolForDrops()
                            .noOcclusion(),
                    () -> ModItems.MICRO_SMOOTH_QUARTZ
            )
    );
    public static final Block MICRO_SPRUCE_PLANKS = registerBlockWithoutItem(
            "micro_spruce_planks",
            properties -> new MicroBlock(
                    properties
                            .strength(2.0F)
                            .sound(SoundType.WOOD)
                            .noOcclusion(),
                    () -> ModItems.MICRO_SPRUCE_PLANKS
            )
    );
    public static final Block MICRO_BLACKSTONE = registerBlockWithoutItem(
            "micro_blackstone",
            properties -> new MicroBlock(
                    properties
                            .strength(1.5F)
                            .requiresCorrectToolForDrops()
                            .noOcclusion(),
                    () -> ModItems.MICRO_BLACKSTONE
            )
    );

    public static final Block MICRO_POLISHED_BLACKSTONE_BRICKS = registerBlockWithoutItem(
            "micro_polished_blackstone_bricks",
            properties -> new MicroBlock(
                    properties
                            .strength(1.5F)
                            .requiresCorrectToolForDrops()
                            .noOcclusion(),
                    () -> ModItems.MICRO_POLISHED_BLACKSTONE_BRICKS
            )
    );

    public static final Block MICRO_END_BRICKS = registerBlockWithoutItem(
            "micro_end_bricks",
            properties -> new MicroBlock(
                    properties
                            .strength(0.5F)
                            .noOcclusion(),
                    () -> ModItems.MICRO_END_BRICKS
            )
    );

    public static final Block MICRO_QUARTZ_BRICKS = registerBlockWithoutItem(
            "micro_quartz_bricks",
            properties -> new MicroBlock(
                    properties
                            .strength(0.8F)
                            .requiresCorrectToolForDrops()
                            .noOcclusion(),
                    () -> ModItems.MICRO_QUARTZ_BRICKS
            )
    );

    public static final Block MICRO_NETHER_BRICKS = registerBlockWithoutItem(
            "micro_nether_bricks",
            properties -> new MicroBlock(
                    properties
                            .strength(2.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.NETHER_BRICKS)
                            .noOcclusion(),
                    () -> ModItems.MICRO_NETHER_BRICKS
            )
    );

    public static final Block MICRO_RED_NETHER_BRICKS = registerBlockWithoutItem(
            "micro_red_nether_bricks",
            properties -> new MicroBlock(
                    properties
                            .strength(2.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.NETHER_BRICKS)
                            .noOcclusion(),
                    () -> ModItems.MICRO_RED_NETHER_BRICKS
            )
    );

    public static final Block MICRO_DRIPSTONE_BLOCK = registerBlockWithoutItem(
            "micro_dripstone_block",
            properties -> new MicroBlock(
                    properties
                            .strength(1.5F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.DRIPSTONE_BLOCK)
                            .noOcclusion(),
                    () -> ModItems.MICRO_DRIPSTONE_BLOCK
            )
    );

    public static final Block MICRO_SMOOTH_BASALT = registerBlockWithoutItem(
            "micro_smooth_basalt",
            properties -> new MicroBlock(
                    properties
                            .strength(1.25F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.BASALT)
                            .noOcclusion(),
                    () -> ModItems.MICRO_SMOOTH_BASALT
            )
    );

    public static final Block MICRO_AMETHYST_BLOCK = registerBlockWithoutItem(
            "micro_amethyst_block",
            properties -> new MicroBlock(
                    properties
                            .strength(1.5F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.AMETHYST)
                            .noOcclusion(),
                    () -> ModItems.MICRO_AMETHYST_BLOCK
            )
    );

    public static final Block MICRO_RESIN_BLOCK = registerBlockWithoutItem(
            "micro_resin_block",
            properties -> new MicroBlock(
                    properties
                            .strength(1.0F)
                            .sound(SoundType.RESIN)
                            .noOcclusion(),
                    () -> ModItems.MICRO_RESIN_BLOCK
            )
    );

    public static final Block MICRO_RESIN_BRICKS = registerBlockWithoutItem(
            "micro_resin_bricks",
            properties -> new MicroBlock(
                    properties
                            .strength(1.5F)
                            .sound(SoundType.RESIN_BRICKS)
                            .noOcclusion(),
                    () -> ModItems.MICRO_RESIN_BRICKS
            )
    );

    public static final Block MICRO_SULFUR = registerBlockWithoutItem(
            "micro_sulfur",
            properties -> new MicroBlock(
                    properties
                            .strength(1.0F)
                            .sound(SoundType.SULFUR)
                            .noOcclusion(),
                    () -> ModItems.MICRO_SULFUR
            )
    );

    public static final Block MICRO_SULFUR_BRICKS = registerBlockWithoutItem(
            "micro_sulfur_bricks",
            properties -> new MicroBlock(
                    properties
                            .strength(1.5F)
                            .sound(SoundType.SULFUR)
                            .noOcclusion(),
                    () -> ModItems.MICRO_SULFUR_BRICKS
            )
    );

    public static final Block MICRO_CINNABAR = registerBlockWithoutItem(
            "micro_cinnabar",
            properties -> new MicroBlock(
                    properties
                            .strength(1.0F)
                            .sound(SoundType.CINNABAR)
                            .noOcclusion(),
                    () -> ModItems.MICRO_CINNABAR
            )
    );

    public static final Block MICRO_CINNABAR_BRICKS = registerBlockWithoutItem(
            "micro_cinnabar_bricks",
            properties -> new MicroBlock(
                    properties
                            .strength(1.5F)
                            .sound(SoundType.CINNABAR)
                            .noOcclusion(),
                    () -> ModItems.MICRO_CINNABAR_BRICKS
            )
    );

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
                            .offsetType(BlockBehaviour.OffsetType.XYZ)
                            .instabreak()
                            .sound(SoundType.MOSS)
            )
    );
    public static final Block WILLOW_VINE_PLANT = registerBlockWithoutItem(
            "willow_vine_plant",
            properties -> new WillowVinePlantBlock(
                    properties
                            .noCollision()
                            .offsetType(BlockBehaviour.OffsetType.XYZ)
                            .instabreak()
                            .sound(SoundType.MOSS)
            )
    );


    public static final Block MOSS_CARPET = registerBlock(
            "moss_carpet",
            properties -> new MossCarpetBlock(
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

    public static final Block SALT_BLOCk = registerBlock(
            "salt_block",
            properties -> new Block(
                    properties.strength(0.5f)
                            .sound(SoundType.GRAVEL)
                            .friction(28f)

            ));

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
                            .offsetType(BlockBehaviour.OffsetType.XYZ)
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
                            .offsetType(BlockBehaviour.OffsetType.XYZ)
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
    public static final Block MICRO_STONE = registerBlockWithoutItem(
            "micro_stone",
            properties -> new MicroBlock(
                    properties
                            .strength(1.5F)
                            .requiresCorrectToolForDrops()
                            .noOcclusion(),
                    () -> ModItems.MICRO_STONE
            )
    );
    public static final Block MICRO_OAK_PLANKS = registerBlockWithoutItem(
            "micro_oak_planks",
            properties -> new MicroBlock(
                    properties
                            .strength(2.0F)
                            .sound(SoundType.WOOD)
                            .noOcclusion(),
                    () -> ModItems.MICRO_OAK_PLANKS
            )
    );


    public static final Block DRIPSTONE_STAIRS = registerBlock(
            "dripstone_stairs",
            properties -> new StairBlock(
                    Blocks.SMOOTH_BASALT.defaultBlockState(),
                    properties
                            .strength(2f)
                            .sound(SoundType.DRIPSTONE_BLOCK)
                            .mapColor(MapColor.COLOR_BROWN)
            )
    );
    public static final Block DRIPSTONE_SLAB = registerBlock(
            "dripstone_slab",
            properties -> new SlabBlock(
                    properties
                            .strength(2f)
                            .sound(SoundType.DRIPSTONE_BLOCK)
                            .mapColor(MapColor.COLOR_BROWN)

            )
    );
    public static final Block DRIPSTONE_WALL = registerBlock(
            "dripstone_wall",
            properties -> new WallBlock(
                    properties
                            .strength(2f)
                            .sound(SoundType.DRIPSTONE_BLOCK)
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
                            .sound(SoundType.GRAVEL)
                            .mapColor(MapColor.CLAY)
            )
    );
    public static final Block CLAY_SLAB = registerBlock(
            "clay_slab",
            properties -> new SlabBlock(
                    properties
                            .strength(2f)
                            .sound(SoundType.GRAVEL)
                            .mapColor(MapColor.CLAY)

            )
    );
    public static final Block CLAY_WALL = registerBlock(
            "clay_wall",
            properties -> new WallBlock(
                    properties
                            .strength(2f)
                            .sound(SoundType.GRAVEL)
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
                            .sound(SoundType.BONE_BLOCK)
                            .mapColor(MapColor.TERRACOTTA_WHITE)
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
