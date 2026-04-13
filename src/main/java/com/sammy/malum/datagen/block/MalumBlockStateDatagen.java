package com.sammy.malum.datagen.block;

import com.sammy.malum.*;
import com.sammy.malum.datagen.MalumMetallicsDatagen;
import com.sammy.malum.datagen.item.*;
import com.sammy.malum.registry.common.MalumContent;
import com.sammy.malum.registry.common.MalumContent.*;
import com.sammy.malum.registry.common.util.RockBlockSet;
import com.sammy.malum.registry.common.util.WoodBlockSet;
import com.sammy.malum.registry.common.util.data.BlockBundle;
import com.sammy.malum.registry.common.util.data.BlockBundleWithWall;
import com.sammy.malum.registry.common.util.data.ItemlessBlockBundle;
import net.minecraft.data.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.*;
import team.lodestar.lodestone.modules.datagen.BlockStateSmithTypes;
import team.lodestar.lodestone.modules.datagen.ItemModelSmithTypes;
import team.lodestar.lodestone.modules.datagen.providers.block.LodestoneBlockStateSystem;
import team.lodestar.lodestone.modules.datagen.providers.item.LodestoneItemModelSystem;
import team.lodestar.lodestone.modules.datagen.smith.blockstate.BlockStateSystemData;

import javax.annotation.*;
import java.util.*;
import java.util.function.*;

