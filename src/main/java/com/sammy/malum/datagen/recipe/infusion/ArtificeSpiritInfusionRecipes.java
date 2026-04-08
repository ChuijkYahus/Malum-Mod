package com.sammy.malum.datagen.recipe.infusion;

import com.sammy.malum.datagen.recipe.builder.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.item.*;

import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.crafting.SizedIngredient;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class ArtificeSpiritInfusionRecipes {

    public static void buildRecipes(RecipeOutput recipeOutput) {
        new SpiritInfusionRecipeBuilder(net.minecraft.world.item.Items.COPPER_BLOCK, 1, MalumItems.WAVECHARGER.get(), 2)
                .addSpirit(AERIAL_SPIRIT, 4)
                .addSpirit(ARCANE_SPIRIT, 2)
                .addExtraItem(net.minecraft.world.item.Items.REDSTONE, 4)
                .addExtraItem(MalumItems.ETHER.get(), 1)
                .addExtraItem(MalumTags.Items.RUNEWOOD_PLANKS, 2)
                .save(recipeOutput);
        new SpiritInfusionRecipeBuilder(net.minecraft.world.item.Items.COPPER_BLOCK, 1, MalumItems.WAVEBANKER.get(), 2)
                .addSpirit(AQUEOUS_SPIRIT, 4)
                .addSpirit(ARCANE_SPIRIT, 2)
                .addExtraItem(net.minecraft.world.item.Items.REDSTONE, 4)
                .addExtraItem(MalumItems.ETHER.get(), 1)
                .addExtraItem(MalumTags.Items.RUNEWOOD_PLANKS, 2)
                .save(recipeOutput);
        new SpiritInfusionRecipeBuilder(net.minecraft.world.item.Items.COPPER_BLOCK, 1, MalumItems.WAVEMAKER.get(), 2)
                .addSpirit(EARTHEN_SPIRIT, 4)
                .addSpirit(ARCANE_SPIRIT, 2)
                .addExtraItem(net.minecraft.world.item.Items.REDSTONE, 4)
                .addExtraItem(MalumItems.ETHER.get(), 1)
                .addExtraItem(MalumTags.Items.RUNEWOOD_PLANKS, 2)
                .save(recipeOutput);
        new SpiritInfusionRecipeBuilder(net.minecraft.world.item.Items.COPPER_BLOCK, 1, MalumItems.WAVEBREAKER.get(), 2)
                .addSpirit(INFERNAL_SPIRIT, 4)
                .addSpirit(ARCANE_SPIRIT, 2)
                .addExtraItem(net.minecraft.world.item.Items.REDSTONE, 4)
                .addExtraItem(MalumItems.ETHER.get(), 1)
                .addExtraItem(MalumTags.Items.RUNEWOOD_PLANKS, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Ingredient.of(Tags.Items.INGOTS_COPPER), 2, MalumItems.ARTIFICERS_CLAW.get(), 1)
                .addSpirit(EARTHEN_SPIRIT, 8)
                .addSpirit(AQUEOUS_SPIRIT, 8)
                .addExtraItem(MalumItems.RUNEWOOD_PLANKS.get(), 2)
                .addExtraItem(net.minecraft.world.item.Items.REDSTONE_BLOCK, 1)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(net.minecraft.world.item.Items.COPPER_BLOCK, 1, MalumItems.GUST_IGNITER.get(), 2)
                .addSpirit(AERIAL_SPIRIT, 8)
                .addExtraItem(net.minecraft.world.item.Items.REDSTONE, 4)
                .addExtraItem(net.minecraft.world.item.Items.WIND_CHARGE, 2)
                .addExtraItem(MalumTags.Items.RUNEWOOD_PLANKS, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(net.minecraft.world.item.Items.COPPER_BLOCK, 1, MalumItems.WIND_TUNNEL.get(), 4)
                .addSpirit(AERIAL_SPIRIT, 8)
                .addExtraItem(net.minecraft.world.item.Items.REDSTONE, 4)
                .addExtraItem(MalumItems.WIND_NUCLEUS.get(), 2)
                .addExtraItem(MalumTags.Items.RUNEWOOD_PLANKS, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(net.minecraft.world.item.Items.FURNACE, 1, MalumItems.SPIRIT_CRUCIBLE.get(), 1)
                .addSpirit(INFERNAL_SPIRIT, 8)
                .addSpirit(AQUEOUS_SPIRIT, 8)
                .addExtraItem(MalumItems.HEX_ASH.get(), 2)
                .addExtraItem(MalumItems.TAINTED_ROCK.get(), 8)
                .addExtraItem(MalumItems.TWISTED_ROCK.get(), 8)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.ALCHEMICAL_CALX.get(), 4, MalumItems.ALCHEMICAL_IMPETUS.get(), 1)
                .addSpirit(ARCANE_SPIRIT, 4)
                .addSpirit(EARTHEN_SPIRIT, 4)
                .addExtraItem(MalumItems.REFINED_SOULSTONE.get(), 4)
                .addExtraItem(MalumItems.HEX_ASH.get(), 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Ingredient.of(Tags.Items.INGOTS_IRON), 2, MalumItems.TUNING_FORK.get(), 1)
                .addSpirit(ARCANE_SPIRIT, 8)
                .addSpirit(AQUEOUS_SPIRIT, 8)
                .addExtraItem(MalumItems.RUNEWOOD_PLANKS.get(), 2)
                .addExtraItem(MalumItems.REFINED_SOULSTONE.get(), 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.ALCHEMICAL_CALX.get(), 6, MalumItems.SYMPATHY_DRIVE.get(), 1)
                .addSpirit(SACRED_SPIRIT, 16)
                .addSpirit(WICKED_SPIRIT, 16)
                .addExtraItem(MalumItems.LIVING_FLESH.get(), 8)
                .addExtraItem(MalumItems.REFINED_SOULSTONE.get(), 8)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.ALCHEMICAL_CALX.get(), 6, MalumItems.SUSPICIOUS_DEVICE.get(), 1)
                .addSpirit(ARCANE_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .addExtraItem(MalumItems.WARP_FLUX.get(), 4)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.ALCHEMICAL_CALX.get(), 6, MalumItems.CAUSTIC_CATALYST.get(), 1)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(INFERNAL_SPIRIT, 16)
                .addExtraItem(MalumItems.BLAZING_QUARTZ.get(), 8)
                .addExtraItem(net.minecraft.world.item.Items.PRISMARINE_CRYSTALS, 8)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.ALCHEMICAL_CALX.get(), 6, MalumItems.RESONANCE_TUNER.get(), 1)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .addSpirit(AERIAL_SPIRIT, 16)
                .addExtraItem(MalumItems.EERIE_WEAVE.get(), 8)
                .addExtraItem(MalumItems.REFINED_BRILLIANCE.get(), 8)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.ALCHEMICAL_CALX.get(), 4, MalumItems.MENDING_DIFFUSER.get(), 1)
                .addSpirit(SACRED_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 4)
                .addExtraItem(MalumItems.LIVING_FLESH.get(), 2)
                .addExtraItem(MalumItems.REFINED_SOULSTONE.get(), 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.ALCHEMICAL_CALX.get(), 4, MalumItems.IMPURITY_STABILIZER.get(), 1)
                .addSpirit(WICKED_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 4)
                .addExtraItem(MalumItems.HEX_ASH.get(), 2)
                .addExtraItem(MalumItems.REFINED_SOULSTONE.get(), 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.ALCHEMICAL_CALX.get(), 4, MalumItems.SHIELDING_APPARATUS.get(), 1)
                .addSpirit(ARCANE_SPIRIT, 8)
                .addExtraItem(MalumItems.SOUL_STAINED_STEEL_PLATING.get(), 2)
                .addExtraItem(MalumItems.REFINED_SOULSTONE.get(), 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.ALCHEMICAL_CALX.get(), 4, MalumItems.WARPING_ENGINE.get(), 1)
                .addSpirit(ELDRITCH_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 4)
                .addExtraItem(MalumItems.WARP_FLUX.get(), 2)
                .addExtraItem(MalumItems.REFINED_SOULSTONE.get(), 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.ALCHEMICAL_CALX.get(), 4, MalumItems.ACCELERATING_INLAY.get(), 1)
                .addSpirit(AERIAL_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 4)
                .addExtraItem(MalumItems.EERIE_WEAVE.get(), 2)
                .addExtraItem(MalumItems.REFINED_SOULSTONE.get(), 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.ALCHEMICAL_CALX.get(), 4, MalumItems.PRISMATIC_FOCUS_LENS.get(), 1)
                .addSpirit(AQUEOUS_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 4)
                .addExtraItem(SizedIngredient.of(Tags.Items.GEMS_PRISMARINE, 2))
                .addExtraItem(MalumItems.REFINED_SOULSTONE.get(), 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.ALCHEMICAL_CALX.get(), 4, MalumItems.BLAZING_DIODE.get(), 1)
                .addSpirit(INFERNAL_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 4)
                .addExtraItem(MalumItems.BLAZING_QUARTZ.get(), 2)
                .addExtraItem(MalumItems.REFINED_SOULSTONE.get(), 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.ALCHEMICAL_CALX.get(), 4, MalumItems.INTRICATE_ASSEMBLY.get(), 1)
                .addSpirit(EARTHEN_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 4)
                .addExtraItem(SizedIngredient.of(Tags.Items.GEMS_EMERALD, 2))
                .addExtraItem(MalumItems.REFINED_SOULSTONE.get(), 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.TWISTED_ROCK_ITEM_PEDESTAL.get(), 1, MalumItems.SPIRIT_CATALYZER.get(), 1)
                .addSpirit(INFERNAL_SPIRIT, 8)
                .addSpirit(AERIAL_SPIRIT, 8)
                .addExtraItem(MalumItems.TAINTED_ROCK.get(), 4)
                .addExtraItem(MalumItems.ETHER.get(), 1)
                .addExtraItem(MalumItems.TWISTED_ROCK.get(), 4)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.TAINTED_ROCK_ITEM_PEDESTAL.get(), 1, MalumItems.REPAIR_PYLON.get(), 1)
                .addSpirit(SACRED_SPIRIT, 16)
                .addSpirit(AERIAL_SPIRIT, 16)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(INFERNAL_SPIRIT, 16)
                .addExtraItem(MalumItems.TAINTED_ROCK.get(), 8)
                .addExtraItem(MalumItems.TWISTED_ROCK.get(), 8)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.ALCHEMICAL_CALX.get(), 4, MalumItems.STELLAR_MECHANISM.get(), 1)
                .addExtraItem(MalumItems.FUSED_CONSCIOUSNESS.get(), 1)
                .addExtraItem(MalumItems.NULL_SLATE.get(), 2)
                .addSpirit(AERIAL_SPIRIT, 16)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .addSpirit(INFERNAL_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.ALCHEMICAL_IMPETUS.get(), 1, MalumItems.ZEPHYR_IMPETUS.get(), 1)
                .addSpirit(AERIAL_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addExtraItem(net.minecraft.world.item.Items.WIND_CHARGE, 8)
                .addExtraItem(MalumItems.IRON_METALLICS.getNode().get(), 6)
                .addExtraItem(MalumItems.WIND_NUCLEUS.get(), 4)
                .addExtraItem(net.minecraft.world.item.Items.HEAVY_CORE, 1)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.ALCHEMICAL_IMPETUS.get(), 1, MalumItems.IFRIT_IMPETUS.get(), 1)
                .addSpirit(INFERNAL_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addExtraItem(net.minecraft.world.item.Items.BLAZE_POWDER, 8)
                .addExtraItem(MalumItems.IRON_METALLICS.getNode().get(), 6)
                .addExtraItem(MalumItems.PYRE_NUCLEUS.get(), 4)
                .addExtraItem(net.minecraft.world.item.Items.HEAVY_CORE, 1)
                .save(recipeOutput);

    }
}
