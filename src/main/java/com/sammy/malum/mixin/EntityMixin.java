package com.sammy.malum.mixin;

import com.sammy.malum.common.geas.pact.infernal.*;
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
}
