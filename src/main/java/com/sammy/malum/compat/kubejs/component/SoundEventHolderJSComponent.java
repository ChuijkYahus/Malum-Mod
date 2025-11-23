package com.sammy.malum.compat.kubejs.component;

import com.google.gson.*;
import com.mojang.brigadier.*;
import com.mojang.brigadier.exceptions.*;
import com.mojang.serialization.*;
import com.sammy.malum.*;
import com.sammy.malum.core.systems.recipe.*;
import com.sammy.malum.registry.common.*;
import dev.latvian.mods.kubejs.recipe.*;
import dev.latvian.mods.kubejs.recipe.component.*;
import dev.latvian.mods.kubejs.recipe.filter.*;
import dev.latvian.mods.kubejs.recipe.match.*;
import dev.latvian.mods.kubejs.util.*;
import dev.latvian.mods.rhino.*;
import dev.latvian.mods.rhino.type.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.sounds.*;

public record SoundEventHolderJSComponent(RecipeComponentType<?> type) implements RecipeComponent<Holder<SoundEvent>> {

    //TODO: This should just be a built-in component in KubeJS, but using the existing one just parses a SoundEvent instead not a Holder<SoundEvent>
    public static final RecipeComponentType<Holder<SoundEvent>> SOUND_HOLDER = RecipeComponentType.unit(
            MalumMod.malumPath("runic_workbench_sound"),
            SoundEventHolderJSComponent::new
    );

    @Override
    public RecipeComponentType<?> type() {
        return SOUND_HOLDER;
    }

    @Override
    public Codec<Holder<SoundEvent>> codec() {
        return SoundEvent.CODEC;
    }

    @Override
    public TypeInfo typeInfo() {
        return TypeInfo.of(SoundEvents.NOTE_BLOCK_XYLOPHONE.getClass());
    }

    @Override
    public String toString() {
        return type().id().getPath();
    }

    @Override
    public Holder<SoundEvent> wrap(RecipeScriptContext cx, Object from) {
        if (from instanceof JsonObject json) {
            return codec().decode(JsonOps.INSTANCE, json).result().orElseThrow().getFirst();
        }
        return fromObject(RegistryAccessContainer.of(cx.cx()), from);
    }

    public static Holder<SoundEvent> fromObject(RegistryAccessContainer registries, Object from) {
        if (from instanceof CharSequence) {
            try {
                return read(new StringReader(from.toString()));
            } catch (Exception exception) {
                throw new IllegalArgumentException("Failed to read Runic Workbench Sound from string: " + from, exception);
            }
        }

        throw new IllegalArgumentException("Can't create Runic Workbench Sound from object: " + from);
    }

    public static Holder<SoundEvent> read(StringReader reader) throws CommandSyntaxException {
        if (!reader.canRead()) {
            return MalumSoundEvents.RUNIC_WORKBENCH_SHAPES_RUNE_GENERIC;
        }
        reader.skipWhitespace();
        String string = reader.readString();
        ResourceKey<SoundEvent> key = ResourceKey.create(Registries.SOUND_EVENT, ResourceLocation.parse(string));
        return BuiltInRegistries.SOUND_EVENT.getHolder(key).orElseThrow();
    }
}