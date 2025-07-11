package com.sammy.malum.client.screen.codex.pages.text;

import com.sammy.malum.*;
import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.client.screen.codex.screens.*;
import com.sammy.malum.core.systems.registry.RiteHolder;
import com.sammy.malum.core.systems.rite.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.network.chat.*;

import static com.sammy.malum.client.screen.codex.ArcanaCodexHelper.*;

public class SpiritRiteTextPage extends BookPage {

    public final SpiritRiteType riteType;
    private final Component text;

    public SpiritRiteTextPage(RiteHolder<SpiritRiteType> riteType, String text) {
        super(MalumMod.malumPath("textures/gui/book/pages/spirit_rite_page.png"));
        this.riteType = riteType.value();
        this.text = Component.translatable(BookPage.TEXT + "." + text);
    }

    public String headlineTranslationKey() {
        return riteType.getLangKey();
    }

    @Override
    public void render(EntryScreen screen, GuiGraphics guiGraphics, int left, int top, int mouseX, int mouseY, float partialTicks, boolean isRepeat) {
        Component component = Component.translatable(headlineTranslationKey());
        renderText(guiGraphics, component, left + 70 - Minecraft.getInstance().font.width(component.getString()) / 2f, top + 5);
        renderWrappingText(guiGraphics, text, left + 6, top + 78, 130);

        final int riteIconX = left + 63;
        final int riteIconY = top + 38;
        renderRiteIcon(riteType, guiGraphics.pose(), riteType.isCorrupted(), 0.4f, riteIconX, riteIconY);
        if (screen.isHovering(mouseX, mouseY, riteIconX, riteIconY, 16, 16)) {
            screen.renderLater(()->guiGraphics.renderComponentTooltip(Minecraft.getInstance().font, riteType.getDetailedDescription(), mouseX, mouseY));
        }
    }
}
