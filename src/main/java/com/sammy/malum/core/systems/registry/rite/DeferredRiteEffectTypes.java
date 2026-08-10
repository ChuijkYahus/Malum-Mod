package com.sammy.malum.core.systems.registry.rite;

import com.sammy.malum.core.systems.rite.effect.*;
import com.sammy.malum.registry.common.magic.rite.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.neoforged.neoforge.registries.*;
import org.jetbrains.annotations.*;

import java.util.function.*;

@SuppressWarnings("unchecked")
public class DeferredRiteEffectTypes extends DeferredRegister<SpiritRiteEffect> {

    protected DeferredRiteEffectTypes(String namespace) {
        super(MalumSpiritRiteEffectTypes.EFFECT_KEY, namespace);
    }

    public static DeferredRiteEffectTypes create(String modid) {
        return new DeferredRiteEffectTypes(modid);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <I extends SpiritRiteEffect> @NotNull RiteEffectHolder<I> register(@NotNull String name, @NotNull Function<ResourceLocation, ? extends I> func) {
        return (RiteEffectHolder<I>) super.register(name, func);
    }

    @Override
    public <I extends SpiritRiteEffect> @NotNull RiteEffectHolder<I> register(@NotNull String name, @NotNull Supplier<? extends I> sup) {
        return this.register(name, key -> sup.get());
    }

    @Override
    protected <I extends SpiritRiteEffect> @NotNull RiteEffectHolder<I> createHolder(@NotNull ResourceKey<? extends Registry<SpiritRiteEffect>> registryKey, @NotNull ResourceLocation key) {
        return new RiteEffectHolder<>(ResourceKey.create(registryKey, key));
    }
}