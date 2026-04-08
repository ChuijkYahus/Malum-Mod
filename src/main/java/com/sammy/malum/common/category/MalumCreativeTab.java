package com.sammy.malum.common.category;

import com.sammy.malum.*;
import com.sammy.malum.common.item.banner.SoulwovenBannerBlockItem;
import com.sammy.malum.common.item.metallics.MetallicsItemRegistryBundle;
import com.sammy.malum.core.handlers.hiding.*;
import com.sammy.malum.registry.common.block.MalumBlocks;
import com.sammy.malum.registry.common.item.MalumItems;
import net.minecraft.resources.*;
import net.minecraft.world.item.*;
import team.lodestar.lodestone.modules.toolkit.creative_tab.CategorizedCreativeTab;
import team.lodestar.lodestone.modules.toolkit.creative_tab.CreativeTabCategory;

import java.util.*;

import static com.sammy.malum.registry.common.block.MalumBlocks.*;
import static com.sammy.malum.registry.common.item.MalumItems.*;

public class MalumCreativeTab extends CategorizedCreativeTab {

        private static final ResourceLocation SLOT_WRAPPER = MalumMod.malumPath("slot_wrapper");
        private static final ResourceLocation SLOT_WRAPPER_LEFT = MalumMod.malumPath("slot_wrapper_left");
        private static final ResourceLocation SLOT_WRAPPER_RIGHT = MalumMod.malumPath("slot_wrapper_right");
        private static final ResourceLocation EMPTY_SLOT = MalumMod.malumPath("empty_slot");

