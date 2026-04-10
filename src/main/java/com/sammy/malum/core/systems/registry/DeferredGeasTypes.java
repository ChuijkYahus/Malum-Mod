package com.sammy.malum.core.systems.registry;

import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.neoforged.neoforge.registries.*;
import org.jetbrains.annotations.*;

import java.util.function.*;

@SuppressWarnings("unchecked")
public class DeferredGeasTypes extends DeferredRegister<GeasEffectType> {

    protected DeferredGeasTypes(String namespace) {
        super(MalumGeasEffectTypes.GEAS_TYPES_KEY, namespace);
    }

    public static DeferredGeasTypes create(String modid) {
        return new DeferredGeasTypes(modid);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <I extends GeasEffectType> @NotNull GeasHolder<I> register(@NotNull String name, @NotNull Function<ResourceLocation, ? extends I> func) {
        return (GeasHolder<I>) super.register(name, func);
    }

    @Override
    public <I extends GeasEffectType> @NotNull GeasHolder<I> register(@NotNull String name, @NotNull Supplier<? extends I> sup) {
        return this.register(name, key -> sup.get());
    }

    @Override
    protected <I extends GeasEffectType> @NotNull GeasHolder<I> createHolder(@NotNull ResourceKey<? extends Registry<GeasEffectType>> registryKey, @NotNull ResourceLocation key) {
        return new GeasHolder<>(ResourceKey.create(registryKey, key));
    }
}