package net.paulem.pos;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.paulem.pos.items.POSItems;
import net.paulem.pos.items.armors.POSEnergyArmorItem;
import net.paulem.pos.sounds.POSSounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class POS implements ModInitializer {
	public static final String MOD_ID = "pos";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final EquipmentSlot[] ARMOR_SLOTS = {
			EquipmentSlot.FEET,
			EquipmentSlot.LEGS,
			EquipmentSlot.CHEST,
			EquipmentSlot.HEAD
	};

	private static final ThreadLocal<Boolean> IS_ABSORBING = ThreadLocal.withInitial(() -> false);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello from Paulem's Overpowered Stuff!");
		LOGGER.info("Initializing reactors destruction...");
		LOGGER.info("Hey, reminds me of something, should I call Brandon's Core?");

		POSSounds.init();

		POSItems.init();
		POSCreativeTab.init();

		ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {
			if (IS_ABSORBING.get()) {
				return true;
			}

			if (entity instanceof ServerPlayer player && !source.is(DamageTypeTags.BYPASSES_ARMOR) && amount > 0) {
				long energyPerDamage = 100;
				long totalEnergyNeeded = (long) (amount * energyPerDamage);
				long energyAbsorbed = 0;

				for (EquipmentSlot slot : ARMOR_SLOTS) {
					ItemStack stack = player.getItemBySlot(slot);
					if (stack.getItem() instanceof POSEnergyArmorItem energyItem) {
						long currentEnergy = energyItem.getStoredEnergy(stack);
						long needed = totalEnergyNeeded - energyAbsorbed;
						if (needed <= 0) break;

						long toDrain = Math.min(currentEnergy, needed);
						if (toDrain > 0) {
							energyItem.tryUseEnergy(stack, toDrain);
							energyAbsorbed += toDrain;

							if (energyItem.updateAttributes(stack)) {
								player.setItemSlot(slot, stack.copy());
							}
						}
					}
				}

				if (energyAbsorbed > 0) {
					float damageAbsorbed = (float) energyAbsorbed / energyPerDamage;
					float remainingDamage = amount - damageAbsorbed;

					if (remainingDamage <= 0) {
						player.level().playSound(
								null, player.getX(), player.getY(), player.getZ(),
								POSSounds.ENERGY_ATTACK, SoundSource.PLAYERS, 1F, 1F
						);
						return false; // Cancel the full attack
					} else {
						IS_ABSORBING.set(true);
						try {
							player.hurtServer(player.level(), source, remainingDamage);
						} finally {
							IS_ABSORBING.set(false);
						}
						return false; // Cancel the initial attack in favor of the reduced damage
					}
				}
			}
			return true;
		});

		ServerTickEvents.END_SERVER_TICK.register(server -> {
			for (ServerPlayer player : server.getPlayerList().getPlayers()) {
				for (EquipmentSlot slot : ARMOR_SLOTS) {
					ItemStack stack = player.getItemBySlot(slot);
					if (stack.getItem() instanceof POSEnergyArmorItem energyItem) {
						if (energyItem.updateAttributes(stack)) {
							player.setItemSlot(slot, stack.copy());
						}
					}
				}
			}
		});
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}