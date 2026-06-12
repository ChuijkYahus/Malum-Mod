package com.sammy.malum.registry.common.util.building;

import com.mojang.datafixers.util.Pair;
import com.sammy.malum.MalumMod;
import com.sammy.malum.datagen.block.MalumBlockStateDatagen;
import com.sammy.malum.datagen.recipe.RecipeDatagenCommons;
import com.sammy.malum.datagen.recipe.builder.SpiritFocusingRecipeBuilder;
import com.sammy.malum.datagen.recipe.builder.SpiritInfusionRecipeBuilder;
import com.sammy.malum.datagen.recipe.builder.SpiritRepairRecipeBuilder;
import com.sammy.malum.datagen.sound.MalumBlockSoundDatagen;
import com.sammy.malum.registry.common.sound.MalumBlockSoundType;
import com.sammy.malum.registry.common.util.MalumRegistrySet;
import com.sammy.malum.registry.common.util.MetallicsItemRegistryBundle;
import com.sammy.malum.registry.common.util.data.BlockBundle;
import com.sammy.malum.registry.common.util.data.BlockBundleWithWall;
import net.minecraft.advancements.Criterion;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.SoundType;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.NotCondition;
import net.neoforged.neoforge.common.conditions.TagEmptyCondition;
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import team.lodestar.lodestone.modules.core.util.BlockItemTagKey;
import team.lodestar.lodestone.modules.datagen.smith.blockstate.BlockStateSystemData;
import team.lodestar.lodestone.modules.toolkit.block.LodestoneBlockProperties;
import team.lodestar.lodestone.modules.toolkit.creative_tab.CreativeTabCategoryBuilder;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.sammy.malum.datagen.recipe.RecipeDatagenCommons.blockBundleStonecutting;
import static com.sammy.malum.registry.common.MalumContent.*;
import static com.sammy.malum.registry.common.MalumContent.AlchemyAndMetallics.ALCHEMICAL_IMPETUS;
import static com.sammy.malum.registry.common.MalumContent.Materials.CTHONIC_GOLD;
import static com.sammy.malum.registry.common.MalumContent.Materials.CTHONIC_GOLD_FRAGMENT;
import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class MinorBuildingSet extends MalumRegistrySet {

    public static List<MinorBuildingSet> getMalumSets() {
        return List.of(BlockSets.TRODDEN_STONE, BlockSets.COMPOSITE_STONE, BlockSets.IGNEOUS_ROCK, BlockSets.SEED_QUARTZ, BlockSets.EBONSTONE);
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
                .addItems(bricks.wall, tiles.wall)
                .bake();
    }

    public void addBlockStates(MalumBlockStateDatagen datagen, BlockStateSystemData<MalumBlockStateDatagen> data) {
        datagen.setTexturePath("building/common_rock/" + id);


        datagen.generateBlockBundle(data, getRaw());
        datagen.generateBlockBundle(data, getSmooth());
        datagen.generateBlockBundle(data, getPolished());
        datagen.generateBlockBundle(data, getBricks());
        datagen.generateBlockBundle(data, getTiles());
    }

    public void addSounds(MalumBlockSoundDatagen datagen) {
        String path = "block/common_rock/" + id;
        datagen.add(rawSound, path, s -> s.setPlaceSoundName("break"));
        datagen.add(polishedSound, path, s -> s.modifySounds(se -> se.pitch(1.2f)));
    }

    public void addRecipes(RecipeOutput recipeOutput) {
        blockBundleStonecutting(recipeOutput, raw,
                blocksTag.itemTag(), stairsTag.itemTag(), slabsTag.itemTag(), wallsTag.itemTag());
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
}