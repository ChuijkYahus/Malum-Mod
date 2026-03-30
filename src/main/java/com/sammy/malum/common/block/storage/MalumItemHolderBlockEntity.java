package com.sammy.malum.common.block.storage;

import com.sammy.malum.common.block.*;
import com.sammy.malum.common.item.spirit.*;
import com.sammy.malum.visual_effects.*;
import net.minecraft.core.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.phys.*;
import team.lodestar.lodestone.modules.toolkit.blockentity.*;
import team.lodestar.lodestone.modules.toolkit.inventory.LodestoneItemStackHandler;

public abstract class MalumItemHolderBlockEntity extends ItemHolderBlockEntity implements IMalumSpecialItemAccessPoint {

    public MalumItemHolderBlockEntity(LodestoneBlockEntityType<? extends MalumItemHolderBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        inventory = MalumBlockItemStackHandler.create(this, 1).build();
    }

    @Override
    public LodestoneItemStackHandler getSuppliedInventory() {
        return inventory;
    }

    @Override
    public Vec3 getItemPos(float partialTicks) {
        final BlockPos blockPos = getBlockPos();
        final Vec3 offset = getItemOffset(partialTicks);
        return new Vec3(blockPos.getX() + offset.x, blockPos.getY() + offset.y, blockPos.getZ() + offset.z);
    }

    @Override
    public BlockPos getAccessPointBlockPos() {
        return worldPosition;
    }

    @Override
    public void clientTick(Level level) {
        if (inventory.getStackInSlot(0).getItem() instanceof SpiritShardItem item) {
            SpiritLightSpecs.rotatingLightSpecs(level, getItemPos(), item, 0.4f, 2);
        }
    }

    public abstract Vec3 getItemOffset(float partialTicks);
}