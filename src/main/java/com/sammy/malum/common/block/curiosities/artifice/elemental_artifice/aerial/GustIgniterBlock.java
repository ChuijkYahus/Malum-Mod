package com.sammy.malum.common.block.curiosities.artifice.elemental_artifice.aerial;

import com.sammy.malum.common.block.curiosities.artifice.elemental_artifice.base.PrimaryArtificeBlock;
import net.minecraft.core.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.state.*;

public class GustIgniterBlock extends PrimaryArtificeBlock<GustIgniterBlockEntity> {

    public GustIgniterBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canCapture(Level level, BlockState state, BlockPos pos, BlockState connectedTo, BlockPos connectedAt) {
        if (!(connectedTo.getBlock() instanceof WindTunnelBlock)) {
            return false;
        }
        return super.canCapture(level, state, pos, connectedTo, connectedAt);
    }
}