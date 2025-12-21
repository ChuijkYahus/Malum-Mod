package com.sammy.malum.common.entity.cultist.cardinal;

import com.sammy.malum.common.entity.cultist.*;
import com.sammy.malum.registry.common.MalumDamageTypes;
import com.sammy.malum.registry.common.MalumParticleEffectTypes;
import com.sammy.malum.registry.common.entity.MalumEntities;
import com.sammy.malum.visual_effects.networked.cultist.CardinalFireRetaliationBlastParticleEffect;
import com.sammy.malum.visual_effects.networked.cultist.CardinalTriggerDetonationParticleEffect;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
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

public class CardinalCultist extends CultistMonster implements IAltarBlessingRecipient {

    public static final float RETALIATION_BLAST_RADIUS = 4f;
    public static final int RETALIATION_BLAST_COOLDOWN = 80;

    public static final int RANGED_ATTACK_INTERVAL = 80;
    public static final float RANGED_RADIUS = 16f;

    public static final byte LOB_EVENT = 11;
    public static final byte RETALIATION_BLAST_EVENT = 12;
    public static final byte DETONATE_EVENT = 13;

    public AnimationState lobAnimationState = new AnimationState();
    public AnimationState quickFireAnimationState = new AnimationState();
    public AnimationState detonateAnimationState = new AnimationState();

    public UUID entropyChargeID;
    public EntropyChargeProjectile entropyCharge;

    public int retaliationBlastCooldown;

