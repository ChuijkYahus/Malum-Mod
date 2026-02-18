package com.sammy.malum.common.entity.bolt;

import com.sammy.malum.registry.common.MalumDamageTypes;
import com.sammy.malum.registry.common.sound.MalumSoundEvents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public abstract class AbstractStaffBoltProjectile extends AbstractBoltProjectile {

    public AbstractStaffBoltProjectile(EntityType<? extends AbstractBoltProjectile> pEntityType, Level level) {
        super(pEntityType, level);
    }

    @Override
    public ResourceKey<DamageType> getDamageType() {
        return MalumDamageTypes.VOODOO;
    }

    @Override
    public SoundEvent getShootSound() {
        return MalumSoundEvents.STAFF_FIRES.get();
    }

    @Override
    public SoundEvent getImpactSound() {
        return MalumSoundEvents.STAFF_STRIKES.get();
    }

    @Override
    public Class<LivingEntity> getHomingTarget() {
        return LivingEntity.class;
    }

    @Override
    public float getHomingDelta(float dot) {
        float angleScalar = Math.max(dot * 2f, 0.6f);
        return 0.1f * angleScalar;
    }

    @Override
    public float getMovementDecay() {
        return 0.96f;
    }

    @Override
    public float getBoltGravity() {
        return 0.02f;
    }
}
