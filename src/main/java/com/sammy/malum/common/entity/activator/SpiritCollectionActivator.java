package com.sammy.malum.common.entity.activator;

import com.sammy.malum.common.entity.*;
import com.sammy.malum.core.handlers.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.entity.*;
import com.sammy.malum.registry.common.magic.*;
import com.sammy.malum.visual_effects.*;
import net.minecraft.network.syncher.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.util.*;
import net.minecraft.world.level.*;
import net.minecraft.world.phys.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.systems.rendering.trail.*;

import java.util.*;

public class SpiritCollectionActivator extends FloatingEntity {

    public final List<TrailPointBuilder> orbitingTrails = new ArrayList<>(List.of(TrailPointBuilder.create(4), TrailPointBuilder.create(4)));

    public float spinOffset = (float) (random.nextFloat() * Math.PI * 2);

    public SpiritCollectionActivator(Level level) {
        super(MalumEntityTypes.SPIRIT_COLLECTION_ACTIVATOR.get(), level);
        maxAge = 4000;
    }

    public SpiritCollectionActivator(Level level, UUID ownerUUID, Vec3 position, Vec3 velocity) {
        this(level);
        setDestination(new FloatingItemDestinationData(ownerUUID));
        setPos(position);
        setDeltaMovement(velocity);
        maxAge = 800;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

    }

    @Override
    public SoundSource getSoundSource() {
        return SoundSource.NEUTRAL;
    }

    @Override
    public void collect(ServerLevel level) {
        getDestination().getEntityCollector(level).ifPresent(SoulHarvestHandler::triggerSpiritCollection);
        SoundHelper.playSound(this, MalumSoundEvents.SPIRIT_PICKUP.get(), 0.3f, Mth.nextFloat(random, 1.2f, 1.5f));
    }

    @Override
    public void tick() {
        super.tick();
        if (level().isClientSide) {
            float offsetScale = 0.1f + random.nextFloat() * 0.2f;
            Vec3 position = getOffsetPosition(0.5f);
            for (int i = 0; i < orbitingTrails.size(); i++) {
                var trail = orbitingTrails.get(i);
                float scalar = age / 6f;
                float offset = i * 3.14f;
                float angle = spinOffset + scalar + offset;
                double xOffset = Math.sin(angle) * offsetScale;
                double zOffset = Math.cos(angle) * offsetScale;
                trail.addTrailPoint(position.add(xOffset, 0, zOffset));
                trail.tickTrailPoints();
            }

            Vec3 motion = getDeltaMovement();
            Vec3 norm = motion.normalize().scale(0.05f);
            var lightSpecs = SpiritLightSpecs.spiritLightSpecs(level(), getOffsetPosition(), MalumSpiritTypes.UMBRAL_SPIRIT);
            lightSpecs.getBuilder().setMotion(norm);
            lightSpecs.getBloomBuilder().setMotion(norm);
            lightSpecs.spawnParticles();
        }
    }

    @Override
    public int getWindUpDuration() {
        return 25;
    }

    @Override
    public float getMovementEasing(float windUp, float distance) {
        return super.getMovementEasing(windUp, distance) * 4f;
    }

    @Override
    public float getFriction() {
        return 0.9f;
    }
}