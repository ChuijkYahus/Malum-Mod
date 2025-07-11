package com.sammy.malum.common.data.component;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sammy.malum.core.systems.spirit.type.*;
import io.netty.buffer.*;
import net.minecraft.network.codec.*;
import net.minecraft.world.item.*;
import org.jetbrains.annotations.*;

public record SpiritJarContentsComponent(SpiritArcanaType spirit, int count) implements SpiritLike {
    public static Codec<SpiritJarContentsComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            SpiritArcanaType.CODEC.fieldOf("spirit").forGetter(SpiritJarContentsComponent::spirit),
            Codec.INT.fieldOf("count").forGetter(SpiritJarContentsComponent::count)
    ).apply(instance, SpiritJarContentsComponent::new));

    public static StreamCodec<ByteBuf, SpiritJarContentsComponent> STREAM_CODEC = ByteBufCodecs.fromCodec(SpiritJarContentsComponent.CODEC);

    public SpiritJarContentsComponent(SpiritLike spirit, int count) {
        this(spirit.getSpirit(), count);
    }

    public ItemStack createStack() {
        return createStack(Math.min(count, 64));
    }

    public ItemStack createStack(int count) {
        return spirit.getSpiritStack(count);
    }

    public SpiritJarContentsComponent add(int added) {
        return new SpiritJarContentsComponent(spirit, count + added);
    }

    public SpiritJarContentsComponent remove(int removed) {
        int amount = Math.max(count - removed, 0);
        if (amount == 0) {
            return null;
        }
        return new SpiritJarContentsComponent(spirit, amount);
    }

    @Override
    public @NotNull SpiritArcanaType getSpirit() {
        return spirit;
    }
}
