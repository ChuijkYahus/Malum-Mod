package com.sammy.malum.client.screen.codex.pages.text;

import com.mojang.blaze3d.vertex.*;
import com.mojang.datafixers.util.*;
import com.sammy.malum.*;
import com.sammy.malum.client.screen.codex.*;
import com.sammy.malum.client.screen.codex.pages.*;
import com.sammy.malum.client.screen.codex.screens.*;
import com.sammy.malum.core.systems.geas.*;
import net.minecraft.client.gui.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;

import static com.sammy.malum.client.screen.codex.ArcanaCodexHelper.renderWrappingText;

public class GeasInfoPage extends BookPage {

    public final Component pros;
    public final float prosScale;
    public final Component cons;
    public final float consScale;

    public GeasInfoPage(Holder<GeasEffectType> geas) {
        super(MalumMod.malumPath("textures/gui/book/pages/geas_info_page.png"));
        var prosData = makeText(geas.value().getDetailedPros());
        var consData = makeText(geas.value().getDetailedCons());
        this.pros = prosData.getFirst();
        this.prosScale = prosData.getSecond();
        this.cons = consData.getFirst();
        this.consScale = consData.getSecond();
    }

    @Override
    public void render(EntryScreen screen, GuiGraphics guiGraphics, int left, int top, int mouseX, int mouseY, float partialTicks, boolean isRepeat) {
        int leftStart = ((left + 14));
        int upperTopStart = ((top + 10));
        int lowerTopStart = ((top + 100));
        renderWrappingText(guiGraphics, ArcanaCodexHelper.GEAS_POSITIVE_COLOR, pros, leftStart, upperTopStart, 118, prosScale);
        renderWrappingText(guiGraphics, ArcanaCodexHelper.GEAS_NEGATIVE_COLOR, cons, leftStart, lowerTopStart, 118, consScale);
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