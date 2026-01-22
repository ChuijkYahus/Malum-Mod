package com.sammy.malum.common.worldgen.ore;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.*;

import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

public record LayeredOreConfiguration(List<OreLayer> oreLayers, Optional<LayeredOreFeatureDecorator> decorator) implements FeatureConfiguration {
    public static final Codec<LayeredOreConfiguration> CODEC = RecordCodecBuilder.create(obj -> obj.group(
                            OreLayer.CODEC.listOf().fieldOf("ore_layers").forGetter(LayeredOreConfiguration::oreLayers),
                            LayeredOreFeatureDecorator.CODEC.optionalFieldOf("decorator").forGetter(LayeredOreConfiguration::decorator)
                    )
                    .apply(obj, LayeredOreConfiguration::new)
    );

    public static LayeredTargetBlockState target(RuleTest target, BlockStateProvider state) {
        return new LayeredTargetBlockState(target, state);
    }

    public record OreLayer(List<LayeredTargetBlockState> targetStates, int size, float discardChanceOnAirExposure,
                           boolean discardFeatureIfEmpty) {
        public static final Codec<OreLayer> CODEC = RecordCodecBuilder.create(
                p_67849_ -> p_67849_.group(
                                Codec.list(LayeredTargetBlockState.CODEC).fieldOf("targets").forGetter(layer -> layer.targetStates),
                                Codec.intRange(0, 64).fieldOf("size").forGetter(layer -> layer.size),
                                Codec.floatRange(0.0F, 1.0F).fieldOf("discard_chance_on_air_exposure").forGetter(layer -> layer.discardChanceOnAirExposure),
                                Codec.BOOL.optionalFieldOf("discard_feature_if_empty", false).forGetter(layer -> layer.discardFeatureIfEmpty)
                        )
                        .apply(p_67849_, OreLayer::new)
        );
    }

    public record LayeredTargetBlockState(RuleTest target, BlockStateProvider state) {
        public static final Codec<LayeredTargetBlockState> CODEC = RecordCodecBuilder.create(
                obj -> obj.group(
                                RuleTest.CODEC.fieldOf("target").forGetter(target -> target.target),
                                BlockStateProvider.CODEC.fieldOf("state_provider").forGetter(target -> target.state)
                        )
                        .apply(obj, LayeredTargetBlockState::new)
        );
    }

    public record LayeredOreFeatureDecorator(List<LayeredTargetBlockState> decorators, int minDecorations, int maxDecorations) {
        public static final Codec<LayeredOreFeatureDecorator> CODEC = RecordCodecBuilder.create(
                obj -> obj.group(
                                Codec.list(LayeredTargetBlockState.CODEC).fieldOf("decorators").forGetter(layer -> layer.decorators),
                                Codec.INT.fieldOf("min_decorations").forGetter(layer -> layer.minDecorations),
                                Codec.INT.fieldOf("max_decorations").forGetter(layer -> layer.maxDecorations)
                        )
                        .apply(obj, LayeredOreFeatureDecorator::new)
        );

    }
}