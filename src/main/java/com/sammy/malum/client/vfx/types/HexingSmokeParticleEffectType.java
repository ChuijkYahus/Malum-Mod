package com.sammy.malum.client.vfx.types;

import com.sammy.malum.client.vfx.types.base.*;
import net.minecraft.util.*;
import net.minecraftforge.api.distmarker.*;
import team.lodestar.lodestone.helpers.render.ColorHelper;
import team.lodestar.lodestone.registry.common.particle.LodestoneParticleRegistry;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.particle.*;
import team.lodestar.lodestone.systems.particle.data.*;

import java.awt.*;
import java.util.function.*;

public class HexingSmokeParticleEffectType extends ParticleEffectType {

    public final float intensity;

    public HexingSmokeParticleEffectType(String id, float intensity) {
        super(id);
        this.intensity = intensity;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public Supplier<ParticleEffectType.ParticleEffectActor> get() {
        return () -> (level, random, positionData, colorData) -> {
            Color primaryColor = colorData.getPrimaryColor();
            Color secondaryColor = colorData.getSecondaryColor();
            double posX = positionData.posX;
            double posY = positionData.posY;
            double posZ = positionData.posZ;
            int adjustedLifespan = (int) (20 * (intensity)*0.6f);
            for (int i = 0; i <= 3; i++) {
                int spinDirection = (random.nextBoolean() ? 1 : -1);
                WorldParticleBuilder.create(LodestoneParticleRegistry.WISP_PARTICLE)
                        .setTransparencyData(GenericParticleData.create(0.05f, 0.1f, 0).build())
                        .setSpinData(SpinParticleData.create(0.025f * spinDirection, (0.2f + random.nextFloat() * 0.05f) * spinDirection, 0).setEasing(Easing.QUINTIC_OUT, Easing.SINE_IN).build())
                        .setScaleData(GenericParticleData.create(0.025f, 0.1f + random.nextFloat() * 0.075f, 0.35f).setEasing(Easing.QUINTIC_OUT, Easing.SINE_IN).build())
                        .setColorData(ColorParticleData.create(primaryColor, secondaryColor).build())
                        .setLifetime((int) (adjustedLifespan * Mth.nextFloat(random, 0.9f, 1.2f)))
                        .enableNoClip()
                        .setRandomOffset(0.2f, 0.2f)
                        .setRandomMotion(0.02f)
                        .addActor(p -> p.setParticleSpeed(p.getParticleSpeed().scale(0.95f)))
                        .repeat(level, posX, posY, posZ, (int) (4*intensity));
            }
            WorldParticleBuilder.create(LodestoneParticleRegistry.WISP_PARTICLE)
                    .setTransparencyData(GenericParticleData.create(0.02f, 0.05f, 0).build())
                    .setSpinData(SpinParticleData.create(0.1f, 0.4f, 0).setEasing(Easing.QUINTIC_OUT, Easing.SINE_IN).build())
                    .setScaleData(GenericParticleData.create(0.15f, 0.4f, 0.35f).setEasing(Easing.QUINTIC_OUT, Easing.SINE_IN).build())
                    .setColorData(ColorParticleData.create(primaryColor, secondaryColor).build())
                    .setLifetime((int) (adjustedLifespan * Mth.nextFloat(random, 0.65f, 0.85f)))
                    .enableNoClip()
                    .setRandomOffset(0.05f, 0.05f)
                    .setRandomMotion(0.05f)
                    .addActor(p -> p.setParticleSpeed(p.getParticleSpeed().scale(0.5f)))
                    .repeat(level, posX, posY, posZ, (int) (6*intensity));
            WorldParticleBuilder.create(LodestoneParticleRegistry.SMOKE_PARTICLE)
                    .setTransparencyData(GenericParticleData.create(0, 0.05f, 0).build())
                    .setSpinData(SpinParticleData.create(0.1f, 0.25f, 0).setEasing(Easing.QUINTIC_OUT, Easing.SINE_IN).build())
                    .setScaleData(GenericParticleData.create(0.15f, 0.45f, 0.35f).setEasing(Easing.QUINTIC_OUT, Easing.SINE_IN).build())
                    .setColorData(ColorParticleData.create(primaryColor.darker(), ColorHelper.darker(secondaryColor, 3)).build())
                    .setLifetime((int) (adjustedLifespan * Mth.nextFloat(random, 0.9f, 1.2f)))
                    .enableNoClip()
                    .setRandomOffset(0.15f, 0.15f)
                    .setRandomMotion(0.015f, 0.015f)
                    .addActor(p -> p.setParticleSpeed(p.getParticleSpeed().scale(0.92f)))
                    .repeat(level, posX, posY, posZ, (int) (10*intensity));
        };
    }
}