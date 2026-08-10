package com.sammy.malum.datagen.spiritRite;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class SpiritRiteBuilder {
    private final List<ResourceLocation> spirits;
    private final boolean corrupted;
    private final ResourceLocation effect;

    public SpiritRiteBuilder(List<ResourceLocation> spirits, boolean corrupted, ResourceLocation effect) {
        this.spirits = spirits;
        this.corrupted = corrupted;
        this.effect = effect;
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();

        JsonArray spiritArray = new JsonArray();
        for (ResourceLocation spirit : spirits) {
            spiritArray.add(spirit.toString());
        }

        json.add("spirits", spiritArray);
        json.addProperty("corrupted", corrupted);
        json.addProperty("effect", effect.toString());

        return json;
    }

    public static SpiritRiteBuilder of(
            List<ResourceLocation> spirits,
            boolean corrupted,
            ResourceLocation effect) {
        return new SpiritRiteBuilder(spirits, corrupted, effect);
    }
}