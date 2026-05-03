package com.sammy.malum.datagen.wand;

import com.sammy.malum.MalumMod;
import com.sammy.malum.common.data.custom.wand_parts.WandPartType;
import com.sammy.malum.common.data.custom.wand_parts.WandPartType.WandPartGroup;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.server.packs.PackType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.JsonCodecProvider;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.data.PackOutput.Target.DATA_PACK;

public final class WandPartTypeDatagen extends JsonCodecProvider<WandPartType> {

    public WandPartTypeDatagen(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, DATA_PACK, "wand/part_types", PackType.SERVER_DATA, WandPartType.DIRECT_CODEC, lookupProvider, MalumMod.MALUM, existingFileHelper);
    }

    @Override
    protected void gather() {
        add(WandPartGroup.CORE, "short", "medium", "long");
        add(WandPartGroup.HEAD, "cap", "orb", "beacon");
        add(WandPartGroup.BASE, "simple");
        add(WandPartGroup.BAUBLE, "loop", "spike");
        add(WandPartGroup.ORNAMENT, "lower", "middle", "upper");
    }

    public void add(WandPartGroup group, String... types) {
        for (String type : types) {
            var id = MalumMod.malumPath(group.getIdForPart(type));
            unconditional(id, new WandPartType(group, id));
        }
    }
}