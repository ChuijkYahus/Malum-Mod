package com.sammy.malum.common.worldgen.tree;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import net.minecraft.core.registries.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.levelgen.feature.configurations.*;

public record RunewoodTreeConfiguration(Block sapling, Block log, Block sapFilledLog, Block leaves,
                                        Block hangingLeaves) implements FeatureConfiguration {

    public static final Codec<RunewoodTreeConfiguration> CODEC =
            RecordCodecBuilder.create(inst -> inst.group(
                            BuiltInRegistries.BLOCK.byNameCodec().fieldOf("sapling").forGetter(obj -> obj.sapling),
                            BuiltInRegistries.BLOCK.byNameCodec().fieldOf("log").forGetter(obj -> obj.log),
                            BuiltInRegistries.BLOCK.byNameCodec().fieldOf("sapFilledLog").forGetter(obj -> obj.sapFilledLog),
                            BuiltInRegistries.BLOCK.byNameCodec().fieldOf("leaves").forGetter(obj -> obj.leaves),
                            BuiltInRegistries.BLOCK.byNameCodec().fieldOf("hangingLeaves").forGetter(obj -> obj.hangingLeaves))
                    .apply(inst, RunewoodTreeConfiguration::new));
}