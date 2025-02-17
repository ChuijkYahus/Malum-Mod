package com.sammy.malum.data.recipe;

import com.sammy.malum.data.recipe.builder.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.*;
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
                .addExtraItem(Items.IRON_INGOT, 8)
                .addSpirit(SpiritTypeRegistry.WICKED_SPIRIT, 32)
                .addSpirit(SpiritTypeRegistry.ELDRITCH_SPIRIT, 8)
                .save(recipeOutput);

    }
}
