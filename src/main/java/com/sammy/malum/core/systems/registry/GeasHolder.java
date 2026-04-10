package com.sammy.malum.core.systems.registry;

import com.sammy.malum.core.systems.geas.*;
import net.minecraft.resources.*;
import net.neoforged.neoforge.registries.*;

public class GeasHolder<T extends GeasEffectType> extends DeferredHolder<GeasEffectType, T> {

    protected GeasHolder(ResourceKey<GeasEffectType> key) {
        super(key);
    }
}
