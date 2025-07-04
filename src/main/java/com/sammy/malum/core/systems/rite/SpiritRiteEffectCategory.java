package com.sammy.malum.core.systems.rite;

import com.sammy.malum.*;
import net.minecraft.resources.*;

public class SpiritRiteEffectCategory {

    public static final SpiritRiteEffectCategory AURA = new SpiritRiteEffectCategory("aura", 80, 8);

    public static final SpiritRiteEffectCategory LOCUS = new SpiritRiteEffectCategory("locus", 160, 4);
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

    public int getTickRate() {
        return tickRate;
    }

    public int getRange() {
        return range;
    }

    public String getTranslationKey() {
        return name.getNamespace() + ".gui.rite.category." + name.getPath();
    }
}
