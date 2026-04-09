package com.sammy.malum.registry.common.content;

import com.sammy.malum.common.block.blight.*;
import com.sammy.malum.common.block.blight.scarstone.LargeStrangeCrystalBlock;
import com.sammy.malum.common.block.blight.scarstone.ScarstoneBlock;
import com.sammy.malum.common.block.blight.scarstone.StrangeCrystalBlock;
import com.sammy.malum.common.block.blight.scarstone.StrangeRootBlock;
import com.sammy.malum.common.block.curiosities.banner.SoulwovenBannerBlock;
import com.sammy.malum.common.block.curiosities.gust_igniter.GustIgniterBlock;
import com.sammy.malum.common.block.curiosities.gust_igniter.wind_tunnel.WindTunnelBlock;
import com.sammy.malum.common.block.curiosities.mana_mote.ManaMoteBlock;
import com.sammy.malum.common.block.curiosities.obelisk.ObeliskComponentBlock;
import com.sammy.malum.common.block.curiosities.obelisk.brilliant.BrillianceObeliskCoreBlock;
import com.sammy.malum.common.block.curiosities.obelisk.brilliant.BrilliantObeliskBlockEntity;
import com.sammy.malum.common.block.curiosities.obelisk.rite_pylon.ArcanaPylonBlockEntity;
import com.sammy.malum.common.block.curiosities.obelisk.rite_pylon.ArcanaPylonComponentBlock;
import com.sammy.malum.common.block.curiosities.obelisk.rite_pylon.ArcanaPylonCoreBlock;
import com.sammy.malum.common.block.curiosities.obelisk.runewood.RunewoodObeliskBlockEntity;
import com.sammy.malum.common.block.curiosities.obelisk.runewood.RunewoodObeliskCoreBlock;
import com.sammy.malum.common.block.curiosities.redstone.wavebanker.WaveBankerBlock;
import com.sammy.malum.common.block.curiosities.redstone.wavebreaker.WaveBreakerBlock;
import com.sammy.malum.common.block.curiosities.redstone.wavecharger.WaveChargerBlock;
import com.sammy.malum.common.block.curiosities.redstone.wavemaker.WaveMakerBlock;
import com.sammy.malum.common.block.curiosities.repair_pylon.RepairPylonComponentBlock;
import com.sammy.malum.common.block.curiosities.repair_pylon.RepairPylonCoreBlock;
import com.sammy.malum.common.block.curiosities.repair_pylon.RepairPylonCoreBlockEntity;
import com.sammy.malum.common.block.curiosities.ritual_plinth.RitualPlinthBlock;
import com.sammy.malum.common.block.curiosities.runic_workbench.RunicWorkbenchBlock;
import com.sammy.malum.common.block.curiosities.soul_brazier.SoulBrazierBlock;
import com.sammy.malum.common.block.curiosities.spirit_altar.SpiritAltarBlock;
import com.sammy.malum.common.block.curiosities.spirit_catalyzer.SpiritCatalyzerComponentBlock;
import com.sammy.malum.common.block.curiosities.spirit_catalyzer.SpiritCatalyzerCoreBlock;
import com.sammy.malum.common.block.curiosities.spirit_catalyzer.SpiritCatalyzerCoreBlockEntity;
import com.sammy.malum.common.block.curiosities.spirit_crucible.SpiritCrucibleComponentBlock;
import com.sammy.malum.common.block.curiosities.spirit_crucible.SpiritCrucibleCoreBlock;
import com.sammy.malum.common.block.curiosities.spirit_crucible.SpiritCrucibleCoreBlockEntity;
import com.sammy.malum.common.block.curiosities.totem.TotemBaseBlock;
import com.sammy.malum.common.block.curiosities.totem.TotemPoleBlock;
import com.sammy.malum.common.block.curiosities.totem.anchor.RiteAnchorBlock;
import com.sammy.malum.common.block.curiosities.totem.channel.RiteChannelBlock;
import com.sammy.malum.common.block.curiosities.totem.spreader.RiteSpreaderBlock;
import com.sammy.malum.common.block.curiosities.totem.unweaver.RiteUnweaverBlock;
import com.sammy.malum.common.block.curiosities.totem.waveform.WaveformTotemBaseBlock;
import com.sammy.malum.common.block.curiosities.void_depot.VoidDepotBlock;
import com.sammy.malum.common.block.curiosities.weavers_workbench.WeaversWorkbenchBlock;
import com.sammy.malum.common.block.curiosities.weeping_well.PrimordialSoupBlock;
import com.sammy.malum.common.block.curiosities.weeping_well.VoidConduitBlock;
import com.sammy.malum.common.block.curiosities.weeping_well.encasement.WeepingWellBlock;
import com.sammy.malum.common.block.curiosities.weeping_well.encasement.WeepingWellDirectionalBlock;
import com.sammy.malum.common.block.curiosities.weeping_well.encasement.WeepingWellLayeredBlock;
import com.sammy.malum.common.block.decor.ColumnBlock;
import com.sammy.malum.common.block.decor.SpiritedGlassBlock;
import com.sammy.malum.common.block.decor.VarnishedTerracottaBlock;
import com.sammy.malum.common.block.dungeon.*;
import com.sammy.malum.common.block.dungeon.curiosities.OminousAltarBlock;
import com.sammy.malum.common.block.dungeon.curiosities.OminousObeliskCoreBlock;
import com.sammy.malum.common.block.ether.EtherBlock;
import com.sammy.malum.common.block.ether.EtherCandleBlock;
import com.sammy.malum.common.block.ether.EtherTorchBlock;
import com.sammy.malum.common.block.ether.EtherWallTorchBlock;
import com.sammy.malum.common.block.flora.EbonySaplingBlock;
import com.sammy.malum.common.block.flora.EbonyStalkBlock;
import com.sammy.malum.common.block.flora.WildWitchhazelPlantBlock;
import com.sammy.malum.common.block.flora.WitchhazelCropBlock;
import com.sammy.malum.common.block.storage.jar.SpiritJarBlock;
import com.sammy.malum.common.block.storage.pedestal.ItemPedestalBlock;
import com.sammy.malum.common.block.storage.stand.ItemStandBlock;
import com.sammy.malum.common.entity.nitrate.EthericNitrate;
import com.sammy.malum.common.item.BlightedGunkItem;
import com.sammy.malum.common.item.BrillianceChunkItem;
import com.sammy.malum.common.item.GeasItem;
import com.sammy.malum.common.item.augment.*;
import com.sammy.malum.common.item.augment.core.*;
import com.sammy.malum.common.item.banner.SoulwovenBannerBlockItem;
import com.sammy.malum.common.item.codex.EncyclopediaArcanaItem;
import com.sammy.malum.common.item.codex.EncyclopediaEsotericaItem;
import com.sammy.malum.common.item.curiosities.armor.MalignantStrongholdArmorItem;
import com.sammy.malum.common.item.curiosities.armor.SoulHunterArmorItem;
import com.sammy.malum.common.item.curiosities.armor.SoulStainedSteelArmorItem;
import com.sammy.malum.common.item.curiosities.curios.CurioGildedBelt;
import com.sammy.malum.common.item.curiosities.curios.CurioGildedRing;
import com.sammy.malum.common.item.curiosities.curios.CurioOrnateNecklace;
import com.sammy.malum.common.item.curiosities.curios.CurioOrnateRing;
import com.sammy.malum.common.item.curiosities.curios.brooches.CurioElaborateBrooch;
import com.sammy.malum.common.item.curiosities.curios.brooches.CurioGlassBrooch;
import com.sammy.malum.common.item.curiosities.curios.brooches.CurioGluttonousBrooch;
import com.sammy.malum.common.item.curiosities.curios.brooches.CurioRunicBrooch;
import com.sammy.malum.common.item.curiosities.curios.runes.madness.*;
import com.sammy.malum.common.item.curiosities.curios.runes.miracle.*;
import com.sammy.malum.common.item.curiosities.curios.runes.totemic.*;
import com.sammy.malum.common.item.curiosities.curios.sets.alchemical.*;
import com.sammy.malum.common.item.curiosities.curios.sets.esoteric.CurioArcaneSpoilRing;
import com.sammy.malum.common.item.curiosities.curios.sets.esoteric.CurioConcealingRing;
import com.sammy.malum.common.item.curiosities.curios.sets.prospector.CurioDischargeRing;
import com.sammy.malum.common.item.curiosities.curios.sets.prospector.CurioHeartyAvariceRing;
import com.sammy.malum.common.item.curiosities.curios.sets.prospector.CurioProspectorBelt;
import com.sammy.malum.common.item.curiosities.curios.sets.rotten.CurioStarvedBelt;
import com.sammy.malum.common.item.curiosities.curios.sets.rotten.CurioSwarmingRing;
import com.sammy.malum.common.item.curiosities.curios.sets.rotten.CurioVoraciousRing;
import com.sammy.malum.common.item.curiosities.curios.sets.scythe.CurioHowlingMaelstromRing;
import com.sammy.malum.common.item.curiosities.curios.sets.scythe.CurioNarrowEdgeNecklace;
import com.sammy.malum.common.item.curiosities.curios.sets.scythe.CurioRisingEdgeRing;
import com.sammy.malum.common.item.curiosities.curios.sets.soulward.CurioMagebaneBelt;
import com.sammy.malum.common.item.curiosities.curios.sets.weeping.*;
import com.sammy.malum.common.item.curiosities.pouch.RavenousPouchItem;
import com.sammy.malum.common.item.curiosities.pouch.SoulwovenPouchItem;
import com.sammy.malum.common.item.curiosities.tools.CatalystLobberItem;
import com.sammy.malum.common.item.curiosities.tools.LamplightersTongsItem;
import com.sammy.malum.common.item.curiosities.tools.TinkeringToolItem;
import com.sammy.malum.common.item.curiosities.tools.spellweaver.SpellweavingAxeItem;
import com.sammy.malum.common.item.curiosities.tools.spellweaver.SpellweavingPickaxeItem;
import com.sammy.malum.common.item.curiosities.weapons.*;
import com.sammy.malum.common.item.curiosities.weapons.scythe.EdgeOfDeliveranceItem;
import com.sammy.malum.common.item.curiosities.weapons.scythe.MagicScytheItem;
import com.sammy.malum.common.item.curiosities.weapons.scythe.MalumScytheItem;
import com.sammy.malum.common.item.curiosities.weapons.scythe.RavenousScytheItem;
import com.sammy.malum.common.item.curiosities.weapons.staff.ErosionScepterItem;
import com.sammy.malum.common.item.curiosities.weapons.staff.HexStaffItem;
import com.sammy.malum.common.item.curiosities.weapons.staff.UnwindingChaosStaffItem;
import com.sammy.malum.common.item.disc.AestheticaMusicDiscItem;
import com.sammy.malum.common.item.disc.ArcaneElegyMusicDiscItem;
import com.sammy.malum.common.item.food.BottledDrinkItem;
import com.sammy.malum.common.item.impetus.FracturedImpetusItem;
import com.sammy.malum.common.item.impetus.ImpetusItem;
import com.sammy.malum.common.item.nucleus.PyreNucleusItem;
import com.sammy.malum.common.item.nucleus.WindNucleusItem;
import com.sammy.malum.common.item.spirit.FusedConsciousnessItem;
import com.sammy.malum.common.item.spirit.SpiritShardItem;
import com.sammy.malum.common.item.spirit.UmbralSpiritShardItem;
import com.sammy.malum.compat.farmersdelight.FarmersDelightCompat;
import com.sammy.malum.registry.common.MalumTags;
import com.sammy.malum.registry.common.content.block.MalumBlockEntities;
import com.sammy.malum.registry.common.content.block.MalumBlocks;
import com.sammy.malum.registry.common.content.block.properties.*;
import com.sammy.malum.registry.common.content.item.MalumFoodProperties;
import com.sammy.malum.registry.common.content.item.MalumItemProperties;
import com.sammy.malum.registry.common.content.item.MalumItemTiers;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;
import com.sammy.malum.registry.common.sound.MalumBlockSoundEvents;
import com.sammy.malum.registry.common.util.MetallicsItemRegistryBundle;
import com.sammy.malum.registry.common.util.RockBlockSet;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import team.lodestar.lodestone.modules.toolkit.block.BlockBlockItemHolder;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneDirectionalBlock;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneStairBlock;
import team.lodestar.lodestone.modules.toolkit.item.tools.magic.*;
import team.lodestar.lodestone.modules.toolkit.multiblock.MultiBlockItem;

