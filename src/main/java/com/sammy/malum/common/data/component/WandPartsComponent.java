package com.sammy.malum.common.data.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sammy.malum.common.data.custom.wand_parts.WandMaterialType;
import com.sammy.malum.common.data.custom.wand_parts.WandMaterialTypeDataReloadListener;
import com.sammy.malum.common.data.custom.wand_parts.WandPartType;
import com.sammy.malum.common.data.custom.wand_parts.WandPartTypeDataReloadListener;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.neoforge.common.LenientUnboundedMapCodec;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public record WandPartsComponent(Map<WandPartType, WandMaterialType> parts) {

    public static final Codec<WandPartsComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            new LenientUnboundedMapCodec<>(WandPartTypeDataReloadListener.DATA.getLookupCodec(), WandMaterialTypeDataReloadListener.DATA.getLookupCodec())
                    .fieldOf("parts").forGetter(r -> r.parts)
    ).apply(instance, WandPartsComponent::new));

    public static StreamCodec<ByteBuf, WandPartsComponent> STREAM_CODEC = ByteBufCodecs.fromCodec(WandPartsComponent.CODEC);

    public WandPartsComponent addPart(WandPartType part, WandMaterialType material) {
        if (!part.canApply(this)) {
            return this;
        }
        var copy = new HashMap<>(parts);
        copy.put(part, material);
        return new WandPartsComponent(copy);
    }

    public WandPartsComponent removePart(WandPartType part) {
        var copy = new HashMap<>(parts);
        copy.remove(part);
        return new WandPartsComponent(copy);
    }

    public WandPartsComponent clearGroup(WandPartType.WandPartGroup group) {
        for (Map.Entry<WandPartType, WandMaterialType> entry : parts.entrySet()) {
            WandPartType partType = entry.getKey();
            if (partType.group().equals(group)) {
                return removePart(partType);
            }
        }
        return this;
    }

    public boolean hasPart(WandPartType part) {
        return parts.keySet().stream().anyMatch(part::equals);
    }

    public Optional<WandPartType> getPart(WandPartType.WandPartGroup group) {
        for (WandPartType part : parts.keySet()) {
            if (part.group().equals(group)) {
                return Optional.of(part);
            }
        }
        return Optional.empty();
    }

    public int getCoreTier() {
        return getPart(WandPartType.WandPartGroup.CORE).map(WandPartType::coreTier).orElse(-1);
    }
}