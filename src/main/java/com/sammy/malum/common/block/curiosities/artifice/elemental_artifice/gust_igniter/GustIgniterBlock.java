package com.sammy.malum.common.block.curiosities.artifice.elemental_artifice.gust_igniter;

import com.sammy.malum.common.block.curiosities.artifice.elemental_artifice.CaptureCompatibleArtificeBlock;
import com.sammy.malum.common.block.curiosities.artifice.elemental_artifice.wind_tunnel.WindTunnelBlock;
import net.minecraft.core.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.state.*;

public class GustIgniterBlock extends CaptureCompatibleArtificeBlock<GustIgniterBlockEntity> {

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

    @Override
    public void activate(Level level, BlockPos pos, boolean powered) {
        if (!(level.getBlockEntity(pos) instanceof GustIgniterBlockEntity igniter)) {
            return;
        }
        igniter.activate(powered);
    }
}