package com.sammy.malum.registry.common.entity;

import com.sammy.malum.MalumMod;
import com.sammy.malum.client.renderer.entity.*;
import com.sammy.malum.client.renderer.entity.activator.*;
import com.sammy.malum.client.renderer.entity.cultist.cherub.CherubRenderer;
import com.sammy.malum.client.renderer.entity.weapon.staff.*;
import com.sammy.malum.client.renderer.entity.cultist.altar.CultistBlessingRenderer;
import com.sammy.malum.client.renderer.entity.cultist.altar.CultistBoltRenderer;
import com.sammy.malum.client.renderer.entity.cultist.cardinal.EntropyChargeRenderer;
import com.sammy.malum.client.renderer.entity.nitrate.*;
import com.sammy.malum.client.renderer.entity.weapon.scythe.*;
import com.sammy.malum.client.renderer.entity.cultist.altar.AltarRenderer;
import com.sammy.malum.client.renderer.entity.cultist.believer.BelieverRenderer;
import com.sammy.malum.client.renderer.entity.cultist.cardinal.CardinalRenderer;
import com.sammy.malum.client.renderer.entity.cultist.evangelist.EvangelistRenderer;
import com.sammy.malum.common.entity.*;
import com.sammy.malum.common.entity.activator.*;
import com.sammy.malum.common.entity.activator.gluttony.*;
import com.sammy.malum.common.entity.activator.rite.*;
import com.sammy.malum.common.entity.bolt.*;
import com.sammy.malum.common.entity.mob.cultist.altar.projectile.CultistBlessingProjectile;
import com.sammy.malum.common.entity.mob.cultist.altar.projectile.CursedBoltProjectile;
import com.sammy.malum.common.entity.mob.cultist.cardinal.projectile.EntropyChargeProjectile;
import com.sammy.malum.common.entity.mob.cultist.believer.BelieverCultist;
import com.sammy.malum.common.entity.mob.cultist.cardinal.CardinalCultist;
import com.sammy.malum.common.entity.mob.cultist.cherub.CherubCultist;
import com.sammy.malum.common.entity.mob.cultist.evangelist.EvangelistCultist;
import com.sammy.malum.common.entity.hidden_blade.*;
import com.sammy.malum.common.entity.mob.cultist.altar.AltarCultist;
import com.sammy.malum.common.entity.nitrate.EthericNitrate;
import com.sammy.malum.common.entity.nitrate.VividNitrate;
import com.sammy.malum.common.entity.scythe.*;
import com.sammy.malum.common.entity.spirit.SpiritItemEntity;
import com.sammy.malum.common.entity.thrown.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Consumer;

