package com.sammy.malum.common.data_components;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sammy.malum.common.item.spirit.*;
import com.sammy.malum.core.systems.spirit.*;
import io.netty.buffer.*;
import net.minecraft.network.codec.*;
import net.minecraft.world.item.*;

public record SpiritJarContentsComponent(MalumSpiritType spirit, int count) {
    public static Codec<SpiritJarContentsComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            MalumSpiritType.CODEC.fieldOf("spirit").forGetter(SpiritJarContentsComponent::spirit),
            Codec.INT.fieldOf("count").forGetter(SpiritJarContentsComponent::count)
    ).apply(instance, SpiritJarContentsComponent::new));

    public static StreamCodec<ByteBuf, SpiritJarContentsComponent> STREAM_CODEC = ByteBufCodecs.fromCodec(SpiritJarContentsComponent.CODEC);

    public ItemStack createStack() {
        return createStack(Math.min(count, 64));
    }
    public ItemStack createStack(int count) {
        return new ItemStack(spirit.getSpiritShard(), count);
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
}
