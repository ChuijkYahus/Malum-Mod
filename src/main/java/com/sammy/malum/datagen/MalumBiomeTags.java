package com.sammy.malum.datagen;

import com.sammy.malum.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.core.*;
import net.minecraft.data.*;
import net.minecraft.data.tags.*;
import net.minecraft.world.level.biome.*;
import net.neoforged.neoforge.common.*;
import net.neoforged.neoforge.common.data.*;
import org.jetbrains.annotations.*;

import java.util.concurrent.*;

public class MalumBiomeTags extends BiomeTagsProvider {

    public MalumBiomeTags(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, MalumMod.MALUM, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        //super.addTags(pProvider);
        tag(MalumTags.BiomeTags.HAS_SOULSTONE).addTag(net.minecraft.tags.BiomeTags.IS_OVERWORLD);
        tag(MalumTags.BiomeTags.HAS_BRILLIANT).addTag(net.minecraft.tags.BiomeTags.IS_OVERWORLD);
        tag(MalumTags.BiomeTags.HAS_BLAZING_QUARTZ).addTag(net.minecraft.tags.BiomeTags.IS_NETHER);
        tag(MalumTags.BiomeTags.HAS_QUARTZ).addTag(net.minecraft.tags.BiomeTags.IS_OVERWORLD);
        tag(MalumTags.BiomeTags.HAS_CTHONIC).addTag(net.minecraft.tags.BiomeTags.IS_OVERWORLD);

        tag(MalumTags.BiomeTags.HAS_RUNEWOOD).addTag(Tags.Biomes.IS_PLAINS).addTag(Tags.Biomes.IS_MOUNTAIN).addTag(net.minecraft.tags.BiomeTags.IS_HILL).remove(Tags.Biomes.IS_SNOWY);
        tag(MalumTags.BiomeTags.HAS_RARE_RUNEWOOD).addTag(net.minecraft.tags.BiomeTags.IS_FOREST).remove(Tags.Biomes.IS_SNOWY);

        tag(MalumTags.BiomeTags.HAS_AZURE_RUNEWOOD).add(Biomes.SNOWY_PLAINS).add(Biomes.SNOWY_TAIGA).add(Biomes.FROZEN_RIVER).add(Biomes.SNOWY_BEACH);
        tag(MalumTags.BiomeTags.HAS_RARE_AZURE_RUNEWOOD).add(Biomes.FROZEN_PEAKS).add(Biomes.JAGGED_PEAKS).add(Biomes.SNOWY_SLOPES).add(Biomes.GROVE);

        tag(MalumTags.BiomeTags.HAS_WEEPING_WELL).addTag(net.minecraft.tags.BiomeTags.IS_OVERWORLD);
    }
}
