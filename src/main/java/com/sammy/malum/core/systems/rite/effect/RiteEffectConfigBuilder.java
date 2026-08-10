package com.sammy.malum.core.systems.rite.effect;

import net.minecraft.core.*;

public class RiteEffectConfigBuilder {
    private int totemHeight = 0;
    private Direction totemDirection = Direction.NORTH;

    public RiteEffectConfigBuilder setTotemHeight(int totemHeight) {
        this.totemHeight = totemHeight;
        return this;
    }

    public RiteEffectConfigBuilder setTotemDirection(Direction totemDirection) {
        this.totemDirection = totemDirection;
        return this;
    }

    public RiteEffectConfig build() {
        return new RiteEffectConfig(totemHeight, totemDirection);
    }
}
