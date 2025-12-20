package com.sammy.malum.core.systems.rite.effect;

import com.sammy.malum.common.entity.activator.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.visual_effects.networked.MalumNetworkedParticleEffectType;
import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.util.*;
import net.minecraft.world.level.block.state.*;
import team.lodestar.lodestone.helpers.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.*;

import static com.sammy.malum.visual_effects.networked.MalumNetworkedParticleEffectColorData.fromSpirits;

public abstract class SpiritRiteBlockEffect extends SpiritRiteEffect {

    protected SpiritRiteBlockEffect(SpiritRiteEffectTag... tags) {
        this(Arrays.asList(tags));
    }

    protected SpiritRiteBlockEffect(List<SpiritRiteEffectTag> tags) {
        super(Stream.concat(Stream.of(SpiritRiteEffectTag.LOCUS_EFFECT), tags.stream()).toList());
    }

    @Override
    public boolean triggerRiteEffect(ServerLevel level, BlockPos pos, SpiritArcanaType definingSpirit, RiteParameters parameters) {
        var random = level.getRandom();
        Direction direction = parameters.getTotemDirection().orElseThrow();
        BlockRiteEffectActivator entity = new BlockRiteEffectActivator(level, this, pos, direction);
        entity.setSpirit(definingSpirit);
        level.addFreshEntity(entity);
        SoundHelper.playSound(entity, MalumSoundEvents.SPARK_FORMED.get(), 0.5f, Mth.nextFloat(random, 0.9f, 1.1f));
        return true;
    }

    public abstract void applyEffect(ServerLevel level, BlockRiteEffectActivator entity, BlockState state, BlockPos pos, float impact);

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