package com.sammy.malum.datagen.recipe.builder;

import com.sammy.malum.common.recipe.SpiritRepairRecipe;
import com.sammy.malum.core.systems.recipe.SpiritIngredient;
import com.sammy.malum.core.systems.spirit.type.*;
import net.minecraft.advancements.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import team.lodestar.lodestone.recipe.builder.LodestoneRecipeBuilder;

import java.util.*;

public class SpiritRepairRecipeBuilder implements LodestoneRecipeBuilder<SpiritRepairRecipe> {

    public final String itemIdRegex;
    public final String modIdRegex;
    public final float durabilityPercentage;
    public final List<Item> inputs = new ArrayList<>();
    public final SizedIngredient repairMaterial;
    public final List<SpiritIngredient> spirits = new ArrayList<>();
    public Item repairOutputOverride = Items.AIR;
    public Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

    public SpiritRepairRecipeBuilder(String itemIdRegex, String modIdRegex, float durabilityPercentage, SizedIngredient repairMaterial) {
        this.itemIdRegex = itemIdRegex;
        this.modIdRegex = modIdRegex;
        this.durabilityPercentage = durabilityPercentage;
        this.repairMaterial = repairMaterial;
    }

    public SpiritRepairRecipeBuilder(String itemIdRegex, float durabilityPercentage, Ingredient repairMaterial, int repairMaterialCount) {
        this(itemIdRegex, "", durabilityPercentage, new SizedIngredient(repairMaterial, repairMaterialCount));
    }

    public SpiritRepairRecipeBuilder(float durabilityPercentage, Ingredient repairMaterial, int repairMaterialCount) {
        this("", "", durabilityPercentage, new SizedIngredient(repairMaterial, repairMaterialCount));
    }

    public SpiritRepairRecipeBuilder withValidItem(Item item) {
        inputs.add(item);
        return this;
    }

    public SpiritRepairRecipeBuilder addSpirit(SpiritWrapper type, int count) {
        spirits.add(new SpiritIngredient(type, count));
        return this;
    }

    public SpiritRepairRecipeBuilder overrideOutput(Item repairOutputOverride) {
        this.repairOutputOverride = repairOutputOverride;
        return this;
    }

    public SpiritRepairRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        criteria.put(name, criterion);
        return this;
    }

    @Override
    public void tweakAdvancement(Advancement.Builder advancement) {
        criteria.forEach(advancement::addCriterion);
    }

    @Override
    public SpiritRepairRecipe buildRecipe(ResourceLocation resourceLocation) {
        List<ResourceLocation> inputIds = new ArrayList<>();
        for (Item input : inputs) {
            inputIds.add(BuiltInRegistries.ITEM.getKey(input));
        }
        return new SpiritRepairRecipe(durabilityPercentage, itemIdRegex, modIdRegex, inputIds, repairMaterial, spirits, repairOutputOverride);
    }

    @Override
    public String getRecipeSubfolder() {
        return "spirit_repair";
    }
}
