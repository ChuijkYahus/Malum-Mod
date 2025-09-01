package com.sammy.malum.client.screen.codex.objects.progression;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sammy.malum.client.screen.codex.*;
import com.sammy.malum.client.screen.codex.helper.*;
import com.sammy.malum.client.screen.codex.objects.BookObject;
import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.client.screen.codex.screens.*;
import com.sammy.malum.client.screen.codex.screens.progression.*;
import com.sammy.malum.core.systems.geas.GeasEffectType;
import net.minecraft.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static com.sammy.malum.client.screen.codex.WidgetDesignType.FillingType.PAPER;
import static com.sammy.malum.client.screen.codex.WidgetDesignType.FrameType.RUNEWOOD;
import static com.sammy.malum.client.screen.codex.helper.CodexRenderHelper.renderTexture;

public class ProgressionEntryObject extends BookObject<AbstractProgressionCodexScreen> {

    public final BookEntry entry;
    public WidgetDesign design = WidgetDesignType.DEFAULT.createDesign(RUNEWOOD, PAPER);
    public ChatFormatting headlineFormatting;
    public Predicate<AbstractProgressionCodexScreen> isValid = t -> true;
    public ItemStack iconStack;

    public ProgressionEntryObject(BookEntry entry, int posX, int posY) {
        super(posX, posY, 32, 32);
        this.entry = entry;
    }

    @Override
    public boolean isValid(AbstractProgressionCodexScreen screen) {
        return isValid.test(screen) && entry.shouldShow();
    }

    @Override
    public boolean isInView(AbstractProgressionCodexScreen screen) {
        return screen.isInView(getOffsetXPosition(), getOffsetYPosition())
                || screen.isInView(getOffsetXPosition() + width, getOffsetYPosition())
                || screen.isInView(getOffsetXPosition(), getOffsetYPosition() + height)
                || screen.isInView(getOffsetXPosition() + width, getOffsetYPosition() + height);
    }

    @Override
    public boolean click(AbstractProgressionCodexScreen screen, double mouseX, double mouseY) {
        if (entry.hasContents()) {
            CodexEntryScreen.openScreen(entry);
            return true;
        }
        return false;
    }

    @Override
    public void render(AbstractProgressionCodexScreen screen, GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        var poseStack = guiGraphics.pose();
        var designType = design.getDesignType();
        int width = designType.getTextureWidth();
        int height = designType.getTextureHeight();
        int posX = getOffsetXPosition() - (width - 32) / 2;
        int posY = getOffsetYPosition() - (height - 32) / 2;
        int centerX = posX + width / 2;
        int centerY = posY + height / 2;
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
    public void renderLate(AbstractProgressionCodexScreen screen, GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if (isHoveredOver && entry.hasTooltip()) {
            ChatFormatting formatting = getHeadlineStyle(screen);
            List<Component> list = new ArrayList<>(List.of(
                    CodexTextHelper.convertToComponent(entry.translationKey(), entry.titleStyle).withStyle(formatting),
                    CodexTextHelper.convertToComponent(entry.descriptionTranslationKey(), entry.subtitleStyle)));

            for (EntryReference reference : entry.references) {
                if (reference.entry.shouldShow()) {
                    MutableComponent slash = Component.literal(" -").withStyle(reference.entry.subtitleStyle);
                    MutableComponent text = slash.append(Component.translatable(reference.entry.translationKey()));
                    list.add(text.setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY)));
                }
            }
            guiGraphics.renderComponentTooltip(Minecraft.getInstance().font, list, mouseX, mouseY);
        }
    }

    public ChatFormatting getHeadlineStyle(AbstractProgressionCodexScreen screen) {
        if (headlineFormatting != null) {
            return headlineFormatting;
        }
        //TODO: Un-hardcode This
        boolean isVoid = screen instanceof VoidProgressionScreen;
        ChatFormatting formatting = isVoid ? ChatFormatting.DARK_PURPLE : ChatFormatting.GOLD;
        if (design.getDesignType().equals(WidgetDesignType.GILDED)) {
            formatting = isVoid ? ChatFormatting.LIGHT_PURPLE : ChatFormatting.YELLOW;
        }
        return formatting;
    }

    public ProgressionEntryObject setIcon(Supplier<? extends Item> item) {
        return setIcon(item.get());
    }

    public ProgressionEntryObject setIcon(Item item) {
        return setIcon(item.getDefaultInstance());
    }

    public ProgressionEntryObject setIcon(Holder<GeasEffectType> geas) {
        return setIcon(geas.value().createDefaultStack());
    }

    public ProgressionEntryObject setIcon(ItemStack itemStack) {
        iconStack = itemStack;
        return this;
    }

    public ProgressionEntryObject setDesign(WidgetDesignType design, WidgetDesignType.FrameType frame, WidgetDesignType.FillingType filling) {
        return setDesign(design.createDesign(frame, filling));
    }

    public ProgressionEntryObject setDesign(WidgetDesign design) {
        this.design = design;
        return this;
    }

    public ProgressionEntryObject setHeadlineFormatting(ChatFormatting formatting) {
        this.headlineFormatting = formatting;
        return this;
    }

    public ProgressionEntryObject setCondition(Predicate<AbstractProgressionCodexScreen> isValid) {
        this.isValid = isValid;
        return this;
    }
}
