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
import team.lodestar.lodestone.modules.datagen.providers.tag.LodestoneBlockTagsSystem;

import java.util.*;
import java.util.concurrent.CompletableFuture;

import static com.sammy.malum.registry.common.MalumTags.BlockTags.*;
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

        tag(WILD_WITCHHAZEL_PLACEABLE_ON).addTags(BASE_STONE_OVERWORLD).add(Blocks.MYCELIUM);
        tag(EBONY_PLANTABLE_ON).addTags(BASE_STONE_OVERWORLD);

        tag(BLIGHT_REPLACEABLE).addTags(MOSS_REPLACEABLE, SAND).add(Blocks.CLAY, Blocks.GRAVEL);
        tag(BLIGHT_REMOVABLE).addTags(FLOWERS, REPLACEABLE).remove(BLIGHTED_PLANTS).remove(MalumBlocks.CLINGING_BLIGHT.get());

        tag(UNCHAINED_RITE_CATALYST).add(MalumBlocks.BLIGHTED_EARTH.get());
        tag(IS_RITE_IMMUNE).addTags(TAINTED_ROCK, TWISTED_ROCK, WEEPING_WELL);

        tag(INEXTINGUISHABLE_FLAME);
        tag(GREATER_AERIAL_WHITELIST);

        tag(SUNDERING_ANCHOR_KNIFE_BEHAVIOR).addOptional(ResourceLocation.parse("farmersdelight:cutting_board"));

        addTagsFromBlockProperties(blocks);
    }
}
