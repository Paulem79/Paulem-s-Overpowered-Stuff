package net.paulem.pos.sounds;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.paulem.pos.POS;

public class POSSounds {
    private POSSounds() {
        /* This utility class should not be instantiated */
    }

    public static final SoundEvent ENERGY_ATTACK = register("energy_attack");

    private static SoundEvent register(String id) {
        Identifier identifier = POS.id(id);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, identifier, SoundEvent.createVariableRangeEvent(identifier));
    }

    public static void init() {
        POS.LOGGER.info("Registering sounds for " + POS.MOD_ID);
    }
}
