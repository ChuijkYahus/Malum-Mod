package com.sammy.malum.common.effect.weapon;

import com.sammy.malum.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.ai.attributes.*;
import team.lodestar.lodestone.helpers.*;

public class HatredEffect extends MobEffect {
    public HatredEffect() {
        super(MobEffectCategory.BENEFICIAL, ColorHelper.getColor(MalumSpiritTypes.INFERNAL_COLORS().primaryColor()));
        var id = MalumMod.malumPath("hatred");
        addAttributeModifier(MalumAttributes.MALIGNANT_CONVERSION, id, 0.02f, AttributeModifier.Operation.ADD_VALUE);
    }


}