package com.sammy.malum.common.spiritrite.effect.wicked;

import com.sammy.malum.core.systems.rite.effect.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.server.level.*;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.entity.*;
import team.lodestar.lodestone.helpers.*;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class HurtEffect extends SpiritRiteEntityEffect<LivingEntity> {

    public HurtEffect() {
        super();
    }

    @Override
    public Class<LivingEntity> getTargetClass() {
        return LivingEntity.class;
    }

    @Override
    public void applyEffect(ServerLevel level, LivingEntity target) {
        DamageSource damageSource = DamageTypeHelper.create(target.level(), MalumDamageTypes.VOODOO_PLAYERLESS);
        target.hurt(damageSource, 2);
        createEffect(level, target, WICKED_SPIRIT);
    }

    @Override
    public boolean canApplyEffect(ServerLevel level, LivingEntity target) {
        DamageSource damageSource = DamageTypeHelper.create(target.level(), MalumDamageTypes.VOODOO_PLAYERLESS);
        if (target.getHealth() > 2.5f) {
            return !target.isInvulnerableTo(damageSource);
        }
        return false;
    }
}
