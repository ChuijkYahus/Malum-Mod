package com.sammy.malum.common.geas.oath.staff;

import com.google.common.collect.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.item.*;

import java.util.function.*;

public class OvereagerFistGeas extends GeasEffect {

    public OvereagerFistGeas() {
        super(MalumGeasEffectTypes.OATH_OF_THE_OVEREAGER_FIST.get());
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        super.addTooltipComponents(entity, tooltipAcceptor, tooltipFlag);
        tooltipAcceptor.accept(TooltipComponentHelper.negativeGeasEffect("staff_autofire"));
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> createAttributeModifiers(LivingEntity entity, Multimap<Holder<Attribute>, AttributeModifier> modifiers) {
        addAttributeModifier(modifiers, MalumAttributes.CHARGE_DURATION, -0.5f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        return modifiers;
    }
}
