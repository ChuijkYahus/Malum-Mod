package com.sammy.malum.common.entity.mob.cultist.evangelist.goal;

import com.sammy.malum.common.entity.mob.cultist.evangelist.EvangelistHeavyStanceData.HeavyStanceState;
import com.sammy.malum.common.entity.mob.cultist.evangelist.EvangelistCultist;

public class EvangelistMeleeAttackGoal extends AbstractEvangelistMeleeAttackGoal {

    public EvangelistMeleeAttackGoal(EvangelistCultist evangelistCultist, double speedModifier) {
        super(evangelistCultist, speedModifier);
    }

    @Override
    public int getAttackDelay() {
        return 12;
    }

    @Override
    public byte getAttackAnimation() {
        return EvangelistCultist.MELEE_SWING_ANIMATION;
    }

    @Override
    public boolean canUse() {
        if (evangelistCultist.heavyStanceData.is(HeavyStanceState.ACTIVE)) {
            return false;
        }
        return super.canUse();
    }

    @Override
    protected void resetAttackCooldown() {
        this.ticksUntilNextAttack = 40;
    }
}
