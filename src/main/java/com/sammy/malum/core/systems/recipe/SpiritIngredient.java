package com.sammy.malum.core.systems.recipe;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.recipe.*;
import net.minecraft.core.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.crafting.*;
import org.jetbrains.annotations.*;

import java.util.stream.*;

public record SpiritIngredient(MalumSpiritType spiritType, int count) implements ICustomIngredient, SpiritWrapper {

    public static final MapCodec<SpiritIngredient> CODEC = RecordCodecBuilder.mapCodec(
            builder -> builder
                    .group(
                            MalumSpiritType.CODEC.fieldOf("type").forGetter(SpiritIngredient::spiritType),
                            Codec.INT.fieldOf("count").forGetter(SpiritIngredient::count))
                    .apply(builder, SpiritIngredient::new));

    public SpiritIngredient(SpiritWrapper spiritType, int count) {
        this(spiritType.unwrapSpirit(), count);
    }

    @Override
    public boolean test(ItemStack itemStack) {
        return itemStack.is(spiritType.getSpiritShard()) && itemStack.getCount() >= count;
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
        return spiritType().getSpiritStack(count);
    }

    @Override
    public boolean isSimple() {
        return true;
    }

    @Override
    public @NotNull MalumSpiritType unwrapSpirit() {
        return spiritType.unwrapSpirit();
    }
}
