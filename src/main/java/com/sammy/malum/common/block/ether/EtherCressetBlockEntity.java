package com.sammy.malum.common.block.ether;

import com.sammy.malum.registry.common.block.MalumBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import org.apache.commons.lang3.mutable.MutableDouble;
import team.lodestar.lodestone.modules.toolkit.blockentity.LodestoneBlockEntityType;

public class EtherCressetBlockEntity extends EtherBlockEntity {

    public EtherCressetBlockEntity(LodestoneBlockEntityType<? extends EtherBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public EtherCressetBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.ETHER_CRESSET.get(), pos, state);
    }

    @Override
    public void clientTick(Level level) {
        if (getBlockState().getValue(EtherCressetBlock.HALF).equals(DoubleBlockHalf.LOWER)) {
            return;
        }
        super.clientTick(level);
    }

    @Override
    public void modifyParticleCenter(MutableDouble x, MutableDouble y, MutableDouble z) {
        y.add(0.1f);
    }
}
