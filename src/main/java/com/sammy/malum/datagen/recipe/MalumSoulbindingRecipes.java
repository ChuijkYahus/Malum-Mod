package com.sammy.malum.datagen.recipe;

import com.sammy.malum.datagen.recipe.builder.*;
import com.sammy.malum.registry.common.content.MalumContent;
import com.sammy.malum.registry.common.content.item.MalumItemProperties;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.neoforged.neoforge.common.conditions.*;

public class MalumSoulbindingRecipes implements IConditionBuilder {

    protected static void buildRecipes(RecipeOutput recipeOutput) {
        new SoulBindingRecipeBuilder(MalumContent.Materials.LIVING_FLESH.get(), 12, MalumGeasEffectTypes.PACT_OF_DEFIANCE)
                .addExtraItem(Items.GOLDEN_CARROT, 4)
                .addExtraItem(Items.GOLDEN_APPLE, 1)
                .addSpirit(MalumSpiritTypes.SACRED_SPIRIT, 16)
                .addSpirit(MalumSpiritTypes.INFERNAL_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(MalumContent.Materials.LIVING_FLESH.get(), 12, MalumGeasEffectTypes.PACT_OF_THE_PARASITE)
                .addExtraItem(Items.GLISTERING_MELON_SLICE, 4)
                .addExtraItem(Items.GOLDEN_APPLE, 1)
                .addSpirit(MalumSpiritTypes.SACRED_SPIRIT, 16)
                .addSpirit(MalumSpiritTypes.AQUEOUS_SPIRIT, 16)
                .addSpirit(MalumSpiritTypes.EARTHEN_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(MalumContent.Materials.IMITATION_FLESH.get(), 8, MalumGeasEffectTypes.PACT_OF_THE_LIFEWEAVER)
                .addExtraItem(Items.GOLDEN_CARROT, 4)
                .addExtraItem(Items.GOLDEN_APPLE, 4)
                .addExtraItem(Items.GLISTERING_MELON_SLICE, 4)
                .addSpirit(MalumSpiritTypes.SACRED_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.ARCANE_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.EARTHEN_SPIRIT, 16)
                .addSpirit(MalumSpiritTypes.ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);

        new SoulBindingRecipeBuilder(MalumContent.Materials.REFINED_SOULSTONE.get(), 12, MalumGeasEffectTypes.PACT_OF_THE_WARLOCK)
                .addExtraItem(MalumContent.Materials.ARCANE_CHARCOAL.get(), 4)
                .addExtraItem(MalumContent.Materials.HEX_ASH.get(), 4)
                .addSpirit(MalumSpiritTypes.WICKED_SPIRIT, 16)
                .addSpirit(MalumSpiritTypes.AQUEOUS_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(MalumContent.Materials.REFINED_SOULSTONE.get(), 12, MalumGeasEffectTypes.PACT_OF_THE_REAPER)
                .addExtraItem(MalumContent.Materials.GRIM_TALC.get(), 4)
                .addExtraItem(MalumContent.Materials.HEX_ASH.get(), 4)
                .addSpirit(MalumSpiritTypes.WICKED_SPIRIT, 16)
                .addSpirit(MalumSpiritTypes.AERIAL_SPIRIT, 16)
                .addSpirit(MalumSpiritTypes.INFERNAL_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(MalumContent.Materials.IMITATION_HEART.get(), 8, MalumGeasEffectTypes.PACT_OF_THE_BERSERKER)
                .addExtraItem(MalumContent.Materials.LIVING_FLESH.get(), 16)
                .addExtraItem(MalumContent.Materials.GRIM_TALC.get(), 16)
                .addExtraItem(Items.ROTTEN_FLESH, 16)
                .addSpirit(MalumSpiritTypes.WICKED_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.ARCANE_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.INFERNAL_SPIRIT, 16)
                .addSpirit(MalumSpiritTypes.ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);


        new SoulBindingRecipeBuilder(MalumContent.Materials.SOUL_STAINED_STEEL_PLATING.get(), 12, MalumGeasEffectTypes.PACT_OF_THE_FORTRESS)
                .addExtraItem(MalumContent.Materials.REFINED_SOULSTONE.get(), 8)
                .addSpirit(MalumSpiritTypes.ARCANE_SPIRIT, 16)
                .addSpirit(MalumSpiritTypes.EARTHEN_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(MalumContent.Materials.SOUL_STAINED_STEEL_PLATING.get(), 12, MalumGeasEffectTypes.PACT_OF_THE_SHIELD)
                .addExtraItem(MalumContent.Materials.REFINED_SOULSTONE.get(), 8)
                .addSpirit(MalumSpiritTypes.ARCANE_SPIRIT, 16)
                .addSpirit(MalumSpiritTypes.AERIAL_SPIRIT, 16)
                .addSpirit(MalumSpiritTypes.AQUEOUS_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(MalumContent.Materials.IMITATION_FLESH.get(), 8, MalumGeasEffectTypes.PACT_OF_RECIPROCATION)
                .addExtraItem(MalumContent.Materials.WARP_FLUX.get(), 8)
                .addExtraItem(MalumContent.Materials.REFINED_SOULSTONE.get(), 8)
                .addExtraItem(MalumContent.Materials.SOUL_STAINED_STEEL_PLATING.get(), 8)
                .addSpirit(MalumSpiritTypes.ARCANE_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.AQUEOUS_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.EARTHEN_SPIRIT, 16)
                .addSpirit(MalumSpiritTypes.ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);


        new SoulBindingRecipeBuilder(MalumContent.Materials.RAW_SOULSTONE.get(), 12, MalumGeasEffectTypes.PACT_OF_THE_SHATTERING_ADDICT)
                .addExtraItem(MalumContent.Materials.HEX_ASH.get(), 8)
                .addExtraItem(MalumContent.Materials.WARP_FLUX.get(), 4)
                .addSpirit(MalumSpiritTypes.ELDRITCH_SPIRIT, 16)
                .addSpirit(MalumSpiritTypes.WICKED_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(MalumContent.Materials.RAW_SOULSTONE.get(), 12, MalumGeasEffectTypes.PACT_OF_THE_ARCANAPHAGE)
                .addExtraItem(MalumContent.Materials.WARP_FLUX.get(), 8)
                .addExtraItem(MalumContent.Materials.SOUL_STAINED_STEEL_PLATING.get(), 4)
                .addSpirit(MalumSpiritTypes.ELDRITCH_SPIRIT, 16)
                .addSpirit(MalumSpiritTypes.ARCANE_SPIRIT, 16)
                .addSpirit(MalumSpiritTypes.WICKED_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(MalumContent.Materials.IMITATION_HEART.get(), 8, MalumGeasEffectTypes.PACT_OF_RUNE_EXPLOITATION)
                .addExtraItem(MalumContent.Materials.WARP_FLUX.get(), 8)
                .addExtraItem(MalumContent.Materials.REFINED_SOULSTONE.get(), 8)
                .addExtraItem(MalumContent.Materials.HEX_ASH.get(), 8)
                .addSpirit(MalumSpiritTypes.ELDRITCH_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.ARCANE_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.WICKED_SPIRIT, 16)
                .addSpirit(MalumSpiritTypes.SACRED_SPIRIT, 16)
                .save(recipeOutput);


        new SoulBindingRecipeBuilder(Items.PRISMARINE_CRYSTALS, 12, MalumGeasEffectTypes.PACT_OF_SELF_CARE)
                .addExtraItem(Items.SALMON, 8)
                .addExtraItem(Items.NAUTILUS_SHELL, 2)
                .addSpirit(MalumSpiritTypes.AQUEOUS_SPIRIT, 16)
                .addSpirit(MalumSpiritTypes.SACRED_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(Items.PRISMARINE_CRYSTALS, 12, MalumGeasEffectTypes.PACT_OF_THE_HIGH_PRIEST)
                .addExtraItem(Items.PRISMARINE_SHARD, 8)
                .addExtraItem(Items.NAUTILUS_SHELL, 2)
                .addSpirit(MalumSpiritTypes.AQUEOUS_SPIRIT, 16)
                .addSpirit(MalumSpiritTypes.AERIAL_SPIRIT, 16)
                .addSpirit(MalumSpiritTypes.EARTHEN_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(Items.HEART_OF_THE_SEA, 1, MalumGeasEffectTypes.PACT_OF_TIDAL_AFFINITY)
                .addExtraItem(Items.PRISMARINE_SHARD, 8)
                .addExtraItem(Items.PRISMARINE_CRYSTALS, 8)
                .addExtraItem(Items.KELP, 8)
                .addSpirit(MalumSpiritTypes.AQUEOUS_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.SACRED_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.AERIAL_SPIRIT, 16)
                .addSpirit(MalumSpiritTypes.ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(Items.HEART_OF_THE_SEA, 1, MalumGeasEffectTypes.PACT_OF_PATIENCE_REPAID)
                .addExtraItem(Items.PRISMARINE_SHARD, 8)
                .addExtraItem(Items.PRISMARINE_CRYSTALS, 8)
                .addExtraItem(Items.COPPER_INGOT, 8)
                .addSpirit(MalumSpiritTypes.AQUEOUS_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.SACRED_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.EARTHEN_SPIRIT, 16)
                .addSpirit(MalumSpiritTypes.ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);


        new SoulBindingRecipeBuilder(Items.FEATHER, 12, MalumGeasEffectTypes.PACT_OF_THE_WINDSWEPT)
                .addExtraItem(Items.LEATHER, 8)
                .addExtraItem(MalumContent.Materials.EERIE_WEAVE.get(), 4)
                .addSpirit(MalumSpiritTypes.AERIAL_SPIRIT, 16)
                .addSpirit(MalumSpiritTypes.EARTHEN_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(Items.FEATHER, 12, MalumGeasEffectTypes.PACT_OF_THE_CONTINUING_SHOT)
                .addExtraItem(Items.ARROW, 8)
                .addExtraItem(MalumContent.Materials.EERIE_WEAVE.get(), 4)
                .addSpirit(MalumSpiritTypes.AERIAL_SPIRIT, 16)
                .addSpirit(MalumSpiritTypes.WICKED_SPIRIT, 16)
                .addSpirit(MalumSpiritTypes.AQUEOUS_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(Items.HEAVY_CORE, 1, MalumGeasEffectTypes.PACT_OF_THE_SKYBREAKER)
                .addExtraItem(MalumContent.Materials.EERIE_WEAVE.get(), 8)
                .addExtraItem(MalumContent.Materials.WIND_NUCLEUS.get(), 8)
                .addExtraItem(Items.PHANTOM_MEMBRANE, 8)
                .addSpirit(MalumSpiritTypes.AERIAL_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.EARTHEN_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.WICKED_SPIRIT, 16)
                .addSpirit(MalumSpiritTypes.ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(MalumContent.Materials.WIND_NUCLEUS.get(), 16, MalumGeasEffectTypes.PACT_OF_THE_CLOUDSKIPPER)
                .addExtraItem(MalumContent.Materials.EERIE_WEAVE.get(), 8)
                .addExtraItem(Items.WIND_CHARGE, 8)
                .addExtraItem(Items.FEATHER, 8)
                .addSpirit(MalumSpiritTypes.AERIAL_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.ARCANE_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.SACRED_SPIRIT, 16)
                .addSpirit(MalumSpiritTypes.ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);


        new SoulBindingRecipeBuilder(Ingredient.of(ItemTags.SAPLINGS), 12, MalumGeasEffectTypes.PACT_OF_CONTENTEDNESS)
                .addExtraItem(Items.BREAD, 8)
                .addExtraItem(Items.EMERALD, 4)
                .addSpirit(MalumSpiritTypes.EARTHEN_SPIRIT, 16)
                .addSpirit(MalumSpiritTypes.SACRED_SPIRIT, 16)
                .addSpirit(MalumSpiritTypes.ARCANE_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(Ingredient.of(ItemTags.SAPLINGS), 12, MalumGeasEffectTypes.PACT_OF_THE_LONE_DRUID)
                .addExtraItem(Items.CHAIN, 8)
                .addExtraItem(Items.EMERALD, 4)
                .addSpirit(MalumSpiritTypes.EARTHEN_SPIRIT, 16)
                .addSpirit(MalumSpiritTypes.AQUEOUS_SPIRIT, 16)
                .addSpirit(MalumSpiritTypes.ARCANE_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(MalumContent.Materials.ROTTING_ESSENCE.get(), 16, MalumGeasEffectTypes.PACT_OF_THE_PROFANE_ASCETIC)
                .addExtraItem(MalumContent.Materials.IMITATION_FLESH.get(), 8)
                .addExtraItem(Items.ROTTEN_FLESH, 8)
                .addExtraItem(MalumContent.Materials.GRIM_TALC.get(), 8)
                .addSpirit(MalumSpiritTypes.EARTHEN_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.SACRED_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.AQUEOUS_SPIRIT, 16)
                .addSpirit(MalumSpiritTypes.ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(MalumContent.Materials.ROTTING_ESSENCE.get(), 16, MalumGeasEffectTypes.PACT_OF_THE_PROFANE_GLUTTON)
                .addExtraItem(MalumContent.Materials.IMITATION_HEART.get(), 8)
                .addExtraItem(Items.ROTTEN_FLESH, 8)
                .addExtraItem(Items.SPIDER_EYE, 8)
                .addSpirit(MalumSpiritTypes.EARTHEN_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.WICKED_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.AQUEOUS_SPIRIT, 16)
                .addSpirit(MalumSpiritTypes.ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);

        new SoulBindingRecipeBuilder(Items.GUNPOWDER, 12, MalumGeasEffectTypes.PACT_OF_COMBUSTION)
                .addExtraItem(Items.BLAZE_POWDER, 8)
                .addExtraItem(MalumItemProperties.BLAZING_QUARTZ.get(), 4)
                .addSpirit(MalumSpiritTypes.INFERNAL_SPIRIT, 16)
                .addSpirit(MalumSpiritTypes.WICKED_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(Items.GUNPOWDER, 12, MalumGeasEffectTypes.PACT_OF_THE_PROSPECTOR)
                .addExtraItem(Items.BLAZE_POWDER, 8)
                .addExtraItem(Items.RAW_GOLD, 4)
                .addSpirit(MalumSpiritTypes.INFERNAL_SPIRIT, 16)
                .addSpirit(MalumSpiritTypes.EARTHEN_SPIRIT, 16)
                .addSpirit(MalumSpiritTypes.SACRED_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(Items.BLAZE_POWDER, 16, MalumGeasEffectTypes.PACT_OF_THE_BLASTWEAVER)
                .addExtraItem(Items.TNT, 8)
                .addExtraItem(Items.FIRE_CHARGE, 8)
                .addExtraItem(Items.GUNPOWDER, 8)
                .addSpirit(MalumSpiritTypes.ARCANE_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.INFERNAL_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.AERIAL_SPIRIT, 16)
                .addSpirit(MalumSpiritTypes.AQUEOUS_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(Items.BLAZE_POWDER, 16, MalumGeasEffectTypes.PACT_OF_WYRD_RECONSTRUCTION)
                .addExtraItem(MalumContent.Materials.IMITATION_FLESH.get(), 8)
                .addExtraItem(Items.ROTTEN_FLESH, 8)
                .addExtraItem(Items.GHAST_TEAR, 8)
                .addSpirit(MalumSpiritTypes.SACRED_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.WICKED_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.AQUEOUS_SPIRIT, 16)
                .addSpirit(MalumSpiritTypes.EARTHEN_SPIRIT, 16)
                .save(recipeOutput);


        new SoulBindingRecipeBuilder(MalumContent.Materials.MNEMONIC_FRAGMENT.get(), 8, MalumGeasEffectTypes.OATH_OF_THE_OVERKEEN_EYE)
                .addExtraItem(MalumContent.Materials.NULL_SLATE.get(), 8)
                .addExtraItem(MalumContent.Materials.IMITATION_HEART.get(), 4)
                .addExtraItem(Items.ENDER_PEARL, 4)
                .addSpirit(MalumSpiritTypes.ARCANE_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.AERIAL_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.ELDRITCH_SPIRIT, 32)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(MalumContent.Materials.MNEMONIC_FRAGMENT.get(), 8, MalumGeasEffectTypes.OATH_OF_THE_OVERBURDENED_MIND)
                .addExtraItem(MalumContent.Materials.AURIC_EMBERS.get(), 8)
                .addExtraItem(MalumContent.Materials.IMITATION_HEART.get(), 4)
                .addExtraItem(Items.ECHO_SHARD, 4)
                .addSpirit(MalumSpiritTypes.ARCANE_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.AQUEOUS_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.ELDRITCH_SPIRIT, 32)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(MalumContent.Materials.MNEMONIC_FRAGMENT.get(), 8, MalumGeasEffectTypes.OATH_OF_THE_OVEREAGER_FIST)
                .addExtraItem(MalumContent.Materials.VOID_SALTS.get(), 8)
                .addExtraItem(MalumContent.Materials.IMITATION_HEART.get(), 4)
                .addExtraItem(Items.BLAZE_POWDER, 4)
                .addSpirit(MalumSpiritTypes.ARCANE_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.INFERNAL_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.ELDRITCH_SPIRIT, 32)
                .save(recipeOutput);

        new SoulBindingRecipeBuilder(MalumContent.Materials.MALIGNANT_PEWTER_PLATING.get(), 8, MalumGeasEffectTypes.OATH_OF_UNMAKERS_DISDAIN)
                .addExtraItem(MalumContent.Materials.VOID_SALTS.get(), 8)
                .addExtraItem(MalumContent.Materials.IMITATION_FLESH.get(), 4)
                .addSpirit(MalumSpiritTypes.WICKED_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.ARCANE_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.ELDRITCH_SPIRIT, 32)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(MalumContent.Materials.MALIGNANT_PEWTER_PLATING.get(), 8, MalumGeasEffectTypes.OATH_OF_UNSIGHTED_RESISTANCE)
                .addExtraItem(Items.NETHERITE_SCRAP, 8)
                .addExtraItem(MalumContent.Materials.IMITATION_FLESH.get(), 4)
                .addSpirit(MalumSpiritTypes.EARTHEN_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.ARCANE_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.ELDRITCH_SPIRIT, 32)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(MalumContent.Materials.MALIGNANT_PEWTER_PLATING.get(), 8, MalumGeasEffectTypes.OATH_OF_THE_UNDISCERNED_MAW)
                .addExtraItem(MalumContent.Materials.LIVING_FLESH.get(), 8)
                .addExtraItem(MalumContent.Materials.IMITATION_FLESH.get(), 4)
                .addSpirit(MalumSpiritTypes.SACRED_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.ARCANE_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.ELDRITCH_SPIRIT, 32)
                .save(recipeOutput);


        new SoulBindingRecipeBuilder(MalumContent.Materials.FUSED_CONSCIOUSNESS.get(), 1, MalumGeasEffectTypes.AUTHORITY_OF_THE_GLEEFUL_TARGET)
                .addExtraItem(MalumContent.Materials.MALIGNANT_PEWTER_PLATING.get(), 16)
                .addExtraItem(MalumContent.Materials.IMITATION_FLESH.get(), 16)
                .addExtraItem(MalumContent.Materials.VOID_SALTS.get(), 16)
                .addSpirit(MalumSpiritTypes.SACRED_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.WICKED_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.ARCANE_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.ELDRITCH_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.AERIAL_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.AQUEOUS_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.EARTHEN_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.INFERNAL_SPIRIT, 32)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(MalumContent.Materials.FUSED_CONSCIOUSNESS.get(), 1, MalumGeasEffectTypes.AUTHORITY_OF_THE_INVERTED_HEART)
                .addExtraItem(MalumContent.Materials.MALIGNANT_PEWTER_PLATING.get(), 16)
                .addExtraItem(MalumContent.Materials.IMITATION_HEART.get(), 16)
                .addExtraItem(MalumContent.Materials.MNEMONIC_FRAGMENT.get(), 16)
                .addSpirit(MalumSpiritTypes.SACRED_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.WICKED_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.ARCANE_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.ELDRITCH_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.AERIAL_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.AQUEOUS_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.EARTHEN_SPIRIT, 32)
                .addSpirit(MalumSpiritTypes.INFERNAL_SPIRIT, 32)
                .save(recipeOutput);

    }
}
