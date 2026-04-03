package com.sammy.malum.common.block.storage.pedestal;

import com.sammy.malum.common.block.storage.ItemHolderItemDisplayData;
import com.sammy.malum.common.block.storage.MalumItemHolderBlockEntity;
import com.sammy.malum.common.item.spirit.SpiritShardItem;
import com.sammy.malum.registry.common.block.MalumBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import team.lodestar.lodestone.modules.toolkit.blockentity.LodestoneBlockEntityType;

public class ItemPedestalBlockEntity extends MalumItemHolderBlockEntity {

    public ItemPedestalBlockEntity(LodestoneBlockEntityType<? extends ItemPedestalBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public ItemPedestalBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.ITEM_PEDESTAL.get(), pos, state);
        inventory.attachDisplayData(ItemPedestalItemDisplayData::new);
    }
}