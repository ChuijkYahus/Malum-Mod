package com.sammy.malum.datagen.tag;

import com.sammy.malum.MalumMod;
import com.sammy.malum.common.data.map.SoulstoneOreConversionMap;
import com.sammy.malum.common.item.metallics.MetallicsItemRegistryBundle;
import com.sammy.malum.datagen.MalumMetallicsDatagen;
import com.sammy.malum.registry.common.MalumTags;
import com.sammy.malum.registry.common.block.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.NotCondition;
import net.neoforged.neoforge.common.conditions.TagEmptyCondition;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import team.lodestar.lodestone.modules.datagen.providers.tag.LodestoneBlockTagsSystem;

import java.util.*;
import java.util.concurrent.CompletableFuture;

import static com.sammy.malum.registry.common.MalumDataMaps.SOULSTONE_ORE_CONVERSION;
import static com.sammy.malum.registry.common.MalumTags.Blocks.*;
import static com.sammy.malum.registry.common.block.MalumBlocks.BLOCKS;
import static net.minecraft.tags.BlockTags.*;

public class MalumBlockTagDatagen extends LodestoneBlockTagsSystem {

    public MalumBlockTagDatagen(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MalumMod.MALUM, existingFileHelper);
    }

    @Override
    public String getName() {
        return "Malum Block Tags";
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        var blocks = new HashSet<>(BLOCKS.getEntries());

        MalumMetallicsDatagen.MALUM.addTags(this::tag);

        tag(WILD_WITCHHAZEL_PLACEABLE_ON).addTags(BASE_STONE_OVERWORLD).add(net.minecraft.world.level.block.Blocks.MYCELIUM);
        tag(EBONY_PLANTABLE_ON).addTags(BASE_STONE_OVERWORLD);

        tag(BLIGHT_REPLACEABLE).addTags(MOSS_REPLACEABLE, SAND).add(net.minecraft.world.level.block.Blocks.CLAY, net.minecraft.world.level.block.Blocks.GRAVEL);
        tag(BLIGHT_REMOVABLE).addTags(FLOWERS, REPLACEABLE).remove(BLIGHTED_PLANTS).remove(MalumBlocks.CLINGING_BLIGHT.get());

        tag(UNCHAINED_RITE_CATALYST).add(MalumBlocks.BLIGHTED_EARTH.get());
        tag(IS_RITE_IMMUNE).addTags(TAINTED_ROCK, TWISTED_ROCK, WEEPING_WELL);

        tag(GREATER_AERIAL_WHITELIST);

        tag(SUNDERING_ANCHOR_PLEASE_BE_A_KNIFE).addOptional(ResourceLocation.parse("farmersdelight:cutting_board"));

        addTagsFromBlockProperties(blocks);
    }
}
