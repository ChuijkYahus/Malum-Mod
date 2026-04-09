package com.sammy.malum.datagen.tag;

import com.sammy.malum.*;
import com.sammy.malum.common.item.augment.*;
import com.sammy.malum.common.item.augment.core.CoreAugmentItem;
import com.sammy.malum.common.item.curiosities.armor.*;
import com.sammy.malum.common.item.curiosities.curios.*;
import com.sammy.malum.common.item.curiosities.curios.runes.*;
import com.sammy.malum.common.item.curiosities.curios.runes.madness.*;
import com.sammy.malum.common.item.curiosities.curios.runes.miracle.*;
import com.sammy.malum.common.item.impetus.*;
import com.sammy.malum.common.item.metallics.FracturedMetalImpetusItem;
import com.sammy.malum.common.item.metallics.MetalNodeItem;
import com.sammy.malum.datagen.recipe.crafting.*;
import com.sammy.malum.registry.common.content.MalumContent;
import net.minecraft.core.*;
import net.minecraft.data.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.common.*;
import net.neoforged.neoforge.common.data.*;
import net.neoforged.neoforge.registries.*;
import org.jetbrains.annotations.*;
import team.lodestar.lodestone.modules.datagen.providers.tag.LodestoneItemTagsSystem;

import java.util.HashSet;
import java.util.concurrent.*;

import static com.sammy.malum.registry.common.MalumTags.Items.*;
import static com.sammy.malum.registry.common.content.block.MalumBlocks.BLOCKS;
import static com.sammy.malum.registry.common.content.item.MalumItemProperties.*;
import static net.minecraft.world.item.Items.*;
import static team.lodestar.lodestone.registry.common.tag.LodestoneItemTags.*;

@SuppressWarnings("unchecked")
public class MalumItemTagDatagen extends LodestoneItemTagsSystem {

