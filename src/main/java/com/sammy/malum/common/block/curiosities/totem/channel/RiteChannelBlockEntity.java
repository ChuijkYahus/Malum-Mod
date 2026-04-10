package com.sammy.malum.common.block.curiosities.totem.channel;

import com.sammy.malum.common.block.curiosities.totem.*;
import com.sammy.malum.common.entity.activator.rite.*;
import com.sammy.malum.registry.common.block.*;
import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.world.level.block.state.*;
import team.lodestar.lodestone.modules.toolkit.blockentity.*;

public class RiteChannelBlockEntity extends LodestoneBlockEntity implements RiteSparkInteractable {

    public RiteChannelBlockEntity(LodestoneBlockEntityType<? extends RiteChannelBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public RiteChannelBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.RITE_CHANNEL.get(), pos, state);
    }

    @Override
    public void travel(ServerLevel level, BlockRiteEffectActivator spark) {
    }
}