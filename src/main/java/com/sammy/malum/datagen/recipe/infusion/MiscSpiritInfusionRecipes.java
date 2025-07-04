package com.sammy.malum.datagen.recipe.infusion;

import com.sammy.malum.*;
import com.sammy.malum.datagen.recipe.builder.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.item.*;

import net.minecraft.data.recipes.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.crafting.SizedIngredient;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class MiscSpiritInfusionRecipes {

    public static void buildRecipes(RecipeOutput consumer) {
        new SpiritInfusionRecipeBuilder(MalumItems.RUNEWOOD_PLANKS.get(), 2, MalumItems.RUNEWOOD_OBELISK.get(), 1)
                .addExtraItem(MalumItems.HALLOWED_GOLD_INGOT.get(), 2)
                .addSpirit(AERIAL_SPIRIT, 16)
                .addSpirit(SACRED_SPIRIT, 8)
                .save(consumer);

        new SpiritInfusionRecipeBuilder(MalumItems.RUNEWOOD_PLANKS.get(), 2, MalumItems.BRILLIANT_OBELISK.get(), 1)
                .addExtraItem(MalumItems.RAW_BRILLIANCE.get(), 2)
                .addSpirit(AERIAL_SPIRIT, 16)
                .addSpirit(AQUEOUS_SPIRIT, 8)
                .save(consumer);

        new SpiritInfusionRecipeBuilder(MalumItems.RUNEWOOD_ITEM_PEDESTAL.get(), 1, MalumItems.RUNIC_WORKBENCH.get(), 1)
                .addExtraItem(SizedIngredient.of(MalumTags.ItemTags.RUNEWOOD_PLANKS, 4))
                .addExtraItem(MalumItems.SOULWOVEN_SILK.get(), 4)
                .addExtraItem(MalumItems.HALLOWED_GOLD_INGOT.get(), 2)
                .addSpirit(SACRED_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .save(consumer);

        new SpiritInfusionRecipeBuilder(Items.HONEY_BOTTLE, 1, MalumItems.CONCENTRATED_GLUTTONY.get(), 2)
                .addExtraItem(MalumItems.ROTTING_ESSENCE.get(), 1)
                .addSpirit(AQUEOUS_SPIRIT, 2)
                .addSpirit(SACRED_SPIRIT, 2)
                .addSpirit(WICKED_SPIRIT, 2)
                .save(consumer);

        new SpiritInfusionRecipeBuilder(Items.HONEY_BOTTLE, 1, MalumItems.SPLASH_OF_GLUTTONY.get(), 2)
                .addExtraItem(MalumItems.ROTTING_ESSENCE.get(), 1)
                .addExtraItem(SizedIngredient.of(Tags.Items.GUNPOWDERS, 1))
                .addSpirit(AQUEOUS_SPIRIT, 3)
                .addSpirit(SACRED_SPIRIT, 2)
                .addSpirit(WICKED_SPIRIT, 2)
                .save(consumer);

        new SpiritInfusionRecipeBuilder(MalumItems.CONCENTRATED_GLUTTONY.get(), 1, MalumItems.SPLASH_OF_GLUTTONY.get(), 1)
                .addExtraItem(SizedIngredient.of(Tags.Items.GUNPOWDERS, 1))
                .addSpirit(AQUEOUS_SPIRIT, 1)
                .save(consumer, MalumMod.malumPath("splash_of_gluttony_from_concentrated_gluttony"));


        new SpiritInfusionRecipeBuilder(MalumItems.SOULWOVEN_POUCH.get(), 1, MalumItems.RAVENOUS_POUCH.get(), 1)
                .addExtraItem(MalumItems.ROTTING_ESSENCE.get(), 12)
                .addExtraItem(MalumItems.GRIM_TALC.get(), 12)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(SACRED_SPIRIT, 8)
                .addSpirit(WICKED_SPIRIT, 8)
                .save(consumer);

        new SpiritInfusionRecipeBuilder(Ingredient.of(Tags.Items.INGOTS_IRON), 2, MalumItems.LAMPLIGHTERS_TONGS.get(), 1)
                .addSpirit(ARCANE_SPIRIT, 8)
                .addSpirit(INFERNAL_SPIRIT, 8)
                .addExtraItem(MalumItems.RUNEWOOD_PLANKS.get(), 2)
                .addExtraItem(MalumItems.REFINED_SOULSTONE.get(), 1)
                .save(consumer);

        new SpiritInfusionRecipeBuilder(MalumItems.LAMPLIGHTERS_TONGS.get(), 1, MalumItems.CATALYST_LOBBER.get(), 1)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .addSpirit(INFERNAL_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .addExtraItem(SizedIngredient.of(Tags.Items.INGOTS_IRON, 4))
                .addExtraItem(MalumItems.SOULWOOD_PLANKS.get(), 2)
                .addExtraItem(MalumItems.MALIGNANT_LEAD.get(), 1)
                .save(consumer);

        new SpiritInfusionRecipeBuilder(Ingredient.of(MalumTags.ItemTags.ARCANE_ELEGY_COMPONENTS), 1, MalumItems.ARCANE_ELEGY.get(), 1)
                .addSpirit(AERIAL_SPIRIT, 4)
                .addSpirit(AQUEOUS_SPIRIT, 4)
                .addSpirit(EARTHEN_SPIRIT, 4)
                .addSpirit(INFERNAL_SPIRIT, 4)
                .save(consumer);
    }
}
