package com.sammy.malum.registry.common.worldgen;

import net.minecraft.resources.*;
import net.minecraft.world.level.block.grower.*;
import net.minecraft.world.level.levelgen.feature.*;

import java.util.*;

public class MalumTreeGrowers {

    public static final TreeGrower RUNEWOOD = register("malum:runewood", MalumFeatures.ConfiguredFeatures.RUNEWOOD_TREE);
    public static final TreeGrower AZURE_RUNEWOOD = register("malum:azure_runewood", MalumFeatures.ConfiguredFeatures.AZURE_RUNEWOOD_TREE);
    public static final TreeGrower SOULWOOD = register("malum:soulwood", MalumFeatures.ConfiguredFeatures.SOULWOOD_TREE);

    public static TreeGrower register(String id, ResourceKey<ConfiguredFeature<?, ?>> tree) {
        return new TreeGrower(id, Optional.empty(), Optional.of(tree), Optional.empty());
    }
}
