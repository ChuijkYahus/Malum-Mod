package com.sammy.malum.common.item.curiosities.curios.sets.rotten;

import com.sammy.malum.common.effect.gluttony.*;
import com.sammy.malum.common.item.*;
import com.sammy.malum.common.item.curiosities.curios.*;
import com.sammy.malum.common.item.food.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.events.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.network.chat.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.util.*;
import net.minecraft.world.entity.*;
import team.lodestar.lodestone.helpers.*;

import java.util.function.*;

public class CurioStarvedBelt extends MalumCurioItem implements IMalumEventResponder {

    public CurioStarvedBelt(Properties builder) {
        super(builder, MalumTrinketType.ROTTEN);
    }

    @Override
    public void addExtraTooltipLines(Consumer<Component> consumer) {
        consumer.accept(ComponentHelper.positiveCurioEffect("spirits_gluttony"));
    }

    @Override
    public void spiritCollectionEvent(CollectSpiritEvent event, LivingEntity collector, double arcaneResonance) {
        if (collector.level() instanceof ServerLevel serverLevel) {
            GluttonyEffect.applyGluttony(collector, b -> b
                    .setInitialData(Mth.floor(600 * arcaneResonance), 0)
                    .setStackingData(0, 1)
                    .setLimitData(0, Mth.floor(arcaneResonance * 5 - 1)));
            var random = serverLevel.random;
            SoundHelper.playSound(collector, MalumSoundEvents.HUNGRY_BELT_FEEDS.get(), 0.7f, RandomHelper.randomBetween(random, 1.5f, 2f));
            SoundHelper.playSound(collector, SoundEvents.GENERIC_EAT, 0.7f, RandomHelper.randomBetween(random, 0.8f, 1.2f));
            ConcentratedGluttonyItem.createGluttonyVFX(serverLevel, collector, 0.5f);
        }
    }

}