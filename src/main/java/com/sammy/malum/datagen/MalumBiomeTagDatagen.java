package com.sammy.malum.datagen;

import com.sammy.malum.*;
import net.minecraft.core.*;
import net.minecraft.data.*;
import net.minecraft.data.tags.*;
import net.neoforged.neoforge.common.data.*;
import org.jetbrains.annotations.*;

import java.util.concurrent.*;

import static com.sammy.malum.registry.common.MalumTags.Biomes.*;
import static net.minecraft.world.level.biome.Biomes.*;
import static net.neoforged.neoforge.common.Tags.Biomes.*;

public class MalumBiomeTagDatagen extends BiomeTagsProvider {

    public MalumBiomeTagDatagen(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, MalumMod.MALUM, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        //super.addTags(pProvider);
        tag(HAS_SOULSTONE).addTag(IS_OVERWORLD);
        tag(HAS_BRILLIANT).addTag(IS_OVERWORLD);
        tag(HAS_BLAZING_QUARTZ).addTag(IS_NETHER);
        tag(HAS_QUARTZ).addTag(IS_OVERWORLD);
        tag(HAS_CTHONIC).addTag(IS_OVERWORLD);

        tag(HAS_RUNEWOOD).addTag(IS_PLAINS).addTag(IS_MOUNTAIN).addTag(IS_HILL).remove(IS_SNOWY);
        tag(HAS_RARE_RUNEWOOD).addTag(IS_FOREST).remove(IS_SNOWY);
        tag(HAS_RUNIC_SANCTUARY).addTag(IS_PLAINS).remove(IS_SNOWY);

        tag(HAS_AZURE_RUNEWOOD).addTag(IS_SNOWY_PLAINS).add(SNOWY_TAIGA).add(FROZEN_RIVER).add(SNOWY_BEACH);
        tag(HAS_RARE_AZURE_RUNEWOOD).add(FROZEN_PEAKS).add(JAGGED_PEAKS).add(SNOWY_SLOPES).add(GROVE);
        tag(HAS_AZURE_SANCTUARY).addTag(IS_SNOWY_PLAINS);

        tag(HAS_WEEPING_WELL).addTag(IS_OVERWORLD);
    }
}
