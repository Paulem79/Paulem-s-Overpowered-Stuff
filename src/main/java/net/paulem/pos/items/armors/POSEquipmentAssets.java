package net.paulem.pos.items.armors;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.paulem.pos.POS;

public interface POSEquipmentAssets {
    ResourceKey<EquipmentAsset> ECHO_STABILIZED_AMETHYST = createId("echo_stabilized_amethyst");

    static ResourceKey<EquipmentAsset> createId(final String name) {
        return ResourceKey.create(EquipmentAssets.ROOT_ID, POS.id(name));
    }
}
