package com.sammy.malum.common.entity.mob.cultist.evangelist;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.sammy.malum.MalumMod;
import com.sammy.malum.common.entity.mob.cultist.CultistMeleeAttackGoal;
import com.sammy.malum.common.entity.mob.cultist.CultistMonster;
import com.sammy.malum.common.entity.mob.cultist.IAltarBlessingRecipient;
import com.sammy.malum.registry.common.entity.MalumEntities;
import com.sammy.malum.registry.common.item.MalumItems;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;
import team.lodestar.lodestone.registry.common.LodestoneAttributes;

public class EvangelistCultist extends CultistMonster implements IAltarBlessingRecipient {

    public static final float ALTAR_BLESSING_THRESHOLD = 0.6f;
    public static final float ALTAR_BLESSING_HEALING = 0.4f;
    public static final int EMPOWERMENT_DURATION = 80;


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

        var meleeAttackGoal = new CultistMeleeAttackGoal(this, 1.5f);

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
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);

        compound.putInt("EmpowermentDuration", empowermentDuration);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);

        empowermentDuration = compound.getInt("EmpowermentDuration");
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 60.0)
                .add(Attributes.FOLLOW_RANGE, 35.0)
                .add(Attributes.MOVEMENT_SPEED, 0.15)
                .add(LodestoneAttributes.MAGIC_DAMAGE, 2.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5f)
                .add(LodestoneAttributes.MAGIC_RESISTANCE, 0.75f)
                .add(Attributes.ARMOR, 20.0)
                .add(Attributes.STEP_HEIGHT, 1);
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
        return healthDelta <= ALTAR_BLESSING_THRESHOLD;
    }

    @Override
    public void receiveAltarBuff() {
        float recoveredHealth = getMaxHealth()*ALTAR_BLESSING_HEALING;
        heal(recoveredHealth);
        empowermentDuration = EMPOWERMENT_DURATION;
        getAttributes().addTransientAttributeModifiers(EMPOWERMENT_MODIFIERS);
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        setItemSlot(EquipmentSlot.MAINHAND, MalumItems.SHAPED_SLAB.get().getDefaultInstance());
        enchantSpawnedWeapon(level, random, difficulty);
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }

    @Override
    public boolean isWithinMeleeAttackRange(LivingEntity entity) {
        return getAttackBoundingBox().inflate(0.5f).intersects(entity.getHitbox());
    }
}