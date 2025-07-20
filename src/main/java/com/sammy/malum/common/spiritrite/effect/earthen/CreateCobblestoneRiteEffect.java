package com.sammy.malum.common.spiritrite.effect.earthen;

import com.sammy.malum.core.systems.rite.effect.SpiritRiteBlockEffect;
import com.sammy.malum.registry.common.MalumParticleEffectTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.EARTHEN_SPIRIT;
import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.ELDRITCH_SPIRIT;

public class CreateCobblestoneRiteEffect extends SpiritRiteBlockEffect {

    public CreateCobblestoneRiteEffect() {
        super();
    }

    @Override
    public void applyEffect(ServerLevel level, BlockState state, BlockPos pos) {
        boolean canPlace = state.isAir() || state.canBeReplaced();
        if (canPlace) {
            BlockState cobblestone = Blocks.COBBLESTONE.defaultBlockState();
            level.setBlockAndUpdate(pos, cobblestone);
            level.levelEvent(2001, pos, Block.getId(cobblestone));
            createEffect(level, pos, EARTHEN_SPIRIT);
        }
    }
}
