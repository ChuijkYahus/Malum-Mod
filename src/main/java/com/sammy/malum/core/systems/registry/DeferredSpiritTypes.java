package com.sammy.malum.core.systems.registry;

import com.sammy.malum.core.systems.spirit.SpiritArcanaType;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.neoforged.neoforge.registries.*;
import org.jetbrains.annotations.*;

import java.util.function.*;

@SuppressWarnings("unchecked")
public class DeferredSpiritTypes extends DeferredRegister<SpiritArcanaType> {

    protected DeferredSpiritTypes(String namespace) {
        super(MalumSpiritTypes.SPIRIT_TYPES_KEY, namespace);
    }

    public static DeferredSpiritTypes create(String modid) {
        return new DeferredSpiritTypes(modid);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <I extends SpiritArcanaType> @NotNull SpiritHolder<I> register(@NotNull String name, @NotNull Function<ResourceLocation, ? extends I> func) {
        return (SpiritHolder<I>) super.register(name, func);
    }

    @Override
    public <I extends SpiritArcanaType> @NotNull SpiritHolder<I> register(@NotNull String name, @NotNull Supplier<? extends I> sup) {
        return this.register(name, key -> sup.get());
    }

    @Override
    protected <I extends SpiritArcanaType> @NotNull SpiritHolder<I> createHolder(@NotNull ResourceKey<? extends Registry<SpiritArcanaType>> registryKey, @NotNull ResourceLocation key) {
        return new SpiritHolder<>(ResourceKey.create(registryKey, key));
    }
}