package com.sammy.malum.client.screen.codex.objects;

import com.sammy.malum.client.screen.codex.*;
import com.sammy.malum.client.screen.codex.display.*;
import com.sammy.malum.client.screen.codex.display.gizmo.DisplayedGizmo;
import com.sammy.malum.client.screen.codex.helper.*;
import com.sammy.malum.client.screen.codex.screens.*;
import com.sammy.malum.client.screen.codex.screens.progression.AbstractProgressionCodexScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

import java.util.*;

public abstract class SelectableEntryObject<T extends AbstractMalumCodexScreen> extends BookObject<T> implements IGizmoHolder {

    public final BookEntry entry;
    public final DisplayedGizmo icon;

    public SelectableEntryObject(BookEntry entry, DisplayedGizmo icon, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.entry = entry;
        this.icon = icon;
    }

    public void additionalSetup(AbstractProgressionCodexScreen screen) {

    }

    @Override
    public String getGizmoTooltipKey() {
        return entry.identifier;
    }

    public List<Component> gatherTooltip(T screen) {
        return new ArrayList<>(List.of(
                CodexTextHelper.convertToComponent(entry.translationKey()),
                CodexTextHelper.convertToComponent(entry.descriptionTranslationKey())));
    }

    @Override
    public boolean click(T screen, double mouseX, double mouseY) {
        if (entry.hasContents()) {
            CodexEntryScreen.openScreen(entry);
            return true;
        }
        return false;
    }
}