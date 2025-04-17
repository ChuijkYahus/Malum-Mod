package com.sammy.malum.client.renderer.item;

import com.mojang.blaze3d.systems.*;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.*;
import com.sammy.malum.client.screen.codex.ArcanaCodexHelper;
import com.sammy.malum.common.block.storage.jar.SpiritJarBlockEntity;
import com.sammy.malum.common.data.component.*;
import com.sammy.malum.core.handlers.GeasEffectHandler;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.block.BlockRegistry;
import com.sammy.malum.registry.common.item.DataComponentRegistry;
import net.minecraft.client.*;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.*;
import org.lwjgl.opengl.*;
import team.lodestar.lodestone.registry.client.*;
import team.lodestar.lodestone.systems.rendering.*;
import team.lodestar.lodestone.systems.rendering.shader.*;

import java.util.function.*;

public class GeasItemRenderer extends BlockEntityWithoutLevelRenderer {

    public GeasItemRenderer(BlockEntityRenderDispatcher pBlockEntityRenderDispatcher, EntityModelSet pEntityModelSet) {
        super(pBlockEntityRenderDispatcher, pEntityModelSet);
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext ctx, PoseStack poseStack, MultiBufferSource buffer, int light, int overlay) {
        if (ctx.equals(ItemDisplayContext.GUI)) {
            if (stack.has(DataComponentRegistry.GEAS_EFFECT)) {
                GeasEffectHandler.getStoredGeasEffect(stack).ifPresent(c -> {
                    var geas = c.geasEffectType();
                    poseStack.popPose();
                    poseStack.pushPose();
                    poseStack.mulPose(Axis.ZP.rotation(3.14f));
                    poseStack.mulPose(Axis.YN.rotation(3.14f));
                    poseStack.translate(-.5, -.5, -.5);
                    poseStack.scale(0.0625f, 0.0625f, 0.0625f);
                    ArcanaCodexHelper.renderGeasIcon(geas.getIcon(), poseStack, geas, 0, 0);
                });
            }
        }
    }
}
