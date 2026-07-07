package com.sammy.malum.common.item.curiosities.curios.sets.rotten;

import com.sammy.malum.common.effect.gluttony.*;
import com.sammy.malum.common.item.*;
import com.sammy.malum.common.item.curiosities.curios.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.events.*;
import com.sammy.malum.registry.common.MalumParticleEffectTypes;
import com.sammy.malum.registry.common.sound.*;
import com.sammy.malum.visual_effects.networked.gluttony.AbsorbGluttonyParticleEffect;
import net.minecraft.network.chat.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.util.*;
import net.minecraft.world.entity.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.modules.core.easing.Easing;

import java.util.function.*;

public class CurioStarvedBelt extends MalumCurioItem implements IMalumEventResponder {

    public CurioStarvedBelt(Properties builder) {
        super(builder, MalumTrinketFamily.ROTTEN);
    }

    @Override
    public void addExtraTooltipLines(Consumer<Component> consumer) {
        consumer.accept(TooltipComponentHelper.positiveCurioEffect("spirits_gluttony"));
    }

    @Override
    public void spiritCollectionEvent(CollectSpiritEvent event, LivingEntity collector, double arcaneResonance) {
        if (collector.level() instanceof ServerLevel serverLevel) {
            int limit = Mth.floor(arcaneResonance * 5);
            GluttonyEffect.applyGluttony(collector, b -> b
                    .setDuration(600)
                    .setAmplifierGain(1)
                    .setAmplifierLimit(limit));

            var random = serverLevel.random;
            SoundHelper.playSound(collector, MalumGearSoundEvents.HUNGRY_BELT_FEEDS.get(), 0.7f, Easing.SINE_IN_OUT.asWeighedRandom(random, 1.5f, 2f));
            SoundHelper.playSound(collector, SoundEvents.GENERIC_EAT, 0.7f, Easing.SINE_IN_OUT.asWeighedRandom(random, 0.8f, 1.2f));
            createGluttonyVFX(serverLevel, collector, 0.5f);
        }
    }

    public static void createGluttonyVFX(ServerLevel serverLevel, LivingEntity target, float potency) {
        var position = target.position().add(0, target.getBbHeight() / 2f, 0);
        MalumParticleEffectTypes.GLUTTONY_ABSORB.createEffect(position)
                .customData(new AbsorbGluttonyParticleEffect.AbsorbGluttonyEffectData(potency))
                .spawn(serverLevel);
    }

}