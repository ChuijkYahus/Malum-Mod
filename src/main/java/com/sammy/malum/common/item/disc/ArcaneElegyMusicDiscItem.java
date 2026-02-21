package com.sammy.malum.common.item.disc;

import com.sammy.malum.registry.common.sound.MalumSoundEvents;
import net.minecraft.world.item.*;

public class ArcaneElegyMusicDiscItem extends Item {

    public ArcaneElegyMusicDiscItem(Properties builder) {
        super(builder.jukeboxPlayable(MalumSoundEvents.ARCANE_ELEGY_KEY));
    }
}