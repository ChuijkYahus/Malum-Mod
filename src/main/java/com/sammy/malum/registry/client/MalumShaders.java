package com.sammy.malum.registry.client;

import com.mojang.blaze3d.vertex.*;
import com.sammy.malum.*;
import net.neoforged.api.distmarker.*;
import net.neoforged.bus.api.*;
import net.neoforged.fml.common.*;
import net.neoforged.neoforge.client.event.*;
import team.lodestar.lodestone.registry.client.*;
import team.lodestar.lodestone.systems.rendering.shader.*;

import static com.mojang.blaze3d.vertex.DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP;

@EventBusSubscriber(value = Dist.CLIENT, modid = MalumMod.MALUM, bus = EventBusSubscriber.Bus.MOD)
public class MalumShaders {

    public static LodestoneShaderRegistry SHADERS = new LodestoneShaderRegistry(MalumMod.MALUM);

    public static ShaderHolder TOUCH_OF_DARKNESS = SHADERS.register("touch_of_darkness", DefaultVertexFormat.POSITION_TEX_COLOR);
    public static ShaderHolder WEEPING_WELL_DISTORTION = SHADERS.register("weeping_distortion", DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP);
    public static ShaderHolder SOULLESS_OUTLINE = SHADERS.register("soulless_outline", POSITION_COLOR_TEX_LIGHTMAP);
    public static ShaderHolder PARALLEL_WORLD_SKYBOX = SHADERS.register("parallel_world/skybox", DefaultVertexFormat.POSITION);

    @SubscribeEvent
    public static void shaderRegistry(RegisterShadersEvent event) {
        SHADERS.init(event);
    }
}