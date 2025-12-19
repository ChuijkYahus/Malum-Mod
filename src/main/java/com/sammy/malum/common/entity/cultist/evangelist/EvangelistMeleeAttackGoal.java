package com.sammy.malum.common.entity.cultist.evangelist;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class EvangelistMeleeAttackGoal extends MeleeAttackGoal {

    private final EvangelistCultist evangelist;

    public EvangelistMeleeAttackGoal(EvangelistCultist evangelist, double speedModifier) {
        super(evangelist, speedModifier, false);
        this.evangelist = evangelist;
    }

    @Override
    public void start() {
        super.start();
        evangelist.setAggressive(true);
        Minecraft.getInstance().player.displayClientMessage(Component.literal("Entering Melee"), false);
    }

    @Override
    public void stop() {
        super.stop();
        evangelist.setAggressive(false);
    }
}
