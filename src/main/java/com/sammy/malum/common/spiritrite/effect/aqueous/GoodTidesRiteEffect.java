package com.sammy.malum.common.spiritrite.effect.aqueous;

import com.sammy.malum.core.systems.rite.effect.SpiritRitePotionEffect;
import com.sammy.malum.registry.common.MalumMobEffects;
import net.minecraft.world.entity.LivingEntity;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.AQUEOUS_SPIRIT;

public class GoodTidesRiteEffect extends SpiritRitePotionEffect<LivingEntity> {

    public GoodTidesRiteEffect() {
        super(MalumMobEffects.GOOD_TIDES, AQUEOUS_SPIRIT);
    }

    @Override
    public Class<LivingEntity> getTargetClass() {
        return LivingEntity.class;
    }
}
