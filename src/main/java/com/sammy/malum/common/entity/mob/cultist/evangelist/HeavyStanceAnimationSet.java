package com.sammy.malum.common.entity.mob.cultist.evangelist;

import com.sammy.malum.common.entity.mob.cultist.CultistAnimationState;
import net.minecraft.world.entity.AnimationState;

import static com.sammy.malum.common.entity.mob.cultist.evangelist.EvangelistCultist.*;

public class HeavyStanceAnimationSet {

    public boolean isInHeavyStance;

    public final EvangelistCultist evangelist;

    public final CultistAnimationState idleAnimationState;

    public final CultistAnimationState startAnimationState;
    public final CultistAnimationState parryStartAnimationState;

    public final CultistAnimationState meleeSwingAnimationState;
    public final CultistAnimationState endingSwingAnimationState;

    public HeavyStanceAnimationSet(EvangelistCultist evangelist) {
        this.evangelist = evangelist;

        idleAnimationState = new CultistAnimationState(evangelist);

        startAnimationState = new CultistAnimationState(evangelist);
        parryStartAnimationState = new CultistAnimationState(evangelist);

        meleeSwingAnimationState = new CultistAnimationState(evangelist);
        endingSwingAnimationState = new CultistAnimationState(evangelist);
    }

    public boolean acceptEvent(byte id) {
        return switch (id) {
            case ENTER_HEAVY_STANCE_ANIMATION -> manageAnimationAndState(startAnimationState, true);
            case PARRY_INTO_HEAVY_STANCE_ANIMATION -> manageAnimationAndState(parryStartAnimationState, true);
            case HEAVY_MELEE_SWING_ANIMATION -> manageAnimationAndState(meleeSwingAnimationState, true);
            case HEAVY_MELEE_ENDING_SWING_ANIMATION -> manageAnimationAndState(endingSwingAnimationState, false);
            default -> false;
        };
    }

    public void animateIdleOrFallback(AnimationState fallback) {
        idleAnimationState.animateWhen(isInHeavyStance, evangelist.tickCount);
        fallback.animateWhen(!isInHeavyStance, evangelist.tickCount);
    }

    public boolean manageAnimationAndState(CultistAnimationState state, boolean isInHeavyStance) {
        state.start(evangelist.tickCount);
        this.isInHeavyStance = isInHeavyStance;
        return true;
    }
}
