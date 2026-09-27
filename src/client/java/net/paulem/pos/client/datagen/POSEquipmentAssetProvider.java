package net.paulem.pos.client.datagen;

import net.minecraft.client.data.models.EquipmentAssetProvider;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.paulem.pos.POS;
import net.paulem.pos.items.armors.POSEquipmentAssets;
import org.jspecify.annotations.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class POSEquipmentAssetProvider extends EquipmentAssetProvider {
    public POSEquipmentAssetProvider(PackOutput output) {
        super(output);
    }

    private static void bootstrap(final BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> consumer) {
        consumer.accept(POSEquipmentAssets.ECHO_STABILIZED_AMETHYST, onlyHumanoid("echo_stabilized_amethyst").build());
    }

    @Override
    public @NonNull CompletableFuture<?> run(@NonNull CachedOutput cache) {
        Map<ResourceKey<EquipmentAsset>, EquipmentClientInfo> equipmentAssets = new HashMap<>();
        bootstrap((id, asset) -> {
            if (equipmentAssets.putIfAbsent(id, asset) != null) {
                throw new IllegalStateException("Tried to register equipment asset twice for id: " + id);
            }
        });
        return DataProvider.saveAll(cache, EquipmentClientInfo.CODEC, this.pathProvider::json, equipmentAssets);
    }

    private static EquipmentClientInfo.Builder onlyHumanoid(final String name) {
        return EquipmentClientInfo.builder().addHumanoidLayers(POS.id(name));
    }

    @Override
    public @NonNull String getName() {
        return "POS Equipment Assets";
    }
}