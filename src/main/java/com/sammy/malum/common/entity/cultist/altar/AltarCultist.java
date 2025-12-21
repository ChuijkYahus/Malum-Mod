package com.sammy.malum.common.entity.cultist.altar;

import com.sammy.malum.common.entity.cultist.CultistBlessingProjectile;
import com.sammy.malum.common.entity.cultist.CultistBoltProjectile;
import com.sammy.malum.common.entity.cultist.CultistMonster;
import com.sammy.malum.common.entity.cultist.IAltarBlessingRecipient;
import com.sammy.malum.registry.common.MalumParticleEffectTypes;
import com.sammy.malum.registry.common.entity.MalumEntities;
import com.sammy.malum.visual_effects.networked.cultist.AltarBlessTargetParticleEffect;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import team.lodestar.lodestone.helpers.RandomHelper;
import team.lodestar.lodestone.registry.common.LodestoneAttributes;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;

import java.util.UUID;

public class AltarCultist extends CultistMonster {

    private static final EntityDataAccessor<Boolean> IS_SQUISHED = SynchedEntityData.defineId(AltarCultist.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Integer> HEAD_TILT = SynchedEntityData.defineId(AltarCultist.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Float> CANDLE_ROTATION = SynchedEntityData.defineId(AltarCultist.class, EntityDataSerializers.FLOAT);

    public static final int SQUISH_ANIMATION_DURATION = 8;
    public static final int HEAD_TILT_ANIMATION_DURATION = 16;

    public static final float MELEE_RADIUS = 4f;
    public static final int MELEE_COOLDOWN = 120;
    public static final float RETREAT_RADIUS = 8f;
    public static final int RETREAT_DURATION = 40;
    public static final float RANGED_RADIUS = 16f;
    public static final int RANGED_ATTACK_INTERVAL = 80;
    public static final int BLESSING_CHARGE_DURATION = 60;
    public static final float BLESSING_RADIUS = 8f;

    public int meleeCooldown;
    public UUID meleeVictim;

    public int retreatCooldown;

    public int squish;
    public int oSquish;

    public int headTiltStart;
    public int headTiltEnd;
    public int headTiltDuration;
    public float headTilt;
    public float oHeadTilt;
    public int headTiltTimer = -1;

    public AltarCultist(Level level) {
        super(MalumEntities.ALTAR.get(), level);
    }

    @Override
    protected void registerGoals() {
        var playerTarget = new NearestAttackableTargetGoal<>(this, Player.class, true);

        var retreat = new AltarRetreatGoal(this, 1.5f, RETREAT_RADIUS);
        var bestowBlessing = new AltarBestowBlessingGoal(this, 0.5f, BLESSING_CHARGE_DURATION, BLESSING_RADIUS);
        var rangedAttack = new AltarRangedAttackGoal(this, 1.0f, RANGED_ATTACK_INTERVAL, RANGED_RADIUS);
        var meleeAttack = new AltarMeleeAttackGoal(this, 1.25f);

        var randomStroll = new WaterAvoidingRandomStrollGoal(this, 0.8f);
        var lookAtPlayer = new LookAtPlayerGoal(this, Player.class, 24.0F);
        var randomLookAround = new RandomLookAroundGoal(this);

        targetSelector.addGoal(0, playerTarget);

        goalSelector.addGoal(0, retreat);
        goalSelector.addGoal(1, bestowBlessing);
        goalSelector.addGoal(2, rangedAttack);
        goalSelector.addGoal(3, meleeAttack);
        goalSelector.addGoal(4, randomStroll);
        goalSelector.addGoal(5, lookAtPlayer);
        goalSelector.addGoal(6, randomLookAround);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(IS_SQUISHED, false);
        builder.define(HEAD_TILT, 0);
        builder.define(CANDLE_ROTATION, 0f);
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        if (HEAD_TILT.equals(key)) {
            headTiltStart = Math.round(headTilt);
            headTiltEnd = getEntityData().get(HEAD_TILT);
            headTiltDuration = Mth.abs(headTiltEnd - headTiltStart);
            headTiltTimer = 0;
        }
        super.onSyncedDataUpdated(key);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);

        compound.putFloat("candleRotation", getCandleRotation());

        compound.putInt("meleeCooldown", meleeCooldown);
        if (meleeVictim != null) {
            compound.putUUID("meleeVictim", meleeVictim);
        }
        compound.putInt("retreatCooldown", retreatCooldown);

        compound.putInt("squish", squish);
        compound.putInt("oSquish", oSquish);

        compound.putInt("headTiltStart", headTiltStart);
        compound.putInt("headTiltEnd", headTiltEnd);
        compound.putInt("headTiltDuration", headTiltDuration);
        compound.putFloat("headTilt", headTilt);
        compound.putFloat("oHeadTilt", oHeadTilt);
        compound.putInt("headTiltTimer", headTiltTimer);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);

        setCandleRotation(compound.getFloat("candleRotation"));

        meleeCooldown = compound.getInt("meleeCooldown");
        if (compound.hasUUID("meleeVictim")) {
            meleeVictim = compound.getUUID("meleeVictim");
        }
        retreatCooldown = compound.getInt("retreatCooldown");

        squish = compound.getInt("squish");
        oSquish = compound.getInt("oSquish");

        headTiltStart = compound.getInt("headTiltStart");
        headTiltEnd = compound.getInt("headTiltEnd");
        headTiltDuration = compound.getInt("headTiltDuration");
        headTilt = compound.getFloat("headTilt");
        oHeadTilt = compound.getFloat("oHeadTilt");
        headTiltTimer = compound.getInt("headTiltTimer");
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 24.0)
                .add(Attributes.FOLLOW_RANGE, 35.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(LodestoneAttributes.MAGIC_DAMAGE, 4.0)
                .add(LodestoneAttributes.MAGIC_RESISTANCE, 0.5f)
                .add(Attributes.ARMOR, 8.0)
                .add(Attributes.STEP_HEIGHT, 1);
    }

