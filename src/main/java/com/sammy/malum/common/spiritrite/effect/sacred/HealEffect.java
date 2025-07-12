package com.sammy.malum.common.spiritrite.effect.sacred;

import com.sammy.malum.core.systems.rite.effect.*;
import net.minecraft.server.level.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.SACRED_SPIRIT;

public class HealEffect extends SpiritRiteEntityEffect<LivingEntity> {

    public HealEffect() {
        super(SpiritRiteEffectTag.TARGET_FRIENDLY);
    }

    @Override
    public Class<LivingEntity> getTargetClass() {
        return LivingEntity.class;
    }

    @Override
    public void applyEffect(ServerLevel level, LivingEntity target) {
        if (target.getHealth() < target.getMaxHealth()) {
            target.heal(4);
            createEffect(level, target, SACRED_SPIRIT);
        }
    }

    @Override
    public boolean canApplyEffect(ServerLevel level, LivingEntity target) {
        if (target instanceof Monster) {
            return false;
        }
        return super.canApplyEffect(level, target);
    }
}
