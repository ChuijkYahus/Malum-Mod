package com.sammy.malum.client.screen.codex.objects;

import com.sammy.malum.client.screen.codex.BookEntry;
import com.sammy.malum.client.screen.codex.helper.*;
import com.sammy.malum.client.screen.codex.pages.EntryReference;
import com.sammy.malum.client.screen.codex.screens.CodexEntryScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractSelectableEntryObject extends BookObject<CodexEntryScreen> {

    public final EntryReference entryReference;

    public AbstractSelectableEntryObject(int posX, int posY, int width, int height, EntryReference entryReference) {
        super(posX, posY, width, height);
        this.entryReference = entryReference;
    }

    @Override
    public void renderLate(CodexEntryScreen screen, GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if (isHoveredOver && entryReference.entry.hasTooltip()) {
            var entry = entryReference.entry;
            final List<Component> list = Arrays.asList(
                CodexTextHelper.convertToComponent(entry.translationKey(), entry.titleStyle),
                CodexTextHelper.convertToComponent(entry.descriptionTranslationKey(), entry.subtitleStyle));
            guiGraphics.renderComponentTooltip(Minecraft.getInstance().font, list, mouseX, mouseY);
        }
    }

    @Override
    public boolean click(CodexEntryScreen screen, double mouseX, double mouseY) {
        if (entryReference.entry.hasContents()) {
            CodexEntryScreen.openScreen(entryReference.entry);
            return true;
        }
        return false;
    }
}
