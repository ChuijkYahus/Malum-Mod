package com.sammy.malum.datagen.recipe;

import com.sammy.malum.*;
import com.sammy.malum.common.recipe.spirit_repair.*;
import com.sammy.malum.datagen.recipe.builder.*;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.core.*;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.neoforged.neoforge.common.crafting.*;

import static com.sammy.malum.registry.common.MalumSpiritTypes.*;

public class MalumSpiritRepairRecipes implements IConditionBuilder {

    protected static void buildRecipes(RecipeOutput recipeOutput) {
        var has = MalumRecipes.has(MalumItems.REPAIR_PYLON.get());

        new SpiritRepairRecipeBuilder(SizedIngredient.of(ItemTags.PLANKS, 4), 0.5f)
                .withRegex(SpiritRepairRegexData.any("wooden_.+"))
                .addSpirit(SACRED_SPIRIT, 4)
                .addSpirit(EARTHEN_SPIRIT, 4)
                .withValidItem(Items.BOW)
                .withValidItem(Items.CROSSBOW)
                .unlockedBy("has_pylon", has)
                .save(recipeOutput, MalumMod.malumPath("wooden_restoration"));

        new SpiritRepairRecipeBuilder(SizedIngredient.of(Items.FLINT, 2), 0.5f)
                .withRegex(SpiritRepairRegexData.any("flint_.+"))
                .addSpirit(EARTHEN_SPIRIT, 4)
                .unlockedBy("has_pylon", has)
                .save(recipeOutput, MalumMod.malumPath("flint_restoration"));

        new SpiritRepairRecipeBuilder(SizedIngredient.of(ItemTags.STONE_TOOL_MATERIALS, 2), 0.5f)
                .withRegex(SpiritRepairRegexData.any("stone_.+"))
                .addSpirit(EARTHEN_SPIRIT, 4)
                .unlockedBy("has_pylon", has)
                .save(recipeOutput, MalumMod.malumPath("stone_restoration"));

        new SpiritRepairRecipeBuilder(SizedIngredient.of(Tags.Items.INGOTS_COPPER, 2), 0.5f)
                .withRegex(SpiritRepairRegexData.any("copper_.+"))
                .addSpirit(EARTHEN_SPIRIT, 6)
                .unlockedBy("has_pylon", has)
                .save(recipeOutput, MalumMod.malumPath("copper_restoration"));

        new SpiritRepairRecipeBuilder(SizedIngredient.of(Tags.Items.INGOTS_IRON, 2), 0.5f)
                .withRegex(SpiritRepairRegexData.any("iron_.+"))
                .withValidItem(MalumItems.CRUDE_SCYTHE.get())
                .addSpirit(EARTHEN_SPIRIT, 8)
                .unlockedBy("has_pylon", has)
                .save(recipeOutput, MalumMod.malumPath("iron_restoration"));

        new SpiritRepairRecipeBuilder(SizedIngredient.of(Tags.Items.INGOTS_GOLD, 2), 0.5f)
                .withRegex(SpiritRepairRegexData.any("golden_.+"))
                .addSpirit(ARCANE_SPIRIT, 8)
                .unlockedBy("has_pylon", has)
                .save(recipeOutput, MalumMod.malumPath("gold_restoration"));

        new SpiritRepairRecipeBuilder(SizedIngredient.of(Tags.Items.GEMS_DIAMOND, 2), 0.5f)
                .withRegex(SpiritRepairRegexData.any("diamond_.+"))
                .addSpirit(ARCANE_SPIRIT, 16)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .unlockedBy("has_pylon", has)
                .save(recipeOutput, MalumMod.malumPath("diamond_restoration"));

        new SpiritRepairRecipeBuilder(SizedIngredient.of(Tags.Items.INGOTS_NETHERITE, 1), 0.5f)
                .withRegex(SpiritRepairRegexData.any("netherite_.+"))
                .addSpirit(INFERNAL_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .unlockedBy("has_pylon", has)
                .save(recipeOutput, MalumMod.malumPath("netherite_restoration"));

        new SpiritRepairRecipeBuilder(SizedIngredient.of(Items.PRISMARINE_CRYSTALS, 8), 0.5f)
                .withValidItem(Items.TRIDENT)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .unlockedBy("has_pylon", has)
                .save(recipeOutput, MalumMod.malumPath("trident_restoration"));

        new SpiritRepairRecipeBuilder(SizedIngredient.of(Items.WIND_CHARGE, 8), 0.5f)
                .withValidItem(Items.MACE)
                .addSpirit(AERIAL_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .unlockedBy("has_pylon", has)
                .save(recipeOutput, MalumMod.malumPath("mace_restoration"));

        new SpiritRepairRecipeBuilder(SizedIngredient.of(Items.OBSIDIAN, 2), 0.75f)
                .withValidItem(MalumItems.TYRVING.get())
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 8)
                .addSpirit(EARTHEN_SPIRIT, 8)
                .addSpirit(ELDRITCH_SPIRIT, 4)
                .unlockedBy("has_pylon", has)
                .save(recipeOutput, MalumMod.malumPath("tyrving_restoration"));

        new SpiritRepairRecipeBuilder(SizedIngredient.of(MalumItems.SOUL_STAINED_STEEL_INGOT.get(), 2), 0.75f)
                .withValidItem(MalumItems.SOUL_STAINED_STEEL_SCYTHE.get())
                .withValidItem(MalumItems.SOUL_STAINED_STEEL_SWORD.get())
                .withValidItem(MalumItems.SOUL_STAINED_STEEL_AXE.get())
                .withValidItem(MalumItems.SOUL_STAINED_STEEL_PICKAXE.get())
                .withValidItem(MalumItems.SOUL_STAINED_STEEL_SHOVEL.get())
                .withValidItem(MalumItems.SOUL_STAINED_STEEL_HOE.get())
                .withValidItem(MalumItems.SOUL_STAINED_STEEL_KNIFE.get())
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 8)
                .addSpirit(EARTHEN_SPIRIT, 4)
                .unlockedBy("has_pylon", has)
                .save(recipeOutput, MalumMod.malumPath("soul_stained_steel_restoration"));

        new SpiritRepairRecipeBuilder(SizedIngredient.of(MalumItems.SOUL_STAINED_STEEL_PLATING.get(), 3), 0.75f)
                .withValidItem(MalumItems.SOUL_STAINED_STEEL_HELMET.get())
                .withValidItem(MalumItems.SOUL_STAINED_STEEL_CHESTPLATE.get())
                .withValidItem(MalumItems.SOUL_STAINED_STEEL_LEGGINGS.get())
                .withValidItem(MalumItems.SOUL_STAINED_STEEL_BOOTS.get())
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 8)
                .addSpirit(EARTHEN_SPIRIT, 4)
                .unlockedBy("has_pylon", has)
                .save(recipeOutput, MalumMod.malumPath("soul_stained_steel_armor_restoration"));

