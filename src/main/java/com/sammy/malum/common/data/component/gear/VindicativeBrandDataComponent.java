package com.sammy.malum.common.data.component.gear;

import com.mojang.serialization.*;
import io.netty.buffer.*;
import net.minecraft.network.codec.*;

public record VindicativeBrandDataComponent() {

    public static final VindicativeBrandDataComponent UNIT = new VindicativeBrandDataComponent();
    public static final Codec<VindicativeBrandDataComponent> CODEC = Codec.unit(VindicativeBrandDataComponent::new);

    public static StreamCodec<ByteBuf, VindicativeBrandDataComponent> STREAM_CODEC = ByteBufCodecs.fromCodec(VindicativeBrandDataComponent.CODEC);

}
