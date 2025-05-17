package com.sammy.malum.common.geas.pact.earthen;

import com.google.common.collect.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.tick.*;

import java.util.function.*;

public class ContentednessGeas extends GeasEffect {

    private int foodLevel = 20;
    public ContentednessGeas() {
        super(MalumGeasEffectTypeRegistry.PACT_OF_CONTENTEDNESS.get());
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("high_hunger_more_armor"));
        super.addTooltipComponents(entity, tooltipAcceptor, tooltipFlag);
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("low_hunger_less_armor"));
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> createAttributeModifiers(LivingEntity entity, Multimap<Holder<Attribute>, AttributeModifier> modifiers) {
        float multiplier = foodLevel >= 10 ? 0.2f : -0.5f;
        if (foodLevel > 6 && foodLevel < 14) {
            multiplier = 0f;
        }
        addAttributeModifier(modifiers, Attributes.ARMOR, multiplier, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(modifiers, Attributes.ARMOR_TOUGHNESS, multiplier, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        return modifiers;
    }

    @Override
    public void update(EntityTickEvent.Pre event, LivingEntity entity) {
        if (entity instanceof Player player) {
            int oldFoodLevel = foodLevel;
            foodLevel = player.getFoodData().getFoodLevel();
            if (oldFoodLevel != foodLevel) {
                setDirty();
            }
        }
    }
}