package com.sammy.malum.common.entity;

import com.sammy.malum.common.item.curiosities.*;
import com.sammy.malum.common.worldevent.*;
import com.sammy.malum.registry.client.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.entity.*;
import com.sammy.malum.registry.common.item.*;
import com.sammy.malum.visual_effects.*;
import com.sammy.malum.visual_effects.networked.data.*;
import net.minecraft.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.network.chat.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.util.*;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.phys.*;
import net.neoforged.api.distmarker.*;
import org.jetbrains.annotations.*;
import team.lodestar.lodestone.handlers.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.particle.*;
import team.lodestar.lodestone.systems.particle.builder.*;
import team.lodestar.lodestone.systems.particle.data.*;
import team.lodestar.lodestone.systems.particle.data.color.*;
import team.lodestar.lodestone.systems.particle.data.spin.*;
import team.lodestar.lodestone.systems.particle.render_types.*;
import team.lodestar.lodestone.systems.particle.world.*;
import team.lodestar.lodestone.systems.particle.world.behaviors.*;
import team.lodestar.lodestone.systems.rendering.trail.*;

import java.util.*;
import java.util.function.*;

import static com.sammy.malum.common.item.curiosities.weapons.SunderingAnchorItem.*;

public class SunderingAnchorProjectileEntity extends ThrowableItemProjectile {

    public TrailPointBuilder trailPointBuilder = TrailPointBuilder.create(60);
    public TrailPointBuilder spinningTrailPointBuilder = TrailPointBuilder.create(8);
    public float spinOffset = (float) (random.nextFloat() * Math.PI * 2);
    protected float damage;
    protected float magicDamage;
    public int age;
    public int targetSelectionTimer;
    public int bounceCount;
    public int returnTimer;
    public int slot;

    private List<Entity> hitEntities = new ArrayList<>();
    private LivingEntity forcedTarget;

    public SunderingAnchorProjectileEntity(Level level) {
        super(EntityRegistry.SUNDERING_ANCHOR.get(), level);
        noPhysics = false;
    }

    public SunderingAnchorProjectileEntity(Level level, double pX, double pY, double pZ) {
        this(level);
        setPos(pX, pY, pZ);
        noPhysics = true;
    }

