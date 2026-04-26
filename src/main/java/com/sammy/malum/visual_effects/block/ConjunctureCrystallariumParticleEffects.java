package com.sammy.malum.visual_effects.block;

import com.sammy.malum.common.block.curiosities.artifice.crystallarium.ConjunctureCrystallariumBlockEntity;
import net.minecraft.core.Direction;
import team.lodestar.lodestone.modules.core.easing.Easing;
import team.lodestar.lodestone.modules.rendering.handlers.ParticleHandler;
import team.lodestar.lodestone.modules.rendering.particle.builder.ParticleBuilder;
import team.lodestar.lodestone.modules.rendering.particle.builder.ParticleSpec;
import team.lodestar.lodestone.modules.rendering.particle.runtime.ParticleSpawnContext;
import team.lodestar.lodestone.modules.rendering.particle.runtime.ParticleSpawnContextChain;
import team.lodestar.lodestone.modules.rendering.particle.runtime.profile.cube.CuboidDistributionProfile;
import team.lodestar.lodestone.modules.rendering.particle.runtime.profile.sphere.SphericalDistributionProfile;
import team.lodestar.lodestone.modules.rendering.particle.visual.instance.InstanceFormat;
import team.lodestar.lodestone.modules.rendering.particle.visual.instance.StandardInstanceWriters;
import team.lodestar.lodestone.registry.client.LodestoneParticleComponents;
import team.lodestar.lodestone.registry.client.LodestoneParticleVisuals;
import team.lodestar.lodestone.registry.client.LodestoneRenderTypes;

import java.awt.*;

import static com.sammy.malum.visual_effects.SpiritLightSpecs.spiritLightSpecs;

public class ConjunctureCrystallariumParticleEffects {

    private static final InstanceFormat POS_COLOR = new InstanceFormat.Builder()
            .add(StandardInstanceWriters.POSITION)
            .add(StandardInstanceWriters.COLOR)
            .add(StandardInstanceWriters.MODEL_MATRIX)
            .build();

    private static final ParticleSpec SPEC2 = ParticleBuilder.create()
            .withVisual(LodestoneParticleVisuals.QUAD, v -> {
                v.renderType(LodestoneRenderTypes.DEBUG_POS_TEX);
                v.instanceFormat(POS_COLOR);
            })
            .build();

    public static void passiveCrystallariumParticles(ConjunctureCrystallariumBlockEntity crystallarium) {
        for (int i = 0; i < 3; i++) {
            var profile = CuboidDistributionProfile.centeredOn(crystallarium.getBlockPos().relative(Direction.NORTH, i*16), 8).weighed(Easing.QUINTIC_IN);
            if (i == 1) {
                profile.surround();
            }
            else if (i == 2) {
                profile.outline();
            }
            var chain = new ParticleSpawnContextChain(profile);
            var ctx = new ParticleSpawnContext().delay(40 * i).lifetime(100);
            ParticleHandler.spawn(SPEC2, chain.apply(ctx));
        }
    }
}
