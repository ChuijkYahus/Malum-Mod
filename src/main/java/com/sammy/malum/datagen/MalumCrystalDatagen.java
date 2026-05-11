package com.sammy.malum.datagen;

import com.sammy.malum.datagen.block.VariedBlockStateSmithTypes;
import com.sammy.malum.registry.common.util.GeodeCrystalSet;
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

public class MalumCrystalDatagen {

    public static final MalumCrystalDatagen MALUM = new MalumCrystalDatagen();

    public final List<GeodeCrystalSet> crystals;

    public MalumCrystalDatagen() {
        this(GeodeCrystalSet.getMalumCrystals());
    }

    public MalumCrystalDatagen(List<GeodeCrystalSet> crystals) {
        this.crystals = crystals;
    }

    public void addBlockStates(BlockStateSystemData data) {
        setTexturePath("geode");
        for (GeodeCrystalSet crystal : crystals) {
            VariedBlockStateSmithTypes.VARIED_FULL_BLOCK.act(data, crystal.getGeode());
            BlockStateSmithTypes.FULL_BLOCK.act(data, crystal.getBudding(), crystal.getPolished());
            MalumBlockStateSmithTypes.GEODE_CRYSTAL_CLUSTER.act(data, crystal.getCluster());
        }
        setTexturePath("");
    }

    public void addTags(Function<TagKey<Block>, IntrinsicHolderTagsProvider.IntrinsicTagAppender<Block>> tag) {
        for (GeodeCrystalSet crystal : crystals) {
        }
    }

    public void buildCrystalRecipes(RecipeOutput output) {
        for (GeodeCrystalSet crystal : crystals) {
//            MalumMetallicsRecipes.buildMetallicsRecipes(output, crystal);
        }
    }

    public void setTexturePath(String folder) {
        DatagenSystemCommons.ITEM_TEXTURE.setFolder(folder);
    }
}
