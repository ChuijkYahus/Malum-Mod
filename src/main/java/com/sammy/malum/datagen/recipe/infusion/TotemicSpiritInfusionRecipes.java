package com.sammy.malum.datagen.recipe.infusion;

import com.sammy.malum.datagen.recipe.builder.*;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.data.recipes.*;

import static com.sammy.malum.registry.common.MalumSpiritTypes.*;

public class TotemicSpiritInfusionRecipes {

    public static void buildRecipes(RecipeOutput recipeOutput) {
        new SpiritInfusionRecipeBuilder(MalumItems.RUNEWOOD_LOG.get(), 4, MalumItems.RUNEWOOD_TOTEM_BASE.get(), 4)
                .addExtraItem(MalumItems.RUNEWOOD_PLANKS.get(), 6)
                .addExtraItem(MalumItems.HEX_ASH.get(), 2)
                .addSpirit(AERIAL_SPIRIT, 2)
                .addSpirit(AQUEOUS_SPIRIT, 2)
                .addSpirit(EARTHEN_SPIRIT, 2)
                .addSpirit(INFERNAL_SPIRIT, 2)
                .addSpirit(ELDRITCH_SPIRIT, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.SOULWOOD_LOG.get(), 4, MalumItems.SOULWOOD_TOTEM_BASE.get(), 4)
                .addExtraItem(MalumItems.SOULWOOD_PLANKS.get(), 6)
                .addExtraItem(MalumItems.HEX_ASH.get(), 2)
                .addSpirit(AERIAL_SPIRIT, 2)
                .addSpirit(AQUEOUS_SPIRIT, 2)
                .addSpirit(EARTHEN_SPIRIT, 2)
                .addSpirit(INFERNAL_SPIRIT, 2)
                .addSpirit(ELDRITCH_SPIRIT, 2)
                .save(recipeOutput);
    }
}
