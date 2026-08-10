package com.sammy.malum.core.systems.rite;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sammy.malum.MalumMod;
import com.sammy.malum.common.block.curiosities.totem.TotemBaseBlockEntity;
import com.sammy.malum.core.systems.registry.SpiritHolder;
import com.sammy.malum.core.systems.registry.rite.RiteEffectHolder;
import com.sammy.malum.core.systems.rite.effect.SpiritRiteEffect;
import com.sammy.malum.core.systems.spirit.SpiritArcanaType;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;
import com.sammy.malum.registry.common.magic.rite.MalumSpiritRiteEffectTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpiritRiteRecipeManager
        extends SimpleJsonResourceReloadListener {

    private static final Gson GSON =
            new GsonBuilder().create();

    private final Map<ResourceLocation, SpiritRiteType> rites =
            new HashMap<>();

    public SpiritRiteRecipeManager() {
        super(
                GSON,
                "spirit_rites"
        );
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> jsons, ResourceManager resourceManager, ProfilerFiller profiler) {
        Map<ResourceLocation, SpiritRiteType> loadedRites =
                new HashMap<>();

        for (Map.Entry<ResourceLocation, JsonElement> entry : jsons.entrySet()) {
            ResourceLocation id = entry.getKey();

            try {
                JsonObject json =
                        entry.getValue().getAsJsonObject();

                SpiritRiteType rite =
                        loadRite(id, json);

                loadedRites.put(id, rite);

                MalumMod.LOGGER.info(
                        "Loaded spirit rite {}",
                        id
                );
            } catch (Exception exception) {
                MalumMod.LOGGER.error(
                        "Failed to load spirit rite {}",
                        id,
                        exception
                );
            }
        }

        rites.clear();
        rites.putAll(loadedRites);

        MalumMod.LOGGER.info(
                "Loaded {} spirit rites",
                rites.size()
        );
    }

    private SpiritRiteType loadRite(ResourceLocation id, JsonObject json) {
        JsonArray spiritArray =
                json.getAsJsonArray("spirits");

        List<SpiritHolder<SpiritArcanaType>> spirits =
                new ArrayList<>();

        for (JsonElement element : spiritArray) {
            ResourceLocation spiritId =
                    ResourceLocation.parse(
                            element.getAsString()
                    );

            SpiritHolder<SpiritArcanaType> spirit =
                    findSpirit(spiritId);

            if (spirit == null) {
                throw new IllegalStateException(
                        "Unknown spirit '" +
                                spiritId +
                                "' in rite '" +
                                id +
                                "'"
                );
            }

            spirits.add(spirit);
        }

        boolean corrupted = json.has("corrupted") && json.get("corrupted").getAsBoolean();

        ResourceLocation effectId =
                ResourceLocation.parse(
                        json.get("effect").getAsString()
                );

        RiteEffectHolder<? extends SpiritRiteEffect> effect =
                findEffect(effectId);

        if (effect == null) {
            throw new IllegalStateException(
                    "Unknown rite effect '" +
                            effectId +
                            "' in rite '" +
                            id +
                            "'"
            );
        }

        return new SpiritRiteType(
                id,
                effect,
                corrupted,
                spirits
        );
    }

    private SpiritHolder<SpiritArcanaType> findSpirit(ResourceLocation id) {
        for (var entry : MalumSpiritTypes.SPIRIT_TYPES.getEntries()) {
            var holder = SpiritHolder.getSpiritType(entry.getId());
            if (entry.getId().equals(id)) {
                return holder;
            }
        }

        return null;
    }

    private RiteEffectHolder<? extends SpiritRiteEffect> findEffect(ResourceLocation id) {
        for (var effect : MalumSpiritRiteEffectTypes.RITE_EFFECT_TYPES.getEntries()) {
            ResourceLocation effectId =
                    MalumSpiritRiteEffectTypes.EFFECT_TYPE_REGISTRY.getKey(
                            effect.get()
                    );

            if (id.equals(effectId)) {
                return (RiteEffectHolder<? extends SpiritRiteEffect>) effect;
            }
        }

        return null;
    }

    public SpiritRiteType get(ResourceLocation id) {
        return rites.get(id);
    }

    public List<SpiritRiteType> getRites() {
        return List.copyOf(rites.values());
    }

    public SpiritRiteType findMatching(ServerLevel level, TotemBaseBlockEntity totemBase) {
        for (SpiritRiteType rite : rites.values()) {
            if (rite.matches(level, totemBase)) {
                return rite;
            }
        }

        return null;
    }
}