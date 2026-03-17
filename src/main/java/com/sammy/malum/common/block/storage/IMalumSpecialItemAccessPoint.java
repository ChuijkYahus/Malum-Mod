package com.sammy.malum.common.block.storage;

import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import team.lodestar.lodestone.modules.toolkit.inventory.LodestoneItemStackHandler;

public interface IMalumSpecialItemAccessPoint {
    LodestoneItemStackHandler getSuppliedInventory();

    default Vec3 getItemPos() {
        return getItemPos(0);
    }

    Vec3 getItemPos(float partialTicks);

    BlockPos getAccessPointBlockPos();
}
