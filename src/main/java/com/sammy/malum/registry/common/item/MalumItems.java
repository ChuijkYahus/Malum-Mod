package com.sammy.malum.registry.common.item;

import com.sammy.malum.*;
import com.sammy.malum.client.renderer.curio.*;
import com.sammy.malum.common.block.curiosities.obelisk.brilliant.*;
import com.sammy.malum.common.block.curiosities.obelisk.rite_pylon.*;
import com.sammy.malum.common.block.curiosities.obelisk.runewood.*;
import com.sammy.malum.common.block.curiosities.repair_pylon.*;
import com.sammy.malum.common.block.curiosities.spirit_crucible.*;
import com.sammy.malum.common.block.curiosities.spirit_catalyzer.*;
import com.sammy.malum.common.block.dungeon.curiosities.*;
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
import com.sammy.malum.common.item.nucleus.PyreNucleusItem;
import com.sammy.malum.common.item.nucleus.WindNucleusItem;
import com.sammy.malum.common.item.spirit.*;
import com.sammy.malum.compat.farmersdelight.*;
import com.sammy.malum.core.enumextension.*;
import com.sammy.malum.registry.client.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.block.*;
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
import team.lodestar.lodestone.modules.toolkit.multiblock.*;
import top.theillusivec4.curios.api.client.*;

import java.util.function.*;

