package com.sammy.malum.client.screen.codex.objects.button;

import com.sammy.malum.MalumMod;
import com.sammy.malum.client.screen.codex.display.CodexOutlineRenderer;
import com.sammy.malum.client.screen.codex.display.DisplayedGizmo;
import com.sammy.malum.client.screen.codex.objects.BookObject;
import com.sammy.malum.client.screen.codex.screens.CodexEntryScreen;
import com.sammy.malum.registry.common.sound.MalumSoundEvents;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import team.lodestar.lodestone.helpers.DataHelper;

import static com.sammy.malum.client.screen.codex.helper.CodexRenderHelper.renderTexture;

public abstract class ButtonObject extends BookObject<CodexEntryScreen> {

    public static final ResourceLocation SELECTION = MalumMod.malumPath("textures/gui/book/entry_elements/page_selection.png");
    public static final ResourceLocation SELECTION_HOVER = MalumMod.malumPath("textures/gui/book/entry_elements/page_selection_hover.png");
    public static final ResourceLocation SELECTION_PRESSED = MalumMod.malumPath("textures/gui/book/entry_elements/page_selection_pressed.png");
    public static final ResourceLocation SELECTION_ACTIVE = MalumMod.malumPath("textures/gui/book/entry_elements/page_selection_active.png");

    public static final ResourceLocation SELECTION_GLOW = MalumMod.malumPath("textures/gui/book/entry_elements/page_selection_glow.png");
    public static final ResourceLocation SELECTION_OUTLINE = MalumMod.malumPath("textures/gui/book/entry_elements/page_selection_outline.png");

    protected final DisplayedGizmo gizmo;
    protected final int buttonIndex;

    protected float oldOutlineVisibility;
    protected float outlineVisibility;

    public ButtonObject(DisplayedGizmo gizmo, int buttonIndex, int posX, int posY) {
        super(posX, posY, 28, 30);
        this.gizmo = gizmo;
        this.buttonIndex = buttonIndex;
    }

    public abstract boolean isSelected();

    @Override
    public void render(CodexEntryScreen screen, GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        int x = getOffsetXPosition();
        int y = getOffsetYPosition();

        y += choose(0, -1, 1, -2);
        var texture = choose(SELECTION, SELECTION_HOVER, SELECTION_PRESSED, SELECTION_ACTIVE);
        var poseStack = guiGraphics.pose();
        CodexOutlineRenderer.create(SELECTION_GLOW, SELECTION_OUTLINE, x-18, y-17)
                .setEffectStrength(oldOutlineVisibility, outlineVisibility, 1f)
                .setDistortion(50f)
                .setOffset(buttonIndex * 600)
                .renderOutline(poseStack);
        renderTexture(texture, poseStack, x, y, 0, 0, width, height);
        if (gizmo != null) {
            gizmo.render(screen, guiGraphics, x + 6, y + 6, mouseX, mouseY);
        }
    }

    @Override
    public void tick(CodexEntryScreen screen, double mouseX, double mouseY) {
        oldOutlineVisibility = outlineVisibility;
        float target = choose(0f, 0.5f, 0.75f, 1.0f);
        outlineVisibility = DataHelper.approach(outlineVisibility, target, 0.125f);
        if (!isSelected()) {
            if (wasHoveredOver != isHoveredOver) {
                var sound = isHoveredOver ? MalumSoundEvents.ARCANA_BUTTON_HOVER : MalumSoundEvents.ARCANA_BUTTON_UNHOVER;
                screen.playSound(sound, 1, 1);
            }
        }
    }

    @Override
    public boolean release(CodexEntryScreen screen, double mouseX, double mouseY) {
        if (!isSelected()) {
            screen.playSound(MalumSoundEvents.ARCANA_BUTTON_CLICK, 0.5f, 1);
        }
        return super.release(screen, mouseX, mouseY);
    }

    @Override
    public boolean click(CodexEntryScreen screen, double mouseX, double mouseY) {
        if (!isSelected()) {
            screen.playSound(MalumSoundEvents.ARCANA_BUTTON_UNCLICK, 1, 1);
        }
        return super.click(screen, mouseX, mouseY);
    }

    public <T> T choose(T onDefault, T onHover, T onPressed, T onSelected) {
        if (isSelected()) {
            return onSelected;
        }
        if (isPressed) {
            return onPressed;
        }
        if (isHoveredOver) {
            return onHover;
        }
        return onDefault;
    }
}