    public void setData(LivingEntity owner, float damage, float magicDamage, int slot) {
        setOwner(owner);
        this.damage = damage;
        this.magicDamage = magicDamage;
        this.slot = slot;
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.SUNDERING_ANCHOR.get();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        if (damage != 0) {
            compound.putFloat("damage", damage);
        }
        if (magicDamage != 0) {
            compound.putFloat("magicDamage", magicDamage);
        }
        if (age != 0) {
            compound.putInt("age", age);
        }
        if (targetSelectionTimer != 0) {
            compound.putInt("secondBounceDelay", targetSelectionTimer);
        }
        if (bounceCount != 0) {
            compound.putInt("bounceCount", bounceCount);
        }
        if (slot != 0) {
            compound.putInt("slot", slot);
        }
        if (returnTimer != 0) {
            compound.putInt("returnTimer", returnTimer);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        damage = compound.getFloat("damage");
        magicDamage = compound.getFloat("magicDamage");
        age = compound.getInt("age");
        targetSelectionTimer = compound.getInt("secondBounceDelay");
        bounceCount = compound.getInt("bounceCount");
        slot = compound.getInt("slot");
        returnTimer = compound.getInt("returnTimer");
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        freeTarget();
        bounce();
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        if (isReturning()) {
            return;
        }
        if (forcedTarget != null && forcedTarget.isAlive()) {
            if(position().distanceTo(forcedTarget.position()) < 2f) {
                return;
            }
        }
        super.onHitBlock(result);
        if (result.getDirection().getAxis().isVertical()) {
            setDeltaMovement(getDeltaMovement().multiply(1, -1, 1));
        } else if (result.getDirection().getAxis().equals(Direction.Axis.X)) {
            setDeltaMovement(getDeltaMovement().multiply(-1, 1, 1));
        } else if (result.getDirection().getAxis().equals(Direction.Axis.Z)) {
            setDeltaMovement(getDeltaMovement().multiply(1, 1, -1));
        }
        jumbleMovement(0.2f);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        if (level() instanceof ServerLevel level) {
            if (getOwner() instanceof LivingEntity owner) {
                Entity target = result.getEntity();
                hitEntities.add(target);
                target.invulnerableTime = 0;
                DamageSource source = DamageTypeHelper.create(level(), DamageTypeRegistry.VOODOO, this, owner);
                boolean success = target.hurt(source, magicDamage);
                if (success && target instanceof LivingEntity livingEntity) {
                    int slashCount = 6;
                    var physicalDamageType = DamageTypeRegistry.SUNDERING_ANCHOR_PHYSICAL_COMBO;
                    var magicDamageType = DamageTypeRegistry.SUNDERING_ANCHOR_MAGIC_COMBO;
                    int delay = 8;
                    float pitch = RandomHelper.randomBetween(level.getRandom(), 1.5f, 2f);
                    SoundHelper.playSound(this, SoundRegistry.SUNDERING_ANCHOR_SWING.get(), 2f, pitch);
                    applyHatred(livingEntity);
                    for (int j = 0; j < slashCount; j++) {
                        int comboDelay = delay + j;
                        WorldEventHandler.addWorldEvent(level,
                                new DelayedDamageWorldEvent(target)
                                        .setAttacker(owner)
                                        .setDamageData(physicalDamageType, damage/slashCount, magicDamageType, magicDamage/slashCount, comboDelay)
                                        .setImpactParticleEffect(ParticleEffectTypeRegistry.SUNDERING_ANCHOR_SWEEP, new ColorEffectData(getSunderingAnchorSpirit()))
                                        .setSound(SoundRegistry.SUNDERING_ANCHOR_PROJECTILE_SWING, 1.25f, 1.5f, 0.7f));
                    }

                    selectNearbyTarget(level);
                    jumbleMovement(0.8f);
                }
            }
        }
    }

    @Override
    protected boolean canHitEntity(Entity pTarget) {
        return false; //TODO: we're implementing custom entity hitting logic cause for whatever reason the motherfucker keeps getting stuck in some sorta loop unable to hit shit
//        if (pTarget.equals(getOwner())) {
//            return false;
//        }
//        if (pTarget instanceof SunderingAnchorProjectileEntity) {
//            return false;
//        }
//        if (hitEntities.contains(pTarget)) {
//            return false;
//        }
//        return super.canHitEntity(pTarget);
    }
    protected boolean canHitEntityStupidCopy(Entity pTarget) {
        if (pTarget.equals(getOwner())) {
            return false;
        }
        if (pTarget instanceof SunderingAnchorProjectileEntity) {
            return false;
        }
        if (hitEntities.contains(pTarget)) {
            return false;
        }
        return super.canHitEntity(pTarget);
    }

    @Override
    public void tick() {
        var deltaMovement = getDeltaMovement();
        super.tick();
        setDeltaMovement(getDeltaMovement().normalize().scale(deltaMovement.length()));

        age++;
        returnTimer++;
        if (level() instanceof ServerLevel level) {
            final AABB aabb = getBoundingBox().inflate(2);
            for (Entity target : level.getEntities(this, aabb, this::canHitEntityStupidCopy)) {
                onHit(new EntityHitResult(target));
            }
            if (age % 20 == 0) {
                freeTarget();
                selectNearbyTarget(level);
            }
            if (getOwner() instanceof LivingEntity owner) {
                if (isReturning()) {
                    var ownerPos = owner.position().add(0, owner.getBbHeight() * 0.6f, 0);
                    float velocityLimit = 3f;
                    var motion = getDeltaMovement();
                    double velocity = Mth.clamp(motion.length() * 1.25f, 0.5f, velocityLimit);
                    var returnMotion = ownerPos.subtract(position()).normalize().scale(velocity);
                    setDeltaMovement(motion.lerp(returnMotion, 0.3f));

                    if (isAlive() && distanceTo(owner) < 2.5f) {
                        SoundHelper.playSound(owner, SoundRegistry.SUNDERING_ANCHOR_CATCH.get(), 0.5f, RandomHelper.randomBetween(level().getRandom(), 1.5f, 2f));
                        if (owner instanceof ServerPlayer player) {
                            TemporarilyDisabledItem.enable(player, slot);
                            if (!player.isCreative()) {
                                int cooldown = 120;
                                player.getCooldowns().addCooldown(getItem().getItem(), cooldown);
                            }
                        }

                        remove(RemovalReason.DISCARDED);
                    }
                }
            }
            if (!isReturning()) {
                if (targetSelectionTimer > 0) {
                    targetSelectionTimer--;
                    if (targetSelectionTimer == 0) {
                        selectNearbyTarget(level);
                    }
                }
                homeIn(level);
            }
        }
        else {
            spawnParticles();
            Vec3 projectileDirection = getDeltaMovement().normalize();
            float yRot = ((float) (Mth.atan2(projectileDirection.x, projectileDirection.z) * (double) (180F / (float) Math.PI)));
            float yaw = (float) Math.toRadians(yRot);
            Vec3 left = new Vec3(-Math.cos(yaw), 0, Math.sin(yaw));
            Vec3 up = left.cross(projectileDirection);

            float offsetScale = Mth.clampedLerp(0.1f, 0.3f, getVisualEffectScalar());
            for (int i = 0; i < 2; i++) {
                float progress = (i + 1) * 0.5f;
                Vec3 position = getPosition(progress);
                float scalar = (age + progress) / 2f;
                double xOffset = Math.cos(spinOffset + scalar) * offsetScale;
                double zOffset = Math.sin(spinOffset + scalar) * offsetScale;
                trailPointBuilder.addTrailPoint(position);
                spinningTrailPointBuilder.addTrailPoint(position.add(left.scale(xOffset)).add(up.scale(zOffset)));
            }
            trailPointBuilder.tickTrailPoints();
            spinningTrailPointBuilder.tickTrailPoints();
        }
    }

    @Override
    public @NotNull SoundSource getSoundSource() {
        return getOwner() != null ? getOwner().getSoundSource() : SoundSource.PLAYERS;
    }

    @Override
    public void shootFromRotation(@NotNull Entity shooter, float rotationPitch, float rotationYaw, float pitchOffset, float velocity, float innacuracy) {
        float f = -Mth.sin(rotationYaw * ((float) Math.PI / 180F)) * Mth.cos(rotationPitch * ((float) Math.PI / 180F));
        float f1 = -Mth.sin((rotationPitch + pitchOffset) * ((float) Math.PI / 180F));
        float f2 = Mth.cos(rotationYaw * ((float) Math.PI / 180F)) * Mth.cos(rotationPitch * ((float) Math.PI / 180F));
        this.shoot(f, f1, f2, velocity, innacuracy);
    }

    public boolean isReturning() {
        return returnTimer > 80 || bounceCount >= 20;
    }

    public void jumbleMovement(float weight) {
        float randomRotationX = (float) (Math.random() * Math.PI * 2);
        float randomRotationY = (float) (Math.random() * Math.PI * 2);
        float randomRotationZ = (float) (Math.random() * Math.PI * 2);
        Vec3 motion = getDeltaMovement();
        Vec3 newMotion = motion
                .xRot(randomRotationX)
                .yRot(randomRotationY);
        double cosZ = Math.cos(randomRotationZ);
        double sinZ = Math.sin(randomRotationZ);
        double x = newMotion.x * cosZ - newMotion.y * sinZ;
        double y = newMotion.x * sinZ + newMotion.y * cosZ;
        double z = newMotion.z;
        final Vec3 lerp = motion.lerp(new Vec3(x, y, z), weight);
        setDeltaMovement(lerp.normalize().scale(motion.length()));
    }

    public void bounce() {
        bounceCount++;
        targetSelectionTimer = 3;
        returnTimer -= 10;
    }

    public void freeTarget() {
        if (!hitEntities.isEmpty()) {
            float chance = 0.8f - hitEntities.size() * 0.1f;
            if (random.nextFloat() < chance) {
                hitEntities.remove(hitEntities.get(random.nextInt(hitEntities.size())));
            }
        }
    }

    public void selectNearbyTarget(ServerLevel level) {
        Entity owner = getOwner();
        if (owner == null) {
            return;
        }
        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, getBoundingBox().inflate(30),
                target -> target != owner && target.isAlive() && !target.isAlliedTo(owner) && !hitEntities.contains(target) && hasLineOfSight(level, target));
        if (!entities.isEmpty()) {
            forcedTarget = entities.stream().min(Comparator.comparingDouble((e) -> e.distanceToSqr(this))).get();
        }
        if (forcedTarget != null) {
            var speed = getDeltaMovement().length();
            for (int i = 0; i < 3; i++) {
                var distance = forcedTarget.position().subtract(position());
                var newMotion = getDeltaMovement().lerp(distance.normalize(), 0.3f);
                setDeltaMovement(newMotion.normalize());
            }
            setDeltaMovement(getDeltaMovement().scale(speed));
        }
    }

