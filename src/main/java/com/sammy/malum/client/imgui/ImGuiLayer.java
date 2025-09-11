package com.sammy.malum.client.imgui;

import imgui.ImGui;
import imgui.type.ImBoolean;
import imgui.type.ImInt;

public class ImGuiLayer {

    public int[] texture = new int[]{0};
    public int[] textureSize = new int[]{500};

    public void render() {
        ImGui.begin("Texture Viewer");
        ImGui.sliderInt("Texture", texture, 0, 100);
        ImGui.sliderInt("Texture Size", textureSize, 100, 2000);
        ImGui.image(texture[0], textureSize[0], textureSize[0], 0, 1, 1, 0);
        ImGui.end();
    }
}
