package com.sammy.malum.client.screen.codex.pages.text;

import com.sammy.malum.*;
import com.sammy.malum.client.screen.codex.helper.*;
import com.sammy.malum.client.screen.codex.screens.*;
import com.sammy.malum.core.systems.spirit.type.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.resources.*;

import static com.sammy.malum.MalumMod.malumPath;
import static com.sammy.malum.client.screen.codex.helper.CodexRenderHelper.*;
import static com.sammy.malum.client.screen.codex.helper.CodexTextHelper.renderHeadline;

public class HeadlineTextIconPage extends HeadlineTextPage {

    private static final ResourceLocation GLOW_TEXTURE = malumPath("textures/gui/book/icon_glow.png");
    private static final ResourceLocation ANCHOR_TEXTURE = malumPath("textures/vfx/rite_anchor_glow_pointer.png");

    private final ResourceLocation icon;
    private final SpiritLike spirit;

    public static HeadlineTextIconPage riteAnchorPage(String text, SpiritLike spirit) {
        return new HeadlineTextIconPage(text, ANCHOR_TEXTURE, spirit);
    }

    public HeadlineTextIconPage(String headline, String text, ResourceLocation icon, SpiritLike spirit) {
        super(headline, text);
        this.icon = icon;
        this.spirit = spirit;
    }

    public HeadlineTextIconPage(String text, ResourceLocation icon, SpiritLike spirit) {
        this(text, text + ".1", icon, spirit);
    }

    @Override
    public ResourceLocation getBackground() {
        return MalumMod.malumPath("textures/gui/book/pages/headline_icon_page.png");
    }

    @Override
    public void render(CodexEntryScreen screen, GuiGraphics guiGraphics, int left, int top, int mouseX, int mouseY, float partialTicks, boolean isRepeat) {
        int iconX = left + 63;
        int iconY = top + 38;
        renderHeadline(guiGraphics, headline, left, top);
        CodexTextHelper.renderWrappingText(guiGraphics, text, left + 6, top + 87, 140);

        renderSpiritIcon(icon, guiGraphics.pose(), spirit, false, iconX, iconY);
        renderSpiritIcon(GLOW_TEXTURE, guiGraphics.pose(), spirit, false, iconX - 8, iconY - 8, 32, 32);

        if (screen.isHovering(mouseX, mouseY, iconX, iconY, 16, 16)) {
            var minecraft = Minecraft.getInstance();
            guiGraphics.renderComponentTooltip(minecraft.font, Screen.getTooltipFromItem(minecraft, spirit.getSpiritStack()), mouseX, mouseY);
        }
    }
}