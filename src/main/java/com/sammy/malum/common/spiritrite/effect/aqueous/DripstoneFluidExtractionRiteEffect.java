package com.sammy.malum.common.spiritrite.effect.aqueous;

import com.sammy.malum.core.systems.rite.effect.SpiritRiteBlockEffect;
import com.sammy.malum.registry.common.MalumParticleEffectTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockState;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class DripstoneFluidExtractionRiteEffect extends SpiritRiteBlockEffect {

    public DripstoneFluidExtractionRiteEffect() {
        super();
    }

    @Override
    public void applyEffect(ServerLevel level, BlockState state, BlockPos pos) {
        if (state.is(BlockTags.CAULDRONS)) {
            BlockPos.MutableBlockPos mutable = pos.mutable();
            boolean foundDripstone = false;
            for (int i = 0; i < 8; i++) {
                mutable.move(Direction.UP);
                BlockState aboveState = level.getBlockState(mutable);
                if (aboveState.getBlock() instanceof PointedDripstoneBlock) {
                    foundDripstone = true;
                    if (level.random.nextFloat() < 0.2f) {
                        int tickCount = 4 + level.random.nextInt(2);
                        for (int j = 0; j < tickCount; j++) {
                            aboveState.randomTick(level, mutable, level.random);
                        }
                    }
                }
            }
            if (foundDripstone) {
                createEffect(level, MalumParticleEffectTypes.BLOCK_INFUSION_RITE_EFFECT, pos, AQUEOUS_SPIRIT, ELDRITCH_SPIRIT);
            }
        }
    }
}
