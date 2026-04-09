package com.sammy.malum.datagen.recipe;

import com.mojang.datafixers.util.Pair;
import com.sammy.malum.common.data.component.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.content.MalumContent;
import com.sammy.malum.registry.common.content.item.MalumItemProperties;

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
        var hasBlight = RecipeDatagenCommons.has(MalumContent.BlockSets.BLIGHTED_GUNK.get());
        var hasSoulstone = RecipeDatagenCommons.has(MalumContent.Materials.RAW_SOULSTONE.get());
        var hasHallowedGold = RecipeDatagenCommons.has(MalumContent.Materials.HALLOWED_GOLD_INGOT.get());
        var hasSoulStainedSteel = RecipeDatagenCommons.has(MalumContent.Materials.SOUL_STAINED_STEEL_INGOT.get());


        //KEY ITEMS
        shapeless(RecipeCategory.MISC, MalumContent.ENCYCLOPEDIA_ARCANA.get())
                .requires(net.minecraft.world.item.Items.BOOK)
                .requires(MalumContent.Materials.REFINED_SOULSTONE.get())
                .unlockedBy("has_soulstone", hasSoulstone)
                .save(output);
        shaped(RecipeCategory.MISC, MalumContent.Gear.CRUDE_SCYTHE.get())
                .define('#', Tags.Items.RODS_WOODEN)
                .define('Y', MalumContent.Materials.REFINED_SOULSTONE.get())
                .define('X', Tags.Items.INGOTS_IRON)
                .pattern("XXY")
                .pattern(" #X")
                .pattern("#  ")
                .unlockedBy("has_soulstone", hasSoulstone)
                .save(output);
        shaped(RecipeCategory.MISC, MalumContent.Progression.SPIRIT_ALTAR.get())
                .define('Z', Tags.Items.INGOTS_GOLD)
                .define('Y', MalumContent.Materials.REFINED_SOULSTONE.get())
                .define('X', MalumTags.Items.RUNEWOOD_PLANKS)
                .pattern(" Y ")
                .pattern("ZXZ")
                .pattern("XXX")
                .unlockedBy("has_soulstone", hasSoulstone)
                .save(output);
        shaped(RecipeCategory.MISC, MalumContent.Progression.WEAVERS_WORKBENCH.get())
                .define('Z', MalumContent.Materials.HALLOWED_GOLD_INGOT.get())
                .define('Y', MalumContent.Materials.HEX_ASH.get())
                .define('X', MalumTags.Items.RUNEWOOD_PLANKS)
                .pattern("XYX")
                .pattern("XZX")
                .unlockedBy("has_hex_ash", RecipeDatagenCommons.has(MalumContent.Materials.HEX_ASH.get()))
                .save(output);
        shaped(RecipeCategory.MISC, MalumContent.Progression.SOUL_BRAZIER.get())
                .define('Z', MalumContent.Materials.CTHONIC_GOLD.get())
                .define('Y', MalumContent.Materials.HALLOWED_GOLD_INLAY.get())
                .define('X', MalumTags.Items.RUNEWOOD_PLANKS)
                .define('W', MalumContent.BlockSets.TAINTED_ROCK_SET.getRock())
                .pattern("YZY")
                .pattern("XXX")
                .pattern("WXW")
                .unlockedBy("has_soulstone", hasSoulstone)
                .save(output);
        shaped(RecipeCategory.MISC, MalumContent.Progression.SPIRIT_JAR.get())
                .define('X', MalumContent.Materials.HALLOWED_GOLD_INGOT.get())
                .define('Y', Tags.Items.GLASS_BLOCKS)
                .pattern("X")
                .pattern("Y")
                .unlockedBy("has_hallowed_gold", hasHallowedGold)
                .save(output);
        shaped(RecipeCategory.MISC, MalumContent.Gear.SOULWOVEN_POUCH.get())
                .define('X', Tags.Items.STRINGS)
                .define('Y', MalumContent.Materials.SOULWOVEN_SILK.get())
                .pattern("X")
                .pattern("Y")
                .unlockedBy("has_soulwoven_silk", RecipeDatagenCommons.has(MalumContent.Materials.SOULWOVEN_SILK.get()))
                .save(output);
        shaped(RecipeCategory.MISC, MalumContent.Totemancy.TOTEMIC_STAFF.get())
                .define('X', Tags.Items.RODS_WOODEN)
                .define('Y', MalumTags.Items.RUNEWOOD_PLANKS)
                .pattern("  Y")
                .pattern(" X ")
                .pattern("X  ")
                .unlockedBy("has_totem_base", RecipeDatagenCommons.has(MalumContent.Totemancy.RUNEWOOD_TOTEM_BASE.get()))
                .save(output);

        //CRAFTING COMPONENTS
        shaped(RecipeCategory.MISC, MalumContent.Materials.CONVOLUTED_LENS.get(), 2)
                .define('X', MalumContent.Materials.HALLOWED_GOLD_NUGGET.get())
                .define('Y', MalumContent.Materials.WARP_FLUX.get())
                .pattern(" X ")
                .pattern("XYX")
                .pattern(" X ")
                .unlockedBy("has_hallowed_gold", hasHallowedGold).save(output);

        shapeless(RecipeCategory.MISC, MalumContent.Materials.MIMICRY_RELAY.get())
                .requires(MalumContent.Progression.IRON_METALLICS.getNode().get())
                .requires(MalumItemProperties.CTHONIC_GOLD_FRAGMENT.get())
                .requires(Tags.Items.GEMS_QUARTZ)
                .unlockedBy("has_iron_impetus", RecipeDatagenCommons.has(MalumContent.Progression.IRON_METALLICS.getImpetus().get())).save(output);

        //ETHER
        etherTorch(output, MalumItemProperties.ETHER_TORCH.get(), MalumItemProperties.ETHER.get());
        etherTorch(output, MalumItemProperties.IRIDESCENT_ETHER_TORCH.get(), MalumItemProperties.IRIDESCENT_ETHER.get());

        etherCandle(output, MalumItemProperties.ETHER_CANDLE.get(), MalumItemProperties.ETHER.get());
        etherCandle(output, MalumItemProperties.IRIDESCENT_ETHER_CANDLE.get(), MalumItemProperties.IRIDESCENT_ETHER.get());

        etherBrazier(output, MalumItemProperties.TAINTED_ETHER_BRAZIER.get(), MalumItemProperties.TAINTED_ROCK.get(), MalumItemProperties.ETHER.get());
        etherBrazier(output, MalumItemProperties.TWISTED_ETHER_BRAZIER.get(), MalumItemProperties.TWISTED_ROCK.get(), MalumItemProperties.ETHER.get());
        etherBrazier(output, MalumItemProperties.DROSS_ETHER_BRAZIER.get(), MalumItemProperties.DROSS_STONE.get(), MalumItemProperties.ETHER.get());

        etherBrazier(output, MalumItemProperties.TAINTED_IRIDESCENT_ETHER_BRAZIER.get(), MalumItemProperties.TAINTED_ROCK.get(), MalumItemProperties.IRIDESCENT_ETHER.get());
        etherBrazier(output, MalumItemProperties.TWISTED_IRIDESCENT_ETHER_BRAZIER.get(), MalumItemProperties.TWISTED_ROCK.get(), MalumItemProperties.IRIDESCENT_ETHER.get());
        etherBrazier(output, MalumItemProperties.DROSS_IRIDESCENT_ETHER_BRAZIER.get(), MalumItemProperties.DROSS_STONE.get(), MalumItemProperties.IRIDESCENT_ETHER.get());

        etherCresset(output, MalumItemProperties.TAINTED_ETHER_CRESSET.get(), MalumItemProperties.TAINTED_ROCK.get(), MalumItemProperties.ETHER.get());
        etherCresset(output, MalumItemProperties.TWISTED_ETHER_CRESSET.get(), MalumItemProperties.TWISTED_ROCK.get(), MalumItemProperties.ETHER.get());
        etherCresset(output, MalumItemProperties.DROSS_ETHER_CRESSET.get(), MalumItemProperties.DROSS_STONE.get(), MalumItemProperties.ETHER.get());

        etherCresset(output, MalumItemProperties.TAINTED_IRIDESCENT_ETHER_CRESSET.get(), MalumItemProperties.TAINTED_ROCK.get(), MalumItemProperties.IRIDESCENT_ETHER.get());
        etherCresset(output, MalumItemProperties.TWISTED_IRIDESCENT_ETHER_CRESSET.get(), MalumItemProperties.TWISTED_ROCK.get(), MalumItemProperties.IRIDESCENT_ETHER.get());
        etherCresset(output, MalumItemProperties.DROSS_IRIDESCENT_ETHER_CRESSET.get(), MalumItemProperties.DROSS_STONE.get(), MalumItemProperties.IRIDESCENT_ETHER.get());

        //SAP & ARCANE CHARCOAL
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(MalumTags.Items.RUNEWOOD_LOGS), RecipeCategory.MISC, MalumContent.Materials.ARCANE_CHARCOAL.get(), 0.25f, 200).unlockedBy("has_runewood_planks", RecipeDatagenCommons.has(MalumTags.Items.RUNEWOOD_LOGS)).save(output, malumPath("arcane_charcoal_from_runewood"));
        shapeless(RecipeCategory.MISC, MalumContent.Materials.RUNIC_SAPBALL.get()).requires(MalumContent.Materials.RUNIC_SAP_BOTTLE.get()).requires(MalumContent.Materials.RUNIC_SAP_BOTTLE.get()).unlockedBy("has_runic_sap", RecipeDatagenCommons.has(MalumContent.Materials.RUNIC_SAP_BOTTLE.get())).save(output);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(MalumTags.Items.SOULWOOD_LOGS), RecipeCategory.MISC, MalumContent.Materials.ARCANE_CHARCOAL.get(), 0.25f, 200).unlockedBy("has_soulwood_planks", RecipeDatagenCommons.has(MalumTags.Items.SOULWOOD_LOGS)).save(output, malumPath("arcane_charcoal_from_soulwood"));
        shapeless(RecipeCategory.MISC, MalumContent.Materials.CURSED_SAPBALL.get()).requires(MalumContent.Materials.CURSED_SAP_BOTTLE.get()).unlockedBy("has_cursed_sap", RecipeDatagenCommons.has(MalumContent.Materials.CURSED_SAP_BOTTLE.get())).save(output);

        //BLIGHT
        shapeless(RecipeCategory.MISC, MalumItemProperties.BLIGHT.get()).requires(MalumItemProperties.BLIGHTED_GUNK.get()).unlockedBy("has_blight", hasBlight).save(output);
        shaped(RecipeCategory.MISC, MalumItemProperties.BLIGHTED_EARTH.get())
                .define('X', MalumItemProperties.BLIGHTED_GUNK.get())
                .pattern("XX")
                .pattern("XX")
                .unlockedBy("has_blight", hasBlight).save(output);
        shaped(RecipeCategory.MISC, MalumItemProperties.COLUMNAR_BLIGHT.get())
                .define('X', MalumItemProperties.BLIGHTED_GUNK.get())
                .pattern("X")
                .pattern("X")
                .pattern("X")
                .unlockedBy("has_blight", hasBlight).save(output);

        //BANNERS
        shaped(RecipeCategory.BUILDING_BLOCKS, MalumItemProperties.SOULWOVEN_BANNER.get()).define('X', MalumTags.Items.RUNEWOOD_PLANKS).define('Y', MalumContent.Materials.SOULWOVEN_SILK.get()).pattern("X").pattern("Y").pattern("Y").unlockedBy("has_soulwoven_silk", RecipeDatagenCommons.has(MalumContent.Materials.SOULWOVEN_SILK.get())).save(output);
        bannerRecipe(output, MalumContent.Materials.ROTTING_ESSENCE.get(), SoulwovenBannerPatternDataComponent.HUNGER);
        bannerRecipe(output, MalumContent.Materials.GRIM_TALC.get(), SoulwovenBannerPatternDataComponent.HORNS);
        bannerRecipe(output, MalumContent.Materials.EERIE_WEAVE.get(), SoulwovenBannerPatternDataComponent.HEFT);
        bannerRecipe(output, MalumContent.Materials.WARP_FLUX.get(), SoulwovenBannerPatternDataComponent.HALLUCINATION);

        //SPIRIT METALS
        RecipeDatagenCommons.blockIngotExchange(output, MalumContent.Materials.SOUL_STAINED_STEEL_INGOT, MalumItemProperties.BLOCK_OF_SOUL_STAINED_STEEL);
        RecipeDatagenCommons.ingotNuggetExchange(output, MalumContent.Materials.SOUL_STAINED_STEEL_NUGGET, MalumContent.Materials.SOUL_STAINED_STEEL_INGOT);
        plating(output, MalumContent.Materials.SOUL_STAINED_STEEL_NUGGET, MalumContent.Materials.SOUL_STAINED_STEEL_INGOT, MalumContent.Materials.SOUL_STAINED_STEEL_PLATING);

        RecipeDatagenCommons.blockIngotExchange(output, MalumContent.Materials.HALLOWED_GOLD_INGOT, MalumItemProperties.BLOCK_OF_HALLOWED_GOLD);
        RecipeDatagenCommons.ingotNuggetExchange(output, MalumContent.Materials.HALLOWED_GOLD_NUGGET, MalumContent.Materials.HALLOWED_GOLD_INGOT);
        plating(output, MalumContent.Materials.HALLOWED_GOLD_NUGGET, MalumContent.Materials.HALLOWED_GOLD_INGOT, MalumContent.Materials.HALLOWED_GOLD_INLAY);

        RecipeDatagenCommons.blockIngotExchange(output, MalumContent.Materials.MALIGNANT_PEWTER_INGOT, MalumItemProperties.BLOCK_OF_MALIGNANT_PEWTER);
        RecipeDatagenCommons.ingotNuggetExchange(output, MalumContent.Materials.MALIGNANT_PEWTER_NUGGET, MalumContent.Materials.MALIGNANT_PEWTER_INGOT);
        plating(output, MalumContent.Materials.MALIGNANT_PEWTER_NUGGET, MalumContent.Materials.MALIGNANT_PEWTER_INGOT, MalumContent.Materials.MALIGNANT_PEWTER_PLATING);
        
        //TOOLS
        shaped(RecipeCategory.MISC, MalumContent.Gear.SOUL_STAINED_STEEL_HOE.get()).define('#', Tags.Items.RODS_WOODEN).define('X', MalumContent.Materials.SOUL_STAINED_STEEL_INGOT.get()).pattern("XX").pattern(" #").pattern(" #").unlockedBy("has_soul_stained_steel", hasSoulStainedSteel).save(output);
        shaped(RecipeCategory.MISC, MalumContent.Gear.SOUL_STAINED_STEEL_PICKAXE.get()).define('#', Tags.Items.RODS_WOODEN).define('X', MalumContent.Materials.SOUL_STAINED_STEEL_INGOT.get()).pattern("XXX").pattern(" # ").pattern(" # ").unlockedBy("has_soul_stained_steel", hasSoulStainedSteel).save(output);
        shaped(RecipeCategory.MISC, MalumContent.Gear.SOUL_STAINED_STEEL_AXE.get()).define('#', Tags.Items.RODS_WOODEN).define('X', MalumContent.Materials.SOUL_STAINED_STEEL_INGOT.get()).pattern("XX ").pattern("X# ").pattern(" # ").unlockedBy("has_soul_stained_steel", hasSoulStainedSteel).save(output);
        shaped(RecipeCategory.MISC, MalumContent.Gear.SOUL_STAINED_STEEL_SHOVEL.get()).define('#', Tags.Items.RODS_WOODEN).define('X', MalumContent.Materials.SOUL_STAINED_STEEL_INGOT.get()).pattern("X").pattern("#").pattern("#").unlockedBy("has_soul_stained_steel", hasSoulStainedSteel).save(output);
        shaped(RecipeCategory.MISC, MalumContent.Gear.SOUL_STAINED_STEEL_SWORD.get()).define('#', Tags.Items.RODS_WOODEN).define('X', MalumContent.Materials.SOUL_STAINED_STEEL_INGOT.get()).pattern("X").pattern("X").pattern("#").unlockedBy("has_soul_stained_steel", hasSoulStainedSteel).save(output);

        //TRINKETS
        shaped(RecipeCategory.MISC, MalumContent.Gear.GILDED_RING.get()).define('X', MalumContent.Materials.HALLOWED_GOLD_INGOT.get()).define('Y', Tags.Items.LEATHERS).pattern("XY ").pattern("Y Y").pattern(" Y ").unlockedBy("has_hallowed_gold", hasHallowedGold).save(output);
        shaped(RecipeCategory.MISC, MalumContent.Gear.GILDED_BELT.get()).define('X', MalumContent.Materials.HALLOWED_GOLD_INLAY.get()).define('Y', Tags.Items.LEATHERS).pattern(" Y ").pattern("Y Y").pattern(" X ").unlockedBy("has_hallowed_gold", hasHallowedGold).save(output);

        shaped(RecipeCategory.MISC, MalumContent.Gear.ORNATE_RING.get()).define('X', MalumContent.Materials.SOUL_STAINED_STEEL_INGOT.get()).define('Y', Tags.Items.LEATHERS).pattern("XY ").pattern("Y Y").pattern(" Y ").unlockedBy("has_soul_stained_steel", hasSoulStainedSteel).save(output);
        shaped(RecipeCategory.MISC, MalumContent.Gear.ORNATE_NECKLACE.get()).define('X', MalumContent.Materials.SOUL_STAINED_STEEL_PLATING.get()).define('Y', MalumContent.Materials.EERIE_WEAVE.get()).pattern(" Y ").pattern("Y Y").pattern(" X ").unlockedBy("has_soul_stained_steel", hasSoulStainedSteel).save(output);

        shaped(RecipeCategory.MISC, MalumContent.Gear.RUNIC_BROOCH.get()).define('X', MalumContent.Materials.HALLOWED_GOLD_INLAY.get()).define('Y', MalumItemProperties.BLOCK_OF_HALLOWED_GOLD.get()).define('Z', Tags.Items.LEATHERS).pattern(" Z ").pattern("ZXZ").pattern(" Y ").unlockedBy("has_hallowed_gold", hasHallowedGold).save(output);
        shaped(RecipeCategory.MISC, MalumContent.Gear.ELABORATE_BROOCH.get()).define('X', MalumContent.Materials.SOUL_STAINED_STEEL_PLATING.get()).define('Y', MalumItemProperties.BLOCK_OF_SOUL_STAINED_STEEL.get()).define('Z', Tags.Items.LEATHERS).pattern(" Z ").pattern("ZXZ").pattern(" Y ").unlockedBy("has_soul_stained_steel", hasSoulStainedSteel).save(output);

        smeltAndBlast(output, malumPath("blazing_quartz"), Ingredient.of(MalumItemProperties.BLAZING_QUARTZ_ORE.get()), RecipeCategory.MISC,
                Pair.of("has_blazing_quartz", RecipeDatagenCommons.has(MalumItemProperties.BLAZING_QUARTZ.get())),
                MalumItemProperties.BLAZING_QUARTZ.get(), 0.25f
        );

        smeltAndBlast(output, malumPath("natural_quartz"), Ingredient.of(MalumItemProperties.NATURAL_QUARTZ_ORE.get()), RecipeCategory.MISC,
                Pair.of("has_natural_quartz", RecipeDatagenCommons.has(MalumItemProperties.NATURAL_QUARTZ.get())),
                MalumItemProperties.NATURAL_QUARTZ.get(), 0.25f
        );
        smeltAndBlast(output, malumPath("natural_quartz_deepslate"), Ingredient.of(MalumItemProperties.DEEPSLATE_QUARTZ_ORE.get()), RecipeCategory.MISC,
                Pair.of("has_natural_quartz", RecipeDatagenCommons.has(MalumItemProperties.NATURAL_QUARTZ.get())),
                MalumItemProperties.NATURAL_QUARTZ.get(), 0.25f
        );

        smeltAndBlast(output, malumPath("brilliance"), Ingredient.of(MalumItemProperties.BRILLIANT_STONE.get()), RecipeCategory.MISC,
                Pair.of("has_brilliance", RecipeDatagenCommons.has(MalumContent.Materials.RAW_BRILLIANCE.get())),
                MalumContent.Materials.REFINED_BRILLIANCE.get(), 2, 1f
        );
        smeltAndBlast(output, malumPath("brilliance_deepslate"), Ingredient.of(MalumItemProperties.BRILLIANT_DEEPSLATE.get()), RecipeCategory.MISC,
                Pair.of("has_brilliance", RecipeDatagenCommons.has(MalumContent.Materials.RAW_BRILLIANCE.get())),
                MalumContent.Materials.REFINED_BRILLIANCE.get(), 2, 1f
        );

        smeltAndBlast(output, malumPath("soulstone"), Ingredient.of(MalumItemProperties.SOULSTONE_ORE.get()), RecipeCategory.MISC,
                Pair.of("has_soulstone", hasSoulstone),
                MalumContent.Materials.REFINED_SOULSTONE.get(), 2, 0.25f
        );
        smeltAndBlast(output, malumPath("soulstone_deepslate"), Ingredient.of(MalumItemProperties.DEEPSLATE_SOULSTONE_ORE.get()), RecipeCategory.MISC,
                Pair.of("has_soulstone", hasSoulstone),
                MalumContent.Materials.REFINED_SOULSTONE.get(), 2, 0.25f
        );

        smeltAndBlast(output, malumPath("brilliance_raw"), Ingredient.of(MalumContent.Materials.RAW_BRILLIANCE.get()), RecipeCategory.MISC,
                Pair.of("has_brilliance", RecipeDatagenCommons.has(MalumContent.Materials.RAW_BRILLIANCE.get())),
                MalumContent.Materials.REFINED_BRILLIANCE.get(), 2, 1f
        );
        smeltAndBlast(output, malumPath("brilliance_crushed"), Ingredient.of(MalumContent.Materials.CRUSHED_BRILLIANCE.get()), RecipeCategory.MISC,
                Pair.of("has_brilliance", RecipeDatagenCommons.has(MalumContent.Materials.RAW_BRILLIANCE.get())),
                MalumContent.Materials.REFINED_BRILLIANCE.get(), 2, 1f
        );
        smeltAndBlast(output, malumPath("raw_soulstone"), Ingredient.of(MalumContent.Materials.RAW_SOULSTONE.get()), RecipeCategory.MISC,
                Pair.of("has_soulstone", hasSoulstone),
                MalumContent.Materials.REFINED_SOULSTONE.get(), 2, 0.25f
        );
        smeltAndBlast(output, malumPath("soulstone_crushed"), Ingredient.of(MalumContent.Materials.CRUSHED_SOULSTONE.get()), RecipeCategory.MISC,
                Pair.of("has_soulstone", hasSoulstone),
                MalumContent.Materials.REFINED_SOULSTONE.get(), 2, 0.25f
        );

        //FULL BLOCKS
        RecipeDatagenCommons.blockIngotExchange(output, MalumContent.Materials.RAW_SOULSTONE, MalumItemProperties.BLOCK_OF_RAW_SOULSTONE);
        RecipeDatagenCommons.blockIngotExchange(output, MalumContent.Materials.REFINED_SOULSTONE, MalumItemProperties.BLOCK_OF_SOULSTONE);
        RecipeDatagenCommons.blockIngotExchange(output, MalumContent.Materials.RAW_BRILLIANCE, MalumItemProperties.BLOCK_OF_RAW_BRILLIANCE);
        RecipeDatagenCommons.blockIngotExchange(output, MalumContent.Materials.REFINED_BRILLIANCE, MalumItemProperties.BLOCK_OF_BRILLIANCE);
        RecipeDatagenCommons.blockIngotExchange(output, MalumItemProperties.BLAZING_QUARTZ, MalumItemProperties.BLOCK_OF_BLAZING_QUARTZ);
        RecipeDatagenCommons.blockIngotExchange(output, MalumItemProperties.NATURAL_QUARTZ, MalumItemProperties.BLOCK_OF_NATURAL_QUARTZ);
        RecipeDatagenCommons.blockIngotExchange(output, MalumContent.Materials.CTHONIC_GOLD, MalumItemProperties.BLOCK_OF_CTHONIC_GOLD);

        RecipeDatagenCommons.blockIngotExchange(output, MalumContent.Materials.ROTTING_ESSENCE, MalumItemProperties.BLOCK_OF_ROTTING_ESSENCE);
        RecipeDatagenCommons.blockIngotExchange(output, MalumContent.Materials.GRIM_TALC, MalumItemProperties.BLOCK_OF_GRIM_TALC);
        RecipeDatagenCommons.blockIngotExchange(output, MalumContent.Materials.EERIE_WEAVE, MalumItemProperties.BLOCK_OF_EERIE_WEAVE);
        RecipeDatagenCommons.blockIngotExchange(output, MalumContent.Materials.WARP_FLUX, MalumItemProperties.BLOCK_OF_WARP_FLUX);

        RecipeDatagenCommons.blockIngotExchange(output, MalumContent.Materials.WIND_NUCLEUS, MalumItemProperties.BLOCK_OF_WIND_NUCLEI);
        RecipeDatagenCommons.blockIngotExchange(output, MalumContent.Materials.PYRE_NUCLEUS, MalumItemProperties.BLOCK_OF_PYRE_NUCLEI);

        RecipeDatagenCommons.blockIngotExchange(output, MalumContent.Materials.HEX_ASH, MalumItemProperties.BLOCK_OF_HEX_ASH);
        RecipeDatagenCommons.blockIngotExchange(output, MalumContent.Materials.LIVING_FLESH, MalumItemProperties.BLOCK_OF_LIVING_FLESH);
        RecipeDatagenCommons.blockIngotExchange(output, MalumContent.Materials.ALCHEMICAL_CALX, MalumItemProperties.BLOCK_OF_ALCHEMICAL_CALX);
        RecipeDatagenCommons.blockIngotExchange(output, MalumContent.Materials.ARCANE_CHARCOAL, MalumItemProperties.BLOCK_OF_ARCANE_CHARCOAL);

        RecipeDatagenCommons.blockIngotExchange(output, MalumContent.Materials.EBONY, MalumItemProperties.BLOCK_OF_EBONY);
        RecipeDatagenCommons.blockIngotExchange(output, MalumItemProperties.WITCHHAZEL, MalumItemProperties.CRATE_OF_WITCHHAZEL);

        RecipeDatagenCommons.blockIngotExchange(output, MalumContent.Materials.NULL_SLATE, MalumItemProperties.BLOCK_OF_NULL_SLATE);
        RecipeDatagenCommons.blockIngotExchange(output, MalumContent.Materials.VOID_SALTS, MalumItemProperties.BLOCK_OF_VOID_SALTS);
        RecipeDatagenCommons.blockIngotExchange(output, MalumContent.Materials.MNEMONIC_FRAGMENT, MalumItemProperties.BLOCK_OF_MNEMONIC_FRAGMENT);
        RecipeDatagenCommons.blockIngotExchange(output, MalumContent.Materials.MALIGNANT_LEAD, MalumItemProperties.BLOCK_OF_MALIGNANT_LEAD);
        RecipeDatagenCommons.blockIngotExchange(output, MalumContent.Materials.AURIC_EMBERS, MalumItemProperties.BLOCK_OF_AURIC_EMBERS);

        //MISC
        shaped(RecipeCategory.MISC, net.minecraft.world.item.Items.NETHERRACK, 2).define('Z', MalumItemProperties.BLAZING_QUARTZ.get()).define('Y', Tags.Items.COBBLESTONES).pattern("ZY").pattern("YZ").unlockedBy("has_blazing_quartz", RecipeDatagenCommons.has(MalumItemProperties.BLAZING_QUARTZ.get())).save(output, malumPath("netherrack_from_blazing_quartz"));
        shapeless(RecipeCategory.MISC, net.minecraft.world.item.Items.EXPERIENCE_BOTTLE).requires(MalumContent.Materials.REFINED_BRILLIANCE.get()).requires(net.minecraft.world.item.Items.GLASS_BOTTLE).unlockedBy("has_brilliance", RecipeDatagenCommons.has(MalumContent.Materials.REFINED_BRILLIANCE.get())).save(output, malumPath("experience_bottle_from_brilliance"));

        shapeless(RecipeCategory.MISC, net.minecraft.world.item.Items.BONE_MEAL, 6).requires(MalumContent.Materials.GRIM_TALC.get()).unlockedBy("has_grim_talc", RecipeDatagenCommons.has(MalumContent.Materials.GRIM_TALC.get())).save(output, malumPath("bonemeal_from_grim_talc"));
        shaped(RecipeCategory.MISC, net.minecraft.world.item.Items.SKELETON_SKULL).define('#', MalumContent.Materials.GRIM_TALC.get()).define('&', Tags.Items.BONES).pattern("&&&").pattern("&#&").pattern("&&&").unlockedBy("has_grim_talc", RecipeDatagenCommons.has(MalumContent.Materials.GRIM_TALC.get())).save(output, malumPath("skeleton_skull_from_grim_talc"));
        shaped(RecipeCategory.MISC, net.minecraft.world.item.Items.ZOMBIE_HEAD).define('#', MalumContent.Materials.GRIM_TALC.get()).define('&', net.minecraft.world.item.Items.ROTTEN_FLESH).pattern("&&&").pattern("&#&").pattern("&&&").unlockedBy("has_grim_talc", RecipeDatagenCommons.has(MalumContent.Materials.GRIM_TALC.get())).save(output, malumPath("zombie_head_from_grim_talc"));

        shaped(RecipeCategory.MISC, net.minecraft.world.item.Items.TORCH, 6).define('#', MalumItemProperties.BLAZING_QUARTZ.get()).define('&', net.minecraft.world.item.Items.STICK).pattern("#").pattern("&").unlockedBy("has_blazing_quartz", RecipeDatagenCommons.has(MalumItemProperties.BLAZING_QUARTZ.get())).save(output, malumPath("torch_from_blazing_quartz"));

        //THE DEVICE
        shaped(RecipeCategory.MISC, MalumItemProperties.THE_DEVICE.get()).define('X', MalumItemProperties.TWISTED_ROCK.get()).define('Y', MalumItemProperties.TAINTED_ROCK.get()).pattern("XYX").pattern("YXY").pattern("XYX").unlockedBy("has_bedrock", RecipeDatagenCommons.has(net.minecraft.world.item.Items.BEDROCK)).save(output);


        //WEAVES
        weaveRecipe(output, MalumItemProperties.BLIGHTED_GUNK.get(), MalumItemProperties.ANCIENT_WEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.IRON_INGOT, MalumItemProperties.CORNERED_WEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.LAPIS_LAZULI, MalumItemProperties.MECHANICAL_WEAVE_V1);
        weaveRecipe(output, net.minecraft.world.item.Items.REDSTONE, MalumItemProperties.MECHANICAL_WEAVE_V2);

        weaveRecipe(output, net.minecraft.world.item.Items.BREAD, MalumItemProperties.ACE_PRIDEWEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.BOOK, MalumItemProperties.AGENDER_PRIDEWEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.ARROW, MalumItemProperties.ARO_PRIDEWEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.WHEAT_SEEDS, MalumItemProperties.AROACE_PRIDEWEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.WHEAT, MalumItemProperties.BI_PRIDEWEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.RAW_IRON, MalumItemProperties.DEMIBOY_PRIDEWEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.RAW_COPPER, MalumItemProperties.DEMIGIRL_PRIDEWEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.MOSS_BLOCK, MalumItemProperties.ENBY_PRIDEWEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.MELON_SLICE, MalumItemProperties.GAY_PRIDEWEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.WATER_BUCKET, MalumItemProperties.GENDERFLUID_PRIDEWEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.GLASS_BOTTLE, MalumItemProperties.GENDERQUEER_PRIDEWEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.AZALEA, MalumItemProperties.INTERSEX_PRIDEWEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.HONEYCOMB, MalumItemProperties.LESBIAN_PRIDEWEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.CARROT, MalumItemProperties.PAN_PRIDEWEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.REPEATER, MalumItemProperties.PLURAL_PRIDEWEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.COMPARATOR, MalumItemProperties.POLY_PRIDEWEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.STONE_BRICK_WALL, MalumItemProperties.PRIDE_PRIDEWEAVE);
        weaveRecipe(output, net.minecraft.world.item.Items.EGG, MalumItemProperties.TRANS_PRIDEWEAVE);
    }

    protected static void bannerRecipe(RecipeOutput consumer, Item material, SoulwovenBannerPatternDataComponent pattern) {
        shapeless(RecipeCategory.BUILDING_BLOCKS, pattern.getDefaultStack()).requires(MalumItemProperties.SOULWOVEN_BANNER.get()).requires(material).unlockedBy("has_soulwoven_silk", RecipeDatagenCommons.has(MalumContent.Materials.SOULWOVEN_SILK.get())).save(consumer, pattern.getRecipeId());
    }

    protected static void weaveRecipe(RecipeOutput consumer, Item sideItem, Supplier<? extends Item> output) {
        shapeless(RecipeCategory.MISC, output.get()).requires(MalumItemProperties.ESOTERIC_SPOOL.get()).requires(sideItem).unlockedBy("has_spool", RecipeDatagenCommons.has(MalumItemProperties.ESOTERIC_SPOOL.get())).save(consumer);
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
                .unlockedBy("has_ether", RecipeDatagenCommons.has(MalumItemProperties.ETHER.get()))
                .save(recipeoutput, id + "_crafting");
    }

    protected static void etherCandle(RecipeOutput recipeoutput, ItemLike output, ItemLike ether) {
        var id = BuiltInRegistries.ITEM.getKey(output.asItem()).getPath();
        new NBTCarryRecipeBuilder(RecipeCategory.BUILDING_BLOCKS, new ItemStack(output.asItem(), 2), Ingredient.of(ether))
                .define('X', ether)
                .define('Y', net.minecraft.world.item.Items.HONEYCOMB)
                .pattern("X").pattern("Y")
                .unlockedBy("has_ether", RecipeDatagenCommons.has(MalumItemProperties.ETHER.get()))
                .save(recipeoutput, id + "_crafting");
    }

    protected static void etherBrazier(RecipeOutput recipeoutput, ItemLike output, ItemLike rock, ItemLike ether) {
        new NBTCarryRecipeBuilder(RecipeCategory.BUILDING_BLOCKS, new ItemStack(output.asItem(), 2), Ingredient.of(ether))
                .define('X', ether)
                .define('Y', rock)
                .pattern("X").pattern("Y")
                .unlockedBy("has_ether", RecipeDatagenCommons.has(MalumItemProperties.ETHER.get()))
                .save(recipeoutput, BuiltInRegistries.ITEM.getKey(output.asItem()).getPath());
    }


    protected static void etherCresset(RecipeOutput recipeoutput, ItemLike output, ItemLike rock, ItemLike ether) {
        new NBTCarryRecipeBuilder(RecipeCategory.BUILDING_BLOCKS, new ItemStack(output.asItem(), 2), Ingredient.of(ether))
                .define('X', ether)
                .define('Y', rock)
                .pattern("X").pattern("Y").pattern("Y")
                .unlockedBy("has_ether", RecipeDatagenCommons.has(MalumItemProperties.ETHER.get()))
                .save(recipeoutput, BuiltInRegistries.ITEM.getKey(output.asItem()).getPath());
    }
}
