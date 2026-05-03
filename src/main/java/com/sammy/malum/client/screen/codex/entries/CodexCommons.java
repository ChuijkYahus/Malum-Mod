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
    public static final ResourceLocation CORE_KEEPING_TEXTURE = malumPath("textures/gui/book/icons/core_keeping.png");

    public static final DisplayedGizmo SOUL_SHARD = texture(CodexIconRenderer.create(SOUL_SHARD_TEXTURE, 16, 16));
    public static final DisplayedGizmo OVERWORLD = texture(CodexIconRenderer.create(OVERWORLD_TEXTURE, 16, 16));
    public static final DisplayedGizmo CORE_KEEPING = texture(CodexIconRenderer.create(CORE_KEEPING_TEXTURE, 16, 16));

    public static final DisplayedGizmo RAW_SOULSTONE = item(Materials.RAW_SOULSTONE);
    public static final DisplayedGizmo REFINED_SOULSTONE = item(Materials.REFINED_SOULSTONE);

    public static final DisplayedGizmo SOULSTONE_BUD = item(Materials.SOULSTONE_BUD);
    public static final DisplayedGizmo REALIZED_SOULSTONE_BUD = item(Materials.REALIZED_SOULSTONE_BUD);

    public static final DisplayedGizmo CRUDE_SCYTHE = item(Gear.CRUDE_SCYTHE);
    public static final DisplayedGizmo SPIRIT_ALTAR = item(Sorcery.SPIRIT_ALTAR);

    public static final DisplayedGizmo HEX_ASH = item(Materials.HEX_ASH);
    public static final DisplayedGizmo LIVING_FLESH = item(Materials.LIVING_FLESH);
    public static final DisplayedGizmo ALCHEMICAL_CALX = item(Materials.ALCHEMICAL_CALX);

    public static final DisplayedGizmo GRIM_TALC = item(Materials.GRIM_TALC);
    public static final DisplayedGizmo ROTTING_ESSENCE = item(Materials.ROTTING_ESSENCE);
    public static final DisplayedGizmo EERIE_WEAVE = item(Materials.EERIE_WEAVE);
    public static final DisplayedGizmo WARP_FLUX = item(Materials.WARP_FLUX);
    public static final DisplayedGizmo WIND_NUCLEUS = item(Materials.WIND_NUCLEUS);
    public static final DisplayedGizmo PYRE_NUCLEUS = item(Materials.PYRE_NUCLEUS);

}
