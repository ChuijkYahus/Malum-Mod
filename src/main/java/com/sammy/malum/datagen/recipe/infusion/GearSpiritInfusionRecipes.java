package com.sammy.malum.datagen.recipe.infusion;

import com.sammy.malum.datagen.recipe.builder.*;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.crafting.SizedIngredient;

import static com.sammy.malum.registry.common.MalumSpiritTypes.*;

public class GearSpiritInfusionRecipes {

    public static void buildRecipes(RecipeOutput recipeOutput) {
        new SpiritInfusionRecipeBuilder(MalumItems.SOUL_STAINED_STEEL_SWORD.get(), 1, MalumItems.TYRVING.get(), 1)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .addExtraItem(MalumItems.TWISTED_ROCK.get(), 16)
                .addExtraItem(MalumItems.SOULWOOD_PLANKS.get(), 8)
                .addExtraItem(MalumItems.PARACAUSAL_FLAME.get(), 1)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.SOUL_STAINED_STEEL_AXE.get(), 1, MalumItems.WEIGHT_OF_WORLDS.get(), 1)
                .addExtraItem(MalumItems.MALIGNANT_PEWTER_INGOT.get(), 2)
                .addSpirit(EARTHEN_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.SOUL_STAINED_STEEL_SCYTHE.get(), 1, MalumItems.EDGE_OF_DELIVERANCE.get(), 1)
                .addExtraItem(MalumItems.MALIGNANT_PEWTER_INGOT.get(), 2)
                .addSpirit(EARTHEN_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.TOTEMIC_STAFF.get(), 1, MalumItems.MNEMONIC_HEX_STAFF.get(), 1)
                .addExtraItem(MalumItems.MNEMONIC_FRAGMENT.get(), 8)
                .addExtraItem(MalumItems.SOUL_STAINED_STEEL_INGOT.get(), 4)
                .addExtraItem(MalumItems.SOULWOOD_PLANKS.get(), 2)
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(AERIAL_SPIRIT, 16)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.MNEMONIC_HEX_STAFF.get(), 1, MalumItems.EROSION_SCEPTER.get(), 1)
                .addExtraItem(MalumItems.MALIGNANT_PEWTER_INGOT.get(), 2)
                .addExtraItem(MalumItems.VOID_SALTS.get(), 8)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.MNEMONIC_HEX_STAFF.get(), 1, MalumItems.UNWINDING_CHAOS.get(), 1)
                .addExtraItem(MalumItems.FUSED_CONSCIOUSNESS.get(), 1)
                .addExtraItem(MalumItems.AURIC_EMBERS.get(), 8)
                .addExtraItem(MalumItems.MALIGNANT_PEWTER_PLATING.get(), 4)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(INFERNAL_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.SOUL_STAINED_STEEL_KNIFE.get(), 1, MalumItems.SUNDERING_ANCHOR.get(), 1)
                .addExtraItem(MalumItems.FUSED_CONSCIOUSNESS.get(), 1)
                .addExtraItem(Items.NAUTILUS_SHELL, 8)
                .addExtraItem(MalumItems.LIVING_FLESH.get(), 8)
                .addExtraItem(MalumItems.MALIGNANT_PEWTER_PLATING.get(), 4)
                .addSpirit(SACRED_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.CRUDE_SCYTHE.get(), 1, MalumItems.SOUL_STAINED_STEEL_SCYTHE.get(), 1)
                .addExtraItem(MalumItems.SOUL_STAINED_STEEL_INGOT.get(), 4)
                .addExtraItem(MalumItems.HEX_ASH.get(), 2)
                .addExtraItem(MalumItems.REFINED_SOULSTONE.get(), 4)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .addSpirit(WICKED_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 8)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.IRON_HELMET, 1, MalumItems.SOUL_STAINED_STEEL_HELMET.get(), 1)
                .addExtraItem(MalumItems.SOUL_STAINED_STEEL_PLATING.get(), 6)
                .addExtraItem(MalumItems.REFINED_SOULSTONE.get(), 4)
                .addExtraItem(MalumItems.TWISTED_ROCK.get(), 8)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .addSpirit(WICKED_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 8)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.IRON_CHESTPLATE, 1, MalumItems.SOUL_STAINED_STEEL_CHESTPLATE.get(), 1)
                .addExtraItem(MalumItems.SOUL_STAINED_STEEL_PLATING.get(), 6)
                .addExtraItem(MalumItems.REFINED_SOULSTONE.get(), 4)
                .addExtraItem(MalumItems.TWISTED_ROCK.get(), 8)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .addSpirit(WICKED_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 8)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.IRON_LEGGINGS, 1, MalumItems.SOUL_STAINED_STEEL_LEGGINGS.get(), 1)
                .addExtraItem(MalumItems.SOUL_STAINED_STEEL_PLATING.get(), 6)
                .addExtraItem(MalumItems.REFINED_SOULSTONE.get(), 4)
                .addExtraItem(MalumItems.TWISTED_ROCK.get(), 8)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .addSpirit(WICKED_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 8)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.IRON_BOOTS, 1, MalumItems.SOUL_STAINED_STEEL_BOOTS.get(), 1)
                .addExtraItem(MalumItems.SOUL_STAINED_STEEL_PLATING.get(), 6)
                .addExtraItem(MalumItems.REFINED_SOULSTONE.get(), 4)
                .addExtraItem(MalumItems.TWISTED_ROCK.get(), 8)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .addSpirit(WICKED_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 8)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.LEATHER_HELMET, 1, MalumItems.SOUL_HUNTER_CLOAK.get(), 1)
                .addExtraItem(MalumItems.SOULWOVEN_SILK.get(), 4)
                .addExtraItem(MalumItems.REFINED_SOULSTONE.get(), 4)
                .addExtraItem(SizedIngredient.of(Tags.Items.LEATHERS, 2))
                .addSpirit(AERIAL_SPIRIT, 8)
                .addSpirit(EARTHEN_SPIRIT, 8)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.LEATHER_CHESTPLATE, 1, MalumItems.SOUL_HUNTER_ROBE.get(), 1)
                .addExtraItem(MalumItems.SOULWOVEN_SILK.get(), 4)
                .addExtraItem(MalumItems.REFINED_SOULSTONE.get(), 4)
                .addExtraItem(SizedIngredient.of(Tags.Items.LEATHERS, 2))
                .addSpirit(AERIAL_SPIRIT, 8)
                .addSpirit(EARTHEN_SPIRIT, 8)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.LEATHER_LEGGINGS, 1, MalumItems.SOUL_HUNTER_LEGGINGS.get(), 1)
                .addExtraItem(MalumItems.SOULWOVEN_SILK.get(), 4)
                .addExtraItem(MalumItems.REFINED_SOULSTONE.get(), 4)
                .addExtraItem(SizedIngredient.of(Tags.Items.LEATHERS, 2))
                .addSpirit(AERIAL_SPIRIT, 8)
                .addSpirit(EARTHEN_SPIRIT, 8)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.LEATHER_BOOTS, 1, MalumItems.SOUL_HUNTER_BOOTS.get(), 1)
                .addExtraItem(MalumItems.SOULWOVEN_SILK.get(), 4)
                .addExtraItem(MalumItems.REFINED_SOULSTONE.get(), 4)
                .addExtraItem(SizedIngredient.of(Tags.Items.LEATHERS, 2))
                .addSpirit(AERIAL_SPIRIT, 8)
                .addSpirit(EARTHEN_SPIRIT, 8)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.SOUL_STAINED_STEEL_HELMET.get(), 1, MalumItems.MALIGNANT_STRONGHOLD_HELMET.get(), 1)
                .addExtraItem(MalumItems.SOULWOVEN_SILK.get(), 8)
                .addExtraItem(MalumItems.MALIGNANT_PEWTER_PLATING.get(), 3)
                .addSpirit(EARTHEN_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.SOUL_STAINED_STEEL_CHESTPLATE.get(), 1, MalumItems.MALIGNANT_STRONGHOLD_CHESTPLATE.get(), 1)
                .addExtraItem(MalumItems.SOULWOVEN_SILK.get(), 8)
                .addExtraItem(MalumItems.MALIGNANT_PEWTER_PLATING.get(), 3)
                .addSpirit(EARTHEN_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.SOUL_STAINED_STEEL_LEGGINGS.get(), 1, MalumItems.MALIGNANT_STRONGHOLD_LEGGINGS.get(), 1)
                .addExtraItem(MalumItems.SOULWOVEN_SILK.get(), 8)
                .addExtraItem(MalumItems.MALIGNANT_PEWTER_PLATING.get(), 3)
                .addSpirit(EARTHEN_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.SOUL_STAINED_STEEL_BOOTS.get(), 1, MalumItems.MALIGNANT_STRONGHOLD_BOOTS.get(), 1)
                .addExtraItem(MalumItems.SOULWOVEN_SILK.get(), 8)
                .addExtraItem(MalumItems.MALIGNANT_PEWTER_PLATING.get(), 3)
                .addSpirit(EARTHEN_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .carryOverComponentData()
                .save(recipeOutput);
    }
}
