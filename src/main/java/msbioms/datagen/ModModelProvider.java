package msbioms.datagen;

import com.mojang.math.Quadrant;
import msbioms.MSBioms;
import msbioms.block.BogPlantBlock;
import msbioms.block.MicroBlock;
import msbioms.block.ModBlocks;
import msbioms.item.ModItems;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.*;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jspecify.annotations.NonNull;
import com.mojang.math.Transformation;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import net.minecraft.client.renderer.item.CuboidItemModelWrapper;

import java.util.Collections;


import java.util.Optional;


public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }



    private MultiVariant createRandomRotatedVariants(
            Identifier... models
    ) {
        WeightedList.Builder<Variant> variants =
                WeightedList.builder();
        for (Identifier model : models) {
            variants.add(
                    new Variant(model)
                            .withYRot(Quadrant.R0));
            variants.add(
                    new Variant(model)
                            .withYRot(Quadrant.R90));
            variants.add(
                    new Variant(model)
                            .withYRot(Quadrant.R180));
            variants.add(
                    new Variant(model)
                            .withYRot(Quadrant.R270));}
        return new MultiVariant(
                variants.build());
    }



    private void generateBog(
            BlockModelGenerators generator
    ) {
        Identifier bog1 =
                Identifier.fromNamespaceAndPath(
                        MSBioms.MOD_ID,
                        "block/bog_1"
                );

        Identifier bog2 =
                Identifier.fromNamespaceAndPath(
                        MSBioms.MOD_ID,
                        "block/bog_2"
                );

        Identifier bog3 =
                Identifier.fromNamespaceAndPath(
                        MSBioms.MOD_ID,
                        "block/bog_3"
                );

        Identifier bog4 =
                Identifier.fromNamespaceAndPath(
                        MSBioms.MOD_ID,
                        "block/bog_4"
                );

        generator.blockStateOutput.accept(
                MultiPartGenerator.multiPart(ModBlocks.BOG)
                        .with(
                                BlockModelGenerators.plainVariant(bog1)
                        )
                        .with(
                                BlockModelGenerators.plainVariant(bog2)
                        )
                        .with(
                                BlockModelGenerators.plainVariant(bog3)
                        )
                        .with(
                                BlockModelGenerators.plainVariant(bog4)
                        )
        );
    }
    private void createTopSideSlab(
            BlockModelGenerators generator,
            Block slab,
            Identifier sideTexture,
            Identifier topTexture,
            Block fullBlock
    ) {

        TextureMapping textures = new TextureMapping()
                .put(
                        TextureSlot.SIDE,
                        new Material(sideTexture)
                )
                .put(
                        TextureSlot.TOP,
                        new Material(topTexture)
                )
                .put(
                        TextureSlot.BOTTOM,
                        new Material(topTexture)
                );

        Identifier bottomModel =
                ModelTemplates.SLAB_BOTTOM.create(
                        slab,
                        textures,
                        generator.modelOutput
                );

        Identifier topModel =
                ModelTemplates.SLAB_TOP.createWithSuffix(
                        slab,
                        "_top",
                        textures,
                        generator.modelOutput
                );

        Identifier blockId =
                BuiltInRegistries.BLOCK.getKey(fullBlock);

        Identifier fullModel =
                Identifier.fromNamespaceAndPath(
                        blockId.getNamespace(),
                        "block/" + blockId.getPath()
                );

        generator.blockStateOutput.accept(
                BlockModelGenerators.createSlab(
                        slab,
                        BlockModelGenerators.plainVariant(bottomModel),
                        BlockModelGenerators.plainVariant(topModel),
                        BlockModelGenerators.plainVariant(fullModel)
                )
        );
    }


    private void generateBogPlant(
            BlockModelGenerators generator
    ) {
        // =========================
        // AGE 0
        // =========================
        Identifier stage0 =
                MSBioms.id("block/bog_plant_stage_0");
        Identifier stage0Offset1 =
                MSBioms.id(
                        "block/bog_plant_stage_0_offset_1");
        Identifier stage0Offset2 =
                MSBioms.id(
                        "block/bog_plant_stage_0_offset_2");
        Identifier stage0Offset3 =
                MSBioms.id(
                        "block/bog_plant_stage_0_offset_3");
        MultiVariant stage0Variants =
                createRandomRotatedVariants(
                        stage0,
                        stage0Offset1,
                        stage0Offset2,
                        stage0Offset3);
        // =========================
        // AGE 1
        // =========================
        Identifier stage1 =
                MSBioms.id("block/bog_plant_stage_1");
        Identifier stage1Offset1 =
                MSBioms.id(
                        "block/bog_plant_stage_1_offset_1");
        Identifier stage1Offset2 =
                MSBioms.id(
                        "block/bog_plant_stage_1_offset_2");
        Identifier stage1Offset3 =
                MSBioms.id(
                        "block/bog_plant_stage_1_offset_3");
        MultiVariant stage1Variants =
                createRandomRotatedVariants(
                        stage1,
                        stage1Offset1,
                        stage1Offset2,
                        stage1Offset3);
        // =========================
        // AGE 2
        // =========================
        Identifier stage2 =
                MSBioms.id("block/bog_plant_stage_2");
        Identifier stage2Offset1 =
                MSBioms.id(
                        "block/bog_plant_stage_2_offset_1"
                );
        Identifier stage2Offset2 =
                MSBioms.id(
                        "block/bog_plant_stage_2_offset_2"
                );
        Identifier stage2Offset3 =
                MSBioms.id(
                        "block/bog_plant_stage_2_offset_3"
                );
        MultiVariant stage2Variants =
                createRandomRotatedVariants(
                        stage2,
                        stage2Offset1,
                        stage2Offset2,
                        stage2Offset3
                );
        // =========================
        // BLOCKSTATE
        // =========================
        generator.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(
                        ModBlocks.BOG_PLANT).with(
                        PropertyDispatch.initial(
                                        BogPlantBlock.AGE)
                                .select(
                                        0,
                                        stage0Variants)
                                .select(
                                        1,
                                        stage1Variants)
                                .select(
                                        2,
                                        stage2Variants)));
    }
    private void generateMicroBlockItem(
            ItemModelGenerators generator,
            net.minecraft.world.item.Item item,
            Block microBlock
    ) {
        String name =
                BuiltInRegistries.BLOCK
                        .getKey(microBlock)
                        .getPath();

        Identifier modelId =
                MSBioms.id(
                        "block/" +
                                name +
                                "_cell_0"
                );

        Transformation transformation =
                new Transformation(
                        new Vector3f(
                                0.5F,
                                0.4F,
                                0.0F
                        ),
                        new Quaternionf(),
                        new Vector3f(
                                1.0F,
                                1.0F,
                                1.0F
                        ),
                        new Quaternionf()
                );

        generator.itemModelOutput.accept(
                item,
                new CuboidItemModelWrapper.Unbaked(
                        modelId,
                        Optional.of(transformation),
                        Collections.emptyList()
                )
        );
    }
    private void generateMicroBlock(
            BlockModelGenerators generator,
            Block microBlock,
            Block textureBlock,
            String textureName
    ) {
        generateMicroBlock(
                generator,
                microBlock,
                Identifier.fromNamespaceAndPath(
                        BuiltInRegistries.BLOCK
                                .getKey(textureBlock)
                                .getNamespace(),
                        "block/" + textureName
                )
        );
    }
    private static com.google.gson.JsonArray createVector(
            float x,
            float y,
            float z
    ) {
        com.google.gson.JsonArray array =
                new com.google.gson.JsonArray();

        array.add(x);
        array.add(y);
        array.add(z);

        return array;
    }
    private void generateMicroBlock(
            BlockModelGenerators generator,
            Block microBlock,
            Identifier texture
    ) {
        String name =
                BuiltInRegistries.BLOCK
                        .getKey(microBlock)
                        .getPath();

        Identifier[] models = new Identifier[8];

        for (int cell = 0; cell < 8; cell++) {
            Identifier modelId =
                    MSBioms.id(
                            "block/" +
                                    name +
                                    "_cell_" +
                                    cell
                    );

            models[cell] = modelId;

            ModelTemplate template =
                    new ModelTemplate(
                            Optional.of(
                                    MSBioms.id(
                                            "block/micro_cube_" +
                                                    cell
                                    )
                            ),
                            Optional.empty(),
                            TextureSlot.PARTICLE,
                            TextureSlot.DOWN,
                            TextureSlot.UP,
                            TextureSlot.NORTH,
                            TextureSlot.SOUTH,
                            TextureSlot.WEST,
                            TextureSlot.EAST
                    );

            TextureMapping mapping =
                    new TextureMapping()
                            .put(TextureSlot.PARTICLE, new Material(texture))
                            .put(TextureSlot.DOWN, new Material(texture))
                            .put(TextureSlot.UP, new Material(texture))
                            .put(TextureSlot.NORTH, new Material(texture))
                            .put(TextureSlot.SOUTH, new Material(texture))
                            .put(TextureSlot.WEST, new Material(texture))
                            .put(TextureSlot.EAST, new Material(texture));

            template.create(
                    modelId,
                    mapping,
                    generator.modelOutput
            );
        }







        MultiPartGenerator blockState =
                MultiPartGenerator.multiPart(microBlock);

        for (int cell = 0; cell < 8; cell++) {
            ConditionBuilder condition =
                    BlockModelGenerators.condition()
                            .term(
                                    MicroBlock.getCellProperty(cell),
                                    true
                            );

            blockState.with(
                    condition,
                    BlockModelGenerators.plainVariant(
                            models[cell]
                    )
            );
        }

        generator.blockStateOutput.accept(blockState);
    }

    private void generateMicroBlock(
            BlockModelGenerators generator,
            Block microBlock,
            Block textureBlock
    ) {
        String name =
                BuiltInRegistries.BLOCK
                        .getKey(microBlock)
                        .getPath();

        Identifier texture =
                Identifier.fromNamespaceAndPath(
                        BuiltInRegistries.BLOCK
                                .getKey(textureBlock)
                                .getNamespace(),
                        "block/" +
                                BuiltInRegistries.BLOCK
                                        .getKey(textureBlock)
                                        .getPath()
                );

        Identifier[] models = new Identifier[8];

        for (int cell = 0; cell < 8; cell++) {

            Identifier modelId =
                    MSBioms.id(
                            "block/" +
                                    name +
                                    "_cell_" +
                                    cell
                    );

            models[cell] = modelId;

            ModelTemplate template =
                    new ModelTemplate(
                            Optional.of(
                                    MSBioms.id(
                                            "block/micro_cube_" +
                                                    cell
                                    )
                            ),
                            Optional.empty(),
                            TextureSlot.PARTICLE,
                            TextureSlot.DOWN,
                            TextureSlot.UP,
                            TextureSlot.NORTH,
                            TextureSlot.SOUTH,
                            TextureSlot.WEST,
                            TextureSlot.EAST
                    );

            TextureMapping mapping =
                    new TextureMapping()
                            .put(
                                    TextureSlot.PARTICLE,
                                    new Material(texture)
                            )
                            .put(
                                    TextureSlot.DOWN,
                                    new Material(texture)
                            )
                            .put(
                                    TextureSlot.UP,
                                    new Material(texture)
                            )
                            .put(
                                    TextureSlot.NORTH,
                                    new Material(texture)
                            )
                            .put(
                                    TextureSlot.SOUTH,
                                    new Material(texture)
                            )
                            .put(
                                    TextureSlot.WEST,
                                    new Material(texture)
                            )
                            .put(
                                    TextureSlot.EAST,
                                    new Material(texture)
                            );

            template.create(
                    modelId,
                    mapping,
                    generator.modelOutput
            );
        }

        MultiPartGenerator blockState =
                MultiPartGenerator.multiPart(microBlock);

        for (int cell = 0; cell < 8; cell++) {

            ConditionBuilder condition =
                    BlockModelGenerators.condition()
                            .term(
                                    MicroBlock.getCellProperty(cell),
                                    true
                            );

            blockState.with(
                    condition,
                    BlockModelGenerators.plainVariant(
                            models[cell]
                    )
            );
        }

        generator.blockStateOutput.accept(
                blockState
        );
    }
    private void generateMicroQuartzBlock(
            BlockModelGenerators generator,
            Block microBlock
    ) {
        String name =
                BuiltInRegistries.BLOCK
                        .getKey(microBlock)
                        .getPath();

        Identifier[] models = new Identifier[8];

        Identifier sideTexture =
                Identifier.fromNamespaceAndPath(
                        "minecraft",
                        "block/quartz_block_side"
                );

        Identifier topTexture =
                Identifier.fromNamespaceAndPath(
                        "minecraft",
                        "block/quartz_block_top"
                );

        Identifier bottomTexture =
                Identifier.fromNamespaceAndPath(
                        "minecraft",
                        "block/quartz_block_bottom"
                );

        for (int cell = 0; cell < 8; cell++) {
            Identifier modelId =
                    MSBioms.id(
                            "block/" +
                                    name +
                                    "_cell_" +
                                    cell
                    );

            models[cell] = modelId;

            ModelTemplate template =
                    new ModelTemplate(
                            Optional.of(
                                    MSBioms.id(
                                            "block/micro_cube_" +
                                                    cell
                                    )
                            ),
                            Optional.empty(),
                            TextureSlot.PARTICLE,
                            TextureSlot.DOWN,
                            TextureSlot.UP,
                            TextureSlot.NORTH,
                            TextureSlot.SOUTH,
                            TextureSlot.WEST,
                            TextureSlot.EAST
                    );

            TextureMapping mapping =
                    new TextureMapping()
                            .put(
                                    TextureSlot.PARTICLE,
                                    new Material(sideTexture)
                            )
                            .put(
                                    TextureSlot.DOWN,
                                    new Material(bottomTexture)
                            )
                            .put(
                                    TextureSlot.UP,
                                    new Material(topTexture)
                            )
                            .put(
                                    TextureSlot.NORTH,
                                    new Material(sideTexture)
                            )
                            .put(
                                    TextureSlot.SOUTH,
                                    new Material(sideTexture)
                            )
                            .put(
                                    TextureSlot.WEST,
                                    new Material(sideTexture)
                            )
                            .put(
                                    TextureSlot.EAST,
                                    new Material(sideTexture)
                            );

            template.create(
                    modelId,
                    mapping,
                    generator.modelOutput
            );
        }

        MultiPartGenerator blockState =
                MultiPartGenerator.multiPart(microBlock);

        for (int cell = 0; cell < 8; cell++) {
            ConditionBuilder condition =
                    BlockModelGenerators.condition()
                            .term(
                                    MicroBlock.getCellProperty(cell),
                                    true
                            );

            blockState.with(
                    condition,
                    BlockModelGenerators.plainVariant(
                            models[cell]
                    )
            );
        }

        generator.blockStateOutput.accept(blockState);
    }




    @Override
    public void generateBlockStateModels(BlockModelGenerators generator) {
        // =========================
        // Willow logs
        // =========================
        generateBogPlant(generator);
        generateMicroBlock(
                generator,
                ModBlocks.MICRO_STONE,
                Blocks.STONE
        );
        generateMicroBlock(
                generator,
                ModBlocks.MICRO_OAK_PLANKS,
                Blocks.OAK_PLANKS
        );
        generateMicroBlock(
                generator,
                ModBlocks.MICRO_BIRCH_PLANKS,
                Blocks.BIRCH_PLANKS
        );

        generateMicroBlock(
                generator,
                ModBlocks.MICRO_DARK_OAK_PLANKS,
                Blocks.DARK_OAK_PLANKS
        );

        generateMicroBlock(
                generator,
                ModBlocks.MICRO_ACACIA_PLANKS,
                Blocks.ACACIA_PLANKS
        );

        generateMicroBlock(
                generator,
                ModBlocks.MICRO_JUNGLE_PLANKS,
                Blocks.JUNGLE_PLANKS
        );

        generateMicroBlock(
                generator,
                ModBlocks.MICRO_MANGROVE_PLANKS,
                Blocks.MANGROVE_PLANKS
        );

        generateMicroBlock(
                generator,
                ModBlocks.MICRO_CHERRY_PLANKS,
                Blocks.CHERRY_PLANKS
        );

        generateMicroBlock(
                generator,
                ModBlocks.MICRO_PALE_OAK_PLANKS,
                Blocks.PALE_OAK_PLANKS
        );

        generateMicroBlock(
                generator,
                ModBlocks.MICRO_BAMBOO_MOSAIC,
                Blocks.BAMBOO_MOSAIC
        );

        generateMicroBlock(
                generator,
                ModBlocks.MICRO_WARPED_PLANKS,
                Blocks.WARPED_PLANKS
        );
        generateMicroBlock(
                generator,
                ModBlocks.MICRO_SPRUCE_PLANKS,
                Blocks.SPRUCE_PLANKS
        );

        generateMicroBlock(
                generator,
                ModBlocks.MICRO_CRIMSON_PLANKS,
                Blocks.CRIMSON_PLANKS
        );

        generateMicroBlock(
                generator,
                ModBlocks.MICRO_WILLOW_PLANKS,
                ModBlocks.WILLOW_PLANKS
        );

        generator.woodProvider(ModBlocks.WILLOW_LOG)
                .log(ModBlocks.WILLOW_LOG)
                .wood(ModBlocks.WILLOW_WOOD);


        generator.woodProvider(ModBlocks.STRIPPED_WILLOW_LOG)
                .log(ModBlocks.STRIPPED_WILLOW_LOG)
                .wood(ModBlocks.STRIPPED_WILLOW_WOOD);



        // =========================
        // Willow wood family
        // =========================
        generator.family(Blocks.PACKED_ICE)
                .stairs(ModBlocks.PACKED_ICE_STAIRS)
                .slab(ModBlocks.PACKED_ICE_SLAB);

        generator.family(Blocks.CALCITE)
                .stairs(ModBlocks.CALCITE_STAIRS)
                .wall(ModBlocks.CALCITE_WALL)
                .slab(ModBlocks.CALCITE_SLAB);
        generator.family(Blocks.PRISMARINE_BRICKS)
                .wall(ModBlocks.PRISMARINE_BRICKS_WALL);
        generator.family(Blocks.DARK_PRISMARINE)
                .wall(ModBlocks.DARK_PRISMARINE_WALL);

        generator.family(Blocks.POLISHED_ANDESITE)
                .wall(ModBlocks.POLISHED_ANDESITE_WALL);

        generator.family(Blocks.POLISHED_DIORITE)
                .wall(ModBlocks.POLISHED_DIORITE_WALL);

        generator.family(Blocks.POLISHED_GRANITE)
                .wall(ModBlocks.POLISHED_GRANITE_WALL);

        generator.family(Blocks.PURPUR_BLOCK)
                .wall(ModBlocks.PURPUR_BLOCK_WALL);

        generator.family(Blocks.QUARTZ_BRICKS)
                .wall(ModBlocks.QUARTZ_BRICKS_WALL);

        generator.family(Blocks.SMOOTH_SANDSTONE)
                .wall(ModBlocks.SMOOTH_SANDSTONE_WALL);

        generator.family(Blocks.SMOOTH_RED_SANDSTONE)
                .wall(ModBlocks.SMOOTH_RED_SANDSTONE_WALL);

        generator.family(Blocks.SMOOTH_QUARTZ)
                .wall(ModBlocks.SMOOTH_QUARTZ_WALL);

        generator.family(Blocks.RESIN_BLOCK)
                .stairs(ModBlocks.RESIN_STAIRS)
                .wall(ModBlocks.RESIN_WALL)
                .slab(ModBlocks.RESIN_SLAB);

        generator.family(Blocks.DRIPSTONE_BLOCK)
                .stairs(ModBlocks.DRIPSTONE_STAIRS)
                .wall(ModBlocks.DRIPSTONE_WALL)
                .slab(ModBlocks.DRIPSTONE_SLAB);

        generator.family(Blocks.PACKED_MUD)
                .stairs(ModBlocks.PACKED_MUD_STAIRS)
                .wall(ModBlocks.PACKED_MUD_WALL)
                .slab(ModBlocks.PACKED_MUD_SLAB);

        generator.family(Blocks.END_STONE)
                .stairs(ModBlocks.END_STONE_STAIRS)
                .wall(ModBlocks.END_STONE_WALL)
                .slab(ModBlocks.END_STONE_SLAB);

        generator.family(Blocks.AMETHYST_BLOCK)
                .stairs(ModBlocks.AMETHYST_STAIRS)
                .wall(ModBlocks.AMETHYST_WALL)
                .slab(ModBlocks.AMETHYST_SLAB);

        generator.family(Blocks.SMOOTH_STONE)
                .stairs(ModBlocks.SMOOTH_STONE_STAIRS)
                .wall(ModBlocks.SMOOTH_STONE_WALL);

        generator.family(Blocks.HONEYCOMB_BLOCK)
                .stairs(ModBlocks.HONEYCOMB_STAIRS)
                .wall(ModBlocks.HONEYCOMB_WALL)
                .slab(ModBlocks.HONEYCOMB_SLAB);

        generator.family(Blocks.CLAY)
                .stairs(ModBlocks.CLAY_STAIRS)
                .wall(ModBlocks.CLAY_WALL)
                .slab(ModBlocks.CLAY_SLAB);

        generator.family(Blocks.NETHERRACK)
                .stairs(ModBlocks.NETHERRACK_STAIRS)
                .wall(ModBlocks.NETHERRACK_WALL)
                .slab(ModBlocks.NETHERRACK_SLAB);

        generator.family(Blocks.BLUE_ICE)
                .stairs(ModBlocks.BLUE_ICE_STAIRS)
                .wall(ModBlocks.BLUE_ICE_WALL)
                .slab(ModBlocks.BLUE_ICE_SLAB);


        generator.family(Blocks.MUD)
                .slab(ModBlocks.MUD_SLAB);

        createTopSideSlab(
                generator,
                ModBlocks.BASALT_SLAB,
                Identifier.fromNamespaceAndPath(
                        "minecraft",
                        "block/basalt_side"
                ),
                Identifier.fromNamespaceAndPath(
                        "minecraft",
                        "block/basalt_top"
                ),
                Blocks.BASALT
        );

        createTopSideSlab(
                generator,
                ModBlocks.HAY_SLAB,
                Identifier.fromNamespaceAndPath(
                        "minecraft",
                        "block/hay_block_side"
                ),
                Identifier.fromNamespaceAndPath(
                        "minecraft",
                        "block/hay_block_top"
                ),
                Blocks.HAY_BLOCK
        );

        createTopSideSlab(
                generator,
                ModBlocks.BONE_SLAB,
                Identifier.fromNamespaceAndPath(
                        "minecraft",
                        "block/bone_block_side"
                ),
                Identifier.fromNamespaceAndPath(
                        "minecraft",
                        "block/bone_block_top"
                ),
                Blocks.BONE_BLOCK
        );





        generator.family(Blocks.SMOOTH_BASALT)
                .stairs(ModBlocks.SMOOTH_BASALT_STAIRS)
                .wall(ModBlocks.SMOOTH_BASALT_WALL)
                .slab(ModBlocks.SMOOTH_BASALT_SLAB);


        generator.family(ModBlocks.WILLOW_PLANKS)
                .generateFor(ModBlocks.WILLOW_FAMILY);
        // =========================
        // Willow leaves
        // =========================

        generator.createTintedLeaves(
                ModBlocks.WILLOW_LEAVES,
                TexturedModel.LEAVES,
                -12012264
        );

        generator.createCrossBlockWithDefaultItem(
                ModBlocks.WILLOW_SAPLING,
                BlockModelGenerators.PlantType.NOT_TINTED
        );
        generateMicroBlock(
                generator,
                ModBlocks.MICRO_COBBLESTONE,
                Blocks.COBBLESTONE
        );

        generateMicroBlock(
                generator,
                ModBlocks.MICRO_MOSSY_COBBLESTONE,
                Blocks.MOSSY_COBBLESTONE
        );

        generateMicroBlock(
                generator,
                ModBlocks.MICRO_ANDESITE,
                Blocks.ANDESITE
        );

        generateMicroBlock(
                generator,
                ModBlocks.MICRO_GRANITE,
                Blocks.GRANITE
        );

        generateMicroBlock(
                generator,
                ModBlocks.MICRO_DIORITE,
                Blocks.DIORITE
        );

        generateMicroBlock(
                generator,
                ModBlocks.MICRO_COBBLED_DEEPSLATE,
                Blocks.COBBLED_DEEPSLATE
        );

        generateMicroBlock(
                generator,
                ModBlocks.MICRO_TUFF,
                Blocks.TUFF
        );

        generateMicroBlock(
                generator,
                ModBlocks.MICRO_TUFF_BRICKS,
                Blocks.TUFF_BRICKS
        );

        generateMicroBlock(
                generator,
                ModBlocks.MICRO_DEEPSLATE_BRICKS,
                Blocks.DEEPSLATE_BRICKS
        );

        generateMicroBlock(
                generator,
                ModBlocks.MICRO_STONE_BRICKS,
                Blocks.STONE_BRICKS
        );

        generateMicroBlock(
                generator,
                ModBlocks.MICRO_BRICKS,
                Blocks.BRICKS
        );

        generateMicroBlock(
                generator,
                ModBlocks.MICRO_DARK_PRISMARINE,
                Blocks.DARK_PRISMARINE
        );

        generateMicroBlock(
                generator,
                ModBlocks.MICRO_PRISMARINE,
                Blocks.PRISMARINE
        );
        generateMicroBlock(
                generator,
                ModBlocks.MICRO_BLACKSTONE,
                Blocks.BLACKSTONE
        );

        generateMicroBlock(
                generator,
                ModBlocks.MICRO_POLISHED_BLACKSTONE_BRICKS,
                Blocks.POLISHED_BLACKSTONE_BRICKS
        );

        generateMicroBlock(
                generator,
                ModBlocks.MICRO_MAGMA,
                Blocks.MAGMA_BLOCK
        );

        generateMicroBlock(
                generator,
                ModBlocks.MICRO_QUARTZ_BRICKS,
                Blocks.QUARTZ_BRICKS
        );

        generateMicroBlock(
                generator,
                ModBlocks.MICRO_NETHER_BRICKS,
                Blocks.NETHER_BRICKS
        );

        generateMicroBlock(
                generator,
                ModBlocks.MICRO_RED_NETHER_BRICKS,
                Blocks.RED_NETHER_BRICKS
        );

        generateMicroBlock(
                generator,
                ModBlocks.MICRO_DRIPSTONE_BLOCK,
                Blocks.DRIPSTONE_BLOCK
        );

        generateMicroBlock(
                generator,
                ModBlocks.MICRO_SMOOTH_BASALT,
                Blocks.SMOOTH_BASALT
        );

        generateMicroBlock(
                generator,
                ModBlocks.MICRO_AMETHYST_BLOCK,
                Blocks.AMETHYST_BLOCK
        );

        generateMicroBlock(
                generator,
                ModBlocks.MICRO_RESIN_BLOCK,
                Blocks.RESIN_BLOCK
        );

        generateMicroBlock(
                generator,
                ModBlocks.MICRO_RESIN_BRICKS,
                Blocks.RESIN_BRICKS
        );

        generateMicroBlock(
                generator,
                ModBlocks.MICRO_SULFUR,
                Blocks.SULFUR
        );

        generateMicroBlock(
                generator,
                ModBlocks.MICRO_SULFUR_BRICKS,
                Blocks.SULFUR_BRICKS
        );

        generateMicroBlock(
                generator,
                ModBlocks.MICRO_CINNABAR,
                Blocks.CINNABAR
        );

        generateMicroBlock(
                generator,
                ModBlocks.MICRO_CINNABAR_BRICKS,
                Blocks.CINNABAR_BRICKS
        );
        generateMicroBlock(
                generator,
                ModBlocks.MICRO_SMOOTH_SANDSTONE,
                Identifier.fromNamespaceAndPath(
                        "minecraft",
                        "block/sandstone_top"
                )
        );
        generateMicroBlock(
                generator,
                ModBlocks.MICRO_SMOOTH_RED_SANDSTONE,
                Identifier.fromNamespaceAndPath(
                        "minecraft",
                        "block/red_sandstone_top"
                )
        );
        generateMicroQuartzBlock(
                generator,
                ModBlocks.MICRO_SMOOTH_QUARTZ
        );




        // =========================
        // Willow door / trapdoor
        // =========================

        generator.createDoor(ModBlocks.WILLOW_DOOR);
        generator.createTrapdoor(ModBlocks.WILLOW_TRAPDOOR);

        generator.createGrowingPlant(
                ModBlocks.HIGH_GRASS,
                ModBlocks.HIGH_GRASS_PLANT,
                BlockModelGenerators.PlantType.TINTED
        );

        // =========================
        // Other blocks
        // =========================

        generator.createTrivialCube(ModBlocks.DRIED_EARTH);
        generator.createTrivialCube(ModBlocks.SALT_BLOCk);
        generateBog(generator);


    }

    @Override
    public void generateItemModels(
            @NonNull ItemModelGenerators generator
    ) {
        generateMicroBlockItem(
                generator,
                ModItems.MICRO_STONE,
                ModBlocks.MICRO_STONE
        );
        generateMicroBlockItem(
                generator,
                ModItems.MICRO_BIRCH_PLANKS,
                ModBlocks.MICRO_BIRCH_PLANKS
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_DARK_OAK_PLANKS,
                ModBlocks.MICRO_DARK_OAK_PLANKS
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_ACACIA_PLANKS,
                ModBlocks.MICRO_ACACIA_PLANKS
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_JUNGLE_PLANKS,
                ModBlocks.MICRO_JUNGLE_PLANKS
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_MANGROVE_PLANKS,
                ModBlocks.MICRO_MANGROVE_PLANKS
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_CHERRY_PLANKS,
                ModBlocks.MICRO_CHERRY_PLANKS
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_PALE_OAK_PLANKS,
                ModBlocks.MICRO_PALE_OAK_PLANKS
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_BAMBOO_MOSAIC,
                ModBlocks.MICRO_BAMBOO_MOSAIC
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_WARPED_PLANKS,
                ModBlocks.MICRO_WARPED_PLANKS
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_CRIMSON_PLANKS,
                ModBlocks.MICRO_CRIMSON_PLANKS
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_WILLOW_PLANKS,
                ModBlocks.MICRO_WILLOW_PLANKS
        );
        generateMicroBlockItem(
                generator,
                ModItems.MICRO_COBBLESTONE,
                ModBlocks.MICRO_COBBLESTONE
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_MOSSY_COBBLESTONE,
                ModBlocks.MICRO_MOSSY_COBBLESTONE
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_ANDESITE,
                ModBlocks.MICRO_ANDESITE
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_GRANITE,
                ModBlocks.MICRO_GRANITE
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_DIORITE,
                ModBlocks.MICRO_DIORITE
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_COBBLED_DEEPSLATE,
                ModBlocks.MICRO_COBBLED_DEEPSLATE
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_TUFF,
                ModBlocks.MICRO_TUFF
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_TUFF_BRICKS,
                ModBlocks.MICRO_TUFF_BRICKS
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_DEEPSLATE_BRICKS,
                ModBlocks.MICRO_DEEPSLATE_BRICKS
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_STONE_BRICKS,
                ModBlocks.MICRO_STONE_BRICKS
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_BRICKS,
                ModBlocks.MICRO_BRICKS
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_DARK_PRISMARINE,
                ModBlocks.MICRO_DARK_PRISMARINE
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_PRISMARINE,
                ModBlocks.MICRO_PRISMARINE
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_SMOOTH_SANDSTONE,
                ModBlocks.MICRO_SMOOTH_SANDSTONE
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_SMOOTH_RED_SANDSTONE,
                ModBlocks.MICRO_SMOOTH_RED_SANDSTONE
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_SMOOTH_QUARTZ,
                ModBlocks.MICRO_SMOOTH_QUARTZ
        );
        generateMicroBlockItem(
                generator,
                ModItems.MICRO_SPRUCE_PLANKS,
                ModBlocks.MICRO_SPRUCE_PLANKS
        );
        generateMicroBlockItem(
                generator,
                ModItems.MICRO_BLACKSTONE,
                ModBlocks.MICRO_BLACKSTONE
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_POLISHED_BLACKSTONE_BRICKS,
                ModBlocks.MICRO_POLISHED_BLACKSTONE_BRICKS
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_MAGMA,
                ModBlocks.MICRO_MAGMA
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_QUARTZ_BRICKS,
                ModBlocks.MICRO_QUARTZ_BRICKS
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_NETHER_BRICKS,
                ModBlocks.MICRO_NETHER_BRICKS
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_RED_NETHER_BRICKS,
                ModBlocks.MICRO_RED_NETHER_BRICKS
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_DRIPSTONE_BLOCK,
                ModBlocks.MICRO_DRIPSTONE_BLOCK
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_SMOOTH_BASALT,
                ModBlocks.MICRO_SMOOTH_BASALT
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_AMETHYST_BLOCK,
                ModBlocks.MICRO_AMETHYST_BLOCK
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_RESIN_BLOCK,
                ModBlocks.MICRO_RESIN_BLOCK
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_RESIN_BRICKS,
                ModBlocks.MICRO_RESIN_BRICKS
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_SULFUR,
                ModBlocks.MICRO_SULFUR
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_SULFUR_BRICKS,
                ModBlocks.MICRO_SULFUR_BRICKS
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_CINNABAR,
                ModBlocks.MICRO_CINNABAR
        );

        generateMicroBlockItem(
                generator,
                ModItems.MICRO_CINNABAR_BRICKS,
                ModBlocks.MICRO_CINNABAR_BRICKS
        );



        generateMicroBlockItem(
                generator,
                ModItems.MICRO_OAK_PLANKS,
                ModBlocks.MICRO_OAK_PLANKS
        );
        generator.generateFlatItem(
                ModItems.DEAD_BRANCH,
                ModelTemplates.FLAT_ITEM
        );
        generator.generateFlatItem(
                ModItems.HIGH_GRASS,
                ModelTemplates.FLAT_ITEM
        );
        generator.generateFlatItem(
                ModItems.WATERGRASS,
                ModelTemplates.FLAT_ITEM
        );
        generator.generateFlatItem(
                ModItems.BOG,
                ModelTemplates.FLAT_ITEM
        );

    }


    @Override
    public @NonNull String getName() {
        return "MSBiomsModelProvider";
    }

}