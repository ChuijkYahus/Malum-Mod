package com.sammy.malum.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.sammy.malum.common.effect.geas.*;
import net.minecraft.core.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.parameters.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(ApplyBonusCount.class)
public class ApplyBonusCountMixin {

    @Shadow
    @Final
    private Holder<Enchantment> enchantment;

    @WrapOperation(
            method = "run",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;getItemEnchantmentLevel(Lnet/minecraft/core/Holder;Lnet/minecraft/world/item/ItemStack;)I",
                    ordinal = 0)
    )
    private int malum$applyEnchantBonus(Holder<Enchantment> enchantment, ItemStack stack, Operation<Integer> original, @Local(argsOnly = true) LootContext lootContext) {
        var enchantmentLevel = original.call(enchantment, stack);
        if (enchantment.is(Enchantments.FORTUNE)) {
            if (lootContext.getParamOrNull(LootContextParams.THIS_ENTITY) instanceof LivingEntity entity) {
                var position = lootContext.getParamOrNull(LootContextParams.ORIGIN);
                return enchantmentLevel + AvariceEffect.addFortune(entity, position, enchantmentLevel);
            }
        }
        return enchantmentLevel;
    }
}