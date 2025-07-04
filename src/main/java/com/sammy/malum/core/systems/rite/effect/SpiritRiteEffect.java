package com.sammy.malum.core.systems.rite.effect;

import com.sammy.malum.core.systems.rite.*;
import net.minecraft.network.chat.*;

public abstract class SpiritRiteEffect {

    public final SpiritRiteEffectCategory category;

    protected SpiritRiteEffect(SpiritRiteEffectCategory category) {
        this.category = category;
    }

    public SpiritRiteEffectCategory getCategory() {
        return category;
    }

    public MutableComponent getRiteCoverageDescriptor() {
        if (category.equals(SpiritRiteEffectCategory.LOCUS)) {
            return Component.translatable(SpiritRiteType.ANCHOR);
        }
        int coverage = category.getRange();
        if (coverage > 1) {
            coverage = coverage * 2 + 1;
        }
        return Component.literal(coverage + "x" + coverage + "x" + coverage);
    }
}
