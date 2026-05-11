package com.sammy.malum.common.data.component;

import com.google.common.collect.HashMultimap;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sammy.malum.common.data.custom.wand_parts.WandMaterialType;
import com.sammy.malum.common.data.custom.wand_parts.WandMaterialTypeDataReloadListener;
import com.sammy.malum.common.data.custom.wand_parts.WandPartType;
import com.sammy.malum.common.data.custom.wand_parts.WandPartTypeDataReloadListener;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.LenientUnboundedMapCodec;

import java.util.Map;
import java.util.Optional;

public record WandPartsComponent(Map<WandPartType, WandMaterialType> parts) {

    public static final Codec<WandPartsComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            new LenientUnboundedMapCodec<>(WandPartTypeDataReloadListener.DATA.getLookupCodec(), WandMaterialTypeDataReloadListener.DATA.getLookupCodec())
                    .fieldOf("parts").forGetter(r -> r.parts)
    ).apply(instance, WandPartsComponent::new));

    public static StreamCodec<ByteBuf, WandPartsComponent> STREAM_CODEC = ByteBufCodecs.fromCodec(WandPartsComponent.CODEC);

    public static HashMultimap<WandPartType, WandMaterialType> getPossibleParts(ItemStack stack) {
        HashMultimap<WandPartType, WandMaterialType> result = HashMultimap.create();
        for (WandPartType partType : WandPartTypeDataReloadListener.DATA.getValues()) {
            for (WandMaterialType materialType : WandMaterialTypeDataReloadListener.DATA.getValues()) {
                if (materialType.isValid(stack, partType)) {
                    result.put(partType, materialType);
                }
            }
        }
        return result;
    }

    public boolean isValid() {
        return hasPart(WandPartType.WandPartGroup.CORE) && hasPart(WandPartType.WandPartGroup.HEAD);
    }

    public boolean hasPart(WandPartType.WandPartGroup group) {
        return parts.keySet().stream().anyMatch(p -> p.group().equals(group));
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