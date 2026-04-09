package com.sammy.malum.datagen.recipe.infusion;

import com.sammy.malum.datagen.recipe.builder.*;
import com.sammy.malum.registry.common.content.MalumContent;
import com.sammy.malum.registry.common.content.item.MalumItemProperties;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.*;
import net.neoforged.neoforge.common.crafting.*;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class CurioSpiritInfusionRecipes {

    public static void buildRecipes(RecipeOutput recipeOutput) {

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.RUNIC_BROOCH.get(), 1, MalumContent.Gear.GLASS_BROOCH.get(), 1)
                .addExtraItem(SizedIngredient.of(Tags.Items.GEMS_DIAMOND, 8))
                .addExtraItem(MalumContent.Materials.EERIE_WEAVE.get(), 4)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.ELABORATE_BROOCH.get(), 1, MalumContent.Gear.GLUTTONOUS_BROOCH.get(), 1)
                .addExtraItem(MalumContent.Materials.ROTTING_ESSENCE.get(), 8)
                .addExtraItem(MalumContent.Materials.GRIM_TALC.get(), 4)
                .addSpirit(WICKED_SPIRIT, 32)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.GILDED_RING.get(), 1, MalumContent.Gear.RING_OF_ESOTERIC_SPOILS.get(), 1)
                .addExtraItem(MalumContent.Materials.REFINED_SOULSTONE.get(), 8)
                .addSpirit(WICKED_SPIRIT, 8)
                .addSpirit(AQUEOUS_SPIRIT, 8)
                .addSpirit(ELDRITCH_SPIRIT, 8)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.ORNATE_RING.get(), 1, MalumContent.Gear.RING_OF_ESOTERIC_SHADOW.get(), 1)
                .addExtraItem(MalumContent.Materials.REFINED_SOULSTONE.get(), 8)
                .addSpirit(SACRED_SPIRIT, 8)
                .addSpirit(AERIAL_SPIRIT, 8)
                .addSpirit(ELDRITCH_SPIRIT, 8)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.GILDED_RING.get(), 1, MalumContent.Gear.RING_OF_CURATIVE_TALENT.get(), 1)
                .addExtraItem(MalumContent.Materials.LIVING_FLESH.get(), 4)
                .addExtraItem(MalumContent.Materials.ALCHEMICAL_CALX.get(), 4)
                .addExtraItem(Items.GHAST_TEAR, 1)
                .addSpirit(SACRED_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.GILDED_RING.get(), 1, MalumContent.Gear.RING_OF_ALCHEMICAL_MASTERY.get(), 1)
                .addExtraItem(MalumContent.Materials.HEX_ASH.get(), 2)
                .addExtraItem(Items.NETHER_WART, 4)
                .addExtraItem(Items.FERMENTED_SPIDER_EYE, 1)
                .addExtraItem(MalumContent.Materials.ALCHEMICAL_CALX.get(), 4)
                .addSpirit(ARCANE_SPIRIT, 16)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.ORNATE_RING.get(), 1, MalumContent.Gear.RING_OF_MANAWEAVING.get(), 1)
                .addExtraItem(MalumContent.Materials.SOUL_STAINED_STEEL_PLATING.get(), 6)
                .addExtraItem(MalumContent.Materials.REFINED_SOULSTONE.get(), 4)
                .addSpirit(ARCANE_SPIRIT, 16)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.ORNATE_RING.get(), 1, MalumContent.Gear.RING_OF_ARCANE_PROWESS.get(), 1)
                .addExtraItem(MalumContent.Materials.RAW_BRILLIANCE.get(), 4)
                .addExtraItem(MalumContent.Materials.ALCHEMICAL_CALX.get(), 4)
                .addSpirit(ARCANE_SPIRIT, 32)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.GILDED_RING.get(), 1, MalumContent.Gear.RING_OF_DESPERATE_VORACITY.get(), 1)
                .addExtraItem(Items.ROTTEN_FLESH, 4)
                .addExtraItem(MalumContent.Materials.GRIM_TALC.get(), 4)
                .addExtraItem(Items.BONE, 4)
                .addExtraItem(MalumContent.Materials.ROTTING_ESSENCE.get(), 8)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(SACRED_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.ORNATE_RING.get(), 1, MalumContent.Gear.RING_OF_SWARMING_ROT.get(), 1)
                .addExtraItem(Items.SPIDER_EYE, 4)
                .addExtraItem(MalumContent.Materials.GRIM_TALC.get(), 4)
                .addExtraItem(Items.BONE, 4)
                .addExtraItem(MalumContent.Materials.ROTTING_ESSENCE.get(), 8)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.GILDED_RING.get(), 1, MalumContent.Gear.RING_OF_THE_RISING_EDGE.get(), 1)
                .addExtraItem(SizedIngredient.of(Tags.Items.INGOTS_IRON, 6))
                .addExtraItem(MalumContent.Materials.WIND_NUCLEUS.get(), 4)
                .addExtraItem(Items.WIND_CHARGE, 4)
                .addSpirit(AERIAL_SPIRIT, 32)
                .addSpirit(SACRED_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.ORNATE_RING.get(), 1, MalumContent.Gear.RING_OF_THE_HOWLING_MAELSTROM.get(), 1)
                .addExtraItem(SizedIngredient.of(Tags.Items.INGOTS_IRON, 6))
                .addExtraItem(MalumContent.Materials.WIND_NUCLEUS.get(), 4)
                .addExtraItem(Items.WIND_CHARGE, 4)
                .addSpirit(AERIAL_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.GILDED_RING.get(), 1, MalumContent.Gear.RING_OF_HEARTY_AVARICE.get(), 1)
                .addExtraItem(SizedIngredient.of(Tags.Items.INGOTS_IRON, 6))
                .addExtraItem(MalumContent.Materials.LIVING_FLESH.get(), 8)
                .addExtraItem(Items.ROTTEN_FLESH, 4)
                .addSpirit(SACRED_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.ORNATE_RING.get(), 1, MalumContent.Gear.RING_OF_HEAVY_DISCHARGE.get(), 1)
                .addExtraItem(SizedIngredient.of(Tags.Items.INGOTS_IRON, 6))
                .addExtraItem(MalumContent.Materials.PYRE_NUCLEUS.get(), 8)
                .addExtraItem(Items.BLAZE_POWDER, 4)
                .addSpirit(INFERNAL_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.ORNATE_NECKLACE.get(), 1, MalumContent.Gear.NECKLACE_OF_THE_NARROW_EDGE.get(), 1)
                .addExtraItem(SizedIngredient.of(Tags.Items.STORAGE_BLOCKS_IRON, 1))
                .addExtraItem(SizedIngredient.of(Tags.Items.INGOTS_IRON, 2))
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 8)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.ORNATE_NECKLACE.get(), 1, MalumContent.Gear.NECKLACE_OF_MYSTIC_POTENCY.get(), 1)
                .addExtraItem(MalumItemProperties.RUNEWOOD_PLANKS.get(), 8)
                .addExtraItem(MalumContent.Materials.WARP_FLUX.get(), 4)
                .addSpirit(SACRED_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.GILDED_BELT.get(), 1, MalumContent.Gear.BELT_OF_THE_STARVED.get(), 1)
                .addExtraItem(Items.BONE, 4)
                .addExtraItem(MalumContent.Materials.GRIM_TALC.get(), 8)
                .addExtraItem(MalumContent.Materials.ROTTING_ESSENCE.get(), 4)
                .addExtraItem(MalumContent.Materials.REFINED_SOULSTONE.get(), 4)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.GILDED_BELT.get(), 1, MalumContent.Gear.BELT_OF_THE_PROSPECTOR.get(), 1)
                .addExtraItem(MalumContent.Materials.CTHONIC_GOLD.get(), 1)
                .addExtraItem(Items.RAW_GOLD, 4)
                .addExtraItem(Items.RAW_IRON, 4)
                .addExtraItem(Items.RAW_COPPER, 4)
                .addSpirit(EARTHEN_SPIRIT, 32)
                .addSpirit(INFERNAL_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.GILDED_BELT.get(), 1, MalumContent.Gear.BELT_OF_THE_MAGEBANE.get(), 1)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .addExtraItem(MalumItemProperties.TWISTED_ROCK.get(), 16)
                .addExtraItem(MalumItemProperties.SOULWOOD_PLANKS.get(), 8)
                .addExtraItem(MalumContent.Materials.SOUL_STAINED_STEEL_PLATING.get(), 6)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.RING_OF_ESOTERIC_SPOILS.get(), 1, MalumContent.Gear.RING_OF_THE_ENDLESS_WELL.get(), 1)
                .addExtraItem(MalumContent.Materials.NULL_SLATE.get(), 16)
                .addExtraItem(MalumContent.Materials.MNEMONIC_FRAGMENT.get(), 8)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.RING_OF_DESPERATE_VORACITY.get(), 1, MalumContent.Gear.RING_OF_GRUESOME_CONCENTRATION.get(), 1)
                .addExtraItem(MalumContent.Materials.NULL_SLATE.get(), 16)
                .addExtraItem(MalumContent.Materials.VOID_SALTS.get(), 8)
                .addSpirit(SACRED_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.RING_OF_CURATIVE_TALENT.get(), 1, MalumContent.Gear.RING_OF_GROWING_FLESH.get(), 1)
                .addExtraItem(MalumContent.Materials.NULL_SLATE.get(), 16)
                .addExtraItem(MalumContent.Materials.VOID_SALTS.get(), 8)
                .addSpirit(SACRED_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.RING_OF_MANAWEAVING.get(), 1, MalumContent.Gear.RING_OF_ECHOING_ARCANA.get(), 1)
                .addExtraItem(MalumContent.Materials.NULL_SLATE.get(), 16)
                .addExtraItem(MalumContent.Materials.MNEMONIC_FRAGMENT.get(), 8)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.NECKLACE_OF_THE_NARROW_EDGE.get(), 1, MalumContent.Gear.NECKLACE_OF_THE_HIDDEN_BLADE.get(), 1)
                .addExtraItem(MalumContent.Materials.NULL_SLATE.get(), 16)
                .addExtraItem(MalumContent.Materials.MALIGNANT_LEAD.get(), 1)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(EARTHEN_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.NECKLACE_OF_MYSTIC_POTENCY.get(), 1, MalumContent.Gear.NECKLACE_OF_THE_WATCHER.get(), 1)
                .addExtraItem(MalumContent.Materials.NULL_SLATE.get(), 16)
                .addExtraItem(MalumContent.Materials.MALIGNANT_LEAD.get(), 1)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Gear.BELT_OF_THE_MAGEBANE.get(), 1, MalumContent.Gear.BELT_OF_THE_LIMITLESS.get(), 1)
                .addExtraItem(MalumContent.Materials.FUSED_CONSCIOUSNESS.get(), 1)
                .addExtraItem(MalumContent.Materials.VOID_SALTS.get(), 16)
                .addExtraItem(MalumContent.Materials.NULL_SLATE.get(), 8)
                .addExtraItem(MalumContent.Materials.MNEMONIC_FRAGMENT.get(), 4)
                .addSpirit(SACRED_SPIRIT, 64)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .save(recipeOutput);
    }
}
