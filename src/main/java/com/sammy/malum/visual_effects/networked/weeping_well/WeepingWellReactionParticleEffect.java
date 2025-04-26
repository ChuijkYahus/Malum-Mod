package com.sammy.malum.visual_effects.networked.weeping_well;

import com.sammy.malum.visual_effects.*;
import com.sammy.malum.visual_effects.networked.*;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.*;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectExtraData;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectPositionData;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectType;

import java.util.function.Supplier;

public class WeepingWellReactionParticleEffect extends MalumNetworkedParticleEffectType<NetworkedParticleEffectExtraData> {

    public WeepingWellReactionParticleEffect(String id) {
        super(id);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void act(Level level, RandomSource random, NetworkedParticleEffectPositionData positionData, MalumNetworkedParticleEffectColorData colorData, NetworkedParticleEffectExtraData extraData) {
        WeepingWellParticleEffects.spitOutItemParticles(level, positionData);
    }
}