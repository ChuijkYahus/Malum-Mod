package com.sammy.malum.common.data.custom.wand_parts;

import com.mojang.serialization.Codec;
import com.sammy.malum.common.data.custom.CodecBasedReloadListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.neoforged.neoforge.event.AddReloadListenerEvent;

public class WandPartTypeDataReloadListener extends CodecBasedReloadListener<ResourceLocation, WandPartType> {

    public static final WandPartTypeDataReloadListener DATA = new WandPartTypeDataReloadListener();

    public WandPartTypeDataReloadListener() {
        super("wand/part_types");
    }

    public static void register(AddReloadListenerEvent event) {
        event.addListener(DATA);
    }

    @Override
    public Codec<WandPartType> getCodec() {
        return WandPartType.DIRECT_CODEC;
    }

    @Override
    public ResourceLocation getID(WandPartType instance) {
        return instance.id();
    }
}
