package com.sammy.malum.common.block.ether;

import com.sammy.malum.registry.common.MalumParticles;
import com.sammy.malum.registry.common.block.MalumBlockEntities;
import com.sammy.malum.visual_effects.SparkParticleEffects;
import com.sammy.malum.visual_effects.SpiritLightSpecs;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import team.lodestar.lodestone.handlers.LodestoneRenderHandler;

import team.lodestar.lodestone.modules.core.easing.Easing;
import team.lodestar.lodestone.modules.toolkit.blockentity.LodestoneBlockEntityType;
import team.lodestar.lodestone.systems.particle.builder.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;

import java.awt.*;
import java.util.List;

public class EtherCandleBlockEntity extends EtherBlockEntity {

    public static final List<Vec3> BASE_OFFSETS = List.of(
            new Vec3(0.5f, 0.625f, 0.5f),

            new Vec3(0.6f, 0.625f, 0.625f),
            new Vec3(0.46875, 0.75f, 0.375f),

            new Vec3(0.6875f, 0.625f, 0.625f),
            new Vec3(0.5625f, 0.75f, 0.375f),
            new Vec3(0.3125f, 0.875f, 0.4375f),

            new Vec3(0.6875f, 0.625f, 0.5625f),
            new Vec3(0.5625f, 0.75f, 0.3125f),
            new Vec3(0.3125f, 0.875f, 0.375f),
            new Vec3(0.34375f, 0.9375f, 0.65625f)
    );
    @SuppressWarnings("unchecked")
    public static final List<Vec3>[] PARTICLE_OFFSETS = new List[]{
            BASE_OFFSETS,
            rotateAroundCenter(BASE_OFFSETS, 1),
            rotateAroundCenter(BASE_OFFSETS, 2),
            rotateAroundCenter(BASE_OFFSETS, 3)
    };

    public static final RandomSource CANDLE_ROTATION = RandomSource.create();

