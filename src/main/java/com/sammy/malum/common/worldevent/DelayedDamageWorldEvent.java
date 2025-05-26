package com.sammy.malum.common.worldevent;

import com.sammy.malum.registry.common.*;
import com.sammy.malum.visual_effects.networked.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.nbt.*;
import net.minecraft.resources.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.systems.network.*;
import team.lodestar.lodestone.systems.network.particle.*;
import team.lodestar.lodestone.systems.worldevent.*;

import javax.annotation.*;
import java.util.*;

@SuppressWarnings("rawtypes")
public class DelayedDamageWorldEvent extends WorldEventInstance {

    protected ResourceKey<DamageType> physicalDamageType = DamageTypes.PLAYER_ATTACK;
    protected ResourceKey<DamageType> magicDamageType = MalumDataTypes.VOODOO;

    protected UUID attackerUUID;
    protected UUID projectileUUID;
    protected UUID targetUUID;
    protected int delay;
    protected float physicalDamage;
    protected float magicDamage;

    protected Holder<SoundEvent> soundEvent;
    protected float minPitch;
    protected float maxPitch;
    protected float minVolume;
    protected float maxVolume;

    protected NetworkedParticleEffectType particleEffect;
    protected MalumNetworkedParticleEffectColorData particleColor;
    protected NetworkedParticleEffectExtraData nbtData;

    public DelayedDamageWorldEvent(Entity target) {
        this();
        this.targetUUID = target.getUUID();
    }

    public DelayedDamageWorldEvent() {
        this(MalumWorldEventTypes.DELAYED_DAMAGE.get());
    }

    public DelayedDamageWorldEvent(WorldEventType type) {
        super(type);
    }

    public DelayedDamageWorldEvent setAttacker(Entity attacker) {
        return setAttacker(attacker, attacker);
    }

    public DelayedDamageWorldEvent setAttacker(@Nonnull Entity attacker, Entity projectile) {
        this.attackerUUID = attacker.getUUID();
        this.projectileUUID = projectile != null ? projectile.getUUID() : null;
        return this;
    }

    public DelayedDamageWorldEvent setDamageData(float physicalDamage, float magicDamage, int delay) {
        this.physicalDamage = physicalDamage;
        this.magicDamage = magicDamage;
        this.delay = delay;
        return this;
    }

    public DelayedDamageWorldEvent setDamageData(ResourceKey<DamageType> physicalDamageType, float physicalDamage, ResourceKey<DamageType> magicDamageType, float magicDamage, int delay) {
        this.physicalDamage = physicalDamage;
        this.magicDamage = magicDamage;
        this.delay = delay;
        return setPhysicalDamageType(physicalDamageType).setMagicDamageType(magicDamageType);
    }

    public DelayedDamageWorldEvent setPhysicalDamageType(ResourceKey<DamageType> physicalDamageType) {
        this.physicalDamageType = physicalDamageType;
        return this;
    }

    public DelayedDamageWorldEvent setMagicDamageType(ResourceKey<DamageType> magicDamageType) {
        this.magicDamageType = magicDamageType;
        return this;
    }

    public DelayedDamageWorldEvent setSound(Holder<SoundEvent> soundEvent, float minPitch, float maxPitch, float volume) {
        return setSound(soundEvent, minPitch, maxPitch, volume, volume);
    }

    public DelayedDamageWorldEvent setSound(Holder<SoundEvent> soundEvent, float minPitch, float maxPitch, float minVolume, float maxVolume) {
        this.soundEvent = soundEvent;
        this.minPitch = minPitch;
        this.maxPitch = maxPitch;
        this.minVolume = minVolume;
        this.maxVolume = maxVolume;
        return this;
    }

    public DelayedDamageWorldEvent setImpactParticleEffect(NetworkedParticleEffectType particleEffect, MalumNetworkedParticleEffectColorData color) {
        this.particleEffect = particleEffect;
        this.particleColor = color;
        return this;
    }

