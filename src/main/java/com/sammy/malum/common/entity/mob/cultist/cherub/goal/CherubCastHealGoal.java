package com.sammy.malum.common.entity.mob.cultist.cherub.goal;

import com.sammy.malum.common.entity.mob.cultist.cherub.CherubCultist;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

public class CherubCastHealGoal extends AbstractCherubCastSpellGoal {

    public CherubCastHealGoal(CherubCultist cherub) {
        super(cherub);
    }

    @Override
    protected float getRange() {
        return CherubCultist.HEAL_CAST_RANGE;
    }

    @Override
    protected int getInterval() {
        return CherubCultist.HEAL_INTERVAL;
    }

    @Override
    protected LivingEntity getTarget() {
        return cherub.getHealingTarget();
    }

    @Override
    protected void castSpell(@Nullable LivingEntity target) {
        cherub.castHeal();
    }

}