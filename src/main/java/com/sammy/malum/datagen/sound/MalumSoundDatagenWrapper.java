package com.sammy.malum.datagen.sound;

import net.minecraft.resources.*;
import net.minecraft.sounds.*;
import net.neoforged.neoforge.common.data.*;
import team.lodestar.lodestone.modules.core.sound.RegistryReadyBlockSoundType;
import team.lodestar.lodestone.modules.datagen.providers.sound.BlockSoundEventBuilder;
import team.lodestar.lodestone.modules.datagen.providers.sound.LodestoneBlockSoundEventSystem.SoundEventBuilderBlueprint;

import java.util.function.*;

public abstract class MalumSoundDatagenWrapper {

    public final MalumSoundDatagen soundDatagen;

    public MalumSoundDatagenWrapper(MalumSoundDatagen soundDatagen) {
        this.soundDatagen = soundDatagen;
    }

    public abstract void registerSounds();

    @SafeVarargs
    public final SoundDefinition add(Supplier<SoundEvent> soundEvent, Consumer<SoundDefinition>... modifiers) {
        return soundDatagen.add(soundEvent, modifiers);
    }
    
    public void add(RegistryReadyBlockSoundType soundType, String path) {
        soundDatagen.add(soundType, path);
    }
    
    public void add(RegistryReadyBlockSoundType soundType, String path, Consumer<BlockSoundEventBuilder> modifier) {
        soundDatagen.add(soundType, path, modifier);
    }
    
    public SoundEventBuilderBlueprint blueprint(String path) {
        return soundDatagen.blueprint(path);
    }
    
    public SoundEventBuilderBlueprint blueprint(String path, Consumer<BlockSoundEventBuilder> modifier) {
        return soundDatagen.blueprint(path, modifier);
    }
    
    public SoundDefinition definition(SoundEvent soundEvent) {
        return soundDatagen.definition(soundEvent);
    }
    
    public void add(String soundEvent, SoundDefinition definition) {
        soundDatagen.add(soundEvent, definition);
    }
    
    public void add(Supplier<SoundEvent> soundEvent, SoundDefinition definition) {
        soundDatagen.add(soundEvent, definition);
    }
    
    public void add(SoundEvent soundEvent, SoundDefinition definition) {
        soundDatagen.add(soundEvent, definition);
    }
    
    public void add(ResourceLocation soundEvent, SoundDefinition definition) {
        soundDatagen.add(soundEvent, definition);
    }
    
    public SoundDefinition.Sound[] sounds(String name, int variants) {
        return soundDatagen.sounds(name, variants);
    }
    
    public SoundDefinition.Sound[] sounds(String name, int variants, Consumer<SoundDefinition.Sound> modifier) {
        return soundDatagen.sounds(name, variants, modifier);
    }

    public SoundDefinition.Sound[] allSounds(String path, Consumer<SoundDefinition.Sound> modifier) {
        return soundDatagen.allSounds(path, modifier);
    }

    public SoundDefinition.Sound[] allSounds(String path) {
        return soundDatagen.allSounds(path);
    }
    
    public SoundDefinition.Sound[] allSounds(String basePath, String name, Consumer<SoundDefinition.Sound> modifier, String... fallbacks) {
        return soundDatagen.allSounds(basePath, name, modifier, fallbacks);
    }

    public SoundDefinition.Sound[] allSounds(String basePath, String name, String... fallbacks) {
        return soundDatagen.allSounds(basePath, name, fallbacks);
    }
    
    public String subtitle(SoundEvent soundEvent) {
        return soundDatagen.subtitle(soundEvent);
    }
    
    public String subtitle(ResourceLocation id) {
        return soundDatagen.subtitle(id);
    }
}