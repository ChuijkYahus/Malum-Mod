package com.sammy.malum.datagen.recipe.infusion;

import com.sammy.malum.*;
import com.sammy.malum.datagen.recipe.builder.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.content.MalumContent;
import com.sammy.malum.registry.common.content.item.MalumItemProperties;

import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.crafting.SizedIngredient;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class MiscSpiritInfusionRecipes {

    public static void buildRecipes(RecipeOutput consumer) {
        new SpiritInfusionRecipeBuilder(MalumItemProperties.RUNEWOOD_PLANKS.get(), 2, MalumItemProperties.RUNEWOOD_OBELISK.get(), 1)
                .addExtraItem(MalumContent.Materials.HALLOWED_GOLD_INLAY.get(), 2)
                .addSpirit(AERIAL_SPIRIT, 16)
                .addSpirit(SACRED_SPIRIT, 8)
                .save(consumer);

        new SpiritInfusionRecipeBuilder(MalumItemProperties.RUNEWOOD_PLANKS.get(), 2, MalumItemProperties.BRILLIANT_OBELISK.get(), 1)
                .addExtraItem(MalumContent.Materials.RAW_BRILLIANCE.get(), 2)
                .addSpirit(AERIAL_SPIRIT, 16)
                .addSpirit(AQUEOUS_SPIRIT, 8)
                .save(consumer);

        new SpiritInfusionRecipeBuilder(MalumItemProperties.RUNEWOOD_ITEM_PEDESTAL.get(), 1, MalumItemProperties.RUNIC_WORKBENCH.get(), 1)
                .addExtraItem(SizedIngredient.of(MalumTags.Items.RUNEWOOD_PLANKS, 4))
                .addExtraItem(MalumContent.Materials.SOULWOVEN_SILK.get(), 4)
                .addExtraItem(MalumContent.Materials.HALLOWED_GOLD_INGOT.get(), 2)
                .addSpirit(SACRED_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .save(consumer);

        new SpiritInfusionRecipeBuilder(net.minecraft.world.item.Items.HONEY_BOTTLE, 1, MalumContent.Gear.CONCENTRATED_GLUTTONY.get(), 2)
                .addExtraItem(MalumContent.Materials.ROTTING_ESSENCE.get(), 1)
                .addSpirit(AQUEOUS_SPIRIT, 2)
                .addSpirit(SACRED_SPIRIT, 2)
                .addSpirit(WICKED_SPIRIT, 2)
                .save(consumer);

        new SpiritInfusionRecipeBuilder(net.minecraft.world.item.Items.HONEY_BOTTLE, 1, MalumContent.Gear.SPLASH_OF_GLUTTONY.get(), 2)
                .addExtraItem(MalumContent.Materials.ROTTING_ESSENCE.get(), 1)
                .addExtraItem(SizedIngredient.of(Tags.Items.GUNPOWDERS, 1))
                .addSpirit(AQUEOUS_SPIRIT, 3)
                .addSpirit(SACRED_SPIRIT, 2)
                .addSpirit(WICKED_SPIRIT, 2)
                .save(consumer);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.CONCENTRATED_GLUTTONY.get(), 1, MalumContent.Gear.SPLASH_OF_GLUTTONY.get(), 1)
                .addExtraItem(SizedIngredient.of(Tags.Items.GUNPOWDERS, 1))
                .addSpirit(AQUEOUS_SPIRIT, 1)
                .save(consumer, MalumMod.malumPath("splash_of_gluttony_from_concentrated_gluttony"));


        new SpiritInfusionRecipeBuilder(MalumContent.Gear.SOULWOVEN_POUCH.get(), 1, MalumContent.Gear.RAVENOUS_POUCH.get(), 1)
                .addExtraItem(MalumContent.Materials.ROTTING_ESSENCE.get(), 12)
                .addExtraItem(MalumContent.Materials.GRIM_TALC.get(), 8)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(SACRED_SPIRIT, 8)
                .addSpirit(WICKED_SPIRIT, 8)
                .save(consumer);

        new SpiritInfusionRecipeBuilder(Ingredient.of(Tags.Items.INGOTS_IRON), 2, MalumContent.Gear.LAMPLIGHTERS_TONGS.get(), 1)
                .addSpirit(ARCANE_SPIRIT, 8)
                .addSpirit(INFERNAL_SPIRIT, 8)
                .addExtraItem(MalumItemProperties.RUNEWOOD_PLANKS.get(), 2)
                .addExtraItem(MalumContent.Materials.REFINED_SOULSTONE.get(), 1)
                .save(consumer);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.LAMPLIGHTERS_TONGS.get(), 1, MalumContent.Gear.CATALYST_LOBBER.get(), 1)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .addSpirit(INFERNAL_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .addExtraItem(SizedIngredient.of(Tags.Items.INGOTS_IRON, 4))
                .addExtraItem(MalumItemProperties.SOULWOOD_PLANKS.get(), 2)
                .addExtraItem(MalumContent.Materials.MALIGNANT_LEAD.get(), 1)
                .save(consumer);

        new SpiritInfusionRecipeBuilder(Ingredient.of(MalumTags.Items.ARCANE_ELEGY_COMPONENTS), 1, MalumContent.ARCANE_ELEGY.get(), 1)
                .addSpirit(AERIAL_SPIRIT, 4)
                .addSpirit(AQUEOUS_SPIRIT, 4)
                .addSpirit(EARTHEN_SPIRIT, 4)
                .addSpirit(INFERNAL_SPIRIT, 4)
                .save(consumer);
    }
}
