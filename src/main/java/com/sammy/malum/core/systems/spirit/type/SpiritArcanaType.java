package com.sammy.malum.core.systems.spirit.type;

import com.mojang.datafixers.util.*;
import com.mojang.serialization.*;
import com.sammy.malum.common.item.spirit.*;
import com.sammy.malum.registry.common.magic.*;
import io.netty.buffer.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.network.chat.*;
import net.minecraft.network.codec.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class SpiritArcanaType implements SpiritLike {

    public static final Codec<Holder<SpiritArcanaType>> HOLDER_CODEC = MalumSpiritTypes.SPIRIT_TYPES_REGISTRY.holderByNameCodec();

    public static final Codec<SpiritArcanaType> CODEC = MalumSpiritTypes.SPIRIT_TYPES_REGISTRY.byNameCodec();

    public static StreamCodec<ByteBuf, SpiritArcanaType> STREAM_CODEC = ByteBufCodecs.fromCodec(SpiritArcanaType.CODEC);

    private final SpiritColorProperties colorProperties;
    private final DeferredHolder<Item, SpiritShardItem> spiritShard;

    protected Rarity itemRarity;
    protected ResourceLocation glowTexture;

    public SpiritArcanaType(SpiritColorProperties colorProperties, DeferredHolder<Item, SpiritShardItem> spiritShard) {
        this.colorProperties = colorProperties;
        this.spiritShard = spiritShard;
    }

    @Override
    public @NotNull SpiritArcanaType getSpirit() {
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

    public ResourceLocation getGlowTexture() {
        if (glowTexture == null) {
            glowTexture = getRegistryName()
                    .withPath(p -> "textures/vfx/totem_poles/" + p)
                    .withSuffix("_glow.png");
        }
        return glowTexture;
    }

    public final void save(CompoundTag tag) {
        save(tag, "spirit");
    }

    public final void save(CompoundTag tag, String name) {
        tag.put(name, CODEC.encodeStart(NbtOps.INSTANCE, this).getOrThrow());
    }

    public static Optional<SpiritArcanaType> load(CompoundTag tag) {
        return load(tag, "spirit");
    }

    public static Optional<SpiritArcanaType> load(CompoundTag tag, String name) {
        return CODEC.decode(NbtOps.INSTANCE, tag.get(name)).map(Pair::getFirst).result();
    }
}