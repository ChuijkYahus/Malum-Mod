package com.sammy.malum.common.item.curiosities.curios.runes.miracle;

import com.google.common.collect.Multimap;
import com.sammy.malum.MalumMod;
import com.sammy.malum.common.item.curiosities.curios.runes.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.registry.common.MalumSpiritTypes;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.function.*;

public class RuneDexterityItem extends AbstractRuneCurioItem {

    public RuneDexterityItem(Properties builder) {
        super(builder, MalumSpiritTypes.AERIAL_SPIRIT);
    }

    @Override
    public void addExtraTooltipLines(Consumer<Component> consumer) {
        consumer.accept(ComponentHelper.positiveCurioEffect("low_health_speed"));
    }

    @Override
    public void addAttributeModifiers(Multimap<Holder<Attribute>, AttributeModifier> map, SlotContext slotContext, ItemStack stack) {
        addAttributeModifier(map, Attributes.MOVEMENT_SPEED, getMovementSpeedBonus(slotContext.entity()));
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();
        if (livingEntity.level().getGameTime() % 5L == 0) {
            AttributeInstance attribute = livingEntity.getAttribute(Attributes.MOVEMENT_SPEED);
            if (attribute != null) {
                attribute.setDirty();
            }
        }
    }

    public AttributeModifier getMovementSpeedBonus(LivingEntity entity) {
        float value = 0.2f;
        if (entity != null) {
            final float health = entity.getHealth();
            final float maxHealth = entity.getMaxHealth();
            value = 0.2f * (2 - (health / maxHealth));
        }
        return new AttributeModifier(MalumMod.malumPath("rune_of_dexterity"), value, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }
}
