package com.sammy.malum.registry.common.block;

import com.sammy.malum.*;
import com.sammy.malum.client.renderer.block.*;
import com.sammy.malum.client.renderer.block.artifice.SpiritCatalyzerRenderer;
import com.sammy.malum.client.renderer.block.artifice.SpiritCrucibleRenderer;
import com.sammy.malum.client.renderer.block.redstone.WaveMakerRenderer;
import com.sammy.malum.client.renderer.block.redstone.WaveChargerRenderer;
import com.sammy.malum.client.renderer.block.redstone.WavebankerRenderer;
import com.sammy.malum.client.renderer.block.redstone.WaveBreakerRenderer;
import com.sammy.malum.client.renderer.block.totemancy.*;
import com.sammy.malum.common.block.curiosities.banner.*;
import com.sammy.malum.common.block.curiosities.gust_igniter.*;
import com.sammy.malum.common.block.curiosities.gust_igniter.wind_tunnel.*;
import com.sammy.malum.common.block.curiosities.mana_mote.*;
import com.sammy.malum.common.block.curiosities.obelisk.brilliant.*;
import com.sammy.malum.common.block.curiosities.obelisk.rite_pylon.*;
import com.sammy.malum.common.block.curiosities.obelisk.runewood.*;
import com.sammy.malum.common.block.curiosities.redstone.wavemaker.WaveMakerBlock;
import com.sammy.malum.common.block.curiosities.redstone.wavemaker.WaveMakerBlockEntity;
import com.sammy.malum.common.block.curiosities.redstone.wavebanker.WaveBankerBlock;
import com.sammy.malum.common.block.curiosities.redstone.wavebanker.WaveBankerBlockEntity;
import com.sammy.malum.common.block.curiosities.redstone.wavecharger.WaveChargerBlock;
import com.sammy.malum.common.block.curiosities.redstone.wavecharger.WaveChargerBlockEntity;
import com.sammy.malum.common.block.curiosities.redstone.wavebreaker.WaveBreakerBlock;
import com.sammy.malum.common.block.curiosities.redstone.wavebreaker.WaveBreakerBlockEntity;
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
import com.sammy.malum.common.block.dungeon.curiosities.*;
import com.sammy.malum.common.block.ether.*;
import com.sammy.malum.common.block.storage.jar.*;
import com.sammy.malum.common.block.storage.pedestal.*;
import com.sammy.malum.common.block.storage.stand.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.*;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import team.lodestar.lodestone.modules.toolkit.blockentity.*;
import team.lodestar.lodestone.modules.toolkit.blockentity.LodestoneBlockEntityTicker.Type;

import java.util.*;
import java.util.function.Supplier;

import static com.sammy.malum.MalumMod.*;

