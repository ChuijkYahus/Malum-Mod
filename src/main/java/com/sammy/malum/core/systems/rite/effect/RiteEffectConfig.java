package com.sammy.malum.core.systems.rite.effect;

import net.minecraft.core.*;

import java.util.*;

public class RiteEffectConfig {
    private final int totemHeight;
    private final Direction totemDirection;

    public RiteEffectConfig(int totemHeight, Direction totemDirection) {
        this.totemHeight = totemHeight;
        this.totemDirection = totemDirection;
    }

    public static RiteEffectConfigBuilder builder() {
        return new RiteEffectConfigBuilder();
    }

    public int getTotemHeight() {
        return totemHeight;
    }

    public Optional<Direction> getTotemDirection() {
        return Optional.ofNullable(totemDirection);
    }
}
