package com.sammy.malum.compat.kubejs.component;

import com.google.gson.*;
import com.mojang.brigadier.*;
import com.mojang.brigadier.exceptions.*;
import com.mojang.serialization.*;
import com.sammy.malum.registry.common.*;
import dev.latvian.mods.kubejs.recipe.*;
import dev.latvian.mods.kubejs.recipe.component.*;
import dev.latvian.mods.kubejs.recipe.match.*;
import dev.latvian.mods.kubejs.util.*;
import dev.latvian.mods.rhino.*;
import dev.latvian.mods.rhino.type.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.sounds.*;

public record SoundEventHolderJSComponent(String name, Codec<Holder<SoundEvent>> codec) implements RecipeComponent<Holder<SoundEvent>> {

    //TODO: This should just be a built-in component in KubeJS, but using the existing one just parses a SoundEvent instead
    public static final RecipeComponent<Holder<SoundEvent>> SOUND_HOLDER = new SoundEventHolderJSComponent("malum:runic_workbench_sound", BuiltInRegistries.SOUND_EVENT.holderByNameCodec());
    public static final TypeInfo TYPE_INFO = TypeInfo.of(Holder.class).withParams(TypeInfo.of(SoundEvent.class));

    @Override
    public boolean matches(Context cx, KubeRecipe recipe, Holder<SoundEvent> value, ReplacementMatchInfo match) {
        return false;
    }

    @Override
    public TypeInfo typeInfo() {
        return TYPE_INFO;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public Holder<SoundEvent> wrap(Context cx, KubeRecipe recipe, Object from) {
        if (from instanceof JsonObject json) {
            return this.codec.decode(JsonOps.INSTANCE, json).result().orElseThrow().getFirst();
        }
        return fromObject(RegistryAccessContainer.of(cx), from);
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