package com.sammy.malum.visual_effects.networked.gluttony;

import com.sammy.malum.visual_effects.*;
import com.sammy.malum.visual_effects.networked.*;
import net.minecraft.util.*;
import net.minecraft.world.level.*;
import net.neoforged.api.distmarker.*;
import team.lodestar.lodestone.systems.network.particle.*;

import java.util.function.*;

public class ThrownGluttonyParticleEffect extends MalumNetworkedParticleEffectType<NetworkedParticleEffectExtraData> {

    public ThrownGluttonyParticleEffect(String id) {
        super(id);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void act(Level level, RandomSource random, NetworkedParticleEffectPositionData positionData, MalumNetworkedParticleEffectColorData colorData, NetworkedParticleEffectExtraData extraData) {
        GluttonyParticleEffects.thrownGluttonySplash(positionData);
    }
}