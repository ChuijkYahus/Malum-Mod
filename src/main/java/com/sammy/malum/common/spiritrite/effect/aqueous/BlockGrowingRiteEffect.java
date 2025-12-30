package com.sammy.malum.common.spiritrite.effect.aqueous;

import com.sammy.malum.common.entity.activator.rite.*;
import com.sammy.malum.core.systems.rite.effect.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.*;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import team.lodestar.lodestone.helpers.*;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class BlockGrowingRiteEffect extends SpiritRiteBlockEffect {

    public BlockGrowingRiteEffect() {
        super(SpiritRiteEffectTag.GREATER_RITE);
    }

    @Override
    public void applyEffect(ServerLevel level, BlockRiteEffectActivator entity, BlockState state, BlockPos pos, float impact) {
        var block = state.getBlock();
        if (block instanceof FarmBlock) {
            var abovePos = pos.above();
            var aboveState = level.getBlockState(abovePos);
            if (aboveState.is(BlockTags.CROPS)) {
                int ticks = 2 * Mth.floor(impact);
                for (int i = 0; i < ticks; i++) {
                    aboveState.randomTick(level, abovePos, level.random);
                }
            }
        } else if (block instanceof BonemealableBlock bonemealableBlock) {
            if (!bonemealableBlock.isValidBonemealTarget(level, pos, state)) {
                return;
            }
            if (!bonemealableBlock.isBonemealSuccess(level, level.random, pos, state)) {
                return;
            }
            bonemealableBlock.performBonemeal(level, level.random, pos, state);
        }
        createEffect(level, MalumParticleEffectTypes.BLOCK_GROW_RITE_EFFECT, pos, AQUEOUS_SPIRIT);
        level.playSound(null, pos, MalumSoundEvents.TOTEM_BLOCK_GROW.get(), SoundSource.BLOCKS, 0.5f, RandomHelper.randomBetween(level.random, 1.75f, 2f));
    }
}
