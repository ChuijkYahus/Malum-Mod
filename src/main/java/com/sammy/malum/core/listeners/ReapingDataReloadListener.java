package com.sammy.malum.core.listeners;

import com.google.gson.*;
import com.mojang.serialization.JsonOps;
import com.sammy.malum.MalumMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.event.AddReloadListenerEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReapingDataReloadListener extends SimpleJsonResourceReloadListener {
    public static Map<ResourceLocation, List<MalumReapingDropsData>> REAPING_DATA = new HashMap<>();
    private static final Gson GSON = (new GsonBuilder()).create();

    public ReapingDataReloadListener() {
        super(GSON, "reaping_data");
    }

    public static void register(AddReloadListenerEvent event) {
        event.addListener(new ReapingDataReloadListener());
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> objectIn, ResourceManager resourceManagerIn, ProfilerFiller profilerIn) {
        REAPING_DATA.clear();
        for (int i = 0; i < objectIn.size(); i++) {
            var location = (ResourceLocation) objectIn.keySet().toArray()[i];
            var object = objectIn.get(location).getAsJsonObject();
            var entityName = object.getAsJsonPrimitive("registry_name").getAsString();
            var entity = ResourceLocation.tryParse(entityName);
            if (entity != null && !BuiltInRegistries.ENTITY_TYPE.containsKey(entity)) {
                continue;
            }
            if (REAPING_DATA.containsKey(entity)) {
                MalumMod.LOGGER.info("Entity with registry name: {} already has reaping data associated with it. Overwriting.", entityName);
            }
            var drops = object.getAsJsonArray("drops");
            var list = new ArrayList<MalumReapingDropsData>();
            for (JsonElement drop : drops) {
                var dropObject = drop.getAsJsonObject();
                if (!dropObject.has("ingredient")) {
                    MalumMod.LOGGER.info("Entity with registry name: {} lacks a reaping ingredient. Skipping drops entry.", entityName);
                    continue;
                }
                var ingredient = Ingredient.CODEC.parse(JsonOps.INSTANCE, dropObject.get("ingredient")).getOrThrow(JsonParseException::new);
                float chance = dropObject.has("chance") ? dropObject.get("chance").getAsFloat() : 1f;
                int min = dropObject.has("min") ? dropObject.get("min").getAsInt() : 1;
                int max = dropObject.has("max") ? dropObject.get("max").getAsInt() : Math.max(min, 1);
                list.add(new MalumReapingDropsData(ingredient, chance, min, max));
            }
            REAPING_DATA.put(entity, list);
        }
    }

    public record MalumReapingDropsData(Ingredient drop, float chance, int min, int max) {

    }
}
