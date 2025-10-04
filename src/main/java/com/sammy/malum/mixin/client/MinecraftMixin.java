package com.sammy.malum.mixin.client;

import com.sammy.malum.client.imgui.MalumImGui;
import net.minecraft.client.Minecraft;
import net.neoforged.fml.loading.FMLLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Inject(method = "runTick", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/pipeline/RenderTarget;blitToScreen(II)V", shift = At.Shift.AFTER))
    private void renderImGui(boolean pRenderLevel, CallbackInfo ci) {
//        if (FMLLoader.isProduction()) {
//            MalumImGui.render();
//        }
    }
    @Inject(method = "close", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/VirtualScreen;close()V", shift = At.Shift.BEFORE))
    private void destroyImGui(CallbackInfo ci) {
//        if (FMLLoader.isProduction()) {
//            MalumImGui.destroy();
//        }
    }
}