        public static final String FUNDAMENTALS = "fundamentals_of_arcana";
        public static final String GEAR = "gear_and_trinkets";
        public static final String ARTIFICE = "alchemy_and_artifice";
        public static final String ALCHEMICAL_FOCI = "alchemical_foci";
        public static final String MATERIALS = "soulformed_substance";
        public static final String ORES = "earthen_treasures";
        public static final String ETHER = "ether";
        public static final String SOULSTUFF = "soulstuff";
        public static final String RUNEWOOD = "runewood";
        public static final String SOULWOOD = "soulwood";
        public static final String BLIGHT = "blight";
        public static final String TAINTED_ROCK = "tainted_rock";
        public static final String TWISTED_ROCK = "twisted_rock";

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
                createCategory(FUNDAMENTALS)
                        .addItems(
                                SACRED_SPIRIT, WICKED_SPIRIT, ARCANE_SPIRIT, ELDRITCH_SPIRIT,
                                AERIAL_SPIRIT, AQUEOUS_SPIRIT, EARTHEN_SPIRIT, INFERNAL_SPIRIT,
                                UMBRAL_SPIRIT
                        ).nextLine()
                        .addItems(
                                ENCYCLOPEDIA_ARCANA,
                                ENCYCLOPEDIA_ESOTERICA,
                                SPIRIT_ALTAR::getItem,
                                RUNEWOOD_OBELISK::getItem,
                                BRILLIANT_OBELISK::getItem,
                                SPIRIT_JAR::getItem,
                                RUNIC_WORKBENCH::getItem,
                                SOUL_BRAZIER::getItem
                        ).nextLine()
                        .addItems(
                                RUNEWOOD_TOTEM_BASE::getItem,
                                SOULWOOD_TOTEM_BASE::getItem,
                                WAVEFORM_RUNEWOOD_TOTEM_BASE::getItem,
                                WAVEFORM_SOULWOOD_TOTEM_BASE::getItem,
                                ARCANA_PYLON::getItem,
                                RITE_ANCHOR::getItem,
                                RITE_UNWEAVER::getItem,
                                RITE_SPREADER::getItem,
                                RITE_CHANNEL::getItem
                        )
                        .bake();
                createCategory(GEAR)
                        .addItems(
                                CRUDE_SCYTHE,
                                SOULWOVEN_POUCH,
                                RAVENOUS_POUCH,
                                TOTEMIC_STAFF,
                                ARTIFICERS_CLAW,
                                TUNING_FORK,
                                LAMPLIGHTERS_TONGS,
                                CATALYST_LOBBER,
                                CONCENTRATED_GLUTTONY,
                                SPLASH_OF_GLUTTONY
                        ).nextLine()
                        .addItems(
                                SOUL_STAINED_STEEL_HELMET,
                                SOUL_STAINED_STEEL_CHESTPLATE,
                                SOUL_STAINED_STEEL_LEGGINGS,
                                SOUL_STAINED_STEEL_BOOTS,
                                SOUL_STAINED_STEEL_SWORD,
                                SOUL_STAINED_STEEL_PICKAXE,
                                SOUL_STAINED_STEEL_AXE,
                                SOUL_STAINED_STEEL_SHOVEL,
                                SOUL_STAINED_STEEL_HOE
                        )
                        .addItems(
                                SOUL_HUNTER_CLOAK,
                                SOUL_HUNTER_ROBE,
                                SOUL_HUNTER_LEGGINGS,
                                SOUL_HUNTER_BOOTS,
                                SOUL_STAINED_STEEL_SCYTHE,
                                SOUL_STAINED_STEEL_KNIFE,
                                TYRVING,
                                RAVENOUS_SCYTHE,
                                GLUTTONOUS_BLUDGEON
                        )
                        .addItems(
                                MALIGNANT_STRONGHOLD_HELMET,
                                MALIGNANT_STRONGHOLD_CHESTPLATE,
                                MALIGNANT_STRONGHOLD_LEGGINGS,
                                MALIGNANT_STRONGHOLD_BOOTS,
                                WEIGHT_OF_WORLDS,
                                EDGE_OF_DELIVERANCE,
                                SUNDERING_ANCHOR,
                                SPELLWEAVING_PICKAXE,
                                SPELLWEAVING_AXE
                        ).nextLine()
                        .addItems(
                                MNEMONIC_HEX_STAFF,
                                EROSION_SCEPTER,
                                UNWINDING_CHAOS
                        ).nextLine()
                        .addItems(
                                GILDED_RING,
                                GILDED_BELT,
                                ORNATE_RING,
                                ORNATE_NECKLACE
                        )
                        .addItems(
                                RUNIC_BROOCH,
                                ELABORATE_BROOCH,
                                GLASS_BROOCH,
                                GLUTTONOUS_BROOCH
                        ).nextLine()
                        .addItems(
                                RING_OF_ESOTERIC_SPOILS,
                                RING_OF_CURATIVE_TALENT,
                                RING_OF_ALCHEMICAL_MASTERY,
                                RING_OF_DESPERATE_VORACITY,
                                RING_OF_THE_RISING_EDGE,
                                RING_OF_HEARTY_AVARICE
                        ).nextLine()
                        .addItems(
                                RING_OF_ESOTERIC_SHADOW,
                                RING_OF_MANAWEAVING,
                                RING_OF_ARCANE_PROWESS,
                                RING_OF_SWARMING_ROT,
                                RING_OF_THE_HOWLING_MAELSTROM,
                                RING_OF_HEAVY_DISCHARGE
                        ).nextLine()
                        .addItems(
                                NECKLACE_OF_MYSTIC_POTENCY,
                                NECKLACE_OF_THE_NARROW_EDGE
                        )
                        .addItems(
                                BELT_OF_THE_STARVED,
                                BELT_OF_THE_PROSPECTOR,
                                BELT_OF_THE_MAGEBANE
                        ).nextLine()
                        .addItems(
                                RING_OF_THE_ENDLESS_WELL,
                                RING_OF_ECHOING_ARCANA,
                                RING_OF_GROWING_FLESH,
                                RING_OF_GRUESOME_CONCENTRATION,
                                NECKLACE_OF_THE_HIDDEN_BLADE,
                                NECKLACE_OF_THE_WATCHER,
                                BELT_OF_THE_LIMITLESS
                        ).nextLine()
                        .addItems(
                                RUNE_OF_VITALITY,
                                RUNE_OF_CULLING,
                                RUNE_OF_REINFORCEMENT,
                                RUNE_OF_VOLATILE_DISTORTION,
                                RUNE_OF_DEXTERITY,
                                RUNE_OF_AILMENT_CLEANSING,
                                RUNE_OF_PROTECTION,
                                RUNE_OF_SCORCHING
                        ).nextLine()
                        .addItems(
                                RUNE_OF_HOWLING_GALE,
                                RUNE_OF_FLOWING_GRASP,
                                RUNE_OF_STONE_WARD,
                                RUNE_OF_BURNING_FERVOR,
                                RUNE_OF_SKY_TETHER,
                                RUNE_OF_GOOD_TIDES,
                                RUNE_OF_OAKEN_MIGHT,
                                RUNE_OF_FIERY_EMBRACE
                        ).nextLine()
                        .addItems(
                                RUNE_OF_BOLSTERING,
                                RUNE_OF_RADIAL_EMPOWERMENT,
                                RUNE_OF_SPELL_MASTERY,
                                RUNE_OF_HERESY,
                                RUNE_OF_UNNATURAL_STAMINA,
                                RUNE_OF_TWINNED_DURATION,
                                RUNE_OF_INDOMITABILITY,
                                RUNE_OF_IGNEOUS_SOLACE
                        )
                        .bake();
                createCategory(ARTIFICE)
                        .addItems(
                                TUNING_FORK,
                                SPIRIT_CRUCIBLE::getItem,
                                SPIRIT_CATALYZER::getItem,
                                REPAIR_PYLON::getItem
                        ).nextLine()
                        .addItems(
                                WAVECHARGER::getItem,
                                WAVEBANKER::getItem,
                                WAVEMAKER::getItem,
                                WAVEBREAKER::getItem,
                                GUST_IGNITER::getItem,
                                WIND_TUNNEL::getItem
                        ).nextLine()
                        .addItems(
                                MENDING_DIFFUSER,
                                IMPURITY_STABILIZER,
                                SHIELDING_APPARATUS,
                                WARPING_ENGINE,
                                ACCELERATING_INLAY,
                                PRISMATIC_FOCUS_LENS,
                                BLAZING_DIODE,
                                INTRICATE_ASSEMBLY
                        ).nextLine()
                        .addItems(
                                SYMPATHY_DRIVE,
                                SUSPICIOUS_DEVICE,
                                CAUSTIC_CATALYST,
                                RESONANCE_TUNER,
                                STELLAR_MECHANISM
                        )
                        .bake();
                createCategory(ALCHEMICAL_FOCI)
                        .addItems(ALCHEMICAL_IMPETUS, FRACTURED_ALCHEMICAL_IMPETUS)
                        .addItems(ZEPHYR_IMPETUS, FRACTURED_ZEPHYR_IMPETUS)
                        .addItems(IFRIT_IMPETUS, FRACTURED_IFRIT_IMPETUS)
                        .nextLine()

