package com.sammy.malum.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.*;
import com.mojang.blaze3d.vertex.*;
import com.sammy.malum.core.handlers.client.*;
import net.minecraft.client.model.geom.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(ModelPart.class)
public class ModelPartMixin {

    @WrapOperation(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;III)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/geom/ModelPart;compile(Lcom/mojang/blaze3d/vertex/PoseStack$Pose;Lcom/mojang/blaze3d/vertex/VertexConsumer;III)V"))
    private void malum$renderSoullessOutline(ModelPart instance, PoseStack.Pose pose, VertexConsumer buffer, int packedLight, int packedOverlay, int color, Operation<Void> original) {
        original.call(instance, pose, buffer, packedLight, packedOverlay, color);
        SoullessStateRenderHandler.renderSoullessModelPart(instance, pose, packedLight, packedOverlay);
    }
}
