package com.sammy.malum.common.block.curiosities.gust_igniter.wind_tunnel;

import com.sammy.malum.common.block.curiosities.gust_igniter.*;
import com.sammy.malum.common.block.curiosities.redstone.*;
import com.sammy.malum.registry.common.block.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import org.jetbrains.annotations.*;
import team.lodestar.lodestone.helpers.*;

import java.util.*;

public class WindTunnelBlockEntity extends AbstractGustGizmoBlockEntity {

    private BlockPos igniterPos;

    public WindTunnelBlockEntity(BlockEntityType<? extends WindTunnelBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public WindTunnelBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.WIND_TUNNEL.get(), pos, state);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if (igniterPos != null) {
            tag.put("igniterPos", NBTHelper.saveBlockPos(igniterPos));
        }
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        igniterPos = NBTHelper.readBlockPos(pTag.getCompound("igniterPos"));
    }

    @Override
    public void setInfo(GustGizmoInfo info) {
        getIgniter().ifPresent(g -> g.setInfo(info));
    }

    @Override
    public InboundInfo<? extends OpenStateBlockEntity> resetState() {
        if (getIgniter().isEmpty()) {
            return new GustGizmoInfo(0, false);
        }
        return getIgniter().get().resetState();
    }

    @Override
    public boolean canTinker() {
        return getIgniter().isPresent();
    }

    @Override
    public OpenStateBlockEntity getTinkeredBlock() {
        var optional = getIgniter();
        if (optional.isPresent()) {
            return optional.get();
        }
        return super.getTinkeredBlock();
    }

    @Override
    public void onBreak(@Nullable Player player) {
        var optional = getIgniter();
        if (optional.isPresent()) {
            var igniter = optional.get();
            level.scheduleTick(igniterPos, igniter.getBlockState().getBlock(), 2);
        }
    }

    public boolean isActive() {
        return WindTunnelBlock.isActive(getBlockState());
    }

    public Optional<GustIgniterBlockEntity> getIgniter() {
        if (igniterPos == null) {
            return Optional.empty();
        }
        if (level.getBlockEntity(igniterPos) instanceof GustIgniterBlockEntity igniter) {
            return Optional.of(igniter);
        }
        return Optional.empty();
    }

    public boolean canIgnite(GustIgniterBlockEntity igniter) {
        if (igniterPos == null) {
            return true;
        }
        return igniter.getBlockPos().equals(igniterPos);
    }

    public int getTunnelLength() {
        return getIgniter().map(GustIgniterBlockEntity::getTunnelLength).orElse(0);
    }

    public boolean isModified() {
        return getIgniter().map(i -> i.modified).orElse(false);
    }

    public void bind(GustIgniterBlockEntity igniter) {
        igniterPos = igniter.getBlockPos();
    }

    public void unbind() {
        igniterPos = null;
    }

    public int findLimit(int igniterStrength) {
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