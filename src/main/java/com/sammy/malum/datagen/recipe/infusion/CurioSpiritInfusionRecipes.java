package com.sammy.malum.datagen.recipe.infusion;

import com.sammy.malum.datagen.recipe.builder.*;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.*;
import net.neoforged.neoforge.common.crafting.*;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class CurioSpiritInfusionRecipes {

    public static void buildRecipes(RecipeOutput recipeOutput) {

        new SpiritInfusionRecipeBuilder(MalumItems.RUNIC_BROOCH.get(), 1, MalumItems.GLASS_BROOCH.get(), 1)
                .addExtraItem(SizedIngredient.of(Tags.Items.GEMS_DIAMOND, 8))
                .addExtraItem(MalumItems.EERIE_WEAVE.get(), 4)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.ELABORATE_BROOCH.get(), 1, MalumItems.GLUTTONOUS_BROOCH.get(), 1)
                .addExtraItem(MalumItems.ROTTING_ESSENCE.get(), 8)
                .addExtraItem(MalumItems.GRIM_TALC.get(), 4)
                .addSpirit(WICKED_SPIRIT, 32)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.GILDED_RING.get(), 1, MalumItems.RING_OF_ESOTERIC_SPOILS.get(), 1)
                .addExtraItem(MalumItems.REFINED_SOULSTONE.get(), 8)
                .addSpirit(WICKED_SPIRIT, 8)
                .addSpirit(AQUEOUS_SPIRIT, 8)
                .addSpirit(ELDRITCH_SPIRIT, 8)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.ORNATE_RING.get(), 1, MalumItems.RING_OF_ESOTERIC_SHADOW.get(), 1)
                .addExtraItem(MalumItems.REFINED_SOULSTONE.get(), 8)
                .addSpirit(SACRED_SPIRIT, 8)
                .addSpirit(AERIAL_SPIRIT, 8)
                .addSpirit(ELDRITCH_SPIRIT, 8)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.GILDED_RING.get(), 1, MalumItems.RING_OF_CURATIVE_TALENT.get(), 1)
                .addExtraItem(MalumItems.LIVING_FLESH.get(), 4)
                .addExtraItem(MalumItems.ALCHEMICAL_CALX.get(), 4)
                .addExtraItem(Items.GHAST_TEAR, 1)
                .addSpirit(SACRED_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.GILDED_RING.get(), 1, MalumItems.RING_OF_ALCHEMICAL_MASTERY.get(), 1)
                .addExtraItem(MalumItems.HEX_ASH.get(), 2)
                .addExtraItem(Items.NETHER_WART, 4)
                .addExtraItem(Items.FERMENTED_SPIDER_EYE, 1)
                .addExtraItem(MalumItems.ALCHEMICAL_CALX.get(), 4)
                .addSpirit(ARCANE_SPIRIT, 16)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.ORNATE_RING.get(), 1, MalumItems.RING_OF_MANAWEAVING.get(), 1)
                .addExtraItem(MalumItems.SOUL_STAINED_STEEL_PLATING.get(), 6)
                .addExtraItem(MalumItems.REFINED_SOULSTONE.get(), 4)
                .addSpirit(ARCANE_SPIRIT, 16)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.ORNATE_RING.get(), 1, MalumItems.RING_OF_ARCANE_PROWESS.get(), 1)
                .addExtraItem(MalumItems.RAW_BRILLIANCE.get(), 4)
                .addExtraItem(MalumItems.ALCHEMICAL_CALX.get(), 4)
                .addSpirit(ARCANE_SPIRIT, 32)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.GILDED_RING.get(), 1, MalumItems.RING_OF_DESPERATE_VORACITY.get(), 1)
                .addExtraItem(Items.ROTTEN_FLESH, 4)
                .addExtraItem(MalumItems.GRIM_TALC.get(), 4)
                .addExtraItem(Items.BONE, 4)
                .addExtraItem(MalumItems.ROTTING_ESSENCE.get(), 8)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(SACRED_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.ORNATE_RING.get(), 1, MalumItems.RING_OF_SWARMING_ROT.get(), 1)
                .addExtraItem(Items.SPIDER_EYE, 4)
                .addExtraItem(MalumItems.GRIM_TALC.get(), 4)
                .addExtraItem(Items.BONE, 4)
                .addExtraItem(MalumItems.ROTTING_ESSENCE.get(), 8)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.GILDED_RING.get(), 1, MalumItems.RING_OF_THE_RISING_EDGE.get(), 1)
                .addExtraItem(SizedIngredient.of(Tags.Items.INGOTS_IRON, 6))
                .addExtraItem(MalumItems.WIND_NUCLEUS.get(), 4)
                .addExtraItem(Items.WIND_CHARGE, 4)
                .addSpirit(AERIAL_SPIRIT, 32)
                .addSpirit(SACRED_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.ORNATE_RING.get(), 1, MalumItems.RING_OF_THE_HOWLING_MAELSTROM.get(), 1)
                .addExtraItem(SizedIngredient.of(Tags.Items.INGOTS_IRON, 6))
                .addExtraItem(MalumItems.WIND_NUCLEUS.get(), 4)
                .addExtraItem(Items.WIND_CHARGE, 4)
                .addSpirit(AERIAL_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.GILDED_RING.get(), 1, MalumItems.RING_OF_HEARTY_AVARICE.get(), 1)
                .addExtraItem(SizedIngredient.of(Tags.Items.INGOTS_IRON, 6))
                .addExtraItem(MalumItems.LIVING_FLESH.get(), 8)
                .addExtraItem(Items.ROTTEN_FLESH, 4)
                .addSpirit(SACRED_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.ORNATE_RING.get(), 1, MalumItems.RING_OF_HEAVY_DISCHARGE.get(), 1)
                .addExtraItem(SizedIngredient.of(Tags.Items.INGOTS_IRON, 6))
                .addExtraItem(MalumItems.PYRE_NUCLEUS.get(), 8)
                .addExtraItem(Items.BLAZE_POWDER, 4)
                .addSpirit(INFERNAL_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.ORNATE_NECKLACE.get(), 1, MalumItems.NECKLACE_OF_THE_NARROW_EDGE.get(), 1)
                .addExtraItem(SizedIngredient.of(Tags.Items.STORAGE_BLOCKS_IRON, 1))
                .addExtraItem(SizedIngredient.of(Tags.Items.INGOTS_IRON, 2))
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 8)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.ORNATE_NECKLACE.get(), 1, MalumItems.NECKLACE_OF_MYSTIC_POTENCY.get(), 1)
                .addExtraItem(MalumItems.RUNEWOOD_PLANKS.get(), 8)
                .addExtraItem(MalumItems.WARP_FLUX.get(), 4)
                .addSpirit(SACRED_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.GILDED_BELT.get(), 1, MalumItems.BELT_OF_THE_STARVED.get(), 1)
                .addExtraItem(Items.BONE, 4)
                .addExtraItem(MalumItems.GRIM_TALC.get(), 8)
                .addExtraItem(MalumItems.ROTTING_ESSENCE.get(), 4)
                .addExtraItem(MalumItems.REFINED_SOULSTONE.get(), 4)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.GILDED_BELT.get(), 1, MalumItems.BELT_OF_THE_PROSPECTOR.get(), 1)
                .addExtraItem(MalumItems.CTHONIC_GOLD.get(), 1)
                .addExtraItem(Items.RAW_GOLD, 4)
                .addExtraItem(Items.RAW_IRON, 4)
                .addExtraItem(Items.RAW_COPPER, 4)
                .addSpirit(EARTHEN_SPIRIT, 32)
                .addSpirit(INFERNAL_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.GILDED_BELT.get(), 1, MalumItems.BELT_OF_THE_MAGEBANE.get(), 1)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .addExtraItem(MalumItems.TWISTED_ROCK.get(), 16)
                .addExtraItem(MalumItems.SOULWOOD_PLANKS.get(), 8)
                .addExtraItem(MalumItems.SOUL_STAINED_STEEL_PLATING.get(), 6)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.RING_OF_ESOTERIC_SPOILS.get(), 1, MalumItems.RING_OF_THE_ENDLESS_WELL.get(), 1)
                .addExtraItem(MalumItems.NULL_SLATE.get(), 16)
                .addExtraItem(MalumItems.MNEMONIC_FRAGMENT.get(), 8)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.RING_OF_DESPERATE_VORACITY.get(), 1, MalumItems.RING_OF_GRUESOME_CONCENTRATION.get(), 1)
                .addExtraItem(MalumItems.NULL_SLATE.get(), 16)
                .addExtraItem(MalumItems.VOID_SALTS.get(), 8)
                .addSpirit(SACRED_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.RING_OF_CURATIVE_TALENT.get(), 1, MalumItems.RING_OF_GROWING_FLESH.get(), 1)
                .addExtraItem(MalumItems.NULL_SLATE.get(), 16)
                .addExtraItem(MalumItems.VOID_SALTS.get(), 8)
                .addSpirit(SACRED_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.RING_OF_MANAWEAVING.get(), 1, MalumItems.RING_OF_ECHOING_ARCANA.get(), 1)
                .addExtraItem(MalumItems.NULL_SLATE.get(), 16)
                .addExtraItem(MalumItems.MNEMONIC_FRAGMENT.get(), 8)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.NECKLACE_OF_THE_NARROW_EDGE.get(), 1, MalumItems.NECKLACE_OF_THE_HIDDEN_BLADE.get(), 1)
                .addExtraItem(MalumItems.NULL_SLATE.get(), 16)
                .addExtraItem(MalumItems.MALIGNANT_LEAD.get(), 1)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(EARTHEN_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.NECKLACE_OF_MYSTIC_POTENCY.get(), 1, MalumItems.NECKLACE_OF_THE_WATCHER.get(), 1)
                .addExtraItem(MalumItems.NULL_SLATE.get(), 16)
                .addExtraItem(MalumItems.MALIGNANT_LEAD.get(), 1)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.BELT_OF_THE_MAGEBANE.get(), 1, MalumItems.BELT_OF_THE_LIMITLESS.get(), 1)
                .addExtraItem(MalumItems.FUSED_CONSCIOUSNESS.get(), 1)
                .addExtraItem(MalumItems.VOID_SALTS.get(), 16)
                .addExtraItem(MalumItems.NULL_SLATE.get(), 8)
                .addExtraItem(MalumItems.MNEMONIC_FRAGMENT.get(), 4)
                .addSpirit(SACRED_SPIRIT, 64)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .save(recipeOutput);
    }
}
