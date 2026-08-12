package com.sammy.malum.registry.common.util.building;

import com.mojang.datafixers.util.*;
import com.sammy.malum.*;
import com.sammy.malum.datagen.block.MalumBlockStateDatagen;
import com.sammy.malum.datagen.block.MalumBlockStateSmithTypes;
import com.sammy.malum.datagen.recipe.RecipeDatagenCommons;
import com.sammy.malum.datagen.sound.MalumBlockSoundDatagen;
import com.sammy.malum.registry.common.sound.MalumBlockSoundType;
import com.sammy.malum.registry.common.util.MalumRegistrySet;
import com.sammy.malum.registry.common.util.data.BlockBundle;
import com.sammy.malum.registry.common.util.data.BlockBundleWithWall;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.block.*;
import team.lodestar.lodestone.modules.core.util.BlockItemTagKey;
import team.lodestar.lodestone.modules.datagen.*;
import team.lodestar.lodestone.modules.datagen.smith.blockstate.BlockStateSystemData;
import team.lodestar.lodestone.modules.toolkit.block.*;
import team.lodestar.lodestone.modules.toolkit.creative_tab.CreativeTabCategoryBuilder;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.sammy.malum.datagen.recipe.RecipeDatagenCommons.*;
import static com.sammy.malum.registry.common.MalumContent.*;

public class MinorBuildingSet extends MalumRegistrySet {

    public static List<MinorBuildingSet> getMalumSets() {
//        return List.of(BlockSets.SEED_QUARTZ, BlockSets.TRODDEN_STONE, BlockSets.IGNEOUS_ROCK, BlockSets.COMPOSITE_STONE, BlockSets.EBONSTONE);
        return List.of(BuildingBlocks.TRODDEN_STONE, BuildingBlocks.IGNEOUS_ROCK, BuildingBlocks.COMPOSITE_STONE, BuildingBlocks.EBONSTONE);
    }

    private final MalumBlockSoundType rawSound;
    private final MalumBlockSoundType polishedSound;

    public final BlockItemTagKey blocksTag;
    public final BlockItemTagKey stairsTag;
    public final BlockItemTagKey slabsTag;
    public final BlockItemTagKey wallsTag;

    private final BlockBundle raw;
    private final BlockBundle smooth;
    private final BlockBundle polished;

    private final BlockBundleWithWall bricks;
    private final BlockBundleWithWall tiles;

    private final BlockBlockItemHolder<Block, BlockItem> cut;
    private final BlockBlockItemHolder<Block, BlockItem> chiseled;
    private final BlockBlockItemHolder<Block, BlockItem> pillar;

    public MinorBuildingSet(String id, Function<SoundType, LodestoneBlockProperties> properties) {
        super(id);

        blocksTag = createTag("blocks");
        stairsTag = createTag("stairs");
        slabsTag = createTag("slabs");
        wallsTag = createTag("walls");

        rawSound = new MalumBlockSoundType(name("raw_%s"));
        polishedSound = new MalumBlockSoundType(name("polished_%s"));

        Supplier<LodestoneBlockProperties> rawProperties = () -> properties.apply(rawSound);
        Supplier<LodestoneBlockProperties> polishedProperties = () -> properties.apply(polishedSound);

        raw = new BlockBundle(id, rawProperties, blocksTag, stairsTag, slabsTag);
        smooth = new BlockBundle(name("smooth_%s"), polishedProperties, blocksTag, stairsTag, slabsTag);
        polished = new BlockBundle(name("polished_%s"), polishedProperties, blocksTag, stairsTag, slabsTag);

        bricks = new BlockBundleWithWall(name("%s_bricks"), polishedProperties, blocksTag, stairsTag, slabsTag, wallsTag);
        tiles = new BlockBundleWithWall(name("%s_tiles"), polishedProperties, blocksTag, stairsTag, slabsTag, wallsTag);

        cut = registerBlock(name("cut_%s"), () -> new Block(polishedProperties.get()));
        chiseled = registerBlock(name("chiseled_%s"), () -> new Block(polishedProperties.get()));
        pillar = registerBlock(name("%s_pillar"), () -> new RotatedPillarBlock(polishedProperties.get()));
    }

    protected BlockItemTagKey createTag(String tag) {
        return BlockBundle.createTag(id, tag);
    }

    public static void addCommonRock(CreativeTabCategoryBuilder builder) {
        for (MinorBuildingSet set : getMalumSets()) {
            set.addToCreativeTab(builder);
        }
    }

    public void addToCreativeTab(CreativeTabCategoryBuilder builder) {
        builder.nextLine()
                .addItems(raw.block, smooth.block, polished.block, bricks.block, tiles.block).nextLine()
                .addItems(raw.stairs, smooth.stairs, polished.stairs, bricks.stairs, tiles.stairs).nextLine()
                .addItems(raw.slab, smooth.slab, polished.slab, bricks.slab, tiles.slab).nextLine()
                .addItems(pillar, cut, chiseled, bricks.wall, tiles.wall)
                .bake();
    }

    public void addBlockStates(MalumBlockStateDatagen datagen, BlockStateSystemData<MalumBlockStateDatagen> data) {
        datagen.setTexturePath("building/stone/" + id);

        datagen.generateBlockBundle(data, getRaw());
        datagen.generateBlockBundle(data, getSmooth());
        datagen.generateBlockBundle(data, getPolished());
        datagen.generateBlockBundle(data, getBricks());
        datagen.generateBlockBundle(data, getTiles());

        BlockStateSmithTypes.FULL_BLOCK.act(data, getChiseled());
        MalumBlockStateSmithTypes.CUT_STONE_BLOCK.act(data, getCut());
        BlockStateSmithTypes.LOG_BLOCK.act(data, getPillar());

    }

    public void addSounds(MalumBlockSoundDatagen datagen) {
        String path = "block/common_rock/" + id;
        datagen.add(rawSound, path, s -> s.setPlaceSoundName("break"));
        datagen.add(polishedSound, path, s -> s.modifySounds(se -> se.pitch(1.2f)));
    }

    public void addRecipes(RecipeOutput recipeOutput) {
        RecipeDatagenCommons.smelting(recipeOutput, MalumMod.malumPath(name("smooth_%s")), Ingredient.of(raw.block), RecipeCategory.MISC, Pair.of("has_block", has(blocksTag.itemTag())), smooth.block, 1, 0.25f);

        blockBundleCraftingAndStonecutting(recipeOutput, raw);
        blockBundleCraftingAndStonecutting(recipeOutput, smooth);
        blockBundleCraftingAndStonecutting(recipeOutput, polished);
        blockBundleCraftingAndStonecutting(recipeOutput, bricks);
        blockBundleCraftingAndStonecutting(recipeOutput, tiles);
    }

    public BlockBundle getRaw() {
        return raw;
    }

    public BlockBundle getSmooth() {
        return smooth;
    }

    public BlockBundle getPolished() {
        return polished;
    }

    public BlockBundleWithWall getBricks() {
        return bricks;
    }

    public BlockBundleWithWall getTiles() {
        return tiles;
    }

    public BlockBlockItemHolder<Block, BlockItem> getCut() {
        return cut;
    }

    public BlockBlockItemHolder<Block, BlockItem> getChiseled() {
        return chiseled;
    }

    public BlockBlockItemHolder<Block, BlockItem> getPillar() {
        return pillar;
    }
}