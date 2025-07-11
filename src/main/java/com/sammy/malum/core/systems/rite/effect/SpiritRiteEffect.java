package com.sammy.malum.core.systems.rite.effect;

import com.sammy.malum.common.block.curiosities.totem.TotemBaseBlockEntity;
import com.sammy.malum.core.systems.rite.*;
import com.sammy.malum.core.systems.rite.category.SpiritRiteEffectCategory;
import net.minecraft.network.chat.*;
import net.minecraft.server.level.ServerLevel;

public abstract class SpiritRiteEffect {

    public final SpiritRiteEffectCategory category;

    protected SpiritRiteEffect(SpiritRiteEffectCategory category) {
        this.category = category;
    }

    protected void beginRite(ServerLevel level, TotemBaseBlockEntity totemBase) {
    }

    public SpiritRiteEffectCategory getCategory() {
        return category;
    }

    public int getEffectInterval() {
        return getCategory().getEffectInterval();
    }

    public int getEffectRange() {
        return getCategory().getEffectRange();
    }

    public MutableComponent getRiteCoverageDescriptor() {
        return category.getCoverage();
    }
}
