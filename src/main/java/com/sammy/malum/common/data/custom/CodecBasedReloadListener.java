package com.sammy.malum.common.data.custom;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.sammy.malum.MalumMod;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.HashMap;
import java.util.Map;

public abstract class CodecBasedReloadListener<K, T> extends SimpleJsonResourceReloadListener {

    private static final Gson GSON = (new GsonBuilder()).create();

    public final Map<K, T> data = new HashMap<>();

    public CodecBasedReloadListener(String directory) {
        super(GSON, directory);
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> objects, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        data.clear();
        for (int i = 0; i < objects.size(); i++) {
            var location = (ResourceLocation) objects.keySet().toArray()[i];
            var object = objects.get(location).getAsJsonObject();

            try {
                var result = getCodec().parse(RegistryOps.create(JsonOps.INSTANCE, getRegistryLookup()), object).result();
                result.ifPresent(b -> data.put(getID(b), b));
            } catch (JsonParseException exception) {
                MalumMod.LOGGER.info("Could not parse Bonus Enchantment Source Data: ", exception);
            }
        }
    }

    public abstract Codec<T> getCodec();

    public abstract K getID(T instance);
}