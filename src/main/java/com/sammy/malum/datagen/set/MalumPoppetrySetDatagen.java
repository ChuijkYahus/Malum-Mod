package com.sammy.malum.datagen.set;

import com.google.common.collect.ImmutableMap;
import com.sammy.malum.datagen.block.MalumBlockStateDatagen;
import com.sammy.malum.datagen.block.MalumBlockStateSmithTypes;
import com.sammy.malum.datagen.block.VariedBlockStateSmithTypes;
import com.sammy.malum.registry.common.MalumContent;
import com.sammy.malum.registry.common.util.DyedVariantBundle;
import com.sammy.malum.registry.common.util.GeodeCrystalRegistrySet;
import com.sammy.malum.registry.common.util.PoppetRegistrySet;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import team.lodestar.lodestone.modules.datagen.BlockStateSmithTypes;
import team.lodestar.lodestone.modules.datagen.DatagenSystemCommons;
import team.lodestar.lodestone.modules.datagen.smith.blockstate.BlockStateSystemData;
import team.lodestar.lodestone.modules.toolkit.block.BlockBlockItemHolder;

import java.util.List;
import java.util.function.Function;

import static com.sammy.malum.MalumMod.malumPath;
import static com.sammy.malum.registry.common.MalumContent.DungeonBlockSets.DROSS_STONE_ITEM_STAND;

public class MalumPoppetrySetDatagen {

    public static List<PoppetRegistrySet> getMalumPoppetworks() {
        return List.of(MalumContent.Poppetry.RUNEWOOD_POPPETWARE, MalumContent.Poppetry.SOULWOOD_POPPETWARE);
    }

    public static final MalumPoppetrySetDatagen MALUM = new MalumPoppetrySetDatagen();

    public final List<PoppetRegistrySet> poppetworks;

    public MalumPoppetrySetDatagen() {
        this(MalumPoppetrySetDatagen.getMalumPoppetworks());
    }

    public MalumPoppetrySetDatagen(List<PoppetRegistrySet> poppetworks) {
        this.poppetworks = poppetworks;
    }

    public void addBlockStates(BlockStateSystemData<MalumBlockStateDatagen> data) {
        DatagenSystemCommons.BLOCK_TEXTURE.setFolder("poppetry/pillow");
        for (PoppetRegistrySet set : poppetworks) {
            var pillow = set.getPillow();
            var variants = pillow.getVariants();
            for (DyeColor dyeColor : variants.keySet()) {
                var holder = variants.get(dyeColor);
                BlockStateSmithTypes.CUSTOM_MODEL.act(data, data.provider()::simpleBlock, data.provider()::poppetPillowModel, holder);
            }
        }
        DatagenSystemCommons.BLOCK_TEXTURE.clearFolder();
    }


    public void addTags(Function<TagKey<Block>, IntrinsicHolderTagsProvider.IntrinsicTagAppender<Block>> tag) {
        for (PoppetRegistrySet poppet : poppetworks) {
        }
    }

    public void buildPoppetryRecipes(RecipeOutput output) {
        for (PoppetRegistrySet poppet : poppetworks) {

        }
    }
}