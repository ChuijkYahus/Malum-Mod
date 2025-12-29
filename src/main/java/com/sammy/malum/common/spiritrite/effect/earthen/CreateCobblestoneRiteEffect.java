package com.sammy.malum.common.spiritrite.effect.earthen;

import com.sammy.malum.common.entity.activator.rite.*;
import com.sammy.malum.core.systems.rite.effect.*;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import static com.sammy.malum.common.spiritrite.effect.earthen.BlockBreakRiteEffect.playBreakSound;
import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.EARTHEN_SPIRIT;

public class CreateCobblestoneRiteEffect extends SpiritRiteBlockEffect {

    public CreateCobblestoneRiteEffect() {
        super(SpiritRiteEffectTag.GREATER_RITE);
    }

    @Override
    public void applyEffect(ServerLevel level, BlockRiteEffectActivator entity, BlockState state, BlockPos pos, float impact) {
        boolean canPlace = state.isAir() || state.canBeReplaced();
        if (canPlace) {
            BlockState cobblestone = Blocks.COBBLESTONE.defaultBlockState();
            playBreakSound(level, state, pos, 1.1f);
            level.setBlockAndUpdate(pos, cobblestone);
            createEffect(level, pos, EARTHEN_SPIRIT);
        }
    }
}
