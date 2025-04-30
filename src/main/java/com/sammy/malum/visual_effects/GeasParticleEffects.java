package com.sammy.malum.visual_effects;

import com.sammy.malum.registry.client.*;
import com.sammy.malum.visual_effects.networked.MalumNetworkedParticleEffectColorData;
import net.minecraft.client.*;
import net.minecraft.util.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.*;
import net.minecraft.world.phys.*;
import team.lodestar.lodestone.handlers.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.network.*;
import team.lodestar.lodestone.systems.network.particle.NetworkedParticleEffectPositionData;
import team.lodestar.lodestone.systems.particle.*;
import team.lodestar.lodestone.systems.particle.builder.*;
import team.lodestar.lodestone.systems.particle.data.*;
import team.lodestar.lodestone.systems.particle.data.color.*;
import team.lodestar.lodestone.systems.particle.data.spin.*;
import team.lodestar.lodestone.systems.particle.render_types.*;
import team.lodestar.lodestone.systems.particle.world.*;
import team.lodestar.lodestone.systems.particle.world.behaviors.*;
import team.lodestar.lodestone.systems.particle.world.options.*;

import java.awt.*;
import java.util.function.*;

import static net.minecraft.util.Mth.nextFloat;

public class GeasParticleEffects {
    public static void wyrdReconstructionRevive(Level level, Entity entity, RandomSource random, NetworkedParticleEffectPositionData positionData, MalumNetworkedParticleEffectColorData colorData) {
        double posX = positionData.getPosX();
        double posY = positionData.getPosY();
        double posZ = positionData.getPosZ();
        Vec3 pos = new Vec3(posX, posY, posZ);
        final Color smokeColor = new Color(45, 15, 15);

        for (int i = 0; i < 12; i++) {
            Color primaryColor = colorData.getColor().getStartingColor();
            int lifetime = RandomHelper.randomBetween(random, 20, 40);
            final boolean isAdditive = i % 2 == 0;
            final float scale = (2f + i * 0.5f) * (isAdditive ? 1 : 2);
            final LodestoneWorldParticleRenderType renderType = (isAdditive ? LodestoneWorldParticleRenderType.ADDITIVE : LodestoneWorldParticleRenderType.LUMITRANSPARENT).withDepthFade();
            final float spin = RandomHelper.randomBetween(random, 0.04f, 0.08f);
            float randomOffset = i * 0.2f;
            for (int j = 0; j < 2; j++) {
                var options = new WorldParticleOptions(ParticleRegistry.GIANT_GLOWING_STAR);
                if (j == 1) {
                    options.setBehavior(DirectionalParticleBehavior.directional());
                }
                WorldParticleBuilder.create(options)
                        .setTransparencyData(GenericParticleData.create(0.1f, 0.4f, 0).build())
                        .setColorData(ColorParticleData.create(primaryColor, smokeColor).setCoefficient(4f).build())
                        .setScaleData(GenericParticleData.create( scale / 2f, scale, 0.5f).setCoefficient(1.25f).setEasing(Easing.EXPO_OUT, Easing.EXPO_IN).build())
                        .setSpinData(SpinParticleData.createRandomDirection(random, spin).build())
                        .setRenderType(renderType)
                        .setRandomOffset(randomOffset, 0)
                        .setMotion(0, 0.001f, 0)
                        .setLifetime(lifetime)
                        .enableNoClip()
                        .repeat(level, posX, posY-0.3f, posZ, 2);
            }
        }

        int sparkCount = 64;
        float distance = 0.7f;
        long gameTime = level.getGameTime();
        Consumer<LodestoneWorldParticle> behavior = p -> {
            Vec3 offset = entity.position().add(0, entity.getBbHeight() / 2f, 0).subtract(p.getParticlePosition());
            if (offset.length() == 0) {
                offset = new Vec3(0, 0.02f, 0);
            }
            float delta = Math.max(p.getAge() / (float) p.getLifetime(), 0) * 2;
            float lerp = Easing.QUINTIC_IN.ease(delta, 0, 0.3f);
            float velocity = Easing.CIRC_IN.ease(delta, 0f, 0.3f + offset.length() * 0.6f);
            final Vec3 speed = p.getParticleSpeed().lerp(offset.normalize().scale(velocity), lerp);
            p.setParticleSpeed(speed);
        };
        for (int i = 0; i < sparkCount; i++) {
            ColorParticleData color = colorData.getColor();
            Color primaryColor = color.getStartingColor();
            Color secondaryColor = color.getEndingColor();
            Vec3 offsetPosition = VecHelper.rotatingRadialOffset(pos, distance, i, sparkCount, gameTime, 320);
            final float motionFactor = RandomHelper.randomBetween(random, 0.06f, 0.12f);
            int lifetime = RandomHelper.randomBetween(random, 20, 40);
            Vec3 motion = offsetPosition.subtract(pos).normalize().scale(motionFactor);
            distance += 0.03f;
            gameTime += 10;
            for (int j = 0; j < 12; j++) {
                boolean isAdditive = j % 2 == 0;
                Color start = isAdditive ? primaryColor : secondaryColor;
                Color end = isAdditive ? secondaryColor : smokeColor;
                float lengthMultiplier = (isAdditive ? 0.5f : 1f) * RandomHelper.randomBetween(random, 0.4f, 1.8f);
                float scaleMultiplier = (isAdditive ? 1.75f : 5.5f) * RandomHelper.randomBetween(random, 0.4f, 1.8f);
                float alphaMultiplier = isAdditive ? 1.5f : 3f;
                float colorCoefficient = isAdditive ? 1f : 1.75f;
                var renderType = isAdditive ? LodestoneWorldParticleRenderType.ADDITIVE : LodestoneWorldParticleRenderType.LUMITRANSPARENT;
                var renderTarget = isAdditive ? RenderHandler.LATE_DELAYED_RENDER : RenderHandler.DELAYED_RENDER;
                WorldParticleBuilder.create(new WorldParticleOptions(ParticleRegistry.GIANT_GLOWING_STAR))
                        .setBehavior(SparkParticleBehavior.sparkBehavior().setForcedDirection(new Vec3(0, 1, 0)))
                        .setLengthData(GenericParticleData.create(0.1f, 0.6f, 0.3f).setEasing(Easing.SINE_IN_OUT, Easing.SINE_IN_OUT).setCoefficient(1.25f).build().multiplyValue(lengthMultiplier))
                        .setScaleData(GenericParticleData.create(0.025f, 0.25f, 0.6f).build().multiplyValue(scaleMultiplier))
                        .setTransparencyData(GenericParticleData.create(0.8f, 0f).build().multiplyValue(alphaMultiplier))
                        .setColorData(ColorParticleData.create(start, end).setCoefficient(colorCoefficient).build())
                        .setSpritePicker(SimpleParticleOptions.ParticleSpritePicker.WITH_AGE)
                        .setRenderTarget(renderTarget)
                        .setRenderType(renderType)
                        .setLifeDelay(j + i / 2)
                        .addTickActor(behavior)
                        .setLifetime(lifetime)
                        .enableNoClip()
                        .setMotion(motion)
                        .spawn(level, offsetPosition.x, offsetPosition.y, offsetPosition.z);
            }
        }
    }


