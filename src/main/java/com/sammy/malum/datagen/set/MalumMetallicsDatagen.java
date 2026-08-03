package com.sammy.malum.datagen.set;

import com.sammy.malum.common.data.component.soulstone.*;
import com.sammy.malum.common.data.map.SoulstoneOreConversionMap;
import com.sammy.malum.registry.common.util.MetallicsItemRegistryBundle;
import com.sammy.malum.datagen.block.MalumBlockStateSmithTypes;
import com.sammy.malum.datagen.item.MalumItemModelSmithTypes;
import com.sammy.malum.datagen.recipe.MalumMetallicsRecipes;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.DataMapProvider;
import team.lodestar.lodestone.modules.datagen.smith.blockstate.BlockStateSystemData;
import team.lodestar.lodestone.modules.datagen.smith.itemmodel.data.ItemModelSystemData;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static com.sammy.malum.registry.common.MalumTags.Blocks.PREFERRED_SOULSTONE_BUD_SURFACE;

public class MalumMetallicsDatagen {

    public static final MalumMetallicsDatagen MALUM = new MalumMetallicsDatagen();

    public final List<MetallicsItemRegistryBundle> metallics;

    public MalumMetallicsDatagen() {
        this(MetallicsItemRegistryBundle.getMalumMetallics());
    }

    public MalumMetallicsDatagen(List<MetallicsItemRegistryBundle> metallics) {
        this.metallics = metallics;
    }

    public void addBlockStates(BlockStateSystemData data) {
        for (MetallicsItemRegistryBundle metallic : metallics) {
            MalumBlockStateSmithTypes.METALLIC_STORAGE_BLOCK.act(data, metallic.getDerealizedStorageBlock());
            MalumBlockStateSmithTypes.METALLIC_STORAGE_BLOCK.act(data, metallic.getHarmonizedStorageBlock());
        }
    }

    public void addItemModels(ItemModelSystemData data) {
        for (MetallicsItemRegistryBundle metallic : metallics) {
            MalumItemModelSmithTypes.IMPETUS_ITEM.act(data, metallic.getImpetus());
            MalumItemModelSmithTypes.IMPETUS_ITEM.act(data, metallic.getFracturedImpetus());
            MalumItemModelSmithTypes.IMPETUS_ITEM.act(data, metallic.getNode());
            MalumItemModelSmithTypes.GENERATED_ITEM.act(data, metallic.getDerealizedMetal());
            MalumItemModelSmithTypes.GENERATED_ITEM.act(data, metallic.getHarmonizedMetal());
        }
    }

    public void addSoulstoneConversions(DataMapProvider.Builder<SoulstoneOreConversionMap, Block> builder) {
        for (MetallicsItemRegistryBundle metallic : metallics) {
            var metalData = new StoredInSoulstoneMetal(metallic.getId(), metallic.getNuggetTag());
            var map = new SoulstoneOreConversionMap(List.of(
                    new SoulstoneOreConversionMap.SoulstoneOreConversion(
                            Optional.of(new TagMatchTest(Tags.Blocks.ORES_IN_GROUND_STONE)),
                            metallic.getOre().get().defaultBlockState(),
                            metalData
                    ),
                    new SoulstoneOreConversionMap.SoulstoneOreConversion(
                            Optional.of(new TagMatchTest(Tags.Blocks.ORES_IN_GROUND_DEEPSLATE)),
                            metallic.getDeepslateOre().get().defaultBlockState(),
                            metalData
                    )
            ));
            var oreTag = metallic.getOreTag();
            builder.add(oreTag, map, false);
        }
    }

    public void addTags(Function<TagKey<Block>, IntrinsicHolderTagsProvider.IntrinsicTagAppender<Block>> tag) {
        for (MetallicsItemRegistryBundle metallic : metallics) {
            tag.apply(PREFERRED_SOULSTONE_BUD_SURFACE).add(metallic.getOre().get(), metallic.getDeepslateOre().get());
            tag.apply(PREFERRED_SOULSTONE_BUD_SURFACE).addOptionalTag(metallic.getOreTag());
        }
    }

    public void buildMetallicsRecipes(RecipeOutput output) {
        for (MetallicsItemRegistryBundle metallic : metallics) {
            MalumMetallicsRecipes.buildMetallicsRecipes(output, metallic);
        }
    }
}
