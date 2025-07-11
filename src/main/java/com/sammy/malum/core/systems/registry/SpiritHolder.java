package com.sammy.malum.core.systems.registry;

import com.sammy.malum.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.nbt.*;
import net.minecraft.resources.*;
import net.neoforged.neoforge.registries.*;
import org.jetbrains.annotations.*;

public class SpiritHolder<T extends SpiritArcanaType> extends DeferredHolder<SpiritArcanaType, T> implements SpiritLike {

    protected SpiritHolder(ResourceKey<SpiritArcanaType> key) {
        super(key);
    }

    public static SpiritHolder<SpiritArcanaType> getSpiritType(CompoundTag pTag) {
        return getSpiritType(pTag.getString("spirit"));
    }

    public static SpiritHolder<SpiritArcanaType> getSpiritType(String spirit) {
        return getSpiritType(ResourceLocation.parse(spirit));
    }

    public static SpiritHolder<SpiritArcanaType> getSpiritType(ResourceLocation spirit) {
        if (spirit.getNamespace().equals("minecraft")) {
            spirit = MalumMod.malumPath(spirit.getPath());
        }
        return new SpiritHolder<>(ResourceKey.create(MalumSpiritTypes.SPIRIT_TYPES_KEY, spirit));
    }

    public boolean is(SpiritLike spirit) {
        return getSpirit().equals(spirit.getSpirit());
    }

    @Override
    public @NotNull SpiritArcanaType getSpirit() {
        return get();
    }

    public SpiritArcanaType orElse(@Nullable SpiritArcanaType fallback) {
        return isBound() ? value() : fallback;
    }
}
