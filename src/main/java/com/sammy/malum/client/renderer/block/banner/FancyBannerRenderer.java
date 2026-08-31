package com.sammy.malum.client.renderer.block.banner;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.sammy.malum.common.block.building.banner.fancy.FancyBannerBlockEntity;
import com.sammy.malum.common.block.building.banner.soulwoven.SoulwovenBannerBlock;
import com.sammy.malum.common.block.building.banner.soulwoven.SoulwovenBannerBlockEntity;
import com.sammy.malum.common.data.component.banner.FancyBannerDataComponent;
import com.sammy.malum.common.data.listener.banner.MalumBannerPatternReloadListener;
import com.sammy.malum.common.data.listener.banner.MalumBannerPatternType;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.AABB;
import org.joml.Vector3f;
import team.lodestar.lodestone.modules.rendering.LodestoneRenderingSystem;
import team.lodestar.lodestone.registry.client.LodestoneRenderTypes;
import team.lodestar.lodestone.systems.rendering.builder.VFXBuilders;
import team.lodestar.lodestone.systems.rendering.builder.WorldVFXBuilder;
import team.lodestar.lodestone.systems.rendering.rendeertype.RenderTypeToken;
import team.lodestar.lodestone.systems.rendering.rendeertype.ShaderUniformHandler;

import java.awt.*;


public class FancyBannerRenderer extends MalumBannerRenderer<FancyBannerBlockEntity> {
    public FancyBannerRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void renderBanner(FancyBannerBlockEntity blockEntity, PoseStack poseStack, WorldVFXBuilder builder, Vector3f[] vertices) {
        var key = getBlockId(blockEntity);
        var token = RenderTypeToken.createToken(key.withPath(s -> "textures/block/building/wool/" + s + ".png"));
        var banner = LodestoneRenderTypes.CUTOUT_TEXTURE.apply(token).withModifier(b -> b.setCullState(RenderStateShard.NO_CULL));
        builder.setRenderType(banner).renderQuad(poseStack, vertices);
    }
}
