package com.sammy.malum.datagen.recipe;

import com.sammy.malum.common.block.soulstone.SoulstoneBudBlock;
import com.sammy.malum.common.data.component.soulstone.StoredInSoulstoneMetal;
import com.sammy.malum.common.recipe.derealization.ConjunctureCrystallariumRecipe;
import com.sammy.malum.common.recipe.derealization.CrystalPropertyModifier;
import com.sammy.malum.common.recipe.derealization.MalumSizedChanceResult;
import com.sammy.malum.datagen.recipe.builder.ConjunctureCrystallariumRecipeBuilder;
import com.sammy.malum.datagen.recipe.builder.OreDerealizationRecipeBuilder;
import com.sammy.malum.registry.common.MalumContent;
import dev.latvian.mods.kubejs.level.ruletest.AllMatchRuleTest;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import vectorwing.farmersdelight.common.registry.ModBlocks;

import java.util.List;

public class MalumConjunctureCrystallariumRecipes implements IConditionBuilder {

    protected static void buildRecipes(RecipeOutput recipeOutput) { //TODO a lot here can later be done better in MalumMetallicsDatagen
        new OreDerealizationRecipeBuilder(
                new TagMatchTest(Tags.Blocks.ORES_IRON),
                MalumContent.AlchemyAndMetallics.IRON_METALLICS.getOre().getDefaultState(),
                CrystalPropertyModifier.DEFAULT,
                new StoredInSoulstoneMetal(MalumContent.AlchemyAndMetallics.IRON_METALLICS.getId(), MalumContent.AlchemyAndMetallics.IRON_METALLICS.getNuggetTag())
        ).save(recipeOutput);

        new OreDerealizationRecipeBuilder(
                new TagMatchTest(Tags.Blocks.ORES_COPPER),
                MalumContent.AlchemyAndMetallics.COPPER_METALLICS.getOre().getDefaultState(),
                CrystalPropertyModifier.DEFAULT,
                new StoredInSoulstoneMetal(MalumContent.AlchemyAndMetallics.COPPER_METALLICS.getId(), MalumContent.AlchemyAndMetallics.COPPER_METALLICS.getNuggetTag())
        ).save(recipeOutput);

        new ConjunctureCrystallariumRecipeBuilder(
                Ingredient.of(Items.RAW_IRON),
                CrystalPropertyModifier.DEFAULT,
                new MalumSizedChanceResult(MalumContent.AlchemyAndMetallics.IRON_METALLICS.getDerealizedMetal().toStack(), 1.0F),
                new StoredInSoulstoneMetal(MalumContent.AlchemyAndMetallics.IRON_METALLICS.getId(), MalumContent.AlchemyAndMetallics.IRON_METALLICS.getNuggetTag()),
                200
        ).addAdditionalResult(Items.BONE_MEAL).save(recipeOutput);
    }
}
