package com.sammy.malum.registry.common.sound;

import com.sammy.malum.*;
import net.minecraft.resources.*;
import net.minecraft.sounds.*;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.registries.*;
import org.jetbrains.annotations.*;
import team.lodestar.lodestone.systems.sound.*;

import java.util.function.*;

public class MalumSoundType extends RegistryReadyBlockSoundType {

    public MalumSoundType(String name) {
        super(MalumSoundEvents::register, MalumMod::malumPath, name);
    }

    public MalumSoundType(String name, float volume, float pitch) {
        super(MalumSoundEvents::register, MalumMod::malumPath, name, volume, pitch);
    }
}