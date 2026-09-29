package net.paulem.pos.items;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorType;
import net.paulem.pos.POS;
import net.paulem.pos.items.armors.EchoStabilizedAmethystArmorMaterial;
import net.paulem.pos.items.armors.POSEnergyArmorItem;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public class POSItems {
    private POSItems() {
        /* This utility class should not be instantiated */
    }

    public static final Map<ResourceKey<Item>, Item> ITEMS = new LinkedHashMap<>();

    public static final Item ECHO_STABILIZED_AMETHYST = register(POSItemsIds.ECHO_STABILIZED_AMETHYST, Item::new, new Item.Properties());

    public static final POSEnergyArmorItem ECHO_STABILIZED_AMETHYST_HELMET = register(
            POSItemsIds.ECHO_STABILIZED_AMETHYST_HELMET,
            POSEnergyArmorItem::new,
            new Item.Properties().humanoidArmor(EchoStabilizedAmethystArmorMaterial.INSTANCE, ArmorType.HELMET)
    );
    public static final POSEnergyArmorItem ECHO_STABILIZED_AMETHYST_CHESTPLATE = register(
            POSItemsIds.ECHO_STABILIZED_AMETHYST_CHESTPLATE,
            POSEnergyArmorItem::new,
            new Item.Properties().humanoidArmor(EchoStabilizedAmethystArmorMaterial.INSTANCE, ArmorType.CHESTPLATE)
    );

    public static final POSEnergyArmorItem ECHO_STABILIZED_AMETHYST_LEGGINGS = register(
            POSItemsIds.ECHO_STABILIZED_AMETHYST_LEGGINGS,
            POSEnergyArmorItem::new,
            new Item.Properties().humanoidArmor(EchoStabilizedAmethystArmorMaterial.INSTANCE, ArmorType.LEGGINGS)
    );

    public static final POSEnergyArmorItem ECHO_STABILIZED_AMETHYST_BOOTS = register(
            POSItemsIds.ECHO_STABILIZED_AMETHYST_BOOTS,
            POSEnergyArmorItem::new,
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
