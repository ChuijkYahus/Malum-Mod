package com.sammy.malum.common.item.curiosities.curios.sets.rotten;

import com.sammy.malum.common.item.*;
import com.sammy.malum.common.item.curiosities.curios.*;
import com.sammy.malum.common.item.food.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.events.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.neoforged.neoforge.common.*;
import team.lodestar.lodestone.handlers.*;
import team.lodestar.lodestone.helpers.*;

import java.util.function.*;

public class CurioStarvedBelt extends MalumCurioItem implements IMalumEventResponderItem {

    public CurioStarvedBelt(Properties builder) {
        super(builder, MalumTrinketType.ROTTEN);
    }

    @Override
    public void addExtraTooltipLines(Consumer<Component> consumer) {
        consumer.accept(ComponentHelper.positiveCurioEffect("spirits_gluttony"));
    }

    @Override
    public void spiritCollectionEvent(CollectSpiritEvent event, LivingEntity collector, double arcaneResonance) {
        var gluttony = getGluttonyEffectType(collector);
        var effect = collector.getEffect(gluttony);
        if (effect == null) {
            collector.addEffect(new MobEffectInstance(gluttony, 600 + (int) (arcaneResonance * 600), 0, true, true, true));
        } else {
            int limit = (int) ((arcaneResonance) * 5 - 1);
            EntityHelper.amplifyEffect(effect, collector, 1, limit);
        }
        if (collector.level() instanceof ServerLevel serverLevel) {
            var random = serverLevel.random;
            SoundHelper.playSound(collector, SoundRegistry.HUNGRY_BELT_FEEDS.get(), 0.7f, RandomHelper.randomBetween(random, 1.5f, 2f));
            SoundHelper.playSound(collector, SoundEvents.GENERIC_EAT, 0.7f, RandomHelper.randomBetween(random, 0.8f, 1.2f));
            ConcentratedGluttonyItem.createGluttonyVFX(serverLevel, collector, 0.5f);
        }
    }

    public static Holder<MobEffect> getGluttonyEffectType(LivingEntity collector) {
        var event = new ModifyGluttonyPropertiesEvent(collector);
        ItemEventHandler.getEventResponders(collector).forEach(lookup -> lookup.run(IMalumEventResponderItem.class,
                (eventResponderItem, stack) -> eventResponderItem.modifyGluttonyPropertiesEvent(event, collector)));
        NeoForge.EVENT_BUS.post(event);
        return event.effect;
    }
}