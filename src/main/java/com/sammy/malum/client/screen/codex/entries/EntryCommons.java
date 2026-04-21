package com.sammy.malum.client.screen.codex.entries;

import com.sammy.malum.client.screen.codex.display.CodexIconRenderer;
import com.sammy.malum.client.screen.codex.display.DisplayedGizmo;
import com.sammy.malum.client.screen.codex.display.DisplayedGizmo.DisplayedTexture;
import net.minecraft.resources.ResourceLocation;

import static com.sammy.malum.MalumMod.malumPath;
import static com.sammy.malum.client.screen.codex.display.DisplayedGizmo.*;

public class EntryCommons {

    public static final ResourceLocation SOUL_SHARD_TEXTURE = malumPath("textures/gui/book/icons/soul_shard.png");
    public static final ResourceLocation OVERWORLD_TEXTURE = malumPath("textures/gui/book/icons/overworld.png");

    public static final DisplayedGizmo SOUL_SHARD = texture(CodexIconRenderer.create(SOUL_SHARD_TEXTURE, 16, 16)).addTitle("icon.soul_shard");
    public static final DisplayedGizmo OVERWORLD = texture(CodexIconRenderer.create(OVERWORLD_TEXTURE, 16, 16)).addTitle("icon.overworld");
}
