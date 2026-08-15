package com.sammy.malum.common.data.component;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.UUID;

public record SoulTagDataComponent(UUID targetUUID, Component targetName) {

    public static final Codec<SoulTagDataComponent> CODEC =
            RecordCodecBuilder.create(instance -> instance.group(
                    Codec.STRING.xmap(UUID::fromString, UUID::toString)
                            .fieldOf("target_uuid")
                            .forGetter(SoulTagDataComponent::targetUUID),

                    ComponentSerialization.CODEC
                            .fieldOf("target_name")
                            .forGetter(SoulTagDataComponent::targetName)
            ).apply(instance, SoulTagDataComponent::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, SoulTagDataComponent> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.STRING_UTF8.map(
                            UUID::fromString,
                            UUID::toString
                    ),
                    SoulTagDataComponent::targetUUID,

                    ComponentSerialization.STREAM_CODEC,
                    SoulTagDataComponent::targetName,

                    SoulTagDataComponent::new
            );
}