package com.sammy.malum.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.*;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.*;
import com.sammy.malum.core.handlers.client.*;
import net.minecraft.client.model.geom.*;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModelPart.class)
public class ModelPartMixin {

    @WrapOperation(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;III)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/geom/ModelPart;compile(Lcom/mojang/blaze3d/vertex/PoseStack$Pose;Lcom/mojang/blaze3d/vertex/VertexConsumer;III)V"))
    private void malum$renderSoullessOutline(ModelPart instance, PoseStack.Pose pose, VertexConsumer buffer, int packedLight, int packedOverlay, int color, Operation<Void> original) {
        original.call(instance, pose, buffer, packedLight, packedOverlay, color);
        SoullessStateRenderHandler.renderSoullessModelPart(instance, pose, buffer, packedLight, packedOverlay);
    }


    @Mixin(ModelPart.Cube.class)
    public static class Cube {
        @Inject(
                method = "compile",
                at = @At(
                        value = "INVOKE",
                        target = "Lcom/mojang/blaze3d/vertex/PoseStack$Pose;transformNormal(Lorg/joml/Vector3f;Lorg/joml/Vector3f;)Lorg/joml/Vector3f;",
                        shift = At.Shift.AFTER
                )
        )
        private void malum$extractModelPartPolygon(PoseStack.Pose pose, VertexConsumer buffer, int packedLight, int packedOverlay, int color, CallbackInfo ci, @Local ModelPart.Polygon polygon, @Local Vector3f normal) {
            SoullessStateRenderHandler.generateTangentBitangent(pose, buffer, polygon, normal);
        }
    }
}
