package com.sammy.malum.common.spiritrite.effect.earthen;

import com.sammy.malum.core.systems.rite.effect.SpiritRitePotionEffect;
import com.sammy.malum.registry.common.MalumMobEffects;
import net.minecraft.world.entity.LivingEntity;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.EARTHEN_SPIRIT;

public class StoneWardRiteEffect extends SpiritRitePotionEffect<LivingEntity> {

    public StoneWardRiteEffect() {
        super(MalumMobEffects.STONE_WARD, EARTHEN_SPIRIT);
    }

    @Override
    public Class<LivingEntity> getTargetClass() {
        return LivingEntity.class;
    }
}
