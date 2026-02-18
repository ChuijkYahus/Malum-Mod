package com.sammy.malum.common.entity.mob.cultist.evangelist;

import com.sammy.malum.common.entity.mob.cultist.CultistMeleeAttackGoal;
import com.sammy.malum.common.entity.mob.cultist.CultistMonster;
import com.sammy.malum.common.entity.mob.cultist.IAltarBlessingRecipient;
import com.sammy.malum.registry.common.entity.*;
import com.sammy.malum.registry.common.item.MalumItems;
import com.sammy.malum.registry.common.sound.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
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
import org.jetbrains.annotations.Nullable;
import team.lodestar.lodestone.registry.common.LodestoneAttributes;

public class EvangelistCultist extends CultistMonster implements IAltarBlessingRecipient {

    public EvangelistCultist(Level level) {
        super(MalumCultistEntityTypes.EVANGELIST.get(), MalumCultistSoundEvents.EVANGELIST, level);
    }

    @Override
    protected void registerGoals() {
        var targeting = new NearestAttackableTargetGoal<>(this, Player.class, true);

        var meleeAttackGoal = new CultistMeleeAttackGoal(this, 1f);

        var randomStroll = new WaterAvoidingRandomStrollGoal(this, 0.5f);
        var lookAtPlayer = new LookAtPlayerGoal(this, Player.class, 24.0F);
        var randomLookAround = new RandomLookAroundGoal(this);

        targetSelector.addGoal(0, targeting);

        goalSelector.addGoal(1, meleeAttackGoal);
        goalSelector.addGoal(3, randomStroll);
        goalSelector.addGoal(4, lookAtPlayer);
        goalSelector.addGoal(5, randomLookAround);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 60.0)
                .add(Attributes.FOLLOW_RANGE, 35.0)
                .add(Attributes.MOVEMENT_SPEED, 0.1)
                .add(LodestoneAttributes.MAGIC_DAMAGE, 2.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.75)
                .add(LodestoneAttributes.MAGIC_RESISTANCE, 0.75)
                .add(Attributes.ARMOR, 20.0)
                .add(Attributes.STEP_HEIGHT, 1);
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

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {

    }
}