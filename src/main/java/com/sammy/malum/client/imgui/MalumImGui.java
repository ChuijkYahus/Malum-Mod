package com.sammy.malum.client.imgui;

import com.sammy.malum.MalumMod;
import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.flag.ImGuiConfigFlags;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.glfwGetCurrentContext;

public class MalumImGui {
    private static final ImGuiImplGlfw imGuiGlfw = new ImGuiImplGlfw();
    private static final ImGuiImplGl3 imGuiGl3 = new ImGuiImplGl3();
    public static ImGuiLayer imguiLayer;

    static {
        MalumMod.LOGGER.info("Initializing ImGui with GLSL #version 330");
        init(Minecraft.getInstance().getWindow().getWindow());
    }

    public static void init(long windowHandle) {
        ImGui.createContext();
        ImGuiIO io = ImGui.getIO();
        io.addConfigFlags(ImGuiConfigFlags.ViewportsEnable);
        io.setIniFilename("aspect_imgui.ini");

        imGuiGlfw.init(windowHandle, true);
        imGuiGl3.init("#version 330");

        imguiLayer = new ImGuiLayer();
    }

    public static void destroy() {
        imGuiGlfw.dispose();
        imGuiGl3.dispose();
        ImGui.destroyContext();
    }

    public static void render() {
        imGuiGlfw.newFrame();
        ImGui.newFrame();

        imguiLayer.render();

        ImGui.render();
        imGuiGl3.renderDrawData(ImGui.getDrawData());

        if (ImGui.getIO().hasConfigFlags(ImGuiConfigFlags.ViewportsEnable)) {
            final long backupWindowPtr = glfwGetCurrentContext();
            ImGui.updatePlatformWindows();
            ImGui.renderPlatformWindowsDefault();
            GLFW.glfwMakeContextCurrent(backupWindowPtr);
        }
    }

}
