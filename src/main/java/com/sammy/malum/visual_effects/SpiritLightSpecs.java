package com.sammy.malum.visual_effects;

import com.sammy.malum.client.*;
import com.sammy.malum.core.systems.spirit.*;
import com.sammy.malum.registry.client.*;
import net.minecraft.world.level.*;
import net.minecraft.world.phys.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.registry.common.particle.*;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.particle.*;
import team.lodestar.lodestone.systems.particle.builder.*;
import team.lodestar.lodestone.systems.particle.data.*;
import team.lodestar.lodestone.systems.particle.data.color.*;
import team.lodestar.lodestone.systems.particle.data.spin.*;
import team.lodestar.lodestone.systems.particle.world.*;
import team.lodestar.lodestone.systems.particle.world.options.*;

import java.util.*;
import java.util.function.*;

import static net.minecraft.util.Mth.*;

public class SpiritLightSpecs {

    public static void coolLookingShinyThing(Level level, Vec3 pos, MalumSpiritType spiritType) {
        var centralLightSpecs = spiritLightSpecs(level, pos, spiritType, new WorldParticleOptions(ParticleRegistry.SHINE.get()));
        centralLightSpecs.getBuilder()
                .multiplyLifetime(0.6f)
                .modifyColorData(d -> d.multiplyCoefficient(0.5f))
                .modifyData(AbstractParticleBuilder::getScaleData, d -> d.multiplyValue(6f))
                .modifyData(AbstractParticleBuilder::getTransparencyData, d -> d.multiplyValue(3f));
        centralLightSpecs.getBloomBuilder()
                .multiplyLifetime(0.6f)
                .modifyColorData(d -> d.multiplyCoefficient(0.5f))
                .modifyData(AbstractParticleBuilder::getScaleData, d -> d.multiplyValue(4f))
                .modifyData(AbstractParticleBuilder::getTransparencyData, d -> d.multiplyValue(3f));
        centralLightSpecs.spawnParticles();
    }

    public static void rotatingLightSpecs(Level level, Vec3 pos, ColorParticleData colorData, float distance, int rotatingSpecs, Consumer<WorldParticleBuilder> modifier) {
        rotatingLightSpecs(level, pos, new WorldParticleOptions(ParticleRegistry.LIGHT_SPEC_SMALL.get()), colorData, distance, rotatingSpecs, modifier);
    }

    public static void rotatingLightSpecs(Level level, Vec3 pos, WorldParticleOptions options, ColorParticleData colorData, float distance, int rotatingSpecs, Consumer<WorldParticleBuilder> modifier) {
        rotatingLightSpecs(level, pos, p -> spiritLightSpecs(level, p, colorData, options).act(modifier), distance, rotatingSpecs);
    }

    public static void rotatingLightSpecs(Level level, Vec3 pos, ColorParticleData colorData, float distance, int rotatingSpecs) {
        rotatingLightSpecs(level, pos, new WorldParticleOptions(ParticleRegistry.LIGHT_SPEC_SMALL.get()), colorData, distance, rotatingSpecs);
    }

    public static void rotatingLightSpecs(Level level, Vec3 pos, WorldParticleOptions options, ColorParticleData colorData, float distance, int rotatingSpecs) {
        rotatingLightSpecs(level, pos, p -> spiritLightSpecs(level, p, colorData, options), distance, rotatingSpecs);
    }

    public static void rotatingLightSpecs(Level level, Vec3 pos, MalumSpiritType spiritType, float distance, int rotatingSpecs) {
        rotatingLightSpecs(level, pos, new WorldParticleOptions(ParticleRegistry.LIGHT_SPEC_SMALL.get()), spiritType, distance, rotatingSpecs);
    }

    public static void rotatingLightSpecs(Level level, Vec3 pos, WorldParticleOptions options, MalumSpiritType spiritType, float distance, int rotatingSpecs) {
        rotatingLightSpecs(level, pos, p -> spiritLightSpecs(level, p, spiritType, options), distance, rotatingSpecs);
    }

