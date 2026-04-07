package com.sammy.malum.common.entity.mob.cultist.altar.goal;

import com.sammy.malum.common.entity.mob.cultist.altar.AltarCultist;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class AltarMeleeAttackGoal extends MeleeAttackGoal {

    private final AltarCultist altar;
    public AltarMeleeAttackGoal(AltarCultist altar, double speedModifier) {
        super(altar, speedModifier, false);
        this.altar = altar;
    }

    @Override
    public boolean canUse() {
        if (altar.hasAttackedRecently(AltarCultist.MELEE_COOLDOWN)) {
            return false;
        }
        return altar.shouldChaseTarget() && super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        if (altar.hasAttackedRecently(AltarCultist.MELEE_COOLDOWN)) {
            return false;
        }
        return altar.shouldChaseTarget() && super.canContinueToUse();
    }
}
