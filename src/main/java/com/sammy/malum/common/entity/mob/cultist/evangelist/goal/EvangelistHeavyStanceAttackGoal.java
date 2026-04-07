package com.sammy.malum.common.entity.mob.cultist.evangelist.goal;

import com.sammy.malum.common.entity.mob.cultist.evangelist.EvangelistHeavyStanceData;
import com.sammy.malum.common.entity.mob.cultist.evangelist.EvangelistHeavyStanceData.HeavyStanceState;
import com.sammy.malum.common.entity.mob.cultist.evangelist.EvangelistCultist;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.phys.Vec3;

public class EvangelistHeavyStanceAttackGoal extends AbstractEvangelistMeleeAttackGoal {

    protected int preemptiveAttackTimer;
    protected Vec3 attackDirection;

    public EvangelistHeavyStanceAttackGoal(EvangelistCultist evangelistCultist, double speedModifier) {
        super(evangelistCultist, speedModifier);
    }

    @Override
    public int getAttackDelay() {
        return 4;
    }

    @Override
    public byte getAttackAnimation() {
        if (getData().isLastSwing()) {
            return EvangelistCultist.HEAVY_MELEE_ENDING_SWING_ANIMATION;
        }
        return EvangelistCultist.HEAVY_MELEE_SWING_ANIMATION;
    }

    @Override
    public boolean canUse() {
        if (getData().is(HeavyStanceState.ACTIVE)) {
            return super.canUse();
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        if (getData().is(HeavyStanceState.ACTIVE)) {
            return super.canContinueToUse();
        }
        return false;
    }

    @Override
    public boolean isCloseEnoughToStartSwing(LivingEntity target) {
        if (!getData().canPerformHeavyAttack()) {
            return false;
        }
        preemptiveAttackTimer++;
        if (preemptiveAttackTimer > 60) {
            preemptiveAttackTimer = 40;
            return true;
        }
        if (target.distanceTo(evangelistCultist) < 8f) {
            return true;
        }
        return super.isCloseEnoughToStartSwing(target);
    }

    @Override
    public void tickReadiedAttack() {
        evangelistCultist.move(MoverType.SELF, attackDirection.scale(0.6f));
        super.tickReadiedAttack();
    }

    @Override
    public void beginAttack(LivingEntity target) {
        super.beginAttack(target);
        attackDirection = target.position().subtract(evangelistCultist.position()).normalize();
        evangelistCultist.move(MoverType.SELF, attackDirection.scale(0.9f));
    }

    @Override
    protected void resetAttackCooldown() {
        getData().spendHeavyStanceToken();
        this.ticksUntilNextAttack = 12;
    }

    public EvangelistHeavyStanceData getData() {
        return evangelistCultist.heavyStanceData;
    }
}
