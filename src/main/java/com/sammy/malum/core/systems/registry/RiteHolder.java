package com.sammy.malum.core.systems.registry;

import com.sammy.malum.*;
import com.sammy.malum.core.systems.rite.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.nbt.*;
import net.minecraft.resources.*;
import net.neoforged.neoforge.registries.*;
import org.jetbrains.annotations.*;

public class RiteHolder<T extends SpiritRiteType> extends DeferredHolder<SpiritRiteType, T> {

    protected RiteHolder(ResourceKey<SpiritRiteType> key) {
        super(key);
    }

    public static RiteHolder<SpiritRiteType> getRiteType(CompoundTag pTag) {
        return getRiteType(pTag.getString("rite"));
    }

    public static RiteHolder<SpiritRiteType> getRiteType(String rite) {
        return getRiteType(ResourceLocation.parse(rite));
    }

    public static RiteHolder<SpiritRiteType> getRiteType(ResourceLocation rite) {
        if (rite.getNamespace().equals("minecraft")) {
            rite = MalumMod.malumPath(rite.getPath());
        }
        return new RiteHolder<>(ResourceKey.create(MalumSpiritRiteTypes.SPIRIT_RITE_KEY, rite));
    }

    public SpiritRiteType orElse(@Nullable SpiritRiteType fallback) {
        return isBound() ? value() : fallback;
    }
}
