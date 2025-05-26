package com.sammy.malum.common.item.curiosities.curios.runes.miracle;

import com.sammy.malum.common.item.curiosities.curios.runes.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.registry.common.MalumMobEffects;
import com.sammy.malum.registry.common.MalumSpiritTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import team.lodestar.lodestone.helpers.EntityHelper;
import team.lodestar.lodestone.handlers.*;

import java.util.function.Consumer;

public class RuneReactiveShieldingItem extends AbstractRuneCurioItem implements ItemEventHandler.IEventResponder {

    public RuneReactiveShieldingItem(Properties builder) {
        super(builder, MalumSpiritTypes.EARTHEN_SPIRIT);
    }

    @Override
    public void addExtraTooltipLines(Consumer<Component> consumer) {
        consumer.accept(ComponentHelper.positiveCurioEffect("attacked_resistance"));
    }

    @Override
    public void incomingDamageEvent(LivingDamageEvent.Pre event, LivingEntity attacker, LivingEntity attacked, ItemStack stack) {
        var shielding = MalumMobEffects.REACTIVE_SHIELDING;
        var instance = attacked.getEffect(shielding);
        var level = attacked.level();
        if (level.random.nextFloat() < 0.5f) {
            if (instance == null) {
                attacked.addEffect(new MobEffectInstance(shielding, 80, 0, true, true, true));
            }
        } else {
            if(instance == null) {
                return;
            }
            EntityHelper.amplifyEffect(instance, attacked, 1, 3);
            EntityHelper.extendEffect(instance, attacked, 40, 100);
        }
    }
}
