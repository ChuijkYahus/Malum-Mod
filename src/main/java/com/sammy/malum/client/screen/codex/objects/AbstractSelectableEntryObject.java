package com.sammy.malum.client.screen.codex.objects;

import com.sammy.malum.client.screen.codex.*;
import com.sammy.malum.client.screen.codex.display.*;
import com.sammy.malum.client.screen.codex.display.gizmo.DisplayedGizmo;
import com.sammy.malum.client.screen.codex.helper.*;
import com.sammy.malum.client.screen.codex.pages.EntryReference;
import com.sammy.malum.client.screen.codex.screens.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

import java.util.*;
import java.util.function.*;

public abstract class AbstractSelectableEntryObject<T extends AbstractMalumCodexScreen> extends BookObject<T> implements IGizmoHolder {

    public final BookEntry entry;
    public DisplayedGizmo displayedGizmo;
    public Predicate<T> isValid = t -> true;

    public AbstractSelectableEntryObject(BookEntry entry, int posX, int posY, int width, int height) {
        super(posX, posY, width, height);
        this.entry = entry;
    }

    public AbstractSelectableEntryObject(EntryReference reference, int posY, int width, int height, int posX) {
        super(posX, posY, width, height);
        this.entry = reference.entry;
        this.displayedGizmo = reference.gizmo;
    }

    @Override
    public String getGizmoId() {
        return entry.identifier;
    }

    @Override
    public boolean isValid(T screen) {
        return isValid.test(screen) && entry.shouldShow();
    }

    @Override
    public void renderLate(T screen, GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if (isHoveredOver && entry.hasTooltip()) {
            screen.renderLater(() -> {
                var tooltip = gatherTooltip(screen);
                guiGraphics.renderComponentTooltip(Minecraft.getInstance().font, tooltip, mouseX, mouseY);
            });
        }
    }

    public List<Component> gatherTooltip(T screen) {
        return new ArrayList<>(List.of(
                CodexTextHelper.convertToComponent(entry.translationKey()).withStyle(entry.titleStyle),
                CodexTextHelper.convertToComponent(entry.descriptionTranslationKey()).withStyle(entry.subtitleStyle)));
    }

    @Override
    public boolean click(T screen, double mouseX, double mouseY) {
        if (entry.hasContents()) {
            CodexEntryScreen.openScreen(entry);
            return true;
        }
        return false;
    }

    public AbstractSelectableEntryObject<T> setIcon(DisplayedGizmo displayedGizmo) {
        this.displayedGizmo = displayedGizmo;
        return this;
    }

    public AbstractSelectableEntryObject<T> setCondition(Predicate<T> isValid) {
        this.isValid = isValid;
        return this;
    }
}