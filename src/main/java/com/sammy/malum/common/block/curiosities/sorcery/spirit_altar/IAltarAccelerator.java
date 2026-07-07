package com.sammy.malum.common.block.curiosities.sorcery.spirit_altar;

import com.sammy.malum.core.systems.spirit.SpiritArcanaType;
import net.minecraft.server.level.*;

public interface IAltarAccelerator {

    AltarAcceleratorType getAcceleratorType();

    default boolean canAccelerate(SpiritAltarBlockEntity altar) {
        return true;
    }

    default void completeSpiritInfusion(ServerLevel level, SpiritAltarBlockEntity altar) {

    }

    float getAcceleration();

    default void addParticles(SpiritAltarBlockEntity altar, SpiritArcanaType activeSpiritType) {

    }

    record AltarAcceleratorType(int maximumEntries, String type) {
    }
}