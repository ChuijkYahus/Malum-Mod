package com.sammy.malum.mixin.client;

import com.sammy.malum.core.handlers.hiding.HiddenTagHandler;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.Collection;

@Mixin(CreativeModeTab.class)
public class CreativeModeTabMixin {

    @Inject(method = "getDisplayItems", at = @At("RETURN"), cancellable = true)
    private void malum$getDisplayItems(CallbackInfoReturnable<Collection<ItemStack>> cir) {
        CreativeModeTab tab = (CreativeModeTab) (Object) this;
        if (tab.hasSearchBar())
            return;

        Collection<ItemStack> items = new ArrayList<>(cir.getReturnValue());
        HiddenTagHandler.hideItems(items);
        cir.setReturnValue(items);
    }
}
