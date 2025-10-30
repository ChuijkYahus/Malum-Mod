package com.sammy.malum.client.screen.codex.screens.progression;

import com.mojang.blaze3d.vertex.*;
import com.sammy.malum.client.screen.codex.*;
import com.sammy.malum.client.screen.codex.handlers.*;
import com.sammy.malum.client.screen.codex.helper.*;
import com.sammy.malum.client.screen.codex.objects.*;
import com.sammy.malum.client.screen.codex.screens.*;
import com.sammy.malum.core.systems.events.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import net.minecraft.resources.*;
import net.minecraft.sounds.*;
import net.minecraft.util.*;
import net.minecraft.world.phys.*;
import net.neoforged.neoforge.common.*;
import org.jetbrains.annotations.*;
import org.lwjgl.opengl.*;

import java.util.*;

import static com.sammy.malum.MalumMod.*;
import static com.sammy.malum.client.screen.codex.helper.CodexRenderHelper.*;
import static org.lwjgl.opengl.GL11C.*;

public abstract class AbstractProgressionCodexScreen extends AbstractMalumCodexScreen implements PlacedEntryAcceptor {

    public static final ResourceLocation FRAME_TEXTURE = malumPath("textures/gui/book/frame.png");
    public static final ResourceLocation FRAME_FADE_TEXTURE = malumPath("textures/gui/book/frame_fade.png");

    public static final int BOOK_WIDTH = 378;
    public static final int BOOK_HEIGHT = 250;
    public static final int BOOK_INSIDE_WIDTH = 344;
    public static final int BOOK_INSIDE_HEIGHT = 218;

    protected float xOffset;
    protected float yOffset;
    protected float cachedXOffset;
    protected float cachedYOffset;

    protected boolean ignoreNextMouseInput;

    protected int voidFadeoutTimer;
    protected int voidFadeoutCounter;

    public final EntryObjectHandler progressionObjects = new EntryObjectHandler();
    public final List<PlacedBookEntry> entries = new ArrayList<>();

    protected final int backgroundImageWidth;
    protected final int backgroundImageHeight;

    // Minecraft instance, non nullable
    protected final Minecraft minecraft = Minecraft.getInstance();

    protected AbstractProgressionCodexScreen(Holder<SoundEvent> sweetenerSound, int backgroundImageWidth, int backgroundImageHeight) {
        super(Component.empty(), sweetenerSound);
        this.backgroundImageWidth = backgroundImageWidth;
        this.backgroundImageHeight = backgroundImageHeight;

        setupEntries();
        NeoForge.EVENT_BUS.post(new SetupMalumCodexEntriesEvent(this));
        setupObjects();
        faceOrigin();
    }

    @Override
    public void init() {
        super.init();
    }

    public abstract void renderBackground(PoseStack poseStack);

    public abstract void setupEntries();


