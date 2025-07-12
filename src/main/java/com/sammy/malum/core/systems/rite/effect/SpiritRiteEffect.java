package com.sammy.malum.core.systems.rite.effect;

import com.google.common.collect.ImmutableList;
import com.sammy.malum.common.block.curiosities.totem.TotemBaseBlockEntity;
import net.minecraft.network.chat.*;
import net.minecraft.server.level.ServerLevel;
import oshi.annotation.concurrent.Immutable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class SpiritRiteEffect {

    protected final List<SpiritRiteEffectTag> tags;

    protected SpiritRiteEffect(SpiritRiteEffectTag... tags) {
        this(ImmutableList.copyOf(Arrays.asList(tags)));
    }
    protected SpiritRiteEffect(List<SpiritRiteEffectTag> tags) {
        this.tags = ImmutableList.copyOf(tags);
    }

    protected void beginRite(ServerLevel level, TotemBaseBlockEntity totemBase) {
    }

    public List<SpiritRiteEffectTag> getTags() {
        return tags;
    }
}
