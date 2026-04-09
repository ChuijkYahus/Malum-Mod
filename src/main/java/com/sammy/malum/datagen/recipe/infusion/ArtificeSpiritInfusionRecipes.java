package com.sammy.malum.datagen.recipe.infusion;

import com.sammy.malum.datagen.recipe.builder.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.content.MalumContent;
import com.sammy.malum.registry.common.content.item.MalumItemProperties;

import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.crafting.SizedIngredient;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class ArtificeSpiritInfusionRecipes {

    public static void buildRecipes(RecipeOutput recipeOutput) {
        new SpiritInfusionRecipeBuilder(net.minecraft.world.item.Items.COPPER_BLOCK, 1, MalumItemProperties.WAVECHARGER.get(), 2)
                .addSpirit(AERIAL_SPIRIT, 4)
                .addSpirit(ARCANE_SPIRIT, 2)
                .addExtraItem(net.minecraft.world.item.Items.REDSTONE, 4)
                .addExtraItem(MalumItemProperties.ETHER.get(), 1)
                .addExtraItem(MalumTags.Items.RUNEWOOD_PLANKS, 2)
                .save(recipeOutput);
        new SpiritInfusionRecipeBuilder(net.minecraft.world.item.Items.COPPER_BLOCK, 1, MalumItemProperties.WAVEBANKER.get(), 2)
                .addSpirit(AQUEOUS_SPIRIT, 4)
                .addSpirit(ARCANE_SPIRIT, 2)
                .addExtraItem(net.minecraft.world.item.Items.REDSTONE, 4)
                .addExtraItem(MalumItemProperties.ETHER.get(), 1)
                .addExtraItem(MalumTags.Items.RUNEWOOD_PLANKS, 2)
                .save(recipeOutput);
        new SpiritInfusionRecipeBuilder(net.minecraft.world.item.Items.COPPER_BLOCK, 1, MalumItemProperties.WAVEMAKER.get(), 2)
                .addSpirit(EARTHEN_SPIRIT, 4)
                .addSpirit(ARCANE_SPIRIT, 2)
                .addExtraItem(net.minecraft.world.item.Items.REDSTONE, 4)
                .addExtraItem(MalumItemProperties.ETHER.get(), 1)
                .addExtraItem(MalumTags.Items.RUNEWOOD_PLANKS, 2)
                .save(recipeOutput);
        new SpiritInfusionRecipeBuilder(net.minecraft.world.item.Items.COPPER_BLOCK, 1, MalumItemProperties.WAVEBREAKER.get(), 2)
                .addSpirit(INFERNAL_SPIRIT, 4)
                .addSpirit(ARCANE_SPIRIT, 2)
                .addExtraItem(net.minecraft.world.item.Items.REDSTONE, 4)
                .addExtraItem(MalumItemProperties.ETHER.get(), 1)
                .addExtraItem(MalumTags.Items.RUNEWOOD_PLANKS, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Ingredient.of(Tags.Items.INGOTS_COPPER), 2, MalumContent.Progression.ARTIFICERS_CLAW.get(), 1)
                .addSpirit(EARTHEN_SPIRIT, 8)
                .addSpirit(AQUEOUS_SPIRIT, 8)
                .addExtraItem(MalumItemProperties.RUNEWOOD_PLANKS.get(), 2)
                .addExtraItem(net.minecraft.world.item.Items.REDSTONE_BLOCK, 1)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(net.minecraft.world.item.Items.COPPER_BLOCK, 1, MalumItemProperties.GUST_IGNITER.get(), 2)
                .addSpirit(AERIAL_SPIRIT, 8)
                .addExtraItem(net.minecraft.world.item.Items.REDSTONE, 4)
                .addExtraItem(net.minecraft.world.item.Items.WIND_CHARGE, 2)
                .addExtraItem(MalumTags.Items.RUNEWOOD_PLANKS, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(net.minecraft.world.item.Items.COPPER_BLOCK, 1, MalumItemProperties.WIND_TUNNEL.get(), 4)
                .addSpirit(AERIAL_SPIRIT, 8)
                .addExtraItem(net.minecraft.world.item.Items.REDSTONE, 4)
                .addExtraItem(MalumContent.Materials.WIND_NUCLEUS.get(), 2)
                .addExtraItem(MalumTags.Items.RUNEWOOD_PLANKS, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(net.minecraft.world.item.Items.FURNACE, 1, MalumItemProperties.SPIRIT_CRUCIBLE.get(), 1)
                .addSpirit(INFERNAL_SPIRIT, 8)
                .addSpirit(AQUEOUS_SPIRIT, 8)
                .addExtraItem(MalumContent.Materials.HEX_ASH.get(), 2)
                .addExtraItem(MalumItemProperties.TAINTED_ROCK.get(), 8)
                .addExtraItem(MalumItemProperties.TWISTED_ROCK.get(), 8)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Materials.ALCHEMICAL_CALX.get(), 4, MalumContent.Progression.ALCHEMICAL_IMPETUS.get(), 1)
                .addSpirit(ARCANE_SPIRIT, 4)
                .addSpirit(EARTHEN_SPIRIT, 4)
                .addExtraItem(MalumContent.Materials.REFINED_SOULSTONE.get(), 4)
                .addExtraItem(MalumContent.Materials.HEX_ASH.get(), 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Ingredient.of(Tags.Items.INGOTS_IRON), 2, MalumContent.Progression.TUNING_FORK.get(), 1)
                .addSpirit(ARCANE_SPIRIT, 8)
                .addSpirit(AQUEOUS_SPIRIT, 8)
                .addExtraItem(MalumItemProperties.RUNEWOOD_PLANKS.get(), 2)
                .addExtraItem(MalumContent.Materials.REFINED_SOULSTONE.get(), 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Materials.ALCHEMICAL_CALX.get(), 6, MalumContent.Progression.SYMPATHY_DRIVE.get(), 1)
                .addSpirit(SACRED_SPIRIT, 16)
                .addSpirit(WICKED_SPIRIT, 16)
                .addExtraItem(MalumContent.Materials.LIVING_FLESH.get(), 8)
                .addExtraItem(MalumContent.Materials.REFINED_SOULSTONE.get(), 8)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Materials.ALCHEMICAL_CALX.get(), 6, MalumContent.Progression.SUSPICIOUS_DEVICE.get(), 1)
                .addSpirit(ARCANE_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .addExtraItem(MalumContent.Materials.WARP_FLUX.get(), 4)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Materials.ALCHEMICAL_CALX.get(), 6, MalumContent.Progression.CAUSTIC_CATALYST.get(), 1)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(INFERNAL_SPIRIT, 16)
                .addExtraItem(MalumItemProperties.BLAZING_QUARTZ.get(), 8)
                .addExtraItem(net.minecraft.world.item.Items.PRISMARINE_CRYSTALS, 8)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Materials.ALCHEMICAL_CALX.get(), 6, MalumContent.Progression.RESONANCE_TUNER.get(), 1)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .addSpirit(AERIAL_SPIRIT, 16)
                .addExtraItem(MalumContent.Materials.EERIE_WEAVE.get(), 8)
                .addExtraItem(MalumContent.Materials.REFINED_BRILLIANCE.get(), 8)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Materials.ALCHEMICAL_CALX.get(), 4, MalumContent.Progression.MENDING_DIFFUSER.get(), 1)
                .addSpirit(SACRED_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 4)
                .addExtraItem(MalumContent.Materials.LIVING_FLESH.get(), 2)
                .addExtraItem(MalumContent.Materials.REFINED_SOULSTONE.get(), 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Materials.ALCHEMICAL_CALX.get(), 4, MalumContent.Progression.IMPURITY_STABILIZER.get(), 1)
                .addSpirit(WICKED_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 4)
                .addExtraItem(MalumContent.Materials.HEX_ASH.get(), 2)
                .addExtraItem(MalumContent.Materials.REFINED_SOULSTONE.get(), 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Materials.ALCHEMICAL_CALX.get(), 4, MalumContent.Progression.SHIELDING_APPARATUS.get(), 1)
                .addSpirit(ARCANE_SPIRIT, 8)
                .addExtraItem(MalumContent.Materials.SOUL_STAINED_STEEL_PLATING.get(), 2)
                .addExtraItem(MalumContent.Materials.REFINED_SOULSTONE.get(), 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Materials.ALCHEMICAL_CALX.get(), 4, MalumContent.Progression.WARPING_ENGINE.get(), 1)
                .addSpirit(ELDRITCH_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 4)
                .addExtraItem(MalumContent.Materials.WARP_FLUX.get(), 2)
                .addExtraItem(MalumContent.Materials.REFINED_SOULSTONE.get(), 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Materials.ALCHEMICAL_CALX.get(), 4, MalumContent.Progression.ACCELERATING_INLAY.get(), 1)
                .addSpirit(AERIAL_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 4)
                .addExtraItem(MalumContent.Materials.EERIE_WEAVE.get(), 2)
                .addExtraItem(MalumContent.Materials.REFINED_SOULSTONE.get(), 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Materials.ALCHEMICAL_CALX.get(), 4, MalumContent.Progression.PRISMATIC_FOCUS_LENS.get(), 1)
                .addSpirit(AQUEOUS_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 4)
                .addExtraItem(SizedIngredient.of(Tags.Items.GEMS_PRISMARINE, 2))
                .addExtraItem(MalumContent.Materials.REFINED_SOULSTONE.get(), 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Materials.ALCHEMICAL_CALX.get(), 4, MalumContent.Progression.BLAZING_DIODE.get(), 1)
                .addSpirit(INFERNAL_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 4)
                .addExtraItem(MalumItemProperties.BLAZING_QUARTZ.get(), 2)
                .addExtraItem(MalumContent.Materials.REFINED_SOULSTONE.get(), 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Materials.ALCHEMICAL_CALX.get(), 4, MalumContent.Progression.INTRICATE_ASSEMBLY.get(), 1)
                .addSpirit(EARTHEN_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 4)
                .addExtraItem(SizedIngredient.of(Tags.Items.GEMS_EMERALD, 2))
                .addExtraItem(MalumContent.Materials.REFINED_SOULSTONE.get(), 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItemProperties.TWISTED_ROCK_ITEM_PEDESTAL.get(), 1, MalumItemProperties.SPIRIT_CATALYZER.get(), 1)
                .addSpirit(INFERNAL_SPIRIT, 8)
                .addSpirit(AERIAL_SPIRIT, 8)
                .addExtraItem(MalumItemProperties.TAINTED_ROCK.get(), 4)
                .addExtraItem(MalumItemProperties.ETHER.get(), 1)
                .addExtraItem(MalumItemProperties.TWISTED_ROCK.get(), 4)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItemProperties.TAINTED_ROCK_ITEM_PEDESTAL.get(), 1, MalumItemProperties.REPAIR_PYLON.get(), 1)
                .addSpirit(SACRED_SPIRIT, 16)
                .addSpirit(AERIAL_SPIRIT, 16)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(INFERNAL_SPIRIT, 16)
                .addExtraItem(MalumItemProperties.TAINTED_ROCK.get(), 8)
                .addExtraItem(MalumItemProperties.TWISTED_ROCK.get(), 8)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Materials.ALCHEMICAL_CALX.get(), 4, MalumContent.Progression.STELLAR_MECHANISM.get(), 1)
                .addExtraItem(MalumContent.Materials.FUSED_CONSCIOUSNESS.get(), 1)
                .addExtraItem(MalumContent.Materials.NULL_SLATE.get(), 2)
                .addSpirit(AERIAL_SPIRIT, 16)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .addSpirit(INFERNAL_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Progression.ALCHEMICAL_IMPETUS.get(), 1, MalumContent.Progression.ZEPHYR_IMPETUS.get(), 1)
                .addSpirit(AERIAL_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addExtraItem(net.minecraft.world.item.Items.WIND_CHARGE, 8)
                .addExtraItem(MalumContent.Progression.IRON_METALLICS.getNode().get(), 6)
                .addExtraItem(MalumContent.Materials.WIND_NUCLEUS.get(), 4)
                .addExtraItem(net.minecraft.world.item.Items.HEAVY_CORE, 1)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Progression.ALCHEMICAL_IMPETUS.get(), 1, MalumContent.Progression.IFRIT_IMPETUS.get(), 1)
                .addSpirit(INFERNAL_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addExtraItem(net.minecraft.world.item.Items.BLAZE_POWDER, 8)
                .addExtraItem(MalumContent.Progression.IRON_METALLICS.getNode().get(), 6)
                .addExtraItem(MalumContent.Materials.PYRE_NUCLEUS.get(), 4)
                .addExtraItem(net.minecraft.world.item.Items.HEAVY_CORE, 1)
                .save(recipeOutput);

    }
}
