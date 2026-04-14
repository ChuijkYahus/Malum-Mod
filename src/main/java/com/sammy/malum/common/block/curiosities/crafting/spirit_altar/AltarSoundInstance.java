package com.sammy.malum.common.block.curiosities.crafting.spirit_altar;

import com.sammy.malum.registry.common.sound.*;
import team.lodestar.lodestone.systems.sound.*;

public class AltarSoundInstance extends CachedBlockEntitySoundInstance<SpiritAltarBlockEntity> {
    public AltarSoundInstance(SpiritAltarBlockEntity blockEntity, float volume, float pitch) {
        super(blockEntity, MalumSoundEvents.ALTAR_LOOP, volume, pitch);
        this.pitch = 0.8f;
    }

    @Override
    public void tick() {
        if (!blockEntity.isCrafting) {
            stop();
        }
        super.tick();
    }

    public static void playSound(SpiritAltarBlockEntity blockEntity) {
        playSound(blockEntity, new AltarSoundInstance(blockEntity, 1, 1));
    }
}