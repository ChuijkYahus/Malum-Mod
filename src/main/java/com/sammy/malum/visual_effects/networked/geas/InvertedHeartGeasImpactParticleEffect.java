package com.sammy.malum.visual_effects.networked.geas;

import com.sammy.malum.visual_effects.GeasParticleEffects;
import com.sammy.malum.visual_effects.networked.*;
import net.neoforged.api.distmarker.*;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectType;

import java.util.function.*;

import static com.sammy.malum.visual_effects.SpiritLightSpecs.spiritLightSpecs;

public class InvertedHeartGeasImpactParticleEffect extends MalumNetworkedParticleEffectType {

    public InvertedHeartGeasImpactParticleEffect(String id) {
        super(id);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public Supplier<ParticleEffectActor> get() {
        return () -> (level, random, positionData, colorData, nbtData) -> {
            GeasParticleEffects.invertedHeartDamageEffect(level, random, positionData, colorData);
        };
    }
}