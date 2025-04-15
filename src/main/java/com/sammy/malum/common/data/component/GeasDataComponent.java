package com.sammy.malum.common.data.component;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sammy.malum.core.systems.geas.*;
import io.netty.buffer.*;
import net.minecraft.network.codec.*;

import java.util.*;

public record GeasDataComponent(GeasEffectType geasEffectType, boolean isCreative) {

    public static Codec<GeasDataComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            GeasEffectType.CODEC.lenientOptionalFieldOf("geasEffectType").forGetter(g -> Optional.ofNullable(g.geasEffectType)),
            Codec.BOOL.fieldOf("isCreative").forGetter(g -> g.isCreative)
    ).apply(instance, (g, c) -> new GeasDataComponent(g.orElse(null), c)));

    public static StreamCodec<ByteBuf, GeasDataComponent> STREAM_CODEC = ByteBufCodecs.fromCodec(GeasDataComponent.CODEC);

    public boolean isInvalid() {
        return geasEffectType == null;
    }
}