package com.sammy.malum.common.effect.gluttony;

import com.sammy.malum.*;
import com.sammy.malum.compat.irons_spellbooks.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.resources.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.player.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.registry.common.*;

public class DesperateNeedEffect extends MobEffect {
    public DesperateNeedEffect() {
        super(MobEffectCategory.BENEFICIAL, ColorHelper.getColor(88, 86, 60));
        final ResourceLocation id = MalumMod.malumPath("desperate_need");
        addAttributeModifier(MalumAttributes.SCYTHE_PROFICIENCY, id, 0.1f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(LodestoneAttributes.MAGIC_RESISTANCE, id, -0.02f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(MalumAttributes.HEALING_MULTIPLIER, id, -0.02f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(Attributes.ARMOR, id, -0.02f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        IronsSpellsCompat.addDesperateNeedSpellPower(this);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyEffectTick(LivingEntity entityLivingBaseIn, int amplifier) {
        if (entityLivingBaseIn instanceof Player player) {
            player.causeFoodExhaustion(0.0075f * (amplifier + 1));
        }
        return true;
    }
}