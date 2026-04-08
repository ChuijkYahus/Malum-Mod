package com.sammy.malum.datagen.recipe.infusion;

import com.sammy.malum.datagen.recipe.builder.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.data.recipes.*;
import net.neoforged.neoforge.common.crafting.*;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

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

        new SpiritInfusionRecipeBuilder(net.minecraft.world.item.Items.COPPER_BLOCK, 4, MalumItems.WAVEFORM_RUNEWOOD_TOTEM_BASE.get(), 4)
                .addExtraItem(net.minecraft.world.item.Items.REDSTONE, 8)
                .addExtraItem(SizedIngredient.of(MalumTags.Items.RUNEWOOD_PLANKS, 6))
                .addExtraItem(MalumItems.RUNEWOOD_LOG.get(), 4)
                .addExtraItem(MalumItems.ETHER.get(), 2)
                .addSpirit(AERIAL_SPIRIT, 2)
                .addSpirit(AQUEOUS_SPIRIT, 2)
                .addSpirit(EARTHEN_SPIRIT, 2)
                .addSpirit(INFERNAL_SPIRIT, 2)
                .addSpirit(ARCANE_SPIRIT, 2)
                .addSpirit(ELDRITCH_SPIRIT, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(net.minecraft.world.item.Items.COPPER_BLOCK, 4, MalumItems.WAVEFORM_SOULWOOD_TOTEM_BASE.get(), 4)
                .addExtraItem(net.minecraft.world.item.Items.REDSTONE, 8)
                .addExtraItem(SizedIngredient.of(MalumTags.Items.SOULWOOD_PLANKS, 6))
                .addExtraItem(MalumItems.SOULWOOD_LOG.get(), 4)
                .addExtraItem(MalumItems.ETHER.get(), 2)
                .addSpirit(AERIAL_SPIRIT, 2)
                .addSpirit(AQUEOUS_SPIRIT, 2)
                .addSpirit(EARTHEN_SPIRIT, 2)
                .addSpirit(INFERNAL_SPIRIT, 2)
                .addSpirit(ARCANE_SPIRIT, 2)
                .addSpirit(ELDRITCH_SPIRIT, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.RUNEWOOD_OBELISK.get(), 1, MalumItems.ARCANA_PYLON.get(), 1)
                .addExtraItem(MalumItems.REFINED_SOULSTONE.get(), 8)
                .addExtraItem(MalumItems.HEX_ASH.get(), 4)
                .addExtraItem(MalumItems.SOULWOOD_PLANKS.get(), 2)
                .addSpirit(AERIAL_SPIRIT, 8)
                .addSpirit(AQUEOUS_SPIRIT, 8)
                .addSpirit(EARTHEN_SPIRIT, 8)
                .addSpirit(INFERNAL_SPIRIT, 8)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.RUNEWOOD_PLANKS.get(), 4, MalumItems.RITE_ANCHOR.get(), 4)
                .addExtraItem(MalumItems.REFINED_SOULSTONE.get(), 8)
                .addExtraItem(MalumItems.HEX_ASH.get(), 4)
                .addExtraItem(MalumItems.SOULWOOD_PLANKS.get(), 2)
                .addExtraItem(MalumTags.Items.TAINTED_ROCK_BLOCKS, 2)
                .addSpirit(AERIAL_SPIRIT, 4)
                .addSpirit(AQUEOUS_SPIRIT, 4)
                .addSpirit(EARTHEN_SPIRIT, 4)
                .addSpirit(INFERNAL_SPIRIT, 4)
                .addSpirit(ARCANE_SPIRIT, 4)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.RUNEWOOD_PLANKS.get(), 4, MalumItems.RITE_UNWEAVER.get(), 4)
                .addExtraItem(MalumItems.SOUL_STAINED_STEEL_PLATING.get(), 6)
                .addExtraItem(MalumItems.SOULWOOD_PLANKS.get(), 2)
                .addExtraItem(MalumTags.Items.TWISTED_ROCK_BLOCKS, 2)
                .addSpirit(WICKED_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 8)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.RUNEWOOD_PLANKS.get(), 4, MalumItems.RITE_SPREADER.get(), 4)
                .addExtraItem(MalumItems.CONVOLUTED_LENS.get(), 2)
                .addExtraItem(net.minecraft.world.item.Items.COPPER_INGOT, 4)
                .addExtraItem(MalumItems.SOULWOOD_PLANKS.get(), 2)
                .addExtraItem(MalumTags.Items.TAINTED_ROCK_BLOCKS, 2)
                .addSpirit(ARCANE_SPIRIT, 8)
                .addSpirit(ELDRITCH_SPIRIT, 8)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.RUNEWOOD_PLANKS.get(), 4, MalumItems.RITE_CHANNEL.get(), 4)
                .addExtraItem(MalumItems.HALLOWED_GOLD_INLAY.get(), 2)
                .addExtraItem(net.minecraft.world.item.Items.COPPER_INGOT, 4)
                .addExtraItem(MalumItems.SOULWOOD_PLANKS.get(), 2)
                .addExtraItem(MalumTags.Items.TAINTED_ROCK_BLOCKS, 2)
                .addSpirit(ARCANE_SPIRIT, 4)
                .addSpirit(AQUEOUS_SPIRIT, 4)
                .addSpirit(EARTHEN_SPIRIT, 4)
                .addSpirit(INFERNAL_SPIRIT, 4)
                .addSpirit(ARCANE_SPIRIT, 4)
                .save(recipeOutput);
    }
}
