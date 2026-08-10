package com.sammy.malum.common.entity.mob.cultist.altar;

import com.sammy.malum.common.entity.mob.cultist.ICherubFriend;
import com.sammy.malum.common.entity.mob.cultist.altar.goal.AltarBestowBlessingGoal;
import com.sammy.malum.common.entity.mob.cultist.altar.goal.AltarMeleeAttackGoal;
import com.sammy.malum.common.entity.mob.cultist.altar.goal.AltarRangedAttackGoal;
import com.sammy.malum.common.entity.mob.cultist.altar.goal.AltarRetreatGoal;
import com.sammy.malum.common.entity.mob.cultist.altar.projectile.CultistBlessingProjectile;
import com.sammy.malum.common.entity.mob.cultist.altar.projectile.CursedBoltProjectile;
import com.sammy.malum.common.entity.mob.cultist.CultistMonster;
import com.sammy.malum.common.entity.mob.cultist.IAltarBlessingRecipient;
import com.sammy.malum.registry.common.MalumParticleEffectTypes;
import com.sammy.malum.registry.common.entity.*;
import com.sammy.malum.registry.common.sound.*;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import team.lodestar.lodestone.modules.core.easing.Easing;
import team.lodestar.lodestone.modules.rendering.particle.standard.data.color.ColorParticleData;
import team.lodestar.wayward_attributes.core.registry.WaywardAttributeTypes;

public class AltarCultist extends CultistMonster implements ICherubFriend {

