package com.sammy.malum.client.screen.codex.objects.progression;

import com.google.common.collect.*;
import com.sammy.malum.client.screen.codex.*;
import com.sammy.malum.client.screen.codex.handlers.*;
import com.sammy.malum.client.screen.codex.pages.text.*;
import com.sammy.malum.client.screen.codex.screens.progression.*;
import com.sammy.malum.core.systems.rite.*;
import net.minecraft.client.gui.*;

import java.util.*;
import java.util.function.*;

import static com.sammy.malum.client.screen.codex.helper.CodexRenderHelper.*;

public class SubspaceEntryObject extends ProgressionEntryObject {

    protected final EntryObjectHandler objects = new EntryObjectHandler();
    protected final List<PlacedBookEntry> entries;

    protected boolean assembledObjects = false;

    protected boolean isActive = false;

    public SubspaceEntryObject(BookEntry entry, int posX, int posY, List<PlacedBookEntry> entries) {
        super(entry, posX, posY);
        this.entries = ImmutableList.copyOf(entries);
    }

    @Override
    public void tick(AbstractProgressionCodexScreen screen, double mouseX, double mouseY) {
        if (isActive) {
            objects.tick(screen, mouseX, mouseY);
        } else {
            super.tick(screen, mouseX, mouseY);
        }
    }

    @Override
    public void renderLate(AbstractProgressionCodexScreen screen, GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if (isActive) {
            objects.renderObjects(screen, guiGraphics, xOffset, yOffset, mouseX, mouseY, partialTicks);
            objects.renderObjectsLate(screen, guiGraphics, mouseX, mouseY, partialTicks);
        }
        else {
            super.renderLate(screen, guiGraphics, mouseX, mouseY, partialTicks);
        }
    }

    @Override
    public boolean tryClick(AbstractProgressionCodexScreen screen, double mouseX, double mouseY) {
        if (!assembledObjects) {
            objects.setupEntryObjects(screen, entries);
            assembledObjects = true;
        }
        if (isActive) {
            boolean success = objects.click(screen, mouseX, mouseY);
            if (success) {
                isActive = false;
                return true;
            }
        }
        return super.tryClick(screen, mouseX, mouseY);
    }

    @Override
    public boolean click(AbstractProgressionCodexScreen screen, double mouseX, double mouseY) {
        if (!isActive) {
            isActive = true;
            return true;
        }
        return super.click(screen, mouseX, mouseY);
    }

    public static class SubspaceWidgetSupplier implements PlacedBookEntry.WidgetSupplier, PlacedEntryAcceptor {

        protected final List<PlacedBookEntry> entries = new ArrayList<>();

        public SubspaceWidgetSupplier() {
        }

        @Override
        public List<PlacedBookEntry> getEntries() {
            return entries;
        }

        @Override
        public ProgressionEntryObject getBookObject(BookEntry entry, int x, int y) {
            return new SubspaceEntryObject(entry, x, y, entries);
        }
    }
}