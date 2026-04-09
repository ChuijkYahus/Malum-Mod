package com.sammy.malum.datagen.recipe.infusion;

import com.sammy.malum.common.data.component.*;
import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.datagen.recipe.builder.*;
import com.sammy.malum.registry.common.content.MalumContent;
import com.sammy.malum.registry.common.content.item.MalumItemProperties;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.crafting.SizedIngredient;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class MaterialSpiritInfusionRecipes {

    public static void buildRecipes(RecipeOutput recipeOutput) {
        spiritedGlassRecipe(recipeOutput, SACRED_SPIRIT, MalumItemProperties.SACRED_SPIRITED_GLASS.get());
        spiritedGlassRecipe(recipeOutput, WICKED_SPIRIT, MalumItemProperties.WICKED_SPIRITED_GLASS.get());
        spiritedGlassRecipe(recipeOutput, ARCANE_SPIRIT, MalumItemProperties.ARCANE_SPIRITED_GLASS.get());
        spiritedGlassRecipe(recipeOutput, ELDRITCH_SPIRIT, MalumItemProperties.ELDRITCH_SPIRITED_GLASS.get());
        spiritedGlassRecipe(recipeOutput, AERIAL_SPIRIT, MalumItemProperties.AERIAL_SPIRITED_GLASS.get());
        spiritedGlassRecipe(recipeOutput, AQUEOUS_SPIRIT, MalumItemProperties.AQUEOUS_SPIRITED_GLASS.get());
        spiritedGlassRecipe(recipeOutput, EARTHEN_SPIRIT, MalumItemProperties.EARTHEN_SPIRITED_GLASS.get());
        spiritedGlassRecipe(recipeOutput, INFERNAL_SPIRIT, MalumItemProperties.INFERNAL_SPIRITED_GLASS.get());
        new SpiritInfusionRecipeBuilder(Ingredient.of(Tags.Items.GLASS_BLOCKS), 8, MalumItemProperties.NULL_SPIRITED_GLASS.get(), 8)
                .addSpirit(ARCANE_SPIRIT, 2)
                .addSpirit(ELDRITCH_SPIRIT, 2)
                .addExtraItem(Items.IRON_INGOT, 1)
                .addExtraItem(MalumContent.Materials.NULL_SLATE.get(), 1)
                .save(recipeOutput);

        varnishedTerracottaRecipe(recipeOutput, SACRED_SPIRIT, MalumItemProperties.SACRED_VARNISHED_TERRACOTTA.get());
        varnishedTerracottaRecipe(recipeOutput, WICKED_SPIRIT, MalumItemProperties.WICKED_VARNISHED_TERRACOTTA.get());
        varnishedTerracottaRecipe(recipeOutput, ARCANE_SPIRIT, MalumItemProperties.ARCANE_VARNISHED_TERRACOTTA.get());
        varnishedTerracottaRecipe(recipeOutput, ELDRITCH_SPIRIT, MalumItemProperties.ELDRITCH_VARNISHED_TERRACOTTA.get());
        varnishedTerracottaRecipe(recipeOutput, AERIAL_SPIRIT, MalumItemProperties.AERIAL_VARNISHED_TERRACOTTA.get());
        varnishedTerracottaRecipe(recipeOutput, AQUEOUS_SPIRIT, MalumItemProperties.AQUEOUS_VARNISHED_TERRACOTTA.get());
        varnishedTerracottaRecipe(recipeOutput, EARTHEN_SPIRIT, MalumItemProperties.EARTHEN_VARNISHED_TERRACOTTA.get());
        varnishedTerracottaRecipe(recipeOutput, INFERNAL_SPIRIT, MalumItemProperties.INFERNAL_VARNISHED_TERRACOTTA.get());
        new SpiritInfusionRecipeBuilder(Items.TERRACOTTA, 8, MalumItemProperties.NULL_VARNISHED_TERRACOTTA.get(), 8)
                .addSpirit(ARCANE_SPIRIT, 2)
                .addSpirit(ELDRITCH_SPIRIT, 2)
                .addExtraItem(MalumContent.Materials.ALCHEMICAL_CALX.get(), 1)
                .addExtraItem(MalumContent.Materials.NULL_SLATE.get(), 1)
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

        new SpiritInfusionRecipeBuilder(Items.GUNPOWDER, 1, MalumContent.Materials.HEX_ASH.get(), 1)
                .addSpirit(ARCANE_SPIRIT, 1)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.ROTTEN_FLESH, 4, MalumContent.Materials.LIVING_FLESH.get(), 2)
                .addSpirit(SACRED_SPIRIT, 2)
                .addSpirit(WICKED_SPIRIT, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.CLAY_BALL, 4, MalumContent.Materials.ALCHEMICAL_CALX.get(), 4)
                .addSpirit(ARCANE_SPIRIT, 2)
                .addSpirit(EARTHEN_SPIRIT, 2)
                .addSpirit(AQUEOUS_SPIRIT, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Ingredient.of(ItemTags.COALS), 4, MalumContent.Materials.ARCANE_CHARCOAL.get(), 4)
                .addSpirit(ARCANE_SPIRIT, 1)
                .addSpirit(INFERNAL_SPIRIT, 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Ingredient.of(ItemTags.STONE_TOOL_MATERIALS), 16, MalumItemProperties.TAINTED_ROCK.get(), 16)
                .addSpirit(SACRED_SPIRIT, 1)
                .addSpirit(ARCANE_SPIRIT, 1)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Ingredient.of(ItemTags.STONE_TOOL_MATERIALS), 16, MalumItemProperties.TWISTED_ROCK.get(), 16)
                .addSpirit(WICKED_SPIRIT, 1)
                .addSpirit(ARCANE_SPIRIT, 1)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.GLOWSTONE_DUST, 4, MalumItemProperties.ETHER.get(), 2)
                .addSpirit(INFERNAL_SPIRIT, 2)
                .addSpirit(ARCANE_SPIRIT, 1)
                .addExtraItem(MalumItemProperties.BLAZING_QUARTZ.get(), 1)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItemProperties.ETHER.get(), 1, MalumItemProperties.IRIDESCENT_ETHER.get(), 1)
                .addSpirit(AQUEOUS_SPIRIT, 2)
                .addExtraItem(Items.PRISMARINE_CRYSTALS, 1)
                .addExtraItem(MalumContent.Materials.ARCANE_CHARCOAL.get(), 1)
                .carryOverComponentData()
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.GOLD_INGOT, 1, MalumContent.Materials.HALLOWED_GOLD_INGOT.get(), 1)
                .addExtraItem(SizedIngredient.of(Tags.Items.GEMS_QUARTZ, 4))
                .addSpirit(SACRED_SPIRIT, 2)
                .addSpirit(ARCANE_SPIRIT, 1)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Items.IRON_INGOT, 1, MalumContent.Materials.SOUL_STAINED_STEEL_INGOT.get(), 1)
                .addExtraItem(MalumContent.Materials.REFINED_SOULSTONE.get(), 4)
                .addSpirit(WICKED_SPIRIT, 3)
                .addSpirit(EARTHEN_SPIRIT, 1)
                .addSpirit(ARCANE_SPIRIT, 1)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Ingredient.of(ItemTags.WOOL), 2, MalumContent.Materials.SOULWOVEN_SILK.get(), 4)
                .addExtraItem(SizedIngredient.of(Tags.Items.STRINGS, 2))
                .addSpirit(AERIAL_SPIRIT, 3)
                .addSpirit(EARTHEN_SPIRIT, 3)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumItemProperties.ETHER.get(), 1, MalumContent.Materials.PARACAUSAL_FLAME.get(), 1)
                .addExtraItem(MalumContent.Materials.HEX_ASH.get(), 8)
                .addExtraItem(MalumContent.Materials.WARP_FLUX.get(), 4)
                .addExtraItem(SizedIngredient.of(Tags.Items.OBSIDIANS_CRYING, 2))
                .addSpirit(ARCANE_SPIRIT, 8)
                .addSpirit(ELDRITCH_SPIRIT, 8)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Materials.ALCHEMICAL_CALX.get(), 4, MalumContent.Materials.IMITATION_FLESH.get(), 4)
                .addExtraItem(MalumContent.Materials.LIVING_FLESH.get(), 8)
                .addExtraItem(Items.NETHER_WART, 4)
                .addExtraItem(MalumContent.Materials.ROTTING_ESSENCE.get(), 2)
                .addSpirit(SACRED_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Materials.ALCHEMICAL_CALX.get(), 4, MalumContent.Materials.IMITATION_HEART.get(), 4)
                .addExtraItem(MalumContent.Materials.HEX_ASH.get(), 8)
                .addExtraItem(MalumContent.Materials.REFINED_SOULSTONE.get(), 4)
                .addExtraItem(MalumContent.Materials.WARP_FLUX.get(), 2)
                .addSpirit(WICKED_SPIRIT, 16)
                .addSpirit(ARCANE_SPIRIT, 16)
                .addSpirit(AQUEOUS_SPIRIT, 16)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Ingredient.of(Tags.Items.INGOTS_IRON), 4, MalumItemProperties.ESOTERIC_SPOOL.get(), 4)
                .addSpirit(ARCANE_SPIRIT, 4)
                .addExtraItem(MalumContent.Materials.HEX_ASH.get(), 2)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(MalumContent.Materials.ANOMALOUS_DESIGN.get(), 1, MalumContent.Materials.COMPLETE_DESIGN.get(), 1)
                .addSpirit(SACRED_SPIRIT, 4)
                .addSpirit(WICKED_SPIRIT, 4)
                .addSpirit(ARCANE_SPIRIT, 4)
                .addSpirit(ELDRITCH_SPIRIT, 4)
                .addSpirit(AERIAL_SPIRIT, 4)
                .addSpirit(AQUEOUS_SPIRIT, 4)
                .addSpirit(EARTHEN_SPIRIT, 4)
                .addSpirit(INFERNAL_SPIRIT, 4)
                .save(recipeOutput);

        new SpiritInfusionRecipeBuilder(Ingredient.of(Tags.Items.INGOTS_IRON), 4, MalumContent.Materials.MALIGNANT_PEWTER_INGOT.get(), 1)
                .addExtraItem(MalumContent.Materials.MALIGNANT_LEAD.get(), 1)
                .addExtraItem(MalumContent.Materials.NULL_SLATE.get(), 8)
                .addExtraItem(Items.NETHERITE_SCRAP, 2)
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

    public static void varnishedTerracottaRecipe(RecipeOutput recipeOutput, SpiritHolder<SpiritArcanaType> spirit, Item terracotta) {
        new SpiritInfusionRecipeBuilder(Items.TERRACOTTA, 8, terracotta, 8)
                .addSpirit(spirit, 2)
                .addExtraItem(MalumContent.Materials.ALCHEMICAL_CALX.get(), 1)
                .save(recipeOutput);
    }

    @SafeVarargs
    public static void soulwovenBannerRecipe(RecipeOutput recipeOutput, SoulwovenBannerPatternDataComponent pattern, SpiritHolder<SpiritArcanaType>... spirits) {
        final SpiritInfusionRecipeBuilder builder = new SpiritInfusionRecipeBuilder(MalumItemProperties.SOULWOVEN_BANNER.get(), pattern.getDefaultStack());
        for (SpiritHolder<SpiritArcanaType> spirit : spirits) {
            builder.addSpirit(spirit, 1);
        }
        builder.save(recipeOutput, pattern.getRecipeId());
    }
}
