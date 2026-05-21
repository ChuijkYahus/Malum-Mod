package com.sammy.malum.datagen.set;

import com.sammy.malum.datagen.block.MalumBlockStateDatagen;
import com.sammy.malum.datagen.block.VariedBlockStateSmithTypes;
import com.sammy.malum.registry.common.util.GeodeCrystalRegistrySet;
import com.sammy.malum.datagen.block.MalumBlockStateSmithTypes;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import team.lodestar.lodestone.modules.datagen.BlockStateSmithTypes;
import team.lodestar.lodestone.modules.datagen.DatagenSystemCommons;
import team.lodestar.lodestone.modules.datagen.smith.blockstate.BlockStateSystemData;

import java.util.List;
import java.util.function.Function;

public class MalumCrystalSetDatagen {

    public static final MalumCrystalSetDatagen MALUM = new MalumCrystalSetDatagen();

    public final List<GeodeCrystalRegistrySet> crystals;

    public MalumCrystalSetDatagen() {
        this(GeodeCrystalRegistrySet.getMalumCrystals());
    }

    public MalumCrystalSetDatagen(List<GeodeCrystalRegistrySet> crystals) {
        this.crystals = crystals;
    }

    public void addBlockStates(BlockStateSystemData<MalumBlockStateDatagen> data) {

        DatagenSystemCommons.ITEM_TEXTURE.setFolder("geode");
        DatagenSystemCommons.BLOCK_TEXTURE.setFolder("geode");
        for (GeodeCrystalRegistrySet crystal : crystals) {
            VariedBlockStateSmithTypes.VARIED_FULL_BLOCK.act(data, crystal.getGeode());
            BlockStateSmithTypes.FULL_BLOCK.act(data, crystal.getBudding(), crystal.getPolished());
            MalumBlockStateSmithTypes.GEODE_CRYSTAL_CLUSTER.act(data, crystal.getCluster());
            BlockStateSmithTypes.LAMP_BLOCK.act(data, crystal.getLamp());
        }
        DatagenSystemCommons.BLOCK_TEXTURE.clearFolder();
        DatagenSystemCommons.ITEM_TEXTURE.clearFolder();
    }

    public void addTags(Function<TagKey<Block>, IntrinsicHolderTagsProvider.IntrinsicTagAppender<Block>> tag) {
        for (GeodeCrystalRegistrySet crystal : crystals) {
        }
    }

    public void buildCrystalRecipes(RecipeOutput output) {
        for (GeodeCrystalRegistrySet crystal : crystals) {
//            MalumMetallicsRecipes.buildMetallicsRecipes(output, crystal);
        }
    }
}
