package com.sammy.malum.registry.common.worldgen;

import com.sammy.malum.common.worldgen.ore.*;
import com.sammy.malum.common.worldgen.tree.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.placement.*;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.sammy.malum.MalumMod.*;

public class MalumFeatures {

    public static final DeferredRegister<Feature<?>> FEATURE_TYPES = DeferredRegister.create(BuiltInRegistries.FEATURE, MALUM);

    public static final DeferredHolder<Feature<?>, RunewoodTreeFeature> RUNEWOOD_TREE = FEATURE_TYPES.register("runewood_tree", RunewoodTreeFeature::new);
    public static final DeferredHolder<Feature<?>, SoulwoodTreeFeature> SOULWOOD_TREE = FEATURE_TYPES.register("soulwood_tree", SoulwoodTreeFeature::new);

    public static final DeferredHolder<Feature<?>, LayeredOreFeature> LAYERED_ORE = FEATURE_TYPES.register("layered_ore", LayeredOreFeature::new);
    public static final DeferredHolder<Feature<?>, DistributedOreFeature> DISTRIBUTED_ORE = FEATURE_TYPES.register("distributed_ore", DistributedOreFeature::new);

    public static class ConfiguredFeatures {

        public static final ResourceKey<ConfiguredFeature<?, ?>> SOULSTONE_ORE = registerKey("soulstone_ore");
        public static final ResourceKey<ConfiguredFeature<?, ?>> SOULSTONE_ORE_SURFACE = registerKey("soulstone_ore_surface");
        public static final ResourceKey<ConfiguredFeature<?, ?>> SOULSTONE_ORE_CAVES = registerKey("soulstone_ore_caves");
        public static final ResourceKey<ConfiguredFeature<?, ?>> SOULSTONE_ORE_DEEPSLATE_CAVES = registerKey("soulstone_ore_deepslate_caves");

        public static final ResourceKey<ConfiguredFeature<?, ?>> BRILLIANT_ORE = registerKey("brilliant_ore");
        public static final ResourceKey<ConfiguredFeature<?, ?>> CTHONIC_GOLD_ORE = registerKey("cthonic_gold_ore");
        public static final ResourceKey<ConfiguredFeature<?, ?>> NATURAL_QUARTZ_ORE = registerKey("natural_quartz_ore");
        public static final ResourceKey<ConfiguredFeature<?, ?>> BLAZING_QUARTZ_ORE = registerKey("blazing_quartz_ore");


        public static final ResourceKey<ConfiguredFeature<?, ?>> QUARTZ_GEODE = registerKey("quartz_geode");
        public static final ResourceKey<ConfiguredFeature<?, ?>> DEEPSLATE_QUARTZ_GEODE = registerKey("deepslate_quartz_geode");

        public static final ResourceKey<ConfiguredFeature<?, ?>> RUNEWOOD_TREE = registerKey("runewood_tree");
        public static final ResourceKey<ConfiguredFeature<?, ?>> AZURE_RUNEWOOD_TREE = registerKey("azure_runewood_tree");
        public static final ResourceKey<ConfiguredFeature<?, ?>> SOULWOOD_TREE = registerKey("soulwood_tree");

        public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
            return ResourceKey.create(Registries.CONFIGURED_FEATURE, malumPath(name));
        }
    }

    public static class PlacedFeatures {

        public static final ResourceKey<PlacedFeature> ORE_SOULSTONE_SURFACE = registerKey("ore_soulstone_surface");
        public static final ResourceKey<PlacedFeature> ORE_SOULSTONE_CAVES = registerKey("ore_soulstone_caves");
        public static final ResourceKey<PlacedFeature> ORE_SOULSTONE_DEEPSLATE_CAVES = registerKey("ore_soulstone_deepslate");

        public static final ResourceKey<PlacedFeature> ORE_BRILLIANT = registerKey("ore_brilliant");
        public static final ResourceKey<PlacedFeature> ORE_CTHONIC_GOLD = registerKey("cthonic_gold_ore");
        public static final ResourceKey<PlacedFeature> ORE_NATURAL_QUARTZ = registerKey("ore_natural_quartz");
        public static final ResourceKey<PlacedFeature> ORE_BLAZING_QUARTZ = registerKey("blazing_quartz_ore");

        public static final ResourceKey<PlacedFeature> RUNEWOOD_TREE = registerKey("runewood_tree");
        public static final ResourceKey<PlacedFeature> RARE_RUNEWOOD_TREE = registerKey("rare_runewood_tree");
        public static final ResourceKey<PlacedFeature> AZURE_RUNEWOOD_TREE = registerKey("azure_runewood_tree");
        public static final ResourceKey<PlacedFeature> RARE_AZURE_RUNEWOOD_TREE = registerKey("rare_azure_runewood_tree");

        public static final ResourceKey<PlacedFeature> QUARTZ_GEODE_FEATURE = registerKey("quartz_geode");
        public static final ResourceKey<PlacedFeature> DEEPSLATE_QUARTZ_GEODE_FEATURE = registerKey("deepslate_quartz_geode");

        public static ResourceKey<PlacedFeature> registerKey(String name) {
            return ResourceKey.create(Registries.PLACED_FEATURE, malumPath(name));
        }
    }
}