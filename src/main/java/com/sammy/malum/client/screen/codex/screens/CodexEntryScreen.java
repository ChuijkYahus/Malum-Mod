package com.sammy.malum.client.screen.codex.screens;

import com.sammy.malum.*;
import com.sammy.malum.client.screen.codex.*;
import com.sammy.malum.client.screen.codex.handlers.*;
import com.sammy.malum.client.screen.codex.objects.*;
import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.client.screen.codex.screens.progression.*;
import com.sammy.malum.config.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.network.chat.*;
import net.minecraft.resources.*;
import net.minecraft.util.*;

import javax.annotation.*;

import static com.sammy.malum.client.screen.codex.helper.CodexRenderHelper.*;

public class CodexEntryScreen extends AbstractMalumCodexScreen {

    public static final ResourceLocation BOOK_TEXTURE = MalumMod.malumPath("textures/gui/book/entry.png");
    public static final ResourceLocation ITEM_SOCKET = MalumMod.malumPath("textures/gui/book/entry_elements/item_sockets.png");

    protected static final int BOOK_WIDTH = 312;
    protected static final int BOOK_HEIGHT = 206;

    public static float textJump;

    @Nullable
    protected final AbstractMalumCodexScreen parentScreen;

    protected final BookEntry openEntry;
    protected int openPageIndex;

    protected final BookObjectHandler<CodexEntryScreen> entryObjects = new BookObjectHandler<>();

    // Minecraft instance, non nullable
    protected final Minecraft minecraft = Minecraft.getInstance();

    public CodexEntryScreen(BookEntry openEntry) {
        this(null, openEntry);
    }

