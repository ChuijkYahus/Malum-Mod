package com.sammy.malum.datagen.recipe;

import com.sammy.malum.*;
import com.sammy.malum.common.data.component.*;
import com.sammy.malum.datagen.recipe.builder.vanilla.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.item.*;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.*;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.neoforged.neoforge.common.conditions.NotCondition;
import net.neoforged.neoforge.common.conditions.TagEmptyCondition;
import team.lodestar.lodestone.recipe.builder.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.*;

import static com.sammy.malum.MalumMod.*;
import static net.minecraft.data.recipes.ShapedRecipeBuilder.*;
import static net.minecraft.data.recipes.ShapelessRecipeBuilder.*;
import static net.minecraft.data.recipes.SimpleCookingRecipeBuilder.*;
import static team.lodestar.lodestone.registry.common.tag.LodestoneItemTags.*;

public class MalumVanillaRecipes implements IConditionBuilder {

    protected static void buildRecipes(RecipeOutput output) {
        //KEY ITEMS
        shapeless(RecipeCategory.MISC, MalumItems.ENCYCLOPEDIA_ARCANA.get())
                .requires(Items.BOOK)
                .requires(MalumItems.REFINED_SOULSTONE.get())
                .unlockedBy("has_soulstone", has(MalumItems.REFINED_SOULSTONE.get()))
                .save(output);
        shaped(RecipeCategory.MISC, MalumItems.CRUDE_SCYTHE.get())
                .define('#', Tags.Items.RODS_WOODEN)
                .define('Y', MalumItems.REFINED_SOULSTONE.get())
                .define('X', Tags.Items.INGOTS_IRON)
                .pattern("XXY")
                .pattern(" #X")
                .pattern("#  ")
                .unlockedBy("has_soulstone", has(MalumItems.RAW_SOULSTONE.get()))
                .save(output);
        shaped(RecipeCategory.MISC, MalumItems.SPIRIT_ALTAR.get())
                .define('Z', Tags.Items.INGOTS_GOLD)
                .define('Y', MalumItems.REFINED_SOULSTONE.get())
                .define('X', MalumTags.ItemTags.RUNEWOOD_PLANKS)
                .pattern(" Y ")
                .pattern("ZXZ")
                .pattern("XXX")
                .unlockedBy("has_soulstone", has(MalumItems.RAW_SOULSTONE.get()))
                .save(output);
        shaped(RecipeCategory.MISC, MalumItems.WEAVERS_WORKBENCH.get())
                .define('Z', MalumItems.HALLOWED_GOLD_INGOT.get())
                .define('Y', MalumItems.HEX_ASH.get())
                .define('X', MalumTags.ItemTags.RUNEWOOD_PLANKS)
                .pattern("XYX")
                .pattern("XZX")
                .unlockedBy("has_hex_ash", has(MalumItems.HEX_ASH.get()))
                .save(output);
        shaped(RecipeCategory.MISC, MalumItems.SOUL_BRAZIER.get())
                .define('Z', MalumItems.CTHONIC_GOLD.get())
                .define('Y', MalumItems.HALLOWED_GOLD_INGOT.get())
                .define('X', MalumTags.ItemTags.RUNEWOOD_PLANKS)
                .define('W', MalumItems.TAINTED_ROCK.get())
                .pattern("YZY")
                .pattern("XXX")
                .pattern("WXW")
                .unlockedBy("has_soulstone", has(MalumItems.RAW_SOULSTONE.get()))
                .save(output);
        shaped(RecipeCategory.MISC, MalumItems.SPIRIT_JAR.get())
                .define('X', MalumItems.HALLOWED_GOLD_INGOT.get())
                .define('Y', Tags.Items.GLASS_BLOCKS)
                .pattern("X")
                .pattern("Y")
                .unlockedBy("has_hallowed_gold", has(MalumItems.HALLOWED_GOLD_INGOT.get()))
                .save(output);
        shaped(RecipeCategory.MISC, MalumItems.SOULWOVEN_POUCH.get())
                .define('X', Tags.Items.STRINGS)
                .define('Y', MalumItems.SOULWOVEN_SILK.get())
                .pattern("X")
                .pattern("Y")
                .unlockedBy("has_soulwoven_silk", has(MalumItems.SOULWOVEN_SILK.get()))
                .save(output);
        shaped(RecipeCategory.MISC, MalumItems.TOTEMIC_STAFF.get())
                .define('X', Tags.Items.RODS_WOODEN)
                .define('Y', MalumTags.ItemTags.RUNEWOOD_PLANKS)
                .pattern("  Y")
                .pattern(" X ")
                .pattern("X  ")
                .unlockedBy("has_totem_base", has(MalumItems.RUNEWOOD_TOTEM_BASE.get()))
                .save(output);

        //CRAFTING COMPONENTS
        shaped(RecipeCategory.MISC, MalumItems.CONVOLUTED_LENS.get(), 2)
                .define('X', MalumItems.HALLOWED_GOLD_NUGGET.get())
                .define('Y', MalumItems.WARP_FLUX.get())
                .pattern(" X ")
                .pattern("XYX")
                .pattern(" X ")
                .unlockedBy("has_hallowed_gold", has(MalumItems.HALLOWED_GOLD_INGOT.get())).save(output);

        shapeless(RecipeCategory.MISC, MalumItems.MIMICRY_RELAY.get())
                .requires(MalumItems.IRON_NODE.get())
                .requires(MalumItems.CTHONIC_GOLD_FRAGMENT.get())
                .requires(Tags.Items.GEMS_QUARTZ)
                .unlockedBy("has_iron_impetus", has(MalumItems.IRON_IMPETUS.get())).save(output);

        //ETHER
        etherTorch(output, MalumItems.ETHER_TORCH.get(), MalumItems.ETHER.get());
        etherBrazier(output, MalumItems.TAINTED_ETHER_BRAZIER.get(), MalumItems.TAINTED_ROCK.get(), MalumItems.ETHER.get());
        etherBrazier(output, MalumItems.TWISTED_ETHER_BRAZIER.get(), MalumItems.TWISTED_ROCK.get(), MalumItems.ETHER.get());
        etherTorch(output, MalumItems.IRIDESCENT_ETHER_TORCH.get(), MalumItems.IRIDESCENT_ETHER.get());
        etherBrazier(output, MalumItems.TAINTED_IRIDESCENT_ETHER_BRAZIER.get(), MalumItems.TAINTED_ROCK.get(), MalumItems.IRIDESCENT_ETHER.get());
        etherBrazier(output, MalumItems.TWISTED_IRIDESCENT_ETHER_BRAZIER.get(), MalumItems.TWISTED_ROCK.get(), MalumItems.IRIDESCENT_ETHER.get());

        //BANNERS
        shaped(RecipeCategory.BUILDING_BLOCKS, MalumItems.SOULWOVEN_BANNER.get()).define('X', MalumTags.ItemTags.RUNEWOOD_PLANKS).define('Y', MalumItems.SOULWOVEN_SILK.get()).pattern("X").pattern("Y").pattern("Y").unlockedBy("has_soulwoven_silk", has(MalumItems.SOULWOVEN_SILK.get())).save(output);
        bannerRecipe(output, MalumItems.ROTTING_ESSENCE.get(), SoulwovenBannerPatternDataComponent.HUNGER);
        bannerRecipe(output, MalumItems.GRIM_TALC.get(), SoulwovenBannerPatternDataComponent.HORNS);
        bannerRecipe(output, MalumItems.ASTRAL_WEAVE.get(), SoulwovenBannerPatternDataComponent.HEFT);
        bannerRecipe(output, MalumItems.WARP_FLUX.get(), SoulwovenBannerPatternDataComponent.HALLUCINATION);


        //SPIRIT METALS
        shaped(RecipeCategory.MISC, MalumItems.BLOCK_OF_SOUL_STAINED_STEEL.get()).define('#', MalumItems.SOUL_STAINED_STEEL_INGOT.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_soul_stained_steel", has(MalumItems.SOUL_STAINED_STEEL_INGOT.get())).save(output);
        shaped(RecipeCategory.MISC, MalumItems.SOUL_STAINED_STEEL_INGOT.get()).define('#', MalumItems.SOUL_STAINED_STEEL_NUGGET.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_soul_stained_steel", has(MalumItems.SOUL_STAINED_STEEL_INGOT.get())).save(output, malumPath("soul_stained_steel_from_nuggets"));
        shapeless(RecipeCategory.MISC, MalumItems.SOUL_STAINED_STEEL_NUGGET.get(), 9).requires(MalumItems.SOUL_STAINED_STEEL_INGOT.get()).unlockedBy("has_soul_stained_steel", has(MalumItems.SOUL_STAINED_STEEL_INGOT.get())).save(output);
        shapeless(RecipeCategory.MISC, MalumItems.SOUL_STAINED_STEEL_INGOT.get(), 9).requires(MalumItems.BLOCK_OF_SOUL_STAINED_STEEL.get()).unlockedBy("has_soul_stained_steel", has(MalumItems.SOUL_STAINED_STEEL_INGOT.get())).save(output, malumPath("soul_stained_steel_from_block"));
        shaped(RecipeCategory.MISC, MalumItems.SOUL_STAINED_STEEL_PLATING.get(), 2).define('X', MalumItems.SOUL_STAINED_STEEL_INGOT.get()).define('Y', MalumItems.SOUL_STAINED_STEEL_NUGGET.get()).pattern(" Y ").pattern("YXY").pattern(" Y ").unlockedBy("has_soul_stained_steel", has(MalumItems.SOUL_STAINED_STEEL_INGOT.get())).save(output);

        shaped(RecipeCategory.MISC, MalumItems.BLOCK_OF_HALLOWED_GOLD.get()).define('#', MalumItems.HALLOWED_GOLD_INGOT.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_hallowed_gold", has(MalumItems.HALLOWED_GOLD_INGOT.get())).save(output);
        shaped(RecipeCategory.MISC, MalumItems.HALLOWED_GOLD_INGOT.get()).define('#', MalumItems.HALLOWED_GOLD_NUGGET.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_hallowed_gold", has(MalumItems.HALLOWED_GOLD_INGOT.get())).save(output, malumPath("hallowed_gold_from_nuggets"));
        shapeless(RecipeCategory.MISC, MalumItems.HALLOWED_GOLD_NUGGET.get(), 9).requires(MalumItems.HALLOWED_GOLD_INGOT.get()).unlockedBy("has_hallowed_gold", has(MalumItems.HALLOWED_GOLD_INGOT.get())).save(output);
        shapeless(RecipeCategory.MISC, MalumItems.HALLOWED_GOLD_INGOT.get(), 9).requires(MalumItems.BLOCK_OF_HALLOWED_GOLD.get()).unlockedBy("has_hallowed_gold", has(MalumItems.HALLOWED_GOLD_INGOT.get())).save(output, malumPath("hallowed_gold_from_block"));

        shaped(RecipeCategory.MISC, MalumItems.BLOCK_OF_MALIGNANT_PEWTER.get()).define('#', MalumItems.MALIGNANT_PEWTER_INGOT.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_malignant_alloy", has(MalumItems.MALIGNANT_PEWTER_INGOT.get())).save(output);
        shaped(RecipeCategory.MISC, MalumItems.MALIGNANT_PEWTER_INGOT.get()).define('#', MalumItems.MALIGNANT_PEWTER_NUGGET.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_malignant_alloy", has(MalumItems.MALIGNANT_PEWTER_INGOT.get())).save(output, malumPath("malignant_alloy_from_nuggets"));
        shapeless(RecipeCategory.MISC, MalumItems.MALIGNANT_PEWTER_NUGGET.get(), 9).requires(MalumItems.MALIGNANT_PEWTER_INGOT.get()).unlockedBy("has_malignant_alloy", has(MalumItems.MALIGNANT_PEWTER_INGOT.get())).save(output);
        shapeless(RecipeCategory.MISC, MalumItems.MALIGNANT_PEWTER_INGOT.get(), 9).requires(MalumItems.BLOCK_OF_MALIGNANT_PEWTER.get()).unlockedBy("has_malignant_alloy", has(MalumItems.MALIGNANT_PEWTER_INGOT.get())).save(output, malumPath("malignant_alloy_from_block"));
        shaped(RecipeCategory.MISC, MalumItems.MALIGNANT_PEWTER_PLATING.get(), 2).define('X', MalumItems.MALIGNANT_PEWTER_INGOT.get()).define('Y', MalumItems.MALIGNANT_PEWTER_NUGGET.get()).pattern(" Y ").pattern("YXY").pattern(" Y ").unlockedBy("has_malignant_alloy", has(MalumItems.MALIGNANT_PEWTER_INGOT.get())).save(output);

        //NODES
        smeltingWithCount(Ingredient.of(MalumItems.IRON_NODE.get()), RecipeCategory.MISC, Items.IRON_NUGGET, 6, 0.25f, 200).unlockedBy("has_impetus", has(MalumItems.IRON_IMPETUS.get())).save(output, malumPath("iron_from_node_smelting"));
        blastingWithCount(Ingredient.of(MalumItems.IRON_NODE.get()), RecipeCategory.MISC, Items.IRON_NUGGET, 6, 0.25f, 100).unlockedBy("has_impetus", has(MalumItems.IRON_IMPETUS.get())).save(output, malumPath("iron_from_node_blasting"));

        smeltingWithCount(Ingredient.of(MalumItems.GOLD_NODE.get()), RecipeCategory.MISC, Items.GOLD_NUGGET, 6, 0.25f, 200).unlockedBy("has_impetus", has(MalumItems.GOLD_IMPETUS.get())).save(output, malumPath("gold_from_node_smelting"));
        blastingWithCount(Ingredient.of(MalumItems.GOLD_NODE.get()), RecipeCategory.MISC, Items.GOLD_NUGGET, 6, 0.25f, 100).unlockedBy("has_impetus", has(MalumItems.GOLD_IMPETUS.get())).save(output, malumPath("gold_from_node_blasting"));

        nodeSmelting(output, MalumItems.COPPER_NODE, NUGGETS_COPPER);
        nodeSmelting(output, MalumItems.LEAD_NODE, NUGGETS_LEAD);
        nodeSmelting(output, MalumItems.SILVER_NODE, NUGGETS_SILVER);
        nodeSmelting(output, MalumItems.ALUMINUM_NODE, NUGGETS_ALUMINUM);
        nodeSmelting(output, MalumItems.NICKEL_NODE, NUGGETS_NICKEL);
        nodeSmelting(output, MalumItems.URANIUM_NODE, NUGGETS_URANIUM);
        nodeSmelting(output, MalumItems.COBALT_NODE, NUGGETS_COBALT);
        nodeSmelting(output, MalumItems.OSMIUM_NODE, NUGGETS_OSMIUM);
        nodeSmelting(output, MalumItems.ZINC_NODE, NUGGETS_ZINC);
        nodeSmelting(output, MalumItems.TIN_NODE, NUGGETS_TIN);
        //TOOLS
        shaped(RecipeCategory.MISC, MalumItems.SOUL_STAINED_STEEL_HOE.get()).define('#', Tags.Items.RODS_WOODEN).define('X', MalumItems.SOUL_STAINED_STEEL_INGOT.get()).pattern("XX").pattern(" #").pattern(" #").unlockedBy("has_soul_stained_steel", has(MalumItems.SOUL_STAINED_STEEL_INGOT.get())).save(output);
        shaped(RecipeCategory.MISC, MalumItems.SOUL_STAINED_STEEL_PICKAXE.get()).define('#', Tags.Items.RODS_WOODEN).define('X', MalumItems.SOUL_STAINED_STEEL_INGOT.get()).pattern("XXX").pattern(" # ").pattern(" # ").unlockedBy("has_soul_stained_steel", has(MalumItems.SOUL_STAINED_STEEL_INGOT.get())).save(output);
        shaped(RecipeCategory.MISC, MalumItems.SOUL_STAINED_STEEL_AXE.get()).define('#', Tags.Items.RODS_WOODEN).define('X', MalumItems.SOUL_STAINED_STEEL_INGOT.get()).pattern("XX ").pattern("X# ").pattern(" # ").unlockedBy("has_soul_stained_steel", has(MalumItems.SOUL_STAINED_STEEL_INGOT.get())).save(output);
        shaped(RecipeCategory.MISC, MalumItems.SOUL_STAINED_STEEL_SHOVEL.get()).define('#', Tags.Items.RODS_WOODEN).define('X', MalumItems.SOUL_STAINED_STEEL_INGOT.get()).pattern("X").pattern("#").pattern("#").unlockedBy("has_soul_stained_steel", has(MalumItems.SOUL_STAINED_STEEL_INGOT.get())).save(output);
        shaped(RecipeCategory.MISC, MalumItems.SOUL_STAINED_STEEL_SWORD.get()).define('#', Tags.Items.RODS_WOODEN).define('X', MalumItems.SOUL_STAINED_STEEL_INGOT.get()).pattern("X").pattern("X").pattern("#").unlockedBy("has_soul_stained_steel", has(MalumItems.SOUL_STAINED_STEEL_INGOT.get())).save(output);

        //TRINKETS
        shaped(RecipeCategory.MISC, MalumItems.GILDED_BELT.get()).define('#', MalumItems.HALLOWED_GOLD_INGOT.get()).define('X', Tags.Items.LEATHERS).define('Y', MalumItems.REFINED_SOULSTONE.get()).pattern("XXX").pattern("#Y#").pattern(" # ").unlockedBy("has_hallowed_gold", has(MalumItems.HALLOWED_GOLD_INGOT.get())).save(output);
        shaped(RecipeCategory.MISC, MalumItems.GILDED_RING.get()).define('#', MalumItems.HALLOWED_GOLD_INGOT.get()).define('X', Tags.Items.LEATHERS).pattern("#X ").pattern("X X").pattern(" X ").unlockedBy("has_hallowed_gold", has(MalumItems.HALLOWED_GOLD_INGOT.get())).save(output);
        shaped(RecipeCategory.MISC, MalumItems.ORNATE_NECKLACE.get()).define('#', MalumItems.SOUL_STAINED_STEEL_INGOT.get()).define('X', Tags.Items.STRINGS).pattern(" X ").pattern("X X").pattern(" # ").unlockedBy("has_soul_stained_steel", has(MalumItems.SOUL_STAINED_STEEL_INGOT.get())).save(output);
        shaped(RecipeCategory.MISC, MalumItems.ORNATE_RING.get()).define('#', MalumItems.SOUL_STAINED_STEEL_INGOT.get()).define('X', Tags.Items.LEATHERS).pattern("#X ").pattern("X X").pattern(" X ").unlockedBy("has_soul_stained_steel", has(MalumItems.SOUL_STAINED_STEEL_INGOT.get())).save(output);

        shaped(RecipeCategory.MISC, MalumItems.RUNIC_BROOCH.get()).define('X', MalumItems.HALLOWED_GOLD_INGOT.get()).define('Y', MalumItems.BLOCK_OF_HALLOWED_GOLD.get()).define('Z', Tags.Items.LEATHERS).pattern(" Z ").pattern("ZXZ").pattern(" Y ").unlockedBy("has_hallowed_gold", has(MalumItems.HALLOWED_GOLD_INGOT.get())).save(output);
        shaped(RecipeCategory.MISC, MalumItems.ELABORATE_BROOCH.get()).define('X', MalumItems.SOUL_STAINED_STEEL_INGOT.get()).define('Y', MalumItems.BLOCK_OF_SOUL_STAINED_STEEL.get()).define('Z', Tags.Items.LEATHERS).pattern(" Z ").pattern("ZXZ").pattern(" Y ").unlockedBy("has_soul_stained_steel", has(MalumItems.SOUL_STAINED_STEEL_INGOT.get())).save(output);

        //FRAGMENTS
        shapeless(RecipeCategory.MISC, MalumItems.COAL_FRAGMENT.get(), 8).requires(Items.COAL).unlockedBy("has_coal", has(Items.COAL)).save(output, malumPath("coal_fragment"));
        shapeless(RecipeCategory.MISC, Items.COAL, 1).requires(MalumItems.COAL_FRAGMENT.get(), 8).unlockedBy("has_coal", has(Items.COAL)).save(output, malumPath("coal_from_fragment"));
        shapeless(RecipeCategory.MISC, MalumItems.CHARCOAL_FRAGMENT.get(), 8).requires(Items.CHARCOAL).unlockedBy("has_charcoal", has(Items.CHARCOAL)).save(output, malumPath("charcoal_fragment"));
        shapeless(RecipeCategory.MISC, Items.CHARCOAL, 1).requires(MalumItems.CHARCOAL_FRAGMENT.get(), 8).unlockedBy("has_charcoal", has(Items.CHARCOAL)).save(output, malumPath("charcoal_from_fragment"));
        shapeless(RecipeCategory.MISC, MalumItems.BLAZING_QUARTZ_FRAGMENT.get(), 8).requires(MalumItems.BLAZING_QUARTZ.get()).unlockedBy("has_blazing_quartz", has(MalumItems.BLAZING_QUARTZ.get())).save(output, malumPath("blazing_quartz_fragment"));
        shapeless(RecipeCategory.MISC, MalumItems.BLAZING_QUARTZ.get(), 1).requires(MalumItems.BLAZING_QUARTZ_FRAGMENT.get(), 8).unlockedBy("has_blazing_quartz", has(MalumItems.BLAZING_QUARTZ.get())).save(output, malumPath("blazing_quartz_from_fragment"));
        shapeless(RecipeCategory.MISC, MalumItems.ARCANE_CHARCOAL_FRAGMENT.get(), 8).requires(MalumItems.ARCANE_CHARCOAL.get()).unlockedBy("has_arcane_charcoal", has(MalumItems.ARCANE_CHARCOAL.get())).save(output, malumPath("arcane_charcoal_fragment"));
        shapeless(RecipeCategory.MISC, MalumItems.ARCANE_CHARCOAL.get(), 1).requires(MalumItems.ARCANE_CHARCOAL_FRAGMENT.get(), 8).unlockedBy("has_arcane_charcoal", has(MalumItems.ARCANE_CHARCOAL.get())).save(output, malumPath("arcane_charcoal_from_fragment"));

        //ORE SMELTING
        smelting(Ingredient.of(MalumItems.BLAZING_QUARTZ_ORE.get()), RecipeCategory.MISC, MalumItems.BLAZING_QUARTZ.get(), 0.25f, 200).unlockedBy("has_blazing_quartz", has(MalumItems.BLAZING_QUARTZ.get())).save(output, malumPath("blazing_quartz_from_smelting"));
        blasting(Ingredient.of(MalumItems.BLAZING_QUARTZ_ORE.get()), RecipeCategory.MISC, MalumItems.BLAZING_QUARTZ.get(), 0.25f, 100).unlockedBy("has_blazing_quartz", has(MalumItems.BLAZING_QUARTZ.get())).save(output, malumPath("blazing_quartz_from_blasting"));
        smelting(Ingredient.of(MalumItems.NATURAL_QUARTZ_ORE.get()), RecipeCategory.MISC, MalumItems.NATURAL_QUARTZ.get(), 0.25f, 200).unlockedBy("has_natural_quartz", has(MalumItems.NATURAL_QUARTZ.get())).save(output, malumPath("natural_quartz_from_smelting"));
        blasting(Ingredient.of(MalumItems.NATURAL_QUARTZ_ORE.get()), RecipeCategory.MISC, MalumItems.NATURAL_QUARTZ.get(), 0.25f, 100).unlockedBy("has_natural_quartz", has(MalumItems.NATURAL_QUARTZ.get())).save(output, malumPath("natural_quartz_from_blasting"));
        smelting(Ingredient.of(MalumItems.DEEPSLATE_QUARTZ_ORE.get()), RecipeCategory.MISC, MalumItems.NATURAL_QUARTZ.get(), 0.25f, 200).unlockedBy("has_natural_quartz", has(MalumItems.NATURAL_QUARTZ.get())).save(output, malumPath("natural_quartz_from_deepslate_smelting"));
        blasting(Ingredient.of(MalumItems.DEEPSLATE_QUARTZ_ORE.get()), RecipeCategory.MISC, MalumItems.NATURAL_QUARTZ.get(), 0.25f, 100).unlockedBy("has_natural_quartz", has(MalumItems.NATURAL_QUARTZ.get())).save(output, malumPath("natural_quartz_from_deepslate_blasting"));
        smeltingWithCount(Ingredient.of(MalumItems.BRILLIANT_STONE.get()), RecipeCategory.MISC, MalumItems.REFINED_BRILLIANCE.get(), 2, 1, 200).unlockedBy("has_brilliance", has(MalumItems.RAW_BRILLIANCE.get())).save(output, malumPath("brilliance_from_smelting"));
        blastingWithCount(Ingredient.of(MalumItems.BRILLIANT_STONE.get()), RecipeCategory.MISC, MalumItems.REFINED_BRILLIANCE.get(), 2, 1, 100).unlockedBy("has_brilliance", has(MalumItems.RAW_BRILLIANCE.get())).save(output, malumPath("brilliance_from_blasting"));
        smeltingWithCount(Ingredient.of(MalumItems.BRILLIANT_DEEPSLATE.get()), RecipeCategory.MISC, MalumItems.REFINED_BRILLIANCE.get(), 2, 1, 200).unlockedBy("has_brilliance", has(MalumItems.RAW_BRILLIANCE.get())).save(output, malumPath("brilliance_from_deepslate_smelting"));
        blastingWithCount(Ingredient.of(MalumItems.BRILLIANT_DEEPSLATE.get()), RecipeCategory.MISC, MalumItems.REFINED_BRILLIANCE.get(), 2, 1, 100).unlockedBy("has_brilliance", has(MalumItems.RAW_BRILLIANCE.get())).save(output, malumPath("brilliance_from_deepslate_blasting"));
        smeltingWithCount(Ingredient.of(MalumItems.SOULSTONE_ORE.get()), RecipeCategory.MISC, MalumItems.REFINED_SOULSTONE.get(), 2, 0.25f, 200).unlockedBy("has_soulstone", has(MalumItems.RAW_SOULSTONE.get())).save(output, malumPath("soulstone_from_smelting"));
        blastingWithCount(Ingredient.of(MalumItems.SOULSTONE_ORE.get()), RecipeCategory.MISC, MalumItems.REFINED_SOULSTONE.get(), 2, 0.25f, 100).unlockedBy("has_soulstone", has(MalumItems.RAW_SOULSTONE.get())).save(output, malumPath("soulstone_from_blasting"));
        smeltingWithCount(Ingredient.of(MalumItems.DEEPSLATE_SOULSTONE_ORE.get()), RecipeCategory.MISC, MalumItems.REFINED_SOULSTONE.get(), 2, 0.25f, 200).unlockedBy("has_soulstone", has(MalumItems.RAW_SOULSTONE.get())).save(output, malumPath("soulstone_from_deepslate_smelting"));
        blastingWithCount(Ingredient.of(MalumItems.DEEPSLATE_SOULSTONE_ORE.get()), RecipeCategory.MISC, MalumItems.REFINED_SOULSTONE.get(), 2, 0.25f, 100).unlockedBy("has_soulstone", has(MalumItems.RAW_SOULSTONE.get())).save(output, malumPath("soulstone_from_deepslate_blasting"));

        smeltingWithCount(Ingredient.of(MalumItems.RAW_BRILLIANCE.get()), RecipeCategory.MISC, MalumItems.REFINED_BRILLIANCE.get(), 2, 1, 200).unlockedBy("has_brilliance", has(MalumItems.RAW_BRILLIANCE.get())).save(output, malumPath("brilliance_from_raw_smelting"));
        blastingWithCount(Ingredient.of(MalumItems.RAW_BRILLIANCE.get()), RecipeCategory.MISC, MalumItems.REFINED_BRILLIANCE.get(), 2, 1, 100).unlockedBy("has_brilliance", has(MalumItems.RAW_BRILLIANCE.get())).save(output, malumPath("brilliance_from_raw_blasting"));
        smeltingWithCount(Ingredient.of(MalumItems.CRUSHED_BRILLIANCE.get()), RecipeCategory.MISC, MalumItems.REFINED_BRILLIANCE.get(), 2, 1, 200).unlockedBy("has_brilliance", has(MalumItems.RAW_BRILLIANCE.get())).save(output, malumPath("brilliance_from_crushed_smelting"));
        blastingWithCount(Ingredient.of(MalumItems.CRUSHED_BRILLIANCE.get()), RecipeCategory.MISC, MalumItems.REFINED_BRILLIANCE.get(), 2, 1, 100).unlockedBy("has_brilliance", has(MalumItems.RAW_BRILLIANCE.get())).save(output, malumPath("brilliance_from_crushed_blasting"));
        smeltingWithCount(Ingredient.of(MalumItems.RAW_SOULSTONE.get()), RecipeCategory.MISC, MalumItems.REFINED_SOULSTONE.get(), 2, 0.25f, 200).unlockedBy("has_soulstone", has(MalumItems.RAW_SOULSTONE.get())).save(output, malumPath("soulstone_from_raw_smelting"));
        blastingWithCount(Ingredient.of(MalumItems.RAW_SOULSTONE.get()), RecipeCategory.MISC, MalumItems.REFINED_SOULSTONE.get(), 2, 0.25f, 100).unlockedBy("has_soulstone", has(MalumItems.RAW_SOULSTONE.get())).save(output, malumPath("soulstone_from_raw_blasting"));
        smeltingWithCount(Ingredient.of(MalumItems.CRUSHED_SOULSTONE.get()), RecipeCategory.MISC, MalumItems.REFINED_SOULSTONE.get(), 2, 0.25f, 200).unlockedBy("has_soulstone", has(MalumItems.RAW_SOULSTONE.get())).save(output, malumPath("soulstone_from_crushed_smelting"));
        blastingWithCount(Ingredient.of(MalumItems.CRUSHED_SOULSTONE.get()), RecipeCategory.MISC, MalumItems.REFINED_SOULSTONE.get(), 2, 0.25f, 100).unlockedBy("has_soulstone", has(MalumItems.RAW_SOULSTONE.get())).save(output, malumPath("soulstone_from_crushed_blasting"));

        //RAW ORE BLOCKS
        shaped(RecipeCategory.MISC, MalumItems.BLOCK_OF_RAW_SOULSTONE.get()).define('#', MalumItems.RAW_SOULSTONE.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_soulstone", has(MalumItems.RAW_SOULSTONE.get())).save(output, malumPath("raw_soulstone_block"));
        shapeless(RecipeCategory.MISC, MalumItems.RAW_SOULSTONE.get(), 9).requires(MalumItems.BLOCK_OF_RAW_SOULSTONE.get()).unlockedBy("has_soulstone", has(MalumItems.RAW_SOULSTONE.get())).save(output, malumPath("raw_soulstone_from_block"));

        //ORE BLOCKS
        shaped(RecipeCategory.MISC, MalumItems.BLOCK_OF_BLAZING_QUARTZ.get()).define('#', MalumItems.BLAZING_QUARTZ.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_blazing_quartz", has(MalumItems.BLAZING_QUARTZ.get())).save(output, malumPath("block_of_blazing_quartz"));
        shapeless(RecipeCategory.MISC, MalumItems.BLAZING_QUARTZ.get(), 9).requires(MalumItems.BLOCK_OF_BLAZING_QUARTZ.get()).unlockedBy("has_blazing_quartz", has(MalumItems.BLAZING_QUARTZ.get())).save(output, malumPath("blazing_quartz_from_block"));
        shaped(RecipeCategory.MISC, MalumItems.BLOCK_OF_ARCANE_CHARCOAL.get()).define('#', MalumItems.ARCANE_CHARCOAL.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_arcane_charcoal", has(MalumItems.ARCANE_CHARCOAL.get())).save(output, malumPath("block_of_arcane_charcoal"));
        shapeless(RecipeCategory.MISC, MalumItems.ARCANE_CHARCOAL.get(), 9).requires(MalumItems.BLOCK_OF_ARCANE_CHARCOAL.get()).unlockedBy("has_arcane_charcoal", has(MalumItems.ARCANE_CHARCOAL.get())).save(output, malumPath("arcane_charcoal_from_block"));
        shaped(RecipeCategory.MISC, MalumItems.BLOCK_OF_BRILLIANCE.get()).define('#', MalumItems.RAW_BRILLIANCE.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_brilliance", has(MalumItems.RAW_BRILLIANCE.get())).save(output, malumPath("block_of_brilliance"));
        shapeless(RecipeCategory.MISC, MalumItems.RAW_BRILLIANCE.get(), 9).requires(MalumItems.BLOCK_OF_BRILLIANCE.get()).unlockedBy("has_brilliance", has(MalumItems.RAW_BRILLIANCE.get())).save(output, malumPath("brilliance_from_block"));
        shaped(RecipeCategory.MISC, MalumItems.BLOCK_OF_SOULSTONE.get()).define('#', MalumItems.REFINED_SOULSTONE.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_soulstone", has(MalumItems.RAW_SOULSTONE.get())).save(output, malumPath("block_of_soulstone"));
        shapeless(RecipeCategory.MISC, MalumItems.REFINED_SOULSTONE.get(), 9).requires(MalumItems.BLOCK_OF_SOULSTONE.get()).unlockedBy("has_soulstone", has(MalumItems.RAW_SOULSTONE.get())).save(output, malumPath("soulstone_from_block"));

        shaped(RecipeCategory.MISC, MalumItems.BLOCK_OF_CTHONIC_GOLD.get()).define('#', MalumItems.CTHONIC_GOLD.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_cthonic_gold", has(MalumItems.CTHONIC_GOLD.get())).save(output, malumPath("block_of_cthonic_gold"));
        shapeless(RecipeCategory.MISC, MalumItems.CTHONIC_GOLD.get(), 9).requires(MalumItems.BLOCK_OF_CTHONIC_GOLD.get()).unlockedBy("has_cthonic_gold", has(MalumItems.CTHONIC_GOLD.get())).save(output, malumPath("cthonic_gold_from_block"));

        shapeless(RecipeCategory.MISC, MalumItems.CTHONIC_GOLD.get()).requires(MalumItems.CTHONIC_GOLD_FRAGMENT.get(), 8).unlockedBy("has_cthonic_gold", has(MalumItems.CTHONIC_GOLD.get())).save(output, malumPath("cthonic_gold_from_fragment"));
        shapeless(RecipeCategory.MISC, MalumItems.CTHONIC_GOLD_FRAGMENT.get(), 8).requires(MalumItems.CTHONIC_GOLD.get()).unlockedBy("has_cthonic_gold", has(MalumItems.CTHONIC_GOLD.get())).save(output, malumPath("cthonic_gold_fragment"));

        //COMPACT BLOCKS
        shaped(RecipeCategory.MISC, MalumItems.BLOCK_OF_ROTTING_ESSENCE.get()).define('#', MalumItems.ROTTING_ESSENCE.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_rotting_essence", has(MalumItems.ROTTING_ESSENCE.get())).save(output, malumPath("block_of_rotting_essence"));
        shapeless(RecipeCategory.MISC, MalumItems.ROTTING_ESSENCE.get(), 9).requires(MalumItems.BLOCK_OF_ROTTING_ESSENCE.get()).unlockedBy("has_rotting_essence", has(MalumItems.ROTTING_ESSENCE.get())).save(output, malumPath("rotting_essence_from_block"));
        shaped(RecipeCategory.MISC, MalumItems.BLOCK_OF_GRIM_TALC.get()).define('#', MalumItems.GRIM_TALC.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_grim_talc", has(MalumItems.GRIM_TALC.get())).save(output, malumPath("block_of_grim_talc"));
        shapeless(RecipeCategory.MISC, MalumItems.GRIM_TALC.get(), 9).requires(MalumItems.BLOCK_OF_GRIM_TALC.get()).unlockedBy("has_grim_talc", has(MalumItems.GRIM_TALC.get())).save(output, malumPath("grim_talc_from_block"));
        shaped(RecipeCategory.MISC, MalumItems.BLOCK_OF_ALCHEMICAL_CALX.get()).define('#', MalumItems.ALCHEMICAL_CALX.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_alchemical_calx", has(MalumItems.ALCHEMICAL_CALX.get())).save(output, malumPath("block_of_alchemical_calx"));
        shapeless(RecipeCategory.MISC, MalumItems.ALCHEMICAL_CALX.get(), 9).requires(MalumItems.BLOCK_OF_ALCHEMICAL_CALX.get()).unlockedBy("has_alchemical_calx", has(MalumItems.ALCHEMICAL_CALX.get())).save(output, malumPath("alchemical_calx_from_block"));
        shaped(RecipeCategory.MISC, MalumItems.BLOCK_OF_ASTRAL_WEAVE.get()).define('#', MalumItems.ASTRAL_WEAVE.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_astral_weave", has(MalumItems.ASTRAL_WEAVE.get())).save(output, malumPath("block_of_astral_weave"));
        shapeless(RecipeCategory.MISC, MalumItems.ASTRAL_WEAVE.get(), 9).requires(MalumItems.BLOCK_OF_ASTRAL_WEAVE.get()).unlockedBy("has_astral_weave", has(MalumItems.ASTRAL_WEAVE.get())).save(output, malumPath("astral_weave_from_block"));
        shaped(RecipeCategory.MISC, MalumItems.BLOCK_OF_HEX_ASH.get()).define('#', MalumItems.HEX_ASH.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_hex_ash", has(MalumItems.HEX_ASH.get())).save(output, malumPath("block_of_hex_ash"));
        shapeless(RecipeCategory.MISC, MalumItems.HEX_ASH.get(), 9).requires(MalumItems.BLOCK_OF_HEX_ASH.get()).unlockedBy("has_hex_ash", has(MalumItems.HEX_ASH.get())).save(output, malumPath("hex_ash_from_block"));
        shaped(RecipeCategory.MISC, MalumItems.BLOCK_OF_LIVING_FLESH.get()).define('#', MalumItems.LIVING_FLESH.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_living_flesh", has(MalumItems.LIVING_FLESH.get())).save(output, malumPath("block_of_living_flesh"));
        shapeless(RecipeCategory.MISC, MalumItems.LIVING_FLESH.get(), 9).requires(MalumItems.BLOCK_OF_LIVING_FLESH.get()).unlockedBy("has_living_flesh", has(MalumItems.LIVING_FLESH.get())).save(output, malumPath("living_flesh_from_block"));
        shaped(RecipeCategory.MISC, MalumItems.MASS_OF_BLIGHTED_GUNK.get()).define('#', MalumItems.BLIGHTED_GUNK.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_blighted_gunk", has(MalumItems.BLIGHTED_GUNK.get())).save(output);
        shapeless(RecipeCategory.MISC, MalumItems.BLIGHTED_GUNK.get(), 9).requires(MalumItems.MASS_OF_BLIGHTED_GUNK.get()).unlockedBy("has_blighted_gunk", has(MalumItems.BLIGHTED_GUNK.get())).save(output, malumPath("blighted_gunk_from_mass"));
        shaped(RecipeCategory.MISC, MalumItems.BLOCK_OF_NULL_SLATE.get()).define('#', MalumItems.NULL_SLATE.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_null_slate", has(MalumItems.NULL_SLATE.get())).save(output);
        shapeless(RecipeCategory.MISC, MalumItems.NULL_SLATE.get(), 9).requires(MalumItems.BLOCK_OF_NULL_SLATE.get()).unlockedBy("has_null_slate", has(MalumItems.NULL_SLATE.get())).save(output, malumPath("null_slate_from_block"));
        shaped(RecipeCategory.MISC, MalumItems.BLOCK_OF_VOID_SALTS.get()).define('#', MalumItems.VOID_SALTS.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_void_salts", has(MalumItems.VOID_SALTS.get())).save(output);
        shapeless(RecipeCategory.MISC, MalumItems.VOID_SALTS.get(), 9).requires(MalumItems.BLOCK_OF_VOID_SALTS.get()).unlockedBy("has_void_salts", has(MalumItems.VOID_SALTS.get())).save(output, malumPath("void_salts_from_block"));
        shaped(RecipeCategory.MISC, MalumItems.BLOCK_OF_MNEMONIC_FRAGMENT.get()).define('#', MalumItems.MNEMONIC_FRAGMENT.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_mnemonic_fragment", has(MalumItems.MNEMONIC_FRAGMENT.get())).save(output);
        shapeless(RecipeCategory.MISC, MalumItems.MNEMONIC_FRAGMENT.get(), 9).requires(MalumItems.BLOCK_OF_MNEMONIC_FRAGMENT.get()).unlockedBy("has_mnemonic_fragment", has(MalumItems.MNEMONIC_FRAGMENT.get())).save(output, malumPath("mnemonic_fragment_from_block"));
        shaped(RecipeCategory.MISC, MalumItems.BLOCK_OF_MALIGNANT_LEAD.get()).define('#', MalumItems.MALIGNANT_LEAD.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_malignant_lead", has(MalumItems.MALIGNANT_LEAD.get())).save(output);
        shapeless(RecipeCategory.MISC, MalumItems.MALIGNANT_LEAD.get(), 9).requires(MalumItems.BLOCK_OF_MALIGNANT_LEAD.get()).unlockedBy("has_malignant_lead", has(MalumItems.MALIGNANT_LEAD.get())).save(output, malumPath("malignant_lead_from_block"));
        shaped(RecipeCategory.MISC, MalumItems.BLOCK_OF_AURIC_EMBERS.get()).define('#', MalumItems.AURIC_EMBERS.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_auric_embers", has(MalumItems.AURIC_EMBERS.get())).save(output);
        shapeless(RecipeCategory.MISC, MalumItems.AURIC_EMBERS.get(), 9).requires(MalumItems.BLOCK_OF_AURIC_EMBERS.get()).unlockedBy("has_auric_embers", has(MalumItems.AURIC_EMBERS.get())).save(output, malumPath("auric_embers_from_block"));

        //MISC
        shaped(RecipeCategory.MISC, Items.NETHERRACK, 2).define('Z', MalumItems.BLAZING_QUARTZ.get()).define('Y', Tags.Items.COBBLESTONES).pattern("ZY").pattern("YZ").unlockedBy("has_blazing_quartz", has(MalumItems.BLAZING_QUARTZ.get())).save(output, malumPath("netherrack_from_blazing_quartz"));
        shapeless(RecipeCategory.MISC, Items.EXPERIENCE_BOTTLE).requires(MalumItems.REFINED_BRILLIANCE.get()).requires(Items.GLASS_BOTTLE).unlockedBy("has_brilliance", has(MalumItems.REFINED_BRILLIANCE.get())).save(output, malumPath("experience_bottle_from_brilliance"));

        shapeless(RecipeCategory.MISC, Items.BONE_MEAL, 6).requires(MalumItems.GRIM_TALC.get()).unlockedBy("has_grim_talc", has(MalumItems.GRIM_TALC.get())).save(output, malumPath("bonemeal_from_grim_talc"));
        shaped(RecipeCategory.MISC, Items.SKELETON_SKULL).define('#', MalumItems.GRIM_TALC.get()).define('&', Tags.Items.BONES).pattern("&&&").pattern("&#&").pattern("&&&").unlockedBy("has_grim_talc", has(MalumItems.GRIM_TALC.get())).save(output, malumPath("skeleton_skull_from_grim_talc"));
        shaped(RecipeCategory.MISC, Items.ZOMBIE_HEAD).define('#', MalumItems.GRIM_TALC.get()).define('&', Items.ROTTEN_FLESH).pattern("&&&").pattern("&#&").pattern("&&&").unlockedBy("has_grim_talc", has(MalumItems.GRIM_TALC.get())).save(output, malumPath("zombie_head_from_grim_talc"));

        shaped(RecipeCategory.MISC, Items.TORCH, 6).define('#', MalumItems.BLAZING_QUARTZ.get()).define('&', Items.STICK).pattern("#").pattern("&").unlockedBy("has_blazing_quartz", has(MalumItems.BLAZING_QUARTZ.get())).save(output, malumPath("torch_from_blazing_quartz"));

        //SAP & ARCANE CHARCOAL
        smelting(Ingredient.of(MalumTags.ItemTags.RUNEWOOD_LOGS), RecipeCategory.MISC, MalumItems.ARCANE_CHARCOAL.get(), 0.25f, 200).unlockedBy("has_runewood_planks", has(MalumTags.ItemTags.RUNEWOOD_LOGS)).save(output, malumPath("arcane_charcoal_from_runewood"));
        shapeless(RecipeCategory.MISC, MalumItems.RUNIC_SAPBALL.get()).requires(MalumItems.RUNIC_SAP.get()).unlockedBy("has_runic_sap", has(MalumItems.RUNIC_SAP.get())).save(output);

        smelting(Ingredient.of(MalumTags.ItemTags.SOULWOOD_LOGS), RecipeCategory.MISC, MalumItems.ARCANE_CHARCOAL.get(), 0.25f, 200).unlockedBy("has_soulwood_planks", has(MalumTags.ItemTags.SOULWOOD_LOGS)).save(output, malumPath("arcane_charcoal_from_soulwood"));
        shapeless(RecipeCategory.MISC, MalumItems.CURSED_SAPBALL.get()).requires(MalumItems.CURSED_SAP.get()).unlockedBy("has_cursed_sap", has(MalumItems.CURSED_SAP.get())).save(output);

        //THE DEVICE
        shaped(RecipeCategory.MISC, MalumItems.THE_DEVICE.get()).define('X', MalumItems.TWISTED_ROCK.get()).define('Y', MalumItems.TAINTED_ROCK.get()).pattern("XYX").pattern("YXY").pattern("XYX").unlockedBy("has_bedrock", has(Items.BEDROCK)).save(output);


        //WEAVES
        weaveRecipe(output, MalumItems.BLIGHTED_GUNK.get(), MalumItems.ANCIENT_WEAVE);
        weaveRecipe(output, Items.IRON_INGOT, MalumItems.CORNERED_WEAVE);
        weaveRecipe(output, Items.LAPIS_LAZULI, MalumItems.MECHANICAL_WEAVE_V1);
        weaveRecipe(output, Items.REDSTONE, MalumItems.MECHANICAL_WEAVE_V2);

        weaveRecipe(output, Items.BREAD, MalumItems.ACE_PRIDEWEAVE);
        weaveRecipe(output, Items.BOOK, MalumItems.AGENDER_PRIDEWEAVE);
        weaveRecipe(output, Items.ARROW, MalumItems.ARO_PRIDEWEAVE);
        weaveRecipe(output, Items.WHEAT_SEEDS, MalumItems.AROACE_PRIDEWEAVE);
        weaveRecipe(output, Items.WHEAT, MalumItems.BI_PRIDEWEAVE);
        weaveRecipe(output, Items.RAW_IRON, MalumItems.DEMIBOY_PRIDEWEAVE);
        weaveRecipe(output, Items.RAW_COPPER, MalumItems.DEMIGIRL_PRIDEWEAVE);
        weaveRecipe(output, Items.MOSS_BLOCK, MalumItems.ENBY_PRIDEWEAVE);
        weaveRecipe(output, Items.MELON_SLICE, MalumItems.GAY_PRIDEWEAVE);
        weaveRecipe(output, Items.WATER_BUCKET, MalumItems.GENDERFLUID_PRIDEWEAVE);
        weaveRecipe(output, Items.GLASS_BOTTLE, MalumItems.GENDERQUEER_PRIDEWEAVE);
        weaveRecipe(output, Items.AZALEA, MalumItems.INTERSEX_PRIDEWEAVE);
        weaveRecipe(output, Items.HONEYCOMB, MalumItems.LESBIAN_PRIDEWEAVE);
        weaveRecipe(output, Items.CARROT, MalumItems.PAN_PRIDEWEAVE);
        weaveRecipe(output, Items.REPEATER, MalumItems.PLURAL_PRIDEWEAVE);
        weaveRecipe(output, Items.COMPARATOR, MalumItems.POLY_PRIDEWEAVE);
        weaveRecipe(output, Items.STONE_BRICK_WALL, MalumItems.PRIDE_PRIDEWEAVE);
        weaveRecipe(output, Items.EGG, MalumItems.TRANS_PRIDEWEAVE);
    }

    private static RecipeBuilder smeltingWithCount(Ingredient ingredient, RecipeCategory category, net.minecraft.world.item.Item resultItem, int resultCount, float experience, int time) {
        return smelting(ingredient, category, new ItemStack(resultItem, resultCount), experience, time);
    }

    private static RecipeBuilder blastingWithCount(Ingredient ingredient, RecipeCategory category, net.minecraft.world.item.Item resultItem, int resultCount, float experience, int time) {
        return blasting(ingredient, category, new ItemStack(resultItem, resultCount), experience, time);
    }

    private static void bannerRecipe(RecipeOutput consumer, net.minecraft.world.item.Item material, SoulwovenBannerPatternDataComponent pattern) {
        shapeless(RecipeCategory.BUILDING_BLOCKS, pattern.getDefaultStack()).requires(MalumItems.SOULWOVEN_BANNER.get()).requires(material).unlockedBy("has_soulwoven_silk", has(MalumItems.SOULWOVEN_SILK.get())).save(consumer, pattern.getRecipeId());
    }

    private static void weaveRecipe(RecipeOutput consumer, net.minecraft.world.item.Item sideItem, Supplier<? extends net.minecraft.world.item.Item> output) {
        shapeless(RecipeCategory.MISC, output.get()).requires(MalumItems.ESOTERIC_SPOOL.get()).requires(sideItem).unlockedBy("has_spool", has(MalumItems.ESOTERIC_SPOOL.get())).save(consumer);
    }

    private static void nodeSmelting(RecipeOutput recipeoutput, Holder<net.minecraft.world.item.Item> node, TagKey<net.minecraft.world.item.Item> tag) {
        String name = BuiltInRegistries.ITEM.getKey(node.value()).getPath().replaceFirst("_node", "");

        var input = Ingredient.of(node.value());
        var output = Ingredient.of(tag);
        var unlockCondition = has(MalumItems.SPIRIT_CRUCIBLE.get());
        var conditionOutput = recipeoutput.withConditions(new NotCondition(new TagEmptyCondition(tag.location().toString())));
        MetalNodeCookingRecipeBuilder.smelting(input, RecipeCategory.MISC, output, 6, 0.25f, 200)
                .unlockedBy("has_crucible", unlockCondition)
                .save(conditionOutput, MalumMod.malumPath(name + "_from_node_smelting"));

        MetalNodeCookingRecipeBuilder.blasting(input, RecipeCategory.MISC, output, 6, 0.25f, 100)
                .unlockedBy("has_crucible", unlockCondition)
                .save(conditionOutput, MalumMod.malumPath(name + "_from_node_blasting"));
    }

    private static void etherBrazier(RecipeOutput recipeoutput, ItemLike output, ItemLike rock, ItemLike ether) {
        new NBTCarryRecipeBuilder(RecipeCategory.BUILDING_BLOCKS, new ItemStack(output.asItem(), 2), Ingredient.of(ether))
                .define('#', rock)
                .define('S', Ingredient.of(Tags.Items.RODS_WOODEN))
                .define('X', ether)
                .pattern("#X#").pattern("S#S")
                .unlockedBy("has_ether", has(MalumItems.ETHER.get()))
                .save(recipeoutput, BuiltInRegistries.ITEM.getKey(output.asItem()).getPath());
    }

    private static void etherTorch(RecipeOutput recipeoutput, ItemLike output, ItemLike ether) {
        new NBTCarryRecipeBuilder(RecipeCategory.BUILDING_BLOCKS, new ItemStack(output.asItem(), 2), Ingredient.of(ether))
                .define('S', Ingredient.of(Tags.Items.RODS_WOODEN))
                .define('X', ether)
                .pattern("X").pattern("S")
                .unlockedBy("has_ether", has(MalumItems.ETHER.get()))
                .save(recipeoutput, BuiltInRegistries.ITEM.getKey(output.asItem()).getPath() + "_from_stick");
    }

    public static Criterion<InventoryChangeTrigger.TriggerInstance> has(MinMaxBounds.Ints count, ItemLike item) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(item).withCount(count));
    }

    public static Criterion<InventoryChangeTrigger.TriggerInstance> has(ItemLike itemLike) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(itemLike));
    }

    public static Criterion<InventoryChangeTrigger.TriggerInstance> has(TagKey<net.minecraft.world.item.Item> tag) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(tag));
    }

    public static Criterion<InventoryChangeTrigger.TriggerInstance> inventoryTrigger(ItemPredicate.Builder... items) {
        return inventoryTrigger(Arrays.stream(items).map(ItemPredicate.Builder::build).toArray(ItemPredicate[]::new));
    }

    public static Criterion<InventoryChangeTrigger.TriggerInstance> inventoryTrigger(ItemPredicate... predicates) {
        return CriteriaTriggers.INVENTORY_CHANGED
                .createCriterion(new InventoryChangeTrigger.TriggerInstance(Optional.empty(), InventoryChangeTrigger.TriggerInstance.Slots.ANY, List.of(predicates)));
    }
}