    public DelayedDamageWorldEvent setParticleEffectNBT(NetworkedParticleEffectExtraData nbtData) {
        this.nbtData = nbtData;
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void tick(Level level) {
        if (delay > 0) {
            delay--;
            return;
        }
        if (level instanceof ServerLevel serverLevel) {
            Entity target = serverLevel.getEntity(targetUUID);
            Entity attacker = serverLevel.getEntity(attackerUUID);
            Entity projectile = serverLevel.getEntity(projectileUUID);
            if (target != null) {
                if (target.isAlive()) {
                    var deltaMovement = target.getDeltaMovement();
                    if (physicalDamage > 0) {
                        target.invulnerableTime = 0;
                        target.hurt(DamageTypeHelper.create(level, physicalDamageType, projectile, attacker), physicalDamage);
                    }
                    if (magicDamage > 0) {
                        target.invulnerableTime = 0;
                        target.hurt(DamageTypeHelper.create(level, magicDamageType, projectile, attacker), magicDamage);
                    }
                    target.setDeltaMovement(deltaMovement);
                    if (soundEvent != null) {
                        float pitch = RandomHelper.randomBetween(serverLevel.getRandom(), minPitch, maxPitch);
                        float volume = RandomHelper.randomBetween(serverLevel.getRandom(), minVolume, maxVolume);
                        SoundHelper.playSound(attacker == null ? target : attacker, soundEvent.value(), volume, pitch);
                    }
                    if (particleEffect != null) {
                        if (particleEffect instanceof MalumNetworkedWeaponParticleEffectType weaponParticleEffect) {
                            weaponParticleEffect.createEffect()
                                    .originatesFrom(attacker)
                                    .targets(target)
                                    .tiedToTarget()
                                    .color(particleColor)
                                    .customData((WeaponParticleEffectType.WeaponParticleEffectData) nbtData)
                                    .spawn(serverLevel);
                        }
                        else {
                            particleEffect.createEffect(target)
                                    .color(particleColor)
                                    .customData(nbtData)
                                    .spawn(serverLevel);
                        }
                    }
                }
            }
        }
        end(level);
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {
        if (physicalDamageType != DamageTypes.PLAYER_ATTACK) {
            compoundTag.putString("physicalDamageType", physicalDamageType.location().toString());
        }
        if (magicDamageType != MalumDataTypes.VOODOO) {
            compoundTag.putString("magicDamageType", magicDamageType.location().toString());
        }
        if (attackerUUID != null) {
            compoundTag.putUUID("attackerUUID", attackerUUID);
        }
        compoundTag.putUUID("targetUUID", targetUUID);
        compoundTag.putFloat("physicalDamage", physicalDamage);
        compoundTag.putFloat("magicDamage", magicDamage);
        compoundTag.putInt("delay", delay);
        if (soundEvent != null) {
            compoundTag.put("soundEvent", SoundEvent.CODEC.encodeStart(NbtOps.INSTANCE, soundEvent).result().orElseThrow());
            compoundTag.putFloat("minPitch", minPitch);
            compoundTag.putFloat("maxPitch", maxPitch);
            compoundTag.putFloat("minVolume", minVolume);
            compoundTag.putFloat("maxVolume", maxVolume);
        }
        if (particleEffect != null) {
            compoundTag.put("particleEffect", NetworkedParticleEffectType.CODEC.encodeStart(NbtOps.INSTANCE, particleEffect).result().orElseThrow());
            compoundTag.put("particleColor", MalumNetworkedParticleEffectColorData.CODEC.encodeStart(NbtOps.INSTANCE, particleColor).result().orElseThrow());
        }
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {
        physicalDamageType = compoundTag.contains("physicalDamageType")
                ? ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse(compoundTag.getString("physicalDamageType")))
                : DamageTypes.PLAYER_ATTACK;
        magicDamageType = compoundTag.contains("magicDamageType")
                ? ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse(compoundTag.getString("magicDamageType")))
                : MalumDataTypes.VOODOO;
        attackerUUID = compoundTag.getUUID("attackerUUID");
        targetUUID = compoundTag.getUUID("targetUUID");
        physicalDamage = compoundTag.getFloat("physicalDamage");
        magicDamage = compoundTag.getFloat("magicDamage");
        delay = compoundTag.getInt("delay");
        soundEvent = SoundEvent.CODEC.parse(NbtOps.INSTANCE, compoundTag.get("soundEvent")).result().orElse(null);
        minPitch = compoundTag.getFloat("minPitch");
        maxPitch = compoundTag.getFloat("maxPitch");
        minVolume = compoundTag.getFloat("minVolume");
        maxVolume = compoundTag.getFloat("maxVolume");
        particleEffect = NetworkedParticleEffectType.CODEC.parse(NbtOps.INSTANCE, compoundTag.get("particleEffect")).result().orElse(null);
        particleColor = MalumNetworkedParticleEffectColorData.CODEC.parse(NbtOps.INSTANCE, compoundTag.get("particleColor")).result().orElse(null);
    }
}