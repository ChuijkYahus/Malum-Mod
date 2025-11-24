package com.sammy.malum.visual_effects;

import com.sammy.malum.common.block.curiosities.spirit_altar.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.visual_effects.networked.*;
import com.sammy.malum.visual_effects.networked.wind_gust.*;
import net.minecraft.util.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.*;
import net.minecraft.world.phys.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.particle.builder.*;
import team.lodestar.lodestone.systems.particle.data.*;
import team.lodestar.lodestone.systems.particle.render_types.*;
import team.lodestar.lodestone.systems.particle.world.behaviors.*;

public class WindTunnelParticleEffects {

    public static void windTrailParticles(Level level, RandomSource random, Entity target, Vec3 pos, MalumNetworkedParticleEffectColorData colorData, WindTrailParticleEffect.WindTrailParticleEffectData extraData) {
        int delay = extraData.delay();
        int duration = extraData.duration();
        var transparencyData = GenericParticleData.create(1f, 0.4f).setEasing(Easing.QUAD_IN).build();
        var builder = WorldParticleBuilder.create(MalumParticles.MOTION_LINES.get())
                .setBehavior(SparkParticleBehavior.sparkBehavior())
                .setRenderType(LodestoneWorldParticleRenderType.LUMITRANSPARENT)
                .setScaleData(GenericParticleData.create(1f, 0f))
                .setLengthData(GenericParticleData.create(0.75f))
                .setTransparencyData(transparencyData)
                .setColorData(colorData.getColor())
                .enableForcedSpawn()
                .setLifetime(30)
                .enableNoClip();
        for (int i = 0; i < duration; i++) {
            builder.setLifeDelay(delay + i)
                    .clearSpawnActors()
                    .addSpawnActor(p -> {
                        float offsetScale = 0.2f;
                        Vec3 offset = new Vec3(
                                RandomHelper.randomBetween(random, -offsetScale, offsetScale),
                                RandomHelper.randomBetween(random, -offsetScale, offsetScale),
                                RandomHelper.randomBetween(random, -offsetScale, offsetScale)
                        );
                        var position = target.position().add(0, target.getBbHeight() / 2, 0).add(offset);
                        p.setParticlePosition(position);
                        p.setParticleSpeed(target.getDeltaMovement().scale(0.3f));
                    })
                    .spawn(level, pos);
        }
    }
}
