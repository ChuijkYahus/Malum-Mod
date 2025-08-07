package com.sammy.malum.core.systems.registry;

import com.sammy.malum.core.systems.rite.*;
import com.sammy.malum.registry.common.magic.rite.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.neoforged.neoforge.registries.*;
import org.jetbrains.annotations.*;

import java.util.function.*;

@SuppressWarnings("unchecked")
public class DeferredRiteTypes extends DeferredRegister<SpiritRiteType> {

    protected DeferredRiteTypes(String namespace) {
        super(MalumSpiritRiteTypes.RITE_KEY, namespace);
    }

    public static DeferredRiteTypes create(String modid) {
        return new DeferredRiteTypes(modid);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <I extends SpiritRiteType> @NotNull RiteHolder<I> register(@NotNull String name, @NotNull Function<ResourceLocation, ? extends I> func) {
        return (RiteHolder<I>) super.register(name, func);
    }

    @Override
    public <I extends SpiritRiteType> @NotNull RiteHolder<I> register(@NotNull String name, @NotNull Supplier<? extends I> sup) {
        return this.register(name, key -> sup.get());
    }

    @Override
    protected <I extends SpiritRiteType> @NotNull RiteHolder<I> createHolder(@NotNull ResourceKey<? extends Registry<SpiritRiteType>> registryKey, @NotNull ResourceLocation key) {
        return new RiteHolder<>(ResourceKey.create(registryKey, key));
    }
}