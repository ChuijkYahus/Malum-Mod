package com.sammy.malum.common.block.curiosities.redstone.wavemaker;

import com.sammy.malum.common.block.curiosities.redstone.SpiritDiodeBlock;
import com.sammy.malum.registry.common.MalumSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class WaveMakerBlock extends SpiritDiodeBlock<WaveMakerBlockEntity> {
    public WaveMakerBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if (oldState.getBlock().equals(this)) {
            return;
        }
        level.scheduleTick(pos, this, 20);
    }

    @Override
    public int redstoneTicksUntilUpdate(Level level, BlockPos pos, BlockState state, WaveMakerBlockEntity diode, int newInput) {
        return diode.inverted ? 5 : super.redstoneTicksUntilUpdate(level, pos, state, diode, newInput);
    }

    @Override
    public boolean processUpdate(Level level, BlockPos pos, BlockState state, WaveMakerBlockEntity diode, int signal) {
        diode.outputSignal = signal;

        if (!diode.inverted) {
            level.playSound(null, pos, MalumSoundEvents.WAVEMAKER_PULSE.get(), SoundSource.BLOCKS, 0.3f, 1.8f);
            emitRedstoneParticles(level, pos);
        }
        diode.inverted = !diode.inverted;

        updateState(level, pos, state, diode);
        return true;
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        // Will check during update loop anyway
    }

    @Override
    public boolean shouldUpdateWhenNeighborChanged(Level level, BlockPos pos, BlockState state, WaveMakerBlockEntity diode, int newInput) {
        return false;
    }
}
