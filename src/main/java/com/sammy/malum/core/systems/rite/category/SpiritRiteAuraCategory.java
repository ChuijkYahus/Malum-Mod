package com.sammy.malum.core.systems.rite.category;

import com.sammy.malum.core.systems.rite.SpiritRiteType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class SpiritRiteAuraCategory extends SpiritRiteEffectCategory {

    public static final SpiritRiteEffectCategory AURA = new SpiritRiteAuraCategory();

    public SpiritRiteAuraCategory() {
        super("aura", 80, 8);
    }

    public MutableComponent getCoverage() {
        int coverage = getEffectRange();
        if (coverage > 1) {
            coverage = coverage * 2 + 1;
        }
        return Component.literal(coverage + "x" + coverage + "x" + coverage);
    }
}
