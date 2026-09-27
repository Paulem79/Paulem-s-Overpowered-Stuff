package net.paulem.pos.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.triggers.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.paulem.pos.POS;
import net.paulem.pos.items.POSItems;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class POSAdvancementProvider extends FabricAdvancementProvider {
    public POSAdvancementProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(HolderLookup.@NonNull Provider registryLookup, @NonNull Consumer<AdvancementHolder> consumer) {
        AdvancementHolder getEchoStabilizedAmethyst = Advancement.Builder.advancement()
                .rootDisplay(
                        POSItems.ECHO_STABILIZED_AMETHYST, // The display icon
                        Component.literal("It’s a Long Way to the Top..."), // The title
                        Component.literal("...If You Wanna Craft ’n’ Roll! Make your first echo stabilized amethyst!"), // The description
                        Identifier.withDefaultNamespace("gui/advancements/backgrounds/adventure"),
                        AdvancementType.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                // the name referenced by other advancements when they want to have "requirements."
                .addCriterion("got_echo_stabilized_amethyst", InventoryChangeTrigger.TriggerInstance.hasItems(POSItems.ECHO_STABILIZED_AMETHYST))
                // Give the advancement an id
                .save(consumer, POS.id("get_echo_stabilized_amethyst"));
    }
}