    public static void rotatingLightSpecs(Level level, Vec3 pos, MalumSpiritType spiritType, float distance, int rotatingSpecs, Consumer<WorldParticleBuilder> modifier) {
        rotatingLightSpecs(level, pos, new WorldParticleOptions(ParticleRegistry.LIGHT_SPEC_SMALL.get()), spiritType, distance, rotatingSpecs, modifier);
    }

    public static void rotatingLightSpecs(Level level, Vec3 pos, WorldParticleOptions options, MalumSpiritType spiritType, float distance, int rotatingSpecs, Consumer<WorldParticleBuilder> modifier) {
        rotatingLightSpecs(level, pos, p -> spiritLightSpecs(level, p, spiritType, options).act(modifier), distance, rotatingSpecs);
    }

    public static void rotatingLightSpecs(Level level, Vec3 pos, Function<Vec3, ParticleEffectSpawner> particleSpawner, float distance, int rotatingSpecs) {
        long gameTime = level.getGameTime();
        if (level.getGameTime() % 2L == 0) {
            for (int i = 0; i < rotatingSpecs; i++) {
                long offsetGameTime = gameTime + i * 120L;
                double yOffset = Math.sin((offsetGameTime % 360) / 30f) * 0.1f;
                Vec3 offsetPosition = VecHelper.rotatingRadialOffset(pos.add(0, yOffset, 0), distance, i, rotatingSpecs, gameTime, 160);

                var lightSpecs = particleSpawner.apply(offsetPosition);
                lightSpecs.getBuilder().multiplyLifetime(2f).modifyData(AbstractParticleBuilder::getScaleData, d -> d.multiplyValue(1.2f));
                lightSpecs.getBloomBuilder().act(b -> b.multiplyLifetime(1.4f).modifyData(List.of(b::getScaleData, b::getTransparencyData), d -> d.multiplyValue(0.6f)));
                lightSpecs.spawnParticles();
            }
        }
        var lightSpecs = particleSpawner.apply(pos);
        lightSpecs.getBuilder()
                .multiplyLifetime(0.5f)
                .modifyData(AbstractParticleBuilder::getScaleData, d -> d.multiplyValue(1.7f))
                .modifyData(AbstractParticleBuilder::getTransparencyData, d -> d.multiplyValue(0.5f));

        lightSpecs.getBloomBuilder()
                .multiplyLifetime(0.5f)
                .modifyData(AbstractParticleBuilder::getScaleData, d -> d.multiplyValue(1.3f))
                .modifyData(AbstractParticleBuilder::getTransparencyData, d -> d.multiplyValue(0.75f));

        lightSpecs.spawnParticles();
    }

    public static ParticleEffectSpawner spiritLightSpecs(Level level, Vec3 pos, MalumSpiritType spiritType) {
        return spiritLightSpecs(level, pos, spiritType, new WorldParticleOptions(ParticleRegistry.LIGHT_SPEC_SMALL));
    }

    public static ParticleEffectSpawner spiritLightSpecs(Level level, Vec3 pos, ColorParticleData colorData) {
        return spiritLightSpecs(level, pos, colorData, new WorldParticleOptions(ParticleRegistry.LIGHT_SPEC_SMALL));
    }

    public static ParticleEffectSpawner spiritLightSpecs(Level level, Vec3 pos, MalumSpiritType spiritType, WorldParticleOptions options) {
        return spiritLightSpecs(level, pos, options, o -> SpiritBasedParticleBuilder.createSpirit(o).setSpirit(spiritType));
    }

    public static ParticleEffectSpawner spiritLightSpecs(Level level, Vec3 pos, ColorParticleData colorData, WorldParticleOptions options) {
        return spiritLightSpecs(level, pos, options, o -> WorldParticleBuilder.create(o).setColorData(colorData));
    }

