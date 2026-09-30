package net.paulem.pos.client.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.paulem.pos.items.ModItems;
import net.paulem.pos.items.armors.ModEnergyArmorItem;
import org.apache.commons.lang3.StringUtils;
import org.jspecify.annotations.NonNull;

import java.util.Map;

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
        itemModelGenerator.generateFlatItem(ModItems.ECHO_STABILIZED_AMETHYST, ModelTemplates.FLAT_ITEM);

        ModItems.ITEMS.forEach((itemKey, item) -> {
            if (item instanceof ModEnergyArmorItem) {
                itemModelGenerator.generateTrimmableItem(
                        item,
                        ItemModelGenerators.prefixForSlotTrim(StringUtils.substringAfterLast(itemKey.identifier().getPath(), "_")),
                        false,
                        Map.of()
                );
            }
        });
    }

    @Override
    public @NonNull String getName() {
        return "POSModelProvider";
    }
}