package com.sammy.malum.mixin;

import com.llamalad7.mixinextras.injector.*;
import com.sammy.malum.common.geas.gluttony.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.food.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(FoodData.class)
public class FoodDataMixin {

    @Unique
    private Player malum$player;

    @Inject(method = "tick", at = @At(value = "HEAD"))
    private void malum$eat(Player player, CallbackInfo ci) {
        malum$player = player;
    }

    @ModifyExpressionValue(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/GameRules;getBoolean(Lnet/minecraft/world/level/GameRules$Key;)Z"))
    private boolean malum$tick(boolean original) {
        if (ProfaneAsceticGeas.isProfaneAscetic(malum$player)) {
            return false;
        }
        return original;
    }
}
