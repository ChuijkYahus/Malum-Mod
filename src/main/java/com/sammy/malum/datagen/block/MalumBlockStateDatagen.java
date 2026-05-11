package com.sammy.malum.datagen.block;

import com.sammy.malum.*;
import com.sammy.malum.datagen.MalumCrystalDatagen;
import com.sammy.malum.datagen.MalumMetallicsDatagen;
import com.sammy.malum.datagen.item.*;
import com.sammy.malum.registry.common.MalumContent;
import com.sammy.malum.registry.common.MalumContent.*;
import com.sammy.malum.registry.common.util.RockBlockSet;
import com.sammy.malum.registry.common.util.WoodBlockSet;
import com.sammy.malum.registry.common.util.data.*;
import net.minecraft.data.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.*;
import team.lodestar.lodestone.modules.datagen.BlockStateSmithTypes;
import team.lodestar.lodestone.modules.datagen.providers.block.LodestoneBlockStateSystem;
import team.lodestar.lodestone.modules.datagen.providers.item.LodestoneItemModelSystem;
import team.lodestar.lodestone.modules.datagen.smith.blockstate.BlockStateSystemData;

import javax.annotation.*;
import java.util.*;
import java.util.function.*;

import static com.sammy.malum.MalumMod.*;
import static com.sammy.malum.registry.common.MalumContent.Artifice.*;
import static com.sammy.malum.registry.common.MalumContent.CompactBlocks.*;
import static com.sammy.malum.registry.common.MalumContent.DungeonBlockSets.*;
import static com.sammy.malum.registry.common.MalumContent.Materials.*;
import static com.sammy.malum.registry.common.MalumContent.Sorcery.*;
import static com.sammy.malum.registry.common.MalumContent.WeepingWell.*;
import static team.lodestar.lodestone.modules.datagen.ItemModelSmithTypes.*;

public class MalumBlockStateDatagen extends LodestoneBlockStateSystem {

    public MalumBlockStateDatagen(PackOutput output, ExistingFileHelper exFileHelper, LodestoneItemModelSystem itemModelProvider) {
        super(output, MALUM, exFileHelper, itemModelProvider);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Malum BlockStates";
    }

