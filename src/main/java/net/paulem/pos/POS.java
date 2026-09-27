package net.paulem.pos;

import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class POS implements ModInitializer {
	public static final String MOD_ID = "pos";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello from Paulem's Overpowered Stuff!");
		LOGGER.info("Initializing reactors destruction...");
		LOGGER.info("Hey, reminds me of something, should I call Brandon's Core?");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
