package com.sammy.malum.common.block.curiosities.soul_brazier;

import com.sammy.malum.registry.common.sound.MalumSoundEvents;
import team.lodestar.lodestone.systems.sound.CachedBlockEntitySoundInstance;

public class BrazierSoundInstance extends CachedBlockEntitySoundInstance<SoulBrazierBlockEntity> {
    public BrazierSoundInstance(SoulBrazierBlockEntity blockEntity, float volume, float pitch) {
        super(blockEntity, MalumSoundEvents.BRAZIER_LOOP, volume, pitch);
        this.pitch = 0.8f;
    }

    @Override
    public void tick() {
        if (blockEntity.isIdle()) {
            stop();
        }
        super.tick();
    }

    public static void playSound(SoulBrazierBlockEntity blockEntity) {
        playSound(blockEntity, new BrazierSoundInstance(blockEntity, 1, 1));
    }
}