    public static void shakenFaithDamageEffect(Level level, RandomSource random, NetworkedParticleEffectPositionData positionData, MalumNetworkedParticleEffectColorData colorData, WeaponParticleEffectType.WeaponParticleEffectData extraData) {
        var pos = positionData.getAsVector();
        var direction = extraData.getDirection();
        var spinOffset = extraData.getSlashRotation();
        for (int i = 0; i < 6; i++) {
            var slash = WeaponParticleEffects.spawnSlashParticle(level, pos, ParticleRegistry.ROUNDABOUT_SLASH, colorData);
            slash.getBuilder()
                    .setSpinData(SpinParticleData.createRandomDirection(random, 0.15f - i * 0.025f, 0.02f).setSpinOffset(spinOffset).build())
                    .setScaleData(GenericParticleData.create(RandomHelper.randomBetween(random, 0.5f, 1.5f)+i*0.25f, 0).build())
                    .setBehavior(PointyDirectionalParticleBehavior.pointyDirectional(direction))
                    .setLifetime(RandomHelper.randomBetween(random, 8, 12))
                    .setLifeDelay(i/2);
            slash.spawnParticles();
            slash.getBuilder().setBehavior(BillboardParticleBehavior.INSTANCE);
            slash.spawnParticles();
        }
    }
    public static void invertedHeartDamageEffect(Level level, RandomSource random, NetworkedParticleEffectPositionData positionData, MalumNetworkedParticleEffectColorData colorData) {
        double posX = positionData.getPosX();
        double posY = positionData.getPosY();
        double posZ = positionData.getPosZ();
        Vec3 pos = new Vec3(posX, posY, posZ);
        Vec3 directionToPlayer = Minecraft.getInstance().player.getEyePosition().subtract(pos).normalize();
        Vec3 behindPos = pos.subtract(directionToPlayer.scale(2f));
        Vec3 inFrontPos = pos.add(directionToPlayer.scale(0.75f));
        for (int i = 0; i < 3; i++) {
            final ColorParticleData color = colorData.getColor();
            final SpinParticleData spinData = SpinParticleData.createRandomDirection(random, nextFloat(random, 0.15f, 0.3f)).randomSpinOffset(random).build();
            float scaleMultiplier = RandomHelper.randomBetween(random, 0.5f, 1f);
            WorldParticleBuilder.create(ParticleRegistry.SHINE.get())
                    .setScaleData(GenericParticleData.create(0.8f * scaleMultiplier, 0.25f, 0).setEasing(Easing.EXPO_OUT, Easing.SINE_IN).build())
                    .setTransparencyData(GenericParticleData.create(0.6f, 0.07f, 0).setEasing(Easing.SINE_IN, Easing.CIRC_IN).build())
                    .setSpritePicker(SimpleParticleOptions.ParticleSpritePicker.WITH_AGE)
                    .setLifetime(RandomHelper.randomBetween(random, 5, 15))
                    .setRandomMotion(0.02f, 0.02f)
                    .setColorData(color)
                    .setSpinData(spinData)
                    .setRandomOffset(1f)
                    .enableNoClip()
                    .repeat(level, inFrontPos.x, inFrontPos.y, inFrontPos.z, 2);

            WorldParticleBuilder.create(ParticleRegistry.GIANT_GLOWING_STAR.get())
                    .setScaleData(GenericParticleData.create(5f * scaleMultiplier, 0.25f, 0).setEasing(Easing.SINE_IN, Easing.SINE_IN).build())
                    .setTransparencyData(GenericParticleData.create(0.4f, 0.07f, 0).setEasing(Easing.SINE_IN, Easing.CIRC_IN).build())
                    .setColorData(color.invert().build())
                    .setLifetime(RandomHelper.randomBetween(random, 10, 20))
                    .setRenderType(LodestoneWorldParticleRenderType.LUMITRANSPARENT)
                    .setRandomMotion(0.02f, 0.02f)
                    .setSpinData(spinData)
                    .setRandomOffset(1f)
                    .enableNoClip()
                    .repeat(level, behindPos.x, behindPos.y, behindPos.z, 1);
        }
    }
}