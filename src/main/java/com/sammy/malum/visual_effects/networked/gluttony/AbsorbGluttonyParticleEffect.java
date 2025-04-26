package com.sammy.malum.visual_effects.networked.gluttony;

import com.sammy.malum.visual_effects.*;
import com.sammy.malum.visual_effects.networked.*;
import team.lodestar.lodestone.systems.network.*;
import net.neoforged.api.distmarker.*;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectExtraData;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectType;

import java.util.function.*;

public class AbsorbGluttonyParticleEffect extends MalumNetworkedParticleEffectType {

    public static NetworkedParticleEffectExtraData createData(float potency) {
        NetworkedParticleEffectExtraData effectData = new NetworkedParticleEffectExtraData();
        effectData.compoundTag.putFloat("potency", potency);
        return effectData;
    }

    public AbsorbGluttonyParticleEffect(String id) {
        super(id);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public Supplier<NetworkedParticleEffectType.ParticleEffectActor> get() {
        return () -> (level, random, positionData, colorData, nbtData) -> {
            GluttonyParticleEffects.incrementGluttonyStatusEffect(positionData, nbtData.compoundTag.getFloat("potency"));
        };
    }
}