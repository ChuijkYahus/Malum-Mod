package com.sammy.malum.core.systems.rite.effect;

import com.sammy.malum.core.systems.spirit.type.SpiritLike;
import com.sammy.malum.registry.common.MalumParticleEffectTypes;
import com.sammy.malum.visual_effects.networked.MalumNetworkedParticleEffectType;
import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.world.level.block.state.*;

import java.util.Arrays;
import java.util.List;

import static com.sammy.malum.visual_effects.networked.MalumNetworkedParticleEffectColorData.fromSpirits;

public abstract class SpiritRiteBlockEffect extends SpiritRiteEffect {

    protected SpiritRiteBlockEffect() {
        super(SpiritRiteEffectTag.LOCUS_EFFECT);
    }

    public abstract void applyEffect(ServerLevel level, BlockState state, BlockPos pos);

    protected void createEffect(ServerLevel level, BlockPos target, SpiritLike... spirits) {
        createEffect(level, target, Arrays.asList(spirits));
    }

    protected void createEffect(ServerLevel level, BlockPos target, List<? extends SpiritLike> spirits) {
        createEffect(level, MalumParticleEffectTypes.BLOCK_RITE_EFFECT, target, spirits);
    }

    protected void createEffect(ServerLevel level, MalumNetworkedParticleEffectType<?> effect, BlockPos target, SpiritLike... spirits) {
        createEffect(level, effect, target, Arrays.asList(spirits));
    }

    protected void createEffect(ServerLevel level, MalumNetworkedParticleEffectType<?> effect, BlockPos target, List<? extends SpiritLike> spirits) {
        effect.createEffect(target)
                .color(fromSpirits(spirits))
                .spawn(level);
    }
}