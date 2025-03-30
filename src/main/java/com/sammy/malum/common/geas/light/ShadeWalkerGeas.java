package com.sammy.malum.common.geas.light;

import com.google.common.collect.*;
import com.sammy.malum.core.handlers.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.core.systems.spirit.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.tick.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.registry.common.*;

import java.util.*;
import java.util.function.*;

public class ShadeWalkerGeas extends LightLevelBasedGeas {

    public ShadeWalkerGeas() {
        super(MalumGeasEffectTypeRegistry.PACT_OF_THE_SHADEWALKER.get());
    }

    public static void preventDetection(LivingEvent.LivingVisibilityEvent event) {
        var geasEffect = GeasEffectHandler.getGeasEffect(event.getEntity(), MalumGeasEffectTypeRegistry.PACT_OF_THE_SHADEWALKER);
        if (geasEffect instanceof ShadeWalkerGeas shadeWalkerGeas) {
            if (!shadeWalkerGeas.isInLight) {
                event.modifyVisibility(0.3f);
            }
        }
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag, boolean isInLight) {
        if (isInLight) {
            tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("burning_sunlight"));
        }
        else {
            tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("hidden_presence"));
        }
        super.addTooltipComponents(entity, tooltipAcceptor, tooltipFlag, isInLight);
    }

    @Override
    public void update(EntityTickEvent.Pre event, LivingEntity entity) {
        super.update(event, entity);
        var level = entity.level();
        if (isInLight && level.getGameTime() % 20L == 0) {
            entity.igniteForTicks(25);
        }
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> createAttributeModifiers(LivingEntity entity, Multimap<Holder<Attribute>, AttributeModifier> modifiers, boolean isInLight) {
        if (isInLight) {
            addAttributeModifier(modifiers, Attributes.BURNING_TIME, 0.5f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
            return modifiers;
        }
        addAttributeModifier(modifiers, Attributes.MOVEMENT_SPEED, 0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(modifiers, Attributes.ATTACK_SPEED, 0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(modifiers, Attributes.BLOCK_BREAK_SPEED, 0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        return modifiers;
    }
}