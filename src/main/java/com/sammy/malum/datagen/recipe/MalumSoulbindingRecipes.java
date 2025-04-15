package com.sammy.malum.datagen.recipe;

import com.sammy.malum.datagen.recipe.builder.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.neoforged.neoforge.common.*;
import net.neoforged.neoforge.common.conditions.*;

public class MalumSoulbindingRecipes implements IConditionBuilder {

    protected static void buildRecipes(RecipeOutput recipeOutput) {
        new SoulBindingRecipeBuilder(ItemRegistry.LIVING_FLESH.get(), 12, MalumGeasEffectTypeRegistry.PACT_OF_DEFIANCE)
                .addExtraItem(Items.GOLDEN_CARROT, 4)
                .addExtraItem(Items.GOLDEN_APPLE, 1)
                .addSpirit(SpiritTypeRegistry.SACRED_SPIRIT, 16)
                .addSpirit(SpiritTypeRegistry.INFERNAL_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(ItemRegistry.LIVING_FLESH.get(), 12, MalumGeasEffectTypeRegistry.PACT_OF_THE_PARASITE)
                .addExtraItem(Items.GLISTERING_MELON_SLICE, 4)
                .addExtraItem(Items.GOLDEN_APPLE, 1)
                .addSpirit(SpiritTypeRegistry.SACRED_SPIRIT, 16)
                .addSpirit(SpiritTypeRegistry.AQUEOUS_SPIRIT, 16)
                .addSpirit(SpiritTypeRegistry.EARTHEN_SPIRIT, 16)
                .save(recipeOutput);


        new SoulBindingRecipeBuilder(ItemRegistry.REFINED_SOULSTONE.get(), 12, MalumGeasEffectTypeRegistry.PACT_OF_THE_CHALLENGER)
                .addExtraItem(ItemRegistry.ARCANE_CHARCOAL.get(), 4)
                .addExtraItem(ItemRegistry.GRIM_TALC.get(), 4)
                .addSpirit(SpiritTypeRegistry.WICKED_SPIRIT, 16)
                .addSpirit(SpiritTypeRegistry.AQUEOUS_SPIRIT, 16)
                .addSpirit(SpiritTypeRegistry.INFERNAL_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(Items.IRON_BLOCK, 1, MalumGeasEffectTypeRegistry.PACT_OF_THE_REAPER)
                .addExtraItem(Tags.Items.GEMS_QUARTZ, 8)
                .addExtraItem(ItemRegistry.REFINED_SOULSTONE.get(), 8)
                .addSpirit(SpiritTypeRegistry.WICKED_SPIRIT, 32)
                .addSpirit(SpiritTypeRegistry.AERIAL_SPIRIT, 32)
                .addSpirit(SpiritTypeRegistry.ARCANE_SPIRIT, 16)
                .addSpirit(SpiritTypeRegistry.ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);


        new SoulBindingRecipeBuilder(ItemRegistry.SOUL_STAINED_STEEL_PLATING.get(), 12, MalumGeasEffectTypeRegistry.PACT_OF_THE_FORTRESS)
                .addExtraItem(ItemRegistry.REFINED_SOULSTONE.get(), 8)
                .addSpirit(SpiritTypeRegistry.ARCANE_SPIRIT, 16)
                .addSpirit(SpiritTypeRegistry.EARTHEN_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(ItemRegistry.SOUL_STAINED_STEEL_PLATING.get(), 12, MalumGeasEffectTypeRegistry.PACT_OF_THE_SHIELD)
                .addExtraItem(ItemRegistry.REFINED_SOULSTONE.get(), 8)
                .addSpirit(SpiritTypeRegistry.ARCANE_SPIRIT, 16)
                .addSpirit(SpiritTypeRegistry.AERIAL_SPIRIT, 16)
                .addSpirit(SpiritTypeRegistry.AQUEOUS_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(ItemRegistry.BLOCK_OF_SOUL_STAINED_STEEL.get(), 1, MalumGeasEffectTypeRegistry.PACT_OF_RECIPROCATION)
                .addExtraItem(ItemRegistry.SOUL_STAINED_STEEL_PLATING.get(), 8)
                .addExtraItem(ItemRegistry.REFINED_SOULSTONE.get(), 8)
                .addSpirit(SpiritTypeRegistry.WICKED_SPIRIT, 32)
                .addSpirit(SpiritTypeRegistry.AQUEOUS_SPIRIT, 32)
                .addSpirit(SpiritTypeRegistry.ARCANE_SPIRIT, 16)
                .addSpirit(SpiritTypeRegistry.ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);


        new SoulBindingRecipeBuilder(ItemRegistry.RAW_SOULSTONE.get(), 12, MalumGeasEffectTypeRegistry.PACT_OF_THE_SHATTERING_ADDICT)
                .addExtraItem(ItemRegistry.HEX_ASH.get(), 8)
                .addExtraItem(ItemRegistry.WARP_FLUX.get(), 4)
                .addSpirit(SpiritTypeRegistry.ELDRITCH_SPIRIT, 16)
                .addSpirit(SpiritTypeRegistry.WICKED_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(ItemRegistry.RAW_SOULSTONE.get(), 12, MalumGeasEffectTypeRegistry.PACT_OF_THE_ARCANAPHAGE)
                .addExtraItem(ItemRegistry.WARP_FLUX.get(), 8)
                .addExtraItem(ItemRegistry.SOUL_STAINED_STEEL_PLATING.get(), 4)
                .addSpirit(SpiritTypeRegistry.ELDRITCH_SPIRIT, 16)
                .addSpirit(SpiritTypeRegistry.ARCANE_SPIRIT, 16)
                .addSpirit(SpiritTypeRegistry.WICKED_SPIRIT, 16)
                .save(recipeOutput);


        new SoulBindingRecipeBuilder(Items.PRISMARINE_CRYSTALS, 12, MalumGeasEffectTypeRegistry.PACT_OF_SELF_CARE)
                .addExtraItem(Items.SALMON, 8)
                .addExtraItem(Items.NAUTILUS_SHELL, 4)
                .addSpirit(SpiritTypeRegistry.AQUEOUS_SPIRIT, 16)
                .addSpirit(SpiritTypeRegistry.SACRED_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(Items.PRISMARINE_CRYSTALS, 12, MalumGeasEffectTypeRegistry.PACT_OF_THE_HIGH_PRIEST)
                .addExtraItem(Items.PRISMARINE_SHARD, 8)
                .addExtraItem(Items.NAUTILUS_SHELL, 4)
                .addSpirit(SpiritTypeRegistry.AQUEOUS_SPIRIT, 16)
                .addSpirit(SpiritTypeRegistry.AERIAL_SPIRIT, 16)
                .addSpirit(SpiritTypeRegistry.EARTHEN_SPIRIT, 16)
                .save(recipeOutput);


        new SoulBindingRecipeBuilder(Items.FEATHER, 12, MalumGeasEffectTypeRegistry.PACT_OF_THE_WINDSWEPT)
                .addExtraItem(Items.LEATHER, 8)
                .addExtraItem(ItemRegistry.ASTRAL_WEAVE.get(), 4)
                .addSpirit(SpiritTypeRegistry.AERIAL_SPIRIT, 16)
                .addSpirit(SpiritTypeRegistry.EARTHEN_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(Items.FEATHER, 12, MalumGeasEffectTypeRegistry.PACT_OF_THE_CONTINUING_SHOT)
                .addExtraItem(Items.ARROW, 8)
                .addExtraItem(ItemRegistry.ASTRAL_WEAVE.get(), 4)
                .addSpirit(SpiritTypeRegistry.AERIAL_SPIRIT, 16)
                .addSpirit(SpiritTypeRegistry.WICKED_SPIRIT, 16)
                .addSpirit(SpiritTypeRegistry.AQUEOUS_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(Items.HEAVY_CORE, 1, MalumGeasEffectTypeRegistry.PACT_OF_THE_SKYBREAKER)
                .addExtraItem(ItemRegistry.ASTRAL_WEAVE.get(), 8)
                .addExtraItem(ItemRegistry.WIND_NUCLEUS.get(), 8)
                .addExtraItem(Items.PHANTOM_MEMBRANE, 8)
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


        new SoulBindingRecipeBuilder(Ingredient.of(ItemTags.SAPLINGS), 12, MalumGeasEffectTypeRegistry.PACT_OF_CONTENTEDNESS)
                .addExtraItem(Items.BREAD, 8)
                .addExtraItem(Items.EMERALD, 4)
                .addSpirit(SpiritTypeRegistry.EARTHEN_SPIRIT, 16)
                .addSpirit(SpiritTypeRegistry.SACRED_SPIRIT, 16)
                .addSpirit(SpiritTypeRegistry.ARCANE_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(Ingredient.of(ItemTags.SAPLINGS), 12, MalumGeasEffectTypeRegistry.PACT_OF_THE_LONE_DRUID)
                .addExtraItem(Items.CHAIN, 8)
                .addExtraItem(Items.EMERALD, 4)
                .addSpirit(SpiritTypeRegistry.EARTHEN_SPIRIT, 16)
                .addSpirit(SpiritTypeRegistry.AQUEOUS_SPIRIT, 16)
                .addSpirit(SpiritTypeRegistry.ARCANE_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(ItemRegistry.ROTTING_ESSENCE.get(), 16, MalumGeasEffectTypeRegistry.PACT_OF_THE_PROFANE_ASCETIC)
                .addExtraItem(ItemRegistry.IMITATION_FLESH.get(), 8)
                .addExtraItem(Items.ROTTEN_FLESH, 8)
                .addExtraItem(ItemRegistry.GRIM_TALC.get(), 8)
                .addSpirit(SpiritTypeRegistry.SACRED_SPIRIT, 32)
                .addSpirit(SpiritTypeRegistry.AQUEOUS_SPIRIT, 32)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(ItemRegistry.ROTTING_ESSENCE.get(), 16, MalumGeasEffectTypeRegistry.PACT_OF_THE_PROFANE_GLUTTON)
                .addExtraItem(ItemRegistry.IMITATION_HEART.get(), 8)
                .addExtraItem(Items.ROTTEN_FLESH, 8)
                .addExtraItem(Items.SPIDER_EYE, 8)
                .addSpirit(SpiritTypeRegistry.WICKED_SPIRIT, 32)
                .addSpirit(SpiritTypeRegistry.AQUEOUS_SPIRIT, 32)
                .save(recipeOutput);

        new SoulBindingRecipeBuilder(Items.BLAZE_POWDER, 16, MalumGeasEffectTypeRegistry.PACT_OF_THE_PYROMANIAC)
                .addExtraItem(Items.TNT, 8)
                .addExtraItem(Items.FIRE_CHARGE, 8)
                .addExtraItem(Items.GUNPOWDER, 8)
                .addSpirit(SpiritTypeRegistry.ARCANE_SPIRIT, 32)
                .addSpirit(SpiritTypeRegistry.INFERNAL_SPIRIT, 32)
                .addSpirit(SpiritTypeRegistry.AERIAL_SPIRIT, 16)
                .addSpirit(SpiritTypeRegistry.AQUEOUS_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(ItemRegistry.LIVING_FLESH.get(), 16, MalumGeasEffectTypeRegistry.PACT_OF_WYRD_RECONSTRUCTION)
                .addExtraItem(ItemRegistry.IMITATION_FLESH.get(), 8)
                .addExtraItem(Items.ROTTEN_FLESH, 8)
                .addExtraItem(Items.GHAST_TEAR, 4)
                .addSpirit(SpiritTypeRegistry.SACRED_SPIRIT, 32)
                .addSpirit(SpiritTypeRegistry.WICKED_SPIRIT, 32)
                .addSpirit(SpiritTypeRegistry.AQUEOUS_SPIRIT, 16)
                .addSpirit(SpiritTypeRegistry.EARTHEN_SPIRIT, 16)
                .save(recipeOutput);


        new SoulBindingRecipeBuilder(ItemRegistry.MNEMONIC_FRAGMENT.get(), 8, MalumGeasEffectTypeRegistry.OATH_OF_THE_OVERKEEN_EYE)
                .addExtraItem(ItemRegistry.NULL_SLATE.get(), 8)
                .addExtraItem(ItemRegistry.IMITATION_HEART.get(), 4)
                .addExtraItem(Items.ENDER_PEARL, 4)
                .addSpirit(SpiritTypeRegistry.ARCANE_SPIRIT, 32)
                .addSpirit(SpiritTypeRegistry.AERIAL_SPIRIT, 32)
                .addSpirit(SpiritTypeRegistry.ELDRITCH_SPIRIT, 32)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(ItemRegistry.MNEMONIC_FRAGMENT.get(), 8, MalumGeasEffectTypeRegistry.OATH_OF_THE_OVERBURDENED_MIND)
                .addExtraItem(ItemRegistry.MNEMONIC_FRAGMENT.get(), 8)
                .addExtraItem(ItemRegistry.IMITATION_HEART.get(), 4)
                .addExtraItem(Items.ECHO_SHARD, 4)
                .addSpirit(SpiritTypeRegistry.ARCANE_SPIRIT, 32)
                .addSpirit(SpiritTypeRegistry.AQUEOUS_SPIRIT, 32)
                .addSpirit(SpiritTypeRegistry.ELDRITCH_SPIRIT, 32)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(ItemRegistry.MNEMONIC_FRAGMENT.get(), 8, MalumGeasEffectTypeRegistry.OATH_OF_THE_OVEREAGER_FIST)
                .addExtraItem(ItemRegistry.VOID_SALTS.get(), 8)
                .addExtraItem(ItemRegistry.IMITATION_HEART.get(), 4)
                .addExtraItem(Items.BLAZE_POWDER, 4)
                .addSpirit(SpiritTypeRegistry.ARCANE_SPIRIT, 32)
                .addSpirit(SpiritTypeRegistry.INFERNAL_SPIRIT, 32)
                .addSpirit(SpiritTypeRegistry.ELDRITCH_SPIRIT, 32)
                .save(recipeOutput);

        new SoulBindingRecipeBuilder(ItemRegistry.MALIGNANT_PEWTER_PLATING.get(), 8, MalumGeasEffectTypeRegistry.OATH_OF_UNMAKERS_DISDAIN)
                .addExtraItem(ItemRegistry.VOID_SALTS.get(), 8)
                .addExtraItem(ItemRegistry.IMITATION_FLESH.get(), 4)
                .addSpirit(SpiritTypeRegistry.WICKED_SPIRIT, 32)
                .addSpirit(SpiritTypeRegistry.ARCANE_SPIRIT, 32)
                .addSpirit(SpiritTypeRegistry.ELDRITCH_SPIRIT, 32)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(ItemRegistry.MALIGNANT_PEWTER_PLATING.get(), 8, MalumGeasEffectTypeRegistry.OATH_OF_UNSIGHTED_RESISTANCE)
                .addExtraItem(Items.NETHERITE_SCRAP, 8)
                .addExtraItem(ItemRegistry.IMITATION_FLESH.get(), 4)
                .addSpirit(SpiritTypeRegistry.EARTHEN_SPIRIT, 32)
                .addSpirit(SpiritTypeRegistry.ARCANE_SPIRIT, 32)
                .addSpirit(SpiritTypeRegistry.ELDRITCH_SPIRIT, 32)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(ItemRegistry.MALIGNANT_PEWTER_PLATING.get(), 8, MalumGeasEffectTypeRegistry.OATH_OF_THE_UNDISCERNED_MAW)
                .addExtraItem(ItemRegistry.LIVING_FLESH.get(), 8)
                .addExtraItem(ItemRegistry.IMITATION_FLESH.get(), 4)
                .addSpirit(SpiritTypeRegistry.SACRED_SPIRIT, 32)
                .addSpirit(SpiritTypeRegistry.ARCANE_SPIRIT, 32)
                .addSpirit(SpiritTypeRegistry.ELDRITCH_SPIRIT, 32)
                .save(recipeOutput);

    }
}
