package com.sammy.malum.common.data.component.soulstone;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import net.minecraft.core.registries.*;
import net.minecraft.network.*;
import net.minecraft.network.chat.*;
import net.minecraft.network.codec.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import team.lodestar.lodestone.modules.toolkit.codec.LodestoneStreamCodecs;
import team.lodestar.lodestone.network.*;

import java.util.*;

public record StoredInSoulstoneMetal(String id, TagKey<Item> nuggetForm) {

    private static String path(String key) {
        return "malum.metal_data." + key;
    }
    public static final String METAL_COMPOSITION = path("composition");
    public static final String METAL_PURITY = path( "purity");
    public static final String METAL_ENTRY = path( "entry.");
    public static final String EMPTY = path("entry.empty");

    public static Codec<StoredInSoulstoneMetal> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("id").forGetter(g -> g.id),
            TagKey.codec(Registries.ITEM).fieldOf("nuggetForm").forGetter(StoredInSoulstoneMetal::nuggetForm)
    ).apply(instance, StoredInSoulstoneMetal::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, StoredInSoulstoneMetal> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.STRING_UTF8, StoredInSoulstoneMetal::id,
                    LodestoneStreamCodecs.tagStreamCodec(Registries.ITEM), StoredInSoulstoneMetal::nuggetForm,
                    StoredInSoulstoneMetal::new
            );

    public MutableComponent getComponent() {
        return Component.translatable(METAL_ENTRY + id);
    }
}