package com.sammy.malum.common.effect.rite.aura.soulwood;

import com.sammy.malum.*;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import team.lodestar.lodestone.helpers.ColorHelper;

public class OakenMightEffect extends MobEffect {
    public OakenMightEffect() {
        super(MobEffectCategory.BENEFICIAL, ColorHelper.getColor(MalumSpiritTypes.EARTHEN_COLORS().primaryColor()));
    }
}