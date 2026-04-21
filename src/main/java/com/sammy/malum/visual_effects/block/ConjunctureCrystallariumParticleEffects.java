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

    private static final ParticleSpec SPEC = ParticleBuilder.create()
            .with(LodestoneParticleComponents.COLOR, c -> c.interpolate(Color.BLACK, Color.WHITE, Easing.LINEAR))
            .build();

    private static final ParticleSpawnContext CTX = new ParticleSpawnContext()
            .motion(0, 0.1, 0)
            .lifetime(200);

    private static final InstanceFormat POS_COLOR = new InstanceFormat.Builder()
            .add(StandardInstanceWriters.POSITION)
            .add(StandardInstanceWriters.COLOR)
            .build();

    private static final ParticleSpec SPEC2 = ParticleBuilder.create()
            .with(LodestoneParticleComponents.COLOR, cfg -> cfg.interpolate(Color.BLUE, Color.ORANGE, Easing.LINEAR))
            .withVisual(LodestoneParticleVisuals.BILLBOARD, v -> {
                v.renderType(LodestoneRenderTypes.DEBUG_POS_TEX);
                v.instanceFormat(POS_COLOR);
            })
            .withVisual(LodestoneParticleVisuals.TRAIL, v -> {
                v.renderType(LodestoneRenderTypes.DEBUG_TRAIL);
                v.maxPoints(4);
                v.width(0.8f);
            })
            .build();

    public static void passiveCrystallariumParticles(ConjunctureCrystallariumBlockEntity crystallarium) {
//        if (crystallarium.getBlockPos().getX() % 2 == 0) {
//
//            int divisions = 8;
//            float size = 20;
//            var profile = SphericalDistributionProfile.centeredOn(crystallarium.getBlockPos(), size)
//                    .weighedDistance(Easing.BOUNCE_IN_OUT)
//                    .weighedAngle(Easing.QUINTIC_IN, divisions);
//            var chain = new ParticleSpawnContextChain(profile);
//            var ctx = new ParticleSpawnContext().lifetime(40);
//            profile.outline();
//
//            ParticleHandler.spawn(SPEC2, chain.apply(ctx), 10);
//
//
//            return;
//        }
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
