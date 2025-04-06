package com.sammy.malum.common.effect.geas;

import com.sammy.malum.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.resources.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.ai.attributes.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.registry.common.*;

public class ArcanaphageEffect extends MobEffect {
    public ArcanaphageEffect() {
        super(MobEffectCategory.BENEFICIAL, ColorHelper.getColor(140, 90, 140));
        final ResourceLocation id = MalumMod.malumPath("arcanaphage");
        addAttributeModifier(LodestoneAttributes.MAGIC_RESISTANCE, id, 0.02f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(LodestoneAttributes.MAGIC_PROFICIENCY, id, 0.02f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

        addAttributeModifier(AttributeRegistry.SOUL_WARD_INTEGRITY, id, 0.02f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(AttributeRegistry.SOUL_WARD_RECOVERY_RATE, id, 0.02f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(AttributeRegistry.SOUL_WARD_RECOVERY_MULTIPLIER, id, 0.02f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

        addAttributeModifier(AttributeRegistry.CHARGE_DURATION, id, -0.02f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(AttributeRegistry.CHARGE_RECOVERY_RATE, id, 0.02f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }
}