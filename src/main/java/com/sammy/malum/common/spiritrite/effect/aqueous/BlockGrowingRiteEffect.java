package com.sammy.malum.common.spiritrite.effect.aqueous;

import com.sammy.malum.core.systems.rite.effect.*;
import com.sammy.malum.registry.common.MalumParticleEffectTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class BlockGrowingRiteEffect extends SpiritRiteBlockEffect {

    public BlockGrowingRiteEffect() {
        super(SpiritRiteEffectTag.GREATER_RITE);
    }

    @Override
    public void applyEffect(ServerLevel level, BlockState state, BlockPos pos) {
        if (level.random.nextFloat() <= 0.06f) {
            var block = state.getBlock();
            if (block instanceof FarmBlock) {
                var abovePos = pos.above();
                var aboveState = level.getBlockState(abovePos);
                if (aboveState.is(BlockTags.CROPS)) {
                    int tickCount = 5 + level.random.nextInt(3);
                    for (int i = 0; i < tickCount; i++) {
                        state.randomTick(level, abovePos, level.random);
                    }
                }
            }
            else if (block instanceof BonemealableBlock bonemealableBlock) {
                if (!bonemealableBlock.isValidBonemealTarget(level, pos, state)){
                    return;
                }
                if (!bonemealableBlock.isBonemealSuccess(level, level.random, pos, state)) {
                    return;
                }
                bonemealableBlock.performBonemeal(level, level.random, pos, state);
            }
            var particlePos = state.canOcclude() ? pos : pos.below();
            createEffect(level, MalumParticleEffectTypes.BLOCK_GROW_RITE_EFFECT, particlePos, AQUEOUS_SPIRIT);
        }
    }
}
