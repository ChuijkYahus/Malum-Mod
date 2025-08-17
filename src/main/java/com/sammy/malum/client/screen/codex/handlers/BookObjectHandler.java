package com.sammy.malum.client.screen.codex.handlers;

import com.sammy.malum.client.screen.codex.objects.*;
import com.sammy.malum.client.screen.codex.screens.*;
import net.minecraft.client.gui.*;

import java.util.*;

public class BookObjectHandler<T extends AbstractMalumScreen> extends ArrayList<BookObject<T>> {

    public BookObjectHandler() {
    }

    public void click(T screen, double mouseX, double mouseY) {
        for (BookObject<T> object : this) {
            if (object.isValid(screen) && object.isHoveredOver) {
                object.click(screen, mouseX, mouseY);
                break;
            }
        }
    }

    public void renderObjects(T screen, GuiGraphics guiGraphics, float left, float top, int mouseX, int mouseY, float partialTicks) {
        for (int i = size() - 1; i >= 0; i--) {
            BookObject<T> object = get(i);
            if (!object.isValid(screen)) {
                continue;
            }
            object.xOffset = left;
            object.yOffset = top;
            if (!object.isInView(screen)) {
                continue;
            }
            object.isHoveredOver = object.isHovering(screen, left, top, mouseX, mouseY);
            renderObject(screen, guiGraphics, object, mouseX, mouseY, partialTicks);
        }
    }

    public void renderObject(T screen, GuiGraphics guiGraphics, BookObject<T> object, int mouseX, int mouseY, float partialTicks) {
        object.render(screen, guiGraphics, mouseX, mouseY, partialTicks);
    }

    public void renderObjectsLate(T screen, GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        for (int i = size() - 1; i >= 0; i--) {
            BookObject<T> object = get(i);
            if (object.isValid(screen)) {
                object.renderLate(screen, guiGraphics, mouseX, mouseY, partialTicks);
            }
        }
    }
}