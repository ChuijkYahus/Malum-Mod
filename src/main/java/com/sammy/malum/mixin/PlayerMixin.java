package com.sammy.malum.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.sammy.malum.common.geas.pact.earthen.ProfaneAsceticGeas;
import com.sammy.malum.common.item.curiosities.weapons.*;
import com.sammy.malum.common.item.curiosities.weapons.greatsword.*;
import com.sammy.malum.common.item.curiosities.weapons.scythe.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(Player.class)
public abstract class PlayerMixin {


    @WrapOperation(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/damagesource/DamageSources;playerAttack(Lnet/minecraft/world/entity/player/Player;)Lnet/minecraft/world/damagesource/DamageSource;"))
    private DamageSource malum$updateScytheDamageType(DamageSources instance, Player player, Operation<DamageSource> original, @Local ItemStack weapon) {
        if (weapon.getItem() instanceof ICustomMeleeDamageTypeItem customDamageItem) {
            return customDamageItem.replaceDirectDamage(player, weapon);
        }
        return original.call(instance, player);
    }

    @Inject(method = "attack", slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;setSprinting(Z)V")), at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;getAttributeValue(Lnet/minecraft/core/Holder;)D", ordinal = 0))
    private void malum$updateScytheSweepingDamageType(Entity target, CallbackInfo ci, @Local ItemStack weapon, @Local LocalRef<DamageSource> damageSource, @Share("originalSweepingDamageType") LocalRef<DamageSource> originalSweepingDamageType) {
        Player player = (Player) (Object) this;
        if (weapon.getItem() instanceof ICustomMeleeDamageTypeItem customDamageItem) {
            originalSweepingDamageType.set(damageSource.get());
            damageSource.set(customDamageItem.replaceSweepingDamage(player, weapon));
        }
    }

    @Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;sweepAttack()V"))
    private void malum$restoreOriginalScytheDamageType(Entity target, CallbackInfo ci, @Local LocalRef<DamageSource> damageSource, @Share("originalSweepingDamageType") LocalRef<DamageSource> originalSweepingDamageType) {
        if (originalSweepingDamageType.get() != null) {
            damageSource.set(originalSweepingDamageType.get());
            originalSweepingDamageType.set(null);
        }
    }

    @Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;playSound(Lnet/minecraft/world/entity/player/Player;DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V", ordinal = 1), cancellable = true)
    private void malum$removeSweepingSound(Entity target, CallbackInfo ci, @Local ItemStack weapon) {
        var item = weapon.getItem();
        if (item instanceof ICustomMeleeDamageTypeItem customDamageItem) {
            Player player = (Player) (Object) this;
            if (!customDamageItem.shouldPlaySweepingSound(player, weapon)) {
                ci.cancel();
            }
        }
    }

    @Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;sweepAttack()V"), cancellable = true)
    private void malum$removeSweepingParticle(Entity target, CallbackInfo ci, @Local ItemStack weapon) {
        var item = weapon.getItem();
        if (item instanceof ICustomMeleeDamageTypeItem customDamageItem) {
            Player player = (Player) (Object) this;
            if (!customDamageItem.shouldDisplaySweepingParticle(player, weapon)) {
                ci.cancel();
            }
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
