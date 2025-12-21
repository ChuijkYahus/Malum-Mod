package com.sammy.malum.common.entity.cultist;

import com.sammy.malum.registry.common.MalumDamageTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import team.lodestar.lodestone.helpers.DamageTypeHelper;
import team.lodestar.lodestone.helpers.RandomHelper;
import team.lodestar.lodestone.registry.common.LodestoneAttributes;

public abstract class CultistMonster extends Monster implements Enemy {

    private static final EntityDataAccessor<Integer> SCALE = SynchedEntityData.defineId(CultistMonster.class, EntityDataSerializers.INT);

    protected CultistMonster(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(SCALE, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("CultistScale", getCultistScale());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        setCultistScale(compound.getInt("CultistScale"));
    }

    @Override
    public @NotNull SoundSource getSoundSource() {
        return SoundSource.HOSTILE;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.is(DamageTypes.IN_WALL)) {
            return false;
        }
        return super.hurt(source, amount);
    }

    @Override
    public boolean doHurtTarget(@NotNull Entity target) {
        if (super.doHurtTarget(target)) {
            float magicDamage = (float) this.getAttributeValue(LodestoneAttributes.MAGIC_DAMAGE);
            var damagesource = DamageTypeHelper.create(level(), MalumDamageTypes.CULTIST_MAGIC);
            target.invulnerableTime = 0;
            target.hurt(damagesource, magicDamage);
            return true;
        }
        return false;
    }

    @SuppressWarnings("deprecation")
    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        int scale = RandomHelper.randomBetween(random, 0, 3);
        setCultistScale(scale);
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }

    @Override
    public boolean isWithinMeleeAttackRange(LivingEntity entity) {
        return getAttackBoundingBox().inflate(0.25f).intersects(entity.getHitbox());
    }

    public void setCultistScale(int scale) {
        entityData.set(SCALE, scale);
    }

    public int getCultistScale() {
        return entityData.get(SCALE);
    }

    public float getCultistScaleMultiplier() {
        return 0.9f + getCultistScale() * 0.05f;
    }
}