                        .addItems(IRON_METALLICS::addToCreativeTab)
                        .addItems(COPPER_METALLICS::addToCreativeTab)
                        .addItems(GOLD_METALLICS::addToCreativeTab)
                        .addItems(ZINC_METALLICS::addToCreativeTab)
                        .addItems(LEAD_METALLICS::addToCreativeTab)
                        .addItems(SILVER_METALLICS::addToCreativeTab)
                        .addItems(ALUMINIUM_METALLICS::addToCreativeTab)
                        .addItems(NICKEL_METALLICS::addToCreativeTab)

                        .bake();
                createCategory(MATERIALS)
                        .addItems(
                                ROTTING_ESSENCE,
                                GRIM_TALC,
                                EERIE_WEAVE,
                                WARP_FLUX
                        )
                        .addItems(
                                WIND_NUCLEUS,
                                PYRE_NUCLEUS
                        ).nextLine()
                        .addItems(
                                HEX_ASH,
                                LIVING_FLESH,
                                ALCHEMICAL_CALX,
                                ARCANE_CHARCOAL
                        )
                        .addItems(
                                EBONY_STALK,
                                EBONY,
                                WILD_WITCHHAZEL,
                                WITCHHAZEL
                        ).nextLine()
                        .addItems(
                                NULL_SLATE,
                                VOID_SALTS,
                                MNEMONIC_FRAGMENT,
                                AURIC_EMBERS,
                                MALIGNANT_LEAD
                        ).nextLine()
                        .addItems(
                                SOUL_STAINED_STEEL_INGOT,
                                SOUL_STAINED_STEEL_PLATING,
                                SOUL_STAINED_STEEL_NUGGET,
                                HALLOWED_GOLD_INGOT,
                                HALLOWED_GOLD_INLAY,
                                HALLOWED_GOLD_NUGGET,
                                MALIGNANT_PEWTER_INGOT,
                                MALIGNANT_PEWTER_PLATING,
                                MALIGNANT_PEWTER_NUGGET
                        ).nextLine()
                        .addItems(
                                SOULWOVEN_SILK,
                                PARACAUSAL_FLAME,
                                CONVOLUTED_LENS,
                                MIMICRY_RELAY,
                                POPPET,
                                IMITATION_FLESH,
                                IMITATION_HEART
                        ).nextLine()
                        .addItems(
                                ANOMALOUS_DESIGN,
                                COMPLETE_DESIGN,
                                FUSED_CONSCIOUSNESS
                        ).nextLine()
                        .addItems(
                                BLOCK_OF_ROTTING_ESSENCE,
                                BLOCK_OF_GRIM_TALC,
                                BLOCK_OF_EERIE_WEAVE,
                                BLOCK_OF_WARP_FLUX
                        )
                        .addItems(
                                BLOCK_OF_WIND_NUCLEI,
                                BLOCK_OF_PYRE_NUCLEI
                        ).nextLine()
                        .addItems(
                                BLOCK_OF_HEX_ASH,
                                BLOCK_OF_LIVING_FLESH,
                                BLOCK_OF_ALCHEMICAL_CALX,
                                BLOCK_OF_ARCANE_CHARCOAL
                        )
                        .addItems(
                                BLOCK_OF_EBONY,
                                CRATE_OF_WITCHHAZEL
                        ).nextLine()
                        .addItems(
                                BLOCK_OF_NULL_SLATE,
                                BLOCK_OF_VOID_SALTS,
                                BLOCK_OF_MNEMONIC_FRAGMENT,
                                BLOCK_OF_AURIC_EMBERS,
                                BLOCK_OF_MALIGNANT_LEAD
                        ).nextLine()
                        .addItems(
                                BLOCK_OF_SOUL_STAINED_STEEL,
                                BLOCK_OF_HALLOWED_GOLD,
                                BLOCK_OF_MALIGNANT_PEWTER
                        ).bake();
                createCategory(ORES)
                        .addItems(
                                BLOCK_OF_SOULSTONE, BLOCK_OF_RAW_SOULSTONE, DEEPSLATE_SOULSTONE_ORE, SOULSTONE_ORE,
                                RAW_SOULSTONE, CRUSHED_SOULSTONE, REFINED_SOULSTONE
                        ).nextLine()
                        .addItems(
                                BLOCK_OF_BRILLIANCE, BLOCK_OF_RAW_BRILLIANCE, BRILLIANT_DEEPSLATE, BRILLIANT_STONE,
                                RAW_BRILLIANCE, CRUSHED_BRILLIANCE, REFINED_BRILLIANCE
                        ).nextLine()
                        .addItems(
                                BLOCK_OF_BLAZING_QUARTZ, BLAZING_QUARTZ_ORE,
                                BLAZING_QUARTZ
                        )
                        .addItems(
                                BLOCK_OF_NATURAL_QUARTZ, DEEPSLATE_QUARTZ_ORE, NATURAL_QUARTZ_ORE,
                                NATURAL_QUARTZ
                        ).nextLine()
                        .addItems(
                                BLOCK_OF_CTHONIC_GOLD, CTHONIC_GOLD_ORE,
                                CTHONIC_GOLD, CTHONIC_GOLD_FRAGMENT
                        )
                        .bake();
                createCategory(ETHER)
                        .addItems(
                                ETHER,
                                ETHER_CANDLE,
                                ETHER_TORCH,

                                TAINTED_ETHER_BRAZIER,
                                TWISTED_ETHER_BRAZIER,
                                DROSS_ETHER_BRAZIER,

                                TAINTED_ETHER_CRESSET,
                                TWISTED_ETHER_CRESSET,
                                DROSS_ETHER_CRESSET
                        ).nextLine()
                        .addItems(
                                IRIDESCENT_ETHER,
                                IRIDESCENT_ETHER_CANDLE,
                                IRIDESCENT_ETHER_TORCH,

                                TAINTED_IRIDESCENT_ETHER_BRAZIER,
                                TWISTED_IRIDESCENT_ETHER_BRAZIER,
                                DROSS_IRIDESCENT_ETHER_BRAZIER,

                                TAINTED_IRIDESCENT_ETHER_CRESSET,
                                TWISTED_IRIDESCENT_ETHER_CRESSET,
                                DROSS_IRIDESCENT_ETHER_CRESSET
                        )
                        .bake();
                createCategory(SOULSTUFF)
                        .addItems(
                                SACRED_SPIRITED_GLASS,
                                WICKED_SPIRITED_GLASS,
                                ARCANE_SPIRITED_GLASS,
                                ELDRITCH_SPIRITED_GLASS,
                                AERIAL_SPIRITED_GLASS,
                                AQUEOUS_SPIRITED_GLASS,
                                EARTHEN_SPIRITED_GLASS,
                                INFERNAL_SPIRITED_GLASS,
                                NULL_SPIRITED_GLASS
                        ).nextLine()
                        .addItems(
                                SACRED_VARNISHED_TERRACOTTA,
                                WICKED_VARNISHED_TERRACOTTA,
                                ARCANE_VARNISHED_TERRACOTTA,
                                ELDRITCH_VARNISHED_TERRACOTTA,
                                AERIAL_VARNISHED_TERRACOTTA,
                                AQUEOUS_VARNISHED_TERRACOTTA,
                                EARTHEN_VARNISHED_TERRACOTTA,
                                INFERNAL_VARNISHED_TERRACOTTA,
                                NULL_VARNISHED_TERRACOTTA
                        ).nextLine()
                        .addItems(SoulwovenBannerBlockItem::addBannerPatterns)
                        .bake();
                createCategory(RUNEWOOD)
                        .addItems(
                                RUNIC_SAP,
                                RUNIC_SAPBALL,
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
                createCategory(SOULWOOD)
                        .addItems(
                                CURSED_SAP,
                                CURSED_SAPBALL,
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
                createCategory(BLIGHT)
                        .addItems(
                                BLIGHTED_SOULWOOD,
                                COLUMNAR_BLIGHT,
                                BLIGHTED_EARTH,
                                BLIGHT,
                                BLIGHTED_GUNK,
                                BLIGHTPEARL,
                                BLIGHTROOT
                        ).nextLine()
                        .addItems(
                                SCARSTONE,
                                LARGE_STRANGE_CRYSTAL,
                                STRANGE_CRYSTAL,
                                STRANGEROOT
                        )
                        .bake();

                createCategory(TAINTED_ROCK)
                        .addItems(
                                TAINTED_ROCK,
                                POLISHED_TAINTED_ROCK,
                                TAINTED_ROCK_BRICKS,
                                TAINTED_ROCK_TILES,
                                TAINTED_ROCK_MOSAIC
                        )
                        .addItems(
                                TAINTED_ROCK_COLUMN,
                                TAINTED_ROCK_ALTAR,
                                CUT_TAINTED_ROCK,
                                CHISELED_TAINTED_ROCK
                        ).nextLine()
                        .addItems(
                                TAINTED_ROCK_STAIRS,
                                POLISHED_TAINTED_ROCK_STAIRS,
                                TAINTED_ROCK_BRICKS_STAIRS,
                                TAINTED_ROCK_TILES_STAIRS,
                                TAINTED_ROCK_MOSAIC_STAIRS
                        )
                        .addItems(
                                TAINTED_ROCK_ITEM_PEDESTAL,
                                TAINTED_ROCK_ITEM_STAND,
                                TAINTED_ROCK_PRESSURE_PLATE,
                                TAINTED_ROCK_BUTTON
                        ).nextLine()
                        .addItems(
                                TAINTED_ROCK_SLAB,
                                POLISHED_TAINTED_ROCK_SLAB,
                                TAINTED_ROCK_BRICKS_SLAB,
                                TAINTED_ROCK_TILES_SLAB,
                                TAINTED_ROCK_MOSAIC_SLAB
                        ).nextLine()
                        .addItems(
                                TAINTED_ROCK_WALL,
                                POLISHED_TAINTED_ROCK_WALL,
                                TAINTED_ROCK_BRICKS_WALL,
                                TAINTED_ROCK_TILES_WALL,
                                TAINTED_ROCK_MOSAIC_WALL
                        )
                        .bake();
                createCategory(TWISTED_ROCK)
                        .addItems(
                                TWISTED_ROCK,
                                POLISHED_TWISTED_ROCK,
                                TWISTED_ROCK_BRICKS,
                                TWISTED_ROCK_TILES,
                                TWISTED_ROCK_MOSAIC
                        )
                        .addItems(
                                TWISTED_ROCK_COLUMN,
                                TWISTED_ROCK_ALTAR,
                                CUT_TWISTED_ROCK,
                                CHISELED_TWISTED_ROCK
                        ).nextLine()
                        .addItems(
                                TWISTED_ROCK_STAIRS,
                                POLISHED_TWISTED_ROCK_STAIRS,
                                TWISTED_ROCK_BRICKS_STAIRS,
                                TWISTED_ROCK_TILES_STAIRS,
                                TWISTED_ROCK_MOSAIC_STAIRS
                        )
                        .addItems(
                                TWISTED_ROCK_ITEM_PEDESTAL,
                                TWISTED_ROCK_ITEM_STAND,
                                TWISTED_ROCK_PRESSURE_PLATE,
                                TWISTED_ROCK_BUTTON
                        ).nextLine()
                        .addItems(
                                TWISTED_ROCK_SLAB,
                                POLISHED_TWISTED_ROCK_SLAB,
                                TWISTED_ROCK_BRICKS_SLAB,
                                TWISTED_ROCK_TILES_SLAB,
                                TWISTED_ROCK_MOSAIC_SLAB
                        ).nextLine()
                        .addItems(
                                TWISTED_ROCK_WALL,
                                POLISHED_TWISTED_ROCK_WALL,
                                TWISTED_ROCK_BRICKS_WALL,
                                TWISTED_ROCK_TILES_WALL,
                                TWISTED_ROCK_MOSAIC_WALL
                        )
                        .bake();
                createCategory(DUNGEON_REGALIA)
                        .addItems(
                                OMINOUS_ALTAR,
                                OMINOUS_OBELISK
                        )
                        .addItems(
                                BROKEN_BLADE,
                                SHAPED_SLAB
                        ).nextLine()
                        .addItems(
                                IRON_CROWN,
                                VEILED_EFFIGY,
                                CORRUPT_EFFIGY,
                                CRACKED_EFFIGY
                        )
                        .bake();
                createCategory(DUNGEON_ARCHITECTURE)
                        .addItems(
                                ODD_SCRIPTURES_I,
                                ODD_SCRIPTURES_II,
                                ODD_SCRIPTURES_III,
                                ODD_SCRIPTURES_IV,
                                ODD_SCRIPTURES_V,
                                ODD_SCRIPTURES_VI,
                                ODD_SCRIPTURES_VII,
                                ODD_SCRIPTURES_VIII,
                                ODD_SCRIPTURES_IX
                        )
                        .addItems(
                                DROSS_STONE,
                                POLISHED_DROSS_STONE,
                                DROSS_STONE_BRICKS,
                                DROSS_STONE_TILES,
                                DROSS_STONE_MOSAIC,
                                DARK_DROSS_TILES,
                                GRAY_DROSS_TILES
                        )
                        .addItems(
                                DROSS_STONE_COLUMN,
                                DROSS_STONE_ALTAR
                        ).nextLine()
                        .addItems(
                                DROSS_STONE_STAIRS,
                                POLISHED_DROSS_STONE_STAIRS,
                                DROSS_STONE_BRICKS_STAIRS,
                                DROSS_STONE_TILES_STAIRS,
                                DROSS_STONE_MOSAIC_STAIRS,
                                DARK_DROSS_TILES_STAIRS,
                                GRAY_DROSS_TILES_STAIRS
                        )
                        .addItems(
                                CUT_DROSS_STONE,
                                CHISELED_DROSS_STONE
                        ).nextLine()
                        .addItems(
                                DROSS_STONE_SLAB,
                                POLISHED_DROSS_STONE_SLAB,
                                DROSS_STONE_BRICKS_SLAB,
                                DROSS_STONE_TILES_SLAB,
                                DROSS_STONE_MOSAIC_SLAB,
                                DARK_DROSS_TILES_SLAB,
                                GRAY_DROSS_TILES_SLAB
                        )
                        .addItems(
                                DROSS_STONE_ITEM_PEDESTAL,
                                DROSS_STONE_ITEM_STAND
                        ).nextLine()
                        .addItems(
                                DROSS_STONE_WALL,
                                POLISHED_DROSS_STONE_WALL,
                                DROSS_STONE_BRICKS_WALL,
                                DROSS_STONE_TILES_WALL,
                                DROSS_STONE_MOSAIC_WALL,
                                DARK_DROSS_TILES_WALL,
                                GRAY_DROSS_TILES_WALL
                        )
                        .addItems(
                                DROSS_STONE_PRESSURE_PLATE,
                                DROSS_STONE_BUTTON
                        )
                        .bake();
                createCategory(DUNGEON_ODDITIES)
                        .addItems(
                                WRITHING_FLESH,
                                COLUMNAR_FLESH,
                                FLESHBULB
                        )
                        .bake();
        }
}