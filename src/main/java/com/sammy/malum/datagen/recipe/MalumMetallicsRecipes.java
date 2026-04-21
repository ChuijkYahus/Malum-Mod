package com.sammy.malum.datagen.recipe;

import com.mojang.datafixers.util.Pair;
import com.sammy.malum.MalumMod;
import com.sammy.malum.registry.common.util.MetallicsItemRegistryBundle;
import com.sammy.malum.datagen.recipe.builder.SpiritFocusingRecipeBuilder;
import com.sammy.malum.datagen.recipe.builder.SpiritInfusionRecipeBuilder;
import com.sammy.malum.datagen.recipe.builder.SpiritRepairRecipeBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.neoforged.neoforge.common.conditions.NotCondition;
import net.neoforged.neoforge.common.conditions.TagEmptyCondition;
import net.neoforged.neoforge.common.crafting.SizedIngredient;

import static com.sammy.malum.registry.common.MalumContent.AlchemyAndMetallics.*;
import static com.sammy.malum.registry.common.MalumContent.Materials.*;
import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;
import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;
import static net.minecraft.data.recipes.ShapelessRecipeBuilder.shapeless;
import static net.minecraft.data.recipes.SimpleCookingRecipeBuilder.blasting;
import static net.minecraft.data.recipes.SimpleCookingRecipeBuilder.smelting;

public class MalumMetallicsRecipes implements IConditionBuilder {

    public static void buildMetallicsRecipes(RecipeOutput output, MetallicsItemRegistryBundle bundle) {
        var id = bundle.getId();
        var node = bundle.getNode();
        Pair<String, Criterion<?>> hasNode = Pair.of("has_node", RecipeDatagenCommons.has(node));
        var derealizedMetal = bundle.getDerealizedMetal();
        Pair<String, Criterion<?>> hasDerealizedMetal = Pair.of("has_derealized_metal", RecipeDatagenCommons.has(derealizedMetal));
        var harmonizedMetal = bundle.getHarmonizedMetal();
        Pair<String, Criterion<?>> hasHarmonizedMetal = Pair.of("has_harmonized_metal", RecipeDatagenCommons.has(harmonizedMetal));

        TagKey<Item> nuggetTag = bundle.getNuggetTag();
        var nugget = Ingredient.of(nuggetTag);
        TagKey<Item> ingotTag = bundle.getIngotTag();
        var conditional = output.withConditions(
                new NotCondition(new TagEmptyCondition(nuggetTag.location())),
                new NotCondition(new TagEmptyCondition(ingotTag.location())));

        //Impetus
        Item impetus = bundle.getImpetus().get();
        new SpiritInfusionRecipeBuilder(ALCHEMICAL_IMPETUS, impetus, 1)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .addSpirit(INFERNAL_SPIRIT, 8)
                .addSpirit(AQUEOUS_SPIRIT, 8)
                .addExtraItem(SizedIngredient.of(Tags.Items.GUNPOWDERS, 6))
                .addExtraItem(SizedIngredient.of(CTHONIC_GOLD, 1))
                .addExtraItem(SizedIngredient.of(ingotTag, 6))
                .save(conditional);

        //Impetus Repair
        new SpiritRepairRecipeBuilder(SizedIngredient.of(CTHONIC_GOLD_FRAGMENT, 2), 1f)
                .withValidItem(bundle.getFracturedImpetus().value())
                .addSpirit(AQUEOUS_SPIRIT, 8)
                .addSpirit(INFERNAL_SPIRIT, 8)
                .unlockedBy("has_impetus", RecipeDatagenCommons.has(impetus))
                .save(conditional, MalumMod.malumPath(id + "_impetus_repair"));

        //Node Focusing
        new SpiritFocusingRecipeBuilder(900, 2, Ingredient.of(impetus), node, 3)
                .addSpirit(EARTHEN_SPIRIT, 2)
                .addSpirit(INFERNAL_SPIRIT, 2)
                .save(conditional, MalumMod.malumPath(id + "_node_focusing"));

        //Node -> 6x Nugget
        RecipeDatagenCommons.smeltAndBlast(conditional, MalumMod.malumPath(id + "_node"),
                Ingredient.of(node), RecipeCategory.MISC, hasNode, nugget, 6, 0.5f);

        //Derealized Metal -> 6x Nugget
        RecipeDatagenCommons.smeltAndBlast(conditional, MalumMod.malumPath(id + "derealized_metal"),
                Ingredient.of(derealizedMetal), RecipeCategory.MISC, hasDerealizedMetal, nugget, 6, 0.5f);

        //Harmonized Metal -> 3x Node
        RecipeDatagenCommons.smeltAndBlast(conditional, MalumMod.malumPath(id + "harmonized_metal"),
                Ingredient.of(harmonizedMetal), RecipeCategory.MISC, hasHarmonizedMetal, node, 3, 2f);

        //Metal Blocks
        RecipeDatagenCommons.blockIngotExchange(output, bundle.getDerealizedMetal(), bundle.getDerealizedStorageBlock());
        RecipeDatagenCommons.blockIngotExchange(output, bundle.getHarmonizedMetal(), bundle.getHarmonizedStorageBlock());

    }
}
