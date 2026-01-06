package com.sammy.malum.mixin;

import com.sammy.malum.common.effect.ascension.*;
import com.sammy.malum.common.geas.pact.aqueous.*;
import com.sammy.malum.common.geas.pact.earthen.ProfaneAsceticGeas;
import com.sammy.malum.common.item.curiosities.curios.sets.rotten.CurioVoraciousRing;
import com.sammy.malum.common.item.curiosities.curios.sets.weeping.CurioGruesomeConcentrationRing;
import com.sammy.malum.core.handlers.*;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(method = "getDamageAfterArmorAbsorb", at = @At("HEAD"), cancellable = true)
    private void malum$getDamageAfterArmorAbsorb(DamageSource damageSource, float damageAmount, CallbackInfoReturnable<Float> cir) {
        LivingEntity livingEntity = (LivingEntity) ((Object) (this));
        var optional = MalumAttributeEventHandler.modifyMagicDamageArmorPiercing(livingEntity, damageSource, damageAmount);
        optional.ifPresent(cir::setReturnValue);
    }

    @Inject(method = "eat(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/food/FoodProperties;)Lnet/minecraft/world/item/ItemStack;", at = @At("HEAD"))
    private void malum$eat(Level level, ItemStack food, FoodProperties foodProperties, CallbackInfoReturnable<ItemStack> cir) {
        LivingEntity livingEntity = (LivingEntity) ((Object)(this));
        if (food.getFoodProperties(livingEntity) != null) {
            CurioVoraciousRing.onEat(level, livingEntity, food);
            ProfaneAsceticGeas.onEat(level, livingEntity, food);
            SelfCareGeas.onEat(level, livingEntity, food);
        }
    }

    @ModifyArg(method = "travel",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;setDeltaMovement(DDD)V"),
            slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;handleRelativeFrictionAndCalculateMovement(Lnet/minecraft/world/phys/Vec3;F)Lnet/minecraft/world/phys/Vec3;")),
    index = 1)
    private double malum$travel(double y) {
        LivingEntity livingEntity = (LivingEntity) ((Object)(this));
        return LiftedEffect.modifyVelocity(livingEntity, y);
    }
}
