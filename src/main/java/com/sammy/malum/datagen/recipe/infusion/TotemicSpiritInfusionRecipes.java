package com.sammy.malum.datagen.recipe.infusion;

import com.sammy.malum.datagen.recipe.builder.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.MalumContent;
import net.minecraft.data.recipes.*;
import net.neoforged.neoforge.common.crafting.*;

import static com.sammy.malum.registry.common.MalumContent.BlockSets.*;
import static com.sammy.malum.registry.common.MalumContent.Progression.*;
import static com.sammy.malum.registry.common.MalumContent.Totemancy.*;
import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class TotemicSpiritInfusionRecipes {

    public static void buildRecipes(RecipeOutput recipeOutput) {
        new SpiritInfusionRecipeBuilder(RUNEWOOD_SET.getLog(), 4, RUNEWOOD_TOTEM_BASE, 4)
                .addExtraItem(RUNEWOOD_SET.getPlanks(), 6)
                .addExtraItem(MalumContent.Materials.HEX_ASH, 2)
                .addSpirit(AERIAL_SPIRIT, 2)
                .addSpirit(AQUEOUS_SPIRIT, 2)
                .addSpirit(EARTHEN_SPIRIT, 2)
                .addSpirit(INFERNAL_SPIRIT, 2)
                .addSpirit(ELDRITCH_SPIRIT, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(SOULWOOD_SET.getPlanks(), 4, SOULWOOD_TOTEM_BASE, 4)
                .addExtraItem(SOULWOOD_SET.getPlanks(), 6)
                .addExtraItem(MalumContent.Materials.HEX_ASH, 2)
                .addSpirit(AERIAL_SPIRIT, 2)
                .addSpirit(AQUEOUS_SPIRIT, 2)
                .addSpirit(EARTHEN_SPIRIT, 2)
                .addSpirit(INFERNAL_SPIRIT, 2)
                .addSpirit(ELDRITCH_SPIRIT, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(net.minecraft.world.item.Items.COPPER_BLOCK, 4, WAVEFORM_RUNEWOOD_TOTEM_BASE, 4)
                .addExtraItem(net.minecraft.world.item.Items.REDSTONE, 8)
                .addExtraItem(SizedIngredient.of(MalumTags.Items.RUNEWOOD_PLANKS, 6))
                .addExtraItem(RUNEWOOD_SET.getPlanks(), 4)
                .addExtraItem(ETHER, 2)
                .addSpirit(AERIAL_SPIRIT, 2)
                .addSpirit(AQUEOUS_SPIRIT, 2)
                .addSpirit(EARTHEN_SPIRIT, 2)
                .addSpirit(INFERNAL_SPIRIT, 2)
                .addSpirit(ARCANE_SPIRIT, 2)
                .addSpirit(ELDRITCH_SPIRIT, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(net.minecraft.world.item.Items.COPPER_BLOCK, 4, WAVEFORM_SOULWOOD_TOTEM_BASE, 4)
                .addExtraItem(net.minecraft.world.item.Items.REDSTONE, 8)
                .addExtraItem(SizedIngredient.of(MalumTags.Items.SOULWOOD_PLANKS, 6))
                .addExtraItem(SOULWOOD_SET.getPlanks(), 4)
                .addExtraItem(ETHER, 2)
                .addSpirit(AERIAL_SPIRIT, 2)
                .addSpirit(AQUEOUS_SPIRIT, 2)
                .addSpirit(EARTHEN_SPIRIT, 2)
                .addSpirit(INFERNAL_SPIRIT, 2)
                .addSpirit(ARCANE_SPIRIT, 2)
                .addSpirit(ELDRITCH_SPIRIT, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(RUNEWOOD_OBELISK, 1, ARCANA_PYLON, 1)
                .addExtraItem(MalumContent.Materials.REFINED_SOULSTONE, 8)
                .addExtraItem(MalumContent.Materials.HEX_ASH, 4)
                .addExtraItem(SOULWOOD_SET.getPlanks(), 2)
                .addSpirit(AERIAL_SPIRIT, 8)
                .addSpirit(AQUEOUS_SPIRIT, 8)
                .addSpirit(EARTHEN_SPIRIT, 8)
                .addSpirit(INFERNAL_SPIRIT, 8)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(RUNEWOOD_SET.getPlanks(), 4, RITE_ANCHOR, 4)
                .addExtraItem(MalumContent.Materials.REFINED_SOULSTONE, 8)
                .addExtraItem(MalumContent.Materials.HEX_ASH, 4)
                .addExtraItem(SOULWOOD_SET.getPlanks(), 2)
                .addExtraItem(MalumTags.Items.TAINTED_ROCK_BLOCKS, 2)
                .addSpirit(AERIAL_SPIRIT, 4)
                .addSpirit(AQUEOUS_SPIRIT, 4)
                .addSpirit(EARTHEN_SPIRIT, 4)
                .addSpirit(INFERNAL_SPIRIT, 4)
                .addSpirit(ARCANE_SPIRIT, 4)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(RUNEWOOD_SET.getPlanks(), 4, RITE_UNWEAVER, 4)
                .addExtraItem(MalumContent.Materials.SOUL_STAINED_STEEL_PLATING, 6)
                .addExtraItem(SOULWOOD_SET.getPlanks(), 2)
                .addExtraItem(MalumTags.Items.TWISTED_ROCK_BLOCKS, 2)
                .addSpirit(WICKED_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 8)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(RUNEWOOD_SET.getPlanks(), 4, RITE_SPREADER, 4)
                .addExtraItem(MalumContent.Materials.CONVOLUTED_LENS, 2)
                .addExtraItem(net.minecraft.world.item.Items.COPPER_INGOT, 4)
                .addExtraItem(SOULWOOD_SET.getPlanks(), 2)
                .addExtraItem(MalumTags.Items.TAINTED_ROCK_BLOCKS, 2)
                .addSpirit(ARCANE_SPIRIT, 8)
                .addSpirit(ELDRITCH_SPIRIT, 8)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(RUNEWOOD_SET.getPlanks(), 4, RITE_CHANNEL, 4)
                .addExtraItem(MalumContent.Materials.HALLOWED_GOLD_INLAY, 2)
                .addExtraItem(net.minecraft.world.item.Items.COPPER_INGOT, 4)
                .addExtraItem(SOULWOOD_SET.getPlanks(), 2)
                .addExtraItem(MalumTags.Items.TAINTED_ROCK_BLOCKS, 2)
                .addSpirit(ARCANE_SPIRIT, 4)
                .addSpirit(AQUEOUS_SPIRIT, 4)
                .addSpirit(EARTHEN_SPIRIT, 4)
                .addSpirit(INFERNAL_SPIRIT, 4)
                .addSpirit(ARCANE_SPIRIT, 4)
                .save(recipeOutput);
    }
}
