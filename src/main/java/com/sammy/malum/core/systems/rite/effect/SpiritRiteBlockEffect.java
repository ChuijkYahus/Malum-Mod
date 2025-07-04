package com.sammy.malum.core.systems.rite.effect;

import com.sammy.malum.core.systems.rite.*;
import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.block.state.*;

public abstract class SpiritRiteBlockEffect extends SpiritRiteEffect {

    protected SpiritRiteBlockEffect() {
        super(SpiritRiteEffectCategory.LOCUS);
    }

    public abstract void applyEffect(ServerLevel level, BlockState state, BlockPos pos);
}
