package com.sammy.malum;

import com.sammy.malum.compability.create.CreateCompat;
import com.sammy.malum.compability.farmersdelight.FarmersDelightCompat;
import com.sammy.malum.compability.tetra.TetraCompat;
import com.sammy.malum.config.ClientConfig;
import com.sammy.malum.config.CommonConfig;
import com.sammy.malum.data.MalumLang;
import com.sammy.malum.data.MalumRecipes;
import com.sammy.malum.data.block.MalumBlockLootTables;
import com.sammy.malum.data.block.MalumBlockStates;
import com.sammy.malum.data.block.MalumBlockTags;
import com.sammy.malum.data.item.MalumItemModels;
import com.sammy.malum.data.item.MalumItemTags;
import com.sammy.malum.data.recipe.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import team.lodestar.lodestone.systems.config.ConfigGroup;

import static com.sammy.malum.registry.client.ParticleRegistry.PARTICLES;
import static com.sammy.malum.registry.common.AttributeRegistry.ATTRIBUTES;
import static com.sammy.malum.registry.common.ContainerRegistry.CONTAINERS;
import static com.sammy.malum.registry.common.MobEffectRegistry.EFFECTS;
import static com.sammy.malum.registry.common.SoundRegistry.SOUNDS;
import static com.sammy.malum.registry.common.block.BlockEntityRegistry.BLOCK_ENTITY_TYPES;
import static com.sammy.malum.registry.common.block.BlockRegistry.BLOCKS;
import static com.sammy.malum.registry.common.entity.EntityRegistry.ENTITY_TYPES;
import static com.sammy.malum.registry.common.item.EnchantmentRegistry.ENCHANTMENTS;
import static com.sammy.malum.registry.common.item.ItemRegistry.ITEMS;
import static com.sammy.malum.registry.common.recipe.RecipeSerializerRegistry.RECIPE_SERIALIZERS;
import static com.sammy.malum.registry.common.recipe.RecipeTypeRegistry.RECIPE_TYPES;
import static com.sammy.malum.registry.common.worldgen.ConfiguredFeatureRegistry.CONFIGURED_FEATURES;
import static com.sammy.malum.registry.common.worldgen.FeatureRegistry.FEATURE_TYPES;
import static com.sammy.malum.registry.common.worldgen.PlacedFeatureRegistry.PLACED_FEATURES;

@SuppressWarnings("unused")
@Mod(MalumMod.MALUM)
public class MalumMod {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MALUM = "malum";
    public static final ConfigGroup MALUM_CONFIG_GROUP = new ConfigGroup(MALUM, "common");
    public static final ConfigGroup MALUM_CONFIG_GROUP_CLIENT = new ConfigGroup(MALUM, "client");
    public static final RandomSource RANDOM = RandomSource.create();

    public MalumMod() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC);

        ENCHANTMENTS.register(modBus);
        BLOCKS.register(modBus);
        BLOCK_ENTITY_TYPES.register(modBus);
        ITEMS.register(modBus);
        ENTITY_TYPES.register(modBus);
        EFFECTS.register(modBus);
        PARTICLES.register(modBus);
        SOUNDS.register(modBus);
        CONTAINERS.register(modBus);
        ATTRIBUTES.register(modBus);
        RECIPE_TYPES.register(modBus);
        RECIPE_SERIALIZERS.register(modBus);
        FEATURE_TYPES.register(modBus);
        CONFIGURED_FEATURES.register(modBus);
        PLACED_FEATURES.register(modBus);

        TetraCompat.init();
        FarmersDelightCompat.init();
        CreateCompat.init();

        modBus.addListener(DataOnly::gatherData);
    }

    public static ResourceLocation malumPath(String path) {
        return new ResourceLocation(MALUM, path);
    }


    public static class DataOnly {
        public static void gatherData(GatherDataEvent event) {
            DataGenerator generator = event.getGenerator();
            BlockTagsProvider provider = new MalumBlockTags(generator, event.getExistingFileHelper());
            MalumItemModels itemProvider = new MalumItemModels(generator, event.getExistingFileHelper());
            MalumBlockStates blockStateProvider = new MalumBlockStates(generator, event.getExistingFileHelper(), itemProvider);
            generator.addProvider(event.includeServer(), blockStateProvider);
            generator.addProvider(event.includeServer(), itemProvider);
            generator.addProvider(event.includeClient(), new MalumLang(generator));
            generator.addProvider(event.includeServer(), provider);
            generator.addProvider(event.includeServer(), new MalumBlockLootTables(generator));
            generator.addProvider(event.includeServer(), new MalumItemTags(generator, provider, event.getExistingFileHelper()));
            generator.addProvider(event.includeServer(), new MalumRecipes(generator));
            generator.addProvider(event.includeServer(), new MalumVanillaRecipeReplacements(generator));
            generator.addProvider(event.includeServer(), new MalumSpiritInfusionRecipes(generator));
            generator.addProvider(event.includeServer(), new MalumSpiritFocusingRecipes(generator));
            generator.addProvider(event.includeServer(), new MalumSpiritTransmutationRecipes(generator));
            generator.addProvider(event.includeServer(), new MalumVoidFavorRecipes(generator));
        }
    }
}