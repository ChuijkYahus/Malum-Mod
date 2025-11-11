package com.sammy.malum.common.effect;

import com.sammy.malum.*;
import com.sammy.malum.compat.irons_spellbooks.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.registry.common.*;

public class SilencedEffect extends MobEffect {
    public SilencedEffect() {
        super(MobEffectCategory.HARMFUL, ColorHelper.getColor(20, 14, 22));
        float ratio = -0.05f;
        var id = MalumMod.malumPath("silenced");
        addAttributeModifier(LodestoneAttributes.MAGIC_PROFICIENCY, id, ratio, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

        addAttributeModifier(MalumAttributes.SOUL_WARD_INTEGRITY, id, ratio, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(MalumAttributes.SOUL_WARD_CAPACITY, id, ratio, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(MalumAttributes.SOUL_WARD_RECOVERY_RATE, id, ratio, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(MalumAttributes.SOUL_WARD_RECOVERY_GAIN, id, ratio, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

        addAttributeModifier(MalumAttributes.ARCANE_RESONANCE, id, ratio, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        IronsSpellsCompat.addSilencedNegativeAttributeModifiers(this);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        pLivingEntity.getData(MalumAttachmentTypes.TOUCH_OF_DARKNESS).setAfflictionLevel(10 + pAmplifier * 4);
        return true;
    }
}