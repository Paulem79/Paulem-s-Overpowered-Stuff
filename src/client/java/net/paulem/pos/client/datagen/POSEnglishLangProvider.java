package net.paulem.pos.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.paulem.pos.POSCreativeTab;
import net.paulem.pos.items.POSItems;
import org.apache.commons.lang3.text.WordUtils;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class POSEnglishLangProvider extends FabricLanguageProvider {
    public POSEnglishLangProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider holderLookup, TranslationBuilder translationBuilder) {
        translationBuilder.addCreativeModeTab(POSCreativeTab.CUSTOM_CREATIVE_TAB_KEY, "Paulem's Overpowered Armors");

        for (Map.Entry<ResourceKey<Item>, Item> entry : POSItems.ITEMS.entrySet()) {
            ResourceKey<Item> resourceKey = entry.getKey();
            Item item = entry.getValue();

            Identifier identifier = resourceKey.identifier();
            String path = identifier.getPath();

            // for example, echo_stabilized_amethyst_helmet -> Echo Stabilized Amethyst Helmet
            String name = WordUtils.capitalizeFully(path.replace("_", " "));
            translationBuilder.add(item, name);
        }
    }
}