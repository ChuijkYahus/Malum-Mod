package com.sammy.malum.common.data.map;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import team.lodestar.lodestone.helpers.ColorHelper;

import java.awt.*;

public record FluidTappingMap(Holder<Block> filledCauldron, Holder<Block> leftoverBlock, Holder<Item> bottledFluid, int color, float chance) {

    public static final Codec<FluidTappingMap> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BuiltInRegistries.BLOCK.holderByNameCodec().fieldOf("filledCauldron").forGetter(FluidTappingMap::filledCauldron),
            BuiltInRegistries.BLOCK.holderByNameCodec().fieldOf("leftoverBlock").forGetter(FluidTappingMap::leftoverBlock),
            BuiltInRegistries.ITEM.holderByNameCodec().fieldOf("bottledFluid").forGetter(FluidTappingMap::bottledFluid),
            Codec.INT.fieldOf("color").forGetter(FluidTappingMap::color),
            Codec.FLOAT.fieldOf("chance").forGetter(FluidTappingMap::chance)
    ).apply(instance, FluidTappingMap::new));

    public Color getColor() {
        return ColorHelper.getColor(color);
    }
}