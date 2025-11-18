package com.sammy.malum.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.*;
import com.sammy.malum.common.geas.pact.infernal.*;
import com.sammy.malum.core.handlers.*;
import net.minecraft.world.entity.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(Entity.class)
public class EntityMixin {

    @Inject(method = "extinguishFire", at = @At("HEAD"))
    private void malum$extinguishFire(CallbackInfo ci) {
        Entity entity = (Entity) (Object) this;
        if (entity instanceof LivingEntity livingEntity) {
            CombustionGeas.extinguish(livingEntity);
        }
    }

    @WrapOperation(method = "applyGravity", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;getGravity()D"))
    private double malum$applyGravity(Entity entity, Operation<Double> original) {
        return WindTunnelHandler.modifyEntityGravity(entity, original.call(entity));
    }
}
