package com.sammy.malum.common.entity.mob.cultist.cherub.goal;

import com.sammy.malum.common.entity.mob.cultist.cherub.CherubCultist;
import com.sammy.malum.registry.common.sound.MalumCultistSoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public abstract class AbstractCherubCastSpellGoal extends Goal {

    protected final CherubCultist cherub;

    protected int spellProgress;

    public AbstractCherubCastSpellGoal(CherubCultist cherub) {
        this.cherub = cherub;
        this.setFlags(EnumSet.of(Flag.LOOK));
    }

    protected abstract float getRange();

    protected abstract int getInterval();

    protected abstract LivingEntity getTarget();

    protected abstract void castSpell(@Nullable LivingEntity target);

    @Override
    public boolean canUse() {
        if (getTarget() == null) {
            return false;
        }
        if (cherub.isScared()) {
            return false;
        }
        if (cherub.hasCastSpellRecently(getInterval())) {
            return false;
        }
        if (cherub.getRandom().nextFloat() < 0.05f) {
            return cherub.isTargetWithinRadius(getTarget(), getRange());
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        if (getTarget() == null) {
            return false;
        }
        if (cherub.hasCastSpellRecently(getInterval())) {
            return false;
        }
        return !cherub.isScared();
    }

    @Override
    public void start() {
        super.start();
        spellProgress = 0;
        cherub.broadcastAnimation(CherubCultist.CAST_ANIMATION, MalumCultistSoundEvents.CHERUB_PREPARE_SPELL);
    }

    @Override
    public void tick() {
        super.tick();
        var target = getTarget();
        if (target != null) {
            cherub.faceTarget(target);
        }
        spellProgress++;
        if (spellProgress >= CherubCultist.SPELL_DURATION) {
            spellProgress = 0;
            if (target != null) {
                castSpell(target);
            }
            stop();
        }
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

}