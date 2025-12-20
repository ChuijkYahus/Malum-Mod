package com.sammy.malum.common.entity.cultist.cardinal;

import com.sammy.malum.common.entity.cultist.ICultist;
import com.sammy.malum.registry.common.MalumDamageTypes;
import com.sammy.malum.registry.common.entity.MalumEntities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import team.lodestar.lodestone.helpers.DamageTypeHelper;
import team.lodestar.lodestone.helpers.RandomHelper;
import team.lodestar.lodestone.registry.common.LodestoneAttributes;

public class CardinalCultist extends Monster implements Enemy, ICultist {

    private static final EntityDataAccessor<Integer> SCALE = SynchedEntityData.defineId(CardinalCultist.class, EntityDataSerializers.INT);

    public CardinalCultist(Level level) {
        super(MalumEntities.CARDINAL.get(), level);
    }

    @Override
    protected void registerGoals() {
        var targeting = new NearestAttackableTargetGoal<>(this, Player.class, true);

        var randomStroll = new WaterAvoidingRandomStrollGoal(this, 0.8f);
        var lookAtPlayer = new LookAtPlayerGoal(this, Player.class, 24.0F);
        var randomLookAround = new RandomLookAroundGoal(this);

        targetSelector.addGoal(0, targeting);

        goalSelector.addGoal(3, randomStroll);
        goalSelector.addGoal(4, lookAtPlayer);
        goalSelector.addGoal(5, randomLookAround);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(SCALE, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);

        compound.putInt("evangelistScale", getCardinalScale());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);

        setCardinalScale(compound.getInt("evangelistScale"));

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 50.0)
                .add(Attributes.FOLLOW_RANGE, 35.0)
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(LodestoneAttributes.MAGIC_DAMAGE, 4.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5f)
                .add(LodestoneAttributes.MAGIC_RESISTANCE, 1.5f)
                .add(Attributes.ARMOR, 10.0);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public boolean canReceiveAltarBuff() {
        return false;
    }

    @Override
    public void receiveAltarBuff() {
    }

    @Override
    public @NotNull SoundSource getSoundSource() {
        return SoundSource.HOSTILE;
    }

    @Override
    public boolean doHurtTarget(@NotNull Entity target) {
        boolean success = super.doHurtTarget(target);
        if (success) {
            float magicDamage = (float) this.getAttributeValue(LodestoneAttributes.MAGIC_DAMAGE);
            DamageSource damagesource = DamageTypeHelper.create(level(), MalumDamageTypes.CULTIST_MAGIC);
            target.hurt(damagesource, magicDamage);
        }
        return success;
    }

    @SuppressWarnings("deprecation")
    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        int scale = RandomHelper.randomBetween(random, 0, 3);
        setCardinalScale(scale);
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }

    @Override
    public boolean isWithinMeleeAttackRange(LivingEntity entity) {
        return getAttackBoundingBox().inflate(0.5f).intersects(entity.getHitbox());
    }

    public void setCardinalScale(int scale) {
        entityData.set(SCALE, scale);
    }

    public int getCardinalScale() {
        return entityData.get(SCALE);
    }

    public float getCardinalScaleMultiplier() {
        return 1f + getCardinalScale() * 0.1f;
    }
}