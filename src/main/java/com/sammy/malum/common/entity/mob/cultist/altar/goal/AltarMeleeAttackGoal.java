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
        return altar.canEnterMeleeState() && altar.isWithinMeleeRadius() && super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        return altar.canEnterMeleeState() && altar.isWithinMeleeRadius() && super.canContinueToUse();
    }
}
