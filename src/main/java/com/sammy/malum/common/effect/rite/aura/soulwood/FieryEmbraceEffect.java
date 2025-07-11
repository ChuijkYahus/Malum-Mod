package com.sammy.malum.common.effect.rite.aura.soulwood;

import com.sammy.malum.registry.common.magic.MalumSpiritTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import team.lodestar.lodestone.helpers.ColorHelper;

public class FieryEmbraceEffect extends MobEffect {
    public FieryEmbraceEffect() {
        super(MobEffectCategory.BENEFICIAL, ColorHelper.getColor(MalumSpiritTypes.INFERNAL_COLORS().primaryColor()));
    }

    @Override
    public boolean applyEffectTick(LivingEntity entityLivingBaseIn, int amplifier) {
        if (entityLivingBaseIn.isOnFire()) {
            entityLivingBaseIn.extinguishFire();
            return true;
        }
        if (entityLivingBaseIn.getHealth() < entityLivingBaseIn.getMaxHealth()) {
            entityLivingBaseIn.heal(amplifier + 1);
            return true;
        }
        return false;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 10 == 0;
    }
}
