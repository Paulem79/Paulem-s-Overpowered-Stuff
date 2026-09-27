package net.paulem.pos.client;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.paulem.pos.client.datagen.POSEnglishLangProvider;
import net.paulem.pos.client.datagen.POSModelProvider;
import net.paulem.pos.client.datagen.POSRecipeProvider;

public class POSDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(POSModelProvider::new);
		pack.addProvider(POSEnglishLangProvider::new);
		pack.addProvider(POSRecipeProvider::new);
	}
}
