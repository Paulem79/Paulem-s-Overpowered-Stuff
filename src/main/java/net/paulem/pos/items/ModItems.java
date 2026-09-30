package net.paulem.pos.items;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorType;
import net.paulem.pos.POS;
import net.paulem.pos.items.armors.EchoStabilizedAmethystArmorMaterial;
import net.paulem.pos.items.armors.ModEnergyArmorItem;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public class ModItems {
    private ModItems() {
        /* This utility class should not be instantiated */
    }

    public static final Map<ResourceKey<Item>, Item> ITEMS = new LinkedHashMap<>();

    public static final Item ECHO_STABILIZED_AMETHYST = register(ModItemsIds.ECHO_STABILIZED_AMETHYST, Item::new, new Item.Properties());

    public static final ModEnergyArmorItem ECHO_STABILIZED_AMETHYST_HELMET = register(
            ModItemsIds.ECHO_STABILIZED_AMETHYST_HELMET,
            ModEnergyArmorItem::new,
            new Item.Properties().humanoidArmor(EchoStabilizedAmethystArmorMaterial.INSTANCE, ArmorType.HELMET)
    );
    public static final ModEnergyArmorItem ECHO_STABILIZED_AMETHYST_CHESTPLATE = register(
            ModItemsIds.ECHO_STABILIZED_AMETHYST_CHESTPLATE,
            ModEnergyArmorItem::new,
            new Item.Properties().humanoidArmor(EchoStabilizedAmethystArmorMaterial.INSTANCE, ArmorType.CHESTPLATE)
    );

    public static final ModEnergyArmorItem ECHO_STABILIZED_AMETHYST_LEGGINGS = register(
            ModItemsIds.ECHO_STABILIZED_AMETHYST_LEGGINGS,
            ModEnergyArmorItem::new,
            new Item.Properties().humanoidArmor(EchoStabilizedAmethystArmorMaterial.INSTANCE, ArmorType.LEGGINGS)
    );

    public static final ModEnergyArmorItem ECHO_STABILIZED_AMETHYST_BOOTS = register(
            ModItemsIds.ECHO_STABILIZED_AMETHYST_BOOTS,
            ModEnergyArmorItem::new,
            new Item.Properties().humanoidArmor(EchoStabilizedAmethystArmorMaterial.INSTANCE, ArmorType.BOOTS)
    );

    private static<T extends Item> T register(ResourceKey<Item> itemKey, Function<Item.Properties, T> itemFactory, Item.Properties settings) {
        T item = itemFactory.apply(settings.setId(itemKey));

        ITEMS.put(itemKey, item);

        return Registry.register(BuiltInRegistries.ITEM, itemKey, item);
    }

    public static void init() {
        POS.LOGGER.info("Initialized items!");
    }
}
