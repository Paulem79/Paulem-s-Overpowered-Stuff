package net.paulem.pos.items.armors;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.*;
import net.paulem.pos.POS;

public interface EchoStabilizedAmethystArmorMaterial {
    int BASE_DURABILITY = 49;
    ResourceKey<EquipmentAsset> ECHO_STABILIZED_AMETHYST_ARMOR_MATERIAL_KEY = ResourceKey.create(EquipmentAssets.ROOT_ID, POS.id("echo_stabilized_amethyst"));
    TagKey<Item> REPAIRS_ECHO_STABILIZED_AMETHYST_ARMOR = TagKey.create(BuiltInRegistries.ITEM.key(), POS.id("repairs_echo_stabilized_amethyst_armor"));

    ArmorMaterial INSTANCE = new ArmorMaterial(
            BASE_DURABILITY,
            ArmorMaterials.makeDefense(4, 7, 9, 4, 25),
            25,
            SoundEvents.ARMOR_EQUIP_IRON,
            5.0F,
            0.3F,
            REPAIRS_ECHO_STABILIZED_AMETHYST_ARMOR,
            ECHO_STABILIZED_AMETHYST_ARMOR_MATERIAL_KEY
    );
}
