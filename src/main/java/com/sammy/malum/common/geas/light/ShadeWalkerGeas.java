package com.sammy.malum.common.geas.light;

import com.google.common.collect.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.tick.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.registry.common.*;

import java.util.function.*;

public class ShadeWalkerGeas extends LightLevelBasedGeas {

    public ShadeWalkerGeas() {
        super(MalumGeasEffectTypeRegistry.PACT_OF_THE_SHADEWALKER.get());
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag, boolean isInLight) {
        super.addTooltipComponents(entity, tooltipAcceptor, tooltipFlag);
        if (isInLight) {
            tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("burning_sunlight"));
        }
    }

    @Override
    public void update(EntityTickEvent.Pre event, LivingEntity entity) {
        super.update(event, entity);
        if (isInLight && entity.level().getGameTime() % 20L == 0) {

        }
        if (isInLight) {
            final MobEffectInstance instance = entity.getEffect(MobEffects.DARKNESS);
            if (instance != null) {
                EntityHelper.shortenEffect(instance, entity, 2);
            }
            if (entity.level().getGameTime() % 40L == 0) {
                entity.heal(1);
            }
        }
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> createAttributeModifiers(LivingEntity entity, Multimap<Holder<Attribute>, AttributeModifier> modifiers, boolean isInLight) {
        if (isInLight) {
            addAttributeModifier(modifiers, AttributeRegistry.HEALING_MULTIPLIER, 0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
            addAttributeModifier(modifiers, LodestoneAttributes.MAGIC_RESISTANCE, 0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
            addAttributeModifier(modifiers, Attributes.ARMOR, 0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
            return modifiers;
        }
        addAttributeModifier(modifiers, Attributes.ATTACK_SPEED, -0.5f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        return modifiers;
    }
}