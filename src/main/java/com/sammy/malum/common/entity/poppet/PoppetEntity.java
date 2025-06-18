package com.sammy.malum.common.entity.poppet;

import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.network.syncher.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.ai.control.*;
import net.minecraft.world.entity.ai.navigation.*;
import net.minecraft.world.entity.npc.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.state.*;

public class PoppetEntity extends Entity implements InventoryCarrier {

    protected SimpleContainer inventory = new SimpleContainer(4);

    protected PoppetEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        writeInventoryToTag(compound, registryAccess());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        readInventoryFromTag(compound, registryAccess());
    }

    @Override
    public SimpleContainer getInventory() {
        return inventory;
    }


    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public boolean fireImmune() {
        return true;
    }
}