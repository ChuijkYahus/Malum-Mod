package com.sammy.malum.common.spiritrite.effect.earthen;

import com.sammy.malum.common.entity.activator.*;
import com.sammy.malum.core.systems.rite.effect.*;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.BlockState;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class BlockBreakRiteEffect extends SpiritRiteBlockEffect {

    public BlockBreakRiteEffect() {
        super(SpiritRiteEffectTag.GREATER_RITE);
    }

    @Override
    public void applyEffect(ServerLevel level, BlockRiteEffectActivatorEntity entity, BlockState state, BlockPos pos, float impact) {
        boolean canBreak = !state.isAir() && state.getDestroySpeed(level, pos) != -1;
        if (canBreak) {
            level.destroyBlock(pos, true);
            createEffect(level, pos, EARTHEN_SPIRIT, ELDRITCH_SPIRIT);
        }
    }
}
