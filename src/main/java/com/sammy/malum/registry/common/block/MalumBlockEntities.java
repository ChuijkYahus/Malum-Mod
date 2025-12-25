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
import com.sammy.malum.common.block.curiosities.totem.unweaver.*;
import com.sammy.malum.common.block.curiosities.totem.waveform.*;
import com.sammy.malum.common.block.curiosities.void_depot.*;
import com.sammy.malum.common.block.curiosities.weavers_workbench.*;
import com.sammy.malum.common.block.curiosities.weeping_well.*;
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
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import team.lodestar.lodestone.systems.blockentity.*;

import java.util.*;

import static com.sammy.malum.MalumMod.*;

public class MalumBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, MALUM);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<VoidConduitBlockEntity>> VOID_CONDUIT = BLOCK_ENTITY_TYPES.register("void_conduit", () -> BlockEntityType.Builder.of(VoidConduitBlockEntity::new, MalumBlocks.VOID_CONDUIT.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<VoidDepotBlockEntity>> VOID_DEPOT = BLOCK_ENTITY_TYPES.register("void_depot", () -> BlockEntityType.Builder.of(VoidDepotBlockEntity::new, MalumBlocks.VOID_DEPOT.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SpiritAltarBlockEntity>> SPIRIT_ALTAR = BLOCK_ENTITY_TYPES.register("spirit_altar", () -> BlockEntityType.Builder.of(SpiritAltarBlockEntity::new, MalumBlocks.SPIRIT_ALTAR.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SpiritJarBlockEntity>> SPIRIT_JAR = BLOCK_ENTITY_TYPES.register("spirit_jar", () -> BlockEntityType.Builder.of(SpiritJarBlockEntity::new, MalumBlocks.SPIRIT_JAR.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RunicWorkbenchBlockEntity>> RUNIC_WORKBENCH = BLOCK_ENTITY_TYPES.register("runic_workbench", () -> BlockEntityType.Builder.of(RunicWorkbenchBlockEntity::new, MalumBlocks.RUNIC_WORKBENCH.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WeaversWorkbenchBlockEntity>> WEAVERS_WORKBENCH = BLOCK_ENTITY_TYPES.register("weavers_workbench", () -> BlockEntityType.Builder.of(WeaversWorkbenchBlockEntity::new, MalumBlocks.WEAVERS_WORKBENCH.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SoulBrazierBlockEntity>> SOUL_BRAZIER = BLOCK_ENTITY_TYPES.register("soulbinding_brazier", () -> BlockEntityType.Builder.of(SoulBrazierBlockEntity::new, MalumBlocks.SOUL_BRAZIER.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RunewoodObeliskBlockEntity>> RUNEWOOD_OBELISK = BLOCK_ENTITY_TYPES.register("runewood_obelisk", () -> BlockEntityType.Builder.of(RunewoodObeliskBlockEntity::new, MalumBlocks.RUNEWOOD_OBELISK.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BrilliantObeliskBlockEntity>> BRILLIANT_OBELISK = BLOCK_ENTITY_TYPES.register("brilliant_obelisk", () -> BlockEntityType.Builder.of(BrilliantObeliskBlockEntity::new, MalumBlocks.BRILLIANT_OBELISK.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ArcanaPylonBlockEntity>> ARCANA_PYLON = BLOCK_ENTITY_TYPES.register("arcana_pylon", () -> BlockEntityType.Builder.of(ArcanaPylonBlockEntity::new, MalumBlocks.ARCANA_PYLON.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SpiritCrucibleCoreBlockEntity>> SPIRIT_CRUCIBLE = BLOCK_ENTITY_TYPES.register("spirit_crucible", () -> BlockEntityType.Builder.of(SpiritCrucibleCoreBlockEntity::new, MalumBlocks.SPIRIT_CRUCIBLE.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SpiritCatalyzerCoreBlockEntity>> SPIRIT_CATALYZER = BLOCK_ENTITY_TYPES.register("spirit_catalyzer", () -> BlockEntityType.Builder.of(SpiritCatalyzerCoreBlockEntity::new, MalumBlocks.SPIRIT_CATALYZER.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RepairPylonCoreBlockEntity>> REPAIR_PYLON = BLOCK_ENTITY_TYPES.register("repair_pylon", () -> BlockEntityType.Builder.of(RepairPylonCoreBlockEntity::new, MalumBlocks.REPAIR_PYLON.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<EtherBlockEntity>> ETHER = BLOCK_ENTITY_TYPES.register("ether", () -> BlockEntityType.Builder.of(EtherBlockEntity::new, MalumBlocks.ETHER.get(), MalumBlocks.IRIDESCENT_ETHER.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<EtherTorchBlockEntity>> ETHER_TORCH = BLOCK_ENTITY_TYPES.register("ether_torch", () -> BlockEntityType.Builder.of(EtherTorchBlockEntity::new, getBlocks(EtherTorchBlock.class, EtherWallTorchBlock.class)).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<EtherBrazierBlockEntity>> ETHER_BRAZIER = BLOCK_ENTITY_TYPES.register("ether_brazier", () -> BlockEntityType.Builder.of(EtherBrazierBlockEntity::new, getBlocks(EtherBrazierBlock.class)).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<EtherCressetBlockEntity>> ETHER_CRESSET = BLOCK_ENTITY_TYPES.register("ether_cresset", () -> BlockEntityType.Builder.of(EtherCressetBlockEntity::new, getBlocks(EtherCressetBlock.class)).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ItemStandBlockEntity>> ITEM_STAND = BLOCK_ENTITY_TYPES.register("item_stand", () -> BlockEntityType.Builder.of(ItemStandBlockEntity::new, getBlocks(ItemStandBlock.class)).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ItemPedestalBlockEntity>> ITEM_PEDESTAL = BLOCK_ENTITY_TYPES.register("item_pedestal", () -> BlockEntityType.Builder.of(ItemPedestalBlockEntity::new, getBlocks(ItemPedestalBlock.class)).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TotemPoleBlockEntity>> TOTEM_POLE = BLOCK_ENTITY_TYPES.register("totem_pole", () -> BlockEntityType.Builder.of(TotemPoleBlockEntity::new, MalumBlocks.RUNEWOOD_TOTEM_POLE.get(), MalumBlocks.SOULWOOD_TOTEM_POLE.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TotemBaseBlockEntity>> TOTEM_BASE = BLOCK_ENTITY_TYPES.register("totem_base", () -> BlockEntityType.Builder.of(TotemBaseBlockEntity::new, getBlocks(TotemBaseBlock.class)).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WaveformTotemBaseBlockEntity>> WAVEFORM_TOTEM_BASE = BLOCK_ENTITY_TYPES.register("waveform_totem_base", () -> BlockEntityType.Builder.of(WaveformTotemBaseBlockEntity::new, getBlocks(WaveformTotemBaseBlock.class)).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RiteAnchorBlockEntity>> RITE_ANCHOR = BLOCK_ENTITY_TYPES.register("rite_anchor", () -> BlockEntityType.Builder.of(RiteAnchorBlockEntity::new, MalumBlocks.RITE_ANCHOR.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RiteUnweaverBlockEntity>> RITE_UNWEAVER = BLOCK_ENTITY_TYPES.register("rite_unweaver", () -> BlockEntityType.Builder.of(RiteUnweaverBlockEntity::new, MalumBlocks.RITE_UNWEAVER.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WaveChargerBlockEntity>> WAVECHARGER = BLOCK_ENTITY_TYPES.register("wavecharger", () -> BlockEntityType.Builder.of(WaveChargerBlockEntity::new, getBlocks(WaveChargerBlock.class)).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WaveBankerBlockEntity>> WAVEBANKER = BLOCK_ENTITY_TYPES.register("wavebanker", () -> BlockEntityType.Builder.of(WaveBankerBlockEntity::new, getBlocks(WaveBankerBlock.class)).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WaveMakerBlockEntity>> WAVEMAKER = BLOCK_ENTITY_TYPES.register("wavemaker", () -> BlockEntityType.Builder.of(WaveMakerBlockEntity::new, getBlocks(WaveMakerBlock.class)).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WaveBreakerBlockEntity>> WAVEBREAKER = BLOCK_ENTITY_TYPES.register("wavebreaker", () -> BlockEntityType.Builder.of(WaveBreakerBlockEntity::new, getBlocks(WaveBreakerBlock.class)).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GustIgniterBlockEntity>> GUST_IGNITER = BLOCK_ENTITY_TYPES.register("gust_igniter", () -> BlockEntityType.Builder.of(GustIgniterBlockEntity::new, MalumBlocks.GUST_IGNITER.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WindTunnelBlockEntity>> WIND_TUNNEL = BLOCK_ENTITY_TYPES.register("wind_tunnel", () -> BlockEntityType.Builder.of(WindTunnelBlockEntity::new, MalumBlocks.WIND_TUNNEL.get()).build(null));


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RitualPlinthBlockEntity>> RITUAL_PLINTH = BLOCK_ENTITY_TYPES.register("ritual_plinth", () -> BlockEntityType.Builder.of(RitualPlinthBlockEntity::new, MalumBlocks.RITUAL_PLINTH.get()).build(null));


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SoulwovenBannerBlockEntity>> SOULWOVEN_BANNER = BLOCK_ENTITY_TYPES.register("soulwoven_banner", () -> BlockEntityType.Builder.of(SoulwovenBannerBlockEntity::new, getBlocks(SoulwovenBannerBlock.class)).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ManaMoteBlockEntity>> MANA_MOTE = BLOCK_ENTITY_TYPES.register("mote_of_mana", () -> BlockEntityType.Builder.of(ManaMoteBlockEntity::new, getBlocks(ManaMoteBlock.class)).build(null));

    public static void registerCapabilities(RegisterCapabilitiesEvent event)
    {
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, SPIRIT_ALTAR.get(), IItemHandlerSupplier::getInventory);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, SPIRIT_CRUCIBLE.get(), IItemHandlerSupplier::getInventory);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, SPIRIT_CATALYZER.get(), IItemHandlerSupplier::getInventory);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, REPAIR_PYLON.get(), IItemHandlerSupplier::getInventory);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ITEM_STAND.get(), IItemHandlerSupplier::getInventory);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ITEM_PEDESTAL.get(), IItemHandlerSupplier::getInventory);
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
            event.registerBlockEntityRenderer(RUNIC_WORKBENCH.get(), MalumItemHolderRenderer::new);
            event.registerBlockEntityRenderer(SPIRIT_JAR.get(), SpiritJarRenderer::new);
            event.registerBlockEntityRenderer(SOUL_BRAZIER.get(), SoulBrazierRenderer::new);
            event.registerBlockEntityRenderer(ARCANA_PYLON.get(), ArcanaPylonRenderer::new);

            event.registerBlockEntityRenderer(SPIRIT_CRUCIBLE.get(), SpiritCrucibleRenderer::new);
            event.registerBlockEntityRenderer(SPIRIT_CATALYZER.get(), SpiritCatalyzerRenderer::new);
            event.registerBlockEntityRenderer(REPAIR_PYLON.get(), RepairPylonRenderer::new);

            event.registerBlockEntityRenderer(TOTEM_BASE.get(), TotemBaseRenderer::new);
            event.registerBlockEntityRenderer(TOTEM_POLE.get(), TotemPoleRenderer::new);

            event.registerBlockEntityRenderer(RITE_ANCHOR.get(), RiteAnchorRenderer::new);

            event.registerBlockEntityRenderer(WAVECHARGER.get(), WaveChargerRenderer::new);
            event.registerBlockEntityRenderer(WAVEBANKER.get(), WavebankerRenderer::new);
            event.registerBlockEntityRenderer(WAVEMAKER.get(), WaveMakerRenderer::new);
            event.registerBlockEntityRenderer(WAVEBREAKER.get(), WaveBreakerRenderer::new);

            event.registerBlockEntityRenderer(WIND_TUNNEL.get(), WindTunnelRenderer::new);

            event.registerBlockEntityRenderer(RITUAL_PLINTH.get(), RitualPlinthRenderer::new);

            event.registerBlockEntityRenderer(ITEM_STAND.get(), MalumItemHolderRenderer::new);
            event.registerBlockEntityRenderer(ITEM_PEDESTAL.get(), MalumItemHolderRenderer::new);



            event.registerBlockEntityRenderer(MANA_MOTE.get(), MoteOfManaRenderer::new);
            event.registerBlockEntityRenderer(SOULWOVEN_BANNER.get(), SoulwovenBannerRenderer::new);
        }
    }
}
