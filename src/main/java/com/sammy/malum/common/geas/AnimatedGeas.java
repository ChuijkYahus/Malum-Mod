package com.sammy.malum.common.geas;

import com.google.common.collect.Multimap;
import com.sammy.malum.core.handlers.GeasEffectHandler;
import com.sammy.malum.core.helpers.ComponentHelper;
import com.sammy.malum.core.systems.geas.GeasEffect;
import com.sammy.malum.registry.common.AttributeRegistry;
import com.sammy.malum.registry.common.MalumGeasEffectTypeRegistry;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.TooltipFlag;

import java.util.function.Consumer;

public class AnimatedGeas extends GeasEffect {

    public AnimatedGeas() {
        super(MalumGeasEffectTypeRegistry.PACT_OF_THE_ANIMATED.get());
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        super.addTooltipComponents(entity, tooltipAcceptor, tooltipFlag);
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("faster_natural_healing"));
    }
    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> createAttributeModifiers(LivingEntity entity, Multimap<Holder<Attribute>, AttributeModifier> modifiers) {
        addAttributeModifier(modifiers, AttributeRegistry.HEALING_MULTIPLIER, 0.25f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(modifiers, Attributes.MAX_HEALTH, -0.25f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        return modifiers;
    }

    public static int accelerateNaturalHealing(LivingEntity entity) {
        if (GeasEffectHandler.hasGeasEffect(entity, MalumGeasEffectTypeRegistry.PACT_OF_THE_ANIMATED)) {
            return entity.getHealth() < entity.getMaxHealth()/2f ? 2 : 1;
        }
        return 0;
    }
}
