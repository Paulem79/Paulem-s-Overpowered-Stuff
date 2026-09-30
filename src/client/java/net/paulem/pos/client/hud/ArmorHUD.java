package net.paulem.pos.client.hud;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.paulem.pos.POS;
import net.paulem.pos.items.armors.ModEnergyArmorItem;

public class ArmorHUD {

    public static void extract(GuiGraphicsExtractor graphics, DeltaTracker tickCounter) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return;

        Font font = Minecraft.getInstance().font;

        long totalEnergy = 0;
        long totalMaxEnergy = 0;
        boolean hasEnergyArmor = false;

        // Go through all armor slots
        for (EquipmentSlot equipmentSlot : POS.ARMOR_SLOTS) {
            ItemStack stack = player.getItemBySlot(equipmentSlot);
            Item item = stack.getItem();

            if (item instanceof ModEnergyArmorItem armorItem) {
                totalEnergy += armorItem.getStoredEnergy(stack);
                totalMaxEnergy += armorItem.getEnergyCapacity(stack);
                hasEnergyArmor = true;
            }
        }

        // If no armor, show nothing
        if (!hasEnergyArmor || totalMaxEnergy == 0) return;

        int startX = 10;
        int startY = 10;
        int barWidth = 110;
        int barHeight = 12;

        // Show chest icon
        ItemStack chestStack = player.getItemBySlot(EquipmentSlot.CHEST);
        if (!chestStack.isEmpty()) {
            graphics.item(chestStack, startX, startY);
        }

        int barX = startX + 20;
        int barY = startY + 2;

        // Border and background
        int borderColor = 0xFF2B003B;
        int backgroundColor = 0xFF050510;

        graphics.fill(barX - 1, barY - 1, barX + barWidth + 1, barY + barHeight + 1, borderColor);
        graphics.fill(barX, barY, barX + barWidth, barY + barHeight, backgroundColor);

        // Bar fill
        float ratio = Math.clamp((float) totalEnergy / totalMaxEnergy, 0.0f, 1.0f);
        int filledWidth = (int) (barWidth * ratio);
        if (filledWidth > 0) {
            int fillColor = ARGB.linearLerp(ratio, 0xFFFF0000, 0xFF00FF00);
            graphics.fill(barX, barY, barX + filledWidth, barY + barHeight, fillColor);
        }

        // Text with total
        String text = formatEnergy(totalEnergy) + " / " + formatEnergy(totalMaxEnergy);
        int textWidth = font.width(text);
        int textX = barX + (barWidth - textWidth) / 2;
        int textY = barY + (barHeight - font.lineHeight) / 2 + 1;

        graphics.text(font, text, textX, textY, 0xFF00FFFF, true);
    }

    private static String formatEnergy(long amount) {
        if (amount >= 1_000_000_000L) {
            return String.format("%.1fB", amount / 1_000_000_000.0);
        } else if (amount >= 1_000_000L) {
            return String.format("%.1fM", amount / 1_000_000.0);
        } else if (amount >= 1_000L) {
            return String.format("%.1fk", amount / 1_000.0);
        }
        return String.valueOf(amount);
    }
}