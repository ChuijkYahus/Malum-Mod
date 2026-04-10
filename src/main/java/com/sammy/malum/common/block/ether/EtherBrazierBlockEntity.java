package com.sammy.malum.common.block.ether;

import com.sammy.malum.registry.common.block.MalumBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.lang3.mutable.MutableDouble;
import team.lodestar.lodestone.modules.toolkit.blockentity.LodestoneBlockEntityType;

public class EtherBrazierBlockEntity extends EtherBlockEntity{

    public EtherBrazierBlockEntity(LodestoneBlockEntityType<? extends EtherBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public EtherBrazierBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.ETHER_BRAZIER.get(), pos, state);
    }

    @Override
    public void modifyParticleCenter(MutableDouble x, MutableDouble y, MutableDouble z) {
        y.subtract(0.05f);
    }
}
