package com.sammy.malum.common.data.custom.rite;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.*;
import com.sammy.malum.MalumMod;
import com.sammy.malum.common.block.curiosities.totem.TotemBaseBlockEntity;
import com.sammy.malum.common.data.custom.malignant_conversion.*;
import com.sammy.malum.core.systems.registry.SpiritHolder;
import com.sammy.malum.core.systems.registry.rite.RiteEffectHolder;
import com.sammy.malum.core.systems.rite.*;
import com.sammy.malum.core.systems.rite.effect.SpiritRiteEffect;
import com.sammy.malum.core.systems.spirit.SpiritArcanaType;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;
import com.sammy.malum.registry.common.magic.rite.MalumSpiritRiteEffectTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.neoforged.neoforge.event.*;
import team.lodestar.lodestone.modules.toolkit.reload_listener.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpiritRiteTypeReloadListener extends CodecBasedReloadListener<ResourceLocation, SpiritRiteType> {

    public static final String DIRECTORY = "malum_data/spirit_rites";

    public static final SpiritRiteTypeReloadListener DATA = new SpiritRiteTypeReloadListener();

    public SpiritRiteTypeReloadListener() {
        super(DIRECTORY);
    }

    public static void register(AddReloadListenerEvent event) {
        event.addListener(DATA);
    }

    @Override
    public Codec<ResourceLocation> getKeyCodec() {
        return ResourceLocation.CODEC;
    }

    @Override
    public Codec<SpiritRiteType> getCodec() {
        return SpiritRiteType.DIRECT_CODEC;
    }

    @Override
    public ResourceLocation getID(SpiritRiteType instance) {
        return instance.getId();
    }

    public SpiritRiteType findMatching(ServerLevel level, TotemBaseBlockEntity totemBase) {
        for (SpiritRiteType rite : data.values()) {
            if (rite.matches(level, totemBase)) {
                return rite;
            }
        }

        return null;
    }
}