    public static ParticleEffectSpawner spiritLightSpecs(Level level, Vec3 pos, WorldParticleOptions options) {
        return spiritLightSpecs(level, pos, options, WorldParticleBuilder::create);
    }

    public static ParticleEffectSpawner spiritLightSpecs(Level level, Vec3 pos, WorldParticleOptions options, Function<WorldParticleOptions, WorldParticleBuilder> builderSupplier) {
        var builder = builderSupplier.apply(options);
        var bloomBuilder = builderSupplier.apply(new WorldParticleOptions(LodestoneParticleTypes.WISP_PARTICLE));
        return spiritLightSpecs(level, pos, builder, bloomBuilder);
    }

    public static ParticleEffectSpawner spiritLightSpecs(Level level, Vec3 pos, WorldParticleBuilder builder, WorldParticleBuilder bloomBuilder) {
        var rand = level.getRandom();
        final SpinParticleData spinData = SpinParticleData.createRandomDirection(rand, nextFloat(rand, 0.05f, 0.1f)).randomSpinOffset(rand).build();
        final Consumer<LodestoneWorldParticle> slowDown = p -> p.setParticleSpeed(p.getParticleSpeed().scale(0.95f));
        int lifetime = RandomHelper.randomBetween(rand, 10, 20);
        final WorldParticleBuilder worldParticleBuilder = builder
                .setTransparencyData(GenericParticleData.create(0.8f, 0f).build())
                .setSpinData(spinData)
                .setScaleData(GenericParticleData.create(0.025f, RandomHelper.randomBetween(rand, 0.2f, 0.3f), 0).build())
                .setLifetime(lifetime)
                .enableNoClip()
                .addTickActor(slowDown);
        final WorldParticleBuilder bloomParticleBuilder = SpiritLightSpecs.spiritBloom(level, bloomBuilder, lifetime).setSpinData(spinData).addTickActor(slowDown);
        return new ParticleEffectSpawner(level, pos, worldParticleBuilder, bloomParticleBuilder);
    }

    public static WorldParticleBuilder spiritBloom(Level level, MalumSpiritType spiritType, int lifetime) {
        return spiritBloom(level, spiritType, new WorldParticleOptions(LodestoneParticleTypes.WISP_PARTICLE), lifetime);
    }

    public static WorldParticleBuilder spiritBloom(Level level, ColorParticleData colorData, int lifetime) {
        return spiritBloom(level, colorData, new WorldParticleOptions(LodestoneParticleTypes.WISP_PARTICLE), lifetime);
    }

    public static WorldParticleBuilder spiritBloom(Level level, MalumSpiritType spiritType, WorldParticleOptions options, int lifetime) {
        return spiritBloom(level, options, o -> SpiritBasedParticleBuilder.createSpirit(o).setSpirit(spiritType), lifetime);
    }

    public static WorldParticleBuilder spiritBloom(Level level, ColorParticleData colorData, WorldParticleOptions options, int lifetime) {
        return spiritBloom(level, options, o -> WorldParticleBuilder.create(o).setColorData(colorData), lifetime);
    }

    public static WorldParticleBuilder spiritBloom(Level level, WorldParticleOptions options, Function<WorldParticleOptions, WorldParticleBuilder> builderSupplier, int lifetime) {
        return spiritBloom(level, builderSupplier.apply(options), lifetime);
    }

    public static WorldParticleBuilder spiritBloom(Level level, WorldParticleBuilder builder, int lifetime) {
        var rand = level.random;
        return builder
                .setTransparencyData(GenericParticleData.create(0.35f, 0f).build())
                .setScaleData(GenericParticleData.create(0.04f, RandomHelper.randomBetween(rand, 0.08f, 0.14f), 0).setEasing(Easing.SINE_IN, Easing.SINE_IN_OUT).build())
                .setLifetime(lifetime)
                .enableNoClip();
    }
}