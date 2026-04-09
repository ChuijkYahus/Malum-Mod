package com.sammy.malum.core.systems.rite.effect;

import com.google.common.collect.ImmutableList;
import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.magic.rite.*;
import net.minecraft.core.*;
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

    public abstract boolean triggerRiteEffect(ServerLevel level, BlockPos pos, SpiritArcanaType definingSpirit, RiteParameters parameters);

    public List<SpiritRiteEffectTag> getTags() {
        return tags;
    }

    public ResourceLocation getRegistryName() {
        return MalumSpiritRiteEffectTypes.EFFECT_TYPE_REGISTRY.getKey(this);
    }

    public int getCooldown() {
        return 100;
    }

    @Override
    public RegistryCodecBuddy<SpiritRiteEffect> getCodec() {
        return CODEC;
    }

    public static RiteParametersBuilder builder() {
        return new RiteParametersBuilder();
    }

    public static class RiteParametersBuilder {
        private int totemHeight = 0;
        private Direction totemDirection = Direction.NORTH;

        public RiteParametersBuilder setTotemHeight(int totemHeight) {
            this.totemHeight = totemHeight;
            return this;
        }

        public RiteParametersBuilder setTotemDirection(Direction totemDirection) {
            this.totemDirection = totemDirection;
            return this;
        }

        public RiteParameters build() {
            return new RiteParameters(totemHeight, totemDirection);
        }
    }

    public static class RiteParameters {
        private final int totemHeight;
        private final Direction totemDirection;

        public RiteParameters(int totemHeight, Direction totemDirection) {
            this.totemHeight = totemHeight;
            this.totemDirection = totemDirection;
        }

        public int getTotemHeight() {
            return totemHeight;
        }

        public Optional<Direction> getTotemDirection() {
            return Optional.ofNullable(totemDirection);
        }
    }
}