package com.sammy.malum.datagen.tag;

import com.sammy.malum.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.core.*;
import net.minecraft.data.*;
import net.minecraft.data.tags.*;
import net.neoforged.neoforge.common.data.*;
import net.neoforged.neoforge.registries.*;
import org.jetbrains.annotations.*;

import java.util.*;
import java.util.concurrent.*;

public class MalumGeasTagDatagen extends IntrinsicHolderTagsProvider<GeasEffectType> {

    public MalumGeasTagDatagen(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, MalumGeasEffectTypes.GEAS_TYPES_KEY, lookupProvider, geas -> MalumGeasEffectTypes.GEAS_TYPES_REGISTRY.getHolder(geas.getId()).orElseThrow().getKey(), MalumMod.MALUM, existingFileHelper);
    }

    @Override
    public String getName() {
        return "Malum Geas Tags";
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        Set<DeferredHolder<GeasEffectType, ? extends GeasEffectType>> gease = new HashSet<>(MalumGeasEffectTypes.GEAS_TYPES.getEntries());

        for (DeferredHolder<GeasEffectType, ? extends GeasEffectType> holder : gease) {
            var geas = holder.get();
            geas.getId();
            var id = holder.getId();
            if (id.getPath().startsWith("oath")) {
                tag(MalumTags.GeasTags.IS_OATH).add(geas);
                continue;
            }
            if (id.getPath().startsWith("bond")) {
                tag(MalumTags.GeasTags.IS_BOND).add(geas);
                continue;
            }
            if (id.getPath().startsWith("authority")) {
                tag(MalumTags.GeasTags.IS_AUTHORITY).add(geas);
            }
        }

        tag(MalumTags.GeasTags.HIDDEN_UNTIL_BLACK_CRYSTAL).addTags(MalumTags.GeasTags.IS_OATH, MalumTags.GeasTags.IS_AUTHORITY);
    }
}