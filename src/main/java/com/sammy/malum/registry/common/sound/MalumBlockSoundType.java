package com.sammy.malum.registry.common.sound;

import com.sammy.malum.*;
import team.lodestar.lodestone.core.sound.RegistryReadyBlockSoundType;
import team.lodestar.lodestone.systems.sound.*;

public class MalumBlockSoundType extends RegistryReadyBlockSoundType {

    public MalumBlockSoundType(String name) {
        super(MalumSoundEvents::register, MalumMod::malumPath, name);
    }

    public MalumBlockSoundType(String name, float volume, float pitch) {
        super(MalumSoundEvents::register, MalumMod::malumPath, name, volume, pitch);
    }
}