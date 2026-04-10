package com.sammy.malum.datagen.recipe;

import com.sammy.malum.datagen.recipe.builder.*;
import com.sammy.malum.registry.common.MalumContent;
import com.sammy.malum.registry.common.MalumContent.BlockSets;
import com.sammy.malum.registry.common.item.MalumItemProperties;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;
import net.minecraft.data.recipes.*;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import static com.sammy.malum.registry.common.MalumContent.BlockSets.*;

public class MalumRuneworkingRecipes implements IConditionBuilder {

    protected static void buildRecipes(RecipeOutput recipeOutput) {
        new RunicWorkbenchRecipeBuilder(MalumContent.Gear.RUNE_OF_VITALITY, 1)
                .setPrimaryInput(TAINTED_ROCK_SET.getRock(), 4)
                .setSecondaryInput(MalumSpiritTypes.SACRED_SPIRIT, 16)
                .save(recipeOutput);
        new RunicWorkbenchRecipeBuilder(MalumContent.Gear.RUNE_OF_BOLSTERING, 1)
                .setPrimaryInput(MalumContent.Materials.NULL_SLATE, 4)
                .setSecondaryInput(MalumSpiritTypes.SACRED_SPIRIT, 16)
                .save(recipeOutput);

        new RunicWorkbenchRecipeBuilder(MalumContent.Gear.RUNE_OF_CULLING, 1)
                .setPrimaryInput(TAINTED_ROCK_SET.getRock(), 4)
                .setSecondaryInput(MalumSpiritTypes.WICKED_SPIRIT, 16)
                .save(recipeOutput);
        new RunicWorkbenchRecipeBuilder(MalumContent.Gear.RUNE_OF_RADIAL_EMPOWERMENT, 1)
                .setPrimaryInput(MalumContent.Materials.NULL_SLATE, 4)
                .setSecondaryInput(MalumSpiritTypes.WICKED_SPIRIT, 16)
                .save(recipeOutput);

        new RunicWorkbenchRecipeBuilder(MalumContent.Gear.RUNE_OF_REINFORCEMENT, 1)
                .setPrimaryInput(TAINTED_ROCK_SET.getRock(), 4)
                .setSecondaryInput(MalumSpiritTypes.ARCANE_SPIRIT, 16)
                .save(recipeOutput);
        new RunicWorkbenchRecipeBuilder(MalumContent.Gear.RUNE_OF_SPELL_MASTERY, 1)
                .setPrimaryInput(MalumContent.Materials.NULL_SLATE, 4)
                .setSecondaryInput(MalumSpiritTypes.ARCANE_SPIRIT, 16)
                .save(recipeOutput);

        new RunicWorkbenchRecipeBuilder(MalumContent.Gear.RUNE_OF_VOLATILE_DISTORTION, 1)
                .setPrimaryInput(TAINTED_ROCK_SET.getRock(), 4)
                .setSecondaryInput(MalumSpiritTypes.ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);
        new RunicWorkbenchRecipeBuilder(MalumContent.Gear.RUNE_OF_HERESY, 1)
                .setPrimaryInput(MalumContent.Materials.NULL_SLATE, 4)
                .setSecondaryInput(MalumSpiritTypes.ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);

        new RunicWorkbenchRecipeBuilder(MalumContent.Gear.RUNE_OF_DEXTERITY, 1)
                .setPrimaryInput(TAINTED_ROCK_SET.getRock(), 4)
                .setSecondaryInput(MalumSpiritTypes.AERIAL_SPIRIT, 16)
                .save(recipeOutput);
        new RunicWorkbenchRecipeBuilder(MalumContent.Gear.RUNE_OF_UNNATURAL_STAMINA, 1)
                .setPrimaryInput(MalumContent.Materials.NULL_SLATE, 4)
                .setSecondaryInput(MalumSpiritTypes.AERIAL_SPIRIT, 16)
                .save(recipeOutput);

        new RunicWorkbenchRecipeBuilder(MalumContent.Gear.RUNE_OF_AILMENT_CLEANSING, 1)
                .setPrimaryInput(TAINTED_ROCK_SET.getRock(), 4)
                .setSecondaryInput(MalumSpiritTypes.AQUEOUS_SPIRIT, 16)
                .save(recipeOutput);
        new RunicWorkbenchRecipeBuilder(MalumContent.Gear.RUNE_OF_TWINNED_DURATION, 1)
                .setPrimaryInput(MalumContent.Materials.NULL_SLATE, 4)
                .setSecondaryInput(MalumSpiritTypes.AQUEOUS_SPIRIT, 16)
                .save(recipeOutput);

        new RunicWorkbenchRecipeBuilder(MalumContent.Gear.RUNE_OF_PROTECTION, 1)
                .setPrimaryInput(TAINTED_ROCK_SET.getRock(), 4)
                .setSecondaryInput(MalumSpiritTypes.EARTHEN_SPIRIT, 16)
                .save(recipeOutput);
        new RunicWorkbenchRecipeBuilder(MalumContent.Gear.RUNE_OF_INDOMITABILITY, 1)
                .setPrimaryInput(MalumContent.Materials.NULL_SLATE, 4)
                .setSecondaryInput(MalumSpiritTypes.EARTHEN_SPIRIT, 16)
                .save(recipeOutput);

        new RunicWorkbenchRecipeBuilder(MalumContent.Gear.RUNE_OF_SCORCHING, 1)
                .setPrimaryInput(TAINTED_ROCK_SET.getRock(), 4)
                .setSecondaryInput(MalumSpiritTypes.INFERNAL_SPIRIT, 16)
                .save(recipeOutput);
        new RunicWorkbenchRecipeBuilder(MalumContent.Gear.RUNE_OF_IGNEOUS_SOLACE, 1)
                .setPrimaryInput(MalumContent.Materials.NULL_SLATE, 4)
                .setSecondaryInput(MalumSpiritTypes.INFERNAL_SPIRIT, 16)
                .save(recipeOutput);


        new RunicWorkbenchRecipeBuilder(MalumContent.Gear.RUNE_OF_HOWLING_GALE, 1)
                .setPrimaryInput(RUNEWOOD_SET.getPlanks(), 4)
                .setSecondaryInput(MalumSpiritTypes.AERIAL_SPIRIT, 32)
                .save(recipeOutput);
        new RunicWorkbenchRecipeBuilder(MalumContent.Gear.RUNE_OF_SKY_TETHER, 1)
                .setPrimaryInput(SOULWOOD_SET.getPlanks(), 4)
                .setSecondaryInput(MalumSpiritTypes.AERIAL_SPIRIT, 32)
                .save(recipeOutput);

        new RunicWorkbenchRecipeBuilder(MalumContent.Gear.RUNE_OF_FLOWING_GRASP, 1)
                .setPrimaryInput(RUNEWOOD_SET.getPlanks(), 4)
                .setSecondaryInput(MalumSpiritTypes.AQUEOUS_SPIRIT, 32)
                .save(recipeOutput);
        new RunicWorkbenchRecipeBuilder(MalumContent.Gear.RUNE_OF_GOOD_TIDES, 1)
                .setPrimaryInput(SOULWOOD_SET.getPlanks(), 4)
                .setSecondaryInput(MalumSpiritTypes.AQUEOUS_SPIRIT, 32)
                .save(recipeOutput);

        new RunicWorkbenchRecipeBuilder(MalumContent.Gear.RUNE_OF_STONE_WARD, 1)
                .setPrimaryInput(RUNEWOOD_SET.getPlanks(), 4)
                .setSecondaryInput(MalumSpiritTypes.EARTHEN_SPIRIT, 32)
                .save(recipeOutput);
        new RunicWorkbenchRecipeBuilder(MalumContent.Gear.RUNE_OF_OAKEN_MIGHT, 1)
                .setPrimaryInput(SOULWOOD_SET.getPlanks(), 4)
                .setSecondaryInput(MalumSpiritTypes.EARTHEN_SPIRIT, 32)
                .save(recipeOutput);

        new RunicWorkbenchRecipeBuilder(MalumContent.Gear.RUNE_OF_BURNING_FERVOR, 1)
                .setPrimaryInput(RUNEWOOD_SET.getPlanks(), 4)
                .setSecondaryInput(MalumSpiritTypes.INFERNAL_SPIRIT, 32)
                .save(recipeOutput);
        new RunicWorkbenchRecipeBuilder(MalumContent.Gear.RUNE_OF_FIERY_EMBRACE, 1)
                .setPrimaryInput(SOULWOOD_SET.getPlanks(), 4)
                .setSecondaryInput(MalumSpiritTypes.INFERNAL_SPIRIT, 32)
                .save(recipeOutput);

    }
}
