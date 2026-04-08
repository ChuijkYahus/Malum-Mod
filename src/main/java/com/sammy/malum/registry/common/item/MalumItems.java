package com.sammy.malum.registry.common.item;

import com.sammy.malum.*;
import com.sammy.malum.client.renderer.curio.*;
import com.sammy.malum.common.block.flora.wood.IGradientedLeavesBlock;
import com.sammy.malum.common.data.component.*;
import com.sammy.malum.common.entity.nitrate.*;
import com.sammy.malum.common.item.*;
import com.sammy.malum.common.item.augment.*;
import com.sammy.malum.common.item.augment.core.*;
import com.sammy.malum.common.item.banner.*;
import com.sammy.malum.common.item.codex.*;
import com.sammy.malum.common.item.cosmetic.curios.*;
import com.sammy.malum.common.item.curiosities.*;
import com.sammy.malum.common.item.curiosities.armor.*;
import com.sammy.malum.common.item.curiosities.curios.*;
import com.sammy.malum.common.item.curiosities.curios.brooches.*;
import com.sammy.malum.common.item.curiosities.curios.runes.madness.*;
import com.sammy.malum.common.item.curiosities.curios.runes.miracle.*;
import com.sammy.malum.common.item.curiosities.curios.runes.totemic.*;
import com.sammy.malum.common.item.curiosities.curios.sets.alchemical.*;
import com.sammy.malum.common.item.curiosities.curios.sets.esoteric.*;
import com.sammy.malum.common.item.curiosities.curios.sets.prospector.*;
import com.sammy.malum.common.item.curiosities.curios.sets.rotten.*;
import com.sammy.malum.common.item.curiosities.curios.sets.scythe.*;
import com.sammy.malum.common.item.curiosities.curios.sets.soulward.*;
import com.sammy.malum.common.item.curiosities.curios.sets.weeping.*;
import com.sammy.malum.common.item.curiosities.pouch.*;
import com.sammy.malum.common.item.curiosities.tools.*;
import com.sammy.malum.common.item.curiosities.tools.spellweaver.*;
import com.sammy.malum.common.item.curiosities.weapons.*;
import com.sammy.malum.common.item.curiosities.weapons.scythe.*;
import com.sammy.malum.common.item.curiosities.weapons.staff.*;
import com.sammy.malum.common.item.disc.*;
import com.sammy.malum.common.item.ether.*;
import com.sammy.malum.common.item.food.*;
import com.sammy.malum.common.item.impetus.*;
import com.sammy.malum.common.item.metallics.MetallicsItemRegistryBundle;
import com.sammy.malum.common.item.nucleus.PyreNucleusItem;
import com.sammy.malum.common.item.nucleus.WindNucleusItem;
import com.sammy.malum.common.item.spirit.*;
import com.sammy.malum.compat.farmersdelight.*;
import com.sammy.malum.core.enumextension.*;
import com.sammy.malum.registry.client.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.client.renderer.item.*;
import net.minecraft.world.food.*;
import net.minecraft.world.item.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.registries.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.modules.toolkit.item.*;

import team.lodestar.lodestone.modules.toolkit.item.tools.magic.*;
import top.theillusivec4.curios.api.client.*;

import java.util.function.*;

import static com.sammy.malum.MalumMod.*;
import static com.sammy.malum.registry.common.block.MalumBlocks.*;
import static com.sammy.malum.registry.common.item.MalumItemTiers.*;
import static net.minecraft.world.item.Rarity.*;

