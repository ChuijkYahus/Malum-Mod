package com.sammy.malum.common.effect.rite.aura;

import com.sammy.malum.*;
import com.sammy.malum.registry.common.MalumMobEffects;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import team.lodestar.lodestone.helpers.ColorHelper;

public class FlowingGraspEffect extends MobEffect {
    public FlowingGraspEffect() {
        super(MobEffectCategory.BENEFICIAL, ColorHelper.getColor(MalumSpiritTypes.AQUEOUS_COLORS().primaryColor()));
        var id = MalumMod.malumPath("flowing_grasp");
        addAttributeModifier(Attributes.BLOCK_INTERACTION_RANGE, id, 0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }

    public static AABB growBoundingBox(Player player, AABB original) {
        MobEffectInstance effect = player.getEffect(MalumMobEffects.FLOWING_GRASP);
        if (effect != null) {
            original = original.inflate((effect.getAmplifier() + 1) * 1.2f);
        }
        return original;
    }
}