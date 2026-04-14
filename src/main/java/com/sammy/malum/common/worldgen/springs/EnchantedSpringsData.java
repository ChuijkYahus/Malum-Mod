package com.sammy.malum.common.worldgen.springs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sammy.malum.MalumMod;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;

import static com.sammy.malum.MalumMod.LOGGER;

public class EnchantedSpringsData {

    public static final Codec<EnchantedSpringsData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BlockPos.CODEC.fieldOf("center").forGetter(EnchantedSpringsData::getCenter),
            Codec.INT.fieldOf("radius").forGetter(EnchantedSpringsData::getRadius),
            Codec.INT.fieldOf("springRadius").forGetter(EnchantedSpringsData::getSpringRadius)
    ).apply(instance, EnchantedSpringsData::new));


    public final BlockPos center;

    public final int radius;
    public final int springRadius;

    public EnchantedSpringsData(BlockPos center, int radius, int springRadius) {
        this.center = center;
        this.radius = radius;
        this.springRadius = springRadius;
    }

    public void save(CompoundTag tag) {
        EnchantedSpringsData.CODEC
                .encodeStart(NbtOps.INSTANCE, this)
                .resultOrPartial(LOGGER::error)
                .ifPresent(p -> tag.put("groveData", p));
    }

    public static EnchantedSpringsData load(CompoundTag tag) {
        return EnchantedSpringsData.CODEC.parse(NbtOps.INSTANCE, tag.get("groveData")).resultOrPartial(LOGGER::error).orElse(null);
    }

    public BlockPos getCenter() {
        return center;
    }

    public int getRadius() {
        return radius;
    }

    public int getSpringRadius() {
        return springRadius;
    }
}