    public MalumItemTagDatagen(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, MalumMod.MALUM, existingFileHelper);
    }

    @Override
    public String getName() {
        return "Malum Item Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        var blocks = new HashSet<>(BLOCKS.getEntries());
        var items = ITEMS.getEntries();
        MalumWoodSetDatagen.addTags(this);
        addTagsFromBlockProperties(blocks);


        tag(ItemTags.BOOKSHELF_BOOKS).add(MalumContent.ENCYCLOPEDIA_ARCANA.get(), MalumContent.ENCYCLOPEDIA_ESOTERICA.get());

        tag(Tags.Items.GEMS).add(MalumContent.Materials.REFINED_SOULSTONE.get(), BLAZING_QUARTZ.get());
        tag(ItemTags.LOGS).addTag(RUNEWOOD_LOGS).addTag(SOULWOOD_LOGS);
        tag(Tags.Items.SLIME_BALLS).add(MalumContent.Materials.RUNIC_SAPBALL.get(), MalumContent.Materials.CURSED_SAPBALL.get());
        tag(Tags.Items.GEMS_QUARTZ).add(NATURAL_QUARTZ.get());

        tag(Tags.Items.RAW_MATERIALS).add(MalumContent.Materials.RAW_SOULSTONE.get(), MalumContent.Materials.RAW_BRILLIANCE.get(), MalumContent.Materials.CTHONIC_GOLD.get(), CTHONIC_GOLD_FRAGMENT.get());
        tag(Tags.Items.NUGGETS).add(MalumContent.Materials.HALLOWED_GOLD_NUGGET.get(), MalumContent.Materials.SOUL_STAINED_STEEL_NUGGET.get(), MalumContent.Materials.MALIGNANT_PEWTER_NUGGET.get());
        tag(Tags.Items.INGOTS).add(MalumContent.Materials.HALLOWED_GOLD_INGOT.get(), MalumContent.Materials.SOUL_STAINED_STEEL_INGOT.get(), MalumContent.Materials.MALIGNANT_PEWTER_INGOT.get());
        tag(Tags.Items.GEMS).add(NATURAL_QUARTZ.get(), BLAZING_QUARTZ.get(), MalumContent.Materials.RAW_BRILLIANCE.get());
        tag(Tags.Items.NUGGETS).addOptional(MalumMod.malumPath("copper_nugget"));
        tag(NUGGETS_COPPER).addOptional(MalumMod.malumPath("copper_nugget"));

        tag(ItemTags.DYEABLE).add(
                ETHER.get(), ETHER_CANDLE.get(), IRIDESCENT_ETHER.get(),
                ETHER_TORCH.get(), IRIDESCENT_ETHER_CANDLE.get(), IRIDESCENT_ETHER_TORCH.get(),
                TAINTED_ETHER_BRAZIER.get(), TWISTED_ETHER_BRAZIER.get(), DROSS_ETHER_BRAZIER.get(),
                TAINTED_IRIDESCENT_ETHER_BRAZIER.get(), TWISTED_IRIDESCENT_ETHER_BRAZIER.get(), DROSS_IRIDESCENT_ETHER_BRAZIER.get(),
                TAINTED_ETHER_CRESSET.get(), TWISTED_ETHER_CRESSET.get(), DROSS_ETHER_CRESSET.get(),
                TAINTED_IRIDESCENT_ETHER_CRESSET.get(), TWISTED_IRIDESCENT_ETHER_CRESSET.get(), DROSS_IRIDESCENT_ETHER_CRESSET.get());

        tag(SOUL_SHATTER_CAPABLE_WEAPON)
                .addTags(SCYTHES, STAVES)
                .add(MalumContent.SHAPED_SLAB.get(), MalumContent.BROKEN_BLADE.get())
                .add(MalumContent.Gear.TYRVING.get(), MalumContent.Gear.WEIGHT_OF_WORLDS.get(), MalumContent.Gear.SUNDERING_ANCHOR.get())
                .add(MalumContent.Gear.SOUL_STAINED_STEEL_AXE.get(), MalumContent.Gear.SOUL_STAINED_STEEL_PICKAXE.get(), MalumContent.Gear.SOUL_STAINED_STEEL_SHOVEL.get(), MalumContent.Gear.SOUL_STAINED_STEEL_SWORD.get(), MalumContent.Gear.SOUL_STAINED_STEEL_HOE.get(), MalumContent.Gear.SOUL_STAINED_STEEL_KNIFE.get())
                .add(MalumContent.Gear.SPELLWEAVING_PICKAXE.get(), MalumContent.Gear.SPELLWEAVING_AXE.get());

        tag(MAGIC_CAPABLE_WEAPON)
                .addTags(SCYTHES, STAVES)
                .add(MalumContent.Gear.SOUL_STAINED_STEEL_SWORD.get(), MalumContent.Gear.SOUL_STAINED_STEEL_KNIFE.get())
                .add(MalumContent.Gear.GLUTTONOUS_BLUDGEON.get(), MalumContent.Gear.TYRVING.get(), MalumContent.Gear.SUNDERING_ANCHOR.get())
                .remove(MalumContent.Gear.EDGE_OF_DELIVERANCE.get());

        tag(SCYTHES)
                .add(MalumContent.Gear.CRUDE_SCYTHE.get(), MalumContent.Gear.SOUL_STAINED_STEEL_SCYTHE.get(), MalumContent.Gear.RAVENOUS_SCYTHE.get(), MalumContent.Gear.EDGE_OF_DELIVERANCE.get());
        tag(SCYTHES_COMMON).addTag(SCYTHES);

        tag(STAVES)
                .add(MalumContent.Gear.MNEMONIC_HEX_STAFF.get(), MalumContent.Gear.UNWINDING_CHAOS.get(), MalumContent.Gear.EROSION_SCEPTER.get());
        tag(STAVES_COMMON).addTag(STAVES);

        tag(Tags.Items.MELEE_WEAPON_TOOLS).add(
                MalumContent.Gear.CRUDE_SCYTHE.get(), MalumContent.Gear.SOUL_STAINED_STEEL_SCYTHE.get(), MalumContent.Gear.RAVENOUS_SCYTHE.get(), MalumContent.Gear.EDGE_OF_DELIVERANCE.get(),
                MalumContent.Gear.SOUL_STAINED_STEEL_KNIFE.get(), MalumContent.Gear.SOUL_STAINED_STEEL_SWORD.get(), MalumContent.Gear.SOUL_STAINED_STEEL_AXE.get(),
                MalumContent.Gear.GLUTTONOUS_BLUDGEON.get(), MalumContent.Gear.TYRVING.get(),
                MalumContent.Gear.MNEMONIC_HEX_STAFF.get(), MalumContent.Gear.EROSION_SCEPTER.get(), MalumContent.Gear.UNWINDING_CHAOS.get(),
                MalumContent.Gear.SUNDERING_ANCHOR.get());
        tag(Tags.Items.RANGED_WEAPON_TOOLS).add(MalumContent.Gear.MNEMONIC_HEX_STAFF.get(), MalumContent.Gear.EROSION_SCEPTER.get(), MalumContent.Gear.UNWINDING_CHAOS.get(), MalumContent.Gear.SUNDERING_ANCHOR.get());
        tag(ItemTags.SWORDS).add(MalumContent.Gear.SOUL_STAINED_STEEL_SWORD.get(), MalumContent.Gear.TYRVING.get());
        tag(ItemTags.PICKAXES).add(MalumContent.Gear.SOUL_STAINED_STEEL_PICKAXE.get(), MalumContent.Gear.SPELLWEAVING_PICKAXE.get());
        tag(ItemTags.AXES).add(MalumContent.Gear.SOUL_STAINED_STEEL_AXE.get(), MalumContent.Gear.SPELLWEAVING_AXE.get(), MalumContent.Gear.WEIGHT_OF_WORLDS.get());
        tag(ItemTags.SHOVELS).add(MalumContent.Gear.SOUL_STAINED_STEEL_SHOVEL.get());
        tag(ItemTags.HOES).add(MalumContent.Gear.SOUL_STAINED_STEEL_HOE.get());
        tag(KNIVES_COMMON).add(MalumContent.Gear.SOUL_STAINED_STEEL_KNIFE.get(), MalumContent.Gear.SUNDERING_ANCHOR.get());
        tag(KNIVES).add(MalumContent.Gear.SOUL_STAINED_STEEL_KNIFE.get(), MalumContent.Gear.SUNDERING_ANCHOR.get());

        tag(ItemTags.HEAD_ARMOR).add(MalumContent.Gear.SOUL_HUNTER_CLOAK.get(), MalumContent.Gear.SOUL_STAINED_STEEL_HELMET.get(), MalumContent.Gear.MALIGNANT_STRONGHOLD_HELMET.get());
        tag(ItemTags.CHEST_ARMOR).add(MalumContent.Gear.SOUL_HUNTER_ROBE.get(), MalumContent.Gear.SOUL_STAINED_STEEL_CHESTPLATE.get(), MalumContent.Gear.MALIGNANT_STRONGHOLD_CHESTPLATE.get());
        tag(ItemTags.LEG_ARMOR).add(MalumContent.Gear.SOUL_HUNTER_LEGGINGS.get(), MalumContent.Gear.SOUL_STAINED_STEEL_LEGGINGS.get(), MalumContent.Gear.MALIGNANT_STRONGHOLD_LEGGINGS.get());
        tag(ItemTags.FOOT_ARMOR).add(MalumContent.Gear.SOUL_HUNTER_BOOTS.get(), MalumContent.Gear.SOUL_STAINED_STEEL_BOOTS.get(), MalumContent.Gear.MALIGNANT_STRONGHOLD_BOOTS.get());

        tag(MELEE_ENCHANTABLE).addTags(SCYTHE_ENCHANTABLE, STAFF_ENCHANTABLE).add(MalumContent.Gear.GLUTTONOUS_BLUDGEON.get());
        tag(ItemTags.FIRE_ASPECT_ENCHANTABLE).addTags(SCYTHES);

        tag(HAUNTED_ENCHANTABLE).addTag(MAGIC_CAPABLE_WEAPON);
        tag(ANIMATED_ENCHANTABLE).addTag(MAGIC_CAPABLE_WEAPON);
        tag(SPIRIT_PLUNDER_ENCHANTABLE).addTag(SOUL_SHATTER_CAPABLE_WEAPON);

        tag(SCYTHE_ENCHANTABLE).addTag(SCYTHES);
        tag(REBOUND_ENCHANTABLE).addTag(SCYTHE_ENCHANTABLE);
        tag(ASCENSION_ENCHANTABLE).addTag(SCYTHE_ENCHANTABLE);

        tag(SPELLWEAVING_ENCHANTABLE).add(MalumContent.Gear.SPELLWEAVING_PICKAXE.get(), MalumContent.Gear.SPELLWEAVING_AXE.get());
        tag(WEAVERS_PROPAGATION_ENCHANTABLE).addTag(SPELLWEAVING_ENCHANTABLE);
        tag(WEAVERS_HASTE_ENCHANTABLE).addTag(SPELLWEAVING_ENCHANTABLE);

        tag(STAFF_ENCHANTABLE).addTag(STAVES);
        tag(REPLENISHING_ENCHANTABLE).addTag(STAFF_ENCHANTABLE);
        tag(CAPACITOR_ENCHANTABLE).addTag(STAFF_ENCHANTABLE);


        tag(ItemTags.DURABILITY_ENCHANTABLE).addTags(SCYTHES, STAVES)
                .add(MalumContent.Gear.GLUTTONOUS_BLUDGEON.get())
                .add(MalumContent.Gear.CATALYST_LOBBER.get(), MalumContent.Gear.SUNDERING_ANCHOR.get());


        tag(ASPECTED_SPIRITS).add(
                MalumContent.Materials.SACRED_SPIRIT.get(), MalumContent.Materials.WICKED_SPIRIT.get(), MalumContent.Materials.ARCANE_SPIRIT.get(), MalumContent.Materials.ELDRITCH_SPIRIT.get(),
                MalumContent.Materials.AERIAL_SPIRIT.get(), MalumContent.Materials.AQUEOUS_SPIRIT.get(), MalumContent.Materials.EARTHEN_SPIRIT.get(), MalumContent.Materials.INFERNAL_SPIRIT.get());
        tag(SPIRITS).addTag(ASPECTED_SPIRITS).add(MalumContent.Materials.UMBRAL_SPIRIT.get());
        tag(MOB_DROPS).add(
                MalumContent.Materials.ROTTING_ESSENCE.get(), MalumContent.Materials.GRIM_TALC.get(), MalumContent.Materials.EERIE_WEAVE.get(), MalumContent.Materials.WARP_FLUX.get(),
                ROTTEN_FLESH, SPIDER_EYE, BONE, ARROW, GUNPOWDER, STRING, SLIME_BALL,
                MAGMA_CREAM, BLAZE_ROD, BREEZE_ROD,
                LEATHER, RABBIT_HIDE, FEATHER, INK_SAC);
        tag(MATERIALS).add(
                MalumContent.Materials.ROTTING_ESSENCE.get(), MalumContent.Materials.GRIM_TALC.get(), MalumContent.Materials.EERIE_WEAVE.get(), MalumContent.Materials.WARP_FLUX.get(),
                MalumContent.Materials.HEX_ASH.get(), MalumContent.Materials.LIVING_FLESH.get(), MalumContent.Materials.ALCHEMICAL_CALX.get(), BLIGHTED_GUNK.get(),
                MalumContent.Materials.SOULWOVEN_SILK.get(), ETHER.get(), IRIDESCENT_ETHER.get(),
                MalumContent.Materials.SOUL_STAINED_STEEL_INGOT.get(), MalumContent.Materials.SOUL_STAINED_STEEL_NUGGET.get(), MalumContent.Materials.SOUL_STAINED_STEEL_PLATING.get(),
                MalumContent.Materials.HALLOWED_GOLD_INGOT.get(), MalumContent.Materials.HALLOWED_GOLD_NUGGET.get(),
                MalumContent.Materials.MALIGNANT_PEWTER_INGOT.get(), MalumContent.Materials.MALIGNANT_PEWTER_NUGGET.get(), MalumContent.Materials.MALIGNANT_PEWTER_PLATING.get(),
                MalumContent.Materials.NULL_SLATE.get(), MalumContent.Materials.VOID_SALTS.get(), MalumContent.Materials.MNEMONIC_FRAGMENT.get(), MalumContent.Materials.AURIC_EMBERS.get(), MalumContent.Materials.MALIGNANT_LEAD.get(),
                MalumContent.Materials.ANOMALOUS_DESIGN.get(), MalumContent.Materials.COMPLETE_DESIGN.get(), MalumContent.Materials.FUSED_CONSCIOUSNESS.get());
        tag(MINERALS).add(
                MalumContent.Materials.RAW_SOULSTONE.get(), MalumContent.Materials.CRUSHED_SOULSTONE.get(), MalumContent.Materials.REFINED_SOULSTONE.get(),
                MalumContent.Materials.RAW_BRILLIANCE.get(), MalumContent.Materials.CRUSHED_BRILLIANCE.get(), MalumContent.Materials.REFINED_BRILLIANCE.get(),
                BLAZING_QUARTZ.get(),
                NATURAL_QUARTZ.get(), MalumContent.Materials.CTHONIC_GOLD.get(), CTHONIC_GOLD_FRAGMENT.get());

        tag(AUGMENTS, AugmentItem.class);
        tag(CORE_AUGMENTS, CoreAugmentItem.class);
        tag(METAL_NODES, MetalNodeItem.class);

        tag(IMPETUS, ImpetusItem.class);
        tag(METAL_IMPETUS, ImpetusItem.class).remove(MalumContent.Progression.ALCHEMICAL_IMPETUS.get(), MalumContent.Progression.ZEPHYR_IMPETUS.get());

        tag(FRACTURED_IMPETUS, FracturedImpetusItem.class);
        tag(FRACTURED_METAL_IMPETUS, FracturedMetalImpetusItem.class).addTag(FRACTURED_IMPETUS);

        tag(ARMORS, MalumArmorItem.class);

        tag(RUNES_STONE, MiracleRuneCurioItem.class);
        tag(RUNES_VOID, MadnessRuneCurioItem.class);
        tag(RUNES_WOODEN, TotemicRuneCurioItem.class);

        tag(IS_TOTEMIC_TOOL).add(MalumContent.Progression.TOTEMIC_STAFF.get());
        tag(IS_REDSTONE_TOOL).add(MalumContent.Progression.ARTIFICERS_CLAW.get()).addOptional(ResourceLocation.parse("create:wrench"));
        tag(IS_ARTIFICE_TOOL).add(MalumContent.Progression.TUNING_FORK.get()).addOptional(ResourceLocation.parse("create:wrench"));
        tag(COUNTS_AS_EMPTY_HAND).addOptional(ResourceLocation.parse("mowziesmobs:earthrend_gauntlet"));

        tag(SAPBALLS).add(MalumContent.Materials.RUNIC_SAPBALL.get(), MalumContent.Materials.CURSED_SAPBALL.get());
        tag(GROSS_FOODS).add(ROTTEN_FLESH, MalumContent.Materials.ROTTING_ESSENCE.get(), MalumContent.Gear.CONCENTRATED_GLUTTONY.get());

        tag(PROSPECTORS_TREASURE)
                .addTags(Tags.Items.ORES, Tags.Items.STORAGE_BLOCKS, Tags.Items.INGOTS, Tags.Items.NUGGETS, Tags.Items.GEMS, Tags.Items.RAW_MATERIALS, ItemTags.COALS, METAL_NODES)
                .addOptional(ResourceLocation.parse("tetra:geode"));

        tag(SOULWOVEN_POUCH_EFFICIENT)
                .addTags(SPIRITS);
        tag(SOULWOVEN_POUCH_AUTOCOLLECT)
                .addTags(SPIRITS);

        tag(ARCANE_ELEGY_COMPONENTS).addTag(Tags.Items.MUSIC_DISCS).remove(MalumContent.ARCANE_ELEGY.get(), MalumContent.AESTHETICA.get());

        tag(VOID_SOULSTONE_CONVERSION)
                .addTags(Tags.Items.RAW_MATERIALS)
                .remove(MalumContent.Materials.RAW_SOULSTONE.get(), MalumContent.Materials.RAW_BRILLIANCE.get(), MalumContent.Materials.CTHONIC_GOLD.get(), CTHONIC_GOLD_FRAGMENT.get());

        tag(HIDDEN_ALWAYS).add(THE_DEVICE.get(), THE_VESSEL.get());

        tag(HIDDEN_UNTIL_VOID)
                .addTag(HIDDEN_UNTIL_BLACK_CRYSTAL)
                // The Well
                .add(PRIMORDIAL_SOUP.get())
                // Encyclopedia
                .add(MalumContent.ENCYCLOPEDIA_ESOTERICA.get())
                // Equipment
                .add(MalumContent.Gear.CATALYST_LOBBER.get())
                // Decor
                .add(NULL_SPIRITED_GLASS.get(), NULL_VARNISHED_TERRACOTTA.get())
                // Materials
                .add(BLOCK_OF_NULL_SLATE.get(), MalumContent.Materials.NULL_SLATE.get(),
                        BLOCK_OF_VOID_SALTS.get(), MalumContent.Materials.VOID_SALTS.get(),
                        BLOCK_OF_MNEMONIC_FRAGMENT.get(), MalumContent.Materials.MNEMONIC_FRAGMENT.get(),
                        BLOCK_OF_AURIC_EMBERS.get(), MalumContent.Materials.AURIC_EMBERS.get(),
                        BLOCK_OF_MALIGNANT_LEAD.get(), MalumContent.Materials.MALIGNANT_LEAD.get());

        tag(HIDDEN_UNTIL_BLACK_CRYSTAL)
                // Umbral Spirit
                .add(MalumContent.Materials.UMBRAL_SPIRIT.get())
                // Anomalous Design
                .add(MalumContent.Materials.ANOMALOUS_DESIGN.get(), MalumContent.Materials.COMPLETE_DESIGN.get(), MalumContent.Materials.FUSED_CONSCIOUSNESS.get())
                // Malignant Pewter
                .add(MalumContent.Materials.MALIGNANT_PEWTER_INGOT.get(), MalumContent.Materials.MALIGNANT_PEWTER_PLATING.get(),
                        MalumContent.Materials.MALIGNANT_PEWTER_NUGGET.get(), BLOCK_OF_MALIGNANT_PEWTER.get())
                // Equipment
                .add(MalumContent.Gear.MALIGNANT_STRONGHOLD_HELMET.get(), MalumContent.Gear.MALIGNANT_STRONGHOLD_CHESTPLATE.get(),
                        MalumContent.Gear.MALIGNANT_STRONGHOLD_LEGGINGS.get(), MalumContent.Gear.MALIGNANT_STRONGHOLD_BOOTS.get(),
                        MalumContent.Gear.WEIGHT_OF_WORLDS.get(), MalumContent.Gear.EDGE_OF_DELIVERANCE.get(),
                        MalumContent.Gear.MNEMONIC_HEX_STAFF.get(), MalumContent.Gear.EROSION_SCEPTER.get(),
                        MalumContent.Gear.UNWINDING_CHAOS.get(), MalumContent.Gear.SUNDERING_ANCHOR.get())
                // Runes
                .add(MalumContent.Gear.RUNE_OF_BOLSTERING.get(), MalumContent.Gear.RUNE_OF_RADIAL_EMPOWERMENT.get(),
                        MalumContent.Gear.RUNE_OF_SPELL_MASTERY.get(), MalumContent.Gear.RUNE_OF_HERESY.get(),
                        MalumContent.Gear.RUNE_OF_UNNATURAL_STAMINA.get(), MalumContent.Gear.RUNE_OF_TWINNED_DURATION.get(),
                        MalumContent.Gear.RUNE_OF_INDOMITABILITY.get(), MalumContent.Gear.RUNE_OF_IGNEOUS_SOLACE.get())
                // Trinkets
                .add(MalumContent.Gear.RING_OF_THE_ENDLESS_WELL.get(), MalumContent.Gear.RING_OF_GROWING_FLESH.get(), MalumContent.Gear.RING_OF_ECHOING_ARCANA.get(),
                        MalumContent.Gear.RING_OF_GRUESOME_CONCENTRATION.get(), MalumContent.Gear.NECKLACE_OF_THE_HIDDEN_BLADE.get(),
                        MalumContent.Gear.NECKLACE_OF_THE_WATCHER.get(), MalumContent.Gear.BELT_OF_THE_LIMITLESS.get())
                // Augments
                .add(MalumContent.Progression.STELLAR_MECHANISM.get())
                // Aesthetica
                .add(MalumContent.AESTHETICA.get());

        tag(HIDDEN_AS_RESULT_ONLY).add(MalumContent.Gear.WEIGHT_OF_WORLDS.get(), MalumContent.Gear.EDGE_OF_DELIVERANCE.get(), MalumContent.Gear.SUNDERING_ANCHOR.get());

        for (DeferredHolder<net.minecraft.world.item.Item, ? extends net.minecraft.world.item.Item> i : items) {
            if (i.get() instanceof MalumCurioItem) {
                final net.minecraft.world.item.Item item = i.get();
                final ResourceLocation id = i.getId();
                if (id.getPath().contains("_ring") || id.getPath().contains("ring_")) {
                    tag(RING_CURIO).add(item);
                    continue;
                }
                if (id.getPath().contains("_necklace") || id.getPath().contains("necklace_")) {
                    tag(NECKLACE_CURIO).add(item);
                    continue;
                }
                if (id.getPath().contains("_belt") || id.getPath().contains("belt_")) {
                    tag(BELT_CURIO).add(item);
                    continue;
                }
                if (id.getPath().contains("_rune") || id.getPath().contains("rune_")) {
                    tag(RUNE_CURIO).add(item);
                    continue;
                }
                if (id.getPath().contains("_brooch") || id.getPath().contains("brooch_")) {
                    tag(BROOCH_CURIO).add(item);
                }
            }
        }
        tag(CHARM_CURIO).add(TOPHAT.get(), TOKEN_OF_GRATITUDE.get());
    }

    @Override
    public IntrinsicTagAppender<Item> tag(TagKey<Item> tag) {
        return super.tag(tag);
    }

    protected IntrinsicTagAppender<Item> tag(TagKey<Item> tag, Class<? extends Item> itemClass) {
        var appender = super.tag(tag);
        for (DeferredHolder<Item, ? extends Item> entry : ITEMS.getEntries()) {
            var item = entry.get();
            if (itemClass.isInstance(item)) {
                appender.add(item);
            }
        }
        return appender;
    }
}