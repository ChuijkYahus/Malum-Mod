package com.sammy.malum.common.geas;

import com.google.common.collect.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.core.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;

public class BlightEaterCreed extends GeasEffect {
    public BlightEaterCreed() {
        super(MalumGeasEffectTypeRegistry.CREED_OF_THE_BLIGHT_EATER.get());
    }
    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> createAttributeModifiers(LivingEntity entity, Multimap<Holder<Attribute>, AttributeModifier> modifiers) {
        addAttributeModifier(modifiers, AttributeRegistry.GEAS_LIMIT, 1, AttributeModifier.Operation.ADD_VALUE);
        return modifiers;
    }
}
