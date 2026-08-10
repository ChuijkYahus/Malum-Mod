package com.sammy.malum.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.sammy.malum.common.geas.pact.earthen.ProfaneAsceticGeas;
import com.sammy.malum.common.item.curiosities.weapons.scythe.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(Player.class)
public abstract class PlayerMixin {


    @WrapOperation(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/damagesource/DamageSources;playerAttack(Lnet/minecraft/world/entity/player/Player;)Lnet/minecraft/world/damagesource/DamageSource;"))
    private DamageSource malum$updateScytheDamageType(DamageSources instance, Player player, Operation<DamageSource> original, @Local ItemStack weapon) {
        var scytheDamage = MalumScytheItem.replaceDirectDamage(player, weapon);
        return scytheDamage.orElseGet(() -> original.call(instance, player));
    }

    @Inject(method = "attack", slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;setSprinting(Z)V")), at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;getAttributeValue(Lnet/minecraft/core/Holder;)D", ordinal = 0))
    private void malum$updateScytheSweepingDamageType(Entity target, CallbackInfo ci, @Local ItemStack weapon, @Local LocalRef<DamageSource> damageSource, @Share("originalSweepingDamageType") LocalRef<DamageSource> originalSweepingDamageType) {
        Player player = (Player) (Object) this;
        var scytheDamage = MalumScytheItem.replaceSweepingDamage(player, weapon);
        if (scytheDamage.isPresent()) {
            originalSweepingDamageType.set(damageSource.get());
            damageSource.set(scytheDamage.get());
        }
    }

    @Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;sweepAttack()V"), cancellable = true)
    private void malum$restoreOriginalScytheDamageType(Entity target, CallbackInfo ci, @Local LocalRef<DamageSource> damageSource, @Share("originalSweepingDamageType") LocalRef<DamageSource> originalSweepingDamageType) {
        if (originalSweepingDamageType.get() != null) {
            damageSource.set(originalSweepingDamageType.get());
            originalSweepingDamageType.set(null);
            ci.cancel();
        }
    }

    @Inject(method = "canEat", at = @At("HEAD"), cancellable = true)
    private void malum$canEat(boolean canAlwaysEat, CallbackInfoReturnable<Boolean> cir) {
        Player player = (Player) (Object) this;
        if (ProfaneAsceticGeas.isProfaneAscetic(player)) {
            cir.setReturnValue(true);
        }
    }
}
