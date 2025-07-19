package com.sammy.malum;

import com.sammy.malum.compat.attributelib.*;
import com.sammy.malum.compat.create.*;
import com.sammy.malum.compat.farmersdelight.*;
import com.sammy.malum.compat.irons_spellbooks.*;
import com.sammy.malum.compat.tetra.*;
import com.sammy.malum.config.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.resources.*;
import net.minecraft.util.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.*;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.*;
import org.apache.logging.log4j.*;

import static com.sammy.malum.registry.common.MalumEntityDataSerializers.ENTITY_DATA_SERIALIZERS;
import static com.sammy.malum.registry.common.MalumParticles.*;
import static com.sammy.malum.registry.common.MalumAttachmentTypes.ATTACHMENT_TYPES;
import static com.sammy.malum.registry.common.MalumAttributes.*;
import static com.sammy.malum.registry.common.MalumContainers.*;
import static com.sammy.malum.registry.common.magic.MalumGeasEffectTypes.GEAS_TYPES;
import static com.sammy.malum.registry.common.MalumMobEffects.*;
import static com.sammy.malum.registry.common.MalumSoundEvents.*;
import static com.sammy.malum.registry.common.magic.MalumSpiritRiteTypes.SPIRIT_RITE_TYPES;
import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.SPIRIT_TYPES;
import static com.sammy.malum.registry.common.MalumWorldEventTypes.WORLD_EVENT_TYPES;
import static com.sammy.malum.registry.common.block.MalumBlockEntities.*;
import static com.sammy.malum.registry.common.block.MalumBlocks.*;
import static com.sammy.malum.registry.common.entity.MalumEntities.*;
import static com.sammy.malum.registry.common.item.MalumDataComponents.*;
import static com.sammy.malum.registry.common.item.MalumItems.*;
import static com.sammy.malum.registry.common.MalumCreativeTabs.*;
import static com.sammy.malum.registry.common.recipe.MalumRecipeSerializers.*;
import static com.sammy.malum.registry.common.recipe.MalumRecipeTypes.*;
import static com.sammy.malum.registry.common.worldgen.MalumFeatures.*;
import static com.sammy.malum.registry.common.worldgen.MalumStructures.StructurePieceTypes.STRUCTURE_PIECE_TYPES;
import static com.sammy.malum.registry.common.worldgen.MalumStructures.StructureTypes.STRUCTURE_TYPES;

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

        MalumRegistryAliases.registerAliases();

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
        ENTITY_DATA_SERIALIZERS.register(modEventBus);
        ATTACHMENT_TYPES.register(modEventBus);
        WORLD_EVENT_TYPES.register(modEventBus);
        SPIRIT_TYPES.register(modEventBus);
        SPIRIT_RITE_TYPES.register(modEventBus);
        GEAS_TYPES.register(modEventBus);

        MalumParticleEffectTypes.init();
    }

    public static ResourceLocation malumPath(String path) {
        return ResourceLocation.fromNamespaceAndPath(MALUM, path);
    }
}