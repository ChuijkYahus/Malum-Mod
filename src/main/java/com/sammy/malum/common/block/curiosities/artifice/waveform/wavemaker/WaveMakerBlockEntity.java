package com.sammy.malum.common.block.curiosities.artifice.waveform.wavemaker;

import com.sammy.malum.common.block.curiosities.artifice.waveform.SpiritDiodeBlockEntity;
import com.sammy.malum.registry.common.block.MalumBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.*;
import net.minecraft.world.level.block.state.BlockState;

public class WaveMakerBlockEntity extends SpiritDiodeBlockEntity {

    public boolean inverted;

    public WaveMakerBlockEntity(BlockPos pos, BlockState state) {
        super(MalumBlockEntities.WAVEMAKER.get(), pos, state);
    }

    @Override
    public void updateAnimation(ServerLevel serverLevel, BlockPos pos, int inputSignal) {
        if (getOutputSignal() == inputSignal) {
            return;
        }
        super.updateAnimation(serverLevel, pos, inputSignal);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putBoolean("inverted", inverted);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        inverted = pTag.getBoolean("inverted");
    }

    @Override
    public int getOutputSignal() {
        int rawSignal = super.getOutputSignal();
        return inverted ? 15 - rawSignal : rawSignal;
    }
}
