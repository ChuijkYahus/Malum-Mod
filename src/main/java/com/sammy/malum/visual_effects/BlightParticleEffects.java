package com.sammy.malum.visual_effects;

import com.sammy.malum.core.systems.spirit.*;
import com.sammy.malum.visual_effects.networked.*;
import com.sammy.malum.visual_effects.networked.blight.*;
import net.minecraft.client.*;
import net.minecraft.core.*;
import net.minecraft.util.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.registry.common.particle.*;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.network.particle.*;
import team.lodestar.lodestone.systems.particle.builder.*;
import team.lodestar.lodestone.systems.particle.data.*;
import team.lodestar.lodestone.systems.particle.data.color.*;
import team.lodestar.lodestone.systems.particle.data.spin.*;
import team.lodestar.lodestone.systems.particle.render_types.*;
import team.lodestar.lodestone.systems.particle.world.*;
import team.lodestar.lodestone.systems.particle.world.behaviors.*;

import java.awt.*;
import java.util.function.*;
import java.util.logging.*;

import static net.minecraft.util.Mth.nextFloat;

public class BlightParticleEffects {

    public static void blightSpreads(Level level, BlockPos sourcePos, BlockPos targetPos) {
        var rand = level.getRandom();
        for (int i = 0; i < 3; i++) {
            if (rand.nextFloat() < 0.85f) {
                Color color = getBlightColor(rand);
                float xVelocity = RandomHelper.randomBetween(rand, Easing.CUBIC_OUT, -0.025f, 0.025f);
                float zVelocity = RandomHelper.randomBetween(rand, Easing.CUBIC_OUT, -0.025f, 0.025f);
                float xOffset = RandomHelper.randomBetween(rand, -0.5f, 0.5f);
                float yOffset = RandomHelper.randomBetween(rand, 0.5f, 0.65f);
                float zOffset = RandomHelper.randomBetween(rand, -0.5f, 0.5f);

                Consumer<LodestoneWorldParticle> slowDown = p -> p.setParticleSpeed(p.getParticleSpeed().scale(0.95f));
                Vec3 particlePosition = targetPos.getCenter().add(xOffset, yOffset, zOffset);
                int distance = targetPos.distManhattan(sourcePos);
                int lifetime = 20 + 12 * distance;
                var builder = WorldParticleBuilder.create(LodestoneParticleTypes.WISP_PARTICLE.get())
                        .setSpinData(SpinParticleData.createRandomDirection(rand, nextFloat(rand, 0.05f, 0.1f)).randomSpinOffset(rand).build())
                        .setColorData(ColorParticleData.create(color).build())
                        .setMotion(xVelocity, 0, zVelocity)
                        .addTickActor(slowDown)
                        .setLifetime(lifetime)
                        .setNaturalLighting()
                        .enableNoClip();

                for (int j = 0; j < 4; j++) {
                    var renderType = j / 2 == 0 ? LodestoneWorldParticleRenderType.LUMITRANSPARENT : LodestoneWorldParticleRenderType.ADDITIVE;
                    var behavior = j % 2 == 0 ? DirectionalParticleBehavior.directional(new Vec3(0, 1, 0)) : BillboardParticleBehavior.INSTANCE;
                    float alpha = j / 2 == 0 ? 0.25f : 0.1f;
                    float scale = j % 2 == 0 ? 2f : 0.8f;
                    builder
                            .setScaleData(GenericParticleData.create(0f, scale, scale*0.7f).setEasing(Easing.CUBIC_OUT, Easing.CUBIC_IN_OUT).build())
                            .setTransparencyData(GenericParticleData.create(alpha*2, alpha, 0f).setEasing(Easing.CUBIC_OUT, Easing.CUBIC_IN).build())
                            .setRenderType(renderType)
                            .setBehavior(behavior)
                            .spawn(level, particlePosition.x, particlePosition.y, particlePosition.z);
                }
            }
        }
    }

    public static void blightPlantGrows(Level level, BlockPos sourcePos, BlockPos targetPos) {
        var rand = level.getRandom();
        for (int i = 0; i < 3; i++) {
            if (rand.nextFloat() < 0.85f) {
                Color color = getBlightColor(rand);
                float xOffset = RandomHelper.randomBetween(rand, -0.3f, 0.3f);
                float yOffset = RandomHelper.randomBetween(rand, -0.6f, -0.4f);
                float zOffset = RandomHelper.randomBetween(rand, -0.3f, 0.3f);

                Consumer<LodestoneWorldParticle> movement = p -> {
                    var center = targetPos.getCenter();
                    Vec3 distance = p.getParticlePosition().subtract(center.x, center.y+1, center.z);
                    Vec3 direction = distance.normalize();
                    float delta = Math.max(p.getAge() / (float) p.getLifetime(), 0);
                    float velocity = Easing.CIRC_IN_OUT.ease(delta, 0.02f, 0.5f - distance.length() * 0.4);
                    Vec3 speed = direction.scale(velocity).multiply(1f, 0.7f, 1f);
                    Vec3 lerp = p.getParticleSpeed().lerp(speed, delta);
                    p.setParticleSpeed(lerp);
                };
                Vec3 particlePosition = targetPos.getCenter().add(xOffset, yOffset, zOffset);
                int distance = targetPos.distManhattan(sourcePos);
                int lifetime = 20 + 12 * distance;
                var builder = WorldParticleBuilder.create(LodestoneParticleTypes.WISP_PARTICLE.get())
                        .setSpinData(SpinParticleData.createRandomDirection(rand, nextFloat(rand, 0.05f, 0.1f)).randomSpinOffset(rand).build())
                        .setColorData(ColorParticleData.create(color).build())
                        .addTickActor(movement)
                        .setLifetime(lifetime)
                        .setNaturalLighting()
                        .enableNoClip();

                for (int j = 0; j < 4; j++) {
                    var renderType = j / 2 == 0 ? LodestoneWorldParticleRenderType.LUMITRANSPARENT : LodestoneWorldParticleRenderType.ADDITIVE;
                    var behavior = j % 2 == 0 ? SparkParticleBehavior.sparkBehavior() : BillboardParticleBehavior.INSTANCE;
                    float alpha = j / 2 == 0 ? 0.4f : 0.2f;
                    float scale = j % 2 == 0 ? 1.2f : 0.8f;
                    builder
                            .setScaleData(GenericParticleData.create(0, scale, scale*0.7f).setEasing(Easing.CUBIC_OUT, Easing.CUBIC_IN_OUT).build())
                            .setTransparencyData(GenericParticleData.create(alpha*2, alpha, 0f).setEasing(Easing.CUBIC_OUT, Easing.CUBIC_IN).build())
                            .setRenderType(renderType)
                            .setBehavior(behavior)
                            .spawn(level, particlePosition.x, particlePosition.y, particlePosition.z);
                }
            }
        }
    }

    public static Color getBlightColor(RandomSource random) {
        float multiplier = Mth.nextFloat(random, 0.4f, 1f);
        return new Color((int) (31 * multiplier), (int) (19 * multiplier), (int) (31 * multiplier));
    }
}
