package com.sammy.malum.common.block.curiosities.artifice.soul_link;

import com.sammy.malum.common.block.curiosities.artifice.ArtificeTinkeringInfo;
import com.sammy.malum.common.block.curiosities.artifice.ConfigurableArtificeBlockEntity;
import com.sammy.malum.common.block.curiosities.artifice.waveform.SpiritDiodeConfigurationInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;
import team.lodestar.lodestone.modules.core.easing.Easing;
import team.lodestar.lodestone.modules.toolkit.blockentity.LodestoneBlockEntityType;

public class SoulLinkBlockEntity extends ConfigurableArtificeBlockEntity {

    public float delta = 0;

    public SoulLinkBlockEntity(LodestoneBlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public SoulLinkConfigurationInfo defaultTinkeringState() {
        return new SoulLinkConfigurationInfo(delta);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        delta = pTag.getFloat("delta");
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putFloat("delta", delta);
    }

    @Override
    public void setInfo(ArtificeTinkeringInfo info) {
        if (info instanceof SoulLinkConfigurationInfo soulLinkConfigurationInfo) {
            delta = soulLinkConfigurationInfo.delta();
        }
    }

    public int getOutputSignal(ServerLevel level) {
        return 0;
    }

    public int getTickInterval(ServerLevel level) {
        delta = Mth.clamp(delta, 0, 1);
        return (int) Easing.QUAD_IN.lerp(delta, 2, 160);
    }

    public int getRange(ServerLevel level) {
        delta = Mth.clamp(delta, 0, 1);
        return (int) Easing.QUAD_OUT.lerp(delta, 4, 40);
    }
}