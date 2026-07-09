package com.sammy.malum.common.block.curiosities.artifice.waveform;

import com.sammy.malum.common.block.curiosities.artifice.ArtificeTinkeringInfo;
import com.sammy.malum.common.block.curiosities.artifice.ConfigurableArtificeBlockEntity;
import com.sammy.malum.common.block.curiosities.artifice.RedstoneTimeIntervalType;
import com.sammy.malum.common.payloads.waveform.SpiritDiodeVisualUpdatePayload;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.*;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.network.PacketDistributor;
import team.lodestar.lodestone.modules.toolkit.blockentity.LodestoneBlockEntityType;

import static net.minecraft.network.chat.Component.translatable;

public class SpiritDiodeBlockEntity extends ConfigurableArtificeBlockEntity {

    public RedstoneTimeIntervalType type = RedstoneTimeIntervalType.REDSTONE_TICK;
    public int frequency = 20;

    public int cachedInputSignal = -1;
    public int outputSignal;

    //TODO: remove all this
    public long visualStartTime;
    public int visualTransitionDuration;
    public int visualTransitionStart;
    public int visualTransitionEnd;

    public SpiritDiodeBlockEntity(LodestoneBlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public SpiritDiodeConfigurationInfo defaultTinkeringState() {
        return new SpiritDiodeConfigurationInfo(type, frequency);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        type = RedstoneTimeIntervalType.valueOf(pTag.getString("type"));
        frequency = pTag.getInt("frequency");

        cachedInputSignal = pTag.getInt("cachedInputSignal");
        outputSignal = pTag.getInt("outputSignal");
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putString("type", type.name());
        tag.putInt("frequency", frequency);

        tag.putInt("cachedInputSignal", cachedInputSignal);
        tag.putInt("outputSignal", outputSignal);
    }

    @Override
    public void setInfo(ArtificeTinkeringInfo info) {
        if (info instanceof SpiritDiodeConfigurationInfo spiritDiodeConfigurationInfo) {
            type = spiritDiodeConfigurationInfo.type();
            frequency = spiritDiodeConfigurationInfo.frequency();
        }
    }

    public int getAdjustedFrequency() {
        return frequency * type.timeScale;
    }

    public int getOutputSignal() {
        return Mth.clamp(outputSignal, 0, 15);
    }

    public void updateVisuals(int outputSignal, int inputSignal, boolean isPowering) {
        this.outputSignal = outputSignal;
        this.cachedInputSignal = inputSignal;
        this.visualStartTime = getLevel().getGameTime();
        this.visualTransitionDuration = getAdjustedFrequency();
        this.visualTransitionStart = isPowering ? 0 : 1;
        this.visualTransitionEnd = 1 - visualTransitionStart;
    }

    public void updateAnimation(ServerLevel serverLevel, BlockPos pos, int inputSignal) {
        int outputSignal = getOutputSignal();
        PacketDistributor.sendToPlayersTrackingChunk(serverLevel,
                new ChunkPos(pos), new SpiritDiodeVisualUpdatePayload(pos, outputSignal, inputSignal, outputSignal == 0));
    }
}