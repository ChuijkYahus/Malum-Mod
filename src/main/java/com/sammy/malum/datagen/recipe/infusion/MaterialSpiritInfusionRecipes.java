package com.sammy.malum.datagen.recipe.infusion;

import com.sammy.malum.common.data.component.*;
import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.datagen.recipe.builder.*;
import com.sammy.malum.registry.common.MalumContent;
import com.sammy.malum.registry.common.MalumContent.Vanity;
import com.sammy.malum.registry.common.item.MalumItemProperties;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.crafting.SizedIngredient;

import static com.sammy.malum.registry.common.MalumContent.BlockSets.*;
import static com.sammy.malum.registry.common.MalumContent.Materials.*;
import static com.sammy.malum.registry.common.MalumContent.Vanity.*;
import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class MaterialSpiritInfusionRecipes {

    public static void buildRecipes(RecipeOutput recipeOutput) {
        spiritedGlassRecipe(recipeOutput, SACRED_SPIRIT, SACRED_SPIRITED_GLASS);
        spiritedGlassRecipe(recipeOutput, WICKED_SPIRIT, WICKED_SPIRITED_GLASS);
        spiritedGlassRecipe(recipeOutput, ARCANE_SPIRIT, ARCANE_SPIRITED_GLASS);
        spiritedGlassRecipe(recipeOutput, ELDRITCH_SPIRIT, ELDRITCH_SPIRITED_GLASS);
        spiritedGlassRecipe(recipeOutput, AERIAL_SPIRIT, AERIAL_SPIRITED_GLASS);
        spiritedGlassRecipe(recipeOutput, AQUEOUS_SPIRIT, AQUEOUS_SPIRITED_GLASS);
        spiritedGlassRecipe(recipeOutput, EARTHEN_SPIRIT, EARTHEN_SPIRITED_GLASS);
        spiritedGlassRecipe(recipeOutput, INFERNAL_SPIRIT, INFERNAL_SPIRITED_GLASS);
        new SpiritInfusionRecipeBuilder(Ingredient.of(Tags.Items.GLASS_BLOCKS), 8, NULL_SPIRITED_GLASS, 8)
                .addSpirit(ARCANE_SPIRIT, 2)
                .addSpirit(ELDRITCH_SPIRIT, 2)
                .addExtraItem(Items.IRON_INGOT, 1)
                .addExtraItem(NULL_SLATE, 1)
                .save(recipeOutput);

        varnishedTerracottaRecipe(recipeOutput, SACRED_SPIRIT, SACRED_VARNISHED_TERRACOTTA);
        varnishedTerracottaRecipe(recipeOutput, WICKED_SPIRIT, WICKED_VARNISHED_TERRACOTTA);
        varnishedTerracottaRecipe(recipeOutput, ARCANE_SPIRIT, ARCANE_VARNISHED_TERRACOTTA);
        varnishedTerracottaRecipe(recipeOutput, ELDRITCH_SPIRIT, ELDRITCH_VARNISHED_TERRACOTTA);
        varnishedTerracottaRecipe(recipeOutput, AERIAL_SPIRIT, AERIAL_VARNISHED_TERRACOTTA);
        varnishedTerracottaRecipe(recipeOutput, AQUEOUS_SPIRIT, AQUEOUS_VARNISHED_TERRACOTTA);
        varnishedTerracottaRecipe(recipeOutput, EARTHEN_SPIRIT, EARTHEN_VARNISHED_TERRACOTTA);
        varnishedTerracottaRecipe(recipeOutput, INFERNAL_SPIRIT, INFERNAL_VARNISHED_TERRACOTTA);
        new SpiritInfusionRecipeBuilder(Items.TERRACOTTA, 8, NULL_VARNISHED_TERRACOTTA, 8)
                .addSpirit(ARCANE_SPIRIT, 2)
                .addSpirit(ELDRITCH_SPIRIT, 2)
                .addExtraItem(ALCHEMICAL_CALX, 1)
                .addExtraItem(NULL_SLATE, 1)
                .save(recipeOutput);

        soulwovenBannerRecipe(recipeOutput, SoulwovenBannerPatternDataComponent.SACRED, SACRED_SPIRIT);
        soulwovenBannerRecipe(recipeOutput, SoulwovenBannerPatternDataComponent.WICKED, WICKED_SPIRIT);
        soulwovenBannerRecipe(recipeOutput, SoulwovenBannerPatternDataComponent.ARCANE, ARCANE_SPIRIT);
        soulwovenBannerRecipe(recipeOutput, SoulwovenBannerPatternDataComponent.ELDRITCH, ELDRITCH_SPIRIT);
        soulwovenBannerRecipe(recipeOutput, SoulwovenBannerPatternDataComponent.AERIAL, AERIAL_SPIRIT);
        soulwovenBannerRecipe(recipeOutput, SoulwovenBannerPatternDataComponent.AQUEOUS, AQUEOUS_SPIRIT);
        soulwovenBannerRecipe(recipeOutput, SoulwovenBannerPatternDataComponent.EARTHEN, EARTHEN_SPIRIT);
        soulwovenBannerRecipe(recipeOutput, SoulwovenBannerPatternDataComponent.INFERNAL, INFERNAL_SPIRIT);
        soulwovenBannerRecipe(recipeOutput, SoulwovenBannerPatternDataComponent.COLORFUL_WORLD, AERIAL_SPIRIT, AQUEOUS_SPIRIT, EARTHEN_SPIRIT, INFERNAL_SPIRIT);

        new SpiritInfusionRecipeBuilder(Items.GUNPOWDER, 1, HEX_ASH, 1)
                .addSpirit(ARCANE_SPIRIT, 1)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.ROTTEN_FLESH, 4, LIVING_FLESH, 2)
                .addSpirit(SACRED_SPIRIT, 2)
                .addSpirit(WICKED_SPIRIT, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.CLAY_BALL, 4, ALCHEMICAL_CALX, 4)
                .addSpirit(ARCANE_SPIRIT, 2)
                .addSpirit(EARTHEN_SPIRIT, 2)
                .addSpirit(AQUEOUS_SPIRIT, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Ingredient.of(ItemTags.COALS), 4, ARCANE_CHARCOAL, 4)
                .addSpirit(ARCANE_SPIRIT, 1)
                .addSpirit(INFERNAL_SPIRIT, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Ingredient.of(ItemTags.STONE_TOOL_MATERIALS), 16, TAINTED_ROCK_SET.getRock(), 16)
                .addSpirit(SACRED_SPIRIT, 1)
                .addSpirit(ARCANE_SPIRIT, 1)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Ingredient.of(ItemTags.STONE_TOOL_MATERIALS), 16, TWISTED_ROCK_SET.getRock(), 16)
                .addSpirit(WICKED_SPIRIT, 1)
                .addSpirit(ARCANE_SPIRIT, 1)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.GLOWSTONE_DUST, 4, ETHER, 2)
                .addSpirit(INFERNAL_SPIRIT, 2)
                .addSpirit(ARCANE_SPIRIT, 1)
                .addExtraItem(BLAZING_QUARTZ, 1)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(ETHER, 1, IRIDESCENT_ETHER, 1)
                .addSpirit(AQUEOUS_SPIRIT, 2)
                .addExtraItem(Items.PRISMARINE_CRYSTALS, 1)
                .addExtraItem(ARCANE_CHARCOAL, 1)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.GOLD_INGOT, 1, HALLOWED_GOLD_INGOT, 1)
                .addExtraItem(SizedIngredient.of(Tags.Items.GEMS_QUARTZ, 4))
                .addSpirit(SACRED_SPIRIT, 2)
                .addSpirit(ARCANE_SPIRIT, 1)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.IRON_INGOT, 1, SOUL_STAINED_STEEL_INGOT, 1)
                .addExtraItem(REFINED_SOULSTONE, 4)
                .addSpirit(WICKED_SPIRIT, 3)
                .addSpirit(EARTHEN_SPIRIT, 1)
                .addSpirit(ARCANE_SPIRIT, 1)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Ingredient.of(ItemTags.WOOL), 2, SOULWOVEN_SILK, 4)
                .addExtraItem(SizedIngredient.of(Tags.Items.STRINGS, 2))
                .addSpirit(AERIAL_SPIRIT, 3)
                .addSpirit(EARTHEN_SPIRIT, 3)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(ETHER, 1, PARACAUSAL_FLAME, 1)
                .addExtraItem(HEX_ASH, 8)
                .addExtraItem(WARP_FLUX, 4)
                .addExtraItem(SizedIngredient.of(Tags.Items.OBSIDIANS_CRYING, 2))
                .addSpirit(ARCANE_SPIRIT, 8)
                .addSpirit(ELDRITCH_SPIRIT, 8)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(ALCHEMICAL_CALX, 4, IMITATION_FLESH, 4)
                .addExtraItem(LIVING_FLESH, 8)
                .addExtraItem(Items.NETHER_WART, 4)
                .addExtraItem(ROTTING_ESSENCE, 2)
                .addSpirit(SACRED_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(ALCHEMICAL_CALX, 4, IMITATION_HEART, 4)
                .addExtraItem(HEX_ASH, 8)
                .addExtraItem(REFINED_SOULSTONE, 4)
                .addExtraItem(WARP_FLUX, 2)
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Ingredient.of(Tags.Items.INGOTS_IRON), 4, ESOTERIC_SPOOL, 4)
                .addSpirit(ARCANE_SPIRIT, 4)
                .addExtraItem(HEX_ASH, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(ANOMALOUS_DESIGN, 1, COMPLETE_DESIGN, 1)
                .addSpirit(SACRED_SPIRIT, 4)
                .addSpirit(WICKED_SPIRIT, 4)
                .addSpirit(ARCANE_SPIRIT, 4)
                .addSpirit(ELDRITCH_SPIRIT, 4)
                .addSpirit(AERIAL_SPIRIT, 4)
                .addSpirit(AQUEOUS_SPIRIT, 4)
                .addSpirit(EARTHEN_SPIRIT, 4)
                .addSpirit(INFERNAL_SPIRIT, 4)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Ingredient.of(Tags.Items.INGOTS_IRON), 4, MALIGNANT_PEWTER_INGOT, 1)
                .addExtraItem(MALIGNANT_LEAD, 1)
                .addExtraItem(NULL_SLATE, 8)
                .addExtraItem(Items.NETHERITE_SCRAP, 2)
                .addSpirit(EARTHEN_SPIRIT, 16)
                .addSpirit(ELDRITCH_SPIRIT, 16)
                .save(recipeOutput);
    }

    public static void spiritedGlassRecipe(RecipeOutput recipeOutput, SpiritHolder<SpiritArcanaType> spirit, ItemLike glass) {
        new SpiritInfusionRecipeBuilder(Ingredient.of(Tags.Items.GLASS_BLOCKS), 8, glass, 8)
                .addSpirit(spirit, 2)
                .addExtraItem(Items.IRON_INGOT, 1)
                .save(recipeOutput);
    }

    public static void varnishedTerracottaRecipe(RecipeOutput recipeOutput, SpiritHolder<SpiritArcanaType> spirit, ItemLike terracotta) {
        new SpiritInfusionRecipeBuilder(Items.TERRACOTTA, 8, terracotta, 8)
                .addSpirit(spirit, 2)
                .addExtraItem(ALCHEMICAL_CALX, 1)
                .save(recipeOutput);
    }

    @SafeVarargs
    public static void soulwovenBannerRecipe(RecipeOutput recipeOutput, SoulwovenBannerPatternDataComponent pattern, SpiritHolder<SpiritArcanaType>... spirits) {
        final SpiritInfusionRecipeBuilder builder = new SpiritInfusionRecipeBuilder(SOULWOVEN_BANNER, pattern.getDefaultStack());
        for (SpiritHolder<SpiritArcanaType> spirit : spirits) {
            builder.addSpirit(spirit, 1);
        }
        builder.save(recipeOutput, pattern.getRecipeId());
    }
}
