package com.sammy.malum.datagen;

import com.sammy.malum.*;
import com.sammy.malum.registry.common.util.GeodeCrystalRegistrySet;
import net.minecraft.core.*;
import net.minecraft.data.*;
import net.minecraft.data.tags.*;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.common.data.*;
import org.jetbrains.annotations.*;

import java.util.concurrent.*;
import java.util.function.Consumer;

import static com.sammy.malum.registry.common.MalumContent.Materials.*;
import static com.sammy.malum.registry.common.MalumTags.Biomes.*;
import static net.minecraft.world.level.biome.Biomes.*;
import static net.neoforged.neoforge.common.Tags.Biomes.*;

public class MalumBiomeTagDatagen extends BiomeTagsProvider {

    public MalumBiomeTagDatagen(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, MalumMod.MALUM, existingFileHelper);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        //super.addTags(pProvider);
        tag(HAS_SOULSTONE).addTag(IS_OVERWORLD);
        tag(HAS_BRILLIANCE).addTag(IS_OVERWORLD);
        tag(HAS_CTHONIC_GOLD).addTag(IS_OVERWORLD);

        tag(VIVID_AMETRINE, b -> b.addTags(IS_FOREST, IS_PLAINS, IS_JUNGLE).add(CHERRY_GROVE));
        tag(MARINE_AGATE, b -> b.addTags(IS_OCEAN, IS_AQUATIC, IS_ICY));
        tag(RUGGED_CITRINE, b -> b.addTags(IS_MOUNTAIN, IS_BADLANDS, IS_TAIGA));

        tag(HAS_RUNEWOOD).addTag(IS_PLAINS).addTag(IS_MOUNTAIN).addTag(IS_HILL).remove(IS_SNOWY);
        tag(HAS_RARE_RUNEWOOD).addTag(IS_FOREST).remove(IS_SNOWY);
        tag(HAS_RUNIC_SANCTUARY).addTag(IS_PLAINS).remove(IS_SNOWY);

        tag(HAS_AZURE_RUNEWOOD).addTag(IS_SNOWY_PLAINS).add(SNOWY_TAIGA).add(FROZEN_RIVER).add(SNOWY_BEACH);
        tag(HAS_RARE_AZURE_RUNEWOOD).add(FROZEN_PEAKS).add(JAGGED_PEAKS).add(SNOWY_SLOPES).add(GROVE);
        tag(HAS_AZURE_SANCTUARY).addTag(IS_SNOWY_PLAINS);

        tag(HAS_WEEPING_WELL).addTag(IS_OVERWORLD);
    }

    public void tag(GeodeCrystalRegistrySet set, Consumer<TagAppender<Biome>> tagAppender) {
        tagAppender.accept(tag(set.getBiomeTag()));
    }
}
