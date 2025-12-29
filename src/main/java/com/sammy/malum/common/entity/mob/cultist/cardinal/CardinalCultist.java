package com.sammy.malum.common.entity.mob.cultist.cardinal;

import com.sammy.malum.common.entity.mob.cultist.*;
import com.sammy.malum.common.entity.mob.cultist.altar.projectile.CursedBoltProjectile;
import com.sammy.malum.common.entity.mob.cultist.cardinal.goal.*;
import com.sammy.malum.common.entity.mob.cultist.cardinal.projectile.EntropyChargeProjectile;
import com.sammy.malum.registry.common.MalumDamageTypes;
import com.sammy.malum.registry.common.MalumParticleEffectTypes;
import com.sammy.malum.registry.common.entity.MalumEntityTypes;
import com.sammy.malum.visual_effects.networked.cultist.CardinalImmolationBlastParticleEffect;
import com.sammy.malum.visual_effects.networked.cultist.CardinalRetaliationBlastParticleEffect;
import com.sammy.malum.visual_effects.networked.cultist.CardinalDetonationBlastParticleEffect;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import team.lodestar.lodestone.helpers.DamageTypeHelper;
import team.lodestar.lodestone.registry.common.LodestoneAttributes;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

public class CardinalCultist extends CultistMonster implements IAltarBlessingRecipient {

    public static final float AVOID_TARGET_RADIUS = 5f;

    public static final float RETALIATION_BLAST_TRIGGER_RADIUS = 4f;
    public static final float RETALIATION_BLAST_DAMAGE_RADIUS = 3.75f;
    public static final int RETALIATION_BLAST_COOLDOWN = 100;
    public static final float RETALIATION_BLAST_DAMAGE = 0.25f;

    public static final float IMMOLATION_BLAST_CHANCE = 0.15f;
    public static final float IMMOLATION_BLAST_TRIGGER_RADIUS = 3f;
    public static final float IMMOLATION_BLAST_DAMAGE_RADIUS = 10f;
    public static final float IMMOLATION_BLAST_DAMAGE = 2.5f;

    public static final int ENTROPY_THROW_INTERVAL = 80;
    public static final float ENTROPY_THROW_RADIUS = 16f;
    public static final float ENTROPY_DETONATION_RADIUS = 24f;

    public static final byte THROW_ANIMATION = 11;
    public static final byte DETONATE_ANIMATION = 12;
    public static final byte RETALIATION_BLAST_ANIMATION = 13;
    public static final byte IMMOLATION_BLAST_ANIMATION = 14;

    public AnimationState idleAnimationState = new AnimationState();
    public AnimationState lobAnimationState = new AnimationState();
    public AnimationState detonateAnimationState = new AnimationState();
    public AnimationState retaliationBlastAnimationState = new AnimationState();
    public AnimationState immolationBlastAnimationState = new AnimationState();

    public UUID entropyChargeID;
    public EntropyChargeProjectile entropyCharge;

    public int retaliationBlastCooldown;
    public int immolationBlastProgress;
    public boolean useImmolationBlast;

