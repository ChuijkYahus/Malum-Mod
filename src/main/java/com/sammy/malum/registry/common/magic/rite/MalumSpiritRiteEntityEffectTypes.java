package com.sammy.malum.registry.common.magic.rite;

import com.sammy.malum.*;
import com.sammy.malum.core.systems.geas.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.neoforged.neoforge.registries.*;

public class MalumSpiritRiteEntityEffectTypes {

    public static ResourceKey<Registry<GeasEffectType>> RITE_ENTITY_EFFECT_KEY = ResourceKey.createRegistryKey(MalumMod.malumPath("entity_effect_types"));
    public static final DeferredRegister<GeasEffectType> RITE_ENTITY_EFFECT_TYPES = DeferredRegister.create(RITE_ENTITY_EFFECT_KEY, MalumMod.MALUM);
    public static final Registry<GeasEffectType> RITE_ENTITY_EFFECT_TYPE_REGISTRY = RITE_ENTITY_EFFECT_TYPES.makeRegistry(builder -> builder
            .defaultKey(MalumMod.malumPath("heal"))
            .sync(true));
}
