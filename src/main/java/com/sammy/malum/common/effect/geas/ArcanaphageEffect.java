package com.sammy.malum.common.effect.geas;

import com.sammy.malum.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.resources.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.ai.attributes.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.registry.common.*;
import team.lodestar.wayward_attributes.core.registry.WaywardAttributeTypes;

public class ArcanaphageEffect extends MobEffect {
    public ArcanaphageEffect() {
        super(MobEffectCategory.BENEFICIAL, ColorHelper.getColor(140, 90, 140));
        final ResourceLocation id = MalumMod.malumPath("arcanaphage");
        addAttributeModifier(WaywardAttributeTypes.MAGIC_RESISTANCE, id, 0.02f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(WaywardAttributeTypes.MAGIC_PROFICIENCY, id, 0.02f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

        addAttributeModifier(MalumAttributes.SOUL_WARD_INTEGRITY, id, 0.02f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(MalumAttributes.SOUL_WARD_RECOVERY_RATE, id, 0.02f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(MalumAttributes.SOUL_WARD_RECOVERY_GAIN, id, 0.02f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

        addAttributeModifier(MalumAttributes.CHARGE_DURATION, id, -0.02f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(MalumAttributes.CHARGE_RECOVERY_RATE, id, 0.02f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }
}