    @Override
    public void tick() {
        if (!isNoAi()) {
            updateSquish();
            updateHeadTilt();
            updateMeleeState();
            updateRetreatState();
        }
        super.tick();
    }

    public void updateSquish() {
        boolean isSquished = entityData.get(IS_SQUISHED);
        if (isSquished) {
            oSquish = squish;
            squish++;
            if (squish >= SQUISH_ANIMATION_DURATION) {
                oSquish = 0;
                squish = 0;
                entityData.set(IS_SQUISHED, false);
            }
        } else {
            oSquish = 0;
            squish = 0;
        }
    }

    public void updateHeadTilt() {
        int duration = Math.max(1, headTiltDuration) * HEAD_TILT_ANIMATION_DURATION;
        if (headTiltTimer == -1) {
            if (!level().isClientSide) {
                if (level().getGameTime() % duration * 2 == 0) {
                    int rotation = RandomHelper.randomBetween(random, Easing.QUAD_IN, 1, 4) * (random.nextBoolean() ? 1 : -1);
                    entityData.set(HEAD_TILT, entityData.get(HEAD_TILT) + rotation);
                }
            }
            return;
        }
        if (headTiltTimer < duration) {
            headTiltTimer++;
            float delta = headTiltTimer / (float) duration;
            delta = Easing.BACK_IN_OUT.clamped(delta, 0, 1);
            oHeadTilt = headTilt;
            headTilt = Mth.lerp(delta, headTiltStart, headTiltEnd);
            if (headTiltTimer == duration) {
                headTiltTimer = -1;
            }
        }
    }

    public void updateMeleeState() {
        if (meleeCooldown > 0) {
            meleeCooldown--;
            if (meleeCooldown == 0) {
                meleeVictim = null;
            }
        }
    }

    public void updateRetreatState() {
        if (retreatCooldown > 0) {
            retreatCooldown--;
        }
    }


    @Override
    public boolean doHurtTarget(@NotNull Entity target) {
        if (super.doHurtTarget(target)) {
            meleeVictim = target.getUUID();
            meleeCooldown = MELEE_COOLDOWN;
            triggerSquishAnimation();
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        float rotation = random.nextFloat() * 6.28f;
        setCandleRotation(rotation);
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }

    public void performRangedAttack(LivingEntity target) {
        boolean isBlessing = target instanceof IAltarBlessingRecipient;
        triggerSquishAnimation();
        var pos = getProjectileSpawnPos();
        var level = level();
        float magicDamage = (float) this.getAttributeValue(LodestoneAttributes.MAGIC_DAMAGE);
        double x = target.getX() - pos.x;
        double y = target.getY(0.5f) - pos.y;
        double z = target.getZ() - pos.z;
        float inaccuracy = (14 - level.getDifficulty().getId() * 4);

        int amount = isBlessing ? 1 : 2;
        for (int i = 0; i < amount; i++) {
            int delay = i * 6;
            var projectile = isBlessing ? new CultistBlessingProjectile(level) : new CultistBoltProjectile(level);
            projectile.setPos(pos);
            projectile.shoot(x, y + 0.4f, z, 1.3F, inaccuracy);
            projectile.setDeltaMovement(projectile.getDeltaMovement().add(0, 0.2f, 0));
            projectile.setData(this, magicDamage, delay, true);
            level.addFreshEntity(projectile);
        }
        if (level instanceof ServerLevel serverLevel) {
            var color = isBlessing ? ColorParticleData.create(CultistBlessingProjectile.CULTIST_PINK, CultistBlessingProjectile.CULTIST_PURPLE)
                    : ColorParticleData.create(CultistBoltProjectile.CULTIST_RED, CultistBoltProjectile.CULTIST_CRIMSON);
            MalumParticleEffectTypes.ALTAR_WEAVES_PROJECTILE
                    .createEffect(pos)
                    .color(color)
                    .spawn(serverLevel);
        }
    }

    public void applyBlessing(ServerLevel level, LivingEntity target) {
        var position = target.position().add(0, target.getBbHeight() * 0.35f, 0);
        var color = ColorParticleData.create(CultistBlessingProjectile.CULTIST_PINK, CultistBlessingProjectile.CULTIST_PURPLE);
        MalumParticleEffectTypes.ALTAR_BESTOWS_BLESSING
                .createEffect(position)
                .customData(new AltarBlessTargetParticleEffect.AltarBlessTargetParticleData(target.getId()))
                .color(color)
                .spawn(level);
    }

    public Vec3 getProjectileSpawnPos() {
        return position().add(0, 1.5f * getCultistScaleMultiplier(), 0);
    }

    public boolean canEnterMeleeState() {
        return retreatCooldown == 0 && meleeCooldown == 0;
    }

    public boolean isWithinMeleeRadius() {
        var target = getTarget();
        if (target != null) {
            float distanceToTarget = distanceTo(target);
            return (distanceToTarget < MELEE_RADIUS);
        }
        return false;
    }

    public boolean isRetreating() {
        return retreatCooldown > 0;
    }

    public void setCandleRotation(float rotation) {
        entityData.set(CANDLE_ROTATION, rotation);
    }

    public float getCandleRotation() {
        return entityData.get(CANDLE_ROTATION);
    }

    public void triggerSquishAnimation() {
        entityData.set(IS_SQUISHED, true);
    }

    public boolean isSquished() {
        return entityData.get(IS_SQUISHED);
    }
}