package com.sammy.malum.client.imgui;

import com.sammy.malum.client.renderer.renderpass.ParallelWorldRenderer;
import com.sammy.malum.client.screen.codex.screens.progression.AbstractProgressionCodexScreen;
import imgui.ImGui;
import imgui.type.ImBoolean;
import imgui.type.ImInt;
import net.minecraft.client.Minecraft;

public class ImGuiLayer {

    public int[] texture = new int[]{0};
    public int[] textureSize = new int[]{500};

    public void render() {
        ImGui.begin("Texture Viewer");
        if (Minecraft.getInstance().screen instanceof AbstractProgressionCodexScreen codexScreen) {
            ImGui.text("" + codexScreen.target.getColorTextureId());
        }
//        if (ParallelWorldRenderer.INSTANCE != null) {
//            ImGui.text("" + ParallelWorldRenderer.INSTANCE.getTarget().getColorTextureId());
//        }
        ImGui.sliderInt("Texture", texture, 0, 200);
        ImGui.sliderInt("Texture Size", textureSize, 100, 2000);
        ImGui.image(texture[0], textureSize[0], textureSize[0], 0, 1, 1, 0);
        ImGui.end();
    }
}
