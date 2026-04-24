package com.sammy.malum.common.worldgen.sanctuary;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;

import static com.sammy.malum.MalumMod.LOGGER;

public record SanctuaryGenerationData(BlockPos center, int radius) {

    public static final Codec<SanctuaryGenerationData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BlockPos.CODEC.fieldOf("center").forGetter(SanctuaryGenerationData::center),
            Codec.INT.fieldOf("radius").forGetter(SanctuaryGenerationData::radius)
    ).apply(instance, SanctuaryGenerationData::new));


    public void save(CompoundTag tag) {
        SanctuaryGenerationData.CODEC
                .encodeStart(NbtOps.INSTANCE, this)
                .resultOrPartial(LOGGER::error)
                .ifPresent(p -> tag.put("data", p));
    }

    public static SanctuaryGenerationData load(CompoundTag tag) {
        return SanctuaryGenerationData.CODEC.parse(NbtOps.INSTANCE, tag.get("data")).resultOrPartial(LOGGER::error).orElse(null);
    }
}
