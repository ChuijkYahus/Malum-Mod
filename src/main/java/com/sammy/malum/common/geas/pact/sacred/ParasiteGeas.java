package com.sammy.malum.common.geas.pact.sacred;

import com.google.common.collect.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.events.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import net.minecraft.util.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.item.*;

import java.util.function.*;

public class ParasiteGeas extends GeasEffect {

    public ParasiteGeas() {
        super(MalumGeasEffectTypes.PACT_OF_THE_PARASITE.get());
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        tooltipAcceptor.accept(EffectComponentHelper.positiveGeasEffect("spirits_absorption"));
        super.addTooltipComponents(entity, tooltipAcceptor, tooltipFlag);
    }
    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> createAttributeModifiers(LivingEntity entity, Multimap<Holder<Attribute>, AttributeModifier> modifiers) {
        addAttributeModifier(modifiers, MalumAttributes.HEALING_MULTIPLIER, -0.4f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        return modifiers;
    }

    @Override
    public void spiritCollectionEvent(CollectSpiritEvent event, LivingEntity collector, double arcaneResonance) {
        collector.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 400, Mth.floor(arcaneResonance-1)));
    }
}
