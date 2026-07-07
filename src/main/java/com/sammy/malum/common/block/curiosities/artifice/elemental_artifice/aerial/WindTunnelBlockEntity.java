package com.sammy.malum.common.block.curiosities.artifice.elemental_artifice.aerial;

import com.sammy.malum.common.block.curiosities.artifice.elemental_artifice.base.SecondaryArtificeBlockEntity;
import com.sammy.malum.registry.common.block.*;
import net.minecraft.core.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import team.lodestar.lodestone.modules.toolkit.blockentity.LodestoneBlockEntityType;

public class WindTunnelBlockEntity extends SecondaryArtificeBlockEntity {

    public WindTunnelBlockEntity(LodestoneBlockEntityType<? extends WindTunnelBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public WindTunnelBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.WIND_TUNNEL.get(), pos, state);
    }

    public int getTunnelLength() {
        return getOwner(GustIgniterBlockEntity.class).map(GustIgniterBlockEntity::getTunnelLength).orElse(0);
    }

    public int clampStrength(int igniterStrength) {
        var direction = getBlockState().getValue(WindTunnelBlock.FACING);
        var mutable = new BlockPos.MutableBlockPos();
        var next = new BlockPos.MutableBlockPos();
        int limiter = 0;
        while (limiter < igniterStrength) {
            mutable.set(getBlockPos()).move(direction, limiter);
            next.set(mutable).move(direction);
            boolean canRender = Block.shouldRenderFace(getBlockState(), level, mutable, direction, next);
            if (!canRender) {
                break;
            }
            limiter++;
        }
        return limiter;
    }
}