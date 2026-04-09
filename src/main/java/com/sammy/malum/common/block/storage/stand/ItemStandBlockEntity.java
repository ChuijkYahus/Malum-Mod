package com.sammy.malum.common.block.storage.stand;

import com.sammy.malum.common.block.storage.*;
import com.sammy.malum.registry.common.content.block.*;
import net.minecraft.core.*;
import net.minecraft.world.level.block.state.*;
import team.lodestar.lodestone.modules.toolkit.blockentity.LodestoneBlockEntityType;


public class ItemStandBlockEntity extends MalumItemHolderBlockEntity {

    public ItemStandBlockEntity(LodestoneBlockEntityType<? extends ItemStandBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public ItemStandBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.ITEM_STAND.get(), pos, state);
        inventory.attachDisplayData(ItemStandDisplayData::new);
    }
}
