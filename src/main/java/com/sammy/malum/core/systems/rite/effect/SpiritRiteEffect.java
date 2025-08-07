package com.sammy.malum.core.systems.rite.effect;

import com.google.common.collect.ImmutableList;
import com.sammy.malum.common.block.curiosities.totem.TotemBaseBlockEntity;
import net.minecraft.server.level.ServerLevel;

import java.util.Arrays;
import java.util.List;

public abstract class SpiritRiteEffect {

    protected final List<SpiritRiteEffectTag> tags;

    protected SpiritRiteEffect(SpiritRiteEffectTag... tags) {
        this(ImmutableList.copyOf(Arrays.asList(tags)));
    }

    protected SpiritRiteEffect(List<SpiritRiteEffectTag> tags) {
        this.tags = ImmutableList.copyOf(tags);
    }

    public abstract void triggerRiteEffect(ServerLevel level, TotemBaseBlockEntity totemBase);

    public List<SpiritRiteEffectTag> getTags() {
        return tags;
    }
}