package net.paulem.pos.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.paulem.pos.items.armors.EchoStabilizedAmethystArmorMaterial;
import net.paulem.pos.items.ModItemsIds;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class POSItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
    public POSItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider registries) {
        builder(EchoStabilizedAmethystArmorMaterial.REPAIRS_ECHO_STABILIZED_AMETHYST_ARMOR)
                .add(ModItemsIds.ECHO_STABILIZED_AMETHYST);

        builder(ItemTags.HEAD_ARMOR)
                .add(ModItemsIds.ECHO_STABILIZED_AMETHYST_HELMET);
        builder(ItemTags.CHEST_ARMOR)
                .add(ModItemsIds.ECHO_STABILIZED_AMETHYST_CHESTPLATE);
        builder(ItemTags.LEG_ARMOR)
                .add(ModItemsIds.ECHO_STABILIZED_AMETHYST_LEGGINGS);
        builder(ItemTags.FOOT_ARMOR)
                .add(ModItemsIds.ECHO_STABILIZED_AMETHYST_BOOTS);
    }
}
