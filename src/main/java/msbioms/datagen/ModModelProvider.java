package msbioms.datagen;

import com.mojang.math.Quadrant;
import msbioms.MSBioms;
import msbioms.block.BogPlantBlock;
import msbioms.block.ModBlocks;
import msbioms.item.ModItems;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiPartGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jspecify.annotations.NonNull;


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


    @Override
    public void generateBlockStateModels(BlockModelGenerators generator) {

        // =========================
        // Willow logs
        // =========================
        generateBogPlant(generator);

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