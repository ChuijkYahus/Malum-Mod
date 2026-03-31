package com.sammy.malum.common.block.storage;

import com.sammy.malum.common.block.curiosities.spirit_altar.SpiritAltarBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import team.lodestar.lodestone.helpers.DataHelper;
import team.lodestar.lodestone.modules.core.easing.Easing;
import team.lodestar.lodestone.modules.toolkit.blockentity.LodestoneBlockEntity;
import team.lodestar.lodestone.modules.toolkit.inventory.ItemStackHandlerItemDisplayData;
import team.lodestar.lodestone.modules.toolkit.inventory.LodestoneItemStackBlockHandler;

public class ItemHolderItemDisplayData extends ItemStackHandlerItemDisplayData {

    public ItemHolderItemDisplayData(LodestoneItemStackBlockHandler parent) {
        super(parent, 0.2f, 0.0125f, 0.01f, 0.025f);
    }

    @Override
    public float getDistanceForItem(ItemDisplayDataEntry item, int index, float total) {
        return Math.max(1 - (item.getAge() / 40f), 0) * 0.25f;
    }

    @Override
    public Vec3 getDisplayCenter(LodestoneBlockEntity parent, float partialTicks) {
        BlockPos pos = parent.getBlockPos();
        return new Vec3(pos.getX() + 0.5f, pos.getY() + 1.25f, pos.getZ() + 0.5f);
    }
}