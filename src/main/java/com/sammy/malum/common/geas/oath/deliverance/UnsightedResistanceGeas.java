package com.sammy.malum.common.geas.oath.deliverance;

import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.events.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import team.lodestar.lodestone.helpers.*;

import java.util.function.*;

public class UnsightedResistanceGeas extends GeasEffect {

    public UnsightedResistanceGeas() {
        super(MalumGeasEffectTypes.OATH_OF_UNSIGHTED_RESISTANCE.get());
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("malignant_crit_reinforcement"));
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("malignant_crit_reduced_damage"));
        super.addTooltipComponents(entity, tooltipAcceptor, tooltipFlag);
    }

    @Override
    public void finalizedMalignantCritEvent(MalignantCritEvent.Post event, LivingEntity attacker) {
        event.setNewDamage(event.getNewDamage() * 0.8f);
        var shielding = MalumMobEffects.UNSIGHTED_RESISTANCE;
        var instance = attacker.getEffect(shielding);
        if (instance == null) {
            attacker.addEffect(new MobEffectInstance(shielding, 100, 0, true, true, true));
        } else {
            EntityHelper.amplifyEffect(instance, attacker, 1, 9);
            EntityHelper.extendEffect(instance, attacker, 40, 400);
        }
    }
}