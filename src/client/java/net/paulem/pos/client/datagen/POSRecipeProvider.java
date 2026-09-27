package net.paulem.pos.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.paulem.pos.items.POSItems;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class POSRecipeProvider extends FabricRecipeProvider {
    public POSRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected @NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider registries, @NonNull BootstrapContext<Recipe<?>> recipes, @NonNull BootstrapContext<Advancement> advancements) {
        return new RecipeProvider(recipes, advancements) {
            @Override
            public void buildRecipes() {
                shaped(RecipeCategory.MISC, POSItems.ECHO_STABILIZED_AMETHYST)
                        .pattern("eae")
                        .pattern("ada")
                        .pattern("eae")
                        .define('a', Items.AMETHYST_SHARD)
                        .define('e', Items.ECHO_SHARD)
                        .define('d', Items.DIAMOND)
                        .group("pos") // groups are shown in one slot in the recipe book
                        .unlockedBy(getHasName(Items.ECHO_SHARD), has(Items.ECHO_SHARD))
                        .save(output);
            }
        };
    }

    @Override
    public @NonNull String getName() {
        return "POSRecipeProvider";
    }
}