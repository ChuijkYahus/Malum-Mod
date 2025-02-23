package com.sammy.malum.data.recipe;

import com.sammy.malum.data.recipe.builder.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.*;
import net.neoforged.neoforge.common.conditions.*;

public class MalumSoulbindingRecipes implements IConditionBuilder {

    protected static void buildRecipes(RecipeOutput recipeOutput) {
        new SoulBindingRecipeBuilder(ItemRegistry.REFINED_SOULSTONE.get(), 8, MalumGeasEffectTypeRegistry.PACT_OF_THE_NIGHTCHILD)
                .addExtraItem(ItemRegistry.ARCANE_CHARCOAL.get(), 4)
                .addSpirit(SpiritTypeRegistry.WICKED_SPIRIT, 16)
                .addSpirit(SpiritTypeRegistry.ARCANE_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(ItemRegistry.REFINED_SOULSTONE.get(), 8, MalumGeasEffectTypeRegistry.PACT_OF_THE_DAYBLESSED)
                .addExtraItem(ItemRegistry.ARCANE_CHARCOAL.get(), 4)
                .addSpirit(SpiritTypeRegistry.SACRED_SPIRIT, 16)
                .addSpirit(SpiritTypeRegistry.ARCANE_SPIRIT, 16)
                .save(recipeOutput);

        new SoulBindingRecipeBuilder(ItemRegistry.RAW_SOULSTONE.get(), 8, MalumGeasEffectTypeRegistry.PACT_OF_THE_SHATTERING_ADDICT)
                .addExtraItem(ItemRegistry.WARP_FLUX.get(), 2)
                .addSpirit(SpiritTypeRegistry.ELDRITCH_SPIRIT, 4)
                .save(recipeOutput);

        new SoulBindingRecipeBuilder(ItemRegistry.SOUL_STAINED_STEEL_PLATING.get(), 12, MalumGeasEffectTypeRegistry.PACT_OF_THE_FORTRESS)
                .addExtraItem(ItemRegistry.REFINED_SOULSTONE.get(), 4)
                .addSpirit(SpiritTypeRegistry.ARCANE_SPIRIT, 16)
                .addSpirit(SpiritTypeRegistry.EARTHEN_SPIRIT, 16)
                .addSpirit(SpiritTypeRegistry.ELDRITCH_SPIRIT, 4)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(ItemRegistry.SOUL_STAINED_STEEL_PLATING.get(), 12, MalumGeasEffectTypeRegistry.PACT_OF_THE_SHIELD)
                .addSpirit(SpiritTypeRegistry.ARCANE_SPIRIT, 16)
                .addSpirit(SpiritTypeRegistry.AQUEOUS_SPIRIT, 16)
                .addSpirit(SpiritTypeRegistry.ELDRITCH_SPIRIT, 4)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(ItemRegistry.BLOCK_OF_SOUL_STAINED_STEEL.get(), 1, MalumGeasEffectTypeRegistry.PACT_OF_RECIPROCATION)
                .addExtraItem(ItemRegistry.SOUL_STAINED_STEEL_PLATING.get(), 6)
                .addSpirit(SpiritTypeRegistry.WICKED_SPIRIT, 24)
                .addSpirit(SpiritTypeRegistry.ARCANE_SPIRIT, 16)
                .addSpirit(SpiritTypeRegistry.ELDRITCH_SPIRIT, 8)
                .save(recipeOutput);


        new SoulBindingRecipeBuilder(Items.IRON_BLOCK, 1, MalumGeasEffectTypeRegistry.PACT_OF_THE_REAPER)
                .addExtraItem(Tags.Items.GEMS_QUARTZ, 16)
                .addExtraItem(Items.IRON_INGOT, 8)
                .addSpirit(SpiritTypeRegistry.WICKED_SPIRIT, 32)
                .addSpirit(SpiritTypeRegistry.ELDRITCH_SPIRIT, 8)
                .save(recipeOutput);

        new SoulBindingRecipeBuilder(Items.HEAVY_CORE, 1, MalumGeasEffectTypeRegistry.PACT_OF_THE_SKYBREAKER)
                .addExtraItem(ItemRegistry.ASTRAL_WEAVE.get(), 8)
                .addSpirit(SpiritTypeRegistry.AERIAL_SPIRIT, 32)
                .addSpirit(SpiritTypeRegistry.EARTHEN_SPIRIT, 32)
                .save(recipeOutput);

        new SoulBindingRecipeBuilder(ItemRegistry.WIND_NUCLEUS.get(), 16, MalumGeasEffectTypeRegistry.PACT_OF_THE_CLOUDSKIPPER)
                .addExtraItem(ItemRegistry.ASTRAL_WEAVE.get(), 8)
                .addExtraItem(Items.WIND_CHARGE, 8)
                .addExtraItem(Items.FEATHER, 8)
                .addSpirit(SpiritTypeRegistry.AERIAL_SPIRIT, 32)
                .addSpirit(SpiritTypeRegistry.ARCANE_SPIRIT, 32)
                .save(recipeOutput);

        new SoulBindingRecipeBuilder(Items.BLAZE_POWDER, 16, MalumGeasEffectTypeRegistry.PACT_OF_THE_PYROMANIAC)
                .addExtraItem(Items.TNT, 8)
                .addExtraItem(Items.FIRE_CHARGE, 8)
                .addExtraItem(Items.GUNPOWDER, 8)
                .addSpirit(SpiritTypeRegistry.AERIAL_SPIRIT, 32)
                .addSpirit(SpiritTypeRegistry.ARCANE_SPIRIT, 32)
                .save(recipeOutput);

        new SoulBindingRecipeBuilder(ItemRegistry.ROTTING_ESSENCE.get(), 16, MalumGeasEffectTypeRegistry.PACT_OF_THE_PROFANE_ASCETIC)
                .addExtraItem(ItemRegistry.IMITATION_FLESH.get(), 8)
                .addExtraItem(Items.ROTTEN_FLESH, 8)
                .addSpirit(SpiritTypeRegistry.SACRED_SPIRIT, 32)
                .addSpirit(SpiritTypeRegistry.AQUEOUS_SPIRIT, 32)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(ItemRegistry.ROTTING_ESSENCE.get(), 16, MalumGeasEffectTypeRegistry.PACT_OF_THE_PROFANE_GLUTTON)
                .addExtraItem(ItemRegistry.IMITATION_HEART.get(), 8)
                .addExtraItem(Items.ROTTEN_FLESH, 8)
                .addSpirit(SpiritTypeRegistry.WICKED_SPIRIT, 32)
                .addSpirit(SpiritTypeRegistry.AQUEOUS_SPIRIT, 32)
                .save(recipeOutput);

        new SoulBindingRecipeBuilder(ItemRegistry.LIVING_FLESH.get(), 16, MalumGeasEffectTypeRegistry.PACT_OF_WYRD_RECONSTRUCTION)
                .addExtraItem(ItemRegistry.IMITATION_FLESH.get(), 8)
                .addExtraItem(Items.ROTTEN_FLESH, 8)
                .addExtraItem(Items.CRYING_OBSIDIAN, 4)
                .addExtraItem(Items.GHAST_TEAR, 4)
                .addSpirit(SpiritTypeRegistry.WICKED_SPIRIT, 32)
                .addSpirit(SpiritTypeRegistry.AQUEOUS_SPIRIT, 32)
                .save(recipeOutput);

    }
}
