package com.sammy.malum.common.item.curiosities.curios.runes.madness;

import com.sammy.malum.*;
import com.sammy.malum.common.item.curiosities.curios.runes.AbstractRuneCurioItem;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.*;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.living.*;
import team.lodestar.lodestone.handlers.*;
import top.theillusivec4.curios.api.*;

import java.util.function.Consumer;

public class RuneRadialEmpowermentItem extends AbstractRuneCurioItem implements ItemEventHandler.IEventResponder {

    private static final ResourceLocation MODIFIER = MalumMod.malumPath("radial_empowerment_rune");
    public RuneRadialEmpowermentItem(Properties builder) {
        super(builder, MalumSpiritTypes.WICKED_SPIRIT);
    }

    @Override
    public void addExtraTooltipLines(Consumer<Component> consumer) {
        consumer.accept(ComponentHelper.positiveCurioEffect("scythe_chain"));
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        super.onUnequip(slotContext, newStack, stack);
        LivingEntity entity = slotContext.entity();
        final AttributeInstance attribute = entity.getAttribute(Attributes.SWEEPING_DAMAGE_RATIO);
        if (attribute != null) {
            attribute.removeModifier(MODIFIER);
        }
    }

    @Override
    public void outgoingDamageEvent(LivingIncomingDamageEvent event, LivingEntity attacker, LivingEntity target, ItemStack stack) {
        final DamageSource source = event.getSource();
        if (source.is(MalumTags.DamageTypeTags.IS_SCYTHE_MELEE) && !source.is(MalumDamageTypes.SCYTHE_SWEEP)) {
            var attribute = attacker.getAttribute(Attributes.SWEEPING_DAMAGE_RATIO);
            if (attribute == null) {
                return;
            }
            float amount = target.getHealth() < target.getMaxHealth() * 0.33f ? 0.4f : 0.2f;
            attribute.removeModifier(MODIFIER);
            attribute.addTransientModifier(new AttributeModifier(MODIFIER, amount, AttributeModifier.Operation.ADD_VALUE));
        }
    }
}
