package com.sammy.malum.common.effect.rite.aura.soulwood;

import com.sammy.malum.registry.common.magic.MalumSpiritTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import team.lodestar.lodestone.helpers.ColorHelper;

public class CorruptedAqueousAura extends MobEffect {
    public CorruptedAqueousAura() {
        super(MobEffectCategory.BENEFICIAL, ColorHelper.getColor(MalumSpiritTypes.AQUEOUS_COLORS().primaryColor()));
    }
}