import static com.sammy.malum.registry.common.MalumTags.Blocks.*;
import static com.sammy.malum.registry.common.content.block.MalumBlocks.registerBlock;
import static com.sammy.malum.registry.common.content.block.properties.MalumBlockProperties.*;
import static com.sammy.malum.registry.common.content.item.MalumItemProperties.register;
import static com.sammy.malum.registry.common.content.item.MalumItemTiers.*;
import static net.minecraft.tags.BlockTags.*;
import static net.minecraft.world.item.Rarity.*;

public class MalumContent {

    public static final DeferredItem<Item> ENCYCLOPEDIA_ARCANA = register("encyclopedia_arcana", () -> MalumItemProperties.GEAR_PROPERTIES().rarity(UNCOMMON), EncyclopediaArcanaItem::new);
    public static final DeferredItem<Item> ENCYCLOPEDIA_ESOTERICA = register("encyclopedia_esoterica", () -> MalumItemProperties.GEAR_PROPERTIES().rarity(EPIC), EncyclopediaEsotericaItem::new);

    public static final DeferredItem<GeasItem> GEAS = register("geas", () -> MalumItemProperties.HIDDEN_PROPERTIES().rarity(RARE), GeasItem::new);

    public static final DeferredItem<Item> ARCANE_ELEGY = register("music_disc_arcane_elegy", () -> MalumItemProperties.HIDDEN_PROPERTIES().rarity(RARE), ArcaneElegyMusicDiscItem::new);
    public static final DeferredItem<Item> AESTHETICA = register("music_disc_aesthetica", () -> MalumItemProperties.HIDDEN_PROPERTIES().rarity(RARE), AestheticaMusicDiscItem::new);

    public static class Spirits {

        public static final DeferredItem<SpiritShardItem> SACRED_SPIRIT = register("sacred_spirit", MalumItemProperties::DEFAULT_PROPERTIES, (p) -> new SpiritShardItem(p, MalumSpiritTypes.SACRED_SPIRIT));
        public static final DeferredItem<SpiritShardItem> WICKED_SPIRIT = register("wicked_spirit", MalumItemProperties::DEFAULT_PROPERTIES, (p) -> new SpiritShardItem(p, MalumSpiritTypes.WICKED_SPIRIT));
        public static final DeferredItem<SpiritShardItem> ARCANE_SPIRIT = register("arcane_spirit", MalumItemProperties::DEFAULT_PROPERTIES, (p) -> new SpiritShardItem(p, MalumSpiritTypes.ARCANE_SPIRIT));
        public static final DeferredItem<SpiritShardItem> ELDRITCH_SPIRIT = register("eldritch_spirit", MalumItemProperties::DEFAULT_PROPERTIES, (p) -> new SpiritShardItem(p, MalumSpiritTypes.ELDRITCH_SPIRIT));
        public static final DeferredItem<SpiritShardItem> AERIAL_SPIRIT = register("aerial_spirit", MalumItemProperties::DEFAULT_PROPERTIES, (p) -> new SpiritShardItem(p, MalumSpiritTypes.AERIAL_SPIRIT));
        public static final DeferredItem<SpiritShardItem> AQUEOUS_SPIRIT = register("aqueous_spirit", MalumItemProperties::DEFAULT_PROPERTIES, (p) -> new SpiritShardItem(p, MalumSpiritTypes.AQUEOUS_SPIRIT));
        public static final DeferredItem<SpiritShardItem> EARTHEN_SPIRIT = register("earthen_spirit", MalumItemProperties::DEFAULT_PROPERTIES, (p) -> new SpiritShardItem(p, MalumSpiritTypes.EARTHEN_SPIRIT));
        public static final DeferredItem<SpiritShardItem> INFERNAL_SPIRIT = register("infernal_spirit", MalumItemProperties::DEFAULT_PROPERTIES, (p) -> new SpiritShardItem(p, MalumSpiritTypes.INFERNAL_SPIRIT));
        public static final DeferredItem<SpiritShardItem> UMBRAL_SPIRIT = register("umbral_spirit", MalumItemProperties::DEFAULT_PROPERTIES, (p) -> new UmbralSpiritShardItem(p, MalumSpiritTypes.UMBRAL_SPIRIT));

    }

    public static class CompactBlocks {

