package com.sammy.malum.datagen.recipe;

import com.sammy.malum.datagen.recipe.builder.*;
import com.sammy.malum.registry.common.MalumSpiritTypes;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.data.recipes.*;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

public class MalumRuneworkingRecipes implements IConditionBuilder {

    protected static void buildRecipes(RecipeOutput recipeOutput) {
        new RunicWorkbenchRecipeBuilder(MalumItems.RUNE_OF_VITALITY.get(), 1)
                .setPrimaryInput(MalumItems.TAINTED_ROCK.get(), 4)
                .setSecondaryInput(MalumSpiritTypes.SACRED_SPIRIT, 16)
                .save(recipeOutput);
        new RunicWorkbenchRecipeBuilder(MalumItems.RUNE_OF_BOLSTERING.get(), 1)
                .setPrimaryInput(MalumItems.NULL_SLATE.get(), 4)
                .setSecondaryInput(MalumSpiritTypes.SACRED_SPIRIT, 16)
                .save(recipeOutput);

        new RunicWorkbenchRecipeBuilder(MalumItems.RUNE_OF_CULLING.get(), 1)
                .setPrimaryInput(MalumItems.TAINTED_ROCK.get(), 4)
                .setSecondaryInput(MalumSpiritTypes.WICKED_SPIRIT, 16)
                .save(recipeOutput);
        new RunicWorkbenchRecipeBuilder(MalumItems.RUNE_OF_SACRIFICIAL_EMPOWERMENT.get(), 1)
                .setPrimaryInput(MalumItems.NULL_SLATE.get(), 4)
                .setSecondaryInput(MalumSpiritTypes.WICKED_SPIRIT, 16)
                .save(recipeOutput);

        new RunicWorkbenchRecipeBuilder(MalumItems.RUNE_OF_REINFORCEMENT.get(), 1)
                .setPrimaryInput(MalumItems.TAINTED_ROCK.get(), 4)
                .setSecondaryInput(MalumSpiritTypes.ARCANE_SPIRIT, 16)
                .save(recipeOutput);
        new RunicWorkbenchRecipeBuilder(MalumItems.RUNE_OF_SPELL_MASTERY.get(), 1)
                .setPrimaryInput(MalumItems.NULL_SLATE.get(), 4)
                .setSecondaryInput(MalumSpiritTypes.ARCANE_SPIRIT, 16)
                .save(recipeOutput);

        new RunicWorkbenchRecipeBuilder(MalumItems.RUNE_OF_VOLATILE_DISTORTION.get(), 1)
                .setPrimaryInput(MalumItems.TAINTED_ROCK.get(), 4)
                .setSecondaryInput(MalumSpiritTypes.ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);
        new RunicWorkbenchRecipeBuilder(MalumItems.RUNE_OF_HERESY.get(), 1)
                .setPrimaryInput(MalumItems.NULL_SLATE.get(), 4)
                .setSecondaryInput(MalumSpiritTypes.ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);

        new RunicWorkbenchRecipeBuilder(MalumItems.RUNE_OF_DEXTERITY.get(), 1)
                .setPrimaryInput(MalumItems.TAINTED_ROCK.get(), 4)
                .setSecondaryInput(MalumSpiritTypes.AERIAL_SPIRIT, 16)
                .save(recipeOutput);
        new RunicWorkbenchRecipeBuilder(MalumItems.RUNE_OF_UNNATURAL_STAMINA.get(), 1)
                .setPrimaryInput(MalumItems.NULL_SLATE.get(), 4)
                .setSecondaryInput(MalumSpiritTypes.AERIAL_SPIRIT, 16)
                .save(recipeOutput);

        new RunicWorkbenchRecipeBuilder(MalumItems.RUNE_OF_AILMENT_CLEANSING.get(), 1)
                .setPrimaryInput(MalumItems.TAINTED_ROCK.get(), 4)
                .setSecondaryInput(MalumSpiritTypes.AQUEOUS_SPIRIT, 16)
                .save(recipeOutput);
        new RunicWorkbenchRecipeBuilder(MalumItems.RUNE_OF_TWINNED_DURATION.get(), 1)
                .setPrimaryInput(MalumItems.NULL_SLATE.get(), 4)
                .setSecondaryInput(MalumSpiritTypes.AQUEOUS_SPIRIT, 16)
                .save(recipeOutput);

        new RunicWorkbenchRecipeBuilder(MalumItems.RUNE_OF_PROTECTION.get(), 1)
                .setPrimaryInput(MalumItems.TAINTED_ROCK.get(), 4)
                .setSecondaryInput(MalumSpiritTypes.EARTHEN_SPIRIT, 16)
                .save(recipeOutput);
        new RunicWorkbenchRecipeBuilder(MalumItems.RUNE_OF_INDOMITABILITY.get(), 1)
                .setPrimaryInput(MalumItems.NULL_SLATE.get(), 4)
                .setSecondaryInput(MalumSpiritTypes.EARTHEN_SPIRIT, 16)
                .save(recipeOutput);

        new RunicWorkbenchRecipeBuilder(MalumItems.RUNE_OF_SCORCHING.get(), 1)
                .setPrimaryInput(MalumItems.TAINTED_ROCK.get(), 4)
                .setSecondaryInput(MalumSpiritTypes.INFERNAL_SPIRIT, 16)
                .save(recipeOutput);
        new RunicWorkbenchRecipeBuilder(MalumItems.RUNE_OF_IGNEOUS_SOLACE.get(), 1)
                .setPrimaryInput(MalumItems.NULL_SLATE.get(), 4)
                .setSecondaryInput(MalumSpiritTypes.INFERNAL_SPIRIT, 16)
                .save(recipeOutput);


        new RunicWorkbenchRecipeBuilder(MalumItems.RUNE_OF_MOTION.get(), 1)
                .setPrimaryInput(MalumItems.RUNEWOOD_PLANKS.get(), 4)
                .setSecondaryInput(MalumSpiritTypes.AERIAL_SPIRIT, 32)
                .save(recipeOutput);
        new RunicWorkbenchRecipeBuilder(MalumItems.RUNE_OF_THE_AETHER.get(), 1)
                .setPrimaryInput(MalumItems.SOULWOOD_PLANKS.get(), 4)
                .setSecondaryInput(MalumSpiritTypes.AERIAL_SPIRIT, 32)
                .save(recipeOutput);

        new RunicWorkbenchRecipeBuilder(MalumItems.RUNE_OF_LOYALTY.get(), 1)
                .setPrimaryInput(MalumItems.RUNEWOOD_PLANKS.get(), 4)
                .setSecondaryInput(MalumSpiritTypes.AQUEOUS_SPIRIT, 32)
                .save(recipeOutput);
        new RunicWorkbenchRecipeBuilder(MalumItems.RUNE_OF_THE_SEAS.get(), 1)
                .setPrimaryInput(MalumItems.SOULWOOD_PLANKS.get(), 4)
                .setSecondaryInput(MalumSpiritTypes.AQUEOUS_SPIRIT, 32)
                .save(recipeOutput);

        new RunicWorkbenchRecipeBuilder(MalumItems.RUNE_OF_WARDING.get(), 1)
                .setPrimaryInput(MalumItems.RUNEWOOD_PLANKS.get(), 4)
                .setSecondaryInput(MalumSpiritTypes.EARTHEN_SPIRIT, 32)
                .save(recipeOutput);
        new RunicWorkbenchRecipeBuilder(MalumItems.RUNE_OF_THE_ARENA.get(), 1)
                .setPrimaryInput(MalumItems.SOULWOOD_PLANKS.get(), 4)
                .setSecondaryInput(MalumSpiritTypes.EARTHEN_SPIRIT, 32)
                .save(recipeOutput);

        new RunicWorkbenchRecipeBuilder(MalumItems.RUNE_OF_HASTE.get(), 1)
                .setPrimaryInput(MalumItems.RUNEWOOD_PLANKS.get(), 4)
                .setSecondaryInput(MalumSpiritTypes.INFERNAL_SPIRIT, 32)
                .save(recipeOutput);
        new RunicWorkbenchRecipeBuilder(MalumItems.RUNE_OF_THE_HELLS.get(), 1)
                .setPrimaryInput(MalumItems.SOULWOOD_PLANKS.get(), 4)
                .setSecondaryInput(MalumSpiritTypes.INFERNAL_SPIRIT, 32)
                .save(recipeOutput);

    }
}
