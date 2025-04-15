package com.sammy.malum.datagen.tag;

import com.sammy.malum.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.block.*;
import com.sammy.malum.registry.common.tag.*;
import net.minecraft.core.*;
import net.minecraft.data.*;
import net.minecraft.data.tags.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.common.data.*;
import net.neoforged.neoforge.registries.*;
import org.jetbrains.annotations.*;
import team.lodestar.lodestone.systems.datagen.providers.*;

import java.util.*;
import java.util.concurrent.*;

import static com.sammy.malum.registry.common.block.BlockRegistry.*;

public class MalumGeasTagDatagen extends IntrinsicHolderTagsProvider<GeasEffectType> {

    public MalumGeasTagDatagen(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, MalumGeasEffectTypeRegistry.GEAS_TYPES_KEY, lookupProvider, geas -> MalumGeasEffectTypeRegistry.GEAS_TYPES_REGISTRY.getHolder(geas.getId()).orElseThrow().getKey(), MalumMod.MALUM, existingFileHelper);
    }

    @Override
    public String getName() {
        return "Malum Geas Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        Set<DeferredHolder<GeasEffectType, ? extends GeasEffectType>> gease = new HashSet<>(MalumGeasEffectTypeRegistry.GEAS_TYPES.getEntries());

        for (DeferredHolder<GeasEffectType, ? extends GeasEffectType> holder : gease) {
            var geas = holder.get();
            geas.getId();
            var id = holder.getId();
            if (id.getPath().startsWith("oath")) {
                tag(GeasTagRegistry.IS_OATH).add(geas);
                continue;
            }
            if (id.getPath().startsWith("bond")) {
                tag(GeasTagRegistry.IS_BOND).add(geas);
                continue;
            }
            if (id.getPath().startsWith("ultimatum")) {
                tag(GeasTagRegistry.IS_ULTIMATUM).add(geas);
            }
        }
    }
}