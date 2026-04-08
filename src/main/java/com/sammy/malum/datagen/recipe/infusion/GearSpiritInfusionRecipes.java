package com.sammy.malum.datagen.recipe.infusion;

import com.sammy.malum.datagen.recipe.builder.*;
import com.sammy.malum.registry.common.block.MalumBlocks;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.crafting.SizedIngredient;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class GearSpiritInfusionRecipes {

    public static void buildRecipes(RecipeOutput recipeOutput) {
        new SpiritInfusionRecipeBuilder(MalumItems.SOUL_STAINED_STEEL_SWORD, 1, MalumItems.TYRVING, 1)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .addExtraItem(MalumBlocks.TWISTED_ROCK, 16)
                .addExtraItem(MalumBlocks.SOULWOOD_PLANKS, 8)
                .addExtraItem(MalumItems.PARACAUSAL_FLAME, 1)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.SOUL_STAINED_STEEL_PICKAXE, 1, MalumItems.SPELLWEAVING_PICKAXE, 1)
                .addSpirit(EARTHEN_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .addExtraItem(MalumItems.SOUL_STAINED_STEEL_PLATING, 6)
                .addExtraItem(MalumItems.IRON_METALLICS.getNode().get(), 4)
                .addExtraItem(MalumItems.CTHONIC_GOLD, 2)
                .carryOverComponentData()
                .save(recipeOutput);
        new SpiritInfusionRecipeBuilder(MalumItems.SOUL_STAINED_STEEL_AXE, 1, MalumItems.SPELLWEAVING_AXE, 1)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .addExtraItem(MalumItems.SOUL_STAINED_STEEL_PLATING, 6)
                .addExtraItem(MalumItems.IRON_METALLICS.getNode(), 4)
                .addExtraItem(MalumItems.CTHONIC_GOLD, 2)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.SOUL_STAINED_STEEL_AXE, 1, MalumItems.WEIGHT_OF_WORLDS, 1)
                .addExtraItem(MalumItems.MALIGNANT_PEWTER_INGOT, 2)
                .addSpirit(EARTHEN_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.SOUL_STAINED_STEEL_SCYTHE, 1, MalumItems.EDGE_OF_DELIVERANCE, 1)
                .addExtraItem(MalumItems.MALIGNANT_PEWTER_INGOT, 2)
                .addSpirit(EARTHEN_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.TOTEMIC_STAFF, 1, MalumItems.MNEMONIC_HEX_STAFF, 1)
                .addExtraItem(MalumItems.MNEMONIC_FRAGMENT, 8)
                .addExtraItem(MalumItems.SOUL_STAINED_STEEL_INGOT, 4)
                .addExtraItem(MalumBlocks.SOULWOOD_PLANKS, 2)
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(AERIAL_SPIRIT, 16)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.MNEMONIC_HEX_STAFF, 1, MalumItems.EROSION_SCEPTER, 1)
                .addExtraItem(MalumItems.MALIGNANT_PEWTER_INGOT, 2)
                .addExtraItem(MalumItems.VOID_SALTS, 8)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.MNEMONIC_HEX_STAFF, 1, MalumItems.UNWINDING_CHAOS, 1)
                .addExtraItem(MalumItems.FUSED_CONSCIOUSNESS, 1)
                .addExtraItem(MalumItems.AURIC_EMBERS, 8)
                .addExtraItem(MalumItems.MALIGNANT_PEWTER_PLATING, 4)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(INFERNAL_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.SOUL_STAINED_STEEL_KNIFE, 1, MalumItems.SUNDERING_ANCHOR, 1)
                .addExtraItem(MalumItems.FUSED_CONSCIOUSNESS, 1)
                .addExtraItem(Items.NAUTILUS_SHELL, 8)
                .addExtraItem(MalumItems.LIVING_FLESH, 8)
                .addExtraItem(MalumItems.MALIGNANT_PEWTER_PLATING, 4)
                .addSpirit(SACRED_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.CRUDE_SCYTHE, 1, MalumItems.SOUL_STAINED_STEEL_SCYTHE, 1)
                .addExtraItem(MalumItems.SOUL_STAINED_STEEL_INGOT, 4)
                .addExtraItem(MalumItems.HEX_ASH, 2)
                .addExtraItem(MalumItems.REFINED_SOULSTONE, 4)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .addSpirit(WICKED_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 8)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.IRON_HELMET, 1, MalumItems.SOUL_STAINED_STEEL_HELMET, 1)
                .addExtraItem(MalumItems.SOUL_STAINED_STEEL_PLATING, 6)
                .addExtraItem(MalumItems.REFINED_SOULSTONE, 4)
                .addExtraItem(MalumBlocks.TWISTED_ROCK, 8)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .addSpirit(WICKED_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 8)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.IRON_CHESTPLATE, 1, MalumItems.SOUL_STAINED_STEEL_CHESTPLATE, 1)
                .addExtraItem(MalumItems.SOUL_STAINED_STEEL_PLATING, 6)
                .addExtraItem(MalumItems.REFINED_SOULSTONE, 4)
                .addExtraItem(MalumBlocks.TWISTED_ROCK, 8)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .addSpirit(WICKED_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 8)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.IRON_LEGGINGS, 1, MalumItems.SOUL_STAINED_STEEL_LEGGINGS, 1)
                .addExtraItem(MalumItems.SOUL_STAINED_STEEL_PLATING, 6)
                .addExtraItem(MalumItems.REFINED_SOULSTONE, 4)
                .addExtraItem(MalumBlocks.TWISTED_ROCK, 8)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .addSpirit(WICKED_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 8)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.IRON_BOOTS, 1, MalumItems.SOUL_STAINED_STEEL_BOOTS, 1)
                .addExtraItem(MalumItems.SOUL_STAINED_STEEL_PLATING, 6)
                .addExtraItem(MalumItems.REFINED_SOULSTONE, 4)
                .addExtraItem(MalumBlocks.TWISTED_ROCK, 8)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .addSpirit(WICKED_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 8)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.LEATHER_HELMET, 1, MalumItems.SOUL_HUNTER_CLOAK, 1)
                .addExtraItem(MalumItems.SOULWOVEN_SILK, 4)
                .addExtraItem(MalumItems.REFINED_SOULSTONE, 4)
                .addExtraItem(SizedIngredient.of(Tags.Items.LEATHERS, 2))
                .addSpirit(AERIAL_SPIRIT, 8)
                .addSpirit(EARTHEN_SPIRIT, 8)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.LEATHER_CHESTPLATE, 1, MalumItems.SOUL_HUNTER_ROBE, 1)
                .addExtraItem(MalumItems.SOULWOVEN_SILK, 4)
                .addExtraItem(MalumItems.REFINED_SOULSTONE, 4)
                .addExtraItem(SizedIngredient.of(Tags.Items.LEATHERS, 2))
                .addSpirit(AERIAL_SPIRIT, 8)
                .addSpirit(EARTHEN_SPIRIT, 8)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.LEATHER_LEGGINGS, 1, MalumItems.SOUL_HUNTER_LEGGINGS, 1)
                .addExtraItem(MalumItems.SOULWOVEN_SILK, 4)
                .addExtraItem(MalumItems.REFINED_SOULSTONE, 4)
                .addExtraItem(SizedIngredient.of(Tags.Items.LEATHERS, 2))
                .addSpirit(AERIAL_SPIRIT, 8)
                .addSpirit(EARTHEN_SPIRIT, 8)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.LEATHER_BOOTS, 1, MalumItems.SOUL_HUNTER_BOOTS, 1)
                .addExtraItem(MalumItems.SOULWOVEN_SILK, 4)
                .addExtraItem(MalumItems.REFINED_SOULSTONE, 4)
                .addExtraItem(SizedIngredient.of(Tags.Items.LEATHERS, 2))
                .addSpirit(AERIAL_SPIRIT, 8)
                .addSpirit(EARTHEN_SPIRIT, 8)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.SOUL_STAINED_STEEL_HELMET, 1, MalumItems.MALIGNANT_STRONGHOLD_HELMET, 1)
                .addExtraItem(MalumItems.SOULWOVEN_SILK, 8)
                .addExtraItem(MalumItems.MALIGNANT_PEWTER_PLATING, 3)
                .addSpirit(EARTHEN_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.SOUL_STAINED_STEEL_CHESTPLATE, 1, MalumItems.MALIGNANT_STRONGHOLD_CHESTPLATE, 1)
                .addExtraItem(MalumItems.SOULWOVEN_SILK, 8)
                .addExtraItem(MalumItems.MALIGNANT_PEWTER_PLATING, 3)
                .addSpirit(EARTHEN_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.SOUL_STAINED_STEEL_LEGGINGS, 1, MalumItems.MALIGNANT_STRONGHOLD_LEGGINGS, 1)
                .addExtraItem(MalumItems.SOULWOVEN_SILK, 8)
                .addExtraItem(MalumItems.MALIGNANT_PEWTER_PLATING, 3)
                .addSpirit(EARTHEN_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.SOUL_STAINED_STEEL_BOOTS, 1, MalumItems.MALIGNANT_STRONGHOLD_BOOTS, 1)
                .addExtraItem(MalumItems.SOULWOVEN_SILK, 8)
                .addExtraItem(MalumItems.MALIGNANT_PEWTER_PLATING, 3)
                .addSpirit(EARTHEN_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .carryOverComponentData()
                .save(recipeOutput);
    }
}
