package com.sammy.malum.common.effect.rite;

import com.sammy.malum.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import team.lodestar.lodestone.helpers.*;

public class WickedEmpowerment extends MobEffect {
    public WickedEmpowerment() {
        super(MobEffectCategory.BENEFICIAL, ColorHelper.getColor(MalumSpiritTypes.WICKED_COLORS().primaryColor()));
        var id = MalumMod.malumPath("wicked_empowerment");
        addAttributeModifier(Attributes.MAX_HEALTH, id, 0.5, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(Attributes.ATTACK_DAMAGE, id, 0.5, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(Attributes.ATTACK_KNOCKBACK, id, 0.5, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }

    @Override
    public void onEffectAdded(LivingEntity livingEntity, int amplifier) {
        super.onEffectAdded(livingEntity, amplifier);
        livingEntity.heal(livingEntity.getMaxHealth());
    }
}