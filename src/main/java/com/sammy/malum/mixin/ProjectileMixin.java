package com.sammy.malum.mixin;

import com.sammy.malum.common.data.attachment.soul_data.*;
import com.sammy.malum.common.geas.pact.aerial.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.phys.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(Projectile.class)
public class ProjectileMixin {

    @Inject(method = "onHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/Projectile;onHitBlock(Lnet/minecraft/world/phys/BlockHitResult;)V"))
    private void malum$onHitBlock(HitResult result, CallbackInfo ci) {
        Projectile projectile = (Projectile) ((Object)this);
        ContinuingShotGeas.projectileHitBlock(projectile);
    }
}
