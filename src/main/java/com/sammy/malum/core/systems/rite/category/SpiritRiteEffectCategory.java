package com.sammy.malum.core.systems.rite.category;

import com.sammy.malum.*;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.*;

public abstract class SpiritRiteEffectCategory {

    protected final ResourceLocation name;

    protected final int tickRate;
    protected final int range;

    public SpiritRiteEffectCategory(String name, int tickRate, int range) {
        this(MalumMod.malumPath(name), tickRate, range);
    }

    public SpiritRiteEffectCategory(ResourceLocation name, int tickRate, int range) {
        this.name = name;
        this.tickRate = tickRate;
        this.range = range;
    }

    public int getEffectInterval() {
        return tickRate;
    }

    public int getEffectRange() {
        return range;
    }

    public ResourceLocation getName() {
        return name;
    }

    public String getTranslationKey() {
        return name.getNamespace() + ".gui.rite.category." + name.getPath();
    }

    public abstract MutableComponent getCoverage();
}
