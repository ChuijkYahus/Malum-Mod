package com.sammy.malum.datagen.tag;

import com.sammy.malum.MalumMod;
import com.sammy.malum.registry.common.MalumTags;
import com.sammy.malum.registry.common.entity.MalumEntities;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class MalumEntityTypeTagDatagen extends EntityTypeTagsProvider {

    public MalumEntityTypeTagDatagen(PackOutput pOutput, CompletableFuture<Provider> pProvider, ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, MalumMod.MALUM, existingFileHelper);
    }

    @Override
    protected void addTags(Provider pProvider) {
        tag(MalumTags.EntityTags.CULTIST)
                .add(MalumEntities.ALTAR.get())
                .add(MalumEntities.BELIEVER.get())
                .add(MalumEntities.CHERUB.get())
                .add(MalumEntities.CARDINAL.get())
                .add(MalumEntities.EVANGELIST.get());

        tag(EntityTypeTags.FALL_DAMAGE_IMMUNE)
                .addTag(MalumTags.EntityTags.CULTIST);
    }
}