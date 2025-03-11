package com.sammy.malum.common.effect.aura;

import com.sammy.malum.*;
import com.sammy.malum.registry.common.MobEffectRegistry;
import com.sammy.malum.registry.common.SpiritTypeRegistry;
import net.minecraft.resources.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import team.lodestar.lodestone.helpers.ColorHelper;

public class InfernalAura extends MobEffect {
    public InfernalAura() {
        super(MobEffectCategory.BENEFICIAL, ColorHelper.getColor(SpiritTypeRegistry.INFERNAL_SPIRIT.getPrimaryColor()));
        final ResourceLocation id = MalumMod.malumPath("infernal_aura");
        addAttributeModifier(Attributes.ATTACK_SPEED, id, 0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(Attributes.BLOCK_BREAK_SPEED, id, 0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }
}