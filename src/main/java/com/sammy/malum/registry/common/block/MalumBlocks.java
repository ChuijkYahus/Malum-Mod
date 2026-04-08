package com.sammy.malum.registry.common.block;

import com.sammy.malum.common.block.blight.*;
import com.sammy.malum.common.block.blight.scarstone.*;
import com.sammy.malum.common.block.curiosities.banner.*;
import com.sammy.malum.common.block.curiosities.gust_igniter.*;
import com.sammy.malum.common.block.curiosities.gust_igniter.wind_tunnel.*;
import com.sammy.malum.common.block.curiosities.mana_mote.*;
import com.sammy.malum.common.block.curiosities.obelisk.*;
import com.sammy.malum.common.block.curiosities.obelisk.brilliant.*;
import com.sammy.malum.common.block.curiosities.obelisk.rite_pylon.*;
import com.sammy.malum.common.block.curiosities.obelisk.runewood.*;
import com.sammy.malum.common.block.curiosities.redstone.wavemaker.WaveMakerBlock;
import com.sammy.malum.common.block.curiosities.redstone.wavebanker.WaveBankerBlock;
import com.sammy.malum.common.block.curiosities.redstone.wavecharger.WaveChargerBlock;
import com.sammy.malum.common.block.curiosities.redstone.wavebreaker.WaveBreakerBlock;
import com.sammy.malum.common.block.curiosities.repair_pylon.*;
import com.sammy.malum.common.block.curiosities.ritual_plinth.*;
import com.sammy.malum.common.block.curiosities.runic_workbench.*;
import com.sammy.malum.common.block.curiosities.soul_brazier.*;
import com.sammy.malum.common.block.curiosities.spirit_altar.*;
import com.sammy.malum.common.block.curiosities.spirit_crucible.*;
import com.sammy.malum.common.block.curiosities.spirit_catalyzer.*;
import com.sammy.malum.common.block.curiosities.totem.*;
import com.sammy.malum.common.block.curiosities.totem.anchor.*;
import com.sammy.malum.common.block.curiosities.totem.channel.*;
import com.sammy.malum.common.block.curiosities.totem.spreader.*;
import com.sammy.malum.common.block.curiosities.totem.unweaver.*;
import com.sammy.malum.common.block.curiosities.totem.waveform.*;
import com.sammy.malum.common.block.curiosities.void_depot.*;
import com.sammy.malum.common.block.curiosities.weavers_workbench.*;
import com.sammy.malum.common.block.curiosities.weeping_well.*;
import com.sammy.malum.common.block.curiosities.weeping_well.encasement.*;
import com.sammy.malum.common.block.decor.ColumnBlock;
import com.sammy.malum.common.block.decor.SpiritedGlassBlock;
import com.sammy.malum.common.block.decor.VarnishedTerracottaBlock;
import com.sammy.malum.common.block.dungeon.*;
import com.sammy.malum.common.block.dungeon.curiosities.*;
import com.sammy.malum.common.block.ether.*;
import com.sammy.malum.common.block.flora.EbonySaplingBlock;
import com.sammy.malum.common.block.flora.EbonyStalkBlock;
import com.sammy.malum.common.block.flora.WildWitchhazelPlantBlock;
import com.sammy.malum.common.block.flora.WitchhazelCropBlock;
import com.sammy.malum.common.block.flora.soulwood.SapFilledSoulwoodLogBlock;
import com.sammy.malum.common.block.flora.soulwood.SoulwoodBlock;
import com.sammy.malum.common.block.flora.soulwood.SoulwoodGrowthBlock;
import com.sammy.malum.common.block.flora.soulwood.SoulwoodLogBlock;
import com.sammy.malum.common.block.flora.wood.*;
import com.sammy.malum.common.block.storage.jar.*;
import com.sammy.malum.common.block.storage.pedestal.*;
import com.sammy.malum.common.block.storage.stand.*;
import com.sammy.malum.common.block.the_device.*;
import com.sammy.malum.common.item.BlightedGunkItem;
import com.sammy.malum.common.item.banner.SoulwovenBannerBlockItem;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.block.properties.*;
import com.sammy.malum.registry.common.item.*;
import com.sammy.malum.registry.common.magic.*;
import com.sammy.malum.registry.common.sound.*;
import com.sammy.malum.registry.common.worldgen.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.valueproviders.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.*;
import net.neoforged.fml.event.lifecycle.*;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import team.lodestar.lodestone.modules.toolkit.block.*;
import team.lodestar.lodestone.modules.toolkit.item.LodestoneItemProperties;
import team.lodestar.lodestone.modules.toolkit.multiblock.MultiBlockItem;
import team.lodestar.lodestone.modules.toolkit.multiblock.MultiBlockStructure;

import java.awt.*;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import static com.sammy.malum.MalumMod.*;
import static com.sammy.malum.registry.common.MalumTags.Blocks.*;
import static net.minecraft.tags.BlockTags.*;
import static net.neoforged.neoforge.common.Tags.Blocks.FENCE_GATES_WOODEN;


