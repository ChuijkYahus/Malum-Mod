package com.sammy.malum;

import com.sammy.malum.compability.attributelib.*;
import com.sammy.malum.compability.create.*;
import com.sammy.malum.compability.farmersdelight.*;
import com.sammy.malum.compability.irons_spellbooks.*;
import com.sammy.malum.compability.tetra.*;
import com.sammy.malum.config.*;
import net.minecraft.resources.*;
import net.minecraft.util.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.*;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.*;
import org.apache.logging.log4j.*;

import static com.sammy.malum.registry.client.ParticleRegistry.*;
import static com.sammy.malum.registry.common.AttachmentTypeRegistry.ATTACHMENT_TYPES;
import static com.sammy.malum.registry.common.AttributeRegistry.*;
import static com.sammy.malum.registry.common.ContainerRegistry.*;
import static com.sammy.malum.registry.common.MalumGeasEffectTypeRegistry.GEAS_TYPES;
import static com.sammy.malum.registry.common.MobEffectRegistry.*;
import static com.sammy.malum.registry.common.SoundRegistry.*;
import static com.sammy.malum.registry.common.WorldEventTypeRegistry.WORLD_EVENT_TYPES;
import static com.sammy.malum.registry.common.block.BlockEntityRegistry.*;
import static com.sammy.malum.registry.common.block.BlockRegistry.*;
import static com.sammy.malum.registry.common.entity.EntityRegistry.*;
import static com.sammy.malum.registry.common.item.DataComponentRegistry.*;
import static com.sammy.malum.registry.common.item.ItemRegistry.*;
import static com.sammy.malum.registry.common.item.tabs.CreativeTabRegistry.*;
import static com.sammy.malum.registry.common.recipe.RecipeSerializerRegistry.*;
import static com.sammy.malum.registry.common.recipe.RecipeTypeRegistry.*;
import static com.sammy.malum.registry.common.worldgen.FeatureRegistry.*;
import static com.sammy.malum.registry.common.worldgen.MalumStructurePieceTypes.STRUCTURE_PIECE_TYPES;
import static com.sammy.malum.registry.common.worldgen.MalumStructureTypes.STRUCTURE_TYPES;

@SuppressWarnings("unused")
@Mod(MalumMod.MALUM)
public class MalumMod {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MALUM = "malum";
    public static final RandomSource RANDOM = RandomSource.create();

    public MalumMod(IEventBus modEventBus, ModContainer modContainer) {

        ModLoadingContext.get().getActiveContainer().registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC);
        ModLoadingContext.get().getActiveContainer().registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC);

        NeoForgeMod.enableMergedAttributeTooltips();
        TetraCompat.init();
        FarmersDelightCompat.init();
        AttributeLibCompat.init();
        IronsSpellsCompat.init();
        CreateCompat.init();
        BLOCKS.register(modEventBus);
        BLOCK_ENTITY_TYPES.register(modEventBus);
        COMPONENTS.register(modEventBus);
        ITEMS.register(modEventBus);
        ENTITY_TYPES.register(modEventBus);
        EFFECTS.register(modEventBus);
        PARTICLES.register(modEventBus);
        SOUNDS.register(modEventBus);
        CONTAINERS.register(modEventBus);
        ATTRIBUTES.register(modEventBus);
        RECIPE_TYPES.register(modEventBus);
        RECIPE_SERIALIZERS.register(modEventBus);
        FEATURE_TYPES.register(modEventBus);
        STRUCTURE_TYPES.register(modEventBus);
        STRUCTURE_PIECE_TYPES.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        ATTACHMENT_TYPES.register(modEventBus);
        WORLD_EVENT_TYPES.register(modEventBus);
        GEAS_TYPES.register(modEventBus);
    }

    public static ResourceLocation malumPath(String path) {
        return ResourceLocation.fromNamespaceAndPath(MALUM, path);
    }
}