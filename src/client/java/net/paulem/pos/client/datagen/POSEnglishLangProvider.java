package net.paulem.pos.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.paulem.pos.items.POAItems;

import java.util.concurrent.CompletableFuture;

public class POSEnglishLangProvider extends FabricLanguageProvider {
    public POSEnglishLangProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider holderLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(POAItems.ECHO_STABILIZED_AMETHYST, "Echo Stabilized Amethyst");
    }
}