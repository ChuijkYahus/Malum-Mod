package com.sammy.malum.client.screen.codex.pages.text;

import com.sammy.malum.*;
import com.sammy.malum.client.screen.codex.helper.*;
import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.client.screen.codex.screens.*;
import com.sammy.malum.core.systems.registry.rite.RiteHolder;
import com.sammy.malum.core.systems.rite.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.network.chat.*;
import net.minecraft.resources.ResourceLocation;

import static com.sammy.malum.client.screen.codex.helper.CodexRenderHelper.*;

public class SpiritRiteTextPage extends BookPage {

    public final SpiritRiteType rite;
    private final Component headline;
    private final Component text;

    public SpiritRiteTextPage(RiteHolder<SpiritRiteType> riteType) {
        this.rite = riteType.value();
        this.headline = Component.translatable(rite.getLangKey());
        this.text = Component.translatable(rite.getCodexEntryLangKey());
    }

    @Override
    public ResourceLocation getBackground() {
        return MalumMod.malumPath("textures/gui/book/pages/spirit_rite_page.png");
    }

    @Override
    public void render(CodexEntryScreen screen, GuiGraphics guiGraphics, int left, int top, int mouseX, int mouseY, float partialTicks, boolean isRepeat) {
//        CodexTextHelper.renderHeadline(guiGraphics, headline, left, top);
//        CodexTextHelper.renderWrappingText(guiGraphics, text, left + 6, top + 78, 130);

        int riteIconX = left + 63;
        int riteIconY = top + 38;
        renderRiteIcon(rite, guiGraphics.pose(), riteIconX, riteIconY);
        if (screen.isHovering(mouseX, mouseY, riteIconX, riteIconY, 16, 16)) {
            screen.renderLater(()->guiGraphics.renderComponentTooltip(Minecraft.getInstance().font, rite.getDetailedDescription(), mouseX, mouseY));
        }
    }
}
