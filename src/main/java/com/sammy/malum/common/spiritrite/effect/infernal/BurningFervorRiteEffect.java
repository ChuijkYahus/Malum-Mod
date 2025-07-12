package com.sammy.malum.common.spiritrite.effect.infernal;

import com.sammy.malum.core.systems.rite.effect.SpiritRiteAuraEffect;
import com.sammy.malum.registry.common.MalumMobEffects;
import net.minecraft.world.entity.LivingEntity;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.INFERNAL_SPIRIT;

public class BurningFervorRiteEffect extends SpiritRiteAuraEffect<LivingEntity> {

    public BurningFervorRiteEffect() {
        super(MalumMobEffects.BURNING_FERVOR, INFERNAL_SPIRIT);
    }

    @Override
    public Class<LivingEntity> getTargetClass() {
        return LivingEntity.class;
    }
}
