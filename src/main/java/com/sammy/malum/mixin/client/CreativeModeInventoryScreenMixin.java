package com.sammy.malum.mixin.client;

import com.sammy.malum.client.creative_tab.MalumCreativeTabTweaks;
import com.sammy.malum.core.handlers.hiding.HiddenTagHandler;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.world.item.CreativeModeTab;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreativeModeInventoryScreen.class)
public class CreativeModeInventoryScreenMixin {

    @Shadow private static CreativeModeTab selectedTab;

    @Inject(method = "refreshSearchResults", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/NonNullList;addAll(Ljava/util/Collection;)Z", shift = At.Shift.AFTER))
    private void malum$refreshSearchResults(CallbackInfo ci) {
        var screen = ((CreativeModeInventoryScreen) (Object) this);
        var menu = screen.getMenu();
        HiddenTagHandler.hideItems(menu.items);
    }
    @Inject(method = "selectTab", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/NonNullList;addAll(Ljava/util/Collection;)Z", ordinal = 1, shift = At.Shift.AFTER))
    private void malum$selectTab(CallbackInfo ci) {
        var screen = ((CreativeModeInventoryScreen) (Object) this);
        var menu = screen.getMenu();
        MalumCreativeTabTweaks.modifyTab(screen, menu, selectedTab);
    }
    @Inject(method = "<init>", at = @At(value = "TAIL"))
    private void malum$initCreativeTab(CallbackInfo ci) {
        var screen = ((CreativeModeInventoryScreen) (Object) this);
        var menu = screen.getMenu();
        MalumCreativeTabTweaks.ensureCategoriesAreReal();
    }
}
