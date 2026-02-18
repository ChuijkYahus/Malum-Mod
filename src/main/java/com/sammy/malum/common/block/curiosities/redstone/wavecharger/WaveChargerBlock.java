package com.sammy.malum.common.block.curiosities.redstone.wavecharger;

import com.sammy.malum.common.block.curiosities.redstone.SpiritDiodeBlock;
import com.sammy.malum.registry.common.sound.MalumSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class WaveChargerBlock extends SpiritDiodeBlock<WaveChargerBlockEntity> {

    public WaveChargerBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean processUpdate(Level level, BlockPos pos, BlockState state, WaveChargerBlockEntity diode, int cachedSignal, int liveSignal) {
        int startingSignal = diode.outputSignal;
        if (startingSignal > liveSignal) {
            diode.outputSignal--;
            level.playSound(null, pos, MalumSoundEvents.WAVECHARGER_RELEASE.get(), SoundSource.BLOCKS);
        } else if (startingSignal < liveSignal) {
            diode.outputSignal++;
            level.playSound(null, pos, MalumSoundEvents.WAVECHARGER_CHARGE.get(), SoundSource.BLOCKS);
        } else {
            return false;
        }

        updateState(level, pos, state, diode);

        return diode.outputSignal != liveSignal;
    }

    @Override
    public boolean shouldUpdateWhenNeighborChanged(Level level, BlockPos pos, BlockState state, WaveChargerBlockEntity diode, int liveSignal) {
        return liveSignal != diode.outputSignal;
    }
}
