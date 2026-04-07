package com.sammy.malum.client.screen.codex.handlers;

import com.sammy.malum.client.screen.codex.objects.*;
import com.sammy.malum.client.screen.codex.screens.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;

import java.util.*;
import java.util.function.Predicate;

public class BookObjectHandler<T extends AbstractMalumCodexScreen> {

    protected final ArrayList<BookObject<T>> objects = new ArrayList<>();
    
    public BookObjectHandler() {
    }

    public void add(BookObject<T> object) {
        objects.add(object);
    }

    public void addAll(Collection<? extends BookObject<T>> objects) {
        this.objects.addAll(objects);
    }

    public ArrayList<BookObject<T>> getObjects() {
        return objects;
    }

    public BookObject<T> get(int index) {
        return objects.get(index);
    }

    public BookObject<T> getFirst() {
        return objects.getFirst();
    }

    public boolean isEmpty() {
        return objects.isEmpty();
    }

    public void remove(BookObject<T> object) {
        objects.remove(object);
    }

    public void clear() {
        objects.clear();
    }

    public void tick(T screen) {
        var minecraft = Minecraft.getInstance();
        var mouseHandler = minecraft.mouseHandler;
        var window = minecraft.getWindow();
        double x = mouseHandler.xpos() * (double) window.getGuiScaledWidth() / (double) window.getScreenWidth();
        double y = mouseHandler.ypos() * (double) window.getGuiScaledHeight() / (double) window.getScreenHeight();
        tick(screen, x, y);
    }

    public void tick(T screen, double mouseX, double mouseY) {
        for (BookObject<T> object : objects) {
            if (object.isValid(screen)) {
                object.tick(screen, mouseX, mouseY);
                object.updateValues(screen, mouseX, mouseY);
            }
        }
    }

    public boolean click(T screen, double mouseX, double mouseY) {
        return interact(screen, o -> o.tryClick(screen, mouseX, mouseY));
    }

    public boolean release(T screen, double mouseX, double mouseY) {
        return interact(screen, o -> o.tryRelease(screen, mouseX, mouseY));
    }

    public boolean interact(T screen, Predicate<BookObject<T>> interaction) {
        ArrayList<BookObject<T>> sorted = new ArrayList<>();
        for (BookObject<T> object : objects) {
            if (object.isValid(screen)) {
                if (object.hasPriority(screen)) {
                    sorted.addFirst(object);
                    continue;
                }
                sorted.add(object);
            }
        }
        for (BookObject<T> object : sorted) {
            if (object.isValid(screen)) {
                if (interaction.test(object)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasVisibleObject(T screen) {
        for (BookObject<T> object : objects) {
            if (object.isInView(screen)) {
                return true;
            }
        }
        return false;
    }

    public void renderObjects(T screen, GuiGraphics guiGraphics, float left, float top, int mouseX, int mouseY, float partialTicks) {
        for (int i = objects.size() - 1; i >= 0; i--) {
            BookObject<T> object = objects.get(i);
            if (!object.isValid(screen)) {
                continue;
            }
            object.xOffset = left;
            object.yOffset = top;
            if (!object.isInSubspace && !object.isInView(screen)) {
                object.isHoveredOver = false;
                continue;
            }
            object.isHoveredOver = object.isHovering(screen, mouseX, mouseY);
            renderObject(screen, guiGraphics, object, mouseX, mouseY, partialTicks);
        }
    }

    public void renderObject(T screen, GuiGraphics guiGraphics, BookObject<T> object, int mouseX, int mouseY, float partialTicks) {
        var poseStack = guiGraphics.pose();
        poseStack.pushPose();
        object.applyTransforms(screen, poseStack, mouseX, mouseY, partialTicks);
        object.render(screen, guiGraphics, mouseX, mouseY, partialTicks);
        poseStack.popPose();
    }

    public void renderObjectsLate(T screen, GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        boolean priorityOnly = false;
        for (BookObject<T> object : objects) {
            if (object.isValid(screen)) {
                if (object.hasPriority(screen)) {
                    priorityOnly = true;
                    break;
                }
            }
        }
        for (int i = objects.size() - 1; i >= 0; i--) {
            BookObject<T> object = objects.get(i);
            if (priorityOnly && !object.hasPriority(screen)) {
                continue;
            }
            if (object.isValid(screen)) {
                object.renderLate(screen, guiGraphics, mouseX, mouseY, partialTicks);
            }
        }
    }
}