    public void homeIn(ServerLevel level) {
        Vec3 motion = getDeltaMovement();
        Entity owner = getOwner();
        if (owner == null) {
            return;
        }
        Entity nearest;
        boolean demandAccuracy = true;

        if (forcedTarget != null && forcedTarget.isAlive() && !hitEntities.contains(forcedTarget)) {
            nearest = forcedTarget;
            demandAccuracy = false;
        }
        else if (owner.position().distanceTo(position()) > 30f) {
            nearest = owner;
            demandAccuracy = false;
        }
        else {
            List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, getBoundingBox().inflate(20),
                    target -> target != owner && target.isAlive() && !target.isAlliedTo(owner) && !hitEntities.contains(target) && hasLineOfSight(level, target));
            nearest = entities.stream().min(Comparator.comparingDouble((e) -> e.distanceToSqr(this))).orElse(null);
        }
        if (nearest != null) {
            Vec3 nearestPosition = nearest.position().add(0, nearest.getBbHeight() / 2, 0);
            Vec3 distance = nearestPosition.subtract(position());
            double speed = motion.length();
            Vec3 nextPosition = position().add(getDeltaMovement());
            if (demandAccuracy && nearest.distanceToSqr(nextPosition) > nearest.distanceToSqr(position())) {
                return;
            }
            Vec3 newMotion = distance.normalize();
            final double dot = motion.normalize().dot(distance.normalize());
            if (demandAccuracy && dot < 0.1f) {
                return;
            }
            if (newMotion.length() == 0) {
                newMotion = newMotion.add(0.01, 0, 0);
            }
            float angleScalar = Math.max(((Mth.abs((float) (0.5f - dot)) - 0.2f) * 2.5f), 0.4f);
            float factor = 0.125f * angleScalar;
            if (!demandAccuracy) {
                factor = Mth.clamp((float) (1 - distance.length() / 80f) *2f, 0.1f, 0.5f);
            }
            final double x = Mth.clampedLerp(motion.x, newMotion.x, factor);
            final double y = Mth.clampedLerp(motion.y, newMotion.y, factor);
            final double z = Mth.clampedLerp(motion.z, newMotion.z, factor);
            setDeltaMovement(new Vec3(x, y, z).normalize().scale(speed));
        }
    }

    public boolean hasLineOfSight(ServerLevel level, Entity target) {
        Vec3 wrathPosition = new Vec3(getX(), getEyeY(), getZ());
        Vec3 targetPosition = new Vec3(target.getX(), target.getEyeY(), target.getZ());
        var clipResult = level.clip(new ClipContext(wrathPosition, targetPosition, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
        return clipResult.getType().equals(HitResult.Type.MISS);
    }

    public float getVisualEffectScalar() {
        float effectScalar = 1;
        if (age < 8) {
            effectScalar = age / 8f;
        }
        return effectScalar;
    }

    @OnlyIn(Dist.CLIENT)
    public void spawnParticles() {
        Level level = level();
        float scalar = getVisualEffectScalar();
        Vec3 norm = getDeltaMovement().normalize().scale(0.05f);
        var spirit = getSunderingAnchorSpirit();
        var lightSpecs = SpiritLightSpecs.spiritLightSpecs(level, position(), spirit);
        lightSpecs.getBuilder()
                .setRenderTarget(RenderHandler.LATE_DELAYED_RENDER)
                .multiplyLifetime(5f)
                .setMotion(norm);
        lightSpecs.getBloomBuilder()
                .setRenderTarget(RenderHandler.LATE_DELAYED_RENDER)
                .multiplyLifetime(5f)
                .setMotion(norm);
        lightSpecs.spawnParticles();
        final Consumer<LodestoneWorldParticle> behavior = p -> p.setParticleSpeed(p.getParticleSpeed().scale(0.95f));

        float count = 1 + (float) getDeltaMovement().length() * 3;
        for (int i = 0; i < count; i++) {
            spirit = getSunderingAnchorSpirit();
            Vec3 position = getPosition(i / count);
            WorldParticleBuilder.create(ParticleRegistry.ROUNDABOUT_SLASH)
                    .setBehavior(DirectionalParticleBehavior.directional(getDeltaMovement().normalize()))
                    .setTransparencyData(GenericParticleData.create(0.9f * scalar, 0.7f * scalar, 0f).setEasing(Easing.SINE_IN_OUT, Easing.SINE_IN).build())
                    .setSpinData(SpinParticleData.createRandomDirection(random, RandomHelper.randomBetween(random, 0.25f, 0.5f)).randomSpinOffset(random).build())
                    .setScaleData(GenericParticleData.create(0.2f * scalar, 0.4f * scalar).setEasing(Easing.SINE_IN_OUT).build())
                    .setSpritePicker(SimpleParticleOptions.ParticleSpritePicker.WITH_AGE)
                    .setRenderTarget(RenderHandler.LATE_DELAYED_RENDER)
                    .setColorData(spirit.createColorData().build())
                    .setLifetime(Math.min(5 + age * 2, 20))
                    .addTickActor(behavior)
                    .enableForcedSpawn()
                    .enableNoClip()
                    .spawn(level, position.x, position.y, position.z)
                    .setScaleData(GenericParticleData.create(0.3f * scalar, 0.5f * scalar).setEasing(Easing.SINE_IN_OUT).build())
                    .setTransparencyData(GenericParticleData.create(0.6f * scalar, 0.5f * scalar, 0f).setEasing(Easing.SINE_IN_OUT, Easing.SINE_IN).build())
                    .setRenderType(LodestoneWorldParticleRenderType.LUMITRANSPARENT)
                    .setColorData(ColorParticleData.create(ColorHelper.darker(spirit.getPrimaryColor(), 2)).build())
                    .spawn(level, position.x, position.y, position.z);
        }
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    public float getPickRadius() {
        return 4f;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    public boolean ignoreExplosion(Explosion explosion) {
        return true;
    }
}