package com.sammy.malum.visual_effects;

import com.sammy.malum.common.entity.cultist.altar.AltarCultist;
import com.sammy.malum.registry.common.MalumParticles;
import com.sammy.malum.visual_effects.networked.MalumNetworkedParticleEffectColorData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import team.lodestar.lodestone.helpers.RandomHelper;
import team.lodestar.lodestone.helpers.VecHelper;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectPositionData;
import team.lodestar.lodestone.systems.particle.ParticleEffectSpawner;
import team.lodestar.lodestone.systems.particle.builder.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;
import team.lodestar.lodestone.systems.particle.data.spin.SpinParticleData;
import team.lodestar.lodestone.systems.particle.render_types.LodestoneWorldParticleRenderType;
import team.lodestar.lodestone.systems.particle.world.LodestoneWorldParticle;
import team.lodestar.lodestone.systems.particle.world.behaviors.DirectionalParticleBehavior;
import team.lodestar.lodestone.systems.particle.world.options.WorldParticleOptions;

import java.util.function.Consumer;
import java.util.function.Function;

import static com.sammy.malum.visual_effects.SpiritLightSpecs.spiritLightSpecs;

public class CultistParticleEffects {

    public static void altarFireProjectile(Level level, NetworkedParticleEffectPositionData positionData, MalumNetworkedParticleEffectColorData colorData) {
        var random = level.random;
        var position = positionData.getAsVector();

        for (int i = 0; i < 4; i++) {
            int lifeDelay = i * 2;
            var sigil = cultistSigil(level, position, colorData.getColor());
            sigil.getBuilder()
                    .setSpinData(SpinParticleData.createRandomDirection(random, 0.3f, 0).setEasing(Easing.EXPO_OUT).build())
                    .modifyScaleData(d -> d.multiplyValue(1.25f))
                    .setLifeDelay(lifeDelay);
            sigil.spawnParticles();
        }
        long gameTime = level.getGameTime();
        float time = 16;
        for (int i = 0; i < 8; i++) {
            var offsetPosition = VecHelper.rotatingRadialOffset(position, 0.8f, i, 8, gameTime, time);
            offsetPosition = offsetPosition.add(0, (Math.cos(((gameTime + i * 480) % time) / time) * 0.25f) - 0.25f, 0);
            for (int j = 0; j < 3; j++) {
                var lightSpecs = spiritLightSpecs(level, offsetPosition, colorData.getColor());
                float velocity = RandomHelper.randomBetween(random, 0.02f, 0.03f);
                var motion = offsetPosition.subtract(position).normalize().scale(velocity);
                int lifeDelay = j * 6;
                lightSpecs.getBuilder()
                        .setRenderType(LodestoneWorldParticleRenderType.LUMITRANSPARENT)
                        .multiplyLifetime(2.5f)
                        .setMotion(motion)
                        .setLifeDelay(lifeDelay)
                        .setTransparencyData(GenericParticleData.create(0.9f, 0.5f).build())
                        .modifyScaleData(d -> d.multiplyValue(RandomHelper.randomBetween(random, 1f, 2f)));
                lightSpecs.getBloomBuilder()
                        .setRenderType(LodestoneWorldParticleRenderType.LUMITRANSPARENT)
                        .multiplyLifetime(1.5f)
                        .setMotion(motion)
                        .setLifeDelay(lifeDelay)
                        .setTransparencyData(GenericParticleData.create(0.6f, 0.35f).build())
                        .modifyScaleData(d -> d.multiplyValue(RandomHelper.randomBetween(random, 0.5f, 1f)));
                lightSpecs.spawnParticles();
            }
        }
        for (int i = 0; i < 16; i++) {
            var offsetPosition = VecHelper.rotatingRadialOffset(position, 0.4f, i, 16, gameTime, time);
            offsetPosition = offsetPosition.add(0, (Math.cos(((gameTime + i * 480) % time) / time) * 0.25f) - 0.25f, 0);
            float upwardsVelocity = 0.02f;
            for (int j = 0; j < 2; j++) {
                var lightSpecs = SparkParticleEffects.spiritMotionSparks(level, offsetPosition, colorData.getColor(), new WorldParticleOptions(MalumParticles.MOTION_LINES));
                float velocity = RandomHelper.randomBetween(random, 0.02f, 0.03f);
                var motion = offsetPosition.subtract(position).normalize().scale(velocity);
                int lifeDelay = j * 3;
                lightSpecs.getBuilder()
                        .setRenderType(LodestoneWorldParticleRenderType.LUMITRANSPARENT)
                        .multiplyLifetime(1.5f)
                        .setMotion(motion)
                        .addMotion(0, upwardsVelocity, 0)
                        .setLifeDelay(lifeDelay)
                        .setTransparencyData(GenericParticleData.create(0.9f, 0.6f).build())
                        .modifyScaleData(d -> d.multiplyValue(RandomHelper.randomBetween(random, 1f, 2f)));
                lightSpecs.getBloomBuilder()
                        .setRenderType(LodestoneWorldParticleRenderType.LUMITRANSPARENT)
                        .multiplyLifetime(1.5f)
                        .setMotion(motion)
                        .addMotion(0, upwardsVelocity, 0)
                        .setLifeDelay(lifeDelay)
                        .setTransparencyData(GenericParticleData.create(0.9f, 0.35f).build())
                        .modifyScaleData(d -> d.multiplyValue(RandomHelper.randomBetween(random, 0.5f, 1f)));
                lightSpecs.spawnParticles();
            }
        }
    }

