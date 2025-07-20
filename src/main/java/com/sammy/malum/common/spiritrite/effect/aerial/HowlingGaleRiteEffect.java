package com.sammy.malum.common.spiritrite.effect.aerial;

import com.sammy.malum.core.systems.rite.effect.SpiritRitePotionEffect;
import com.sammy.malum.registry.common.MalumMobEffects;
import net.minecraft.world.entity.LivingEntity;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.AERIAL_SPIRIT;

public class HowlingGaleRiteEffect extends SpiritRitePotionEffect<LivingEntity> {

    public HowlingGaleRiteEffect() {
        super(MalumMobEffects.HOWLING_GALE, AERIAL_SPIRIT);
    }

    @Override
    public Class<LivingEntity> getTargetClass() {
        return LivingEntity.class;
    }
}
