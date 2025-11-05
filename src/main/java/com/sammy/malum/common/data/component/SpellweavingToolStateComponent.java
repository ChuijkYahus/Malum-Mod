package com.sammy.malum.common.data.component;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import io.netty.buffer.*;
import net.minecraft.network.codec.*;
import net.minecraft.util.*;

public record SpellweavingToolStateComponent(boolean isPrimed, int timer) {

    public SpellweavingToolStateComponent() {
        this(false, 0);
    }

    public static Codec<SpellweavingToolStateComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.BOOL.fieldOf("isPrimed").forGetter(SpellweavingToolStateComponent::isPrimed),
            ExtraCodecs.NON_NEGATIVE_INT.fieldOf("timer").forGetter(SpellweavingToolStateComponent::timer)
    ).apply(instance, SpellweavingToolStateComponent::new));

    public static StreamCodec<ByteBuf, SpellweavingToolStateComponent> STREAM_CODEC = ByteBufCodecs.fromCodec(SpellweavingToolStateComponent.CODEC);

}
