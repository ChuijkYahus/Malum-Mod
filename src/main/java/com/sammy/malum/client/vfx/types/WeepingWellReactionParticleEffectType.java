package com.sammy.malum.client.vfx.types;

import com.sammy.malum.client.vfx.types.base.*;
import net.minecraft.util.*;
import net.minecraftforge.api.distmarker.*;
import team.lodestar.lodestone.helpers.render.ColorHelper;
import team.lodestar.lodestone.registry.common.particle.LodestoneParticleRegistry;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.particle.*;
import team.lodestar.lodestone.systems.particle.data.*;
import team.lodestar.lodestone.systems.particle.world.*;

import java.awt.*;
import java.util.function.*;

public class WeepingWellReactionParticleEffectType extends ParticleEffectType {

    public WeepingWellReactionParticleEffectType(String id) {
        super(id);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public Supplier<ParticleEffectActor> get() {
        return () -> (level, random, positionData, colorData) -> {
            double posX = positionData.posX;
            double posY = positionData.posY;
            double posZ = positionData.posZ;
            for (int i = 0; i < 8; i++) {
                float multiplier = Mth.nextFloat(random, 0.1f, 1.5f);
                Color color = new Color((int)(12*multiplier), (int)(4*multiplier), (int)(14*multiplier));
                int spinDirection = (random.nextBoolean() ? 1 : -1);
                int spinOffset = random.nextInt(360);
                float motionMultiplier = (float) (1+Math.pow(random.nextFloat()+i*0.2f, 2));
                float extraMotion = 0.2f * motionMultiplier * ((8 - i) / 8f);
                WorldParticleBuilder.create(LodestoneParticleRegistry.TWINKLE_PARTICLE)
                        .setTransparencyData(GenericParticleData.create(0.2f, 1f, 0).build())
                        .setSpinData(SpinParticleData.create(0.9f * spinDirection, 0).setSpinOffset(spinOffset).setCoefficient(1.25f).setEasing(Easing.CUBIC_IN).build())
                        .setScaleData(GenericParticleData.create(0.075f, 0.25f, 0).setCoefficient(0.8f).setEasing(Easing.QUINTIC_OUT, Easing.EXPO_IN_OUT).build())
                        .setColorData(ColorParticleData.create(ColorHelper.brighter(color, 2), color).build())
                        .setLifetime(30)
                        .enableNoClip()
                        .setRandomOffset(0.85f)
                        .setGravity(1.1f)
                        .addMotion(0, 0.3f + random.nextFloat() * 0.15f * motionMultiplier, 0)
                        .disableNoClip()
                        .setRandomMotion(extraMotion, extraMotion)
                        .setDiscardFunction(SimpleParticleOptions.ParticleDiscardFunctionType.ENDING_CURVE_INVISIBLE)
                        .setRenderType(LodestoneWorldParticleRenderType.LUMITRANSPARENT)
                        .repeat(level, posX, posY, posZ, 6);
            }
            int spinOffset = random.nextInt(360);
            for (int i = 0; i < 4; i++) {
                float multiplier = Mth.nextFloat(random, 0.1f, 1.5f);
                Color color = new Color((int)(4*multiplier), (int)(2*multiplier), (int)(4*multiplier));
                int spinDirection = (random.nextBoolean() ? 1 : -1);
                float scaleMultiplier = (float) (1+Math.pow(random.nextFloat(), 2));
                WorldParticleBuilder.create(LodestoneParticleRegistry.SPARKLE_PARTICLE)
                        .setTransparencyData(GenericParticleData.create(0.7f, 0.5f, 0).setEasing(Easing.SINE_IN, Easing.CIRC_IN).build())
                        .setSpinData(SpinParticleData.create((0.125f + random.nextFloat() * 0.075f) * spinDirection).setSpinOffset(spinOffset).build())
                        .setScaleData(GenericParticleData.create(1.8f*scaleMultiplier, 0.6f, 0).setEasing(Easing.EXPO_OUT, Easing.SINE_IN).build())
                        .setColorData(ColorParticleData.create(color.brighter(), color.darker()).build())
                        .setLifetime(25)
                        .setRandomOffset(0.6f)
                        .enableNoClip()
                        .setRandomMotion(0.02f, 0.02f)
                        .setDiscardFunction(SimpleParticleOptions.ParticleDiscardFunctionType.ENDING_CURVE_INVISIBLE)
                        .setRenderType(LodestoneWorldParticleRenderType.LUMITRANSPARENT)
                        .repeat(level, posX, posY, posZ, 5);
            }
        };
    }
}