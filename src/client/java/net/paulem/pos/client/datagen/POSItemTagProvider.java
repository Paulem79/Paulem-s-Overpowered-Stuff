package net.paulem.pos.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.paulem.pos.items.armors.EchoStabilizedAmethystArmorMaterial;
import net.paulem.pos.items.POSItemsIds;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class POSItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
    public POSItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider registries) {
        builder(EchoStabilizedAmethystArmorMaterial.REPAIRS_ECHO_STABILIZED_AMETHYST_ARMOR)
                .add(POSItemsIds.ECHO_STABILIZED_AMETHYST);
    }
}
