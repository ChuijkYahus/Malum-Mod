package com.sammy.malum.common.entity;

import com.sammy.malum.*;
import com.sammy.malum.common.item.curiosities.weapons.*;
import com.sammy.malum.common.worldevent.*;
import com.sammy.malum.core.handlers.*;
import com.sammy.malum.core.handlers.enchantment.*;
import com.sammy.malum.core.systems.spirit.*;
import com.sammy.malum.registry.client.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.entity.*;
import com.sammy.malum.registry.common.item.*;
import com.sammy.malum.visual_effects.*;
import com.sammy.malum.visual_effects.networked.*;
import com.sammy.malum.visual_effects.networked.attack.slash.*;
import com.sammy.malum.visual_effects.networked.data.*;
import com.sammy.malum.visual_effects.networked.staff.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.network.syncher.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.util.*;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.phys.*;
import net.neoforged.api.distmarker.*;
import org.jetbrains.annotations.*;
import team.lodestar.lodestone.handlers.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.registry.common.*;
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

import javax.annotation.*;
import java.util.*;
import java.util.function.*;

import static com.sammy.malum.common.item.curiosities.weapons.SunderingAnchorItem.getSunderingAnchorSpirit;

public class SunderingAnchorProjectileEntity extends ThrowableItemProjectile {

    public TrailPointBuilder trailPointBuilder = TrailPointBuilder.create(30);
    public TrailPointBuilder spinningTrailPointBuilder = TrailPointBuilder.create(6);
    public float spinOffset = (float) (random.nextFloat() * Math.PI * 2);
    protected float magicDamage;
    public int age;
    public int secondBounceDelay;
    public int bounceCount;

    private List<Entity> hitEntities = new ArrayList<>();
    private LivingEntity forcedTarget;

    public SunderingAnchorProjectileEntity(Level level) {
        super(EntityRegistry.SUNDERING_ANCHOR.get(), level);
        noPhysics = false;
    }

    public SunderingAnchorProjectileEntity(Level level, double pX, double pY, double pZ) {
        this(level);
        setPos(pX, pY, pZ);
        noPhysics = false;
    }

