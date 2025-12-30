package com.sammy.malum.datagen.tag;

import com.sammy.malum.MalumMod;
import com.sammy.malum.registry.common.MalumTags;
import com.sammy.malum.registry.common.entity.MalumEntityTypes;
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
                .add(MalumEntityTypes.ALTAR.get())
                .add(MalumEntityTypes.BELIEVER.get())
                .add(MalumEntityTypes.CHERUB.get())
                .add(MalumEntityTypes.CARDINAL.get())
                .add(MalumEntityTypes.EVANGELIST.get());

        tag(EntityTypeTags.FALL_DAMAGE_IMMUNE)
                .addTag(MalumTags.EntityTags.CULTIST);
    }
}