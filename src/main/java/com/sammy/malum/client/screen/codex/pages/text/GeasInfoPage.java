package com.sammy.malum.client.screen.codex.pages.text;

import com.mojang.datafixers.util.*;
import com.sammy.malum.*;
import com.sammy.malum.client.screen.codex.display.*;
import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.client.screen.codex.screens.*;
import com.sammy.malum.core.systems.geas.*;
import net.minecraft.client.gui.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import net.minecraft.resources.ResourceLocation;


public class GeasInfoPage extends BookPage {

    public final Component pros;
    public final float prosScale;
    public final Component cons;
    public final float consScale;

    public GeasInfoPage(Holder<GeasEffectType> geas) {
        var prosData = makeText(geas.value().getDetailedPros());
        var consData = makeText(geas.value().getDetailedCons());
        this.pros = prosData.getFirst();
        this.prosScale = prosData.getSecond();
        this.cons = consData.getFirst();
        this.consScale = consData.getSecond();
    }

    @Override
    public ResourceLocation getBackground() {
        return MalumMod.malumPath("textures/gui/book/pages/geas_info_page.png");
    }

    @Override
    public void render(CodexEntryScreen screen, GuiGraphics guiGraphics, int left, int top, int mouseX, int mouseY, float partialTicks, boolean isRepeat) {
        int leftStart = ((left + 14));
        int upperTopStart = ((top + 10));
        int lowerTopStart = ((top + 100));
//        renderWrappingText(guiGraphics, CodexTextRenderer.GEAS_POSITIVE_COLOR, pros, leftStart, upperTopStart, 118, prosScale);
//        renderWrappingText(guiGraphics, CodexTextRenderer.GEAS_NEGATIVE_COLOR, cons, leftStart, lowerTopStart, 118, consScale);
    }

    public Pair<Component, Float> makeText(String text) {
        String translated = Component.translatable(text).getString();
        if (translated.startsWith("$m"))
        {
            final int i = translated.indexOf("/$");
            float value = Float.parseFloat(translated.substring(3, i));
            return Pair.of(Component.literal(translated.substring(i + 2)), value);
        }
        return Pair.of(Component.translatable(text), 0.85f);
    }

}