public class MalumBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, MALUM);

    public static final Supplier<LodestoneBlockEntityType<VoidConduitBlockEntity>> VOID_CONDUIT = BLOCK_ENTITY_TYPES.register("void_conduit", () -> LodestoneBlockEntityTypeBuilder.of(VoidConduitBlockEntity::new, MalumBlocks.VOID_CONDUIT.get()).setTickerType(Type.BOTH).build());
    public static final Supplier<LodestoneBlockEntityType<VoidDepotBlockEntity>> VOID_DEPOT = BLOCK_ENTITY_TYPES.register("void_depot", () -> LodestoneBlockEntityTypeBuilder.of(VoidDepotBlockEntity::new, MalumBlocks.VOID_DEPOT.get()).setTickerType(Type.CLIENT).build());

    public static final Supplier<LodestoneBlockEntityType<SpiritAltarBlockEntity>> SPIRIT_ALTAR = BLOCK_ENTITY_TYPES.register("spirit_altar", () -> LodestoneBlockEntityTypeBuilder.of(SpiritAltarBlockEntity::new, MalumBlocks.SPIRIT_ALTAR.get()).setTickerType(Type.BOTH).build());
    public static final Supplier<LodestoneBlockEntityType<SpiritJarBlockEntity>> SPIRIT_JAR = BLOCK_ENTITY_TYPES.register("spirit_jar", () -> LodestoneBlockEntityTypeBuilder.of(SpiritJarBlockEntity::new, MalumBlocks.SPIRIT_JAR.get()).setTickerType(Type.CLIENT).build());

    public static final Supplier<LodestoneBlockEntityType<RunicWorkbenchBlockEntity>> RUNIC_WORKBENCH = BLOCK_ENTITY_TYPES.register("runic_workbench", () -> LodestoneBlockEntityTypeBuilder.of(RunicWorkbenchBlockEntity::new, MalumBlocks.RUNIC_WORKBENCH.get()).setTickerType(Type.SERVER).build());
    public static final Supplier<LodestoneBlockEntityType<WeaversWorkbenchBlockEntity>> WEAVERS_WORKBENCH = BLOCK_ENTITY_TYPES.register("weavers_workbench", () -> LodestoneBlockEntityTypeBuilder.of(WeaversWorkbenchBlockEntity::new, MalumBlocks.WEAVERS_WORKBENCH.get()).build());

    public static final Supplier<LodestoneBlockEntityType<SoulBrazierBlockEntity>> SOUL_BRAZIER = BLOCK_ENTITY_TYPES.register("soulbinding_brazier", () -> LodestoneBlockEntityTypeBuilder.of(SoulBrazierBlockEntity::new, MalumBlocks.SOUL_BRAZIER.get()).setTickerType(Type.BOTH).build());

    public static final Supplier<LodestoneBlockEntityType<RunewoodObeliskBlockEntity>> RUNEWOOD_OBELISK = BLOCK_ENTITY_TYPES.register("runewood_obelisk", () -> LodestoneBlockEntityTypeBuilder.of(RunewoodObeliskBlockEntity::new, MalumBlocks.RUNEWOOD_OBELISK.get()).build());
    public static final Supplier<LodestoneBlockEntityType<BrilliantObeliskBlockEntity>> BRILLIANT_OBELISK = BLOCK_ENTITY_TYPES.register("brilliant_obelisk", () -> LodestoneBlockEntityTypeBuilder.of(BrilliantObeliskBlockEntity::new, MalumBlocks.BRILLIANT_OBELISK.get()).build());
    public static final Supplier<LodestoneBlockEntityType<ArcanaPylonBlockEntity>> ARCANA_PYLON = BLOCK_ENTITY_TYPES.register("arcana_pylon", () -> LodestoneBlockEntityTypeBuilder.of(ArcanaPylonBlockEntity::new, MalumBlocks.ARCANA_PYLON.get()).setTickerType(Type.BOTH).build());

    public static final Supplier<LodestoneBlockEntityType<SpiritCrucibleCoreBlockEntity>> SPIRIT_CRUCIBLE = BLOCK_ENTITY_TYPES.register("spirit_crucible", () -> LodestoneBlockEntityTypeBuilder.of(SpiritCrucibleCoreBlockEntity::new, MalumBlocks.SPIRIT_CRUCIBLE.get()).setTickerType(Type.BOTH).build());
    public static final Supplier<LodestoneBlockEntityType<SpiritCatalyzerCoreBlockEntity>> SPIRIT_CATALYZER = BLOCK_ENTITY_TYPES.register("spirit_catalyzer", () -> LodestoneBlockEntityTypeBuilder.of(SpiritCatalyzerCoreBlockEntity::new, MalumBlocks.SPIRIT_CATALYZER.get()).setTickerType(Type.BOTH).build());
    public static final Supplier<LodestoneBlockEntityType<RepairPylonCoreBlockEntity>> REPAIR_PYLON = BLOCK_ENTITY_TYPES.register("repair_pylon", () -> LodestoneBlockEntityTypeBuilder.of(RepairPylonCoreBlockEntity::new, MalumBlocks.REPAIR_PYLON.get()).setTickerType(Type.BOTH).build());

    public static final Supplier<LodestoneBlockEntityType<EtherBlockEntity>> ETHER = BLOCK_ENTITY_TYPES.register("ether", () -> LodestoneBlockEntityTypeBuilder.of(EtherBlockEntity::new, MalumBlocks.ETHER.get(), MalumBlocks.IRIDESCENT_ETHER.get()).setTickerType(Type.CLIENT).build());
    public static final Supplier<LodestoneBlockEntityType<EtherCandleBlockEntity>> ETHER_CANDLE = BLOCK_ENTITY_TYPES.register("ether_candle", () -> LodestoneBlockEntityTypeBuilder.of(EtherCandleBlockEntity::new, MalumBlocks.ETHER_CANDLE.get(), MalumBlocks.IRIDESCENT_ETHER_CANDLE.get()).setTickerType(Type.CLIENT).build());
    public static final Supplier<LodestoneBlockEntityType<EtherTorchBlockEntity>> ETHER_TORCH = BLOCK_ENTITY_TYPES.register("ether_torch", () -> LodestoneBlockEntityTypeBuilder.of(EtherTorchBlockEntity::new, getBlocks(EtherTorchBlock.class, EtherWallTorchBlock.class)).setTickerType(Type.CLIENT).build());
    public static final Supplier<LodestoneBlockEntityType<EtherBrazierBlockEntity>> ETHER_BRAZIER = BLOCK_ENTITY_TYPES.register("ether_brazier", () -> LodestoneBlockEntityTypeBuilder.of(EtherBrazierBlockEntity::new, getBlocks(EtherBrazierBlock.class)).setTickerType(Type.CLIENT).build());
    public static final Supplier<LodestoneBlockEntityType<EtherCressetBlockEntity>> ETHER_CRESSET = BLOCK_ENTITY_TYPES.register("ether_cresset", () -> LodestoneBlockEntityTypeBuilder.of(EtherCressetBlockEntity::new, getBlocks(EtherCressetBlock.class)).setTickerType(Type.CLIENT).build());

    public static final Supplier<LodestoneBlockEntityType<ItemStandBlockEntity>> ITEM_STAND = BLOCK_ENTITY_TYPES.register("item_stand", () -> LodestoneBlockEntityTypeBuilder.of(ItemStandBlockEntity::new, getBlocks(ItemStandBlock.class)).setTickerType(Type.CLIENT).build());
    public static final Supplier<LodestoneBlockEntityType<ItemPedestalBlockEntity>> ITEM_PEDESTAL = BLOCK_ENTITY_TYPES.register("item_pedestal", () -> LodestoneBlockEntityTypeBuilder.of(ItemPedestalBlockEntity::new, getBlocks(ItemPedestalBlock.class)).setTickerType(Type.CLIENT).build());

    public static final Supplier<LodestoneBlockEntityType<TotemPoleBlockEntity>> TOTEM_POLE = BLOCK_ENTITY_TYPES.register("totem_pole", () -> LodestoneBlockEntityTypeBuilder.of(TotemPoleBlockEntity::new, MalumBlocks.RUNEWOOD_TOTEM_POLE.get(), MalumBlocks.SOULWOOD_TOTEM_POLE.get()).setTickerType(Type.BOTH).build());
    public static final Supplier<LodestoneBlockEntityType<TotemBaseBlockEntity>> TOTEM_BASE = BLOCK_ENTITY_TYPES.register("totem_base", () -> LodestoneBlockEntityTypeBuilder.of(TotemBaseBlockEntity::new, getBlocks(TotemBaseBlock.class)).setTickerType(Type.SERVER).build());
    public static final Supplier<LodestoneBlockEntityType<WaveformTotemBaseBlockEntity>> WAVEFORM_TOTEM_BASE = BLOCK_ENTITY_TYPES.register("waveform_totem_base", () -> LodestoneBlockEntityTypeBuilder.of(WaveformTotemBaseBlockEntity::new, getBlocks(WaveformTotemBaseBlock.class)).setTickerType(Type.SERVER).build());

    public static final Supplier<LodestoneBlockEntityType<RiteAnchorBlockEntity>> RITE_ANCHOR = BLOCK_ENTITY_TYPES.register("rite_anchor", () -> LodestoneBlockEntityTypeBuilder.of(RiteAnchorBlockEntity::new, MalumBlocks.RITE_ANCHOR.get()).setTickerType(Type.BOTH).build());
    public static final Supplier<LodestoneBlockEntityType<RiteUnweaverBlockEntity>> RITE_UNWEAVER = BLOCK_ENTITY_TYPES.register("rite_unweaver", () -> LodestoneBlockEntityTypeBuilder.of(RiteUnweaverBlockEntity::new, MalumBlocks.RITE_UNWEAVER.get()).build());
    public static final Supplier<LodestoneBlockEntityType<RiteSpreaderBlockEntity>> RITE_SPREADER = BLOCK_ENTITY_TYPES.register("rite_spreader", () -> LodestoneBlockEntityTypeBuilder.of(RiteSpreaderBlockEntity::new, MalumBlocks.RITE_SPREADER.get()).build());
    public static final Supplier<LodestoneBlockEntityType<RiteChannelBlockEntity>> RITE_CHANNEL = BLOCK_ENTITY_TYPES.register("rite_channel", () -> LodestoneBlockEntityTypeBuilder.of(RiteChannelBlockEntity::new, MalumBlocks.RITE_CHANNEL.get()).build());

    public static final Supplier<LodestoneBlockEntityType<WaveChargerBlockEntity>> WAVECHARGER = BLOCK_ENTITY_TYPES.register("wavecharger", () -> LodestoneBlockEntityTypeBuilder.of(WaveChargerBlockEntity::new, getBlocks(WaveChargerBlock.class)).build());
    public static final Supplier<LodestoneBlockEntityType<WaveBankerBlockEntity>> WAVEBANKER = BLOCK_ENTITY_TYPES.register("wavebanker", () -> LodestoneBlockEntityTypeBuilder.of(WaveBankerBlockEntity::new, getBlocks(WaveBankerBlock.class)).build());
    public static final Supplier<LodestoneBlockEntityType<WaveMakerBlockEntity>> WAVEMAKER = BLOCK_ENTITY_TYPES.register("wavemaker", () -> LodestoneBlockEntityTypeBuilder.of(WaveMakerBlockEntity::new, getBlocks(WaveMakerBlock.class)).build());
    public static final Supplier<LodestoneBlockEntityType<WaveBreakerBlockEntity>> WAVEBREAKER = BLOCK_ENTITY_TYPES.register("wavebreaker", () -> LodestoneBlockEntityTypeBuilder.of(WaveBreakerBlockEntity::new, getBlocks(WaveBreakerBlock.class)).build());

    public static final Supplier<LodestoneBlockEntityType<GustIgniterBlockEntity>> GUST_IGNITER = BLOCK_ENTITY_TYPES.register("gust_igniter", () -> LodestoneBlockEntityTypeBuilder.of(GustIgniterBlockEntity::new, MalumBlocks.GUST_IGNITER.get()).setTickerType(Type.SERVER).build());
    public static final Supplier<LodestoneBlockEntityType<WindTunnelBlockEntity>> WIND_TUNNEL = BLOCK_ENTITY_TYPES.register("wind_tunnel", () -> LodestoneBlockEntityTypeBuilder.of(WindTunnelBlockEntity::new, MalumBlocks.WIND_TUNNEL.get()).build());

    public static final Supplier<LodestoneBlockEntityType<OminousAltarBlockEntity>> OMINOUS_ALTAR = BLOCK_ENTITY_TYPES.register("ominous_altar", () -> LodestoneBlockEntityTypeBuilder.of(OminousAltarBlockEntity::new, MalumBlocks.OMINOUS_ALTAR.get()).setTickerType(Type.BOTH).build());
    public static final Supplier<LodestoneBlockEntityType<OminousObeliskBlockEntity>> OMINOUS_OBELISK = BLOCK_ENTITY_TYPES.register("ominous_obelisk", () -> LodestoneBlockEntityTypeBuilder.of(OminousObeliskBlockEntity::new, MalumBlocks.OMINOUS_OBELISK.get()).setTickerType(Type.BOTH).build());

    public static final Supplier<LodestoneBlockEntityType<RitualPlinthBlockEntity>> RITUAL_PLINTH = BLOCK_ENTITY_TYPES.register("ritual_plinth", () -> LodestoneBlockEntityTypeBuilder.of(RitualPlinthBlockEntity::new, MalumBlocks.RITUAL_PLINTH.get()).build());


    public static final Supplier<LodestoneBlockEntityType<SoulwovenBannerBlockEntity>> SOULWOVEN_BANNER = BLOCK_ENTITY_TYPES.register("soulwoven_banner", () -> LodestoneBlockEntityTypeBuilder.of(SoulwovenBannerBlockEntity::new, getBlocks(SoulwovenBannerBlock.class)).build());
    public static final Supplier<LodestoneBlockEntityType<ManaMoteBlockEntity>> MANA_MOTE = BLOCK_ENTITY_TYPES.register("mote_of_mana", () -> LodestoneBlockEntityTypeBuilder.of(ManaMoteBlockEntity::new, getBlocks(ManaMoteBlock.class)).build());

    public static void bindBlockEntities(BlockEntityTypeAddBlocksEvent event) {
        event.modify(BlockEntityType.SIGN,
                MalumBlocks.RUNEWOOD_SIGN.get(), MalumBlocks.SOULWOOD_SIGN.get(),
                MalumBlocks.RUNEWOOD_WALL_SIGN.get(), MalumBlocks.SOULWOOD_WALL_SIGN.get());
    }

    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, SPIRIT_ALTAR.get(), IInventoryCapabilityProvider::getInventory);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ARCANA_PYLON.get(), IInventoryCapabilityProvider::getInventory);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, SPIRIT_CRUCIBLE.get(), IInventoryCapabilityProvider::getInventory);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, SPIRIT_CATALYZER.get(), IInventoryCapabilityProvider::getInventory);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, REPAIR_PYLON.get(), IInventoryCapabilityProvider::getInventory);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ITEM_STAND.get(), IInventoryCapabilityProvider::getInventory);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ITEM_PEDESTAL.get(), IInventoryCapabilityProvider::getInventory);
    }

    public static Block[] getBlocks(Class<?>... blockClasses) {
        Collection<DeferredHolder<Block, ? extends Block>> blocks = MalumBlocks.BLOCKS.getEntries();
        List<Block> matchingBlocks = new ArrayList<>();
        for (DeferredHolder<Block, ? extends Block> registryObject : blocks) {
            if (registryObject.isBound() && Arrays.stream(blockClasses).anyMatch(b -> b.isInstance(registryObject.get()))) {
                matchingBlocks.add(registryObject.get());
            }
        }
        return matchingBlocks.toArray(new Block[0]);
    }

    public static Block[] getBlocksExact(Class<?> clazz) {
        Collection<DeferredHolder<Block, ? extends Block>> blocks = MalumBlocks.BLOCKS.getEntries();
        List<Block> matchingBlocks = new ArrayList<>();
        for (DeferredHolder<Block, ? extends Block> registryObject : blocks) {
            if (clazz.equals(registryObject.get().getClass())) {
                matchingBlocks.add(registryObject.get());
            }
        }
        return matchingBlocks.toArray(new Block[0]);
    }

    @EventBusSubscriber(modid = MalumMod.MALUM, value = Dist.CLIENT)
    public static class ClientOnly {
        @SubscribeEvent
        public static void registerRenderer(EntityRenderersEvent.RegisterRenderers event) {

            event.registerBlockEntityRenderer(VOID_CONDUIT.get(), VoidConduitRenderer::new);
            event.registerBlockEntityRenderer(VOID_DEPOT.get(), VoidDepotRenderer::new);

            event.registerBlockEntityRenderer(SPIRIT_ALTAR.get(), SpiritAltarRenderer::new);
            event.registerBlockEntityRenderer(SPIRIT_JAR.get(), SpiritJarRenderer::new);
            event.registerBlockEntityRenderer(RUNIC_WORKBENCH.get(), MalumItemHolderRenderer::new);
            event.registerBlockEntityRenderer(SOUL_BRAZIER.get(), SoulBrazierRenderer::new);
            event.registerBlockEntityRenderer(ARCANA_PYLON.get(), ArcanaPylonRenderer::new);

            event.registerBlockEntityRenderer(SPIRIT_CRUCIBLE.get(), SpiritCrucibleRenderer::new);
            event.registerBlockEntityRenderer(SPIRIT_CATALYZER.get(), SpiritCatalyzerRenderer::new);
            event.registerBlockEntityRenderer(REPAIR_PYLON.get(), RepairPylonRenderer::new);

            event.registerBlockEntityRenderer(ITEM_STAND.get(), MalumItemHolderRenderer::new);
            event.registerBlockEntityRenderer(ITEM_PEDESTAL.get(), MalumItemHolderRenderer::new);

            event.registerBlockEntityRenderer(TOTEM_BASE.get(), TotemBaseRenderer::new);
            event.registerBlockEntityRenderer(TOTEM_POLE.get(), TotemPoleRenderer::new);

            event.registerBlockEntityRenderer(RITE_ANCHOR.get(), RiteAnchorRenderer::new);

            event.registerBlockEntityRenderer(WAVECHARGER.get(), WaveChargerRenderer::new);
            event.registerBlockEntityRenderer(WAVEBANKER.get(), WavebankerRenderer::new);
            event.registerBlockEntityRenderer(WAVEMAKER.get(), WaveMakerRenderer::new);
            event.registerBlockEntityRenderer(WAVEBREAKER.get(), WaveBreakerRenderer::new);

            event.registerBlockEntityRenderer(WIND_TUNNEL.get(), WindTunnelRenderer::new);

            event.registerBlockEntityRenderer(OMINOUS_ALTAR.get(), SpiritAltarRenderer::new);

            event.registerBlockEntityRenderer(RITUAL_PLINTH.get(), RitualPlinthRenderer::new);

            event.registerBlockEntityRenderer(SOULWOVEN_BANNER.get(), SoulwovenBannerRenderer::new);
            event.registerBlockEntityRenderer(MANA_MOTE.get(), MoteOfManaRenderer::new);
        }
    }
}
