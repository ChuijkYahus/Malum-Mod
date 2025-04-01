package com.sammy.malum.common.block.curiosities.weeping_well.encasement;

import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.material.*;

public class WeepingWellBlock extends Block {
    public WeepingWellBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState pState) {
        return PushReaction.IGNORE;
    }
}
