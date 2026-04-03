package com.sammy.malum.common.block.curiosities.totem.waveform;

import com.sammy.malum.common.block.curiosities.totem.*;
import com.sammy.malum.registry.common.block.*;
import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import team.lodestar.lodestone.modules.toolkit.blockentity.LodestoneBlockEntityType;

public class WaveformTotemBaseBlockEntity extends TotemBaseBlockEntity {
    public WaveformTotemBaseBlockEntity(LodestoneBlockEntityType<? extends WaveformTotemBaseBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public WaveformTotemBaseBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.WAVEFORM_TOTEM_BASE.get(), pos, state);
    }

    @Override
    public void updateRite(ServerLevel level) {
        if (timerPause > 0 && timer == 1) {
            timerPause--;
            return;
        }
        if (timer > 0) {
            timer--;
            if (timer == 0) {
                notifyObservers();
            }
        }
    }
}
