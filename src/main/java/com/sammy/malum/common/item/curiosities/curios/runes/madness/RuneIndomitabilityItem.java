package com.sammy.malum.common.item.curiosities.curios.runes.madness;

import com.google.common.collect.*;
import com.sammy.malum.MalumMod;
import com.sammy.malum.common.item.curiosities.curios.runes.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.item.*;
import top.theillusivec4.curios.api.*;

public class RuneIndomitabilityItem extends AbstractRuneCurioItem {

    public RuneIndomitabilityItem(Properties builder) {
        super(builder, MalumSpiritTypes.EARTHEN_SPIRIT);
    }

    @Override
    public void addAttributeModifiers(Multimap<Holder<Attribute>, AttributeModifier> map, SlotContext slotContext, ItemStack stack) {
        addAttributeModifier(map, Attributes.KNOCKBACK_RESISTANCE,
                new AttributeModifier(MalumMod.malumPath("indomitability_rune"), 1f, AttributeModifier.Operation.ADD_VALUE));
    }
}