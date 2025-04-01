package com.sammy.malum.registry.common.worldgen;

import com.sammy.malum.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.levelgen.structure.*;

public class MalumStructureKeys {

    public static final ResourceKey<Structure> WEEPING_WELL_STRUCTURE_KEY = register(Registries.STRUCTURE, "weeping_well");
    public static final ResourceKey<StructureSet> WEEPING_WELL_STRUCTURE_SET_KEY = register(Registries.STRUCTURE_SET, "weeping_well");

    private static <T> ResourceKey<T> register(ResourceKey<Registry<T>> key, String id) {
        return ResourceKey.create(key, MalumMod.malumPath(id));
    }

}