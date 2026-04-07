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

import java.awt.*;

import static com.sammy.malum.MalumMod.*;
import static com.sammy.malum.registry.common.MalumTags.BlockTags.*;
import static net.minecraft.tags.BlockTags.*;
import static net.neoforged.neoforge.common.Tags.Blocks.FENCE_GATES_WOODEN;


public class MalumBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, MALUM);

    //region useful blocks
    public static final DeferredHolder<Block, Block> SPIRIT_ALTAR = BLOCKS.register("spirit_altar", () -> new SpiritAltarBlock<>(MalumBlockProperties.SPIRIT_ALTAR()).setBlockEntity(MalumBlockEntities.SPIRIT_ALTAR));
    public static final DeferredHolder<Block, Block> SPIRIT_JAR = BLOCKS.register("spirit_jar", () -> new SpiritJarBlock<>(MalumBlockProperties.SPIRIT_JAR().setCutoutRenderType().noOcclusion()).setBlockEntity(MalumBlockEntities.SPIRIT_JAR));

    public static final DeferredHolder<Block, Block> WEAVERS_WORKBENCH = BLOCKS.register("weavers_workbench", () -> new WeaversWorkbenchBlock<>(MalumBlockProperties.RUNEWOOD().setCutoutRenderType().noOcclusion()).setBlockEntity(MalumBlockEntities.WEAVERS_WORKBENCH));
    public static final DeferredHolder<Block, Block> RUNIC_WORKBENCH = BLOCKS.register("runic_workbench", () -> new RunicWorkbenchBlock<>(MalumBlockProperties.RUNEWOOD().setCutoutRenderType().noOcclusion()).setBlockEntity(MalumBlockEntities.RUNIC_WORKBENCH));

    public static final DeferredHolder<Block, Block> SOUL_BRAZIER = BLOCKS.register("soulbinding_brazier", () -> new SoulBrazierBlock<>(MalumBlockProperties.SOUL_BRAZIER()).setBlockEntity(MalumBlockEntities.SOUL_BRAZIER));

    public static final DeferredHolder<Block, Block> RITUAL_PLINTH = BLOCKS.register("ritual_plinth", () -> new RitualPlinthBlock<>(MalumBlockProperties.SOULWOOD().setCutoutRenderType().noOcclusion()).setBlockEntity(MalumBlockEntities.RITUAL_PLINTH));

    public static final DeferredHolder<Block, Block> RUNEWOOD_OBELISK = BLOCKS.register("runewood_obelisk", () -> new RunewoodObeliskCoreBlock(MalumBlockProperties.RUNEWOOD().setCutoutRenderType().noOcclusion()));
    public static final DeferredHolder<Block, Block> RUNEWOOD_OBELISK_COMPONENT = BLOCKS.register("runewood_obelisk_component", () -> new ObeliskComponentBlock(MalumBlockProperties.RUNEWOOD().setCutoutRenderType().lootFrom(RUNEWOOD_OBELISK).noOcclusion(), MalumItems.RUNEWOOD_OBELISK));

    public static final DeferredHolder<Block, Block> BRILLIANT_OBELISK = BLOCKS.register("brilliant_obelisk", () -> new BrillianceObeliskCoreBlock(MalumBlockProperties.RUNEWOOD().setCutoutRenderType().noOcclusion()));
    public static final DeferredHolder<Block, Block> BRILLIANT_OBELISK_COMPONENT = BLOCKS.register("brilliant_obelisk_component", () -> new ObeliskComponentBlock(MalumBlockProperties.RUNEWOOD().setCutoutRenderType().lootFrom(BRILLIANT_OBELISK).noOcclusion(), MalumItems.BRILLIANT_OBELISK));

    public static final DeferredHolder<Block, Block> ARCANA_PYLON = BLOCKS.register("arcana_pylon", () -> new ArcanaPylonCoreBlock(MalumBlockProperties.SOULWOOD().setCutoutRenderType().noOcclusion()));
    public static final DeferredHolder<Block, Block> ARCANA_PYLON_COMPONENT = BLOCKS.register("arcana_pylon_component", () -> new ArcanaPylonComponentBlock(MalumBlockProperties.SOULWOOD().setCutoutRenderType().lootFrom(ARCANA_PYLON).noOcclusion(), MalumItems.ARCANA_PYLON));

    public static final DeferredHolder<Block, Block> SPIRIT_CRUCIBLE = BLOCKS.register("spirit_crucible", () -> new SpiritCrucibleCoreBlock<>(MalumBlockProperties.ARCANE_ROCK_ARTIFICE()).setBlockEntity(MalumBlockEntities.SPIRIT_CRUCIBLE));
    public static final DeferredHolder<Block, Block> SPIRIT_CRUCIBLE_COMPONENT = BLOCKS.register("spirit_crucible_component", () -> new SpiritCrucibleComponentBlock(MalumBlockProperties.ARCANE_ROCK_ARTIFICE().lootFrom(SPIRIT_CRUCIBLE)));

    public static final DeferredHolder<Block, Block> SPIRIT_CATALYZER = BLOCKS.register("spirit_catalyzer", () -> new SpiritCatalyzerCoreBlock<>(MalumBlockProperties.ARCANE_ROCK_ARTIFICE()).setBlockEntity(MalumBlockEntities.SPIRIT_CATALYZER));
    public static final DeferredHolder<Block, Block> SPIRIT_CATALYZER_COMPONENT = BLOCKS.register("spirit_catalyzer_component", () -> new SpiritCatalyzerComponentBlock(MalumBlockProperties.ARCANE_ROCK_ARTIFICE().lootFrom(SPIRIT_CATALYZER)));

    public static final DeferredHolder<Block, Block> REPAIR_PYLON = BLOCKS.register("repair_pylon", () -> new RepairPylonCoreBlock<>(MalumBlockProperties.ARCANE_ROCK_ARTIFICE()).setBlockEntity(MalumBlockEntities.REPAIR_PYLON));
    public static final DeferredHolder<Block, Block> REPAIR_PYLON_COMPONENT = BLOCKS.register("repair_pylon_component", () -> new RepairPylonComponentBlock(MalumBlockProperties.ARCANE_ROCK_ARTIFICE().lootFrom(REPAIR_PYLON)));

    public static final DeferredHolder<Block, Block> RUNEWOOD_TOTEM_BASE = BLOCKS.register("runewood_totem_base", () -> new TotemBaseBlock<>(MalumBlockProperties.RUNEWOOD().addTag(IS_RITE_IMMUNE).noOcclusion(), false).setBlockEntity(MalumBlockEntities.TOTEM_BASE));
    public static final DeferredHolder<Block, Block> SOULWOOD_TOTEM_BASE = BLOCKS.register("soulwood_totem_base", () -> new TotemBaseBlock<>(MalumBlockProperties.SOULWOOD().addTag(IS_RITE_IMMUNE).noOcclusion(), true).setBlockEntity(MalumBlockEntities.TOTEM_BASE));

    public static final DeferredHolder<Block, Block> WAVEFORM_RUNEWOOD_TOTEM_BASE = BLOCKS.register("waveform_runewood_totem_base", () -> new WaveformTotemBaseBlock<>(MalumBlockProperties.WAVEFORM_DIODE().addTag(IS_RITE_IMMUNE).noOcclusion(), false).setBlockEntity(MalumBlockEntities.WAVEFORM_TOTEM_BASE));
    public static final DeferredHolder<Block, Block> WAVEFORM_SOULWOOD_TOTEM_BASE = BLOCKS.register("waveform_soulwood_totem_base", () -> new WaveformTotemBaseBlock<>(MalumBlockProperties.WAVEFORM_DIODE().addTag(IS_RITE_IMMUNE).noOcclusion(), true).setBlockEntity(MalumBlockEntities.WAVEFORM_TOTEM_BASE));

    public static final DeferredHolder<Block, Block> RUNEWOOD_TOTEM_POLE = BLOCKS.register("runewood_totem_pole", () -> new TotemPoleBlock<>(MalumBlockProperties.RUNEWOOD().addTag(IS_RITE_IMMUNE).noOcclusion(), MalumBlocks.RUNEWOOD_LOG, false).setBlockEntity(MalumBlockEntities.TOTEM_POLE));
    public static final DeferredHolder<Block, Block> SOULWOOD_TOTEM_POLE = BLOCKS.register("soulwood_totem_pole", () -> new TotemPoleBlock<>(MalumBlockProperties.SOULWOOD().addTag(IS_RITE_IMMUNE).noOcclusion(), MalumBlocks.SOULWOOD_LOG, true).setBlockEntity(MalumBlockEntities.TOTEM_POLE));

    public static final DeferredHolder<Block, Block> RITE_ANCHOR = BLOCKS.register("rite_anchor", () -> new RiteAnchorBlock(MalumBlockProperties.TAINTED_ROCK_TOTEMANCY()).setBlockEntity(MalumBlockEntities.RITE_ANCHOR));
    public static final DeferredHolder<Block, Block> RITE_UNWEAVER = BLOCKS.register("rite_unweaver", () -> new RiteUnweaverBlock(MalumBlockProperties.TWISTED_ROCK_TOTEMANCY()).setBlockEntity(MalumBlockEntities.RITE_UNWEAVER));
    public static final DeferredHolder<Block, Block> RITE_SPREADER = BLOCKS.register("rite_spreader", () -> new RiteSpreaderBlock(MalumBlockProperties.TAINTED_ROCK_TOTEMANCY()).setBlockEntity(MalumBlockEntities.RITE_SPREADER));
    public static final DeferredHolder<Block, Block> RITE_CHANNEL = BLOCKS.register("rite_channel", () -> new RiteChannelBlock(MalumBlockProperties.TAINTED_ROCK_TOTEMANCY()).setBlockEntity(MalumBlockEntities.RITE_CHANNEL));

    public static final DeferredHolder<Block, Block> WAVECHARGER = BLOCKS.register("wavecharger", () -> new WaveChargerBlock(MalumBlockProperties.WAVEFORM_DIODE()).setBlockEntity(MalumBlockEntities.WAVECHARGER));
    public static final DeferredHolder<Block, Block> WAVEBANKER = BLOCKS.register("wavebanker", () -> new WaveBankerBlock(MalumBlockProperties.WAVEFORM_DIODE()).setBlockEntity(MalumBlockEntities.WAVEBANKER));
    public static final DeferredHolder<Block, Block> WAVEMAKER = BLOCKS.register("wavemaker", () -> new WaveMakerBlock(MalumBlockProperties.WAVEFORM_DIODE()).setBlockEntity(MalumBlockEntities.WAVEMAKER));
    public static final DeferredHolder<Block, Block> WAVEBREAKER = BLOCKS.register("wavebreaker", () -> new WaveBreakerBlock(MalumBlockProperties.WAVEFORM_DIODE()).setBlockEntity(MalumBlockEntities.WAVEBREAKER));

    public static final DeferredHolder<Block, Block> GUST_IGNITER = BLOCKS.register("gust_igniter", () -> new GustIgniterBlock(MalumBlockProperties.GUST_TECH()).setBlockEntity(MalumBlockEntities.GUST_IGNITER));
    public static final DeferredHolder<Block, Block> WIND_TUNNEL = BLOCKS.register("wind_tunnel", () -> new WindTunnelBlock(MalumBlockProperties.GUST_TECH()).setBlockEntity(MalumBlockEntities.WIND_TUNNEL));

    public static final DeferredHolder<Block, Block> SPIRIT_MOTE = BLOCKS.register("spirit_mote", () -> new ManaMoteBlock(MalumStorageBlockProperties.MANA_MOTE_BLOCK()).setBlockEntity(MalumBlockEntities.MANA_MOTE));

    public static final DeferredHolder<Block, Block> VOID_CONDUIT = BLOCKS.register("void_conduit", () -> new VoidConduitBlock<>(MalumBlockProperties.PRIMORDIAL_SOUP()).setBlockEntity(MalumBlockEntities.VOID_CONDUIT));
    public static final DeferredHolder<Block, Block> PRIMORDIAL_SOUP = BLOCKS.register("primordial_soup", () -> new PrimordialSoupBlock(MalumBlockProperties.PRIMORDIAL_SOUP()));

    public static final DeferredHolder<Block, Block> VOID_DEPOT = BLOCKS.register("void_depot", () -> new VoidDepotBlock<>(MalumBlockProperties.WEEPING_WELL()).setBlockEntity(MalumBlockEntities.VOID_DEPOT));

    public static final DeferredHolder<Block, Block> WEEPING_WELL_CENTER = BLOCKS.register("weeping_well_center", () -> new WeepingWellLayeredBlock(MalumBlockProperties.WEEPING_WELL()));
    public static final DeferredHolder<Block, Block> WEEPING_WELL_SIDE = BLOCKS.register("weeping_well_side", () -> new WeepingWellLayeredBlock(MalumBlockProperties.WEEPING_WELL()));
    public static final DeferredHolder<Block, Block> WEEPING_WELL_SIDE_MIRROR = BLOCKS.register("weeping_well_side_mirror", () -> new WeepingWellLayeredBlock(MalumBlockProperties.WEEPING_WELL()));
    public static final DeferredHolder<Block, Block> WEEPING_WELL_CORNER = BLOCKS.register("weeping_well_corner", () -> new WeepingWellLayeredBlock(MalumBlockProperties.WEEPING_WELL()));
    public static final DeferredHolder<Block, Block> WEEPING_WELL_FLAGSTONE = BLOCKS.register("weeping_well_flagstone", () -> new WeepingWellBlock(MalumBlockProperties.WEEPING_WELL()));
    public static final DeferredHolder<Block, Block> WEEPING_WELL_COLUMN_BASE = BLOCKS.register("weeping_well_column_base", () -> new WeepingWellDirectionalBlock(MalumBlockProperties.WEEPING_WELL()));
    public static final DeferredHolder<Block, Block> WEEPING_WELL_COLUMN = BLOCKS.register("weeping_well_column", () -> new WeepingWellDirectionalBlock(MalumBlockProperties.WEEPING_WELL()));
    public static final DeferredHolder<Block, Block> WEEPING_WELL_COLUMN_CAP = BLOCKS.register("weeping_well_column_cap", () -> new WeepingWellDirectionalBlock(MalumBlockProperties.WEEPING_WELL()));
    //endregion

    //region spirited glass
    public static final DeferredHolder<Block, Block> SACRED_SPIRITED_GLASS = BLOCKS.register("sacred_spirited_glass", () -> new SpiritedGlassBlock(MalumBlockProperties.SPIRITED_GLASS()));
    public static final DeferredHolder<Block, Block> WICKED_SPIRITED_GLASS = BLOCKS.register("wicked_spirited_glass", () -> new SpiritedGlassBlock(MalumBlockProperties.SPIRITED_GLASS()));
    public static final DeferredHolder<Block, Block> ARCANE_SPIRITED_GLASS = BLOCKS.register("arcane_spirited_glass", () -> new SpiritedGlassBlock(MalumBlockProperties.SPIRITED_GLASS()));
    public static final DeferredHolder<Block, Block> ELDRITCH_SPIRITED_GLASS = BLOCKS.register("eldritch_spirited_glass", () -> new SpiritedGlassBlock(MalumBlockProperties.SPIRITED_GLASS()));
    public static final DeferredHolder<Block, Block> AERIAL_SPIRITED_GLASS = BLOCKS.register("aerial_spirited_glass", () -> new SpiritedGlassBlock(MalumBlockProperties.SPIRITED_GLASS()));
    public static final DeferredHolder<Block, Block> AQUEOUS_SPIRITED_GLASS = BLOCKS.register("aqueous_spirited_glass", () -> new SpiritedGlassBlock(MalumBlockProperties.SPIRITED_GLASS()));
    public static final DeferredHolder<Block, Block> EARTHEN_SPIRITED_GLASS = BLOCKS.register("earthen_spirited_glass", () -> new SpiritedGlassBlock(MalumBlockProperties.SPIRITED_GLASS()));
    public static final DeferredHolder<Block, Block> INFERNAL_SPIRITED_GLASS = BLOCKS.register("infernal_spirited_glass", () -> new SpiritedGlassBlock(MalumBlockProperties.SPIRITED_GLASS()));
    public static final DeferredHolder<Block, Block> NULL_SPIRITED_GLASS = BLOCKS.register("null_spirited_glass", () -> new SpiritedGlassBlock(MalumBlockProperties.SPIRITED_GLASS()));

    //endregion

    //region varnished terracotta

    public static final DeferredHolder<Block, Block> SACRED_VARNISHED_TERRACOTTA = BLOCKS.register("sacred_varnished_terracotta", () -> new VarnishedTerracottaBlock(MalumBlockProperties.VARNISHED_TERRACOTTA(DyeColor.RED)));
    public static final DeferredHolder<Block, Block> WICKED_VARNISHED_TERRACOTTA = BLOCKS.register("wicked_varnished_terracotta", () -> new VarnishedTerracottaBlock(MalumBlockProperties.VARNISHED_TERRACOTTA(DyeColor.PURPLE)));
    public static final DeferredHolder<Block, Block> ARCANE_VARNISHED_TERRACOTTA = BLOCKS.register("arcane_varnished_terracotta", () -> new VarnishedTerracottaBlock(MalumBlockProperties.VARNISHED_TERRACOTTA(DyeColor.PINK)));
    public static final DeferredHolder<Block, Block> ELDRITCH_VARNISHED_TERRACOTTA = BLOCKS.register("eldritch_varnished_terracotta", () -> new VarnishedTerracottaBlock(MalumBlockProperties.VARNISHED_TERRACOTTA(DyeColor.MAGENTA)));
    public static final DeferredHolder<Block, Block> AERIAL_VARNISHED_TERRACOTTA = BLOCKS.register("aerial_varnished_terracotta", () -> new VarnishedTerracottaBlock(MalumBlockProperties.VARNISHED_TERRACOTTA(DyeColor.LIGHT_BLUE)));
    public static final DeferredHolder<Block, Block> AQUEOUS_VARNISHED_TERRACOTTA = BLOCKS.register("aqueous_varnished_terracotta", () -> new VarnishedTerracottaBlock(MalumBlockProperties.VARNISHED_TERRACOTTA(DyeColor.BLUE)));
    public static final DeferredHolder<Block, Block> EARTHEN_VARNISHED_TERRACOTTA = BLOCKS.register("earthen_varnished_terracotta", () -> new VarnishedTerracottaBlock(MalumBlockProperties.VARNISHED_TERRACOTTA(DyeColor.GREEN)));
    public static final DeferredHolder<Block, Block> INFERNAL_VARNISHED_TERRACOTTA = BLOCKS.register("infernal_varnished_terracotta", () -> new VarnishedTerracottaBlock(MalumBlockProperties.VARNISHED_TERRACOTTA(DyeColor.YELLOW)));
    public static final DeferredHolder<Block, Block> NULL_VARNISHED_TERRACOTTA = BLOCKS.register("null_varnished_terracotta", () -> new VarnishedTerracottaBlock(MalumBlockProperties.VARNISHED_TERRACOTTA(DyeColor.BLACK)));

    //endregion
    public static final DeferredHolder<Block, Block> SOULWOVEN_BANNER = BLOCKS.register("soulwoven_banner", () -> new SoulwovenBannerBlock(MalumBlockProperties.SOULWOVEN_BANNER()).setBlockEntity(MalumBlockEntities.SOULWOVEN_BANNER));

    //region ether
    public static final DeferredHolder<Block, Block> ETHER = BLOCKS.register("ether", () -> new EtherBlock<>(MalumBlockProperties.ETHER()).setBlockEntity(MalumBlockEntities.ETHER));
    public static final DeferredHolder<Block, Block> IRIDESCENT_ETHER = BLOCKS.register("iridescent_ether", () -> new EtherBlock<>(MalumBlockProperties.ETHER()).setBlockEntity(MalumBlockEntities.ETHER));

    public static final DeferredHolder<Block, Block> ETHER_CANDLE = BLOCKS.register("ether_candle", () -> new EtherCandleBlock<>(MalumBlockProperties.ETHER_CANDLE()).setBlockEntity(MalumBlockEntities.ETHER_CANDLE));
    public static final DeferredHolder<Block, Block> IRIDESCENT_ETHER_CANDLE = BLOCKS.register("iridescent_ether_candle", () -> new EtherCandleBlock<>(MalumBlockProperties.ETHER_CANDLE()).setBlockEntity(MalumBlockEntities.ETHER_CANDLE));

    public static final DeferredHolder<Block, Block> ETHER_TORCH = BLOCKS.register("ether_torch", () -> new EtherTorchBlock<>(MalumBlockProperties.ETHER_TORCH()).setBlockEntity(MalumBlockEntities.ETHER_TORCH));
    public static final DeferredHolder<Block, Block> WALL_ETHER_TORCH = BLOCKS.register("wall_ether_torch", () -> new EtherWallTorchBlock<>(MalumBlockProperties.ETHER_TORCH().lootFrom(ETHER_TORCH)).setBlockEntity(MalumBlockEntities.ETHER_TORCH));
    public static final DeferredHolder<Block, Block> IRIDESCENT_ETHER_TORCH = BLOCKS.register("iridescent_ether_torch", () -> new EtherTorchBlock<>(MalumBlockProperties.ETHER_TORCH()).setBlockEntity(MalumBlockEntities.ETHER_TORCH));
    public static final DeferredHolder<Block, Block> IRIDESCENT_WALL_ETHER_TORCH = BLOCKS.register("iridescent_wall_ether_torch", () -> new EtherWallTorchBlock<>(MalumBlockProperties.ETHER_TORCH().lootFrom(IRIDESCENT_ETHER_TORCH)).setBlockEntity(MalumBlockEntities.ETHER_TORCH));

    public static final DeferredHolder<Block, Block> TAINTED_ETHER_BRAZIER = BLOCKS.register("tainted_ether_brazier", () -> new EtherBrazierBlock<>(MalumBlockProperties.TAINTED_ETHER_BRAZIER()).setBlockEntity(MalumBlockEntities.ETHER_BRAZIER));
    public static final DeferredHolder<Block, Block> TWISTED_ETHER_BRAZIER = BLOCKS.register("twisted_ether_brazier", () -> new EtherBrazierBlock<>(MalumBlockProperties.TWISTED_ETHER_BRAZIER()).setBlockEntity(MalumBlockEntities.ETHER_BRAZIER));
    public static final DeferredHolder<Block, Block> DROSS_ETHER_BRAZIER = BLOCKS.register("dross_ether_brazier", () -> new EtherBrazierBlock<>(MalumBlockProperties.DROSS_ETHER_BRAZIER()).setBlockEntity(MalumBlockEntities.ETHER_BRAZIER));
    public static final DeferredHolder<Block, Block> TAINTED_IRIDESCENT_ETHER_BRAZIER = BLOCKS.register("tainted_iridescent_ether_brazier", () -> new EtherBrazierBlock<>(MalumBlockProperties.TAINTED_ETHER_BRAZIER()).setBlockEntity(MalumBlockEntities.ETHER_BRAZIER));
    public static final DeferredHolder<Block, Block> TWISTED_IRIDESCENT_ETHER_BRAZIER = BLOCKS.register("twisted_iridescent_ether_brazier", () -> new EtherBrazierBlock<>(MalumBlockProperties.TWISTED_ETHER_BRAZIER()).setBlockEntity(MalumBlockEntities.ETHER_BRAZIER));
    public static final DeferredHolder<Block, Block> DROSS_IRIDESCENT_ETHER_BRAZIER = BLOCKS.register("dross_iridescent_ether_brazier", () -> new EtherBrazierBlock<>(MalumBlockProperties.DROSS_ETHER_BRAZIER()).setBlockEntity(MalumBlockEntities.ETHER_BRAZIER));

    public static final DeferredHolder<Block, Block> TAINTED_ETHER_CRESSET = BLOCKS.register("tainted_ether_cresset", () -> new EtherCressetBlock<>(MalumBlockProperties.TAINTED_ETHER_CRESSET()).setBlockEntity(MalumBlockEntities.ETHER_CRESSET));
    public static final DeferredHolder<Block, Block> TWISTED_ETHER_CRESSET = BLOCKS.register("twisted_ether_cresset", () -> new EtherCressetBlock<>(MalumBlockProperties.TWISTED_ETHER_CRESSET()).setBlockEntity(MalumBlockEntities.ETHER_CRESSET));
    public static final DeferredHolder<Block, Block> DROSS_ETHER_CRESSET = BLOCKS.register("dross_ether_cresset", () -> new EtherCressetBlock<>(MalumBlockProperties.DROSS_ETHER_CRESSET()).setBlockEntity(MalumBlockEntities.ETHER_CRESSET));
    public static final DeferredHolder<Block, Block> TAINTED_IRIDESCENT_ETHER_CRESSET = BLOCKS.register("tainted_iridescent_ether_cresset", () -> new EtherCressetBlock<>(MalumBlockProperties.TAINTED_ETHER_CRESSET()).setBlockEntity(MalumBlockEntities.ETHER_CRESSET));
    public static final DeferredHolder<Block, Block> TWISTED_IRIDESCENT_ETHER_CRESSET = BLOCKS.register("twisted_iridescent_ether_cresset", () -> new EtherCressetBlock<>(MalumBlockProperties.TWISTED_ETHER_CRESSET()).setBlockEntity(MalumBlockEntities.ETHER_CRESSET));
    public static final DeferredHolder<Block, Block> DROSS_IRIDESCENT_ETHER_CRESSET = BLOCKS.register("dross_iridescent_ether_cresset", () -> new EtherCressetBlock<>(MalumBlockProperties.DROSS_ETHER_CRESSET()).setBlockEntity(MalumBlockEntities.ETHER_CRESSET));
    //endregion

    //region tainted rock
    public static final DeferredHolder<Block, Block> TAINTED_ROCK = BLOCKS.register("tainted_rock", () -> new Block(MalumBlockProperties.TAINTED_ROCK().addTag(TAINTED_ROCK_BLOCKS)));
    public static final DeferredHolder<Block, Block> POLISHED_TAINTED_ROCK = BLOCKS.register("polished_tainted_rock", () -> new Block(MalumBlockProperties.TAINTED_ROCK().addTag(TAINTED_ROCK_BLOCKS)));
    public static final DeferredHolder<Block, Block> TAINTED_ROCK_BRICKS = BLOCKS.register("tainted_rock_bricks", () -> new Block(MalumBlockProperties.TAINTED_ROCK_BRICKS().addTag(TAINTED_ROCK_BLOCKS)));
    public static final DeferredHolder<Block, Block> TAINTED_ROCK_TILES = BLOCKS.register("tainted_rock_tiles", () -> new Block(MalumBlockProperties.TAINTED_ROCK_BRICKS().addTag(TAINTED_ROCK_BLOCKS)));
    public static final DeferredHolder<Block, Block> TAINTED_ROCK_MOSAIC = BLOCKS.register("tainted_rock_mosaic", () -> new Block(MalumBlockProperties.TAINTED_ROCK_BRICKS().addTag(TAINTED_ROCK_BLOCKS)));

    public static final DeferredHolder<Block, Block> TAINTED_ROCK_COLUMN = BLOCKS.register("tainted_rock_column", () -> new ColumnBlock(MalumBlockProperties.CHISELED_TAINTED_ROCK()));
    public static final DeferredHolder<Block, Block> TAINTED_ROCK_ALTAR = BLOCKS.register("tainted_rock_altar", () -> new Block(MalumBlockProperties.CHISELED_TAINTED_ROCK().addTag(EIDOLON_ALTAR_BLOCK)));
    public static final DeferredHolder<Block, Block> CUT_TAINTED_ROCK = BLOCKS.register("cut_tainted_rock", () -> new Block(MalumBlockProperties.CHISELED_TAINTED_ROCK()));
    public static final DeferredHolder<Block, Block> CHISELED_TAINTED_ROCK = BLOCKS.register("chiseled_tainted_rock", () -> new Block(MalumBlockProperties.CHISELED_TAINTED_ROCK()));

    public static final DeferredHolder<Block, Block> TAINTED_ROCK_SLAB = BLOCKS.register("tainted_rock_slab", () -> new SlabBlock(MalumBlockProperties.TAINTED_ROCK().addTags(SLABS, TAINTED_ROCK_SLABS)));
    public static final DeferredHolder<Block, Block> POLISHED_TAINTED_ROCK_SLAB = BLOCKS.register("polished_tainted_rock_slab", () -> new SlabBlock(MalumBlockProperties.TAINTED_ROCK().addTags(SLABS, TAINTED_ROCK_SLABS)));
    public static final DeferredHolder<Block, Block> TAINTED_ROCK_BRICKS_SLAB = BLOCKS.register("tainted_rock_bricks_slab", () -> new SlabBlock(MalumBlockProperties.TAINTED_ROCK_BRICKS().addTags(SLABS, TAINTED_ROCK_SLABS)));
    public static final DeferredHolder<Block, Block> TAINTED_ROCK_TILES_SLAB = BLOCKS.register("tainted_rock_tiles_slab", () -> new SlabBlock(MalumBlockProperties.TAINTED_ROCK_BRICKS().addTags(SLABS, TAINTED_ROCK_SLABS)));
    public static final DeferredHolder<Block, Block> TAINTED_ROCK_MOSAIC_SLAB = BLOCKS.register("tainted_rock_mosaic_slab", () -> new SlabBlock(MalumBlockProperties.TAINTED_ROCK_BRICKS().addTags(SLABS, TAINTED_ROCK_SLABS)));

    public static final DeferredHolder<Block, Block> TAINTED_ROCK_STAIRS = BLOCKS.register("tainted_rock_stairs", () -> new StairBlock(TAINTED_ROCK.get().defaultBlockState(), MalumBlockProperties.TAINTED_ROCK().addTags(STAIRS, MalumTags.BlockTags.TAINTED_ROCK_STAIRS)));
    public static final DeferredHolder<Block, Block> POLISHED_TAINTED_ROCK_STAIRS = BLOCKS.register("polished_tainted_rock_stairs", () -> new StairBlock(TAINTED_ROCK.get().defaultBlockState(), MalumBlockProperties.TAINTED_ROCK().addTags(STAIRS, MalumTags.BlockTags.TAINTED_ROCK_STAIRS)));
    public static final DeferredHolder<Block, Block> TAINTED_ROCK_BRICKS_STAIRS = BLOCKS.register("tainted_rock_bricks_stairs", () -> new StairBlock(TAINTED_ROCK.get().defaultBlockState(), MalumBlockProperties.TAINTED_ROCK_BRICKS().addTags(STAIRS, MalumTags.BlockTags.TAINTED_ROCK_STAIRS)));
    public static final DeferredHolder<Block, Block> TAINTED_ROCK_TILES_STAIRS = BLOCKS.register("tainted_rock_tiles_stairs", () -> new StairBlock(TAINTED_ROCK.get().defaultBlockState(), MalumBlockProperties.TAINTED_ROCK_BRICKS().addTags(STAIRS, MalumTags.BlockTags.TAINTED_ROCK_STAIRS)));
    public static final DeferredHolder<Block, Block> TAINTED_ROCK_MOSAIC_STAIRS = BLOCKS.register("tainted_rock_mosaic_stairs", () -> new StairBlock(TAINTED_ROCK.get().defaultBlockState(), MalumBlockProperties.TAINTED_ROCK_BRICKS().addTags(STAIRS, MalumTags.BlockTags.TAINTED_ROCK_STAIRS)));

    public static final DeferredHolder<Block, Block> TAINTED_ROCK_WALL = BLOCKS.register("tainted_rock_wall", () -> new WallBlock(MalumBlockProperties.TAINTED_ROCK().addTags(WALLS, TAINTED_ROCK_WALLS)));
    public static final DeferredHolder<Block, Block> POLISHED_TAINTED_ROCK_WALL = BLOCKS.register("polished_tainted_rock_wall", () -> new WallBlock(MalumBlockProperties.TAINTED_ROCK().addTags(WALLS, TAINTED_ROCK_WALLS)));
    public static final DeferredHolder<Block, Block> TAINTED_ROCK_BRICKS_WALL = BLOCKS.register("tainted_rock_bricks_wall", () -> new WallBlock(MalumBlockProperties.TAINTED_ROCK_BRICKS().addTags(WALLS, TAINTED_ROCK_WALLS)));
    public static final DeferredHolder<Block, Block> TAINTED_ROCK_TILES_WALL = BLOCKS.register("tainted_rock_tiles_wall", () -> new WallBlock(MalumBlockProperties.TAINTED_ROCK_BRICKS().addTags(WALLS, TAINTED_ROCK_WALLS)));
    public static final DeferredHolder<Block, Block> TAINTED_ROCK_MOSAIC_WALL = BLOCKS.register("tainted_rock_mosaic_wall", () -> new WallBlock(MalumBlockProperties.TAINTED_ROCK_BRICKS().addTags(WALLS, TAINTED_ROCK_WALLS)));

    public static final DeferredHolder<Block, Block> TAINTED_ROCK_BUTTON = BLOCKS.register("tainted_rock_button", () -> new ButtonBlock(BlockSetType.STONE, 20, MalumBlockProperties.TAINTED_ROCK().noCollission().addTag(BUTTONS)));
    public static final DeferredHolder<Block, Block> TAINTED_ROCK_PRESSURE_PLATE = BLOCKS.register("tainted_rock_pressure_plate", () -> new PressurePlateBlock(BlockSetType.STONE, MalumBlockProperties.TAINTED_ROCK().noCollission().addTag(PRESSURE_PLATES)));

    public static final DeferredHolder<Block, Block> TAINTED_ROCK_ITEM_STAND = BLOCKS.register("tainted_rock_item_stand", () -> new ItemStandBlock<>(MalumBlockProperties.TAINTED_ROCK().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_STAND));
    public static final DeferredHolder<Block, Block> TAINTED_ROCK_ITEM_PEDESTAL = BLOCKS.register("tainted_rock_item_pedestal", () -> new ItemPedestalBlock<>(MalumBlockProperties.TAINTED_ROCK().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_PEDESTAL));
    //endregion

    //region twisted rock
    public static final DeferredHolder<Block, Block> TWISTED_ROCK = BLOCKS.register("twisted_rock", () -> new Block(MalumBlockProperties.TWISTED_ROCK().addTag(TWISTED_ROCK_BLOCKS)));
    public static final DeferredHolder<Block, Block> POLISHED_TWISTED_ROCK = BLOCKS.register("polished_twisted_rock", () -> new Block(MalumBlockProperties.TWISTED_ROCK().addTag(TWISTED_ROCK_BLOCKS)));
    public static final DeferredHolder<Block, Block> TWISTED_ROCK_BRICKS = BLOCKS.register("twisted_rock_bricks", () -> new Block(MalumBlockProperties.TWISTED_ROCK_BRICKS().addTag(TWISTED_ROCK_BLOCKS)));
    public static final DeferredHolder<Block, Block> TWISTED_ROCK_TILES = BLOCKS.register("twisted_rock_tiles", () -> new Block(MalumBlockProperties.TWISTED_ROCK_BRICKS().addTag(TWISTED_ROCK_BLOCKS)));
    public static final DeferredHolder<Block, Block> TWISTED_ROCK_MOSAIC = BLOCKS.register("twisted_rock_mosaic", () -> new Block(MalumBlockProperties.TWISTED_ROCK_BRICKS().addTag(TWISTED_ROCK_BLOCKS)));

    public static final DeferredHolder<Block, Block> TWISTED_ROCK_COLUMN = BLOCKS.register("twisted_rock_column", () -> new ColumnBlock(MalumBlockProperties.CHISELED_TWISTED_ROCK()));
    public static final DeferredHolder<Block, Block> TWISTED_ROCK_ALTAR = BLOCKS.register("twisted_rock_altar", () -> new Block(MalumBlockProperties.CHISELED_TWISTED_ROCK().addTag(EIDOLON_ALTAR_BLOCK)));
    public static final DeferredHolder<Block, Block> CUT_TWISTED_ROCK = BLOCKS.register("cut_twisted_rock", () -> new Block(MalumBlockProperties.CHISELED_TWISTED_ROCK()));
    public static final DeferredHolder<Block, Block> CHISELED_TWISTED_ROCK = BLOCKS.register("chiseled_twisted_rock", () -> new Block(MalumBlockProperties.CHISELED_TWISTED_ROCK()));

    public static final DeferredHolder<Block, Block> TWISTED_ROCK_SLAB = BLOCKS.register("twisted_rock_slab", () -> new SlabBlock(MalumBlockProperties.TWISTED_ROCK().addTags(SLABS, TWISTED_ROCK_SLABS)));
    public static final DeferredHolder<Block, Block> POLISHED_TWISTED_ROCK_SLAB = BLOCKS.register("polished_twisted_rock_slab", () -> new SlabBlock(MalumBlockProperties.TWISTED_ROCK().addTags(SLABS, TWISTED_ROCK_SLABS)));
    public static final DeferredHolder<Block, Block> TWISTED_ROCK_BRICKS_SLAB = BLOCKS.register("twisted_rock_bricks_slab", () -> new SlabBlock(MalumBlockProperties.TWISTED_ROCK_BRICKS().addTags(SLABS, TWISTED_ROCK_SLABS)));
    public static final DeferredHolder<Block, Block> TWISTED_ROCK_TILES_SLAB = BLOCKS.register("twisted_rock_tiles_slab", () -> new SlabBlock(MalumBlockProperties.TWISTED_ROCK_BRICKS().addTags(SLABS, TWISTED_ROCK_SLABS)));
    public static final DeferredHolder<Block, Block> TWISTED_ROCK_MOSAIC_SLAB = BLOCKS.register("twisted_rock_mosaic_slab", () -> new SlabBlock(MalumBlockProperties.TWISTED_ROCK_BRICKS().addTags(SLABS, TWISTED_ROCK_SLABS)));

    public static final DeferredHolder<Block, Block> TWISTED_ROCK_STAIRS = BLOCKS.register("twisted_rock_stairs", () -> new StairBlock(TWISTED_ROCK.get().defaultBlockState(), MalumBlockProperties.TWISTED_ROCK().addTags(STAIRS, MalumTags.BlockTags.TWISTED_ROCK_STAIRS)));
    public static final DeferredHolder<Block, Block> POLISHED_TWISTED_ROCK_STAIRS = BLOCKS.register("polished_twisted_rock_stairs", () -> new StairBlock(TWISTED_ROCK.get().defaultBlockState(), MalumBlockProperties.TWISTED_ROCK().addTags(STAIRS, MalumTags.BlockTags.TWISTED_ROCK_STAIRS)));
    public static final DeferredHolder<Block, Block> TWISTED_ROCK_BRICKS_STAIRS = BLOCKS.register("twisted_rock_bricks_stairs", () -> new StairBlock(TWISTED_ROCK.get().defaultBlockState(), MalumBlockProperties.TWISTED_ROCK_BRICKS().addTags(STAIRS, MalumTags.BlockTags.TWISTED_ROCK_STAIRS)));
    public static final DeferredHolder<Block, Block> TWISTED_ROCK_TILES_STAIRS = BLOCKS.register("twisted_rock_tiles_stairs", () -> new StairBlock(TWISTED_ROCK.get().defaultBlockState(), MalumBlockProperties.TWISTED_ROCK_BRICKS().addTags(STAIRS, MalumTags.BlockTags.TWISTED_ROCK_STAIRS)));
    public static final DeferredHolder<Block, Block> TWISTED_ROCK_MOSAIC_STAIRS = BLOCKS.register("twisted_rock_mosaic_stairs", () -> new StairBlock(TWISTED_ROCK.get().defaultBlockState(), MalumBlockProperties.TWISTED_ROCK_BRICKS().addTags(STAIRS, MalumTags.BlockTags.TWISTED_ROCK_STAIRS)));

    public static final DeferredHolder<Block, Block> TWISTED_ROCK_WALL = BLOCKS.register("twisted_rock_wall", () -> new WallBlock(MalumBlockProperties.TWISTED_ROCK().addTags(WALLS, TWISTED_ROCK_WALLS)));
    public static final DeferredHolder<Block, Block> POLISHED_TWISTED_ROCK_WALL = BLOCKS.register("polished_twisted_rock_wall", () -> new WallBlock(MalumBlockProperties.TWISTED_ROCK().addTags(WALLS, TWISTED_ROCK_WALLS)));
    public static final DeferredHolder<Block, Block> TWISTED_ROCK_BRICKS_WALL = BLOCKS.register("twisted_rock_bricks_wall", () -> new WallBlock(MalumBlockProperties.TWISTED_ROCK_BRICKS().addTags(WALLS, TWISTED_ROCK_WALLS)));
    public static final DeferredHolder<Block, Block> TWISTED_ROCK_TILES_WALL = BLOCKS.register("twisted_rock_tiles_wall", () -> new WallBlock(MalumBlockProperties.TWISTED_ROCK_BRICKS().addTags(WALLS, TWISTED_ROCK_WALLS)));
    public static final DeferredHolder<Block, Block> TWISTED_ROCK_MOSAIC_WALL = BLOCKS.register("twisted_rock_mosaic_wall", () -> new WallBlock(MalumBlockProperties.TWISTED_ROCK_BRICKS().addTags(WALLS, TWISTED_ROCK_WALLS)));

    public static final DeferredHolder<Block, Block> TWISTED_ROCK_BUTTON = BLOCKS.register("twisted_rock_button", () -> new ButtonBlock(BlockSetType.STONE, 20, MalumBlockProperties.TWISTED_ROCK().noCollission().addTag(BUTTONS)));
    public static final DeferredHolder<Block, Block> TWISTED_ROCK_PRESSURE_PLATE = BLOCKS.register("twisted_rock_pressure_plate", () -> new PressurePlateBlock(BlockSetType.STONE, MalumBlockProperties.TWISTED_ROCK().noCollission().addTag(PRESSURE_PLATES)));

    public static final DeferredHolder<Block, Block> TWISTED_ROCK_ITEM_STAND = BLOCKS.register("twisted_rock_item_stand", () -> new ItemStandBlock<>(MalumBlockProperties.TWISTED_ROCK().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_STAND));
    public static final DeferredHolder<Block, Block> TWISTED_ROCK_ITEM_PEDESTAL = BLOCKS.register("twisted_rock_item_pedestal", () -> new ItemPedestalBlock<>(MalumBlockProperties.TWISTED_ROCK().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_PEDESTAL));
    //endregion

    //region dross stone
    public static final DeferredHolder<Block, Block> DROSS_STONE = BLOCKS.register("dross_stone", () -> new Block(MalumBlockProperties.DROSS_STONE().addTag(DROSS_STONE_BLOCKS)));
    public static final DeferredHolder<Block, Block> POLISHED_DROSS_STONE = BLOCKS.register("polished_dross_stone", () -> new Block(MalumBlockProperties.DROSS_STONE().addTag(DROSS_STONE_BLOCKS)));
    public static final DeferredHolder<Block, Block> DROSS_STONE_BRICKS = BLOCKS.register("dross_stone_bricks", () -> new Block(MalumBlockProperties.DROSS_STONE_BRICKS().addTag(DROSS_STONE_BLOCKS)));
    public static final DeferredHolder<Block, Block> DROSS_STONE_TILES = BLOCKS.register("dross_stone_tiles", () -> new Block(MalumBlockProperties.DROSS_STONE_BRICKS().addTag(DROSS_STONE_BLOCKS)));
    public static final DeferredHolder<Block, Block> DROSS_STONE_MOSAIC = BLOCKS.register("dross_stone_mosaic", () -> new Block(MalumBlockProperties.DROSS_STONE_BRICKS().addTag(DROSS_STONE_BLOCKS)));

    public static final DeferredHolder<Block, Block> DARK_DROSS_TILES = BLOCKS.register("dark_dross_tiles", () -> new Block(MalumBlockProperties.DROSS_STONE()));
    public static final DeferredHolder<Block, Block> GRAY_DROSS_TILES = BLOCKS.register("gray_dross_tiles", () -> new Block(MalumBlockProperties.DROSS_STONE()));

    public static final DeferredHolder<Block, Block> DROSS_STONE_COLUMN = BLOCKS.register("dross_stone_column", () -> new ColumnBlock(MalumBlockProperties.CHISELED_DROSS_STONE()));
    public static final DeferredHolder<Block, Block> DROSS_STONE_ALTAR = BLOCKS.register("dross_stone_altar", () -> new Block(MalumBlockProperties.CHISELED_DROSS_STONE().addTag(EIDOLON_ALTAR_BLOCK)));
    public static final DeferredHolder<Block, Block> CUT_DROSS_STONE = BLOCKS.register("cut_dross_stone", () -> new Block(MalumBlockProperties.CHISELED_DROSS_STONE()));
    public static final DeferredHolder<Block, Block> CHISELED_DROSS_STONE = BLOCKS.register("chiseled_dross_stone", () -> new Block(MalumBlockProperties.CHISELED_DROSS_STONE()));

    public static final DeferredHolder<Block, Block> DROSS_STONE_SLAB = BLOCKS.register("dross_stone_slab", () -> new SlabBlock(MalumBlockProperties.DROSS_STONE().addTags(SLABS, DROSS_STONE_SLABS)));
    public static final DeferredHolder<Block, Block> POLISHED_DROSS_STONE_SLAB = BLOCKS.register("polished_dross_stone_slab", () -> new SlabBlock(MalumBlockProperties.DROSS_STONE().addTags(SLABS, DROSS_STONE_SLABS)));
    public static final DeferredHolder<Block, Block> DROSS_STONE_BRICKS_SLAB = BLOCKS.register("dross_stone_bricks_slab", () -> new SlabBlock(MalumBlockProperties.DROSS_STONE_BRICKS().addTags(SLABS, DROSS_STONE_SLABS)));
    public static final DeferredHolder<Block, Block> DROSS_STONE_TILES_SLAB = BLOCKS.register("dross_stone_tiles_slab", () -> new SlabBlock(MalumBlockProperties.DROSS_STONE_BRICKS().addTags(SLABS, DROSS_STONE_SLABS)));
    public static final DeferredHolder<Block, Block> DROSS_STONE_MOSAIC_SLAB = BLOCKS.register("dross_stone_mosaic_slab", () -> new SlabBlock(MalumBlockProperties.DROSS_STONE_BRICKS().addTags(SLABS, DROSS_STONE_SLABS)));

    public static final DeferredHolder<Block, Block> DARK_DROSS_TILES_SLAB = BLOCKS.register("dark_dross_tiles_slab", () -> new SlabBlock(MalumBlockProperties.DROSS_STONE().addTags(SLABS)));
    public static final DeferredHolder<Block, Block> GRAY_DROSS_TILES_SLAB = BLOCKS.register("gray_dross_tiles_slab", () -> new SlabBlock(MalumBlockProperties.DROSS_STONE().addTags(SLABS)));

    public static final DeferredHolder<Block, Block> DROSS_STONE_STAIRS = BLOCKS.register("dross_stone_stairs", () -> new StairBlock(DROSS_STONE.get().defaultBlockState(), MalumBlockProperties.DROSS_STONE().addTags(STAIRS, MalumTags.BlockTags.DROSS_STONE_STAIRS)));
    public static final DeferredHolder<Block, Block> POLISHED_DROSS_STONE_STAIRS = BLOCKS.register("polished_dross_stone_stairs", () -> new StairBlock(DROSS_STONE.get().defaultBlockState(), MalumBlockProperties.DROSS_STONE().addTags(STAIRS, MalumTags.BlockTags.DROSS_STONE_STAIRS)));
    public static final DeferredHolder<Block, Block> DROSS_STONE_BRICKS_STAIRS = BLOCKS.register("dross_stone_bricks_stairs", () -> new StairBlock(DROSS_STONE.get().defaultBlockState(), MalumBlockProperties.DROSS_STONE_BRICKS().addTags(STAIRS, MalumTags.BlockTags.DROSS_STONE_STAIRS)));
    public static final DeferredHolder<Block, Block> DROSS_STONE_TILES_STAIRS = BLOCKS.register("dross_stone_tiles_stairs", () -> new StairBlock(DROSS_STONE.get().defaultBlockState(), MalumBlockProperties.DROSS_STONE_BRICKS().addTags(STAIRS, MalumTags.BlockTags.DROSS_STONE_STAIRS)));
    public static final DeferredHolder<Block, Block> DROSS_STONE_MOSAIC_STAIRS = BLOCKS.register("dross_stone_mosaic_stairs", () -> new StairBlock(DROSS_STONE.get().defaultBlockState(), MalumBlockProperties.DROSS_STONE_BRICKS().addTags(STAIRS, MalumTags.BlockTags.DROSS_STONE_STAIRS)));

    public static final DeferredHolder<Block, Block> DARK_DROSS_TILES_STAIRS = BLOCKS.register("dark_dross_tiles_stairs", () -> new StairBlock(DROSS_STONE.get().defaultBlockState(), MalumBlockProperties.DROSS_STONE().addTags(STAIRS)));
    public static final DeferredHolder<Block, Block> GRAY_DROSS_TILES_STAIRS = BLOCKS.register("gray_dross_tiles_stairs", () -> new StairBlock(DROSS_STONE.get().defaultBlockState(), MalumBlockProperties.DROSS_STONE().addTags(STAIRS)));

    public static final DeferredHolder<Block, Block> DROSS_STONE_WALL = BLOCKS.register("dross_stone_wall", () -> new WallBlock(MalumBlockProperties.DROSS_STONE().addTags(WALLS, DROSS_STONE_WALLS)));
    public static final DeferredHolder<Block, Block> POLISHED_DROSS_STONE_WALL = BLOCKS.register("polished_dross_stone_wall", () -> new WallBlock(MalumBlockProperties.DROSS_STONE().addTags(WALLS, DROSS_STONE_WALLS)));
    public static final DeferredHolder<Block, Block> DROSS_STONE_BRICKS_WALL = BLOCKS.register("dross_stone_bricks_wall", () -> new WallBlock(MalumBlockProperties.DROSS_STONE_BRICKS().addTags(WALLS, DROSS_STONE_WALLS)));
    public static final DeferredHolder<Block, Block> DROSS_STONE_TILES_WALL = BLOCKS.register("dross_stone_tiles_wall", () -> new WallBlock(MalumBlockProperties.DROSS_STONE_BRICKS().addTags(WALLS, DROSS_STONE_WALLS)));
    public static final DeferredHolder<Block, Block> DROSS_STONE_MOSAIC_WALL = BLOCKS.register("dross_stone_mosaic_wall", () -> new WallBlock(MalumBlockProperties.DROSS_STONE_BRICKS().addTags(WALLS, DROSS_STONE_WALLS)));

    public static final DeferredHolder<Block, Block> DARK_DROSS_TILES_WALL = BLOCKS.register("dark_dross_tiles_wall", () -> new WallBlock(MalumBlockProperties.DROSS_STONE().addTags(WALLS)));
    public static final DeferredHolder<Block, Block> GRAY_DROSS_TILES_WALL = BLOCKS.register("gray_dross_tiles_wall", () -> new WallBlock(MalumBlockProperties.DROSS_STONE().addTags(WALLS)));

    public static final DeferredHolder<Block, Block> DROSS_STONE_BUTTON = BLOCKS.register("dross_stone_button", () -> new ButtonBlock(BlockSetType.STONE, 20, MalumBlockProperties.DROSS_STONE().noCollission().addTag(BUTTONS)));
    public static final DeferredHolder<Block, Block> DROSS_STONE_PRESSURE_PLATE = BLOCKS.register("dross_stone_pressure_plate", () -> new PressurePlateBlock(BlockSetType.STONE, MalumBlockProperties.DROSS_STONE().noCollission().addTag(PRESSURE_PLATES)));

    public static final DeferredHolder<Block, Block> DROSS_STONE_ITEM_STAND = BLOCKS.register("dross_stone_item_stand", () -> new ItemStandBlock<>(MalumBlockProperties.DROSS_STONE().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_STAND));
    public static final DeferredHolder<Block, Block> DROSS_STONE_ITEM_PEDESTAL = BLOCKS.register("dross_stone_item_pedestal", () -> new ItemPedestalBlock<>(MalumBlockProperties.DROSS_STONE().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_PEDESTAL));
    //endregion


    //region runewood
    public static final DeferredHolder<Block, Block> RUNEWOOD_SAPLING = BLOCKS.register("runewood_sapling", () -> new MalumSaplingBlock(MalumTreeGrowers.RUNEWOOD, MalumBlockProperties.RUNEWOOD_SAPLING()));
    public static final DeferredHolder<Block, Block> AZURE_RUNEWOOD_SAPLING = BLOCKS.register("azure_runewood_sapling", () -> new MalumSaplingBlock(MalumTreeGrowers.AZURE_RUNEWOOD, MalumBlockProperties.RUNEWOOD_SAPLING()));

    public static final DeferredHolder<Block, Block> RUNEWOOD_LEAVES = BLOCKS.register("runewood_leaves", () -> new MalumLeavesBlock(MalumBlockProperties.RUNEWOOD_LEAVES(), MalumBlockProperties.RUNEWOOD_LEAVES_ORANGE, MalumBlockProperties.RUNEWOOD_LEAVES_YELLOW));
    public static final DeferredHolder<Block, Block> AZURE_RUNEWOOD_LEAVES = BLOCKS.register("azure_runewood_leaves", () -> new MalumLeavesBlock(MalumBlockProperties.RUNEWOOD_LEAVES(), MalumBlockProperties.AZURE_RUNEWOOD_LEAVES_BLUE, MalumBlockProperties.AZURE_RUNEWOOD_LEAVES_CYAN));

    public static final DeferredHolder<Block, Block> HANGING_RUNEWOOD_LEAVES = BLOCKS.register("hanging_runewood_leaves", () -> new MalumHangingLeavesBlock(MalumBlockProperties.HANGING_RUNEWOOD_LEAVES().setCutoutRenderType().noOcclusion().noCollission(), MalumBlockProperties.RUNEWOOD_LEAVES_ORANGE, MalumBlockProperties.RUNEWOOD_LEAVES_YELLOW));
    public static final DeferredHolder<Block, Block> HANGING_AZURE_RUNEWOOD_LEAVES = BLOCKS.register("hanging_azure_runewood_leaves", () -> new MalumHangingLeavesBlock(MalumBlockProperties.HANGING_RUNEWOOD_LEAVES().setCutoutRenderType().noOcclusion().noCollission(), MalumBlockProperties.AZURE_RUNEWOOD_LEAVES_BLUE, MalumBlockProperties.AZURE_RUNEWOOD_LEAVES_CYAN));

    public static final DeferredHolder<Block, Block> STRIPPED_RUNEWOOD_LOG = BLOCKS.register("stripped_runewood_log", () -> new RotatedPillarBlock(MalumBlockProperties.RUNEWOOD_LOGS().addTags(STRIPPED_LOGS)));
    public static final DeferredHolder<Block, Block> RUNEWOOD_LOG = BLOCKS.register("runewood_log", () -> new MalumLogBlock(MalumBlockProperties.RUNEWOOD(), STRIPPED_RUNEWOOD_LOG));

    public static final DeferredHolder<Block, Block> STRIPPED_RUNEWOOD = BLOCKS.register("stripped_runewood", () -> new RotatedPillarBlock(MalumBlockProperties.RUNEWOOD_LOGS().addTags(STRIPPED_WOODS)));
    public static final DeferredHolder<Block, Block> RUNEWOOD = BLOCKS.register("runewood", () -> new LodestoneLogBlock(MalumBlockProperties.RUNEWOOD(), STRIPPED_RUNEWOOD));

    public static final DeferredHolder<Block, Block> STRIPPED_SAPPY_RUNEWOOD_LOG = BLOCKS.register("stripped_sappy_runewood_log", () -> new SapFilledLogBlock(MalumBlockProperties.RUNEWOOD_LOGS(), STRIPPED_RUNEWOOD_LOG, MalumItems.RUNIC_SAP, MalumSpiritTypes.INFERNAL_COLORS().primaryColor()));
    public static final DeferredHolder<Block, Block> SAPPY_RUNEWOOD_LOG = BLOCKS.register("sappy_runewood_log", () -> new LodestoneLogBlock(MalumBlockProperties.RUNEWOOD().addTags(STRIPPED_LOGS), STRIPPED_SAPPY_RUNEWOOD_LOG));

    public static final DeferredHolder<Block, Block> RUNEWOOD_BOARDS = BLOCKS.register("runewood_boards", () -> new Block(MalumBlockProperties.RUNEWOOD_PLANKS()));
    public static final DeferredHolder<Block, Block> RUNEWOOD_BOARDS_SLAB = BLOCKS.register("runewood_boards_slab", () -> new SlabBlock(MalumBlockProperties.RUNEWOOD_SLABS()));
    public static final DeferredHolder<Block, Block> RUNEWOOD_BOARDS_STAIRS = BLOCKS.register("runewood_boards_stairs", () -> new StairBlock(RUNEWOOD_BOARDS.get().defaultBlockState(), MalumBlockProperties.RUNEWOOD_STAIRS()));

    public static final DeferredHolder<Block, Block> VERTICAL_RUNEWOOD_BOARDS = BLOCKS.register("vertical_runewood_boards", () -> new Block(MalumBlockProperties.RUNEWOOD_PLANKS()));
    public static final DeferredHolder<Block, Block> VERTICAL_RUNEWOOD_BOARDS_SLAB = BLOCKS.register("vertical_runewood_boards_slab", () -> new SlabBlock(MalumBlockProperties.RUNEWOOD_SLABS()));
    public static final DeferredHolder<Block, Block> VERTICAL_RUNEWOOD_BOARDS_STAIRS = BLOCKS.register("vertical_runewood_boards_stairs", () -> new StairBlock(VERTICAL_RUNEWOOD_BOARDS.get().defaultBlockState(), MalumBlockProperties.RUNEWOOD_STAIRS()));

    public static final DeferredHolder<Block, Block> RUNEWOOD_PLANKS = BLOCKS.register("runewood_planks", () -> new Block(MalumBlockProperties.RUNEWOOD_PLANKS()));
    public static final DeferredHolder<Block, Block> RUNEWOOD_PLANKS_SLAB = BLOCKS.register("runewood_planks_slab", () -> new SlabBlock(MalumBlockProperties.RUNEWOOD_SLABS()));
    public static final DeferredHolder<Block, Block> RUNEWOOD_PLANKS_STAIRS = BLOCKS.register("runewood_planks_stairs", () -> new StairBlock(RUNEWOOD_PLANKS.get().defaultBlockState(), MalumBlockProperties.RUNEWOOD_STAIRS()));

    public static final DeferredHolder<Block, Block> RUSTIC_RUNEWOOD_PLANKS = BLOCKS.register("rustic_runewood_planks", () -> new Block(MalumBlockProperties.RUNEWOOD_PLANKS()));
    public static final DeferredHolder<Block, Block> RUSTIC_RUNEWOOD_PLANKS_SLAB = BLOCKS.register("rustic_runewood_planks_slab", () -> new SlabBlock(MalumBlockProperties.RUNEWOOD_SLABS()));
    public static final DeferredHolder<Block, Block> RUSTIC_RUNEWOOD_PLANKS_STAIRS = BLOCKS.register("rustic_runewood_planks_stairs", () -> new StairBlock(RUSTIC_RUNEWOOD_PLANKS.get().defaultBlockState(), MalumBlockProperties.RUNEWOOD_STAIRS()));

    public static final DeferredHolder<Block, Block> VERTICAL_RUNEWOOD_PLANKS = BLOCKS.register("vertical_runewood_planks", () -> new Block(MalumBlockProperties.RUNEWOOD_PLANKS()));
    public static final DeferredHolder<Block, Block> VERTICAL_RUNEWOOD_PLANKS_SLAB = BLOCKS.register("vertical_runewood_planks_slab", () -> new SlabBlock(MalumBlockProperties.RUNEWOOD_SLABS()));
    public static final DeferredHolder<Block, Block> VERTICAL_RUNEWOOD_PLANKS_STAIRS = BLOCKS.register("vertical_runewood_planks_stairs", () -> new StairBlock(VERTICAL_RUNEWOOD_PLANKS.get().defaultBlockState(), MalumBlockProperties.RUNEWOOD_STAIRS()));

    public static final DeferredHolder<Block, Block> VERTICAL_RUSTIC_RUNEWOOD_PLANKS = BLOCKS.register("vertical_rustic_runewood_planks", () -> new Block(MalumBlockProperties.RUNEWOOD_PLANKS()));
    public static final DeferredHolder<Block, Block> VERTICAL_RUSTIC_RUNEWOOD_PLANKS_SLAB = BLOCKS.register("vertical_rustic_runewood_planks_slab", () -> new SlabBlock(MalumBlockProperties.RUNEWOOD_SLABS()));
    public static final DeferredHolder<Block, Block> VERTICAL_RUSTIC_RUNEWOOD_PLANKS_STAIRS = BLOCKS.register("vertical_rustic_runewood_planks_stairs", () -> new StairBlock(VERTICAL_RUSTIC_RUNEWOOD_PLANKS.get().defaultBlockState(), MalumBlockProperties.RUNEWOOD_STAIRS()));

    public static final DeferredHolder<Block, Block> RUNEWOOD_TILES = BLOCKS.register("runewood_tiles", () -> new Block(MalumBlockProperties.RUNEWOOD_PLANKS()));
    public static final DeferredHolder<Block, Block> RUNEWOOD_TILES_SLAB = BLOCKS.register("runewood_tiles_slab", () -> new SlabBlock(MalumBlockProperties.RUNEWOOD_SLABS()));
    public static final DeferredHolder<Block, Block> RUNEWOOD_TILES_STAIRS = BLOCKS.register("runewood_tiles_stairs", () -> new StairBlock(RUNEWOOD_TILES.get().defaultBlockState(), MalumBlockProperties.RUNEWOOD_STAIRS()));

    public static final DeferredHolder<Block, Block> RUSTIC_RUNEWOOD_TILES = BLOCKS.register("rustic_runewood_tiles", () -> new Block(MalumBlockProperties.RUNEWOOD_PLANKS()));
    public static final DeferredHolder<Block, Block> RUSTIC_RUNEWOOD_TILES_SLAB = BLOCKS.register("rustic_runewood_tiles_slab", () -> new SlabBlock(MalumBlockProperties.RUNEWOOD_SLABS()));
    public static final DeferredHolder<Block, Block> RUSTIC_RUNEWOOD_TILES_STAIRS = BLOCKS.register("rustic_runewood_tiles_stairs", () -> new StairBlock(RUSTIC_RUNEWOOD_TILES.get().defaultBlockState(), MalumBlockProperties.RUNEWOOD_STAIRS()));

    public static final DeferredHolder<Block, Block> RUNEWOOD_PANEL = BLOCKS.register("runewood_panel", () -> new Block(MalumBlockProperties.RUNEWOOD()));
    public static final DeferredHolder<Block, Block> CUT_RUNEWOOD_PLANKS = BLOCKS.register("cut_runewood_planks", () -> new Block(MalumBlockProperties.RUNEWOOD_PLANKS()));
    public static final DeferredHolder<Block, Block> RUNEWOOD_BEAM = BLOCKS.register("runewood_beam", () -> new RotatedPillarBlock(MalumBlockProperties.RUNEWOOD()));

    public static final DeferredHolder<Block, Block> RUNEWOOD_DOOR = BLOCKS.register("runewood_door", () -> new DoorBlock(MalumBlockSetTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD_DOOR()));
    public static final DeferredHolder<Block, Block> RUNEWOOD_BOARDS_DOOR = BLOCKS.register("runewood_boards_door", () -> new DoorBlock(MalumBlockSetTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD_DOOR()));
    public static final DeferredHolder<Block, Block> RUNEWOOD_TRAPDOOR = BLOCKS.register("runewood_trapdoor", () -> new TrapDoorBlock(MalumBlockSetTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD_TRAPDOOR()));
    public static final DeferredHolder<Block, Block> RUNEWOOD_BOARDS_TRAPDOOR = BLOCKS.register("runewood_boards_trapdoor", () -> new TrapDoorBlock(MalumBlockSetTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD_TRAPDOOR()));

    public static final DeferredHolder<Block, Block> BOLTED_RUNEWOOD_DOOR = BLOCKS.register("bolted_runewood_door", () -> new DoorBlock(MalumBlockSetTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD_DOOR()));
    public static final DeferredHolder<Block, Block> BOLTED_RUNEWOOD_BOARDS_DOOR = BLOCKS.register("bolted_runewood_boards_door", () -> new DoorBlock(MalumBlockSetTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD_DOOR()));
    public static final DeferredHolder<Block, Block> BOLTED_RUNEWOOD_TRAPDOOR = BLOCKS.register("bolted_runewood_trapdoor", () -> new TrapDoorBlock(MalumBlockSetTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD_TRAPDOOR()));
    public static final DeferredHolder<Block, Block> BOLTED_RUNEWOOD_BOARDS_TRAPDOOR = BLOCKS.register("bolted_runewood_boards_trapdoor", () -> new TrapDoorBlock(MalumBlockSetTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD_TRAPDOOR()));

    public static final DeferredHolder<Block, Block> RUNEWOOD_BUTTON = BLOCKS.register("runewood_planks_button", () -> new ButtonBlock(MalumBlockSetTypes.RUNEWOOD, 20, MalumBlockProperties.RUNEWOOD().noCollission().addTags(BUTTONS, WOODEN_BUTTONS).addTags(BUTTONS, WOODEN_BUTTONS)));
    public static final DeferredHolder<Block, Block> RUNEWOOD_PRESSURE_PLATE = BLOCKS.register("runewood_planks_pressure_plate", () -> new PressurePlateBlock(MalumBlockSetTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD().noCollission().addTags(PRESSURE_PLATES, WOODEN_PRESSURE_PLATES)));

    public static final DeferredHolder<Block, Block> RUNEWOOD_BOARDS_WALL = BLOCKS.register("runewood_boards_wall", () -> new WallBlock(MalumBlockProperties.RUNEWOOD().addTags(WALLS)));
    public static final DeferredHolder<Block, Block> RUNEWOOD_FENCE = BLOCKS.register("runewood_planks_fence", () -> new FenceBlock(MalumBlockProperties.RUNEWOOD().addTags(FENCES, WOODEN_FENCES)));
    public static final DeferredHolder<Block, Block> RUNEWOOD_FENCE_GATE = BLOCKS.register("runewood_planks_fence_gate", () -> new FenceGateBlock(MalumWoodTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD().addTags(FENCE_GATES, FENCE_GATES_WOODEN)));

    public static final DeferredHolder<Block, Block> RUNEWOOD_ITEM_STAND = BLOCKS.register("runewood_item_stand", () -> new ItemStandBlock<>(MalumBlockProperties.RUNEWOOD().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_STAND));
    public static final DeferredHolder<Block, Block> GILDED_RUNEWOOD_ITEM_PEDESTAL = BLOCKS.register("gilded_runewood_item_pedestal", () -> new DecoratedItemPedestalBlock<>(MalumBlockProperties.RUNEWOOD().setCutoutRenderType().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_PEDESTAL));
    public static final DeferredHolder<Block, Block> RUNEWOOD_ITEM_PEDESTAL = BLOCKS.register("runewood_item_pedestal", () -> new WoodItemPedestalBlock<>(MalumBlockProperties.RUNEWOOD().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_PEDESTAL));
    public static final DeferredHolder<Block, Block> GILDED_RUNEWOOD_ITEM_STAND = BLOCKS.register("gilded_runewood_item_stand", () -> new ItemStandBlock<>(MalumBlockProperties.RUNEWOOD().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_STAND));

    public static final DeferredHolder<Block, Block> RUNEWOOD_SIGN = BLOCKS.register("runewood_sign", () -> new StandingSignBlock(MalumWoodTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD().addTags(SIGNS, STANDING_SIGNS).noOcclusion().noCollission()));
    public static final DeferredHolder<Block, Block> RUNEWOOD_WALL_SIGN = BLOCKS.register("runewood_wall_sign", () -> new WallSignBlock(MalumWoodTypes.RUNEWOOD, MalumBlockProperties.RUNEWOOD().addTags(SIGNS, WALL_SIGNS).noOcclusion().noCollission()));
    //endregion

    //region soulwood
    public static final DeferredHolder<Block, Block> SOULWOOD_SAPLING = BLOCKS.register("soulwood_sapling", () -> new SoulwoodGrowthBlock(MalumTreeGrowers.SOULWOOD, MalumBlockProperties.SOULWOOD_SAPLING()));

    public static final DeferredHolder<Block, Block> SOULWOOD_LEAVES = BLOCKS.register("soulwood_leaves", () -> new MalumLeavesBlock(MalumBlockProperties.SOULWOOD_LEAVES().setCutoutRenderType(), new Color(213, 8, 63), new Color(255, 61, 243)));
    public static final DeferredHolder<Block, Block> HANGING_SOULWOOD_LEAVES = BLOCKS.register("hanging_soulwood_leaves", () -> new MalumHangingLeavesBlock(MalumBlockProperties.HANGING_SOULWOOD_LEAVES().setCutoutRenderType().noOcclusion().noCollission(), new Color(213, 8, 63), new Color(255, 61, 243)));

    public static final DeferredHolder<Block, Block> BLIGHTED_SOULWOOD = BLOCKS.register("blighted_soulwood", () -> new BlightedSoulwoodBlock(MalumBlockProperties.SOULWOOD_LOGS()));

    public static final DeferredHolder<Block, Block> STRIPPED_SOULWOOD_LOG = BLOCKS.register("stripped_soulwood_log", () -> new RotatedPillarBlock(MalumBlockProperties.SOULWOOD_LOGS().addTags(STRIPPED_LOGS)));
    public static final DeferredHolder<Block, Block> SOULWOOD_LOG = BLOCKS.register("soulwood_log", () -> new SoulwoodLogBlock(MalumBlockProperties.SOULWOOD_LOGS(), STRIPPED_SOULWOOD_LOG));

    public static final DeferredHolder<Block, Block> STRIPPED_SOULWOOD = BLOCKS.register("stripped_soulwood", () -> new RotatedPillarBlock(MalumBlockProperties.SOULWOOD_LOGS().addTags(STRIPPED_WOODS)));
    public static final DeferredHolder<Block, Block> SOULWOOD = BLOCKS.register("soulwood", () -> new SoulwoodBlock(MalumBlockProperties.SOULWOOD_LOGS(), STRIPPED_SOULWOOD));

    public static final DeferredHolder<Block, Block> STRIPPED_SAPPY_SOULWOOD_LOG = BLOCKS.register("stripped_sappy_soulwood_log", () -> new SapFilledSoulwoodLogBlock(MalumBlockProperties.SOULWOOD_LOGS(), STRIPPED_SOULWOOD_LOG, MalumItems.CURSED_SAP, MalumSpiritTypes.ELDRITCH_COLORS().primaryColor(), new Color(255, 61, 106)));
    public static final DeferredHolder<Block, Block> SAPPY_SOULWOOD_LOG = BLOCKS.register("sappy_soulwood_log", () -> new LodestoneLogBlock(MalumBlockProperties.SOULWOOD_LOGS().addTags(STRIPPED_LOGS), STRIPPED_SAPPY_SOULWOOD_LOG));

    public static final DeferredHolder<Block, Block> SOULWOOD_BOARDS = BLOCKS.register("soulwood_boards", () -> new Block(MalumBlockProperties.SOULWOOD_PLANKS()));
    public static final DeferredHolder<Block, Block> SOULWOOD_BOARDS_SLAB = BLOCKS.register("soulwood_boards_slab", () -> new SlabBlock(MalumBlockProperties.SOULWOOD_SLABS()));
    public static final DeferredHolder<Block, Block> SOULWOOD_BOARDS_STAIRS = BLOCKS.register("soulwood_boards_stairs", () -> new StairBlock(SOULWOOD_BOARDS.get().defaultBlockState(), MalumBlockProperties.SOULWOOD_STAIRS()));

    public static final DeferredHolder<Block, Block> VERTICAL_SOULWOOD_BOARDS = BLOCKS.register("vertical_soulwood_boards", () -> new Block(MalumBlockProperties.SOULWOOD_PLANKS()));
    public static final DeferredHolder<Block, Block> VERTICAL_SOULWOOD_BOARDS_SLAB = BLOCKS.register("vertical_soulwood_boards_slab", () -> new SlabBlock(MalumBlockProperties.SOULWOOD_SLABS()));
    public static final DeferredHolder<Block, Block> VERTICAL_SOULWOOD_BOARDS_STAIRS = BLOCKS.register("vertical_soulwood_boards_stairs", () -> new StairBlock(VERTICAL_SOULWOOD_BOARDS.get().defaultBlockState(), MalumBlockProperties.SOULWOOD_STAIRS()));

    public static final DeferredHolder<Block, Block> SOULWOOD_PLANKS = BLOCKS.register("soulwood_planks", () -> new Block(MalumBlockProperties.SOULWOOD_PLANKS()));
    public static final DeferredHolder<Block, Block> SOULWOOD_PLANKS_SLAB = BLOCKS.register("soulwood_planks_slab", () -> new SlabBlock(MalumBlockProperties.SOULWOOD_SLABS()));
    public static final DeferredHolder<Block, Block> SOULWOOD_PLANKS_STAIRS = BLOCKS.register("soulwood_planks_stairs", () -> new StairBlock(SOULWOOD_PLANKS.get().defaultBlockState(), MalumBlockProperties.SOULWOOD_STAIRS()));

    public static final DeferredHolder<Block, Block> RUSTIC_SOULWOOD_PLANKS = BLOCKS.register("rustic_soulwood_planks", () -> new Block(MalumBlockProperties.SOULWOOD_PLANKS()));
    public static final DeferredHolder<Block, Block> RUSTIC_SOULWOOD_PLANKS_SLAB = BLOCKS.register("rustic_soulwood_planks_slab", () -> new SlabBlock(MalumBlockProperties.SOULWOOD_SLABS()));
    public static final DeferredHolder<Block, Block> RUSTIC_SOULWOOD_PLANKS_STAIRS = BLOCKS.register("rustic_soulwood_planks_stairs", () -> new StairBlock(RUSTIC_SOULWOOD_PLANKS.get().defaultBlockState(), MalumBlockProperties.SOULWOOD_STAIRS()));

    public static final DeferredHolder<Block, Block> VERTICAL_SOULWOOD_PLANKS = BLOCKS.register("vertical_soulwood_planks", () -> new Block(MalumBlockProperties.SOULWOOD_PLANKS()));
    public static final DeferredHolder<Block, Block> VERTICAL_SOULWOOD_PLANKS_SLAB = BLOCKS.register("vertical_soulwood_planks_slab", () -> new SlabBlock(MalumBlockProperties.SOULWOOD_SLABS()));
    public static final DeferredHolder<Block, Block> VERTICAL_SOULWOOD_PLANKS_STAIRS = BLOCKS.register("vertical_soulwood_planks_stairs", () -> new StairBlock(VERTICAL_SOULWOOD_PLANKS.get().defaultBlockState(), MalumBlockProperties.SOULWOOD_STAIRS()));

    public static final DeferredHolder<Block, Block> VERTICAL_RUSTIC_SOULWOOD_PLANKS = BLOCKS.register("vertical_rustic_soulwood_planks", () -> new Block(MalumBlockProperties.SOULWOOD_PLANKS()));
    public static final DeferredHolder<Block, Block> VERTICAL_RUSTIC_SOULWOOD_PLANKS_SLAB = BLOCKS.register("vertical_rustic_soulwood_planks_slab", () -> new SlabBlock(MalumBlockProperties.SOULWOOD_SLABS()));
    public static final DeferredHolder<Block, Block> VERTICAL_RUSTIC_SOULWOOD_PLANKS_STAIRS = BLOCKS.register("vertical_rustic_soulwood_planks_stairs", () -> new StairBlock(VERTICAL_RUSTIC_SOULWOOD_PLANKS.get().defaultBlockState(), MalumBlockProperties.SOULWOOD_STAIRS()));

    public static final DeferredHolder<Block, Block> SOULWOOD_TILES = BLOCKS.register("soulwood_tiles", () -> new Block(MalumBlockProperties.SOULWOOD_PLANKS()));
    public static final DeferredHolder<Block, Block> SOULWOOD_TILES_SLAB = BLOCKS.register("soulwood_tiles_slab", () -> new SlabBlock(MalumBlockProperties.SOULWOOD_SLABS()));
    public static final DeferredHolder<Block, Block> SOULWOOD_TILES_STAIRS = BLOCKS.register("soulwood_tiles_stairs", () -> new StairBlock(SOULWOOD_TILES.get().defaultBlockState(), MalumBlockProperties.SOULWOOD_STAIRS()));

    public static final DeferredHolder<Block, Block> RUSTIC_SOULWOOD_TILES = BLOCKS.register("rustic_soulwood_tiles", () -> new Block(MalumBlockProperties.SOULWOOD_PLANKS()));
    public static final DeferredHolder<Block, Block> RUSTIC_SOULWOOD_TILES_SLAB = BLOCKS.register("rustic_soulwood_tiles_slab", () -> new SlabBlock(MalumBlockProperties.SOULWOOD_SLABS()));
    public static final DeferredHolder<Block, Block> RUSTIC_SOULWOOD_TILES_STAIRS = BLOCKS.register("rustic_soulwood_tiles_stairs", () -> new StairBlock(RUSTIC_SOULWOOD_TILES.get().defaultBlockState(), MalumBlockProperties.SOULWOOD_STAIRS()));

    public static final DeferredHolder<Block, Block> SOULWOOD_PANEL = BLOCKS.register("soulwood_panel", () -> new Block(MalumBlockProperties.SOULWOOD()));
    public static final DeferredHolder<Block, Block> CUT_SOULWOOD_PLANKS = BLOCKS.register("cut_soulwood_planks", () -> new Block(MalumBlockProperties.SOULWOOD_PLANKS()));
    public static final DeferredHolder<Block, Block> SOULWOOD_BEAM = BLOCKS.register("soulwood_beam", () -> new RotatedPillarBlock(MalumBlockProperties.SOULWOOD()));

    public static final DeferredHolder<Block, Block> SOULWOOD_DOOR = BLOCKS.register("soulwood_door", () -> new DoorBlock(MalumBlockSetTypes.SOULWOOD, MalumBlockProperties.SOULWOOD_DOOR()));
    public static final DeferredHolder<Block, Block> SOULWOOD_BOARDS_DOOR = BLOCKS.register("soulwood_boards_door", () -> new DoorBlock(MalumBlockSetTypes.SOULWOOD, MalumBlockProperties.SOULWOOD_DOOR()));
    public static final DeferredHolder<Block, Block> SOULWOOD_TRAPDOOR = BLOCKS.register("soulwood_trapdoor", () -> new TrapDoorBlock(MalumBlockSetTypes.SOULWOOD, MalumBlockProperties.SOULWOOD_TRAPDOOR()));
    public static final DeferredHolder<Block, Block> SOULWOOD_BOARDS_TRAPDOOR = BLOCKS.register("soulwood_boards_trapdoor", () -> new TrapDoorBlock(MalumBlockSetTypes.SOULWOOD, MalumBlockProperties.SOULWOOD_TRAPDOOR()));

    public static final DeferredHolder<Block, Block> BOLTED_SOULWOOD_DOOR = BLOCKS.register("bolted_soulwood_door", () -> new DoorBlock(MalumBlockSetTypes.SOULWOOD, MalumBlockProperties.SOULWOOD_DOOR()));
    public static final DeferredHolder<Block, Block> BOLTED_SOULWOOD_BOARDS_DOOR = BLOCKS.register("bolted_soulwood_boards_door", () -> new DoorBlock(MalumBlockSetTypes.SOULWOOD, MalumBlockProperties.SOULWOOD_DOOR()));
    public static final DeferredHolder<Block, Block> BOLTED_SOULWOOD_TRAPDOOR = BLOCKS.register("bolted_soulwood_trapdoor", () -> new TrapDoorBlock(MalumBlockSetTypes.SOULWOOD, MalumBlockProperties.SOULWOOD_TRAPDOOR()));
    public static final DeferredHolder<Block, Block> BOLTED_SOULWOOD_BOARDS_TRAPDOOR = BLOCKS.register("bolted_soulwood_boards_trapdoor", () -> new TrapDoorBlock(MalumBlockSetTypes.SOULWOOD, MalumBlockProperties.SOULWOOD_TRAPDOOR()));

    public static final DeferredHolder<Block, Block> SOULWOOD_BUTTON = BLOCKS.register("soulwood_planks_button", () -> new ButtonBlock(MalumBlockSetTypes.SOULWOOD, 20, MalumBlockProperties.SOULWOOD().noCollission().addTags(BUTTONS, WOODEN_BUTTONS).addTags(BUTTONS, WOODEN_BUTTONS)));
    public static final DeferredHolder<Block, Block> SOULWOOD_PRESSURE_PLATE = BLOCKS.register("soulwood_planks_pressure_plate", () -> new PressurePlateBlock(MalumBlockSetTypes.SOULWOOD, MalumBlockProperties.SOULWOOD().noCollission().addTags(PRESSURE_PLATES, WOODEN_PRESSURE_PLATES)));

    public static final DeferredHolder<Block, Block> SOULWOOD_BOARDS_WALL = BLOCKS.register("soulwood_boards_wall", () -> new WallBlock(MalumBlockProperties.SOULWOOD().addTags(WALLS)));
    public static final DeferredHolder<Block, Block> SOULWOOD_FENCE = BLOCKS.register("soulwood_planks_fence", () -> new FenceBlock(MalumBlockProperties.SOULWOOD().addTags(FENCES, WOODEN_FENCES)));
    public static final DeferredHolder<Block, Block> SOULWOOD_FENCE_GATE = BLOCKS.register("soulwood_planks_fence_gate", () -> new FenceGateBlock(MalumWoodTypes.SOULWOOD, MalumBlockProperties.SOULWOOD().addTags(FENCE_GATES, FENCE_GATES_WOODEN)));


    public static final DeferredHolder<Block, Block> SOULWOOD_ITEM_PEDESTAL = BLOCKS.register("soulwood_item_pedestal", () -> new WoodItemPedestalBlock<>(MalumBlockProperties.SOULWOOD().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_PEDESTAL));
    public static final DeferredHolder<Block, Block> ORNATE_SOULWOOD_ITEM_PEDESTAL = BLOCKS.register("ornate_soulwood_item_pedestal", () -> new DecoratedItemPedestalBlock<>(MalumBlockProperties.SOULWOOD().setCutoutRenderType().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_PEDESTAL));
    public static final DeferredHolder<Block, Block> SOULWOOD_ITEM_STAND = BLOCKS.register("soulwood_item_stand", () -> new ItemStandBlock<>(MalumBlockProperties.SOULWOOD().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_STAND));
    public static final DeferredHolder<Block, Block> ORNATE_SOULWOOD_ITEM_STAND = BLOCKS.register("ornate_soulwood_item_stand", () -> new ItemStandBlock<>(MalumBlockProperties.SOULWOOD().noOcclusion()).setBlockEntity(MalumBlockEntities.ITEM_STAND));

    public static final DeferredHolder<Block, Block> SOULWOOD_SIGN = BLOCKS.register("soulwood_sign", () -> new StandingSignBlock(MalumWoodTypes.SOULWOOD, MalumBlockProperties.SOULWOOD().addTags(SIGNS, STANDING_SIGNS).noOcclusion().noCollission()));
    public static final DeferredHolder<Block, Block> SOULWOOD_WALL_SIGN = BLOCKS.register("soulwood_wall_sign", () -> new WallSignBlock(MalumWoodTypes.SOULWOOD, MalumBlockProperties.SOULWOOD().addTags(SIGNS, WALL_SIGNS).noOcclusion().noCollission()));
    //endregion

    //region ores and such
    public static final DeferredHolder<Block, Block> BLOCK_OF_SOULSTONE = BLOCKS.register("block_of_soulstone", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.SOULSTONE_BLOCK(false)));
    public static final DeferredHolder<Block, Block> BLOCK_OF_RAW_SOULSTONE = BLOCKS.register("block_of_raw_soulstone", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.SOULSTONE_BLOCK(true)));
    public static final DeferredHolder<Block, Block> DEEPSLATE_SOULSTONE_ORE = BLOCKS.register("deepslate_soulstone_ore", () -> new DropExperienceBlock(UniformInt.of(14, 18), MalumOreBlockProperties.SOULSTONE_ORE(true)));
    public static final DeferredHolder<Block, Block> SOULSTONE_ORE = BLOCKS.register("soulstone_ore", () -> new DropExperienceBlock(UniformInt.of(14, 18), MalumOreBlockProperties.SOULSTONE_ORE(false)));

    public static final DeferredHolder<Block, Block> BLOCK_OF_BRILLIANCE = BLOCKS.register("block_of_brilliance", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.BRILLIANCE_BLOCK(false)));
    public static final DeferredHolder<Block, Block> BLOCK_OF_RAW_BRILLIANCE = BLOCKS.register("block_of_raw_brilliance", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.BRILLIANCE_BLOCK(true)));
    public static final DeferredHolder<Block, Block> BRILLIANT_DEEPSLATE = BLOCKS.register("brilliant_deepslate", () -> new DropExperienceBlock(UniformInt.of(16, 26), MalumOreBlockProperties.BRILLIANCE_ORE(true).setCutoutRenderType()));
    public static final DeferredHolder<Block, Block> BRILLIANT_STONE = BLOCKS.register("brilliant_stone", () -> new DropExperienceBlock(UniformInt.of(14, 18), MalumOreBlockProperties.BRILLIANCE_ORE(false).setCutoutRenderType()));

    public static final DeferredHolder<Block, Block> BLOCK_OF_BLAZING_QUARTZ = BLOCKS.register("block_of_blazing_quartz", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.BLAZING_QUARTZ_BLOCK().lightLevel((b) -> 14)));
    public static final DeferredHolder<Block, Block> BLAZING_QUARTZ_ORE = BLOCKS.register("blazing_quartz_ore", () -> new DropExperienceBlock(UniformInt.of(4, 7), MalumOreBlockProperties.BLAZING_QUARTZ_ORE().setCutoutRenderType().lightLevel((b) -> 6)));
    public static final DeferredHolder<Block, Block> BLAZING_QUARTZ_CLUSTER = BLOCKS.register("blazing_quartz_cluster", () -> new AmethystClusterBlock(4, 3, MalumBlockProperties.BLAZING_QUARTZ_CLUSTER().setCutoutRenderType().lightLevel((b) -> 14)));

    public static final DeferredHolder<Block, Block> BLOCK_OF_NATURAL_QUARTZ = BLOCKS.register("block_of_natural_quartz", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.NATURAL_QUARTZ_BLOCK()));
    public static final DeferredHolder<Block, Block> DEEPSLATE_QUARTZ_ORE = BLOCKS.register("deepslate_quartz_ore", () -> new DropExperienceBlock(UniformInt.of(2, 5), MalumOreBlockProperties.NATURAL_QUARTZ_ORE(true).setCutoutRenderType()));
    public static final DeferredHolder<Block, Block> NATURAL_QUARTZ_ORE = BLOCKS.register("natural_quartz_ore", () -> new DropExperienceBlock(UniformInt.of(1, 4), MalumOreBlockProperties.NATURAL_QUARTZ_ORE(false).setCutoutRenderType()));
    public static final DeferredHolder<Block, Block> NATURAL_QUARTZ_CLUSTER = BLOCKS.register("natural_quartz_cluster", () -> new AmethystClusterBlock(6, 3, MalumBlockProperties.NATURAL_QUARTZ_CLUSTER().setCutoutRenderType()));

    public static final DeferredHolder<Block, Block> BLOCK_OF_CTHONIC_GOLD = BLOCKS.register("block_of_cthonic_gold", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.CTHONIC_GOLD_BLOCK()));
    public static final DeferredHolder<Block, Block> CTHONIC_GOLD_ORE = BLOCKS.register("cthonic_gold_ore", () -> new DropExperienceBlock(UniformInt.of(10, 100), MalumOreBlockProperties.CTHONIC_GOLD_ORE()));
    public static final DeferredHolder<Block, Block> CTHONIC_GOLD_CLUSTER = BLOCKS.register("cthonic_gold_cluster", () -> new AmethystClusterBlock(4, 3, MalumBlockProperties.CTHONIC_GOLD_CLUSTER().setCutoutRenderType()));

    public static final DeferredHolder<Block, Block> BLOCK_OF_ROTTING_ESSENCE = BLOCKS.register("block_of_rotting_essence", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.GENERIC_STORAGE_BLOCK(SoundType.CORAL_BLOCK, DyeColor.GREEN).needsShovel()));
    public static final DeferredHolder<Block, Block> BLOCK_OF_GRIM_TALC = BLOCKS.register("block_of_grim_talc", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.GENERIC_STORAGE_BLOCK(SoundType.BONE_BLOCK, DyeColor.YELLOW).requiresCorrectToolForDrops().needsPickaxe()));
    public static final DeferredHolder<Block, Block> BLOCK_OF_EERIE_WEAVE = BLOCKS.register("block_of_eerie_weave", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.GENERIC_STORAGE_BLOCK(SoundType.WOOL, DyeColor.LIGHT_BLUE)));
    public static final DeferredHolder<Block, Block> BLOCK_OF_WARP_FLUX = BLOCKS.register("block_of_warp_flux", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.GENERIC_STORAGE_BLOCK(MalumBlockSoundEvents.STRANGE_CRYSTAL, DyeColor.PURPLE).requiresCorrectToolForDrops().needsPickaxe().noOcclusion().lightLevel(b -> 8)));

    public static final DeferredHolder<Block, Block> BLOCK_OF_WIND_NUCLEI = BLOCKS.register("block_of_wind_nuclei", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.GENERIC_STORAGE_BLOCK(SoundType.WOOL, DyeColor.LIGHT_BLUE).requiresCorrectToolForDrops().needsPickaxe()));
    public static final DeferredHolder<Block, Block> BLOCK_OF_PYRE_NUCLEI = BLOCKS.register("block_of_pyre_nuclei", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.GENERIC_STORAGE_BLOCK(SoundType.COPPER_BULB, DyeColor.YELLOW).requiresCorrectToolForDrops().needsPickaxe().noOcclusion().lightLevel(b -> 8)));

    public static final DeferredHolder<Block, Block> BLOCK_OF_HEX_ASH = BLOCKS.register("block_of_hex_ash", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.GENERIC_STORAGE_BLOCK(SoundType.WOOL, DyeColor.PURPLE).needsHoe()));
    public static final DeferredHolder<Block, Block> BLOCK_OF_LIVING_FLESH = BLOCKS.register("block_of_living_flesh", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.GENERIC_STORAGE_BLOCK(SoundType.CORAL_BLOCK, DyeColor.RED).needsShovel()));
    public static final DeferredHolder<Block, Block> BLOCK_OF_ALCHEMICAL_CALX = BLOCKS.register("block_of_alchemical_calx", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.GENERIC_STORAGE_BLOCK(SoundType.CALCITE, DyeColor.YELLOW).requiresCorrectToolForDrops().needsPickaxe()));
    public static final DeferredHolder<Block, Block> BLOCK_OF_ARCANE_CHARCOAL = BLOCKS.register("block_of_arcane_charcoal", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.ARCANE_CHARCOAL_BLOCK()));

    public static final DeferredHolder<Block, Block> BLOCK_OF_EBONY = BLOCKS.register("block_of_ebony", () -> new LodestoneDirectionalBlock(MalumStorageBlockProperties.EBONY_BLOCK()));
    public static final DeferredHolder<Block, Block> CRATE_OF_WITCHHAZEL = BLOCKS.register("crate_of_witchhazel", () -> new Block(MalumStorageBlockProperties.WITCHHAZEL_CRATE()));

    public static final DeferredHolder<Block, Block> BLOCK_OF_NULL_SLATE = BLOCKS.register("block_of_null_slate", () -> new Block(MalumStorageBlockProperties.SOULSTONE_BLOCK(false)));
    public static final DeferredHolder<Block, Block> BLOCK_OF_VOID_SALTS = BLOCKS.register("block_of_void_salts", () -> new Block(MalumStorageBlockProperties.GENERIC_STORAGE_BLOCK(SoundType.WOOL, DyeColor.PURPLE).needsHoe()));
    public static final DeferredHolder<Block, Block> BLOCK_OF_MNEMONIC_FRAGMENT = BLOCKS.register("block_of_mnemonic_fragment", () -> new Block(MalumStorageBlockProperties.BRILLIANCE_BLOCK(false)));
    public static final DeferredHolder<Block, Block> BLOCK_OF_AURIC_EMBERS = BLOCKS.register("block_of_auric_embers", () -> new Block(MalumStorageBlockProperties.GENERIC_STORAGE_BLOCK(MalumBlockSoundEvents.STRANGE_CRYSTAL, DyeColor.YELLOW).requiresCorrectToolForDrops().needsPickaxe().noOcclusion().lightLevel(b -> 12)));
    public static final DeferredHolder<Block, Block> BLOCK_OF_MALIGNANT_LEAD = BLOCKS.register("block_of_malignant_lead", () -> new Block(MalumStorageBlockProperties.MALIGNANT_LEAD_BLOCK()));

    public static final DeferredHolder<Block, Block> BLOCK_OF_SOUL_STAINED_STEEL = BLOCKS.register("block_of_soul_stained_steel", () -> new Block(MalumStorageBlockProperties.SOUL_STAINED_STEEL_BLOCK()));
    public static final DeferredHolder<Block, Block> BLOCK_OF_HALLOWED_GOLD = BLOCKS.register("block_of_hallowed_gold", () -> new Block(MalumStorageBlockProperties.HALLOWED_GOLD()));
    public static final DeferredHolder<Block, Block> BLOCK_OF_MALIGNANT_PEWTER = BLOCKS.register("block_of_malignant_pewter", () -> new Block(MalumStorageBlockProperties.MALIGNANT_PEWTER_BLOCK()));
    //endregion

    //region flora
    public static final DeferredHolder<Block, Block> EBONY_SAPLING = BLOCKS.register("ebony_sapling", () -> new EbonySaplingBlock(MalumFloraBlockProperties.EBONY_SAPLING()));
    public static final DeferredHolder<Block, Block> EBONY = BLOCKS.register("ebony", () -> new EbonyStalkBlock(MalumFloraBlockProperties.EBONY()));

    public static final DeferredHolder<Block, Block> WILD_WITCHHAZEL = BLOCKS.register("wild_witchhazel", () -> new WildWitchhazelPlantBlock(MalumFloraBlockProperties.WILD_WITCHHAZEL()));
    public static final DeferredHolder<Block, Block> WITCHHAZEL = BLOCKS.register("witchhazel", () -> new WitchhazelCropBlock(MalumFloraBlockProperties.WITCHHAZEL_CROP()));
    //endregion

    //region blight
    public static final DeferredHolder<Block, Block> COLUMNAR_BLIGHT = BLOCKS.register("columnar_blight", () -> new ColumnarBlightBlock(MalumBlockProperties.BLIGHTED_EARTH()));
    public static final DeferredHolder<Block, Block> BLIGHTED_EARTH = BLOCKS.register("blighted_earth", () -> new BlightedEarthBlock(MalumBlockProperties.BLIGHTED_EARTH()));
    public static final DeferredHolder<Block, Block> BLIGHT = BLOCKS.register("blight", () -> new BlightedCoverageBlock(MalumBlockProperties.BLIGHTED_COVERING()));
    public static final DeferredHolder<Block, Block> BLIGHTED_GROWTH = BLOCKS.register("blighted_growth", () -> new BlightedPlantBlock(MalumBlockProperties.BLIGHTED_PLANTS()));
    public static final DeferredHolder<Block, Block> BLIGHTPEARL = BLOCKS.register("blightpearl", () -> new BlightedPlantBlock(MalumBlockProperties.BLIGHTED_PLANTS()));
    public static final DeferredHolder<Block, Block> BLIGHTROOT = BLOCKS.register("blightroot", () -> new BlightedPlantBlock(MalumBlockProperties.BLIGHTED_PLANTS()));
    public static final DeferredHolder<Block, Block> CLINGING_BLIGHT = BLOCKS.register("clinging_blight", () -> new CreepingBlightBlock(MalumBlockProperties.CLINGING_BLIGHT()));
    //endregion

    //region scarstone
    public static final DeferredHolder<Block, Block> SCARSTONE = BLOCKS.register("scarstone", () -> new ScarstoneBlock(MalumBlockProperties.SCARSTONE()));
    public static final DeferredHolder<Block, Block> STRANGE_CRYSTAL = BLOCKS.register("strange_crystal", () -> new StrangeCrystalBlock(MalumBlockProperties.STRANGE_CRYSTAL()));
    public static final DeferredHolder<Block, Block> LARGE_STRANGE_CRYSTAL = BLOCKS.register("large_strange_crystal", () -> new LargeStrangeCrystalBlock(MalumBlockProperties.STRANGE_CRYSTAL()));
    public static final DeferredHolder<Block, Block> STRANGEROOT = BLOCKS.register("strangeroot", () -> new StrangeRootBlock(MalumBlockProperties.STRANGEROOT()));
    //endregion

    //region dungeon
    public static final DeferredHolder<Block, Block> OMINOUS_ALTAR = BLOCKS.register("ominous_altar", () -> new OminousAltarBlock(MalumDungeonBlockProperties.OMINOUS_CRAFT()).setBlockEntity(MalumBlockEntities.OMINOUS_ALTAR));
    public static final DeferredHolder<Block, Block> OMINOUS_OBELISK = BLOCKS.register("ominous_obelisk", () -> new OminousObeliskCoreBlock(MalumDungeonBlockProperties.OMINOUS_CRAFT().setCutoutRenderType().noOcclusion()));
    public static final DeferredHolder<Block, Block> OMINOUS_OBELISK_COMPONENT = BLOCKS.register("ominous_obelisk_component", () -> new ObeliskComponentBlock(MalumDungeonBlockProperties.OMINOUS_CRAFT().setCutoutRenderType().lootFrom(OMINOUS_OBELISK).noOcclusion(), MalumItems.OMINOUS_OBELISK));

    public static final DeferredHolder<Block, Block> ODD_SCRIPTURES_I = BLOCKS.register("odd_scriptures_i", () -> new OddScripturesBlock(MalumDungeonBlockProperties.ODD_SCRIPTURES()));
    public static final DeferredHolder<Block, Block> ODD_SCRIPTURES_II = BLOCKS.register("odd_scriptures_ii", () -> new OddScripturesBlock(MalumDungeonBlockProperties.ODD_SCRIPTURES()));
    public static final DeferredHolder<Block, Block> ODD_SCRIPTURES_III = BLOCKS.register("odd_scriptures_iii", () -> new OddScripturesBlock(MalumDungeonBlockProperties.ODD_SCRIPTURES()));
    public static final DeferredHolder<Block, Block> ODD_SCRIPTURES_IV = BLOCKS.register("odd_scriptures_iv", () -> new OddScripturesBlock(MalumDungeonBlockProperties.ODD_SCRIPTURES()));
    public static final DeferredHolder<Block, Block> ODD_SCRIPTURES_V = BLOCKS.register("odd_scriptures_v", () -> new OddScripturesBlock(MalumDungeonBlockProperties.ODD_SCRIPTURES()));
    public static final DeferredHolder<Block, Block> ODD_SCRIPTURES_VI = BLOCKS.register("odd_scriptures_vi", () -> new OddScripturesBlock(MalumDungeonBlockProperties.ODD_SCRIPTURES()));
    public static final DeferredHolder<Block, Block> ODD_SCRIPTURES_VII = BLOCKS.register("odd_scriptures_vii", () -> new OddScripturesBlock(MalumDungeonBlockProperties.ODD_SCRIPTURES()));
    public static final DeferredHolder<Block, Block> ODD_SCRIPTURES_VIII = BLOCKS.register("odd_scriptures_viii", () -> new OddScripturesBlock(MalumDungeonBlockProperties.ODD_SCRIPTURES()));
    public static final DeferredHolder<Block, Block> ODD_SCRIPTURES_IX = BLOCKS.register("odd_scriptures_ix", () -> new OddScripturesBlock(MalumDungeonBlockProperties.ODD_SCRIPTURES()));

    public static final DeferredHolder<Block, Block> VEILED_EFFIGY = BLOCKS.register("veiled_effigy", () -> new MeditatingEffigyBlock(MalumDungeonBlockProperties.MEDITATING_EFFIGY()));
    public static final DeferredHolder<Block, Block> CORRUPT_EFFIGY = BLOCKS.register("corrupt_effigy", () -> new MeditatingEffigyBlock(MalumDungeonBlockProperties.MEDITATING_EFFIGY()));
    public static final DeferredHolder<Block, Block> CRACKED_EFFIGY = BLOCKS.register("cracked_effigy", () -> new MeditatingEffigyBlock(MalumDungeonBlockProperties.MEDITATING_EFFIGY()));

    public static final DeferredHolder<Block, Block> COLUMNAR_FLESH = BLOCKS.register("columnar_flesh", () -> new ColumnarFleshBlock(MalumDungeonBlockProperties.FLESH_BLOCK()));
    public static final DeferredHolder<Block, Block> FLESHBULB = BLOCKS.register("fleshbulb", () -> new FleshBulbBlock(MalumDungeonBlockProperties.FLESHBULB()));
    public static final DeferredHolder<Block, Block> WRITHING_FLESH = BLOCKS.register("writhing_flesh", () -> new WrithingFleshBlock(MalumDungeonBlockProperties.WRITHING_FLESH()));

    //endregion

    public static final DeferredHolder<Block, Block> POTTED_RUNEWOOD_SAPLING = BLOCKS.register("potted_runewood_sapling", () -> flowerPot(RUNEWOOD_SAPLING));
    public static final DeferredHolder<Block, Block> POTTED_AZURE_RUNEWOOD_SAPLING = BLOCKS.register("potted_azure_runewood_sapling", () -> flowerPot(AZURE_RUNEWOOD_SAPLING));
    public static final DeferredHolder<Block, Block> POTTED_SOULWOOD_SAPLING = BLOCKS.register("potted_soulwood_sapling", () -> flowerPot(SOULWOOD_SAPLING));
    public static final DeferredHolder<Block, Block> POTTED_BLIGHTROOT = BLOCKS.register("potted_blightroot", () -> flowerPot(BLIGHTROOT));
    public static final DeferredHolder<Block, Block> POTTED_BLIGHTPEARL = BLOCKS.register("potted_blightpearl", () -> flowerPot(BLIGHTPEARL));
    public static final DeferredHolder<Block, Block> POTTED_STRANGEROOT = BLOCKS.register("potted_strangeroot", () -> flowerPot(STRANGEROOT));


    public static final DeferredHolder<Block, Block> THE_DEVICE = BLOCKS.register("the_device", () -> new TheDevice(MalumBlockProperties.TAINTED_ROCK()));
    public static final DeferredHolder<Block, Block> THE_VESSEL = BLOCKS.register("the_vessel", () -> new TheVessel(MalumBlockProperties.TWISTED_ROCK()));

    private static Block flowerPot(DeferredHolder<Block, Block> potted) {
        return new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, potted, MalumBlockProperties.POTTED_PLANT());
    }

    public static void addPottedBlocks(FMLCommonSetupEvent event) {
        FlowerPotBlock flowerPot = (FlowerPotBlock) Blocks.FLOWER_POT;
        flowerPot.addPlant(RUNEWOOD_SAPLING.getId(), POTTED_RUNEWOOD_SAPLING);
        flowerPot.addPlant(AZURE_RUNEWOOD_SAPLING.getId(), POTTED_AZURE_RUNEWOOD_SAPLING);
        flowerPot.addPlant(SOULWOOD_SAPLING.getId(), POTTED_SOULWOOD_SAPLING);
        flowerPot.addPlant(BLIGHTROOT.getId(), POTTED_BLIGHTROOT);
        flowerPot.addPlant(BLIGHTPEARL.getId(), POTTED_BLIGHTPEARL);
        flowerPot.addPlant(STRANGEROOT.getId(), POTTED_STRANGEROOT);
    }
}