public class MalumEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, MalumMod.MALUM);

    public static final DeferredHolder<EntityType<?>, EntityType<AltarCultist>> ALTAR = register(
            "altar", AltarCultist::new, MobCategory.MONSTER, b -> b
                    .sized(0.9F, 1.25F)
                    .eyeHeight(1F)
                    .passengerAttachments(1.1F)
                    .ridingOffset(-0.2F)
                    .clientTrackingRange(8)
    );

    public static final DeferredHolder<EntityType<?>, EntityType<BelieverCultist>> BELIEVER = register(
            "believer", BelieverCultist::new, MobCategory.MONSTER, b -> b
                    .sized(0.6F, 1.9F)
                    .eyeHeight(1.75F)
                    .passengerAttachments(1.8f)
                    .clientTrackingRange(8)
    );

    public static final DeferredHolder<EntityType<?>, EntityType<CherubCultist>> CHERUB = register(
            "cherub", CherubCultist::new, MobCategory.MONSTER, b -> b
                    .sized(0.8F, 0.8F)
                    .eyeHeight(0.5f)
                    .clientTrackingRange(8)
    );

    public static final DeferredHolder<EntityType<?>, EntityType<CardinalCultist>> CARDINAL = register(
            "cardinal", CardinalCultist::new, MobCategory.MONSTER, b -> b
                    .sized(1.5F, 2.6F)
                    .eyeHeight(2.7F)
                    .passengerAttachments(2.8f)
                    .clientTrackingRange(8)
    );

    public static final DeferredHolder<EntityType<?>, EntityType<EvangelistCultist>> EVANGELIST = register(
            "evangelist", EvangelistCultist::new, MobCategory.MONSTER, b -> b
                    .sized(0.9F, 2.9F)
                    .eyeHeight(2.75F)
                    .passengerAttachments(2.8f)
                    .clientTrackingRange(8)
    );

    public static final DeferredHolder<EntityType<?>, EntityType<CursedBoltProjectile>> CURSED_BOLT =
            register("cursed_bolt", CursedBoltProjectile::new, 1F, 1F, 10);

    public static final DeferredHolder<EntityType<?>, EntityType<EntropyChargeProjectile>> ENTROPY_CHARGE =
            register("entropy_charge", EntropyChargeProjectile::new, 0.5F, 0.5F, 10);


    public static final DeferredHolder<EntityType<?>, EntityType<CultistBlessingProjectile>> CULTIST_BLESSING =
            register("cultist_blessing", CultistBlessingProjectile::new, 1F, 1F, 10);

    public static final DeferredHolder<EntityType<?>, EntityType<AscendingBlock>> ASCENDING_BLOCK =
            register("ascending_block", AscendingBlock::new, 0.98F, 0.98F, 10, 20);

    public static final DeferredHolder<EntityType<?>, EntityType<SpiritItemEntity>> NATURAL_SPIRIT =
            register("natural_spirit", SpiritItemEntity::new, 0.5F, 0.75F, 10);

    public static final DeferredHolder<EntityType<?>, EntityType<EthericNitrate>> ETHERIC_NITRATE =
            register("etheric_nitrate", EthericNitrate::new, 0.5F, 0.5F, 20);

    public static final DeferredHolder<EntityType<?>, EntityType<VividNitrate>> VIVID_NITRATE =
            register("vivid_nitrate", VividNitrate::new, 0.5F, 0.5F, 20);

    public static final DeferredHolder<EntityType<?>, EntityType<ScytheBoomerang>> SCYTHE_BOOMERANG =
            register("scythe_boomerang", ScytheBoomerang::new, 2f, 2f, 20);

    public static final DeferredHolder<EntityType<?>, EntityType<LocalizedMaelstrom>> SCYTHE_MAELSTROM =
            register("scythe_maelstrom", LocalizedMaelstrom::new, 2f, 2f, 20);

    public static final DeferredHolder<EntityType<?>, EntityType<GluttonyDamageActivator>> GLUTTONY_LOCUST =
            register("gluttony_locust", GluttonyDamageActivator::new, 0.25f, 0.25f, 4, 1);

    public static final DeferredHolder<EntityType<?>, EntityType<SpellweaverToolEffectActivator>> SPELLWEAVER_TOOL_EFFECT_ACTIVATOR =
            register("spellweavers_locus", SpellweaverToolEffectActivator::new, 0.25f, 0.25f, 4, 1);

    public static final DeferredHolder<EntityType<?>, EntityType<EntityRiteEffectActivator>> RITE_ENTITY_EFFECT_ACTIVATOR =
            register("seeking_rite_locus", EntityRiteEffectActivator::new, 1f, 1f, 4, 1);

    public static final DeferredHolder<EntityType<?>, EntityType<BlockRiteEffectActivator>> RITE_BLOCK_EFFECT_ACTIVATOR =
            register("bound_rite_locus", BlockRiteEffectActivator::new, 1f, 1f, 1, 1);

    public static final DeferredHolder<EntityType<?>, EntityType<BlockRiteEffectWaveActivator>> RITE_BLOCK_WAVE_EFFECT_ACTIVATOR =
            register("locus_wave_projection", BlockRiteEffectWaveActivator::new, 1f, 1f, 1, 1);

    public static final DeferredHolder<EntityType<?>, EntityType<SpiritCollectionActivator>> SPIRIT_COLLECTION_ACTIVATOR =
            register("pneuma_void", SpiritCollectionActivator::new, 1f, 1f, 10);

    public static final DeferredHolder<EntityType<?>, EntityType<HiddenBladeDelayedImpact>> HIDDEN_BLADE_DELAYED_IMPACT =
            register("hidden_blade_delayed_impact", HiddenBladeDelayedImpact::new, 8F, 8F, 10);

    public static final DeferredHolder<EntityType<?>, EntityType<ThrownConcentratedGluttony>> THROWN_GLUTTONY =
            register("thrown_gluttony", ThrownConcentratedGluttony::new, 0.25f, 0.25f, 10);

    public static final DeferredHolder<EntityType<?>, EntityType<HexBolt>> HEX_BOLT =
            register("hex_bolt", HexBolt::new, 1.5F, 1.5F, 10);

    public static final DeferredHolder<EntityType<?>, EntityType<DrainingBolt>> DRAINING_BOLT =
            register("draining_bolt", DrainingBolt::new, 1.5F, 1.5F, 10);

    public static final DeferredHolder<EntityType<?>, EntityType<EntropicFlameBolt>> ENTROPIC_FLAME_BOLT =
            register("entropic_flame_bolt", EntropicFlameBolt::new, 3F, 3F, 10);

    public static final DeferredHolder<EntityType<?>, EntityType<SunderingAnchorProjectile>> SUNDERING_ANCHOR =
            register("sundering_anchor", SunderingAnchorProjectile::new, 2f, 2f, 10);

    private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(
            String name, MalumEntityFactory<T> factory, float width, float height, int trackingRange) {
        return register(name, factory, width, height, trackingRange, 3);
    }

    private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(
            String name, MalumEntityFactory<T> factory, float width, float height, int trackingRange, int updateInterval) {

        return register(name, factory, MobCategory.MISC, b -> b
                .sized(width, height)
                .clientTrackingRange(trackingRange)
                .updateInterval(updateInterval)
        );
    }

    private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(
            String name, MalumEntityFactory<T> factory, MobCategory category, Consumer<EntityType.Builder<T>> builder) {
        EntityType.EntityFactory<T> entityFactory = (e, level) -> factory.create(level);
        return ENTITY_TYPES.register(name, () -> {
            EntityType.Builder<T> b = EntityType.Builder.of(entityFactory, category);
            builder.accept(b);
            return b.build(MalumMod.malumPath(name).toString());
        });
    }

    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(ALTAR.get(), AltarCultist.createAttributes().build());
        event.put(CHERUB.get(), CherubCultist.createAttributes().build());
        event.put(BELIEVER.get(), BelieverCultist.createAttributes().build());
        event.put(CARDINAL.get(), CardinalCultist.createAttributes().build());
        event.put(EVANGELIST.get(), EvangelistCultist.createAttributes().build());
    }

    public interface MalumEntityFactory<T extends Entity> {
        T create(Level level);
    }

    public static class ClientOnly {
        public static void bindEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
            EntityRenderers.register(MalumEntityTypes.ALTAR.get(), AltarRenderer::new);
            EntityRenderers.register(MalumEntityTypes.CHERUB.get(), CherubRenderer::new);
            EntityRenderers.register(MalumEntityTypes.BELIEVER.get(), BelieverRenderer::new);
            EntityRenderers.register(MalumEntityTypes.CARDINAL.get(), CardinalRenderer::new);
            EntityRenderers.register(MalumEntityTypes.EVANGELIST.get(), EvangelistRenderer::new);

            EntityRenderers.register(MalumEntityTypes.CURSED_BOLT.get(), CultistBoltRenderer::new);
            EntityRenderers.register(MalumEntityTypes.CULTIST_BLESSING.get(), CultistBlessingRenderer::new);
            EntityRenderers.register(MalumEntityTypes.ENTROPY_CHARGE.get(), EntropyChargeRenderer::new);

            EntityRenderers.register(MalumEntityTypes.ASCENDING_BLOCK.get(), AscendingBlockRenderer::new);
            EntityRenderers.register(MalumEntityTypes.NATURAL_SPIRIT.get(), FloatingItemRenderer::new);

            EntityRenderers.register(MalumEntityTypes.SCYTHE_BOOMERANG.get(), ScytheBoomerangRenderer::new);
            EntityRenderers.register(MalumEntityTypes.SCYTHE_MAELSTROM.get(), NoopRenderer::new);

            EntityRenderers.register(MalumEntityTypes.GLUTTONY_LOCUST.get(), GluttonyDamageActivatorRenderer::new);

            EntityRenderers.register(MalumEntityTypes.ETHERIC_NITRATE.get(), EthericNitrateRenderer::new);
            EntityRenderers.register(MalumEntityTypes.VIVID_NITRATE.get(), VividNitrateRenderer::new);

            EntityRenderers.register(MalumEntityTypes.SPELLWEAVER_TOOL_EFFECT_ACTIVATOR.get(), SpellweaverToolEffectActivatorRenderer::new);
            EntityRenderers.register(MalumEntityTypes.RITE_ENTITY_EFFECT_ACTIVATOR.get(), EntityRiteEffectActivatorRenderer::new);
            EntityRenderers.register(MalumEntityTypes.RITE_BLOCK_EFFECT_ACTIVATOR.get(), BlockRiteEffectActivatorRenderer::new);
            EntityRenderers.register(MalumEntityTypes.RITE_BLOCK_WAVE_EFFECT_ACTIVATOR.get(), BlockRiteEffectWaveActivatorRenderer::new);

            EntityRenderers.register(MalumEntityTypes.SPIRIT_COLLECTION_ACTIVATOR.get(), SpiritCollectionActivatorRenderer::new);
            EntityRenderers.register(MalumEntityTypes.HIDDEN_BLADE_DELAYED_IMPACT.get(), NoopRenderer::new);

            EntityRenderers.register(MalumEntityTypes.THROWN_GLUTTONY.get(), ThrownConcentratedGluttonyRenderer::new);

            EntityRenderers.register(MalumEntityTypes.HEX_BOLT.get(), HexBoltRenderer::new);
            EntityRenderers.register(MalumEntityTypes.DRAINING_BOLT.get(), DrainingBoltRenderer::new);
            EntityRenderers.register(MalumEntityTypes.ENTROPIC_FLAME_BOLT.get(), EntropicFlameBoltRenderer::new);

            EntityRenderers.register(MalumEntityTypes.SUNDERING_ANCHOR.get(), SunderingAnchorRenderer::new);

        }
    }
}