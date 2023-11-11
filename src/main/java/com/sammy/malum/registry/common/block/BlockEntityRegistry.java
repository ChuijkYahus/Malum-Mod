package com.sammy.malum.registry.common.block;

import com.sammy.malum.MalumMod;
import com.sammy.malum.client.renderer.block.*;
import com.sammy.malum.common.block.ether.EtherBlock;
import com.sammy.malum.common.block.mana_mote.*;
import com.sammy.malum.common.block.storage.pedestal.ItemPedestalBlock;
import com.sammy.malum.common.block.storage.stand.ItemStandBlock;
import com.sammy.malum.common.block.curiosities.totem.TotemBaseBlock;
import com.sammy.malum.common.block.curiosities.totem.TotemPoleBlock;
import com.sammy.malum.common.block.ether.EtherBlockEntity;
import com.sammy.malum.common.block.curiosities.weeping_well.VoidConduitBlockEntity;
import com.sammy.malum.common.block.curiosities.weavers_workbench.WeaversWorkbenchBlockEntity;
import com.sammy.malum.common.block.curiosities.spirit_crucible.catalyzer.SpiritCatalyzerCoreBlockEntity;
import com.sammy.malum.common.block.curiosities.spirit_crucible.SpiritCrucibleCoreBlockEntity;
import com.sammy.malum.common.block.curiosities.obelisk.BrilliantObeliskBlockEntity;
import com.sammy.malum.common.block.curiosities.obelisk.RunewoodObeliskBlockEntity;
import com.sammy.malum.common.block.curiosities.spirit_altar.SpiritAltarBlockEntity;
import com.sammy.malum.common.block.storage.pedestal.ItemPedestalBlockEntity;
import com.sammy.malum.common.block.storage.stand.ItemStandBlockEntity;
import com.sammy.malum.common.block.storage.vial.SoulVialBlockEntity;
import com.sammy.malum.common.block.storage.jar.SpiritJarBlockEntity;
import com.sammy.malum.common.block.curiosities.tablet.TwistedTabletBlockEntity;
import com.sammy.malum.common.block.curiosities.totem.TotemBaseBlockEntity;
import com.sammy.malum.common.block.curiosities.totem.TotemPoleBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.sammy.malum.MalumMod.MALUM;

