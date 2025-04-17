package com.sammy.malum.mixin;

import com.llamalad7.mixinextras.injector.*;
import com.sammy.malum.common.geas.pact.aqueous.*;
import com.sammy.malum.common.geas.pact.earthen.*;
import com.sammy.malum.common.geas.pact.sacred.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.food.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(FoodData.class)
public class FoodDataMixin {

    @Shadow private int tickTimer;
    @Shadow private int foodLevel;
    @Unique
    private Player malum$player;

    @Unique
    private boolean malum$CanHeal;

    @Inject(method = "tick", at = @At(value = "HEAD"))
    private void malum$eat(Player player, CallbackInfo ci) {
        malum$player = player;
    }

    @ModifyExpressionValue(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/GameRules;getBoolean(Lnet/minecraft/world/level/GameRules$Key;)Z"))
    private boolean malum$cancelHealing(boolean original) {
        malum$CanHeal = original;
        if (ProfaneAsceticGeas.isProfaneAscetic(malum$player)) {
            malum$CanHeal = false;
        }
        return malum$CanHeal;
    }

    @Inject(method = "tick", at = @At(value = "HEAD"))
    private void malum$accelerateHealing(Player player, CallbackInfo ci) {
        if (malum$CanHeal) {
            tickTimer += DefianceGeas.accelerateNaturalHealing(player);
        }
        tickTimer += SelfCareGeas.accelerateHunger(player, foodLevel);
    }
}
