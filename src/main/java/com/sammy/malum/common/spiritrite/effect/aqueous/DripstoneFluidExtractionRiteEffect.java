package com.sammy.malum.common.spiritrite.effect.aqueous;

import com.sammy.malum.common.entity.activator.*;
import com.sammy.malum.core.systems.rite.effect.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.*;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.*;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockState;
import team.lodestar.lodestone.helpers.*;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class DripstoneFluidExtractionRiteEffect extends SpiritRiteBlockEffect {

    public DripstoneFluidExtractionRiteEffect() {
        super(SpiritRiteEffectTag.GREATER_RITE);
    }

    @Override
    public void applyEffect(ServerLevel level, BlockRiteEffectActivatorEntity entity, BlockState state, BlockPos pos, float impact) {
        if (state.is(BlockTags.CAULDRONS)) {
            var mutable = pos.mutable();
            boolean foundDripstone = false;
            for (int i = 0; i < 8; i++) {
                mutable.move(Direction.UP);
                var dripstoneState = level.getBlockState(mutable);
                if (dripstoneState.getBlock() instanceof PointedDripstoneBlock) {
                    foundDripstone = true;
                    int tickCount = Mth.floor(impact);
                    for (int j = 0; j < tickCount; j++) {
                        dripstoneState.randomTick(level, mutable, level.random);
                    }
                }
            }
            if (foundDripstone) {
                createEffect(level, MalumParticleEffectTypes.BLOCK_INFUSION_RITE_EFFECT, pos, AQUEOUS_SPIRIT, ELDRITCH_SPIRIT);
                level.playSound(null, pos, MalumSoundEvents.TOTEM_BLOCK_SAP.get(), SoundSource.BLOCKS, 0.5f, RandomHelper.randomBetween(level.random, 0.5f, 0.75f));
            }
        }
    }
}
