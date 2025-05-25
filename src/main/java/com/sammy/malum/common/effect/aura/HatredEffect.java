package com.sammy.malum.common.effect.aura;

import com.sammy.malum.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.ai.attributes.*;
import team.lodestar.lodestone.helpers.*;

public class HatredEffect extends MobEffect {
    public HatredEffect() {
        super(MobEffectCategory.BENEFICIAL, ColorHelper.getColor(MalumSpiritTypes.INFERNAL_SPIRIT.getPrimaryColor()));
        var id = MalumMod.malumPath("hatred");
        addAttributeModifier(MalumAttributes.MALIGNANT_CONVERSION, id, 0.02f, AttributeModifier.Operation.ADD_VALUE);
    }


}