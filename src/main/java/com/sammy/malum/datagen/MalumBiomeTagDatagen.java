package com.sammy.malum.datagen;

import com.sammy.malum.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.core.*;
import net.minecraft.data.*;
import net.minecraft.data.tags.*;
import net.neoforged.neoforge.common.*;
import net.neoforged.neoforge.common.data.*;
import org.jetbrains.annotations.*;

import java.util.concurrent.*;

public class MalumBiomeTagDatagen extends BiomeTagsProvider {

    public MalumBiomeTagDatagen(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, MalumMod.MALUM, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        //super.addTags(pProvider);
        tag(MalumTags.Biomes.HAS_SOULSTONE).addTag(net.minecraft.tags.BiomeTags.IS_OVERWORLD);
        tag(MalumTags.Biomes.HAS_BRILLIANT).addTag(net.minecraft.tags.BiomeTags.IS_OVERWORLD);
        tag(MalumTags.Biomes.HAS_BLAZING_QUARTZ).addTag(net.minecraft.tags.BiomeTags.IS_NETHER);
        tag(MalumTags.Biomes.HAS_QUARTZ).addTag(net.minecraft.tags.BiomeTags.IS_OVERWORLD);
        tag(MalumTags.Biomes.HAS_CTHONIC).addTag(net.minecraft.tags.BiomeTags.IS_OVERWORLD);

        tag(MalumTags.Biomes.HAS_RUNEWOOD).addTag(Tags.Biomes.IS_PLAINS).addTag(Tags.Biomes.IS_MOUNTAIN).addTag(net.minecraft.tags.BiomeTags.IS_HILL).remove(Tags.Biomes.IS_SNOWY);
        tag(MalumTags.Biomes.HAS_RARE_RUNEWOOD).addTag(net.minecraft.tags.BiomeTags.IS_FOREST).remove(Tags.Biomes.IS_SNOWY);

        tag(MalumTags.Biomes.HAS_AZURE_RUNEWOOD).add(net.minecraft.world.level.biome.Biomes.SNOWY_PLAINS).add(net.minecraft.world.level.biome.Biomes.SNOWY_TAIGA).add(net.minecraft.world.level.biome.Biomes.FROZEN_RIVER).add(net.minecraft.world.level.biome.Biomes.SNOWY_BEACH);
        tag(MalumTags.Biomes.HAS_RARE_AZURE_RUNEWOOD).add(net.minecraft.world.level.biome.Biomes.FROZEN_PEAKS).add(net.minecraft.world.level.biome.Biomes.JAGGED_PEAKS).add(net.minecraft.world.level.biome.Biomes.SNOWY_SLOPES).add(net.minecraft.world.level.biome.Biomes.GROVE);

        tag(MalumTags.Biomes.HAS_WEEPING_WELL).addTag(net.minecraft.tags.BiomeTags.IS_OVERWORLD);
    }
}
