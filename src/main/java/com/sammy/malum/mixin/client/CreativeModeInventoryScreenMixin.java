package com.sammy.malum.mixin.client;

import com.sammy.malum.core.handlers.hiding.HiddenTagHandler;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreativeModeInventoryScreen.class)
public class CreativeModeInventoryScreenMixin {

    @Inject(method = "refreshSearchResults", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/NonNullList;addAll(Ljava/util/Collection;)Z", shift = At.Shift.AFTER))
    private void malum$refreshSearchResults(CallbackInfo ci) {
        AbstractContainerScreenAccessor<?> accessor = (AbstractContainerScreenAccessor<?>) (Object) this;
        AbstractContainerMenu menu = accessor.malum$getMenu();
        var itemPickerMenu = ((CreativeModeInventoryScreen.ItemPickerMenu)menu);
        HiddenTagHandler.hideItems(itemPickerMenu.items);
    }
}