    public EtherCandleBlockEntity(LodestoneBlockEntityType<? extends EtherBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public EtherCandleBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.ETHER_CANDLE.get(), pos, state);
    }
    @Override
    public void clientTick(Level level) {
        if (firstColor == null) {
            return;
        }
        var random = level.random;
        var start = new Color(firstColor.rgb());
        var end = new Color(secondColor == null ? firstColor.rgb() : secondColor.rgb());

        var state = getBlockState();

        //This should match perfectly the use of WeighedRandom in WeighedBakedModel
        CANDLE_ROTATION.setSeed(state.getSeed(getBlockPos()));
        int rotationIndex = Math.abs((int) CANDLE_ROTATION.nextLong()) % 4;
        var offsets = PARTICLE_OFFSETS[rotationIndex];
        int candles = state.getValue(EtherCandleBlock.CANDLES);
        for (int i = 0; i < candles; i++) {
            int offsetIndex = i;
            for (int j = 0; j < candles; j++) {
                offsetIndex += j;
            }
            var offset = offsets.get(offsetIndex);
            var x = worldPosition.getX()+offset.x;
            var y = worldPosition.getY()+offset.y;
            var z = worldPosition.getZ()+offset.z;
            Vec3 candleFlameCenter = new Vec3(x, y - 0.05f, z);
            //Upwards Moving Particles
            if (level.getGameTime() % (32L + i) == 0) {
                var color = ColorParticleData.create(start, end).setCoefficient(1.5f).setEasing(Easing.SINE_IN_OUT).build();
                int lifeDelay = i + Easing.SINE_IN_OUT.asWeighedRandom(random, 0, 5);
                int lifeTime = Easing.SINE_IN_OUT.asWeighedRandom(random, 40, 120);
                float scale = Easing.SINE_IN_OUT.asWeighedRandom(random, 0.1f, 0.3f);
                float velocity = Easing.SINE_IN_OUT.asWeighedRandom(random, 0.012f, 0.024f);
                var lightSpecs = SpiritLightSpecs.spiritLightSpecs(level, candleFlameCenter, color);
                lightSpecs.getBuilder()
                        .setRenderTarget(LodestoneRenderHandler.LATE_DEFERRED_RENDER)
                        .setTransparencyData(GenericParticleData.create(0.2f, 0.4f, 0).setEasing(Easing.EXPO_OUT, Easing.SINE_IN_OUT).build())
                        .setScaleData(GenericParticleData.create(scale, 0).setEasing(Easing.SINE_IN_OUT).build())
                        .addMotion(0, velocity, 0)
                        .setRandomOffset(0.1f)
                        .setLifetime(lifeTime)
                        .setLifeDelay(lifeDelay)
                        .setFriction(1);
                lightSpecs.spawnParticlesRaw();
            }
            //Upwards Moving Sparks
            if (level.getGameTime() % (24L + i) == 0) {
                var color = ColorParticleData.create(start, end).setCoefficient(2.5f).setEasing(Easing.SINE_IN_OUT).build();
                int lifeDelay = i + Easing.SINE_IN_OUT.asWeighedRandom(random, 0, 5);
                int lifeTime = Easing.SINE_IN_OUT.asWeighedRandom(random, 30, 40);
                float scale = Easing.SINE_IN_OUT.asWeighedRandom(random, 0.2f, 0.35f);
                float velocity = Easing.SINE_IN_OUT.asWeighedRandom(random, 0.02f, 0.025f);
                var lightSpecs = SparkParticleEffects.spiritMotionSparks(level, candleFlameCenter, color);
                lightSpecs.getBuilder()
                        .setRenderTarget(LodestoneRenderHandler.LATE_DEFERRED_RENDER)
                        .setLifeDelay(lifeDelay)
                        .setLifetime(lifeTime)
                        .setScaleData(GenericParticleData.create(scale, 0).setEasing(Easing.SINE_IN_OUT).build())
                        .setTransparencyData(GenericParticleData.create(0.1f, 0.6f, 0).setEasing(Easing.EXPO_OUT, Easing.SINE_IN_OUT).build())
                        .addMotion(0, velocity * 0.6f, 0)
                        .setRandomOffset(0.2f);
                lightSpecs.spawnParticlesRaw();
            }

            //Small Shine
            if (level.getGameTime() % (22L + i) == 0) {
                var color = ColorParticleData.create(start, end).setCoefficient(0.6f).setEasing(Easing.SINE_IN_OUT).build();
                int lifeDelay = i + Easing.SINE_IN_OUT.asWeighedRandom(random, 0, 5);
                int lifeTime = Easing.SINE_IN_OUT.asWeighedRandom(random, 30, 40);
                float scale = Easing.SINE_IN_OUT.asWeighedRandom(random, 0.15f, 0.25f);
                WorldParticleBuilder.create(MalumParticles.STAR)
                        .setTransparencyData(GenericParticleData.create(0.1f, 0.6f, 1f).setEasing(Easing.SINE_IN_OUT, Easing.SINE_IN_OUT).build())
                        .setScaleData(GenericParticleData.create(scale, 0).setEasing(Easing.CIRC_IN).build())
                        .setRenderTarget(LodestoneRenderHandler.LATE_DEFERRED_RENDER)
                        .setLifeDelay(lifeDelay)
                        .setLifetime(lifeTime)
                        .setColorData(color)
                        .enableNoClip()
                        .spawn(level, candleFlameCenter);
            }

        }
    }

    public static List<Vec3> rotateAroundCenter(List<Vec3> offsets, int rotationIndex) {
        return offsets.stream().map(o -> rotateAroundCenter(o, rotationIndex)).toList();
    }
    public static Vec3 rotateAroundCenter(Vec3 offset, int rotationIndex) {
        double x = offset.x - 0.5;
        double z = offset.z - 0.5;

        double rx, rz;

        switch (rotationIndex) {
            default -> {
                rx = x;
                rz = z;
            }
            case 1 -> {
                rx = -z;
                rz = x;
            }
            case 2 -> {
                rx = -x;
                rz = -z;
            }
            case 3 -> {
                rx = z;
                rz = -x;
            }
        }

        return new Vec3(
                rx + 0.5,
                offset.y,
                rz + 0.5
        );
    }
}
