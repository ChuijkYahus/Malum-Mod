package com.sammy.malum.common.entity.mob.cultist;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.LookControl;

public class CultistLookControl extends LookControl {

    public final CultistMonster cultist;

    public CultistLookControl(CultistMonster cultist) {
        super(cultist);
        this.cultist = cultist;
    }
}