    public CodexEntryScreen(@Nullable AbstractMalumCodexScreen parentScreen, BookEntry openEntry) {
        super(Component.empty(), openEntry.isVoid ? MalumSoundEvents.ARCANA_SWEETENER_EVIL : MalumSoundEvents.ARCANA_SWEETENER_NORMAL);
        this.parentScreen = parentScreen;
        this.openEntry = openEntry;
        if (parentScreen != null) {
            setVoidTouched(parentScreen.isVoidTouched);
        }
        int left = -21;
        int right = BOOK_WIDTH - 15;
        entryObjects.add(new ArrowObject(left, 150, false));
        entryObjects.add(new ArrowObject(right, 150, true));

        var references = openEntry.references;
        if (references != null) {
            int counter = 0;
            for (EntryReference reference : references) {
                if (reference.entry.shouldShow()) {
                    entryObjects.add(new LinkedEntryObject(right, 15 + counter * 30, true, reference));
                    counter++;
                }
            }
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        var poseStack = guiGraphics.pose();
        int guiLeft = getGuiLeft();
        int guiTop = getGuiTop();
        renderTexture(BOOK_TEXTURE, poseStack, guiLeft, guiTop, 0, 0, BOOK_WIDTH, BOOK_HEIGHT);

        int pageTop = guiTop + 11;
        if (!openEntry.pages.isEmpty()) {
            int openPages = openPageIndex * 2;
            for (int i = openPages; i < openPages + 2; i++) {
                if (i < openEntry.pages.size()) {
                    var page = openEntry.pages.get(i);
                    final boolean isRightSide = i % 2 == 1;
                    int backgroundLeft = guiLeft + (isRightSide ? 165 : 13);
                    final ResourceLocation background = page.getBackground(isRightSide);
                    if (background != null) {
                        renderTexture(background, poseStack, backgroundLeft, pageTop, 0, 0, 134, 172);
                    }
                }
            }
        }
        entryObjects.renderObjects(this, guiGraphics, guiLeft, guiTop, mouseX, mouseY, partialTicks);
        if (!openEntry.pages.isEmpty()) {
            int openPages = openPageIndex * 2;
            for (int i = openPages; i < openPages + 2; i++) {
                if (i < openEntry.pages.size()) {
                    var page = openEntry.pages.get(i);
                    boolean isRightSide = i % 2 == 1;
                    int pageLeft = guiLeft + (isRightSide ? 161 : 9);
                    boolean isRepeat = i % 2 != 0 && page.getClass().equals(openEntry.pages.get(i - 1).getClass());
                    page.render(this, guiGraphics, pageLeft, pageTop, mouseX, mouseY, partialTicks, isRepeat);
                    lateRendering.add(() -> page.renderLate(this, guiGraphics, pageLeft, pageTop, mouseX, mouseY, partialTicks, isRepeat));
                }
            }
        }
        entryObjects.renderObjectsLate(this, guiGraphics, mouseX, mouseY, partialTicks);
        doLateRendering();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        entryObjects.click(this, mouseX, mouseY);

        int guiLeft = getGuiLeft();
        int guiTop = getGuiTop();

        if (!openEntry.pages.isEmpty()) {
            int openPages = openPageIndex * 2;
            for (int i = openPages; i < openPages + 2; i++) {
                if (i < openEntry.pages.size()) {
                    var page = openEntry.pages.get(i);
                    final boolean isRightSide = i % 2 == 1;
                    int pageLeft = guiLeft + (isRightSide ? 161 : 9);
                    int pageTop = guiTop + 8;
                    if (isHovering(mouseX, mouseY, pageLeft, pageTop, 142, 172)) {
                        double relativeX = Mth.clamp(mouseX - guiLeft, guiLeft, guiLeft + 142);
                        double relativeY = Mth.clamp(mouseY - guiTop, guiTop, guiTop + 172);
                        page.click(this, pageLeft, pageTop, mouseX, mouseY, relativeX, relativeY);
                    }
                }
            }
        }
        textJump += 1f;

        return false;
    }

    @Override
    public void tick() {
        super.tick();
        textJump = Math.max(textJump - 0.1f, 0);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        if (ClientConfig.SCROLL_DIRECTION.getConfigValue()) {
            scrollY = -scrollY;
        }
        if (scrollY > 0) {
            nextPage();
        } else {
            previousPage(false);
        }
        return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (minecraft.options.keyRight.matches(keyCode, scanCode)) {
            nextPage();
            return true;
        } else if (minecraft.options.keyLeft.matches(keyCode, scanCode)) {
            previousPage(false);
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void onClose() {
        close(false);
    }

    public boolean hasNextPage() {
        return openPageIndex < openEntry.pages.size() / 2f - 1;
    }

    public void nextPage() {
        if (hasNextPage()) {
            openPageIndex += 1;
            playPageFlipSound(MalumSoundEvents.ARCANA_PAGE_FLIP, getSweetenerPitch());
        }
    }

    public void previousPage(boolean ignore) {
        if (openPageIndex > 0) {
            openPageIndex -= 1;
            playPageFlipSound(MalumSoundEvents.ARCANA_PAGE_FLIP, getSweetenerPitch());
        } else {
            close(ignore);
        }
    }

    public void close(boolean ignoreNextInput) {
        if (parentScreen == null) {
            ProgressionScreenHolder.getAppropriateCodexScreen().reopenCodexFromEntryScreen(isVoidTouched, ignoreNextInput);
        }
        else {
            Minecraft.getInstance().setScreen(parentScreen);
        }
        playSweetenedSound(MalumSoundEvents.ARCANA_ENTRY_CLOSE, 0.85f);
    }

    public static void openScreen(BookEntry bookEntry) {
        var minecraft = Minecraft.getInstance();
        var openScreen = minecraft.screen;
        var screen = openScreen instanceof CodexEntryScreen openCodexEntryScreen ? new CodexEntryScreen(openCodexEntryScreen, bookEntry) : new CodexEntryScreen(bookEntry);
        screen.playSweetenedSound(MalumSoundEvents.ARCANA_ENTRY_OPEN, 1.15f);
        minecraft.setScreen(screen);
    }

    public float getSweetenerPitch() {
        return 1 + (float) openPageIndex / openEntry.pages.size();
    }

    public int getGuiLeft() {
        return (width - BOOK_WIDTH) / 2;
    }

    public int getGuiTop() {
        return (height - BOOK_HEIGHT) / 2;
    }
}