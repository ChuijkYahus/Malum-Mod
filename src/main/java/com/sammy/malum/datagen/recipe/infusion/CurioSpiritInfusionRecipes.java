package com.sammy.malum.datagen.recipe.infusion;

import com.sammy.malum.datagen.recipe.builder.*;
import com.sammy.malum.registry.common.MalumContent;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.*;
import net.neoforged.neoforge.common.crafting.*;

import static com.sammy.malum.registry.common.MalumContent.BlockSets.*;
import static com.sammy.malum.registry.common.MalumContent.Materials.*;
import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class CurioSpiritInfusionRecipes {

    public static void buildRecipes(RecipeOutput recipeOutput) {

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.RUNIC_BROOCH, 1, MalumContent.Gear.GLASS_BROOCH, 1)
                .addExtraItem(SizedIngredient.of(Tags.Items.GEMS_DIAMOND, 8))
                .addExtraItem(EERIE_WEAVE, 4)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.ELABORATE_BROOCH, 1, MalumContent.Gear.GLUTTONOUS_BROOCH, 1)
                .addExtraItem(ROTTING_ESSENCE, 8)
                .addExtraItem(GRIM_TALC, 4)
                .addSpirit(WICKED_SPIRIT, 32)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.GILDED_RING, 1, MalumContent.Gear.RING_OF_ESOTERIC_SPOILS, 1)
                .addExtraItem(REFINED_SOULSTONE, 8)
                .addSpirit(WICKED_SPIRIT, 8)
                .addSpirit(AQUEOUS_SPIRIT, 8)
                .addSpirit(ELDRITCH_SPIRIT, 8)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.ORNATE_RING, 1, MalumContent.Gear.RING_OF_ESOTERIC_SHADOW, 1)
                .addExtraItem(REFINED_SOULSTONE, 8)
                .addSpirit(SACRED_SPIRIT, 8)
                .addSpirit(AERIAL_SPIRIT, 8)
                .addSpirit(ELDRITCH_SPIRIT, 8)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.GILDED_RING, 1, MalumContent.Gear.RING_OF_CURATIVE_TALENT, 1)
                .addExtraItem(LIVING_FLESH, 4)
                .addExtraItem(ALCHEMICAL_CALX, 4)
                .addExtraItem(Items.GHAST_TEAR, 1)
                .addSpirit(SACRED_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.GILDED_RING, 1, MalumContent.Gear.RING_OF_ALCHEMICAL_MASTERY, 1)
                .addExtraItem(HEX_ASH, 2)
                .addExtraItem(Items.NETHER_WART, 4)
                .addExtraItem(Items.FERMENTED_SPIDER_EYE, 1)
                .addExtraItem(ALCHEMICAL_CALX, 4)
                .addSpirit(ARCANE_SPIRIT, 16)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.ORNATE_RING, 1, MalumContent.Gear.RING_OF_MANAWEAVING, 1)
                .addExtraItem(SOUL_STAINED_STEEL_PLATING, 6)
                .addExtraItem(REFINED_SOULSTONE, 4)
                .addSpirit(ARCANE_SPIRIT, 16)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.ORNATE_RING, 1, MalumContent.Gear.RING_OF_ARCANE_PROWESS, 1)
                .addExtraItem(RAW_BRILLIANCE, 4)
                .addExtraItem(ALCHEMICAL_CALX, 4)
                .addSpirit(ARCANE_SPIRIT, 32)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.GILDED_RING, 1, MalumContent.Gear.RING_OF_DESPERATE_VORACITY, 1)
                .addExtraItem(Items.ROTTEN_FLESH, 4)
                .addExtraItem(GRIM_TALC, 4)
                .addExtraItem(Items.BONE, 4)
                .addExtraItem(ROTTING_ESSENCE, 8)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(SACRED_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.ORNATE_RING, 1, MalumContent.Gear.RING_OF_SWARMING_ROT, 1)
                .addExtraItem(Items.SPIDER_EYE, 4)
                .addExtraItem(GRIM_TALC, 4)
                .addExtraItem(Items.BONE, 4)
                .addExtraItem(ROTTING_ESSENCE, 8)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.GILDED_RING, 1, MalumContent.Gear.RING_OF_THE_RISING_EDGE, 1)
                .addExtraItem(SizedIngredient.of(Tags.Items.INGOTS_IRON, 6))
                .addExtraItem(WIND_NUCLEUS, 4)
                .addExtraItem(Items.WIND_CHARGE, 4)
                .addSpirit(AERIAL_SPIRIT, 32)
                .addSpirit(SACRED_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.ORNATE_RING, 1, MalumContent.Gear.RING_OF_THE_HOWLING_MAELSTROM, 1)
                .addExtraItem(SizedIngredient.of(Tags.Items.INGOTS_IRON, 6))
                .addExtraItem(WIND_NUCLEUS, 4)
                .addExtraItem(Items.WIND_CHARGE, 4)
                .addSpirit(AERIAL_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.GILDED_RING, 1, MalumContent.Gear.RING_OF_HEARTY_AVARICE, 1)
                .addExtraItem(SizedIngredient.of(Tags.Items.INGOTS_IRON, 6))
                .addExtraItem(LIVING_FLESH, 8)
                .addExtraItem(Items.ROTTEN_FLESH, 4)
                .addSpirit(SACRED_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.ORNATE_RING, 1, MalumContent.Gear.RING_OF_HEAVY_DISCHARGE, 1)
                .addExtraItem(SizedIngredient.of(Tags.Items.INGOTS_IRON, 6))
                .addExtraItem(PYRE_NUCLEUS, 8)
                .addExtraItem(Items.BLAZE_POWDER, 4)
                .addSpirit(INFERNAL_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.ORNATE_NECKLACE, 1, MalumContent.Gear.NECKLACE_OF_THE_NARROW_EDGE, 1)
                .addExtraItem(SizedIngredient.of(Tags.Items.STORAGE_BLOCKS_IRON, 1))
                .addExtraItem(SizedIngredient.of(Tags.Items.INGOTS_IRON, 2))
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 8)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.ORNATE_NECKLACE, 1, MalumContent.Gear.NECKLACE_OF_MYSTIC_POTENCY, 1)
                .addExtraItem(RUNEWOOD_SET.planks.block, 8)
                .addExtraItem(WARP_FLUX, 4)
                .addSpirit(SACRED_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.GILDED_BELT, 1, MalumContent.Gear.BELT_OF_THE_STARVED, 1)
                .addExtraItem(Items.BONE, 4)
                .addExtraItem(GRIM_TALC, 8)
                .addExtraItem(ROTTING_ESSENCE, 4)
                .addExtraItem(REFINED_SOULSTONE, 4)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.GILDED_BELT, 1, MalumContent.Gear.BELT_OF_THE_PROSPECTOR, 1)
                .addExtraItem(CTHONIC_GOLD, 1)
                .addExtraItem(Items.RAW_GOLD, 4)
                .addExtraItem(Items.RAW_IRON, 4)
                .addExtraItem(Items.RAW_COPPER, 4)
                .addSpirit(EARTHEN_SPIRIT, 32)
                .addSpirit(INFERNAL_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.GILDED_BELT, 1, MalumContent.Gear.BELT_OF_THE_MAGEBANE, 1)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .addExtraItem(TWISTED_ROCK_SET.rock.block, 16)
                .addExtraItem(SOULWOOD_SET.planks.block, 8)
                .addExtraItem(SOUL_STAINED_STEEL_PLATING, 6)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.RING_OF_ESOTERIC_SPOILS, 1, MalumContent.Gear.RING_OF_THE_ENDLESS_WELL, 1)
                .addExtraItem(NULL_SLATE, 16)
                .addExtraItem(MNEMONIC_FRAGMENT, 8)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.RING_OF_DESPERATE_VORACITY, 1, MalumContent.Gear.RING_OF_GRUESOME_CONCENTRATION, 1)
                .addExtraItem(NULL_SLATE, 16)
                .addExtraItem(VOID_SALTS, 8)
                .addSpirit(SACRED_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.RING_OF_CURATIVE_TALENT, 1, MalumContent.Gear.RING_OF_GROWING_FLESH, 1)
                .addExtraItem(NULL_SLATE, 16)
                .addExtraItem(VOID_SALTS, 8)
                .addSpirit(SACRED_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.RING_OF_MANAWEAVING, 1, MalumContent.Gear.RING_OF_ECHOING_ARCANA, 1)
                .addExtraItem(NULL_SLATE, 16)
                .addExtraItem(MNEMONIC_FRAGMENT, 8)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.NECKLACE_OF_THE_NARROW_EDGE, 1, MalumContent.Gear.NECKLACE_OF_THE_HIDDEN_BLADE, 1)
                .addExtraItem(NULL_SLATE, 16)
                .addExtraItem(MALIGNANT_LEAD, 1)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(EARTHEN_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.NECKLACE_OF_MYSTIC_POTENCY, 1, MalumContent.Gear.NECKLACE_OF_THE_WATCHER, 1)
                .addExtraItem(NULL_SLATE, 16)
                .addExtraItem(MALIGNANT_LEAD, 1)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.BELT_OF_THE_MAGEBANE, 1, MalumContent.Gear.BELT_OF_THE_LIMITLESS, 1)
                .addExtraItem(FUSED_CONSCIOUSNESS, 1)
                .addExtraItem(VOID_SALTS, 16)
                .addExtraItem(NULL_SLATE, 8)
                .addExtraItem(MNEMONIC_FRAGMENT, 4)
                .addSpirit(SACRED_SPIRIT, 64)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .save(recipeOutput);
    }
}
