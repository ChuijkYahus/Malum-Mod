package com.sammy.malum.mixin.client;

import com.sammy.malum.client.creative_tab.MalumCreativeTabTweaks;
import com.sammy.malum.registry.common.MalumCreativeTabs;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractContainerScreen.class)
public class AbstractContainerScreenMixin {

    @Inject(method = "renderSlot", at = @At("HEAD"), cancellable = true)
    private void malum$modifySlotRendering(GuiGraphics guiGraphics, Slot slot, CallbackInfo ci) {
        if (MalumCreativeTabTweaks.renderSlot(guiGraphics, slot)) {
            ci.cancel();
        }
    }

    @Inject(method = "renderSlotHighlight(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/world/inventory/Slot;IIF)V", at = @At("HEAD"), cancellable = true)
    private void malum$modifySlotHighlightRendering(GuiGraphics guiGraphics, Slot slot, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        if (MalumCreativeTabTweaks.disableSlotHighlight(slot)) {
            ci.cancel();
        }
    }
}
