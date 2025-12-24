package com.sammy.malum.common.entity.mob.cultist.believer;

import com.sammy.malum.common.entity.mob.cultist.CultistMonster;
import com.sammy.malum.common.entity.mob.cultist.IAltarBlessingRecipient;
import com.sammy.malum.common.entity.mob.cultist.CultistMeleeAttackGoal;
import com.sammy.malum.registry.common.entity.MalumEntities;
import com.sammy.malum.registry.common.item.MalumItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
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
import org.jetbrains.annotations.Nullable;
import team.lodestar.lodestone.registry.common.LodestoneAttributes;

public class BelieverCultist extends CultistMonster implements IAltarBlessingRecipient {

    public BelieverCultist(Level level) {
        super(MalumEntities.BELIEVER.get(), level);
    }

    @Override
    protected void registerGoals() {
        var targeting = new NearestAttackableTargetGoal<>(this, Player.class, true);

        var meleeAttackGoal = new CultistMeleeAttackGoal(this, 0.8f);

        var randomStroll = new WaterAvoidingRandomStrollGoal(this, 0.8f);
        var lookAtCultistGoal = new LookAtPlayerGoal(this, CultistMonster.class, 12.0F, 0.05f);
        var lookAtPlayer = new LookAtPlayerGoal(this, Player.class, 24.0F);
        var randomLookAround = new RandomLookAroundGoal(this);

        targetSelector.addGoal(0, targeting);

        goalSelector.addGoal(0, meleeAttackGoal);
        goalSelector.addGoal(1, randomStroll);
        goalSelector.addGoal(2, lookAtCultistGoal);
        goalSelector.addGoal(3, lookAtPlayer);
        goalSelector.addGoal(4, randomLookAround);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 30.0)
                .add(Attributes.FOLLOW_RANGE, 35.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(LodestoneAttributes.MAGIC_DAMAGE, 2.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.25f)
                .add(LodestoneAttributes.MAGIC_RESISTANCE, 0.75f)
                .add(Attributes.ARMOR, 8.0)
                .add(Attributes.STEP_HEIGHT, 1);
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

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        setItemSlot(EquipmentSlot.MAINHAND, MalumItems.BROKEN_BLADE.get().getDefaultInstance());
        enchantSpawnedWeapon(level, random, difficulty);
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }
}