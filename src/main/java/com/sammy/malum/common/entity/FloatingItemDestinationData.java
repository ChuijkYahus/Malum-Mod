package com.sammy.malum.common.entity;

import com.mojang.datafixers.util.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.server.level.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.phys.*;
import org.jetbrains.annotations.*;

import javax.annotation.*;
import java.util.*;

public class FloatingItemDestinationData {

    @Nonnull
    private final Either<UUID, BlockPos> targetLocation;

    public FloatingItemDestinationData(LivingEntity targetLocation) {
        this(targetLocation.getUUID());
    }

    public FloatingItemDestinationData(UUID targetLocation) {
        this(Either.left(targetLocation));
    }

    public FloatingItemDestinationData(BlockPos targetLocation) {
        this(Either.right(targetLocation));
    }

    private FloatingItemDestinationData(@NotNull Either<UUID, BlockPos> targetLocation) {
        this.targetLocation = targetLocation;
    }

    public void save(CompoundTag compound) {
        targetLocation.ifLeft(uuid -> compound.putUUID("collectorUUID", uuid));
        targetLocation.ifRight(pos -> compound.put("collectorPos", NbtUtils.writeBlockPos(pos)));
    }

    public static FloatingItemDestinationData load(CompoundTag compound) {
        if (compound.contains("collectorUUID")) {
            return new FloatingItemDestinationData(compound.getUUID("collectorUUID"));
        }
        if (compound.contains("collectorPos")) {
            return new FloatingItemDestinationData(NbtUtils.readBlockPos(compound, "collectorPos").orElseThrow());
        }
        return null;
    }

    public @NotNull Either<UUID, BlockPos> getTargetLocation() {
        return targetLocation;
    }

    public boolean isValid(ServerLevel level) {
        return targetLocation.map(
                uuid -> {
                    Entity entity = level.getEntity(uuid);
                    return entity != null && entity.isAlive();
                },
                pos -> level.isOutsideBuildHeight(pos) && level.getWorldBorder().isWithinBounds(pos));
    }

    public Optional<LivingEntity> getEntityCollector(ServerLevel level) {
        return getTargetLocation().left().map(uuid -> {
            if (level.getEntity(uuid) instanceof LivingEntity entity) {
                return entity;
            }
            return null;
        });
    }

    public Optional<Vec3> getDestination(ServerLevel level) {
        return Optional.ofNullable(targetLocation.map(
                uuid -> getEntityPosition(level, uuid),
                BlockPos::getCenter));
    }

    public double getDistance(ServerLevel level, FloatingEntity entity) {
        return getDestination(level).map(pos -> pos.distanceToSqr(entity.position().add(0, entity.getBbHeight() / 2f, 0))).orElse(Double.MAX_VALUE);
    }

    private static Vec3 getEntityPosition(ServerLevel level, UUID uuid) {
        Entity entity = level.getEntity(uuid);
        if (entity != null && entity.isAlive()) {
            return entity.position().add(0, entity.getBbHeight() / 3, 0);
        }
        return null;
    }
}