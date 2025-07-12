package com.sammy.malum.common.spiritrite.effect.aqueous;

import com.sammy.malum.core.systems.rite.effect.SpiritRiteAuraEffect;
import com.sammy.malum.registry.common.MalumMobEffects;
import net.minecraft.world.entity.LivingEntity;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.AQUEOUS_SPIRIT;

public class FlowingGraspRiteEffect extends SpiritRiteAuraEffect<LivingEntity> {

    public FlowingGraspRiteEffect() {
        super(MalumMobEffects.FLOWING_GRASP, AQUEOUS_SPIRIT);
    }

    @Override
    public Class<LivingEntity> getTargetClass() {
        return LivingEntity.class;
    }
}