@SuppressWarnings("unused")
public class MalumItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MALUM);

    public static LodestoneItemProperties DEFAULT_PROPERTIES() {
        return new LodestoneItemProperties(MalumCreativeTabs.CONTENT);
    }

    public static LodestoneItemProperties GEAR_PROPERTIES() {
        return DEFAULT_PROPERTIES().stacksTo(1);
    }

    public static LodestoneItemProperties IMPETUS_PROPERTIES() {
        return DEFAULT_PROPERTIES().stacksTo(1);
    }

    public static LodestoneItemProperties COSMETIC_PROPERTIES() {
        return new LodestoneItemProperties(MalumCreativeTabs.COSMETIC);
    }

    public static LodestoneItemProperties HIDDEN_PROPERTIES() {
        return new LodestoneItemProperties().stacksTo(1);
    }


    public static <T extends Item> DeferredItem<T> register(String name, Supplier<LodestoneItemProperties> propertySupplier, Function<LodestoneItemProperties, T> function) {
        return ITEMS.register(name, () -> {
            var properties = propertySupplier.get();
            LodestoneItemProperties.addToTabSorting(MalumMod.malumPath(name), properties);
            return function.apply(properties);
        });
    }

    public static final DeferredItem<Item> ENCYCLOPEDIA_ARCANA = register("encyclopedia_arcana", () -> MalumItems.GEAR_PROPERTIES().rarity(UNCOMMON), EncyclopediaArcanaItem::new);

    public static final DeferredItem<GeasItem> GEAS = register("geas", () -> MalumItems.HIDDEN_PROPERTIES().rarity(RARE), GeasItem::new);

    //region spirits
    public static final DeferredItem<SpiritShardItem> SACRED_SPIRIT = register("sacred_spirit", MalumItems::DEFAULT_PROPERTIES, (p) -> new SpiritShardItem(p, MalumSpiritTypes.SACRED_SPIRIT));
    public static final DeferredItem<SpiritShardItem> WICKED_SPIRIT = register("wicked_spirit", MalumItems::DEFAULT_PROPERTIES, (p) -> new SpiritShardItem(p, MalumSpiritTypes.WICKED_SPIRIT));
    public static final DeferredItem<SpiritShardItem> ARCANE_SPIRIT = register("arcane_spirit", MalumItems::DEFAULT_PROPERTIES, (p) -> new SpiritShardItem(p, MalumSpiritTypes.ARCANE_SPIRIT));
    public static final DeferredItem<SpiritShardItem> ELDRITCH_SPIRIT = register("eldritch_spirit", MalumItems::DEFAULT_PROPERTIES, (p) -> new SpiritShardItem(p, MalumSpiritTypes.ELDRITCH_SPIRIT));
    public static final DeferredItem<SpiritShardItem> AERIAL_SPIRIT = register("aerial_spirit", MalumItems::DEFAULT_PROPERTIES, (p) -> new SpiritShardItem(p, MalumSpiritTypes.AERIAL_SPIRIT));
    public static final DeferredItem<SpiritShardItem> AQUEOUS_SPIRIT = register("aqueous_spirit", MalumItems::DEFAULT_PROPERTIES, (p) -> new SpiritShardItem(p, MalumSpiritTypes.AQUEOUS_SPIRIT));
    public static final DeferredItem<SpiritShardItem> EARTHEN_SPIRIT = register("earthen_spirit", MalumItems::DEFAULT_PROPERTIES, (p) -> new SpiritShardItem(p, MalumSpiritTypes.EARTHEN_SPIRIT));
    public static final DeferredItem<SpiritShardItem> INFERNAL_SPIRIT = register("infernal_spirit", MalumItems::DEFAULT_PROPERTIES, (p) -> new SpiritShardItem(p, MalumSpiritTypes.INFERNAL_SPIRIT));
    public static final DeferredItem<SpiritShardItem> UMBRAL_SPIRIT = register("umbral_spirit", MalumItems::DEFAULT_PROPERTIES, (p) -> new UmbralSpiritShardItem(p, MalumSpiritTypes.UMBRAL_SPIRIT));
    //endregion

    public static final DeferredItem<Item> ENCYCLOPEDIA_ESOTERICA = register("encyclopedia_esoterica", () -> MalumItems.GEAR_PROPERTIES().rarity(EPIC), EncyclopediaEsotericaItem::new);

    public static final DeferredItem<Item> ARCANE_ELEGY = register("music_disc_arcane_elegy", () -> MalumItems.HIDDEN_PROPERTIES().rarity(RARE), ArcaneElegyMusicDiscItem::new);
    public static final DeferredItem<Item> AESTHETICA = register("music_disc_aesthetica", () -> MalumItems.HIDDEN_PROPERTIES().rarity(RARE), AestheticaMusicDiscItem::new);

    //endregion

    //region contents
    public static final DeferredItem<Item> CONCENTRATED_GLUTTONY = register("concentrated_gluttony", () -> MalumItems.DEFAULT_PROPERTIES().food(MalumFoodProperties.CONCENTRATED_GLUTTONY), ConcentratedGluttonyItem::new);
    public static final DeferredItem<Item> SPLASH_OF_GLUTTONY = register("splash_of_gluttony", MalumItems::DEFAULT_PROPERTIES, SplashOfGluttonyItem::new);
    public static final DeferredItem<Item> SOULWOVEN_POUCH = register("soulwoven_pouch", MalumItems::GEAR_PROPERTIES, SoulwovenPouchItem::new);
    public static final DeferredItem<Item> RAVENOUS_POUCH = register("ravenous_pouch", MalumItems::GEAR_PROPERTIES, RavenousPouchItem::new);
    public static final DeferredItem<Item> TOTEMIC_STAFF = register("totemic_staff", MalumItems::GEAR_PROPERTIES, TinkeringToolItem::new);
    public static final DeferredItem<Item> ARTIFICERS_CLAW = register("artificers_claw", MalumItems::GEAR_PROPERTIES, TinkeringToolItem::new);
    public static final DeferredItem<Item> TUNING_FORK = register("tuning_fork", MalumItems::GEAR_PROPERTIES, TinkeringToolItem::new);
    public static final DeferredItem<Item> LAMPLIGHTERS_TONGS = register("lamplighters_tongs", MalumItems::GEAR_PROPERTIES, LamplightersTongsItem::new);

    public static final DeferredItem<Item> CATALYST_LOBBER = register("catalyst_lobber", MalumItems::GEAR_PROPERTIES, (p) -> new CatalystLobberItem(p.durability(500), EthericNitrate::new));

    public static final DeferredItem<Item> CRUDE_SCYTHE = register("crude_scythe", MalumItems::GEAR_PROPERTIES, (p) -> new MalumScytheItem(Tiers.IRON, 0, 0.1f, p.durability(500)));
    public static final DeferredItem<Item> SOUL_STAINED_STEEL_SCYTHE = register("soul_stained_steel_scythe", MalumItems::GEAR_PROPERTIES, (p) -> new MagicScytheItem(SOUL_STAINED_STEEL, -3.5f, 0.2f, 4, p));
    public static final DeferredItem<Item> SOUL_STAINED_STEEL_KNIFE = register("soul_stained_steel_knife", () -> FarmersDelightCompat.LOADED ? GEAR_PROPERTIES() : HIDDEN_PROPERTIES(), (p) -> FarmersDelightCompat.LOADED ? FarmersDelightCompat.LoadedOnly.makeMagicKnife(p) : new Item(p));

    public static final DeferredItem<Item> SOUL_STAINED_STEEL_HELMET = register("soul_stained_steel_helmet", MalumItems::GEAR_PROPERTIES, (p) -> new SoulStainedSteelArmorItem(ArmorItem.Type.HELMET, p));
    public static final DeferredItem<Item> SOUL_STAINED_STEEL_CHESTPLATE = register("soul_stained_steel_chestplate", MalumItems::GEAR_PROPERTIES, (p) -> new SoulStainedSteelArmorItem(ArmorItem.Type.CHESTPLATE, p));
    public static final DeferredItem<Item> SOUL_STAINED_STEEL_LEGGINGS = register("soul_stained_steel_leggings", MalumItems::GEAR_PROPERTIES, (p) -> new SoulStainedSteelArmorItem(ArmorItem.Type.LEGGINGS, p));
    public static final DeferredItem<Item> SOUL_STAINED_STEEL_BOOTS = register("soul_stained_steel_boots", MalumItems::GEAR_PROPERTIES, (p) -> new SoulStainedSteelArmorItem(ArmorItem.Type.BOOTS, p));

    public static final DeferredItem<Item> SOUL_STAINED_STEEL_SWORD = register("soul_stained_steel_sword", MalumItems::GEAR_PROPERTIES, (p) -> new MagicSwordItem(SOUL_STAINED_STEEL, -3, 0, 3, p));
    public static final DeferredItem<Item> SOUL_STAINED_STEEL_PICKAXE = register("soul_stained_steel_pickaxe", MalumItems::GEAR_PROPERTIES, (p) -> new MagicPickaxeItem(SOUL_STAINED_STEEL, -2, 0, 2, p));
    public static final DeferredItem<Item> SOUL_STAINED_STEEL_AXE = register("soul_stained_steel_axe", MalumItems::GEAR_PROPERTIES, (p) -> new MagicAxeItem(SOUL_STAINED_STEEL, -3, 0, 4, p));
    public static final DeferredItem<Item> SOUL_STAINED_STEEL_SHOVEL = register("soul_stained_steel_shovel", MalumItems::GEAR_PROPERTIES, (p) -> new MagicShovelItem(SOUL_STAINED_STEEL, -2, 0, 2, p));
    public static final DeferredItem<Item> SOUL_STAINED_STEEL_HOE = register("soul_stained_steel_hoe", MalumItems::GEAR_PROPERTIES, (p) -> new MagicHoeItem(SOUL_STAINED_STEEL, 0, -1.5f, 1, p));

    public static final DeferredItem<Item> SOUL_HUNTER_CLOAK = register("soul_hunter_cloak", MalumItems::GEAR_PROPERTIES, (p) -> new SoulHunterArmorItem(ArmorItem.Type.HELMET, p));
    public static final DeferredItem<Item> SOUL_HUNTER_ROBE = register("soul_hunter_robe", MalumItems::GEAR_PROPERTIES, (p) -> new SoulHunterArmorItem(ArmorItem.Type.CHESTPLATE, p));
    public static final DeferredItem<Item> SOUL_HUNTER_LEGGINGS = register("soul_hunter_leggings", MalumItems::GEAR_PROPERTIES, (p) -> new SoulHunterArmorItem(ArmorItem.Type.LEGGINGS, p));
    public static final DeferredItem<Item> SOUL_HUNTER_BOOTS = register("soul_hunter_boots", MalumItems::GEAR_PROPERTIES, (p) -> new SoulHunterArmorItem(ArmorItem.Type.BOOTS, p));

    public static final DeferredItem<Item> RAVENOUS_SCYTHE = register("ravenous_scythe", MalumItems::GEAR_PROPERTIES, (p) -> new RavenousScytheItem(RAVENOUS, -4f, 0.4f, 2.5f, p));
    public static final DeferredItem<Item> GLUTTONOUS_BLUDGEON = register("gluttonous_bludgeon", MalumItems::GEAR_PROPERTIES, (p) -> new GluttonousBludgeonItem(RAVENOUS, -2.5f, -3f, 2, p));
    public static final DeferredItem<Item> TYRVING = register("tyrving", MalumItems::GEAR_PROPERTIES, (p) -> new TyrvingItem(MalumItemTiers.TYRVING, 0, -0.3f, p));

    public static final DeferredItem<Item> MNEMONIC_HEX_STAFF = register("mnemonic_hex_staff", MalumItems::GEAR_PROPERTIES, (p) -> new HexStaffItem(HEX_STAFF, 5, 1, 2, p));
    public static final DeferredItem<Item> EROSION_SCEPTER = register("erosion_scepter", MalumItems::GEAR_PROPERTIES, (p) -> new ErosionScepterItem(MALIGNANT_ALLOY, 5, 0.5f, 1, p));

    public static final DeferredItem<Item> WEIGHT_OF_WORLDS = register("weight_of_worlds", MalumItems::GEAR_PROPERTIES, (p) -> new WeightOfWorldsItem(MalumItemTiers.MALIGNANT_ALLOY, 1, -0.2f, p));
    public static final DeferredItem<Item> EDGE_OF_DELIVERANCE = register("edge_of_deliverance", MalumItems::GEAR_PROPERTIES, (p) -> new EdgeOfDeliveranceItem(MalumItemTiers.MALIGNANT_ALLOY, 2, -0.1f, p));

    public static final DeferredItem<Item> MALIGNANT_STRONGHOLD_HELMET = register("malignant_stronghold_helmet", MalumItems::GEAR_PROPERTIES, (p) -> new MalignantStrongholdArmorItem(ArmorItem.Type.HELMET, p));
    public static final DeferredItem<Item> MALIGNANT_STRONGHOLD_CHESTPLATE = register("malignant_stronghold_chestplate", MalumItems::GEAR_PROPERTIES, (p) -> new MalignantStrongholdArmorItem(ArmorItem.Type.CHESTPLATE, p));
    public static final DeferredItem<Item> MALIGNANT_STRONGHOLD_LEGGINGS = register("malignant_stronghold_leggings", MalumItems::GEAR_PROPERTIES, (p) -> new MalignantStrongholdArmorItem(ArmorItem.Type.LEGGINGS, p));
    public static final DeferredItem<Item> MALIGNANT_STRONGHOLD_BOOTS = register("malignant_stronghold_boots", MalumItems::GEAR_PROPERTIES, (p) -> new MalignantStrongholdArmorItem(ArmorItem.Type.BOOTS, p));

    public static final DeferredItem<Item> UNWINDING_CHAOS = register("unwinding_chaos", () -> GEAR_PROPERTIES().rarity(EPIC), (p) -> new UnwindingChaosStaffItem(HARNESSED_CHAOS, 5, 1.5f, 3, p));
    public static final DeferredItem<Item> SUNDERING_ANCHOR = register("sundering_anchor", () -> GEAR_PROPERTIES().rarity(EPIC), (p) -> new SunderingAnchorItem(HARNESSED_CHAOS, 4, p));

    public static final DeferredItem<Item> SPELLWEAVING_PICKAXE = register("spellweaving_pickaxe", MalumItems::GEAR_PROPERTIES, (p) -> new SpellweavingPickaxeItem(SPELLWEAVING_TOOLS, -3, 0, 4, p));
    public static final DeferredItem<Item> SPELLWEAVING_AXE = register("spellweaving_axe", MalumItems::GEAR_PROPERTIES, (p) -> new SpellweavingAxeItem(SPELLWEAVING_TOOLS, -4, 0, 6, p));

    public static final DeferredItem<Item> GILDED_RING = register("gilded_ring", MalumItems::GEAR_PROPERTIES, CurioGildedRing::new);
    public static final DeferredItem<Item> GILDED_BELT = register("gilded_belt", MalumItems::GEAR_PROPERTIES, CurioGildedBelt::new);
    public static final DeferredItem<Item> ORNATE_RING = register("ornate_ring", MalumItems::GEAR_PROPERTIES, CurioOrnateRing::new);
    public static final DeferredItem<Item> ORNATE_NECKLACE = register("ornate_necklace", MalumItems::GEAR_PROPERTIES, CurioOrnateNecklace::new);

    public static final DeferredItem<Item> RUNIC_BROOCH = register("runic_brooch", MalumItems::GEAR_PROPERTIES, CurioRunicBrooch::new);
    public static final DeferredItem<Item> ELABORATE_BROOCH = register("elaborate_brooch", MalumItems::GEAR_PROPERTIES, CurioElaborateBrooch::new);
    public static final DeferredItem<Item> GLASS_BROOCH = register("glass_brooch", MalumItems::GEAR_PROPERTIES, CurioGlassBrooch::new);
    public static final DeferredItem<Item> GLUTTONOUS_BROOCH = register("gluttonous_brooch", MalumItems::GEAR_PROPERTIES, CurioGluttonousBrooch::new);

    public static final DeferredItem<Item> RING_OF_ESOTERIC_SPOILS = register("ring_of_esoteric_spoils", MalumItems::GEAR_PROPERTIES, CurioArcaneSpoilRing::new);
    public static final DeferredItem<Item> RING_OF_ESOTERIC_SHADOW = register("ring_of_esoteric_shadow", MalumItems::GEAR_PROPERTIES, CurioConcealingRing::new);

    public static final DeferredItem<Item> RING_OF_CURATIVE_TALENT = register("ring_of_curative_talent", MalumItems::GEAR_PROPERTIES, CurioCurativeRing::new);
    public static final DeferredItem<Item> RING_OF_ALCHEMICAL_MASTERY = register("ring_of_alchemical_mastery", MalumItems::GEAR_PROPERTIES, CurioAlchemicalRing::new);
    public static final DeferredItem<Item> RING_OF_MANAWEAVING = register("ring_of_manaweaving", MalumItems::GEAR_PROPERTIES, CurioManaweavingRing::new);
    public static final DeferredItem<Item> RING_OF_ARCANE_PROWESS = register("ring_of_arcane_prowess", MalumItems::GEAR_PROPERTIES, CurioProwessRing::new);

    public static final DeferredItem<Item> RING_OF_DESPERATE_VORACITY = register("ring_of_desperate_voracity", MalumItems::GEAR_PROPERTIES, CurioVoraciousRing::new);
    public static final DeferredItem<Item> RING_OF_SWARMING_ROT = register("ring_of_swarming_rot", MalumItems::GEAR_PROPERTIES, CurioSwarmingRing::new);

    public static final DeferredItem<Item> RING_OF_THE_RISING_EDGE = register("ring_of_the_rising_edge", MalumItems::GEAR_PROPERTIES, CurioRisingEdgeRing::new);
    public static final DeferredItem<Item> RING_OF_THE_HOWLING_MAELSTROM = register("ring_of_the_howling_maelstrom", MalumItems::GEAR_PROPERTIES, CurioHowlingMaelstromRing::new);

    public static final DeferredItem<Item> RING_OF_HEARTY_AVARICE = register("ring_of_hearty_avarice", MalumItems::GEAR_PROPERTIES, CurioHeartyAvariceRing::new);
    public static final DeferredItem<Item> RING_OF_HEAVY_DISCHARGE = register("ring_of_heavy_discharge", MalumItems::GEAR_PROPERTIES, CurioDischargeRing::new);

    public static final DeferredItem<Item> NECKLACE_OF_MYSTIC_POTENCY = register("necklace_of_mystic_potency", MalumItems::GEAR_PROPERTIES, CurioMysticNecklace::new);
    public static final DeferredItem<Item> NECKLACE_OF_THE_NARROW_EDGE = register("necklace_of_the_narrow_edge", MalumItems::GEAR_PROPERTIES, CurioNarrowEdgeNecklace::new);

    public static final DeferredItem<Item> BELT_OF_THE_STARVED = register("belt_of_the_starved", MalumItems::GEAR_PROPERTIES, CurioStarvedBelt::new);
    public static final DeferredItem<Item> BELT_OF_THE_PROSPECTOR = register("belt_of_the_prospector", MalumItems::GEAR_PROPERTIES, CurioProspectorBelt::new);
    public static final DeferredItem<Item> BELT_OF_THE_MAGEBANE = register("belt_of_the_magebane", MalumItems::GEAR_PROPERTIES, CurioMagebaneBelt::new);

    public static final DeferredItem<Item> RING_OF_THE_ENDLESS_WELL = register("ring_of_the_endless_well", MalumItems::GEAR_PROPERTIES, CurioEndlessRing::new);
    public static final DeferredItem<Item> RING_OF_ECHOING_ARCANA = register("ring_of_echoing_arcana", MalumItems::GEAR_PROPERTIES, CurioEchoingArcanaRing::new);
    public static final DeferredItem<Item> RING_OF_GROWING_FLESH = register("ring_of_growing_flesh", MalumItems::GEAR_PROPERTIES, CurioGrowingFleshRing::new);
    public static final DeferredItem<Item> RING_OF_GRUESOME_CONCENTRATION = register("ring_of_gruesome_concentration", MalumItems::GEAR_PROPERTIES, CurioGruesomeConcentrationRing::new);
    public static final DeferredItem<Item> NECKLACE_OF_THE_HIDDEN_BLADE = register("necklace_of_the_hidden_blade", MalumItems::GEAR_PROPERTIES, CurioHiddenBladeNecklace::new);
    public static final DeferredItem<Item> NECKLACE_OF_THE_WATCHER = register("necklace_of_the_watcher", MalumItems::GEAR_PROPERTIES, CurioWatcherNecklace::new);
    public static final DeferredItem<Item> BELT_OF_THE_LIMITLESS = register("belt_of_the_limitless", MalumItems::GEAR_PROPERTIES, CurioLimitlessBelt::new);

    public static final DeferredItem<Item> RUNE_OF_VITALITY = register("rune_of_vitality", MalumItems::GEAR_PROPERTIES, RuneVitalityItem::new);
    public static final DeferredItem<Item> RUNE_OF_CULLING = register("rune_of_culling", MalumItems::GEAR_PROPERTIES, RuneCullingItem::new);
    public static final DeferredItem<Item> RUNE_OF_REINFORCEMENT = register("rune_of_reinforcement", MalumItems::GEAR_PROPERTIES, RuneReinforcementItem::new);
    public static final DeferredItem<Item> RUNE_OF_VOLATILE_DISTORTION = register("rune_of_volatile_distortion", MalumItems::GEAR_PROPERTIES, RuneVolatileDistortionItem::new);
    public static final DeferredItem<Item> RUNE_OF_DEXTERITY = register("rune_of_dexterity", MalumItems::GEAR_PROPERTIES, RuneDexterityItem::new);
    public static final DeferredItem<Item> RUNE_OF_AILMENT_CLEANSING = register("rune_of_ailment_cleansing", MalumItems::GEAR_PROPERTIES, RuneAilmentCleansingItem::new);
    public static final DeferredItem<Item> RUNE_OF_PROTECTION = register("rune_of_protection", MalumItems::GEAR_PROPERTIES, RuneProtectionItem::new);
    public static final DeferredItem<Item> RUNE_OF_SCORCHING = register("rune_of_scorching", MalumItems::GEAR_PROPERTIES, RuneScorchingItem::new);

    public static final DeferredItem<Item> RUNE_OF_HOWLING_GALE = register("rune_of_howling_gale", MalumItems::GEAR_PROPERTIES, RuneHowlingGale::new);
    public static final DeferredItem<Item> RUNE_OF_FLOWING_GRASP = register("rune_of_flowing_grasp", MalumItems::GEAR_PROPERTIES, RuneFlowingGrasp::new);
    public static final DeferredItem<Item> RUNE_OF_STONE_WARD = register("rune_of_stone_ward", MalumItems::GEAR_PROPERTIES, RuneStoneWard::new);
    public static final DeferredItem<Item> RUNE_OF_BURNING_FERVOR = register("rune_of_burning_fervor", MalumItems::GEAR_PROPERTIES, RuneBurningFervor::new);
    public static final DeferredItem<Item> RUNE_OF_SKY_TETHER = register("rune_of_sky_tether", MalumItems::GEAR_PROPERTIES, RuneSkyTether::new);
    public static final DeferredItem<Item> RUNE_OF_GOOD_TIDES = register("rune_of_good_tides", MalumItems::GEAR_PROPERTIES, RuneGoodTides::new);
    public static final DeferredItem<Item> RUNE_OF_OAKEN_MIGHT = register("rune_of_oaken_might", MalumItems::GEAR_PROPERTIES, RuneOakenMight::new);
    public static final DeferredItem<Item> RUNE_OF_FIERY_EMBRACE = register("rune_of_fiery_embrace", MalumItems::GEAR_PROPERTIES, RuneFieryEmbrace::new);

    public static final DeferredItem<Item> RUNE_OF_BOLSTERING = register("rune_of_bolstering", MalumItems::GEAR_PROPERTIES, RuneBolsteringItem::new);
    public static final DeferredItem<Item> RUNE_OF_RADIAL_EMPOWERMENT = register("rune_of_radial_empowerment", MalumItems::GEAR_PROPERTIES, RuneRadialEmpowermentItem::new);
    public static final DeferredItem<Item> RUNE_OF_SPELL_MASTERY = register("rune_of_spell_mastery", MalumItems::GEAR_PROPERTIES, RuneSpellMasteryItem::new);
    public static final DeferredItem<Item> RUNE_OF_HERESY = register("rune_of_heresy", MalumItems::GEAR_PROPERTIES, RuneHeresyItem::new);
    public static final DeferredItem<Item> RUNE_OF_UNNATURAL_STAMINA = register("rune_of_unnatural_stamina", MalumItems::GEAR_PROPERTIES, RuneUnnaturalStaminaItem::new);
    public static final DeferredItem<Item> RUNE_OF_TWINNED_DURATION = register("rune_of_twinned_duration", MalumItems::GEAR_PROPERTIES, RuneTwinnedDurationItem::new);
    public static final DeferredItem<Item> RUNE_OF_INDOMITABILITY = register("rune_of_indomitability", MalumItems::GEAR_PROPERTIES, RuneIndomitabilityItem::new);
    public static final DeferredItem<Item> RUNE_OF_IGNEOUS_SOLACE = register("rune_of_igneous_solace", MalumItems::GEAR_PROPERTIES, RuneIgneousSolaceItem::new);
    //endregion

    //region augments
    public static final DeferredItem<Item> MENDING_DIFFUSER = register("mending_diffuser", MalumItems::DEFAULT_PROPERTIES, MendingDiffuserItem::new);
    public static final DeferredItem<Item> IMPURITY_STABILIZER = register("impurity_stabilizer", MalumItems::DEFAULT_PROPERTIES, ImpurityStabilizer::new);
    public static final DeferredItem<Item> SHIELDING_APPARATUS = register("shielding_apparatus", MalumItems::DEFAULT_PROPERTIES, ShieldingApparatusItem::new);
    public static final DeferredItem<Item> WARPING_ENGINE = register("warping_engine", MalumItems::DEFAULT_PROPERTIES, WarpingEngineItem::new);
    public static final DeferredItem<Item> ACCELERATING_INLAY = register("accelerating_inlay", MalumItems::DEFAULT_PROPERTIES, AcceleratingInlayItem::new);
    public static final DeferredItem<Item> PRISMATIC_FOCUS_LENS = register("prismatic_focus_lens", MalumItems::DEFAULT_PROPERTIES, PrismaticFocusLensItem::new);
    public static final DeferredItem<Item> BLAZING_DIODE = register("blazing_diode", MalumItems::DEFAULT_PROPERTIES, BlazingDiodeItem::new);
    public static final DeferredItem<Item> INTRICATE_ASSEMBLY = register("intricate_assembly", MalumItems::DEFAULT_PROPERTIES, IntricateAssemblyItem::new);

    public static final DeferredItem<Item> SYMPATHY_DRIVE = register("sympathy_drive", MalumItems::DEFAULT_PROPERTIES, SympathyDrive::new);
    public static final DeferredItem<Item> SUSPICIOUS_DEVICE = register("suspicious_device", MalumItems::DEFAULT_PROPERTIES, SuspiciousDeviceItem::new);
    public static final DeferredItem<Item> CAUSTIC_CATALYST = register("caustic_catalyst", MalumItems::DEFAULT_PROPERTIES, CausticCatalystItem::new);
    public static final DeferredItem<Item> RESONANCE_TUNER = register("resonance_tuner", MalumItems::DEFAULT_PROPERTIES, ResonanceTuner::new);

    public static final DeferredItem<Item> STELLAR_MECHANISM = register("stellar_mechanism", MalumItems::DEFAULT_PROPERTIES, StellarMechanismItem::new);
    //endregion

    //region ores
    public static final DeferredItem<Item> RAW_SOULSTONE = register("raw_soulstone", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredItem<Item> CRUSHED_SOULSTONE = register("crushed_soulstone", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredItem<Item> REFINED_SOULSTONE = register("refined_soulstone", MalumItems::DEFAULT_PROPERTIES, Item::new);

    public static final DeferredItem<Item> RAW_BRILLIANCE = register("raw_brilliance", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredItem<Item> CRUSHED_BRILLIANCE = register("crushed_brilliance", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredItem<Item> REFINED_BRILLIANCE = register("refined_brilliance", MalumItems::DEFAULT_PROPERTIES, (p) -> new BrillianceChunkItem(p.food((new FoodProperties.Builder()).fast().alwaysEdible().build())));

    public static final DeferredItem<Item> CTHONIC_GOLD = register("cthonic_gold", () -> DEFAULT_PROPERTIES().rarity(UNCOMMON), Item::new);
    //endregion

    //region materials
    public static final DeferredItem<Item> ROTTING_ESSENCE = register("rotting_essence", () -> DEFAULT_PROPERTIES().food(MalumFoodProperties.ROTTING_ESSENCE), Item::new);
    public static final DeferredItem<Item> GRIM_TALC = register("grim_talc", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredItem<Item> EERIE_WEAVE = register("eerie_weave", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredItem<Item> WARP_FLUX = register("warp_flux", MalumItems::DEFAULT_PROPERTIES, Item::new);

    public static final DeferredItem<Item> WIND_NUCLEUS = register("wind_nucleus", MalumItems::DEFAULT_PROPERTIES, WindNucleusItem::new);
    public static final DeferredItem<Item> PYRE_NUCLEUS = register("pyre_nucleus", MalumItems::DEFAULT_PROPERTIES, PyreNucleusItem::new);

    public static final DeferredItem<Item> HEX_ASH = register("hex_ash", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredItem<Item> LIVING_FLESH = register("living_flesh", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredItem<Item> ALCHEMICAL_CALX = register("alchemical_calx", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredItem<Item> ARCANE_CHARCOAL = register("arcane_charcoal", MalumItems::DEFAULT_PROPERTIES, (p) -> new LodestoneFuelItem(p, 3200));

    public static final DeferredItem<Item> EBONY = register("ebony", MalumItems::DEFAULT_PROPERTIES, Item::new);

    public static final DeferredItem<Item> SOULWOVEN_SILK = register("soulwoven_silk", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredItem<Item> PARACAUSAL_FLAME = register("paracausal_flame", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredItem<Item> CONVOLUTED_LENS = register("convoluted_lens", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredItem<Item> MIMICRY_RELAY = register("mimicry_relay", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredItem<Item> IMITATION_FLESH = register("imitation_flesh", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredItem<Item> IMITATION_HEART = register("imitation_heart", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredItem<Item> POPPET = register("poppet", MalumItems::HIDDEN_PROPERTIES, Item::new);

    public static final DeferredItem<Item> NULL_SLATE = register("null_slate", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredItem<Item> VOID_SALTS = register("void_salts", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredItem<Item> MNEMONIC_FRAGMENT = register("mnemonic_fragment", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredItem<Item> AURIC_EMBERS = register("auric_embers", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredItem<Item> MALIGNANT_LEAD = register("malignant_lead", () -> DEFAULT_PROPERTIES().rarity(RARE), Item::new);

    public static final DeferredItem<Item> ANOMALOUS_DESIGN = register("anomalous_design", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredItem<Item> COMPLETE_DESIGN = register("complete_design", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredItem<Item> FUSED_CONSCIOUSNESS = register("fused_consciousness", MalumItems::DEFAULT_PROPERTIES, (p) -> new FusedConsciousnessItem(p.rarity(RARE)));

    public static final DeferredItem<Item> SOUL_STAINED_STEEL_INGOT = register("soul_stained_steel_ingot", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredItem<Item> SOUL_STAINED_STEEL_PLATING = register("soul_stained_steel_plating", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredItem<Item> SOUL_STAINED_STEEL_NUGGET = register("soul_stained_steel_nugget", MalumItems::DEFAULT_PROPERTIES, Item::new);

    public static final DeferredItem<Item> HALLOWED_GOLD_INGOT = register("hallowed_gold_ingot", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredItem<Item> HALLOWED_GOLD_INLAY = register("hallowed_gold_inlay", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredItem<Item> HALLOWED_GOLD_NUGGET = register("hallowed_gold_nugget", MalumItems::DEFAULT_PROPERTIES, Item::new);

    public static final DeferredItem<Item> MALIGNANT_PEWTER_INGOT = register("malignant_pewter_ingot", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredItem<Item> MALIGNANT_PEWTER_PLATING = register("malignant_pewter_plating", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredItem<Item> MALIGNANT_PEWTER_NUGGET = register("malignant_pewter_nugget", MalumItems::DEFAULT_PROPERTIES, Item::new);

    //alchemical impetus
    public static final DeferredItem<Item> ALCHEMICAL_IMPETUS = register("alchemical_impetus", MalumItems::IMPETUS_PROPERTIES, ImpetusItem::new);
    public static final DeferredItem<Item> FRACTURED_ALCHEMICAL_IMPETUS = register("fractured_alchemical_impetus", MalumItems::IMPETUS_PROPERTIES, FracturedImpetusItem::new);
    public static final DeferredItem<Item> ZEPHYR_IMPETUS = register("zephyr_impetus", MalumItems::IMPETUS_PROPERTIES, ImpetusItem::new);
    public static final DeferredItem<Item> FRACTURED_ZEPHYR_IMPETUS = register("fractured_zephyr_impetus", MalumItems::IMPETUS_PROPERTIES, FracturedImpetusItem::new);
    public static final DeferredItem<Item> IFRIT_IMPETUS = register("ifrit_impetus", MalumItems::IMPETUS_PROPERTIES, ImpetusItem::new);
    public static final DeferredItem<Item> FRACTURED_IFRIT_IMPETUS = register("fractured_ifrit_impetus", MalumItems::IMPETUS_PROPERTIES, FracturedImpetusItem::new);

    //endregion

    //region metallics
    public static final MetallicsItemRegistryBundle IRON_METALLICS = new MetallicsItemRegistryBundle("iron");
    public static final MetallicsItemRegistryBundle COPPER_METALLICS = new MetallicsItemRegistryBundle("copper");
    public static final MetallicsItemRegistryBundle GOLD_METALLICS = new MetallicsItemRegistryBundle("gold");
    public static final MetallicsItemRegistryBundle ZINC_METALLICS = new MetallicsItemRegistryBundle("zinc");
    public static final MetallicsItemRegistryBundle LEAD_METALLICS = new MetallicsItemRegistryBundle("lead");
    public static final MetallicsItemRegistryBundle SILVER_METALLICS = new MetallicsItemRegistryBundle("silver");
    public static final MetallicsItemRegistryBundle ALUMINIUM_METALLICS = new MetallicsItemRegistryBundle("aluminium");
    public static final MetallicsItemRegistryBundle NICKEL_METALLICS = new MetallicsItemRegistryBundle("nickel");
    //endregion

    //region runewood & soulwood
    public static final DeferredItem<Item> RUNIC_SAP = register("runic_sap", MalumItems::DEFAULT_PROPERTIES, (p) -> new BottledDrinkItem(DEFAULT_PROPERTIES().food(MalumFoodProperties.RUNIC_SAP)));
    public static final DeferredItem<Item> RUNIC_SAPBALL = register("runic_sapball", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredItem<Item> RUNEWOOD_BOAT = register("runewood_boat", () -> DEFAULT_PROPERTIES().stacksTo(1), (p) -> new BoatItem(false, MalumEnumParams.RUNEWOOD_BOAT_TYPE.getValue(), p));
    public static final DeferredItem<Item> RUNEWOOD_CHEST_BOAT = register("runewood_chest_boat", () -> DEFAULT_PROPERTIES().stacksTo(1), (p) -> new BoatItem(true, MalumEnumParams.RUNEWOOD_BOAT_TYPE.getValue(), p));

    public static final DeferredItem<Item> CURSED_SAP = register("cursed_sap", MalumItems::DEFAULT_PROPERTIES, (p) -> new BottledDrinkItem(DEFAULT_PROPERTIES().food(MalumFoodProperties.CURSED_SAP)));
    public static final DeferredItem<Item> CURSED_SAPBALL = register("cursed_sapball", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredItem<Item> SOULWOOD_BOAT = register("soulwood_boat", () -> DEFAULT_PROPERTIES().stacksTo(1), (p) -> new BoatItem(false, MalumEnumParams.SOULWOOD_BOAT_TYPE.getValue(), p));
    public static final DeferredItem<Item> SOULWOOD_CHEST_BOAT = register("soulwood_chest_boat", () -> DEFAULT_PROPERTIES().stacksTo(1), (p) -> new BoatItem(true, MalumEnumParams.SOULWOOD_BOAT_TYPE.getValue(), p));
    //endregion

    //region dungeon
    public static final DeferredItem<Item> SHAPED_SLAB = register("shaped_slab", MalumItems::GEAR_PROPERTIES, (p) -> new ShapedSlabSwordItem(ARCHAIC_SLATE, 2.5f, -0.8f, p));
    public static final DeferredItem<Item> BROKEN_BLADE = register("broken_blade", MalumItems::GEAR_PROPERTIES, (p) -> new BrokenBladeSwordItem(ARCHAIC_SLATE, -0.5f, -0.6f, p));

    public static final DeferredItem<Item> IRON_CROWN = register("iron_crown", MalumItems::DEFAULT_PROPERTIES, Item::new);
    //endregion

    //region cosmetics
    public static final DeferredItem<Item> ESOTERIC_SPOOL = register("esoteric_spool", MalumItems::COSMETIC_PROPERTIES, Item::new);
    public static final DeferredItem<Item> ANCIENT_WEAVE = register("ancient_weave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.ANCIENT_CLOTH));
    public static final DeferredItem<Item> CORNERED_WEAVE = register("cornered_weave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.COMMANDO));
    public static final DeferredItem<Item> MECHANICAL_WEAVE_V1 = register("mechanical_weave_v1", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.BLUE_MACHINE));
    public static final DeferredItem<Item> MECHANICAL_WEAVE_V2 = register("mechanical_weave_v2", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.RED_MACHINE));

    public static final DeferredItem<Item> ACE_PRIDEWEAVE = register("ace_prideweave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.ACE));
    public static final DeferredItem<Item> AGENDER_PRIDEWEAVE = register("agender_prideweave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.AGENDER));
    public static final DeferredItem<Item> ARO_PRIDEWEAVE = register("aro_prideweave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.ARO));
    public static final DeferredItem<Item> AROACE_PRIDEWEAVE = register("aroace_prideweave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.AROACE));
    public static final DeferredItem<Item> BI_PRIDEWEAVE = register("bi_prideweave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.BI));
    public static final DeferredItem<Item> DEMIBOY_PRIDEWEAVE = register("demiboy_prideweave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.DEMIBOY));
    public static final DeferredItem<Item> DEMIGIRL_PRIDEWEAVE = register("demigirl_prideweave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.DEMIGIRL));
    public static final DeferredItem<Item> ENBY_PRIDEWEAVE = register("enby_prideweave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.ENBY));
    public static final DeferredItem<Item> GAY_PRIDEWEAVE = register("gay_prideweave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.GAY));
    public static final DeferredItem<Item> GENDERFLUID_PRIDEWEAVE = register("genderfluid_prideweave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.GENDERFLUID));
    public static final DeferredItem<Item> GENDERQUEER_PRIDEWEAVE = register("genderqueer_prideweave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.GENDERQUEER));
    public static final DeferredItem<Item> INTERSEX_PRIDEWEAVE = register("intersex_prideweave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.INTERSEX));
    public static final DeferredItem<Item> LESBIAN_PRIDEWEAVE = register("lesbian_prideweave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.LESBIAN));
    public static final DeferredItem<Item> PAN_PRIDEWEAVE = register("pan_prideweave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.PAN));
    public static final DeferredItem<Item> PLURAL_PRIDEWEAVE = register("plural_prideweave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.PLURAL));
    public static final DeferredItem<Item> POLY_PRIDEWEAVE = register("poly_prideweave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.POLY));
    public static final DeferredItem<Item> PRIDE_PRIDEWEAVE = register("pride_prideweave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.PRIDE));
    public static final DeferredItem<Item> TRANS_PRIDEWEAVE = register("trans_prideweave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.TRANS));

    public static final DeferredItem<Item> TOPHAT = register("tophat", () -> COSMETIC_PROPERTIES().stacksTo(1), CurioTopHat::new);
    //endregion

    //region hidden items
    public static final DeferredItem<Item> SOUL_OF_A_SCYTHE = register("soul_of_a_scythe", MalumItems::HIDDEN_PROPERTIES, TemporarilyDisabledItem::new);
    public static final DeferredItem<Item> SOUL_OF_THE_ANCHOR = register("soul_of_the_anchor", MalumItems::HIDDEN_PROPERTIES, TemporarilyDisabledItem::new);
    public static final DeferredItem<Item> TOKEN_OF_GRATITUDE = register("token_of_gratitude", MalumItems::HIDDEN_PROPERTIES, CurioTokenOfGratitude::new);
    //endregion


    public static Item skinHoldingItem(Item.Properties properties, ItemSkinComponent skin) {
        return new Item(properties.component(MalumDataComponents.ITEM_SKIN, skin));
    }

    @EventBusSubscriber(modid = MalumMod.MALUM, value = Dist.CLIENT)
    public static class ClientOnly {

        @SubscribeEvent
        public static void registerExtras(FMLClientSetupEvent event) {
            CuriosRendererRegistry.register(MalumItems.TOKEN_OF_GRATITUDE.get(), TokenOfGratitudeRenderer::new);
            CuriosRendererRegistry.register(MalumItems.TOPHAT.get(), TopHatCurioRenderer::new);

            MalumHiddenTags.registerHiddenTags();
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public static void addItemProperties(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                ITEMS.getEntries().stream().filter(r -> r.get() instanceof LodestoneArmorItem).forEach(armor ->
                        ItemProperties.register(
                        armor.get(),
                        MalumMod.malumPath("item_skin"),
                        (stack, level, holder, holderID) -> ItemSkinComponent.getAppliedSkinId(stack)));

                ItemProperties.register(
                        SOULWOVEN_POUCH.get(),
                        MalumMod.malumPath("filled"),
                        (stack, level, holder, holderID) -> SoulwovenPouchItem.getFullnessDisplay(stack));
                ItemProperties.register(
                        RAVENOUS_POUCH.get(),
                        MalumMod.malumPath("filled"),
                        (stack, level, holder, holderID) -> RavenousPouchItem.getFullnessDisplay(stack));
                ItemProperties.register(
                        SOULWOVEN_BANNER.getItem(),
                        MalumMod.malumPath("pattern"),
                        (stack, level, holder, holderID) -> SoulwovenBannerBlockItem.getBannerPattern(stack));
                ItemProperties.register(
                        CATALYST_LOBBER.get(),
                        MalumMod.malumPath("state"),
                        (stack, level, holder, holderID) -> CatalystLobberItem.getStateDisplay(stack));
                ItemProperties.register(
                        SPELLWEAVING_PICKAXE.get(),
                        MalumMod.malumPath("primed"),
                        (stack, level, holder, holderID) -> SpellweavingPickaxeItem.getStateDisplay(stack));
                ItemProperties.register(
                        SPELLWEAVING_AXE.get(),
                        MalumMod.malumPath("primed"),
                        (stack, level, holder, holderID) -> SpellweavingPickaxeItem.getStateDisplay(stack));
            });
        }

        @SubscribeEvent
        public static void setItemColors(RegisterColorHandlersEvent.Item event) {

            event.register((stack, tintIndex) -> ColorHelper.getColor(((SpiritShardItem) stack.getItem()).getSpiritHolder().getItemColor()),
                    SACRED_SPIRIT.get(), WICKED_SPIRIT.get(), ARCANE_SPIRIT.get(), ELDRITCH_SPIRIT.get(),
                    AQUEOUS_SPIRIT.get(), AERIAL_SPIRIT.get(), EARTHEN_SPIRIT.get(), INFERNAL_SPIRIT.get());

            event.register((stack, tintIndex) -> ColorHelper.getColor(((IGradientedLeavesBlock) ((BlockItem) stack.getItem()).getBlock()).getMaxColor()),
                    RUNEWOOD_LEAVES, HANGING_RUNEWOOD_LEAVES, AZURE_RUNEWOOD_LEAVES, HANGING_AZURE_RUNEWOOD_LEAVES);
            event.register((stack, tintIndex) -> ColorHelper.getColor(((IGradientedLeavesBlock) ((BlockItem) stack.getItem()).getBlock()).getMinColor()),
                    SOULWOOD_LEAVES, HANGING_SOULWOOD_LEAVES);

            event.register((s, c) -> switch (c) {
                        case 2 -> EtherItem.getSecondaryColor(s);
                        case 1 -> EtherItem.getPrimaryColor(s);
                        default -> -1;
                    },
                    ETHER_TORCH, IRIDESCENT_ETHER_TORCH,
                    ETHER_CANDLE, IRIDESCENT_ETHER_CANDLE,
                    TAINTED_ETHER_BRAZIER, TWISTED_ETHER_BRAZIER, DROSS_ETHER_BRAZIER,
                    TAINTED_IRIDESCENT_ETHER_BRAZIER, TWISTED_IRIDESCENT_ETHER_BRAZIER, DROSS_IRIDESCENT_ETHER_BRAZIER,
                    TAINTED_ETHER_CRESSET, TWISTED_ETHER_CRESSET, DROSS_ETHER_CRESSET,
                    TAINTED_IRIDESCENT_ETHER_CRESSET, TWISTED_IRIDESCENT_ETHER_CRESSET, DROSS_IRIDESCENT_ETHER_CRESSET);

            event.register((s, c) -> c == 0 ? EtherItem.getPrimaryColor(s) : EtherItem.getSecondaryColor(s),
                    ETHER, IRIDESCENT_ETHER);
        }
    }
}