    public void setData(LivingEntity owner, float magicDamage) {
        setOwner(owner);
        this.magicDamage = magicDamage;
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.SUNDERING_ANCHOR.get();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        if (magicDamage != 0) {
            compound.putFloat("magicDamage", magicDamage);
        }
        if (age != 0) {
            compound.putInt("age", age);
        }
        if (secondBounceDelay != 0) {
            compound.putInt("secondBounceDelay", secondBounceDelay);
        }
        if (bounceCount != 0) {
            compound.putInt("bounceCount", bounceCount);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        magicDamage = compound.getFloat("magicDamage");
        age = compound.getInt("age");
        secondBounceDelay = compound.getInt("secondBounceDelay");
        bounceCount = compound.getInt("bounceCount");
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);

        if (!hitEntities.isEmpty()) {
            if (random.nextFloat() < 0.6f) {
                hitEntities.remove(hitEntities.get(random.nextInt(hitEntities.size())));
            }
        }
        bounceCount++;
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        if (result.getDirection().getAxis().isVertical()) {
            setDeltaMovement(getDeltaMovement().multiply(1, -1, 1));
        } else if (result.getDirection().getAxis().equals(Direction.Axis.X)) {
            setDeltaMovement(getDeltaMovement().multiply(-1, 1, 1));
        } else if (result.getDirection().getAxis().equals(Direction.Axis.Z)) {
            setDeltaMovement(getDeltaMovement().multiply(1, 1, -1));
        }
        if (forcedTarget != null && forcedTarget.isAlive()) {
            float randomRotation = (float) (Math.random() * Math.PI * 2);
            Vec3 motion = getDeltaMovement();
            Vec3 newMotion = new Vec3(
                    motion.x * Math.cos(randomRotation) - motion.z * Math.sin(randomRotation),
                    motion.y,
                    motion.x * Math.sin(randomRotation) + motion.z * Math.cos(randomRotation)
            );
            setDeltaMovement(newMotion);
        }
        secondBounceDelay = 3;
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
                if (success && target instanceof LivingEntity) {
                    int slashCount = 6;
                    var physicalDamageType = DamageTypeRegistry.SUNDERING_ANCHOR_PHYSICAL_COMBO;
                    var magicDamageType = DamageTypeRegistry.SUNDERING_ANCHOR_MAGIC_COMBO;
                    float physicalDamage = (float) (owner.getAttributes().getValue(Attributes.ATTACK_DAMAGE) / slashCount) * 2;
                    float magicDamage = (float) (owner.getAttributes().getValue(LodestoneAttributes.MAGIC_DAMAGE) / slashCount) * 2;
                    int delay = 8;
                    for (int j = 0; j < slashCount; j++) {
                        int comboDelay = delay + j;
                        WorldEventHandler.addWorldEvent(level,
                                new DelayedDamageWorldEvent(target)
                                        .setAttacker(owner)
                                        .setDamageData(physicalDamageType, physicalDamage, magicDamageType, magicDamage, comboDelay)
                                        .setImpactParticleEffect(ParticleEffectTypeRegistry.SUNDERING_ANCHOR_SWEEP, new ColorEffectData(getSunderingAnchorSpirit()))
                                        .setSound(SoundRegistry.SUNDERING_ANCHOR_EXTRA_SWING, 1.75f, 2f, 0.7f));
                    }

                    setDeltaMovement(getDeltaMovement().scale(0.9f));
                    bounceToNearest(level);
                    secondBounceDelay = 3;
                }
            }
        }
        super.onHitEntity(result);
    }

    @Override
    public void tick() {
        var deltaMovement = getDeltaMovement();
        super.tick();
        setDeltaMovement(getDeltaMovement().normalize().scale(deltaMovement.length()));
        age++;
        if (level() instanceof ServerLevel level) {
            if (getOwner() instanceof LivingEntity owner) {
                if (bounceCount >= 10) {
                    var ownerPos = owner.position().add(0, owner.getBbHeight() * 0.6f, 0);
                    float velocityLimit = 2f;
                    var motion = getDeltaMovement();
                    double velocity = Mth.clamp(motion.length() * 3, 0.5f, velocityLimit);
                    var returnMotion = ownerPos.subtract(position()).normalize().scale(velocity);
                    setDeltaMovement(motion.lerp(returnMotion, 0.3f));

                    if (isAlive() && distanceTo(owner) < 1.5f) {
                        SoundHelper.playSound(owner, SoundRegistry.SCYTHE_CATCH.get(), 0.5f, RandomHelper.randomBetween(level().getRandom(), 0.75f, 1.25f));
                        remove(RemovalReason.DISCARDED);
                    }
                    return;
                }
            }


            if (secondBounceDelay > 0) {
                secondBounceDelay--;
                if (secondBounceDelay == 0) {
                    bounceToNearest(level);
                }
            }
            homeIn(level);
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
    protected boolean canHitEntity(Entity pTarget) {
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

    public void bounceToNearest(ServerLevel level) {
        Entity owner = getOwner();
        if (owner == null) {
            return;
        }
        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, getBoundingBox().inflate(60),
                target -> target != owner && target.isAlive() && !target.isAlliedTo(owner) && !hitEntities.contains(target));
        if (!entities.isEmpty()) {
            forcedTarget = entities.stream().min(Comparator.comparingDouble((e) -> e.distanceToSqr(this))).get();
        }
    }

    public void homeIn(ServerLevel level) {
        Vec3 motion = getDeltaMovement();
        Entity owner = getOwner();
        if (owner == null) {
            return;
        }
        LivingEntity nearest;
        boolean demandAccuracy = true;
        if (forcedTarget != null && forcedTarget.isAlive()) {
            nearest = forcedTarget;
            demandAccuracy = false;
        }
        else {
            List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, getBoundingBox().inflate(20),
                    target -> target != owner && target.isAlive() && !target.isAlliedTo(owner) && !hitEntities.contains(target) && hasLineOfSight(level, target));
            nearest = entities.stream().min(Comparator.comparingDouble((e) -> e.distanceToSqr(this))).orElse(null);
        }
        if (nearest != null) {
            Vec3 nearestPosition = nearest.position().add(0, nearest.getBbHeight() / 2, 0);
            Vec3 diff = nearestPosition.subtract(position());
            double speed = motion.length();
            Vec3 nextPosition = position().add(getDeltaMovement());
            if (demandAccuracy && nearest.distanceToSqr(nextPosition) > nearest.distanceToSqr(position())) {
                return;
            }
            Vec3 newMotion = diff.normalize();
            final double dot = motion.normalize().dot(diff.normalize());
            if (demandAccuracy && dot < 0.1f) {
                return;
            }
            if (newMotion.length() == 0) {
                newMotion = newMotion.add(0.01, 0, 0);
            }
            float angleScalar = Math.max(((Mth.abs((float) (0.5f - dot)) - 0.1f) * 2.5f), 0.4f);
            float factor = 0.1f * angleScalar;
            if (nearest == forcedTarget) {
                factor += Mth.clamp((float) (1 - diff.length() / 20f), 0, 1);
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
                .multiplyLifetime(2.5f)
                .setMotion(norm);
        lightSpecs.getBloomBuilder()
                .setRenderTarget(RenderHandler.LATE_DELAYED_RENDER)
                .multiplyLifetime(2.5f)
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
                    .setLifetime(Math.min(6 + age * 3, 30))
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