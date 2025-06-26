package com.sammy.malum.datagen.recipe.builder;

import com.sammy.malum.common.recipe.spirit_repair.*;
import com.sammy.malum.core.systems.recipe.SpiritIngredient;
import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.spirit.type.*;
import net.minecraft.advancements.*;
import net.minecraft.core.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import team.lodestar.lodestone.recipe.builder.LodestoneRecipeBuilder;

import java.util.*;

@SuppressWarnings("deprecation")
public class SpiritRepairRecipeBuilder implements LodestoneRecipeBuilder<SpiritRepairRecipe> {

    public final List<Holder<Item>> validItems = new ArrayList<>();
    public final List<SpiritIngredient> spirits = new ArrayList<>();
    public final SizedIngredient repairMaterial;
    public final float repairEfficiency;

    public SpiritRepairRegexData regex = SpiritRepairRegexData.EMPTY;

    public Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

    public SpiritRepairRecipeBuilder(SizedIngredient repairMaterial, float repairEfficiency) {
        this.repairMaterial = repairMaterial;
        this.repairEfficiency = repairEfficiency;
    }

    public SpiritRepairRecipeBuilder withValidItem(Item item) {
        validItems.add(item.builtInRegistryHolder());
        return this;
    }

    public SpiritRepairRecipeBuilder addSpirit(SpiritHolder<MalumSpiritType> spirit, int count) {
        spirits.add(new SpiritIngredient(spirit, count));
        return this;
    }

    public SpiritRepairRecipeBuilder withRegex(SpiritRepairRegexData regex) {
        this.regex = regex;
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


        return new SpiritRepairRecipe(validItems, spirits, repairMaterial, repairEfficiency, regex);
    }

    @Override
    public String getRecipeSubfolder() {
        return "spirit_repair";
    }
}