    public CardinalCultist(Level level) {
        super(MalumEntities.CARDINAL.get(), level);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        if (entropyChargeID != null) {
            compound.putUUID("EntropyCharge", entropyChargeID);
        }
        compound.putInt("RetaliationBlastCooldown", retaliationBlastCooldown);

    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("EntropyCharge")) {
            entropyChargeID = compound.getUUID("EntropyCharge");
        }
        retaliationBlastCooldown = compound.getInt("RetaliationBlastCooldown");
    }

    @Override
    public void handleEntityEvent(byte id) {
        switch (id) {
            case LOB_EVENT -> lobAnimationState.start(tickCount);
            case RETALIATION_BLAST_EVENT -> quickFireAnimationState.start(tickCount);
            case DETONATE_EVENT -> detonateAnimationState.start(tickCount);
            default -> super.handleEntityEvent(id);
        }
    }

    @Override
    protected void registerGoals() {
        var targeting = new NearestAttackableTargetGoal<>(this, Player.class, true);

        var retaliationBlast = new CardinalRetaliationBlastGoal(this, 0.5f);
        var detonateAttack = new CardinalDetonateEntropyGoal(this, 1.25f, RANGED_RADIUS*2);
        var lobAttack = new CardinalLobEntropyGoal(this, 1.0f, RANGED_ATTACK_INTERVAL, RANGED_RADIUS);

        var randomStroll = new WaterAvoidingRandomStrollGoal(this, 0.8f);
        var lookAtPlayer = new LookAtPlayerGoal(this, Player.class, 24.0F);
        var randomLookAround = new RandomLookAroundGoal(this);

        targetSelector.addGoal(0, targeting);

        goalSelector.addGoal(0, retaliationBlast);
        goalSelector.addGoal(1, detonateAttack);
        goalSelector.addGoal(2, lobAttack);
        goalSelector.addGoal(3, randomStroll);
        goalSelector.addGoal(4, lookAtPlayer);
        goalSelector.addGoal(5, randomLookAround);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 80.0)
                .add(Attributes.FOLLOW_RANGE, 35.0)
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(LodestoneAttributes.MAGIC_DAMAGE, 4.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5f)
                .add(LodestoneAttributes.MAGIC_RESISTANCE, 1.5f)
                .add(Attributes.ARMOR, 10.0)
                .add(Attributes.STEP_HEIGHT, 1);
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();

        if (retaliationBlastCooldown > 0) {
            retaliationBlastCooldown--;
        }
        if (level() instanceof ServerLevel level) {
            if (entropyChargeID != null) {
                entropyCharge = level.getEntity(entropyChargeID) instanceof EntropyChargeProjectile instance ? instance : null;
            }
            if (entropyCharge != null && entropyCharge.isAddedToLevel() && !entropyCharge.isFadingAway()) {
                return;
            }
            entropyChargeID = null;
            entropyCharge = null;
        }
    }

    @Override
    public boolean canReceiveAltarBuff() {
        return false;
    }

    @Override
    public void receiveAltarBuff() {
    }

    public boolean canTriggerRetaliationBlast() {
        return retaliationBlastCooldown == 0;
    }

    public boolean isWithinRetaliationBlastRadius() {
        var target = getTarget();
        if (target != null) {
            float distanceToTarget = distanceTo(target);
            return (distanceToTarget < RETALIATION_BLAST_RADIUS);
        }
        return false;
    }

    public void triggerRetaliationBlast(ServerLevel level) {
        var pos = getRetaliationBlastPos();
        float magicDamage = (float) this.getAttributeValue(LodestoneAttributes.MAGIC_DAMAGE) * 0.25f;
        float radius = RETALIATION_BLAST_RADIUS;
        var area = new AABB(pos.subtract(radius, radius, radius), pos.add(radius, radius, radius));
        var targets = level().getEntities(this, area, t -> !(t instanceof CultistMonster) && t.isAlive() && hasLineOfSight(t));

        var damagesource = DamageTypeHelper.create(level(), MalumDamageTypes.CULTIST_MAGIC);
        float knockbackStrength = 1.5f;
        Vec3 entropyChargeDirection = null;
        if (entropyCharge != null) {
            knockbackStrength += distanceTo(entropyCharge) * 0.1f;
            entropyChargeDirection = entropyCharge.getEyePosition().subtract(getEyePosition()).normalize();
        }
        List<Vec3> directions = new ArrayList<>();
        for (Entity target : targets) {
            if (target.distanceTo(this) > RETALIATION_BLAST_RADIUS) {
                continue;
            }
            if (target instanceof LivingEntity) {
                target.invulnerableTime = 0;
                target.hurt(damagesource, magicDamage);
            }
            Vec3 knockback;

            if (entropyChargeDirection != null) {
                knockback = entropyChargeDirection;
            }
            else {
                double x = target.getX() - getX();
                double y = target.getY(0.5f) - getY(0.5f);
                double z = target.getZ() - getZ();
                knockback = new Vec3(x, y, z).normalize().scale(knockbackStrength);
            }
            if (knockback.y < 0.6f) {
                knockback = new Vec3(knockback.x, 0.6f, knockback.z);
            }
            target.setDeltaMovement(knockback);
            if (target instanceof ServerPlayer serverPlayer) {
                serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(serverPlayer));
            }
            directions.add(knockback);
        }
        retaliationBlastCooldown = RETALIATION_BLAST_COOLDOWN;
        Vec3 blastDirection = getRetaliationBlastParticleDirection(entropyChargeDirection, directions);
        MalumParticleEffectTypes.CARDINAL_FIRE_RETALIATION_BLAST
                .createEffect(pos)
                .customData(new CardinalFireRetaliationBlastParticleEffect.CardinalFireRetaliationBlastParticleData(getId(), blastDirection))
                .color(ColorParticleData.create(CultistBoltProjectile.CULTIST_RED, CultistBoltProjectile.CULTIST_CRIMSON))
                .spawn(level);
    }

    public void throwEntropyCharge(LivingEntity target) {
        var pos = getProjectileSpawnPos();
        var level = level();
        float magicDamage = (float) this.getAttributeValue(LodestoneAttributes.MAGIC_DAMAGE) * 2;
        double x = target.getX() - pos.x;
        double y = target.getY(0.25f) - pos.y;
        double z = target.getZ() - pos.z;
        double distance = Math.sqrt(x * x + z * z);
        float inaccuracy = (14 - level.getDifficulty().getId() * 4);

        var projectile = new EntropyChargeProjectile(level);
        projectile.setPos(pos);
        projectile.shoot(x, y + distance * 0.2f, z, 1.4F, inaccuracy);
        projectile.setDeltaMovement(projectile.getDeltaMovement().add(0, 0.4f, 0));
        projectile.setData(this, magicDamage, 0, true);
        projectile.setHomingTarget(target);
        level.addFreshEntity(projectile);
        entropyChargeID = projectile.getUUID();
    }

    public void triggerDetonation(ServerLevel level) {
        if (entropyCharge == null) {
            throw new IllegalArgumentException("Detonation Attack somehow commenced without a valid Entropy Charge");
        }
        entropyCharge.detonate(level);
        MalumParticleEffectTypes.CARDINAL_TRIGGER_DETONATION
                .createEffect(getRetaliationBlastPos())
                .customData(new CardinalTriggerDetonationParticleEffect.CardinalTriggerDetonationParticleData(getId(), entropyCharge.getId()))
                .color(ColorParticleData.create(CultistBoltProjectile.CULTIST_RED, CultistBoltProjectile.CULTIST_CRIMSON))
                .spawn(level);
    }

    private @NotNull Vec3 getRetaliationBlastParticleDirection(Vec3 entropyChargeDirection, List<Vec3> directions) {
        Vec3 blastDirection;

        if (entropyChargeDirection != null) {
            blastDirection = entropyChargeDirection;
        }
        else if (directions.isEmpty()) {
            blastDirection = getLookAngle();
        }
        else {
            double x = 0, y = 0, z = 0;
            for (Vec3 direction : directions) {
                x += direction.x;
                y += direction.y;
                z += direction.z;
            }
            x /= directions.size();
            y /= directions.size();
            z /= directions.size();
            blastDirection = new Vec3(x, y, z);
        }
        return blastDirection;
    }

    public Vec3 getProjectileSpawnPos() {
        float sideYaw = yBodyRot - 90F;
        float forwardsYaw = yBodyRot - 180F;
        float sideX = Mth.sin(-sideYaw * (float) (Math.PI / 180.0) - (float) Math.PI);
        float sideZ = Mth.cos(-sideYaw * (float) (Math.PI / 180.0) - (float) Math.PI);
        float x = Mth.sin(-forwardsYaw * (float) (Math.PI / 180.0) - (float) Math.PI);
        float z = Mth.cos(-forwardsYaw * (float) (Math.PI / 180.0) - (float) Math.PI);

        float side = 0.8f;
        float forward = 0.4f;
        float up = 1.4f;
        return position().add(x * forward + sideX * side, up * getCultistScaleMultiplier(), z * forward + sideZ * side);
    }

    public Vec3 getRetaliationBlastPos() {
        return getRetaliationBlastPos(-1);
    }
    public Vec3 getRetaliationBlastPos(float partialTicks) {
        boolean hasDelta = partialTicks == -1;
        float rotation = hasDelta ? getPreciseBodyRotation(partialTicks) : yBodyRot;

        float sideYaw = rotation + 90F;
        float forwardsYaw = rotation - 180F;
        float sideX = Mth.sin(-sideYaw * (float) (Math.PI / 180.0) - (float) Math.PI);
        float sideZ = Mth.cos(-sideYaw * (float) (Math.PI / 180.0) - (float) Math.PI);
        float x = Mth.sin(-forwardsYaw * (float) (Math.PI / 180.0) - (float) Math.PI);
        float z = Mth.cos(-forwardsYaw * (float) (Math.PI / 180.0) - (float) Math.PI);

        float side = 0.8f;
        float forward = 0.6f;
        float up = 1.2f;
        Vec3 base = hasDelta ? position() : getPosition(partialTicks);
        return base.add(x * forward + sideX * side, up * getCultistScaleMultiplier(), z * forward + sideZ * side);
    }
}