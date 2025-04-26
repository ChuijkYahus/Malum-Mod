package com.sammy.malum.visual_effects.networked.brazier;

import com.sammy.malum.common.block.curiosities.soul_brazier.SoulBrazierBlockEntity;
import com.sammy.malum.visual_effects.SoulBindingBrazierParticleEffects;
import com.sammy.malum.visual_effects.networked.MalumNetworkedParticleEffectColorData;
import com.sammy.malum.visual_effects.networked.MalumNetworkedParticleEffectType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectExtraData;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectPositionData;

public class SoulBrazierEndParticleEffect extends MalumNetworkedParticleEffectType<SoulBrazierStateEffectData> {

    public SoulBrazierEndParticleEffect(String id) {
        super(id);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void act(Level level, RandomSource random, NetworkedParticleEffectPositionData positionData, MalumNetworkedParticleEffectColorData colorData, SoulBrazierStateEffectData extraData) {
        if (!(level.getBlockEntity(positionData.getAsBlockPos()) instanceof SoulBrazierBlockEntity brazier)) {
            return;
        }
        brazier.state = extraData.state();
        SoulBindingBrazierParticleEffects.finishSoulBindingParticles(brazier, colorData);
    }
}