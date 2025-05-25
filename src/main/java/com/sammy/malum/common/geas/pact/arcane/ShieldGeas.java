package com.sammy.malum.common.geas.pact.arcane;

import com.google.common.collect.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.core.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;

public class ShieldGeas extends GeasEffect {

    public ShieldGeas() {
        super(MalumGeasEffectTypes.PACT_OF_THE_SHIELD.get());
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> createAttributeModifiers(LivingEntity entity, Multimap<Holder<Attribute>, AttributeModifier> modifiers) {
        addAttributeModifier(modifiers, MalumAttributes.SOUL_WARD_CAPACITY, 12, AttributeModifier.Operation.ADD_VALUE);
        addAttributeModifier(modifiers, MalumAttributes.SOUL_WARD_RECOVERY_RATE, 1f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(modifiers, MalumAttributes.SOUL_WARD_INTEGRITY, -0.5f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        return modifiers;
    }
}
