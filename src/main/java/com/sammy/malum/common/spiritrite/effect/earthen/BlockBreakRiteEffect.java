package com.sammy.malum.common.spiritrite.effect.earthen;

import com.sammy.malum.common.entity.activator.rite.*;
import com.sammy.malum.core.systems.rite.effect.*;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class BlockBreakRiteEffect extends SpiritRiteBlockEffect {

    public BlockBreakRiteEffect() {
        super(SpiritRiteEffectTag.GREATER_RITE);
    }

    @Override
    public void applyEffect(ServerLevel level, BlockRiteEffectActivator entity, BlockState state, BlockPos pos, float impact) {
        boolean canBreak = !state.isAir() && state.getDestroySpeed(level, pos) != -1;
        if (canBreak) {
            var blockentity = state.hasBlockEntity() ? level.getBlockEntity(pos) : null;
            Block.dropResources(state, level, pos, blockentity, entity, ItemStack.EMPTY);
            playBreakSound(level, state, pos, 0.7f);
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
            createEffect(level, pos, EARTHEN_SPIRIT, ELDRITCH_SPIRIT);
        }
    }

    public static void playBreakSound(ServerLevel level, BlockState state, BlockPos pos, float pitch) {
        var soundType = state.getSoundType(level, pos, null);
        level.playLocalSound(pos, soundType.getBreakSound(), SoundSource.BLOCKS, (soundType.getVolume() + 1.0F) / 2.0F, soundType.getPitch() * pitch, false);
    }
}
