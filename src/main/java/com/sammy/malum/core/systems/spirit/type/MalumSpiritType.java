package com.sammy.malum.core.systems.spirit.type;

import com.mojang.datafixers.util.*;
import com.mojang.serialization.*;
import com.sammy.malum.common.item.spirit.*;
import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.registry.common.*;
import io.netty.buffer.*;
import net.minecraft.nbt.*;
import net.minecraft.network.chat.*;
import net.minecraft.network.codec.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class MalumSpiritType implements SpiritWrapper {

    public static final Codec<SpiritHolder<MalumSpiritType>> HOLDER_CODEC = MalumSpiritTypes.SPIRIT_TYPES_REGISTRY.holderByNameCodec()
            .flatComapMap(c -> (SpiritHolder<MalumSpiritType>)c, DataResult::success);

    public static final Codec<MalumSpiritType> CODEC = MalumSpiritTypes.SPIRIT_TYPES_REGISTRY.byNameCodec();

    public static StreamCodec<ByteBuf, MalumSpiritType> STREAM_CODEC = ByteBufCodecs.fromCodec(MalumSpiritType.CODEC);

    private final SpiritColorProperties colorProperties;
    private final DeferredHolder<Item, SpiritShardItem> spiritShard;

    protected Rarity itemRarity;

    public MalumSpiritType(SpiritColorProperties colorProperties, DeferredHolder<Item, SpiritShardItem> spiritShard) {
        this.colorProperties = colorProperties;
        this.spiritShard = spiritShard;
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

    @Override
    public @NotNull MalumSpiritType unwrapSpirit() {
        return this;
    }

    public SpiritShardItem getSpiritShard() {
        return spiritShard.value();
    }

    public SpiritColorProperties getColorProperties() {
        return colorProperties;
    }

    public Rarity getItemRarity() {
        if (itemRarity == null) {
            TextColor textColor = getTextColor(false);
            itemRarity = Rarity.UNCOMMON;
//            itemRarity = Rarity.create("malum$" + identifier, (style) -> style.withColor(textColor));
        }
        return itemRarity;
    }
}