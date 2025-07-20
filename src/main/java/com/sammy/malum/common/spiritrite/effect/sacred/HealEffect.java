package com.sammy.malum.common.spiritrite.effect.sacred;

import com.sammy.malum.core.systems.rite.effect.*;
import com.sammy.malum.registry.common.MalumDamageTypes;
import net.minecraft.server.level.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import team.lodestar.lodestone.helpers.DamageTypeHelper;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.SACRED_SPIRIT;

public class HealEffect extends SpiritRiteEntityEffect<LivingEntity> {

    public HealEffect() {
        super();
    }

    @Override
    public Class<LivingEntity> getTargetClass() {
        return LivingEntity.class;
    }

    @Override
    public void applyEffect(ServerLevel level, LivingEntity target) {
        if (target.getHealth() < target.getMaxHealth()) {
            if (target.isInvertedHealAndHarm()) {
                DamageSource damageSource = DamageTypeHelper.create(target.level(), MalumDamageTypes.VOODOO_PLAYERLESS);
                target.hurt(damageSource, 4);
            }
            else {
                target.heal(4);
            }
            createEffect(level, target, SACRED_SPIRIT);
        }
    }
}
