package com.sammy.malum.client.screen.codex.objects.progression;

import com.google.common.collect.*;
import com.mojang.blaze3d.vertex.*;
import com.sammy.malum.client.screen.codex.*;
import com.sammy.malum.client.screen.codex.display.CodexOutlineRenderer;
import com.sammy.malum.client.screen.codex.display.DisplayedGizmo;
import com.sammy.malum.client.screen.codex.objects.*;
import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.client.screen.codex.screens.progression.*;
import com.sammy.malum.registry.common.sound.*;
import net.minecraft.*;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.util.*;
import team.lodestar.lodestone.modules.core.easing.Easing;

import java.util.List;
import java.util.function.*;

import static com.sammy.malum.client.screen.codex.WidgetDesignType.FillingType.PAPER;
import static com.sammy.malum.client.screen.codex.WidgetDesignType.FrameType.RUNEWOOD;
import static com.sammy.malum.client.screen.codex.helper.CodexRenderHelper.renderTexture;

public class ProgressionEntryObject extends AbstractSelectableEntryObject<AbstractProgressionCodexScreen> {

    public WidgetDesign design = WidgetDesignType.DEFAULT.createDesign(RUNEWOOD, PAPER);
    public boolean isOrigin;

    protected int oldOutlineVisibility;
    protected int outlineVisibility;

    public ProgressionEntryObject(BookEntry entry, int posX, int posY) {
        super(entry, posX, posY, 32, 32);
    }

    @Override
    public boolean isInView(AbstractProgressionCodexScreen screen) {
        int posX = getOffsetXPosition() - 16;
        int posY = getOffsetYPosition() - 16;
        return posX + 64 >= 0
                && posY + 64 >= 0
                && posX <= AbstractProgressionCodexScreen.BOOK_WIDTH
                && posY <= AbstractProgressionCodexScreen.BOOK_HEIGHT;
    }

    @Override
    public void tick(AbstractProgressionCodexScreen screen, double mouseX, double mouseY) {
        if (design.getDesignType().equals(WidgetDesignType.EMPTY)) {
            return;
        }
        oldOutlineVisibility = outlineVisibility;
        if (isHoveredOver) {
            if (outlineVisibility == 6) {
                screen.playSound(MalumSoundEvents.ARCANA_ENTRY_HOVER, 0.2f, 1f);
            }
            if (outlineVisibility < 20) {
                outlineVisibility = Math.min(outlineVisibility + 2, 20);
            }
            return;
        }
        if (outlineVisibility == 15) {
            screen.playSound(MalumSoundEvents.ARCANA_ENTRY_UNHOVER, 0.1f, 0.75f);
        }
        if (outlineVisibility > 0) {
            outlineVisibility--;
        }
    }

    @Override
    public void applyTransforms(AbstractProgressionCodexScreen screen, PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        float effectStrength = Mth.lerp(partialTicks, oldOutlineVisibility, outlineVisibility) / 20f;
        if (effectStrength > 0) {
            float offset = Easing.CIRC_OUT.ease(effectStrength) * 2;
            poseStack.translate(0, -offset, 0);
        }
    }

    @Override
    public void render(AbstractProgressionCodexScreen screen, GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        var poseStack = guiGraphics.pose();
        int left = getOffsetXPosition() - 16;
        int top = getOffsetYPosition() - 16;
        int centerX = getCenterX();
        int centerY = getCenterY();
        renderTexture(WIDGET_FADE_TEXTURE, poseStack, centerX - 29, centerY - 29, 0, 0, 58, 58);
        if (design != null) {
            var designType = design.getDesignType();
            CodexOutlineRenderer.create(designType, left, top)
                    .setEffectStrength(oldOutlineVisibility, outlineVisibility, 20f)
                    .renderOutline(poseStack);
            design.getFrameTexture().ifPresent(texture -> renderTexture(texture, poseStack, left, top, 0, 0, 64, 64));
            design.getFillingTexture().ifPresent(texture -> renderTexture(texture, poseStack, left, top, 0, 0, 64, 64));
        }
        if (displayedGizmo != null) {
            displayedGizmo.render(screen, guiGraphics, centerX-8, centerY-8, mouseX, mouseY);
        }
    }

    @Override
    public List<Component> gatherTooltip(AbstractProgressionCodexScreen screen) {
        var tooltip = super.gatherTooltip(screen);
        ImmutableList<EntryReference> references = entry.references;
        for (int i = references.size()-1; i >=0; i--) {
            EntryReference reference = references.get(i);
            if (reference.entry.shouldShow()) {
                var slash = Component.literal("┇ ");
                var text = Component.translatable(reference.entry.translationKey());
                var component = slash.append(text).withStyle(ChatFormatting.DARK_GRAY);
                tooltip.add(1, component);
            }
        }
        return tooltip;
    }

    public int getCenterX() {
        return getOffsetXPosition() + width / 2;
    }

    public int getCenterY() {
        return getOffsetYPosition() + height / 2;
    }

    @Override
    public ProgressionEntryObject setIcon(DisplayedGizmo displayedGizmo) {
        return (ProgressionEntryObject) super.setIcon(displayedGizmo);
    }

    @Override
    public ProgressionEntryObject setCondition(Predicate<AbstractProgressionCodexScreen> isValid) {
        return (ProgressionEntryObject) super.setCondition(isValid);
    }

    public ProgressionEntryObject setDesign(WidgetDesignType design, WidgetDesignType.FrameType frame, WidgetDesignType.FillingType filling) {
        return setDesign(design.createDesign(frame, filling));
    }

    public ProgressionEntryObject setDesign(WidgetDesign design) {
        this.design = design;
        return this;
    }

    public ProgressionEntryObject setOrigin() {
        this.isOrigin = true;
        return this;
    }
}
