package com.sammy.malum.core.handlers.client;

import com.mojang.blaze3d.systems.*;
import com.sammy.malum.*;
import com.sammy.malum.core.handlers.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.resources.*;
import net.minecraft.util.*;
import net.minecraft.world.entity.ai.attributes.*;
import team.lodestar.lodestone.systems.rendering.*;

import java.lang.Math;

public class MalignantReinforcementRenderHandler {

    public static void renderMalignantReinforcement(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        var minecraft = Minecraft.getInstance();
        var poseStack = guiGraphics.pose();
        if (!minecraft.options.hideGui) {
            var player = minecraft.player;
            if (!player.isCreative() && !player.isSpectator()) {
                var data = player.getData(MalumAttachmentTypes.MALIGNANT_INFLUENCE);
                int debt = data.getReinforcementDebt();
                int limit = MalignantConversionHandler.getReinforcementLimit(player);
                int tokens = limit - debt;
                if (tokens > 0) {
                    float armor = (float) player.getAttribute(Attributes.ARMOR).getValue();

                    int left = guiGraphics.guiWidth() / 2 - 89;
                    int top = guiGraphics.guiHeight() - minecraft.gui.leftHeight + 2;

                    if (armor == 0) {
                        top += 10;
                    }
                    poseStack.pushPose();
                    RenderSystem.setShaderTexture(0, getMalignantReinforcementTexture());
                    RenderSystem.depthMask(true);
                    RenderSystem.enableBlend();
                    RenderSystem.defaultBlendFunc();
                    var builder = VFXBuilders.createScreen();
                    for (int i = 0; i < tokens; i++) {
                        int row = Mth.floor(i / 10f);
                        float x = left + i % 10 * 8;
                        float y = top - row * 4;

                        builder.setAlpha(1f).setPositionWithWidth(x - 2, y - 2, 9, 10);
                        builder.blit(poseStack);
                    }
                    minecraft.gui.leftHeight += Mth.floor((limit - 1) / 10f) * 9;
                    RenderSystem.disableBlend();
                    poseStack.popPose();
                }
            }
        }
    }

    public static ResourceLocation getMalignantReinforcementTexture() {
        return MalumMod.malumPath("textures/gui/hud/malignant_reinforcement.png");
    }
}