    @Override
    protected void registerStatesAndModels() {
        Set<Supplier<? extends Block>> blocks = new HashSet<>(MalumContent.BLOCKS.getEntries());

        BlockStateSystemData data = new BlockStateSystemData(this, blocks::remove);


        setTexturePath("banners/");
        MalumBlockStateSmithTypes.SOULWOVEN_BANNER.act(data, BlockSets.SOULWOVEN_BANNER);
        setTexturePath("spirited_glass/");
        BlockStateSmithTypes.FULL_BLOCK.act(data,
                BlockSets.SACRED_SPIRITED_GLASS, BlockSets.WICKED_SPIRITED_GLASS, BlockSets.ARCANE_SPIRITED_GLASS, BlockSets.ELDRITCH_SPIRITED_GLASS,
                BlockSets.AERIAL_SPIRITED_GLASS, BlockSets.AQUEOUS_SPIRITED_GLASS, BlockSets.EARTHEN_SPIRITED_GLASS, BlockSets.INFERNAL_SPIRITED_GLASS,
                BlockSets.NULL_SPIRITED_GLASS);

        setTexturePath("terracotta/");
        BlockStateSmithTypes.GLAZED_TERRACOTTA_BLOCK.act(data,
                BlockSets.SACRED_VARNISHED_TERRACOTTA, BlockSets.WICKED_VARNISHED_TERRACOTTA, BlockSets.ARCANE_VARNISHED_TERRACOTTA, BlockSets.ELDRITCH_VARNISHED_TERRACOTTA,
                BlockSets.AERIAL_VARNISHED_TERRACOTTA, BlockSets.AQUEOUS_VARNISHED_TERRACOTTA, BlockSets.EARTHEN_VARNISHED_TERRACOTTA, BlockSets.INFERNAL_VARNISHED_TERRACOTTA,
                BlockSets.NULL_VARNISHED_TERRACOTTA);

        setTexturePath("sanctuary/trodden_stone/");
        generateVariedBlockBundle(data, BlockSets.TRODDEN_STONE);
        generateBlockBundle(data, BlockSets.TRODDEN_STONE_BRICKS);
        generateBlockBundle(data, BlockSets.POLISHED_TRODDEN_STONE);

        setTexturePath("arcane_rock/tainted/");
        generateRockSet(data, BlockSets.TAINTED_ROCK_SET);
        setTexturePath("arcane_rock/twisted/");
        generateRockSet(data, BlockSets.TWISTED_ROCK_SET);

        setTexturePath("arcane_rock/dross/");
        BlockStateSmithTypes.FULL_BLOCK.act(data,
                DROSS_STONE, POLISHED_DROSS_STONE, DROSS_STONE_BRICKS, DROSS_STONE_TILES, DROSS_STONE_MOSAIC, CHISELED_DROSS_STONE,
                GRAY_DROSS_TILES, DARK_DROSS_TILES);

        BlockStateSmithTypes.SLAB_BLOCK.act(data,
                DROSS_STONE_SLAB, POLISHED_DROSS_STONE_SLAB, DROSS_STONE_BRICKS_SLAB, DROSS_STONE_TILES_SLAB, DROSS_STONE_MOSAIC_SLAB,
                GRAY_DROSS_TILES_SLAB, DARK_DROSS_TILES_SLAB);

        BlockStateSmithTypes.STAIRS_BLOCK.act(data,
                DROSS_STONE_STAIRS, POLISHED_DROSS_STONE_STAIRS, DROSS_STONE_BRICKS_STAIRS, DROSS_STONE_TILES_STAIRS, DROSS_STONE_MOSAIC_STAIRS,
                GRAY_DROSS_TILES_STAIRS, DARK_DROSS_TILES_STAIRS);

        BlockStateSmithTypes.WALL_BLOCK.act(data,
                DROSS_STONE_WALL, POLISHED_DROSS_STONE_WALL, DROSS_STONE_BRICKS_WALL, DROSS_STONE_TILES_WALL, DROSS_STONE_MOSAIC_WALL,
                GRAY_DROSS_TILES_WALL, DARK_DROSS_TILES_WALL);

        MalumBlockStateSmithTypes.COLUMN.act(data, DROSS_STONE_COLUMN);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, models()::cubeBottomTop, DROSS_STONE_ALTAR);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, this::cutRockBlockModel, CUT_DROSS_STONE);

        BlockStateSmithTypes.BUTTON_BLOCK.act(data, DROSS_STONE_BUTTON);
        BlockStateSmithTypes.PRESSURE_PLATE_BLOCK.act(data, DROSS_STONE_PRESSURE_PLATE);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, this::rockItemPedestalModel, DROSS_STONE_ITEM_PEDESTAL);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::directionalBlock, this::itemStandModel, DROSS_STONE_ITEM_STAND);

        setTexturePath("runewood/");
        generateWoodSet(data, BlockSets.RUNEWOOD_SET);

        BlockStateSmithTypes.POTTED_PLANT.act(data, BlockSets.POTTED_RUNEWOOD_SAPLING, BlockSets.POTTED_AZURE_RUNEWOOD_SAPLING);
        BlockStateSmithTypes.CROSS_MODEL_BLOCK.act(data, BlockSets.RUNEWOOD_SAPLING, BlockSets.AZURE_RUNEWOOD_SAPLING);
        MalumBlockStateSmithTypes.TOTEM_POLE.act(data, Totemancy.RUNEWOOD_TOTEM_POLE);

        setTexturePath("runewood/leaves/");
        MalumBlockStateSmithTypes.STAGED_LEAVES.act(data, BlockSets.RUNEWOOD_LEAVES, BlockSets.AZURE_RUNEWOOD_LEAVES);
        MalumBlockStateSmithTypes.STAGED_HANGING_LEAVES.act(data, BlockSets.HANGING_RUNEWOOD_LEAVES, BlockSets.HANGING_AZURE_RUNEWOOD_LEAVES);

        setTexturePath("soulwood/");
        generateWoodSet(data, BlockSets.SOULWOOD_SET);

        BlockStateSmithTypes.POTTED_PLANT.act(data, BlockSets.POTTED_SOULWOOD_SAPLING);
        BlockStateSmithTypes.CROSS_MODEL_BLOCK.act(data, BlockSets.SOULWOOD_SAPLING);
        MalumBlockStateSmithTypes.TOTEM_POLE.act(data, Totemancy.SOULWOOD_TOTEM_POLE);

        setTexturePath("soulwood/leaves/");
        MalumBlockStateSmithTypes.STAGED_LEAVES.act(data, BlockSets.SOULWOOD_LEAVES);
        MalumBlockStateSmithTypes.STAGED_HANGING_LEAVES.act(data, BlockSets.HANGING_SOULWOOD_LEAVES);


        setTexturePath("ores/");
        BlockStateSmithTypes.FULL_BLOCK.act(data, BRILLIANT_STONE, BRILLIANT_DEEPSLATE, CTHONIC_GOLD_ORE, BLAZING_QUARTZ_ORE);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, GENERATED_ITEM, this::directionalBlock, fromFunction(models()::cross), CTHONIC_GOLD_FRAGMENT);

        setTexturePath("ores/soulstone/");
        BlockStateSmithTypes.FULL_BLOCK.act(data, SOULSTONE_ORE, DEEPSLATE_SOULSTONE_ORE);

        MalumBlockStateSmithTypes.SOULSTONE_BUD.act(data, NO_DATAGEN, ARCHAIC_SOULSTONE_BUD);
        MalumBlockStateSmithTypes.SOULSTONE_BUD.act(data, SOULSTONE_BUD);

        setTexturePath("geode/");
        MalumCrystalDatagen.MALUM.addBlockStates(data);

        setTexturePath("storage_blocks/");
        BlockStateSmithTypes.FULL_BLOCK.act(data,
                BLOCK_OF_SOUL_STAINED_STEEL, BLOCK_OF_HALLOWED_GOLD, BLOCK_OF_MALIGNANT_PEWTER,
                BLOCK_OF_NULL_SLATE, BLOCK_OF_VOID_SALTS, BLOCK_OF_MNEMONIC_FRAGMENT, BLOCK_OF_MALIGNANT_LEAD, BLOCK_OF_AURIC_EMBERS);

        MalumBlockStateSmithTypes.STORAGE_BLOCK.act(data, BLOCK_OF_REFINED_SOULSTONE, BLOCK_OF_RAW_SOULSTONE, BLOCK_OF_BRILLIANCE, BLOCK_OF_RAW_BRILLIANCE);
        MalumBlockStateSmithTypes.STORAGE_BLOCK.act(data, BLOCK_OF_BLAZING_QUARTZ, BLOCK_OF_CTHONIC_GOLD);

        MalumBlockStateSmithTypes.STORAGE_BLOCK.act(data, BLOCK_OF_ROTTING_ESSENCE, BLOCK_OF_GRIM_TALC, BLOCK_OF_EERIE_WEAVE, BLOCK_OF_WARP_FLUX);
        MalumBlockStateSmithTypes.STORAGE_BLOCK.act(data, BLOCK_OF_WIND_NUCLEI, BLOCK_OF_PYRE_NUCLEI);

        MalumBlockStateSmithTypes.STORAGE_BLOCK.act(data, BLOCK_OF_HEX_ASH, BLOCK_OF_LIVING_FLESH, BLOCK_OF_ALCHEMICAL_CALX, BLOCK_OF_ARCANE_CHARCOAL);

        MalumBlockStateSmithTypes.STORAGE_BLOCK.act(data, BLOCK_OF_EBONY);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, models()::cubeBottomTop, CRATE_OF_WITCHHAZEL);

        setTexturePath("storage_blocks/metallics");
        MalumMetallicsDatagen.MALUM.addBlockStates(data);

        setTexturePath("flora/");
        BlockStateSmithTypes.CROSS_MODEL_BLOCK.act(data, NO_DATAGEN, EBONY_SAPLING);
        MalumBlockStateSmithTypes.EBONY.act(data, EBONY_STALK);


        BlockStateSmithTypes.CROSS_MODEL_BLOCK.act(data, GENERATED_ITEM, WILD_WITCHHAZEL);
        BlockStateSmithTypes.CROSS_CROP_MODEL_BLOCK.act(data, GENERATED_ITEM, WITCHHAZEL);


        setTexturePath("blight/");
        MalumBlockStateSmithTypes.COLUMN.act(data, Blight.COLUMNAR_BLIGHT);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, models()::cubeBottomTop, Blight.BLIGHTED_EARTH);
        MalumBlockStateSmithTypes.COVERING_BLOCK.act(data, Blight.BLIGHT);
        MalumBlockStateSmithTypes.BLIGHTED_GROWTH.act(data, Blight.BLIGHTED_GUNK);
        MalumBlockStateSmithTypes.CREEPING_BLIGHT.act(data, Blight.CLINGING_BLIGHT);
        BlockStateSmithTypes.POTTED_PLANT.act(data, BlockSets.POTTED_BLIGHTPEARL, BlockSets.POTTED_BLIGHTROOT);
        BlockStateSmithTypes.CROSS_MODEL_BLOCK.act(data, Blight.BLIGHTPEARL, Blight.BLIGHTROOT);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, BLOCK_MODEL_ITEM, this::simpleBlock, this::blightedSoulwoodModel, MalumContent.BlockSets.BLIGHTED_SOULWOOD);

        setTexturePath("blight/scarstone/");
        MalumBlockStateSmithTypes.LARGE_STRANGE_CRYSTAL.act(data, Blight.LARGE_STRANGE_CRYSTAL);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, GENERATED_ITEM, this::simpleBlock, models()::crossModel, Blight.STRANGE_CRYSTAL);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, BLOCK_MODEL_ITEM, this::simpleBlock, models()::cubeBottomTop, Blight.SCARSTONE);
        BlockStateSmithTypes.CROSS_MODEL_BLOCK.act(data, Blight.STRANGEROOT);
        BlockStateSmithTypes.POTTED_PLANT.act(data, BlockSets.POTTED_STRANGEROOT);

        setTexturePath("dungeon/flesh/");
        MalumBlockStateSmithTypes.COLUMN.act(data, COLUMNAR_FLESH);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::directionalBlock, models()::cubeBottomTop, FLESHBULB);
        MalumBlockStateSmithTypes.WRITHING_FLESH.act(data, WRITHING_FLESH);

        setTexturePath("dungeon/odd_scriptures/");
        BlockStateSmithTypes.GLAZED_TERRACOTTA_BLOCK.act(data,
                ODD_SCRIPTURES_I, ODD_SCRIPTURES_II, ODD_SCRIPTURES_III, ODD_SCRIPTURES_IV, ODD_SCRIPTURES_V, ODD_SCRIPTURES_VI, ODD_SCRIPTURES_VII, ODD_SCRIPTURES_VIII, ODD_SCRIPTURES_IX);

        setTexturePath("dungeon/effigy/");
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, BLOCK_MODEL_ITEM, this::horizontalBlock, this::meditatingEffigy, VEILED_EFFIGY, CORRUPT_EFFIGY, CRACKED_EFFIGY);

        setTexturePath("waveform_artifice/");
        MalumBlockStateSmithTypes.SPIRIT_DIODE.act(data, WAVECHARGER, WAVEBANKER, WAVEMAKER, WAVEBREAKER);
        MalumBlockStateSmithTypes.GUST_TECH_BLOCK.act(data, GUST_IGNITER, WIND_TUNNEL);

        setTexturePath("ether/");
        itemModelProvider.setTexturePath("ether/");
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, GENERATED_ITEM,
                this::simpleBlock, this::etherModel, BlockSets.ETHER);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, MalumItemModelSmithTypes.GENERATED_OVERLAY_ITEM,
                this::simpleBlock, this::etherModel, BlockSets.IRIDESCENT_ETHER);

        MalumBlockStateSmithTypes.ETHER_CANDLE_BLOCK.act(data,
                BlockSets.ETHER_CANDLE, BlockSets.IRIDESCENT_ETHER_CANDLE);
        MalumBlockStateSmithTypes.ETHER_TORCH_BLOCK.act(data,
                BlockSets.ETHER_TORCH, BlockSets.IRIDESCENT_ETHER_TORCH);
        MalumBlockStateSmithTypes.ETHER_WALL_TORCH_BLOCK.act(data,
                BlockSets.WALL_ETHER_TORCH, BlockSets.IRIDESCENT_WALL_ETHER_TORCH);
        MalumBlockStateSmithTypes.ETHER_BRAZIER_BLOCK.act(data,
                BlockSets.ETHER_BRAZIER, BlockSets.IRIDESCENT_ETHER_BRAZIER);
        MalumBlockStateSmithTypes.ETHER_CRESSET_BLOCK.act(data,
                BlockSets.ETHER_CRESSET, BlockSets.IRIDESCENT_ETHER_CRESSET);
        itemModelProvider.setTexturePath("");
        setTexturePath("");

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, BLOCK_MODEL_ITEM, this::simpleBlock, models()::cubeBottomTop, Totemancy.RITE_ANCHOR, Totemancy.RITE_UNWEAVER);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, BLOCK_MODEL_ITEM, this::directionalBlock, models()::cubeBottomTop, Totemancy.RITE_SPREADER);
        MalumBlockStateSmithTypes.RITE_CHANNEL.act(data, Totemancy.RITE_CHANNEL);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, BLOCK_MODEL_ITEM, this::simpleBlock, models()::predefinedModel, SPIRIT_ALTAR, WAND_TINKERER, SOUL_BRAZIER, OMINOUS_ALTAR);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, BUILTIN_ENTITY_ITEM, this::simpleBlock, models()::predefinedModel, SPIRIT_JAR);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, BLOCK_MODEL_ITEM, this::horizontalBlock, models()::predefinedModel, WEAVERS_WORKBENCH, RUNIC_WORKBENCH);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, BLOCK_MODEL_ITEM, this::horizontalBlock, models()::orientableWithBottom, MAGEHAND_COFFER, CONJUNCTURE_CRYSTALLARIUM);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, NO_DATAGEN, this::simpleBlock, models()::predefinedModel,
                RUNEWOOD_OBELISK, RUNEWOOD_OBELISK_COMPONENT,
                BRILLIANT_OBELISK, BRILLIANT_OBELISK_COMPONENT,
                ARCANA_PYLON, ARCANA_PYLON_COMPONENT,
                Focusing.SPIRIT_CRUCIBLE, Focusing.SPIRIT_CRUCIBLE_COMPONENT, Focusing.REPAIR_PYLON,
                OMINOUS_OBELISK, OMINOUS_OBELISK_COMPONENT);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, BLOCK_MODEL_ITEM, this::simpleBlock, this::totemBaseModel,
                Totemancy.RUNEWOOD_TOTEM_BASE, Totemancy.SOULWOOD_TOTEM_BASE, Totemancy.WAVEFORM_RUNEWOOD_TOTEM_BASE, Totemancy.WAVEFORM_SOULWOOD_TOTEM_BASE);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, NO_DATAGEN, this::horizontalBlock, models()::predefinedModel,
                Focusing.SPIRIT_CATALYZER, Focusing.SPIRIT_CATALYZER_COMPONENT);

        MalumBlockStateSmithTypes.REPAIR_PYLON_COMPONENT.act(data, Focusing.REPAIR_PYLON_COMPONENT);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, BLOCK_MODEL_ITEM, this::simpleBlock, models()::predefinedModel,
                VOID_CONDUIT, VOID_DEPOT);

        MalumBlockStateSmithTypes.WEEPING_WELL_LAYERED_BLOCK.act(data, WEEPING_WELL_CENTER, WEEPING_WELL_SIDE, WEEPING_WELL_SIDE_MIRROR, WEEPING_WELL_CORNER);
        MalumBlockStateSmithTypes.WEEPING_WELL_BLOCK.act(data, WEEPING_WELL_FLAGSTONE);
        MalumBlockStateSmithTypes.WEEPING_WELL_DIRECTIONAL_BLOCK.act(data, WEEPING_WELL_COLUMN_BASE, WEEPING_WELL_COLUMN, WEEPING_WELL_COLUMN_CAP);

        MalumBlockStateSmithTypes.PRIMORDIAL_SOUP.act(data, PRIMORDIAL_SOUP);

        BlockStateSmithTypes.FULL_BLOCK.act(data, BlockSets.THE_DEVICE, BlockSets.THE_VESSEL);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, NO_DATAGEN, this::simpleBlock, this::cubeModelAirTexture, SPIRIT_MOTE);
    }

    public void generateVariedBlockBundle(BlockStateSystemData data, BlockBundle bundle) {
        VariedBlockStateSmithTypes.VARIED_FULL_BLOCK.act(data, bundle.block);
        VariedBlockStateSmithTypes.VARIED_STAIRS_BLOCK.act(data, bundle.stairs);
        VariedBlockStateSmithTypes.VARIED_SLAB_BLOCK.act(data, bundle.slab);
        if (bundle instanceof BlockBundleWithWall wall) {
            //TODO: Varied wall
//            BlockStateSmithTypes.WALL_BLOCK.act(data, wall.wall);
        }
    }

    public void generateBlockBundle(BlockStateSystemData data, BlockBundle bundle) {
        BlockStateSmithTypes.FULL_BLOCK.act(data, bundle.block);
        BlockStateSmithTypes.STAIRS_BLOCK.act(data, bundle.stairs);
        BlockStateSmithTypes.SLAB_BLOCK.act(data, bundle.slab);
        if (bundle instanceof BlockBundleWithWall wall) {
            BlockStateSmithTypes.WALL_BLOCK.act(data, wall.wall);
        }
    }

    public void generateVariedBlockBundle(BlockStateSystemData data, ItemlessBlockBundle bundle) {
        VariedBlockStateSmithTypes.VARIED_FULL_BLOCK.act(data, NO_DATAGEN, bundle.block);
        VariedBlockStateSmithTypes.VARIED_STAIRS_BLOCK.act(data, NO_DATAGEN, bundle.stairs);
        VariedBlockStateSmithTypes.VARIED_SLAB_BLOCK.act(data, NO_DATAGEN, bundle.slab);
        if (bundle instanceof ItemlessBlockBundleWithWall wall) {
            //TODO: Varied wall
//            BlockStateSmithTypes.WALL_BLOCK.act(data, NO_DATAGEN, wall.wall);
        }
    }

    public void generateBlockBundle(BlockStateSystemData data, ItemlessBlockBundle bundle) {
        BlockStateSmithTypes.FULL_BLOCK.act(data, NO_DATAGEN, bundle.block);
        BlockStateSmithTypes.STAIRS_BLOCK.act(data, NO_DATAGEN, bundle.stairs);
        BlockStateSmithTypes.SLAB_BLOCK.act(data, NO_DATAGEN, bundle.slab);
        if (bundle instanceof ItemlessBlockBundleWithWall wall) {
            BlockStateSmithTypes.WALL_BLOCK.act(data, NO_DATAGEN, wall.wall);
        }
    }

    public void generateRockSet(BlockStateSystemData data, RockBlockSet set) {
        for (BlockBundle bundle : new BlockBundle[] {
                set.rock, set.polishedRock, set.bricks,
                set.tiles//, set.grid, set.mosaic
        }) {
            generateBlockBundle(data, bundle);
        }

        MalumBlockStateSmithTypes.COLUMN.act(data, set.column);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, models()::cubeBottomTop, set.altar);

        BlockStateSmithTypes.BUTTON_BLOCK.act(data, set.button);
        BlockStateSmithTypes.PRESSURE_PLATE_BLOCK.act(data, set.pressurePlate);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, this::rockItemPedestalModel, set.itemPedestal);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::directionalBlock, this::itemStandModel, set.itemStand);
    }

    public void generateWoodSet(BlockStateSystemData data, WoodBlockSet set) {
        for (BlockBundle bundle : new BlockBundle[]{
                set.boards, set.verticalBoards, set.blocks,
                set.planks, set.verticalPlanks, set.tiles
        }) {
            generateBlockBundle(data, bundle);
        }
        for (ItemlessBlockBundle bundle : new ItemlessBlockBundle[]{
                set.carvedBoards, set.carvedVerticalBoards, set.carvedBlocks,
                set.carvedPlanks, set.carvedVerticalPlanks, set.carvedTiles
        }) {
            generateVariedBlockBundle(data, bundle);
        }

        BlockStateSmithTypes.LOG_BLOCK.act(data,
                set.log,
                set.strippedLog,
                set.sappyLog,
                set.strippedSappyLog
        );

        BlockStateSmithTypes.WOOD_BLOCK.act(data,
                set.wood,
                set.strippedWood
        );

        BlockStateSmithTypes.FENCE_BLOCK.act(data, set.fence);
        BlockStateSmithTypes.FENCE_GATE_BLOCK.act(data, set.fenceGate);
        BlockStateSmithTypes.WOODEN_SIGN_BLOCK.act(data, set.sign);
        BlockStateSmithTypes.BUTTON_BLOCK.act(data, set.button);
        BlockStateSmithTypes.PRESSURE_PLATE_BLOCK.act(data, set.pressurePlate);

//        MalumBlockStateSmithTypes.COLUMN.act(data, set.beam);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, models()::cubeBottomTop, set.steps);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, this::woodenItemPedestalModel, set.itemPedestal);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, this::decoratedItemPedestalModel, set.decoratedItemPedestal);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::directionalBlock, this::itemStandModel, set.itemStand);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::directionalBlock, this::decoratedItemStandModel, set.decoratedItemStand);
    }

    public ModelFile cubeModelAirTexture(Block block) {
        String name = getBlockName(block);
        return models().cubeAll(name, MalumMod.malumPath("block/air")).texture("particle", getBlockTexture(name));
    }

    public ModelFile cutRockBlockModel(Block block) {
        String name = getBlockName(block);
        int index = name.indexOf("_");
        String substring = name.substring(index + 1);
        ResourceLocation top = getBlockTexture(substring);
        ResourceLocation bottom = getBlockTexture(substring);
        ResourceLocation side = getBlockTexture(name);
        return models().cubeBottomTop(name, side, bottom, top);
    }

    public ModelFile rockItemPedestalModel(Block block) {
        return itemPedestalModel(block, "template_item_pedestal_rock");
    }

    public ModelFile woodenItemPedestalModel(Block block) {
        return itemPedestalModel(block, "template_item_pedestal_wooden");
    }

    public ModelFile decoratedItemPedestalModel(Block block) {
        return itemPedestalModel(block, "template_item_pedestal_wooden_decorated", s -> s.substring(s.indexOf("_") + 1) + "_" + s.split("_")[0]);
    }

    public ModelFile itemPedestalModel(Block block, String template) {
        return itemPedestalModel(block, template, s -> s);
    }

    public ModelFile itemPedestalModel(Block block, String template, Function<String, String> pathFunction) {
        String name = getBlockName(block);
        ResourceLocation parent = malumPath("block/templates/" + template);
        ResourceLocation pedestal = getBlockTexture(pathFunction.apply(name));
        return models().withExistingParent(name, parent).texture("pedestal", pedestal);
    }

    public ModelFile itemStandModel(Block block) {
        return itemStandModel(block, "template_item_stand", s -> s);
    }

    public ModelFile decoratedItemStandModel(Block block) {
        return itemStandModel(block, "template_item_stand_decorated", s -> s.substring(s.indexOf("_") + 1) + "_" + s.split("_")[0]);
    }

    public ModelFile itemStandModel(Block block, String template, Function<String, String> pathFunction) {
        String name = getBlockName(block);
        ResourceLocation parent = malumPath("block/templates/" + template);
        ResourceLocation stand = getBlockTexture(pathFunction.apply(name));
        return models().withExistingParent(name, parent).texture("stand", stand);
    }

    public ModelFile etherModel(Block block) {
        String name = getBlockName(block);
        return models().withExistingParent(name, ResourceLocation.withDefaultNamespace("block/air")).texture("particle", itemModelProvider.getItemTexture("ether"));
    }

    public ModelFile totemBaseModel(Block block) {
        String name = getBlockName(block);
        ResourceLocation texture = getBlockTexture(name);
        return models().withExistingParent(name, malumPath("block/templates/template_totem_base")).texture("totem_base", texture);
    }

    public ModelFile blightedSoulwoodModel(Block block) {
        String name = getBlockName(block);
        ResourceLocation side = getBlockTexture(name);
        ResourceLocation bottom = getBlockTexture("blighted_earth_bottom");
        ResourceLocation top = getAbsoluteBlockTexture("soulwood/soulwood_log_top");
        return models().cubeBottomTop(name, side, bottom, top);
    }

    public ModelFile meditatingEffigy(Block block) {
        String name = getBlockName(block);
        ResourceLocation effigy = getBlockTexture(name);
        return models().withExistingParent(name, malumPath("block/templates/dungeon/template_meditating_effigy")).texture("effigy", effigy);
    }
}
