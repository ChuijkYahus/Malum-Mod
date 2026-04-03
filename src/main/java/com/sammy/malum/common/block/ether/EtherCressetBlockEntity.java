package com.sammy.malum.common.block.ether;

import com.sammy.malum.registry.common.block.MalumBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.lang3.mutable.MutableDouble;
import team.lodestar.lodestone.modules.toolkit.blockentity.LodestoneBlockEntityType;

public class EtherCressetBlockEntity extends EtherBlockEntity{

    public EtherCressetBlockEntity(LodestoneBlockEntityType<? extends EtherBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public EtherCressetBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.ETHER_CRESSET.get(), pos, state);
    }

    @Override
    public void modifyParticleCenter(MutableDouble x, MutableDouble y, MutableDouble z) {
        y.add(1.2f);
    }
}
