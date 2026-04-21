package com.sammy.malum.datagen.recipe.infusion;

import com.sammy.malum.datagen.recipe.builder.*;
import com.sammy.malum.registry.common.MalumContent;

import net.minecraft.data.recipes.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.crafting.SizedIngredient;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class GearSpiritInfusionRecipes {

    public static void buildRecipes(RecipeOutput recipeOutput) {
        new SpiritInfusionRecipeBuilder(MalumContent.Gear.SOUL_STAINED_STEEL_SWORD, 1, MalumContent.Gear.TYRVING, 1)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .addExtraItem(MalumContent.BlockSets.TWISTED_ROCK_SET.rock.block, 16)
                .addExtraItem(MalumContent.BlockSets.SOULWOOD_SET.planks.block, 8)
                .addExtraItem(MalumContent.Materials.PARACAUSAL_FLAME, 1)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.SOUL_STAINED_STEEL_PICKAXE, 1, MalumContent.Gear.SPELLWEAVING_PICKAXE, 1)
                .addSpirit(EARTHEN_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .addExtraItem(MalumContent.Materials.SOUL_STAINED_STEEL_PLATING, 6)
                .addExtraItem(MalumContent.AlchemyAndMetallics.IRON_METALLICS.getNode().get(), 4)
                .addExtraItem(MalumContent.Materials.CTHONIC_GOLD, 2)
                .carryOverComponentData()
                .save(recipeOutput);
        new SpiritInfusionRecipeBuilder(MalumContent.Gear.SOUL_STAINED_STEEL_AXE, 1, MalumContent.Gear.SPELLWEAVING_AXE, 1)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .addExtraItem(MalumContent.Materials.SOUL_STAINED_STEEL_PLATING, 6)
                .addExtraItem(MalumContent.AlchemyAndMetallics.IRON_METALLICS.getNode(), 4)
                .addExtraItem(MalumContent.Materials.CTHONIC_GOLD, 2)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.SOUL_STAINED_STEEL_AXE, 1, MalumContent.Gear.WEIGHT_OF_WORLDS, 1)
                .addExtraItem(MalumContent.Materials.MALIGNANT_PEWTER_INGOT, 2)
                .addSpirit(EARTHEN_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.SOUL_STAINED_STEEL_SCYTHE, 1, MalumContent.Gear.EDGE_OF_DELIVERANCE, 1)
                .addExtraItem(MalumContent.Materials.MALIGNANT_PEWTER_INGOT, 2)
                .addSpirit(EARTHEN_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Totemancy.TOTEMIC_STAFF, 1, MalumContent.Gear.MNEMONIC_HEX_STAFF, 1)
                .addExtraItem(MalumContent.Materials.MNEMONIC_FRAGMENT, 8)
                .addExtraItem(MalumContent.Materials.SOUL_STAINED_STEEL_INGOT, 4)
                .addExtraItem(MalumContent.BlockSets.SOULWOOD_SET.planks.block, 2)
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(AERIAL_SPIRIT, 16)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.MNEMONIC_HEX_STAFF, 1, MalumContent.Gear.EROSION_SCEPTER, 1)
                .addExtraItem(MalumContent.Materials.MALIGNANT_PEWTER_INGOT, 2)
                .addExtraItem(MalumContent.Materials.VOID_SALTS, 8)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.MNEMONIC_HEX_STAFF, 1, MalumContent.Gear.UNWINDING_CHAOS, 1)
                .addExtraItem(MalumContent.Materials.FUSED_CONSCIOUSNESS, 1)
                .addExtraItem(MalumContent.Materials.AURIC_EMBERS, 8)
                .addExtraItem(MalumContent.Materials.MALIGNANT_PEWTER_PLATING, 4)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(INFERNAL_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.SOUL_STAINED_STEEL_KNIFE, 1, MalumContent.Gear.SUNDERING_ANCHOR, 1)
                .addExtraItem(MalumContent.Materials.FUSED_CONSCIOUSNESS, 1)
                .addExtraItem(Items.NAUTILUS_SHELL, 8)
                .addExtraItem(MalumContent.Materials.LIVING_FLESH, 8)
                .addExtraItem(MalumContent.Materials.MALIGNANT_PEWTER_PLATING, 4)
                .addSpirit(SACRED_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.CRUDE_SCYTHE, 1, MalumContent.Gear.SOUL_STAINED_STEEL_SCYTHE, 1)
                .addExtraItem(MalumContent.Materials.SOUL_STAINED_STEEL_INGOT, 4)
                .addExtraItem(MalumContent.Materials.HEX_ASH, 2)
                .addExtraItem(MalumContent.Materials.REFINED_SOULSTONE, 4)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .addSpirit(WICKED_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 8)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.IRON_HELMET, 1, MalumContent.Gear.SOUL_STAINED_STEEL_HELMET, 1)
                .addExtraItem(MalumContent.Materials.SOUL_STAINED_STEEL_PLATING, 6)
                .addExtraItem(MalumContent.Materials.REFINED_SOULSTONE, 4)
                .addExtraItem(MalumContent.BlockSets.TWISTED_ROCK_SET.rock.block, 8)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .addSpirit(WICKED_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 8)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.IRON_CHESTPLATE, 1, MalumContent.Gear.SOUL_STAINED_STEEL_CHESTPLATE, 1)
                .addExtraItem(MalumContent.Materials.SOUL_STAINED_STEEL_PLATING, 6)
                .addExtraItem(MalumContent.Materials.REFINED_SOULSTONE, 4)
                .addExtraItem(MalumContent.BlockSets.TWISTED_ROCK_SET.rock.block, 8)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .addSpirit(WICKED_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 8)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.IRON_LEGGINGS, 1, MalumContent.Gear.SOUL_STAINED_STEEL_LEGGINGS, 1)
                .addExtraItem(MalumContent.Materials.SOUL_STAINED_STEEL_PLATING, 6)
                .addExtraItem(MalumContent.Materials.REFINED_SOULSTONE, 4)
                .addExtraItem(MalumContent.BlockSets.TWISTED_ROCK_SET.rock.block, 8)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .addSpirit(WICKED_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 8)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.IRON_BOOTS, 1, MalumContent.Gear.SOUL_STAINED_STEEL_BOOTS, 1)
                .addExtraItem(MalumContent.Materials.SOUL_STAINED_STEEL_PLATING, 6)
                .addExtraItem(MalumContent.Materials.REFINED_SOULSTONE, 4)
                .addExtraItem(MalumContent.BlockSets.TWISTED_ROCK_SET.rock.block, 8)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .addSpirit(WICKED_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 8)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.LEATHER_HELMET, 1, MalumContent.Gear.SOUL_HUNTER_CLOAK, 1)
                .addExtraItem(MalumContent.Materials.SOULWOVEN_SILK, 4)
                .addExtraItem(MalumContent.Materials.REFINED_SOULSTONE, 4)
                .addExtraItem(SizedIngredient.of(Tags.Items.LEATHERS, 2))
                .addSpirit(AERIAL_SPIRIT, 8)
                .addSpirit(EARTHEN_SPIRIT, 8)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.LEATHER_CHESTPLATE, 1, MalumContent.Gear.SOUL_HUNTER_ROBE, 1)
                .addExtraItem(MalumContent.Materials.SOULWOVEN_SILK, 4)
                .addExtraItem(MalumContent.Materials.REFINED_SOULSTONE, 4)
                .addExtraItem(SizedIngredient.of(Tags.Items.LEATHERS, 2))
                .addSpirit(AERIAL_SPIRIT, 8)
                .addSpirit(EARTHEN_SPIRIT, 8)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.LEATHER_LEGGINGS, 1, MalumContent.Gear.SOUL_HUNTER_LEGGINGS, 1)
                .addExtraItem(MalumContent.Materials.SOULWOVEN_SILK, 4)
                .addExtraItem(MalumContent.Materials.REFINED_SOULSTONE, 4)
                .addExtraItem(SizedIngredient.of(Tags.Items.LEATHERS, 2))
                .addSpirit(AERIAL_SPIRIT, 8)
                .addSpirit(EARTHEN_SPIRIT, 8)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.LEATHER_BOOTS, 1, MalumContent.Gear.SOUL_HUNTER_BOOTS, 1)
                .addExtraItem(MalumContent.Materials.SOULWOVEN_SILK, 4)
                .addExtraItem(MalumContent.Materials.REFINED_SOULSTONE, 4)
                .addExtraItem(SizedIngredient.of(Tags.Items.LEATHERS, 2))
                .addSpirit(AERIAL_SPIRIT, 8)
                .addSpirit(EARTHEN_SPIRIT, 8)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.SOUL_STAINED_STEEL_HELMET, 1, MalumContent.Gear.MALIGNANT_STRONGHOLD_HELMET, 1)
                .addExtraItem(MalumContent.Materials.SOULWOVEN_SILK, 8)
                .addExtraItem(MalumContent.Materials.MALIGNANT_PEWTER_PLATING, 3)
                .addSpirit(EARTHEN_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.SOUL_STAINED_STEEL_CHESTPLATE, 1, MalumContent.Gear.MALIGNANT_STRONGHOLD_CHESTPLATE, 1)
                .addExtraItem(MalumContent.Materials.SOULWOVEN_SILK, 8)
                .addExtraItem(MalumContent.Materials.MALIGNANT_PEWTER_PLATING, 3)
                .addSpirit(EARTHEN_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.SOUL_STAINED_STEEL_LEGGINGS, 1, MalumContent.Gear.MALIGNANT_STRONGHOLD_LEGGINGS, 1)
                .addExtraItem(MalumContent.Materials.SOULWOVEN_SILK, 8)
                .addExtraItem(MalumContent.Materials.MALIGNANT_PEWTER_PLATING, 3)
                .addSpirit(EARTHEN_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.SOUL_STAINED_STEEL_BOOTS, 1, MalumContent.Gear.MALIGNANT_STRONGHOLD_BOOTS, 1)
                .addExtraItem(MalumContent.Materials.SOULWOVEN_SILK, 8)
                .addExtraItem(MalumContent.Materials.MALIGNANT_PEWTER_PLATING, 3)
                .addSpirit(EARTHEN_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .carryOverComponentData()
                .save(recipeOutput);
    }
}