import static com.sammy.malum.MalumMod.*;
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


    public static <T extends Item> DeferredHolder<Item, T> register(String name, Supplier<LodestoneItemProperties> propertySupplier, Function<LodestoneItemProperties, T> function) {
        return ITEMS.register(name, () -> {
            var properties = propertySupplier.get();
            LodestoneItemProperties.addToTabSorting(MalumMod.malumPath(name), properties);
            return function.apply(properties);
        });
    }

    public static final DeferredHolder<Item, Item> ENCYCLOPEDIA_ARCANA = register("encyclopedia_arcana", () -> MalumItems.GEAR_PROPERTIES().rarity(UNCOMMON), EncyclopediaArcanaItem::new);

    public static final DeferredHolder<Item, GeasItem> GEAS = register("geas", () -> MalumItems.HIDDEN_PROPERTIES().rarity(RARE), GeasItem::new);

    //region spirits
    public static final DeferredHolder<Item, SpiritShardItem> SACRED_SPIRIT = register("sacred_spirit", MalumItems::DEFAULT_PROPERTIES, (p) -> new SpiritShardItem(p, MalumSpiritTypes.SACRED_SPIRIT));
    public static final DeferredHolder<Item, SpiritShardItem> WICKED_SPIRIT = register("wicked_spirit", MalumItems::DEFAULT_PROPERTIES, (p) -> new SpiritShardItem(p, MalumSpiritTypes.WICKED_SPIRIT));
    public static final DeferredHolder<Item, SpiritShardItem> ARCANE_SPIRIT = register("arcane_spirit", MalumItems::DEFAULT_PROPERTIES, (p) -> new SpiritShardItem(p, MalumSpiritTypes.ARCANE_SPIRIT));
    public static final DeferredHolder<Item, SpiritShardItem> ELDRITCH_SPIRIT = register("eldritch_spirit", MalumItems::DEFAULT_PROPERTIES, (p) -> new SpiritShardItem(p, MalumSpiritTypes.ELDRITCH_SPIRIT));
    public static final DeferredHolder<Item, SpiritShardItem> AERIAL_SPIRIT = register("aerial_spirit", MalumItems::DEFAULT_PROPERTIES, (p) -> new SpiritShardItem(p, MalumSpiritTypes.AERIAL_SPIRIT));
    public static final DeferredHolder<Item, SpiritShardItem> AQUEOUS_SPIRIT = register("aqueous_spirit", MalumItems::DEFAULT_PROPERTIES, (p) -> new SpiritShardItem(p, MalumSpiritTypes.AQUEOUS_SPIRIT));
    public static final DeferredHolder<Item, SpiritShardItem> EARTHEN_SPIRIT = register("earthen_spirit", MalumItems::DEFAULT_PROPERTIES, (p) -> new SpiritShardItem(p, MalumSpiritTypes.EARTHEN_SPIRIT));
    public static final DeferredHolder<Item, SpiritShardItem> INFERNAL_SPIRIT = register("infernal_spirit", MalumItems::DEFAULT_PROPERTIES, (p) -> new SpiritShardItem(p, MalumSpiritTypes.INFERNAL_SPIRIT));
    public static final DeferredHolder<Item, SpiritShardItem> UMBRAL_SPIRIT = register("umbral_spirit", MalumItems::DEFAULT_PROPERTIES, (p) -> new UmbralSpiritShardItem(p, MalumSpiritTypes.UMBRAL_SPIRIT));
    //endregion

    public static final DeferredHolder<Item, Item> ENCYCLOPEDIA_ESOTERICA = register("encyclopedia_esoterica", () -> MalumItems.GEAR_PROPERTIES().rarity(EPIC), EncyclopediaEsotericaItem::new);

    public static final DeferredHolder<Item, Item> ARCANE_ELEGY = register("music_disc_arcane_elegy", () -> MalumItems.HIDDEN_PROPERTIES().rarity(RARE), ArcaneElegyMusicDiscItem::new);
    public static final DeferredHolder<Item, Item> AESTHETICA = register("music_disc_aesthetica", () -> MalumItems.HIDDEN_PROPERTIES().rarity(RARE), AestheticaMusicDiscItem::new);

    //endregion

    //region crafting blocks
    public static final DeferredHolder<Item, Item> SPIRIT_ALTAR = register("spirit_altar", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.SPIRIT_ALTAR.get(), p));
    public static final DeferredHolder<Item, Item> RUNEWOOD_OBELISK = register("runewood_obelisk", MalumItems::DEFAULT_PROPERTIES, (p) -> new MultiBlockItem(MalumBlocks.RUNEWOOD_OBELISK.get(), p, RunewoodObeliskBlockEntity.STRUCTURE));
    public static final DeferredHolder<Item, Item> BRILLIANT_OBELISK = register("brilliant_obelisk", MalumItems::DEFAULT_PROPERTIES, (p) -> new MultiBlockItem(MalumBlocks.BRILLIANT_OBELISK.get(), p, BrilliantObeliskBlockEntity.STRUCTURE));
    public static final DeferredHolder<Item, Item> ARCANA_PYLON = register("arcana_pylon", MalumItems::DEFAULT_PROPERTIES, (p) -> new MultiBlockItem(MalumBlocks.ARCANA_PYLON.get(), p, ArcanaPylonBlockEntity.STRUCTURE));
    public static final DeferredHolder<Item, Item> SPIRIT_JAR = register("spirit_jar", MalumItems::DEFAULT_PROPERTIES, (p) -> new SpiritJarItem(MalumBlocks.SPIRIT_JAR.get(), p));
    public static final DeferredHolder<Item, Item> RUNIC_WORKBENCH = register("runic_workbench", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUNIC_WORKBENCH.get(), p));
    public static final DeferredHolder<Item, Item> SOUL_BRAZIER = register("soulbinding_brazier", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.SOUL_BRAZIER.get(), p));
    public static final DeferredHolder<Item, Item> SPIRIT_CRUCIBLE = register("spirit_crucible", MalumItems::DEFAULT_PROPERTIES, (p) -> new MultiBlockItem(MalumBlocks.SPIRIT_CRUCIBLE.get(), p, SpiritCrucibleCoreBlockEntity.STRUCTURE));
    public static final DeferredHolder<Item, Item> SPIRIT_CATALYZER = register("spirit_catalyzer", MalumItems::DEFAULT_PROPERTIES, (p) -> new MultiBlockItem(MalumBlocks.SPIRIT_CATALYZER.get(), p, SpiritCatalyzerCoreBlockEntity.STRUCTURE));
    public static final DeferredHolder<Item, Item> REPAIR_PYLON = register("repair_pylon", MalumItems::DEFAULT_PROPERTIES, (p) -> new MultiBlockItem(MalumBlocks.REPAIR_PYLON.get(), p, RepairPylonCoreBlockEntity.STRUCTURE));
    public static final DeferredHolder<Item, Item> RUNEWOOD_TOTEM_BASE = register("runewood_totem_base", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUNEWOOD_TOTEM_BASE.get(), p));
    public static final DeferredHolder<Item, Item> SOULWOOD_TOTEM_BASE = register("soulwood_totem_base", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.SOULWOOD_TOTEM_BASE.get(), p));
    public static final DeferredHolder<Item, Item> RITE_ANCHOR = register("rite_anchor", MalumItems::DEFAULT_PROPERTIES, p -> new BlockItem(MalumBlocks.RITE_ANCHOR.get(), p));
    public static final DeferredHolder<Item, Item> RITE_UNWEAVER = register("rite_unweaver", MalumItems::DEFAULT_PROPERTIES, p -> new BlockItem(MalumBlocks.RITE_UNWEAVER.get(), p));
    public static final DeferredHolder<Item, Item> RITE_SPREADER = register("rite_spreader", MalumItems::DEFAULT_PROPERTIES, p -> new BlockItem(MalumBlocks.RITE_SPREADER.get(), p));
    public static final DeferredHolder<Item, Item> RITE_CHANNEL = register("rite_channel", MalumItems::DEFAULT_PROPERTIES, p -> new BlockItem(MalumBlocks.RITE_CHANNEL.get(), p));
    public static final DeferredHolder<Item, Item> WAVEFORM_RUNEWOOD_TOTEM_BASE = register("waveform_runewood_totem_base", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.WAVEFORM_RUNEWOOD_TOTEM_BASE.get(), p));
    public static final DeferredHolder<Item, Item> WAVEFORM_SOULWOOD_TOTEM_BASE = register("waveform_soulwood_totem_base", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.WAVEFORM_SOULWOOD_TOTEM_BASE.get(), p));

    public static final DeferredHolder<Item, Item> RITUAL_PLINTH = register("ritual_plinth", MalumItems::HIDDEN_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RITUAL_PLINTH.get(), p));


    public static final DeferredHolder<Item, Item> WAVECHARGER = register("wavecharger", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.WAVECHARGER.get(), p));
    public static final DeferredHolder<Item, Item> WAVEBANKER = register("wavebanker", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.WAVEBANKER.get(), p));
    public static final DeferredHolder<Item, Item> WAVEMAKER = register("wavemaker", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.WAVEMAKER.get(), p));
    public static final DeferredHolder<Item, Item> WAVEBREAKER = register("wavebreaker", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.WAVEBREAKER.get(), p));

    public static final DeferredHolder<Item, Item> GUST_IGNITER = register("gust_igniter", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.GUST_IGNITER.get(), p));
    public static final DeferredHolder<Item, Item> WIND_TUNNEL = register("wind_tunnel", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.WIND_TUNNEL.get(), p));

    public static final DeferredHolder<Item, Item> WEAVERS_WORKBENCH = register("weavers_workbench", MalumItems::COSMETIC_PROPERTIES, (p) -> new BlockItem(MalumBlocks.WEAVERS_WORKBENCH.get(), p));
    //endregion

    //region contents
    public static final DeferredHolder<Item, Item> CONCENTRATED_GLUTTONY = register("concentrated_gluttony", () -> MalumItems.DEFAULT_PROPERTIES().food(MalumFoodProperties.CONCENTRATED_GLUTTONY), ConcentratedGluttonyItem::new);
    public static final DeferredHolder<Item, Item> SPLASH_OF_GLUTTONY = register("splash_of_gluttony", MalumItems::DEFAULT_PROPERTIES, SplashOfGluttonyItem::new);
    public static final DeferredHolder<Item, Item> SOULWOVEN_POUCH = register("soulwoven_pouch", MalumItems::GEAR_PROPERTIES, SoulwovenPouchItem::new);
    public static final DeferredHolder<Item, Item> RAVENOUS_POUCH = register("ravenous_pouch", MalumItems::GEAR_PROPERTIES, RavenousPouchItem::new);
    public static final DeferredHolder<Item, Item> TOTEMIC_STAFF = register("totemic_staff", MalumItems::GEAR_PROPERTIES, TinkeringToolItem::new);
    public static final DeferredHolder<Item, Item> ARTIFICERS_CLAW = register("artificers_claw", MalumItems::GEAR_PROPERTIES, TinkeringToolItem::new);
    public static final DeferredHolder<Item, Item> TUNING_FORK = register("tuning_fork", MalumItems::GEAR_PROPERTIES, TinkeringToolItem::new);
    public static final DeferredHolder<Item, Item> LAMPLIGHTERS_TONGS = register("lamplighters_tongs", MalumItems::GEAR_PROPERTIES, LamplightersTongsItem::new);

    public static final DeferredHolder<Item, Item> CATALYST_LOBBER = register("catalyst_lobber", MalumItems::GEAR_PROPERTIES, (p) -> new CatalystLobberItem(p.durability(500), EthericNitrate::new));

    public static final DeferredHolder<Item, Item> CRUDE_SCYTHE = register("crude_scythe", MalumItems::GEAR_PROPERTIES, (p) -> new MalumScytheItem(Tiers.IRON, 0, 0.1f, p.durability(500)));
    public static final DeferredHolder<Item, Item> SOUL_STAINED_STEEL_SCYTHE = register("soul_stained_steel_scythe", MalumItems::GEAR_PROPERTIES, (p) -> new MagicScytheItem(SOUL_STAINED_STEEL, -3.5f, 0.2f, 4, p));
    public static final DeferredHolder<Item, Item> SOUL_STAINED_STEEL_KNIFE = register("soul_stained_steel_knife", () -> FarmersDelightCompat.LOADED ? GEAR_PROPERTIES() : HIDDEN_PROPERTIES(), (p) -> FarmersDelightCompat.LOADED ? FarmersDelightCompat.LoadedOnly.makeMagicKnife(p) : new Item(p));

    public static final DeferredHolder<Item, Item> SOUL_STAINED_STEEL_HELMET = register("soul_stained_steel_helmet", MalumItems::GEAR_PROPERTIES, (p) -> new SoulStainedSteelArmorItem(ArmorItem.Type.HELMET, p));
    public static final DeferredHolder<Item, Item> SOUL_STAINED_STEEL_CHESTPLATE = register("soul_stained_steel_chestplate", MalumItems::GEAR_PROPERTIES, (p) -> new SoulStainedSteelArmorItem(ArmorItem.Type.CHESTPLATE, p));
    public static final DeferredHolder<Item, Item> SOUL_STAINED_STEEL_LEGGINGS = register("soul_stained_steel_leggings", MalumItems::GEAR_PROPERTIES, (p) -> new SoulStainedSteelArmorItem(ArmorItem.Type.LEGGINGS, p));
    public static final DeferredHolder<Item, Item> SOUL_STAINED_STEEL_BOOTS = register("soul_stained_steel_boots", MalumItems::GEAR_PROPERTIES, (p) -> new SoulStainedSteelArmorItem(ArmorItem.Type.BOOTS, p));

    public static final DeferredHolder<Item, Item> SOUL_STAINED_STEEL_SWORD = register("soul_stained_steel_sword", MalumItems::GEAR_PROPERTIES, (p) -> new MagicSwordItem(SOUL_STAINED_STEEL, -3, 0, 3, p));
    public static final DeferredHolder<Item, Item> SOUL_STAINED_STEEL_PICKAXE = register("soul_stained_steel_pickaxe", MalumItems::GEAR_PROPERTIES, (p) -> new MagicPickaxeItem(SOUL_STAINED_STEEL, -2, 0, 2, p));
    public static final DeferredHolder<Item, Item> SOUL_STAINED_STEEL_AXE = register("soul_stained_steel_axe", MalumItems::GEAR_PROPERTIES, (p) -> new MagicAxeItem(SOUL_STAINED_STEEL, -3, 0, 4, p));
    public static final DeferredHolder<Item, Item> SOUL_STAINED_STEEL_SHOVEL = register("soul_stained_steel_shovel", MalumItems::GEAR_PROPERTIES, (p) -> new MagicShovelItem(SOUL_STAINED_STEEL, -2, 0, 2, p));
    public static final DeferredHolder<Item, Item> SOUL_STAINED_STEEL_HOE = register("soul_stained_steel_hoe", MalumItems::GEAR_PROPERTIES, (p) -> new MagicHoeItem(SOUL_STAINED_STEEL, 0, -1.5f, 1, p));

    public static final DeferredHolder<Item, Item> SOUL_HUNTER_CLOAK = register("soul_hunter_cloak", MalumItems::GEAR_PROPERTIES, (p) -> new SoulHunterArmorItem(ArmorItem.Type.HELMET, p));
    public static final DeferredHolder<Item, Item> SOUL_HUNTER_ROBE = register("soul_hunter_robe", MalumItems::GEAR_PROPERTIES, (p) -> new SoulHunterArmorItem(ArmorItem.Type.CHESTPLATE, p));
    public static final DeferredHolder<Item, Item> SOUL_HUNTER_LEGGINGS = register("soul_hunter_leggings", MalumItems::GEAR_PROPERTIES, (p) -> new SoulHunterArmorItem(ArmorItem.Type.LEGGINGS, p));
    public static final DeferredHolder<Item, Item> SOUL_HUNTER_BOOTS = register("soul_hunter_boots", MalumItems::GEAR_PROPERTIES, (p) -> new SoulHunterArmorItem(ArmorItem.Type.BOOTS, p));

    public static final DeferredHolder<Item, Item> RAVENOUS_SCYTHE = register("ravenous_scythe", MalumItems::GEAR_PROPERTIES, (p) -> new RavenousScytheItem(RAVENOUS, -4f, 0.4f, 2.5f, p));
    public static final DeferredHolder<Item, Item> GLUTTONOUS_BLUDGEON = register("gluttonous_bludgeon", MalumItems::GEAR_PROPERTIES, (p) -> new GluttonousBludgeonItem(RAVENOUS, -2.5f, -3f, 2, p));
    public static final DeferredHolder<Item, Item> TYRVING = register("tyrving", MalumItems::GEAR_PROPERTIES, (p) -> new TyrvingItem(MalumItemTiers.TYRVING, 0, -0.3f, p));

    public static final DeferredHolder<Item, Item> MNEMONIC_HEX_STAFF = register("mnemonic_hex_staff", MalumItems::GEAR_PROPERTIES, (p) -> new HexStaffItem(HEX_STAFF, 5, 1, 2, p));
    public static final DeferredHolder<Item, Item> EROSION_SCEPTER = register("erosion_scepter", MalumItems::GEAR_PROPERTIES, (p) -> new ErosionScepterItem(MALIGNANT_ALLOY, 5, 0.5f, 1, p));

    public static final DeferredHolder<Item, Item> WEIGHT_OF_WORLDS = register("weight_of_worlds", MalumItems::GEAR_PROPERTIES, (p) -> new WeightOfWorldsItem(MalumItemTiers.MALIGNANT_ALLOY, 1, -0.2f, p));
    public static final DeferredHolder<Item, Item> EDGE_OF_DELIVERANCE = register("edge_of_deliverance", MalumItems::GEAR_PROPERTIES, (p) -> new EdgeOfDeliveranceItem(MalumItemTiers.MALIGNANT_ALLOY, 2, -0.1f, p));

    public static final DeferredHolder<Item, Item> MALIGNANT_STRONGHOLD_HELMET = register("malignant_stronghold_helmet", MalumItems::GEAR_PROPERTIES, (p) -> new MalignantStrongholdArmorItem(ArmorItem.Type.HELMET, p));
    public static final DeferredHolder<Item, Item> MALIGNANT_STRONGHOLD_CHESTPLATE = register("malignant_stronghold_chestplate", MalumItems::GEAR_PROPERTIES, (p) -> new MalignantStrongholdArmorItem(ArmorItem.Type.CHESTPLATE, p));
    public static final DeferredHolder<Item, Item> MALIGNANT_STRONGHOLD_LEGGINGS = register("malignant_stronghold_leggings", MalumItems::GEAR_PROPERTIES, (p) -> new MalignantStrongholdArmorItem(ArmorItem.Type.LEGGINGS, p));
    public static final DeferredHolder<Item, Item> MALIGNANT_STRONGHOLD_BOOTS = register("malignant_stronghold_boots", MalumItems::GEAR_PROPERTIES, (p) -> new MalignantStrongholdArmorItem(ArmorItem.Type.BOOTS, p));

    public static final DeferredHolder<Item, Item> UNWINDING_CHAOS = register("unwinding_chaos", () -> GEAR_PROPERTIES().rarity(EPIC), (p) -> new UnwindingChaosStaffItem(HARNESSED_CHAOS, 5, 1.5f, 3, p));
    public static final DeferredHolder<Item, Item> SUNDERING_ANCHOR = register("sundering_anchor", () -> GEAR_PROPERTIES().rarity(EPIC), (p) -> new SunderingAnchorItem(HARNESSED_CHAOS, 4, p));

    public static final DeferredHolder<Item, Item> SPELLWEAVING_PICKAXE = register("spellweaving_pickaxe", MalumItems::GEAR_PROPERTIES, (p) -> new SpellweavingPickaxeItem(SPELLWEAVING_TOOLS, -3, 0, 4, p));
    public static final DeferredHolder<Item, Item> SPELLWEAVING_AXE = register("spellweaving_axe", MalumItems::GEAR_PROPERTIES, (p) -> new SpellweavingAxeItem(SPELLWEAVING_TOOLS, -4, 0, 6, p));

    public static final DeferredHolder<Item, Item> GILDED_RING = register("gilded_ring", MalumItems::GEAR_PROPERTIES, CurioGildedRing::new);
    public static final DeferredHolder<Item, Item> GILDED_BELT = register("gilded_belt", MalumItems::GEAR_PROPERTIES, CurioGildedBelt::new);
    public static final DeferredHolder<Item, Item> ORNATE_RING = register("ornate_ring", MalumItems::GEAR_PROPERTIES, CurioOrnateRing::new);
    public static final DeferredHolder<Item, Item> ORNATE_NECKLACE = register("ornate_necklace", MalumItems::GEAR_PROPERTIES, CurioOrnateNecklace::new);

    public static final DeferredHolder<Item, Item> RUNIC_BROOCH = register("runic_brooch", MalumItems::GEAR_PROPERTIES, CurioRunicBrooch::new);
    public static final DeferredHolder<Item, Item> ELABORATE_BROOCH = register("elaborate_brooch", MalumItems::GEAR_PROPERTIES, CurioElaborateBrooch::new);
    public static final DeferredHolder<Item, Item> GLASS_BROOCH = register("glass_brooch", MalumItems::GEAR_PROPERTIES, CurioGlassBrooch::new);
    public static final DeferredHolder<Item, Item> GLUTTONOUS_BROOCH = register("gluttonous_brooch", MalumItems::GEAR_PROPERTIES, CurioGluttonousBrooch::new);

    public static final DeferredHolder<Item, Item> RING_OF_ESOTERIC_SPOILS = register("ring_of_esoteric_spoils", MalumItems::GEAR_PROPERTIES, CurioArcaneSpoilRing::new);
    public static final DeferredHolder<Item, Item> RING_OF_ESOTERIC_SHADOW = register("ring_of_esoteric_shadow", MalumItems::GEAR_PROPERTIES, CurioConcealingRing::new);

    public static final DeferredHolder<Item, Item> RING_OF_CURATIVE_TALENT = register("ring_of_curative_talent", MalumItems::GEAR_PROPERTIES, CurioCurativeRing::new);
    public static final DeferredHolder<Item, Item> RING_OF_ALCHEMICAL_MASTERY = register("ring_of_alchemical_mastery", MalumItems::GEAR_PROPERTIES, CurioAlchemicalRing::new);
    public static final DeferredHolder<Item, Item> RING_OF_MANAWEAVING = register("ring_of_manaweaving", MalumItems::GEAR_PROPERTIES, CurioManaweavingRing::new);
    public static final DeferredHolder<Item, Item> RING_OF_ARCANE_PROWESS = register("ring_of_arcane_prowess", MalumItems::GEAR_PROPERTIES, CurioProwessRing::new);

    public static final DeferredHolder<Item, Item> RING_OF_DESPERATE_VORACITY = register("ring_of_desperate_voracity", MalumItems::GEAR_PROPERTIES, CurioVoraciousRing::new);
    public static final DeferredHolder<Item, Item> RING_OF_SWARMING_ROT = register("ring_of_swarming_rot", MalumItems::GEAR_PROPERTIES, CurioSwarmingRing::new);

    public static final DeferredHolder<Item, Item> RING_OF_THE_RISING_EDGE = register("ring_of_the_rising_edge", MalumItems::GEAR_PROPERTIES, CurioRisingEdgeRing::new);
    public static final DeferredHolder<Item, Item> RING_OF_THE_HOWLING_MAELSTROM = register("ring_of_the_howling_maelstrom", MalumItems::GEAR_PROPERTIES, CurioHowlingMaelstromRing::new);

    public static final DeferredHolder<Item, Item> RING_OF_HEARTY_AVARICE = register("ring_of_hearty_avarice", MalumItems::GEAR_PROPERTIES, CurioHeartyAvariceRing::new);
    public static final DeferredHolder<Item, Item> RING_OF_HEAVY_DISCHARGE = register("ring_of_heavy_discharge", MalumItems::GEAR_PROPERTIES, CurioDischargeRing::new);

    public static final DeferredHolder<Item, Item> NECKLACE_OF_MYSTIC_POTENCY = register("necklace_of_mystic_potency", MalumItems::GEAR_PROPERTIES, CurioMysticNecklace::new);
    public static final DeferredHolder<Item, Item> NECKLACE_OF_THE_NARROW_EDGE = register("necklace_of_the_narrow_edge", MalumItems::GEAR_PROPERTIES, CurioNarrowEdgeNecklace::new);

    public static final DeferredHolder<Item, Item> BELT_OF_THE_STARVED = register("belt_of_the_starved", MalumItems::GEAR_PROPERTIES, CurioStarvedBelt::new);
    public static final DeferredHolder<Item, Item> BELT_OF_THE_PROSPECTOR = register("belt_of_the_prospector", MalumItems::GEAR_PROPERTIES, CurioProspectorBelt::new);
    public static final DeferredHolder<Item, Item> BELT_OF_THE_MAGEBANE = register("belt_of_the_magebane", MalumItems::GEAR_PROPERTIES, CurioMagebaneBelt::new);

    public static final DeferredHolder<Item, Item> RING_OF_THE_ENDLESS_WELL = register("ring_of_the_endless_well", MalumItems::GEAR_PROPERTIES, CurioEndlessRing::new);
    public static final DeferredHolder<Item, Item> RING_OF_ECHOING_ARCANA = register("ring_of_echoing_arcana", MalumItems::GEAR_PROPERTIES, CurioEchoingArcanaRing::new);
    public static final DeferredHolder<Item, Item> RING_OF_GROWING_FLESH = register("ring_of_growing_flesh", MalumItems::GEAR_PROPERTIES, CurioGrowingFleshRing::new);
    public static final DeferredHolder<Item, Item> RING_OF_GRUESOME_CONCENTRATION = register("ring_of_gruesome_concentration", MalumItems::GEAR_PROPERTIES, CurioGruesomeConcentrationRing::new);
    public static final DeferredHolder<Item, Item> NECKLACE_OF_THE_HIDDEN_BLADE = register("necklace_of_the_hidden_blade", MalumItems::GEAR_PROPERTIES, CurioHiddenBladeNecklace::new);
    public static final DeferredHolder<Item, Item> NECKLACE_OF_THE_WATCHER = register("necklace_of_the_watcher", MalumItems::GEAR_PROPERTIES, CurioWatcherNecklace::new);
    public static final DeferredHolder<Item, Item> BELT_OF_THE_LIMITLESS = register("belt_of_the_limitless", MalumItems::GEAR_PROPERTIES, CurioLimitlessBelt::new);

    public static final DeferredHolder<Item, Item> RUNE_OF_VITALITY = register("rune_of_vitality", MalumItems::GEAR_PROPERTIES, RuneVitalityItem::new);
    public static final DeferredHolder<Item, Item> RUNE_OF_CULLING = register("rune_of_culling", MalumItems::GEAR_PROPERTIES, RuneCullingItem::new);
    public static final DeferredHolder<Item, Item> RUNE_OF_REINFORCEMENT = register("rune_of_reinforcement", MalumItems::GEAR_PROPERTIES, RuneReinforcementItem::new);
    public static final DeferredHolder<Item, Item> RUNE_OF_VOLATILE_DISTORTION = register("rune_of_volatile_distortion", MalumItems::GEAR_PROPERTIES, RuneVolatileDistortionItem::new);
    public static final DeferredHolder<Item, Item> RUNE_OF_DEXTERITY = register("rune_of_dexterity", MalumItems::GEAR_PROPERTIES, RuneDexterityItem::new);
    public static final DeferredHolder<Item, Item> RUNE_OF_AILMENT_CLEANSING = register("rune_of_ailment_cleansing", MalumItems::GEAR_PROPERTIES, RuneAilmentCleansingItem::new);
    public static final DeferredHolder<Item, Item> RUNE_OF_PROTECTION = register("rune_of_protection", MalumItems::GEAR_PROPERTIES, RuneProtectionItem::new);
    public static final DeferredHolder<Item, Item> RUNE_OF_SCORCHING = register("rune_of_scorching", MalumItems::GEAR_PROPERTIES, RuneScorchingItem::new);

    public static final DeferredHolder<Item, Item> RUNE_OF_HOWLING_GALE = register("rune_of_howling_gale", MalumItems::GEAR_PROPERTIES, RuneHowlingGale::new);
    public static final DeferredHolder<Item, Item> RUNE_OF_FLOWING_GRASP = register("rune_of_flowing_grasp", MalumItems::GEAR_PROPERTIES, RuneFlowingGrasp::new);
    public static final DeferredHolder<Item, Item> RUNE_OF_STONE_WARD = register("rune_of_stone_ward", MalumItems::GEAR_PROPERTIES, RuneStoneWard::new);
    public static final DeferredHolder<Item, Item> RUNE_OF_BURNING_FERVOR = register("rune_of_burning_fervor", MalumItems::GEAR_PROPERTIES, RuneBurningFervor::new);
    public static final DeferredHolder<Item, Item> RUNE_OF_SKY_TETHER = register("rune_of_sky_tether", MalumItems::GEAR_PROPERTIES, RuneSkyTether::new);
    public static final DeferredHolder<Item, Item> RUNE_OF_GOOD_TIDES = register("rune_of_good_tides", MalumItems::GEAR_PROPERTIES, RuneGoodTides::new);
    public static final DeferredHolder<Item, Item> RUNE_OF_OAKEN_MIGHT = register("rune_of_oaken_might", MalumItems::GEAR_PROPERTIES, RuneOakenMight::new);
    public static final DeferredHolder<Item, Item> RUNE_OF_FIERY_EMBRACE = register("rune_of_fiery_embrace", MalumItems::GEAR_PROPERTIES, RuneFieryEmbrace::new);

    public static final DeferredHolder<Item, Item> RUNE_OF_BOLSTERING = register("rune_of_bolstering", MalumItems::GEAR_PROPERTIES, RuneBolsteringItem::new);
    public static final DeferredHolder<Item, Item> RUNE_OF_RADIAL_EMPOWERMENT = register("rune_of_radial_empowerment", MalumItems::GEAR_PROPERTIES, RuneRadialEmpowermentItem::new);
    public static final DeferredHolder<Item, Item> RUNE_OF_SPELL_MASTERY = register("rune_of_spell_mastery", MalumItems::GEAR_PROPERTIES, RuneSpellMasteryItem::new);
    public static final DeferredHolder<Item, Item> RUNE_OF_HERESY = register("rune_of_heresy", MalumItems::GEAR_PROPERTIES, RuneHeresyItem::new);
    public static final DeferredHolder<Item, Item> RUNE_OF_UNNATURAL_STAMINA = register("rune_of_unnatural_stamina", MalumItems::GEAR_PROPERTIES, RuneUnnaturalStaminaItem::new);
    public static final DeferredHolder<Item, Item> RUNE_OF_TWINNED_DURATION = register("rune_of_twinned_duration", MalumItems::GEAR_PROPERTIES, RuneTwinnedDurationItem::new);
    public static final DeferredHolder<Item, Item> RUNE_OF_INDOMITABILITY = register("rune_of_indomitability", MalumItems::GEAR_PROPERTIES, RuneIndomitabilityItem::new);
    public static final DeferredHolder<Item, Item> RUNE_OF_IGNEOUS_SOLACE = register("rune_of_igneous_solace", MalumItems::GEAR_PROPERTIES, RuneIgneousSolaceItem::new);
    //endregion

    //region augments
    public static final DeferredHolder<Item, Item> MENDING_DIFFUSER = register("mending_diffuser", MalumItems::DEFAULT_PROPERTIES, MendingDiffuserItem::new);
    public static final DeferredHolder<Item, Item> IMPURITY_STABILIZER = register("impurity_stabilizer", MalumItems::DEFAULT_PROPERTIES, ImpurityStabilizer::new);
    public static final DeferredHolder<Item, Item> SHIELDING_APPARATUS = register("shielding_apparatus", MalumItems::DEFAULT_PROPERTIES, ShieldingApparatusItem::new);
    public static final DeferredHolder<Item, Item> WARPING_ENGINE = register("warping_engine", MalumItems::DEFAULT_PROPERTIES, WarpingEngineItem::new);
    public static final DeferredHolder<Item, Item> ACCELERATING_INLAY = register("accelerating_inlay", MalumItems::DEFAULT_PROPERTIES, AcceleratingInlayItem::new);
    public static final DeferredHolder<Item, Item> PRISMATIC_FOCUS_LENS = register("prismatic_focus_lens", MalumItems::DEFAULT_PROPERTIES, PrismaticFocusLensItem::new);
    public static final DeferredHolder<Item, Item> BLAZING_DIODE = register("blazing_diode", MalumItems::DEFAULT_PROPERTIES, BlazingDiodeItem::new);
    public static final DeferredHolder<Item, Item> INTRICATE_ASSEMBLY = register("intricate_assembly", MalumItems::DEFAULT_PROPERTIES, IntricateAssemblyItem::new);

    public static final DeferredHolder<Item, Item> SYMPATHY_DRIVE = register("sympathy_drive", MalumItems::DEFAULT_PROPERTIES, SympathyDrive::new);
    public static final DeferredHolder<Item, Item> SUSPICIOUS_DEVICE = register("suspicious_device", MalumItems::DEFAULT_PROPERTIES, SuspiciousDeviceItem::new);
    public static final DeferredHolder<Item, Item> CAUSTIC_CATALYST = register("caustic_catalyst", MalumItems::DEFAULT_PROPERTIES, CausticCatalystItem::new);
    public static final DeferredHolder<Item, Item> RESONANCE_TUNER = register("resonance_tuner", MalumItems::DEFAULT_PROPERTIES, ResonanceTuner::new);

    public static final DeferredHolder<Item, Item> STELLAR_MECHANISM = register("stellar_mechanism", MalumItems::DEFAULT_PROPERTIES, StellarMechanismItem::new);
    //endregion

    //region ether
    public static final DeferredHolder<Item, Item> ETHER = register("ether", MalumItems::DEFAULT_PROPERTIES, (p) -> new EtherItem(MalumBlocks.ETHER.get(), p, false));
    public static final DeferredHolder<Item, Item> IRIDESCENT_ETHER = register("iridescent_ether", MalumItems::DEFAULT_PROPERTIES, (p) -> new EtherItem(MalumBlocks.IRIDESCENT_ETHER.get(), p, true));

    public static final DeferredHolder<Item, Item> ETHER_CANDLE = register("ether_candle", MalumItems::DEFAULT_PROPERTIES, (p) -> new EtherCandleItem(MalumBlocks.ETHER_CANDLE.get(), p, false));
    public static final DeferredHolder<Item, Item> IRIDESCENT_ETHER_CANDLE = register("iridescent_ether_candle", MalumItems::DEFAULT_PROPERTIES, (p) -> new EtherCandleItem(MalumBlocks.IRIDESCENT_ETHER_CANDLE.get(), p, true));

    public static final DeferredHolder<Item, Item> ETHER_TORCH = register("ether_torch", MalumItems::DEFAULT_PROPERTIES, (p) -> new EtherTorchItem(MalumBlocks.ETHER_TORCH.get(), MalumBlocks.WALL_ETHER_TORCH.get(), p, false));
    public static final DeferredHolder<Item, Item> IRIDESCENT_ETHER_TORCH = register("iridescent_ether_torch", MalumItems::DEFAULT_PROPERTIES, (p) -> new EtherTorchItem(MalumBlocks.IRIDESCENT_ETHER_TORCH.get(), MalumBlocks.IRIDESCENT_WALL_ETHER_TORCH.get(), p, true));

    public static final DeferredHolder<Item, Item> TAINTED_ETHER_BRAZIER = register("tainted_ether_brazier", MalumItems::DEFAULT_PROPERTIES, (p) -> new EtherBrazierItem(MalumBlocks.TAINTED_ETHER_BRAZIER.get(), p, false));
    public static final DeferredHolder<Item, Item> TWISTED_ETHER_BRAZIER = register("twisted_ether_brazier", MalumItems::DEFAULT_PROPERTIES, (p) -> new EtherBrazierItem(MalumBlocks.TWISTED_ETHER_BRAZIER.get(), p, false));
    public static final DeferredHolder<Item, Item> DROSS_ETHER_BRAZIER = register("dross_ether_brazier", MalumItems::DEFAULT_PROPERTIES, (p) -> new EtherBrazierItem(MalumBlocks.DROSS_ETHER_BRAZIER.get(), p, false));
    public static final DeferredHolder<Item, Item> TAINTED_IRIDESCENT_ETHER_BRAZIER = register("tainted_iridescent_ether_brazier", MalumItems::DEFAULT_PROPERTIES, (p) -> new EtherBrazierItem(MalumBlocks.TAINTED_IRIDESCENT_ETHER_BRAZIER.get(), p, true));
    public static final DeferredHolder<Item, Item> TWISTED_IRIDESCENT_ETHER_BRAZIER = register("twisted_iridescent_ether_brazier", MalumItems::DEFAULT_PROPERTIES, (p) -> new EtherBrazierItem(MalumBlocks.TWISTED_IRIDESCENT_ETHER_BRAZIER.get(), p, true));
    public static final DeferredHolder<Item, Item> DROSS_IRIDESCENT_ETHER_BRAZIER = register("dross_iridescent_ether_brazier", MalumItems::DEFAULT_PROPERTIES, (p) -> new EtherBrazierItem(MalumBlocks.DROSS_IRIDESCENT_ETHER_BRAZIER.get(), p, true));

    public static final DeferredHolder<Item, Item> TAINTED_ETHER_CRESSET = register("tainted_ether_cresset", MalumItems::DEFAULT_PROPERTIES, (p) -> new EtherCressetItem(MalumBlocks.TAINTED_ETHER_CRESSET.get(), p, false));
    public static final DeferredHolder<Item, Item> TWISTED_ETHER_CRESSET = register("twisted_ether_cresset", MalumItems::DEFAULT_PROPERTIES, (p) -> new EtherCressetItem(MalumBlocks.TWISTED_ETHER_CRESSET.get(), p, false));
    public static final DeferredHolder<Item, Item> DROSS_ETHER_CRESSET = register("dross_ether_cresset", MalumItems::DEFAULT_PROPERTIES, (p) -> new EtherCressetItem(MalumBlocks.DROSS_ETHER_CRESSET.get(), p, false));
    public static final DeferredHolder<Item, Item> TAINTED_IRIDESCENT_ETHER_CRESSET = register("tainted_iridescent_ether_cresset", MalumItems::DEFAULT_PROPERTIES, (p) -> new EtherCressetItem(MalumBlocks.TAINTED_IRIDESCENT_ETHER_CRESSET.get(), p, true));
    public static final DeferredHolder<Item, Item> TWISTED_IRIDESCENT_ETHER_CRESSET = register("twisted_iridescent_ether_cresset", MalumItems::DEFAULT_PROPERTIES, (p) -> new EtherCressetItem(MalumBlocks.TWISTED_IRIDESCENT_ETHER_CRESSET.get(), p, true));
    public static final DeferredHolder<Item, Item> DROSS_IRIDESCENT_ETHER_CRESSET = register("dross_iridescent_ether_cresset", MalumItems::DEFAULT_PROPERTIES, (p) -> new EtherCressetItem(MalumBlocks.DROSS_IRIDESCENT_ETHER_CRESSET.get(), p, true));
    //endregion

    //region ores
    public static final DeferredHolder<Item, Item> BLOCK_OF_SOULSTONE = register("block_of_soulstone", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BLOCK_OF_SOULSTONE.get(), p));
    public static final DeferredHolder<Item, Item> BLOCK_OF_RAW_SOULSTONE = register("block_of_raw_soulstone", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BLOCK_OF_RAW_SOULSTONE.get(), p));
    public static final DeferredHolder<Item, Item> DEEPSLATE_SOULSTONE_ORE = register("deepslate_soulstone_ore", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.DEEPSLATE_SOULSTONE_ORE.get(), p));
    public static final DeferredHolder<Item, Item> SOULSTONE_ORE = register("soulstone_ore", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.SOULSTONE_ORE.get(), p));
    public static final DeferredHolder<Item, Item> RAW_SOULSTONE = register("raw_soulstone", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredHolder<Item, Item> CRUSHED_SOULSTONE = register("crushed_soulstone", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredHolder<Item, Item> REFINED_SOULSTONE = register("refined_soulstone", MalumItems::DEFAULT_PROPERTIES, Item::new);

    public static final DeferredHolder<Item, Item> BLOCK_OF_BRILLIANCE = register("block_of_brilliance", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BLOCK_OF_BRILLIANCE.get(), p));
    public static final DeferredHolder<Item, Item> BLOCK_OF_RAW_BRILLIANCE = register("block_of_raw_brilliance", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BLOCK_OF_RAW_BRILLIANCE.get(), p));
    public static final DeferredHolder<Item, Item> BRILLIANT_DEEPSLATE = register("brilliant_deepslate", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BRILLIANT_DEEPSLATE.get(), p));
    public static final DeferredHolder<Item, Item> BRILLIANT_STONE = register("brilliant_stone", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BRILLIANT_STONE.get(), p));
    public static final DeferredHolder<Item, Item> RAW_BRILLIANCE = register("raw_brilliance", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredHolder<Item, Item> CRUSHED_BRILLIANCE = register("crushed_brilliance", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredHolder<Item, Item> REFINED_BRILLIANCE = register("refined_brilliance", MalumItems::DEFAULT_PROPERTIES, (p) -> new BrillianceChunkItem(p.food((new FoodProperties.Builder()).fast().alwaysEdible().build())));

    public static final DeferredHolder<Item, Item> BLOCK_OF_BLAZING_QUARTZ = register("block_of_blazing_quartz", MalumItems::DEFAULT_PROPERTIES, (p) -> new LodestoneFuelBlockItem(MalumBlocks.BLOCK_OF_BLAZING_QUARTZ.get(), p, 16000));
    public static final DeferredHolder<Item, Item> BLAZING_QUARTZ_ORE = register("blazing_quartz_ore", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BLAZING_QUARTZ_ORE.get(), p));
    public static final DeferredHolder<Item, Item> BLAZING_QUARTZ = register("blazing_quartz", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlazingQuartzItem(MalumBlocks.BLAZING_QUARTZ_CLUSTER.get(), 1600, p));

    public static final DeferredHolder<Item, Item> BLOCK_OF_NATURAL_QUARTZ = register("block_of_natural_quartz", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BLOCK_OF_NATURAL_QUARTZ.get(), p));
    public static final DeferredHolder<Item, Item> DEEPSLATE_QUARTZ_ORE = register("deepslate_quartz_ore", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.DEEPSLATE_QUARTZ_ORE.get(), p));
    public static final DeferredHolder<Item, Item> NATURAL_QUARTZ_ORE = register("natural_quartz_ore", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.NATURAL_QUARTZ_ORE.get(), p));
    public static final DeferredHolder<Item, Item> NATURAL_QUARTZ = register("natural_quartz", MalumItems::DEFAULT_PROPERTIES, (p) -> new ItemNameBlockItem(MalumBlocks.NATURAL_QUARTZ_CLUSTER.get(), p));

    public static final DeferredHolder<Item, Item> BLOCK_OF_CTHONIC_GOLD = register("block_of_cthonic_gold", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BLOCK_OF_CTHONIC_GOLD.get(), p));
    public static final DeferredHolder<Item, Item> CTHONIC_GOLD_ORE = register("cthonic_gold_ore", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.CTHONIC_GOLD_ORE.get(), p));
    public static final DeferredHolder<Item, Item> CTHONIC_GOLD = register("cthonic_gold", () -> DEFAULT_PROPERTIES().rarity(UNCOMMON), Item::new);
    public static final DeferredHolder<Item, Item> CTHONIC_GOLD_FRAGMENT = register("cthonic_gold_fragment", MalumItems::DEFAULT_PROPERTIES, (p) -> new ItemNameBlockItem(MalumBlocks.CTHONIC_GOLD_CLUSTER.get(), p));
    //endregion

    //region materials
    public static final DeferredHolder<Item, Item> BLOCK_OF_ROTTING_ESSENCE = register("block_of_rotting_essence", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BLOCK_OF_ROTTING_ESSENCE.get(), p));
    public static final DeferredHolder<Item, Item> BLOCK_OF_GRIM_TALC = register("block_of_grim_talc", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BLOCK_OF_GRIM_TALC.get(), p));
    public static final DeferredHolder<Item, Item> BLOCK_OF_EERIE_WEAVE = register("block_of_eerie_weave", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BLOCK_OF_EERIE_WEAVE.get(), p));
    public static final DeferredHolder<Item, Item> BLOCK_OF_WARP_FLUX = register("block_of_warp_flux", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BLOCK_OF_WARP_FLUX.get(), p));

    public static final DeferredHolder<Item, Item> BLOCK_OF_WIND_NUCLEI = register("block_of_wind_nuclei", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BLOCK_OF_WIND_NUCLEI.get(), p));
    public static final DeferredHolder<Item, Item> BLOCK_OF_PYRE_NUCLEI = register("block_of_pyre_nuclei", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BLOCK_OF_PYRE_NUCLEI.get(), p));

    public static final DeferredHolder<Item, Item> BLOCK_OF_HEX_ASH = register("block_of_hex_ash", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BLOCK_OF_HEX_ASH.get(), p));
    public static final DeferredHolder<Item, Item> BLOCK_OF_LIVING_FLESH = register("block_of_living_flesh", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BLOCK_OF_LIVING_FLESH.get(), p));
    public static final DeferredHolder<Item, Item> BLOCK_OF_ALCHEMICAL_CALX = register("block_of_alchemical_calx", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BLOCK_OF_ALCHEMICAL_CALX.get(), p));
    public static final DeferredHolder<Item, Item> BLOCK_OF_ARCANE_CHARCOAL = register("block_of_arcane_charcoal", MalumItems::DEFAULT_PROPERTIES, (p) -> new LodestoneFuelBlockItem(MalumBlocks.BLOCK_OF_ARCANE_CHARCOAL.get(), p, 32000));

    public static final DeferredHolder<Item, Item> BLOCK_OF_EBONY = register("block_of_ebony", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BLOCK_OF_EBONY.get(), p));
    public static final DeferredHolder<Item, Item> CRATE_OF_WITCHHAZEL = register("crate_of_witchhazel", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.CRATE_OF_WITCHHAZEL.get(), p));

    public static final DeferredHolder<Item, Item> BLOCK_OF_NULL_SLATE = register("block_of_null_slate", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BLOCK_OF_NULL_SLATE.get(), p));
    public static final DeferredHolder<Item, Item> BLOCK_OF_VOID_SALTS = register("block_of_void_salts", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BLOCK_OF_VOID_SALTS.get(), p));
    public static final DeferredHolder<Item, Item> BLOCK_OF_MNEMONIC_FRAGMENT = register("block_of_mnemonic_fragment", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BLOCK_OF_MNEMONIC_FRAGMENT.get(), p));
    public static final DeferredHolder<Item, Item> BLOCK_OF_AURIC_EMBERS = register("block_of_auric_embers", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BLOCK_OF_AURIC_EMBERS.get(), p));
    public static final DeferredHolder<Item, Item> BLOCK_OF_MALIGNANT_LEAD = register("block_of_malignant_lead", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BLOCK_OF_MALIGNANT_LEAD.get(), p));

    public static final DeferredHolder<Item, Item> ROTTING_ESSENCE = register("rotting_essence", () -> DEFAULT_PROPERTIES().food(MalumFoodProperties.ROTTING_ESSENCE), Item::new);
    public static final DeferredHolder<Item, Item> GRIM_TALC = register("grim_talc", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredHolder<Item, Item> EERIE_WEAVE = register("eerie_weave", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredHolder<Item, Item> WARP_FLUX = register("warp_flux", MalumItems::DEFAULT_PROPERTIES, Item::new);

    public static final DeferredHolder<Item, Item> WIND_NUCLEUS = register("wind_nucleus", MalumItems::DEFAULT_PROPERTIES, WindNucleusItem::new);
    public static final DeferredHolder<Item, Item> PYRE_NUCLEUS = register("pyre_nucleus", MalumItems::DEFAULT_PROPERTIES, PyreNucleusItem::new);

    public static final DeferredHolder<Item, Item> HEX_ASH = register("hex_ash", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredHolder<Item, Item> LIVING_FLESH = register("living_flesh", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredHolder<Item, Item> ALCHEMICAL_CALX = register("alchemical_calx", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredHolder<Item, Item> ARCANE_CHARCOAL = register("arcane_charcoal", MalumItems::DEFAULT_PROPERTIES, (p) -> new LodestoneFuelItem(p, 3200));

    public static final DeferredHolder<Item, Item> EBONY_STALK = register("ebony_stalk", MalumItems::DEFAULT_PROPERTIES, p -> new ItemNameBlockItem(MalumBlocks.EBONY.get(), p));
    public static final DeferredHolder<Item, Item> EBONY = register("ebony", MalumItems::DEFAULT_PROPERTIES, Item::new);

    public static final DeferredHolder<Item, Item> WILD_WITCHHAZEL = register("wild_witchhazel", MalumItems::DEFAULT_PROPERTIES, p -> new BlockItem(MalumBlocks.WILD_WITCHHAZEL.get(), p));
    public static final DeferredHolder<Item, Item> WITCHHAZEL = register("witchhazel", MalumItems::DEFAULT_PROPERTIES, p -> new ItemNameBlockItem(MalumBlocks.WITCHHAZEL.get(), p));

    public static final DeferredHolder<Item, Item> SOULWOVEN_SILK = register("soulwoven_silk", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredHolder<Item, Item> PARACAUSAL_FLAME = register("paracausal_flame", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredHolder<Item, Item> CONVOLUTED_LENS = register("convoluted_lens", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredHolder<Item, Item> MIMICRY_RELAY = register("mimicry_relay", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredHolder<Item, Item> IMITATION_FLESH = register("imitation_flesh", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredHolder<Item, Item> IMITATION_HEART = register("imitation_heart", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredHolder<Item, Item> POPPET = register("poppet", MalumItems::HIDDEN_PROPERTIES, Item::new);

    public static final DeferredHolder<Item, Item> NULL_SLATE = register("null_slate", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredHolder<Item, Item> VOID_SALTS = register("void_salts", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredHolder<Item, Item> MNEMONIC_FRAGMENT = register("mnemonic_fragment", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredHolder<Item, Item> AURIC_EMBERS = register("auric_embers", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredHolder<Item, Item> MALIGNANT_LEAD = register("malignant_lead", () -> DEFAULT_PROPERTIES().rarity(RARE), Item::new);

    public static final DeferredHolder<Item, Item> ANOMALOUS_DESIGN = register("anomalous_design", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredHolder<Item, Item> COMPLETE_DESIGN = register("complete_design", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredHolder<Item, Item> FUSED_CONSCIOUSNESS = register("fused_consciousness", MalumItems::DEFAULT_PROPERTIES, (p) -> new FusedConsciousnessItem(p.rarity(RARE)));

    public static final DeferredHolder<Item, Item> BLOCK_OF_SOUL_STAINED_STEEL = register("block_of_soul_stained_steel", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BLOCK_OF_SOUL_STAINED_STEEL.get(), p));
    public static final DeferredHolder<Item, Item> SOUL_STAINED_STEEL_INGOT = register("soul_stained_steel_ingot", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredHolder<Item, Item> SOUL_STAINED_STEEL_PLATING = register("soul_stained_steel_plating", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredHolder<Item, Item> SOUL_STAINED_STEEL_NUGGET = register("soul_stained_steel_nugget", MalumItems::DEFAULT_PROPERTIES, Item::new);

    public static final DeferredHolder<Item, Item> BLOCK_OF_HALLOWED_GOLD = register("block_of_hallowed_gold", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BLOCK_OF_HALLOWED_GOLD.get(), p));
    public static final DeferredHolder<Item, Item> HALLOWED_GOLD_INGOT = register("hallowed_gold_ingot", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredHolder<Item, Item> HALLOWED_GOLD_INLAY = register("hallowed_gold_inlay", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredHolder<Item, Item> HALLOWED_GOLD_NUGGET = register("hallowed_gold_nugget", MalumItems::DEFAULT_PROPERTIES, Item::new);

    public static final DeferredHolder<Item, Item> BLOCK_OF_MALIGNANT_PEWTER = register("block_of_malignant_pewter", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BLOCK_OF_MALIGNANT_PEWTER.get(), p));
    public static final DeferredHolder<Item, Item> MALIGNANT_PEWTER_INGOT = register("malignant_pewter_ingot", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredHolder<Item, Item> MALIGNANT_PEWTER_PLATING = register("malignant_pewter_plating", MalumItems::DEFAULT_PROPERTIES, Item::new);
    public static final DeferredHolder<Item, Item> MALIGNANT_PEWTER_NUGGET = register("malignant_pewter_nugget", MalumItems::DEFAULT_PROPERTIES, Item::new);

    //region impetus
    public static final DeferredHolder<Item, Item> IRON_IMPETUS = register("iron_impetus", MalumItems::IMPETUS_PROPERTIES, ImpetusItem::new);
    public static final DeferredHolder<Item, Item> FRACTURED_IRON_IMPETUS = register("fractured_iron_impetus", MalumItems::IMPETUS_PROPERTIES, FracturedImpetusItem::new);
    public static final DeferredHolder<Item, Item> IRON_NODE = register("iron_node", MalumItems::DEFAULT_PROPERTIES, NodeItem::new);

    public static final DeferredHolder<Item, Item> COPPER_IMPETUS = register("copper_impetus", MalumItems::IMPETUS_PROPERTIES, ImpetusItem::new);
    public static final DeferredHolder<Item, Item> FRACTURED_COPPER_IMPETUS = register("fractured_copper_impetus", MalumItems::IMPETUS_PROPERTIES, FracturedImpetusItem::new);
    public static final DeferredHolder<Item, Item> COPPER_NODE = register("copper_node", MalumItems::DEFAULT_PROPERTIES, NodeItem::new);

    public static final DeferredHolder<Item, Item> GOLD_IMPETUS = register("gold_impetus", MalumItems::IMPETUS_PROPERTIES, ImpetusItem::new);
    public static final DeferredHolder<Item, Item> FRACTURED_GOLD_IMPETUS = register("fractured_gold_impetus", MalumItems::IMPETUS_PROPERTIES, FracturedImpetusItem::new);
    public static final DeferredHolder<Item, Item> GOLD_NODE = register("gold_node", MalumItems::DEFAULT_PROPERTIES, NodeItem::new);

    public static final DeferredHolder<Item, Item> ALUMINUM_IMPETUS = register("aluminum_impetus", MalumItems::IMPETUS_PROPERTIES, ImpetusItem::new);
    public static final DeferredHolder<Item, Item> FRACTURED_ALUMINUM_IMPETUS = register("fractured_aluminum_impetus", MalumItems::IMPETUS_PROPERTIES, FracturedImpetusItem::new);
    public static final DeferredHolder<Item, Item> ALUMINUM_NODE = register("aluminum_node", MalumItems::DEFAULT_PROPERTIES, NodeItem::new);

    public static final DeferredHolder<Item, Item> NICKEL_IMPETUS = register("nickel_impetus", MalumItems::IMPETUS_PROPERTIES, ImpetusItem::new);
    public static final DeferredHolder<Item, Item> FRACTURED_NICKEL_IMPETUS = register("fractured_nickel_impetus", MalumItems::IMPETUS_PROPERTIES, FracturedImpetusItem::new);
    public static final DeferredHolder<Item, Item> NICKEL_NODE = register("nickel_node", MalumItems::DEFAULT_PROPERTIES, NodeItem::new);

    public static final DeferredHolder<Item, Item> SILVER_IMPETUS = register("silver_impetus", MalumItems::IMPETUS_PROPERTIES, ImpetusItem::new);
    public static final DeferredHolder<Item, Item> FRACTURED_SILVER_IMPETUS = register("fractured_silver_impetus", MalumItems::IMPETUS_PROPERTIES, FracturedImpetusItem::new);
    public static final DeferredHolder<Item, Item> SILVER_NODE = register("silver_node", MalumItems::DEFAULT_PROPERTIES, NodeItem::new);

    public static final DeferredHolder<Item, Item> TIN_IMPETUS = register("tin_impetus", MalumItems::IMPETUS_PROPERTIES, ImpetusItem::new);
    public static final DeferredHolder<Item, Item> FRACTURED_TIN_IMPETUS = register("fractured_tin_impetus", MalumItems::IMPETUS_PROPERTIES, FracturedImpetusItem::new);
    public static final DeferredHolder<Item, Item> TIN_NODE = register("tin_node", MalumItems::DEFAULT_PROPERTIES, NodeItem::new);

    public static final DeferredHolder<Item, Item> ZINC_IMPETUS = register("zinc_impetus", MalumItems::IMPETUS_PROPERTIES, ImpetusItem::new);
    public static final DeferredHolder<Item, Item> FRACTURED_ZINC_IMPETUS = register("fractured_zinc_impetus", MalumItems::IMPETUS_PROPERTIES, FracturedImpetusItem::new);
    public static final DeferredHolder<Item, Item> ZINC_NODE = register("zinc_node", MalumItems::DEFAULT_PROPERTIES, NodeItem::new);

    public static final DeferredHolder<Item, Item> OSMIUM_IMPETUS = register("osmium_impetus", MalumItems::IMPETUS_PROPERTIES, ImpetusItem::new);
    public static final DeferredHolder<Item, Item> FRACTURED_OSMIUM_IMPETUS = register("fractured_osmium_impetus", MalumItems::IMPETUS_PROPERTIES, FracturedImpetusItem::new);
    public static final DeferredHolder<Item, Item> OSMIUM_NODE = register("osmium_node", MalumItems::DEFAULT_PROPERTIES, NodeItem::new);

    public static final DeferredHolder<Item, Item> LEAD_IMPETUS = register("lead_impetus", MalumItems::IMPETUS_PROPERTIES, ImpetusItem::new);
    public static final DeferredHolder<Item, Item> FRACTURED_LEAD_IMPETUS = register("fractured_lead_impetus", MalumItems::IMPETUS_PROPERTIES, FracturedImpetusItem::new);
    public static final DeferredHolder<Item, Item> LEAD_NODE = register("lead_node", MalumItems::DEFAULT_PROPERTIES, NodeItem::new);

    public static final DeferredHolder<Item, Item> URANIUM_IMPETUS = register("uranium_impetus", MalumItems::IMPETUS_PROPERTIES, ImpetusItem::new);
    public static final DeferredHolder<Item, Item> FRACTURED_URANIUM_IMPETUS = register("fractured_uranium_impetus", MalumItems::IMPETUS_PROPERTIES, FracturedImpetusItem::new);
    public static final DeferredHolder<Item, Item> URANIUM_NODE = register("uranium_node", MalumItems::DEFAULT_PROPERTIES, NodeItem::new);

    public static final DeferredHolder<Item, Item> COBALT_IMPETUS = register("cobalt_impetus", MalumItems::IMPETUS_PROPERTIES, ImpetusItem::new);
    public static final DeferredHolder<Item, Item> FRACTURED_COBALT_IMPETUS = register("fractured_cobalt_impetus", MalumItems::IMPETUS_PROPERTIES, FracturedImpetusItem::new);
    public static final DeferredHolder<Item, Item> COBALT_NODE = register("cobalt_node", MalumItems::DEFAULT_PROPERTIES, NodeItem::new);

    public static final DeferredHolder<Item, Item> ZEPHYR_IMPETUS = register("zephyr_impetus", MalumItems::IMPETUS_PROPERTIES, ImpetusItem::new);
    public static final DeferredHolder<Item, Item> FRACTURED_ZEPHYR_IMPETUS = register("fractured_zephyr_impetus", MalumItems::IMPETUS_PROPERTIES, FracturedImpetusItem::new);
    public static final DeferredHolder<Item, Item> IFRIT_IMPETUS = register("ifrit_impetus", MalumItems::IMPETUS_PROPERTIES, ImpetusItem::new);
    public static final DeferredHolder<Item, Item> FRACTURED_IFRIT_IMPETUS = register("fractured_ifrit_impetus", MalumItems::IMPETUS_PROPERTIES, FracturedImpetusItem::new);
    public static final DeferredHolder<Item, Item> ALCHEMICAL_IMPETUS = register("alchemical_impetus", MalumItems::IMPETUS_PROPERTIES, ImpetusItem::new);
    public static final DeferredHolder<Item, Item> FRACTURED_ALCHEMICAL_IMPETUS = register("fractured_alchemical_impetus", MalumItems::IMPETUS_PROPERTIES, FracturedImpetusItem::new);
    //endregion

    //region spirited glass
    public static final DeferredHolder<Item, Item> SACRED_SPIRITED_GLASS = register("sacred_spirited_glass", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.SACRED_SPIRITED_GLASS.get(), p));
    public static final DeferredHolder<Item, Item> WICKED_SPIRITED_GLASS = register("wicked_spirited_glass", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.WICKED_SPIRITED_GLASS.get(), p));
    public static final DeferredHolder<Item, Item> ARCANE_SPIRITED_GLASS = register("arcane_spirited_glass", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.ARCANE_SPIRITED_GLASS.get(), p));
    public static final DeferredHolder<Item, Item> ELDRITCH_SPIRITED_GLASS = register("eldritch_spirited_glass", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.ELDRITCH_SPIRITED_GLASS.get(), p));
    public static final DeferredHolder<Item, Item> AERIAL_SPIRITED_GLASS = register("aerial_spirited_glass", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.AERIAL_SPIRITED_GLASS.get(), p));
    public static final DeferredHolder<Item, Item> AQUEOUS_SPIRITED_GLASS = register("aqueous_spirited_glass", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.AQUEOUS_SPIRITED_GLASS.get(), p));
    public static final DeferredHolder<Item, Item> EARTHEN_SPIRITED_GLASS = register("earthen_spirited_glass", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.EARTHEN_SPIRITED_GLASS.get(), p));
    public static final DeferredHolder<Item, Item> INFERNAL_SPIRITED_GLASS = register("infernal_spirited_glass", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.INFERNAL_SPIRITED_GLASS.get(), p));
    public static final DeferredHolder<Item, Item> NULL_SPIRITED_GLASS = register("null_spirited_glass", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.NULL_SPIRITED_GLASS.get(), p));
    //endregion

    //region Varnished Terracotta
    public static final DeferredHolder<Item, Item> SACRED_VARNISHED_TERRACOTTA = register("sacred_varnished_terracotta", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.SACRED_VARNISHED_TERRACOTTA.get(), p));
    public static final DeferredHolder<Item, Item> WICKED_VARNISHED_TERRACOTTA = register("wicked_varnished_terracotta", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.WICKED_VARNISHED_TERRACOTTA.get(), p));
    public static final DeferredHolder<Item, Item> ARCANE_VARNISHED_TERRACOTTA = register("arcane_varnished_terracotta", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.ARCANE_VARNISHED_TERRACOTTA.get(), p));
    public static final DeferredHolder<Item, Item> ELDRITCH_VARNISHED_TERRACOTTA = register("eldritch_varnished_terracotta", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.ELDRITCH_VARNISHED_TERRACOTTA.get(), p));
    public static final DeferredHolder<Item, Item> AERIAL_VARNISHED_TERRACOTTA = register("aerial_varnished_terracotta", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.AERIAL_VARNISHED_TERRACOTTA.get(), p));
    public static final DeferredHolder<Item, Item> AQUEOUS_VARNISHED_TERRACOTTA = register("aqueous_varnished_terracotta", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.AQUEOUS_VARNISHED_TERRACOTTA.get(), p));
    public static final DeferredHolder<Item, Item> EARTHEN_VARNISHED_TERRACOTTA = register("earthen_varnished_terracotta", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.EARTHEN_VARNISHED_TERRACOTTA.get(), p));
    public static final DeferredHolder<Item, Item> INFERNAL_VARNISHED_TERRACOTTA = register("infernal_varnished_terracotta", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.INFERNAL_VARNISHED_TERRACOTTA.get(), p));
    public static final DeferredHolder<Item, Item> NULL_VARNISHED_TERRACOTTA = register("null_varnished_terracotta", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.NULL_VARNISHED_TERRACOTTA.get(), p));
    //endregion

    public static final DeferredHolder<Item, Item> SOULWOVEN_BANNER = register("soulwoven_banner", MalumItems::DEFAULT_PROPERTIES, SoulwovenBannerBlockItem::new);

    //region tainted rock
    public static final DeferredHolder<Item, Item> TAINTED_ROCK = register("tainted_rock", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TAINTED_ROCK.get(), p));
    public static final DeferredHolder<Item, Item> POLISHED_TAINTED_ROCK = register("polished_tainted_rock", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.POLISHED_TAINTED_ROCK.get(), p));
    public static final DeferredHolder<Item, Item> TAINTED_ROCK_BRICKS = register("tainted_rock_bricks", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TAINTED_ROCK_BRICKS.get(), p));
    public static final DeferredHolder<Item, Item> TAINTED_ROCK_TILES = register("tainted_rock_tiles", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TAINTED_ROCK_TILES.get(), p));
    public static final DeferredHolder<Item, Item> TAINTED_ROCK_MOSAIC = register("tainted_rock_mosaic", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TAINTED_ROCK_MOSAIC.get(), p));

    public static final DeferredHolder<Item, Item> TAINTED_ROCK_COLUMN = register("tainted_rock_column", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TAINTED_ROCK_COLUMN.get(), p));
    public static final DeferredHolder<Item, Item> TAINTED_ROCK_ALTAR = register("tainted_rock_altar", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TAINTED_ROCK_ALTAR.get(), p));
    public static final DeferredHolder<Item, Item> CUT_TAINTED_ROCK = register("cut_tainted_rock", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.CUT_TAINTED_ROCK.get(), p));
    public static final DeferredHolder<Item, Item> CHISELED_TAINTED_ROCK = register("chiseled_tainted_rock", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.CHISELED_TAINTED_ROCK.get(), p));

    public static final DeferredHolder<Item, Item> TAINTED_ROCK_STAIRS = register("tainted_rock_stairs", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TAINTED_ROCK_STAIRS.get(), p));
    public static final DeferredHolder<Item, Item> POLISHED_TAINTED_ROCK_STAIRS = register("polished_tainted_rock_stairs", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.POLISHED_TAINTED_ROCK_STAIRS.get(), p));
    public static final DeferredHolder<Item, Item> TAINTED_ROCK_BRICKS_STAIRS = register("tainted_rock_bricks_stairs", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TAINTED_ROCK_BRICKS_STAIRS.get(), p));
    public static final DeferredHolder<Item, Item> TAINTED_ROCK_TILES_STAIRS = register("tainted_rock_tiles_stairs", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TAINTED_ROCK_TILES_STAIRS.get(), p));
    public static final DeferredHolder<Item, Item> TAINTED_ROCK_MOSAIC_STAIRS = register("tainted_rock_mosaic_stairs", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TAINTED_ROCK_MOSAIC_STAIRS.get(), p));

    public static final DeferredHolder<Item, Item> TAINTED_ROCK_SLAB = register("tainted_rock_slab", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TAINTED_ROCK_SLAB.get(), p));
    public static final DeferredHolder<Item, Item> POLISHED_TAINTED_ROCK_SLAB = register("polished_tainted_rock_slab", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.POLISHED_TAINTED_ROCK_SLAB.get(), p));
    public static final DeferredHolder<Item, Item> TAINTED_ROCK_BRICKS_SLAB = register("tainted_rock_bricks_slab", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TAINTED_ROCK_BRICKS_SLAB.get(), p));
    public static final DeferredHolder<Item, Item> TAINTED_ROCK_TILES_SLAB = register("tainted_rock_tiles_slab", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TAINTED_ROCK_TILES_SLAB.get(), p));
    public static final DeferredHolder<Item, Item> TAINTED_ROCK_MOSAIC_SLAB = register("tainted_rock_mosaic_slab", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TAINTED_ROCK_MOSAIC_SLAB.get(), p));

    public static final DeferredHolder<Item, Item> TAINTED_ROCK_WALL = register("tainted_rock_wall", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TAINTED_ROCK_WALL.get(), p));
    public static final DeferredHolder<Item, Item> POLISHED_TAINTED_ROCK_WALL = register("polished_tainted_rock_wall", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.POLISHED_TAINTED_ROCK_WALL.get(), p));
    public static final DeferredHolder<Item, Item> TAINTED_ROCK_BRICKS_WALL = register("tainted_rock_bricks_wall", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TAINTED_ROCK_BRICKS_WALL.get(), p));
    public static final DeferredHolder<Item, Item> TAINTED_ROCK_TILES_WALL = register("tainted_rock_tiles_wall", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TAINTED_ROCK_TILES_WALL.get(), p));
    public static final DeferredHolder<Item, Item> TAINTED_ROCK_MOSAIC_WALL = register("tainted_rock_mosaic_wall", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TAINTED_ROCK_MOSAIC_WALL.get(), p));

    public static final DeferredHolder<Item, Item> TAINTED_ROCK_PRESSURE_PLATE = register("tainted_rock_pressure_plate", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TAINTED_ROCK_PRESSURE_PLATE.get(), p));
    public static final DeferredHolder<Item, Item> TAINTED_ROCK_BUTTON = register("tainted_rock_button", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TAINTED_ROCK_BUTTON.get(), p));

    public static final DeferredHolder<Item, Item> TAINTED_ROCK_ITEM_STAND = register("tainted_rock_item_stand", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TAINTED_ROCK_ITEM_STAND.get(), p));
    public static final DeferredHolder<Item, Item> TAINTED_ROCK_ITEM_PEDESTAL = register("tainted_rock_item_pedestal", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TAINTED_ROCK_ITEM_PEDESTAL.get(), p));
    //endregion

    //region twisted rock
    public static final DeferredHolder<Item, Item> TWISTED_ROCK = register("twisted_rock", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TWISTED_ROCK.get(), p));
    public static final DeferredHolder<Item, Item> POLISHED_TWISTED_ROCK = register("polished_twisted_rock", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.POLISHED_TWISTED_ROCK.get(), p));
    public static final DeferredHolder<Item, Item> TWISTED_ROCK_BRICKS = register("twisted_rock_bricks", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TWISTED_ROCK_BRICKS.get(), p));
    public static final DeferredHolder<Item, Item> TWISTED_ROCK_TILES = register("twisted_rock_tiles", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TWISTED_ROCK_TILES.get(), p));
    public static final DeferredHolder<Item, Item> TWISTED_ROCK_MOSAIC = register("twisted_rock_mosaic", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TWISTED_ROCK_MOSAIC.get(), p));

    public static final DeferredHolder<Item, Item> TWISTED_ROCK_COLUMN = register("twisted_rock_column", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TWISTED_ROCK_COLUMN.get(), p));
    public static final DeferredHolder<Item, Item> TWISTED_ROCK_ALTAR = register("twisted_rock_altar", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TWISTED_ROCK_ALTAR.get(), p));
    public static final DeferredHolder<Item, Item> CUT_TWISTED_ROCK = register("cut_twisted_rock", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.CUT_TWISTED_ROCK.get(), p));
    public static final DeferredHolder<Item, Item> CHISELED_TWISTED_ROCK = register("chiseled_twisted_rock", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.CHISELED_TWISTED_ROCK.get(), p));

    public static final DeferredHolder<Item, Item> TWISTED_ROCK_STAIRS = register("twisted_rock_stairs", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TWISTED_ROCK_STAIRS.get(), p));
    public static final DeferredHolder<Item, Item> POLISHED_TWISTED_ROCK_STAIRS = register("polished_twisted_rock_stairs", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.POLISHED_TWISTED_ROCK_STAIRS.get(), p));
    public static final DeferredHolder<Item, Item> TWISTED_ROCK_BRICKS_STAIRS = register("twisted_rock_bricks_stairs", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TWISTED_ROCK_BRICKS_STAIRS.get(), p));
    public static final DeferredHolder<Item, Item> TWISTED_ROCK_TILES_STAIRS = register("twisted_rock_tiles_stairs", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TWISTED_ROCK_TILES_STAIRS.get(), p));
    public static final DeferredHolder<Item, Item> TWISTED_ROCK_MOSAIC_STAIRS = register("twisted_rock_mosaic_stairs", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TWISTED_ROCK_MOSAIC_STAIRS.get(), p));

    public static final DeferredHolder<Item, Item> TWISTED_ROCK_SLAB = register("twisted_rock_slab", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TWISTED_ROCK_SLAB.get(), p));
    public static final DeferredHolder<Item, Item> POLISHED_TWISTED_ROCK_SLAB = register("polished_twisted_rock_slab", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.POLISHED_TWISTED_ROCK_SLAB.get(), p));
    public static final DeferredHolder<Item, Item> TWISTED_ROCK_BRICKS_SLAB = register("twisted_rock_bricks_slab", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TWISTED_ROCK_BRICKS_SLAB.get(), p));
    public static final DeferredHolder<Item, Item> TWISTED_ROCK_TILES_SLAB = register("twisted_rock_tiles_slab", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TWISTED_ROCK_TILES_SLAB.get(), p));
    public static final DeferredHolder<Item, Item> TWISTED_ROCK_MOSAIC_SLAB = register("twisted_rock_mosaic_slab", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TWISTED_ROCK_MOSAIC_SLAB.get(), p));

    public static final DeferredHolder<Item, Item> TWISTED_ROCK_WALL = register("twisted_rock_wall", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TWISTED_ROCK_WALL.get(), p));
    public static final DeferredHolder<Item, Item> POLISHED_TWISTED_ROCK_WALL = register("polished_twisted_rock_wall", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.POLISHED_TWISTED_ROCK_WALL.get(), p));
    public static final DeferredHolder<Item, Item> TWISTED_ROCK_BRICKS_WALL = register("twisted_rock_bricks_wall", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TWISTED_ROCK_BRICKS_WALL.get(), p));
    public static final DeferredHolder<Item, Item> TWISTED_ROCK_TILES_WALL = register("twisted_rock_tiles_wall", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TWISTED_ROCK_TILES_WALL.get(), p));
    public static final DeferredHolder<Item, Item> TWISTED_ROCK_MOSAIC_WALL = register("twisted_rock_mosaic_wall", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TWISTED_ROCK_MOSAIC_WALL.get(), p));

    public static final DeferredHolder<Item, Item> TWISTED_ROCK_PRESSURE_PLATE = register("twisted_rock_pressure_plate", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TWISTED_ROCK_PRESSURE_PLATE.get(), p));
    public static final DeferredHolder<Item, Item> TWISTED_ROCK_BUTTON = register("twisted_rock_button", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TWISTED_ROCK_BUTTON.get(), p));

    public static final DeferredHolder<Item, Item> TWISTED_ROCK_ITEM_STAND = register("twisted_rock_item_stand", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TWISTED_ROCK_ITEM_STAND.get(), p));
    public static final DeferredHolder<Item, Item> TWISTED_ROCK_ITEM_PEDESTAL = register("twisted_rock_item_pedestal", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.TWISTED_ROCK_ITEM_PEDESTAL.get(), p));
    //endregion twisted rock

    //region dross stone
    public static final DeferredHolder<Item, Item> DROSS_STONE = register("dross_stone", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.DROSS_STONE.get(), p));
    public static final DeferredHolder<Item, Item> POLISHED_DROSS_STONE = register("polished_dross_stone", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.POLISHED_DROSS_STONE.get(), p));
    public static final DeferredHolder<Item, Item> DROSS_STONE_BRICKS = register("dross_stone_bricks", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.DROSS_STONE_BRICKS.get(), p));
    public static final DeferredHolder<Item, Item> DROSS_STONE_TILES = register("dross_stone_tiles", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.DROSS_STONE_TILES.get(), p));
    public static final DeferredHolder<Item, Item> DROSS_STONE_MOSAIC = register("dross_stone_mosaic", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.DROSS_STONE_MOSAIC.get(), p));

    public static final DeferredHolder<Item, Item> GRAY_DROSS_TILES = register("gray_dross_tiles", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.GRAY_DROSS_TILES.get(), p));
    public static final DeferredHolder<Item, Item> DARK_DROSS_TILES = register("dark_dross_tiles", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.DARK_DROSS_TILES.get(), p));

    public static final DeferredHolder<Item, Item> DROSS_STONE_COLUMN = register("dross_stone_column", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.DROSS_STONE_COLUMN.get(), p));
    public static final DeferredHolder<Item, Item> DROSS_STONE_ALTAR = register("dross_stone_altar", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.DROSS_STONE_ALTAR.get(), p));
    public static final DeferredHolder<Item, Item> CUT_DROSS_STONE = register("cut_dross_stone", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.CUT_DROSS_STONE.get(), p));
    public static final DeferredHolder<Item, Item> CHISELED_DROSS_STONE = register("chiseled_dross_stone", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.CHISELED_DROSS_STONE.get(), p));

    public static final DeferredHolder<Item, Item> DROSS_STONE_STAIRS = register("dross_stone_stairs", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.DROSS_STONE_STAIRS.get(), p));
    public static final DeferredHolder<Item, Item> POLISHED_DROSS_STONE_STAIRS = register("polished_dross_stone_stairs", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.POLISHED_DROSS_STONE_STAIRS.get(), p));
    public static final DeferredHolder<Item, Item> DROSS_STONE_BRICKS_STAIRS = register("dross_stone_bricks_stairs", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.DROSS_STONE_BRICKS_STAIRS.get(), p));
    public static final DeferredHolder<Item, Item> DROSS_STONE_TILES_STAIRS = register("dross_stone_tiles_stairs", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.DROSS_STONE_TILES_STAIRS.get(), p));
    public static final DeferredHolder<Item, Item> DROSS_STONE_MOSAIC_STAIRS = register("dross_stone_mosaic_stairs", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.DROSS_STONE_MOSAIC_STAIRS.get(), p));

    public static final DeferredHolder<Item, Item> GRAY_DROSS_TILES_STAIRS = register("gray_dross_tiles_stairs", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.GRAY_DROSS_TILES_STAIRS.get(), p));
    public static final DeferredHolder<Item, Item> DARK_DROSS_TILES_STAIRS = register("dark_dross_tiles_stairs", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.DARK_DROSS_TILES_STAIRS.get(), p));

    public static final DeferredHolder<Item, Item> DROSS_STONE_SLAB = register("dross_stone_slab", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.DROSS_STONE_SLAB.get(), p));
    public static final DeferredHolder<Item, Item> POLISHED_DROSS_STONE_SLAB = register("polished_dross_stone_slab", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.POLISHED_DROSS_STONE_SLAB.get(), p));
    public static final DeferredHolder<Item, Item> DROSS_STONE_BRICKS_SLAB = register("dross_stone_bricks_slab", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.DROSS_STONE_BRICKS_SLAB.get(), p));
    public static final DeferredHolder<Item, Item> DROSS_STONE_TILES_SLAB = register("dross_stone_tiles_slab", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.DROSS_STONE_TILES_SLAB.get(), p));
    public static final DeferredHolder<Item, Item> DROSS_STONE_MOSAIC_SLAB = register("dross_stone_mosaic_slab", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.DROSS_STONE_MOSAIC_SLAB.get(), p));

    public static final DeferredHolder<Item, Item> GRAY_DROSS_TILES_SLAB = register("gray_dross_tiles_slab", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.GRAY_DROSS_TILES_SLAB.get(), p));
    public static final DeferredHolder<Item, Item> DARK_DROSS_TILES_SLAB = register("dark_dross_tiles_slab", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.DARK_DROSS_TILES_SLAB.get(), p));

    public static final DeferredHolder<Item, Item> DROSS_STONE_WALL = register("dross_stone_wall", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.DROSS_STONE_WALL.get(), p));
    public static final DeferredHolder<Item, Item> POLISHED_DROSS_STONE_WALL = register("polished_dross_stone_wall", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.POLISHED_DROSS_STONE_WALL.get(), p));
    public static final DeferredHolder<Item, Item> DROSS_STONE_BRICKS_WALL = register("dross_stone_bricks_wall", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.DROSS_STONE_BRICKS_WALL.get(), p));
    public static final DeferredHolder<Item, Item> DROSS_STONE_TILES_WALL = register("dross_stone_tiles_wall", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.DROSS_STONE_TILES_WALL.get(), p));
    public static final DeferredHolder<Item, Item> DROSS_STONE_MOSAIC_WALL = register("dross_stone_mosaic_wall", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.DROSS_STONE_MOSAIC_WALL.get(), p));

    public static final DeferredHolder<Item, Item> GRAY_DROSS_TILES_WALL = register("gray_dross_tiles_wall", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.GRAY_DROSS_TILES_WALL.get(), p));
    public static final DeferredHolder<Item, Item> DARK_DROSS_TILES_WALL = register("dark_dross_tiles_wall", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.DARK_DROSS_TILES_WALL.get(), p));

    public static final DeferredHolder<Item, Item> DROSS_STONE_PRESSURE_PLATE = register("dross_stone_pressure_plate", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.DROSS_STONE_PRESSURE_PLATE.get(), p));
    public static final DeferredHolder<Item, Item> DROSS_STONE_BUTTON = register("dross_stone_button", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.DROSS_STONE_BUTTON.get(), p));

    public static final DeferredHolder<Item, Item> DROSS_STONE_ITEM_STAND = register("dross_stone_item_stand", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.DROSS_STONE_ITEM_STAND.get(), p));
    public static final DeferredHolder<Item, Item> DROSS_STONE_ITEM_PEDESTAL = register("dross_stone_item_pedestal", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.DROSS_STONE_ITEM_PEDESTAL.get(), p));
    //endregion dross stone

    //region runewood
    public static final DeferredHolder<Item, Item> RUNEWOOD_SAPLING = register("runewood_sapling", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUNEWOOD_SAPLING.get(), p));
    public static final DeferredHolder<Item, Item> AZURE_RUNEWOOD_SAPLING = register("azure_runewood_sapling", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.AZURE_RUNEWOOD_SAPLING.get(), p));
    public static final DeferredHolder<Item, Item> RUNIC_SAP = register("runic_sap", MalumItems::DEFAULT_PROPERTIES, (p) -> new BottledDrinkItem(DEFAULT_PROPERTIES().food(MalumFoodProperties.RUNIC_SAP)));
    public static final DeferredHolder<Item, Item> RUNIC_SAPBALL = register("runic_sapball", MalumItems::DEFAULT_PROPERTIES, Item::new);

    public static final DeferredHolder<Item, Item> RUNEWOOD_LEAVES = register("runewood_leaves", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUNEWOOD_LEAVES.get(), p));
    public static final DeferredHolder<Item, Item> AZURE_RUNEWOOD_LEAVES = register("azure_runewood_leaves", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.AZURE_RUNEWOOD_LEAVES.get(), p));

    public static final DeferredHolder<Item, Item> HANGING_RUNEWOOD_LEAVES = register("hanging_runewood_leaves", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.HANGING_RUNEWOOD_LEAVES.get(), p));
    public static final DeferredHolder<Item, Item> HANGING_AZURE_RUNEWOOD_LEAVES = register("hanging_azure_runewood_leaves", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.HANGING_AZURE_RUNEWOOD_LEAVES.get(), p));

    public static final DeferredHolder<Item, Item> RUNEWOOD_LOG = register("runewood_log", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUNEWOOD_LOG.get(), p));
    public static final DeferredHolder<Item, Item> STRIPPED_RUNEWOOD_LOG = register("stripped_runewood_log", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.STRIPPED_RUNEWOOD_LOG.get(), p));

    public static final DeferredHolder<Item, Item> RUNEWOOD = register("runewood", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUNEWOOD.get(), p));
    public static final DeferredHolder<Item, Item> STRIPPED_RUNEWOOD = register("stripped_runewood", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.STRIPPED_RUNEWOOD.get(), p));

    public static final DeferredHolder<Item, Item> SAPPY_RUNEWOOD_LOG = register("sappy_runewood_log", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.SAPPY_RUNEWOOD_LOG.get(), p));
    public static final DeferredHolder<Item, Item> STRIPPED_SAPPY_RUNEWOOD_LOG = register("stripped_sappy_runewood_log", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.STRIPPED_SAPPY_RUNEWOOD_LOG.get(), p));

    public static final DeferredHolder<Item, Item> RUNEWOOD_BOARDS = register("runewood_boards", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUNEWOOD_BOARDS.get(), p));
    public static final DeferredHolder<Item, Item> RUNEWOOD_BOARDS_STAIRS = register("runewood_boards_stairs", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUNEWOOD_BOARDS_STAIRS.get(), p));
    public static final DeferredHolder<Item, Item> RUNEWOOD_BOARDS_SLAB = register("runewood_boards_slab", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUNEWOOD_BOARDS_SLAB.get(), p));

    public static final DeferredHolder<Item, Item> VERTICAL_RUNEWOOD_BOARDS = register("vertical_runewood_boards", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.VERTICAL_RUNEWOOD_BOARDS.get(), p));
    public static final DeferredHolder<Item, Item> VERTICAL_RUNEWOOD_BOARDS_STAIRS = register("vertical_runewood_boards_stairs", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.VERTICAL_RUNEWOOD_BOARDS_STAIRS.get(), p));
    public static final DeferredHolder<Item, Item> VERTICAL_RUNEWOOD_BOARDS_SLAB = register("vertical_runewood_boards_slab", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.VERTICAL_RUNEWOOD_BOARDS_SLAB.get(), p));

    public static final DeferredHolder<Item, Item> RUNEWOOD_PLANKS = register("runewood_planks", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUNEWOOD_PLANKS.get(), p));
    public static final DeferredHolder<Item, Item> RUNEWOOD_PLANKS_STAIRS = register("runewood_planks_stairs", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUNEWOOD_PLANKS_STAIRS.get(), p));
    public static final DeferredHolder<Item, Item> RUNEWOOD_PLANKS_SLAB = register("runewood_planks_slab", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUNEWOOD_PLANKS_SLAB.get(), p));

    public static final DeferredHolder<Item, Item> VERTICAL_RUNEWOOD_PLANKS = register("vertical_runewood_planks", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.VERTICAL_RUNEWOOD_PLANKS.get(), p));
    public static final DeferredHolder<Item, Item> VERTICAL_RUNEWOOD_PLANKS_STAIRS = register("vertical_runewood_planks_stairs", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.VERTICAL_RUNEWOOD_PLANKS_STAIRS.get(), p));
    public static final DeferredHolder<Item, Item> VERTICAL_RUNEWOOD_PLANKS_SLAB = register("vertical_runewood_planks_slab", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.VERTICAL_RUNEWOOD_PLANKS_SLAB.get(), p));

    public static final DeferredHolder<Item, Item> RUNEWOOD_TILES = register("runewood_tiles", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUNEWOOD_TILES.get(), p));
    public static final DeferredHolder<Item, Item> RUNEWOOD_TILES_STAIRS = register("runewood_tiles_stairs", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUNEWOOD_TILES_STAIRS.get(), p));
    public static final DeferredHolder<Item, Item> RUNEWOOD_TILES_SLAB = register("runewood_tiles_slab", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUNEWOOD_TILES_SLAB.get(), p));

    public static final DeferredHolder<Item, Item> RUSTIC_RUNEWOOD_PLANKS = register("rustic_runewood_planks", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUSTIC_RUNEWOOD_PLANKS.get(), p));
    public static final DeferredHolder<Item, Item> RUSTIC_RUNEWOOD_PLANKS_STAIRS = register("rustic_runewood_planks_stairs", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUSTIC_RUNEWOOD_PLANKS_STAIRS.get(), p));
    public static final DeferredHolder<Item, Item> RUSTIC_RUNEWOOD_PLANKS_SLAB = register("rustic_runewood_planks_slab", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUSTIC_RUNEWOOD_PLANKS_SLAB.get(), p));

    public static final DeferredHolder<Item, Item> VERTICAL_RUSTIC_RUNEWOOD_PLANKS = register("vertical_rustic_runewood_planks", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.VERTICAL_RUSTIC_RUNEWOOD_PLANKS.get(), p));
    public static final DeferredHolder<Item, Item> VERTICAL_RUSTIC_RUNEWOOD_PLANKS_STAIRS = register("vertical_rustic_runewood_planks_stairs", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.VERTICAL_RUSTIC_RUNEWOOD_PLANKS_STAIRS.get(), p));
    public static final DeferredHolder<Item, Item> VERTICAL_RUSTIC_RUNEWOOD_PLANKS_SLAB = register("vertical_rustic_runewood_planks_slab", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.VERTICAL_RUSTIC_RUNEWOOD_PLANKS_SLAB.get(), p));

    public static final DeferredHolder<Item, Item> RUSTIC_RUNEWOOD_TILES = register("rustic_runewood_tiles", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUSTIC_RUNEWOOD_TILES.get(), p));
    public static final DeferredHolder<Item, Item> RUSTIC_RUNEWOOD_TILES_STAIRS = register("rustic_runewood_tiles_stairs", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUSTIC_RUNEWOOD_TILES_STAIRS.get(), p));
    public static final DeferredHolder<Item, Item> RUSTIC_RUNEWOOD_TILES_SLAB = register("rustic_runewood_tiles_slab", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUSTIC_RUNEWOOD_TILES_SLAB.get(), p));

    public static final DeferredHolder<Item, Item> RUNEWOOD_PANEL = register("runewood_panel", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUNEWOOD_PANEL.get(), p));
    public static final DeferredHolder<Item, Item> CUT_RUNEWOOD_PLANKS = register("cut_runewood_planks", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.CUT_RUNEWOOD_PLANKS.get(), p));
    public static final DeferredHolder<Item, Item> RUNEWOOD_BEAM = register("runewood_beam", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUNEWOOD_BEAM.get(), p));

    public static final DeferredHolder<Item, Item> RUNEWOOD_DOOR = register("runewood_door", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUNEWOOD_DOOR.get(), p));
    public static final DeferredHolder<Item, Item> BOLTED_RUNEWOOD_DOOR = register("bolted_runewood_door", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BOLTED_RUNEWOOD_DOOR.get(), p));

    public static final DeferredHolder<Item, Item> RUNEWOOD_TRAPDOOR = register("runewood_trapdoor", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUNEWOOD_TRAPDOOR.get(), p));
    public static final DeferredHolder<Item, Item> BOLTED_RUNEWOOD_TRAPDOOR = register("bolted_runewood_trapdoor", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BOLTED_RUNEWOOD_TRAPDOOR.get(), p));

    public static final DeferredHolder<Item, Item> RUNEWOOD_BOARDS_DOOR = register("runewood_boards_door", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUNEWOOD_BOARDS_DOOR.get(), p));
    public static final DeferredHolder<Item, Item> BOLTED_RUNEWOOD_BOARDS_DOOR = register("bolted_runewood_boards_door", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BOLTED_RUNEWOOD_BOARDS_DOOR.get(), p));

    public static final DeferredHolder<Item, Item> RUNEWOOD_BOARDS_TRAPDOOR = register("runewood_boards_trapdoor", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUNEWOOD_BOARDS_TRAPDOOR.get(), p));
    public static final DeferredHolder<Item, Item> BOLTED_RUNEWOOD_BOARDS_TRAPDOOR = register("bolted_runewood_boards_trapdoor", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BOLTED_RUNEWOOD_BOARDS_TRAPDOOR.get(), p));

    public static final DeferredHolder<Item, Item> RUNEWOOD_BUTTON = register("runewood_planks_button", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUNEWOOD_BUTTON.get(), p));
    public static final DeferredHolder<Item, Item> RUNEWOOD_PRESSURE_PLATE = register("runewood_planks_pressure_plate", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUNEWOOD_PRESSURE_PLATE.get(), p));

    public static final DeferredHolder<Item, Item> RUNEWOOD_BOARDS_WALL = register("runewood_boards_wall", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUNEWOOD_BOARDS_WALL.get(), p));
    public static final DeferredHolder<Item, Item> RUNEWOOD_FENCE = register("runewood_planks_fence", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUNEWOOD_FENCE.get(), p));
    public static final DeferredHolder<Item, Item> RUNEWOOD_FENCE_GATE = register("runewood_planks_fence_gate", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUNEWOOD_FENCE_GATE.get(), p));

    public static final DeferredHolder<Item, Item> RUNEWOOD_ITEM_PEDESTAL = register("runewood_item_pedestal", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUNEWOOD_ITEM_PEDESTAL.get(), p));
    public static final DeferredHolder<Item, Item> GILDED_RUNEWOOD_ITEM_PEDESTAL = register("gilded_runewood_item_pedestal", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.GILDED_RUNEWOOD_ITEM_PEDESTAL.get(), p));
    public static final DeferredHolder<Item, Item> RUNEWOOD_ITEM_STAND = register("runewood_item_stand", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUNEWOOD_ITEM_STAND.get(), p));
    public static final DeferredHolder<Item, Item> GILDED_RUNEWOOD_ITEM_STAND = register("gilded_runewood_item_stand", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.GILDED_RUNEWOOD_ITEM_STAND.get(), p));

    public static final DeferredHolder<Item, Item> RUNEWOOD_SIGN = register("runewood_sign", MalumItems::DEFAULT_PROPERTIES, (p) -> new SignItem(DEFAULT_PROPERTIES().stacksTo(16), MalumBlocks.RUNEWOOD_SIGN.get(), MalumBlocks.RUNEWOOD_WALL_SIGN.get()));
    public static final DeferredHolder<Item, Item> RUNEWOOD_BOAT = register("runewood_boat", () -> DEFAULT_PROPERTIES().stacksTo(1), (p) -> new BoatItem(false, MalumEnumParams.RUNEWOOD_BOAT_TYPE.getValue(), p));
    public static final DeferredHolder<Item, Item> RUNEWOOD_CHEST_BOAT = register("runewood_chest_boat", () -> DEFAULT_PROPERTIES().stacksTo(1), (p) -> new BoatItem(true, MalumEnumParams.RUNEWOOD_BOAT_TYPE.getValue(), p));
    //endregion

    //region blight
    public static final DeferredHolder<Item, Item> COLUMNAR_BLIGHT = register("columnar_blight", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.COLUMNAR_BLIGHT.get(), p));
    public static final DeferredHolder<Item, Item> BLIGHTED_EARTH = register("blighted_earth", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BLIGHTED_EARTH.get(), p));
    public static final DeferredHolder<Item, Item> BLIGHT = register("blight", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BLIGHT.get(), p));
    public static final DeferredHolder<Item, Item> BLIGHTED_GUNK = register("blighted_gunk", MalumItems::DEFAULT_PROPERTIES, BlightedGunkItem::new);
    public static final DeferredHolder<Item, Item> BLIGHTPEARL = register("blightpearl", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BLIGHTPEARL.get(), p));
    public static final DeferredHolder<Item, Item> BLIGHTROOT = register("blightroot", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BLIGHTROOT.get(), p));
    //endregion

    //region scarstone
    public static final DeferredHolder<Item, Item> SCARSTONE = register("scarstone", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.SCARSTONE.get(), p));
    public static final DeferredHolder<Item, Item> LARGE_STRANGE_CRYSTAL = register("large_strange_crystal", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.LARGE_STRANGE_CRYSTAL.get(), p));
    public static final DeferredHolder<Item, Item> STRANGE_CRYSTAL = register("strange_crystal", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.STRANGE_CRYSTAL.get(), p));
    public static final DeferredHolder<Item, Item> STRANGEROOT = register("strangeroot", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.STRANGEROOT.get(), p));
    //endregion

    //region dungeon
    public static final DeferredHolder<Item, Item> OMINOUS_ALTAR = register("ominous_altar", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.OMINOUS_ALTAR.get(), p));
    public static final DeferredHolder<Item, Item> OMINOUS_OBELISK = register("ominous_obelisk", MalumItems::DEFAULT_PROPERTIES, (p) -> new MultiBlockItem(MalumBlocks.OMINOUS_OBELISK.get(), p, OminousObeliskBlockEntity.STRUCTURE));

    public static final DeferredHolder<Item, Item> SHAPED_SLAB = register("shaped_slab", MalumItems::GEAR_PROPERTIES, (p) -> new ShapedSlabSwordItem(ARCHAIC_SLATE, 2.5f, -0.8f, p));
    public static final DeferredHolder<Item, Item> BROKEN_BLADE = register("broken_blade", MalumItems::GEAR_PROPERTIES, (p) -> new BrokenBladeSwordItem(ARCHAIC_SLATE, -0.5f, -0.6f, p));

    public static final DeferredHolder<Item, Item> IRON_CROWN = register("iron_crown", MalumItems::DEFAULT_PROPERTIES, Item::new);


    public static final DeferredHolder<Item, Item> ODD_SCRIPTURES_I = register("odd_scriptures_i", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.ODD_SCRIPTURES_I.get(), p));
    public static final DeferredHolder<Item, Item> ODD_SCRIPTURES_II = register("odd_scriptures_ii", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.ODD_SCRIPTURES_II.get(), p));
    public static final DeferredHolder<Item, Item> ODD_SCRIPTURES_III = register("odd_scriptures_iii", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.ODD_SCRIPTURES_III.get(), p));
    public static final DeferredHolder<Item, Item> ODD_SCRIPTURES_IV = register("odd_scriptures_iv", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.ODD_SCRIPTURES_IV.get(), p));
    public static final DeferredHolder<Item, Item> ODD_SCRIPTURES_V = register("odd_scriptures_v", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.ODD_SCRIPTURES_V.get(), p));
    public static final DeferredHolder<Item, Item> ODD_SCRIPTURES_VI = register("odd_scriptures_vi", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.ODD_SCRIPTURES_VI.get(), p));
    public static final DeferredHolder<Item, Item> ODD_SCRIPTURES_VII = register("odd_scriptures_vii", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.ODD_SCRIPTURES_VII.get(), p));
    public static final DeferredHolder<Item, Item> ODD_SCRIPTURES_VIII = register("odd_scriptures_viii", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.ODD_SCRIPTURES_VIII.get(), p));
    public static final DeferredHolder<Item, Item> ODD_SCRIPTURES_IX = register("odd_scriptures_ix", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.ODD_SCRIPTURES_IX.get(), p));

    public static final DeferredHolder<Item, Item> VEILED_EFFIGY = register("veiled_effigy", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.VEILED_EFFIGY.get(), p));
    public static final DeferredHolder<Item, Item> CORRUPT_EFFIGY = register("corrupt_effigy", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.CORRUPT_EFFIGY.get(), p));
    public static final DeferredHolder<Item, Item> CRACKED_EFFIGY = register("cracked_effigy", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.CRACKED_EFFIGY.get(), p));

    public static final DeferredHolder<Item, Item> COLUMNAR_FLESH = register("columnar_flesh", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.COLUMNAR_FLESH.get(), p));
    public static final DeferredHolder<Item, Item> FLESHBULB = register("fleshbulb", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.FLESHBULB.get(), p));
    public static final DeferredHolder<Item, Item> WRITHING_FLESH = register("writhing_flesh", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.WRITHING_FLESH.get(), p));

    //endregion

    //region soulwood
    public static final DeferredHolder<Item, Item> SOULWOOD_SAPLING = register("soulwood_sapling", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.SOULWOOD_SAPLING.get(), p));

    public static final DeferredHolder<Item, Item> CURSED_SAP = register("cursed_sap", MalumItems::DEFAULT_PROPERTIES, (p) -> new BottledDrinkItem(DEFAULT_PROPERTIES().food(MalumFoodProperties.CURSED_SAP)));
    public static final DeferredHolder<Item, Item> CURSED_SAPBALL = register("cursed_sapball", MalumItems::DEFAULT_PROPERTIES, Item::new);

    public static final DeferredHolder<Item, Item> SOULWOOD_LEAVES = register("soulwood_leaves", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.SOULWOOD_LEAVES.get(), p));
    public static final DeferredHolder<Item, Item> HANGING_SOULWOOD_LEAVES = register("hanging_soulwood_leaves", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.HANGING_SOULWOOD_LEAVES.get(), p));

    public static final DeferredHolder<Item, Item> BLIGHTED_SOULWOOD = register("blighted_soulwood", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BLIGHTED_SOULWOOD.get(), p));

    public static final DeferredHolder<Item, Item> SOULWOOD_LOG = register("soulwood_log", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.SOULWOOD_LOG.get(), p));
    public static final DeferredHolder<Item, Item> STRIPPED_SOULWOOD_LOG = register("stripped_soulwood_log", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.STRIPPED_SOULWOOD_LOG.get(), p));

    public static final DeferredHolder<Item, Item> SOULWOOD = register("soulwood", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.SOULWOOD.get(), p));
    public static final DeferredHolder<Item, Item> STRIPPED_SOULWOOD = register("stripped_soulwood", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.STRIPPED_SOULWOOD.get(), p));

    public static final DeferredHolder<Item, Item> SAPPY_SOULWOOD_LOG = register("sappy_soulwood_log", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.SAPPY_SOULWOOD_LOG.get(), p));
    public static final DeferredHolder<Item, Item> STRIPPED_SAPPY_SOULWOOD_LOG = register("stripped_sappy_soulwood_log", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.STRIPPED_SAPPY_SOULWOOD_LOG.get(), p));

    public static final DeferredHolder<Item, Item> SOULWOOD_BOARDS = register("soulwood_boards", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.SOULWOOD_BOARDS.get(), p));
    public static final DeferredHolder<Item, Item> SOULWOOD_BOARDS_STAIRS = register("soulwood_boards_stairs", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.SOULWOOD_BOARDS_STAIRS.get(), p));
    public static final DeferredHolder<Item, Item> SOULWOOD_BOARDS_SLAB = register("soulwood_boards_slab", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.SOULWOOD_BOARDS_SLAB.get(), p));

    public static final DeferredHolder<Item, Item> VERTICAL_SOULWOOD_BOARDS = register("vertical_soulwood_boards", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.VERTICAL_SOULWOOD_BOARDS.get(), p));
    public static final DeferredHolder<Item, Item> VERTICAL_SOULWOOD_BOARDS_STAIRS = register("vertical_soulwood_boards_stairs", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.VERTICAL_SOULWOOD_BOARDS_STAIRS.get(), p));
    public static final DeferredHolder<Item, Item> VERTICAL_SOULWOOD_BOARDS_SLAB = register("vertical_soulwood_boards_slab", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.VERTICAL_SOULWOOD_BOARDS_SLAB.get(), p));

    public static final DeferredHolder<Item, Item> SOULWOOD_PLANKS = register("soulwood_planks", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.SOULWOOD_PLANKS.get(), p));
    public static final DeferredHolder<Item, Item> SOULWOOD_PLANKS_STAIRS = register("soulwood_planks_stairs", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.SOULWOOD_PLANKS_STAIRS.get(), p));
    public static final DeferredHolder<Item, Item> SOULWOOD_PLANKS_SLAB = register("soulwood_planks_slab", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.SOULWOOD_PLANKS_SLAB.get(), p));

    public static final DeferredHolder<Item, Item> VERTICAL_SOULWOOD_PLANKS = register("vertical_soulwood_planks", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.VERTICAL_SOULWOOD_PLANKS.get(), p));
    public static final DeferredHolder<Item, Item> VERTICAL_SOULWOOD_PLANKS_STAIRS = register("vertical_soulwood_planks_stairs", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.VERTICAL_SOULWOOD_PLANKS_STAIRS.get(), p));
    public static final DeferredHolder<Item, Item> VERTICAL_SOULWOOD_PLANKS_SLAB = register("vertical_soulwood_planks_slab", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.VERTICAL_SOULWOOD_PLANKS_SLAB.get(), p));

    public static final DeferredHolder<Item, Item> SOULWOOD_TILES = register("soulwood_tiles", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.SOULWOOD_TILES.get(), p));
    public static final DeferredHolder<Item, Item> SOULWOOD_TILES_STAIRS = register("soulwood_tiles_stairs", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.SOULWOOD_TILES_STAIRS.get(), p));
    public static final DeferredHolder<Item, Item> SOULWOOD_TILES_SLAB = register("soulwood_tiles_slab", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.SOULWOOD_TILES_SLAB.get(), p));

    public static final DeferredHolder<Item, Item> RUSTIC_SOULWOOD_PLANKS = register("rustic_soulwood_planks", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUSTIC_SOULWOOD_PLANKS.get(), p));
    public static final DeferredHolder<Item, Item> RUSTIC_SOULWOOD_PLANKS_STAIRS = register("rustic_soulwood_planks_stairs", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUSTIC_SOULWOOD_PLANKS_STAIRS.get(), p));
    public static final DeferredHolder<Item, Item> RUSTIC_SOULWOOD_PLANKS_SLAB = register("rustic_soulwood_planks_slab", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUSTIC_SOULWOOD_PLANKS_SLAB.get(), p));

    public static final DeferredHolder<Item, Item> VERTICAL_RUSTIC_SOULWOOD_PLANKS = register("vertical_rustic_soulwood_planks", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.VERTICAL_RUSTIC_SOULWOOD_PLANKS.get(), p));
    public static final DeferredHolder<Item, Item> VERTICAL_RUSTIC_SOULWOOD_PLANKS_STAIRS = register("vertical_rustic_soulwood_planks_stairs", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.VERTICAL_RUSTIC_SOULWOOD_PLANKS_STAIRS.get(), p));
    public static final DeferredHolder<Item, Item> VERTICAL_RUSTIC_SOULWOOD_PLANKS_SLAB = register("vertical_rustic_soulwood_planks_slab", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.VERTICAL_RUSTIC_SOULWOOD_PLANKS_SLAB.get(), p));

    public static final DeferredHolder<Item, Item> RUSTIC_SOULWOOD_TILES = register("rustic_soulwood_tiles", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUSTIC_SOULWOOD_TILES.get(), p));
    public static final DeferredHolder<Item, Item> RUSTIC_SOULWOOD_TILES_STAIRS = register("rustic_soulwood_tiles_stairs", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUSTIC_SOULWOOD_TILES_STAIRS.get(), p));
    public static final DeferredHolder<Item, Item> RUSTIC_SOULWOOD_TILES_SLAB = register("rustic_soulwood_tiles_slab", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.RUSTIC_SOULWOOD_TILES_SLAB.get(), p));

    public static final DeferredHolder<Item, Item> SOULWOOD_PANEL = register("soulwood_panel", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.SOULWOOD_PANEL.get(), p));
    public static final DeferredHolder<Item, Item> CUT_SOULWOOD_PLANKS = register("cut_soulwood_planks", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.CUT_SOULWOOD_PLANKS.get(), p));
    public static final DeferredHolder<Item, Item> SOULWOOD_BEAM = register("soulwood_beam", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.SOULWOOD_BEAM.get(), p));

    public static final DeferredHolder<Item, Item> SOULWOOD_DOOR = register("soulwood_door", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.SOULWOOD_DOOR.get(), p));
    public static final DeferredHolder<Item, Item> BOLTED_SOULWOOD_DOOR = register("bolted_soulwood_door", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BOLTED_SOULWOOD_DOOR.get(), p));

    public static final DeferredHolder<Item, Item> SOULWOOD_TRAPDOOR = register("soulwood_trapdoor", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.SOULWOOD_TRAPDOOR.get(), p));
    public static final DeferredHolder<Item, Item> BOLTED_SOULWOOD_TRAPDOOR = register("bolted_soulwood_trapdoor", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BOLTED_SOULWOOD_TRAPDOOR.get(), p));

    public static final DeferredHolder<Item, Item> SOULWOOD_BOARDS_DOOR = register("soulwood_boards_door", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.SOULWOOD_BOARDS_DOOR.get(), p));
    public static final DeferredHolder<Item, Item> BOLTED_SOULWOOD_BOARDS_DOOR = register("bolted_soulwood_boards_door", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BOLTED_SOULWOOD_BOARDS_DOOR.get(), p));

    public static final DeferredHolder<Item, Item> SOULWOOD_BOARDS_TRAPDOOR = register("soulwood_boards_trapdoor", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.SOULWOOD_BOARDS_TRAPDOOR.get(), p));
    public static final DeferredHolder<Item, Item> BOLTED_SOULWOOD_BOARDS_TRAPDOOR = register("bolted_soulwood_boards_trapdoor", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.BOLTED_SOULWOOD_BOARDS_TRAPDOOR.get(), p));

    public static final DeferredHolder<Item, Item> SOULWOOD_BUTTON = register("soulwood_planks_button", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.SOULWOOD_BUTTON.get(), p));
    public static final DeferredHolder<Item, Item> SOULWOOD_PRESSURE_PLATE = register("soulwood_planks_pressure_plate", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.SOULWOOD_PRESSURE_PLATE.get(), p));

    public static final DeferredHolder<Item, Item> SOULWOOD_BOARDS_WALL = register("soulwood_boards_wall", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.SOULWOOD_BOARDS_WALL.get(), p));
    public static final DeferredHolder<Item, Item> SOULWOOD_FENCE = register("soulwood_planks_fence", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.SOULWOOD_FENCE.get(), p));
    public static final DeferredHolder<Item, Item> SOULWOOD_FENCE_GATE = register("soulwood_planks_fence_gate", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.SOULWOOD_FENCE_GATE.get(), p));

    public static final DeferredHolder<Item, Item> SOULWOOD_ITEM_PEDESTAL = register("soulwood_item_pedestal", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.SOULWOOD_ITEM_PEDESTAL.get(), p));
    public static final DeferredHolder<Item, Item> ORNATE_SOULWOOD_ITEM_PEDESTAL = register("ornate_soulwood_item_pedestal", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.ORNATE_SOULWOOD_ITEM_PEDESTAL.get(), p));
    public static final DeferredHolder<Item, Item> SOULWOOD_ITEM_STAND = register("soulwood_item_stand", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.SOULWOOD_ITEM_STAND.get(), p));
    public static final DeferredHolder<Item, Item> ORNATE_SOULWOOD_ITEM_STAND = register("ornate_soulwood_item_stand", MalumItems::DEFAULT_PROPERTIES, (p) -> new BlockItem(MalumBlocks.ORNATE_SOULWOOD_ITEM_STAND.get(), p));

    public static final DeferredHolder<Item, Item> SOULWOOD_SIGN = register("soulwood_sign", () -> DEFAULT_PROPERTIES().stacksTo(16), (p) -> new SignItem(p, MalumBlocks.SOULWOOD_SIGN.get(), MalumBlocks.SOULWOOD_WALL_SIGN.get()));
    public static final DeferredHolder<Item, Item> SOULWOOD_BOAT = register("soulwood_boat", () -> DEFAULT_PROPERTIES().stacksTo(1), (p) -> new BoatItem(false, MalumEnumParams.SOULWOOD_BOAT_TYPE.getValue(), p));
    public static final DeferredHolder<Item, Item> SOULWOOD_CHEST_BOAT = register("soulwood_chest_boat", () -> DEFAULT_PROPERTIES().stacksTo(1), (p) -> new BoatItem(true, MalumEnumParams.SOULWOOD_BOAT_TYPE.getValue(), p));
    //endregion

    //region cosmetics
    public static final DeferredHolder<Item, Item> ESOTERIC_SPOOL = register("esoteric_spool", MalumItems::COSMETIC_PROPERTIES, Item::new);
    public static final DeferredHolder<Item, Item> ANCIENT_WEAVE = register("ancient_weave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.ANCIENT_CLOTH));
    public static final DeferredHolder<Item, Item> CORNERED_WEAVE = register("cornered_weave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.COMMANDO));
    public static final DeferredHolder<Item, Item> MECHANICAL_WEAVE_V1 = register("mechanical_weave_v1", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.BLUE_MACHINE));
    public static final DeferredHolder<Item, Item> MECHANICAL_WEAVE_V2 = register("mechanical_weave_v2", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.RED_MACHINE));

    public static final DeferredHolder<Item, Item> ACE_PRIDEWEAVE = register("ace_prideweave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.ACE));
    public static final DeferredHolder<Item, Item> AGENDER_PRIDEWEAVE = register("agender_prideweave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.AGENDER));
    public static final DeferredHolder<Item, Item> ARO_PRIDEWEAVE = register("aro_prideweave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.ARO));
    public static final DeferredHolder<Item, Item> AROACE_PRIDEWEAVE = register("aroace_prideweave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.AROACE));
    public static final DeferredHolder<Item, Item> BI_PRIDEWEAVE = register("bi_prideweave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.BI));
    public static final DeferredHolder<Item, Item> DEMIBOY_PRIDEWEAVE = register("demiboy_prideweave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.DEMIBOY));
    public static final DeferredHolder<Item, Item> DEMIGIRL_PRIDEWEAVE = register("demigirl_prideweave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.DEMIGIRL));
    public static final DeferredHolder<Item, Item> ENBY_PRIDEWEAVE = register("enby_prideweave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.ENBY));
    public static final DeferredHolder<Item, Item> GAY_PRIDEWEAVE = register("gay_prideweave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.GAY));
    public static final DeferredHolder<Item, Item> GENDERFLUID_PRIDEWEAVE = register("genderfluid_prideweave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.GENDERFLUID));
    public static final DeferredHolder<Item, Item> GENDERQUEER_PRIDEWEAVE = register("genderqueer_prideweave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.GENDERQUEER));
    public static final DeferredHolder<Item, Item> INTERSEX_PRIDEWEAVE = register("intersex_prideweave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.INTERSEX));
    public static final DeferredHolder<Item, Item> LESBIAN_PRIDEWEAVE = register("lesbian_prideweave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.LESBIAN));
    public static final DeferredHolder<Item, Item> PAN_PRIDEWEAVE = register("pan_prideweave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.PAN));
    public static final DeferredHolder<Item, Item> PLURAL_PRIDEWEAVE = register("plural_prideweave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.PLURAL));
    public static final DeferredHolder<Item, Item> POLY_PRIDEWEAVE = register("poly_prideweave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.POLY));
    public static final DeferredHolder<Item, Item> PRIDE_PRIDEWEAVE = register("pride_prideweave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.PRIDE));
    public static final DeferredHolder<Item, Item> TRANS_PRIDEWEAVE = register("trans_prideweave", MalumItems::COSMETIC_PROPERTIES, p -> skinHoldingItem(p, ItemSkinComponent.TRANS));

    public static final DeferredHolder<Item, Item> TOPHAT = register("tophat", () -> COSMETIC_PROPERTIES().stacksTo(1), CurioTopHat::new);
    //endregion

    //region hidden items
    public static final DeferredHolder<Item, Item> SOUL_OF_A_SCYTHE = register("soul_of_a_scythe", MalumItems::HIDDEN_PROPERTIES, TemporarilyDisabledItem::new);
    public static final DeferredHolder<Item, Item> SOUL_OF_THE_ANCHOR = register("soul_of_the_anchor", MalumItems::HIDDEN_PROPERTIES, TemporarilyDisabledItem::new);

    public static final DeferredHolder<Item, Item> THE_DEVICE = register("the_device", MalumItems::HIDDEN_PROPERTIES, (p) -> new BlockItem(MalumBlocks.THE_DEVICE.get(), p));
    public static final DeferredHolder<Item, Item> THE_VESSEL = register("the_vessel", MalumItems::HIDDEN_PROPERTIES, (p) -> new BlockItem(MalumBlocks.THE_VESSEL.get(), p));
    public static final DeferredHolder<Item, Item> TOKEN_OF_GRATITUDE = register("token_of_gratitude", MalumItems::HIDDEN_PROPERTIES, CurioTokenOfGratitude::new);

    public static final DeferredHolder<Item, Item> PRIMORDIAL_SOUP = register("primordial_soup", MalumItems::HIDDEN_PROPERTIES, (p) -> new BlockItem(MalumBlocks.PRIMORDIAL_SOUP.get(), p));
    public static final DeferredHolder<Item, Item> VOID_CONDUIT = register("void_conduit", MalumItems::HIDDEN_PROPERTIES, (p) -> new BlockItem(MalumBlocks.VOID_CONDUIT.get(), p));
    public static final DeferredHolder<Item, Item> VOID_DEPOT = register("void_depot", MalumItems::HIDDEN_PROPERTIES, (p) -> new BlockItem(MalumBlocks.VOID_DEPOT.get(), p));

    public static final DeferredHolder<Item, Item> WEEPING_WELL_CENTER = register("weeping_well_center", MalumItems::HIDDEN_PROPERTIES, (p) -> new BlockItem(MalumBlocks.WEEPING_WELL_CENTER.get(), p));
    public static final DeferredHolder<Item, Item> WEEPING_WELL_SIDE = register("weeping_well_side", MalumItems::HIDDEN_PROPERTIES, (p) -> new BlockItem(MalumBlocks.WEEPING_WELL_SIDE.get(), p));
    public static final DeferredHolder<Item, Item> WEEPING_WELL_SIDE_MIRROR = register("weeping_well_side_mirror", MalumItems::HIDDEN_PROPERTIES, (p) -> new BlockItem(MalumBlocks.WEEPING_WELL_SIDE_MIRROR.get(), p));
    public static final DeferredHolder<Item, Item> WEEPING_WELL_CORNER = register("weeping_well_corner", MalumItems::HIDDEN_PROPERTIES, (p) -> new BlockItem(MalumBlocks.WEEPING_WELL_CORNER.get(), p));
    public static final DeferredHolder<Item, Item> WEEPING_WELL_FLAGSTONE = register("weeping_well_flagstone", MalumItems::HIDDEN_PROPERTIES, (p) -> new BlockItem(MalumBlocks.WEEPING_WELL_FLAGSTONE.get(), p));
    public static final DeferredHolder<Item, Item> WEEPING_WELL_COLUMN_BASE = register("weeping_well_column_base", MalumItems::HIDDEN_PROPERTIES, (p) -> new BlockItem(MalumBlocks.WEEPING_WELL_COLUMN_BASE.get(), p));
    public static final DeferredHolder<Item, Item> WEEPING_WELL_COLUMN = register("weeping_well_column", MalumItems::HIDDEN_PROPERTIES, (p) -> new BlockItem(MalumBlocks.WEEPING_WELL_COLUMN.get(), p));
    public static final DeferredHolder<Item, Item> WEEPING_WELL_COLUMN_CAP = register("weeping_well_column_cap", MalumItems::HIDDEN_PROPERTIES, (p) -> new BlockItem(MalumBlocks.WEEPING_WELL_COLUMN_CAP.get(), p));

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
                        SOULWOVEN_BANNER.get(),
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
                    RUNEWOOD_LEAVES.get(), HANGING_RUNEWOOD_LEAVES.get(), AZURE_RUNEWOOD_LEAVES.get(), HANGING_AZURE_RUNEWOOD_LEAVES.get());
            event.register((stack, tintIndex) -> ColorHelper.getColor(((IGradientedLeavesBlock) ((BlockItem) stack.getItem()).getBlock()).getMinColor()),
                    SOULWOOD_LEAVES.get(), HANGING_SOULWOOD_LEAVES.get());

            event.register((s, c) -> switch (c) {
                        case 2 -> EtherItem.getSecondaryColor(s);
                        case 1 -> EtherItem.getPrimaryColor(s);
                        default -> -1;
                    },
                    ETHER_TORCH.get(), IRIDESCENT_ETHER_TORCH.get(),
                    ETHER_CANDLE.get(), IRIDESCENT_ETHER_CANDLE.get(),
                    TAINTED_ETHER_BRAZIER.get(), TWISTED_ETHER_BRAZIER.get(), DROSS_ETHER_BRAZIER.get(),
                    TAINTED_IRIDESCENT_ETHER_BRAZIER.get(), TWISTED_IRIDESCENT_ETHER_BRAZIER.get(), DROSS_IRIDESCENT_ETHER_BRAZIER.get(),
                    TAINTED_ETHER_CRESSET.get(), TWISTED_ETHER_CRESSET.get(), DROSS_ETHER_CRESSET.get(),
                    TAINTED_IRIDESCENT_ETHER_CRESSET.get(), TWISTED_IRIDESCENT_ETHER_CRESSET.get(), DROSS_IRIDESCENT_ETHER_CRESSET.get());

            event.register((s, c) -> c == 0 ? EtherItem.getPrimaryColor(s) : EtherItem.getSecondaryColor(s),
                    ETHER.get(), IRIDESCENT_ETHER.get());
        }
    }
}
