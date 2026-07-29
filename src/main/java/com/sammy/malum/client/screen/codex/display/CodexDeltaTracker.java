package com.sammy.malum.client.screen.codex.display;

import net.minecraft.util.Mth;
import team.lodestar.lodestone.helpers.DataHelper;

public class CodexDeltaTracker {

    protected float delta, oDelta;

    public void tick(float target, float step) {
        oDelta = delta;
        delta = DataHelper.approach(delta, target, step);
    }

    public float getDelta(float partialTick) {
        return Mth.lerp(partialTick, oDelta, delta);
    }
}
