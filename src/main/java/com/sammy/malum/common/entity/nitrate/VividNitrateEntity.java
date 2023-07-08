package com.sammy.malum.common.entity.nitrate;

import com.sammy.malum.client.ParticleEffects;
import com.sammy.malum.common.packets.particle.curiosities.nitrate.VividNitrateBounceParticlePacket;
import com.sammy.malum.registry.common.entity.EntityRegistry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PacketDistributor;
import team.lodestar.lodestone.helpers.render.ColorHelper;
import team.lodestar.lodestone.registry.common.particle.LodestoneParticleRegistry;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.ColorParticleData;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.SpinParticleData;
import team.lodestar.lodestone.systems.particle.world.LodestoneWorldParticleRenderType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static com.sammy.malum.registry.common.PacketRegistry.MALUM_CHANNEL;
import static net.minecraft.util.Mth.nextFloat;

public class VividNitrateEntity extends AbstractNitrateEntity {

    public static final List<Color> COLORS = new ArrayList<>(List.of(
            new Color(231, 58, 73),
            new Color(246, 125, 70),
            new Color(249, 206, 77),
            new Color(48, 242, 71),
            new Color(48, 208, 242),
            new Color(59, 48, 242),
            new Color(145, 42, 247),
            new Color(231, 58, 73)));

    public static final Function<ColorFunctionData, Color> COLOR_FUNCTION = f -> {
        float time = ((f.level.getGameTime() + f.partialTicks) % f.duration) / f.duration;
        float lerp = time + f.offset;
        if (lerp > 1) {
            lerp -= Math.floor(lerp);
        }
        return ColorHelper.multicolorLerp(Easing.SINE_IN, lerp, VividNitrateEntity.COLORS);
    };


    public VividNitrateEntity(Level level) {
        super(EntityRegistry.VIVID_NITRATE.get(), level);
    }

    public VividNitrateEntity(double x, double y, double z, Level level) {
        super(EntityRegistry.VIVID_NITRATE.get(), x, y, z, level);
    }

    public VividNitrateEntity(LivingEntity owner, Level level) {
        super(EntityRegistry.VIVID_NITRATE.get(), owner, level);
    }

    @Override
    public float getExplosionRadius() {
        return 3f;
    }

    @Override
    public int getPierce() {
        return 20;
    }

    @Override
    public void onExplode() {
        Vec3 deltaMovement = getDeltaMovement();
        RandomSource random = level.random;
        double x = Mth.lerp(0.5f, deltaMovement.x, Math.min(2, (0.9f + random.nextFloat() * 0.3f) * (random.nextBoolean() ? -deltaMovement.x : deltaMovement.x) + 0.45f - random.nextFloat() * 0.9f));
        double y = Mth.lerp(0.5f, deltaMovement.y, Math.min(2, (deltaMovement.y * (0.7f + random.nextFloat() * 0.3f) + 0.2f + random.nextFloat() * 0.4f)));
        double z = Mth.lerp(0.5f, deltaMovement.z, Math.min(2, (0.9f + random.nextFloat() * 0.3f) * (random.nextBoolean() ? -deltaMovement.z : deltaMovement.z) + 0.45f - random.nextFloat() * 0.9f));
        if (random.nextFloat() < 0.2f) {
            x *= 1.5f + random.nextFloat() * 0.5f;
            y += 0.25f + random.nextFloat() * 0.5f;
            z *= 1.5f + random.nextFloat() * 0.5f;
        }
        y = Math.min(y, 1f);
        setDeltaMovement(x, y, z);
        if (level instanceof ServerLevel) {
            MALUM_CHANNEL.send(PacketDistributor.TRACKING_CHUNK.with(() -> level.getChunkAt(blockPosition())), new VividNitrateBounceParticlePacket(COLOR_FUNCTION.apply(new ColorFunctionData(level, 0f)), getX(), getY(), getZ()));
        }
    }

    @Override
    public void spawnParticles() {
        if (level.isClientSide) {
            ClientOnly.spawnParticles(this);
        }
    }

    public record ColorFunctionData(Level level, float duration, float offset, float partialTicks) {
        public ColorFunctionData(Level level, float offset) {
            this(level, 12f, offset, 0);
        }
    }


    public static class ClientOnly {
        public static void spawnParticles(VividNitrateEntity vividNitrateEntity) {
            double ox = vividNitrateEntity.xOld, oy = vividNitrateEntity.yOld + vividNitrateEntity.getYOffset(0) + 0.25f, oz = vividNitrateEntity.zOld;
            double x = vividNitrateEntity.getX(), y = vividNitrateEntity.getY() + vividNitrateEntity.getYOffset(0) + 0.25f, z = vividNitrateEntity.getZ();
            Vec3 motion = vividNitrateEntity.getDeltaMovement();
            Vec3 norm = motion.normalize().scale(0.1f);
            float extraAlpha = (float) motion.length();
            float cycles = 3;
            Color firstColor = COLOR_FUNCTION.apply(new ColorFunctionData(vividNitrateEntity.level, 0f)).brighter();
            Color secondColor = COLOR_FUNCTION.apply(new ColorFunctionData(vividNitrateEntity.level, 0.125f)).darker();
            RandomSource rand = vividNitrateEntity.level.getRandom();
            for (int i = 0; i < cycles; i++) {
                float pDelta = i / cycles;
                double lerpX = Mth.lerp(pDelta, ox, x) - motion.x / 4f;
                double lerpY = Mth.lerp(pDelta, oy, y) - motion.y / 4f;
                double lerpZ = Mth.lerp(pDelta, oz, z) - motion.z / 4f;
                float alphaMultiplier = (0.30f + extraAlpha) * Math.min(1, vividNitrateEntity.windUp * 2);
                ParticleEffects.spawnSpiritParticles(vividNitrateEntity.level, lerpX, lerpY, lerpZ, alphaMultiplier + 0.1f, norm, firstColor, secondColor);

                final ColorParticleData.ColorParticleDataBuilder colorDataBuilder = ColorParticleData.create(secondColor, SECOND_SMOKE_COLOR)
                        .setEasing(Easing.QUINTIC_OUT)
                        .setCoefficient(1.75f);
                WorldParticleBuilder.create(LodestoneParticleRegistry.SMOKE_PARTICLE)
                        .setTransparencyData(GenericParticleData.create(Math.min(1, 0.25f * alphaMultiplier), 0f).setEasing(Easing.SINE_IN, Easing.SINE_OUT).build())
                        .setLifetime(65 + rand.nextInt(15))
                        .setSpinData(SpinParticleData.create(nextFloat(rand, -0.1f, 0.1f)).setSpinOffset(rand.nextFloat() * 6.28f).build())
                        .setScaleData(GenericParticleData.create(0.2f + rand.nextFloat() * 0.05f, 0.3f, 0f).build())
                        .setColorData(colorDataBuilder.build())
                        .setRandomOffset(0.02f)
                        .enableNoClip()
                        .setRandomMotion(0.01f, 0.01f)
                        .setRenderType(LodestoneWorldParticleRenderType.LUMITRANSPARENT)
                        .spawn(vividNitrateEntity.level, lerpX, lerpY, lerpZ)
                        .setColorData(colorDataBuilder.setCoefficient(2.75f).build())
                        .spawn(vividNitrateEntity.level, lerpX, lerpY, lerpZ);
            }
        }
    }
}
