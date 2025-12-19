package com.sammy.malum.common.entity.cultist.altar;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class AltarMeleeAttackGoal extends MeleeAttackGoal {

    private final AltarCultist altar;

    public AltarMeleeAttackGoal(AltarCultist altar, double speedModifier) {
        super(altar, speedModifier, false);
        this.altar = altar;
    }

    @Override
    public void start() {
        super.start();
        altar.setAggressive(true);
        Minecraft.getInstance().player.displayClientMessage(Component.literal("Entering Melee"), false);
    }

    @Override
    public void stop() {
        super.stop();
        altar.setAggressive(false);
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