    public CardinalCultist(Level level) {
        super(MalumEntityTypes.CARDINAL.get(), level);
        idleAnimationState.start(tickCount);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        var targeting = new NearestAttackableTargetGoal<>(this, Player.class, true);

        var immolationBlast = new CardinalImmolationBlastGoal(this);
        var detonateEntropy = new CardinalDetonateEntropyGoal(this, 1.25f);
        var throwEntropy = new CardinalThrowEntropyGoal(this, 1.0f);
        var retaliationBlast = new CardinalRetaliationBlastGoal(this, 0.5f);
        var avoidTarget = new CardinalAvoidTargetGoal(this, 0.75f);

        var randomStroll = new WaterAvoidingRandomStrollGoal(this, 0.5f);
        var lookAtPlayer = new LookAtPlayerGoal(this, Player.class, 24.0F);
        var randomLookAround = new RandomLookAroundGoal(this);

        targetSelector.addGoal(0, targeting);

        goalSelector.addGoal(0, immolationBlast);
        goalSelector.addGoal(1, detonateEntropy);
        goalSelector.addGoal(2, throwEntropy);
        goalSelector.addGoal(3, retaliationBlast);
        goalSelector.addGoal(4, avoidTarget);
        goalSelector.addGoal(5, randomStroll);
        goalSelector.addGoal(6, lookAtPlayer);
        goalSelector.addGoal(7, randomLookAround);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 80.0)
                .add(Attributes.FOLLOW_RANGE, 35.0)
                .add(Attributes.MOVEMENT_SPEED, 0.12)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(LodestoneAttributes.MAGIC_DAMAGE, 4.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5)
                .add(LodestoneAttributes.MAGIC_RESISTANCE, 1.5)
                .add(Attributes.ARMOR, 10.0)
                .add(Attributes.STEP_HEIGHT, 1);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        if (entropyChargeID != null) {
            compound.putUUID("EntropyCharge", entropyChargeID);
        }
        compound.putInt("RetaliationBlastCooldown", retaliationBlastCooldown);
        compound.putInt("ImmolationBlastProgress", immolationBlastProgress);
        compound.putBoolean("UseImmolationBlast", useImmolationBlast);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("EntropyCharge")) {
            entropyChargeID = compound.getUUID("EntropyCharge");
        }
        retaliationBlastCooldown = compound.getInt("RetaliationBlastCooldown");
        immolationBlastProgress = compound.getInt("ImmolationBlastProgress");
        useImmolationBlast = compound.getBoolean("UseImmolationBlast");
    }

    @Override
    public void handleEntityEvent(byte id) {
        switch (id) {
            case THROW_ANIMATION -> lobAnimationState.start(tickCount);
            case DETONATE_ANIMATION -> detonateAnimationState.start(tickCount);
            case RETALIATION_BLAST_ANIMATION -> retaliationBlastAnimationState.start(tickCount);
            case IMMOLATION_BLAST_ANIMATION -> immolationBlastAnimationState.start(tickCount);
            default -> super.handleEntityEvent(id);
        }
    }

    @Override
    public boolean canDisableShield() {
        return true;
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
        if (retaliationBlastCooldown > 0) {
            retaliationBlastCooldown--;
        }
        if (level() instanceof ServerLevel level) {
            trackEntropyCharge(level);
        }
    }

    public void trackEntropyCharge(ServerLevel level) {
        if (entropyChargeID != null) {
            entropyCharge = level.getEntity(entropyChargeID) instanceof EntropyChargeProjectile instance ? instance : null;
        }
        if (entropyCharge != null && entropyCharge.isAddedToLevel() && !entropyCharge.isFadingAway()) {
            return;
        }
        entropyChargeID = null;
        entropyCharge = null;
    }

    public boolean canTriggerRetaliationBlast() {
        return !useImmolationBlast && retaliationBlastCooldown == 0 && isTargetWithinRadius(RETALIATION_BLAST_TRIGGER_RADIUS);
    }

    public boolean canTriggerImmolationBlast() {
        return useImmolationBlast && isTargetWithinRadius(IMMOLATION_BLAST_TRIGGER_RADIUS);
    }

    public boolean shouldAvoidTarget() {
        var target = getTarget();
        if (target != null) {
            float distanceToTarget = distanceTo(target);
            return distanceToTarget < AVOID_TARGET_RADIUS;
        }
        return false;
    }

    public void throwEntropyCharge(LivingEntity target) {
        var pos = getEntropyChargePos();
        var level = level();
        float magicDamage = (float) this.getAttributeValue(LodestoneAttributes.MAGIC_DAMAGE) * 2;
        double x = target.getX() - pos.x;
        double y = target.getY(0.25f) - pos.y;
        double z = target.getZ() - pos.z;
        double distance = Math.sqrt(x * x + z * z);
        float inaccuracy = (14 - level.getDifficulty().getId() * 4);
        float velocity = (float) (0.5f + distance * 0.06f);

        var projectile = new EntropyChargeProjectile(level);
        projectile.setPos(pos);
        projectile.shoot(x, y + distance * 0.2f, z, velocity, inaccuracy);
        projectile.setDeltaMovement(projectile.getDeltaMovement().add(0, 0.4f, 0));
        projectile.setData(this, magicDamage, 0, true);
        projectile.setHomingTarget(target);
        level.addFreshEntity(projectile);
        entropyChargeID = projectile.getUUID();
    }

    public void triggerDetonation(ServerLevel level, EntropyChargeProjectile target) {
        target.detonate(level);
        MalumParticleEffectTypes.CARDINAL_DETONATION_BLAST
                .createEffect(getRetaliationBlastPos())
                .customData(new CardinalDetonationBlastParticleEffect.CardinalDetonationBlastParticleData(getId(), target.getId()))
                .color(ColorParticleData.create(CursedBoltProjectile.CULTIST_RED, CursedBoltProjectile.CULTIST_CRIMSON))
                .spawn(level);
    }

    public void triggerRetaliationBlast(ServerLevel level) {
        var pos = getRetaliationBlastPos();
        float magicDamage = (float) getAttributeValue(LodestoneAttributes.MAGIC_DAMAGE) * RETALIATION_BLAST_DAMAGE;
        float radius = RETALIATION_BLAST_DAMAGE_RADIUS;
        var area = new AABB(pos.subtract(radius, radius, radius), pos.add(radius, radius, radius));
        var targets = level().getEntities(this, area, t -> !(t instanceof CultistMonster) && t.isAlive() && hasLineOfSight(t));
        var damagesource = DamageTypeHelper.create(level(), MalumDamageTypes.CULTIST_MAGIC, this);

        float immolationBlastChance = immolationBlastProgress * IMMOLATION_BLAST_CHANCE;
        if (random.nextFloat() < immolationBlastChance) {
            useImmolationBlast = true;
        }
        retaliationBlastCooldown = RETALIATION_BLAST_COOLDOWN;
        immolationBlastProgress++;

        record KnockbackInfo(Function<Entity, Vec3> direction, float strength) {
        }
        KnockbackInfo info;
        if (useImmolationBlast) {
            info = new KnockbackInfo(this::directionToTarget, -0.5f);
        } else if (entropyCharge != null) {
            info = new KnockbackInfo(t -> directionToTarget(entropyCharge), 1.2f + distanceTo(entropyCharge) * 0.1f);
        } else {
            info = new KnockbackInfo(this::directionToTarget, 1.5f);
        }
        var directions = new ArrayList<Vec3>();
        for (Entity target : targets) {
            if (target.distanceTo(this) > radius) {
                continue;
            }
            float knockbackStrength = info.strength;
            if (target instanceof LivingEntity living) {
                target.invulnerableTime = 0;
                if (!target.hurt(damagesource, magicDamage)) {
                    continue;
                }
                living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 1));
                knockbackStrength *= (float) (1f - living.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
            }
            Vec3 knockbackDirection = info.direction().apply(target);
            Vec3 knockback = knockbackDirection.scale(knockbackStrength);
            if (knockback.y < 0.6f) {
                knockback = knockback.add(0, 0.6f, 0);
            }
            target.setDeltaMovement(knockback);
            if (target instanceof ServerPlayer serverPlayer) {
                serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(serverPlayer));
            }
            directions.add(knockbackDirection);
        }
        Vec3 blastDirection = getRetaliationBlastParticleDirection(directions);
        MalumParticleEffectTypes.CARDINAL_RETALIATION_BLAST
                .createEffect(pos)
                .customData(new CardinalRetaliationBlastParticleEffect.CardinalRetaliationBlastParticleData(getId(), blastDirection))
                .color(ColorParticleData.create(CursedBoltProjectile.CULTIST_RED, CursedBoltProjectile.CULTIST_CRIMSON))
                .spawn(level);
    }

    public void triggerImmolationBlast(ServerLevel level) {
        var pos = getImmolationBlastPos();
        float magicDamage = (float) getAttributeValue(LodestoneAttributes.MAGIC_DAMAGE) * IMMOLATION_BLAST_DAMAGE;
        float radius = IMMOLATION_BLAST_DAMAGE_RADIUS;
        var area = new AABB(pos.subtract(radius, radius, radius), pos.add(radius, radius, radius));
        var targets = level().getEntities(this, area, t -> t.isAlive() && hasLineOfSight(t));
        var damagesource = DamageTypeHelper.create(level(), MalumDamageTypes.CULTIST_MAGIC, this);
        for (Entity target : targets) {
            if (target.distanceTo(this) > radius) {
                continue;
            }
            if (target instanceof LivingEntity) {
                float damageDealt = magicDamage;
                if (target instanceof CultistMonster) {
                    damageDealt *= 0.5f;
                }
                target.invulnerableTime = 0;
                if (!target.hurt(damagesource, damageDealt)) {
                    continue;
                }
            }
            if (target instanceof ServerPlayer serverPlayer) {
                serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(serverPlayer));
            }
        }
        retaliationBlastCooldown = RETALIATION_BLAST_COOLDOWN;
        immolationBlastProgress = 0;
        useImmolationBlast = false;
        hurt(damagesource, magicDamage);
        MalumParticleEffectTypes.CARDINAL_IMMOLATION_BLAST
                .createEffect(pos)
                .customData(new CardinalImmolationBlastParticleEffect.CardinalImmolationBlastParticleData(getId()))
                .color(ColorParticleData.create(CursedBoltProjectile.CULTIST_RED, CursedBoltProjectile.CULTIST_CRIMSON))
                .spawn(level);
    }

    private @NotNull Vec3 getRetaliationBlastParticleDirection(List<Vec3> directions) {
        if (entropyCharge != null) {
            return directionToTarget(entropyCharge);
        } else if (directions.isEmpty()) {
            return getLookAngle();
        }
        double x = 0, y = 0, z = 0;
        for (Vec3 direction : directions) {
            x += direction.x;
            y += direction.y;
            z += direction.z;
        }
        x /= directions.size();
        y /= directions.size();
        z /= directions.size();
        return new Vec3(x, y, z);
    }

    public Vec3 getEntropyChargePos() {
        return getHandPosition(-0.8f, 0.4f, 1.4f, -1);
    }

    public Vec3 getRetaliationBlastPos() {
        return getRetaliationBlastPos(-1);
    }

    public Vec3 getRetaliationBlastPos(float partialTicks) {
        return getHandPosition(0.8f, 0.6f, 1.2f, partialTicks);
    }

    public Vec3 getImmolationBlastPos() {
        return getHandPosition(0.4f, 0.9f, 0.25f, -1);
    }

    public Vec3 getHandPosition(float side, float forward, float up, float partialTicks) {
        boolean hasDelta = partialTicks == -1;
        float rotation = hasDelta ? getPreciseBodyRotation(partialTicks) : yBodyRot;

        float sideYaw = rotation + 90F;
        float forwardsYaw = rotation - 180F;
        float sideX = Mth.sin(-sideYaw * Mth.DEG_TO_RAD - (float) Math.PI);
        float sideZ = Mth.cos(-sideYaw * Mth.DEG_TO_RAD - (float) Math.PI);
        float x = Mth.sin(-forwardsYaw * Mth.DEG_TO_RAD - (float) Math.PI);
        float z = Mth.cos(-forwardsYaw * Mth.DEG_TO_RAD - (float) Math.PI);

        Vec3 base = hasDelta ? position() : getPosition(partialTicks);
        return base.add(x * forward + sideX * side, up * getCultistScaleMultiplier(), z * forward + sideZ * side);
    }
}