        public static final BlockBlockItemHolder<Block, BlockItem> BLOCK_OF_RAW_SOULSTONE = registerBlock("block_of_raw_soulstone", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.SOULSTONE_BLOCK(true)));
        public static final BlockBlockItemHolder<Block, BlockItem> BLOCK_OF_SOULSTONE = registerBlock("block_of_soulstone", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.SOULSTONE_BLOCK(false)));
        public static final BlockBlockItemHolder<Block, BlockItem> BLOCK_OF_RAW_BRILLIANCE = registerBlock("block_of_raw_brilliance", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.BRILLIANCE_BLOCK(true)));
        public static final BlockBlockItemHolder<Block, BlockItem> BLOCK_OF_BRILLIANCE = registerBlock("block_of_brilliance", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.BRILLIANCE_BLOCK(false)));
        public static final BlockBlockItemHolder<Block, BlockItem> BLOCK_OF_NATURAL_QUARTZ = registerBlock("block_of_natural_quartz", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.NATURAL_QUARTZ_BLOCK()));
        public static final BlockBlockItemHolder<Block, BlockItem> BLOCK_OF_CTHONIC_GOLD = registerBlock("block_of_cthonic_gold", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.CTHONIC_GOLD_BLOCK()));
        public static final BlockBlockItemHolder<Block, BlockItem> BLOCK_OF_BLAZING_QUARTZ = registerBlock("block_of_blazing_quartz", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.BLAZING_QUARTZ_BLOCK().lightLevel((b) -> 14)));


        public static final BlockBlockItemHolder<Block, BlockItem> BLOCK_OF_ROTTING_ESSENCE = registerBlock("block_of_rotting_essence", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.GENERIC_STORAGE_BLOCK(SoundType.CORAL_BLOCK, DyeColor.GREEN).needsShovel()));
        public static final BlockBlockItemHolder<Block, BlockItem> BLOCK_OF_GRIM_TALC = registerBlock("block_of_grim_talc", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.GENERIC_STORAGE_BLOCK(SoundType.BONE_BLOCK, DyeColor.YELLOW).requiresCorrectToolForDrops().needsPickaxe()));
        public static final BlockBlockItemHolder<Block, BlockItem> BLOCK_OF_EERIE_WEAVE = registerBlock("block_of_eerie_weave", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.GENERIC_STORAGE_BLOCK(SoundType.WOOL, DyeColor.LIGHT_BLUE)));
        public static final BlockBlockItemHolder<Block, BlockItem> BLOCK_OF_WARP_FLUX = registerBlock("block_of_warp_flux", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.GENERIC_STORAGE_BLOCK(MalumBlockSoundEvents.STRANGE_CRYSTAL, DyeColor.PURPLE).requiresCorrectToolForDrops().needsPickaxe().noOcclusion().lightLevel(b -> 8)));

        public static final BlockBlockItemHolder<Block, BlockItem> BLOCK_OF_WIND_NUCLEI = registerBlock("block_of_wind_nuclei", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.GENERIC_STORAGE_BLOCK(SoundType.WOOL, DyeColor.LIGHT_BLUE).requiresCorrectToolForDrops().needsPickaxe()));
        public static final BlockBlockItemHolder<Block, BlockItem> BLOCK_OF_PYRE_NUCLEI = registerBlock("block_of_pyre_nuclei", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.GENERIC_STORAGE_BLOCK(SoundType.COPPER_BULB, DyeColor.YELLOW).requiresCorrectToolForDrops().needsPickaxe().noOcclusion().lightLevel(b -> 8)));

        public static final BlockBlockItemHolder<Block, BlockItem> BLOCK_OF_HEX_ASH = registerBlock("block_of_hex_ash", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.GENERIC_STORAGE_BLOCK(SoundType.WOOL, DyeColor.PURPLE).needsHoe()));
        public static final BlockBlockItemHolder<Block, BlockItem> BLOCK_OF_LIVING_FLESH = registerBlock("block_of_living_flesh", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.GENERIC_STORAGE_BLOCK(SoundType.CORAL_BLOCK, DyeColor.RED).needsShovel()));
        public static final BlockBlockItemHolder<Block, BlockItem> BLOCK_OF_ALCHEMICAL_CALX = registerBlock("block_of_alchemical_calx", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.GENERIC_STORAGE_BLOCK(SoundType.CALCITE, DyeColor.YELLOW).requiresCorrectToolForDrops().needsPickaxe()));
        public static final BlockBlockItemHolder<Block, BlockItem> BLOCK_OF_ARCANE_CHARCOAL = registerBlock("block_of_arcane_charcoal", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.ARCANE_CHARCOAL_BLOCK()));

        public static final BlockBlockItemHolder<Block, BlockItem> BLOCK_OF_EBONY = registerBlock("block_of_ebony", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.EBONY_BLOCK()));
        public static final BlockBlockItemHolder<Block, BlockItem> CRATE_OF_WITCHHAZEL = registerBlock("crate_of_witchhazel", () -> new Block(MalumStorageBlockProperties.WITCHHAZEL_CRATE()));

        public static final BlockBlockItemHolder<Block, BlockItem> BLOCK_OF_NULL_SLATE = registerBlock("block_of_null_slate", () -> new Block(MalumStorageBlockProperties.SOULSTONE_BLOCK(false)));
        public static final BlockBlockItemHolder<Block, BlockItem> BLOCK_OF_VOID_SALTS = registerBlock("block_of_void_salts", () -> new Block(MalumStorageBlockProperties.GENERIC_STORAGE_BLOCK(SoundType.WOOL, DyeColor.PURPLE).needsHoe()));
        public static final BlockBlockItemHolder<Block, BlockItem> BLOCK_OF_MNEMONIC_FRAGMENT = registerBlock("block_of_mnemonic_fragment", () -> new Block(MalumStorageBlockProperties.BRILLIANCE_BLOCK(false)));
        public static final BlockBlockItemHolder<Block, BlockItem> BLOCK_OF_AURIC_EMBERS = registerBlock("block_of_auric_embers", () -> new Block(MalumStorageBlockProperties.GENERIC_STORAGE_BLOCK(MalumBlockSoundEvents.STRANGE_CRYSTAL, DyeColor.YELLOW).requiresCorrectToolForDrops().needsPickaxe().noOcclusion().lightLevel(b -> 12)));
        public static final BlockBlockItemHolder<Block, BlockItem> BLOCK_OF_MALIGNANT_LEAD = registerBlock("block_of_malignant_lead", () -> new Block(MalumStorageBlockProperties.MALIGNANT_LEAD_BLOCK()));

        public static final BlockBlockItemHolder<Block, BlockItem> BLOCK_OF_SOUL_STAINED_STEEL = registerBlock("block_of_soul_stained_steel", () -> new Block(MalumStorageBlockProperties.SOUL_STAINED_STEEL_BLOCK()));
        public static final BlockBlockItemHolder<Block, BlockItem> BLOCK_OF_HALLOWED_GOLD = registerBlock("block_of_hallowed_gold", () -> new Block(MalumStorageBlockProperties.HALLOWED_GOLD()));
        public static final BlockBlockItemHolder<Block, BlockItem> BLOCK_OF_MALIGNANT_PEWTER = registerBlock("block_of_malignant_pewter", () -> new Block(MalumStorageBlockProperties.MALIGNANT_PEWTER_BLOCK()));

    }

    public static class Materials {

        public static final BlockBlockItemHolder<Block, BlockItem> DEEPSLATE_SOULSTONE_ORE = registerBlock("deepslate_soulstone_ore", () -> new DropExperienceBlock(UniformInt.of(14, 18), MalumOreBlockProperties.SOULSTONE_ORE(true)));
        public static final BlockBlockItemHolder<Block, BlockItem> SOULSTONE_ORE = registerBlock("soulstone_ore", () -> new DropExperienceBlock(UniformInt.of(14, 18), MalumOreBlockProperties.SOULSTONE_ORE(false)));
        public static final BlockBlockItemHolder<Block, BlockItem> BRILLIANT_DEEPSLATE = registerBlock("brilliant_deepslate", () -> new DropExperienceBlock(UniformInt.of(16, 26), MalumOreBlockProperties.BRILLIANCE_ORE(true).setCutoutRenderType()));
        public static final BlockBlockItemHolder<Block, BlockItem> BRILLIANT_STONE = registerBlock("brilliant_stone", () -> new DropExperienceBlock(UniformInt.of(14, 18), MalumOreBlockProperties.BRILLIANCE_ORE(false).setCutoutRenderType()));
        public static final BlockBlockItemHolder<Block, BlockItem> DEEPSLATE_QUARTZ_ORE = registerBlock("deepslate_quartz_ore", () -> new DropExperienceBlock(UniformInt.of(2, 5), MalumOreBlockProperties.NATURAL_QUARTZ_ORE(true).setCutoutRenderType()));
        public static final BlockBlockItemHolder<Block, BlockItem> NATURAL_QUARTZ_ORE = registerBlock("natural_quartz_ore", () -> new DropExperienceBlock(UniformInt.of(1, 4), MalumOreBlockProperties.NATURAL_QUARTZ_ORE(false).setCutoutRenderType()));
        public static final BlockBlockItemHolder<Block, BlockItem> CTHONIC_GOLD_ORE = registerBlock("cthonic_gold_ore", () -> new DropExperienceBlock(UniformInt.of(10, 100), MalumOreBlockProperties.CTHONIC_GOLD_ORE()));
        public static final BlockBlockItemHolder<Block, BlockItem> BLAZING_QUARTZ_ORE = registerBlock("blazing_quartz_ore", () -> new DropExperienceBlock(UniformInt.of(4, 7), MalumOreBlockProperties.BLAZING_QUARTZ_ORE().setCutoutRenderType().lightLevel((b) -> 6)));

        public static final DeferredItem<Item> RAW_SOULSTONE = register("raw_soulstone", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);
        public static final DeferredItem<Item> CRUSHED_SOULSTONE = register("crushed_soulstone", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);
        public static final DeferredItem<Item> REFINED_SOULSTONE = register("refined_soulstone", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);

        public static final DeferredItem<Item> RAW_BRILLIANCE = register("raw_brilliance", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);
        public static final DeferredItem<Item> CRUSHED_BRILLIANCE = register("crushed_brilliance", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);
        public static final DeferredItem<Item> REFINED_BRILLIANCE = register("refined_brilliance", MalumItemProperties::DEFAULT_PROPERTIES, (p) -> new BrillianceChunkItem(p.food((new FoodProperties.Builder()).fast().alwaysEdible().build())));

        public static final BlockBlockItemHolder<Block, BlockItem> NATURAL_QUARTZ = MalumBlocks.registerItemNameBlock("natural_quartz_cluster", "natural_quartz", () -> new AmethystClusterBlock(6, 3, NATURAL_QUARTZ_CLUSTER().setCutoutRenderType()));

        public static final DeferredItem<Item> BLAZING_QUARTZ = register("blazing_quartz", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);


        public static final DeferredItem<Item> CTHONIC_GOLD = register("cthonic_gold", () -> MalumItemProperties.DEFAULT_PROPERTIES().rarity(UNCOMMON), Item::new);
        public static final BlockBlockItemHolder<Block, BlockItem> CTHONIC_GOLD_FRAGMENT = MalumBlocks.registerItemNameBlock("cthonic_gold_cluster", "cthonic_gold_fragment", () -> new AmethystClusterBlock(4, 3, CTHONIC_GOLD_CLUSTER().setCutoutRenderType()));

        public static final DeferredItem<Item> ROTTING_ESSENCE = register("rotting_essence", () -> MalumItemProperties.DEFAULT_PROPERTIES().food(MalumFoodProperties.ROTTING_ESSENCE), Item::new);
        public static final DeferredItem<Item> GRIM_TALC = register("grim_talc", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);
        public static final DeferredItem<Item> EERIE_WEAVE = register("eerie_weave", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);
        public static final DeferredItem<Item> WARP_FLUX = register("warp_flux", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);

        public static final DeferredItem<Item> WIND_NUCLEUS = register("wind_nucleus", MalumItemProperties::DEFAULT_PROPERTIES, WindNucleusItem::new);
        public static final DeferredItem<Item> PYRE_NUCLEUS = register("pyre_nucleus", MalumItemProperties::DEFAULT_PROPERTIES, PyreNucleusItem::new);

        public static final DeferredItem<Item> HEX_ASH = register("hex_ash", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);
        public static final DeferredItem<Item> LIVING_FLESH = register("living_flesh", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);
        public static final DeferredItem<Item> ALCHEMICAL_CALX = register("alchemical_calx", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);
        public static final DeferredItem<Item> ARCANE_CHARCOAL = register("arcane_charcoal", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);

        public static final DeferredBlock<Block> EBONY_SAPLING = MalumBlocks.registerBlockNoItem("ebony_sapling", () -> new EbonySaplingBlock(MalumFloraBlockProperties.EBONY_SAPLING()));
        public static final BlockBlockItemHolder<Block, BlockItem> EBONY_STALK = registerBlock("ebony", () -> new EbonyStalkBlock(MalumFloraBlockProperties.EBONY()));
        public static final DeferredItem<Item> EBONY = register("ebony", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);

        public static final BlockBlockItemHolder<Block, BlockItem> WILD_WITCHHAZEL = registerBlock("wild_witchhazel", () -> new WildWitchhazelPlantBlock(MalumFloraBlockProperties.WILD_WITCHHAZEL()));
        public static final BlockBlockItemHolder<Block, BlockItem> WITCHHAZEL = registerBlock("witchhazel", () -> new WitchhazelCropBlock(MalumFloraBlockProperties.WITCHHAZEL_CROP()));

        public static final DeferredItem<Item> RUNIC_SAP_BOTTLE = register("runic_sap_bottle", MalumItemProperties::DEFAULT_PROPERTIES, (p) -> new BottledDrinkItem(MalumItemProperties.DEFAULT_PROPERTIES().food(MalumFoodProperties.RUNIC_SAP)));
        public static final DeferredItem<Item> RUNIC_SAPBALL = register("runic_sapball", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);

        public static final DeferredItem<Item> CURSED_SAP_BOTTLE = register("cursed_sap_bottle", MalumItemProperties::DEFAULT_PROPERTIES, (p) -> new BottledDrinkItem(MalumItemProperties.DEFAULT_PROPERTIES().food(MalumFoodProperties.CURSED_SAP)));
        public static final DeferredItem<Item> CURSED_SAPBALL = register("cursed_sapball", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);

        public static final DeferredItem<Item> NULL_SLATE = register("null_slate", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);
        public static final DeferredItem<Item> VOID_SALTS = register("void_salts", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);
        public static final DeferredItem<Item> MNEMONIC_FRAGMENT = register("mnemonic_fragment", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);
        public static final DeferredItem<Item> AURIC_EMBERS = register("auric_embers", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);
        public static final DeferredItem<Item> MALIGNANT_LEAD = register("malignant_lead", () -> MalumItemProperties.DEFAULT_PROPERTIES().rarity(RARE), Item::new);

        public static final DeferredItem<Item> SOUL_STAINED_STEEL_INGOT = register("soul_stained_steel_ingot", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);
        public static final DeferredItem<Item> SOUL_STAINED_STEEL_PLATING = register("soul_stained_steel_plating", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);
        public static final DeferredItem<Item> SOUL_STAINED_STEEL_NUGGET = register("soul_stained_steel_nugget", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);

        public static final DeferredItem<Item> HALLOWED_GOLD_INGOT = register("hallowed_gold_ingot", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);
        public static final DeferredItem<Item> HALLOWED_GOLD_INLAY = register("hallowed_gold_inlay", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);
        public static final DeferredItem<Item> HALLOWED_GOLD_NUGGET = register("hallowed_gold_nugget", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);

        public static final DeferredItem<Item> MALIGNANT_PEWTER_INGOT = register("malignant_pewter_ingot", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);
        public static final DeferredItem<Item> MALIGNANT_PEWTER_PLATING = register("malignant_pewter_plating", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);
        public static final DeferredItem<Item> MALIGNANT_PEWTER_NUGGET = register("malignant_pewter_nugget", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);

        public static final DeferredItem<Item> SOULWOVEN_SILK = register("soulwoven_silk", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);
        public static final DeferredItem<Item> PARACAUSAL_FLAME = register("paracausal_flame", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);
        public static final DeferredItem<Item> CONVOLUTED_LENS = register("convoluted_lens", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);
        public static final DeferredItem<Item> MIMICRY_RELAY = register("mimicry_relay", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);
        public static final DeferredItem<Item> IMITATION_FLESH = register("imitation_flesh", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);
        public static final DeferredItem<Item> IMITATION_HEART = register("imitation_heart", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);

        public static final DeferredItem<Item> POPPET = register("poppet", MalumItemProperties::HIDDEN_PROPERTIES, Item::new);

        public static final DeferredItem<Item> ANOMALOUS_DESIGN = register("anomalous_design", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);
        public static final DeferredItem<Item> COMPLETE_DESIGN = register("complete_design", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);
        public static final DeferredItem<Item> FUSED_CONSCIOUSNESS = register("fused_consciousness", MalumItemProperties::DEFAULT_PROPERTIES, (p) -> new FusedConsciousnessItem(p.rarity(RARE)));
    }

    public static class BlockSets {

        public static final BlockBlockItemHolder<Block, BlockItem> SACRED_SPIRITED_GLASS = MalumBlocks.registerBlock("sacred_spirited_glass", () -> new SpiritedGlassBlock(SPIRITED_GLASS()));
        public static final BlockBlockItemHolder<Block, BlockItem> WICKED_SPIRITED_GLASS = MalumBlocks.registerBlock("wicked_spirited_glass", () -> new SpiritedGlassBlock(SPIRITED_GLASS()));
        public static final BlockBlockItemHolder<Block, BlockItem> ARCANE_SPIRITED_GLASS = MalumBlocks.registerBlock("arcane_spirited_glass", () -> new SpiritedGlassBlock(SPIRITED_GLASS()));
        public static final BlockBlockItemHolder<Block, BlockItem> ELDRITCH_SPIRITED_GLASS = MalumBlocks.registerBlock("eldritch_spirited_glass", () -> new SpiritedGlassBlock(SPIRITED_GLASS()));
        public static final BlockBlockItemHolder<Block, BlockItem> AERIAL_SPIRITED_GLASS = MalumBlocks.registerBlock("aerial_spirited_glass", () -> new SpiritedGlassBlock(SPIRITED_GLASS()));
        public static final BlockBlockItemHolder<Block, BlockItem> AQUEOUS_SPIRITED_GLASS = MalumBlocks.registerBlock("aqueous_spirited_glass", () -> new SpiritedGlassBlock(SPIRITED_GLASS()));
        public static final BlockBlockItemHolder<Block, BlockItem> EARTHEN_SPIRITED_GLASS = MalumBlocks.registerBlock("earthen_spirited_glass", () -> new SpiritedGlassBlock(SPIRITED_GLASS()));
        public static final BlockBlockItemHolder<Block, BlockItem> INFERNAL_SPIRITED_GLASS = MalumBlocks.registerBlock("infernal_spirited_glass", () -> new SpiritedGlassBlock(SPIRITED_GLASS()));
        public static final BlockBlockItemHolder<Block, BlockItem> NULL_SPIRITED_GLASS = MalumBlocks.registerBlock("null_spirited_glass", () -> new SpiritedGlassBlock(SPIRITED_GLASS()));

        public static final BlockBlockItemHolder<Block, BlockItem> SACRED_VARNISHED_TERRACOTTA = MalumBlocks.registerBlock("sacred_varnished_terracotta", () -> new VarnishedTerracottaBlock(VARNISHED_TERRACOTTA(DyeColor.RED)));
        public static final BlockBlockItemHolder<Block, BlockItem> WICKED_VARNISHED_TERRACOTTA = MalumBlocks.registerBlock("wicked_varnished_terracotta", () -> new VarnishedTerracottaBlock(VARNISHED_TERRACOTTA(DyeColor.PURPLE)));
        public static final BlockBlockItemHolder<Block, BlockItem> ARCANE_VARNISHED_TERRACOTTA = MalumBlocks.registerBlock("arcane_varnished_terracotta", () -> new VarnishedTerracottaBlock(VARNISHED_TERRACOTTA(DyeColor.PINK)));
        public static final BlockBlockItemHolder<Block, BlockItem> ELDRITCH_VARNISHED_TERRACOTTA = MalumBlocks.registerBlock("eldritch_varnished_terracotta", () -> new VarnishedTerracottaBlock(VARNISHED_TERRACOTTA(DyeColor.MAGENTA)));
        public static final BlockBlockItemHolder<Block, BlockItem> AERIAL_VARNISHED_TERRACOTTA = MalumBlocks.registerBlock("aerial_varnished_terracotta", () -> new VarnishedTerracottaBlock(VARNISHED_TERRACOTTA(DyeColor.LIGHT_BLUE)));
        public static final BlockBlockItemHolder<Block, BlockItem> AQUEOUS_VARNISHED_TERRACOTTA = MalumBlocks.registerBlock("aqueous_varnished_terracotta", () -> new VarnishedTerracottaBlock(VARNISHED_TERRACOTTA(DyeColor.BLUE)));
        public static final BlockBlockItemHolder<Block, BlockItem> EARTHEN_VARNISHED_TERRACOTTA = MalumBlocks.registerBlock("earthen_varnished_terracotta", () -> new VarnishedTerracottaBlock(VARNISHED_TERRACOTTA(DyeColor.GREEN)));
        public static final BlockBlockItemHolder<Block, BlockItem> INFERNAL_VARNISHED_TERRACOTTA = MalumBlocks.registerBlock("infernal_varnished_terracotta", () -> new VarnishedTerracottaBlock(VARNISHED_TERRACOTTA(DyeColor.YELLOW)));
        public static final BlockBlockItemHolder<Block, BlockItem> NULL_VARNISHED_TERRACOTTA = MalumBlocks.registerBlock("null_varnished_terracotta", () -> new VarnishedTerracottaBlock(VARNISHED_TERRACOTTA(DyeColor.BLACK)));

        public static final BlockBlockItemHolder<Block, BlockItem> SOULWOVEN_BANNER = MalumBlocks.registerBlock("soulwoven_banner", () -> new SoulwovenBannerBlock(SOULWOVEN_BANNER()).setBlockEntity(MalumBlockEntities.SOULWOVEN_BANNER), SoulwovenBannerBlockItem::new);

        public static final BlockBlockItemHolder<Block, BlockItem> ETHER = MalumBlocks.registerBlock("ether", () -> new EtherBlock<>(ETHER()).setBlockEntity(MalumBlockEntities.ETHER));
        public static final BlockBlockItemHolder<Block, BlockItem> IRIDESCENT_ETHER = MalumBlocks.registerBlock("iridescent_ether", () -> new EtherBlock<>(ETHER()).setBlockEntity(MalumBlockEntities.ETHER));

        public static final BlockBlockItemHolder<Block, BlockItem> ETHER_CANDLE = MalumBlocks.registerBlock("ether_candle", () -> new EtherCandleBlock<>(ETHER_CANDLE()).setBlockEntity(MalumBlockEntities.ETHER_CANDLE));
        public static final BlockBlockItemHolder<Block, BlockItem> IRIDESCENT_ETHER_CANDLE = MalumBlocks.registerBlock("iridescent_ether_candle", () -> new EtherCandleBlock<>(ETHER_CANDLE()).setBlockEntity(MalumBlockEntities.ETHER_CANDLE));

        public static final BlockBlockItemHolder<Block, BlockItem> ETHER_TORCH = MalumBlocks.registerBlock("ether_torch", () -> new EtherTorchBlock<>(ETHER_TORCH()).setBlockEntity(MalumBlockEntities.ETHER_TORCH));
        public static final DeferredBlock<Block> WALL_ETHER_TORCH = MalumBlocks.registerBlockNoItem("wall_ether_torch", () -> new EtherWallTorchBlock<>(ETHER_TORCH().lootFrom(ETHER_TORCH)).setBlockEntity(MalumBlockEntities.ETHER_TORCH));
        public static final BlockBlockItemHolder<Block, BlockItem> IRIDESCENT_ETHER_TORCH = MalumBlocks.registerBlock("iridescent_ether_torch", () -> new EtherTorchBlock<>(ETHER_TORCH()).setBlockEntity(MalumBlockEntities.ETHER_TORCH));
        public static final DeferredBlock<Block> IRIDESCENT_WALL_ETHER_TORCH = MalumBlocks.registerBlockNoItem("iridescent_wall_ether_torch", () -> new EtherWallTorchBlock<>(ETHER_TORCH().lootFrom(IRIDESCENT_ETHER_TORCH)).setBlockEntity(MalumBlockEntities.ETHER_TORCH));

        public static final RockBlockSet TAINTED_ROCK_SET = new RockBlockSet("tainted", MalumBlockProperties::TAINTED_ROCK, MalumBlockProperties::TAINTED_ROCK_BRICKS, MalumBlockProperties::CHISELED_TAINTED_ROCK, MalumBlockProperties::TAINTED_ETHER_BRAZIER, MalumBlockProperties::TAINTED_ETHER_CRESSET);

        public static final RockBlockSet TWISTED_ROCK_SET = new RockBlockSet("tainted", MalumBlockProperties::TAINTED_ROCK, MalumBlockProperties::TAINTED_ROCK_BRICKS, MalumBlockProperties::CHISELED_TAINTED_ROCK, MalumBlockProperties::TWISTED_ETHER_BRAZIER, MalumBlockProperties::TWISTED_ETHER_CRESSET);

    }

    public static class Blight {
        public static final BlockBlockItemHolder<Block, BlockItem> COLUMNAR_BLIGHT = MalumBlocks.registerBlock("columnar_blight", () -> new ColumnarBlightBlock(BLIGHTED_EARTH()));
        public static final BlockBlockItemHolder<Block, BlockItem> BLIGHTED_EARTH = MalumBlocks.registerBlock("blighted_earth", () -> new BlightedEarthBlock(BLIGHTED_EARTH()));

        public static final BlockBlockItemHolder<Block, BlockItem> BLIGHT = MalumBlocks.registerBlock("blight", () -> new BlightedCoverageBlock(BLIGHTED_COVERING()));
        public static final DeferredBlock<Block> CLINGING_BLIGHT = MalumBlocks.registerBlockNoItem("clinging_blight", () -> new CreepingBlightBlock(CLINGING_BLIGHT()));
        public static final BlockBlockItemHolder<Block, BlockItem> BLIGHTED_GUNK = MalumBlocks.registerBlock("blighted_growth", "blighted_gunk", () -> new BlightedPlantBlock(BLIGHTED_PLANTS()), BlightedGunkItem::new);

        public static final BlockBlockItemHolder<Block, BlockItem> BLIGHTPEARL = MalumBlocks.registerBlock("blightpearl", () -> new BlightedPlantBlock(BLIGHTED_PLANTS()));
        public static final BlockBlockItemHolder<Block, BlockItem> BLIGHTROOT = MalumBlocks.registerBlock("blightroot", () -> new BlightedPlantBlock(BLIGHTED_PLANTS()));

        public static final BlockBlockItemHolder<Block, BlockItem> SCARSTONE = MalumBlocks.registerBlock("scarstone", () -> new ScarstoneBlock(SCARSTONE()));
        public static final BlockBlockItemHolder<Block, BlockItem> STRANGE_CRYSTAL = MalumBlocks.registerBlock("strange_crystal", () -> new StrangeCrystalBlock(STRANGE_CRYSTAL()));
        public static final BlockBlockItemHolder<Block, BlockItem> LARGE_STRANGE_CRYSTAL = MalumBlocks.registerBlock("large_strange_crystal", () -> new LargeStrangeCrystalBlock(STRANGE_CRYSTAL()));
        public static final BlockBlockItemHolder<Block, BlockItem> STRANGEROOT = MalumBlocks.registerBlock("strangeroot", () -> new StrangeRootBlock(STRANGEROOT()));

    }

    public static class Totemancy {

        public static final DeferredItem<Item> TOTEMIC_STAFF = MalumItemProperties.register("totemic_staff", MalumItemProperties::GEAR_PROPERTIES, TinkeringToolItem::new);

        public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_TOTEM_BASE = MalumBlocks.registerBlock("runewood_totem_base", () -> new TotemBaseBlock<>(RUNEWOOD().addTag(IS_RITE_IMMUNE).noOcclusion(), false).setBlockEntity(MalumBlockEntities.TOTEM_BASE));
        public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_TOTEM_BASE = MalumBlocks.registerBlock("soulwood_totem_base", () -> new TotemBaseBlock<>(SOULWOOD().addTag(IS_RITE_IMMUNE).noOcclusion(), true).setBlockEntity(MalumBlockEntities.TOTEM_BASE));

        public static final BlockBlockItemHolder<Block, BlockItem> WAVEFORM_RUNEWOOD_TOTEM_BASE = MalumBlocks.registerBlock("waveform_runewood_totem_base", () -> new WaveformTotemBaseBlock<>(WAVEFORM_DIODE().addTag(IS_RITE_IMMUNE).noOcclusion(), false).setBlockEntity(MalumBlockEntities.WAVEFORM_TOTEM_BASE));
        public static final BlockBlockItemHolder<Block, BlockItem> WAVEFORM_SOULWOOD_TOTEM_BASE = MalumBlocks.registerBlock("waveform_soulwood_totem_base", () -> new WaveformTotemBaseBlock<>(WAVEFORM_DIODE().addTag(IS_RITE_IMMUNE).noOcclusion(), true).setBlockEntity(MalumBlockEntities.WAVEFORM_TOTEM_BASE));

        public static final DeferredBlock<Block> RUNEWOOD_TOTEM_POLE = MalumBlocks.registerBlockNoItem("runewood_totem_pole", () -> new TotemPoleBlock<>(RUNEWOOD().addTag(IS_RITE_IMMUNE).noOcclusion(), MalumBlocks.RUNEWOOD_LOG, false).setBlockEntity(MalumBlockEntities.TOTEM_POLE));
        public static final DeferredBlock<Block> SOULWOOD_TOTEM_POLE = MalumBlocks.registerBlockNoItem("soulwood_totem_pole", () -> new TotemPoleBlock<>(SOULWOOD().addTag(IS_RITE_IMMUNE).noOcclusion(), MalumBlocks.SOULWOOD_LOG, true).setBlockEntity(MalumBlockEntities.TOTEM_POLE));

        public static final BlockBlockItemHolder<Block, BlockItem> RITE_ANCHOR = MalumBlocks.registerBlock("rite_anchor", () -> new RiteAnchorBlock(TAINTED_ROCK_TOTEMANCY()).setBlockEntity(MalumBlockEntities.RITE_ANCHOR));
        public static final BlockBlockItemHolder<Block, BlockItem> RITE_UNWEAVER = MalumBlocks.registerBlock("rite_unweaver", () -> new RiteUnweaverBlock(TWISTED_ROCK_TOTEMANCY()).setBlockEntity(MalumBlockEntities.RITE_UNWEAVER));
        public static final BlockBlockItemHolder<Block, BlockItem> RITE_SPREADER = MalumBlocks.registerBlock("rite_spreader", () -> new RiteSpreaderBlock(TAINTED_ROCK_TOTEMANCY()).setBlockEntity(MalumBlockEntities.RITE_SPREADER));
        public static final BlockBlockItemHolder<Block, BlockItem> RITE_CHANNEL = MalumBlocks.registerBlock("rite_channel", () -> new RiteChannelBlock(TAINTED_ROCK_TOTEMANCY()).setBlockEntity(MalumBlockEntities.RITE_CHANNEL));

    }

    public static class Artifice {

        public static final DeferredItem<Item> ARTIFICERS_CLAW = MalumItemProperties.register("artificers_claw", MalumItemProperties::GEAR_PROPERTIES, TinkeringToolItem::new);
        public static final DeferredItem<Item> TUNING_FORK = MalumItemProperties.register("tuning_fork", MalumItemProperties::GEAR_PROPERTIES, TinkeringToolItem::new);

        public static final BlockBlockItemHolder<Block, MultiBlockItem> SPIRIT_CRUCIBLE = MalumBlocks.registerMultiBlock("spirit_crucible", () -> new SpiritCrucibleCoreBlock<>(ARCANE_ROCK_ARTIFICE()).setBlockEntity(MalumBlockEntities.SPIRIT_CRUCIBLE), SpiritCrucibleCoreBlockEntity.STRUCTURE);
        public static final DeferredHolder<Block, SpiritCrucibleComponentBlock> SPIRIT_CRUCIBLE_COMPONENT = MalumBlocks.registerBlockNoItem("spirit_crucible_component", () -> new SpiritCrucibleComponentBlock(ARCANE_ROCK_ARTIFICE().lootFrom(SPIRIT_CRUCIBLE)));

        public static final BlockBlockItemHolder<Block, MultiBlockItem> SPIRIT_CATALYZER = MalumBlocks.registerMultiBlock("spirit_catalyzer", () -> new SpiritCatalyzerCoreBlock<>(ARCANE_ROCK_ARTIFICE()).setBlockEntity(MalumBlockEntities.SPIRIT_CATALYZER), SpiritCatalyzerCoreBlockEntity.STRUCTURE);
        public static final DeferredHolder<Block, SpiritCatalyzerComponentBlock> SPIRIT_CATALYZER_COMPONENT = MalumBlocks.registerBlockNoItem("spirit_catalyzer_component", () -> new SpiritCatalyzerComponentBlock(ARCANE_ROCK_ARTIFICE().lootFrom(SPIRIT_CATALYZER)));

        public static final BlockBlockItemHolder<Block, MultiBlockItem> REPAIR_PYLON = MalumBlocks.registerMultiBlock("repair_pylon", () -> new RepairPylonCoreBlock<>(ARCANE_ROCK_ARTIFICE()).setBlockEntity(MalumBlockEntities.REPAIR_PYLON), RepairPylonCoreBlockEntity.STRUCTURE);
        public static final DeferredHolder<Block, RepairPylonComponentBlock> REPAIR_PYLON_COMPONENT = MalumBlocks.registerBlockNoItem("repair_pylon_component", () -> new RepairPylonComponentBlock(ARCANE_ROCK_ARTIFICE().lootFrom(REPAIR_PYLON)));

        public static final DeferredItem<Item> ALCHEMICAL_IMPETUS = MalumItemProperties.register("alchemical_impetus", MalumItemProperties::IMPETUS_PROPERTIES, ImpetusItem::new);
        public static final DeferredItem<Item> FRACTURED_ALCHEMICAL_IMPETUS = MalumItemProperties.register("fractured_alchemical_impetus", MalumItemProperties::IMPETUS_PROPERTIES, FracturedImpetusItem::new);

        public static final DeferredItem<Item> ZEPHYR_IMPETUS = MalumItemProperties.register("zephyr_impetus", MalumItemProperties::IMPETUS_PROPERTIES, ImpetusItem::new);
        public static final DeferredItem<Item> FRACTURED_ZEPHYR_IMPETUS = MalumItemProperties.register("fractured_zephyr_impetus", MalumItemProperties::IMPETUS_PROPERTIES, FracturedImpetusItem::new);

        public static final DeferredItem<Item> IFRIT_IMPETUS = MalumItemProperties.register("ifrit_impetus", MalumItemProperties::IMPETUS_PROPERTIES, ImpetusItem::new);
        public static final DeferredItem<Item> FRACTURED_IFRIT_IMPETUS = MalumItemProperties.register("fractured_ifrit_impetus", MalumItemProperties::IMPETUS_PROPERTIES, FracturedImpetusItem::new);

        public static final MetallicsItemRegistryBundle IRON_METALLICS = new MetallicsItemRegistryBundle("iron");
        public static final MetallicsItemRegistryBundle COPPER_METALLICS = new MetallicsItemRegistryBundle("copper");
        public static final MetallicsItemRegistryBundle GOLD_METALLICS = new MetallicsItemRegistryBundle("gold");
        public static final MetallicsItemRegistryBundle ZINC_METALLICS = new MetallicsItemRegistryBundle("zinc");
        public static final MetallicsItemRegistryBundle LEAD_METALLICS = new MetallicsItemRegistryBundle("lead");
        public static final MetallicsItemRegistryBundle SILVER_METALLICS = new MetallicsItemRegistryBundle("silver");
        public static final MetallicsItemRegistryBundle ALUMINIUM_METALLICS = new MetallicsItemRegistryBundle("aluminium");
        public static final MetallicsItemRegistryBundle NICKEL_METALLICS = new MetallicsItemRegistryBundle("nickel");

        public static final DeferredItem<Item> MENDING_DIFFUSER = MalumItemProperties.register("mending_diffuser", MalumItemProperties::DEFAULT_PROPERTIES, MendingDiffuserItem::new);
        public static final DeferredItem<Item> IMPURITY_STABILIZER = MalumItemProperties.register("impurity_stabilizer", MalumItemProperties::DEFAULT_PROPERTIES, ImpurityStabilizer::new);
        public static final DeferredItem<Item> SHIELDING_APPARATUS = MalumItemProperties.register("shielding_apparatus", MalumItemProperties::DEFAULT_PROPERTIES, ShieldingApparatusItem::new);
        public static final DeferredItem<Item> WARPING_ENGINE = MalumItemProperties.register("warping_engine", MalumItemProperties::DEFAULT_PROPERTIES, WarpingEngineItem::new);

        public static final DeferredItem<Item> ACCELERATING_INLAY = MalumItemProperties.register("accelerating_inlay", MalumItemProperties::DEFAULT_PROPERTIES, AcceleratingInlayItem::new);
        public static final DeferredItem<Item> PRISMATIC_FOCUS_LENS = MalumItemProperties.register("prismatic_focus_lens", MalumItemProperties::DEFAULT_PROPERTIES, PrismaticFocusLensItem::new);
        public static final DeferredItem<Item> BLAZING_DIODE = MalumItemProperties.register("blazing_diode", MalumItemProperties::DEFAULT_PROPERTIES, BlazingDiodeItem::new);
        public static final DeferredItem<Item> INTRICATE_ASSEMBLY = MalumItemProperties.register("intricate_assembly", MalumItemProperties::DEFAULT_PROPERTIES, IntricateAssemblyItem::new);

        public static final DeferredItem<Item> SYMPATHY_DRIVE = MalumItemProperties.register("sympathy_drive", MalumItemProperties::DEFAULT_PROPERTIES, SympathyDrive::new);
        public static final DeferredItem<Item> SUSPICIOUS_DEVICE = MalumItemProperties.register("suspicious_device", MalumItemProperties::DEFAULT_PROPERTIES, SuspiciousDeviceItem::new);
        public static final DeferredItem<Item> CAUSTIC_CATALYST = MalumItemProperties.register("caustic_catalyst", MalumItemProperties::DEFAULT_PROPERTIES, CausticCatalystItem::new);
        public static final DeferredItem<Item> RESONANCE_TUNER = MalumItemProperties.register("resonance_tuner", MalumItemProperties::DEFAULT_PROPERTIES, ResonanceTuner::new);

        public static final DeferredItem<Item> STELLAR_MECHANISM = MalumItemProperties.register("stellar_mechanism", MalumItemProperties::DEFAULT_PROPERTIES, StellarMechanismItem::new);


    }

    public static class Progression {

        public static final BlockBlockItemHolder<Block, BlockItem> SPIRIT_ALTAR = MalumBlocks.registerBlock("spirit_altar", () -> new SpiritAltarBlock<>(SPIRIT_ALTAR()).setBlockEntity(MalumBlockEntities.SPIRIT_ALTAR));
        public static final BlockBlockItemHolder<Block, BlockItem> SOUL_BRAZIER = MalumBlocks.registerBlock("soulbinding_brazier", () -> new SoulBrazierBlock<>(SOUL_BRAZIER()).setBlockEntity(MalumBlockEntities.SOUL_BRAZIER));

        public static final BlockBlockItemHolder<Block, BlockItem> SPIRIT_JAR = MalumBlocks.registerBlock("spirit_jar", () -> new SpiritJarBlock<>(SPIRIT_JAR().setCutoutRenderType().noOcclusion()).setBlockEntity(MalumBlockEntities.SPIRIT_JAR));

        public static final BlockBlockItemHolder<Block, BlockItem> WEAVERS_WORKBENCH = MalumBlocks.registerBlock("weavers_workbench", () -> new WeaversWorkbenchBlock<>(RUNEWOOD().setCutoutRenderType().noOcclusion()).setBlockEntity(MalumBlockEntities.WEAVERS_WORKBENCH));
        public static final BlockBlockItemHolder<Block, BlockItem> RUNIC_WORKBENCH = MalumBlocks.registerBlock("runic_workbench", () -> new RunicWorkbenchBlock<>(RUNEWOOD().setCutoutRenderType().noOcclusion()).setBlockEntity(MalumBlockEntities.RUNIC_WORKBENCH));

        public static final BlockBlockItemHolder<Block, BlockItem> RITUAL_PLINTH = MalumBlocks.registerBlock("ritual_plinth", () -> new RitualPlinthBlock<>(SOULWOOD().setCutoutRenderType().noOcclusion()).setBlockEntity(MalumBlockEntities.RITUAL_PLINTH));

        public static final BlockBlockItemHolder<Block, MultiBlockItem> RUNEWOOD_OBELISK = MalumBlocks.registerMultiBlock("runewood_obelisk", () -> new RunewoodObeliskCoreBlock(RUNEWOOD().setCutoutRenderType().noOcclusion()), RunewoodObeliskBlockEntity.STRUCTURE);
        public static final DeferredHolder<Block, ObeliskComponentBlock> RUNEWOOD_OBELISK_COMPONENT = MalumBlocks.registerBlockNoItem("runewood_obelisk_component", () -> new ObeliskComponentBlock(RUNEWOOD().setCutoutRenderType().lootFrom(RUNEWOOD_OBELISK).noOcclusion()));

        public static final BlockBlockItemHolder<Block, MultiBlockItem> BRILLIANT_OBELISK = MalumBlocks.registerMultiBlock("brilliant_obelisk", () -> new BrillianceObeliskCoreBlock(RUNEWOOD().setCutoutRenderType().noOcclusion()), BrilliantObeliskBlockEntity.STRUCTURE);
        public static final DeferredHolder<Block, ObeliskComponentBlock> BRILLIANT_OBELISK_COMPONENT = MalumBlocks.registerBlockNoItem("brilliant_obelisk_component", () -> new ObeliskComponentBlock(RUNEWOOD().setCutoutRenderType().lootFrom(BRILLIANT_OBELISK).noOcclusion()));

        public static final BlockBlockItemHolder<Block, MultiBlockItem> ARCANA_PYLON = MalumBlocks.registerMultiBlock("arcana_pylon", () -> new ArcanaPylonCoreBlock(SOULWOOD().setCutoutRenderType().noOcclusion()), ArcanaPylonBlockEntity.STRUCTURE);
        public static final DeferredHolder<Block, ArcanaPylonComponentBlock> ARCANA_PYLON_COMPONENT = MalumBlocks.registerBlockNoItem("arcana_pylon_component", () -> new ArcanaPylonComponentBlock(SOULWOOD().setCutoutRenderType().lootFrom(ARCANA_PYLON).noOcclusion()));

        public static final BlockBlockItemHolder<Block, BlockItem> WAVECHARGER = MalumBlocks.registerBlock("wavecharger", () -> new WaveChargerBlock(WAVEFORM_DIODE()).setBlockEntity(MalumBlockEntities.WAVECHARGER));
        public static final BlockBlockItemHolder<Block, BlockItem> WAVEBANKER = MalumBlocks.registerBlock("wavebanker", () -> new WaveBankerBlock(WAVEFORM_DIODE()).setBlockEntity(MalumBlockEntities.WAVEBANKER));
        public static final BlockBlockItemHolder<Block, BlockItem> WAVEMAKER = MalumBlocks.registerBlock("wavemaker", () -> new WaveMakerBlock(WAVEFORM_DIODE()).setBlockEntity(MalumBlockEntities.WAVEMAKER));
        public static final BlockBlockItemHolder<Block, BlockItem> WAVEBREAKER = MalumBlocks.registerBlock("wavebreaker", () -> new WaveBreakerBlock(WAVEFORM_DIODE()).setBlockEntity(MalumBlockEntities.WAVEBREAKER));

        public static final BlockBlockItemHolder<Block, BlockItem> GUST_IGNITER = MalumBlocks.registerBlock("gust_igniter", () -> new GustIgniterBlock(GUST_TECH()).setBlockEntity(MalumBlockEntities.GUST_IGNITER));
        public static final BlockBlockItemHolder<Block, BlockItem> WIND_TUNNEL = MalumBlocks.registerBlock("wind_tunnel", () -> new WindTunnelBlock(GUST_TECH()).setBlockEntity(MalumBlockEntities.WIND_TUNNEL));

        public static final DeferredBlock<Block> SPIRIT_MOTE = MalumBlocks.registerBlockNoItem("spirit_mote", () -> new ManaMoteBlock(MalumStorageBlockProperties.MANA_MOTE_BLOCK()).setBlockEntity(MalumBlockEntities.MANA_MOTE));

        //Weeping Well
        public static final BlockBlockItemHolder<Block, BlockItem> VOID_CONDUIT = MalumBlocks.registerBlock("void_conduit", () -> new VoidConduitBlock<>(PRIMORDIAL_SOUP()).setBlockEntity(MalumBlockEntities.VOID_CONDUIT));
        public static final BlockBlockItemHolder<Block, BlockItem> PRIMORDIAL_SOUP = MalumBlocks.registerBlock("primordial_soup", () -> new PrimordialSoupBlock(PRIMORDIAL_SOUP()));

        public static final BlockBlockItemHolder<Block, BlockItem> VOID_DEPOT = MalumBlocks.registerBlock("void_depot", () -> new VoidDepotBlock<>(WEEPING_WELL()).setBlockEntity(MalumBlockEntities.VOID_DEPOT));

        public static final BlockBlockItemHolder<Block, BlockItem> WEEPING_WELL_CENTER = MalumBlocks.registerBlock("weeping_well_center", () -> new WeepingWellLayeredBlock(WEEPING_WELL()));
        public static final BlockBlockItemHolder<Block, BlockItem> WEEPING_WELL_SIDE = MalumBlocks.registerBlock("weeping_well_side", () -> new WeepingWellLayeredBlock(WEEPING_WELL()));
        public static final BlockBlockItemHolder<Block, BlockItem> WEEPING_WELL_SIDE_MIRROR = MalumBlocks.registerBlock("weeping_well_side_mirror", () -> new WeepingWellLayeredBlock(WEEPING_WELL()));
        public static final BlockBlockItemHolder<Block, BlockItem> WEEPING_WELL_CORNER = MalumBlocks.registerBlock("weeping_well_corner", () -> new WeepingWellLayeredBlock(WEEPING_WELL()));
        public static final BlockBlockItemHolder<Block, BlockItem> WEEPING_WELL_FLAGSTONE = MalumBlocks.registerBlock("weeping_well_flagstone", () -> new WeepingWellBlock(WEEPING_WELL()));
        public static final BlockBlockItemHolder<Block, BlockItem> WEEPING_WELL_COLUMN_BASE = MalumBlocks.registerBlock("weeping_well_column_base", () -> new WeepingWellDirectionalBlock(WEEPING_WELL()));
        public static final BlockBlockItemHolder<Block, BlockItem> WEEPING_WELL_COLUMN = MalumBlocks.registerBlock("weeping_well_column", () -> new WeepingWellDirectionalBlock(WEEPING_WELL()));
        public static final BlockBlockItemHolder<Block, BlockItem> WEEPING_WELL_COLUMN_CAP = MalumBlocks.registerBlock("weeping_well_column_cap", () -> new WeepingWellDirectionalBlock(WEEPING_WELL()));
    }

    public static class WeepingWell {
        public static final BlockBlockItemHolder<Block, BlockItem> VOID_CONDUIT = MalumBlocks.registerBlock("void_conduit", () -> new VoidConduitBlock<>(PRIMORDIAL_SOUP()).setBlockEntity(MalumBlockEntities.VOID_CONDUIT));
        public static final BlockBlockItemHolder<Block, BlockItem> PRIMORDIAL_SOUP = MalumBlocks.registerBlock("primordial_soup", () -> new PrimordialSoupBlock(PRIMORDIAL_SOUP()));

        public static final BlockBlockItemHolder<Block, BlockItem> VOID_DEPOT = MalumBlocks.registerBlock("void_depot", () -> new VoidDepotBlock<>(WEEPING_WELL()).setBlockEntity(MalumBlockEntities.VOID_DEPOT));

        public static final BlockBlockItemHolder<Block, BlockItem> WEEPING_WELL_CENTER = MalumBlocks.registerBlock("weeping_well_center", () -> new WeepingWellLayeredBlock(WEEPING_WELL()));
        public static final BlockBlockItemHolder<Block, BlockItem> WEEPING_WELL_SIDE = MalumBlocks.registerBlock("weeping_well_side", () -> new WeepingWellLayeredBlock(WEEPING_WELL()));
        public static final BlockBlockItemHolder<Block, BlockItem> WEEPING_WELL_SIDE_MIRROR = MalumBlocks.registerBlock("weeping_well_side_mirror", () -> new WeepingWellLayeredBlock(WEEPING_WELL()));
        public static final BlockBlockItemHolder<Block, BlockItem> WEEPING_WELL_CORNER = MalumBlocks.registerBlock("weeping_well_corner", () -> new WeepingWellLayeredBlock(WEEPING_WELL()));
        public static final BlockBlockItemHolder<Block, BlockItem> WEEPING_WELL_FLAGSTONE = MalumBlocks.registerBlock("weeping_well_flagstone", () -> new WeepingWellBlock(WEEPING_WELL()));
        public static final BlockBlockItemHolder<Block, BlockItem> WEEPING_WELL_COLUMN_BASE = MalumBlocks.registerBlock("weeping_well_column_base", () -> new WeepingWellDirectionalBlock(WEEPING_WELL()));
        public static final BlockBlockItemHolder<Block, BlockItem> WEEPING_WELL_COLUMN = MalumBlocks.registerBlock("weeping_well_column", () -> new WeepingWellDirectionalBlock(WEEPING_WELL()));
        public static final BlockBlockItemHolder<Block, BlockItem> WEEPING_WELL_COLUMN_CAP = MalumBlocks.registerBlock("weeping_well_column_cap", () -> new WeepingWellDirectionalBlock(WEEPING_WELL()));
    }

    public static class Gear {

        public static final DeferredItem<Item> LAMPLIGHTERS_TONGS = register("lamplighters_tongs", MalumItemProperties::GEAR_PROPERTIES, LamplightersTongsItem::new);
        public static final DeferredItem<Item> CATALYST_LOBBER = register("catalyst_lobber", MalumItemProperties::GEAR_PROPERTIES, (p) -> new CatalystLobberItem(p.durability(500), EthericNitrate::new));

        public static final DeferredItem<Item> SOULWOVEN_POUCH = register("soulwoven_pouch", MalumItemProperties::GEAR_PROPERTIES, SoulwovenPouchItem::new);
        public static final DeferredItem<Item> RAVENOUS_POUCH = register("ravenous_pouch", MalumItemProperties::GEAR_PROPERTIES, RavenousPouchItem::new);

        public static final DeferredItem<Item> CRUDE_SCYTHE = register("crude_scythe", MalumItemProperties::GEAR_PROPERTIES, (p) -> new MalumScytheItem(Tiers.IRON, 0, 0.1f, p.durability(500)));

        public static final DeferredItem<Item> SOUL_HUNTER_CLOAK = register("soul_hunter_cloak", MalumItemProperties::GEAR_PROPERTIES, (p) -> new SoulHunterArmorItem(ArmorItem.Type.HELMET, p));
        public static final DeferredItem<Item> SOUL_HUNTER_ROBE = register("soul_hunter_robe", MalumItemProperties::GEAR_PROPERTIES, (p) -> new SoulHunterArmorItem(ArmorItem.Type.CHESTPLATE, p));
        public static final DeferredItem<Item> SOUL_HUNTER_LEGGINGS = register("soul_hunter_leggings", MalumItemProperties::GEAR_PROPERTIES, (p) -> new SoulHunterArmorItem(ArmorItem.Type.LEGGINGS, p));
        public static final DeferredItem<Item> SOUL_HUNTER_BOOTS = register("soul_hunter_boots", MalumItemProperties::GEAR_PROPERTIES, (p) -> new SoulHunterArmorItem(ArmorItem.Type.BOOTS, p));

        public static final DeferredItem<Item> SOUL_STAINED_STEEL_HELMET = register("soul_stained_steel_helmet", MalumItemProperties::GEAR_PROPERTIES, (p) -> new SoulStainedSteelArmorItem(ArmorItem.Type.HELMET, p));
        public static final DeferredItem<Item> SOUL_STAINED_STEEL_CHESTPLATE = register("soul_stained_steel_chestplate", MalumItemProperties::GEAR_PROPERTIES, (p) -> new SoulStainedSteelArmorItem(ArmorItem.Type.CHESTPLATE, p));
        public static final DeferredItem<Item> SOUL_STAINED_STEEL_LEGGINGS = register("soul_stained_steel_leggings", MalumItemProperties::GEAR_PROPERTIES, (p) -> new SoulStainedSteelArmorItem(ArmorItem.Type.LEGGINGS, p));
        public static final DeferredItem<Item> SOUL_STAINED_STEEL_BOOTS = register("soul_stained_steel_boots", MalumItemProperties::GEAR_PROPERTIES, (p) -> new SoulStainedSteelArmorItem(ArmorItem.Type.BOOTS, p));

        public static final DeferredItem<Item> MALIGNANT_STRONGHOLD_HELMET = register("malignant_stronghold_helmet", MalumItemProperties::GEAR_PROPERTIES, (p) -> new MalignantStrongholdArmorItem(ArmorItem.Type.HELMET, p));
        public static final DeferredItem<Item> MALIGNANT_STRONGHOLD_CHESTPLATE = register("malignant_stronghold_chestplate", MalumItemProperties::GEAR_PROPERTIES, (p) -> new MalignantStrongholdArmorItem(ArmorItem.Type.CHESTPLATE, p));
        public static final DeferredItem<Item> MALIGNANT_STRONGHOLD_LEGGINGS = register("malignant_stronghold_leggings", MalumItemProperties::GEAR_PROPERTIES, (p) -> new MalignantStrongholdArmorItem(ArmorItem.Type.LEGGINGS, p));
        public static final DeferredItem<Item> MALIGNANT_STRONGHOLD_BOOTS = register("malignant_stronghold_boots", MalumItemProperties::GEAR_PROPERTIES, (p) -> new MalignantStrongholdArmorItem(ArmorItem.Type.BOOTS, p));

        public static final DeferredItem<Item> SOUL_STAINED_STEEL_SCYTHE = register("soul_stained_steel_scythe", MalumItemProperties::GEAR_PROPERTIES, (p) -> new MagicScytheItem(SOUL_STAINED_STEEL, -3.5f, 0.2f, 4, p));
        public static final DeferredItem<Item> SOUL_STAINED_STEEL_KNIFE = register("soul_stained_steel_knife", () -> FarmersDelightCompat.LOADED ? MalumItemProperties.GEAR_PROPERTIES() : MalumItemProperties.HIDDEN_PROPERTIES(), (p) -> FarmersDelightCompat.LOADED ? FarmersDelightCompat.LoadedOnly.makeMagicKnife(p) : new Item(p));
        public static final DeferredItem<Item> SOUL_STAINED_STEEL_SWORD = register("soul_stained_steel_sword", MalumItemProperties::GEAR_PROPERTIES, (p) -> new MagicSwordItem(SOUL_STAINED_STEEL, -3, 0, 3, p));

        public static final DeferredItem<Item> SOUL_STAINED_STEEL_PICKAXE = register("soul_stained_steel_pickaxe", MalumItemProperties::GEAR_PROPERTIES, (p) -> new MagicPickaxeItem(SOUL_STAINED_STEEL, -2, 0, 2, p));
        public static final DeferredItem<Item> SOUL_STAINED_STEEL_AXE = register("soul_stained_steel_axe", MalumItemProperties::GEAR_PROPERTIES, (p) -> new MagicAxeItem(SOUL_STAINED_STEEL, -3, 0, 4, p));
        public static final DeferredItem<Item> SOUL_STAINED_STEEL_SHOVEL = register("soul_stained_steel_shovel", MalumItemProperties::GEAR_PROPERTIES, (p) -> new MagicShovelItem(SOUL_STAINED_STEEL, -2, 0, 2, p));
        public static final DeferredItem<Item> SOUL_STAINED_STEEL_HOE = register("soul_stained_steel_hoe", MalumItemProperties::GEAR_PROPERTIES, (p) -> new MagicHoeItem(SOUL_STAINED_STEEL, 0, -1.5f, 1, p));

        public static final DeferredItem<Item> SPELLWEAVING_PICKAXE = register("spellweaving_pickaxe", MalumItemProperties::GEAR_PROPERTIES, (p) -> new SpellweavingPickaxeItem(SPELLWEAVING_TOOLS, -3, 0, 4, p));
        public static final DeferredItem<Item> SPELLWEAVING_AXE = register("spellweaving_axe", MalumItemProperties::GEAR_PROPERTIES, (p) -> new SpellweavingAxeItem(SPELLWEAVING_TOOLS, -4, 0, 6, p));

        public static final DeferredItem<Item> RAVENOUS_SCYTHE = register("ravenous_scythe", MalumItemProperties::GEAR_PROPERTIES, (p) -> new RavenousScytheItem(RAVENOUS, -4f, 0.4f, 2.5f, p));
        public static final DeferredItem<Item> GLUTTONOUS_BLUDGEON = register("gluttonous_bludgeon", MalumItemProperties::GEAR_PROPERTIES, (p) -> new GluttonousBludgeonItem(RAVENOUS, -2.5f, -3f, 2, p));

        public static final DeferredItem<Item> TYRVING = register("tyrving", MalumItemProperties::GEAR_PROPERTIES, (p) -> new TyrvingItem(MalumItemTiers.TYRVING, 0, -0.3f, p));

        public static final DeferredItem<Item> MNEMONIC_HEX_STAFF = register("mnemonic_hex_staff", MalumItemProperties::GEAR_PROPERTIES, (p) -> new HexStaffItem(HEX_STAFF, 5, 1, 2, p));
        public static final DeferredItem<Item> EROSION_SCEPTER = register("erosion_scepter", MalumItemProperties::GEAR_PROPERTIES, (p) -> new ErosionScepterItem(MALIGNANT_ALLOY, 5, 0.5f, 1, p));

        public static final DeferredItem<Item> WEIGHT_OF_WORLDS = register("weight_of_worlds", MalumItemProperties::GEAR_PROPERTIES, (p) -> new WeightOfWorldsItem(MalumItemTiers.MALIGNANT_ALLOY, 1, -0.2f, p));
        public static final DeferredItem<Item> EDGE_OF_DELIVERANCE = register("edge_of_deliverance", MalumItemProperties::GEAR_PROPERTIES, (p) -> new EdgeOfDeliveranceItem(MalumItemTiers.MALIGNANT_ALLOY, 2, -0.1f, p));

        public static final DeferredItem<Item> UNWINDING_CHAOS = register("unwinding_chaos", () -> MalumItemProperties.GEAR_PROPERTIES().rarity(EPIC), (p) -> new UnwindingChaosStaffItem(HARNESSED_CHAOS, 5, 1.5f, 3, p));
        public static final DeferredItem<Item> SUNDERING_ANCHOR = register("sundering_anchor", () -> MalumItemProperties.GEAR_PROPERTIES().rarity(EPIC), (p) -> new SunderingAnchorItem(HARNESSED_CHAOS, 4, p));

        public static final DeferredItem<Item> GILDED_RING = register("gilded_ring", MalumItemProperties::GEAR_PROPERTIES, CurioGildedRing::new);
        public static final DeferredItem<Item> GILDED_BELT = register("gilded_belt", MalumItemProperties::GEAR_PROPERTIES, CurioGildedBelt::new);
        public static final DeferredItem<Item> ORNATE_RING = register("ornate_ring", MalumItemProperties::GEAR_PROPERTIES, CurioOrnateRing::new);
        public static final DeferredItem<Item> ORNATE_NECKLACE = register("ornate_necklace", MalumItemProperties::GEAR_PROPERTIES, CurioOrnateNecklace::new);

        public static final DeferredItem<Item> RUNIC_BROOCH = register("runic_brooch", MalumItemProperties::GEAR_PROPERTIES, CurioRunicBrooch::new);
        public static final DeferredItem<Item> ELABORATE_BROOCH = register("elaborate_brooch", MalumItemProperties::GEAR_PROPERTIES, CurioElaborateBrooch::new);
        public static final DeferredItem<Item> GLASS_BROOCH = register("glass_brooch", MalumItemProperties::GEAR_PROPERTIES, CurioGlassBrooch::new);
        public static final DeferredItem<Item> GLUTTONOUS_BROOCH = register("gluttonous_brooch", MalumItemProperties::GEAR_PROPERTIES, CurioGluttonousBrooch::new);

        public static final DeferredItem<Item> RING_OF_ESOTERIC_SPOILS = register("ring_of_esoteric_spoils", MalumItemProperties::GEAR_PROPERTIES, CurioArcaneSpoilRing::new);
        public static final DeferredItem<Item> RING_OF_ESOTERIC_SHADOW = register("ring_of_esoteric_shadow", MalumItemProperties::GEAR_PROPERTIES, CurioConcealingRing::new);

        public static final DeferredItem<Item> RING_OF_CURATIVE_TALENT = register("ring_of_curative_talent", MalumItemProperties::GEAR_PROPERTIES, CurioCurativeRing::new);
        public static final DeferredItem<Item> RING_OF_ALCHEMICAL_MASTERY = register("ring_of_alchemical_mastery", MalumItemProperties::GEAR_PROPERTIES, CurioAlchemicalRing::new);
        public static final DeferredItem<Item> RING_OF_MANAWEAVING = register("ring_of_manaweaving", MalumItemProperties::GEAR_PROPERTIES, CurioManaweavingRing::new);
        public static final DeferredItem<Item> RING_OF_ARCANE_PROWESS = register("ring_of_arcane_prowess", MalumItemProperties::GEAR_PROPERTIES, CurioProwessRing::new);

        public static final DeferredItem<Item> RING_OF_DESPERATE_VORACITY = register("ring_of_desperate_voracity", MalumItemProperties::GEAR_PROPERTIES, CurioVoraciousRing::new);
        public static final DeferredItem<Item> RING_OF_SWARMING_ROT = register("ring_of_swarming_rot", MalumItemProperties::GEAR_PROPERTIES, CurioSwarmingRing::new);

        public static final DeferredItem<Item> RING_OF_THE_RISING_EDGE = register("ring_of_the_rising_edge", MalumItemProperties::GEAR_PROPERTIES, CurioRisingEdgeRing::new);
        public static final DeferredItem<Item> RING_OF_THE_HOWLING_MAELSTROM = register("ring_of_the_howling_maelstrom", MalumItemProperties::GEAR_PROPERTIES, CurioHowlingMaelstromRing::new);

        public static final DeferredItem<Item> RING_OF_HEARTY_AVARICE = register("ring_of_hearty_avarice", MalumItemProperties::GEAR_PROPERTIES, CurioHeartyAvariceRing::new);
        public static final DeferredItem<Item> RING_OF_HEAVY_DISCHARGE = register("ring_of_heavy_discharge", MalumItemProperties::GEAR_PROPERTIES, CurioDischargeRing::new);

        public static final DeferredItem<Item> NECKLACE_OF_MYSTIC_POTENCY = register("necklace_of_mystic_potency", MalumItemProperties::GEAR_PROPERTIES, CurioMysticNecklace::new);
        public static final DeferredItem<Item> NECKLACE_OF_THE_NARROW_EDGE = register("necklace_of_the_narrow_edge", MalumItemProperties::GEAR_PROPERTIES, CurioNarrowEdgeNecklace::new);

        public static final DeferredItem<Item> BELT_OF_THE_STARVED = register("belt_of_the_starved", MalumItemProperties::GEAR_PROPERTIES, CurioStarvedBelt::new);
        public static final DeferredItem<Item> BELT_OF_THE_PROSPECTOR = register("belt_of_the_prospector", MalumItemProperties::GEAR_PROPERTIES, CurioProspectorBelt::new);
        public static final DeferredItem<Item> BELT_OF_THE_MAGEBANE = register("belt_of_the_magebane", MalumItemProperties::GEAR_PROPERTIES, CurioMagebaneBelt::new);

        public static final DeferredItem<Item> RING_OF_THE_ENDLESS_WELL = register("ring_of_the_endless_well", MalumItemProperties::GEAR_PROPERTIES, CurioEndlessRing::new);
        public static final DeferredItem<Item> RING_OF_ECHOING_ARCANA = register("ring_of_echoing_arcana", MalumItemProperties::GEAR_PROPERTIES, CurioEchoingArcanaRing::new);
        public static final DeferredItem<Item> RING_OF_GROWING_FLESH = register("ring_of_growing_flesh", MalumItemProperties::GEAR_PROPERTIES, CurioGrowingFleshRing::new);
        public static final DeferredItem<Item> RING_OF_GRUESOME_CONCENTRATION = register("ring_of_gruesome_concentration", MalumItemProperties::GEAR_PROPERTIES, CurioGruesomeConcentrationRing::new);

        public static final DeferredItem<Item> NECKLACE_OF_THE_HIDDEN_BLADE = register("necklace_of_the_hidden_blade", MalumItemProperties::GEAR_PROPERTIES, CurioHiddenBladeNecklace::new);
        public static final DeferredItem<Item> NECKLACE_OF_THE_WATCHER = register("necklace_of_the_watcher", MalumItemProperties::GEAR_PROPERTIES, CurioWatcherNecklace::new);

        public static final DeferredItem<Item> BELT_OF_THE_LIMITLESS = register("belt_of_the_limitless", MalumItemProperties::GEAR_PROPERTIES, CurioLimitlessBelt::new);

        public static final DeferredItem<Item> RUNE_OF_VITALITY = register("rune_of_vitality", MalumItemProperties::GEAR_PROPERTIES, RuneVitalityItem::new);
        public static final DeferredItem<Item> RUNE_OF_CULLING = register("rune_of_culling", MalumItemProperties::GEAR_PROPERTIES, RuneCullingItem::new);
        public static final DeferredItem<Item> RUNE_OF_REINFORCEMENT = register("rune_of_reinforcement", MalumItemProperties::GEAR_PROPERTIES, RuneReinforcementItem::new);
        public static final DeferredItem<Item> RUNE_OF_VOLATILE_DISTORTION = register("rune_of_volatile_distortion", MalumItemProperties::GEAR_PROPERTIES, RuneVolatileDistortionItem::new);
        public static final DeferredItem<Item> RUNE_OF_DEXTERITY = register("rune_of_dexterity", MalumItemProperties::GEAR_PROPERTIES, RuneDexterityItem::new);
        public static final DeferredItem<Item> RUNE_OF_AILMENT_CLEANSING = register("rune_of_ailment_cleansing", MalumItemProperties::GEAR_PROPERTIES, RuneAilmentCleansingItem::new);
        public static final DeferredItem<Item> RUNE_OF_PROTECTION = register("rune_of_protection", MalumItemProperties::GEAR_PROPERTIES, RuneProtectionItem::new);
        public static final DeferredItem<Item> RUNE_OF_SCORCHING = register("rune_of_scorching", MalumItemProperties::GEAR_PROPERTIES, RuneScorchingItem::new);

        public static final DeferredItem<Item> RUNE_OF_HOWLING_GALE = register("rune_of_howling_gale", MalumItemProperties::GEAR_PROPERTIES, RuneHowlingGale::new);
        public static final DeferredItem<Item> RUNE_OF_FLOWING_GRASP = register("rune_of_flowing_grasp", MalumItemProperties::GEAR_PROPERTIES, RuneFlowingGrasp::new);
        public static final DeferredItem<Item> RUNE_OF_STONE_WARD = register("rune_of_stone_ward", MalumItemProperties::GEAR_PROPERTIES, RuneStoneWard::new);
        public static final DeferredItem<Item> RUNE_OF_BURNING_FERVOR = register("rune_of_burning_fervor", MalumItemProperties::GEAR_PROPERTIES, RuneBurningFervor::new);
        public static final DeferredItem<Item> RUNE_OF_SKY_TETHER = register("rune_of_sky_tether", MalumItemProperties::GEAR_PROPERTIES, RuneSkyTether::new);
        public static final DeferredItem<Item> RUNE_OF_GOOD_TIDES = register("rune_of_good_tides", MalumItemProperties::GEAR_PROPERTIES, RuneGoodTides::new);
        public static final DeferredItem<Item> RUNE_OF_OAKEN_MIGHT = register("rune_of_oaken_might", MalumItemProperties::GEAR_PROPERTIES, RuneOakenMight::new);
        public static final DeferredItem<Item> RUNE_OF_FIERY_EMBRACE = register("rune_of_fiery_embrace", MalumItemProperties::GEAR_PROPERTIES, RuneFieryEmbrace::new);

        public static final DeferredItem<Item> RUNE_OF_BOLSTERING = register("rune_of_bolstering", MalumItemProperties::GEAR_PROPERTIES, RuneBolsteringItem::new);
        public static final DeferredItem<Item> RUNE_OF_RADIAL_EMPOWERMENT = register("rune_of_radial_empowerment", MalumItemProperties::GEAR_PROPERTIES, RuneRadialEmpowermentItem::new);
        public static final DeferredItem<Item> RUNE_OF_SPELL_MASTERY = register("rune_of_spell_mastery", MalumItemProperties::GEAR_PROPERTIES, RuneSpellMasteryItem::new);
        public static final DeferredItem<Item> RUNE_OF_HERESY = register("rune_of_heresy", MalumItemProperties::GEAR_PROPERTIES, RuneHeresyItem::new);
        public static final DeferredItem<Item> RUNE_OF_UNNATURAL_STAMINA = register("rune_of_unnatural_stamina", MalumItemProperties::GEAR_PROPERTIES, RuneUnnaturalStaminaItem::new);
        public static final DeferredItem<Item> RUNE_OF_TWINNED_DURATION = register("rune_of_twinned_duration", MalumItemProperties::GEAR_PROPERTIES, RuneTwinnedDurationItem::new);
        public static final DeferredItem<Item> RUNE_OF_INDOMITABILITY = register("rune_of_indomitability", MalumItemProperties::GEAR_PROPERTIES, RuneIndomitabilityItem::new);
        public static final DeferredItem<Item> RUNE_OF_IGNEOUS_SOLACE = register("rune_of_igneous_solace", MalumItemProperties::GEAR_PROPERTIES, RuneIgneousSolaceItem::new);
    }

    public static class DungeonGear {

        public static final DeferredItem<Item> SHAPED_SLAB = register("shaped_slab", MalumItemProperties::GEAR_PROPERTIES, (p) -> new ShapedSlabSwordItem(ARCHAIC_SLATE, 2.5f, -0.8f, p));
        public static final DeferredItem<Item> BROKEN_BLADE = register("broken_blade", MalumItemProperties::GEAR_PROPERTIES, (p) -> new BrokenBladeSwordItem(ARCHAIC_SLATE, -0.5f, -0.6f, p));
        public static final DeferredItem<Item> IRON_CROWN = register("iron_crown", MalumItemProperties::DEFAULT_PROPERTIES, Item::new);

    }

    public static class DungeonBlockSets {

        //region dross stone
        public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE = registerBlock("dross_stone", () -> new Block(DROSS_STONE().addTag(DROSS_STONE_BLOCKS)));
        public static final BlockBlockItemHolder<Block, BlockItem> GRAY_DROSS_TILES_STAIRS = registerBlock("gray_dross_tiles_stairs", () -> new LodestoneStairBlock( DROSS_STONE().addTags(STAIRS)));
        public static final BlockBlockItemHolder<Block, BlockItem> DARK_DROSS_TILES_STAIRS = registerBlock("dark_dross_tiles_stairs", () -> new LodestoneStairBlock( DROSS_STONE().addTags(STAIRS)));
        public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_MOSAIC_STAIRS = registerBlock("dross_stone_mosaic_stairs", () -> new LodestoneStairBlock( DROSS_STONE_BRICKS().addTags(STAIRS, MalumTags.Blocks.DROSS_STONE_STAIRS)));
        public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_TILES_STAIRS = registerBlock("dross_stone_tiles_stairs", () -> new LodestoneStairBlock( DROSS_STONE_BRICKS().addTags(STAIRS, MalumTags.Blocks.DROSS_STONE_STAIRS)));
        public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_BRICKS_STAIRS = registerBlock("dross_stone_bricks_stairs", () -> new LodestoneStairBlock( DROSS_STONE_BRICKS().addTags(STAIRS, MalumTags.Blocks.DROSS_STONE_STAIRS)));
        public static final BlockBlockItemHolder<Block, BlockItem> POLISHED_DROSS_STONE_STAIRS = registerBlock("polished_dross_stone_stairs", () -> new LodestoneStairBlock( DROSS_STONE().addTags(STAIRS, MalumTags.Blocks.DROSS_STONE_STAIRS)));
        public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_STAIRS = registerBlock("dross_stone_stairs", () -> new LodestoneStairBlock( DROSS_STONE().addTags(STAIRS, MalumTags.Blocks.DROSS_STONE_STAIRS)));
        public static final BlockBlockItemHolder<Block, BlockItem> POLISHED_DROSS_STONE = registerBlock("polished_dross_stone", () -> new Block(DROSS_STONE().addTag(DROSS_STONE_BLOCKS)));
        public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_BRICKS = registerBlock("dross_stone_bricks", () -> new Block(DROSS_STONE_BRICKS().addTag(DROSS_STONE_BLOCKS)));
        public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_TILES = registerBlock("dross_stone_tiles", () -> new Block(DROSS_STONE_BRICKS().addTag(DROSS_STONE_BLOCKS)));
        public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_MOSAIC = registerBlock("dross_stone_mosaic", () -> new Block(DROSS_STONE_BRICKS().addTag(DROSS_STONE_BLOCKS)));
        public static final BlockBlockItemHolder<Block, BlockItem> DARK_DROSS_TILES = registerBlock("dark_dross_tiles", () -> new Block(DROSS_STONE()));
        public static final BlockBlockItemHolder<Block, BlockItem> GRAY_DROSS_TILES = registerBlock("gray_dross_tiles", () -> new Block(DROSS_STONE()));
        public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_COLUMN = registerBlock("dross_stone_column", () -> new ColumnBlock(CHISELED_DROSS_STONE()));
        public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_ALTAR = registerBlock("dross_stone_altar", () -> new Block(CHISELED_DROSS_STONE().addTag(EIDOLON_ALTAR_BLOCK)));
        public static final BlockBlockItemHolder<Block, BlockItem> CUT_DROSS_STONE = registerBlock("cut_dross_stone", () -> new Block(CHISELED_DROSS_STONE()));
        public static final BlockBlockItemHolder<Block, BlockItem> CHISELED_DROSS_STONE = registerBlock("chiseled_dross_stone", () -> new Block(CHISELED_DROSS_STONE()));
        public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_SLAB = registerBlock("dross_stone_slab", () -> new SlabBlock(DROSS_STONE().addTags(SLABS, DROSS_STONE_SLABS)));
        public static final BlockBlockItemHolder<Block, BlockItem> POLISHED_DROSS_STONE_SLAB = registerBlock("polished_dross_stone_slab", () -> new SlabBlock(DROSS_STONE().addTags(SLABS, DROSS_STONE_SLABS)));
        public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_BRICKS_SLAB = registerBlock("dross_stone_bricks_slab", () -> new SlabBlock(DROSS_STONE_BRICKS().addTags(SLABS, DROSS_STONE_SLABS)));
        public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_TILES_SLAB = registerBlock("dross_stone_tiles_slab", () -> new SlabBlock(DROSS_STONE_BRICKS().addTags(SLABS, DROSS_STONE_SLABS)));
        public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_MOSAIC_SLAB = registerBlock("dross_stone_mosaic_slab", () -> new SlabBlock(DROSS_STONE_BRICKS().addTags(SLABS, DROSS_STONE_SLABS)));
        public static final BlockBlockItemHolder<Block, BlockItem> DARK_DROSS_TILES_SLAB = registerBlock("dark_dross_tiles_slab", () -> new SlabBlock(DROSS_STONE().addTags(SLABS)));
        public static final BlockBlockItemHolder<Block, BlockItem> GRAY_DROSS_TILES_SLAB = registerBlock("gray_dross_tiles_slab", () -> new SlabBlock(DROSS_STONE().addTags(SLABS)));
        public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_WALL = registerBlock("dross_stone_wall", () -> new WallBlock(DROSS_STONE().addTags(WALLS, DROSS_STONE_WALLS)));
        public static final BlockBlockItemHolder<Block, BlockItem> POLISHED_DROSS_STONE_WALL = registerBlock("polished_dross_stone_wall", () -> new WallBlock(DROSS_STONE().addTags(WALLS, DROSS_STONE_WALLS)));
        public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_BRICKS_WALL = registerBlock("dross_stone_bricks_wall", () -> new WallBlock(DROSS_STONE_BRICKS().addTags(WALLS, DROSS_STONE_WALLS)));
        public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_TILES_WALL = registerBlock("dross_stone_tiles_wall", () -> new WallBlock(DROSS_STONE_BRICKS().addTags(WALLS, DROSS_STONE_WALLS)));
        public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_MOSAIC_WALL = registerBlock("dross_stone_mosaic_wall", () -> new WallBlock(DROSS_STONE_BRICKS().addTags(WALLS, DROSS_STONE_WALLS)));
        public static final BlockBlockItemHolder<Block, BlockItem> DARK_DROSS_TILES_WALL = registerBlock("dark_dross_tiles_wall", () -> new WallBlock(DROSS_STONE().addTags(WALLS)));
        public static final BlockBlockItemHolder<Block, BlockItem> GRAY_DROSS_TILES_WALL = registerBlock("gray_dross_tiles_wall", () -> new WallBlock(DROSS_STONE().addTags(WALLS)));
        public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_BUTTON = registerBlock("dross_stone_button", () -> new ButtonBlock(BlockSetType.STONE, 20, DROSS_STONE().noCollission().addTag(BUTTONS)));
        public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_PRESSURE_PLATE = registerBlock("dross_stone_pressure_plate", () -> new PressurePlateBlock(BlockSetType.STONE, DROSS_STONE().noCollission().addTag(PRESSURE_PLATES)));
        public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_ITEM_STAND = registerBlock("dross_stone_item_stand", () -> new ItemStandBlock<>(DROSS_STONE().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_STAND));
        public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_ITEM_PEDESTAL = registerBlock("dross_stone_item_pedestal", () -> new ItemPedestalBlock<>(DROSS_STONE().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_PEDESTAL));
        //region dungeon
        public static final BlockBlockItemHolder<Block, BlockItem> OMINOUS_ALTAR = registerBlock("ominous_altar", () -> new OminousAltarBlock(MalumDungeonBlockProperties.OMINOUS_CRAFT()).setBlockEntity(MalumBlockEntities.OMINOUS_ALTAR));
        public static final BlockBlockItemHolder<Block, MultiBlockItem> OMINOUS_OBELISK = MalumBlocks.registerMultiBlock("ominous_obelisk", () -> new OminousObeliskCoreBlock(MalumDungeonBlockProperties.OMINOUS_CRAFT().setCutoutRenderType().noOcclusion()), RunewoodObeliskBlockEntity.STRUCTURE);
        public static final DeferredBlock<Block> OMINOUS_OBELISK_COMPONENT = MalumBlocks.registerBlockNoItem("ominous_obelisk_component", () -> new ObeliskComponentBlock(MalumDungeonBlockProperties.OMINOUS_CRAFT().setCutoutRenderType().lootFrom(OMINOUS_OBELISK).noOcclusion()));
        public static final BlockBlockItemHolder<Block, BlockItem> ODD_SCRIPTURES_I = registerBlock("odd_scriptures_i", () -> new OddScripturesBlock(MalumDungeonBlockProperties.ODD_SCRIPTURES()));
        public static final BlockBlockItemHolder<Block, BlockItem> ODD_SCRIPTURES_II = registerBlock("odd_scriptures_ii", () -> new OddScripturesBlock(MalumDungeonBlockProperties.ODD_SCRIPTURES()));
        public static final BlockBlockItemHolder<Block, BlockItem> ODD_SCRIPTURES_III = registerBlock("odd_scriptures_iii", () -> new OddScripturesBlock(MalumDungeonBlockProperties.ODD_SCRIPTURES()));
        public static final BlockBlockItemHolder<Block, BlockItem> ODD_SCRIPTURES_IV = registerBlock("odd_scriptures_iv", () -> new OddScripturesBlock(MalumDungeonBlockProperties.ODD_SCRIPTURES()));
        public static final BlockBlockItemHolder<Block, BlockItem> ODD_SCRIPTURES_V = registerBlock("odd_scriptures_v", () -> new OddScripturesBlock(MalumDungeonBlockProperties.ODD_SCRIPTURES()));
        public static final BlockBlockItemHolder<Block, BlockItem> ODD_SCRIPTURES_VI = registerBlock("odd_scriptures_vi", () -> new OddScripturesBlock(MalumDungeonBlockProperties.ODD_SCRIPTURES()));
        public static final BlockBlockItemHolder<Block, BlockItem> ODD_SCRIPTURES_VII = registerBlock("odd_scriptures_vii", () -> new OddScripturesBlock(MalumDungeonBlockProperties.ODD_SCRIPTURES()));
        public static final BlockBlockItemHolder<Block, BlockItem> ODD_SCRIPTURES_VIII = registerBlock("odd_scriptures_viii", () -> new OddScripturesBlock(MalumDungeonBlockProperties.ODD_SCRIPTURES()));
        public static final BlockBlockItemHolder<Block, BlockItem> ODD_SCRIPTURES_IX = registerBlock("odd_scriptures_ix", () -> new OddScripturesBlock(MalumDungeonBlockProperties.ODD_SCRIPTURES()));
        public static final BlockBlockItemHolder<Block, BlockItem> VEILED_EFFIGY = registerBlock("veiled_effigy", () -> new MeditatingEffigyBlock(MalumDungeonBlockProperties.MEDITATING_EFFIGY()));
        public static final BlockBlockItemHolder<Block, BlockItem> CORRUPT_EFFIGY = registerBlock("corrupt_effigy", () -> new MeditatingEffigyBlock(MalumDungeonBlockProperties.MEDITATING_EFFIGY()));
        public static final BlockBlockItemHolder<Block, BlockItem> CRACKED_EFFIGY = registerBlock("cracked_effigy", () -> new MeditatingEffigyBlock(MalumDungeonBlockProperties.MEDITATING_EFFIGY()));
        public static final BlockBlockItemHolder<Block, BlockItem> COLUMNAR_FLESH = registerBlock("columnar_flesh", () -> new ColumnarFleshBlock(MalumDungeonBlockProperties.FLESH_BLOCK()));
        public static final BlockBlockItemHolder<Block, BlockItem> FLESHBULB = registerBlock("fleshbulb", () -> new FleshBulbBlock(MalumDungeonBlockProperties.FLESHBULB()));
        public static final BlockBlockItemHolder<Block, BlockItem> WRITHING_FLESH = registerBlock("writhing_flesh", () -> new WrithingFleshBlock(MalumDungeonBlockProperties.WRITHING_FLESH()));
    }
}
