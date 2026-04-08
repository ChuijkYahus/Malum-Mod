package com.sammy.malum.common.entity.mob.cultist.cherub;

import com.sammy.malum.common.entity.mob.cultist.CultistMonster;
import com.sammy.malum.common.entity.mob.cultist.CultistMoveControl;
import com.sammy.malum.common.entity.mob.cultist.cherub.goal.CherubCastCurseGoal;
import com.sammy.malum.common.entity.mob.cultist.cherub.goal.CherubCastHealGoal;
import com.sammy.malum.common.entity.mob.cultist.cherub.goal.CherubOrbitEnemyGoal;
import com.sammy.malum.common.entity.mob.cultist.cherub.goal.CherubOrbitLeaderGoal;
import com.sammy.malum.registry.common.entity.*;
import com.sammy.malum.registry.common.sound.*;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import team.lodestar.lodestone.registry.common.LodestoneAttributes;

import java.util.UUID;

public class CherubCultist extends CultistMonster {

    public static final byte CAST_ANIMATION = 11;

    public static final int LEADER_SEARCH_INTERVAL = 10;
    public static final float LEADER_SEARCH_RADIUS = 32f;
    public static final float LEADER_ORBIT_RADIUS = 0.5f;
    public static final float LEADER_ORBIT_RATE = 0.05f;

    public static final float EVASIVE_ENEMY_ORBIT_RADIUS = 12f;
    public static final float AGGRESSIVE_ENEMY_ORBIT_RADIUS = 3f;
    public static final float ENEMY_APPROACH_RADIUS = 16f;

    public static final int SPELL_DURATION = 15;

    public static final float CURSE_CAST_RANGE = 5f;
    public static final int CURSE_INTERVAL = 100;

    public static final float HEAL_CAST_RANGE = 8f;
    public static final float HEAL_AMOUNT = 4f;
    public static final int HEAL_INTERVAL = 60;

    public static final int DISRUPTIVE_FLIGHT_INTERVAL = 40;
    public static final int SCARED_DURATION = 80;

    protected long mostRecentHurt;
    protected long mostRecentSpell;

    public AnimationState idleAnimationState = new AnimationState();
    public AnimationState castAnimationState = new AnimationState();

    protected CherubLeaderSearch leaderSearch = new CherubLeaderSearch(this);

    public CherubCultist(Level level) {
        super(MalumCultistEntityTypes.CHERUB.get(), MalumCultistSoundEvents.CHERUB, level);
        moveControl = new CherubMoveControl(this);
        idleAnimationState.start(tickCount);
    }

    @Override
    public @NotNull CherubMoveControl getMoveControl() {
        return (CherubMoveControl) moveControl;
    }

    @Override
    protected void registerGoals() {

        var targeting = new NearestAttackableTargetGoal<>(this, Player.class, false);

        var castHeal = new CherubCastHealGoal(this);
        var castCurse = new CherubCastCurseGoal(this);
        var orbitLeader = new CherubOrbitLeaderGoal(this, 0.75f);
        var evasiveEnemyOrbit = CherubOrbitEnemyGoal.evasive(this, 1.25f);
        var aggressiveEnemyOrbit = CherubOrbitEnemyGoal.aggressive(this, 1.5f);
        var randomStroll = new WaterAvoidingRandomStrollGoal(this, 0.5f);
        var lookAtPlayer = new LookAtPlayerGoal(this, Player.class, 12.0F);

        var randomLookAround = new RandomLookAroundGoal(this);

        targetSelector.addGoal(0, targeting);

        goalSelector.addGoal(0, castHeal);
        goalSelector.addGoal(1, castCurse);
        goalSelector.addGoal(2, orbitLeader);
        goalSelector.addGoal(3, evasiveEnemyOrbit);
        goalSelector.addGoal(4, aggressiveEnemyOrbit);
        goalSelector.addGoal(5, randomStroll);
        goalSelector.addGoal(6, lookAtPlayer);
        goalSelector.addGoal(7, randomLookAround);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.FOLLOW_RANGE, 35.0)
                .add(Attributes.MOVEMENT_SPEED, 0.22)
                .add(Attributes.ATTACK_DAMAGE, 0.0)
                .add(LodestoneAttributes.MAGIC_DAMAGE, 2.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5)
                .add(LodestoneAttributes.MAGIC_RESISTANCE, 0.5)
                .add(Attributes.ARMOR, 8.0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        leaderSearch.save(compound);
        compound.putLong("MostRecentHurt", mostRecentHurt);
        compound.putLong("MostRecentSpell", mostRecentSpell);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        leaderSearch.load(compound);
        mostRecentHurt = compound.getLong("MostRecentHurt");
        mostRecentSpell = compound.getLong("MostRecentSpell");
    }

    @Override
    public void handleEntityEvent(byte id) {
        switch (id) {
            case CAST_ANIMATION -> startAnimation(castAnimationState);
            default -> super.handleEntityEvent(id);
        }
    }

    @Override
    public void tick() {
        super.tick();
        setNoGravity(true);
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
        if (level() instanceof ServerLevel level) {
            leaderSearch.update(level);
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        boolean hurt = super.hurt(source, amount);
        if (hurt) {
            mostRecentHurt = level().getGameTime();
        }
        return hurt;
    }

    public void castHeal(LivingEntity target) {
        target.heal(HEAL_AMOUNT);

        playSound(MalumCultistSoundEvents.CHERUB_CAST_HEAL.get());
        castSpell();
    }

    public void castCurse(LivingEntity target) {
        doHurtTarget(target);

        playSound(MalumCultistSoundEvents.CHERUB_CAST_CURSE.get());
        castSpell();
    }

    public void castSpell() {
        mostRecentSpell = level().getGameTime();
    }

    public boolean isFeisty() {
        return !isScared();
    }

    public boolean isScared() {
        return wasHurtRecently(SCARED_DURATION);
    }

    public boolean hasCastSpellRecently(int timeframe) {
        return level().getGameTime() - mostRecentSpell < timeframe;
    }

    public boolean wasHurtRecently(int timeframe) {
        return level().getGameTime() - mostRecentHurt < timeframe;
    }

    public CultistMonster getLeader() {
        return getLeaderData().leader;
    }

    public int getLeaderCherubIndex() {
        return getLeaderData().leaderCherubIndex;
    }

    public CherubLeaderSearch getLeaderData() {
        return leaderSearch;
    }


    public LivingEntity getHealingTarget() {
        AABB aabb = getBoundingBox();
        level().getEntitiesOfClass(CultistMonster.class, aabb.inflate(HEAL_CAST_RANGE), this::canHeal);
        return super.getTarget();
    }

    public boolean canHeal(LivingEntity target) {
        return target.getHealth() < target.getMaxHealth() * 0.75f;
    }

    /**
     * Cherubs don't use navigation.
     */
    @Override
    public void lookAtAndFaceTarget(@Nullable Entity target) {
        if (target == null) {
            return;
        }
        getMoveControl().replaceBodyDirection(CultistMoveControl.BodyDirection.FACE_TARGET);
        getLookControl().setLookAt(target, 60.0F, 60.0F);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {

    }
}