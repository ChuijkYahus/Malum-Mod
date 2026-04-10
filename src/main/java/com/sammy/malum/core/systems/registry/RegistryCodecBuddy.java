package com.sammy.malum.core.systems.registry;

import com.mojang.datafixers.util.*;
import com.mojang.serialization.*;
import io.netty.buffer.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.network.codec.*;

import java.util.*;
import java.util.function.*;

//TODO: Move to lodestone
public class RegistryCodecBuddy<T> {

    protected final Codec<Holder<T>> holderCodec;
    protected final Codec<T> codec;
    protected final StreamCodec<ByteBuf, T> streamCodec;

    protected final String defaultEntryName;

    public RegistryCodecBuddy(Registry<T> registry, String defaultEntryName) {
        this.holderCodec = registry.holderByNameCodec();
        this.codec = registry.byNameCodec();
        this.streamCodec = ByteBufCodecs.fromCodec(codec);
        this.defaultEntryName = defaultEntryName;
    }

    public Codec<Holder<T>> getHolderCodec() {
        return holderCodec;
    }

    public Codec<T> getCodec() {
        return codec;
    }

    public StreamCodec<ByteBuf, T> getStreamCodec() {
        return streamCodec;
    }

    public void save(T entry, CompoundTag tag) {
        save(entry, tag, defaultEntryName);
    }

    public void save(T entry, CompoundTag tag, String name) {
        tag.put(name, codec.encodeStart(NbtOps.INSTANCE, entry).getOrThrow());
    }

    public Optional<T> load(CompoundTag tag) {
        return load(tag, defaultEntryName);
    }

    public Optional<T> load(CompoundTag tag, String name) {
        return codec.decode(NbtOps.INSTANCE, tag.get(name)).map(Pair::getFirst).result();
    }

    public <K extends T> Optional<K> load(CompoundTag tag, Class<K> type) {
        return load(tag, type, defaultEntryName);
    }

    public <K extends T> Optional<K> load(CompoundTag tag, Class<K> type, String name) {
        return load(tag, t -> {
            if (type.isInstance(t)) {
                return type.cast(t);
            }
            return null;
        }, name);
    }


    public <K extends T> Optional<K> load(CompoundTag tag, Function<T, K> mapper) {
        return load(tag, mapper, defaultEntryName);
    }

    public <K extends T> Optional<K> load(CompoundTag tag, Function<T, K> mapper, String name) {
        return codec.decode(NbtOps.INSTANCE, tag.get(name)).map(Pair::getFirst).result().map(mapper);
    }

    @SuppressWarnings("unchecked")
    public interface RegistryCodecBuddyHelper<T> {
        RegistryCodecBuddy<T> getCodec();

        default void save(CompoundTag tag) {
            getCodec().save((T) this, tag);
        }

        default void save(CompoundTag tag, String name) {
            getCodec().save((T) this, tag, name);
        }
    }
}