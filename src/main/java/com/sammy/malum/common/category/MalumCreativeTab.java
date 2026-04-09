package com.sammy.malum.common.category;

import com.sammy.malum.*;
import com.sammy.malum.common.item.banner.SoulwovenBannerBlockItem;
import com.sammy.malum.core.handlers.hiding.*;
import com.sammy.malum.registry.common.content.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.*;
import team.lodestar.lodestone.modules.toolkit.creative_tab.CategorizedCreativeTab;
import team.lodestar.lodestone.modules.toolkit.creative_tab.CreativeTabCategory;

import java.util.*;

import static com.sammy.malum.registry.common.content.block.MalumBlocks.*;
import static com.sammy.malum.registry.common.content.item.MalumItemProperties.*;

public class MalumCreativeTab extends CategorizedCreativeTab {

        private static final ResourceLocation SLOT_WRAPPER = MalumMod.malumPath("slot_wrapper");
        private static final ResourceLocation SLOT_WRAPPER_LEFT = MalumMod.malumPath("slot_wrapper_left");
        private static final ResourceLocation SLOT_WRAPPER_RIGHT = MalumMod.malumPath("slot_wrapper_right");
        private static final ResourceLocation EMPTY_SLOT = MalumMod.malumPath("empty_slot");

        public static final String FUNDAMENTALS_CATEGORY = "fundamentals_of_arcana";
        public static final String GEAR_CATEGORY = "gear_and_trinkets";
        public static final String ARTIFICE_CATEGORY = "alchemy_and_artifice";
        public static final String ALCHEMICAL_FOCI_CATEGORY = "alchemical_foci";
        public static final String MATERIALS_CATEGORY = "soulformed_substance";
        public static final String ORES_CATEGORY = "earthen_treasures";
        public static final String ETHERWORKS_CATEGORY = "ether";
        public static final String SOULSTUFF_CATEGORY = "soulstuff";
        public static final String RUNEWOOD_CATEGORY = "runewood";
        public static final String SOULWOOD_CATEGORY = "soulwood";
        public static final String BLIGHT_CATEGORY = "blight";
        public static final String TAINTED_ROCK_CATEGORY = "tainted_rock";
        public static final String TWISTED_ROCK_CATEGORY = "twisted_rock";

        public static final String DUNGEON_REGALIA = "dungeon_regalia";
        public static final String DUNGEON_ARCHITECTURE = "dungeon_architecture";
        public static final String DUNGEON_ODDITIES = "dungeon_distortions";

        public MalumCreativeTab(Builder builder) {
                super(MalumMod.MALUM, builder);
        }

        @Override
        public Optional<ResourceLocation> getHeaderTexture(CreativeTabCategory.CategoryHeader header, int row, int column) {
                if (column == 0) {
                        return Optional.of(SLOT_WRAPPER_LEFT);
                } else if (column == 8) {
                        return Optional.of(SLOT_WRAPPER_RIGHT);
                }
                return Optional.of(SLOT_WRAPPER);
        }

        @Override
        public Optional<ResourceLocation> getEmptySlotTexture(int row, int column) {
                return Optional.of(EMPTY_SLOT);
        }

        @Override
        public boolean isItemVisible(ItemStack stack) {
                return !HiddenTagHandler.isHiddenItem(stack);
        }

