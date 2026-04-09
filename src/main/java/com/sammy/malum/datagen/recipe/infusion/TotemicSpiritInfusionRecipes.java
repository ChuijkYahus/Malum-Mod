package com.sammy.malum.datagen.recipe.infusion;

import com.sammy.malum.datagen.recipe.builder.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.content.MalumContent;
import com.sammy.malum.registry.common.content.item.MalumItemProperties;
import net.minecraft.data.recipes.*;
import net.neoforged.neoforge.common.crafting.*;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class TotemicSpiritInfusionRecipes {

    public static void buildRecipes(RecipeOutput recipeOutput) {
        new SpiritInfusionRecipeBuilder(MalumItemProperties.RUNEWOOD_LOG.get(), 4, MalumItemProperties.RUNEWOOD_TOTEM_BASE.get(), 4)
                .addExtraItem(MalumItemProperties.RUNEWOOD_PLANKS.get(), 6)
                .addExtraItem(MalumContent.Materials.HEX_ASH.get(), 2)
                .addSpirit(AERIAL_SPIRIT, 2)
                .addSpirit(AQUEOUS_SPIRIT, 2)
                .addSpirit(EARTHEN_SPIRIT, 2)
                .addSpirit(INFERNAL_SPIRIT, 2)
                .addSpirit(ELDRITCH_SPIRIT, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItemProperties.SOULWOOD_LOG.get(), 4, MalumItemProperties.SOULWOOD_TOTEM_BASE.get(), 4)
                .addExtraItem(MalumItemProperties.SOULWOOD_PLANKS.get(), 6)
                .addExtraItem(MalumContent.Materials.HEX_ASH.get(), 2)
                .addSpirit(AERIAL_SPIRIT, 2)
                .addSpirit(AQUEOUS_SPIRIT, 2)
                .addSpirit(EARTHEN_SPIRIT, 2)
                .addSpirit(INFERNAL_SPIRIT, 2)
                .addSpirit(ELDRITCH_SPIRIT, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(net.minecraft.world.item.Items.COPPER_BLOCK, 4, MalumItemProperties.WAVEFORM_RUNEWOOD_TOTEM_BASE.get(), 4)
                .addExtraItem(net.minecraft.world.item.Items.REDSTONE, 8)
                .addExtraItem(SizedIngredient.of(MalumTags.Items.RUNEWOOD_PLANKS, 6))
                .addExtraItem(MalumItemProperties.RUNEWOOD_LOG.get(), 4)
                .addExtraItem(MalumItemProperties.ETHER.get(), 2)
                .addSpirit(AERIAL_SPIRIT, 2)
                .addSpirit(AQUEOUS_SPIRIT, 2)
                .addSpirit(EARTHEN_SPIRIT, 2)
                .addSpirit(INFERNAL_SPIRIT, 2)
                .addSpirit(ARCANE_SPIRIT, 2)
                .addSpirit(ELDRITCH_SPIRIT, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(net.minecraft.world.item.Items.COPPER_BLOCK, 4, MalumItemProperties.WAVEFORM_SOULWOOD_TOTEM_BASE.get(), 4)
                .addExtraItem(net.minecraft.world.item.Items.REDSTONE, 8)
                .addExtraItem(SizedIngredient.of(MalumTags.Items.SOULWOOD_PLANKS, 6))
                .addExtraItem(MalumItemProperties.SOULWOOD_LOG.get(), 4)
                .addExtraItem(MalumItemProperties.ETHER.get(), 2)
                .addSpirit(AERIAL_SPIRIT, 2)
                .addSpirit(AQUEOUS_SPIRIT, 2)
                .addSpirit(EARTHEN_SPIRIT, 2)
                .addSpirit(INFERNAL_SPIRIT, 2)
                .addSpirit(ARCANE_SPIRIT, 2)
                .addSpirit(ELDRITCH_SPIRIT, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItemProperties.RUNEWOOD_OBELISK.get(), 1, MalumItemProperties.ARCANA_PYLON.get(), 1)
                .addExtraItem(MalumContent.Materials.REFINED_SOULSTONE.get(), 8)
                .addExtraItem(MalumContent.Materials.HEX_ASH.get(), 4)
                .addExtraItem(MalumItemProperties.SOULWOOD_PLANKS.get(), 2)
                .addSpirit(AERIAL_SPIRIT, 8)
                .addSpirit(AQUEOUS_SPIRIT, 8)
                .addSpirit(EARTHEN_SPIRIT, 8)
                .addSpirit(INFERNAL_SPIRIT, 8)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItemProperties.RUNEWOOD_PLANKS.get(), 4, MalumItemProperties.RITE_ANCHOR.get(), 4)
                .addExtraItem(MalumContent.Materials.REFINED_SOULSTONE.get(), 8)
                .addExtraItem(MalumContent.Materials.HEX_ASH.get(), 4)
                .addExtraItem(MalumItemProperties.SOULWOOD_PLANKS.get(), 2)
                .addExtraItem(MalumTags.Items.TAINTED_ROCK_BLOCKS, 2)
                .addSpirit(AERIAL_SPIRIT, 4)
                .addSpirit(AQUEOUS_SPIRIT, 4)
                .addSpirit(EARTHEN_SPIRIT, 4)
                .addSpirit(INFERNAL_SPIRIT, 4)
                .addSpirit(ARCANE_SPIRIT, 4)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItemProperties.RUNEWOOD_PLANKS.get(), 4, MalumItemProperties.RITE_UNWEAVER.get(), 4)
                .addExtraItem(MalumContent.Materials.SOUL_STAINED_STEEL_PLATING.get(), 6)
                .addExtraItem(MalumItemProperties.SOULWOOD_PLANKS.get(), 2)
                .addExtraItem(MalumTags.Items.TWISTED_ROCK_BLOCKS, 2)
                .addSpirit(WICKED_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 8)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItemProperties.RUNEWOOD_PLANKS.get(), 4, MalumItemProperties.RITE_SPREADER.get(), 4)
                .addExtraItem(MalumContent.Materials.CONVOLUTED_LENS.get(), 2)
                .addExtraItem(net.minecraft.world.item.Items.COPPER_INGOT, 4)
                .addExtraItem(MalumItemProperties.SOULWOOD_PLANKS.get(), 2)
                .addExtraItem(MalumTags.Items.TAINTED_ROCK_BLOCKS, 2)
                .addSpirit(ARCANE_SPIRIT, 8)
                .addSpirit(ELDRITCH_SPIRIT, 8)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItemProperties.RUNEWOOD_PLANKS.get(), 4, MalumItemProperties.RITE_CHANNEL.get(), 4)
                .addExtraItem(MalumContent.Materials.HALLOWED_GOLD_INLAY.get(), 2)
                .addExtraItem(net.minecraft.world.item.Items.COPPER_INGOT, 4)
                .addExtraItem(MalumItemProperties.SOULWOOD_PLANKS.get(), 2)
                .addExtraItem(MalumTags.Items.TAINTED_ROCK_BLOCKS, 2)
                .addSpirit(ARCANE_SPIRIT, 4)
                .addSpirit(AQUEOUS_SPIRIT, 4)
                .addSpirit(EARTHEN_SPIRIT, 4)
                .addSpirit(INFERNAL_SPIRIT, 4)
                .addSpirit(ARCANE_SPIRIT, 4)
                .save(recipeOutput);
    }
}
