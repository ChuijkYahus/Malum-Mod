package com.sammy.malum.common.entity.activator;

import com.sammy.malum.common.entity.FloatingEntity;
import com.sammy.malum.common.entity.FloatingItemDestinationData;
import com.sammy.malum.common.entity.FloatingItemEntity;
import com.sammy.malum.core.handlers.SoulHarvestHandler;
import com.sammy.malum.core.systems.spirit.type.SpiritArcanaType;
import com.sammy.malum.registry.common.MalumEntityDataSerializers;
import com.sammy.malum.registry.common.MalumSoundEvents;
import com.sammy.malum.registry.common.entity.MalumEntities;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;
import com.sammy.malum.visual_effects.SpiritLightSpecs;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import team.lodestar.lodestone.helpers.SoundHelper;
import team.lodestar.lodestone.systems.rendering.trail.TrailPointBuilder;

import java.util.UUID;

public class RiteEffectActivatorEntity extends FloatingEntity {

    protected static final EntityDataAccessor<SpiritArcanaType> DATA_SPIRIT_GLOW = SynchedEntityData.defineId(RiteEffectActivatorEntity.class, MalumEntityDataSerializers.SPIRIT_ARCANA.get());

    public RiteEffectActivatorEntity(Level level) {
        super(MalumEntities.RITE_EFFECT_ACTIVATOR.get(), level);
    }

    public RiteEffectActivatorEntity(Level level, UUID targetUUID, Vec3 position, Vec3 velocity) {
        this(level);
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
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        setSpirit(SpiritArcanaType.load(pCompound).orElse(MalumSpiritTypes.ARCANE_SPIRIT.get()));
    }

    @Override
    public SoundSource getSoundSource() {
        return SoundSource.NEUTRAL;
    }

    @Override
    public void collect(ServerLevel level) {
        SoundHelper.playSound(this, MalumSoundEvents.SPIRIT_PICKUP.get(), 0.3f, Mth.nextFloat(random, 1.2f, 1.5f));
    }

    @Override
    public void tick() {
        super.tick();
        if (level().isClientSide) {
            Vec3 motion = getDeltaMovement();
            Vec3 norm = motion.normalize().scale(0.05f);
            var lightSpecs = SpiritLightSpecs.spiritLightSpecs(level(), getOffsetPosition(), getSpiritType());
            lightSpecs.getBuilder().setMotion(norm);
            lightSpecs.getBloomBuilder().setMotion(norm);
            lightSpecs.spawnParticles();
        }
    }

    @Override
    public int getWindUpDuration() {
        return 60;
    }

    @Override
    public float getFriction() {
        return 0.8f;
    }

    @Override
    public float getMovementInterpolation(float windUp, float distance) {
        if (distance < 2f) {
            return 1f;
        }
        return 0.05f + windUp * 0.2f;
    }
    public SpiritArcanaType getSpiritType() {
        return getEntityData().get(DATA_SPIRIT_GLOW);
    }

    public void setSpirit(SpiritArcanaType spirit) {
        getEntityData().set(DATA_SPIRIT_GLOW, spirit);
    }
}