package net.paulem.pos.items;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.paulem.pos.POS;

public class POSItemsIds {
    private POSItemsIds() {
        /* This utility class should not be instantiated */
    }

    public static final ResourceKey<Item> ECHO_STABILIZED_AMETHYST = create("echo_stabilized_amethyst");

    public static final ResourceKey<Item> ECHO_STABILIZED_AMETHYST_HELMET = create("echo_stabilized_amethyst_helmet");
    public static final ResourceKey<Item> ECHO_STABILIZED_AMETHYST_CHESTPLATE = create("echo_stabilized_amethyst_chestplate");
    public static final ResourceKey<Item> ECHO_STABILIZED_AMETHYST_LEGGINGS = create("echo_stabilized_amethyst_leggings");
    public static final ResourceKey<Item> ECHO_STABILIZED_AMETHYST_BOOTS = create("echo_stabilized_amethyst_boots");

    public static ResourceKey<Item> create(String name) {
        // Create the item key.
        return ResourceKey.create(Registries.ITEM, POS.id(name));
    }

    public static void init() {
        POS.LOGGER.info("Initialized items ids!");
    }
}
