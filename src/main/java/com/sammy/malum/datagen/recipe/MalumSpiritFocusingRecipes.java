package com.sammy.malum.datagen.recipe;

import com.sammy.malum.datagen.recipe.builder.*;
import com.sammy.malum.registry.common.MalumContent.Artifice;
import com.sammy.malum.registry.common.MalumContent.Materials;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class MalumSpiritFocusingRecipes implements IConditionBuilder {

    protected static void buildRecipes(RecipeOutput recipeOutput) {
        int complexDuration = 2700;
        int metalDuration = 900;
        int shortDuration = 300;

        new SpiritFocusingRecipeBuilder(shortDuration, 1, Artifice.ALCHEMICAL_IMPETUS, Items.GUNPOWDER, 8)
                .addSpirit(EARTHEN_SPIRIT, 1)
                .save(recipeOutput);

        new SpiritFocusingRecipeBuilder(shortDuration, 1, Artifice.ALCHEMICAL_IMPETUS, Items.GLOWSTONE_DUST, 8)
                .addSpirit(INFERNAL_SPIRIT, 1)
                .save(recipeOutput);

        new SpiritFocusingRecipeBuilder(shortDuration, 1, Artifice.ALCHEMICAL_IMPETUS, Items.REDSTONE, 8)
                .addSpirit(ARCANE_SPIRIT, 1)
                .save(recipeOutput);

        new SpiritFocusingRecipeBuilder(shortDuration, 1, Artifice.ALCHEMICAL_IMPETUS, Items.QUARTZ, 4)
                .addSpirit(EARTHEN_SPIRIT, 2)
                .addSpirit(ARCANE_SPIRIT, 2)
                .save(recipeOutput);

        new SpiritFocusingRecipeBuilder(shortDuration, 1, Artifice.ALCHEMICAL_IMPETUS, Materials.BLAZING_QUARTZ, 4)
                .addSpirit(INFERNAL_SPIRIT, 2)
                .addSpirit(ARCANE_SPIRIT, 2)
                .save(recipeOutput);

        new SpiritFocusingRecipeBuilder(shortDuration, 1, Artifice.ALCHEMICAL_IMPETUS, Items.PRISMARINE_SHARD, 4)
                .addSpirit(AQUEOUS_SPIRIT, 2)
                .addSpirit(ARCANE_SPIRIT, 2)
                .save(recipeOutput);

        new SpiritFocusingRecipeBuilder(shortDuration, 1, Artifice.ALCHEMICAL_IMPETUS, Items.AMETHYST_SHARD, 4)
                .addSpirit(AERIAL_SPIRIT, 2)
                .addSpirit(ARCANE_SPIRIT, 2)
                .save(recipeOutput);

        new SpiritFocusingRecipeBuilder(complexDuration, 1, Artifice.ZEPHYR_IMPETUS, Items.WIND_CHARGE, 4)
                .addSpirit(AERIAL_SPIRIT, 2)
                .addSpirit(ARCANE_SPIRIT, 2)
                .addSpirit(SACRED_SPIRIT, 2)
                .save(recipeOutput);

        new SpiritFocusingRecipeBuilder(complexDuration, 1, Artifice.ZEPHYR_IMPETUS, Materials.WIND_NUCLEUS.get(), 4)
                .addSpirit(AERIAL_SPIRIT, 2)
                .addSpirit(ARCANE_SPIRIT, 2)
                .addSpirit(WICKED_SPIRIT, 2)
                .save(recipeOutput);

        new SpiritFocusingRecipeBuilder(complexDuration, 1, Artifice.IFRIT_IMPETUS, Items.BLAZE_POWDER, 4)
                .addSpirit(INFERNAL_SPIRIT, 2)
                .addSpirit(ARCANE_SPIRIT, 2)
                .addSpirit(SACRED_SPIRIT, 2)
                .save(recipeOutput);

        new SpiritFocusingRecipeBuilder(complexDuration, 1, Artifice.IFRIT_IMPETUS, Materials.PYRE_NUCLEUS.get(), 4)
                .addSpirit(INFERNAL_SPIRIT, 2)
                .addSpirit(ARCANE_SPIRIT, 2)
                .addSpirit(WICKED_SPIRIT, 2)
                .save(recipeOutput);
    }
}