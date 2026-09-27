package net.paulem.pos.items;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorType;
import net.paulem.pos.POS;
import net.paulem.pos.items.armors.EchoStabilizedAmethystArmorMaterial;
import net.paulem.pos.items.armors.POSArmorItem;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class POSItems {
    private POSItems() {
        /* This utility class should not be instantiated */
    }

    public static final Map<ResourceKey<Item>, Item> ITEMS = new HashMap<>();

    public static final Item ECHO_STABILIZED_AMETHYST = register(POSItemsIds.ECHO_STABILIZED_AMETHYST, Item::new, new Item.Properties());

    public static final POSArmorItem ECHO_STABILIZED_AMETHYST_HELMET = register(
            POSItemsIds.ECHO_STABILIZED_AMETHYST_HELMET,
            POSArmorItem::new,
            new Item.Properties().humanoidArmor(EchoStabilizedAmethystArmorMaterial.INSTANCE, ArmorType.HELMET)
                    .durability(ArmorType.HELMET.getDurability(EchoStabilizedAmethystArmorMaterial.BASE_DURABILITY))
    );
    public static final POSArmorItem ECHO_STABILIZED_AMETHYST_CHESTPLATE = register(
            POSItemsIds.ECHO_STABILIZED_AMETHYST_CHESTPLATE,
            POSArmorItem::new,
            new Item.Properties().humanoidArmor(EchoStabilizedAmethystArmorMaterial.INSTANCE, ArmorType.CHESTPLATE)
                    .durability(ArmorType.CHESTPLATE.getDurability(EchoStabilizedAmethystArmorMaterial.BASE_DURABILITY))
    );

    public static final POSArmorItem ECHO_STABILIZED_AMETHYST_LEGGINGS = register(
            POSItemsIds.ECHO_STABILIZED_AMETHYST_LEGGINGS,
            POSArmorItem::new,
            new Item.Properties().humanoidArmor(EchoStabilizedAmethystArmorMaterial.INSTANCE, ArmorType.LEGGINGS)
                    .durability(ArmorType.LEGGINGS.getDurability(EchoStabilizedAmethystArmorMaterial.BASE_DURABILITY))
    );

    public static final POSArmorItem ECHO_STABILIZED_AMETHYST_BOOTS = register(
            POSItemsIds.ECHO_STABILIZED_AMETHYST_BOOTS,
            POSArmorItem::new,
            new Item.Properties().humanoidArmor(EchoStabilizedAmethystArmorMaterial.INSTANCE, ArmorType.BOOTS)
                    .durability(ArmorType.BOOTS.getDurability(EchoStabilizedAmethystArmorMaterial.BASE_DURABILITY))
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
