package com.sammy.malum.client.screen.codex.pages.text;

import com.mojang.blaze3d.vertex.*;
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

    public final String pros;
    public final String cons;

    public GeasInfoPage(Holder<GeasEffectType> geas) {
        super(MalumMod.malumPath("textures/gui/book/pages/geas_info_page.png"));
        this.pros = geas.value().getDetailedPros();
        this.cons = geas.value().getDetailedCons();
    }

    @Override
    public void render(EntryScreen screen, GuiGraphics guiGraphics, int left, int top, int mouseX, int mouseY, float partialTicks, boolean isRepeat) {
        final PoseStack pose = guiGraphics.pose();
        float scale = 0.85f;
        float inverseScale = 1f - scale;
        int width = (int) (118 * (1 / scale));
        int leftStart = ((left + 14));
        int upperTopStart = ((top + 10));
        int lowerTopStart = ((top + 100));

        //A bit cursed to make the text glow calculations line up with the posestack manipulated positions
        pose.pushPose();
        pose.translate(leftStart * inverseScale, 0, 0);
        pose.pushPose();
        pose.translate(0, upperTopStart * inverseScale, 0);
        pose.scale(scale, scale, 1);
        renderWrappingText(guiGraphics, ArcanaCodexHelper.GEAS_POSITIVE_COLOR, pros, leftStart, upperTopStart, width);
        pose.popPose();
        pose.translate(0, lowerTopStart * inverseScale, 0);
        pose.scale(scale, scale, 1);
        renderWrappingText(guiGraphics, ArcanaCodexHelper.GEAS_NEGATIVE_COLOR, cons, leftStart, lowerTopStart, width);
        pose.popPose();
    }
}