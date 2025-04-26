package com.sammy.malum.visual_effects.networked.gluttony;

import com.sammy.malum.visual_effects.*;
import com.sammy.malum.visual_effects.networked.*;
import net.neoforged.api.distmarker.*;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectType;

import java.util.function.*;

public class ThrownGluttonyParticleEffect extends MalumNetworkedParticleEffectType {

    public ThrownGluttonyParticleEffect(String id) {
        super(id);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public Supplier<ParticleEffectActor> get() {
        return () -> (level, random, positionData, colorData, nbtData) -> {
            GluttonyParticleEffects.thrownGluttonySplash(positionData);
        };
    }
}