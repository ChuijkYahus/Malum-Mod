package com.sammy.malum.visual_effects.networked.geas;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sammy.malum.client.*;
import com.sammy.malum.core.systems.spirit.*;
import com.sammy.malum.registry.client.*;
import com.sammy.malum.visual_effects.*;
import com.sammy.malum.visual_effects.networked.*;
import io.netty.buffer.*;
import net.minecraft.network.codec.*;
import net.minecraft.util.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.*;
import net.minecraft.world.phys.*;
import net.neoforged.api.distmarker.*;
import team.lodestar.lodestone.handlers.*;
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

import static com.sammy.malum.visual_effects.SpiritLightSpecs.*;

public class LifeweaverHealingBeamParticleEffect extends MalumNetworkedParticleEffectType<LifeweaverHealingBeamParticleEffect.LifeweaverHealingBeamEffectData> {

    public record LifeweaverHealingBeamEffectData(int targetId, int sourceId) implements NetworkedParticleEffectExtraData {
        public static final Codec<LifeweaverHealingBeamEffectData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.INT.fieldOf("targetId").forGetter(LifeweaverHealingBeamEffectData::targetId),
                Codec.INT.fieldOf("sourceId").forGetter(LifeweaverHealingBeamEffectData::sourceId)
        ).apply(instance, LifeweaverHealingBeamEffectData::new));

        public static final StreamCodec<ByteBuf, LifeweaverHealingBeamEffectData> STREAM_CODEC = ByteBufCodecs.fromCodec(CODEC);
    }

    public LifeweaverHealingBeamParticleEffect(String id) {
        super(id);
    }

    @Override
    public Optional<StreamCodec<ByteBuf, ? extends NetworkedParticleEffectExtraData>> getExtraCodec() {
        return Optional.of(LifeweaverHealingBeamEffectData.STREAM_CODEC);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void act(Level level, RandomSource random, NetworkedParticleEffectPositionData positionData, MalumNetworkedParticleEffectColorData colorData, LifeweaverHealingBeamEffectData extraData) {
        GeasParticleEffects.healingBeam(level, random, positionData, colorData, extraData);
    }
}