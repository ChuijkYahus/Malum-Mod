package com.sammy.malum.common.spiritrite.effect.infernal;

import com.sammy.malum.core.systems.rite.effect.SpiritRiteAuraEffect;
import com.sammy.malum.registry.common.MalumMobEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.INFERNAL_SPIRIT;

public class FieryEmbraceRiteEffect extends SpiritRiteAuraEffect<LivingEntity> {

    public FieryEmbraceRiteEffect() {
        super(MalumMobEffects.FIERY_EMBRACE, INFERNAL_SPIRIT);
    }

    @Override
    public Class<LivingEntity> getTargetClass() {
        return LivingEntity.class;
    }

    @Override
    public boolean canApplyEffect(ServerLevel level, LivingEntity target) {
        if (!target.isOnFire()) {
            return false;
        }
        return super.canApplyEffect(level, target);
    }
}
