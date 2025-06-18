package com.sammy.malum.core.systems.registry;

import com.mojang.datafixers.util.*;
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

    public void save(CompoundTag tag) {
        save(tag, "spirit");
    }
    @SuppressWarnings("unchecked")
    public void save(CompoundTag tag, String name) {
        MalumSpiritType.HOLDER_CODEC.encode((SpiritHolder<MalumSpiritType>)this, NbtOps.INSTANCE, new CompoundTag()).ifSuccess(c -> tag.put(name, c));
    }

    public static SpiritHolder<MalumSpiritType> load(CompoundTag tag) {
        return load(tag, "spirit");
    }
    public static SpiritHolder<MalumSpiritType> load(CompoundTag tag, String name) {
        return MalumSpiritType.HOLDER_CODEC.decode(NbtOps.INSTANCE, tag.getCompound(name)).map(Pair::getFirst).getOrThrow();
    }

    @Override
    public @NotNull MalumSpiritType unwrapSpirit() {
        return get();
    }
}
