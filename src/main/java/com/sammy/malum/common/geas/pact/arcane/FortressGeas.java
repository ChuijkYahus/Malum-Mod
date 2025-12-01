package com.sammy.malum.common.geas.pact.arcane;

import com.google.common.collect.*;
import com.sammy.malum.core.helpers.ComponentHelper;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.item.TooltipFlag;

import java.util.function.Consumer;

public class FortressGeas extends GeasEffect {

    public FortressGeas() {
        super(MalumGeasEffectTypes.PACT_OF_THE_FORTRESS.get());
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> createAttributeModifiers(LivingEntity entity, Multimap<Holder<Attribute>, AttributeModifier> modifiers) {
        addAttributeModifier(modifiers, MalumAttributes.SOUL_WARD_CAPACITY, 6, AttributeModifier.Operation.ADD_VALUE);
        addAttributeModifier(modifiers, MalumAttributes.SOUL_WARD_INTEGRITY, 0.5f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(modifiers, MalumAttributes.SOUL_WARD_RECOVERY_RATE, -0.5f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        return modifiers;
    }
}
