package com.sammy.malum.registry.common.entity;

import com.sammy.malum.MalumMod;
import com.sammy.malum.client.renderer.entity.*;
import com.sammy.malum.client.renderer.entity.activator.*;
import com.sammy.malum.client.renderer.entity.bolt.*;
import com.sammy.malum.client.renderer.entity.nitrate.*;
import com.sammy.malum.client.renderer.entity.scythe.*;
import com.sammy.malum.common.entity.*;
import com.sammy.malum.common.entity.activator.*;
import com.sammy.malum.common.entity.bolt.*;
import com.sammy.malum.common.entity.hidden_blade.*;
import com.sammy.malum.common.entity.nitrate.EthericNitrateEntity;
import com.sammy.malum.common.entity.nitrate.VividNitrateEntity;
import com.sammy.malum.common.entity.scythe.*;
import com.sammy.malum.common.entity.spirit.SpiritItemEntity;
import com.sammy.malum.common.entity.thrown.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MalumEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, MalumMod.MALUM);


    public static final DeferredHolder<EntityType<?>, EntityType<AscendingBlockEntity>> ASCENDING_BLOCK = ENTITY_TYPES.register("ascending_block",
            () -> EntityType.Builder.<AscendingBlockEntity>of((e, w) -> new AscendingBlockEntity(w), MobCategory.MISC).sized(0.98F, 0.98F).clientTrackingRange(10).updateInterval(20)
                    .build(MalumMod.malumPath("ascending_block").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<SpiritItemEntity>> NATURAL_SPIRIT = ENTITY_TYPES.register("natural_spirit",
            () -> EntityType.Builder.<SpiritItemEntity>of((e, w) -> new SpiritItemEntity(w), MobCategory.MISC).sized(0.5F, 0.75F).clientTrackingRange(10)
                    .build(MalumMod.malumPath("natural_spirit").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<EthericNitrateEntity>> ETHERIC_NITRATE = ENTITY_TYPES.register("etheric_nitrate",
            () -> EntityType.Builder.<EthericNitrateEntity>of((e, w) -> new EthericNitrateEntity(w), MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(20)
                    .build(MalumMod.malumPath("etheric_nitrate").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<VividNitrateEntity>> VIVID_NITRATE = ENTITY_TYPES.register("vivid_nitrate",
            () -> EntityType.Builder.<VividNitrateEntity>of((e, w) -> new VividNitrateEntity(w), MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(20)
                    .build(MalumMod.malumPath("vivid_nitrate").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<ScytheBoomerangEntity>> SCYTHE_BOOMERANG = ENTITY_TYPES.register("scythe_boomerang",
            () -> EntityType.Builder.<ScytheBoomerangEntity>of((e, w) -> new ScytheBoomerangEntity(w), MobCategory.MISC).sized(2f, 2f).clientTrackingRange(20)
                    .build(MalumMod.malumPath("scythe_boomerang").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<LocalizedMaelstromEntity>> SCYTHE_MAELSTROM = ENTITY_TYPES.register("scythe_maelstrom",
            () -> EntityType.Builder.<LocalizedMaelstromEntity>of((e, w) -> new LocalizedMaelstromEntity(w), MobCategory.MISC).sized(2f, 2f).clientTrackingRange(20)
                    .build(MalumMod.malumPath("scythe_maelstrom").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<SpellweaverToolEffectActivatorEntity>> SPELLWEAVER_TOOL_EFFECT_ACTIVATOR = ENTITY_TYPES.register("spellweavers_locus",
            () -> EntityType.Builder.<SpellweaverToolEffectActivatorEntity>of((e, w) -> new SpellweaverToolEffectActivatorEntity(w), MobCategory.MISC).sized(0.25f, 0.25f).clientTrackingRange(4).updateInterval(1)
                    .build(MalumMod.malumPath("spellweavers_locus").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityRiteEffectActivatorEntity>> RITE_ENTITY_EFFECT_ACTIVATOR = ENTITY_TYPES.register("seeking_rite_locus",
            () -> EntityType.Builder.<EntityRiteEffectActivatorEntity>of((e, w) -> new EntityRiteEffectActivatorEntity(w), MobCategory.MISC).sized(1f, 1f).clientTrackingRange(4).updateInterval(1)
                    .build(MalumMod.malumPath("seeking_rite_locus").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<BlockRiteEffectActivatorEntity>> RITE_BLOCK_EFFECT_ACTIVATOR = ENTITY_TYPES.register("bound_rite_locus",
            () -> EntityType.Builder.<BlockRiteEffectActivatorEntity>of((e, w) -> new BlockRiteEffectActivatorEntity(w), MobCategory.MISC).sized(1f, 1f).clientTrackingRange(1).updateInterval(1)
                    .build(MalumMod.malumPath("bound_rite_locus").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<SpiritCollectionActivatorEntity>> SPIRIT_COLLECTION_ACTIVATOR = ENTITY_TYPES.register("pneuma_void",
            () -> EntityType.Builder.<SpiritCollectionActivatorEntity>of((e, w) -> new SpiritCollectionActivatorEntity(w), MobCategory.MISC).sized(1f, 1f).clientTrackingRange(10)
                    .build(MalumMod.malumPath("pneuma_void").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<HiddenBladeDelayedImpactEntity>> HIDDEN_BLADE_DELAYED_IMPACT = ENTITY_TYPES.register("hidden_blade_delayed_impact",
            () -> EntityType.Builder.<HiddenBladeDelayedImpactEntity>of((e, w) -> new HiddenBladeDelayedImpactEntity(w), MobCategory.MISC).sized(8F, 8F).clientTrackingRange(10)
                    .build(MalumMod.malumPath("hidden_blade_delayed_impact").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<ThrownConcentratedGluttony>> THROWN_GLUTTONY = ENTITY_TYPES.register("thrown_gluttony",
            () -> EntityType.Builder.<ThrownConcentratedGluttony>of((e, w) -> new ThrownConcentratedGluttony(w), MobCategory.MISC).sized(0.25f, 0.25f).clientTrackingRange(10)
                    .build(MalumMod.malumPath("thrown_gluttony").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<HexBoltEntity>> HEX_BOLT = ENTITY_TYPES.register("hex_bolt",
            () -> EntityType.Builder.<HexBoltEntity>of((e, w) -> new HexBoltEntity(w), MobCategory.MISC).sized(1.25F, 1.25F).clientTrackingRange(10)
                    .build(MalumMod.malumPath("hex_bolt").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<DrainingBoltEntity>> DRAINING_BOLT = ENTITY_TYPES.register("draining_bolt",
            () -> EntityType.Builder.<DrainingBoltEntity>of((e, w) -> new DrainingBoltEntity(w), MobCategory.MISC).sized(1.5F, 1.5f).clientTrackingRange(10)
                    .build(MalumMod.malumPath("draining_bolt").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<EntropicFlameBoltEntity>> ENTROPIC_FLAME_BOLT = ENTITY_TYPES.register("entropic_flame_bolt",
            () -> EntityType.Builder.<EntropicFlameBoltEntity>of((e, w) -> new EntropicFlameBoltEntity(w), MobCategory.MISC).sized(3F, 3F).clientTrackingRange(10)
                    .build(MalumMod.malumPath("entropic_flame_bolt").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<SunderingAnchorProjectileEntity>> SUNDERING_ANCHOR = ENTITY_TYPES.register("sundering_anchor",
            () -> EntityType.Builder.<SunderingAnchorProjectileEntity>of((e, w) -> new SunderingAnchorProjectileEntity(w), MobCategory.MISC).sized(2f, 2f).clientTrackingRange(10)
                    .build(MalumMod.malumPath("sundering_anchor").toString()));

    @EventBusSubscriber(modid = MalumMod.MALUM, value = Dist.CLIENT)
    public static class ClientOnly {
        @SubscribeEvent
        public static void bindEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
            EntityRenderers.register(MalumEntities.ASCENDING_BLOCK.get(), AscendingBlockRenderer::new);
            EntityRenderers.register(MalumEntities.NATURAL_SPIRIT.get(), FloatingItemEntityRenderer::new);

            EntityRenderers.register(MalumEntities.SCYTHE_BOOMERANG.get(), ScytheBoomerangEntityRenderer::new);
            EntityRenderers.register(MalumEntities.SCYTHE_MAELSTROM.get(), NoopRenderer::new);

            EntityRenderers.register(MalumEntities.ETHERIC_NITRATE.get(), EthericNitrateEntityRenderer::new);
            EntityRenderers.register(MalumEntities.VIVID_NITRATE.get(), VividNitrateEntityRenderer::new);

            EntityRenderers.register(MalumEntities.SPELLWEAVER_TOOL_EFFECT_ACTIVATOR.get(), SpellweaverToolEffectActivatorEntityRenderer::new);
            EntityRenderers.register(MalumEntities.RITE_ENTITY_EFFECT_ACTIVATOR.get(), EntityRiteEffectActivatorEntityRenderer::new);
            EntityRenderers.register(MalumEntities.RITE_BLOCK_EFFECT_ACTIVATOR.get(), BlockRiteEffectActivatorEntityRenderer::new);

            EntityRenderers.register(MalumEntities.SPIRIT_COLLECTION_ACTIVATOR.get(), SpiritCollectionActivatorEntityRenderer::new);
            EntityRenderers.register(MalumEntities.HIDDEN_BLADE_DELAYED_IMPACT.get(), NoopRenderer::new);

            EntityRenderers.register(MalumEntities.THROWN_GLUTTONY.get(), ThrownConcentratedGluttonyRenderer::new);

            EntityRenderers.register(MalumEntities.HEX_BOLT.get(), HexBoltEntityRenderer::new);
            EntityRenderers.register(MalumEntities.DRAINING_BOLT.get(), DrainingBoltEntityRenderer::new);
            EntityRenderers.register(MalumEntities.ENTROPIC_FLAME_BOLT.get(), EntropicFlameBoltEntityRenderer::new);

            EntityRenderers.register(MalumEntities.SUNDERING_ANCHOR.get(), SunderingAnchorEntityRenderer::new);

        }
    }
}
