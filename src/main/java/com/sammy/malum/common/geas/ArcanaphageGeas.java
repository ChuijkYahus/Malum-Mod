package com.sammy.malum.common.geas;

import com.google.common.collect.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.events.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import net.minecraft.server.level.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.tick.*;

import java.util.function.*;

public class ArcanaphageGeas extends GeasEffect {

    public ArcanaphageGeas() {
        super(MalumGeasEffectTypeRegistry.PACT_OF_THE_ARCANAPHAGE.get());
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> createAttributeModifiers(LivingEntity entity, Multimap<Holder<Attribute>, AttributeModifier> modifiers) {
        addAttributeModifier(modifiers, AttributeRegistry.SPIRIT_SPOILS, 2, AttributeModifier.Operation.ADD_VALUE);
        addAttributeModifier(modifiers, AttributeRegistry.ARCANE_RESONANCE, 1f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        return modifiers;
    }
}
