package com.sammy.malum.client.screen.codex.entries;

import com.sammy.malum.client.screen.codex.display.CodexIconRenderer;
import com.sammy.malum.client.screen.codex.display.gizmo.DisplayedGizmo;
import com.sammy.malum.client.screen.codex.display.gizmo.DisplayedItem;
import com.sammy.malum.client.screen.codex.display.gizmo.DisplayedTexture;
import com.sammy.malum.registry.common.MalumContent.*;
import net.minecraft.resources.ResourceLocation;

import static com.sammy.malum.MalumMod.malumPath;

public class CodexCommons {

    public static final ResourceLocation SOUL_SHARD_TEXTURE = malumPath("textures/gui/book/icons/soul_shard.png");
    public static final ResourceLocation OVERWORLD_TEXTURE = malumPath("textures/gui/book/icons/overworld.png");
    public static final ResourceLocation CORE_KEEPING_TEXTURE = malumPath("textures/gui/book/icons/core_keeping.png");

    public static final DisplayedGizmo SOUL_SHARD = DisplayedTexture.texture(CodexIconRenderer.create(SOUL_SHARD_TEXTURE, 16, 16));
    public static final DisplayedGizmo OVERWORLD = DisplayedTexture.texture(CodexIconRenderer.create(OVERWORLD_TEXTURE, 16, 16));
    public static final DisplayedGizmo CORE_KEEPING = DisplayedTexture.texture(CodexIconRenderer.create(CORE_KEEPING_TEXTURE, 16, 16));

    public static final DisplayedGizmo RAW_SOULSTONE = DisplayedItem.item(Materials.RAW_SOULSTONE);
    public static final DisplayedGizmo REFINED_SOULSTONE = DisplayedItem.item(Materials.REFINED_SOULSTONE);

    public static final DisplayedGizmo SOULSTONE_BUD = DisplayedItem.item(Materials.SOULSTONE_BUD);
    public static final DisplayedGizmo REALIZED_SOULSTONE_BUD = DisplayedItem.item(Materials.REALIZED_SOULSTONE_BUD);

    public static final DisplayedGizmo CRUDE_SCYTHE = DisplayedItem.item(Gear.CRUDE_SCYTHE);
    public static final DisplayedGizmo SPIRIT_ALTAR = DisplayedItem.item(Sorcery.SPIRIT_ALTAR);

    public static final DisplayedGizmo HEX_ASH = DisplayedItem.item(Materials.HEX_ASH);
    public static final DisplayedGizmo LIVING_FLESH = DisplayedItem.item(Materials.LIVING_FLESH);
    public static final DisplayedGizmo ALCHEMICAL_CALX = DisplayedItem.item(Materials.ALCHEMICAL_CALX);

    public static final DisplayedGizmo GRIM_TALC = DisplayedItem.item(Materials.GRIM_TALC);
    public static final DisplayedGizmo ROTTING_ESSENCE = DisplayedItem.item(Materials.ROTTING_ESSENCE);
    public static final DisplayedGizmo EERIE_WEAVE = DisplayedItem.item(Materials.EERIE_WEAVE);
    public static final DisplayedGizmo WARP_FLUX = DisplayedItem.item(Materials.WARP_FLUX);
    public static final DisplayedGizmo WIND_NUCLEUS = DisplayedItem.item(Materials.WIND_NUCLEUS);
    public static final DisplayedGizmo PYRE_NUCLEUS = DisplayedItem.item(Materials.PYRE_NUCLEUS);



    public static final DisplayedGizmo SACRED_SPIRIT = DisplayedItem.item(Spirits.SACRED_SPIRIT);
    public static final DisplayedGizmo WICKED_SPIRIT = DisplayedItem.item(Spirits.WICKED_SPIRIT);
    public static final DisplayedGizmo ARCANE_SPIRIT = DisplayedItem.item(Spirits.ARCANE_SPIRIT);
    public static final DisplayedGizmo ELDRITCH_SPIRIT = DisplayedItem.item(Spirits.ELDRITCH_SPIRIT);
    public static final DisplayedGizmo AERIAL_SPIRIT = DisplayedItem.item(Spirits.AERIAL_SPIRIT);
    public static final DisplayedGizmo AQUEOUS_SPIRIT = DisplayedItem.item(Spirits.AQUEOUS_SPIRIT);
    public static final DisplayedGizmo EARTHEN_SPIRIT = DisplayedItem.item(Spirits.EARTHEN_SPIRIT);
    public static final DisplayedGizmo INFERNAL_SPIRIT = DisplayedItem.item(Spirits.INFERNAL_SPIRIT);

}