        public void buildCategories() {
                createCategory(FUNDAMENTALS_CATEGORY)
                        .addItems(
                                MalumContent.Materials.SACRED_SPIRIT, MalumContent.Materials.WICKED_SPIRIT, MalumContent.Materials.ARCANE_SPIRIT, MalumContent.Materials.ELDRITCH_SPIRIT,
                                MalumContent.Materials.AERIAL_SPIRIT, MalumContent.Materials.AQUEOUS_SPIRIT, MalumContent.Materials.EARTHEN_SPIRIT, MalumContent.Materials.INFERNAL_SPIRIT,
                                MalumContent.Materials.UMBRAL_SPIRIT
                        ).nextLine()
                        .addItems(
                                MalumContent.ENCYCLOPEDIA_ARCANA,
                                MalumContent.ENCYCLOPEDIA_ESOTERICA,
                                MalumContent.Progression.SPIRIT_ALTAR::getItem,
                                MalumContent.Progression.RUNEWOOD_OBELISK::getItem,
                                MalumContent.Progression.BRILLIANT_OBELISK::getItem,
                                MalumContent.Progression.SPIRIT_JAR::getItem,
                                MalumContent.Progression.RUNIC_WORKBENCH::getItem,
                                MalumContent.Progression.SOUL_BRAZIER::getItem
                        ).nextLine()
                        .addItems(
                                MalumContent.Progression.RUNEWOOD_TOTEM_BASE::getItem,
                                MalumContent.Progression.SOULWOOD_TOTEM_BASE::getItem,
                                MalumContent.Progression.WAVEFORM_RUNEWOOD_TOTEM_BASE::getItem,
                                MalumContent.Progression.WAVEFORM_SOULWOOD_TOTEM_BASE::getItem,
                                MalumContent.Progression.ARCANA_PYLON::getItem,
                                MalumContent.Progression.RITE_ANCHOR::getItem,
                                MalumContent.Progression.RITE_UNWEAVER::getItem,
                                MalumContent.Progression.RITE_SPREADER::getItem,
                                MalumContent.Progression.RITE_CHANNEL::getItem
                        )
                        .bake();
                createCategory(GEAR_CATEGORY)
                        .addItems(
                                MalumContent.Gear.CRUDE_SCYTHE,
                                MalumContent.Gear.SOULWOVEN_POUCH,
                                MalumContent.Gear.RAVENOUS_POUCH,
                                MalumContent.Progression.TOTEMIC_STAFF,
                                MalumContent.Progression.ARTIFICERS_CLAW,
                                MalumContent.Progression.TUNING_FORK,
                                MalumContent.Gear.LAMPLIGHTERS_TONGS,
                                MalumContent.Gear.CATALYST_LOBBER,
                                MalumContent.Gear.CONCENTRATED_GLUTTONY,
                                MalumContent.Gear.SPLASH_OF_GLUTTONY
                        ).nextLine()
                        .addItems(
                                MalumContent.Gear.SOUL_STAINED_STEEL_HELMET,
                                MalumContent.Gear.SOUL_STAINED_STEEL_CHESTPLATE,
                                MalumContent.Gear.SOUL_STAINED_STEEL_LEGGINGS,
                                MalumContent.Gear.SOUL_STAINED_STEEL_BOOTS,
                                MalumContent.Gear.SOUL_STAINED_STEEL_SWORD,
                                MalumContent.Gear.SOUL_STAINED_STEEL_PICKAXE,
                                MalumContent.Gear.SOUL_STAINED_STEEL_AXE,
                                MalumContent.Gear.SOUL_STAINED_STEEL_SHOVEL,
                                MalumContent.Gear.SOUL_STAINED_STEEL_HOE
                        )
                        .addItems(
                                MalumContent.Gear.SOUL_HUNTER_CLOAK,
                                MalumContent.Gear.SOUL_HUNTER_ROBE,
                                MalumContent.Gear.SOUL_HUNTER_LEGGINGS,
                                MalumContent.Gear.SOUL_HUNTER_BOOTS,
                                MalumContent.Gear.SOUL_STAINED_STEEL_SCYTHE,
                                MalumContent.Gear.SOUL_STAINED_STEEL_KNIFE,
                                MalumContent.Gear.TYRVING,
                                MalumContent.Gear.RAVENOUS_SCYTHE,
                                MalumContent.Gear.GLUTTONOUS_BLUDGEON
                        )
                        .addItems(
                                MalumContent.Gear.MALIGNANT_STRONGHOLD_HELMET,
                                MalumContent.Gear.MALIGNANT_STRONGHOLD_CHESTPLATE,
                                MalumContent.Gear.MALIGNANT_STRONGHOLD_LEGGINGS,
                                MalumContent.Gear.MALIGNANT_STRONGHOLD_BOOTS,
                                MalumContent.Gear.WEIGHT_OF_WORLDS,
                                MalumContent.Gear.EDGE_OF_DELIVERANCE,
                                MalumContent.Gear.SUNDERING_ANCHOR,
                                MalumContent.Gear.SPELLWEAVING_PICKAXE,
                                MalumContent.Gear.SPELLWEAVING_AXE
                        ).nextLine()
                        .addItems(
                                MalumContent.Gear.MNEMONIC_HEX_STAFF,
                                MalumContent.Gear.EROSION_SCEPTER,
                                MalumContent.Gear.UNWINDING_CHAOS
                        ).nextLine()
                        .addItems(
                                MalumContent.Gear.GILDED_RING,
                                MalumContent.Gear.GILDED_BELT,
                                MalumContent.Gear.ORNATE_RING,
                                MalumContent.Gear.ORNATE_NECKLACE
                        )
                        .addItems(
                                MalumContent.Gear.RUNIC_BROOCH,
                                MalumContent.Gear.ELABORATE_BROOCH,
                                MalumContent.Gear.GLASS_BROOCH,
                                MalumContent.Gear.GLUTTONOUS_BROOCH
                        ).nextLine()
                        .addItems(
                                MalumContent.Gear.RING_OF_ESOTERIC_SPOILS,
                                MalumContent.Gear.RING_OF_CURATIVE_TALENT,
                                MalumContent.Gear.RING_OF_ALCHEMICAL_MASTERY,
                                MalumContent.Gear.RING_OF_DESPERATE_VORACITY,
                                MalumContent.Gear.RING_OF_THE_RISING_EDGE,
                                MalumContent.Gear.RING_OF_HEARTY_AVARICE
                        ).nextLine()
                        .addItems(
                                MalumContent.Gear.RING_OF_ESOTERIC_SHADOW,
                                MalumContent.Gear.RING_OF_MANAWEAVING,
                                MalumContent.Gear.RING_OF_ARCANE_PROWESS,
                                MalumContent.Gear.RING_OF_SWARMING_ROT,
                                MalumContent.Gear.RING_OF_THE_HOWLING_MAELSTROM,
                                MalumContent.Gear.RING_OF_HEAVY_DISCHARGE
                        ).nextLine()
                        .addItems(
                                MalumContent.Gear.NECKLACE_OF_MYSTIC_POTENCY,
                                MalumContent.Gear.NECKLACE_OF_THE_NARROW_EDGE
                        )
                        .addItems(
                                MalumContent.Gear.BELT_OF_THE_STARVED,
                                MalumContent.Gear.BELT_OF_THE_PROSPECTOR,
                                MalumContent.Gear.BELT_OF_THE_MAGEBANE
                        ).nextLine()
                        .addItems(
                                MalumContent.Gear.RING_OF_THE_ENDLESS_WELL,
                                MalumContent.Gear.RING_OF_ECHOING_ARCANA,
                                MalumContent.Gear.RING_OF_GROWING_FLESH,
                                MalumContent.Gear.RING_OF_GRUESOME_CONCENTRATION,
                                MalumContent.Gear.NECKLACE_OF_THE_HIDDEN_BLADE,
                                MalumContent.Gear.NECKLACE_OF_THE_WATCHER,
                                MalumContent.Gear.BELT_OF_THE_LIMITLESS
                        ).nextLine()
                        .addItems(
                                MalumContent.Gear.RUNE_OF_VITALITY,
                                MalumContent.Gear.RUNE_OF_CULLING,
                                MalumContent.Gear.RUNE_OF_REINFORCEMENT,
                                MalumContent.Gear.RUNE_OF_VOLATILE_DISTORTION,
                                MalumContent.Gear.RUNE_OF_DEXTERITY,
                                MalumContent.Gear.RUNE_OF_AILMENT_CLEANSING,
                                MalumContent.Gear.RUNE_OF_PROTECTION,
                                MalumContent.Gear.RUNE_OF_SCORCHING
                        ).nextLine()
                        .addItems(
                                MalumContent.Gear.RUNE_OF_HOWLING_GALE,
                                MalumContent.Gear.RUNE_OF_FLOWING_GRASP,
                                MalumContent.Gear.RUNE_OF_STONE_WARD,
                                MalumContent.Gear.RUNE_OF_BURNING_FERVOR,
                                MalumContent.Gear.RUNE_OF_SKY_TETHER,
                                MalumContent.Gear.RUNE_OF_GOOD_TIDES,
                                MalumContent.Gear.RUNE_OF_OAKEN_MIGHT,
                                MalumContent.Gear.RUNE_OF_FIERY_EMBRACE
                        ).nextLine()
                        .addItems(
                                MalumContent.Gear.RUNE_OF_BOLSTERING,
                                MalumContent.Gear.RUNE_OF_RADIAL_EMPOWERMENT,
                                MalumContent.Gear.RUNE_OF_SPELL_MASTERY,
                                MalumContent.Gear.RUNE_OF_HERESY,
                                MalumContent.Gear.RUNE_OF_UNNATURAL_STAMINA,
                                MalumContent.Gear.RUNE_OF_TWINNED_DURATION,
                                MalumContent.Gear.RUNE_OF_INDOMITABILITY,
                                MalumContent.Gear.RUNE_OF_IGNEOUS_SOLACE
                        )
                        .bake();
                createCategory(ARTIFICE_CATEGORY)
                        .addItems(
                                MalumContent.Progression.TUNING_FORK,
                                MalumContent.Progression.SPIRIT_CRUCIBLE::getItem,
                                MalumContent.Progression.SPIRIT_CATALYZER::getItem,
                                MalumContent.Progression.REPAIR_PYLON::getItem
                        ).nextLine()
                        .addItems(
                                MalumContent.Progression.WAVECHARGER::getItem,
                                MalumContent.Progression.WAVEBANKER::getItem,
                                MalumContent.Progression.WAVEMAKER::getItem,
                                MalumContent.Progression.WAVEBREAKER::getItem,
                                MalumContent.Progression.GUST_IGNITER::getItem,
                                MalumContent.Progression.WIND_TUNNEL::getItem
                        ).nextLine()
                        .addItems(
                                MalumContent.Progression.MENDING_DIFFUSER,
                                MalumContent.Progression.IMPURITY_STABILIZER,
                                MalumContent.Progression.SHIELDING_APPARATUS,
                                MalumContent.Progression.WARPING_ENGINE,
                                MalumContent.Progression.ACCELERATING_INLAY,
                                MalumContent.Progression.PRISMATIC_FOCUS_LENS,
                                MalumContent.Progression.BLAZING_DIODE,
                                MalumContent.Progression.INTRICATE_ASSEMBLY
                        ).nextLine()
                        .addItems(
                                MalumContent.Progression.SYMPATHY_DRIVE,
                                MalumContent.Progression.SUSPICIOUS_DEVICE,
                                MalumContent.Progression.CAUSTIC_CATALYST,
                                MalumContent.Progression.RESONANCE_TUNER,
                                MalumContent.Progression.STELLAR_MECHANISM
                        )
                        .bake();
                createCategory(ALCHEMICAL_FOCI_CATEGORY)
                        .addItems(MalumContent.Progression.ALCHEMICAL_IMPETUS, MalumContent.Progression.FRACTURED_ALCHEMICAL_IMPETUS)
                        .addItems(MalumContent.Progression.ZEPHYR_IMPETUS, MalumContent.Progression.FRACTURED_ZEPHYR_IMPETUS)
                        .addItems(MalumContent.Progression.IFRIT_IMPETUS, MalumContent.Progression.FRACTURED_IFRIT_IMPETUS)
                        .nextLine()

                        .addItems(MalumContent.Progression.IRON_METALLICS::addToCreativeTab)
                        .addItems(MalumContent.Progression.COPPER_METALLICS::addToCreativeTab)
                        .addItems(MalumContent.Progression.GOLD_METALLICS::addToCreativeTab)
                        .addItems(MalumContent.Progression.ZINC_METALLICS::addToCreativeTab)
                        .addItems(MalumContent.Progression.LEAD_METALLICS::addToCreativeTab)
                        .addItems(MalumContent.Progression.SILVER_METALLICS::addToCreativeTab)
                        .addItems(MalumContent.Progression.ALUMINIUM_METALLICS::addToCreativeTab)
                        .addItems(MalumContent.Progression.NICKEL_METALLICS::addToCreativeTab)

                        .bake();
                createCategory(MATERIALS_CATEGORY)
                        .addItems(
                                MalumContent.Materials.ROTTING_ESSENCE,
                                MalumContent.Materials.GRIM_TALC,
                                MalumContent.Materials.EERIE_WEAVE,
                                MalumContent.Materials.WARP_FLUX
                        )
                        .addItems(
                                MalumContent.Materials.WIND_NUCLEUS,
                                MalumContent.Materials.PYRE_NUCLEUS
                        ).nextLine()
                        .addItems(
                                MalumContent.Materials.HEX_ASH,
                                MalumContent.Materials.LIVING_FLESH,
                                MalumContent.Materials.ALCHEMICAL_CALX,
                                MalumContent.Materials.ARCANE_CHARCOAL
                        )
                        .addItems(
                                MalumContent.Materials.EBONY_STALK,
                                MalumContent.Materials.EBONY,
                                MalumContent.Materials.WILD_WITCHHAZEL,
                                MalumContent.Materials.WITCHHAZEL
                        ).nextLine()
                        .addItems(
                                MalumContent.Materials.NULL_SLATE,
                                MalumContent.Materials.VOID_SALTS,
                                MalumContent.Materials.MNEMONIC_FRAGMENT,
                                MalumContent.Materials.AURIC_EMBERS,
                                MalumContent.Materials.MALIGNANT_LEAD
                        ).nextLine()
                        .addItems(
                                MalumContent.Materials.SOUL_STAINED_STEEL_INGOT,
                                MalumContent.Materials.SOUL_STAINED_STEEL_PLATING,
                                MalumContent.Materials.SOUL_STAINED_STEEL_NUGGET,
                                MalumContent.Materials.HALLOWED_GOLD_INGOT,
                                MalumContent.Materials.HALLOWED_GOLD_INLAY,
                                MalumContent.Materials.HALLOWED_GOLD_NUGGET,
                                MalumContent.Materials.MALIGNANT_PEWTER_INGOT,
                                MalumContent.Materials.MALIGNANT_PEWTER_PLATING,
                                MalumContent.Materials.MALIGNANT_PEWTER_NUGGET
                        ).nextLine()
                        .addItems(
                                MalumContent.Materials.SOULWOVEN_SILK,
                                MalumContent.Materials.PARACAUSAL_FLAME,
                                MalumContent.Materials.CONVOLUTED_LENS,
                                MalumContent.Materials.MIMICRY_RELAY,
                                MalumContent.Materials.POPPET,
                                MalumContent.Materials.IMITATION_FLESH,
                                MalumContent.Materials.IMITATION_HEART
                        ).nextLine()
                        .addItems(
                                MalumContent.Materials.ANOMALOUS_DESIGN,
                                MalumContent.Materials.COMPLETE_DESIGN,
                                MalumContent.Materials.FUSED_CONSCIOUSNESS
                        ).nextLine()
                        .addItems(
                                MalumContent.Materials.BLOCK_OF_ROTTING_ESSENCE,
                                MalumContent.Materials.BLOCK_OF_GRIM_TALC,
                                MalumContent.Materials.BLOCK_OF_EERIE_WEAVE,
                                MalumContent.Materials.BLOCK_OF_WARP_FLUX
                        )
                        .addItems(
                                MalumContent.Materials.BLOCK_OF_WIND_NUCLEI,
                                MalumContent.Materials.BLOCK_OF_PYRE_NUCLEI
                        ).nextLine()
                        .addItems(
                                MalumContent.Materials.BLOCK_OF_HEX_ASH,
                                MalumContent.Materials.BLOCK_OF_LIVING_FLESH,
                                MalumContent.Materials.BLOCK_OF_ALCHEMICAL_CALX,
                                MalumContent.Materials.BLOCK_OF_ARCANE_CHARCOAL
                        )
                        .addItems(
                                MalumContent.Materials.BLOCK_OF_EBONY,
                                MalumContent.Materials.CRATE_OF_WITCHHAZEL
                        ).nextLine()
                        .addItems(
                                MalumContent.Materials.BLOCK_OF_NULL_SLATE,
                                MalumContent.Materials.BLOCK_OF_VOID_SALTS,
                                MalumContent.Materials.BLOCK_OF_MNEMONIC_FRAGMENT,
                                MalumContent.Materials.BLOCK_OF_AURIC_EMBERS,
                                MalumContent.Materials.BLOCK_OF_MALIGNANT_LEAD
                        ).nextLine()
                        .addItems(
                                MalumContent.Materials.BLOCK_OF_SOUL_STAINED_STEEL,
                                MalumContent.Materials.BLOCK_OF_HALLOWED_GOLD,
                                MalumContent.Materials.BLOCK_OF_MALIGNANT_PEWTER
                        ).bake();
                createCategory(ORES_CATEGORY)
                        .addItems(
                                MalumContent.Materials.BLOCK_OF_SOULSTONE, MalumContent.Materials.BLOCK_OF_RAW_SOULSTONE, MalumContent.Materials.DEEPSLATE_SOULSTONE_ORE, MalumContent.Materials.SOULSTONE_ORE,
                                MalumContent.Materials.RAW_SOULSTONE, MalumContent.Materials.CRUSHED_SOULSTONE, MalumContent.Materials.REFINED_SOULSTONE
                        ).nextLine()
                        .addItems(
                                MalumContent.Materials.BLOCK_OF_BRILLIANCE, MalumContent.Materials.BLOCK_OF_RAW_BRILLIANCE, MalumContent.Materials.BRILLIANT_DEEPSLATE, MalumContent.Materials.BRILLIANT_STONE,
                                MalumContent.Materials.RAW_BRILLIANCE, MalumContent.Materials.CRUSHED_BRILLIANCE, MalumContent.Materials.REFINED_BRILLIANCE
                        ).nextLine()
                        .addItems(
                                MalumContent.Materials.BLOCK_OF_BLAZING_QUARTZ, MalumContent.Materials.BLAZING_QUARTZ_ORE,
                                MalumContent.Materials.BLAZING_QUARTZ_CLUSTER
                        )
                        .addItems(
                                MalumContent.Materials.BLOCK_OF_NATURAL_QUARTZ, MalumContent.Materials.DEEPSLATE_QUARTZ_ORE, MalumContent.Materials.NATURAL_QUARTZ_ORE,
                                MalumContent.Materials.NATURAL_QUARTZ
                        ).nextLine()
                        .addItems(
                                MalumContent.Materials.BLOCK_OF_CTHONIC_GOLD, MalumContent.Materials.CTHONIC_GOLD_ORE,
                                MalumContent.Materials.CTHONIC_GOLD, MalumContent.Materials.CTHONIC_GOLD_FRAGMENT
                        )
                        .bake();
                createCategory(ETHERWORKS_CATEGORY)
                        .addItems(
                                MalumContent.BlockSets.ETHER,
                                MalumContent.BlockSets.ETHER_CANDLE,
                                MalumContent.BlockSets.ETHER_TORCH,

                                MalumContent.BlockSets.TAINTED_ETHER_BRAZIER,
                                MalumContent.BlockSets.TWISTED_ETHER_BRAZIER,
                                MalumContent.BlockSets.DROSS_ETHER_BRAZIER,

                                MalumContent.BlockSets.TAINTED_ETHER_CRESSET,
                                MalumContent.BlockSets.TWISTED_ETHER_CRESSET,
                                MalumContent.BlockSets.DROSS_ETHER_CRESSET
                        ).nextLine()
                        .addItems(
                                MalumContent.BlockSets.IRIDESCENT_ETHER,
                                MalumContent.BlockSets.IRIDESCENT_ETHER_CANDLE,
                                MalumContent.BlockSets.IRIDESCENT_ETHER_TORCH,

                                MalumContent.BlockSets.TAINTED_IRIDESCENT_ETHER_BRAZIER,
                                MalumContent.BlockSets.TWISTED_IRIDESCENT_ETHER_BRAZIER,
                                MalumContent.BlockSets.DROSS_IRIDESCENT_ETHER_BRAZIER,

                                MalumContent.BlockSets.TAINTED_IRIDESCENT_ETHER_CRESSET,
                                MalumContent.BlockSets.TWISTED_IRIDESCENT_ETHER_CRESSET,
                                MalumContent.BlockSets.DROSS_IRIDESCENT_ETHER_CRESSET
                        )
                        .bake();
                createCategory(SOULSTUFF_CATEGORY)
                        .addItems(
                                MalumContent.BlockSets.SACRED_SPIRITED_GLASS,
                                MalumContent.BlockSets.WICKED_SPIRITED_GLASS,
                                MalumContent.BlockSets.ARCANE_SPIRITED_GLASS,
                                MalumContent.BlockSets.ELDRITCH_SPIRITED_GLASS,
                                MalumContent.BlockSets.AERIAL_SPIRITED_GLASS,
                                MalumContent.BlockSets.AQUEOUS_SPIRITED_GLASS,
                                MalumContent.BlockSets.EARTHEN_SPIRITED_GLASS,
                                MalumContent.BlockSets.INFERNAL_SPIRITED_GLASS,
                                MalumContent.BlockSets.NULL_SPIRITED_GLASS
                        ).nextLine()
                        .addItems(
                                MalumContent.BlockSets.SACRED_VARNISHED_TERRACOTTA,
                                MalumContent.BlockSets.WICKED_VARNISHED_TERRACOTTA,
                                MalumContent.BlockSets.ARCANE_VARNISHED_TERRACOTTA,
                                MalumContent.BlockSets.ELDRITCH_VARNISHED_TERRACOTTA,
                                MalumContent.BlockSets.AERIAL_VARNISHED_TERRACOTTA,
                                MalumContent.BlockSets.AQUEOUS_VARNISHED_TERRACOTTA,
                                MalumContent.BlockSets.EARTHEN_VARNISHED_TERRACOTTA,
                                MalumContent.BlockSets.INFERNAL_VARNISHED_TERRACOTTA,
                                MalumContent.BlockSets.NULL_VARNISHED_TERRACOTTA
                        ).nextLine()
                        .addItems(SoulwovenBannerBlockItem::addBannerPatterns)
                        .bake();
                createCategory(RUNEWOOD_CATEGORY)
                        .addItems(
                                MalumContent.Materials.RUNIC_SAP_BOTTLE,
                                MalumContent.Materials.RUNIC_SAPBALL,
                                RUNEWOOD_SAPLING,
                                RUNEWOOD_LEAVES,
                                HANGING_RUNEWOOD_LEAVES,
                                AZURE_RUNEWOOD_SAPLING,
                                AZURE_RUNEWOOD_LEAVES,
                                HANGING_AZURE_RUNEWOOD_LEAVES
                        ).nextLine()
                        .addItems(
                                RUNEWOOD_LOG,
                                STRIPPED_RUNEWOOD_LOG,
                                RUNEWOOD,
                                STRIPPED_RUNEWOOD,
                                SAPPY_RUNEWOOD_LOG,
                                STRIPPED_SAPPY_RUNEWOOD_LOG
                        ).nextLine()
                        .addItems(
                                RUNEWOOD_BOARDS,
                                VERTICAL_RUNEWOOD_BOARDS,
                                RUNEWOOD_PLANKS,
                                VERTICAL_RUNEWOOD_PLANKS,
                                RUNEWOOD_TILES,
                                RUSTIC_RUNEWOOD_PLANKS,
                                VERTICAL_RUSTIC_RUNEWOOD_PLANKS,
                                RUSTIC_RUNEWOOD_TILES
                        ).nextLine()
                        .addItems(
                                RUNEWOOD_BOARDS_SLAB,
                                VERTICAL_RUNEWOOD_BOARDS_SLAB,
                                RUNEWOOD_PLANKS_SLAB,
                                VERTICAL_RUNEWOOD_PLANKS_SLAB,
                                RUNEWOOD_TILES_SLAB,
                                RUSTIC_RUNEWOOD_PLANKS_SLAB,
                                VERTICAL_RUSTIC_RUNEWOOD_PLANKS_SLAB,
                                RUSTIC_RUNEWOOD_TILES_SLAB
                        ).nextLine()
                        .addItems(
                                RUNEWOOD_BOARDS_STAIRS,
                                VERTICAL_RUNEWOOD_BOARDS_STAIRS,
                                RUNEWOOD_PLANKS_STAIRS,
                                VERTICAL_RUNEWOOD_PLANKS_STAIRS,
                                RUNEWOOD_TILES_STAIRS,
                                RUSTIC_RUNEWOOD_PLANKS_STAIRS,
                                VERTICAL_RUSTIC_RUNEWOOD_PLANKS_STAIRS,
                                RUSTIC_RUNEWOOD_TILES_STAIRS
                        ).nextLine()
                        .addItems(
                                RUNEWOOD_PANEL,
                                CUT_RUNEWOOD_PLANKS,
                                RUNEWOOD_BEAM,
                                GILDED_RUNEWOOD_ITEM_PEDESTAL,
                                RUNEWOOD_ITEM_PEDESTAL,
                                GILDED_RUNEWOOD_ITEM_STAND,
                                RUNEWOOD_ITEM_STAND
                        ).nextLine()
                        .addItems(
                                RUNEWOOD_DOOR,
                                BOLTED_RUNEWOOD_DOOR,
                                RUNEWOOD_TRAPDOOR,
                                BOLTED_RUNEWOOD_TRAPDOOR,
                                RUNEWOOD_BOARDS_DOOR,
                                BOLTED_RUNEWOOD_BOARDS_DOOR,
                                RUNEWOOD_BOARDS_TRAPDOOR,
                                BOLTED_RUNEWOOD_BOARDS_TRAPDOOR
                        ).nextLine()
                        .addItems(
                                RUNEWOOD_PRESSURE_PLATE,
                                RUNEWOOD_BUTTON,
                                RUNEWOOD_BOARDS_WALL,
                                RUNEWOOD_FENCE,
                                RUNEWOOD_FENCE_GATE,
                                RUNEWOOD_SIGN,
                                RUNEWOOD_BOAT,
                                RUNEWOOD_CHEST_BOAT
                        )
                        .bake();
                createCategory(SOULWOOD_CATEGORY)
                        .addItems(
                                MalumContent.Materials.CURSED_SAP_BOTTLE,
                                MalumContent.Materials.CURSED_SAPBALL,
                                SOULWOOD_SAPLING,
                                SOULWOOD_LEAVES,
                                HANGING_SOULWOOD_LEAVES
                        ).nextLine()
                        .addItems(
                                SOULWOOD_LOG,
                                STRIPPED_SOULWOOD_LOG,
                                SOULWOOD,
                                STRIPPED_SOULWOOD,
                                SAPPY_SOULWOOD_LOG,
                                STRIPPED_SAPPY_SOULWOOD_LOG,
                                BLIGHTED_SOULWOOD
                        ).nextLine()
                        .addItems(
                                SOULWOOD_BOARDS,
                                VERTICAL_SOULWOOD_BOARDS,
                                SOULWOOD_PLANKS,
                                VERTICAL_SOULWOOD_PLANKS,
                                SOULWOOD_TILES,
                                RUSTIC_SOULWOOD_PLANKS,
                                VERTICAL_RUSTIC_SOULWOOD_PLANKS,
                                RUSTIC_SOULWOOD_TILES
                        ).nextLine()
                        .addItems(
                                SOULWOOD_BOARDS_SLAB,
                                VERTICAL_SOULWOOD_BOARDS_SLAB,
                                SOULWOOD_PLANKS_SLAB,
                                VERTICAL_SOULWOOD_PLANKS_SLAB,
                                SOULWOOD_TILES_SLAB,
                                RUSTIC_SOULWOOD_PLANKS_SLAB,
                                VERTICAL_RUSTIC_SOULWOOD_PLANKS_SLAB,
                                RUSTIC_SOULWOOD_TILES_SLAB
                        ).nextLine()
                        .addItems(
                                SOULWOOD_BOARDS_STAIRS,
                                VERTICAL_SOULWOOD_BOARDS_STAIRS,
                                SOULWOOD_PLANKS_STAIRS,
                                VERTICAL_SOULWOOD_PLANKS_STAIRS,
                                SOULWOOD_TILES_STAIRS,
                                RUSTIC_SOULWOOD_PLANKS_STAIRS,
                                VERTICAL_RUSTIC_SOULWOOD_PLANKS_STAIRS,
                                RUSTIC_SOULWOOD_TILES_STAIRS
                        ).nextLine()
                        .addItems(
                                SOULWOOD_PANEL,
                                CUT_SOULWOOD_PLANKS,
                                SOULWOOD_BEAM,
                                ORNATE_SOULWOOD_ITEM_PEDESTAL,
                                SOULWOOD_ITEM_PEDESTAL,
                                ORNATE_SOULWOOD_ITEM_STAND,
                                SOULWOOD_ITEM_STAND
                        ).nextLine()
                        .addItems(
                                SOULWOOD_DOOR,
                                BOLTED_SOULWOOD_DOOR,
                                SOULWOOD_TRAPDOOR,
                                BOLTED_SOULWOOD_TRAPDOOR,
                                SOULWOOD_BOARDS_DOOR,
                                BOLTED_SOULWOOD_BOARDS_DOOR,
                                SOULWOOD_BOARDS_TRAPDOOR,
                                BOLTED_SOULWOOD_BOARDS_TRAPDOOR
                        ).nextLine()
                        .addItems(
                                SOULWOOD_BUTTON,
                                SOULWOOD_PRESSURE_PLATE,
                                SOULWOOD_BOARDS_WALL,
                                SOULWOOD_FENCE,
                                SOULWOOD_FENCE_GATE,
                                SOULWOOD_SIGN,
                                SOULWOOD_BOAT,
                                SOULWOOD_CHEST_BOAT
                        )
                        .bake();
                createCategory(BLIGHT_CATEGORY)
                        .addItems(
                                BLIGHTED_SOULWOOD,
                                MalumContent.BlockSets.COLUMNAR_BLIGHT,
                                MalumContent.BlockSets.BLIGHTED_EARTH,
                                MalumContent.BlockSets.BLIGHT,
                                MalumContent.BlockSets.BLIGHTED_GUNK,
                                MalumContent.BlockSets.BLIGHTPEARL,
                                MalumContent.BlockSets.BLIGHTROOT
                        ).nextLine()
                        .addItems(
                                MalumContent.BlockSets.SCARSTONE,
                                MalumContent.BlockSets.LARGE_STRANGE_CRYSTAL,
                                MalumContent.BlockSets.STRANGE_CRYSTAL,
                                MalumContent.BlockSets.STRANGEROOT
                        )
                        .bake();

                createCategory(TAINTED_ROCK_CATEGORY)
                        .addItems(
                                MalumContent.BlockSets.TAINTED_ROCK,
                                MalumContent.BlockSets.POLISHED_TAINTED_ROCK,
                                MalumContent.BlockSets.TAINTED_ROCK_BRICKS,
                                MalumContent.BlockSets.TAINTED_ROCK_TILES,
                                MalumContent.BlockSets.TAINTED_ROCK_MOSAIC
                        )
                        .addItems(
                                MalumContent.BlockSets.TAINTED_ROCK_COLUMN,
                                MalumContent.BlockSets.TAINTED_ROCK_ALTAR,
                                MalumContent.BlockSets.CUT_TAINTED_ROCK,
                                MalumContent.BlockSets.CHISELED_TAINTED_ROCK
                        ).nextLine()
                        .addItems(
                                MalumContent.BlockSets.TAINTED_ROCK_STAIRS,
                                MalumContent.BlockSets.POLISHED_TAINTED_ROCK_STAIRS,
                                MalumContent.BlockSets.TAINTED_ROCK_BRICKS_STAIRS,
                                MalumContent.BlockSets.TAINTED_ROCK_TILES_STAIRS,
                                MalumContent.BlockSets.TAINTED_ROCK_MOSAIC_STAIRS
                        )
                        .addItems(
                                MalumContent.BlockSets.TAINTED_ROCK_ITEM_PEDESTAL,
                                MalumContent.BlockSets.TAINTED_ROCK_ITEM_STAND,
                                MalumContent.BlockSets.TAINTED_ROCK_PRESSURE_PLATE,
                                MalumContent.BlockSets.TAINTED_ROCK_BUTTON
                        ).nextLine()
                        .addItems(
                                MalumContent.BlockSets.TAINTED_ROCK_SLAB,
                                MalumContent.BlockSets.POLISHED_TAINTED_ROCK_SLAB,
                                MalumContent.BlockSets.TAINTED_ROCK_BRICKS_SLAB,
                                MalumContent.BlockSets.TAINTED_ROCK_TILES_SLAB,
                                MalumContent.BlockSets.TAINTED_ROCK_MOSAIC_SLAB
                        ).nextLine()
                        .addItems(
                                MalumContent.BlockSets.TAINTED_ROCK_WALL,
                                MalumContent.BlockSets.POLISHED_TAINTED_ROCK_WALL,
                                MalumContent.BlockSets.TAINTED_ROCK_BRICKS_WALL,
                                MalumContent.BlockSets.TAINTED_ROCK_TILES_WALL,
                                MalumContent.BlockSets.TAINTED_ROCK_MOSAIC_WALL
                        )
                        .bake();
                createCategory(TWISTED_ROCK_CATEGORY)
                        .addItems(
                                MalumContent.BlockSets.TWISTED_ROCK,
                                MalumContent.BlockSets.POLISHED_TWISTED_ROCK,
                                MalumContent.BlockSets.TWISTED_ROCK_BRICKS,
                                MalumContent.BlockSets.TWISTED_ROCK_TILES,
                                MalumContent.BlockSets.TWISTED_ROCK_MOSAIC
                        )
                        .addItems(
                                MalumContent.BlockSets.TWISTED_ROCK_COLUMN,
                                MalumContent.BlockSets.TWISTED_ROCK_ALTAR,
                                MalumContent.BlockSets.CUT_TWISTED_ROCK,
                                MalumContent.BlockSets.CHISELED_TWISTED_ROCK
                        ).nextLine()
                        .addItems(
                                MalumContent.BlockSets.TWISTED_ROCK_STAIRS,
                                MalumContent.BlockSets.POLISHED_TWISTED_ROCK_STAIRS,
                                MalumContent.BlockSets.TWISTED_ROCK_BRICKS_STAIRS,
                                MalumContent.BlockSets.TWISTED_ROCK_TILES_STAIRS,
                                MalumContent.BlockSets.TWISTED_ROCK_MOSAIC_STAIRS
                        )
                        .addItems(
                                MalumContent.BlockSets.TWISTED_ROCK_ITEM_PEDESTAL,
                                MalumContent.BlockSets.TWISTED_ROCK_ITEM_STAND,
                                MalumContent.BlockSets.TWISTED_ROCK_PRESSURE_PLATE,
                                MalumContent.BlockSets.TWISTED_ROCK_BUTTON
                        ).nextLine()
                        .addItems(
                                MalumContent.BlockSets.TWISTED_ROCK_SLAB,
                                MalumContent.BlockSets.POLISHED_TWISTED_ROCK_SLAB,
                                MalumContent.BlockSets.TWISTED_ROCK_BRICKS_SLAB,
                                MalumContent.BlockSets.TWISTED_ROCK_TILES_SLAB,
                                MalumContent.BlockSets.TWISTED_ROCK_MOSAIC_SLAB
                        ).nextLine()
                        .addItems(
                                MalumContent.BlockSets.TWISTED_ROCK_WALL,
                                MalumContent.BlockSets.POLISHED_TWISTED_ROCK_WALL,
                                MalumContent.BlockSets.TWISTED_ROCK_BRICKS_WALL,
                                MalumContent.BlockSets.TWISTED_ROCK_TILES_WALL,
                                MalumContent.BlockSets.TWISTED_ROCK_MOSAIC_WALL
                        )
                        .bake();
                createCategory(DUNGEON_REGALIA)
                        .addItems(
                                MalumContent.DungeonBlockSets.OMINOUS_ALTAR,
                                MalumContent.DungeonBlockSets.OMINOUS_OBELISK
                        )
                        .addItems(
                                MalumContent.BROKEN_BLADE,
                                MalumContent.SHAPED_SLAB
                        ).nextLine()
                        .addItems(
                                MalumContent.IRON_CROWN,
                                MalumContent.DungeonBlockSets.VEILED_EFFIGY,
                                MalumContent.DungeonBlockSets.CORRUPT_EFFIGY,
                                MalumContent.DungeonBlockSets.CRACKED_EFFIGY
                        )
                        .bake();
                createCategory(DUNGEON_ARCHITECTURE)
                        .addItems(
                                MalumContent.DungeonBlockSets.ODD_SCRIPTURES_I,
                                MalumContent.DungeonBlockSets.ODD_SCRIPTURES_II,
                                MalumContent.DungeonBlockSets.ODD_SCRIPTURES_III,
                                MalumContent.DungeonBlockSets.ODD_SCRIPTURES_IV,
                                MalumContent.DungeonBlockSets.ODD_SCRIPTURES_V,
                                MalumContent.DungeonBlockSets.ODD_SCRIPTURES_VI,
                                MalumContent.DungeonBlockSets.ODD_SCRIPTURES_VII,
                                MalumContent.DungeonBlockSets.ODD_SCRIPTURES_VIII,
                                MalumContent.DungeonBlockSets.ODD_SCRIPTURES_IX
                        )
                        .addItems(
                                MalumContent.DungeonBlockSets.DROSS_STONE,
                                MalumContent.DungeonBlockSets.POLISHED_DROSS_STONE,
                                MalumContent.DungeonBlockSets.DROSS_STONE_BRICKS,
                                MalumContent.DungeonBlockSets.DROSS_STONE_TILES,
                                MalumContent.DungeonBlockSets.DROSS_STONE_MOSAIC,
                                MalumContent.DungeonBlockSets.DARK_DROSS_TILES,
                                MalumContent.DungeonBlockSets.GRAY_DROSS_TILES
                        )
                        .addItems(
                                MalumContent.DungeonBlockSets.DROSS_STONE_COLUMN,
                                MalumContent.DungeonBlockSets.DROSS_STONE_ALTAR
                        ).nextLine()
                        .addItems(
                                MalumContent.DungeonBlockSets.DROSS_STONE_STAIRS,
                                MalumContent.DungeonBlockSets.POLISHED_DROSS_STONE_STAIRS,
                                MalumContent.DungeonBlockSets.DROSS_STONE_BRICKS_STAIRS,
                                MalumContent.DungeonBlockSets.DROSS_STONE_TILES_STAIRS,
                                MalumContent.DungeonBlockSets.DROSS_STONE_MOSAIC_STAIRS,
                                MalumContent.DungeonBlockSets.DARK_DROSS_TILES_STAIRS,
                                MalumContent.DungeonBlockSets.GRAY_DROSS_TILES_STAIRS
                        )
                        .addItems(
                                MalumContent.DungeonBlockSets.CUT_DROSS_STONE,
                                MalumContent.DungeonBlockSets.CHISELED_DROSS_STONE
                        ).nextLine()
                        .addItems(
                                MalumContent.DungeonBlockSets.DROSS_STONE_SLAB,
                                MalumContent.DungeonBlockSets.POLISHED_DROSS_STONE_SLAB,
                                MalumContent.DungeonBlockSets.DROSS_STONE_BRICKS_SLAB,
                                MalumContent.DungeonBlockSets.DROSS_STONE_TILES_SLAB,
                                MalumContent.DungeonBlockSets.DROSS_STONE_MOSAIC_SLAB,
                                MalumContent.DungeonBlockSets.DARK_DROSS_TILES_SLAB,
                                MalumContent.DungeonBlockSets.GRAY_DROSS_TILES_SLAB
                        )
                        .addItems(
                                MalumContent.DungeonBlockSets.DROSS_STONE_ITEM_PEDESTAL,
                                MalumContent.DungeonBlockSets.DROSS_STONE_ITEM_STAND
                        ).nextLine()
                        .addItems(
                                MalumContent.DungeonBlockSets.DROSS_STONE_WALL,
                                MalumContent.DungeonBlockSets.POLISHED_DROSS_STONE_WALL,
                                MalumContent.DungeonBlockSets.DROSS_STONE_BRICKS_WALL,
                                MalumContent.DungeonBlockSets.DROSS_STONE_TILES_WALL,
                                MalumContent.DungeonBlockSets.DROSS_STONE_MOSAIC_WALL,
                                MalumContent.DungeonBlockSets.DARK_DROSS_TILES_WALL,
                                MalumContent.DungeonBlockSets.GRAY_DROSS_TILES_WALL
                        )
                        .addItems(
                                MalumContent.DungeonBlockSets.DROSS_STONE_PRESSURE_PLATE,
                                MalumContent.DungeonBlockSets.DROSS_STONE_BUTTON
                        )
                        .bake();
                createCategory(DUNGEON_ODDITIES)
                        .addItems(
                                MalumContent.DungeonBlockSets.WRITHING_FLESH,
                                MalumContent.DungeonBlockSets.COLUMNAR_FLESH,
                                MalumContent.DungeonBlockSets.FLESHBULB
                        )
                        .bake();
        }
}