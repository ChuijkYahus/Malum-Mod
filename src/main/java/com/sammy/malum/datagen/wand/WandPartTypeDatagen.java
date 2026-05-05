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
        add(WandPartGroup.CORE, "short", 0, 2);
        add(WandPartGroup.CORE, "balanced", 1, 4);
        add(WandPartGroup.CORE, "long", 2, 6);

        add(WandPartGroup.HEAD, "cap", 0, 3);
        add(WandPartGroup.HEAD, "orb", 1, 6);
        add(WandPartGroup.HEAD, "beacon", 2, 9);

        add(WandPartGroup.BASE, "simple", 0, 1);
        add(WandPartGroup.BAUBLE, "simple", 0, 1);
        add(WandPartGroup.ORNAMENT, "simple", 0, 1);

    }

    public void add(WandPartGroup group, String name) {
        add(group, name, 0, 1);
    }

    public void add(WandPartGroup group, String name, int tier) {
        add(group, name, tier, 1);
    }

    public void add(WandPartGroup group, String name, int tier, int cost) {
        var id = MalumMod.malumPath(group.getIdForPart(name));
        unconditional(id, new WandPartType(group, id, tier, cost));
    }
}