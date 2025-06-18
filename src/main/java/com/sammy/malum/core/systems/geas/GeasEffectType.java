package com.sammy.malum.core.systems.geas;

import com.mojang.serialization.*;
import com.sammy.malum.common.data.component.*;
import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;

import java.util.List;
import java.util.function.*;

public class GeasEffectType {

    public static final Codec<GeasEffectType> CODEC = ResourceLocation.CODEC.xmap(s -> {
        final GeasEffectType geasEffectType = MalumGeasEffectTypes.GEAS_TYPES_REGISTRY.get(s);
        if (geasEffectType == null) {
            return MalumGeasEffectTypes.CREED_OF_THE_BLIGHT_EATER.get();
        }
        return geasEffectType;
    }, GeasEffectType::getRegistryName);


    public final Supplier<GeasEffect> effect;
    public final List<SpiritHolder<MalumSpiritType>> spiritTypes;

    private GeasEffect dummyEffectInstance;
    private ItemStack dummyCreativeStack;

    @SafeVarargs
    public GeasEffectType(Supplier<GeasEffect> effect, SpiritHolder<MalumSpiritType>... spiritTypes) {
        this(effect, List.of(spiritTypes));
    }

    public GeasEffectType(Supplier<GeasEffect> effect, List<SpiritHolder<MalumSpiritType>> spiritTypes) {
        this.effect = effect;
        this.spiritTypes = spiritTypes;
    }

    public String getDetailedCons() {
        return getLangKey() + ".cons";
    }

    public String getDetailedPros() {
        return getLangKey() + ".pros";
    }

    public String getDescription() {
        return getLangKey() + ".tooltip";
    }

    public String getLangKey() {
        return getRegistryName().getNamespace() + ".gui.geas." + getRegistryName().getPath();
    }

    public ResourceLocation getRegistryName() {
        return MalumGeasEffectTypes.GEAS_TYPES_REGISTRY.getKey(this);
    }

    public ResourceLocation getIcon() {
        return getRegistryName().withPath(p -> "textures/item/geas/" + p).withSuffix(".png");
    }

    public Holder<GeasEffectType> getHolder() {
        return MalumGeasEffectTypes.GEAS_TYPES_REGISTRY.getHolder(getRegistryName()).orElseThrow();
    }

    public boolean is(TagKey<GeasEffectType> tag) {
        return getHolder().is(tag);
    }

    public ItemStack createStack(boolean isCreative) {
        ItemStack geas = new ItemStack(MalumItems.GEAS.get());
        geas.set(MalumDataComponents.GEAS_EFFECT, new GeasDataComponent(this, isCreative));
        return geas;
    }

    public ItemStack createDefaultStack() {
        return createStack(false);
    }

    public ItemStack createCreativeStack() {
        return createStack(true);
    }

    public GeasEffect createEffect() {
        return effect.get();
    }

    public GeasEffect getDefaultInstance() {
        if (dummyEffectInstance == null) {
            dummyEffectInstance = effect.get();
        }
        return dummyEffectInstance;
    }

    public ItemStack getDummyCreativeStack() {
        if (dummyCreativeStack == null) {
            dummyCreativeStack = createCreativeStack();
        }
        return dummyCreativeStack;
    }
}