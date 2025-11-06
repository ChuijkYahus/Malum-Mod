package com.sammy.malum.client.screen.codex.objects.progression;

import com.sammy.malum.client.screen.codex.*;
import com.sammy.malum.client.screen.codex.helper.*;
import com.sammy.malum.client.screen.codex.objects.*;
import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.client.screen.codex.screens.*;
import com.sammy.malum.client.screen.codex.screens.progression.*;
import com.sammy.malum.core.systems.geas.GeasEffectType;
import net.minecraft.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static com.sammy.malum.client.screen.codex.WidgetDesignType.FillingType.PAPER;
import static com.sammy.malum.client.screen.codex.WidgetDesignType.FrameType.RUNEWOOD;
import static com.sammy.malum.client.screen.codex.helper.CodexRenderHelper.renderTexture;

public class ProgressionEntryObject extends AbstractSelectableEntryObject<AbstractProgressionCodexScreen> {

    public WidgetDesign design = WidgetDesignType.DEFAULT.createDesign(RUNEWOOD, PAPER);
    public boolean isOrigin;

    public ProgressionEntryObject(BookEntry entry, int posX, int posY) {
        super(entry, posX, posY, 32, 32);
    }

    @Override
    public boolean isInView(AbstractProgressionCodexScreen screen) {
        return screen.isInView(getOffsetXPosition(), getOffsetYPosition())
                || screen.isInView(getOffsetXPosition() + width, getOffsetYPosition())
                || screen.isInView(getOffsetXPosition(), getOffsetYPosition() + height)
                || screen.isInView(getOffsetXPosition() + width, getOffsetYPosition() + height);
    }

    @Override
    public void render(AbstractProgressionCodexScreen screen, GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        var poseStack = guiGraphics.pose();
        var designType = design.getDesignType();
        int width = designType.getTextureWidth();
        int height = designType.getTextureHeight();
        int posX = getLeftPos();
        int posY = getTopPos();
        int centerX = getCenterX();
        int centerY = getCenterY();
        renderTexture(WIDGET_FADE_TEXTURE, poseStack, centerX - 29, centerY - 29, 0, 0, 58, 58);
        if (design != null) {
            design.getFrameTexture().ifPresent(texture -> renderTexture(texture, poseStack, posX, posY, 0, 0, width, height));
            design.getFillingTexture().ifPresent(texture -> renderTexture(texture, poseStack, posX, posY, 0, 0, width, height));
        }
        if (iconStack != null) {
            guiGraphics.renderItem(iconStack, centerX - designType.getItemXOffset(), centerY - designType.getItemYOffset());
        }
    }

    @Override
    public List<Component> gatherTooltip(AbstractProgressionCodexScreen screen) {
        var tooltip = super.gatherTooltip(screen);
        for (EntryReference reference : entry.references) {
            if (reference.entry.shouldShow()) {
                var slash = Component.literal("┇ ");
                var text = Component.translatable(reference.entry.translationKey());
                var component = slash.append(text).withStyle(ChatFormatting.DARK_GRAY);
                tooltip.add(1, component);
            }
        }
        return tooltip;
    }

    public int getLeftPos() {
        var designType = design.getDesignType();
        int width = designType.getTextureWidth();
        return getOffsetXPosition() - (width - 32) / 2;
    }

    public int getTopPos() {
        var designType = design.getDesignType();
        int height = designType.getTextureHeight();
        return getOffsetYPosition() - (height - 32) / 2;
    }

    public int getCenterX() {
        return getLeftPos() + width / 2;
    }

    public int getCenterY() {
        return getTopPos() + height / 2;
    }

    @Override
    public ProgressionEntryObject setIcon(Supplier<? extends Item> item) {
        return (ProgressionEntryObject) super.setIcon(item);
    }

    @Override
    public ProgressionEntryObject setIcon(Item item) {
        return (ProgressionEntryObject) super.setIcon(item);
    }

    @Override
    public ProgressionEntryObject setIcon(Holder<GeasEffectType> geas) {
        return (ProgressionEntryObject) super.setIcon(geas);
    }

    @Override
    public ProgressionEntryObject setIcon(ItemStack itemStack) {
        return (ProgressionEntryObject) super.setIcon(itemStack);
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