    public static void altarBlessTarget(Level level, NetworkedParticleEffectPositionData positionData, MalumNetworkedParticleEffectColorData colorData, LivingEntity target) {
        var random = level.random;
        var position = positionData.getAsVector();

        Function<Vec3, Consumer<LodestoneWorldParticle>> behavior = initialOffset -> p -> {
            var targetPosition = target.getPosition(p.partialTicksCache);
            p.setParticlePosition(targetPosition.add(initialOffset).add(p.getInterpolatedTravelledDistance()));
        };
        var offset = behavior.apply(position.subtract(target.position()));
        for (int i = 0; i < 5; i++) {
            int lifeDelay = i * 2;
            var sigil = cultistSigil(level, position, colorData.getColor());
            float upwardsMotion = 0.01f * i;
            float scale = 2f + i * 0.25f;
            sigil.getBuilder()
                    .setSpinData(SpinParticleData.createRandomDirection(random, 0.3f, 0).setEasing(Easing.EXPO_OUT).build())
                    .modifyScaleData(d -> d.multiplyValue(scale))
                    .setBehavior(DirectionalParticleBehavior.directional(new Vec3(0, 1, 0)))
                    .setFriction(0.9f)
                    .addRenderActor(offset)
                    .addMotion(0, upwardsMotion, 0f)
                    .setLifetime(15)
                    .setLifeDelay(lifeDelay);
            sigil.spawnParticles();
        }
        long gameTime = level.getGameTime();
        float time = 16;
        for (int i = 0; i < 8; i++) {
            var offsetPosition = VecHelper.rotatingRadialOffset(position, 0.9f, i, 8, gameTime, time);
            offsetPosition = offsetPosition.add(0, (Math.cos(((gameTime + i * 480) % time) / time) * 0.25f) + 0.5f, 0);
            offset = behavior.apply(offsetPosition.subtract(position));
            float upwardsVelocity = 0.2f;
            for (int j = 0; j < 3; j++) {
                var lightSpecs = SparkParticleEffects.spiritMotionSparks(level, offsetPosition, colorData.getColor(), new WorldParticleOptions(MalumParticles.MOTION_LINES));
                float velocity = RandomHelper.randomBetween(random, 0.05f, 0.1f);
                var motion = offsetPosition.subtract(position).normalize().scale(velocity);
                int lifeDelay = j * 3;
                lightSpecs.getBuilder()
                        .setRenderType(LodestoneWorldParticleRenderType.LUMITRANSPARENT)
                        .addRenderActor(offset)
                        .multiplyLifetime(1.25f)
                        .setMotion(motion)
                        .addMotion(0, upwardsVelocity, 0)
                        .setLifeDelay(lifeDelay)
                        .setLengthData(GenericParticleData.create(2f, 0.5f).setEasing(Easing.EXPO_OUT).build())
                        .setTransparencyData(GenericParticleData.create(0.6f, 0.8f, 0f).build())
                        .modifyScaleData(d -> d.multiplyValue(RandomHelper.randomBetween(random, 3f, 4f)));
                lightSpecs.getBloomBuilder()
                        .setRenderType(LodestoneWorldParticleRenderType.LUMITRANSPARENT)
                        .addRenderActor(offset)
                        .multiplyLifetime(0.75f)
                        .setMotion(motion)
                        .addMotion(0, upwardsVelocity, 0)
                        .setLifeDelay(lifeDelay)
                        .setScaleData(GenericParticleData.create(1f, 0.25f).setEasing(Easing.EXPO_OUT).build())
                        .setTransparencyData(GenericParticleData.create(0.05f, 0.35f, 0f).build());
                lightSpecs.spawnParticles();
            }
        }
    }


    public static ParticleEffectSpawner cultistSigil(Level level, Vec3 pos, ColorParticleData colorData) {
        RandomSource rand = level.random;
        var scaleData = GenericParticleData.create(RandomHelper.randomBetween(rand, 0.5f, 0.6f), 0.5f)
                .setEasing(Easing.SINE_IN)
                .setCoefficient(RandomHelper.randomBetween(rand, 1f, 1.25f)).build();
        var sigil = WorldParticleBuilder.create(MalumParticles.CULTIST_SIGIL.get())
                .setTransparencyData(GenericParticleData.create(0.95f, 0.7f).setEasing(Easing.SINE_IN_OUT).build())
                .setRenderType(LodestoneWorldParticleRenderType.LUMITRANSPARENT)
                .setScaleData(scaleData)
                .setColorData(colorData)
                .setLifetime(35)
                .enableNoClip()
                .setFriction(0.98f);
        return new ParticleEffectSpawner(sigil, b -> b.spawn(level, pos));
    }
}