        new SpiritRepairRecipeBuilder(SizedIngredient.of(MalumItems.SOULWOVEN_SILK.get(), 2), 0.75f)
                .withValidItem(MalumItems.SOUL_HUNTER_CLOAK.get())
                .withValidItem(MalumItems.SOUL_HUNTER_ROBE.get())
                .withValidItem(MalumItems.SOUL_HUNTER_LEGGINGS.get())
                .withValidItem(MalumItems.SOUL_HUNTER_BOOTS.get())
                .addSpirit(ARCANE_SPIRIT, 8)
                .addSpirit(AERIAL_SPIRIT, 8)
                .unlockedBy("has_pylon", has)
                .save(recipeOutput, MalumMod.malumPath("soul_hunter_armor_restoration"));

        new SpiritRepairRecipeBuilder(SizedIngredient.of(MalumItems.MALIGNANT_LEAD.get(), 8), 0.75f)
                .withValidItem(MalumItems.CATALYST_LOBBER.get())
                .addSpirit(ELDRITCH_SPIRIT, 8)
                .addSpirit(INFERNAL_SPIRIT, 8)
                .addSpirit(AQUEOUS_SPIRIT, 8)
                .unlockedBy("has_pylon", has)
                .save(recipeOutput, MalumMod.malumPath("catalyst_lobber_restoration"));

        new SpiritRepairRecipeBuilder(SizedIngredient.of(MalumItems.MNEMONIC_FRAGMENT.get(), 8), 0.75f)
                .withValidItem(MalumItems.MNEMONIC_HEX_STAFF.get())
                .addSpirit(ELDRITCH_SPIRIT, 8)
                .addSpirit(AERIAL_SPIRIT, 8)
                .addSpirit(AQUEOUS_SPIRIT, 8)
                .unlockedBy("has_pylon", has)
                .save(recipeOutput, MalumMod.malumPath("mnemonic_hex_staff_restoration"));

        new SpiritRepairRecipeBuilder(SizedIngredient.of(MalumItems.VOID_SALTS.get(), 8), 0.75f)
                .withValidItem(MalumItems.EROSION_SCEPTER.get())
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .unlockedBy("has_pylon", has)
                .save(recipeOutput, MalumMod.malumPath("erosion_scepter_restoration"));

