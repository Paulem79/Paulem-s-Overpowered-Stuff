package net.paulem.pos.items;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.paulem.pos.POS;

import java.util.function.Function;

public class POSItems {
    private POSItems() {
        /* This utility class should not be instantiated */
    }

    public static final Item ECHO_STABILIZED_AMETHYST = register("echo_stabilized_amethyst", Item::new, new Item.Properties());

    private static Item register(String itemName, Function<Item.Properties, Item> itemFactory, Item.Properties settings) {
        ResourceKey<Item> itemKey = createId(itemName);

        return register(itemKey, itemFactory, settings);
    }

    private static Item register(ResourceKey<Item> itemKey, Function<Item.Properties, Item> itemFactory, Item.Properties settings) {
        // Create the item instance.
        Item item = itemFactory.apply(settings.setId(itemKey));

        // Register the item.
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    private static ResourceKey<Item> createId(String name) {
        // Create the item key.
        return ResourceKey.create(Registries.ITEM, POS.id(name));
    }

    public static void init() {
        POS.LOGGER.info("Initialized items!");
    }
}