public class MalumBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, MALUM);

    //region useful blocks
    public static final BlockBlockItemHolder<Block, BlockItem> SPIRIT_ALTAR = registerBlock("spirit_altar", () -> new SpiritAltarBlock<>(MalumBlockProperties.SPIRIT_ALTAR()).setBlockEntity(MalumBlockEntities.SPIRIT_ALTAR));
    public static final BlockBlockItemHolder<Block, BlockItem> SPIRIT_JAR = registerBlock("spirit_jar", () -> new SpiritJarBlock<>(MalumBlockProperties.SPIRIT_JAR().setCutoutRenderType().noOcclusion()).setBlockEntity(MalumBlockEntities.SPIRIT_JAR));

    public static final BlockBlockItemHolder<Block, BlockItem> WEAVERS_WORKBENCH = registerBlock("weavers_workbench", () -> new WeaversWorkbenchBlock<>(MalumBlockProperties.RUNEWOOD().setCutoutRenderType().noOcclusion()).setBlockEntity(MalumBlockEntities.WEAVERS_WORKBENCH));
    public static final BlockBlockItemHolder<Block, BlockItem> RUNIC_WORKBENCH = registerBlock("runic_workbench", () -> new RunicWorkbenchBlock<>(MalumBlockProperties.RUNEWOOD().setCutoutRenderType().noOcclusion()).setBlockEntity(MalumBlockEntities.RUNIC_WORKBENCH));

    public static final BlockBlockItemHolder<Block, BlockItem> SOUL_BRAZIER = registerBlock("soulbinding_brazier", () -> new SoulBrazierBlock<>(MalumBlockProperties.SOUL_BRAZIER()).setBlockEntity(MalumBlockEntities.SOUL_BRAZIER));

    public static final BlockBlockItemHolder<Block, BlockItem> RITUAL_PLINTH = registerBlock("ritual_plinth", () -> new RitualPlinthBlock<>(MalumBlockProperties.SOULWOOD().setCutoutRenderType().noOcclusion()).setBlockEntity(MalumBlockEntities.RITUAL_PLINTH));

    public static final BlockBlockItemHolder<Block, MultiBlockItem> RUNEWOOD_OBELISK = registerMultiBlock("runewood_obelisk", () -> new RunewoodObeliskCoreBlock(MalumBlockProperties.RUNEWOOD().setCutoutRenderType().noOcclusion()), RunewoodObeliskBlockEntity.STRUCTURE);
    public static final DeferredHolder<Block, ObeliskComponentBlock> RUNEWOOD_OBELISK_COMPONENT = registerBlockNoItem("runewood_obelisk_component", () -> new ObeliskComponentBlock(MalumBlockProperties.RUNEWOOD().setCutoutRenderType().lootFrom(RUNEWOOD_OBELISK).noOcclusion()));

    public static final BlockBlockItemHolder<Block, MultiBlockItem> BRILLIANT_OBELISK = registerMultiBlock("brilliant_obelisk", () -> new BrillianceObeliskCoreBlock(MalumBlockProperties.RUNEWOOD().setCutoutRenderType().noOcclusion()), BrilliantObeliskBlockEntity.STRUCTURE);
    public static final DeferredHolder<Block, ObeliskComponentBlock> BRILLIANT_OBELISK_COMPONENT = registerBlockNoItem("brilliant_obelisk_component", () -> new ObeliskComponentBlock(MalumBlockProperties.RUNEWOOD().setCutoutRenderType().lootFrom(BRILLIANT_OBELISK).noOcclusion()));

    public static final BlockBlockItemHolder<Block, MultiBlockItem> ARCANA_PYLON = registerMultiBlock("arcana_pylon", () -> new ArcanaPylonCoreBlock(MalumBlockProperties.SOULWOOD().setCutoutRenderType().noOcclusion()), ArcanaPylonBlockEntity.STRUCTURE);
    public static final DeferredHolder<Block, ArcanaPylonComponentBlock> ARCANA_PYLON_COMPONENT = registerBlockNoItem("arcana_pylon_component", () -> new ArcanaPylonComponentBlock(MalumBlockProperties.SOULWOOD().setCutoutRenderType().lootFrom(ARCANA_PYLON).noOcclusion()));

    public static final BlockBlockItemHolder<Block, MultiBlockItem> SPIRIT_CRUCIBLE = registerMultiBlock("spirit_crucible", () -> new SpiritCrucibleCoreBlock<>(MalumBlockProperties.ARCANE_ROCK_ARTIFICE()).setBlockEntity(MalumBlockEntities.SPIRIT_CRUCIBLE), SpiritCrucibleCoreBlockEntity.STRUCTURE);
    public static final DeferredHolder<Block, SpiritCrucibleComponentBlock> SPIRIT_CRUCIBLE_COMPONENT = registerBlockNoItem("spirit_crucible_component", () -> new SpiritCrucibleComponentBlock(MalumBlockProperties.ARCANE_ROCK_ARTIFICE().lootFrom(SPIRIT_CRUCIBLE)));

    public static final BlockBlockItemHolder<Block, MultiBlockItem> SPIRIT_CATALYZER = registerMultiBlock("spirit_catalyzer", () -> new SpiritCatalyzerCoreBlock<>(MalumBlockProperties.ARCANE_ROCK_ARTIFICE()).setBlockEntity(MalumBlockEntities.SPIRIT_CATALYZER), SpiritCatalyzerCoreBlockEntity.STRUCTURE);
    public static final DeferredHolder<Block, SpiritCatalyzerComponentBlock> SPIRIT_CATALYZER_COMPONENT = registerBlockNoItem("spirit_catalyzer_component", () -> new SpiritCatalyzerComponentBlock(MalumBlockProperties.ARCANE_ROCK_ARTIFICE().lootFrom(SPIRIT_CATALYZER)));

    public static final BlockBlockItemHolder<Block, MultiBlockItem> REPAIR_PYLON = registerMultiBlock("repair_pylon", () -> new RepairPylonCoreBlock<>(MalumBlockProperties.ARCANE_ROCK_ARTIFICE()).setBlockEntity(MalumBlockEntities.REPAIR_PYLON), RepairPylonCoreBlockEntity.STRUCTURE);
    public static final DeferredHolder<Block, RepairPylonComponentBlock> REPAIR_PYLON_COMPONENT = registerBlockNoItem("repair_pylon_component", () -> new RepairPylonComponentBlock(MalumBlockProperties.ARCANE_ROCK_ARTIFICE().lootFrom(REPAIR_PYLON)));

    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_TOTEM_BASE = registerBlock("runewood_totem_base", () -> new TotemBaseBlock<>(MalumBlockProperties.RUNEWOOD().addTag(IS_RITE_IMMUNE).noOcclusion(), false).setBlockEntity(MalumBlockEntities.TOTEM_BASE));
    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_TOTEM_BASE = registerBlock("soulwood_totem_base", () -> new TotemBaseBlock<>(MalumBlockProperties.SOULWOOD().addTag(IS_RITE_IMMUNE).noOcclusion(), true).setBlockEntity(MalumBlockEntities.TOTEM_BASE));

    public static final BlockBlockItemHolder<Block, BlockItem> WAVEFORM_RUNEWOOD_TOTEM_BASE = registerBlock("waveform_runewood_totem_base", () -> new WaveformTotemBaseBlock<>(MalumBlockProperties.WAVEFORM_DIODE().addTag(IS_RITE_IMMUNE).noOcclusion(), false).setBlockEntity(MalumBlockEntities.WAVEFORM_TOTEM_BASE));
    public static final BlockBlockItemHolder<Block, BlockItem> WAVEFORM_SOULWOOD_TOTEM_BASE = registerBlock("waveform_soulwood_totem_base", () -> new WaveformTotemBaseBlock<>(MalumBlockProperties.WAVEFORM_DIODE().addTag(IS_RITE_IMMUNE).noOcclusion(), true).setBlockEntity(MalumBlockEntities.WAVEFORM_TOTEM_BASE));

    public static final DeferredHolder<Block, Block> RUNEWOOD_TOTEM_POLE = registerBlockNoItem("runewood_totem_pole", () -> new TotemPoleBlock<>(MalumBlockProperties.RUNEWOOD().addTag(IS_RITE_IMMUNE).noOcclusion(), MalumBlocks.RUNEWOOD_LOG, false).setBlockEntity(MalumBlockEntities.TOTEM_POLE));
    public static final DeferredHolder<Block, Block> SOULWOOD_TOTEM_POLE = registerBlockNoItem("soulwood_totem_pole", () -> new TotemPoleBlock<>(MalumBlockProperties.SOULWOOD().addTag(IS_RITE_IMMUNE).noOcclusion(), MalumBlocks.SOULWOOD_LOG, true).setBlockEntity(MalumBlockEntities.TOTEM_POLE));

    public static final BlockBlockItemHolder<Block, BlockItem> RITE_ANCHOR = registerBlock("rite_anchor", () -> new RiteAnchorBlock(MalumBlockProperties.TAINTED_ROCK_TOTEMANCY()).setBlockEntity(MalumBlockEntities.RITE_ANCHOR));
    public static final BlockBlockItemHolder<Block, BlockItem> RITE_UNWEAVER = registerBlock("rite_unweaver", () -> new RiteUnweaverBlock(MalumBlockProperties.TWISTED_ROCK_TOTEMANCY()).setBlockEntity(MalumBlockEntities.RITE_UNWEAVER));
    public static final BlockBlockItemHolder<Block, BlockItem> RITE_SPREADER = registerBlock("rite_spreader", () -> new RiteSpreaderBlock(MalumBlockProperties.TAINTED_ROCK_TOTEMANCY()).setBlockEntity(MalumBlockEntities.RITE_SPREADER));
    public static final BlockBlockItemHolder<Block, BlockItem> RITE_CHANNEL = registerBlock("rite_channel", () -> new RiteChannelBlock(MalumBlockProperties.TAINTED_ROCK_TOTEMANCY()).setBlockEntity(MalumBlockEntities.RITE_CHANNEL));

    public static final BlockBlockItemHolder<Block, BlockItem> WAVECHARGER = registerBlock("wavecharger", () -> new WaveChargerBlock(MalumBlockProperties.WAVEFORM_DIODE()).setBlockEntity(MalumBlockEntities.WAVECHARGER));
    public static final BlockBlockItemHolder<Block, BlockItem> WAVEBANKER = registerBlock("wavebanker", () -> new WaveBankerBlock(MalumBlockProperties.WAVEFORM_DIODE()).setBlockEntity(MalumBlockEntities.WAVEBANKER));
    public static final BlockBlockItemHolder<Block, BlockItem> WAVEMAKER = registerBlock("wavemaker", () -> new WaveMakerBlock(MalumBlockProperties.WAVEFORM_DIODE()).setBlockEntity(MalumBlockEntities.WAVEMAKER));
    public static final BlockBlockItemHolder<Block, BlockItem> WAVEBREAKER = registerBlock("wavebreaker", () -> new WaveBreakerBlock(MalumBlockProperties.WAVEFORM_DIODE()).setBlockEntity(MalumBlockEntities.WAVEBREAKER));

    public static final BlockBlockItemHolder<Block, BlockItem> GUST_IGNITER = registerBlock("gust_igniter", () -> new GustIgniterBlock(MalumBlockProperties.GUST_TECH()).setBlockEntity(MalumBlockEntities.GUST_IGNITER));
    public static final BlockBlockItemHolder<Block, BlockItem> WIND_TUNNEL = registerBlock("wind_tunnel", () -> new WindTunnelBlock(MalumBlockProperties.GUST_TECH()).setBlockEntity(MalumBlockEntities.WIND_TUNNEL));

    public static final DeferredHolder<Block, Block> SPIRIT_MOTE = registerBlockNoItem("spirit_mote", () -> new ManaMoteBlock(MalumStorageBlockProperties.MANA_MOTE_BLOCK()).setBlockEntity(MalumBlockEntities.MANA_MOTE));

    public static final BlockBlockItemHolder<Block, BlockItem> VOID_CONDUIT = registerBlock("void_conduit", () -> new VoidConduitBlock<>(MalumBlockProperties.PRIMORDIAL_SOUP()).setBlockEntity(MalumBlockEntities.VOID_CONDUIT));
    public static final BlockBlockItemHolder<Block, BlockItem> PRIMORDIAL_SOUP = registerBlock("primordial_soup", () -> new PrimordialSoupBlock(MalumBlockProperties.PRIMORDIAL_SOUP()));

    public static final BlockBlockItemHolder<Block, BlockItem> VOID_DEPOT = registerBlock("void_depot", () -> new VoidDepotBlock<>(MalumBlockProperties.WEEPING_WELL()).setBlockEntity(MalumBlockEntities.VOID_DEPOT));

    public static final BlockBlockItemHolder<Block, BlockItem> WEEPING_WELL_CENTER = registerBlock("weeping_well_center", () -> new WeepingWellLayeredBlock(MalumBlockProperties.WEEPING_WELL()));
    public static final BlockBlockItemHolder<Block, BlockItem> WEEPING_WELL_SIDE = registerBlock("weeping_well_side", () -> new WeepingWellLayeredBlock(MalumBlockProperties.WEEPING_WELL()));
    public static final BlockBlockItemHolder<Block, BlockItem> WEEPING_WELL_SIDE_MIRROR = registerBlock("weeping_well_side_mirror", () -> new WeepingWellLayeredBlock(MalumBlockProperties.WEEPING_WELL()));
    public static final BlockBlockItemHolder<Block, BlockItem> WEEPING_WELL_CORNER = registerBlock("weeping_well_corner", () -> new WeepingWellLayeredBlock(MalumBlockProperties.WEEPING_WELL()));
    public static final BlockBlockItemHolder<Block, BlockItem> WEEPING_WELL_FLAGSTONE = registerBlock("weeping_well_flagstone", () -> new WeepingWellBlock(MalumBlockProperties.WEEPING_WELL()));
    public static final BlockBlockItemHolder<Block, BlockItem> WEEPING_WELL_COLUMN_BASE = registerBlock("weeping_well_column_base", () -> new WeepingWellDirectionalBlock(MalumBlockProperties.WEEPING_WELL()));
    public static final BlockBlockItemHolder<Block, BlockItem> WEEPING_WELL_COLUMN = registerBlock("weeping_well_column", () -> new WeepingWellDirectionalBlock(MalumBlockProperties.WEEPING_WELL()));
    public static final BlockBlockItemHolder<Block, BlockItem> WEEPING_WELL_COLUMN_CAP = registerBlock("weeping_well_column_cap", () -> new WeepingWellDirectionalBlock(MalumBlockProperties.WEEPING_WELL()));
    //endregion

    //region spirited glass
    public static final BlockBlockItemHolder<Block, BlockItem> SACRED_SPIRITED_GLASS = registerBlock("sacred_spirited_glass", () -> new SpiritedGlassBlock(MalumBlockProperties.SPIRITED_GLASS()));
    public static final BlockBlockItemHolder<Block, BlockItem> WICKED_SPIRITED_GLASS = registerBlock("wicked_spirited_glass", () -> new SpiritedGlassBlock(MalumBlockProperties.SPIRITED_GLASS()));
    public static final BlockBlockItemHolder<Block, BlockItem> ARCANE_SPIRITED_GLASS = registerBlock("arcane_spirited_glass", () -> new SpiritedGlassBlock(MalumBlockProperties.SPIRITED_GLASS()));
    public static final BlockBlockItemHolder<Block, BlockItem> ELDRITCH_SPIRITED_GLASS = registerBlock("eldritch_spirited_glass", () -> new SpiritedGlassBlock(MalumBlockProperties.SPIRITED_GLASS()));
    public static final BlockBlockItemHolder<Block, BlockItem> AERIAL_SPIRITED_GLASS = registerBlock("aerial_spirited_glass", () -> new SpiritedGlassBlock(MalumBlockProperties.SPIRITED_GLASS()));
    public static final BlockBlockItemHolder<Block, BlockItem> AQUEOUS_SPIRITED_GLASS = registerBlock("aqueous_spirited_glass", () -> new SpiritedGlassBlock(MalumBlockProperties.SPIRITED_GLASS()));
    public static final BlockBlockItemHolder<Block, BlockItem> EARTHEN_SPIRITED_GLASS = registerBlock("earthen_spirited_glass", () -> new SpiritedGlassBlock(MalumBlockProperties.SPIRITED_GLASS()));
    public static final BlockBlockItemHolder<Block, BlockItem> INFERNAL_SPIRITED_GLASS = registerBlock("infernal_spirited_glass", () -> new SpiritedGlassBlock(MalumBlockProperties.SPIRITED_GLASS()));
    public static final BlockBlockItemHolder<Block, BlockItem> NULL_SPIRITED_GLASS = registerBlock("null_spirited_glass", () -> new SpiritedGlassBlock(MalumBlockProperties.SPIRITED_GLASS()));
    //endregion

    //region varnished terracotta
    public static final BlockBlockItemHolder<Block, BlockItem> SACRED_VARNISHED_TERRACOTTA = registerBlock("sacred_varnished_terracotta", () -> new VarnishedTerracottaBlock(MalumBlockProperties.VARNISHED_TERRACOTTA(DyeColor.RED)));
    public static final BlockBlockItemHolder<Block, BlockItem> WICKED_VARNISHED_TERRACOTTA = registerBlock("wicked_varnished_terracotta", () -> new VarnishedTerracottaBlock(MalumBlockProperties.VARNISHED_TERRACOTTA(DyeColor.PURPLE)));
    public static final BlockBlockItemHolder<Block, BlockItem> ARCANE_VARNISHED_TERRACOTTA = registerBlock("arcane_varnished_terracotta", () -> new VarnishedTerracottaBlock(MalumBlockProperties.VARNISHED_TERRACOTTA(DyeColor.PINK)));
    public static final BlockBlockItemHolder<Block, BlockItem> ELDRITCH_VARNISHED_TERRACOTTA = registerBlock("eldritch_varnished_terracotta", () -> new VarnishedTerracottaBlock(MalumBlockProperties.VARNISHED_TERRACOTTA(DyeColor.MAGENTA)));
    public static final BlockBlockItemHolder<Block, BlockItem> AERIAL_VARNISHED_TERRACOTTA = registerBlock("aerial_varnished_terracotta", () -> new VarnishedTerracottaBlock(MalumBlockProperties.VARNISHED_TERRACOTTA(DyeColor.LIGHT_BLUE)));
    public static final BlockBlockItemHolder<Block, BlockItem> AQUEOUS_VARNISHED_TERRACOTTA = registerBlock("aqueous_varnished_terracotta", () -> new VarnishedTerracottaBlock(MalumBlockProperties.VARNISHED_TERRACOTTA(DyeColor.BLUE)));
    public static final BlockBlockItemHolder<Block, BlockItem> EARTHEN_VARNISHED_TERRACOTTA = registerBlock("earthen_varnished_terracotta", () -> new VarnishedTerracottaBlock(MalumBlockProperties.VARNISHED_TERRACOTTA(DyeColor.GREEN)));
    public static final BlockBlockItemHolder<Block, BlockItem> INFERNAL_VARNISHED_TERRACOTTA = registerBlock("infernal_varnished_terracotta", () -> new VarnishedTerracottaBlock(MalumBlockProperties.VARNISHED_TERRACOTTA(DyeColor.YELLOW)));
    public static final BlockBlockItemHolder<Block, BlockItem> NULL_VARNISHED_TERRACOTTA = registerBlock("null_varnished_terracotta", () -> new VarnishedTerracottaBlock(MalumBlockProperties.VARNISHED_TERRACOTTA(DyeColor.BLACK)));

    //endregion
    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOVEN_BANNER = registerBlock("soulwoven_banner", () -> new SoulwovenBannerBlock(MalumBlockProperties.SOULWOVEN_BANNER()).setBlockEntity(MalumBlockEntities.SOULWOVEN_BANNER), SoulwovenBannerBlockItem::new);

    //region ether
    public static final BlockBlockItemHolder<Block, BlockItem> ETHER = registerBlock("ether", () -> new EtherBlock<>(MalumBlockProperties.ETHER()).setBlockEntity(MalumBlockEntities.ETHER));
    public static final BlockBlockItemHolder<Block, BlockItem> IRIDESCENT_ETHER = registerBlock("iridescent_ether", () -> new EtherBlock<>(MalumBlockProperties.ETHER()).setBlockEntity(MalumBlockEntities.ETHER));

    public static final BlockBlockItemHolder<Block, BlockItem> ETHER_CANDLE = registerBlock("ether_candle", () -> new EtherCandleBlock<>(MalumBlockProperties.ETHER_CANDLE()).setBlockEntity(MalumBlockEntities.ETHER_CANDLE));
    public static final BlockBlockItemHolder<Block, BlockItem> IRIDESCENT_ETHER_CANDLE = registerBlock("iridescent_ether_candle", () -> new EtherCandleBlock<>(MalumBlockProperties.ETHER_CANDLE()).setBlockEntity(MalumBlockEntities.ETHER_CANDLE));

    public static final BlockBlockItemHolder<Block, BlockItem> ETHER_TORCH = registerBlock("ether_torch", () -> new EtherTorchBlock<>(MalumBlockProperties.ETHER_TORCH()).setBlockEntity(MalumBlockEntities.ETHER_TORCH));
    public static final DeferredHolder<Block, Block> WALL_ETHER_TORCH = registerBlockNoItem("wall_ether_torch", () -> new EtherWallTorchBlock<>(MalumBlockProperties.ETHER_TORCH().lootFrom(ETHER_TORCH)).setBlockEntity(MalumBlockEntities.ETHER_TORCH));
    public static final BlockBlockItemHolder<Block, BlockItem> IRIDESCENT_ETHER_TORCH = registerBlock("iridescent_ether_torch", () -> new EtherTorchBlock<>(MalumBlockProperties.ETHER_TORCH()).setBlockEntity(MalumBlockEntities.ETHER_TORCH));
    public static final DeferredHolder<Block, Block> IRIDESCENT_WALL_ETHER_TORCH = registerBlockNoItem("iridescent_wall_ether_torch", () -> new EtherWallTorchBlock<>(MalumBlockProperties.ETHER_TORCH().lootFrom(IRIDESCENT_ETHER_TORCH)).setBlockEntity(MalumBlockEntities.ETHER_TORCH));

    public static final BlockBlockItemHolder<Block, BlockItem> TAINTED_ETHER_BRAZIER = registerBlock("tainted_ether_brazier", () -> new EtherBrazierBlock<>(MalumBlockProperties.TAINTED_ETHER_BRAZIER()).setBlockEntity(MalumBlockEntities.ETHER_BRAZIER));
    public static final BlockBlockItemHolder<Block, BlockItem> TWISTED_ETHER_BRAZIER = registerBlock("twisted_ether_brazier", () -> new EtherBrazierBlock<>(MalumBlockProperties.TWISTED_ETHER_BRAZIER()).setBlockEntity(MalumBlockEntities.ETHER_BRAZIER));
    public static final BlockBlockItemHolder<Block, BlockItem> DROSS_ETHER_BRAZIER = registerBlock("dross_ether_brazier", () -> new EtherBrazierBlock<>(MalumBlockProperties.DROSS_ETHER_BRAZIER()).setBlockEntity(MalumBlockEntities.ETHER_BRAZIER));
    public static final BlockBlockItemHolder<Block, BlockItem> TAINTED_IRIDESCENT_ETHER_BRAZIER = registerBlock("tainted_iridescent_ether_brazier", () -> new EtherBrazierBlock<>(MalumBlockProperties.TAINTED_ETHER_BRAZIER()).setBlockEntity(MalumBlockEntities.ETHER_BRAZIER));
    public static final BlockBlockItemHolder<Block, BlockItem> TWISTED_IRIDESCENT_ETHER_BRAZIER = registerBlock("twisted_iridescent_ether_brazier", () -> new EtherBrazierBlock<>(MalumBlockProperties.TWISTED_ETHER_BRAZIER()).setBlockEntity(MalumBlockEntities.ETHER_BRAZIER));
    public static final BlockBlockItemHolder<Block, BlockItem> DROSS_IRIDESCENT_ETHER_BRAZIER = registerBlock("dross_iridescent_ether_brazier", () -> new EtherBrazierBlock<>(MalumBlockProperties.DROSS_ETHER_BRAZIER()).setBlockEntity(MalumBlockEntities.ETHER_BRAZIER));

    public static final BlockBlockItemHolder<Block, BlockItem> TAINTED_ETHER_CRESSET = registerBlock("tainted_ether_cresset", () -> new EtherCressetBlock<>(MalumBlockProperties.TAINTED_ETHER_CRESSET()).setBlockEntity(MalumBlockEntities.ETHER_CRESSET));
    public static final BlockBlockItemHolder<Block, BlockItem> TWISTED_ETHER_CRESSET = registerBlock("twisted_ether_cresset", () -> new EtherCressetBlock<>(MalumBlockProperties.TWISTED_ETHER_CRESSET()).setBlockEntity(MalumBlockEntities.ETHER_CRESSET));
    public static final BlockBlockItemHolder<Block, BlockItem> DROSS_ETHER_CRESSET = registerBlock("dross_ether_cresset", () -> new EtherCressetBlock<>(MalumBlockProperties.DROSS_ETHER_CRESSET()).setBlockEntity(MalumBlockEntities.ETHER_CRESSET));
    public static final BlockBlockItemHolder<Block, BlockItem> TAINTED_IRIDESCENT_ETHER_CRESSET = registerBlock("tainted_iridescent_ether_cresset", () -> new EtherCressetBlock<>(MalumBlockProperties.TAINTED_ETHER_CRESSET()).setBlockEntity(MalumBlockEntities.ETHER_CRESSET));
    public static final BlockBlockItemHolder<Block, BlockItem> TWISTED_IRIDESCENT_ETHER_CRESSET = registerBlock("twisted_iridescent_ether_cresset", () -> new EtherCressetBlock<>(MalumBlockProperties.TWISTED_ETHER_CRESSET()).setBlockEntity(MalumBlockEntities.ETHER_CRESSET));
    public static final BlockBlockItemHolder<Block, BlockItem> DROSS_IRIDESCENT_ETHER_CRESSET = registerBlock("dross_iridescent_ether_cresset", () -> new EtherCressetBlock<>(MalumBlockProperties.DROSS_ETHER_CRESSET()).setBlockEntity(MalumBlockEntities.ETHER_CRESSET));
    //endregion

    //region tainted rock
    public static final BlockBlockItemHolder<Block, BlockItem> TAINTED_ROCK = registerBlock("tainted_rock", () -> new Block(MalumBlockProperties.TAINTED_ROCK().addTag(TAINTED_ROCK_BLOCKS)));
    public static final BlockBlockItemHolder<Block, BlockItem> POLISHED_TAINTED_ROCK = registerBlock("polished_tainted_rock", () -> new Block(MalumBlockProperties.TAINTED_ROCK().addTag(TAINTED_ROCK_BLOCKS)));
    public static final BlockBlockItemHolder<Block, BlockItem> TAINTED_ROCK_BRICKS = registerBlock("tainted_rock_bricks", () -> new Block(MalumBlockProperties.TAINTED_ROCK_BRICKS().addTag(TAINTED_ROCK_BLOCKS)));
    public static final BlockBlockItemHolder<Block, BlockItem> TAINTED_ROCK_TILES = registerBlock("tainted_rock_tiles", () -> new Block(MalumBlockProperties.TAINTED_ROCK_BRICKS().addTag(TAINTED_ROCK_BLOCKS)));
    public static final BlockBlockItemHolder<Block, BlockItem> TAINTED_ROCK_MOSAIC = registerBlock("tainted_rock_mosaic", () -> new Block(MalumBlockProperties.TAINTED_ROCK_BRICKS().addTag(TAINTED_ROCK_BLOCKS)));

    public static final BlockBlockItemHolder<Block, BlockItem> TAINTED_ROCK_COLUMN = registerBlock("tainted_rock_column", () -> new ColumnBlock(MalumBlockProperties.CHISELED_TAINTED_ROCK()));
    public static final BlockBlockItemHolder<Block, BlockItem> TAINTED_ROCK_ALTAR = registerBlock("tainted_rock_altar", () -> new Block(MalumBlockProperties.CHISELED_TAINTED_ROCK().addTag(EIDOLON_ALTAR_BLOCK)));
    public static final BlockBlockItemHolder<Block, BlockItem> CUT_TAINTED_ROCK = registerBlock("cut_tainted_rock", () -> new Block(MalumBlockProperties.CHISELED_TAINTED_ROCK()));
    public static final BlockBlockItemHolder<Block, BlockItem> CHISELED_TAINTED_ROCK = registerBlock("chiseled_tainted_rock", () -> new Block(MalumBlockProperties.CHISELED_TAINTED_ROCK()));

    public static final BlockBlockItemHolder<Block, BlockItem> TAINTED_ROCK_SLAB = registerBlock("tainted_rock_slab", () -> new SlabBlock(MalumBlockProperties.TAINTED_ROCK().addTags(SLABS, TAINTED_ROCK_SLABS)));
    public static final BlockBlockItemHolder<Block, BlockItem> POLISHED_TAINTED_ROCK_SLAB = registerBlock("polished_tainted_rock_slab", () -> new SlabBlock(MalumBlockProperties.TAINTED_ROCK().addTags(SLABS, TAINTED_ROCK_SLABS)));
    public static final BlockBlockItemHolder<Block, BlockItem> TAINTED_ROCK_BRICKS_SLAB = registerBlock("tainted_rock_bricks_slab", () -> new SlabBlock(MalumBlockProperties.TAINTED_ROCK_BRICKS().addTags(SLABS, TAINTED_ROCK_SLABS)));
    public static final BlockBlockItemHolder<Block, BlockItem> TAINTED_ROCK_TILES_SLAB = registerBlock("tainted_rock_tiles_slab", () -> new SlabBlock(MalumBlockProperties.TAINTED_ROCK_BRICKS().addTags(SLABS, TAINTED_ROCK_SLABS)));
    public static final BlockBlockItemHolder<Block, BlockItem> TAINTED_ROCK_MOSAIC_SLAB = registerBlock("tainted_rock_mosaic_slab", () -> new SlabBlock(MalumBlockProperties.TAINTED_ROCK_BRICKS().addTags(SLABS, TAINTED_ROCK_SLABS)));

    public static final BlockBlockItemHolder<Block, BlockItem> TAINTED_ROCK_STAIRS = registerBlock("tainted_rock_stairs", () -> new StairBlock(TAINTED_ROCK.get().defaultBlockState(), MalumBlockProperties.TAINTED_ROCK().addTags(STAIRS, MalumTags.Blocks.TAINTED_ROCK_STAIRS)));
    public static final BlockBlockItemHolder<Block, BlockItem> POLISHED_TAINTED_ROCK_STAIRS = registerBlock("polished_tainted_rock_stairs", () -> new StairBlock(TAINTED_ROCK.get().defaultBlockState(), MalumBlockProperties.TAINTED_ROCK().addTags(STAIRS, MalumTags.Blocks.TAINTED_ROCK_STAIRS)));
    public static final BlockBlockItemHolder<Block, BlockItem> TAINTED_ROCK_BRICKS_STAIRS = registerBlock("tainted_rock_bricks_stairs", () -> new StairBlock(TAINTED_ROCK.get().defaultBlockState(), MalumBlockProperties.TAINTED_ROCK_BRICKS().addTags(STAIRS, MalumTags.Blocks.TAINTED_ROCK_STAIRS)));
    public static final BlockBlockItemHolder<Block, BlockItem> TAINTED_ROCK_TILES_STAIRS = registerBlock("tainted_rock_tiles_stairs", () -> new StairBlock(TAINTED_ROCK.get().defaultBlockState(), MalumBlockProperties.TAINTED_ROCK_BRICKS().addTags(STAIRS, MalumTags.Blocks.TAINTED_ROCK_STAIRS)));
    public static final BlockBlockItemHolder<Block, BlockItem> TAINTED_ROCK_MOSAIC_STAIRS = registerBlock("tainted_rock_mosaic_stairs", () -> new StairBlock(TAINTED_ROCK.get().defaultBlockState(), MalumBlockProperties.TAINTED_ROCK_BRICKS().addTags(STAIRS, MalumTags.Blocks.TAINTED_ROCK_STAIRS)));

    public static final BlockBlockItemHolder<Block, BlockItem> TAINTED_ROCK_WALL = registerBlock("tainted_rock_wall", () -> new WallBlock(MalumBlockProperties.TAINTED_ROCK().addTags(WALLS, TAINTED_ROCK_WALLS)));
    public static final BlockBlockItemHolder<Block, BlockItem> POLISHED_TAINTED_ROCK_WALL = registerBlock("polished_tainted_rock_wall", () -> new WallBlock(MalumBlockProperties.TAINTED_ROCK().addTags(WALLS, TAINTED_ROCK_WALLS)));
    public static final BlockBlockItemHolder<Block, BlockItem> TAINTED_ROCK_BRICKS_WALL = registerBlock("tainted_rock_bricks_wall", () -> new WallBlock(MalumBlockProperties.TAINTED_ROCK_BRICKS().addTags(WALLS, TAINTED_ROCK_WALLS)));
    public static final BlockBlockItemHolder<Block, BlockItem> TAINTED_ROCK_TILES_WALL = registerBlock("tainted_rock_tiles_wall", () -> new WallBlock(MalumBlockProperties.TAINTED_ROCK_BRICKS().addTags(WALLS, TAINTED_ROCK_WALLS)));
    public static final BlockBlockItemHolder<Block, BlockItem> TAINTED_ROCK_MOSAIC_WALL = registerBlock("tainted_rock_mosaic_wall", () -> new WallBlock(MalumBlockProperties.TAINTED_ROCK_BRICKS().addTags(WALLS, TAINTED_ROCK_WALLS)));

    public static final BlockBlockItemHolder<Block, BlockItem> TAINTED_ROCK_BUTTON = registerBlock("tainted_rock_button", () -> new ButtonBlock(BlockSetType.STONE, 20, MalumBlockProperties.TAINTED_ROCK().noCollission().addTag(BUTTONS)));
    public static final BlockBlockItemHolder<Block, BlockItem> TAINTED_ROCK_PRESSURE_PLATE = registerBlock("tainted_rock_pressure_plate", () -> new PressurePlateBlock(BlockSetType.STONE, MalumBlockProperties.TAINTED_ROCK().noCollission().addTag(PRESSURE_PLATES)));

    public static final BlockBlockItemHolder<Block, BlockItem> TAINTED_ROCK_ITEM_STAND = registerBlock("tainted_rock_item_stand", () -> new ItemStandBlock<>(MalumBlockProperties.TAINTED_ROCK().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_STAND));
    public static final BlockBlockItemHolder<Block, BlockItem> TAINTED_ROCK_ITEM_PEDESTAL = registerBlock("tainted_rock_item_pedestal", () -> new ItemPedestalBlock<>(MalumBlockProperties.TAINTED_ROCK().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_PEDESTAL));
    //endregion

    //region twisted rock
    public static final BlockBlockItemHolder<Block, BlockItem> TWISTED_ROCK = registerBlock("twisted_rock", () -> new Block(MalumBlockProperties.TWISTED_ROCK().addTag(TWISTED_ROCK_BLOCKS)));
    public static final BlockBlockItemHolder<Block, BlockItem> POLISHED_TWISTED_ROCK = registerBlock("polished_twisted_rock", () -> new Block(MalumBlockProperties.TWISTED_ROCK().addTag(TWISTED_ROCK_BLOCKS)));
    public static final BlockBlockItemHolder<Block, BlockItem> TWISTED_ROCK_BRICKS = registerBlock("twisted_rock_bricks", () -> new Block(MalumBlockProperties.TWISTED_ROCK_BRICKS().addTag(TWISTED_ROCK_BLOCKS)));
    public static final BlockBlockItemHolder<Block, BlockItem> TWISTED_ROCK_TILES = registerBlock("twisted_rock_tiles", () -> new Block(MalumBlockProperties.TWISTED_ROCK_BRICKS().addTag(TWISTED_ROCK_BLOCKS)));
    public static final BlockBlockItemHolder<Block, BlockItem> TWISTED_ROCK_MOSAIC = registerBlock("twisted_rock_mosaic", () -> new Block(MalumBlockProperties.TWISTED_ROCK_BRICKS().addTag(TWISTED_ROCK_BLOCKS)));

    public static final BlockBlockItemHolder<Block, BlockItem> TWISTED_ROCK_COLUMN = registerBlock("twisted_rock_column", () -> new ColumnBlock(MalumBlockProperties.CHISELED_TWISTED_ROCK()));
    public static final BlockBlockItemHolder<Block, BlockItem> TWISTED_ROCK_ALTAR = registerBlock("twisted_rock_altar", () -> new Block(MalumBlockProperties.CHISELED_TWISTED_ROCK().addTag(EIDOLON_ALTAR_BLOCK)));
    public static final BlockBlockItemHolder<Block, BlockItem> CUT_TWISTED_ROCK = registerBlock("cut_twisted_rock", () -> new Block(MalumBlockProperties.CHISELED_TWISTED_ROCK()));
    public static final BlockBlockItemHolder<Block, BlockItem> CHISELED_TWISTED_ROCK = registerBlock("chiseled_twisted_rock", () -> new Block(MalumBlockProperties.CHISELED_TWISTED_ROCK()));

    public static final BlockBlockItemHolder<Block, BlockItem> TWISTED_ROCK_SLAB = registerBlock("twisted_rock_slab", () -> new SlabBlock(MalumBlockProperties.TWISTED_ROCK().addTags(SLABS, TWISTED_ROCK_SLABS)));
    public static final BlockBlockItemHolder<Block, BlockItem> POLISHED_TWISTED_ROCK_SLAB = registerBlock("polished_twisted_rock_slab", () -> new SlabBlock(MalumBlockProperties.TWISTED_ROCK().addTags(SLABS, TWISTED_ROCK_SLABS)));
    public static final BlockBlockItemHolder<Block, BlockItem> TWISTED_ROCK_BRICKS_SLAB = registerBlock("twisted_rock_bricks_slab", () -> new SlabBlock(MalumBlockProperties.TWISTED_ROCK_BRICKS().addTags(SLABS, TWISTED_ROCK_SLABS)));
    public static final BlockBlockItemHolder<Block, BlockItem> TWISTED_ROCK_TILES_SLAB = registerBlock("twisted_rock_tiles_slab", () -> new SlabBlock(MalumBlockProperties.TWISTED_ROCK_BRICKS().addTags(SLABS, TWISTED_ROCK_SLABS)));
    public static final BlockBlockItemHolder<Block, BlockItem> TWISTED_ROCK_MOSAIC_SLAB = registerBlock("twisted_rock_mosaic_slab", () -> new SlabBlock(MalumBlockProperties.TWISTED_ROCK_BRICKS().addTags(SLABS, TWISTED_ROCK_SLABS)));

    public static final BlockBlockItemHolder<Block, BlockItem> TWISTED_ROCK_STAIRS = registerBlock("twisted_rock_stairs", () -> new StairBlock(TWISTED_ROCK.get().defaultBlockState(), MalumBlockProperties.TWISTED_ROCK().addTags(STAIRS, MalumTags.Blocks.TWISTED_ROCK_STAIRS)));
    public static final BlockBlockItemHolder<Block, BlockItem> POLISHED_TWISTED_ROCK_STAIRS = registerBlock("polished_twisted_rock_stairs", () -> new StairBlock(TWISTED_ROCK.get().defaultBlockState(), MalumBlockProperties.TWISTED_ROCK().addTags(STAIRS, MalumTags.Blocks.TWISTED_ROCK_STAIRS)));
    public static final BlockBlockItemHolder<Block, BlockItem> TWISTED_ROCK_BRICKS_STAIRS = registerBlock("twisted_rock_bricks_stairs", () -> new StairBlock(TWISTED_ROCK.get().defaultBlockState(), MalumBlockProperties.TWISTED_ROCK_BRICKS().addTags(STAIRS, MalumTags.Blocks.TWISTED_ROCK_STAIRS)));
    public static final BlockBlockItemHolder<Block, BlockItem> TWISTED_ROCK_TILES_STAIRS = registerBlock("twisted_rock_tiles_stairs", () -> new StairBlock(TWISTED_ROCK.get().defaultBlockState(), MalumBlockProperties.TWISTED_ROCK_BRICKS().addTags(STAIRS, MalumTags.Blocks.TWISTED_ROCK_STAIRS)));
    public static final BlockBlockItemHolder<Block, BlockItem> TWISTED_ROCK_MOSAIC_STAIRS = registerBlock("twisted_rock_mosaic_stairs", () -> new StairBlock(TWISTED_ROCK.get().defaultBlockState(), MalumBlockProperties.TWISTED_ROCK_BRICKS().addTags(STAIRS, MalumTags.Blocks.TWISTED_ROCK_STAIRS)));

    public static final BlockBlockItemHolder<Block, BlockItem> TWISTED_ROCK_WALL = registerBlock("twisted_rock_wall", () -> new WallBlock(MalumBlockProperties.TWISTED_ROCK().addTags(WALLS, TWISTED_ROCK_WALLS)));
    public static final BlockBlockItemHolder<Block, BlockItem> POLISHED_TWISTED_ROCK_WALL = registerBlock("polished_twisted_rock_wall", () -> new WallBlock(MalumBlockProperties.TWISTED_ROCK().addTags(WALLS, TWISTED_ROCK_WALLS)));
    public static final BlockBlockItemHolder<Block, BlockItem> TWISTED_ROCK_BRICKS_WALL = registerBlock("twisted_rock_bricks_wall", () -> new WallBlock(MalumBlockProperties.TWISTED_ROCK_BRICKS().addTags(WALLS, TWISTED_ROCK_WALLS)));
    public static final BlockBlockItemHolder<Block, BlockItem> TWISTED_ROCK_TILES_WALL = registerBlock("twisted_rock_tiles_wall", () -> new WallBlock(MalumBlockProperties.TWISTED_ROCK_BRICKS().addTags(WALLS, TWISTED_ROCK_WALLS)));
    public static final BlockBlockItemHolder<Block, BlockItem> TWISTED_ROCK_MOSAIC_WALL = registerBlock("twisted_rock_mosaic_wall", () -> new WallBlock(MalumBlockProperties.TWISTED_ROCK_BRICKS().addTags(WALLS, TWISTED_ROCK_WALLS)));

    public static final BlockBlockItemHolder<Block, BlockItem> TWISTED_ROCK_BUTTON = registerBlock("twisted_rock_button", () -> new ButtonBlock(BlockSetType.STONE, 20, MalumBlockProperties.TWISTED_ROCK().noCollission().addTag(BUTTONS)));
    public static final BlockBlockItemHolder<Block, BlockItem> TWISTED_ROCK_PRESSURE_PLATE = registerBlock("twisted_rock_pressure_plate", () -> new PressurePlateBlock(BlockSetType.STONE, MalumBlockProperties.TWISTED_ROCK().noCollission().addTag(PRESSURE_PLATES)));

    public static final BlockBlockItemHolder<Block, BlockItem> TWISTED_ROCK_ITEM_STAND = registerBlock("twisted_rock_item_stand", () -> new ItemStandBlock<>(MalumBlockProperties.TWISTED_ROCK().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_STAND));
    public static final BlockBlockItemHolder<Block, BlockItem> TWISTED_ROCK_ITEM_PEDESTAL = registerBlock("twisted_rock_item_pedestal", () -> new ItemPedestalBlock<>(MalumBlockProperties.TWISTED_ROCK().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_PEDESTAL));
    //endregion

    //region dross stone
    public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE = registerBlock("dross_stone", () -> new Block(MalumBlockProperties.DROSS_STONE().addTag(DROSS_STONE_BLOCKS)));
    public static final BlockBlockItemHolder<Block, BlockItem> POLISHED_DROSS_STONE = registerBlock("polished_dross_stone", () -> new Block(MalumBlockProperties.DROSS_STONE().addTag(DROSS_STONE_BLOCKS)));
    public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_BRICKS = registerBlock("dross_stone_bricks", () -> new Block(MalumBlockProperties.DROSS_STONE_BRICKS().addTag(DROSS_STONE_BLOCKS)));
    public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_TILES = registerBlock("dross_stone_tiles", () -> new Block(MalumBlockProperties.DROSS_STONE_BRICKS().addTag(DROSS_STONE_BLOCKS)));
    public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_MOSAIC = registerBlock("dross_stone_mosaic", () -> new Block(MalumBlockProperties.DROSS_STONE_BRICKS().addTag(DROSS_STONE_BLOCKS)));

    public static final BlockBlockItemHolder<Block, BlockItem> DARK_DROSS_TILES = registerBlock("dark_dross_tiles", () -> new Block(MalumBlockProperties.DROSS_STONE()));
    public static final BlockBlockItemHolder<Block, BlockItem> GRAY_DROSS_TILES = registerBlock("gray_dross_tiles", () -> new Block(MalumBlockProperties.DROSS_STONE()));

    public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_COLUMN = registerBlock("dross_stone_column", () -> new ColumnBlock(MalumBlockProperties.CHISELED_DROSS_STONE()));
    public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_ALTAR = registerBlock("dross_stone_altar", () -> new Block(MalumBlockProperties.CHISELED_DROSS_STONE().addTag(EIDOLON_ALTAR_BLOCK)));
    public static final BlockBlockItemHolder<Block, BlockItem> CUT_DROSS_STONE = registerBlock("cut_dross_stone", () -> new Block(MalumBlockProperties.CHISELED_DROSS_STONE()));
    public static final BlockBlockItemHolder<Block, BlockItem> CHISELED_DROSS_STONE = registerBlock("chiseled_dross_stone", () -> new Block(MalumBlockProperties.CHISELED_DROSS_STONE()));

    public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_SLAB = registerBlock("dross_stone_slab", () -> new SlabBlock(MalumBlockProperties.DROSS_STONE().addTags(SLABS, DROSS_STONE_SLABS)));
    public static final BlockBlockItemHolder<Block, BlockItem> POLISHED_DROSS_STONE_SLAB = registerBlock("polished_dross_stone_slab", () -> new SlabBlock(MalumBlockProperties.DROSS_STONE().addTags(SLABS, DROSS_STONE_SLABS)));
    public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_BRICKS_SLAB = registerBlock("dross_stone_bricks_slab", () -> new SlabBlock(MalumBlockProperties.DROSS_STONE_BRICKS().addTags(SLABS, DROSS_STONE_SLABS)));
    public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_TILES_SLAB = registerBlock("dross_stone_tiles_slab", () -> new SlabBlock(MalumBlockProperties.DROSS_STONE_BRICKS().addTags(SLABS, DROSS_STONE_SLABS)));
    public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_MOSAIC_SLAB = registerBlock("dross_stone_mosaic_slab", () -> new SlabBlock(MalumBlockProperties.DROSS_STONE_BRICKS().addTags(SLABS, DROSS_STONE_SLABS)));

    public static final BlockBlockItemHolder<Block, BlockItem> DARK_DROSS_TILES_SLAB = registerBlock("dark_dross_tiles_slab", () -> new SlabBlock(MalumBlockProperties.DROSS_STONE().addTags(SLABS)));
    public static final BlockBlockItemHolder<Block, BlockItem> GRAY_DROSS_TILES_SLAB = registerBlock("gray_dross_tiles_slab", () -> new SlabBlock(MalumBlockProperties.DROSS_STONE().addTags(SLABS)));

    public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_STAIRS = registerBlock("dross_stone_stairs", () -> new StairBlock(DROSS_STONE.get().defaultBlockState(), MalumBlockProperties.DROSS_STONE().addTags(STAIRS, MalumTags.Blocks.DROSS_STONE_STAIRS)));
    public static final BlockBlockItemHolder<Block, BlockItem> POLISHED_DROSS_STONE_STAIRS = registerBlock("polished_dross_stone_stairs", () -> new StairBlock(DROSS_STONE.get().defaultBlockState(), MalumBlockProperties.DROSS_STONE().addTags(STAIRS, MalumTags.Blocks.DROSS_STONE_STAIRS)));
    public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_BRICKS_STAIRS = registerBlock("dross_stone_bricks_stairs", () -> new StairBlock(DROSS_STONE.get().defaultBlockState(), MalumBlockProperties.DROSS_STONE_BRICKS().addTags(STAIRS, MalumTags.Blocks.DROSS_STONE_STAIRS)));
    public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_TILES_STAIRS = registerBlock("dross_stone_tiles_stairs", () -> new StairBlock(DROSS_STONE.get().defaultBlockState(), MalumBlockProperties.DROSS_STONE_BRICKS().addTags(STAIRS, MalumTags.Blocks.DROSS_STONE_STAIRS)));
    public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_MOSAIC_STAIRS = registerBlock("dross_stone_mosaic_stairs", () -> new StairBlock(DROSS_STONE.get().defaultBlockState(), MalumBlockProperties.DROSS_STONE_BRICKS().addTags(STAIRS, MalumTags.Blocks.DROSS_STONE_STAIRS)));

    public static final BlockBlockItemHolder<Block, BlockItem> DARK_DROSS_TILES_STAIRS = registerBlock("dark_dross_tiles_stairs", () -> new StairBlock(DROSS_STONE.get().defaultBlockState(), MalumBlockProperties.DROSS_STONE().addTags(STAIRS)));
    public static final BlockBlockItemHolder<Block, BlockItem> GRAY_DROSS_TILES_STAIRS = registerBlock("gray_dross_tiles_stairs", () -> new StairBlock(DROSS_STONE.get().defaultBlockState(), MalumBlockProperties.DROSS_STONE().addTags(STAIRS)));

    public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_WALL = registerBlock("dross_stone_wall", () -> new WallBlock(MalumBlockProperties.DROSS_STONE().addTags(WALLS, DROSS_STONE_WALLS)));
    public static final BlockBlockItemHolder<Block, BlockItem> POLISHED_DROSS_STONE_WALL = registerBlock("polished_dross_stone_wall", () -> new WallBlock(MalumBlockProperties.DROSS_STONE().addTags(WALLS, DROSS_STONE_WALLS)));
    public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_BRICKS_WALL = registerBlock("dross_stone_bricks_wall", () -> new WallBlock(MalumBlockProperties.DROSS_STONE_BRICKS().addTags(WALLS, DROSS_STONE_WALLS)));
    public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_TILES_WALL = registerBlock("dross_stone_tiles_wall", () -> new WallBlock(MalumBlockProperties.DROSS_STONE_BRICKS().addTags(WALLS, DROSS_STONE_WALLS)));
    public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_MOSAIC_WALL = registerBlock("dross_stone_mosaic_wall", () -> new WallBlock(MalumBlockProperties.DROSS_STONE_BRICKS().addTags(WALLS, DROSS_STONE_WALLS)));

    public static final BlockBlockItemHolder<Block, BlockItem> DARK_DROSS_TILES_WALL = registerBlock("dark_dross_tiles_wall", () -> new WallBlock(MalumBlockProperties.DROSS_STONE().addTags(WALLS)));
    public static final BlockBlockItemHolder<Block, BlockItem> GRAY_DROSS_TILES_WALL = registerBlock("gray_dross_tiles_wall", () -> new WallBlock(MalumBlockProperties.DROSS_STONE().addTags(WALLS)));

    public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_BUTTON = registerBlock("dross_stone_button", () -> new ButtonBlock(BlockSetType.STONE, 20, MalumBlockProperties.DROSS_STONE().noCollission().addTag(BUTTONS)));
    public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_PRESSURE_PLATE = registerBlock("dross_stone_pressure_plate", () -> new PressurePlateBlock(BlockSetType.STONE, MalumBlockProperties.DROSS_STONE().noCollission().addTag(PRESSURE_PLATES)));

    public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_ITEM_STAND = registerBlock("dross_stone_item_stand", () -> new ItemStandBlock<>(MalumBlockProperties.DROSS_STONE().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_STAND));
    public static final BlockBlockItemHolder<Block, BlockItem> DROSS_STONE_ITEM_PEDESTAL = registerBlock("dross_stone_item_pedestal", () -> new ItemPedestalBlock<>(MalumBlockProperties.DROSS_STONE().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_PEDESTAL));
    //endregion


    //region runewood
    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_SAPLING = registerBlock("runewood_sapling", () -> new MalumSaplingBlock(MalumTreeGrowers.RUNEWOOD, MalumBlockProperties.RUNEWOOD_SAPLING()));
    public static final BlockBlockItemHolder<Block, BlockItem> AZURE_RUNEWOOD_SAPLING = registerBlock("azure_runewood_sapling", () -> new MalumSaplingBlock(MalumTreeGrowers.AZURE_RUNEWOOD, MalumBlockProperties.RUNEWOOD_SAPLING()));

    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_LEAVES = registerBlock("runewood_leaves", () -> new MalumLeavesBlock(MalumBlockProperties.RUNEWOOD_LEAVES(), MalumBlockProperties.RUNEWOOD_LEAVES_ORANGE, MalumBlockProperties.RUNEWOOD_LEAVES_YELLOW));
    public static final BlockBlockItemHolder<Block, BlockItem> AZURE_RUNEWOOD_LEAVES = registerBlock("azure_runewood_leaves", () -> new MalumLeavesBlock(MalumBlockProperties.RUNEWOOD_LEAVES(), MalumBlockProperties.AZURE_RUNEWOOD_LEAVES_BLUE, MalumBlockProperties.AZURE_RUNEWOOD_LEAVES_CYAN));

    public static final BlockBlockItemHolder<Block, BlockItem> HANGING_RUNEWOOD_LEAVES = registerBlock("hanging_runewood_leaves", () -> new MalumHangingLeavesBlock(MalumBlockProperties.HANGING_RUNEWOOD_LEAVES().setCutoutRenderType().noOcclusion().noCollission(), MalumBlockProperties.RUNEWOOD_LEAVES_ORANGE, MalumBlockProperties.RUNEWOOD_LEAVES_YELLOW));
    public static final BlockBlockItemHolder<Block, BlockItem> HANGING_AZURE_RUNEWOOD_LEAVES = registerBlock("hanging_azure_runewood_leaves", () -> new MalumHangingLeavesBlock(MalumBlockProperties.HANGING_RUNEWOOD_LEAVES().setCutoutRenderType().noOcclusion().noCollission(), MalumBlockProperties.AZURE_RUNEWOOD_LEAVES_BLUE, MalumBlockProperties.AZURE_RUNEWOOD_LEAVES_CYAN));

    public static final BlockBlockItemHolder<Block, BlockItem> STRIPPED_RUNEWOOD_LOG = registerBlock("stripped_runewood_log", () -> new RotatedPillarBlock(MalumBlockProperties.RUNEWOOD_LOGS().addTags(STRIPPED_LOGS)));
    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_LOG = registerBlock("runewood_log", () -> new MalumLogBlock(MalumBlockProperties.RUNEWOOD(), STRIPPED_RUNEWOOD_LOG));

    public static final BlockBlockItemHolder<Block, BlockItem> STRIPPED_RUNEWOOD = registerBlock("stripped_runewood", () -> new RotatedPillarBlock(MalumBlockProperties.RUNEWOOD_LOGS().addTags(STRIPPED_WOODS)));
    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD = registerBlock("runewood", () -> new LodestoneLogBlock(MalumBlockProperties.RUNEWOOD(), STRIPPED_RUNEWOOD));

    public static final BlockBlockItemHolder<Block, BlockItem> STRIPPED_SAPPY_RUNEWOOD_LOG = registerBlock("stripped_sappy_runewood_log", () -> new SapFilledLogBlock(MalumBlockProperties.RUNEWOOD_LOGS(), STRIPPED_RUNEWOOD_LOG, MalumItems.RUNIC_SAP, MalumSpiritTypes.INFERNAL_COLORS().primaryColor()));
    public static final BlockBlockItemHolder<Block, BlockItem> SAPPY_RUNEWOOD_LOG = registerBlock("sappy_runewood_log", () -> new LodestoneLogBlock(MalumBlockProperties.RUNEWOOD().addTags(STRIPPED_LOGS), STRIPPED_SAPPY_RUNEWOOD_LOG));

    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_BOARDS = registerBlock("runewood_boards", () -> new Block(MalumBlockProperties.RUNEWOOD_PLANKS()));
    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_BOARDS_SLAB = registerBlock("runewood_boards_slab", () -> new SlabBlock(MalumBlockProperties.RUNEWOOD_SLABS()));
    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_BOARDS_STAIRS = registerBlock("runewood_boards_stairs", () -> new StairBlock(RUNEWOOD_BOARDS.get().defaultBlockState(), MalumBlockProperties.RUNEWOOD_STAIRS()));

    public static final BlockBlockItemHolder<Block, BlockItem> VERTICAL_RUNEWOOD_BOARDS = registerBlock("vertical_runewood_boards", () -> new Block(MalumBlockProperties.RUNEWOOD_PLANKS()));
    public static final BlockBlockItemHolder<Block, BlockItem> VERTICAL_RUNEWOOD_BOARDS_SLAB = registerBlock("vertical_runewood_boards_slab", () -> new SlabBlock(MalumBlockProperties.RUNEWOOD_SLABS()));
    public static final BlockBlockItemHolder<Block, BlockItem> VERTICAL_RUNEWOOD_BOARDS_STAIRS = registerBlock("vertical_runewood_boards_stairs", () -> new StairBlock(VERTICAL_RUNEWOOD_BOARDS.get().defaultBlockState(), MalumBlockProperties.RUNEWOOD_STAIRS()));

    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_PLANKS = registerBlock("runewood_planks", () -> new Block(MalumBlockProperties.RUNEWOOD_PLANKS()));
    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_PLANKS_SLAB = registerBlock("runewood_planks_slab", () -> new SlabBlock(MalumBlockProperties.RUNEWOOD_SLABS()));
    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_PLANKS_STAIRS = registerBlock("runewood_planks_stairs", () -> new StairBlock(RUNEWOOD_PLANKS.get().defaultBlockState(), MalumBlockProperties.RUNEWOOD_STAIRS()));

    public static final BlockBlockItemHolder<Block, BlockItem> RUSTIC_RUNEWOOD_PLANKS = registerBlock("rustic_runewood_planks", () -> new Block(MalumBlockProperties.RUNEWOOD_PLANKS()));
    public static final BlockBlockItemHolder<Block, BlockItem> RUSTIC_RUNEWOOD_PLANKS_SLAB = registerBlock("rustic_runewood_planks_slab", () -> new SlabBlock(MalumBlockProperties.RUNEWOOD_SLABS()));
    public static final BlockBlockItemHolder<Block, BlockItem> RUSTIC_RUNEWOOD_PLANKS_STAIRS = registerBlock("rustic_runewood_planks_stairs", () -> new StairBlock(RUSTIC_RUNEWOOD_PLANKS.get().defaultBlockState(), MalumBlockProperties.RUNEWOOD_STAIRS()));

    public static final BlockBlockItemHolder<Block, BlockItem> VERTICAL_RUNEWOOD_PLANKS = registerBlock("vertical_runewood_planks", () -> new Block(MalumBlockProperties.RUNEWOOD_PLANKS()));
    public static final BlockBlockItemHolder<Block, BlockItem> VERTICAL_RUNEWOOD_PLANKS_SLAB = registerBlock("vertical_runewood_planks_slab", () -> new SlabBlock(MalumBlockProperties.RUNEWOOD_SLABS()));
    public static final BlockBlockItemHolder<Block, BlockItem> VERTICAL_RUNEWOOD_PLANKS_STAIRS = registerBlock("vertical_runewood_planks_stairs", () -> new StairBlock(VERTICAL_RUNEWOOD_PLANKS.get().defaultBlockState(), MalumBlockProperties.RUNEWOOD_STAIRS()));

    public static final BlockBlockItemHolder<Block, BlockItem> VERTICAL_RUSTIC_RUNEWOOD_PLANKS = registerBlock("vertical_rustic_runewood_planks", () -> new Block(MalumBlockProperties.RUNEWOOD_PLANKS()));
    public static final BlockBlockItemHolder<Block, BlockItem> VERTICAL_RUSTIC_RUNEWOOD_PLANKS_SLAB = registerBlock("vertical_rustic_runewood_planks_slab", () -> new SlabBlock(MalumBlockProperties.RUNEWOOD_SLABS()));
    public static final BlockBlockItemHolder<Block, BlockItem> VERTICAL_RUSTIC_RUNEWOOD_PLANKS_STAIRS = registerBlock("vertical_rustic_runewood_planks_stairs", () -> new StairBlock(VERTICAL_RUSTIC_RUNEWOOD_PLANKS.get().defaultBlockState(), MalumBlockProperties.RUNEWOOD_STAIRS()));

    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_TILES = registerBlock("runewood_tiles", () -> new Block(MalumBlockProperties.RUNEWOOD_PLANKS()));
    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_TILES_SLAB = registerBlock("runewood_tiles_slab", () -> new SlabBlock(MalumBlockProperties.RUNEWOOD_SLABS()));
    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_TILES_STAIRS = registerBlock("runewood_tiles_stairs", () -> new StairBlock(RUNEWOOD_TILES.get().defaultBlockState(), MalumBlockProperties.RUNEWOOD_STAIRS()));

    public static final BlockBlockItemHolder<Block, BlockItem> RUSTIC_RUNEWOOD_TILES = registerBlock("rustic_runewood_tiles", () -> new Block(MalumBlockProperties.RUNEWOOD_PLANKS()));
    public static final BlockBlockItemHolder<Block, BlockItem> RUSTIC_RUNEWOOD_TILES_SLAB = registerBlock("rustic_runewood_tiles_slab", () -> new SlabBlock(MalumBlockProperties.RUNEWOOD_SLABS()));
    public static final BlockBlockItemHolder<Block, BlockItem> RUSTIC_RUNEWOOD_TILES_STAIRS = registerBlock("rustic_runewood_tiles_stairs", () -> new StairBlock(RUSTIC_RUNEWOOD_TILES.get().defaultBlockState(), MalumBlockProperties.RUNEWOOD_STAIRS()));

    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_PANEL = registerBlock("runewood_panel", () -> new Block(MalumBlockProperties.RUNEWOOD()));
    public static final BlockBlockItemHolder<Block, BlockItem> CUT_RUNEWOOD_PLANKS = registerBlock("cut_runewood_planks", () -> new Block(MalumBlockProperties.RUNEWOOD_PLANKS()));
    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_BEAM = registerBlock("runewood_beam", () -> new RotatedPillarBlock(MalumBlockProperties.RUNEWOOD()));

    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_DOOR = registerBlock("runewood_door", () -> new DoorBlock(MalumBlockSetTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD_DOOR()));
    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_BOARDS_DOOR = registerBlock("runewood_boards_door", () -> new DoorBlock(MalumBlockSetTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD_DOOR()));
    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_TRAPDOOR = registerBlock("runewood_trapdoor", () -> new TrapDoorBlock(MalumBlockSetTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD_TRAPDOOR()));
    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_BOARDS_TRAPDOOR = registerBlock("runewood_boards_trapdoor", () -> new TrapDoorBlock(MalumBlockSetTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD_TRAPDOOR()));

    public static final BlockBlockItemHolder<Block, BlockItem> BOLTED_RUNEWOOD_DOOR = registerBlock("bolted_runewood_door", () -> new DoorBlock(MalumBlockSetTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD_DOOR()));
    public static final BlockBlockItemHolder<Block, BlockItem> BOLTED_RUNEWOOD_BOARDS_DOOR = registerBlock("bolted_runewood_boards_door", () -> new DoorBlock(MalumBlockSetTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD_DOOR()));
    public static final BlockBlockItemHolder<Block, BlockItem> BOLTED_RUNEWOOD_TRAPDOOR = registerBlock("bolted_runewood_trapdoor", () -> new TrapDoorBlock(MalumBlockSetTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD_TRAPDOOR()));
    public static final BlockBlockItemHolder<Block, BlockItem> BOLTED_RUNEWOOD_BOARDS_TRAPDOOR = registerBlock("bolted_runewood_boards_trapdoor", () -> new TrapDoorBlock(MalumBlockSetTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD_TRAPDOOR()));

    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_BUTTON = registerBlock("runewood_planks_button", () -> new ButtonBlock(MalumBlockSetTypes.RUNEWOOD, 20, MalumBlockProperties.RUNEWOOD().noCollission().addTags(BUTTONS, WOODEN_BUTTONS).addTags(BUTTONS, WOODEN_BUTTONS)));
    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_PRESSURE_PLATE = registerBlock("runewood_planks_pressure_plate", () -> new PressurePlateBlock(MalumBlockSetTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD().noCollission().addTags(PRESSURE_PLATES, WOODEN_PRESSURE_PLATES)));

    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_BOARDS_WALL = registerBlock("runewood_boards_wall", () -> new WallBlock(MalumBlockProperties.RUNEWOOD().addTags(WALLS)));
    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_FENCE = registerBlock("runewood_planks_fence", () -> new FenceBlock(MalumBlockProperties.RUNEWOOD().addTags(FENCES, WOODEN_FENCES)));
    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_FENCE_GATE = registerBlock("runewood_planks_fence_gate", () -> new FenceGateBlock(MalumWoodTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD().addTags(FENCE_GATES, FENCE_GATES_WOODEN)));

    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_ITEM_STAND = registerBlock("runewood_item_stand", () -> new ItemStandBlock<>(MalumBlockProperties.RUNEWOOD().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_STAND));
    public static final BlockBlockItemHolder<Block, BlockItem> GILDED_RUNEWOOD_ITEM_PEDESTAL = registerBlock("gilded_runewood_item_pedestal", () -> new DecoratedItemPedestalBlock<>(MalumBlockProperties.RUNEWOOD().setCutoutRenderType().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_PEDESTAL));
    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_ITEM_PEDESTAL = registerBlock("runewood_item_pedestal", () -> new WoodItemPedestalBlock<>(MalumBlockProperties.RUNEWOOD().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_PEDESTAL));
    public static final BlockBlockItemHolder<Block, BlockItem> GILDED_RUNEWOOD_ITEM_STAND = registerBlock("gilded_runewood_item_stand", () -> new ItemStandBlock<>(MalumBlockProperties.RUNEWOOD().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_STAND));

    public static final BlockBlockItemHolder<Block, BlockItem> RUNEWOOD_SIGN = registerBlock("runewood_sign", () -> new StandingSignBlock(MalumWoodTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD().addTags(SIGNS, STANDING_SIGNS).noOcclusion().noCollission()));
    public static final DeferredHolder<Block, Block> RUNEWOOD_WALL_SIGN = registerBlockNoItem("runewood_wall_sign", () -> new WallSignBlock(MalumWoodTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD().addTags(SIGNS, WALL_SIGNS).noOcclusion().noCollission()));
    //endregion

    //region soulwood
    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_SAPLING = registerBlock("soulwood_sapling", () -> new SoulwoodGrowthBlock(MalumTreeGrowers.SOULWOOD, MalumBlockProperties.SOULWOOD_SAPLING()));

    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_LEAVES = registerBlock("soulwood_leaves", () -> new MalumLeavesBlock(MalumBlockProperties.SOULWOOD_LEAVES().setCutoutRenderType(), new Color(213, 8, 63), new Color(255, 61, 243)));
    public static final BlockBlockItemHolder<Block, BlockItem> HANGING_SOULWOOD_LEAVES = registerBlock("hanging_soulwood_leaves", () -> new MalumHangingLeavesBlock(MalumBlockProperties.HANGING_SOULWOOD_LEAVES().setCutoutRenderType().noOcclusion().noCollission(), new Color(213, 8, 63), new Color(255, 61, 243)));

    public static final BlockBlockItemHolder<Block, BlockItem> BLIGHTED_SOULWOOD = registerBlock("blighted_soulwood", () -> new BlightedSoulwoodBlock(MalumBlockProperties.SOULWOOD_LOGS()));

    public static final BlockBlockItemHolder<Block, BlockItem> STRIPPED_SOULWOOD_LOG = registerBlock("stripped_soulwood_log", () -> new RotatedPillarBlock(MalumBlockProperties.SOULWOOD_LOGS().addTags(STRIPPED_LOGS)));
    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_LOG = registerBlock("soulwood_log", () -> new SoulwoodLogBlock(MalumBlockProperties.SOULWOOD_LOGS(), STRIPPED_SOULWOOD_LOG));

    public static final BlockBlockItemHolder<Block, BlockItem> STRIPPED_SOULWOOD = registerBlock("stripped_soulwood", () -> new RotatedPillarBlock(MalumBlockProperties.SOULWOOD_LOGS().addTags(STRIPPED_WOODS)));
    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD = registerBlock("soulwood", () -> new SoulwoodBlock(MalumBlockProperties.SOULWOOD_LOGS(), STRIPPED_SOULWOOD));

    public static final BlockBlockItemHolder<Block, BlockItem> STRIPPED_SAPPY_SOULWOOD_LOG = registerBlock("stripped_sappy_soulwood_log", () -> new SapFilledSoulwoodLogBlock(MalumBlockProperties.SOULWOOD_LOGS(), STRIPPED_SOULWOOD_LOG, MalumItems.CURSED_SAP, MalumSpiritTypes.ELDRITCH_COLORS().primaryColor(), new Color(255, 61, 106)));
    public static final BlockBlockItemHolder<Block, BlockItem> SAPPY_SOULWOOD_LOG = registerBlock("sappy_soulwood_log", () -> new LodestoneLogBlock(MalumBlockProperties.SOULWOOD_LOGS().addTags(STRIPPED_LOGS), STRIPPED_SAPPY_SOULWOOD_LOG));

    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_BOARDS = registerBlock("soulwood_boards", () -> new Block(MalumBlockProperties.SOULWOOD_PLANKS()));
    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_BOARDS_SLAB = registerBlock("soulwood_boards_slab", () -> new SlabBlock(MalumBlockProperties.SOULWOOD_SLABS()));
    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_BOARDS_STAIRS = registerBlock("soulwood_boards_stairs", () -> new StairBlock(SOULWOOD_BOARDS.get().defaultBlockState(), MalumBlockProperties.SOULWOOD_STAIRS()));

    public static final BlockBlockItemHolder<Block, BlockItem> VERTICAL_SOULWOOD_BOARDS = registerBlock("vertical_soulwood_boards", () -> new Block(MalumBlockProperties.SOULWOOD_PLANKS()));
    public static final BlockBlockItemHolder<Block, BlockItem> VERTICAL_SOULWOOD_BOARDS_SLAB = registerBlock("vertical_soulwood_boards_slab", () -> new SlabBlock(MalumBlockProperties.SOULWOOD_SLABS()));
    public static final BlockBlockItemHolder<Block, BlockItem> VERTICAL_SOULWOOD_BOARDS_STAIRS = registerBlock("vertical_soulwood_boards_stairs", () -> new StairBlock(VERTICAL_SOULWOOD_BOARDS.get().defaultBlockState(), MalumBlockProperties.SOULWOOD_STAIRS()));

    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_PLANKS = registerBlock("soulwood_planks", () -> new Block(MalumBlockProperties.SOULWOOD_PLANKS()));
    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_PLANKS_SLAB = registerBlock("soulwood_planks_slab", () -> new SlabBlock(MalumBlockProperties.SOULWOOD_SLABS()));
    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_PLANKS_STAIRS = registerBlock("soulwood_planks_stairs", () -> new StairBlock(SOULWOOD_PLANKS.get().defaultBlockState(), MalumBlockProperties.SOULWOOD_STAIRS()));

    public static final BlockBlockItemHolder<Block, BlockItem> RUSTIC_SOULWOOD_PLANKS = registerBlock("rustic_soulwood_planks", () -> new Block(MalumBlockProperties.SOULWOOD_PLANKS()));
    public static final BlockBlockItemHolder<Block, BlockItem> RUSTIC_SOULWOOD_PLANKS_SLAB = registerBlock("rustic_soulwood_planks_slab", () -> new SlabBlock(MalumBlockProperties.SOULWOOD_SLABS()));
    public static final BlockBlockItemHolder<Block, BlockItem> RUSTIC_SOULWOOD_PLANKS_STAIRS = registerBlock("rustic_soulwood_planks_stairs", () -> new StairBlock(RUSTIC_SOULWOOD_PLANKS.get().defaultBlockState(), MalumBlockProperties.SOULWOOD_STAIRS()));

    public static final BlockBlockItemHolder<Block, BlockItem> VERTICAL_SOULWOOD_PLANKS = registerBlock("vertical_soulwood_planks", () -> new Block(MalumBlockProperties.SOULWOOD_PLANKS()));
    public static final BlockBlockItemHolder<Block, BlockItem> VERTICAL_SOULWOOD_PLANKS_SLAB = registerBlock("vertical_soulwood_planks_slab", () -> new SlabBlock(MalumBlockProperties.SOULWOOD_SLABS()));
    public static final BlockBlockItemHolder<Block, BlockItem> VERTICAL_SOULWOOD_PLANKS_STAIRS = registerBlock("vertical_soulwood_planks_stairs", () -> new StairBlock(VERTICAL_SOULWOOD_PLANKS.get().defaultBlockState(), MalumBlockProperties.SOULWOOD_STAIRS()));

    public static final BlockBlockItemHolder<Block, BlockItem> VERTICAL_RUSTIC_SOULWOOD_PLANKS = registerBlock("vertical_rustic_soulwood_planks", () -> new Block(MalumBlockProperties.SOULWOOD_PLANKS()));
    public static final BlockBlockItemHolder<Block, BlockItem> VERTICAL_RUSTIC_SOULWOOD_PLANKS_SLAB = registerBlock("vertical_rustic_soulwood_planks_slab", () -> new SlabBlock(MalumBlockProperties.SOULWOOD_SLABS()));
    public static final BlockBlockItemHolder<Block, BlockItem> VERTICAL_RUSTIC_SOULWOOD_PLANKS_STAIRS = registerBlock("vertical_rustic_soulwood_planks_stairs", () -> new StairBlock(VERTICAL_RUSTIC_SOULWOOD_PLANKS.get().defaultBlockState(), MalumBlockProperties.SOULWOOD_STAIRS()));

    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_TILES = registerBlock("soulwood_tiles", () -> new Block(MalumBlockProperties.SOULWOOD_PLANKS()));
    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_TILES_SLAB = registerBlock("soulwood_tiles_slab", () -> new SlabBlock(MalumBlockProperties.SOULWOOD_SLABS()));
    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_TILES_STAIRS = registerBlock("soulwood_tiles_stairs", () -> new StairBlock(SOULWOOD_TILES.get().defaultBlockState(), MalumBlockProperties.SOULWOOD_STAIRS()));

    public static final BlockBlockItemHolder<Block, BlockItem> RUSTIC_SOULWOOD_TILES = registerBlock("rustic_soulwood_tiles", () -> new Block(MalumBlockProperties.SOULWOOD_PLANKS()));
    public static final BlockBlockItemHolder<Block, BlockItem> RUSTIC_SOULWOOD_TILES_SLAB = registerBlock("rustic_soulwood_tiles_slab", () -> new SlabBlock(MalumBlockProperties.SOULWOOD_SLABS()));
    public static final BlockBlockItemHolder<Block, BlockItem> RUSTIC_SOULWOOD_TILES_STAIRS = registerBlock("rustic_soulwood_tiles_stairs", () -> new StairBlock(RUSTIC_SOULWOOD_TILES.get().defaultBlockState(), MalumBlockProperties.SOULWOOD_STAIRS()));

    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_PANEL = registerBlock("soulwood_panel", () -> new Block(MalumBlockProperties.SOULWOOD()));
    public static final BlockBlockItemHolder<Block, BlockItem> CUT_SOULWOOD_PLANKS = registerBlock("cut_soulwood_planks", () -> new Block(MalumBlockProperties.SOULWOOD_PLANKS()));
    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_BEAM = registerBlock("soulwood_beam", () -> new RotatedPillarBlock(MalumBlockProperties.SOULWOOD()));

    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_DOOR = registerBlock("soulwood_door", () -> new DoorBlock(MalumBlockSetTypes.SOULWOOD, MalumBlockProperties.SOULWOOD_DOOR()));
    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_BOARDS_DOOR = registerBlock("soulwood_boards_door", () -> new DoorBlock(MalumBlockSetTypes.SOULWOOD, MalumBlockProperties.SOULWOOD_DOOR()));
    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_TRAPDOOR = registerBlock("soulwood_trapdoor", () -> new TrapDoorBlock(MalumBlockSetTypes.SOULWOOD, MalumBlockProperties.SOULWOOD_TRAPDOOR()));
    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_BOARDS_TRAPDOOR = registerBlock("soulwood_boards_trapdoor", () -> new TrapDoorBlock(MalumBlockSetTypes.SOULWOOD, MalumBlockProperties.SOULWOOD_TRAPDOOR()));

    public static final BlockBlockItemHolder<Block, BlockItem> BOLTED_SOULWOOD_DOOR = registerBlock("bolted_soulwood_door", () -> new DoorBlock(MalumBlockSetTypes.SOULWOOD, MalumBlockProperties.SOULWOOD_DOOR()));
    public static final BlockBlockItemHolder<Block, BlockItem> BOLTED_SOULWOOD_BOARDS_DOOR = registerBlock("bolted_soulwood_boards_door", () -> new DoorBlock(MalumBlockSetTypes.SOULWOOD, MalumBlockProperties.SOULWOOD_DOOR()));
    public static final BlockBlockItemHolder<Block, BlockItem> BOLTED_SOULWOOD_TRAPDOOR = registerBlock("bolted_soulwood_trapdoor", () -> new TrapDoorBlock(MalumBlockSetTypes.SOULWOOD, MalumBlockProperties.SOULWOOD_TRAPDOOR()));
    public static final BlockBlockItemHolder<Block, BlockItem> BOLTED_SOULWOOD_BOARDS_TRAPDOOR = registerBlock("bolted_soulwood_boards_trapdoor", () -> new TrapDoorBlock(MalumBlockSetTypes.SOULWOOD, MalumBlockProperties.SOULWOOD_TRAPDOOR()));

    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_BUTTON = registerBlock("soulwood_planks_button", () -> new ButtonBlock(MalumBlockSetTypes.SOULWOOD, 20, MalumBlockProperties.SOULWOOD().noCollission().addTags(BUTTONS, WOODEN_BUTTONS).addTags(BUTTONS, WOODEN_BUTTONS)));
    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_PRESSURE_PLATE = registerBlock("soulwood_planks_pressure_plate", () -> new PressurePlateBlock(MalumBlockSetTypes.SOULWOOD, MalumBlockProperties.SOULWOOD().noCollission().addTags(PRESSURE_PLATES, WOODEN_PRESSURE_PLATES)));

    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_BOARDS_WALL = registerBlock("soulwood_boards_wall", () -> new WallBlock(MalumBlockProperties.SOULWOOD().addTags(WALLS)));
    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_FENCE = registerBlock("soulwood_planks_fence", () -> new FenceBlock(MalumBlockProperties.SOULWOOD().addTags(FENCES, WOODEN_FENCES)));
    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_FENCE_GATE = registerBlock("soulwood_planks_fence_gate", () -> new FenceGateBlock(MalumWoodTypes.SOULWOOD, MalumBlockProperties.SOULWOOD().addTags(FENCE_GATES, FENCE_GATES_WOODEN)));


    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_ITEM_PEDESTAL = registerBlock("soulwood_item_pedestal", () -> new WoodItemPedestalBlock<>(MalumBlockProperties.SOULWOOD().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_PEDESTAL));
    public static final BlockBlockItemHolder<Block, BlockItem> ORNATE_SOULWOOD_ITEM_PEDESTAL = registerBlock("ornate_soulwood_item_pedestal", () -> new DecoratedItemPedestalBlock<>(MalumBlockProperties.SOULWOOD().setCutoutRenderType().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_PEDESTAL));
    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_ITEM_STAND = registerBlock("soulwood_item_stand", () -> new ItemStandBlock<>(MalumBlockProperties.SOULWOOD().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_STAND));
    public static final BlockBlockItemHolder<Block, BlockItem> ORNATE_SOULWOOD_ITEM_STAND = registerBlock("ornate_soulwood_item_stand", () -> new ItemStandBlock<>(MalumBlockProperties.SOULWOOD().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_STAND));

    public static final BlockBlockItemHolder<Block, BlockItem> SOULWOOD_SIGN = registerBlock("soulwood_sign", () -> new StandingSignBlock(MalumWoodTypes.SOULWOOD, MalumBlockProperties.SOULWOOD().addTags(SIGNS, STANDING_SIGNS).noOcclusion().noCollission()));
    public static final DeferredHolder<Block, Block> SOULWOOD_WALL_SIGN = registerBlockNoItem("soulwood_wall_sign", () -> new WallSignBlock(MalumWoodTypes.SOULWOOD, MalumBlockProperties.SOULWOOD().addTags(SIGNS, WALL_SIGNS).noOcclusion().noCollission()));
    //endregion

    //region ores and such
    public static final BlockBlockItemHolder<Block, BlockItem> BLOCK_OF_SOULSTONE = registerBlock("block_of_soulstone", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.SOULSTONE_BLOCK(false)));
    public static final BlockBlockItemHolder<Block, BlockItem> BLOCK_OF_RAW_SOULSTONE = registerBlock("block_of_raw_soulstone", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.SOULSTONE_BLOCK(true)));
    public static final BlockBlockItemHolder<Block, BlockItem> DEEPSLATE_SOULSTONE_ORE = registerBlock("deepslate_soulstone_ore", () -> new DropExperienceBlock(UniformInt.of(14, 18), MalumOreBlockProperties.SOULSTONE_ORE(true)));
    public static final BlockBlockItemHolder<Block, BlockItem> SOULSTONE_ORE = registerBlock("soulstone_ore", () -> new DropExperienceBlock(UniformInt.of(14, 18), MalumOreBlockProperties.SOULSTONE_ORE(false)));

    public static final BlockBlockItemHolder<Block, BlockItem> BLOCK_OF_BRILLIANCE = registerBlock("block_of_brilliance", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.BRILLIANCE_BLOCK(false)));
    public static final BlockBlockItemHolder<Block, BlockItem> BLOCK_OF_RAW_BRILLIANCE = registerBlock("block_of_raw_brilliance", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.BRILLIANCE_BLOCK(true)));
    public static final BlockBlockItemHolder<Block, BlockItem> BRILLIANT_DEEPSLATE = registerBlock("brilliant_deepslate", () -> new DropExperienceBlock(UniformInt.of(16, 26), MalumOreBlockProperties.BRILLIANCE_ORE(true).setCutoutRenderType()));
    public static final BlockBlockItemHolder<Block, BlockItem> BRILLIANT_STONE = registerBlock("brilliant_stone", () -> new DropExperienceBlock(UniformInt.of(14, 18), MalumOreBlockProperties.BRILLIANCE_ORE(false).setCutoutRenderType()));

    public static final BlockBlockItemHolder<Block, BlockItem> BLOCK_OF_BLAZING_QUARTZ = registerBlock("block_of_blazing_quartz", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.BLAZING_QUARTZ_BLOCK().lightLevel((b) -> 14)));
    public static final BlockBlockItemHolder<Block, BlockItem> BLAZING_QUARTZ_ORE = registerBlock("blazing_quartz_ore", () -> new DropExperienceBlock(UniformInt.of(4, 7), MalumOreBlockProperties.BLAZING_QUARTZ_ORE().setCutoutRenderType().lightLevel((b) -> 6)));
    public static final BlockBlockItemHolder<Block, BlockItem> BLAZING_QUARTZ_CLUSTER = registerBlock("blazing_quartz_cluster", () -> new AmethystClusterBlock(4, 3, MalumBlockProperties.BLAZING_QUARTZ_CLUSTER().setCutoutRenderType().lightLevel((b) -> 14)));

    public static final BlockBlockItemHolder<Block, BlockItem> BLOCK_OF_NATURAL_QUARTZ = registerBlock("block_of_natural_quartz", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.NATURAL_QUARTZ_BLOCK()));
    public static final BlockBlockItemHolder<Block, BlockItem> DEEPSLATE_QUARTZ_ORE = registerBlock("deepslate_quartz_ore", () -> new DropExperienceBlock(UniformInt.of(2, 5), MalumOreBlockProperties.NATURAL_QUARTZ_ORE(true).setCutoutRenderType()));
    public static final BlockBlockItemHolder<Block, BlockItem> NATURAL_QUARTZ_ORE = registerBlock("natural_quartz_ore", () -> new DropExperienceBlock(UniformInt.of(1, 4), MalumOreBlockProperties.NATURAL_QUARTZ_ORE(false).setCutoutRenderType()));
    public static final BlockBlockItemHolder<Block, BlockItem> NATURAL_QUARTZ_CLUSTER = registerItemNameBlock("natural_quartz_cluster", "natural_quartz", () -> new AmethystClusterBlock(6, 3, MalumBlockProperties.NATURAL_QUARTZ_CLUSTER().setCutoutRenderType()));

    public static final BlockBlockItemHolder<Block, BlockItem> BLOCK_OF_CTHONIC_GOLD = registerBlock("block_of_cthonic_gold", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.CTHONIC_GOLD_BLOCK()));
    public static final BlockBlockItemHolder<Block, BlockItem> CTHONIC_GOLD_ORE = registerBlock("cthonic_gold_ore", () -> new DropExperienceBlock(UniformInt.of(10, 100), MalumOreBlockProperties.CTHONIC_GOLD_ORE()));
    public static final BlockBlockItemHolder<Block, BlockItem> CTHONIC_GOLD_CLUSTER = registerItemNameBlock("cthonic_gold_cluster", "cthonic_gold_fragment", () -> new AmethystClusterBlock(4, 3, MalumBlockProperties.CTHONIC_GOLD_CLUSTER().setCutoutRenderType()));

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
    //endregion

    //region flora
    public static final DeferredHolder<Block, Block> EBONY_SAPLING = registerBlockNoItem("ebony_sapling", () -> new EbonySaplingBlock(MalumFloraBlockProperties.EBONY_SAPLING()));
    public static final BlockBlockItemHolder<Block, BlockItem> EBONY_STALK = registerBlock("ebony", () -> new EbonyStalkBlock(MalumFloraBlockProperties.EBONY()));

    public static final BlockBlockItemHolder<Block, BlockItem> WILD_WITCHHAZEL = registerBlock("wild_witchhazel", () -> new WildWitchhazelPlantBlock(MalumFloraBlockProperties.WILD_WITCHHAZEL()));
    public static final BlockBlockItemHolder<Block, BlockItem> WITCHHAZEL = registerBlock("witchhazel", () -> new WitchhazelCropBlock(MalumFloraBlockProperties.WITCHHAZEL_CROP()));
    //endregion

    //region blight
    public static final BlockBlockItemHolder<Block, BlockItem> COLUMNAR_BLIGHT = registerBlock("columnar_blight", () -> new ColumnarBlightBlock(MalumBlockProperties.BLIGHTED_EARTH()));
    public static final BlockBlockItemHolder<Block, BlockItem> BLIGHTED_EARTH = registerBlock("blighted_earth", () -> new BlightedEarthBlock(MalumBlockProperties.BLIGHTED_EARTH()));
    public static final BlockBlockItemHolder<Block, BlockItem> BLIGHT = registerBlock("blight", () -> new BlightedCoverageBlock(MalumBlockProperties.BLIGHTED_COVERING()));

    public static final DeferredHolder<Block, Block> CLINGING_BLIGHT = registerBlockNoItem("clinging_blight", () -> new CreepingBlightBlock(MalumBlockProperties.CLINGING_BLIGHT()));
    public static final BlockBlockItemHolder<Block, BlockItem> BLIGHTED_GROWTH = registerBlock("blighted_growth", "blighted_gunk", () -> new BlightedPlantBlock(MalumBlockProperties.BLIGHTED_PLANTS()), BlightedGunkItem::new);

    public static final BlockBlockItemHolder<Block, BlockItem> BLIGHTPEARL = registerBlock("blightpearl", () -> new BlightedPlantBlock(MalumBlockProperties.BLIGHTED_PLANTS()));
    public static final BlockBlockItemHolder<Block, BlockItem> BLIGHTROOT = registerBlock("blightroot", () -> new BlightedPlantBlock(MalumBlockProperties.BLIGHTED_PLANTS()));
    //endregion

    //region scarstone
    public static final BlockBlockItemHolder<Block, BlockItem> SCARSTONE = registerBlock("scarstone", () -> new ScarstoneBlock(MalumBlockProperties.SCARSTONE()));
    public static final BlockBlockItemHolder<Block, BlockItem> STRANGE_CRYSTAL = registerBlock("strange_crystal", () -> new StrangeCrystalBlock(MalumBlockProperties.STRANGE_CRYSTAL()));
    public static final BlockBlockItemHolder<Block, BlockItem> LARGE_STRANGE_CRYSTAL = registerBlock("large_strange_crystal", () -> new LargeStrangeCrystalBlock(MalumBlockProperties.STRANGE_CRYSTAL()));
    public static final BlockBlockItemHolder<Block, BlockItem> STRANGEROOT = registerBlock("strangeroot", () -> new StrangeRootBlock(MalumBlockProperties.STRANGEROOT()));
    //endregion

    //region dungeon
    public static final BlockBlockItemHolder<Block, BlockItem> OMINOUS_ALTAR = registerBlock("ominous_altar", () -> new OminousAltarBlock(MalumDungeonBlockProperties.OMINOUS_CRAFT()).setBlockEntity(MalumBlockEntities.OMINOUS_ALTAR));
    public static final BlockBlockItemHolder<Block, MultiBlockItem> OMINOUS_OBELISK = registerMultiBlock("ominous_obelisk", () -> new OminousObeliskCoreBlock(MalumDungeonBlockProperties.OMINOUS_CRAFT().setCutoutRenderType().noOcclusion()), RunewoodObeliskBlockEntity.STRUCTURE);
    public static final DeferredHolder<Block, Block> OMINOUS_OBELISK_COMPONENT = registerBlockNoItem("ominous_obelisk_component", () -> new ObeliskComponentBlock(MalumDungeonBlockProperties.OMINOUS_CRAFT().setCutoutRenderType().lootFrom(OMINOUS_OBELISK).noOcclusion()));

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

    //endregion
    public static final DeferredHolder<Block, Block> POTTED_RUNEWOOD_SAPLING = registerBlockNoItem("potted_runewood_sapling", () -> flowerPot(RUNEWOOD_SAPLING));
    public static final DeferredHolder<Block, Block> POTTED_AZURE_RUNEWOOD_SAPLING = registerBlockNoItem("potted_azure_runewood_sapling", () -> flowerPot(AZURE_RUNEWOOD_SAPLING));
    public static final DeferredHolder<Block, Block> POTTED_SOULWOOD_SAPLING = registerBlockNoItem("potted_soulwood_sapling", () -> flowerPot(SOULWOOD_SAPLING));
    public static final DeferredHolder<Block, Block> POTTED_BLIGHTROOT = registerBlockNoItem("potted_blightroot", () -> flowerPot(BLIGHTROOT));
    public static final DeferredHolder<Block, Block> POTTED_BLIGHTPEARL = registerBlockNoItem("potted_blightpearl", () -> flowerPot(BLIGHTPEARL));
    public static final DeferredHolder<Block, Block> POTTED_STRANGEROOT = registerBlockNoItem("potted_strangeroot", () -> flowerPot(STRANGEROOT));


    public static final BlockBlockItemHolder<Block, BlockItem> THE_DEVICE = registerBlock("the_device", () -> new TheDevice(MalumBlockProperties.TAINTED_ROCK()));
    public static final BlockBlockItemHolder<Block, BlockItem> THE_VESSEL = registerBlock("the_vessel", () -> new TheVessel(MalumBlockProperties.TWISTED_ROCK()));


    public static <T extends Block> BlockBlockItemHolder<T, BlockItem> registerBlock(String name, Supplier<T> supplier) {
        return registerBlock(name, name, supplier, BlockItem::new);
    }

    public static <T extends Block> BlockBlockItemHolder<T, MultiBlockItem> registerMultiBlock(String name, Supplier<T> supplier, Supplier<? extends MultiBlockStructure> structure) {
        return registerBlock(name, name, supplier, (b, p) -> new MultiBlockItem(b, p, structure));
    }

    public static <T extends Block> BlockBlockItemHolder<T, BlockItem> registerItemNameBlock(String blockName, String itemName, Supplier<T> supplier) {
        return registerBlock(blockName, itemName, supplier, ItemNameBlockItem::new);
    }

    public static <T extends Block, K extends BlockItem> BlockBlockItemHolder<T, K> registerBlock(String name, Supplier<T> blockSupplier, BiFunction<Block, LodestoneItemProperties, K> itemSupplier) {
        return registerBlock(name, name, blockSupplier, itemSupplier);
    }

    public static <T extends Block, K extends BlockItem> BlockBlockItemHolder<T, K> registerBlock(String blockName, String itemName, Supplier<T> blockSupplier, BiFunction<Block, LodestoneItemProperties, K> itemSupplier) {
        var block = BLOCKS.register(blockName, blockSupplier);
        var item = MalumItems.register(itemName, MalumItems::DEFAULT_PROPERTIES, p -> itemSupplier.apply(block.get(), p));
        return new BlockBlockItemHolder<>(block, item);
    }

    public static <T extends Block> DeferredHolder<Block, T> registerBlockNoItem(String name, Supplier<T> supplier) {
        return BLOCKS.register(name, supplier);
    }

    private static Block flowerPot(BlockBlockItemHolder<Block, BlockItem> potted) {
        return new FlowerPotBlock(() -> (FlowerPotBlock) net.minecraft.world.level.block.Blocks.FLOWER_POT, potted, MalumBlockProperties.POTTED_PLANT());
    }

    public static void addPottedBlocks(FMLCommonSetupEvent event) {
        FlowerPotBlock flowerPot = (FlowerPotBlock) net.minecraft.world.level.block.Blocks.FLOWER_POT;
        flowerPot.addPlant(RUNEWOOD_SAPLING.block().getId(), POTTED_RUNEWOOD_SAPLING);
        flowerPot.addPlant(AZURE_RUNEWOOD_SAPLING.block().getId(), POTTED_AZURE_RUNEWOOD_SAPLING);
        flowerPot.addPlant(SOULWOOD_SAPLING.block().getId(), POTTED_SOULWOOD_SAPLING);
        flowerPot.addPlant(BLIGHTROOT.block().getId(), POTTED_BLIGHTROOT);
        flowerPot.addPlant(BLIGHTPEARL.block().getId(), POTTED_BLIGHTPEARL);
        flowerPot.addPlant(STRANGEROOT.block().getId(), POTTED_STRANGEROOT);
    }
}