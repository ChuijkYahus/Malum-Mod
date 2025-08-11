package com.sammy.malum.core.systems.registry.rite;

import com.sammy.malum.core.systems.rite.*;
import net.minecraft.resources.*;
import net.neoforged.neoforge.registries.*;

public class RiteHolder<T extends SpiritRiteType> extends DeferredHolder<SpiritRiteType, T> {

    protected RiteHolder(ResourceKey<SpiritRiteType> key) {
        super(key);
    }
}
