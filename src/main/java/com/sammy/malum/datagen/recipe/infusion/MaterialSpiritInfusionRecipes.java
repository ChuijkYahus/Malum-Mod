package com.sammy.malum.datagen.recipe.infusion;

import com.sammy.malum.common.data.component.*;
import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.datagen.recipe.builder.*;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.crafting.SizedIngredient;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class MaterialSpiritInfusionRecipes {

    public static void buildRecipes(RecipeOutput recipeOutput) {
        spiritedGlassRecipe(recipeOutput, SACRED_SPIRIT, MalumItems.SACRED_SPIRITED_GLASS.get());
        spiritedGlassRecipe(recipeOutput, WICKED_SPIRIT, MalumItems.WICKED_SPIRITED_GLASS.get());
        spiritedGlassRecipe(recipeOutput, ARCANE_SPIRIT, MalumItems.ARCANE_SPIRITED_GLASS.get());
        spiritedGlassRecipe(recipeOutput, ELDRITCH_SPIRIT, MalumItems.ELDRITCH_SPIRITED_GLASS.get());
        spiritedGlassRecipe(recipeOutput, AERIAL_SPIRIT, MalumItems.AERIAL_SPIRITED_GLASS.get());
        spiritedGlassRecipe(recipeOutput, AQUEOUS_SPIRIT, MalumItems.AQUEOUS_SPIRITED_GLASS.get());
        spiritedGlassRecipe(recipeOutput, EARTHEN_SPIRIT, MalumItems.EARTHEN_SPIRITED_GLASS.get());
        spiritedGlassRecipe(recipeOutput, INFERNAL_SPIRIT, MalumItems.INFERNAL_SPIRITED_GLASS.get());

        soulwovenBannerRecipe(recipeOutput, SACRED_SPIRIT, SoulwovenBannerPatternDataComponent.SACRED);
        soulwovenBannerRecipe(recipeOutput, WICKED_SPIRIT, SoulwovenBannerPatternDataComponent.WICKED);
        soulwovenBannerRecipe(recipeOutput, ARCANE_SPIRIT, SoulwovenBannerPatternDataComponent.ARCANE);
        soulwovenBannerRecipe(recipeOutput, ELDRITCH_SPIRIT, SoulwovenBannerPatternDataComponent.ELDRITCH);
        soulwovenBannerRecipe(recipeOutput, AERIAL_SPIRIT, SoulwovenBannerPatternDataComponent.AERIAL);
        soulwovenBannerRecipe(recipeOutput, AQUEOUS_SPIRIT, SoulwovenBannerPatternDataComponent.AQUEOUS);
        soulwovenBannerRecipe(recipeOutput, EARTHEN_SPIRIT, SoulwovenBannerPatternDataComponent.EARTHEN);
        soulwovenBannerRecipe(recipeOutput, INFERNAL_SPIRIT, SoulwovenBannerPatternDataComponent.INFERNAL);

        new SpiritInfusionRecipeBuilder(Items.GUNPOWDER, 1, MalumItems.HEX_ASH.get(), 1)
                .addSpirit(ARCANE_SPIRIT, 1)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.ROTTEN_FLESH, 4, MalumItems.LIVING_FLESH.get(), 2)
                .addSpirit(SACRED_SPIRIT, 2)
                .addSpirit(WICKED_SPIRIT, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.CLAY_BALL, 4, MalumItems.ALCHEMICAL_CALX.get(), 4)
                .addSpirit(ARCANE_SPIRIT, 2)
                .addSpirit(EARTHEN_SPIRIT, 2)
                .addSpirit(AQUEOUS_SPIRIT, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Ingredient.of(ItemTags.COALS), 4, MalumItems.ARCANE_CHARCOAL.get(), 4)
                .addSpirit(ARCANE_SPIRIT, 1)
                .addSpirit(INFERNAL_SPIRIT, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Ingredient.of(ItemTags.STONE_TOOL_MATERIALS), 16, MalumItems.TAINTED_ROCK.get(), 16)
                .addSpirit(SACRED_SPIRIT, 1)
                .addSpirit(ARCANE_SPIRIT, 1)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Ingredient.of(ItemTags.STONE_TOOL_MATERIALS), 16, MalumItems.TWISTED_ROCK.get(), 16)
                .addSpirit(WICKED_SPIRIT, 1)
                .addSpirit(ARCANE_SPIRIT, 1)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.GLOWSTONE_DUST, 4, MalumItems.ETHER.get(), 2)
                .addSpirit(INFERNAL_SPIRIT, 2)
                .addSpirit(ARCANE_SPIRIT, 1)
                .addExtraItem(MalumItems.BLAZING_QUARTZ.get(), 1)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.ETHER.get(), 1, MalumItems.IRIDESCENT_ETHER.get(), 1)
                .addSpirit(AQUEOUS_SPIRIT, 2)
                .addExtraItem(Items.PRISMARINE_CRYSTALS, 1)
                .addExtraItem(MalumItems.ARCANE_CHARCOAL.get(), 1)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.GOLD_INGOT, 1, MalumItems.HALLOWED_GOLD_INGOT.get(), 1)
                .addExtraItem(SizedIngredient.of(Tags.Items.GEMS_QUARTZ, 4))
                .addSpirit(SACRED_SPIRIT, 2)
                .addSpirit(ARCANE_SPIRIT, 1)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.IRON_INGOT, 1, MalumItems.SOUL_STAINED_STEEL_INGOT.get(), 1)
                .addExtraItem(MalumItems.REFINED_SOULSTONE.get(), 4)
                .addSpirit(WICKED_SPIRIT, 3)
                .addSpirit(EARTHEN_SPIRIT, 1)
                .addSpirit(ARCANE_SPIRIT, 1)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Ingredient.of(ItemTags.WOOL), 2, MalumItems.SOULWOVEN_SILK.get(), 4)
                .addExtraItem(SizedIngredient.of(Tags.Items.STRINGS, 2))
                .addSpirit(AERIAL_SPIRIT, 3)
                .addSpirit(EARTHEN_SPIRIT, 3)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.ETHER.get(), 1, MalumItems.PARACAUSAL_FLAME.get(), 1)
                .addExtraItem(SizedIngredient.of(Tags.Items.OBSIDIANS_CRYING, 4))
                .addExtraItem(MalumItems.HEX_ASH.get(), 8)
                .addSpirit(ARCANE_SPIRIT, 8)
                .addSpirit(ELDRITCH_SPIRIT, 8)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.ALCHEMICAL_CALX.get(), 4, MalumItems.IMITATION_FLESH.get(), 4)
                .addExtraItem(MalumItems.LIVING_FLESH.get(), 8)
                .addExtraItem(Items.NETHER_WART, 4)
                .addExtraItem(MalumItems.ROTTING_ESSENCE.get(), 2)
                .addSpirit(SACRED_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.ALCHEMICAL_CALX.get(), 4, MalumItems.IMITATION_HEART.get(), 4)
                .addExtraItem(MalumItems.HEX_ASH.get(), 8)
                .addExtraItem(MalumItems.REFINED_SOULSTONE.get(), 4)
                .addExtraItem(MalumItems.WARP_FLUX.get(), 2)
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Ingredient.of(Tags.Items.INGOTS_IRON), 4, MalumItems.ESOTERIC_SPOOL.get(), 4)
                .addSpirit(ARCANE_SPIRIT, 4)
                .addExtraItem(MalumItems.HEX_ASH.get(), 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItems.ANOMALOUS_DESIGN.get(), 1, MalumItems.COMPLETE_DESIGN.get(), 1)
                .addSpirit(SACRED_SPIRIT, 4)
                .addSpirit(WICKED_SPIRIT, 4)
                .addSpirit(ARCANE_SPIRIT, 4)
                .addSpirit(ELDRITCH_SPIRIT, 4)
                .addSpirit(AERIAL_SPIRIT, 4)
                .addSpirit(AQUEOUS_SPIRIT, 4)
                .addSpirit(EARTHEN_SPIRIT, 4)
                .addSpirit(INFERNAL_SPIRIT, 4)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Ingredient.of(Tags.Items.INGOTS_IRON), 4, MalumItems.MALIGNANT_PEWTER_INGOT.get(), 1)
                .addExtraItem(MalumItems.MALIGNANT_LEAD.get(), 1)
                .addExtraItem(MalumItems.NULL_SLATE.get(), 8)
                .addExtraItem(Items.NETHERITE_SCRAP, 3)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);
    }

    public static void spiritedGlassRecipe(RecipeOutput recipeOutput, SpiritHolder<SpiritArcanaType> spirit, Item glass) {
        new SpiritInfusionRecipeBuilder(Ingredient.of(Tags.Items.GLASS_BLOCKS), 8, glass, 8)
                .addSpirit(spirit, 2)
                .addExtraItem(Items.IRON_INGOT, 1)
                .save(recipeOutput);
    }

    public static void soulwovenBannerRecipe(RecipeOutput recipeOutput, SpiritHolder<SpiritArcanaType> spirit, SoulwovenBannerPatternDataComponent pattern) {
        new SpiritInfusionRecipeBuilder(MalumItems.SOULWOVEN_BANNER.get(), pattern.getDefaultStack())
                .addSpirit(spirit, 1)
                .save(recipeOutput, pattern.getRecipeId());
    }
}
