package com.sammy.malum.common.data.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sammy.malum.MalumMod;
import com.sammy.malum.common.data.custom.wand_parts.WandMaterialType;
import com.sammy.malum.common.data.custom.wand_parts.WandMaterialTypeDataReloadListener;
import com.sammy.malum.common.data.custom.wand_parts.WandPartType;
import com.sammy.malum.common.data.custom.wand_parts.WandPartTypeDataReloadListener;
import com.sammy.malum.core.systems.geas.GeasEffectType;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public record WandPartsComponent(Map<Optional<WandPartType>, WandMaterialType> parts) {

    public static final Codec<WandPartsComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.unboundedMap(WandPartTypeDataReloadListener.DATA.getLookupOptionalCodec(), WandMaterialTypeDataReloadListener.DATA.getLookupCodec())
                    .fieldOf("parts").forGetter(r -> r.parts)
    ).apply(instance, WandPartsComponent::new));

    public static StreamCodec<ByteBuf, WandPartsComponent> STREAM_CODEC = ByteBufCodecs.fromCodec(WandPartsComponent.CODEC);

    public WandPartsComponent addPart(WandPartType part, WandMaterialType material) {
        var copy = new HashMap<>(parts);
        copy.put(Optional.of(part), material);
        return new WandPartsComponent(copy);
    }

    public WandPartsComponent removePart(WandPartType part) {
        var copy = new HashMap<>(parts);
        copy.remove(Optional.of(part));
        return new WandPartsComponent(copy);
    }

    public WandPartsComponent removePart(WandPartType.WandPartGroup group) {
        for (Map.Entry<Optional<WandPartType>, WandMaterialType> entry : parts.entrySet()) {
            WandPartType partType = entry.getKey();
            if (partType.group().equals(group)) {
                return removePart(partType);
            }
        }
        return this;
    }
}