        new SpiritRepairRecipeBuilder(SizedIngredient.of(MalumItems.MALIGNANT_PEWTER_PLATING.get(), 2), 0.75f)
                .withValidItem(MalumItems.MALIGNANT_STRONGHOLD_HELMET.get())
                .withValidItem(MalumItems.MALIGNANT_STRONGHOLD_CHESTPLATE.get())
                .withValidItem(MalumItems.MALIGNANT_STRONGHOLD_LEGGINGS.get())
                .withValidItem(MalumItems.MALIGNANT_STRONGHOLD_BOOTS.get())
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .unlockedBy("has_pylon", has)
                .save(recipeOutput, MalumMod.malumPath("malignant_stronghold_armor_restoration"));

        new SpiritRepairRecipeBuilder(SizedIngredient.of(MalumItems.MALIGNANT_PEWTER_INGOT.get(), 1), 0.75f)
                .withValidItem(MalumItems.WEIGHT_OF_WORLDS.get())
                .withValidItem(MalumItems.EDGE_OF_DELIVERANCE.get())
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .unlockedBy("has_pylon", has)
                .save(recipeOutput, MalumMod.malumPath("malignant_weapon_restoration"));

        new SpiritRepairRecipeBuilder(SizedIngredient.of(MalumItems.AURIC_EMBERS.get(), 8), 0.75f)
                .withValidItem(MalumItems.UNWINDING_CHAOS.get())
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .addSpirit(INFERNAL_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .unlockedBy("has_pylon", has)
                .save(recipeOutput, MalumMod.malumPath("unwinding_chaos_restoration"));

        new SpiritRepairRecipeBuilder(SizedIngredient.of(MalumItems.LIVING_FLESH.get(), 8), 0.75f)
                .withValidItem(MalumItems.SUNDERING_ANCHOR.get())
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .unlockedBy("has_pylon", has)
                .save(recipeOutput, MalumMod.malumPath("sundering_anchor_restoration"));

        new SpiritRepairRecipeBuilder(SizedIngredient.of(MalumItems.ALCHEMICAL_CALX.get(), 2), 1f)
                .withValidItem(MalumItems.FRACTURED_ALCHEMICAL_IMPETUS.get())
                .addSpirit(ARCANE_SPIRIT, 4)
                .addSpirit(EARTHEN_SPIRIT, 4)
                .unlockedBy("has_pylon", has)
                .save(recipeOutput, MalumMod.malumPath("alchemical_impetus_restoration"));

        new SpiritRepairRecipeBuilder(SizedIngredient.of(MalumItems.WIND_NUCLEUS.get(), 4), 1f)
                .withValidItem(MalumItems.FRACTURED_ZEPHYR_IMPETUS.get())
                .addSpirit(ARCANE_SPIRIT, 8)
                .addSpirit(AERIAL_SPIRIT, 8)
                .unlockedBy("has_pylon", has)
                .save(recipeOutput, MalumMod.malumPath("zephyr_impetus_restoration"));

        metalImpetusRestoration(recipeOutput, MalumItems.FRACTURED_IRON_IMPETUS);
        metalImpetusRestoration(recipeOutput, MalumItems.FRACTURED_COPPER_IMPETUS);
        metalImpetusRestoration(recipeOutput, MalumItems.FRACTURED_GOLD_IMPETUS);
        metalImpetusRestoration(recipeOutput, MalumItems.FRACTURED_ALUMINUM_IMPETUS);
        metalImpetusRestoration(recipeOutput, MalumItems.FRACTURED_NICKEL_IMPETUS);
        metalImpetusRestoration(recipeOutput, MalumItems.FRACTURED_SILVER_IMPETUS);
        metalImpetusRestoration(recipeOutput, MalumItems.FRACTURED_TIN_IMPETUS);
        metalImpetusRestoration(recipeOutput, MalumItems.FRACTURED_ZINC_IMPETUS);
        metalImpetusRestoration(recipeOutput, MalumItems.FRACTURED_OSMIUM_IMPETUS);
        metalImpetusRestoration(recipeOutput, MalumItems.FRACTURED_LEAD_IMPETUS);
        metalImpetusRestoration(recipeOutput, MalumItems.FRACTURED_URANIUM_IMPETUS);
        metalImpetusRestoration(recipeOutput, MalumItems.FRACTURED_COBALT_IMPETUS);
    }
    @SuppressWarnings({"DataFlowIssue"})
    public static void metalImpetusRestoration(RecipeOutput recipeOutput, Holder<Item> crackedImpetus) {
        var id = crackedImpetus.getKey().location().withSuffix("_restoration");
        var has = MalumRecipes.has(MalumItems.REPAIR_PYLON.get());
        new SpiritRepairRecipeBuilder(SizedIngredient.of(MalumItems.CTHONIC_GOLD_FRAGMENT.get(), 2), 1f)
                .withValidItem(crackedImpetus.value())
                .addSpirit(AQUEOUS_SPIRIT, 8)
                .addSpirit(INFERNAL_SPIRIT, 8)
                .unlockedBy("has_pylon", has)
                .save(recipeOutput, id);
    }
}