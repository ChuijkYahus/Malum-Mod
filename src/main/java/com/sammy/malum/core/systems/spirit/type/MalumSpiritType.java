package com.sammy.malum.core.systems.spirit.type;

import com.mojang.datafixers.util.*;
import com.mojang.serialization.*;
import com.sammy.malum.common.item.spirit.*;
import com.sammy.malum.registry.common.*;
import io.netty.buffer.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.network.chat.*;
import net.minecraft.network.codec.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class MalumSpiritType implements SpiritLike {

    public static final Codec<Holder<MalumSpiritType>> HOLDER_CODEC = MalumSpiritTypes.SPIRIT_TYPES_REGISTRY.holderByNameCodec();

    public static final Codec<MalumSpiritType> CODEC = MalumSpiritTypes.SPIRIT_TYPES_REGISTRY.byNameCodec();

    public static StreamCodec<ByteBuf, MalumSpiritType> STREAM_CODEC = ByteBufCodecs.fromCodec(MalumSpiritType.CODEC);

    private final SpiritColorProperties colorProperties;
    private final DeferredHolder<Item, SpiritShardItem> spiritShard;

    protected Rarity itemRarity;

    public MalumSpiritType(SpiritColorProperties colorProperties, DeferredHolder<Item, SpiritShardItem> spiritShard) {
        this.colorProperties = colorProperties;
        this.spiritShard = spiritShard;
    }

    @Override
    public @NotNull MalumSpiritType getSpirit() {
        return this;
    }

    public SpiritShardItem getSpiritShard() {
        return spiritShard.value();
    }

    public SpiritColorProperties getColorProperties() {
        return colorProperties;
    }

    public void save(CompoundTag tag) {
        save(tag, "spirit");
    }

    public void save(CompoundTag tag, String name) {
        MalumSpiritType.CODEC.encode(this, NbtOps.INSTANCE, new CompoundTag()).ifSuccess(c -> tag.put(name, c));
    }

    public static Optional<MalumSpiritType> load(CompoundTag tag) {
        return load(tag, "spirit");
    }

    public static Optional<MalumSpiritType> load(CompoundTag tag, String name) {
        return MalumSpiritType.CODEC.decode(NbtOps.INSTANCE, tag.getCompound(name)).map(Pair::getFirst).result();
    }

    public Rarity getItemRarity() {
        if (itemRarity == null) {
            TextColor textColor = getTextColor(false);
            itemRarity = Rarity.UNCOMMON;
//            itemRarity = Rarity.create("malum$" + identifier, (style) -> style.withColor(textColor));
        }
        return itemRarity;
    }

    public String asTag() {
        return getRegistryName().toString();
    }
}