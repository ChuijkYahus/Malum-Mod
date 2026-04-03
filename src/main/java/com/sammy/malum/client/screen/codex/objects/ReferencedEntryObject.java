package com.sammy.malum.client.screen.codex.objects;

import com.mojang.blaze3d.vertex.*;
import com.sammy.malum.*;
import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.client.screen.codex.screens.*;
import net.minecraft.client.gui.*;
import net.minecraft.resources.*;

import static com.sammy.malum.client.screen.codex.helper.CodexRenderHelper.*;

public class ReferencedEntryObject extends AbstractSelectableEntryObject<CodexEntryScreen> {

    public static final ResourceLocation LINK = MalumMod.malumPath("textures/gui/book/entry_elements/entry_link.png");

    public final boolean flipped;

    public ReferencedEntryObject(int posX, int posY, boolean flipped, EntryReference entryReference) {
        super(entryReference, posY, 36, 26, posX);
        this.flipped = flipped;
    }

    @Override
    public void render(CodexEntryScreen screen, GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        int entryX = getOffsetXPosition();
        int entryY = getOffsetYPosition();
        var poseStack = guiGraphics.pose();
        renderTexture(LINK, poseStack, entryX, entryY, 0, flipped ? 26 : 0, width, height, 36, 52);
        guiGraphics.renderItem(iconStack, entryX + 5, entryY + 5);
    }
}
