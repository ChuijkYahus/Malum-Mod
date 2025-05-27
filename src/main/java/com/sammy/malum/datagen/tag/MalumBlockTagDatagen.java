package com.sammy.malum.datagen.tag;

import com.sammy.malum.MalumMod;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.block.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.common.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.Nullable;
import team.lodestar.lodestone.systems.datagen.providers.LodestoneBlockTagsProvider;

import java.util.*;
import java.util.concurrent.CompletableFuture;

import static com.sammy.malum.registry.common.block.MalumBlocks.BLOCKS;

public class MalumBlockTagDatagen extends LodestoneBlockTagsProvider {

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
        Set<DeferredHolder<Block, ? extends Block>> blocks = new HashSet<>(BLOCKS.getEntries());

        tag(MalumTags.BlockTags.BLIGHT_REPLACEABLE).addTags(BlockTags.MOSS_REPLACEABLE, BlockTags.SAND).add(Blocks.CLAY);
        tag(MalumTags.BlockTags.BLIGHT_REMOVABLE).addTags(BlockTags.FLOWERS, BlockTags.REPLACEABLE).remove(MalumTags.BlockTags.BLIGHTED_PLANTS);

        tag(MalumTags.BlockTags.UNCHAINED_RITE_CATALYST).add(MalumBlocks.BLIGHTED_EARTH.get());
        tag(MalumTags.BlockTags.IS_RITE_IMMUNE).addTags(MalumTags.BlockTags.TAINTED_ROCK, MalumTags.BlockTags.TWISTED_ROCK, MalumTags.BlockTags.WEEPING_WELL);

        tag(MalumTags.BlockTags.INEXTINGUISHABLE_FLAME);
        tag(MalumTags.BlockTags.GREATER_AERIAL_WHITELIST);

        tag(MalumTags.BlockTags.SUNDERING_ANCHOR_KNIFE_BEHAVIOR).addOptional(ResourceLocation.parse("farmersdelight:cutting_board"));

        addTagsFromBlockProperties(blocks);
    }
}
