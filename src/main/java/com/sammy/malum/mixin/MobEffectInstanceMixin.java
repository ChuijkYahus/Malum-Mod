package com.sammy.malum.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.*;
import com.sammy.malum.common.geas.authority.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(MobEffectInstance.class)
public class MobEffectInstanceMixin {

    @Unique
    private boolean malum$isGleefulTargetActive;

    @Inject(method = "tick", at = @At("HEAD"))
    private void malum$cacheEntity(LivingEntity entity, Runnable onExpirationRunnable, CallbackInfoReturnable<Boolean> cir) {
        if (entity != null) {
            MobEffectInstance instance = (MobEffectInstance) (Object) this;
            if (malum$isGleefulTargetActive) {
                // Check for Gleeful Target less frequently if it's already active
                if (entity.level().getGameTime() % 10 != 0) {
                    return;
                }
            }
            malum$isGleefulTargetActive = GleefulTargetAuthority.pausePotionEffects(entity, instance);
        }
    }

    @Inject(method = "tickDownDuration", at = @At("HEAD"), cancellable = true)
    private void malum$pauseEffectDuration(CallbackInfoReturnable<Integer> cir) {
        if (malum$isGleefulTargetActive) {
            MobEffectInstance instance = (MobEffectInstance) (Object) this;
            cir.setReturnValue((instance).getDuration());
        }
    }

    @WrapOperation(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/effect/MobEffect;shouldApplyEffectTickThisTick(II)Z"))
    private boolean malum$preventEffectTicking(MobEffect instance, int duration, int amplifier, Operation<Boolean> original) {
        if (malum$isGleefulTargetActive) {
            return false;
        }
        return original.call(instance, duration, amplifier);
    }
}