import static com.sammy.malum.MalumMod.*;
import static com.sammy.malum.registry.common.MalumContent.Artifice.*;
import static com.sammy.malum.registry.common.MalumContent.CompactBlocks.*;
import static com.sammy.malum.registry.common.MalumContent.Materials.*;
import static com.sammy.malum.registry.common.MalumContent.Totemancy.*;

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

        setTexturePath("arcane_rock/tainted/");
        generateRockSet(data, BlockSets.TAINTED_ROCK_SET);
        setTexturePath("arcane_rock/twisted/");
        generateRockSet(data, BlockSets.TWISTED_ROCK_SET);

        setTexturePath("arcane_rock/dross/");
        BlockStateSmithTypes.FULL_BLOCK.act(data,
                DungeonBlockSets.DROSS_STONE, DungeonBlockSets.POLISHED_DROSS_STONE, DungeonBlockSets.DROSS_STONE_BRICKS, DungeonBlockSets.DROSS_STONE_TILES, DungeonBlockSets.DROSS_STONE_MOSAIC, DungeonBlockSets.CHISELED_DROSS_STONE,
                DungeonBlockSets.GRAY_DROSS_TILES, DungeonBlockSets.DARK_DROSS_TILES);

        BlockStateSmithTypes.SLAB_BLOCK.act(data,
                DungeonBlockSets.DROSS_STONE_SLAB, DungeonBlockSets.POLISHED_DROSS_STONE_SLAB, DungeonBlockSets.DROSS_STONE_BRICKS_SLAB, DungeonBlockSets.DROSS_STONE_TILES_SLAB, DungeonBlockSets.DROSS_STONE_MOSAIC_SLAB,
                DungeonBlockSets.GRAY_DROSS_TILES_SLAB, DungeonBlockSets.DARK_DROSS_TILES_SLAB);

        BlockStateSmithTypes.STAIRS_BLOCK.act(data,
                DungeonBlockSets.DROSS_STONE_STAIRS, DungeonBlockSets.POLISHED_DROSS_STONE_STAIRS, DungeonBlockSets.DROSS_STONE_BRICKS_STAIRS, DungeonBlockSets.DROSS_STONE_TILES_STAIRS, DungeonBlockSets.DROSS_STONE_MOSAIC_STAIRS,
                DungeonBlockSets.GRAY_DROSS_TILES_STAIRS, DungeonBlockSets.DARK_DROSS_TILES_STAIRS);

        BlockStateSmithTypes.WALL_BLOCK.act(data,
                DungeonBlockSets.DROSS_STONE_WALL, DungeonBlockSets.POLISHED_DROSS_STONE_WALL, DungeonBlockSets.DROSS_STONE_BRICKS_WALL, DungeonBlockSets.DROSS_STONE_TILES_WALL, DungeonBlockSets.DROSS_STONE_MOSAIC_WALL,
                DungeonBlockSets.GRAY_DROSS_TILES_WALL, DungeonBlockSets.DARK_DROSS_TILES_WALL);

        MalumBlockStateSmithTypes.COLUMN.act(data, DungeonBlockSets.DROSS_STONE_COLUMN);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, models()::cubeBottomTop, DungeonBlockSets.DROSS_STONE_ALTAR);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, this::cutRockBlockModel, DungeonBlockSets.CUT_DROSS_STONE);

        BlockStateSmithTypes.BUTTON_BLOCK.act(data, DungeonBlockSets.DROSS_STONE_BUTTON);
        BlockStateSmithTypes.PRESSURE_PLATE_BLOCK.act(data, DungeonBlockSets.DROSS_STONE_PRESSURE_PLATE);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, this::rockItemPedestalModel, DungeonBlockSets.DROSS_STONE_ITEM_PEDESTAL);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::directionalBlock, this::itemStandModel, DungeonBlockSets.DROSS_STONE_ITEM_STAND);

        setTexturePath("runewood/");
        generateWoodSet(data, BlockSets.RUNEWOOD_SET);

        BlockStateSmithTypes.POTTED_PLANT.act(data, BlockSets.POTTED_RUNEWOOD_SAPLING, BlockSets.POTTED_AZURE_RUNEWOOD_SAPLING);
        BlockStateSmithTypes.CROSS_MODEL_BLOCK.act(data, BlockSets.RUNEWOOD_SAPLING, BlockSets.AZURE_RUNEWOOD_SAPLING);
        MalumBlockStateSmithTypes.TOTEM_POLE.act(data, RUNEWOOD_TOTEM_POLE);

        setTexturePath("runewood/leaves/");
        MalumBlockStateSmithTypes.STAGED_LEAVES.act(data, BlockSets.RUNEWOOD_LEAVES, BlockSets.AZURE_RUNEWOOD_LEAVES);
        MalumBlockStateSmithTypes.STAGED_HANGING_LEAVES.act(data, BlockSets.HANGING_RUNEWOOD_LEAVES, BlockSets.HANGING_AZURE_RUNEWOOD_LEAVES);

        setTexturePath("soulwood/");
        generateWoodSet(data, BlockSets.SOULWOOD_SET);

        BlockStateSmithTypes.POTTED_PLANT.act(data, BlockSets.POTTED_SOULWOOD_SAPLING);
        BlockStateSmithTypes.CROSS_MODEL_BLOCK.act(data, BlockSets.SOULWOOD_SAPLING);
        MalumBlockStateSmithTypes.TOTEM_POLE.act(data, SOULWOOD_TOTEM_POLE);

        setTexturePath("soulwood/leaves/");
        MalumBlockStateSmithTypes.STAGED_LEAVES.act(data, BlockSets.SOULWOOD_LEAVES);
        MalumBlockStateSmithTypes.STAGED_HANGING_LEAVES.act(data, BlockSets.HANGING_SOULWOOD_LEAVES);


        setTexturePath("ores/");
        BlockStateSmithTypes.FULL_BLOCK.act(data, BRILLIANT_STONE, BRILLIANT_DEEPSLATE, NATURAL_QUARTZ_ORE, DEEPSLATE_QUARTZ_ORE, CTHONIC_GOLD_ORE, BLAZING_QUARTZ_ORE);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.GENERATED_ITEM, this::directionalBlock, fromFunction(models()::cross), NATURAL_QUARTZ, CTHONIC_GOLD_FRAGMENT);

        setTexturePath("ores/soulstone/");
        BlockStateSmithTypes.FULL_BLOCK.act(data, SOULSTONE_ORE, DEEPSLATE_SOULSTONE_ORE);

        setTexturePath("storage_blocks/");
        BlockStateSmithTypes.FULL_BLOCK.act(data,
                BLOCK_OF_SOUL_STAINED_STEEL, BLOCK_OF_HALLOWED_GOLD, BLOCK_OF_MALIGNANT_PEWTER,
                BLOCK_OF_NULL_SLATE, BLOCK_OF_VOID_SALTS, BLOCK_OF_MNEMONIC_FRAGMENT, BLOCK_OF_MALIGNANT_LEAD, BLOCK_OF_AURIC_EMBERS);

        MalumBlockStateSmithTypes.STORAGE_BLOCK.act(data, BLOCK_OF_SOULSTONE, BLOCK_OF_RAW_SOULSTONE, BLOCK_OF_BRILLIANCE, BLOCK_OF_RAW_BRILLIANCE);
        MalumBlockStateSmithTypes.STORAGE_BLOCK.act(data, BLOCK_OF_BLAZING_QUARTZ, BLOCK_OF_NATURAL_QUARTZ, BLOCK_OF_CTHONIC_GOLD);

        MalumBlockStateSmithTypes.STORAGE_BLOCK.act(data, BLOCK_OF_ROTTING_ESSENCE, BLOCK_OF_GRIM_TALC, BLOCK_OF_EERIE_WEAVE, BLOCK_OF_WARP_FLUX);
        MalumBlockStateSmithTypes.STORAGE_BLOCK.act(data, BLOCK_OF_WIND_NUCLEI, BLOCK_OF_PYRE_NUCLEI);

        MalumBlockStateSmithTypes.STORAGE_BLOCK.act(data, BLOCK_OF_HEX_ASH, BLOCK_OF_LIVING_FLESH, BLOCK_OF_ALCHEMICAL_CALX, BLOCK_OF_ARCANE_CHARCOAL);

        MalumBlockStateSmithTypes.STORAGE_BLOCK.act(data, BLOCK_OF_EBONY);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, models()::cubeBottomTop, CRATE_OF_WITCHHAZEL);

        setTexturePath("storage_blocks/metallics");
        MalumMetallicsDatagen.MALUM.addBlockStates(data);

        setTexturePath("flora/");
        BlockStateSmithTypes.CROSS_MODEL_BLOCK.act(data, ItemModelSmithTypes.NO_DATAGEN, EBONY_SAPLING);
        MalumBlockStateSmithTypes.EBONY.act(data, EBONY_STALK);


        BlockStateSmithTypes.CROSS_MODEL_BLOCK.act(data, ItemModelSmithTypes.GENERATED_ITEM, WILD_WITCHHAZEL);
        BlockStateSmithTypes.CROSS_CROP_MODEL_BLOCK.act(data, ItemModelSmithTypes.GENERATED_ITEM, WITCHHAZEL);


        setTexturePath("blight/");
        MalumBlockStateSmithTypes.COLUMN.act(data, Blight.COLUMNAR_BLIGHT);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, models()::cubeBottomTop, Blight.BLIGHTED_EARTH);
        MalumBlockStateSmithTypes.COVERING_BLOCK.act(data, Blight.BLIGHT);
        MalumBlockStateSmithTypes.BLIGHTED_GROWTH.act(data, Blight.BLIGHTED_GUNK);
        MalumBlockStateSmithTypes.CREEPING_BLIGHT.act(data, Blight.CLINGING_BLIGHT);
        BlockStateSmithTypes.POTTED_PLANT.act(data, BlockSets.POTTED_BLIGHTPEARL, BlockSets.POTTED_BLIGHTROOT);
        BlockStateSmithTypes.CROSS_MODEL_BLOCK.act(data, Blight.BLIGHTPEARL, Blight.BLIGHTROOT);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.BLOCK_MODEL_ITEM, this::simpleBlock, this::blightedSoulwoodModel, MalumContent.BlockSets.BLIGHTED_SOULWOOD);

        setTexturePath("blight/scarstone/");
        MalumBlockStateSmithTypes.LARGE_STRANGE_CRYSTAL.act(data, Blight.LARGE_STRANGE_CRYSTAL);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.GENERATED_ITEM, this::simpleBlock, models()::crossModel, Blight.STRANGE_CRYSTAL);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.BLOCK_MODEL_ITEM, this::simpleBlock, models()::cubeBottomTop, Blight.SCARSTONE);
        BlockStateSmithTypes.CROSS_MODEL_BLOCK.act(data, Blight.STRANGEROOT);
        BlockStateSmithTypes.POTTED_PLANT.act(data, BlockSets.POTTED_STRANGEROOT);

        setTexturePath("dungeon/flesh/");
        MalumBlockStateSmithTypes.COLUMN.act(data, DungeonBlockSets.COLUMNAR_FLESH);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::directionalBlock, models()::cubeBottomTop, DungeonBlockSets.FLESHBULB);
        MalumBlockStateSmithTypes.WRITHING_FLESH.act(data, DungeonBlockSets.WRITHING_FLESH);

        setTexturePath("dungeon/odd_scriptures/");
        BlockStateSmithTypes.GLAZED_TERRACOTTA_BLOCK.act(data,
                DungeonBlockSets.ODD_SCRIPTURES_I, DungeonBlockSets.ODD_SCRIPTURES_II, DungeonBlockSets.ODD_SCRIPTURES_III, DungeonBlockSets.ODD_SCRIPTURES_IV, DungeonBlockSets.ODD_SCRIPTURES_V, DungeonBlockSets.ODD_SCRIPTURES_VI, DungeonBlockSets.ODD_SCRIPTURES_VII, DungeonBlockSets.ODD_SCRIPTURES_VIII, DungeonBlockSets.ODD_SCRIPTURES_IX);

        setTexturePath("dungeon/effigy/");
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.BLOCK_MODEL_ITEM, this::horizontalBlock, this::meditatingEffigy, DungeonBlockSets.VEILED_EFFIGY, DungeonBlockSets.CORRUPT_EFFIGY, DungeonBlockSets.CRACKED_EFFIGY);

        setTexturePath("waveform_artifice/");
        MalumBlockStateSmithTypes.SPIRIT_DIODE.act(data, WAVECHARGER, WAVEBANKER, WAVEMAKER, WAVEBREAKER);
        MalumBlockStateSmithTypes.GUST_TECH_BLOCK.act(data, GUST_IGNITER, WIND_TUNNEL);

        setTexturePath("ether/");
        itemModelProvider.setTexturePath("ether/");
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.GENERATED_ITEM,
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

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.BLOCK_MODEL_ITEM, this::simpleBlock, models()::cubeBottomTop,
                RITE_ANCHOR, RITE_UNWEAVER);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.BLOCK_MODEL_ITEM, this::directionalBlock, models()::cubeBottomTop,
                RITE_SPREADER);
        MalumBlockStateSmithTypes.RITE_CHANNEL.act(data,
                RITE_CHANNEL);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.BLOCK_MODEL_ITEM, this::simpleBlock, models()::predefinedModel,
                MalumContent.Progression.SPIRIT_ALTAR, MalumContent.Progression.SOUL_BRAZIER, MalumContent.Progression.RITUAL_PLINTH,
                DungeonBlockSets.OMINOUS_ALTAR);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.BUILTIN_ENTITY_ITEM, this::simpleBlock, models()::predefinedModel,
                MalumContent.Progression.SPIRIT_JAR);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.BLOCK_MODEL_ITEM, this::horizontalBlock, models()::predefinedModel,
                MalumContent.Progression.WEAVERS_WORKBENCH, MalumContent.Progression.RUNIC_WORKBENCH);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.NO_DATAGEN, this::simpleBlock, models()::predefinedModel,
                MalumContent.Progression.RUNEWOOD_OBELISK, MalumContent.Progression.RUNEWOOD_OBELISK_COMPONENT,
                MalumContent.Progression.BRILLIANT_OBELISK, MalumContent.Progression.BRILLIANT_OBELISK_COMPONENT,
                MalumContent.Progression.ARCANA_PYLON, MalumContent.Progression.ARCANA_PYLON_COMPONENT,
                MalumContent.Artifice.SPIRIT_CRUCIBLE, MalumContent.Artifice.SPIRIT_CRUCIBLE_COMPONENT, MalumContent.Artifice.REPAIR_PYLON,
                DungeonBlockSets.OMINOUS_OBELISK, DungeonBlockSets.OMINOUS_OBELISK_COMPONENT);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.BLOCK_MODEL_ITEM, this::simpleBlock, this::totemBaseModel,
                RUNEWOOD_TOTEM_BASE, SOULWOOD_TOTEM_BASE, WAVEFORM_RUNEWOOD_TOTEM_BASE, WAVEFORM_SOULWOOD_TOTEM_BASE);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.NO_DATAGEN, this::horizontalBlock, models()::predefinedModel,
                MalumContent.Artifice.SPIRIT_CATALYZER, MalumContent.Artifice.SPIRIT_CATALYZER_COMPONENT);

        MalumBlockStateSmithTypes.REPAIR_PYLON_COMPONENT.act(data, MalumContent.Artifice.REPAIR_PYLON_COMPONENT);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.BLOCK_MODEL_ITEM, this::simpleBlock, models()::predefinedModel,
                WeepingWell.VOID_CONDUIT, WeepingWell.VOID_DEPOT);

        MalumBlockStateSmithTypes.WEEPING_WELL_LAYERED_BLOCK.act(data, WeepingWell.WEEPING_WELL_CENTER, WeepingWell.WEEPING_WELL_SIDE, WeepingWell.WEEPING_WELL_SIDE_MIRROR, WeepingWell.WEEPING_WELL_CORNER);
        MalumBlockStateSmithTypes.WEEPING_WELL_BLOCK.act(data, WeepingWell.WEEPING_WELL_FLAGSTONE);
        MalumBlockStateSmithTypes.WEEPING_WELL_DIRECTIONAL_BLOCK.act(data, WeepingWell.WEEPING_WELL_COLUMN_BASE, WeepingWell.WEEPING_WELL_COLUMN, WeepingWell.WEEPING_WELL_COLUMN_CAP);

        MalumBlockStateSmithTypes.PRIMORDIAL_SOUP.act(data, WeepingWell.PRIMORDIAL_SOUP);

        BlockStateSmithTypes.FULL_BLOCK.act(data, BlockSets.THE_DEVICE, BlockSets.THE_VESSEL);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, ItemModelSmithTypes.NO_DATAGEN, this::simpleBlock, this::cubeModelAirTexture, MalumContent.Progression.SPIRIT_MOTE);
    }

    public void generateRockSet(BlockStateSystemData data, RockBlockSet set) {
        var blocks = new BlockBundle[] {
                set.rock, set.polishedRock, set.bricks,
                set.tiles//, set.grid, set.mosaic
        };
        for (BlockBundle bundle : blocks) {
            BlockStateSmithTypes.FULL_BLOCK.act(data, bundle.block);
            BlockStateSmithTypes.STAIRS_BLOCK.act(data, bundle.stairs);
            BlockStateSmithTypes.SLAB_BLOCK.act(data, bundle.slab);
            if (bundle instanceof BlockBundleWithWall wall) {
                BlockStateSmithTypes.WALL_BLOCK.act(data, wall.wall);
            }
        }

        MalumBlockStateSmithTypes.COLUMN.act(data, set.column);
        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, models()::cubeBottomTop, set.altar);

        BlockStateSmithTypes.BUTTON_BLOCK.act(data, set.button);
        BlockStateSmithTypes.PRESSURE_PLATE_BLOCK.act(data, set.pressurePlate);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::simpleBlock, this::rockItemPedestalModel, set.itemPedestal);

        BlockStateSmithTypes.CUSTOM_MODEL.act(data, this::directionalBlock, this::itemStandModel, set.itemStand);
    }

    public void generateWoodSet(BlockStateSystemData data, WoodBlockSet set) {
        var blockSets = new BlockBundle[] {
                set.boards, set.verticalBoards, set.blocks,
                set.planks, set.verticalPlanks, set.tiles
        };
        for (BlockBundle bundle : blockSets) {
            BlockStateSmithTypes.FULL_BLOCK.act(data, bundle.block);
            BlockStateSmithTypes.STAIRS_BLOCK.act(data, bundle.stairs);
            BlockStateSmithTypes.SLAB_BLOCK.act(data, bundle.slab);
            if (bundle instanceof BlockBundleWithWall wall) {
                BlockStateSmithTypes.WALL_BLOCK.act(data, wall.wall);
            }
        }
        var carvedSets = new ItemlessBlockBundle[] {
                set.carvedBoards, set.carvedVerticalBoards, set.carvedBlocks,
                set.carvedPlanks, set.carvedVerticalPlanks, set.carvedTiles
        };
        for (ItemlessBlockBundle carved : carvedSets) {
            VariedBlockStateSmithTypes.VARIED_FULL_BLOCK.act(data, carved.block);
            VariedBlockStateSmithTypes.VARIED_STAIRS_BLOCK.act(data, carved.stairs);
            VariedBlockStateSmithTypes.VARIED_SLAB_BLOCK.act(data, carved.slab);
//            if (carved instanceof BlockBundleWithWall wall) {
//                BlockStateSmithTypes.WALL_BLOCK.act(data, wall.wall);
//            }
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
