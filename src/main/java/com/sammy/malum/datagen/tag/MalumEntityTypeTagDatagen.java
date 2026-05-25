package com.sammy.malum.datagen.tag;

import com.sammy.malum.MalumMod;
import com.sammy.malum.registry.common.MalumTags;
import com.sammy.malum.registry.common.entity.*;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EntityTypeTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

import static com.sammy.malum.registry.common.entity.MalumCultistEntityTypes.*;

public class MalumEntityTypeTagDatagen extends EntityTypeTagsProvider {

    public MalumEntityTypeTagDatagen(PackOutput pOutput, CompletableFuture<Provider> pProvider, ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, MalumMod.MALUM, existingFileHelper);
    }

    @Override
    protected void addTags(Provider pProvider) {
        tag(MalumTags.Entities.CULTIST).add(ALTAR.get(), BELIEVER.get(), CHERUB.get(), CARDINAL.get(), EVANGELIST.get());


        tag(MalumTags.Entities.IGNORE_SEAT).addOptionalTag(ResourceLocation.parse("create:ignore_seat"));


        tag(EntityTypeTags.FALL_DAMAGE_IMMUNE)
                .addTag(MalumTags.Entities.CULTIST);
    }
}