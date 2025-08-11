package com.sammy.malum.core.systems.registry.rite;

import com.sammy.malum.core.systems.rite.effect.*;
import net.minecraft.resources.*;
import net.neoforged.neoforge.registries.*;

public class RiteEffectHolder<T extends SpiritRiteEffect> extends DeferredHolder<SpiritRiteEffect, T> {

    protected RiteEffectHolder(ResourceKey<SpiritRiteEffect> key) {
        super(key);
    }
}
