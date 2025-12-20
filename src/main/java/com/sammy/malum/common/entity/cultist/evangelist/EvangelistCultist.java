package com.sammy.malum.common.entity.cultist.evangelist;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.sammy.malum.MalumMod;
import com.sammy.malum.common.entity.cultist.CultistBoltProjectile;
import com.sammy.malum.common.entity.cultist.ICultist;
import com.sammy.malum.common.entity.cultist.altar.AltarMeleeAttackGoal;
import com.sammy.malum.registry.common.MalumDamageTypes;
import com.sammy.malum.registry.common.entity.MalumEntities;
import com.sammy.malum.registry.common.item.MalumItems;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
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
import net.minecraft.world.phys.Vec3;
import org.antlr.v4.runtime.misc.MultiMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import team.lodestar.lodestone.helpers.DamageTypeHelper;
import team.lodestar.lodestone.helpers.RandomHelper;
import team.lodestar.lodestone.registry.common.LodestoneAttributes;
import team.lodestar.lodestone.systems.easing.Easing;

import java.util.UUID;

public class EvangelistCultist extends Monster implements Enemy, ICultist {

    private static final EntityDataAccessor<Integer> SCALE = SynchedEntityData.defineId(EvangelistCultist.class, EntityDataSerializers.INT);

    public static final int EMPOWERMENT_DURATION = 60;
    public static final ResourceLocation ALTAR_EMPOWERMENT = MalumMod.malumPath("altar_empowerment");
    public static final Multimap<Holder<Attribute>, AttributeModifier> EMPOWERMENT_MODIFIERS =
            ImmutableMultimap.of(
                    Attributes.ATTACK_DAMAGE, new AttributeModifier(ALTAR_EMPOWERMENT, 2f, AttributeModifier.Operation.ADD_VALUE),
                    Attributes.MOVEMENT_SPEED, new AttributeModifier(ALTAR_EMPOWERMENT, 0.5f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                    Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(ALTAR_EMPOWERMENT, 0.5f, AttributeModifier.Operation.ADD_VALUE)
            );
    public int empowermentDuration;

    public EvangelistCultist(Level level) {
        super(MalumEntities.EVANGELIST.get(), level);
    }

    @Override
    protected void registerGoals() {
        var targeting = new NearestAttackableTargetGoal<>(this, Player.class, true);

        var meleeAttackGoal = new EvangelistMeleeAttackGoal(this, 1.5f);

        var randomStroll = new WaterAvoidingRandomStrollGoal(this, 0.8f);
        var lookAtPlayer = new LookAtPlayerGoal(this, Player.class, 24.0F);
        var randomLookAround = new RandomLookAroundGoal(this);

        targetSelector.addGoal(0, targeting);

        goalSelector.addGoal(1, meleeAttackGoal);
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

        compound.putInt("evangelistScale", getEvangelistScale());

        compound.putInt("empowermentDuration", empowermentDuration);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);

        setEvangelistScale(compound.getInt("evangelistScale"));

        empowermentDuration = compound.getInt("empowermentDuration");

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 60.0)
                .add(Attributes.FOLLOW_RANGE, 35.0)
                .add(Attributes.MOVEMENT_SPEED, 0.15)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(LodestoneAttributes.MAGIC_DAMAGE, 2.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5f)
                .add(LodestoneAttributes.MAGIC_RESISTANCE, 0.75f)
                .add(Attributes.ARMOR, 20.0);
    }

    @Override
    public void tick() {
        super.tick();
        updateEmpowerment();
    }

    public void updateEmpowerment() {
        if (empowermentDuration > 0) {
            empowermentDuration--;
            if (empowermentDuration == 0) {
                getAttributes().removeAttributeModifiers(EMPOWERMENT_MODIFIERS);
            }
        }
    }

    @Override
    public boolean canReceiveAltarBuff() {
        float healthDelta = getHealth() / getMaxHealth();
        return healthDelta <= 0.5f;
    }

    @Override
    public void receiveAltarBuff() {
        float recoveredHealth = getMaxHealth()*0.25f;
        heal(recoveredHealth);
        empowermentDuration = EMPOWERMENT_DURATION;
        getAttributes().addTransientAttributeModifiers(EMPOWERMENT_MODIFIERS);
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
        setEvangelistScale(scale);
        setItemSlot(EquipmentSlot.MAINHAND, MalumItems.SHAPED_SLAB.get().getDefaultInstance());
        enchantSpawnedWeapon(level, random, difficulty);
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }

    @Override
    public boolean isWithinMeleeAttackRange(LivingEntity entity) {
        return getAttackBoundingBox().inflate(0.5f).intersects(entity.getHitbox());
    }

    public void setEvangelistScale(int scale) {
        entityData.set(SCALE, scale);
    }

    public int getEvangelistScale() {
        return entityData.get(SCALE);
    }

    public float getEvangelistScaleMultiplier() {
        return 1f + getEvangelistScale() * 0.1f;
    }
}