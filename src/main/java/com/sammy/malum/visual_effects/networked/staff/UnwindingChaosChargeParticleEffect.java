package com.sammy.malum.visual_effects.networked.staff;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sammy.malum.core.systems.spirit.*;
import com.sammy.malum.registry.client.*;
import com.sammy.malum.visual_effects.networked.*;
import io.netty.buffer.*;
import net.minecraft.network.codec.*;
import net.minecraft.nbt.*;
import net.minecraft.util.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.*;
import net.minecraft.world.phys.*;
import net.neoforged.api.distmarker.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.registry.common.particle.*;
import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.network.particle.*;
import team.lodestar.lodestone.systems.particle.builder.*;
import team.lodestar.lodestone.systems.particle.data.*;
import team.lodestar.lodestone.systems.particle.data.color.*;
import team.lodestar.lodestone.systems.particle.render_types.*;
import team.lodestar.lodestone.systems.particle.world.*;
import team.lodestar.lodestone.systems.particle.world.behaviors.*;
import team.lodestar.lodestone.systems.particle.world.options.*;

import java.awt.*;
import java.util.*;
import java.util.function.*;

import static com.sammy.malum.visual_effects.SpiritLightSpecs.spiritLightSpecs;

public class UnwindingChaosChargeParticleEffect extends MalumNetworkedParticleEffectType<UnwindingChaosChargeParticleEffect.UnwindingChaosChargeEffectData> {

    public record UnwindingChaosChargeEffectData(int entityId) implements NetworkedParticleEffectExtraData {
        public static final Codec<UnwindingChaosChargeEffectData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.INT.fieldOf("entityId").forGetter(UnwindingChaosChargeEffectData::entityId)
        ).apply(instance, UnwindingChaosChargeEffectData::new));

        public static final StreamCodec<ByteBuf, UnwindingChaosChargeEffectData> STREAM_CODEC = ByteBufCodecs.fromCodec(CODEC);
    }

    public UnwindingChaosChargeParticleEffect(String id) {
        super(id);
    }

    @Override
    public Optional<StreamCodec<ByteBuf, ? extends NetworkedParticleEffectExtraData>> getExtraCodec() {
        return Optional.of(UnwindingChaosChargeEffectData.STREAM_CODEC);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void act(Level level, RandomSource random, NetworkedParticleEffectPositionData positionData, MalumNetworkedParticleEffectColorData colorData, UnwindingChaosChargeEffectData extraData) {
        final Entity entity = level.getEntity(extraData.entityId);
        if (entity != null) {
            var smokeColor = new Color(45, 15, 15);
            long gameTime = level.getGameTime();
            var startPos = positionData.getAsVector();
            Consumer<LodestoneWorldParticle> behavior = p -> {
                Vec3 offset = entity.position().add(0, entity.getBbHeight() / 2f, 0).subtract(p.getParticlePosition());
                if (offset.length() == 0) {
                    offset = new Vec3(0, 0.02f, 0);
                }
                float delta = Math.max(p.getAge() / (float) p.getLifetime(), 0);
                float lerp = Easing.QUINTIC_IN_OUT.ease(delta, 0.05f, 0.5f);
                float velocity = Easing.CIRC_IN.ease(delta, 0.05f, 0.2f + offset.length() * 0.4f);
                final Vec3 speed = p.getParticleSpeed().lerp(offset.normalize().scale(velocity), lerp);
                p.setParticleSpeed(speed);
            };

            for (int i = 0; i < 4; i++) {
                MalumSpiritType cyclingSpiritType = colorData.getSpirit();
                Vec3 offsetPosition = VecHelper.rotatingRadialOffset(startPos, 0.8f, i, 2, gameTime, 160);
                var lightSpecs = spiritLightSpecs(level, offsetPosition, cyclingSpiritType, new WorldParticleOptions(ParticleRegistry.SPARK.get()));
                var transparencyData = GenericParticleData.create(0.8f, 0f).build();
                final int lifeDelay = i * 8;
                lightSpecs.getBuilder()
                        .setBehavior(SparkParticleBehavior.sparkBehavior())
                        .setLengthData(GenericParticleData.create(0.2f, 0.6f, 0f).setEasing(Easing.SINE_IN, Easing.SINE_IN_OUT).build())
                        .modifyData(AbstractParticleBuilder::getScaleData, d -> d.multiplyValue(RandomHelper.randomBetween(random, 2, 2.5f)))
                        .modifyColorData(c -> c.multiplyCoefficient(0.5f))
                        .setTransparencyData(transparencyData)
                        .setLifeDelay(lifeDelay)
                        .setLifetime(30)
                        .addTickActor(behavior);
                lightSpecs.getBloomBuilder()
                        .setTransparencyData(transparencyData)
                        .setLifeDelay(lifeDelay)
                        .setLifetime(30)
                        .addTickActor(behavior)
                        .modifyColorData(c -> c.multiplyCoefficient(0.5f))
                        .modifyData(AbstractParticleBuilder::getScaleData, d -> d.multiplyValue(RandomHelper.randomBetween(random, 0.7f, 1.1f)));
                lightSpecs.spawnParticles();

                lightSpecs = spiritLightSpecs(level, offsetPosition, cyclingSpiritType, new WorldParticleOptions(LodestoneParticleTypes.WISP_PARTICLE.get()));
                transparencyData = GenericParticleData.create(1f, 0f).setCoefficient(0.5f).build();
                lightSpecs.getBuilder()
                        .setRenderType(LodestoneWorldParticleRenderType.LUMITRANSPARENT)
                        .setColorData(ColorParticleData.create(smokeColor).build())
                        .setTransparencyData(transparencyData)
                        .setLifeDelay(lifeDelay)
                        .addTickActor(behavior)
                        .setLifetime(30);
                lightSpecs.spawnParticlesRaw();
            }
        }
    }
}