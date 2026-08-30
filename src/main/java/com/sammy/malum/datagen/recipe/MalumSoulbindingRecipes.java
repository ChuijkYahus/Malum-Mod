package com.sammy.malum.datagen.recipe;

import com.sammy.malum.datagen.recipe.builder.*;
import com.sammy.malum.registry.common.MalumContent;
import com.sammy.malum.registry.common.MalumContent.Materials;
import com.sammy.malum.registry.common.item.MalumItemProperties;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.neoforged.neoforge.common.conditions.*;

import static com.sammy.malum.registry.common.MalumContent.Materials.*;
import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;
import static net.minecraft.world.item.Items.*;

public class MalumSoulbindingRecipes implements IConditionBuilder {

    protected static void buildRecipes(RecipeOutput recipeOutput) {
        new SoulBindingRecipeBuilder(LIVING_FLESH, 12, MalumGeasEffectTypes.PACT_OF_DEFIANCE)
                .addExtraItem(GOLDEN_CARROT, 4)
                .addExtraItem(GOLDEN_APPLE, 1)
                .addSpirit(SACRED_SPIRIT, 16)
                .addSpirit(INFERNAL_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(LIVING_FLESH, 12, MalumGeasEffectTypes.PACT_OF_THE_PARASITE)
                .addExtraItem(GLISTERING_MELON_SLICE, 4)
                .addExtraItem(GOLDEN_APPLE, 1)
                .addSpirit(SACRED_SPIRIT, 16)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(IMITATION_FLESH, 8, MalumGeasEffectTypes.PACT_OF_THE_LIFEWEAVER)
                .addExtraItem(GOLDEN_CARROT, 4)
                .addExtraItem(GOLDEN_APPLE, 4)
                .addExtraItem(GLISTERING_MELON_SLICE, 4)
                .addSpirit(SACRED_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);

        new SoulBindingRecipeBuilder(REFINED_SOULSTONE, 12, MalumGeasEffectTypes.PACT_OF_THE_WARLOCK)
                .addExtraItem(ARCANE_CHARCOAL, 4)
                .addExtraItem(HEX_ASH, 4)
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(REFINED_SOULSTONE, 12, MalumGeasEffectTypes.PACT_OF_THE_REAPER)
                .addExtraItem(GRIM_TALC, 4)
                .addExtraItem(HEX_ASH, 4)
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(AERIAL_SPIRIT, 16)
                .addSpirit(INFERNAL_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(IMITATION_HEART, 8, MalumGeasEffectTypes.PACT_OF_THE_BERSERKER)
                .addExtraItem(LIVING_FLESH, 16)
                .addExtraItem(GRIM_TALC, 16)
                .addExtraItem(ROTTEN_FLESH, 16)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(INFERNAL_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);


        new SoulBindingRecipeBuilder(SOUL_STAINED_STEEL_PLATING, 12, MalumGeasEffectTypes.PACT_OF_THE_FORTRESS)
                .addExtraItem(REFINED_SOULSTONE, 8)
                .addSpirit(ARCANE_SPIRIT, 16)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(SOUL_STAINED_STEEL_PLATING, 12, MalumGeasEffectTypes.PACT_OF_THE_SHIELD)
                .addExtraItem(REFINED_SOULSTONE, 8)
                .addSpirit(ARCANE_SPIRIT, 16)
                .addSpirit(AERIAL_SPIRIT, 16)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(IMITATION_FLESH, 8, MalumGeasEffectTypes.PACT_OF_RECIPROCATION)
                .addExtraItem(WARP_FLUX, 8)
                .addExtraItem(REFINED_SOULSTONE, 8)
                .addExtraItem(SOUL_STAINED_STEEL_PLATING, 8)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);


        new SoulBindingRecipeBuilder(RAW_SOULSTONE, 12, MalumGeasEffectTypes.PACT_OF_THE_SHATTERING_ADDICT)
                .addExtraItem(HEX_ASH, 8)
                .addExtraItem(WARP_FLUX, 4)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .addSpirit(WICKED_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(RAW_SOULSTONE, 12, MalumGeasEffectTypes.PACT_OF_THE_ARCANAPHAGE)
                .addExtraItem(WARP_FLUX, 8)
                .addExtraItem(SOUL_STAINED_STEEL_PLATING, 4)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .addSpirit(WICKED_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(IMITATION_HEART, 8, MalumGeasEffectTypes.PACT_OF_RUNE_EXPLOITATION)
                .addExtraItem(WARP_FLUX, 8)
                .addExtraItem(REFINED_SOULSTONE, 8)
                .addExtraItem(HEX_ASH, 8)
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(SACRED_SPIRIT, 16)
                .save(recipeOutput);


        new SoulBindingRecipeBuilder(PRISMARINE_CRYSTALS, 12, MalumGeasEffectTypes.PACT_OF_SELF_CARE)
                .addExtraItem(SALMON, 8)
                .addExtraItem(NAUTILUS_SHELL, 2)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(SACRED_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(PRISMARINE_CRYSTALS, 12, MalumGeasEffectTypes.PACT_OF_THE_HIGH_PRIEST)
                .addExtraItem(PRISMARINE_SHARD, 8)
                .addExtraItem(NAUTILUS_SHELL, 2)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(AERIAL_SPIRIT, 16)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(HEART_OF_THE_SEA, 1, MalumGeasEffectTypes.PACT_OF_PATIENCE_REPAID)
                .addExtraItem(PRISMARINE_SHARD, 8)
                .addExtraItem(PRISMARINE_CRYSTALS, 8)
                .addExtraItem(COPPER_INGOT, 8)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .addSpirit(SACRED_SPIRIT, 32)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);


        new SoulBindingRecipeBuilder(FEATHER, 12, MalumGeasEffectTypes.PACT_OF_THE_WINDSWEPT)
                .addExtraItem(LEATHER, 8)
                .addExtraItem(EERIE_WEAVE, 4)
                .addSpirit(AERIAL_SPIRIT, 16)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(FEATHER, 12, MalumGeasEffectTypes.PACT_OF_THE_CONTINUING_SHOT)
                .addExtraItem(ARROW, 8)
                .addExtraItem(EERIE_WEAVE, 4)
                .addSpirit(AERIAL_SPIRIT, 16)
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(HEAVY_CORE, 1, MalumGeasEffectTypes.PACT_OF_THE_SKYBREAKER)
                .addExtraItem(EERIE_WEAVE, 8)
                .addExtraItem(WIND_NUCLEUS, 8)
                .addExtraItem(PHANTOM_MEMBRANE, 8)
                .addSpirit(AERIAL_SPIRIT, 32)
                .addSpirit(EARTHEN_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);


        new SoulBindingRecipeBuilder(Ingredient.of(ItemTags.SAPLINGS), 12, MalumGeasEffectTypes.PACT_OF_CONTENTEDNESS)
                .addExtraItem(BREAD, 8)
                .addExtraItem(EMERALD, 4)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .addSpirit(SACRED_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(Ingredient.of(ItemTags.SAPLINGS), 12, MalumGeasEffectTypes.PACT_OF_THE_LONE_DRUID)
                .addExtraItem(CHAIN, 8)
                .addExtraItem(EMERALD, 4)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(ROTTING_ESSENCE, 16, MalumGeasEffectTypes.PACT_OF_THE_PROFANE_ASCETIC)
                .addExtraItem(IMITATION_FLESH, 8)
                .addExtraItem(ROTTEN_FLESH, 8)
                .addExtraItem(GRIM_TALC, 8)
                .addSpirit(EARTHEN_SPIRIT, 32)
                .addSpirit(SACRED_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(ROTTING_ESSENCE, 16, MalumGeasEffectTypes.PACT_OF_THE_PROFANE_GLUTTON)
                .addExtraItem(IMITATION_HEART, 8)
                .addExtraItem(ROTTEN_FLESH, 8)
                .addExtraItem(SPIDER_EYE, 8)
                .addSpirit(EARTHEN_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);

        new SoulBindingRecipeBuilder(GUNPOWDER, 12, MalumGeasEffectTypes.PACT_OF_COMBUSTION)
                .addExtraItem(BLAZE_POWDER, 8)
                .addExtraItem(BLAZING_QUARTZ, 4)
                .addSpirit(INFERNAL_SPIRIT, 16)
                .addSpirit(WICKED_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(GUNPOWDER, 12, MalumGeasEffectTypes.PACT_OF_THE_PROSPECTOR)
                .addExtraItem(BLAZE_POWDER, 8)
                .addExtraItem(RAW_GOLD, 4)
                .addSpirit(INFERNAL_SPIRIT, 16)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .addSpirit(SACRED_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(BLAZE_POWDER, 16, MalumGeasEffectTypes.PACT_OF_THE_BLASTWEAVER)
                .addExtraItem(TNT, 8)
                .addExtraItem(FIRE_CHARGE, 8)
                .addExtraItem(GUNPOWDER, 8)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(INFERNAL_SPIRIT, 32)
                .addSpirit(AERIAL_SPIRIT, 16)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(BLAZE_POWDER, 16, MalumGeasEffectTypes.PACT_OF_WYRD_RECONSTRUCTION)
                .addExtraItem(IMITATION_FLESH, 8)
                .addExtraItem(ROTTEN_FLESH, 8)
                .addExtraItem(GHAST_TEAR, 8)
                .addSpirit(SACRED_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .save(recipeOutput);


        new SoulBindingRecipeBuilder(MNEMONIC_FRAGMENT, 8, MalumGeasEffectTypes.OATH_OF_THE_OVERKEEN_EYE)
                .addExtraItem(NULL_SLATE, 8)
                .addExtraItem(IMITATION_HEART, 4)
                .addExtraItem(ENDER_PEARL, 4)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(AERIAL_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(MNEMONIC_FRAGMENT, 8, MalumGeasEffectTypes.OATH_OF_THE_OVERBURDENED_MIND)
                .addExtraItem(AURIC_EMBERS, 8)
                .addExtraItem(IMITATION_HEART, 4)
                .addExtraItem(ECHO_SHARD, 4)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(MNEMONIC_FRAGMENT, 8, MalumGeasEffectTypes.OATH_OF_THE_OVEREAGER_FIST)
                .addExtraItem(VOID_SALTS, 8)
                .addExtraItem(IMITATION_HEART, 4)
                .addExtraItem(BLAZE_POWDER, 4)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(INFERNAL_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .save(recipeOutput);

        new SoulBindingRecipeBuilder(MALIGNANT_PEWTER_PLATING, 8, MalumGeasEffectTypes.OATH_OF_UNMAKERS_DISDAIN)
                .addExtraItem(VOID_SALTS, 8)
                .addExtraItem(IMITATION_FLESH, 4)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(MALIGNANT_PEWTER_PLATING, 8, MalumGeasEffectTypes.OATH_OF_UNSIGHTED_RESISTANCE)
                .addExtraItem(NETHERITE_SCRAP, 8)
                .addExtraItem(IMITATION_FLESH, 4)
                .addSpirit(EARTHEN_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(MALIGNANT_PEWTER_PLATING, 8, MalumGeasEffectTypes.OATH_OF_THE_UNDISCERNED_MAW)
                .addExtraItem(LIVING_FLESH, 8)
                .addExtraItem(IMITATION_FLESH, 4)
                .addSpirit(SACRED_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .save(recipeOutput);


        new SoulBindingRecipeBuilder(FUSED_CONSCIOUSNESS, 1, MalumGeasEffectTypes.AUTHORITY_OF_THE_GLEEFUL_TARGET)
                .addExtraItem(MALIGNANT_PEWTER_PLATING, 16)
                .addExtraItem(IMITATION_FLESH, 16)
                .addExtraItem(VOID_SALTS, 16)
                .addSpirit(SACRED_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .addSpirit(AERIAL_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .addSpirit(EARTHEN_SPIRIT, 32)
                .addSpirit(INFERNAL_SPIRIT, 32)
                .save(recipeOutput);
        new SoulBindingRecipeBuilder(FUSED_CONSCIOUSNESS, 1, MalumGeasEffectTypes.AUTHORITY_OF_THE_INVERTED_HEART)
                .addExtraItem(MALIGNANT_PEWTER_PLATING, 16)
                .addExtraItem(IMITATION_HEART, 16)
                .addExtraItem(MNEMONIC_FRAGMENT, 16)
                .addSpirit(SACRED_SPIRIT, 32)
                .addSpirit(WICKED_SPIRIT, 32)
                .addSpirit(ARCANE_SPIRIT, 32)
                .addSpirit(ELDRITCH_SPIRIT, 32)
                .addSpirit(AERIAL_SPIRIT, 32)
                .addSpirit(AQUEOUS_SPIRIT, 32)
                .addSpirit(EARTHEN_SPIRIT, 32)
                .addSpirit(INFERNAL_SPIRIT, 32)
                .save(recipeOutput);

    }
}
