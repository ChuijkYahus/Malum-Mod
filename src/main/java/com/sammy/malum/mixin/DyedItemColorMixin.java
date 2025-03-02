package com.sammy.malum.mixin;

import com.sammy.malum.common.item.ether.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

import java.util.*;

@Mixin(DyedItemColor.class)
public class DyedItemColorMixin {

    @Inject(method = "applyDyes", at = @At("HEAD"), cancellable = true)
    private static void malum$applyDyes(ItemStack stack, List<DyeItem> dyes, CallbackInfoReturnable<ItemStack> cir) {
        if (EtherItem.canApplySecondaryColor(stack)) {
            cir.setReturnValue(EtherItem.applyDyesToSecondaryColor(stack, dyes));
        }
    }
}