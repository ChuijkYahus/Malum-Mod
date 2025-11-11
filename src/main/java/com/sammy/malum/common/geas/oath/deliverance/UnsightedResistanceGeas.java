package com.sammy.malum.common.geas.oath.deliverance;

import com.sammy.malum.common.data.attachment.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.events.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;

import java.util.function.*;

public class UnsightedResistanceGeas extends GeasEffect {

    public UnsightedResistanceGeas() {
        super(MalumGeasEffectTypes.OATH_OF_UNSIGHTED_RESISTANCE.get());
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("malignant_crit_aegis_rerouting"));
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("malignant_crit_reduced_damage"));
        super.addTooltipComponents(entity, tooltipAcceptor, tooltipFlag);
    }

    @Override
    public void finalizedMalignantCritEvent(MalignantCritEvent.Post event, LivingEntity attacker) {
        var data = attacker.getData(MalumAttachmentTypes.MALIGNANT_INFLUENCE);
        if (data.getMalignantAegis() < MalignantInfluenceData.getMalignantAegisCapacity(attacker)) {
            event.setNewDamage(event.getNewDamage() * 0.5f);
            data.recoverAegis(attacker, 1);
        }
    }
}