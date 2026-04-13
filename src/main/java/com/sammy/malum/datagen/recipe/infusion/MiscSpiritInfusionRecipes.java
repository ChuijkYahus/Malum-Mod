package com.sammy.malum.datagen.recipe.infusion;

import com.sammy.malum.datagen.recipe.builder.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.MalumContent;

import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.crafting.SizedIngredient;

import static com.sammy.malum.registry.common.MalumContent.BlockSets.*;
import static com.sammy.malum.registry.common.MalumContent.Progression.*;
import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class MiscSpiritInfusionRecipes {

    public static void buildRecipes(RecipeOutput consumer) {
        new SpiritInfusionRecipeBuilder(RUNEWOOD_SET.planks.block, 2, RUNEWOOD_OBELISK, 1)
                .addExtraItem(MalumContent.Materials.HALLOWED_GOLD_INLAY, 2)
                .addSpirit(AERIAL_SPIRIT, 16)
                .addSpirit(SACRED_SPIRIT, 8)
                .save(consumer);

        new SpiritInfusionRecipeBuilder(RUNEWOOD_SET.planks.block, 2, BRILLIANT_OBELISK, 1)
                .addExtraItem(MalumContent.Materials.RAW_BRILLIANCE, 2)
                .addSpirit(AERIAL_SPIRIT, 16)
                .addSpirit(AQUEOUS_SPIRIT, 8)
                .save(consumer);

        new SpiritInfusionRecipeBuilder(RUNEWOOD_SET.itemPedestal, 1, RUNIC_WORKBENCH, 1)
                .addExtraItem(SizedIngredient.of(RUNEWOOD_SET.planks.block, 4))
                .addExtraItem(MalumContent.Materials.SOULWOVEN_SILK, 4)
                .addExtraItem(MalumContent.Materials.HALLOWED_GOLD_INGOT, 2)
                .addSpirit(SACRED_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .save(consumer);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.SOULWOVEN_POUCH, 1, MalumContent.Gear.RAVENOUS_POUCH, 1)
                .addExtraItem(MalumContent.Materials.ROTTING_ESSENCE, 12)
                .addExtraItem(MalumContent.Materials.GRIM_TALC, 8)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(SACRED_SPIRIT, 8)
                .addSpirit(WICKED_SPIRIT, 8)
                .save(consumer);

        new SpiritInfusionRecipeBuilder(Ingredient.of(Tags.Items.INGOTS_IRON), 2, MalumContent.Gear.LAMPLIGHTERS_TONGS, 1)
                .addSpirit(ARCANE_SPIRIT, 8)
                .addSpirit(INFERNAL_SPIRIT, 8)
                .addExtraItem(RUNEWOOD_SET.planks.block, 2)
                .addExtraItem(MalumContent.Materials.REFINED_SOULSTONE, 1)
                .save(consumer);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.LAMPLIGHTERS_TONGS, 1, MalumContent.Gear.CATALYST_LOBBER, 1)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .addSpirit(INFERNAL_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .addExtraItem(SizedIngredient.of(Tags.Items.INGOTS_IRON, 4))
                .addExtraItem(SOULWOOD_SET.planks.block, 2)
                .addExtraItem(MalumContent.Materials.MALIGNANT_LEAD, 1)
                .save(consumer);

        new SpiritInfusionRecipeBuilder(Ingredient.of(MalumTags.Items.ARCANE_ELEGY_COMPONENTS), 1, MalumContent.ARCANE_ELEGY, 1)
                .addSpirit(AERIAL_SPIRIT, 4)
                .addSpirit(AQUEOUS_SPIRIT, 4)
                .addSpirit(EARTHEN_SPIRIT, 4)
                .addSpirit(INFERNAL_SPIRIT, 4)
                .save(consumer);
    }
}
