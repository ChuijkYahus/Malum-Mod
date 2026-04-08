package com.sammy.malum.datagen.recipe;

import com.mojang.datafixers.util.Pair;
import com.sammy.malum.common.data.component.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.item.*;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.*;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import team.lodestar.lodestone.recipe.builder.*;

import java.util.function.*;

import static com.sammy.malum.MalumMod.*;
import static com.sammy.malum.datagen.recipe.RecipeDatagenCommons.smeltAndBlast;
import static net.minecraft.data.recipes.ShapedRecipeBuilder.*;
import static net.minecraft.data.recipes.ShapelessRecipeBuilder.*;

public class MalumVanillaRecipes implements IConditionBuilder {

    protected static void buildRecipes(RecipeOutput output) {
        var hasBlight = RecipeDatagenCommons.has(MalumItems.BLIGHTED_GUNK.get());
        var hasSoulstone = RecipeDatagenCommons.has(MalumItems.RAW_SOULSTONE.get());
        var hasHallowedGold = RecipeDatagenCommons.has(MalumItems.HALLOWED_GOLD_INGOT.get());
        var hasSoulStainedSteel = RecipeDatagenCommons.has(MalumItems.SOUL_STAINED_STEEL_INGOT.get());


        //KEY ITEMS
        shapeless(RecipeCategory.MISC, MalumItems.ENCYCLOPEDIA_ARCANA.get())
                .requires(net.minecraft.world.item.Items.BOOK)
                .requires(MalumItems.REFINED_SOULSTONE.get())
                .unlockedBy("has_soulstone", hasSoulstone)
                .save(output);
        shaped(RecipeCategory.MISC, MalumItems.CRUDE_SCYTHE.get())
                .define('#', Tags.Items.RODS_WOODEN)
                .define('Y', MalumItems.REFINED_SOULSTONE.get())
                .define('X', Tags.Items.INGOTS_IRON)
                .pattern("XXY")
                .pattern(" #X")
                .pattern("#  ")
                .unlockedBy("has_soulstone", hasSoulstone)
                .save(output);
        shaped(RecipeCategory.MISC, MalumItems.SPIRIT_ALTAR.get())
                .define('Z', Tags.Items.INGOTS_GOLD)
                .define('Y', MalumItems.REFINED_SOULSTONE.get())
                .define('X', MalumTags.Items.RUNEWOOD_PLANKS)
                .pattern(" Y ")
                .pattern("ZXZ")
                .pattern("XXX")
                .unlockedBy("has_soulstone", hasSoulstone)
                .save(output);
        shaped(RecipeCategory.MISC, MalumItems.WEAVERS_WORKBENCH.get())
                .define('Z', MalumItems.HALLOWED_GOLD_INGOT.get())
                .define('Y', MalumItems.HEX_ASH.get())
                .define('X', MalumTags.Items.RUNEWOOD_PLANKS)
                .pattern("XYX")
                .pattern("XZX")
                .unlockedBy("has_hex_ash", RecipeDatagenCommons.has(MalumItems.HEX_ASH.get()))
                .save(output);
        shaped(RecipeCategory.MISC, MalumItems.SOUL_BRAZIER.get())
                .define('Z', MalumItems.CTHONIC_GOLD.get())
                .define('Y', MalumItems.HALLOWED_GOLD_INLAY.get())
                .define('X', MalumTags.Items.RUNEWOOD_PLANKS)
                .define('W', MalumItems.TAINTED_ROCK.get())
                .pattern("YZY")
                .pattern("XXX")
                .pattern("WXW")
                .unlockedBy("has_soulstone", hasSoulstone)
                .save(output);
        shaped(RecipeCategory.MISC, MalumItems.SPIRIT_JAR.get())
                .define('X', MalumItems.HALLOWED_GOLD_INGOT.get())
                .define('Y', Tags.Items.GLASS_BLOCKS)
                .pattern("X")
                .pattern("Y")
                .unlockedBy("has_hallowed_gold", hasHallowedGold)
                .save(output);
        shaped(RecipeCategory.MISC, MalumItems.SOULWOVEN_POUCH.get())
                .define('X', Tags.Items.STRINGS)
                .define('Y', MalumItems.SOULWOVEN_SILK.get())
                .pattern("X")
                .pattern("Y")
                .unlockedBy("has_soulwoven_silk", RecipeDatagenCommons.has(MalumItems.SOULWOVEN_SILK.get()))
                .save(output);
        shaped(RecipeCategory.MISC, MalumItems.TOTEMIC_STAFF.get())
                .define('X', Tags.Items.RODS_WOODEN)
                .define('Y', MalumTags.Items.RUNEWOOD_PLANKS)
                .pattern("  Y")
                .pattern(" X ")
                .pattern("X  ")
                .unlockedBy("has_totem_base", RecipeDatagenCommons.has(MalumItems.RUNEWOOD_TOTEM_BASE.get()))
                .save(output);

        //CRAFTING COMPONENTS
        shaped(RecipeCategory.MISC, MalumItems.CONVOLUTED_LENS.get(), 2)
                .define('X', MalumItems.HALLOWED_GOLD_NUGGET.get())
                .define('Y', MalumItems.WARP_FLUX.get())
                .pattern(" X ")
                .pattern("XYX")
                .pattern(" X ")
                .unlockedBy("has_hallowed_gold", hasHallowedGold).save(output);

        shapeless(RecipeCategory.MISC, MalumItems.MIMICRY_RELAY.get())
                .requires(MalumItems.IRON_METALLICS.getNode().get())
                .requires(MalumItems.CTHONIC_GOLD_FRAGMENT.get())
                .requires(Tags.Items.GEMS_QUARTZ)
                .unlockedBy("has_iron_impetus", RecipeDatagenCommons.has(MalumItems.IRON_METALLICS.getImpetus().get())).save(output);

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

        //SAP & ARCANE CHARCOAL
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(MalumTags.Items.RUNEWOOD_LOGS), RecipeCategory.MISC, MalumItems.ARCANE_CHARCOAL.get(), 0.25f, 200).unlockedBy("has_runewood_planks", RecipeDatagenCommons.has(MalumTags.Items.RUNEWOOD_LOGS)).save(output, malumPath("arcane_charcoal_from_runewood"));
        shapeless(RecipeCategory.MISC, MalumItems.RUNIC_SAPBALL.get()).requires(MalumItems.RUNIC_SAP.get()).requires(MalumItems.RUNIC_SAP.get()).unlockedBy("has_runic_sap", RecipeDatagenCommons.has(MalumItems.RUNIC_SAP.get())).save(output);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(MalumTags.Items.SOULWOOD_LOGS), RecipeCategory.MISC, MalumItems.ARCANE_CHARCOAL.get(), 0.25f, 200).unlockedBy("has_soulwood_planks", RecipeDatagenCommons.has(MalumTags.Items.SOULWOOD_LOGS)).save(output, malumPath("arcane_charcoal_from_soulwood"));
        shapeless(RecipeCategory.MISC, MalumItems.CURSED_SAPBALL.get()).requires(MalumItems.CURSED_SAP.get()).unlockedBy("has_cursed_sap", RecipeDatagenCommons.has(MalumItems.CURSED_SAP.get())).save(output);

        //BLIGHT
        shapeless(RecipeCategory.MISC, MalumItems.BLIGHT.get()).requires(MalumItems.BLIGHTED_GUNK.get()).unlockedBy("has_blight", hasBlight).save(output);
        shaped(RecipeCategory.MISC, MalumItems.BLIGHTED_EARTH.get())
                .define('X', MalumItems.BLIGHTED_GUNK.get())
                .pattern("XX")
                .pattern("XX")
                .unlockedBy("has_blight", hasBlight).save(output);
        shaped(RecipeCategory.MISC, MalumItems.COLUMNAR_BLIGHT.get())
                .define('X', MalumItems.BLIGHTED_GUNK.get())
                .pattern("X")
                .pattern("X")
                .pattern("X")
                .unlockedBy("has_blight", hasBlight).save(output);

        //BANNERS
        shaped(RecipeCategory.BUILDING_BLOCKS, MalumItems.SOULWOVEN_BANNER.get()).define('X', MalumTags.Items.RUNEWOOD_PLANKS).define('Y', MalumItems.SOULWOVEN_SILK.get()).pattern("X").pattern("Y").pattern("Y").unlockedBy("has_soulwoven_silk", RecipeDatagenCommons.has(MalumItems.SOULWOVEN_SILK.get())).save(output);
        bannerRecipe(output, MalumItems.ROTTING_ESSENCE.get(), SoulwovenBannerPatternDataComponent.HUNGER);
        bannerRecipe(output, MalumItems.GRIM_TALC.get(), SoulwovenBannerPatternDataComponent.HORNS);
        bannerRecipe(output, MalumItems.EERIE_WEAVE.get(), SoulwovenBannerPatternDataComponent.HEFT);
        bannerRecipe(output, MalumItems.WARP_FLUX.get(), SoulwovenBannerPatternDataComponent.HALLUCINATION);

        //SPIRIT METALS
        RecipeDatagenCommons.blockIngotExchange(output, MalumItems.SOUL_STAINED_STEEL_INGOT, MalumItems.BLOCK_OF_SOUL_STAINED_STEEL);
        RecipeDatagenCommons.ingotNuggetExchange(output, MalumItems.SOUL_STAINED_STEEL_NUGGET, MalumItems.SOUL_STAINED_STEEL_INGOT);
        plating(output, MalumItems.SOUL_STAINED_STEEL_NUGGET, MalumItems.SOUL_STAINED_STEEL_INGOT, MalumItems.SOUL_STAINED_STEEL_PLATING);

        RecipeDatagenCommons.blockIngotExchange(output, MalumItems.HALLOWED_GOLD_INGOT, MalumItems.BLOCK_OF_HALLOWED_GOLD);
        RecipeDatagenCommons.ingotNuggetExchange(output, MalumItems.HALLOWED_GOLD_NUGGET, MalumItems.HALLOWED_GOLD_INGOT);
        plating(output, MalumItems.HALLOWED_GOLD_NUGGET, MalumItems.HALLOWED_GOLD_INGOT, MalumItems.HALLOWED_GOLD_INLAY);

        RecipeDatagenCommons.blockIngotExchange(output, MalumItems.MALIGNANT_PEWTER_INGOT, MalumItems.BLOCK_OF_MALIGNANT_PEWTER);
        RecipeDatagenCommons.ingotNuggetExchange(output, MalumItems.MALIGNANT_PEWTER_NUGGET, MalumItems.MALIGNANT_PEWTER_INGOT);
        plating(output, MalumItems.MALIGNANT_PEWTER_NUGGET, MalumItems.MALIGNANT_PEWTER_INGOT, MalumItems.MALIGNANT_PEWTER_PLATING);
        
        //TOOLS
        shaped(RecipeCategory.MISC, MalumItems.SOUL_STAINED_STEEL_HOE.get()).define('#', Tags.Items.RODS_WOODEN).define('X', MalumItems.SOUL_STAINED_STEEL_INGOT.get()).pattern("XX").pattern(" #").pattern(" #").unlockedBy("has_soul_stained_steel", hasSoulStainedSteel).save(output);
        shaped(RecipeCategory.MISC, MalumItems.SOUL_STAINED_STEEL_PICKAXE.get()).define('#', Tags.Items.RODS_WOODEN).define('X', MalumItems.SOUL_STAINED_STEEL_INGOT.get()).pattern("XXX").pattern(" # ").pattern(" # ").unlockedBy("has_soul_stained_steel", hasSoulStainedSteel).save(output);
        shaped(RecipeCategory.MISC, MalumItems.SOUL_STAINED_STEEL_AXE.get()).define('#', Tags.Items.RODS_WOODEN).define('X', MalumItems.SOUL_STAINED_STEEL_INGOT.get()).pattern("XX ").pattern("X# ").pattern(" # ").unlockedBy("has_soul_stained_steel", hasSoulStainedSteel).save(output);
        shaped(RecipeCategory.MISC, MalumItems.SOUL_STAINED_STEEL_SHOVEL.get()).define('#', Tags.Items.RODS_WOODEN).define('X', MalumItems.SOUL_STAINED_STEEL_INGOT.get()).pattern("X").pattern("#").pattern("#").unlockedBy("has_soul_stained_steel", hasSoulStainedSteel).save(output);
        shaped(RecipeCategory.MISC, MalumItems.SOUL_STAINED_STEEL_SWORD.get()).define('#', Tags.Items.RODS_WOODEN).define('X', MalumItems.SOUL_STAINED_STEEL_INGOT.get()).pattern("X").pattern("X").pattern("#").unlockedBy("has_soul_stained_steel", hasSoulStainedSteel).save(output);

        //TRINKETS
        shaped(RecipeCategory.MISC, MalumItems.GILDED_RING.get()).define('X', MalumItems.HALLOWED_GOLD_INGOT.get()).define('Y', Tags.Items.LEATHERS).pattern("XY ").pattern("Y Y").pattern(" Y ").unlockedBy("has_hallowed_gold", hasHallowedGold).save(output);
        shaped(RecipeCategory.MISC, MalumItems.GILDED_BELT.get()).define('X', MalumItems.HALLOWED_GOLD_INLAY.get()).define('Y', Tags.Items.LEATHERS).pattern(" Y ").pattern("Y Y").pattern(" X ").unlockedBy("has_hallowed_gold", hasHallowedGold).save(output);

        shaped(RecipeCategory.MISC, MalumItems.ORNATE_RING.get()).define('X', MalumItems.SOUL_STAINED_STEEL_INGOT.get()).define('Y', Tags.Items.LEATHERS).pattern("XY ").pattern("Y Y").pattern(" Y ").unlockedBy("has_soul_stained_steel", hasSoulStainedSteel).save(output);
        shaped(RecipeCategory.MISC, MalumItems.ORNATE_NECKLACE.get()).define('X', MalumItems.SOUL_STAINED_STEEL_PLATING.get()).define('Y', MalumItems.EERIE_WEAVE.get()).pattern(" Y ").pattern("Y Y").pattern(" X ").unlockedBy("has_soul_stained_steel", hasSoulStainedSteel).save(output);

        shaped(RecipeCategory.MISC, MalumItems.RUNIC_BROOCH.get()).define('X', MalumItems.HALLOWED_GOLD_INLAY.get()).define('Y', MalumItems.BLOCK_OF_HALLOWED_GOLD.get()).define('Z', Tags.Items.LEATHERS).pattern(" Z ").pattern("ZXZ").pattern(" Y ").unlockedBy("has_hallowed_gold", hasHallowedGold).save(output);
        shaped(RecipeCategory.MISC, MalumItems.ELABORATE_BROOCH.get()).define('X', MalumItems.SOUL_STAINED_STEEL_PLATING.get()).define('Y', MalumItems.BLOCK_OF_SOUL_STAINED_STEEL.get()).define('Z', Tags.Items.LEATHERS).pattern(" Z ").pattern("ZXZ").pattern(" Y ").unlockedBy("has_soul_stained_steel", hasSoulStainedSteel).save(output);

        smeltAndBlast(output, malumPath("blazing_quartz"), Ingredient.of(MalumItems.BLAZING_QUARTZ_ORE.get()), RecipeCategory.MISC,
                Pair.of("has_blazing_quartz", RecipeDatagenCommons.has(MalumItems.BLAZING_QUARTZ.get())),
                MalumItems.BLAZING_QUARTZ.get(), 0.25f
        );

        smeltAndBlast(output, malumPath("natural_quartz"), Ingredient.of(MalumItems.NATURAL_QUARTZ_ORE.get()), RecipeCategory.MISC,
                Pair.of("has_natural_quartz", RecipeDatagenCommons.has(MalumItems.NATURAL_QUARTZ.get())),
                MalumItems.NATURAL_QUARTZ.get(), 0.25f
        );
        smeltAndBlast(output, malumPath("natural_quartz_deepslate"), Ingredient.of(MalumItems.DEEPSLATE_QUARTZ_ORE.get()), RecipeCategory.MISC,
                Pair.of("has_natural_quartz", RecipeDatagenCommons.has(MalumItems.NATURAL_QUARTZ.get())),
                MalumItems.NATURAL_QUARTZ.get(), 0.25f
        );

        smeltAndBlast(output, malumPath("brilliance"), Ingredient.of(MalumItems.BRILLIANT_STONE.get()), RecipeCategory.MISC,
                Pair.of("has_brilliance", RecipeDatagenCommons.has(MalumItems.RAW_BRILLIANCE.get())),
                MalumItems.REFINED_BRILLIANCE.get(), 2, 1f
        );
        smeltAndBlast(output, malumPath("brilliance_deepslate"), Ingredient.of(MalumItems.BRILLIANT_DEEPSLATE.get()), RecipeCategory.MISC,
                Pair.of("has_brilliance", RecipeDatagenCommons.has(MalumItems.RAW_BRILLIANCE.get())),
                MalumItems.REFINED_BRILLIANCE.get(), 2, 1f
        );

        smeltAndBlast(output, malumPath("soulstone"), Ingredient.of(MalumItems.SOULSTONE_ORE.get()), RecipeCategory.MISC,
                Pair.of("has_soulstone", hasSoulstone),
                MalumItems.REFINED_SOULSTONE.get(), 2, 0.25f
        );
        smeltAndBlast(output, malumPath("soulstone_deepslate"), Ingredient.of(MalumItems.DEEPSLATE_SOULSTONE_ORE.get()), RecipeCategory.MISC,
                Pair.of("has_soulstone", hasSoulstone),
                MalumItems.REFINED_SOULSTONE.get(), 2, 0.25f
        );

        smeltAndBlast(output, malumPath("brilliance_raw"), Ingredient.of(MalumItems.RAW_BRILLIANCE.get()), RecipeCategory.MISC,
                Pair.of("has_brilliance", RecipeDatagenCommons.has(MalumItems.RAW_BRILLIANCE.get())),
                MalumItems.REFINED_BRILLIANCE.get(), 2, 1f
        );
        smeltAndBlast(output, malumPath("brilliance_crushed"), Ingredient.of(MalumItems.CRUSHED_BRILLIANCE.get()), RecipeCategory.MISC,
                Pair.of("has_brilliance", RecipeDatagenCommons.has(MalumItems.RAW_BRILLIANCE.get())),
                MalumItems.REFINED_BRILLIANCE.get(), 2, 1f
        );
        smeltAndBlast(output, malumPath("raw_soulstone"), Ingredient.of(MalumItems.RAW_SOULSTONE.get()), RecipeCategory.MISC,
                Pair.of("has_soulstone", hasSoulstone),
                MalumItems.REFINED_SOULSTONE.get(), 2, 0.25f
        );
        smeltAndBlast(output, malumPath("soulstone_crushed"), Ingredient.of(MalumItems.CRUSHED_SOULSTONE.get()), RecipeCategory.MISC,
                Pair.of("has_soulstone", hasSoulstone),
                MalumItems.REFINED_SOULSTONE.get(), 2, 0.25f
        );

        //FULL BLOCKS
        RecipeDatagenCommons.blockIngotExchange(output, MalumItems.RAW_SOULSTONE, MalumItems.BLOCK_OF_RAW_SOULSTONE);
        RecipeDatagenCommons.blockIngotExchange(output, MalumItems.REFINED_SOULSTONE, MalumItems.BLOCK_OF_SOULSTONE);
        RecipeDatagenCommons.blockIngotExchange(output, MalumItems.RAW_BRILLIANCE, MalumItems.BLOCK_OF_RAW_BRILLIANCE);
        RecipeDatagenCommons.blockIngotExchange(output, MalumItems.REFINED_BRILLIANCE, MalumItems.BLOCK_OF_BRILLIANCE);
        RecipeDatagenCommons.blockIngotExchange(output, MalumItems.BLAZING_QUARTZ, MalumItems.BLOCK_OF_BLAZING_QUARTZ);
        RecipeDatagenCommons.blockIngotExchange(output, MalumItems.NATURAL_QUARTZ, MalumItems.BLOCK_OF_NATURAL_QUARTZ);
        RecipeDatagenCommons.blockIngotExchange(output, MalumItems.CTHONIC_GOLD, MalumItems.BLOCK_OF_CTHONIC_GOLD);

        RecipeDatagenCommons.blockIngotExchange(output, MalumItems.ROTTING_ESSENCE, MalumItems.BLOCK_OF_ROTTING_ESSENCE);
        RecipeDatagenCommons.blockIngotExchange(output, MalumItems.GRIM_TALC, MalumItems.BLOCK_OF_GRIM_TALC);
        RecipeDatagenCommons.blockIngotExchange(output, MalumItems.EERIE_WEAVE, MalumItems.BLOCK_OF_EERIE_WEAVE);
        RecipeDatagenCommons.blockIngotExchange(output, MalumItems.WARP_FLUX, MalumItems.BLOCK_OF_WARP_FLUX);

        RecipeDatagenCommons.blockIngotExchange(output, MalumItems.WIND_NUCLEUS, MalumItems.BLOCK_OF_WIND_NUCLEI);
        RecipeDatagenCommons.blockIngotExchange(output, MalumItems.PYRE_NUCLEUS, MalumItems.BLOCK_OF_PYRE_NUCLEI);

        RecipeDatagenCommons.blockIngotExchange(output, MalumItems.HEX_ASH, MalumItems.BLOCK_OF_HEX_ASH);
        RecipeDatagenCommons.blockIngotExchange(output, MalumItems.LIVING_FLESH, MalumItems.BLOCK_OF_LIVING_FLESH);
        RecipeDatagenCommons.blockIngotExchange(output, MalumItems.ALCHEMICAL_CALX, MalumItems.BLOCK_OF_ALCHEMICAL_CALX);
        RecipeDatagenCommons.blockIngotExchange(output, MalumItems.ARCANE_CHARCOAL, MalumItems.BLOCK_OF_ARCANE_CHARCOAL);

        RecipeDatagenCommons.blockIngotExchange(output, MalumItems.EBONY, MalumItems.BLOCK_OF_EBONY);
        RecipeDatagenCommons.blockIngotExchange(output, MalumItems.WITCHHAZEL, MalumItems.CRATE_OF_WITCHHAZEL);

        RecipeDatagenCommons.blockIngotExchange(output, MalumItems.NULL_SLATE, MalumItems.BLOCK_OF_NULL_SLATE);
        RecipeDatagenCommons.blockIngotExchange(output, MalumItems.VOID_SALTS, MalumItems.BLOCK_OF_VOID_SALTS);
        RecipeDatagenCommons.blockIngotExchange(output, MalumItems.MNEMONIC_FRAGMENT, MalumItems.BLOCK_OF_MNEMONIC_FRAGMENT);
        RecipeDatagenCommons.blockIngotExchange(output, MalumItems.MALIGNANT_LEAD, MalumItems.BLOCK_OF_MALIGNANT_LEAD);
        RecipeDatagenCommons.blockIngotExchange(output, MalumItems.AURIC_EMBERS, MalumItems.BLOCK_OF_AURIC_EMBERS);

        //MISC
        shaped(RecipeCategory.MISC, net.minecraft.world.item.Items.NETHERRACK, 2).define('Z', MalumItems.BLAZING_QUARTZ.get()).define('Y', Tags.Items.COBBLESTONES).pattern("ZY").pattern("YZ").unlockedBy("has_blazing_quartz", RecipeDatagenCommons.has(MalumItems.BLAZING_QUARTZ.get())).save(output, malumPath("netherrack_from_blazing_quartz"));
        shapeless(RecipeCategory.MISC, net.minecraft.world.item.Items.EXPERIENCE_BOTTLE).requires(MalumItems.REFINED_BRILLIANCE.get()).requires(net.minecraft.world.item.Items.GLASS_BOTTLE).unlockedBy("has_brilliance", RecipeDatagenCommons.has(MalumItems.REFINED_BRILLIANCE.get())).save(output, malumPath("experience_bottle_from_brilliance"));

        shapeless(RecipeCategory.MISC, net.minecraft.world.item.Items.BONE_MEAL, 6).requires(MalumItems.GRIM_TALC.get()).unlockedBy("has_grim_talc", RecipeDatagenCommons.has(MalumItems.GRIM_TALC.get())).save(output, malumPath("bonemeal_from_grim_talc"));
        shaped(RecipeCategory.MISC, net.minecraft.world.item.Items.SKELETON_SKULL).define('#', MalumItems.GRIM_TALC.get()).define('&', Tags.Items.BONES).pattern("&&&").pattern("&#&").pattern("&&&").unlockedBy("has_grim_talc", RecipeDatagenCommons.has(MalumItems.GRIM_TALC.get())).save(output, malumPath("skeleton_skull_from_grim_talc"));
        shaped(RecipeCategory.MISC, net.minecraft.world.item.Items.ZOMBIE_HEAD).define('#', MalumItems.GRIM_TALC.get()).define('&', net.minecraft.world.item.Items.ROTTEN_FLESH).pattern("&&&").pattern("&#&").pattern("&&&").unlockedBy("has_grim_talc", RecipeDatagenCommons.has(MalumItems.GRIM_TALC.get())).save(output, malumPath("zombie_head_from_grim_talc"));

        shaped(RecipeCategory.MISC, net.minecraft.world.item.Items.TORCH, 6).define('#', MalumItems.BLAZING_QUARTZ.get()).define('&', net.minecraft.world.item.Items.STICK).pattern("#").pattern("&").unlockedBy("has_blazing_quartz", RecipeDatagenCommons.has(MalumItems.BLAZING_QUARTZ.get())).save(output, malumPath("torch_from_blazing_quartz"));

        //THE DEVICE
        shaped(RecipeCategory.MISC, MalumItems.THE_DEVICE.get()).define('X', MalumItems.TWISTED_ROCK.get()).define('Y', MalumItems.TAINTED_ROCK.get()).pattern("XYX").pattern("YXY").pattern("XYX").unlockedBy("has_bedrock", RecipeDatagenCommons.has(net.minecraft.world.item.Items.BEDROCK)).save(output);


        //WEAVES
        weaveRecipe(output, MalumItems.BLIGHTED_GUNK.get(), MalumItems.ANCIENT_WEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.IRON_INGOT, MalumItems.CORNERED_WEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.LAPIS_LAZULI, MalumItems.MECHANICAL_WEAVE_V1);
        weaveRecipe(output, net.minecraft.world.item.Items.REDSTONE, MalumItems.MECHANICAL_WEAVE_V2);

        weaveRecipe(output, net.minecraft.world.item.Items.BREAD, MalumItems.ACE_PRIDEWEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.BOOK, MalumItems.AGENDER_PRIDEWEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.ARROW, MalumItems.ARO_PRIDEWEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.WHEAT_SEEDS, MalumItems.AROACE_PRIDEWEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.WHEAT, MalumItems.BI_PRIDEWEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.RAW_IRON, MalumItems.DEMIBOY_PRIDEWEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.RAW_COPPER, MalumItems.DEMIGIRL_PRIDEWEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.MOSS_BLOCK, MalumItems.ENBY_PRIDEWEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.MELON_SLICE, MalumItems.GAY_PRIDEWEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.WATER_BUCKET, MalumItems.GENDERFLUID_PRIDEWEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.GLASS_BOTTLE, MalumItems.GENDERQUEER_PRIDEWEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.AZALEA, MalumItems.INTERSEX_PRIDEWEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.HONEYCOMB, MalumItems.LESBIAN_PRIDEWEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.CARROT, MalumItems.PAN_PRIDEWEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.REPEATER, MalumItems.PLURAL_PRIDEWEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.COMPARATOR, MalumItems.POLY_PRIDEWEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.STONE_BRICK_WALL, MalumItems.PRIDE_PRIDEWEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.EGG, MalumItems.TRANS_PRIDEWEAVE);
    }

    protected static void bannerRecipe(RecipeOutput consumer, Item material, SoulwovenBannerPatternDataComponent pattern) {
        shapeless(RecipeCategory.BUILDING_BLOCKS, pattern.getDefaultStack()).requires(MalumItems.SOULWOVEN_BANNER.get()).requires(material).unlockedBy("has_soulwoven_silk", RecipeDatagenCommons.has(MalumItems.SOULWOVEN_SILK.get())).save(consumer, pattern.getRecipeId());
    }

    protected static void weaveRecipe(RecipeOutput consumer, Item sideItem, Supplier<? extends Item> output) {
        shapeless(RecipeCategory.MISC, output.get()).requires(MalumItems.ESOTERIC_SPOOL.get()).requires(sideItem).unlockedBy("has_spool", RecipeDatagenCommons.has(MalumItems.ESOTERIC_SPOOL.get())).save(consumer);
    }

    protected static void plating(RecipeOutput consumer, Supplier<? extends Item> nuggetForm, Supplier<? extends Item> ingotForm, Supplier<? extends Item> result) {
        Item nugget = nuggetForm.get();
        Item ingot = ingotForm.get();
        Item plating = result.get();
        String itemName = BuiltInRegistries.ITEM.getKey(ingot).getPath();
        shaped(RecipeCategory.MISC, plating, 2)
                .define('X', nugget)
                .define('Y', ingot)
                .pattern(" X ")
                .pattern("XYX")
                .pattern(" X ")
                .unlockedBy("has_" + itemName, RecipeDatagenCommons.has(ingot))
                .save(consumer);
    }

    protected static void etherTorch(RecipeOutput recipeoutput, ItemLike output, ItemLike ether) {
        var id = BuiltInRegistries.ITEM.getKey(output.asItem()).getPath();
        new NBTCarryRecipeBuilder(RecipeCategory.BUILDING_BLOCKS, new ItemStack(output.asItem(), 2), Ingredient.of(ether))
                .define('X', ether)
                .define('Y', Ingredient.of(Tags.Items.RODS_WOODEN))
                .pattern("X").pattern("Y")
                .unlockedBy("has_ether", RecipeDatagenCommons.has(MalumItems.ETHER.get()))
                .save(recipeoutput, id + "_crafting");
    }

    protected static void etherCandle(RecipeOutput recipeoutput, ItemLike output, ItemLike ether) {
        var id = BuiltInRegistries.ITEM.getKey(output.asItem()).getPath();
        new NBTCarryRecipeBuilder(RecipeCategory.BUILDING_BLOCKS, new ItemStack(output.asItem(), 2), Ingredient.of(ether))
                .define('X', ether)
                .define('Y', net.minecraft.world.item.Items.HONEYCOMB)
                .pattern("X").pattern("Y")
                .unlockedBy("has_ether", RecipeDatagenCommons.has(MalumItems.ETHER.get()))
                .save(recipeoutput, id + "_crafting");
    }

    protected static void etherBrazier(RecipeOutput recipeoutput, ItemLike output, ItemLike rock, ItemLike ether) {
        new NBTCarryRecipeBuilder(RecipeCategory.BUILDING_BLOCKS, new ItemStack(output.asItem(), 2), Ingredient.of(ether))
                .define('X', ether)
                .define('Y', rock)
                .pattern("X").pattern("Y")
                .unlockedBy("has_ether", RecipeDatagenCommons.has(MalumItems.ETHER.get()))
                .save(recipeoutput, BuiltInRegistries.ITEM.getKey(output.asItem()).getPath());
    }


    protected static void etherCresset(RecipeOutput recipeoutput, ItemLike output, ItemLike rock, ItemLike ether) {
        new NBTCarryRecipeBuilder(RecipeCategory.BUILDING_BLOCKS, new ItemStack(output.asItem(), 2), Ingredient.of(ether))
                .define('X', ether)
                .define('Y', rock)
                .pattern("X").pattern("Y").pattern("Y")
                .unlockedBy("has_ether", RecipeDatagenCommons.has(MalumItems.ETHER.get()))
                .save(recipeoutput, BuiltInRegistries.ITEM.getKey(output.asItem()).getPath());
    }
}
