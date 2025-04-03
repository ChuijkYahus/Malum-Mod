package com.sammy.malum.common.data.attachment.soul_data;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.neoforged.neoforge.event.entity.living.*;

import java.util.*;

public class LivingSoulData {

    public static final Codec<LivingSoulData> CODEC = RecordCodecBuilder.create(obj -> obj.group(
            Codec.FLOAT.fieldOf("exposedSoulDuration").forGetter(sd -> sd.exposedSoulDuration),
            Codec.BOOL.fieldOf("soulless").forGetter(sd -> sd.soulless),
            Codec.BOOL.fieldOf("spawnerSpawned").forGetter(sd -> sd.spawnerSpawned),
            Codec.LONG.fieldOf("mostRecentShatter").forGetter(sd -> sd.mostRecentShatter)
            ).apply(obj, LivingSoulData::new));

    private float exposedSoulDuration;
    private long mostRecentShatter;
    private boolean soulless;
    private boolean spawnerSpawned;

    public LivingSoulData() {
    }

    private LivingSoulData(float exposedSoulDuration, boolean soulless, boolean spawnerSpawned, long mostRecentShatter) {
        this.exposedSoulDuration = exposedSoulDuration;
        this.soulless = soulless;
        this.spawnerSpawned = spawnerSpawned;
        this.mostRecentShatter = mostRecentShatter;
    }

    public void setExposed() {
        setExposedSoulDuration(200);
    }

    public void updateSoullessBehavior(Mob mob) {
        if (isSoulless()) {
            ArrayList<Class<? extends Goal>> goalsToRemove = new ArrayList<>(List.of(
                    LookAtPlayerGoal.class, MeleeAttackGoal.class, SwellGoal.class, PanicGoal.class, RandomLookAroundGoal.class, AvoidEntityGoal.class
            ));
            mob.goalSelector.getAvailableGoals().removeIf(g -> goalsToRemove.stream().anyMatch(c -> c.isInstance(g)));
        }
    }

    public void updateSoullessTargeting(LivingChangeTargetEvent event) {
        if (isSoulless()) {
            event.setNewAboutToBeSetTarget(null);
        }
    }

    public void tickDuration() {
        if (shouldDropSpirits()) {
            exposedSoulDuration--;
        }
    }

    public void setMostRecentShatter(long mostRecentShatter) {
        this.mostRecentShatter = mostRecentShatter;
    }

    public long getMostRecentShatter() {
        return mostRecentShatter;
    }

    public boolean shouldDropSpirits() {
        return !soulless && getExposedSoulDuration() > 0;
    }

    public void setExposedSoulDuration(float exposedSoulDuration) {
        this.exposedSoulDuration = exposedSoulDuration;
    }

    public float getExposedSoulDuration() {
        return exposedSoulDuration;
    }

    public void setSoulless(boolean soulless) {
        this.soulless = soulless;
    }

    public boolean isSoulless() {
        return soulless;
    }

    public void setSpawnerSpawned(boolean spawnerSpawned) {
        this.spawnerSpawned = spawnerSpawned;
    }

    public boolean isSpawnerSpawned() {
        return spawnerSpawned;
    }
}