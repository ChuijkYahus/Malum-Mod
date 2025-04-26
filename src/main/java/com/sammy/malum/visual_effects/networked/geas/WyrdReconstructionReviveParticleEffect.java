package com.sammy.malum.visual_effects.networked.geas;

import com.sammy.malum.visual_effects.GeasParticleEffects;
import com.sammy.malum.visual_effects.networked.*;
import team.lodestar.lodestone.systems.network.*;
import net.minecraft.nbt.*;
import net.minecraft.world.entity.*;
import net.neoforged.api.distmarker.*;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectExtraData;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectType;

import java.util.function.*;

public class WyrdReconstructionReviveParticleEffect extends MalumNetworkedParticleEffectType {

    public WyrdReconstructionReviveParticleEffect(String id) {
        super(id);
    }

    public static NetworkedParticleEffectExtraData createData(Entity entity) {
        CompoundTag tag = new CompoundTag();
        tag.putInt("targetId", entity.getId());
        return new NetworkedParticleEffectExtraData(tag);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public Supplier<ParticleEffectActor> get() {
        return () -> (level, random, positionData, colorData, nbtData) -> {
            if (!nbtData.compoundTag.contains("targetId")) {
                return;
            }
            final Entity entity = level.getEntity(nbtData.compoundTag.getInt("targetId"));
            if (entity != null) {
                GeasParticleEffects.wyrdReconstructionRevive(level, entity, random, positionData, colorData);
            }
        };
    }
}