    private static final EntityDataAccessor<Integer> HEAD_TILT = SynchedEntityData.defineId(AltarCultist.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> CANDLE_ROTATION = SynchedEntityData.defineId(AltarCultist.class, EntityDataSerializers.FLOAT);

    private static final EntityDataAccessor<Integer> SPELL_CHARGE_ID = SynchedEntityData.defineId(AltarCultist.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> SPELL_CHARGE_DELTA = SynchedEntityData.defineId(AltarCultist.class, EntityDataSerializers.FLOAT);

    public static final int HEAD_TILT_ANIMATION_DURATION = 16;

    public static final float MELEE_CHASE_RADIUS = 4f;
    public static final int MELEE_COOLDOWN = 120;

    public static final float RETREAT_RADIUS = 8f;
    public static final int RETREAT_DELAY = 10;
    public static final int RETREAT_DURATION = 120;

    public static final float RANGED_ATTACK_RADIUS = 16f;
    public static final int RANGED_ATTACK_INTERVAL = 60;

    public static final float BLESSING_SEARCH_RADIUS = 24f;
    public static final float BLESSING_CHARGE_RADIUS = 12f;
    public static final int BLESSING_CHARGE_DURATION = 60;
    public static final float BLESSING_HEAL_PERCENTAGE = 0.25f;
    public static final float BLESSING_HEALTH_THRESHOLD = 0.5f;

    public long mostRecentMelee;

    public int headTiltStart;
    public int headTiltEnd;
    public int headTiltDuration;
    public float headTilt;
    public float oHeadTilt;
    public int headTiltTimer = -1;

    public AltarCultist(Level level) {
        super(MalumCultistEntityTypes.ALTAR.get(), MalumCultistSoundEvents.ALTAR, level);
    }

    @Override
    protected void registerGoals() {
        var playerTarget = new NearestAttackableTargetGoal<>(this, Player.class, true);

        var retreat = new AltarRetreatGoal(this, 3f);
        var bestowBlessing = new AltarBestowBlessingGoal(this, 1.5f);
        var meleeAttack = new AltarMeleeAttackGoal(this, 1.25f);
        var rangedAttack = new AltarRangedAttackGoal(this, 1.0f);

        var randomStroll = new WaterAvoidingRandomStrollGoal(this, 0.5f);
        var lookAtPlayer = new LookAtPlayerGoal(this, Player.class, 24.0F);
        var randomLookAround = new RandomLookAroundGoal(this);

        targetSelector.addGoal(0, playerTarget);

        goalSelector.addGoal(0, retreat);
        goalSelector.addGoal(1, bestowBlessing);
        goalSelector.addGoal(2, meleeAttack);
        goalSelector.addGoal(3, rangedAttack);
        goalSelector.addGoal(4, randomStroll);
        goalSelector.addGoal(5, lookAtPlayer);
        goalSelector.addGoal(6, randomLookAround);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 24.0)
                .add(Attributes.FOLLOW_RANGE, 35.0)
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(WaywardAttributeTypes.MAGIC_DAMAGE, 4.0)
                .add(WaywardAttributeTypes.MAGIC_RESISTANCE, 0.5)
                .add(Attributes.ARMOR, 8.0)
                .add(Attributes.STEP_HEIGHT, 1);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(HEAD_TILT, 0);
        builder.define(CANDLE_ROTATION, 0f);
    }

    @Override
    public void onSyncedDataUpdated(@NotNull EntityDataAccessor<?> key) {
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

        compound.putFloat("CandleRotation", getCandleRotation());

        compound.putLong("MostRecentMelee", mostRecentMelee);


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

        mostRecentMelee = compound.getLong("MostRecentMelee");

        headTiltStart = compound.getInt("headTiltStart");
        headTiltEnd = compound.getInt("headTiltEnd");
        headTiltDuration = compound.getInt("headTiltDuration");
        headTilt = compound.getFloat("headTilt");
        oHeadTilt = compound.getFloat("oHeadTilt");
        headTiltTimer = compound.getInt("headTiltTimer");
    }

    @Override
    public int getCherubCapacity() {
        return 3;
    }

    @Override
    public CherubPriority getCherubPriority() {
        return CherubPriority.HIGH;
    }

    @Override
    public Vec3 getCherubHoverOffset(int cherub) {
        float delta = ((tickCount + cherub * 100) % 300) / 300f;
        float angle = delta * 6.28f;
        float offset = getBbWidth()*1.25f;
        float x = Mth.sin(angle) * offset;
        float y = getBbHeight() + 0.25f + 0.5f * (cherub+1);
        float z = Mth.cos(angle) * offset;
        return new Vec3(x, y, z);
    }

    @Override
    public void tick() {
        if (!isNoAi()) {
            updateHeadTilt();
        }
        super.tick();
    }

    public void updateHeadTilt() {
        int duration = Math.max(1, headTiltDuration) * HEAD_TILT_ANIMATION_DURATION;
        if (headTiltTimer == -1) {
            if (!level().isClientSide) {
                if (level().getGameTime() % duration * 2 == 0) {
                    int rotation = Easing.SINE_IN_OUT.asWeighedRandom(random, 1, 4) * (random.nextBoolean() ? 1 : -1);
                    entityData.set(HEAD_TILT, entityData.get(HEAD_TILT) + rotation);
                }
            }
            return;
        }
        if (headTiltTimer < duration) {
            headTiltTimer++;
            float delta = headTiltTimer / (float) duration;
            float eased = Easing.BACK_IN_OUT.lerp(delta, 0f, 1f);
            oHeadTilt = headTilt;
            headTilt = Mth.lerp(eased, headTiltStart, headTiltEnd);
            if (headTiltTimer == duration) {
                headTiltTimer = -1;
            }
        }
    }

    @Override
    public boolean doHurtTarget(@NotNull Entity target) {
        if (super.doHurtTarget(target)) {
            playSound(MalumCultistSoundEvents.ALTAR_MELEE_ATTACK.get());
            mostRecentMelee = level().getGameTime();
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
        var pos = getProjectileSpawnPos();
        var level = level();
        float magicDamage = (float) this.getAttributeValue(WaywardAttributeTypes.MAGIC_DAMAGE);
        double x = target.getX() - pos.x;
        double y = target.getY(0.5f) - pos.y;
        double z = target.getZ() - pos.z;
        float inaccuracy = (14 - level.getDifficulty().getId() * 4);

        int amount = isBlessing ? 1 : 2;
        for (int i = 0; i < amount; i++) {
            int delay = i * 6;
            var projectile = isBlessing ? new CultistBlessingProjectile(level) : new CursedBoltProjectile(level);
            projectile.setPos(pos);
            projectile.shoot(x, y + 0.4f, z, 1.3F, inaccuracy);
            projectile.setDeltaMovement(projectile.getDeltaMovement().add(0, 0.2f, 0));
            projectile.setData(this, magicDamage, delay, true);
            level.addFreshEntity(projectile);
        }
        if (level instanceof ServerLevel serverLevel) {
            var color = isBlessing
                    ? ColorParticleData.create(CultistBlessingProjectile.CULTIST_PINK, CultistBlessingProjectile.CULTIST_PURPLE)
                    : ColorParticleData.create(CursedBoltProjectile.CULTIST_RED, CursedBoltProjectile.CULTIST_CRIMSON);
            MalumParticleEffectTypes.ALTAR_WEAVES_PROJECTILE
                    .createEffect(pos)
                    .color(color)
                    .spawn(serverLevel);
        }
    }

    public boolean canBestowBlessing(LivingEntity target) {
        if (target instanceof IAltarBlessingRecipient recipient) {
            float healthDelta = target.getHealth() / target.getMaxHealth();
            return recipient.canReceiveAltarBuff() && healthDelta <= BLESSING_HEALTH_THRESHOLD;
        }
        return false;
    }

    public void applyBlessing(ServerLevel level, LivingEntity target) {
        float recoveredHealth = target.getMaxHealth()*AltarCultist.BLESSING_HEAL_PERCENTAGE;
        target.heal(recoveredHealth);
        if (target instanceof IAltarBlessingRecipient recipient) {
            recipient.receiveAltarBuff();
        }
        if (target instanceof CultistMonster cultistMonster) {
            cultistMonster.setEmpowermentDuration(EMPOWERMENT_DURATION);
        }

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

    public boolean shouldChaseTarget() {
        return isTargetWithinRadius(MELEE_CHASE_RADIUS);
    }

    public boolean shouldRetreatFromTarget() {
        if (hasAttackedRecently(RETREAT_DELAY)) {
            return false;
        }
        if (!hasAttackedRecently(RETREAT_DELAY + RETREAT_DURATION)) {
            return false;
        }
        return isTargetWithinRadius(RETREAT_RADIUS);
    }

    public boolean hasAttackedRecently(int timeframe) {
        return level().getGameTime() - mostRecentMelee < timeframe;
    }

    public void setCandleRotation(float rotation) {
        entityData.set(CANDLE_ROTATION, rotation);
    }

    public float getCandleRotation() {
        return entityData.get(CANDLE_ROTATION);
    }
}