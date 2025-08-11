package com.sammy.malum.core.systems.rite.effect;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.*;
import com.mojang.serialization.*;
import com.sammy.malum.common.block.curiosities.totem.TotemBaseBlockEntity;
import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.rite.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.magic.rite.*;
import io.netty.buffer.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.network.codec.*;
import net.minecraft.resources.*;
import net.minecraft.server.level.ServerLevel;

import java.util.*;

public abstract class SpiritRiteEffect implements RegistryCodecBuddy.RegistryCodecBuddyHelper<SpiritRiteEffect> {

    public static final RegistryCodecBuddy<SpiritRiteEffect> CODEC = new RegistryCodecBuddy<>(MalumSpiritRiteEffectTypes.EFFECT_TYPE_REGISTRY, "spirit_rite_effect");

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

    public ResourceLocation getRegistryName() {
        return MalumSpiritRiteEffectTypes.EFFECT_TYPE_REGISTRY.getKey(this);
    }

    @Override
    public RegistryCodecBuddy<SpiritRiteEffect> getCodec() {
        return CODEC;
    }
}