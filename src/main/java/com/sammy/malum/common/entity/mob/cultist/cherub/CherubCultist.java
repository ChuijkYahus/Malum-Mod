package com.sammy.malum.common.entity.mob.cultist.cherub;

import com.sammy.malum.common.entity.mob.cultist.CultistMonster;
import com.sammy.malum.common.entity.mob.cultist.cherub.goal.CherubOrbitEnemyGoal;
import com.sammy.malum.common.entity.mob.cultist.cherub.goal.CherubOrbitLeaderGoal;
import com.sammy.malum.registry.common.entity.MalumEntityTypes;
import com.sammy.malum.registry.common.item.MalumItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
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
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import team.lodestar.lodestone.registry.common.LodestoneAttributes;

import java.util.UUID;

public class CherubCultist extends CultistMonster {

    public static final int LEADER_SEARCH_INTERVAL = 10;
    public static final float LEADER_SEARCH_RADIUS = 32f;
    public static final float LEADER_ORBIT_RADIUS = 0.5f;
    public static final float LEADER_ORBIT_RATE = 0.05f;

    public static final float EVASIVE_ENEMY_ORBIT_RADIUS = 12f;
    public static final float AGGRESSIVE_ENEMY_ORBIT_RADIUS = 3f;
    public static final float ENEMY_APPROACH_RADIUS = 16f;


    public static final int DISRUPTIVE_FLIGHT_INTERVAL = 40;
    public static final int SCARED_DURATION = 80;

    protected int scaredTime;

    protected UUID leaderID;
    protected CultistMonster leader;
    protected int leaderCherubIndex;

    public CherubCultist(Level level) {
        super(MalumEntityTypes.CHERUB.get(), level);
        moveControl = new CherubMoveControl(this);
    }

    @Override
    public @NotNull CherubMoveControl getMoveControl() {
        return (CherubMoveControl) moveControl;
    }

    @Override
    protected void registerGoals() {
        var targeting = new NearestAttackableTargetGoal<>(this, Player.class, true);
        var leaderSearch = new NearestCherubFriendGoal(this, LEADER_SEARCH_INTERVAL, LEADER_SEARCH_RADIUS);

        var orbitLeader = new CherubOrbitLeaderGoal(this, 0.75f, LEADER_ORBIT_RADIUS, LEADER_ORBIT_RATE);
        var evasiveEnemyOrbit = CherubOrbitEnemyGoal.evasive(this, 1.25f);
        var aggressiveEnemyOrbit = CherubOrbitEnemyGoal.aggressive(this, 1.5f);
        var randomStroll = new WaterAvoidingRandomStrollGoal(this, 0.5f);
        var lookAtPlayer = new LookAtPlayerGoal(this, Player.class, 12.0F);

        var randomLookAround = new RandomLookAroundGoal(this);

        targetSelector.addGoal(0, targeting);
        targetSelector.addGoal(0, leaderSearch);

        goalSelector.addGoal(1, orbitLeader);
        goalSelector.addGoal(2, evasiveEnemyOrbit);
        goalSelector.addGoal(3, aggressiveEnemyOrbit);
        goalSelector.addGoal(4, randomStroll);
        goalSelector.addGoal(5, lookAtPlayer);
        goalSelector.addGoal(6, randomLookAround);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.FOLLOW_RANGE, 35.0)
                .add(Attributes.MOVEMENT_SPEED, 0.22)
                .add(LodestoneAttributes.MAGIC_DAMAGE, 2.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5)
                .add(LodestoneAttributes.MAGIC_RESISTANCE, 0.5)
                .add(Attributes.ARMOR, 8.0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        if (leaderID != null) {
            compound.putUUID("Leader", leaderID);
            compound.putInt("LeaderCherubIndex", leaderCherubIndex);
        }
        compound.putInt("ScaredTime", scaredTime);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("Leader")) {
            leaderID = compound.getUUID("Leader");
            leaderCherubIndex = compound.getInt("LeaderCherubIndex");
        }
        scaredTime = compound.getInt("ScaredTime");
    }

    @Override
    public void tick() {
        super.tick();
        setNoGravity(true);
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
        if (scaredTime > 0) {
            scaredTime--;
        }
        if (level() instanceof ServerLevel level) {
            trackLeader(level);
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        boolean hurt = super.hurt(source, amount);
        if (hurt) {
            scaredTime = SCARED_DURATION;
        }
        return hurt;
    }

    public void trackLeader(ServerLevel level) {
        if (leaderID != null) {
            leader = level.getEntity(leaderID) instanceof CultistMonster instance ? instance : null;
        }
        if (leader != null && leader.isAddedToLevel() && leader.isAlive()) {
            return;
        }
        leaderID = null;
        leader = null;
        leaderCherubIndex = -1;
    }

    public void setLeader(CultistMonster leader, int leaderCherubIndex) {
        this.leaderID = leader.getUUID();
        this.leader = leader;
        this.leaderCherubIndex = leaderCherubIndex;
    }

    public boolean isFeisty() {
        return !isScared();
    }

    public boolean isScared() {
        return scaredTime > 0;
    }

    public CultistMonster getLeader() {
        return leader;
    }

    public int getLeaderCherubIndex() {
        return leaderCherubIndex;
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        setItemSlot(EquipmentSlot.MAINHAND, MalumItems.BROKEN_BLADE.get().getDefaultInstance());
        enchantSpawnedWeapon(level, random, difficulty);
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {

    }
}