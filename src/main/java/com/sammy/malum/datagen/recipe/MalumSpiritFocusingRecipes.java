package com.sammy.malum.datagen.recipe;

import com.sammy.malum.*;
import com.sammy.malum.datagen.recipe.builder.*;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.neoforged.neoforge.common.conditions.NotCondition;
import net.neoforged.neoforge.common.conditions.TagEmptyCondition;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;
import static team.lodestar.lodestone.registry.common.tag.LodestoneItemTags.*;

public class MalumSpiritFocusingRecipes implements IConditionBuilder {

    protected static void buildRecipes(RecipeOutput recipeOutput) {
        int complexDuration = 2700;
        int metalDuration = 900;
        int shortDuration = 300;

        new SpiritFocusingRecipeBuilder(shortDuration, 1, MalumItems.ALCHEMICAL_IMPETUS, Items.GUNPOWDER, 8)
                .addSpirit(EARTHEN_SPIRIT, 1)
                .save(recipeOutput);

        new SpiritFocusingRecipeBuilder(shortDuration, 1, MalumItems.ALCHEMICAL_IMPETUS, Items.GLOWSTONE_DUST, 8)
                .addSpirit(INFERNAL_SPIRIT, 1)
                .save(recipeOutput);

        new SpiritFocusingRecipeBuilder(shortDuration, 1, MalumItems.ALCHEMICAL_IMPETUS, Items.REDSTONE, 8)
                .addSpirit(ARCANE_SPIRIT, 1)
                .save(recipeOutput);

        new SpiritFocusingRecipeBuilder(shortDuration, 1, MalumItems.ALCHEMICAL_IMPETUS, Items.QUARTZ, 4)
                .addSpirit(EARTHEN_SPIRIT, 2)
                .addSpirit(ARCANE_SPIRIT, 2)
                .save(recipeOutput);

        new SpiritFocusingRecipeBuilder(shortDuration, 1, MalumItems.ALCHEMICAL_IMPETUS, MalumItems.BLAZING_QUARTZ.get(), 4)
                .addSpirit(INFERNAL_SPIRIT, 2)
                .addSpirit(ARCANE_SPIRIT, 2)
                .save(recipeOutput);

        new SpiritFocusingRecipeBuilder(shortDuration, 1, MalumItems.ALCHEMICAL_IMPETUS, Items.PRISMARINE_SHARD, 4)
                .addSpirit(AQUEOUS_SPIRIT, 2)
                .addSpirit(ARCANE_SPIRIT, 2)
                .save(recipeOutput);

        new SpiritFocusingRecipeBuilder(shortDuration, 1, MalumItems.ALCHEMICAL_IMPETUS, Items.AMETHYST_SHARD, 4)
                .addSpirit(AERIAL_SPIRIT, 2)
                .addSpirit(ARCANE_SPIRIT, 2)
                .save(recipeOutput);

        new SpiritFocusingRecipeBuilder(complexDuration, 1, MalumItems.ZEPHYR_IMPETUS, Items.WIND_CHARGE, 4)
                .addSpirit(AERIAL_SPIRIT, 2)
                .addSpirit(ARCANE_SPIRIT, 2)
                .addSpirit(SACRED_SPIRIT, 2)
                .save(recipeOutput);

        new SpiritFocusingRecipeBuilder(complexDuration, 1, MalumItems.ZEPHYR_IMPETUS, MalumItems.WIND_NUCLEUS.get(), 4)
                .addSpirit(AERIAL_SPIRIT, 2)
                .addSpirit(ARCANE_SPIRIT, 2)
                .addSpirit(WICKED_SPIRIT, 2)
                .save(recipeOutput);

        addImpetusRecipes(recipeOutput, metalDuration, MalumItems.IRON_IMPETUS, MalumItems.IRON_NODE);
        addImpetusRecipes(recipeOutput, metalDuration, MalumItems.GOLD_IMPETUS, MalumItems.GOLD_NODE);
        addImpetusRecipes(recipeOutput, metalDuration, MalumItems.COPPER_IMPETUS, MalumItems.COPPER_NODE);
        addImpetusRecipes(recipeOutput, metalDuration, MalumItems.LEAD_IMPETUS, MalumItems.LEAD_NODE, NUGGETS_LEAD);
        addImpetusRecipes(recipeOutput, metalDuration, MalumItems.SILVER_IMPETUS, MalumItems.SILVER_NODE, NUGGETS_SILVER);
        addImpetusRecipes(recipeOutput, metalDuration, MalumItems.ALUMINUM_IMPETUS, MalumItems.ALUMINUM_NODE, NUGGETS_ALUMINUM);
        addImpetusRecipes(recipeOutput, metalDuration, MalumItems.NICKEL_IMPETUS, MalumItems.NICKEL_NODE, NUGGETS_NICKEL);
        addImpetusRecipes(recipeOutput, metalDuration, MalumItems.URANIUM_IMPETUS, MalumItems.URANIUM_NODE, NUGGETS_URANIUM);
        addImpetusRecipes(recipeOutput, metalDuration, MalumItems.COBALT_IMPETUS, MalumItems.COBALT_NODE, NUGGETS_COBALT);
        addImpetusRecipes(recipeOutput, metalDuration, MalumItems.OSMIUM_IMPETUS, MalumItems.OSMIUM_NODE, NUGGETS_OSMIUM);
        addImpetusRecipes(recipeOutput, metalDuration, MalumItems.ZINC_IMPETUS, MalumItems.ZINC_NODE, NUGGETS_ZINC);
        addImpetusRecipes(recipeOutput, metalDuration, MalumItems.TIN_IMPETUS, MalumItems.TIN_NODE, NUGGETS_TIN);
    }

    public static void addImpetusRecipes(RecipeOutput recipeOutput, int duration, Holder<Item> impetus, Holder<Item> node) {
        var recipeName = MalumMod.malumPath("node_focusing_" + BuiltInRegistries.ITEM.getKey(node.value()).getPath().replace("_node", ""));
        new SpiritFocusingRecipeBuilder(duration, 2, Ingredient.of(impetus.value()), node.value(), 3)
                .addSpirit(EARTHEN_SPIRIT, 2)
                .addSpirit(INFERNAL_SPIRIT, 2)
                .save(recipeOutput, recipeName);
    }

    public static void addImpetusRecipes(RecipeOutput recipeOutput, int duration, Holder<Item> impetus, Holder<Item> node, TagKey<Item> nugget) {
        var recipeName = MalumMod.malumPath("node_focusing_" + nugget.location().getPath().replace("nuggets/", ""));
        new SpiritFocusingRecipeBuilder(duration, 2, Ingredient.of(impetus.value()), node.value(), 3)
                .addSpirit(EARTHEN_SPIRIT, 2)
                .addSpirit(INFERNAL_SPIRIT, 2)
                .save(recipeOutput.withConditions(
                        new NotCondition(
                                new TagEmptyCondition(nugget.location())
                        )), recipeName);
    }
}