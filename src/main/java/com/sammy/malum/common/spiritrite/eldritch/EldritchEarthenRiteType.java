package com.sammy.malum.common.spiritrite.eldritch;

import com.sammy.malum.common.block.curiosities.totem.*;
import com.sammy.malum.core.systems.rite.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.server.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class EldritchEarthenRiteType extends SpiritRiteType {
    public EldritchEarthenRiteType() {
        super("greater_earthen_rite", ELDRITCH_SPIRIT, ARCANE_SPIRIT, EARTHEN_SPIRIT, EARTHEN_SPIRIT);
    }

    @Override
    public OldTotemicRiteEffect getNaturalRiteEffect() {
        return new OldTotemicRiteEffect(OldTotemicRiteEffect.MalumRiteEffectCategory.DIRECTIONAL_BLOCK_EFFECT) {
            @Override
            public void doRiteEffect(TotemBaseBlockEntity totemBase, ServerLevel level) {
                getBlocksAhead(totemBase).forEach(p -> {
                    BlockState state = level.getBlockState(p);
                    boolean canBreak = !state.isAir() && state.getDestroySpeed(level, p) != -1;
                    if (canBreak) {
                        level.destroyBlock(p, true);
                        MalumParticleEffectTypes.BLOCK_RITE_EFFECT
                                .createEffect(p)
                                .color(EARTHEN_SPIRIT)
                                .spawn(level);
                    }
                });
            }
        };
    }

    @Override
    public OldTotemicRiteEffect getCorruptedEffect() {
        return new OldTotemicRiteEffect(OldTotemicRiteEffect.MalumRiteEffectCategory.DIRECTIONAL_BLOCK_EFFECT) {
            @SuppressWarnings("ConstantConditions")
            @Override
            public void doRiteEffect(TotemBaseBlockEntity totemBase, ServerLevel level) {
                getBlocksAhead(totemBase).forEach(p -> {
                    BlockState state = level.getBlockState(p);
                    boolean canPlace = state.isAir() || state.canBeReplaced();
                    if (canPlace) {
                        BlockState cobblestone = Blocks.COBBLESTONE.defaultBlockState();
                        level.setBlockAndUpdate(p, cobblestone);
                        level.levelEvent(2001, p, Block.getId(cobblestone));
                        MalumParticleEffectTypes.BLOCK_RITE_EFFECT
                                .createEffect(p)
                                .color(EARTHEN_SPIRIT)
                                .spawn(level);
                    }
                });
            }
        };
    }
}