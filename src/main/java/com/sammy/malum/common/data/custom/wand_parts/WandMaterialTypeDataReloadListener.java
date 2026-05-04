package com.sammy.malum.common.data.custom.wand_parts;

import com.mojang.serialization.Codec;
import com.sammy.malum.common.data.custom.CodecBasedReloadListener;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.event.AddReloadListenerEvent;

public class WandMaterialTypeDataReloadListener extends CodecBasedReloadListener<ResourceLocation, WandMaterialType> {

    public static final WandMaterialTypeDataReloadListener DATA = new WandMaterialTypeDataReloadListener();

    public WandMaterialTypeDataReloadListener() {
        super("wand/material_types");
    }

    public static void register(AddReloadListenerEvent event) {
        event.addListener(DATA);
    }

    @Override
    public Codec<ResourceLocation> getKeyCodec() {
        return ResourceLocation.CODEC;
    }

    @Override
    public Codec<WandMaterialType> getCodec() {
        return WandMaterialType.DIRECT_CODEC;
    }

    @Override
    public ResourceLocation getID(WandMaterialType instance) {
        return instance.id();
    }
}