    @Override
    public List<PlacedBookEntry> getEntries() {
        return entries;
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        int guiLeft = getGuiLeft();
        int guiTop = getGuiTop();
        PoseStack poseStack = guiGraphics.pose();

        renderBackground(poseStack);
        GL11.glEnable(GL_SCISSOR_TEST);
        constrictEntryRendering();

        float objectX = guiLeft + BOOK_INSIDE_WIDTH / 2f + xOffset;
        float objectY = guiTop + BOOK_INSIDE_HEIGHT / 2f + yOffset;
        progressionObjects.renderObjects(this, guiGraphics, objectX, objectY, mouseX, mouseY, partialTicks);
        GL11.glDisable(GL_SCISSOR_TEST);

        renderTexture(FRAME_FADE_TEXTURE, poseStack, guiLeft, guiTop, 0, 0, BOOK_WIDTH, BOOK_HEIGHT);
        if (voidFadeoutTimer > 0) {
            CodexRenderHelper.renderTransitionFade(this, poseStack);
        }
        renderTexture(FRAME_TEXTURE, poseStack, guiLeft, guiTop, 400, 0, 0, BOOK_WIDTH, BOOK_HEIGHT);
        progressionObjects.renderObjectsLate(this, guiGraphics, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (ignoreNextMouseInput) {
            ignoreNextMouseInput = false;
            return super.mouseReleased(mouseX, mouseY, button);
        }
        if (xOffset != cachedXOffset || yOffset != cachedYOffset) {
            return super.mouseReleased(mouseX, mouseY, button);
        }
        progressionObjects.click(this, mouseX, mouseY);
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        xOffset += (float) dragX;
        yOffset += (float) dragY;
        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        cachedXOffset = xOffset;
        cachedYOffset = yOffset;
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void onClose() {
        super.onClose();
        playSweetenedSound(MalumSoundEvents.ARCANA_CODEX_CLOSE, 0.75f);
    }

    @Override
    public void tick() {
        progressionObjects.tick(this);
        if (voidFadeoutTimer > 0) {
            voidFadeoutTimer--;
        }
        super.tick();
    }

    @Override
    public boolean isHovering(double mouseX, double mouseY, float posX, float posY, int width, int height) {
        if (!isInView(mouseX, mouseY)) {
            return false;
        }
        return super.isHovering(mouseX, mouseY, posX, posY, width, height);
    }

    public void correctOOBB() {
        if (progressionObjects.hasVisibleObject(this)) {
            return;
        }
        var offsets = clampOffsets(1f, 0.1f, 0.8f);
        if (offsets.x != xOffset || offsets.y != yOffset) {
            faceOrigin();
        }
    }

    public void setupObjects() {
        var window = minecraft.getWindow();
        this.width = window.getGuiScaledWidth();
        this.height = window.getGuiScaledHeight();
        progressionObjects.setupEntryObjects(this);
    }

    public void faceOrigin() {
        faceObject(progressionObjects.getOriginObject());
    }

    public void faceObject(BookObject<?> object) {
        var window = minecraft.getWindow();
        this.width = window.getGuiScaledWidth();
        this.height = window.getGuiScaledHeight();
        xOffset = -object.posX;
        yOffset = -object.posY;
    }

    public void renderBackground(PoseStack poseStack, ResourceLocation texture, float xModifier, float yModifier) {
        var offsets = clampOffsets(0.8f, 0f, 1f);
        float xOffset = offsets.x;
        float yOffset = offsets.y;
        int insideLeft = getInsideLeft();
        int insideTop = getInsideTop();
        float uOffset = (backgroundImageWidth/12f) - xOffset * xModifier;
        float vOffset = (backgroundImageHeight - BOOK_INSIDE_HEIGHT) - yOffset * yModifier;
        renderTexture(texture, poseStack, insideLeft, insideTop, uOffset, vOffset, BOOK_INSIDE_WIDTH, BOOK_INSIDE_HEIGHT, backgroundImageWidth / 2, backgroundImageHeight / 2);
    }

    public Vec2 clampOffsets(float horizontalClamp, float bottomClamp, float topClamp) {
        float xOffset = this.xOffset;
        float xMin = -backgroundImageWidth * horizontalClamp;
        float xMax = backgroundImageWidth * horizontalClamp;
        if (xOffset < xMin || xOffset > xMax) {
            xOffset = Mth.clamp(xOffset, xMin, xMax);
        }
        float yOffset = this.yOffset;
        float yMin = -backgroundImageHeight * bottomClamp;
        float yMax = backgroundImageHeight * topClamp;
        if (yOffset < yMin || yOffset > yMax) {
            yOffset = Mth.clamp(yOffset, yMin, yMax);
        }
        return new Vec2(xOffset, yOffset);
    }

    public boolean isInView(double x, double y) {
        return x >= getInsideLeft()
                && y >= getInsideTop()
                && x <= (getInsideLeft() + BOOK_INSIDE_WIDTH)
                && y <= (getInsideTop() + BOOK_INSIDE_HEIGHT);
    }

    public void constrictEntryRendering() {
        int scale = (int) getMinecraft().getWindow().getGuiScale();
        GL11.glScissor(
                getInsideLeft() * scale,
                getMinecraft().getWindow().getHeight() - (getInsideTop() + BOOK_INSIDE_HEIGHT) * scale,
                BOOK_INSIDE_WIDTH * scale,
                BOOK_INSIDE_HEIGHT * scale);
    }

    public int getInsideLeft() {
        return getGuiLeft() + 17;
    }

    public int getInsideTop() {
        return getGuiTop() + 14;
    }

    public int getGuiLeft() {
        return (width - BOOK_WIDTH) / 2;
    }

    public int getGuiTop() {
        return (height - BOOK_HEIGHT) / 2;
    }

    public float getVoidFadeoutDelta() {
        return (float) voidFadeoutTimer / getVoidFadeoutDuration();
    }

    public int getVoidFadeoutDuration() {
        return 80 - Mth.clamp(voidFadeoutCounter - 2, 0, 4) * 10;
    }
}