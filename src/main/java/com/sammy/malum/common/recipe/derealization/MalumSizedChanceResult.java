package com.sammy.malum.common.recipe.derealization;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public record MalumSizedChanceResult(ItemStack result, float chance) {
    public static final Codec<MalumSizedChanceResult> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ItemStack.CODEC.fieldOf("item").forGetter(MalumSizedChanceResult::result),
            Codec.FLOAT.optionalFieldOf("chance", 1.0F).forGetter(MalumSizedChanceResult::chance)
    ).apply(instance, MalumSizedChanceResult::new));
}
