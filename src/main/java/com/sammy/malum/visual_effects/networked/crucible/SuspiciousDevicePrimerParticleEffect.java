package com.sammy.malum.visual_effects.networked.crucible;

import com.sammy.malum.visual_effects.*;
import com.sammy.malum.visual_effects.networked.*;
import net.neoforged.api.distmarker.*;

import java.util.function.*;

public class SuspiciousDevicePrimerParticleEffect extends ParticleEffectType {

    public SuspiciousDevicePrimerParticleEffect(String id) {
        super(id);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public Supplier<ParticleEffectActor> get() {
        return () -> (level, random, positionData, colorData, nbtData) -> {
            SpiritCrucibleParticleEffects.suspiciousDevicePrimer(positionData, colorData);
        };
    }
}