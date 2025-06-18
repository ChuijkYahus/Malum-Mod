package com.sammy.malum.core.systems.registry;

import com.sammy.malum.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.nbt.*;
import net.minecraft.resources.*;
import net.neoforged.neoforge.registries.*;
import org.jetbrains.annotations.*;

public class SpiritHolder<T extends MalumSpiritType> extends DeferredHolder<MalumSpiritType, T> implements SpiritWrapper {

    protected SpiritHolder(ResourceKey<MalumSpiritType> key) {
        super(key);
    }

    public static SpiritHolder<MalumSpiritType> getSpiritType(CompoundTag pTag) {
        return getSpiritType(pTag.getString("spirit"));
    }

    public static SpiritHolder<MalumSpiritType> getSpiritType(String spirit) {
        return getSpiritType(ResourceLocation.parse(spirit));
    }

    public static SpiritHolder<MalumSpiritType> getSpiritType(ResourceLocation spirit) {
        if (spirit.getNamespace().equals("minecraft")) {
            spirit = MalumMod.malumPath(spirit.getPath());
        }
        return new SpiritHolder<>(ResourceKey.create(MalumSpiritTypes.SPIRIT_TYPES_KEY, spirit));
    }

    @Override
    public @NotNull MalumSpiritType unwrapSpirit() {
        return get();
    }

    public MalumSpiritType orElse(@Nullable MalumSpiritType fallback) {
        return isBound() ? value() : fallback;
    }
}
