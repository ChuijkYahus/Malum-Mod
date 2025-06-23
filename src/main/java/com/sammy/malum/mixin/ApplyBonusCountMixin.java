package com.sammy.malum.mixin;

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

    @ModifyVariable(
            method = "run",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;getItemEnchantmentLevel(Lnet/minecraft/core/Holder;Lnet/minecraft/world/item/ItemStack;)I",
                    ordinal = 0),
            index = 4
    )
    private int malum$applyEnchantBonus(int enchantmentLevel, ItemStack stack,
                                         LootContext lootContext) {
        if (enchantment.is(Enchantments.FORTUNE)) {
            if (lootContext.getParamOrNull(LootContextParams.THIS_ENTITY) instanceof LivingEntity entity) {
                return enchantmentLevel + ProspectorsGreedEffect.addFortune(entity);
            }
        }
        return enchantmentLevel;
    }
}