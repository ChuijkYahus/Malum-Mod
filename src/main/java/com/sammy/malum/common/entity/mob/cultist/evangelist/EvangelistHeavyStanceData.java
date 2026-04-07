package com.sammy.malum.common.entity.mob.cultist.evangelist;

import net.minecraft.nbt.CompoundTag;

import static com.sammy.malum.common.entity.mob.cultist.evangelist.EvangelistCultist.*;

public class EvangelistHeavyStanceData {

    public enum HeavyStanceState {
        INACTIVE("inactive"),
        READY("ready"),
        ACTIVE("active");

        public final String name;
        HeavyStanceState(String name) {
            this.name = name;
        }

        public static HeavyStanceState byId(String id) {
            for (HeavyStanceState value : values()) {
                if (value.name.equals(id)) {
                    return value;
                }
            }
            return INACTIVE;
        }
    }

    protected final EvangelistCultist evangelist;
    protected int heavyStanceTokens;
    protected int initialAttackDelay;

    protected HeavyStanceState heavyStanceState;

    public EvangelistHeavyStanceData(EvangelistCultist evangelist) {
        this.evangelist = evangelist;
    }

    public void save(CompoundTag compound) {
        compound.putInt("HeavyStanceTokens", heavyStanceTokens);
        compound.putInt("InitialAttackDelay", initialAttackDelay);
        compound.putString("HeavyStanceState", heavyStanceState.name);
    }

    public void load(CompoundTag compound) {
        heavyStanceTokens = compound.getInt("HeavyStanceTokens");
        initialAttackDelay = compound.getInt("InitialAttackDelay");
        heavyStanceState = HeavyStanceState.byId(compound.getString("HeavyStanceState"));
    }

    public void spendHeavyStanceToken() {
        if (is(HeavyStanceState.ACTIVE) && heavyStanceTokens > 0) {
            heavyStanceTokens--;
            if (heavyStanceTokens == 0) {
                endHeavyMeleeState();
            }
        }
    }

    public boolean isLastSwing() {
        return heavyStanceTokens == 1;
    }

    public void updateValues() {
        if (initialAttackDelay > 0) {
            initialAttackDelay--;
        }
        if (is(HeavyStanceState.READY)) {
            if (evangelist.isTargetWithinRadius(3f) || !evangelist.isTargetWithinRadius(12f)) {
                return;
            }
            enterHeavyMeleeStance(ENTER_HEAVY_STANCE_ANIMATION);
        }
    }

    public void workTowardsHeavyStance() {
        if (is(HeavyStanceState.INACTIVE)) {
            heavyStanceTokens++;
            if (heavyStanceTokens >= HEAVY_STANCE_NEEDED_PROGRESS) {
                if (evangelist.getRandom().nextFloat() < HEAVY_STANCE_CHANCE) {
                    enableHeavyMeleeStance();
                }
            }
        }
    }

    protected void endHeavyMeleeState() {
        heavyStanceState = HeavyStanceState.INACTIVE;
        heavyStanceTokens = 0;
    }

    protected void enableHeavyMeleeStance() {
        heavyStanceState = HeavyStanceState.READY;
    }

    protected void enterHeavyMeleeStance(byte animation) {
        heavyStanceState = HeavyStanceState.ACTIVE;
        heavyStanceTokens = HEAVY_STANCE_SWINGS;
        initialAttackDelay = 40;
        evangelist.broadcastAnimation(animation);
    }

    public boolean canPerformHeavyAttack() {
        return initialAttackDelay == 0;
    }

    public boolean is(HeavyStanceState state) {
        return this.heavyStanceState == state;
    }
}
