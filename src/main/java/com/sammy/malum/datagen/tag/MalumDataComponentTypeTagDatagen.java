package com.sammy.malum.datagen.tag;

import com.sammy.malum.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.core.*;
import net.minecraft.core.component.*;
import net.minecraft.core.registries.*;
import net.minecraft.data.*;
import net.minecraft.data.tags.*;
import net.neoforged.neoforge.common.data.*;
import org.jetbrains.annotations.*;

import java.util.concurrent.*;

public class MalumDataComponentTypeTagDatagen extends IntrinsicHolderTagsProvider<DataComponentType<?>> {

    public MalumDataComponentTypeTagDatagen(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, Registries.DATA_COMPONENT_TYPE, lookupProvider, c -> BuiltInRegistries.DATA_COMPONENT_TYPE.getResourceKey(c).orElseThrow(), MalumMod.MALUM, existingFileHelper);
    }

    @Override
    public String getName() {
        return "Malum Data Component Type Tags";
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

        tag(MalumTags.DataComponentTags.SPIRIT_INFUSION_BLACKLIST)
                .add(DataComponents.DAMAGE)
                .add(DataComponents.ATTRIBUTE_MODIFIERS);
    }
}