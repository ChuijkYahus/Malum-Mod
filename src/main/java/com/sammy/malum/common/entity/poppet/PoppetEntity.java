package com.sammy.malum.common.entity.poppet;

import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.ai.control.*;
import net.minecraft.world.entity.ai.navigation.*;
import net.minecraft.world.entity.npc.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.state.*;

public class PoppetEntity extends PathfinderMob implements InventoryCarrier {

    protected SimpleContainer inventory = new SimpleContainer(4);

    protected PoppetEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new FlyingMoveControl(this, 20, true);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.ARMOR, 8.0)
                .add(Attributes.ARMOR_TOUGHNESS, 8.0)
                .add(Attributes.FLYING_SPEED, 0.3F)
                .add(Attributes.MOVEMENT_SPEED, 0.3F)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.FOLLOW_RANGE, 48.0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        writeInventoryToTag(compound, registryAccess());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        readInventoryFromTag(compound, registryAccess());
    }

    @Override
    public SimpleContainer getInventory() {
        return inventory;
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, level);
        flyingpathnavigation.setCanOpenDoors(false);
        flyingpathnavigation.setCanFloat(true);
        flyingpathnavigation.setCanPassDoors(true);
        return flyingpathnavigation;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!level().isClientSide && isAlive() && tickCount % 10 == 0) {
            heal(1.0F);
        }
    }

    @Override
    public boolean isPanicking() {
        return false;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }
}