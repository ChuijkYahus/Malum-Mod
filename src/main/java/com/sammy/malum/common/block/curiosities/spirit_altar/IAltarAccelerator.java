package com.sammy.malum.common.block.curiosities.spirit_altar;

import com.sammy.malum.core.systems.spirit.type.SpiritArcanaType;
import net.minecraft.server.level.*;

public interface IAltarAccelerator {

    AltarAcceleratorType getAcceleratorType();

    default boolean canAccelerate() {
        return true;
    }

    default void completeSpiritInfusion(ServerLevel level) {

    }

    float getAcceleration();

    default void addParticles(SpiritAltarBlockEntity blockEntity, SpiritArcanaType activeSpiritType) {

    }

    record AltarAcceleratorType(int maximumEntries, String type) {
    }
}