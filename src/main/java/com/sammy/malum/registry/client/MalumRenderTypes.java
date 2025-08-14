package com.sammy.malum.registry.client;

import com.mojang.blaze3d.platform.*;
import com.mojang.blaze3d.systems.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import team.lodestar.lodestone.registry.client.*;
import team.lodestar.lodestone.systems.rendering.*;
import team.lodestar.lodestone.systems.rendering.rendeertype.*;

import static com.mojang.blaze3d.vertex.DefaultVertexFormat.*;
import static com.mojang.blaze3d.vertex.VertexFormat.Mode.*;
import static team.lodestar.lodestone.registry.client.LodestoneRenderTypes.createGenericRenderType;
import static team.lodestar.lodestone.registry.client.LodestoneShaders.LODESTONE_TEXTURE;

public class MalumRenderTypes extends RenderStateShard {

    //TODO: move this to lodestone

    private static final RenderStateShard.TransparencyStateShard SUBTRACTIVE_TEXT_TRANSPARENCY = new RenderStateShard.TransparencyStateShard("malum:subtractive_text_transparency", () -> {
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
        RenderSystem.blendEquation(GL14.GL_FUNC_SUBTRACT);
    }, () -> {
        RenderSystem.blendEquation(GL14.GL_FUNC_ADD);
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
    });

    public static final RenderTypeProvider WEEPING_WELL_DISTORTED_TEXTURE = new RenderTypeProvider((token) ->
            createGenericRenderType("weeping_well_distorted_texture", POSITION_COLOR_TEX_LIGHTMAP, QUADS,
                    LodestoneRenderTypes.builder(token, StateShards.ADDITIVE_TRANSPARENCY, MalumShaders.WEEPING_WELL_DISTORTION, CULL, LIGHTMAP, COLOR_WRITE)));

    public static final RenderTypeProvider SUBTRACTIVE_TEXT = new RenderTypeProvider((token) ->
            createGenericRenderType("subtractive_text", POSITION_COLOR_TEX_LIGHTMAP, QUADS,
                    LodestoneRenderTypes.builder(token, RENDERTYPE_TEXT_SEE_THROUGH_SHADER, SUBTRACTIVE_TEXT_TRANSPARENCY, COLOR_WRITE, LIGHTMAP)));

    public static final RenderTypeProvider SUBTRACTIVE_INTENSE_TEXT = new RenderTypeProvider((token) ->
        createGenericRenderType("subtractive_intense_text", POSITION_COLOR_TEX_LIGHTMAP, QUADS,
                LodestoneRenderTypes.builder(token, RENDERTYPE_TEXT_INTENSITY_SEE_THROUGH_SHADER, SUBTRACTIVE_TEXT_TRANSPARENCY, COLOR_WRITE, LIGHTMAP)));

    public static final RenderTypeProvider MALIGNANT_GLOW = new RenderTypeProvider((token) ->
            createGenericRenderType("malignant_glow", POSITION_COLOR_TEX_LIGHTMAP, QUADS,
                    LodestoneRenderTypes.builder(token, LODESTONE_TEXTURE, ADDITIVE_TRANSPARENCY, NO_CULL)));

    public static final RenderTypeProvider SOULLESS = new RenderTypeProvider((token) ->
            createGenericRenderType("transparent_tbn_texture", LodestoneVertexFormats.POSITION_TEX_NORMAL_TANGENT_BITANGENT, QUADS,
                    LodestoneRenderTypes.builder(token, StateShards.NORMAL_TRANSPARENCY, MalumShaders.SOULLESS, CULL, LIGHTMAP, COLOR_WRITE)));

    public MalumRenderTypes(String pName, Runnable pSetupState, Runnable pClearState) {
        super(pName, pSetupState, pClearState);
    }
}