public class BlockEntityRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MALUM);

    public static final RegistryObject<BlockEntityType<VoidConduitBlockEntity>> VOID_CONDUIT = BLOCK_ENTITY_TYPES.register("void_conduit", () -> BlockEntityType.Builder.of(VoidConduitBlockEntity::new, BlockRegistry.VOID_CONDUIT.get()).build(null));
    public static final RegistryObject<BlockEntityType<SpiritAltarBlockEntity>> SPIRIT_ALTAR = BLOCK_ENTITY_TYPES.register("spirit_altar", () -> BlockEntityType.Builder.of(SpiritAltarBlockEntity::new, BlockRegistry.SPIRIT_ALTAR.get()).build(null));
    public static final RegistryObject<BlockEntityType<SpiritJarBlockEntity>> SPIRIT_JAR = BLOCK_ENTITY_TYPES.register("spirit_jar", () -> BlockEntityType.Builder.of(SpiritJarBlockEntity::new, BlockRegistry.SPIRIT_JAR.get()).build(null));
    public static final RegistryObject<BlockEntityType<SoulVialBlockEntity>> SOUL_VIAL = BLOCK_ENTITY_TYPES.register("soul_vial", () -> BlockEntityType.Builder.of(SoulVialBlockEntity::new, BlockRegistry.SOUL_VIAL.get()).build(null));

    public static final RegistryObject<BlockEntityType<WeaversWorkbenchBlockEntity>> WEAVERS_WORKBENCH = BLOCK_ENTITY_TYPES.register("weavers_workbench", () -> BlockEntityType.Builder.of(WeaversWorkbenchBlockEntity::new, BlockRegistry.WEAVERS_WORKBENCH.get()).build(null));

    public static final RegistryObject<BlockEntityType<SpiritCrucibleCoreBlockEntity>> SPIRIT_CRUCIBLE = BLOCK_ENTITY_TYPES.register("spirit_crucible", () -> BlockEntityType.Builder.of(SpiritCrucibleCoreBlockEntity::new, BlockRegistry.SPIRIT_CRUCIBLE.get()).build(null));
    public static final RegistryObject<BlockEntityType<SpiritCatalyzerCoreBlockEntity>> SPIRIT_CATALYZER = BLOCK_ENTITY_TYPES.register("spirit_catalyzer", () -> BlockEntityType.Builder.of(SpiritCatalyzerCoreBlockEntity::new, BlockRegistry.SPIRIT_CATALYZER.get()).build(null));
    public static final RegistryObject<BlockEntityType<TwistedTabletBlockEntity>> TWISTED_TABLET = BLOCK_ENTITY_TYPES.register("twisted_tablet", () -> BlockEntityType.Builder.of(TwistedTabletBlockEntity::new, BlockRegistry.TWISTED_TABLET.get()).build(null));
    public static final RegistryObject<BlockEntityType<RunewoodObeliskBlockEntity>> RUNEWOOD_OBELISK = BLOCK_ENTITY_TYPES.register("runewood_obelisk", () -> BlockEntityType.Builder.of(RunewoodObeliskBlockEntity::new, BlockRegistry.RUNEWOOD_OBELISK.get()).build(null));
    public static final RegistryObject<BlockEntityType<BrilliantObeliskBlockEntity>> BRILLIANT_OBELISK = BLOCK_ENTITY_TYPES.register("brilliant_obelisk", () -> BlockEntityType.Builder.of(BrilliantObeliskBlockEntity::new, BlockRegistry.BRILLIANT_OBELISK.get()).build(null));

    public static final RegistryObject<BlockEntityType<EtherBlockEntity>> ETHER = BLOCK_ENTITY_TYPES.register("ether", () -> BlockEntityType.Builder.of(EtherBlockEntity::new, getBlocks(EtherBlock.class)).build(null));

    public static final RegistryObject<BlockEntityType<ItemStandBlockEntity>> ITEM_STAND = BLOCK_ENTITY_TYPES.register("item_stand", () -> BlockEntityType.Builder.of(ItemStandBlockEntity::new, getBlocks(ItemStandBlock.class)).build(null));
    public static final RegistryObject<BlockEntityType<ItemPedestalBlockEntity>> ITEM_PEDESTAL = BLOCK_ENTITY_TYPES.register("item_pedestal", () -> BlockEntityType.Builder.of(ItemPedestalBlockEntity::new, getBlocks(ItemPedestalBlock.class)).build(null));

    public static final RegistryObject<BlockEntityType<TotemBaseBlockEntity>> TOTEM_BASE = BLOCK_ENTITY_TYPES.register("totem_base", () -> BlockEntityType.Builder.of(TotemBaseBlockEntity::new, getBlocks(TotemBaseBlock.class)).build(null));
    public static final RegistryObject<BlockEntityType<TotemPoleBlockEntity>> TOTEM_POLE = BLOCK_ENTITY_TYPES.register("totem_pole", () -> BlockEntityType.Builder.of(TotemPoleBlockEntity::new, getBlocks(TotemPoleBlock.class)).build(null));

    public static final RegistryObject<BlockEntityType<MoteOfManaBlockEntity>> MOTE_OF_MANA = BLOCK_ENTITY_TYPES.register("mote_of_mana", () -> BlockEntityType.Builder.of(MoteOfManaBlockEntity::new, getBlocks(SpiritMoteBlock.class)).build(null));

    public static Block[] getBlocks(Class<?>... blockClasses) {
        Collection<RegistryObject<Block>> blocks = BlockRegistry.BLOCKS.getEntries();
        List<Block> matchingBlocks = new ArrayList<>();
        for (RegistryObject<Block> registryObject : blocks) {
            if (Arrays.stream(blockClasses).anyMatch(b -> b.isInstance(registryObject.get()))) {
                matchingBlocks.add(registryObject.get());
            }
        }
        return matchingBlocks.toArray(new Block[0]);
    }

    public static Block[] getBlocksExact(Class<?> clazz) {
        Collection<RegistryObject<Block>> blocks = BlockRegistry.BLOCKS.getEntries();
        List<Block> matchingBlocks = new ArrayList<>();
        for (RegistryObject<Block> registryObject : blocks) {
            if (clazz.equals(registryObject.get().getClass())) {
                matchingBlocks.add(registryObject.get());
            }
        }
        return matchingBlocks.toArray(new Block[0]);
    }

    @Mod.EventBusSubscriber(modid = MalumMod.MALUM, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientOnly {
        @SubscribeEvent
        public static void registerRenderer(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(VOID_CONDUIT.get(), VoidConduitRenderer::new);
            event.registerBlockEntityRenderer(SPIRIT_ALTAR.get(), SpiritAltarRenderer::new);
            event.registerBlockEntityRenderer(SPIRIT_CRUCIBLE.get(), SpiritCrucibleRenderer::new);
            event.registerBlockEntityRenderer(SPIRIT_CATALYZER.get(), SpiritCatalyzerRenderer::new);
            event.registerBlockEntityRenderer(TWISTED_TABLET.get(), MalumItemHolderRenderer::new);
            event.registerBlockEntityRenderer(TOTEM_POLE.get(), TotemPoleRenderer::new);
            event.registerBlockEntityRenderer(ITEM_STAND.get(), MalumItemHolderRenderer::new);
            event.registerBlockEntityRenderer(ITEM_PEDESTAL.get(), MalumItemHolderRenderer::new);
            event.registerBlockEntityRenderer(SPIRIT_JAR.get(), SpiritJarRenderer::new);
            event.registerBlockEntityRenderer(SOUL_VIAL.get(), SoulVialRenderer::new);
            event.registerBlockEntityRenderer(MOTE_OF_MANA.get(), MoteOfManaRenderer::new);
        }
    }
}
