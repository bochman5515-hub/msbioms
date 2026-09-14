package msbioms;

import msbioms.block.ModBlocks;
import msbioms.block.ModWoodTypes;
import msbioms.creativemodetab.ModCreativeModeTabs;
import msbioms.item.ModItems;
import msbioms.worldgen.ModBiomeGeneration;
import msbioms.worldgen.carver.ChambersCarver;
import net.fabricmc.api.ModInitializer;


import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import msbioms.worldgen.ModBiomePlacement;

import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import msbioms.worldgen.ModWorldGeneration;



public class MSBioms implements ModInitializer {
	public static final String MOD_ID = "msbioms";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ModWoodTypes.register();
		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		ModWorldGeneration.generateModWorldGen();
		ModBiomeGeneration.generateModBiomeGeneration();
		ModBiomePlacement.register();
		ModCreativeModeTabs.registerModCreativeModeTabs();

		LOGGER.info("MSBioms initialized");
		LOGGER.info("Hello Fabric world!");

	}
	public static final ChambersCarver CHAMBERS_CARVER =
			Registry.register(
					BuiltInRegistries.CARVER,
					id("chambers"),
					new ChambersCarver(
							CaveCarverConfiguration.CODEC
					)
			);


	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}

