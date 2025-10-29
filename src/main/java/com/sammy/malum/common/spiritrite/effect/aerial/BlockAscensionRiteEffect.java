package com.sammy.malum.common.spiritrite.effect.aerial;

import com.sammy.malum.common.entity.activator.*;
import com.sammy.malum.core.systems.rite.effect.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.world.entity.item.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import team.lodestar.lodestone.helpers.*;

import java.util.*;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class BlockAscensionRiteEffect extends SpiritRiteBlockEffect {

    public BlockAscensionRiteEffect() {
        super(SpiritRiteEffectTag.GREATER_RITE);
    }

    @Override
    public void applyEffect(ServerLevel level, BlockRiteEffectActivatorEntity entity, BlockState state, BlockPos pos, float impact) {
        var stateBelow = level.getBlockState(pos.below());
        if (FallingBlock.isFree(stateBelow) || !stateBelow.canOcclude() || stateBelow.is(net.minecraft.tags.BlockTags.SLABS)) {
            if (!state.isAir() && level.getBlockEntity(pos) == null && BlockGravityRiteEffect.canSilkTouch(level, pos, state)) {
//                FallingBlockEntity.fall(level, pos, state);

//                createEffect(level, MalumParticleEffectTypes.BLOCK_FALL_RITE_EFFECT, pos, AERIAL_SPIRIT);
//                level.playSound(null, pos, MalumSoundEvents.TOTEM_BLOCK_GRAVITY.get(), SoundSource.BLOCKS, 0.5f, RandomHelper.randomBetween(level.random, 1.75f, 2f));
            }
        }
    }
}
