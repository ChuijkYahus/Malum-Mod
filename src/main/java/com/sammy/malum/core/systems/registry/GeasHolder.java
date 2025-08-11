package com.sammy.malum.core.systems.registry;

import com.sammy.malum.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.core.systems.rite.*;
import com.sammy.malum.registry.common.magic.*;
import com.sammy.malum.registry.common.magic.rite.*;
import net.minecraft.nbt.*;
import net.minecraft.resources.*;
import net.neoforged.neoforge.registries.*;
import org.jetbrains.annotations.*;

public class GeasHolder<T extends GeasEffectType> extends DeferredHolder<GeasEffectType, T> {

    protected GeasHolder(ResourceKey<GeasEffectType> key) {
        super(key);
    }
}
