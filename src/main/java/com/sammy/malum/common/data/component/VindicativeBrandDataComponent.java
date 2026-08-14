package com.sammy.malum.common.data.component;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sammy.malum.common.item.curiosities.weapons.greatsword.*;
import com.sammy.malum.core.handlers.*;
import io.netty.buffer.*;
import net.minecraft.network.codec.*;
import net.minecraft.util.*;

public record VindicativeBrandDataComponent() {

    public static final VindicativeBrandDataComponent UNIT = new VindicativeBrandDataComponent();
    public static final Codec<VindicativeBrandDataComponent> CODEC = Codec.unit(VindicativeBrandDataComponent::new);

    public static StreamCodec<ByteBuf, VindicativeBrandDataComponent> STREAM_CODEC = ByteBufCodecs.fromCodec(VindicativeBrandDataComponent.CODEC);

}
