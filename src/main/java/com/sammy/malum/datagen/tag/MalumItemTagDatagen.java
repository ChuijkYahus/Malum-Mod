package com.sammy.malum.datagen.tag;

import com.sammy.malum.*;
import com.sammy.malum.common.item.augment.*;
import com.sammy.malum.common.item.banner.*;
import com.sammy.malum.common.item.curiosities.curios.*;
import com.sammy.malum.common.item.impetus.*;
import com.sammy.malum.datagen.recipe.crafting.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.block.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.data.*;
import net.minecraft.data.tags.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.common.*;
import net.neoforged.neoforge.common.data.*;
import net.neoforged.neoforge.registries.*;
import org.jetbrains.annotations.*;
import team.lodestar.lodestone.systems.block.*;
import team.lodestar.lodestone.systems.datagen.*;

import java.util.concurrent.*;

import static com.sammy.malum.registry.common.item.MalumItems.*;
import static team.lodestar.lodestone.registry.common.tag.LodestoneItemTags.*;

@SuppressWarnings("unchecked")
public class MalumItemTagDatagen extends ItemTagsProvider {

    public MalumItemTagDatagen(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, MalumMod.MALUM, existingFileHelper);
    }

    @Override
    public String getName() {
        return "Malum Item Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        var items = ITEMS.getEntries();
        MalumWoodSetDatagen.addTags(this);
        MalumRockSetDatagen.addTags(this);

        copy(net.minecraft.tags.BlockTags.PLANKS, net.minecraft.tags.ItemTags.PLANKS);
        copy(net.minecraft.tags.BlockTags.WOODEN_BUTTONS, net.minecraft.tags.ItemTags.WOODEN_BUTTONS);
        copy(net.minecraft.tags.BlockTags.BUTTONS, net.minecraft.tags.ItemTags.BUTTONS);
        copy(net.minecraft.tags.BlockTags.WOODEN_DOORS, net.minecraft.tags.ItemTags.WOODEN_DOORS);
        copy(net.minecraft.tags.BlockTags.WOODEN_STAIRS, net.minecraft.tags.ItemTags.WOODEN_STAIRS);
        copy(net.minecraft.tags.BlockTags.WOODEN_SLABS, net.minecraft.tags.ItemTags.WOODEN_SLABS);
        copy(net.minecraft.tags.BlockTags.WOODEN_FENCES, net.minecraft.tags.ItemTags.WOODEN_FENCES);
        copy(net.minecraft.tags.BlockTags.WOODEN_PRESSURE_PLATES, net.minecraft.tags.ItemTags.WOODEN_PRESSURE_PLATES);
        copy(net.minecraft.tags.BlockTags.DOORS, net.minecraft.tags.ItemTags.DOORS);
        copy(net.minecraft.tags.BlockTags.SAPLINGS, net.minecraft.tags.ItemTags.SAPLINGS);
        copy(MalumTags.BlockTags.STRIPPED_LOGS, MalumTags.ItemTags.STRIPPED_LOGS);
        copy(MalumTags.BlockTags.STRIPPED_WOODS, MalumTags.ItemTags.STRIPPED_WOODS);
        copy(net.minecraft.tags.BlockTags.SLABS, net.minecraft.tags.ItemTags.SLABS);
        copy(net.minecraft.tags.BlockTags.WALLS, net.minecraft.tags.ItemTags.WALLS);
        copy(net.minecraft.tags.BlockTags.STAIRS, net.minecraft.tags.ItemTags.STAIRS);
        copy(net.minecraft.tags.BlockTags.LEAVES, net.minecraft.tags.ItemTags.LEAVES);
        copy(net.minecraft.tags.BlockTags.WOODEN_TRAPDOORS, net.minecraft.tags.ItemTags.WOODEN_TRAPDOORS);
        copy(net.minecraft.tags.BlockTags.TRAPDOORS, net.minecraft.tags.ItemTags.TRAPDOORS);
        copy(net.minecraft.tags.BlockTags.FENCES, net.minecraft.tags.ItemTags.FENCES);
        copy(Tags.Blocks.ORES, Tags.Items.ORES);
        copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);

        tag(Tags.Items.GEMS).add(REFINED_SOULSTONE.get(), BLAZING_QUARTZ.get());
        tag(net.minecraft.tags.ItemTags.LOGS).addTag(MalumTags.ItemTags.RUNEWOOD_LOGS).addTag(MalumTags.ItemTags.SOULWOOD_LOGS);
        tag(net.minecraft.tags.ItemTags.LOGS_THAT_BURN).addTag(MalumTags.ItemTags.RUNEWOOD_LOGS).addTag(MalumTags.ItemTags.SOULWOOD_LOGS);
        tag(Tags.Items.SLIME_BALLS).add(RUNIC_SAPBALL.get(), CURSED_SAPBALL.get());
        tag(Tags.Items.GEMS_QUARTZ).add(NATURAL_QUARTZ.get());
        tag(Tags.Items.ORES_QUARTZ).add(NATURAL_QUARTZ_ORE.get(), DEEPSLATE_QUARTZ_ORE.get());

        tag(net.minecraft.tags.ItemTags.DYEABLE).add(
                ETHER.get(), ETHER_TORCH.get(), TAINTED_ETHER_BRAZIER.get(), TWISTED_ETHER_BRAZIER.get(),
                IRIDESCENT_ETHER.get(), IRIDESCENT_ETHER_TORCH.get(), TAINTED_IRIDESCENT_ETHER_BRAZIER.get(), TWISTED_IRIDESCENT_ETHER_BRAZIER.get());

        tag(MalumTags.ItemTags.MAGIC_CAPABLE_WEAPONS).add(
                CRUDE_SCYTHE.get(), SOUL_STAINED_STEEL_SCYTHE.get(),
                SOUL_STAINED_STEEL_SWORD.get(), SOUL_STAINED_STEEL_KNIFE.get(),
                MNEMONIC_HEX_STAFF.get(), UNWINDING_CHAOS.get(), EROSION_SCEPTER.get(),
                TYRVING.get(), SUNDERING_ANCHOR.get());

        tag(MalumTags.ItemTags.MAGIC_CAPABLE_WEAPONS).addOptional(ResourceLocation.parse("born_in_chaos_v1:nightmare_scythe"));

        tag(MalumTags.ItemTags.SCYTHES).add(CRUDE_SCYTHE.get(), SOUL_STAINED_STEEL_SCYTHE.get(), EDGE_OF_DELIVERANCE.get());
        tag(MalumTags.ItemTags.STAVES).add(MNEMONIC_HEX_STAFF.get(), UNWINDING_CHAOS.get(), EROSION_SCEPTER.get());

        tag(MalumTags.ItemTags.SCYTHES_COMMON).addTag(MalumTags.ItemTags.SCYTHES);
        tag(MalumTags.ItemTags.STAVES_COMMON).addTag(MalumTags.ItemTags.STAVES);

        tag(MalumTags.ItemTags.SOUL_SHATTER_CAPABLE_WEAPONS)
                .addTags(MalumTags.ItemTags.SCYTHES, MalumTags.ItemTags.STAVES)
                .add(TYRVING.get(), WEIGHT_OF_WORLDS.get(), SUNDERING_ANCHOR.get())
                .add(SOUL_STAINED_STEEL_AXE.get(), SOUL_STAINED_STEEL_PICKAXE.get(), SOUL_STAINED_STEEL_SHOVEL.get(), SOUL_STAINED_STEEL_SWORD.get(), SOUL_STAINED_STEEL_HOE.get(), SOUL_STAINED_STEEL_KNIFE.get());

        tag(MalumTags.ItemTags.SOUL_SHATTER_CAPABLE_WEAPONS)
                .addOptional(ResourceLocation.parse("irons_spellbooks:graybeard_staff"))
                .addOptional(ResourceLocation.parse("irons_spellbooks:artificer_cane"))
                .addOptional(ResourceLocation.parse("irons_spellbooks:lightning_rod"))
                .addOptional(ResourceLocation.parse("irons_spellbooks:ice_staff"))
                .addOptional(ResourceLocation.parse("irons_spellbooks:blood_staff"))
                .addOptional(ResourceLocation.parse("irons_spellbooks:magehunter"))
                .addOptional(ResourceLocation.parse("irons_spellbooks:keeper_flamberge"))
                .addOptional(ResourceLocation.parse("irons_spellbooks:spellbreaker"))
                .addOptional(ResourceLocation.parse("irons_spellbooks:amethyst_rapier"))
                .addOptional(ResourceLocation.parse("irons_spellbooks:pyrium_staff"))
                .addOptional(ResourceLocation.parse("irons_spellbooks:legionnaire_flamberge"))
                .addOptional(ResourceLocation.parse("irons_spellbooks:decrepit_scythe"))
                .addOptional(ResourceLocation.parse("irons_spellbooks:hellrazor"))
                .addOptional(ResourceLocation.parse("born_in_chaos_v1:nightmare_scythe")); //TODO: Whenever BiC updates to 1.21, check if this is still valid! -Atobá

        tag(net.minecraft.tags.ItemTags.HEAD_ARMOR).add(SOUL_HUNTER_CLOAK.get(), SOUL_STAINED_STEEL_HELMET.get(), MALIGNANT_STRONGHOLD_HELMET.get());
        tag(net.minecraft.tags.ItemTags.CHEST_ARMOR).add(SOUL_HUNTER_ROBE.get(), SOUL_STAINED_STEEL_CHESTPLATE.get(), MALIGNANT_STRONGHOLD_CHESTPLATE.get());
        tag(net.minecraft.tags.ItemTags.LEG_ARMOR).add(SOUL_HUNTER_LEGGINGS.get(), SOUL_STAINED_STEEL_LEGGINGS.get(), MALIGNANT_STRONGHOLD_LEGGINGS.get());
        tag(net.minecraft.tags.ItemTags.FOOT_ARMOR).add(SOUL_HUNTER_BOOTS.get(), SOUL_STAINED_STEEL_BOOTS.get(), MALIGNANT_STRONGHOLD_BOOTS.get());

        tag(Tags.Items.TOOLS).add(SOUL_STAINED_STEEL_KNIFE.get(), SUNDERING_ANCHOR.get());
        tag(net.minecraft.tags.ItemTags.SWORDS).add(SOUL_STAINED_STEEL_SWORD.get(), TYRVING.get());
        tag(net.minecraft.tags.ItemTags.PICKAXES).add(SOUL_STAINED_STEEL_PICKAXE.get());
        tag(net.minecraft.tags.ItemTags.AXES).add(SOUL_STAINED_STEEL_AXE.get(), WEIGHT_OF_WORLDS.get());
        tag(net.minecraft.tags.ItemTags.SHOVELS).add(SOUL_STAINED_STEEL_SHOVEL.get());
        tag(net.minecraft.tags.ItemTags.HOES).add(SOUL_STAINED_STEEL_HOE.get());

        tag(MalumTags.ItemTags.KNIVES_COMMON).add(SOUL_STAINED_STEEL_KNIFE.get(), SUNDERING_ANCHOR.get());
        tag(MalumTags.ItemTags.KNIVES).add(SOUL_STAINED_STEEL_KNIFE.get(), SUNDERING_ANCHOR.get());

        tag(MalumTags.ItemTags.REBOUND_ENCHANTABLE).addTag(MalumTags.ItemTags.SCYTHES);
        tag(MalumTags.ItemTags.ASCENSION_ENCHANTABLE).addTag(MalumTags.ItemTags.SCYTHES);

        tag(MalumTags.ItemTags.REPLENISHING_ENCHANTABLE).addTag(MalumTags.ItemTags.STAVES);
        tag(MalumTags.ItemTags.CAPACITOR_ENCHANTABLE).addTag(MalumTags.ItemTags.STAVES);

        tag(MalumTags.ItemTags.ANIMATED_ENCHANTABLE).addTag(MalumTags.ItemTags.MAGIC_CAPABLE_WEAPONS);
        tag(MalumTags.ItemTags.HAUNTED_ENCHANTABLE).addTag(MalumTags.ItemTags.MAGIC_CAPABLE_WEAPONS);
        tag(MalumTags.ItemTags.SPIRIT_SPOILS_ENCHANTABLE).addTag(MalumTags.ItemTags.SOUL_SHATTER_CAPABLE_WEAPONS);

        tag(net.minecraft.tags.ItemTags.DURABILITY_ENCHANTABLE).addTags(MalumTags.ItemTags.SCYTHES, MalumTags.ItemTags.STAVES)
                .add(CATALYST_LOBBER.get(), SUNDERING_ANCHOR.get());

        tag(MalumTags.ItemTags.ASPECTED_SPIRITS).add(
                SACRED_SPIRIT.get(), WICKED_SPIRIT.get(), ARCANE_SPIRIT.get(), ELDRITCH_SPIRIT.get(),
                AERIAL_SPIRIT.get(), AQUEOUS_SPIRIT.get(), EARTHEN_SPIRIT.get(), INFERNAL_SPIRIT.get());
        tag(MalumTags.ItemTags.SPIRITS).addTag(MalumTags.ItemTags.ASPECTED_SPIRITS).add(UMBRAL_SPIRIT.get());
        tag(MalumTags.ItemTags.MOB_DROPS).add(
                ROTTING_ESSENCE.get(), GRIM_TALC.get(), ASTRAL_WEAVE.get(), WARP_FLUX.get(),
                Items.ROTTEN_FLESH, Items.SPIDER_EYE, Items.BONE, Items.GUNPOWDER, Items.STRING, Items.SLIME_BALL,
                Items.MAGMA_CREAM, Items.BLAZE_ROD, Items.BREEZE_ROD,
                Items.LEATHER, Items.RABBIT_HIDE, Items.FEATHER, Items.INK_SAC);
        tag(MalumTags.ItemTags.MATERIALS).add(
                ROTTING_ESSENCE.get(), GRIM_TALC.get(), ASTRAL_WEAVE.get(), WARP_FLUX.get(),
                HEX_ASH.get(), LIVING_FLESH.get(), ALCHEMICAL_CALX.get(), BLIGHTED_GUNK.get(),
                SOULWOVEN_SILK.get(), ETHER.get(), IRIDESCENT_ETHER.get(),
                SOUL_STAINED_STEEL_INGOT.get(), SOUL_STAINED_STEEL_NUGGET.get(), SOUL_STAINED_STEEL_PLATING.get(),
                HALLOWED_GOLD_INGOT.get(), HALLOWED_GOLD_NUGGET.get(),
                MALIGNANT_PEWTER_INGOT.get(), MALIGNANT_PEWTER_NUGGET.get(), MALIGNANT_PEWTER_PLATING.get(),
                NULL_SLATE.get(), VOID_SALTS.get(), MNEMONIC_FRAGMENT.get(), AURIC_EMBERS.get(), MALIGNANT_LEAD.get(),
                ANOMALOUS_DESIGN.get(), COMPLETE_DESIGN.get(), FUSED_CONSCIOUSNESS.get());
        tag(MalumTags.ItemTags.MINERALS).add(
                RAW_SOULSTONE.get(), CRUSHED_SOULSTONE.get(), REFINED_SOULSTONE.get(),
                RAW_BRILLIANCE.get(), CRUSHED_BRILLIANCE.get(), REFINED_BRILLIANCE.get(),
                BLAZING_QUARTZ.get(),
                NATURAL_QUARTZ.get(), CTHONIC_GOLD.get(), CTHONIC_GOLD_FRAGMENT.get());

        tag(MalumTags.ItemTags.AUGMENTS).addAll(items.stream().filter(i -> i.get() instanceof AugmentItem).map(DeferredHolder::getKey).toList());
        tag(MalumTags.ItemTags.METAL_NODES).addAll(items.stream().filter(i -> i.get() instanceof NodeItem).map(DeferredHolder::getKey).toList());
        tag(MalumTags.ItemTags.IMPETUS).addAll(items.stream().filter(i -> i.get() instanceof ImpetusItem).map(DeferredHolder::getKey).toList());
        tag(MalumTags.ItemTags.FRACTURED_IMPETUS).addAll(items.stream().filter(i -> i.get() instanceof CrackedImpetusItem).map(DeferredHolder::getKey).toList());
        tag(MalumTags.ItemTags.METAL_IMPETUS).addTag(MalumTags.ItemTags.IMPETUS).remove(ALCHEMICAL_IMPETUS.get(), ZEPHYR_IMPETUS.get());
        tag(MalumTags.ItemTags.FRACTURED_METAL_IMPETUS).addTag(MalumTags.ItemTags.FRACTURED_IMPETUS).remove(FRACTURED_ALCHEMICAL_IMPETUS.get(), FRACTURED_ZEPHYR_IMPETUS.get());

        tag(MalumTags.ItemTags.SOULWOVEN_BANNERS).addAll(items.stream().filter(i -> i.get() instanceof SoulwovenBannerBlockItem).map(DeferredHolder::getKey).toList());

        tag(MalumTags.ItemTags.IS_TOTEMIC_TOOL).add(TOTEMIC_STAFF.get());
        tag(MalumTags.ItemTags.IS_REDSTONE_TOOL).add(ARTIFICERS_CLAW.get()).addOptional(ResourceLocation.parse("create:wrench"));
        tag(MalumTags.ItemTags.IS_ARTIFICE_TOOL).add(TUNING_FORK.get()).addOptional(ResourceLocation.parse("create:wrench"));

        tag(MalumTags.ItemTags.GROSS_FOODS).add(Items.ROTTEN_FLESH, ROTTING_ESSENCE.get(), CONCENTRATED_GLUTTONY.get());

        tag(MalumTags.ItemTags.PROSPECTORS_TREASURE)
                .addTags(Tags.Items.ORES, Tags.Items.STORAGE_BLOCKS, Tags.Items.INGOTS, Tags.Items.NUGGETS, Tags.Items.GEMS, Tags.Items.RAW_MATERIALS, net.minecraft.tags.ItemTags.COALS, MalumTags.ItemTags.METAL_NODES)
                .addOptional(ResourceLocation.parse("tetra:geode"));

        tag(MalumTags.ItemTags.SOULHUNTERS_TREASURE)
                .addTags(MalumTags.ItemTags.SOUL_SHATTER_CAPABLE_WEAPONS, MalumTags.ItemTags.SPIRITS, MalumTags.ItemTags.MOB_DROPS, MalumTags.ItemTags.MATERIALS, MalumTags.ItemTags.MINERALS)
                .addTags(MalumTags.ItemTags.AUGMENTS, MalumTags.ItemTags.METAL_NODES, MalumTags.ItemTags.SOULWOVEN_BANNERS)
                .addTags(MalumTags.ItemTags.RING, MalumTags.ItemTags.NECKLACE, MalumTags.ItemTags.BELT, MalumTags.ItemTags.BROOCH, MalumTags.ItemTags.RUNE)
                .add(TUNING_FORK.get(), LAMPLIGHTERS_TONGS.get(), CATALYST_LOBBER.get())
                .add(ENCYCLOPEDIA_ARCANA.get(), ENCYCLOPEDIA_ESOTERICA.get());
        tag(MalumTags.ItemTags.SOULWOVEN_POUCH_AUTOCOLLECT)
                .addTags(MalumTags.ItemTags.SPIRITS, MalumTags.ItemTags.MOB_DROPS, MalumTags.ItemTags.MINERALS);

        tag(MalumTags.ItemTags.VOID_SOULSTONE_CONVERSION)
                .addTags(Tags.Items.RAW_MATERIALS)
                .remove(RAW_SOULSTONE.get(), RAW_BRILLIANCE.get(), CTHONIC_GOLD.get(), CTHONIC_GOLD_FRAGMENT.get());

        tag(Tags.Items.RAW_MATERIALS).add(RAW_SOULSTONE.get(), RAW_BRILLIANCE.get(), CTHONIC_GOLD.get(), CTHONIC_GOLD_FRAGMENT.get());
        tag(Tags.Items.NUGGETS).add(HALLOWED_GOLD_NUGGET.get(), SOUL_STAINED_STEEL_NUGGET.get(), MALIGNANT_PEWTER_INGOT.get());
        tag(Tags.Items.INGOTS).add(HALLOWED_GOLD_NUGGET.get(), SOUL_STAINED_STEEL_INGOT.get(), MALIGNANT_PEWTER_NUGGET.get());
        tag(Tags.Items.GEMS).add(NATURAL_QUARTZ.get(), BLAZING_QUARTZ.get(), RAW_BRILLIANCE.get());

        tag(Tags.Items.NUGGETS).addOptional(MalumMod.malumPath("copper_nugget"));
        tag(NUGGETS_COPPER).addOptional(MalumMod.malumPath("copper_nugget"));

        tag(MalumTags.ItemTags.HIDDEN_ALWAYS).add(THE_DEVICE.get(), THE_VESSEL.get());

        tag(MalumTags.ItemTags.HIDDEN_UNTIL_VOID)
                .addTag(MalumTags.ItemTags.HIDDEN_UNTIL_BLACK_CRYSTAL)
                // The Well
                .add(PRIMORDIAL_SOUP.get())
                // Encyclopedia
                .add(ENCYCLOPEDIA_ESOTERICA.get())
                //Equipment
                .add(CATALYST_LOBBER.get())
                // Materials
                .add(BLOCK_OF_NULL_SLATE.get(), NULL_SLATE.get(),
                        BLOCK_OF_VOID_SALTS.get(), VOID_SALTS.get(),
                        BLOCK_OF_MNEMONIC_FRAGMENT.get(), MNEMONIC_FRAGMENT.get(),
                        BLOCK_OF_AURIC_EMBERS.get(), AURIC_EMBERS.get(),
                        BLOCK_OF_MALIGNANT_LEAD.get(), MALIGNANT_LEAD.get());

        tag(MalumTags.ItemTags.HIDDEN_UNTIL_BLACK_CRYSTAL)
                // Umbral Spirit
                .add(UMBRAL_SPIRIT.get())
                // Anomalous Design
                .add(ANOMALOUS_DESIGN.get(), COMPLETE_DESIGN.get(), FUSED_CONSCIOUSNESS.get())
                // Malignant Pewter
                .add(MALIGNANT_PEWTER_INGOT.get(), MALIGNANT_PEWTER_PLATING.get(),
                        MALIGNANT_PEWTER_NUGGET.get(), BLOCK_OF_MALIGNANT_PEWTER.get())
                // Equipment
                .add(MALIGNANT_STRONGHOLD_HELMET.get(), MALIGNANT_STRONGHOLD_CHESTPLATE.get(),
                        MALIGNANT_STRONGHOLD_LEGGINGS.get(), MALIGNANT_STRONGHOLD_BOOTS.get(),
                        WEIGHT_OF_WORLDS.get(), EDGE_OF_DELIVERANCE.get(),
                        MNEMONIC_HEX_STAFF.get(), EROSION_SCEPTER.get(),
                        UNWINDING_CHAOS.get(), SUNDERING_ANCHOR.get())
                // Runes
                .add(RUNE_OF_BOLSTERING.get(), RUNE_OF_SACRIFICIAL_EMPOWERMENT.get(),
                        RUNE_OF_SPELL_MASTERY.get(), RUNE_OF_THE_HERETIC.get(),
                        RUNE_OF_UNNATURAL_STAMINA.get(), RUNE_OF_TWINNED_DURATION.get(),
                        RUNE_OF_TOUGHNESS.get(), RUNE_OF_IGNEOUS_SOLACE.get())
                // Trinkets
                .add(RING_OF_THE_ENDLESS_WELL.get(), RING_OF_GROWING_FLESH.get(), RING_OF_ECHOING_ARCANA.get(),
                        RING_OF_GRUESOME_CONCENTRATION.get(), NECKLACE_OF_THE_HIDDEN_BLADE.get(),
                        NECKLACE_OF_THE_WATCHER.get(), BELT_OF_THE_LIMITLESS.get())
                // Augments
                .add(STELLAR_MECHANISM.get())
                // Aesthetica
                .add(AESTHETICA.get());

        tag(MalumTags.ItemTags.ARCANE_ELEGY_COMPONENTS).addTag(Tags.Items.MUSIC_DISCS).remove(ARCANE_ELEGY.get(), AESTHETICA.get());

        for (DeferredHolder<net.minecraft.world.item.Item, ? extends net.minecraft.world.item.Item> i : items) {
            if (i.get() instanceof MalumCurioItem) {
                final net.minecraft.world.item.Item item = i.get();
                final ResourceLocation id = i.getId();
                if (id.getPath().contains("_ring") || id.getPath().contains("ring_")) {
                    tag(MalumTags.ItemTags.RING).add(item);
                    continue;
                }
                if (id.getPath().contains("_necklace") || id.getPath().contains("necklace_")) {
                    tag(MalumTags.ItemTags.NECKLACE).add(item);
                    continue;
                }
                if (id.getPath().contains("_belt") || id.getPath().contains("belt_")) {
                    tag(MalumTags.ItemTags.BELT).add(item);
                    continue;
                }
                if (id.getPath().contains("_rune") || id.getPath().contains("rune_")) {
                    tag(MalumTags.ItemTags.RUNE).add(item);
                    continue;
                }
                if (id.getPath().contains("_brooch") || id.getPath().contains("brooch_")) {
                    tag(MalumTags.ItemTags.BROOCH).add(item);
                }
            }
        }
        tag(MalumTags.ItemTags.GEAS).add(GEAS.get());
        tag(MalumTags.ItemTags.CHARM).add(TOPHAT.get(), TOKEN_OF_GRATITUDE.get());
    }

    @Override
    public IntrinsicTagAppender<net.minecraft.world.item.Item> tag(TagKey<net.minecraft.world.item.Item> pTag) {
        return super.tag(pTag);
    }

    public void safeCopy(TagKey<net.minecraft.world.item.Item> itemTag) {
        safeCopy(MalumBlocks.BLOCKS, TagKey.create(BuiltInRegistries.BLOCK.key(), itemTag.location()), itemTag);
    }

    public void safeCopy(TagKey<Block> blockTag, TagKey<net.minecraft.world.item.Item> itemTag) {
        safeCopy(MalumBlocks.BLOCKS, blockTag, itemTag);
    }

    public void safeCopy(DeferredRegister<Block> blocks, TagKey<Block> blockTag, TagKey<net.minecraft.world.item.Item> itemTag) {
        for (DeferredHolder<Block, ? extends Block> object : blocks.getEntries()) {
            final Block block = object.get();
            if (block.properties() instanceof LodestoneBlockProperties lodestoneBlockProperties) {
                final LodestoneDatagenBlockData datagenData = lodestoneBlockProperties.getDatagenData();
                if (datagenData.getTags().contains(blockTag)) {
                    final net.minecraft.world.item.Item item = block.asItem();
                    if (!item.equals(Items.AIR)) {
                        tag(itemTag).add(item);
                    }
                }
            }
        }
    }
}