package net.paulem.pos.client.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.paulem.pos.items.POAItems;
import org.jspecify.annotations.NonNull;

public class POSModelProvider extends FabricModelProvider {
    public POSModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(@NonNull BlockModelGenerators blockStateModelGenerator) {
        // empty
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(POAItems.ECHO_STABILIZED_AMETHYST, ModelTemplates.FLAT_ITEM);
    }

    @Override
    public @NonNull String getName() {
        return "POSModelProvider";
    }
}