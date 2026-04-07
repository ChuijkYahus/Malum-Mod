package com.sammy.malum.common.entity.mob.cultist.cherub.goal;

import com.sammy.malum.common.entity.mob.cultist.cherub.CherubCultist;
import com.sammy.malum.registry.common.sound.MalumCultistSoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class CherubCastCurseGoal extends AbstractCherubCastSpellGoal {

    public CherubCastCurseGoal(CherubCultist cherub) {
        super(cherub);
    }

    @Override
    protected float getRange() {
        return CherubCultist.CURSE_CAST_RANGE;
    }

    @Override
    protected int getInterval() {
        return CherubCultist.CURSE_INTERVAL;
    }

    @Override
    protected LivingEntity getTarget() {
        return cherub.getTarget();
    }

    @Override
    protected void castSpell(@Nullable LivingEntity target) {
        cherub.castCurse();
    }

}