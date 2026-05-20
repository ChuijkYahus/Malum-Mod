package com.sammy.malum.common.category;

import com.sammy.malum.*;
import com.sammy.malum.common.block.curiosities.decor.banner.SoulwovenBannerBlockItem;
import com.sammy.malum.core.handlers.hiding.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.*;
import team.lodestar.lodestone.modules.toolkit.creative_tab.CategorizedCreativeTab;
import team.lodestar.lodestone.modules.toolkit.creative_tab.CreativeTabCategory;

import java.util.*;

import static com.sammy.malum.registry.common.MalumContent.*;

public class MalumCreativeTab extends CategorizedCreativeTab {

        private static final ResourceLocation SLOT_WRAPPER = MalumMod.malumPath("slot_wrapper");
        private static final ResourceLocation SLOT_WRAPPER_LEFT = MalumMod.malumPath("slot_wrapper_left");
        private static final ResourceLocation SLOT_WRAPPER_RIGHT = MalumMod.malumPath("slot_wrapper_right");
        private static final ResourceLocation EMPTY_SLOT = MalumMod.malumPath("empty_slot");

        public static final String FUNDAMENTALS_CATEGORY = "fundamentals_of_arcana";
        public static final String GEAR_CATEGORY = "gear_and_trinkets";
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
                                Spirits.SACRED_SPIRIT, Spirits.WICKED_SPIRIT, Spirits.ARCANE_SPIRIT, Spirits.ELDRITCH_SPIRIT,
                                Spirits.AERIAL_SPIRIT, Spirits.AQUEOUS_SPIRIT, Spirits.EARTHEN_SPIRIT, Spirits.INFERNAL_SPIRIT,
                                Spirits.UMBRAL_SPIRIT
                        ).nextLine()
                        .addItems(
                                MalumContent.ENCYCLOPEDIA_ARCANA,
                                MalumContent.ENCYCLOPEDIA_ESOTERICA,
                                Sorcery.SPIRIT_ALTAR,
                                Sorcery.SPIRIT_JAR,
                                Sorcery.RUNEWOOD_OBELISK,
                                Sorcery.BRILLIANT_OBELISK,
                                Sorcery.ARCANA_PYLON
                        ).nextLine()
                        .addItems(
                                Sorcery.MAGEHAND_COFFER,
                                Sorcery.WAND_TINKERER,
                                Sorcery.RUNIC_WORKBENCH,
                                Sorcery.WEAVERS_WORKBENCH
                        ).nextLine()
                        .addItems(
                                Artifice.ARTIFICERS_CLAW,
                                Artifice.CONJUNCTURE_CRYSTALLARIUM,
                                Artifice.WAVECHARGER,
                                Artifice.WAVEBANKER,
                                Artifice.WAVEMAKER,
                                Artifice.WAVEBREAKER,
                                Artifice.GUST_IGNITER,
                                Artifice.WIND_TUNNEL
                        ).nextLine()
                        .addItems(
                                Focusing.TUNING_FORK,
                                Focusing.SPIRIT_CRUCIBLE,
                                Focusing.SPIRIT_CATALYZER,
                                Focusing.REPAIR_PYLON
                        ).nextLine()
                        .addItems(
                                Totemancy.TOTEMIC_STAFF,
                                Totemancy.RUNEWOOD_TOTEM_BASE,
                                Totemancy.SOULWOOD_TOTEM_BASE,
                                Totemancy.WAVEFORM_RUNEWOOD_TOTEM_BASE,
                                Totemancy.WAVEFORM_SOULWOOD_TOTEM_BASE,
                                Totemancy.RITE_ANCHOR,
                                Totemancy.RITE_UNWEAVER,
                                Totemancy.RITE_SPREADER,
                                Totemancy.RITE_CHANNEL
                        )
                        .bake();
                createCategory(GEAR_CATEGORY)
                        .addItems(
                                Gear.CRUDE_SCYTHE,
                                Gear.SOULWOVEN_POUCH,
                                Gear.RAVENOUS_POUCH
                        ).nextLine()
                        .addItems(
                                Gear.SOUL_STAINED_STEEL_HELMET,
                                Gear.SOUL_STAINED_STEEL_CHESTPLATE,
                                Gear.SOUL_STAINED_STEEL_LEGGINGS,
                                Gear.SOUL_STAINED_STEEL_BOOTS,
                                Gear.SOUL_STAINED_STEEL_SWORD,
                                Gear.SOUL_STAINED_STEEL_PICKAXE,
                                Gear.SOUL_STAINED_STEEL_AXE,
                                Gear.SOUL_STAINED_STEEL_SHOVEL,
                                Gear.SOUL_STAINED_STEEL_HOE
                        )
                        .addItems(
                                Gear.SOUL_HUNTER_CLOAK,
                                Gear.SOUL_HUNTER_ROBE,
                                Gear.SOUL_HUNTER_LEGGINGS,
                                Gear.SOUL_HUNTER_BOOTS,
                                Gear.SOUL_STAINED_STEEL_SCYTHE,
                                Gear.SOUL_STAINED_STEEL_KNIFE,
                                Gear.TYRVING,
                                Gear.RAVENOUS_SCYTHE,
                                Gear.GLUTTONOUS_BLUDGEON
                        )
                        .addItems(
                                Gear.MALIGNANT_STRONGHOLD_HELMET,
                                Gear.MALIGNANT_STRONGHOLD_CHESTPLATE,
                                Gear.MALIGNANT_STRONGHOLD_LEGGINGS,
                                Gear.MALIGNANT_STRONGHOLD_BOOTS,
                                Gear.WEIGHT_OF_WORLDS,
                                Gear.EDGE_OF_DELIVERANCE,
                                Gear.SUNDERING_ANCHOR,
                                Gear.SPELLWEAVING_PICKAXE,
                                Gear.SPELLWEAVING_AXE
                        ).nextLine()
                        .addItems(
                                Gear.MNEMONIC_HEX_STAFF,
                                Gear.EROSION_SCEPTER,
                                Gear.UNWINDING_CHAOS
                        ).nextLine()
                        .addItems(
                                Gear.GILDED_RING,
                                Gear.GILDED_BELT,
                                Gear.ORNATE_RING,
                                Gear.ORNATE_NECKLACE
                        )
                        .addItems(
                                Gear.RUNIC_BROOCH,
                                Gear.ELABORATE_BROOCH,
                                Gear.GLASS_BROOCH,
                                Gear.GLUTTONOUS_BROOCH
                        ).nextLine()
                        .addItems(
                                Gear.RING_OF_ESOTERIC_SPOILS,
                                Gear.RING_OF_CURATIVE_TALENT,
                                Gear.RING_OF_ALCHEMICAL_MASTERY,
                                Gear.RING_OF_DESPERATE_VORACITY,
                                Gear.RING_OF_THE_RISING_EDGE,
                                Gear.RING_OF_HEARTY_AVARICE
                        ).nextLine()
                        .addItems(
                                Gear.RING_OF_ESOTERIC_SHADOW,
                                Gear.RING_OF_MANAWEAVING,
                                Gear.RING_OF_ARCANE_PROWESS,
                                Gear.RING_OF_SWARMING_ROT,
                                Gear.RING_OF_THE_HOWLING_MAELSTROM,
                                Gear.RING_OF_HEAVY_DISCHARGE
                        ).nextLine()
                        .addItems(
                                Gear.NECKLACE_OF_MYSTIC_POTENCY,
                                Gear.NECKLACE_OF_THE_NARROW_EDGE,
                                Gear.NECKLACE_OF_THE_WINDWEAVER
                        )
                        .addItems(
                                Gear.BELT_OF_THE_STARVED,
                                Gear.BELT_OF_THE_PROSPECTOR,
                                Gear.BELT_OF_THE_TIDEBOUND,
                                Gear.BELT_OF_OPULENT_INOCULATION,
                                Gear.BELT_OF_THE_MAGEBANE
                        ).nextLine()
                        .addItems(
                                Gear.RING_OF_THE_ENDLESS_WELL,
                                Gear.RING_OF_ECHOING_ARCANA,
                                Gear.RING_OF_GROWING_FLESH,
                                Gear.RING_OF_GRUESOME_CONCENTRATION,
                                Gear.NECKLACE_OF_THE_HIDDEN_BLADE,
                                Gear.NECKLACE_OF_THE_WATCHER,
                                Gear.BELT_OF_THE_LIMITLESS
                        ).nextLine()
                        .addItems(
                                Gear.RUNE_OF_VITALITY,
                                Gear.RUNE_OF_CULLING,
                                Gear.RUNE_OF_REINFORCEMENT,
                                Gear.RUNE_OF_VOLATILE_DISTORTION,
                                Gear.RUNE_OF_DEXTERITY,
                                Gear.RUNE_OF_AILMENT_CLEANSING,
                                Gear.RUNE_OF_PROTECTION,
                                Gear.RUNE_OF_SCORCHING
                        ).nextLine()
                        .addItems(
                                Gear.RUNE_OF_HOWLING_GALE,
                                Gear.RUNE_OF_FLOWING_GRASP,
                                Gear.RUNE_OF_STONE_WARD,
                                Gear.RUNE_OF_BURNING_FERVOR,
                                Gear.RUNE_OF_SKY_TETHER,
                                Gear.RUNE_OF_GOOD_TIDES,
                                Gear.RUNE_OF_OAKEN_MIGHT,
                                Gear.RUNE_OF_FIERY_EMBRACE
                        ).nextLine()
                        .addItems(
                                Gear.RUNE_OF_BOLSTERING,
                                Gear.RUNE_OF_RADIAL_EMPOWERMENT,
                                Gear.RUNE_OF_SPELL_MASTERY,
                                Gear.RUNE_OF_HERESY,
                                Gear.RUNE_OF_UNNATURAL_STAMINA,
                                Gear.RUNE_OF_TWINNED_DURATION,
                                Gear.RUNE_OF_INDOMITABILITY,
                                Gear.RUNE_OF_IGNEOUS_SOLACE
                        )
                        .bake();

                createCategory(MATERIALS_CATEGORY)
                        .addItems(
                                Materials.ROTTING_ESSENCE,
                                Materials.GRIM_TALC,
                                Materials.EERIE_WEAVE,
                                Materials.WARP_FLUX
                        )
                        .addItems(
                                Materials.WIND_NUCLEUS,
                                Materials.PYRE_NUCLEUS
                        ).nextLine()
                        .addItems(
                                Materials.HEX_ASH,
                                Materials.LIVING_FLESH,
                                Materials.ALCHEMICAL_CALX,
                                Materials.ARCANE_CHARCOAL
                        )
                        .addItems(
                                Materials.EBONY_STALK,
                                Materials.CALCIFIED_EBONY,
                                Materials.WILD_WITCHHAZEL,
                                Materials.WITCHHAZEL
                        ).nextLine()
                        .addItems(
                                Materials.NULL_SLATE,
                                Materials.VOID_SALTS,
                                Materials.MNEMONIC_FRAGMENT,
                                Materials.AURIC_EMBERS,
                                Materials.MALIGNANT_LEAD
                        ).nextLine()
                        .addItems(
                                Materials.SOUL_STAINED_STEEL_INGOT,
                                Materials.SOUL_STAINED_STEEL_PLATING,
                                Materials.SOUL_STAINED_STEEL_NUGGET,
                                Materials.HALLOWED_GOLD_INGOT,
                                Materials.HALLOWED_GOLD_INLAY,
                                Materials.HALLOWED_GOLD_NUGGET,
                                Materials.MALIGNANT_PEWTER_INGOT,
                                Materials.MALIGNANT_PEWTER_PLATING,
                                Materials.MALIGNANT_PEWTER_NUGGET
                        ).nextLine()
                        .addItems(
                                Materials.SOULWOVEN_SILK,
                                Materials.PARACAUSAL_FLAME,
                                Materials.CONVOLUTED_LENS,
                                Materials.MIMICRY_RELAY,
                                Materials.POPPET,
                                Materials.IMITATION_FLESH,
                                Materials.IMITATION_HEART
                        ).nextLine()
                        .addItems(
                                Materials.ANOMALOUS_DESIGN,
                                Materials.COMPLETE_DESIGN,
                                Materials.FUSED_CONSCIOUSNESS
                        ).nextLine()
                        .addItems(
                                CompactBlocks.BLOCK_OF_ROTTING_ESSENCE,
                                CompactBlocks.BLOCK_OF_GRIM_TALC,
                                CompactBlocks.BLOCK_OF_EERIE_WEAVE,
                                CompactBlocks.BLOCK_OF_WARP_FLUX
                        )
                        .addItems(
                                CompactBlocks.BLOCK_OF_WIND_NUCLEI,
                                CompactBlocks.BLOCK_OF_PYRE_NUCLEI
                        ).nextLine()
                        .addItems(
                                CompactBlocks.BLOCK_OF_HEX_ASH,
                                CompactBlocks.BLOCK_OF_LIVING_FLESH,
                                CompactBlocks.BLOCK_OF_ALCHEMICAL_CALX,
                                CompactBlocks.BLOCK_OF_ARCANE_CHARCOAL
                        )
                        .addItems(
                                CompactBlocks.BLOCK_OF_EBONY,
                                CompactBlocks.CRATE_OF_WITCHHAZEL
                        ).nextLine()
                        .addItems(
                                CompactBlocks.BLOCK_OF_NULL_SLATE,
                                CompactBlocks.BLOCK_OF_VOID_SALTS,
                                CompactBlocks.BLOCK_OF_MNEMONIC_FRAGMENT,
                                CompactBlocks.BLOCK_OF_AURIC_EMBERS,
                                CompactBlocks.BLOCK_OF_MALIGNANT_LEAD
                        ).nextLine()
                        .addItems(
                                CompactBlocks.BLOCK_OF_SOUL_STAINED_STEEL,
                                CompactBlocks.BLOCK_OF_HALLOWED_GOLD,
                                CompactBlocks.BLOCK_OF_MALIGNANT_PEWTER
                        ).bake();
                createCategory(ORES_CATEGORY)
                        .addItems(
                                CompactBlocks.BLOCK_OF_RAW_SOULSTONE, CompactBlocks.BLOCK_OF_REFINED_SOULSTONE, Materials.DEEPSLATE_SOULSTONE_ORE, Materials.SOULSTONE_ORE,
                                Materials.RAW_SOULSTONE, Materials.REFINED_SOULSTONE, Materials.SOULSTONE_BUD, Materials.REALIZED_SOULSTONE_BUD
                        ).nextLine()
                        .addItems(
                                CompactBlocks.BLOCK_OF_RAW_BRILLIANCE, CompactBlocks.BLOCK_OF_BRILLIANCE, Materials.BRILLIANT_DEEPSLATE, Materials.BRILLIANT_STONE,
                                Materials.RAW_BRILLIANCE, Materials.REFINED_BRILLIANCE
                        ).nextLine()
                        .addItems(
                                CompactBlocks.BLOCK_OF_BLAZING_QUARTZ, Materials.BLAZING_QUARTZ_ORE,
                                Materials.BLAZING_QUARTZ
                        ).nextLine()
                        .addItems(
                                CompactBlocks.BLOCK_OF_CTHONIC_GOLD, Materials.CTHONIC_GOLD_ORE,
                                Materials.CTHONIC_GOLD, Materials.CTHONIC_GOLD_FRAGMENT
                        )
                        .nextLine()
                        .addItems(Materials.MUNDANE_QUARTZ::addToCreativeTab)
                        .addItems(Materials.VIVID_AMETRINE::addToCreativeTab)
                        .nextLine()
                        .addItems(Materials.MARINE_AGATE::addToCreativeTab)
                        .addItems(Materials.RUGGED_CITRINE::addToCreativeTab)
                        .bake();
                createCategory(ETHERWORKS_CATEGORY)
                        .addItems(
                                BlockSets.ETHER,
                                BlockSets.ETHER_CANDLE,
                                BlockSets.ETHER_TORCH,

                                BlockSets.ETHER_BRAZIER,
                                BlockSets.ETHER_CRESSET
                        ).nextLine()
                        .addItems(
                                BlockSets.IRIDESCENT_ETHER,
                                BlockSets.IRIDESCENT_ETHER_CANDLE,
                                BlockSets.IRIDESCENT_ETHER_TORCH,
                                BlockSets.IRIDESCENT_ETHER_BRAZIER,
                                BlockSets.IRIDESCENT_ETHER_CRESSET
                        )
                        .bake();
                createCategory(SOULSTUFF_CATEGORY)
                        .addItems(
                                BlockSets.SACRED_SPIRITED_GLASS,
                                BlockSets.WICKED_SPIRITED_GLASS,
                                BlockSets.ARCANE_SPIRITED_GLASS,
                                BlockSets.ELDRITCH_SPIRITED_GLASS,
                                BlockSets.AERIAL_SPIRITED_GLASS,
                                BlockSets.AQUEOUS_SPIRITED_GLASS,
                                BlockSets.EARTHEN_SPIRITED_GLASS,
                                BlockSets.INFERNAL_SPIRITED_GLASS,
                                BlockSets.NULL_SPIRITED_GLASS
                        ).nextLine()
                        .addItems(
                                BlockSets.SACRED_VARNISHED_TERRACOTTA,
                                BlockSets.WICKED_VARNISHED_TERRACOTTA,
                                BlockSets.ARCANE_VARNISHED_TERRACOTTA,
                                BlockSets.ELDRITCH_VARNISHED_TERRACOTTA,
                                BlockSets.AERIAL_VARNISHED_TERRACOTTA,
                                BlockSets.AQUEOUS_VARNISHED_TERRACOTTA,
                                BlockSets.EARTHEN_VARNISHED_TERRACOTTA,
                                BlockSets.INFERNAL_VARNISHED_TERRACOTTA,
                                BlockSets.NULL_VARNISHED_TERRACOTTA
                        ).nextLine()
                        .addItems(SoulwovenBannerBlockItem::addBannerPatterns)
                        .bake();
                createCategory(RUNEWOOD_CATEGORY)
                        .addItems(
                                Materials.RUNIC_SAP_BOTTLE,
                                Materials.RUNIC_SAPBALL,
                                BlockSets.RUNEWOOD_SAPLING,
                                BlockSets.RUNEWOOD_LEAVES,
                                BlockSets.HANGING_RUNEWOOD_LEAVES,
                                BlockSets.AZURE_RUNEWOOD_SAPLING,
                                BlockSets.AZURE_RUNEWOOD_LEAVES,
                                BlockSets.HANGING_AZURE_RUNEWOOD_LEAVES
                        ).nextLine()
                        .addItems(BlockSets.RUNEWOOD_SET::addToCreativeTab)
                        .addItems(
                                BlockSets.RUNEWOOD_BOAT,
                                BlockSets.RUNEWOOD_CHEST_BOAT
                        )
                        .bake();
                createCategory(SOULWOOD_CATEGORY)
                        .addItems(
                                Materials.AZOIC_SAP_BOTTLE,
                                Materials.AZOIC_SAPBALL,
                                BlockSets.SOULWOOD_SAPLING,
                                BlockSets.SOULWOOD_LEAVES,
                                BlockSets.HANGING_SOULWOOD_LEAVES
                        ).nextLine()
                        .addItems(BlockSets.SOULWOOD_SET::addToCreativeTab)
                        .addItems(
                                BlockSets.SOULWOOD_BOAT,
                                BlockSets.SOULWOOD_CHEST_BOAT
                        )
                        .bake();
                createCategory(BLIGHT_CATEGORY)
                        .addItems(
                                BlockSets.BLIGHTED_SOULWOOD,
                                Blight.COLUMNAR_BLIGHT,
                                Blight.BLIGHTED_EARTH,
                                Blight.BLIGHT,
                                Blight.BLIGHTED_GUNK,
                                Blight.BLIGHTPEARL,
                                Blight.BLIGHTROOT
                        ).nextLine()
                        .addItems(
                                Blight.SCARSTONE,
                                Blight.LARGE_STRANGE_CRYSTAL,
                                Blight.STRANGE_CRYSTAL,
                                Blight.STRANGEROOT
                        )
                        .bake();

                createCategory(TAINTED_ROCK_CATEGORY)
                        .addItems(BlockSets.TAINTED_ROCK_SET::addToCreativeTab)
                        .bake();
                createCategory(TWISTED_ROCK_CATEGORY)
                        .addItems(BlockSets.TWISTED_ROCK_SET::addToCreativeTab)
                        .bake();
                createCategory(DUNGEON_REGALIA)
                        .addItems(
                                DungeonBlockSets.OMINOUS_ALTAR,
                                DungeonBlockSets.OMINOUS_OBELISK
                        )
                        .addItems(
                                DungeonGear.BROKEN_BLADE,
                                DungeonGear.SHAPED_SLAB
                        ).nextLine()
                        .addItems(
                                DungeonGear.IRON_CROWN,
                                DungeonBlockSets.VEILED_EFFIGY,
                                DungeonBlockSets.CORRUPT_EFFIGY,
                                DungeonBlockSets.CRACKED_EFFIGY
                        )
                        .bake();
                createCategory(DUNGEON_ARCHITECTURE)
                        .addItems(
                                DungeonBlockSets.ODD_SCRIPTURES_I,
                                DungeonBlockSets.ODD_SCRIPTURES_II,
                                DungeonBlockSets.ODD_SCRIPTURES_III,
                                DungeonBlockSets.ODD_SCRIPTURES_IV,
                                DungeonBlockSets.ODD_SCRIPTURES_V,
                                DungeonBlockSets.ODD_SCRIPTURES_VI,
                                DungeonBlockSets.ODD_SCRIPTURES_VII,
                                DungeonBlockSets.ODD_SCRIPTURES_VIII,
                                DungeonBlockSets.ODD_SCRIPTURES_IX
                        )
                        .addItems(
                                DungeonBlockSets.DROSS_STONE,
                                DungeonBlockSets.POLISHED_DROSS_STONE,
                                DungeonBlockSets.DROSS_STONE_BRICKS,
                                DungeonBlockSets.DROSS_STONE_TILES,
                                DungeonBlockSets.DROSS_STONE_MOSAIC,
                                DungeonBlockSets.DARK_DROSS_TILES,
                                DungeonBlockSets.GRAY_DROSS_TILES
                        )
                        .addItems(
                                DungeonBlockSets.DROSS_STONE_COLUMN,
                                DungeonBlockSets.DROSS_STONE_ALTAR
                        ).nextLine()
                        .addItems(
                                DungeonBlockSets.DROSS_STONE_STAIRS,
                                DungeonBlockSets.POLISHED_DROSS_STONE_STAIRS,
                                DungeonBlockSets.DROSS_STONE_BRICKS_STAIRS,
                                DungeonBlockSets.DROSS_STONE_TILES_STAIRS,
                                DungeonBlockSets.DROSS_STONE_MOSAIC_STAIRS,
                                DungeonBlockSets.DARK_DROSS_TILES_STAIRS,
                                DungeonBlockSets.GRAY_DROSS_TILES_STAIRS
                        )
                        .addItems(
                                DungeonBlockSets.CUT_DROSS_STONE,
                                DungeonBlockSets.CHISELED_DROSS_STONE
                        ).nextLine()
                        .addItems(
                                DungeonBlockSets.DROSS_STONE_SLAB,
                                DungeonBlockSets.POLISHED_DROSS_STONE_SLAB,
                                DungeonBlockSets.DROSS_STONE_BRICKS_SLAB,
                                DungeonBlockSets.DROSS_STONE_TILES_SLAB,
                                DungeonBlockSets.DROSS_STONE_MOSAIC_SLAB,
                                DungeonBlockSets.DARK_DROSS_TILES_SLAB,
                                DungeonBlockSets.GRAY_DROSS_TILES_SLAB
                        )
                        .addItems(
                                DungeonBlockSets.DROSS_STONE_ITEM_PEDESTAL,
                                DungeonBlockSets.DROSS_STONE_ITEM_STAND
                        ).nextLine()
                        .addItems(
                                DungeonBlockSets.DROSS_STONE_WALL,
                                DungeonBlockSets.POLISHED_DROSS_STONE_WALL,
                                DungeonBlockSets.DROSS_STONE_BRICKS_WALL,
                                DungeonBlockSets.DROSS_STONE_TILES_WALL,
                                DungeonBlockSets.DROSS_STONE_MOSAIC_WALL,
                                DungeonBlockSets.DARK_DROSS_TILES_WALL,
                                DungeonBlockSets.GRAY_DROSS_TILES_WALL
                        )
                        .addItems(
                                DungeonBlockSets.DROSS_STONE_PRESSURE_PLATE,
                                DungeonBlockSets.DROSS_STONE_BUTTON
                        )
                        .bake();
                createCategory(DUNGEON_ODDITIES)
                        .addItems(
                                DungeonBlockSets.WRITHING_FLESH,
                                DungeonBlockSets.COLUMNAR_FLESH,
                                DungeonBlockSets.FLESHBULB
                        )
                        .bake();
        }
}