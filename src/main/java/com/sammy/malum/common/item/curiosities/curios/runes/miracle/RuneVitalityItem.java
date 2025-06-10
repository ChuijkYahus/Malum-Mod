package com.sammy.malum.common.item.curiosities.curios.runes.miracle;

import com.google.common.collect.*;
import com.sammy.malum.*;
import com.sammy.malum.common.item.curiosities.curios.runes.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.core.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

public class RuneVitalityItem extends AbstractRuneCurioItem {

    public RuneVitalityItem(Properties builder) {
        super(builder, MalumSpiritTypes.SACRED_SPIRIT);
    }

    @Override
    public void addAttributeModifiers(Multimap<Holder<Attribute>, AttributeModifier> map, SlotContext slotContext, ItemStack stack) {
        addAttributeModifier(map, MalumAttributes.HEALING_MULTIPLIER,
                new AttributeModifier(MalumMod.malumPath("vitality_rune"), 0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    }
}
