package com.sammy.malum.common.worldgen.geode.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import team.lodestar.lodestone.modules.toolkit.codec.LodestoneCodecs;

import java.util.Optional;

import static com.sammy.malum.MalumMod.LOGGER;

public record MalumGeodeConfiguration(MalumGeodeLayerSettings geodeBlockSettings,
                                      MalumGeodeCrackSettings geodeCrackSettings,
                                      GeodeAnchorSettings geodeAnchorSettings,
                                      Optional<BlockStateProvider> fluid,
                                      int invalidBlocksThreshold) implements FeatureConfiguration {

    public static final Codec<MalumGeodeConfiguration> CODEC = RecordCodecBuilder.create(
            builder -> builder.group(
                            MalumGeodeLayerSettings.CODEC.fieldOf("block_settings").forGetter(MalumGeodeConfiguration::geodeBlockSettings),
                            MalumGeodeCrackSettings.CODEC.fieldOf("crack_settings").forGetter(MalumGeodeConfiguration::geodeCrackSettings),
                            GeodeAnchorSettings.CODEC.fieldOf("anchor_settings").forGetter(MalumGeodeConfiguration::geodeAnchorSettings),

                            LodestoneCodecs.optionalCodec(BlockStateProvider.CODEC).fieldOf("fluid").forGetter(MalumGeodeConfiguration::fluid),
                            Codec.INT.fieldOf("invalid_blocks_threshold").forGetter(MalumGeodeConfiguration::invalidBlocksThreshold)
                    )
                    .apply(builder, MalumGeodeConfiguration::new)
    );


    public static MalumGeodeConfiguration load(CompoundTag tag) {
        return MalumGeodeConfiguration.CODEC.parse(NbtOps.INSTANCE, tag.get("data")).resultOrPartial(LOGGER::error).orElse(null);
    }


}
