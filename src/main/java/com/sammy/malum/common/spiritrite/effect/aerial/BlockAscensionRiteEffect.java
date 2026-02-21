package com.sammy.malum.common.spiritrite.effect.aerial;

import com.sammy.malum.common.entity.AscendingBlock;
import com.sammy.malum.common.entity.activator.rite.BlockRiteEffectActivator;
import com.sammy.malum.core.systems.rite.effect.SpiritRiteBlockEffect;
import com.sammy.malum.core.systems.rite.effect.SpiritRiteEffectTag;
import com.sammy.malum.registry.common.MalumParticleEffectTypes;
import com.sammy.malum.registry.common.sound.MalumSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import team.lodestar.lodestone.helpers.RandomHelper;

import static com.sammy.malum.common.spiritrite.effect.aerial.BlockGravityRiteEffect.canSilkTouch;
import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.AERIAL_SPIRIT;

public class BlockAscensionRiteEffect extends SpiritRiteBlockEffect {

    public BlockAscensionRiteEffect() {
        super(SpiritRiteEffectTag.GREATER_RITE);
    }

    @Override
    public void applyEffect(ServerLevel level, BlockRiteEffectActivator entity, BlockState state, BlockPos pos, float impact) {
        BlockPos posAbove = pos.above();
        BlockState stateAbove = level.getBlockState(posAbove);

        if (FallingBlock.isFree(stateAbove) || !stateAbove.canOcclude() || stateAbove.is(net.minecraft.tags.BlockTags.SLABS)) {
            if (!state.isAir() && level.getBlockEntity(pos) == null && canSilkTouch(level, pos, state)) {
                AscendingBlock.rise(level, pos, state, 50 * impact); // 200 ticks = 10 seconds × impact factor

                createEffect(level, MalumParticleEffectTypes.BLOCK_RITE_EFFECT, pos, AERIAL_SPIRIT);
                level.playSound(null, pos, MalumSoundEvents.TOTEM_BLOCK_GRAVITY.get(), SoundSource.BLOCKS, 0.5f,
                        RandomHelper.randomBetween(level.random, 1.75f, 2f));
            }
        }
    }
}
