package com.sammy.malum.common.block.the_device;

import com.sammy.malum.registry.common.MalumSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;

public class TheVessel extends TheDevice {
    public TheVessel(Properties properties) {
        super(properties);
    }

    public void playSound(Level level, BlockPos pos) {
        level.playSound(null, pos, MalumSoundEvents.THE_HEAVENS_SIGN.get(), SoundSource.BLOCKS, 1, 1);
    }
}
