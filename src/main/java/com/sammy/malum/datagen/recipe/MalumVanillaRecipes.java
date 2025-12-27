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
        etherTorch(output, MalumItems.IRIDESCENT_ETHER_TORCH.get(), MalumItems.IRIDESCENT_ETHER.get());

        etherCandle(output, MalumItems.ETHER_CANDLE.get(), MalumItems.ETHER.get());
        etherCandle(output, MalumItems.IRIDESCENT_ETHER_CANDLE.get(), MalumItems.IRIDESCENT_ETHER.get());

        etherBrazier(output, MalumItems.TAINTED_ETHER_BRAZIER.get(), MalumItems.TAINTED_ROCK.get(), MalumItems.ETHER.get());
        etherBrazier(output, MalumItems.TWISTED_ETHER_BRAZIER.get(), MalumItems.TWISTED_ROCK.get(), MalumItems.ETHER.get());
        etherBrazier(output, MalumItems.DROSS_ETHER_BRAZIER.get(), MalumItems.DROSS_STONE.get(), MalumItems.ETHER.get());

        etherBrazier(output, MalumItems.TAINTED_IRIDESCENT_ETHER_BRAZIER.get(), MalumItems.TAINTED_ROCK.get(), MalumItems.IRIDESCENT_ETHER.get());
        etherBrazier(output, MalumItems.TWISTED_IRIDESCENT_ETHER_BRAZIER.get(), MalumItems.TWISTED_ROCK.get(), MalumItems.IRIDESCENT_ETHER.get());
        etherBrazier(output, MalumItems.DROSS_IRIDESCENT_ETHER_BRAZIER.get(), MalumItems.DROSS_STONE.get(), MalumItems.IRIDESCENT_ETHER.get());

        etherCresset(output, MalumItems.TAINTED_ETHER_CRESSET.get(), MalumItems.TAINTED_ROCK.get(), MalumItems.ETHER.get());
        etherCresset(output, MalumItems.TWISTED_ETHER_CRESSET.get(), MalumItems.TWISTED_ROCK.get(), MalumItems.ETHER.get());
        etherCresset(output, MalumItems.DROSS_ETHER_CRESSET.get(), MalumItems.DROSS_STONE.get(), MalumItems.ETHER.get());

        etherCresset(output, MalumItems.TAINTED_IRIDESCENT_ETHER_CRESSET.get(), MalumItems.TAINTED_ROCK.get(), MalumItems.IRIDESCENT_ETHER.get());
        etherCresset(output, MalumItems.TWISTED_IRIDESCENT_ETHER_CRESSET.get(), MalumItems.TWISTED_ROCK.get(), MalumItems.IRIDESCENT_ETHER.get());
        etherCresset(output, MalumItems.DROSS_IRIDESCENT_ETHER_CRESSET.get(), MalumItems.DROSS_STONE.get(), MalumItems.IRIDESCENT_ETHER.get());

        //BANNERS
        shaped(RecipeCategory.BUILDING_BLOCKS, MalumItems.SOULWOVEN_BANNER.get()).define('X', MalumTags.ItemTags.RUNEWOOD_PLANKS).define('Y', MalumItems.SOULWOVEN_SILK.get()).pattern("X").pattern("Y").pattern("Y").unlockedBy("has_soulwoven_silk", has(MalumItems.SOULWOVEN_SILK.get())).save(output);
        bannerRecipe(output, MalumItems.ROTTING_ESSENCE.get(), SoulwovenBannerPatternDataComponent.HUNGER);
        bannerRecipe(output, MalumItems.GRIM_TALC.get(), SoulwovenBannerPatternDataComponent.HORNS);
        bannerRecipe(output, MalumItems.EERIE_WEAVE.get(), SoulwovenBannerPatternDataComponent.HEFT);
        bannerRecipe(output, MalumItems.WARP_FLUX.get(), SoulwovenBannerPatternDataComponent.HALLUCINATION);

        //SPIRIT METALS
        blockIngotExchange(output, MalumItems.SOUL_STAINED_STEEL_INGOT, MalumItems.BLOCK_OF_SOUL_STAINED_STEEL);
        ingotNuggetExchange(output, MalumItems.SOUL_STAINED_STEEL_NUGGET, MalumItems.SOUL_STAINED_STEEL_INGOT);
        plating(output, MalumItems.SOUL_STAINED_STEEL_NUGGET, MalumItems.SOUL_STAINED_STEEL_INGOT, MalumItems.SOUL_STAINED_STEEL_PLATING);

        blockIngotExchange(output, MalumItems.HALLOWED_GOLD_INGOT, MalumItems.BLOCK_OF_HALLOWED_GOLD);
        ingotNuggetExchange(output, MalumItems.HALLOWED_GOLD_NUGGET, MalumItems.HALLOWED_GOLD_INGOT);
        plating(output, MalumItems.HALLOWED_GOLD_NUGGET, MalumItems.HALLOWED_GOLD_INGOT, MalumItems.HALLOWED_GOLD_INLAY);

        blockIngotExchange(output, MalumItems.MALIGNANT_PEWTER_INGOT, MalumItems.BLOCK_OF_MALIGNANT_PEWTER);
        ingotNuggetExchange(output, MalumItems.MALIGNANT_PEWTER_NUGGET, MalumItems.MALIGNANT_PEWTER_INGOT);
        plating(output, MalumItems.MALIGNANT_PEWTER_NUGGET, MalumItems.MALIGNANT_PEWTER_INGOT, MalumItems.MALIGNANT_PEWTER_PLATING);

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

        //FULL BLOCKS
        blockIngotExchange(output, MalumItems.RAW_SOULSTONE, MalumItems.BLOCK_OF_RAW_SOULSTONE);
        blockIngotExchange(output, MalumItems.REFINED_SOULSTONE, MalumItems.BLOCK_OF_SOULSTONE);
        blockIngotExchange(output, MalumItems.RAW_BRILLIANCE, MalumItems.BLOCK_OF_BRILLIANCE);
        blockIngotExchange(output, MalumItems.BLAZING_QUARTZ, MalumItems.BLOCK_OF_BLAZING_QUARTZ);
        blockIngotExchange(output, MalumItems.CTHONIC_GOLD, MalumItems.BLOCK_OF_CTHONIC_GOLD);

        blockIngotExchange(output, MalumItems.ROTTING_ESSENCE, MalumItems.BLOCK_OF_ROTTING_ESSENCE);
        blockIngotExchange(output, MalumItems.GRIM_TALC, MalumItems.BLOCK_OF_GRIM_TALC);
        blockIngotExchange(output, MalumItems.EERIE_WEAVE, MalumItems.BLOCK_OF_EERIE_WEAVE);
        blockIngotExchange(output, MalumItems.WARP_FLUX, MalumItems.BLOCK_OF_WARP_FLUX);

        blockIngotExchange(output, MalumItems.WIND_NUCLEUS, MalumItems.BLOCK_OF_WIND_NUCLEI);
        blockIngotExchange(output, MalumItems.PYRE_NUCLEUS, MalumItems.BLOCK_OF_PYRE_NUCLEI);

        blockIngotExchange(output, MalumItems.HEX_ASH, MalumItems.BLOCK_OF_HEX_ASH);
        blockIngotExchange(output, MalumItems.LIVING_FLESH, MalumItems.BLOCK_OF_LIVING_FLESH);
        blockIngotExchange(output, MalumItems.ALCHEMICAL_CALX, MalumItems.BLOCK_OF_ALCHEMICAL_CALX);
        blockIngotExchange(output, MalumItems.ARCANE_CHARCOAL, MalumItems.BLOCK_OF_ARCANE_CHARCOAL);

        blockIngotExchange(output, MalumItems.NULL_SLATE, MalumItems.BLOCK_OF_NULL_SLATE);
        blockIngotExchange(output, MalumItems.VOID_SALTS, MalumItems.BLOCK_OF_VOID_SALTS);
        blockIngotExchange(output, MalumItems.MNEMONIC_FRAGMENT, MalumItems.BLOCK_OF_MNEMONIC_FRAGMENT);
        blockIngotExchange(output, MalumItems.MALIGNANT_LEAD, MalumItems.BLOCK_OF_MALIGNANT_LEAD);
        blockIngotExchange(output, MalumItems.AURIC_EMBERS, MalumItems.BLOCK_OF_AURIC_EMBERS);

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

    private static RecipeBuilder smeltingWithCount(Ingredient ingredient, RecipeCategory category, Item resultItem, int resultCount, float experience, int time) {
        return smelting(ingredient, category, new ItemStack(resultItem, resultCount), experience, time);
    }

    private static RecipeBuilder blastingWithCount(Ingredient ingredient, RecipeCategory category, Item resultItem, int resultCount, float experience, int time) {
        return blasting(ingredient, category, new ItemStack(resultItem, resultCount), experience, time);
    }

    private static void bannerRecipe(RecipeOutput consumer, Item material, SoulwovenBannerPatternDataComponent pattern) {
        shapeless(RecipeCategory.BUILDING_BLOCKS, pattern.getDefaultStack()).requires(MalumItems.SOULWOVEN_BANNER.get()).requires(material).unlockedBy("has_soulwoven_silk", has(MalumItems.SOULWOVEN_SILK.get())).save(consumer, pattern.getRecipeId());
    }

    private static void weaveRecipe(RecipeOutput consumer, Item sideItem, Supplier<? extends Item> output) {
        shapeless(RecipeCategory.MISC, output.get()).requires(MalumItems.ESOTERIC_SPOOL.get()).requires(sideItem).unlockedBy("has_spool", has(MalumItems.ESOTERIC_SPOOL.get())).save(consumer);
    }

    private static void ingotNuggetExchange(RecipeOutput consumer, Supplier<? extends Item> itemForm, Supplier<? extends Item> blockForm) {
        compacting(consumer, itemForm, blockForm, "nugget");
    }
    private static void blockIngotExchange(RecipeOutput consumer, Supplier<? extends Item> itemForm, Supplier<? extends Item> blockForm) {
        compacting(consumer, itemForm, blockForm, "block");
    }
    private static void compacting(RecipeOutput consumer, Supplier<? extends Item> smallForm, Supplier<? extends Item> bigForm, String type) {
        var small = smallForm.get();
        var big = bigForm.get();
        String blockName = BuiltInRegistries.ITEM.getKey(big).getPath();
        String itemName = BuiltInRegistries.ITEM.getKey(small).getPath();
        shaped(RecipeCategory.MISC, big)
                .define('#', small)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_" + itemName, has(small))
                .save(consumer, malumPath(blockName));
        shapeless(RecipeCategory.MISC, small, 9)
                .requires(big)
                .unlockedBy("has_" + itemName, has(small))
                .save(consumer, malumPath(itemName + "_from_" + type));
    }
    private static void plating(RecipeOutput consumer, Supplier<? extends Item> nuggetForm, Supplier<? extends Item> ingotForm, Supplier<? extends Item> result) {
        Item nugget = nuggetForm.get();
        Item ingot = ingotForm.get();
        Item plating = result.get();
        String itemName = BuiltInRegistries.ITEM.getKey(ingot).getPath();
        shaped(RecipeCategory.MISC, plating)
                .define('X', nugget)
                .define('Y', ingot)
                .pattern(" X ")
                .pattern("XYX")
                .pattern(" X ")
                .unlockedBy("has_" + itemName, has(ingot))
                .save(consumer);
    }

    private static void nodeSmelting(RecipeOutput recipeoutput, Holder<Item> node, TagKey<Item> tag) {
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

    private static void etherTorch(RecipeOutput recipeoutput, ItemLike output, ItemLike ether) {
        var id = BuiltInRegistries.ITEM.getKey(output.asItem()).getPath();
        new NBTCarryRecipeBuilder(RecipeCategory.BUILDING_BLOCKS, new ItemStack(output.asItem(), 2), Ingredient.of(ether))
                .define('X', ether)
                .define('Y', Ingredient.of(Tags.Items.RODS_WOODEN))
                .pattern("X").pattern("Y")
                .unlockedBy("has_ether", has(MalumItems.ETHER.get()))
                .save(recipeoutput, id + "_crafting");
    }

    private static void etherCandle(RecipeOutput recipeoutput, ItemLike output, ItemLike ether) {
        var id = BuiltInRegistries.ITEM.getKey(output.asItem()).getPath();
        new NBTCarryRecipeBuilder(RecipeCategory.BUILDING_BLOCKS, new ItemStack(output.asItem(), 2), Ingredient.of(ether))
                .define('X', ether)
                .define('Y', Items.HONEYCOMB)
                .pattern("X").pattern("Y")
                .unlockedBy("has_ether", has(MalumItems.ETHER.get()))
                .save(recipeoutput, id + "_crafting");
    }

    private static void etherBrazier(RecipeOutput recipeoutput, ItemLike output, ItemLike rock, ItemLike ether) {
        new NBTCarryRecipeBuilder(RecipeCategory.BUILDING_BLOCKS, new ItemStack(output.asItem(), 2), Ingredient.of(ether))
                .define('X', ether)
                .define('Y', rock)
                .pattern("X").pattern("Y")
                .unlockedBy("has_ether", has(MalumItems.ETHER.get()))
                .save(recipeoutput, BuiltInRegistries.ITEM.getKey(output.asItem()).getPath());
    }


    private static void etherCresset(RecipeOutput recipeoutput, ItemLike output, ItemLike rock, ItemLike ether) {
        new NBTCarryRecipeBuilder(RecipeCategory.BUILDING_BLOCKS, new ItemStack(output.asItem(), 2), Ingredient.of(ether))
                .define('X', ether)
                .define('Y', rock)
                .pattern("X").pattern("Y").pattern("Y")
                .unlockedBy("has_ether", has(MalumItems.ETHER.get()))
                .save(recipeoutput, BuiltInRegistries.ITEM.getKey(output.asItem()).getPath());
    }

    public static Criterion<InventoryChangeTrigger.TriggerInstance> has(MinMaxBounds.Ints count, ItemLike item) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(item).withCount(count));
    }

    public static Criterion<InventoryChangeTrigger.TriggerInstance> has(ItemLike itemLike) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(itemLike));
    }

    public static Criterion<InventoryChangeTrigger.TriggerInstance> has(TagKey<Item> tag) {
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
