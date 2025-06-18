package com.sammy.malum.core.systems.recipe;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.recipe.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.crafting.*;
import org.jetbrains.annotations.*;

import java.util.stream.*;

public record SpiritIngredient(SpiritHolder<MalumSpiritType> spirit, int count) implements ICustomIngredient, SpiritWrapper {

    public static final MapCodec<SpiritIngredient> CODEC = RecordCodecBuilder.mapCodec(
            builder -> builder
                    .group(
                            MalumSpiritType.HOLDER_CODEC.fieldOf("type").forGetter(SpiritIngredient::spirit),
                            Codec.INT.fieldOf("count").forGetter(SpiritIngredient::count))
                    .apply(builder, SpiritIngredient::new));

    @Override
    public boolean test(ItemStack itemStack) {
        return itemStack.is(spirit.getSpiritShard()) && itemStack.getCount() >= count;
    }

    @Override
    public @NotNull Stream<ItemStack> getItems() {
        return Stream.of(asItemStack());
    }

    @Override
    public @NotNull IngredientType<?> getType() {
        return MalumIngredientTypes.SPIRIT.get();
    }

    public ItemStack asItemStack() {
        return spirit().getSpiritStack(count);
    }

    @Override
    public boolean isSimple() {
        return true;
    }

    @Override
    public @NotNull MalumSpiritType unwrapSpirit() {
        return spirit.unwrapSpirit();
    }
}
