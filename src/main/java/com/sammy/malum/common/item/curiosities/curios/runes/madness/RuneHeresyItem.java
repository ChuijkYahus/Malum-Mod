package com.sammy.malum.common.item.curiosities.curios.runes.madness;

import com.sammy.malum.core.helpers.*;
import com.sammy.malum.registry.common.MalumMobEffects;
import com.sammy.malum.registry.common.sound.*;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.living.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.handlers.*;

import java.util.function.Consumer;

public class RuneHeresyItem extends MadnessRuneCurioItem implements ItemEventHandler.IEventResponder {

    public RuneHeresyItem(Properties builder) {
        super(builder, MalumSpiritTypes.ELDRITCH_SPIRIT);
    }

    @Override
    public void addExtraTooltipLines(Consumer<Component> consumer) {
        consumer.accept(TooltipComponentHelper.positiveCurioEffect("silence"));
    }

    @Override
    public void incomingDamageEvent(LivingDamageEvent.Pre event, LivingEntity attacker, LivingEntity attacked, ItemStack stack) {
        if (attacker == null) {
            return;
        }
        Holder<MobEffect> silenced = MalumMobEffects.SILENCED;
        MobEffectInstance effect = attacker.getEffect(silenced);
        if (effect == null) {
            attacker.addEffect(new MobEffectInstance(silenced, 60, 0, true, true, true));
        } else {
            if (attacked.getRandom().nextFloat() < 0.6f) {
                EntityHelper.amplifyEffect(effect, attacker, 1, 19);
            }
            EntityHelper.extendEffect(effect, attacker, 30, 600);
        }
        SoundHelper.playSound(attacked, MalumGearSoundEvents.DRAINING_MOTIF.get(), 1f, 1.5f);
    }
}
