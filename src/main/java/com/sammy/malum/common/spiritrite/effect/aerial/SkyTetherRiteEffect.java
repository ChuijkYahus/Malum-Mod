package com.sammy.malum.common.spiritrite.effect.aerial;

import com.sammy.malum.core.systems.rite.effect.SpiritRiteAuraEffect;
import com.sammy.malum.registry.common.MalumMobEffects;
import net.minecraft.world.entity.LivingEntity;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.AERIAL_SPIRIT;

public class SkyTetherRiteEffect extends SpiritRiteAuraEffect<LivingEntity> {

    public SkyTetherRiteEffect() {
        super(MalumMobEffects.SKY_TETHER, AERIAL_SPIRIT);
    }

    @Override
    public Class<LivingEntity> getTargetClass() {
        return LivingEntity.class;
    }
}
