package com.sammy.malum.common.geas.deliverance;

import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.events.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;

import java.util.function.*;

public class UnsightedResistance extends GeasEffect {

    public UnsightedResistance() {
        super(MalumGeasEffectTypeRegistry.OATH_OF_UNSIGHTED_RESISTANCE.get());
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("malignant_crit_execution"));
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("malignant_crit_weakness"));
        super.addTooltipComponents(entity, tooltipAcceptor, tooltipFlag);
    }


    @Override
    public void malignantCritEvent(MalignantCritEvent event, LivingEntity attacker) {
        final LivingEntity target = event.getLivingEntity();
        if (target.getHealth() < target.getMaxHealth() * 0.2f) {
            event.setNewDamage(event.getNewDamage() * 4f);
        }
        else {
            event.setNewDamage(event.getNewDamage() * 0.6f);
        }
    }
}
