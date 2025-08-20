package com.sammy.malum.client.screen.codex.handlers;

import com.sammy.malum.client.screen.codex.objects.*;
import com.sammy.malum.client.screen.codex.screens.*;
import net.minecraft.client.gui.*;

import java.util.*;

public class BookObjectHandler<T extends AbstractMalumCodexScreen> {

    protected final ArrayList<BookObject<T>> objects = new ArrayList<>();


    public BookObjectHandler() {
    }

    public void add(BookObject<T> object) {
        objects.add(object);
    }

    public void addAll(Collection<BookObject<T>> objects) {
        this.objects.addAll(objects);
    }

    public BookObject<T> get(int index) {
        return objects.get(index);
    }

    public BookObject<T> getFirst() {
        return objects.getFirst();
    }

    public void remove(BookObject<T> object) {
        objects.remove(object);
    }



    public void click(T screen, double mouseX, double mouseY) {
        for (BookObject<T> object : objects) {
            if (object.isValid(screen) && object.isHoveredOver) {
                object.click(screen, mouseX, mouseY);
                break;
            }
        }
    }

    public void renderObjects(T screen, GuiGraphics guiGraphics, float left, float top, int mouseX, int mouseY, float partialTicks) {
        for (int i = objects.size() - 1; i >= 0; i--) {
            BookObject<T> object = objects.get(i);
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
        for (int i = objects.size() - 1; i >= 0; i--) {
            BookObject<T> object = objects.get(i);
            if (object.isValid(screen)) {
                object.renderLate(screen, guiGraphics, mouseX, mouseY, partialTicks);
            }
        }
    }
}