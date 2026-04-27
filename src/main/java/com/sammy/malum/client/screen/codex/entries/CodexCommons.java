package com.sammy.malum.client.screen.codex.entries;

import com.sammy.malum.client.screen.codex.display.CodexIconRenderer;
import com.sammy.malum.client.screen.codex.display.DisplayedGizmo;
import com.sammy.malum.registry.common.MalumContent.*;
import net.minecraft.resources.ResourceLocation;

import static com.sammy.malum.MalumMod.malumPath;
import static com.sammy.malum.client.screen.codex.display.DisplayedGizmo.*;

public class CodexCommons {

    public static final ResourceLocation SOUL_SHARD_TEXTURE = malumPath("textures/gui/book/icons/soul_shard.png");
    public static final ResourceLocation OVERWORLD_TEXTURE = malumPath("textures/gui/book/icons/overworld.png");

    public static final DisplayedGizmo SOUL_SHARD = texture(CodexIconRenderer.create(SOUL_SHARD_TEXTURE, 16, 16));
    public static final DisplayedGizmo OVERWORLD = texture(CodexIconRenderer.create(OVERWORLD_TEXTURE, 16, 16));

    public static final DisplayedGizmo RAW_SOULSTONE = item(Materials.RAW_SOULSTONE);
    public static final DisplayedGizmo REFINED_SOULSTONE = item(Materials.REFINED_SOULSTONE);

    public static final DisplayedGizmo SOULSTONE_BUD = item(Materials.SOULSTONE_BUD);
    public static final DisplayedGizmo REALIZED_SOULSTONE_BUD = item(Materials.REALIZED_SOULSTONE_BUD);

    public static final DisplayedGizmo CRUDE_SCYTHE = item(Gear.CRUDE_SCYTHE);
    public static final DisplayedGizmo SPIRIT_ALTAR = item(Sorcery.SPIRIT_ALTAR);

}
