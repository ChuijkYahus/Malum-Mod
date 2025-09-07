package com.sammy.malum.common.entity.activator;

import com.sammy.malum.common.entity.FloatingEntity;
import com.sammy.malum.common.entity.FloatingItemDestinationData;
import com.sammy.malum.core.systems.rite.effect.*;
import com.sammy.malum.core.systems.spirit.type.SpiritArcanaType;
import com.sammy.malum.registry.common.MalumEntityDataSerializers;
import com.sammy.malum.registry.common.entity.MalumEntities;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;
import com.sammy.malum.visual_effects.SpiritLightSpecs;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import team.lodestar.lodestone.helpers.SoundHelper;
import team.lodestar.lodestone.systems.easing.*;

import java.util.UUID;

public class EntityRiteEffectActivatorEntity extends FloatingEntity {

    protected static final EntityDataAccessor<SpiritArcanaType> DATA_SPIRIT_GLOW = SynchedEntityData.defineId(EntityRiteEffectActivatorEntity.class, MalumEntityDataSerializers.SPIRIT_ARCANA.get());

    protected SpiritRiteEntityEffect<?> effect;

    public EntityRiteEffectActivatorEntity(Level level) {
        super(MalumEntities.RITE_ENTITY_EFFECT_ACTIVATOR.get(), level);
        maxAge = 4000;
    }

    public EntityRiteEffectActivatorEntity(Level level, SpiritRiteEntityEffect<?> effect, UUID targetUUID, Vec3 position, Vec3 velocity) {
        this(level);
        this.effect = effect;
        setDestination(new FloatingItemDestinationData(targetUUID));
        setPos(position);
        setDeltaMovement(velocity);
        maxAge = 800;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(DATA_SPIRIT_GLOW, MalumSpiritTypes.ARCANE_SPIRIT.get());
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        var spirit = getSpiritType();
        if (spirit != null) {
            spirit.save(pCompound);
        }
        if (effect != null) {
            effect.save(pCompound);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        setSpirit(SpiritArcanaType.load(pCompound).orElse(MalumSpiritTypes.ARCANE_SPIRIT.get()));
        effect = SpiritRiteEntityEffect.CODEC.load(pCompound, SpiritRiteEntityEffect.class).orElse(null);
    }

    @Override
    public void collect(ServerLevel level) {
        if (effect != null) {
            getDestination().getEntityCollector(level)
                    .ifPresent(target -> {
                        effect.tryApplyEffect(level, target);
                        SoundHelper.playSound(this, effect.getImpactSound().value(), effect.getImpactSoundVolume(target), Mth.nextFloat(random, 0.9f, 1.1f));
                    });
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (level().isClientSide) {
            var lightSpecs = SpiritLightSpecs.spiritLightSpecs(level(), getOffsetPosition(), getSpiritType());
            lightSpecs.getBuilder().modifyScaleData(d -> d.multiplyValue(1.5f)).multiplyLifetime(2);
            lightSpecs.getBloomBuilder().multiplyLifetime(2);
            lightSpecs.spawnParticles();
        }
    }

    @Override
    public int getWindUpDuration() {
        return 40;
    }

    @Override
    public float getFriction() {
        return 0.9f;
    }

    @Override
    public float getMovementInterpolation(float windUp, float distance) {
        return 0.02f + Easing.EXPO_IN.ease(windUp, 0, 0.4f);
    }

    public SpiritArcanaType getSpiritType() {
        return getEntityData().get(DATA_SPIRIT_GLOW);
    }

    public void setSpirit(SpiritArcanaType spirit) {
        getEntityData().set(DATA_SPIRIT_GLOW, spirit);
    }
}