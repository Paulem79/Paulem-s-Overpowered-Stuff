package net.paulem.pos.items.armors;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.TooltipDisplay;
import net.paulem.pos.energy.EnergyReferences;
import org.jspecify.annotations.NonNull;
import team.reborn.energy.api.base.SimpleEnergyItem;

import java.util.function.Consumer;

public class POSEnergyArmorItem extends Item implements SimpleEnergyItem {
    public POSEnergyArmorItem(Properties properties) {
        super(properties);
    }

    @Override
    public long getEnergyCapacity(ItemStack stack) {
        return EnergyReferences.coalToEnergy(1);
    }

    @Override
    public long getEnergyMaxInput(ItemStack stack) {
        return getEnergyCapacity(stack);
    }

    @Override
    public long getEnergyMaxOutput(ItemStack stack) {
        return 0;
    }

    /**
     * Active ou désactive les attributs selon l'énergie disponible.
     * @return true si le composant d'attributs a été modifié.
     */
    public boolean updateAttributes(ItemStack stack) {
        long energy = getStoredEnergy(stack);
        ItemAttributeModifiers current = stack.get(DataComponents.ATTRIBUTE_MODIFIERS);

        // Default attributes
        ItemAttributeModifiers defaultModifiers = stack.getItem().components()
                .getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY);

        if (energy <= 0) {
            // If empty, then empty attributes
            if (current == null || !current.modifiers().isEmpty()) {
                stack.set(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY);
                return true;
            }
        } else {
            // If reloaded, then default attributes
            if (current != null && current.modifiers().isEmpty()) {
                stack.set(DataComponents.ATTRIBUTE_MODIFIERS, defaultModifiers);
                return true;
            }
        }
        return false;
    }

    @Override
    public void appendHoverText(@NonNull ItemStack itemStack, @NonNull TooltipContext context, @NonNull TooltipDisplay display, Consumer<Component> textConsumer, @NonNull TooltipFlag tooltipFlag) {
        long energy = getStoredEnergy(itemStack);
        textConsumer.accept(Component.literal("Energy: " + energy + "/" + getEnergyCapacity(itemStack)).withStyle(ChatFormatting.GOLD));
        super.appendHoverText(itemStack, context, display, textConsumer, tooltipFlag);
    }

    @Override
    public boolean isBarVisible(@NonNull ItemStack stack) {
        return true;
    }

    @Override
    public int getBarWidth(@NonNull ItemStack stack) {
        long capacity = getEnergyCapacity(stack);
        if (capacity <= 0) {
            return 0;
        }

        double ratio = (double) getStoredEnergy(stack) / capacity;
        return Math.clamp((int) Math.round(ratio * 13), 0, 13);
    }

    @Override
    public int getBarColor(@NonNull ItemStack stack) {
        return 0x00E5FF;
    }
}