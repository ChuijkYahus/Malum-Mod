package com.sammy.malum.common.worldgen.geode.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.GeodeConfiguration;

public record MalumGeodeCrackSettings(double generateCrackChance, double baseCrackSize, int crackPointOffset) {
    public static final Codec<MalumGeodeCrackSettings> CODEC = RecordCodecBuilder.create(
            p_158334_ -> p_158334_.group(
                            GeodeConfiguration.CHANCE_RANGE.fieldOf("generate_crack_chance").orElse(1.0).forGetter(p_158340_ -> p_158340_.generateCrackChance),
                            Codec.doubleRange(0.0, 5.0).fieldOf("base_crack_size").orElse(2.0).forGetter(p_158338_ -> p_158338_.baseCrackSize),
                            Codec.intRange(0, 10).fieldOf("crack_point_offset").orElse(2).forGetter(p_158336_ -> p_158336_.crackPointOffset)
                    )
                    .apply(p_158334_, MalumGeodeCrackSettings::new)
    );
}