package com.sammy.malum.datagen.recipe.infusion;

import com.sammy.malum.datagen.recipe.builder.*;
import com.sammy.malum.registry.common.*;

import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.crafting.SizedIngredient;

import static com.sammy.malum.registry.common.MalumContent.AlchemyAndMetallics.*;
import static com.sammy.malum.registry.common.MalumContent.BlockSets.*;
import static com.sammy.malum.registry.common.MalumContent.Materials.*;
import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;
import static net.minecraft.world.item.Items.*;

public class ArtificeSpiritInfusionRecipes {

    public static void buildRecipes(RecipeOutput recipeOutput) {
        new SpiritInfusionRecipeBuilder(COPPER_BLOCK, 1, MalumContent.Artifice.WAVECHARGER, 2)
                .addSpirit(AERIAL_SPIRIT, 4)
                .addSpirit(ARCANE_SPIRIT, 2)
                .addExtraItem(REDSTONE, 4)
                .addExtraItem(ETHER, 1)
                .addExtraItem(RUNEWOOD_SET.planks.block, 2)
                .save(recipeOutput);
        new SpiritInfusionRecipeBuilder(COPPER_BLOCK, 1, MalumContent.Artifice.WAVEBANKER, 2)
                .addSpirit(AQUEOUS_SPIRIT, 4)
                .addSpirit(ARCANE_SPIRIT, 2)
                .addExtraItem(REDSTONE, 4)
                .addExtraItem(ETHER, 1)
                .addExtraItem(RUNEWOOD_SET.planks.block, 2)
                .save(recipeOutput);
        new SpiritInfusionRecipeBuilder(COPPER_BLOCK, 1, MalumContent.Artifice.WAVEMAKER, 2)
                .addSpirit(EARTHEN_SPIRIT, 4)
                .addSpirit(ARCANE_SPIRIT, 2)
                .addExtraItem(REDSTONE, 4)
                .addExtraItem(ETHER, 1)
                .addExtraItem(RUNEWOOD_SET.planks.block, 2)
                .save(recipeOutput);
        new SpiritInfusionRecipeBuilder(COPPER_BLOCK, 1, MalumContent.Artifice.WAVEBREAKER, 2)
                .addSpirit(INFERNAL_SPIRIT, 4)
                .addSpirit(ARCANE_SPIRIT, 2)
                .addExtraItem(REDSTONE, 4)
                .addExtraItem(ETHER, 1)
                .addExtraItem(RUNEWOOD_SET.planks.block, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Ingredient.of(Tags.Items.INGOTS_COPPER), 2, MalumContent.Artifice.ARTIFICERS_CLAW, 1)
                .addSpirit(EARTHEN_SPIRIT, 8)
                .addSpirit(AQUEOUS_SPIRIT, 8)
                .addExtraItem(RUNEWOOD_SET.planks.block, 2)
                .addExtraItem(REDSTONE_BLOCK, 1)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(COPPER_BLOCK, 1, MalumContent.Artifice.GUST_IGNITER, 2)
                .addSpirit(AERIAL_SPIRIT, 8)
                .addExtraItem(REDSTONE, 4)
                .addExtraItem(WIND_CHARGE, 2)
                .addExtraItem(RUNEWOOD_SET.planks.block, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(COPPER_BLOCK, 1, MalumContent.Artifice.WIND_TUNNEL, 4)
                .addSpirit(AERIAL_SPIRIT, 8)
                .addExtraItem(REDSTONE, 4)
                .addExtraItem(WIND_NUCLEUS, 2)
                .addExtraItem(RUNEWOOD_SET.planks.block, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(FURNACE, 1, MalumContent.Focusing.SPIRIT_CRUCIBLE, 1)
                .addSpirit(INFERNAL_SPIRIT, 8)
                .addSpirit(AQUEOUS_SPIRIT, 8)
                .addExtraItem(HEX_ASH, 2)
                .addExtraItem(TAINTED_ROCK_SET.rock.block, 8)
                .addExtraItem(TWISTED_ROCK_SET.rock.block, 8)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(ALCHEMICAL_CALX, 4, ALCHEMICAL_IMPETUS, 1)
                .addSpirit(ARCANE_SPIRIT, 4)
                .addSpirit(EARTHEN_SPIRIT, 4)
                .addExtraItem(REFINED_SOULSTONE, 4)
                .addExtraItem(HEX_ASH, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Ingredient.of(Tags.Items.INGOTS_IRON), 2, MalumContent.Focusing.TUNING_FORK, 1)
                .addSpirit(ARCANE_SPIRIT, 8)
                .addSpirit(AQUEOUS_SPIRIT, 8)
                .addExtraItem(RUNEWOOD_SET.planks.block, 2)
                .addExtraItem(REFINED_SOULSTONE, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(ALCHEMICAL_CALX, 6, MalumContent.Focusing.SYMPATHY_DRIVE, 1)
                .addSpirit(SACRED_SPIRIT, 16)
                .addSpirit(WICKED_SPIRIT, 16)
                .addExtraItem(LIVING_FLESH, 8)
                .addExtraItem(REFINED_SOULSTONE, 8)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(ALCHEMICAL_CALX, 6, MalumContent.Focusing.SUSPICIOUS_DEVICE, 1)
                .addSpirit(ARCANE_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .addExtraItem(WARP_FLUX, 4)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(ALCHEMICAL_CALX, 6, MalumContent.Focusing.CAUSTIC_CATALYST, 1)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(INFERNAL_SPIRIT, 16)
                .addExtraItem(BLAZING_QUARTZ, 8)
                .addExtraItem(PRISMARINE_CRYSTALS, 8)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(ALCHEMICAL_CALX, 6, MalumContent.Focusing.RESONANCE_TUNER, 1)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .addSpirit(AERIAL_SPIRIT, 16)
                .addExtraItem(EERIE_WEAVE, 8)
                .addExtraItem(REFINED_BRILLIANCE, 8)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(ALCHEMICAL_CALX, 4, MalumContent.Focusing.MENDING_DIFFUSER, 1)
                .addSpirit(SACRED_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 4)
                .addExtraItem(LIVING_FLESH, 2)
                .addExtraItem(REFINED_SOULSTONE, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(ALCHEMICAL_CALX, 4, MalumContent.Focusing.IMPURITY_STABILIZER, 1)
                .addSpirit(WICKED_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 4)
                .addExtraItem(HEX_ASH, 2)
                .addExtraItem(REFINED_SOULSTONE, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(ALCHEMICAL_CALX, 4, MalumContent.Focusing.SHIELDING_APPARATUS, 1)
                .addSpirit(ARCANE_SPIRIT, 8)
                .addExtraItem(SOUL_STAINED_STEEL_PLATING, 2)
                .addExtraItem(REFINED_SOULSTONE, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(ALCHEMICAL_CALX, 4, MalumContent.Focusing.WARPING_ENGINE, 1)
                .addSpirit(ELDRITCH_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 4)
                .addExtraItem(WARP_FLUX, 2)
                .addExtraItem(REFINED_SOULSTONE, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(ALCHEMICAL_CALX, 4, MalumContent.Focusing.ACCELERATING_INLAY, 1)
                .addSpirit(AERIAL_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 4)
                .addExtraItem(EERIE_WEAVE, 2)
                .addExtraItem(REFINED_SOULSTONE, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(ALCHEMICAL_CALX, 4, MalumContent.Focusing.PRISMATIC_FOCUS_LENS, 1)
                .addSpirit(AQUEOUS_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 4)
                .addExtraItem(SizedIngredient.of(Tags.Items.GEMS_PRISMARINE, 2))
                .addExtraItem(REFINED_SOULSTONE, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(ALCHEMICAL_CALX, 4, MalumContent.Focusing.BLAZING_DIODE, 1)
                .addSpirit(INFERNAL_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 4)
                .addExtraItem(BLAZING_QUARTZ, 2)
                .addExtraItem(REFINED_SOULSTONE, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(ALCHEMICAL_CALX, 4, MalumContent.Focusing.INTRICATE_ASSEMBLY, 1)
                .addSpirit(EARTHEN_SPIRIT, 8)
                .addSpirit(ARCANE_SPIRIT, 4)
                .addExtraItem(SizedIngredient.of(Tags.Items.GEMS_EMERALD, 2))
                .addExtraItem(REFINED_SOULSTONE, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(TWISTED_ROCK_SET.itemPedestal, 1, MalumContent.Focusing.SPIRIT_CATALYZER, 1)
                .addSpirit(INFERNAL_SPIRIT, 8)
                .addSpirit(AERIAL_SPIRIT, 8)
                .addExtraItem(TAINTED_ROCK_SET.rock.block, 4)
                .addExtraItem(ETHER, 1)
                .addExtraItem(TWISTED_ROCK_SET.rock.block, 4)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(TAINTED_ROCK_SET.itemPedestal, 1, MalumContent.Focusing.REPAIR_PYLON, 1)
                .addSpirit(SACRED_SPIRIT, 16)
                .addSpirit(AERIAL_SPIRIT, 16)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(INFERNAL_SPIRIT, 16)
                .addExtraItem(TAINTED_ROCK_SET.rock.block, 8)
                .addExtraItem(TWISTED_ROCK_SET.rock.block, 8)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(ALCHEMICAL_CALX, 4, MalumContent.Focusing.STELLAR_MECHANISM, 1)
                .addExtraItem(FUSED_CONSCIOUSNESS, 1)
                .addExtraItem(NULL_SLATE, 2)
                .addSpirit(AERIAL_SPIRIT, 16)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .addSpirit(INFERNAL_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(ALCHEMICAL_IMPETUS, 1, ZEPHYR_IMPETUS, 1)
                .addSpirit(AERIAL_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addExtraItem(WIND_CHARGE, 8)
                .addExtraItem(IRON_METALLICS.getNode(), 6)
                .addExtraItem(WIND_NUCLEUS, 4)
                .addExtraItem(HEAVY_CORE, 1)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(ALCHEMICAL_IMPETUS, 1, IFRIT_IMPETUS, 1)
                .addSpirit(INFERNAL_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addExtraItem(BLAZE_POWDER, 8)
                .addExtraItem(IRON_METALLICS.getNode(), 6)
                .addExtraItem(PYRE_NUCLEUS, 4)
                .addExtraItem(HEAVY_CORE, 1)
                .save(recipeOutput);

    }
}
