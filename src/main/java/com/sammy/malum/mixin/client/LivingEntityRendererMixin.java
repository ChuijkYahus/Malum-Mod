package com.sammy.malum.mixin.client;

import com.mojang.blaze3d.vertex.*;
import com.sammy.malum.core.handlers.client.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.world.entity.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin {

    @Inject(method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "HEAD"))
    private void malum$startRenderingSoullessOutline(LivingEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, CallbackInfo ci) {
        SoullessStateRenderHandler.startRenderingSoullessOutline(entity, poseStack);
    }

    @Inject(method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "TAIL"))
    private void malum$endRenderingSoullessOutline(LivingEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, CallbackInfo ci) {
        SoullessStateRenderHandler.endRenderingSoullessOutline(entity);
    }
}