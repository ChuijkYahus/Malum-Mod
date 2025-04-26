package com.sammy.malum.visual_effects.networked.crucible;

import com.sammy.malum.common.block.curiosities.spirit_crucible.*;
import com.sammy.malum.visual_effects.*;
import com.sammy.malum.visual_effects.networked.*;
import net.neoforged.api.distmarker.*;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectType;

import java.util.function.*;

public class SpiritCrucibleCraftParticleEffect extends MalumNetworkedParticleEffectType {

    public SpiritCrucibleCraftParticleEffect(String id) {
        super(id);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public Supplier<ParticleEffectActor> get() {
        return () -> (level, random, positionData, colorData, nbtData) -> {
            if (!(level.getBlockEntity(positionData.getAsBlockPos()) instanceof SpiritCrucibleCoreBlockEntity crucible)) {
                return;
            }
            SpiritCrucibleParticleEffects.craftItemParticles(crucible, colorData);
        };
    }
}