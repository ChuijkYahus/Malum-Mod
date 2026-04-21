package com.sammy.malum.common.item.curiosities.curios.runes.madness;

import com.google.common.collect.*;
import com.sammy.malum.MalumMod;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.item.*;
import top.theillusivec4.curios.api.*;

public class RuneSpellMasteryItem extends MadnessRuneCurioItem {

    public RuneSpellMasteryItem(Properties builder) {
        super(builder, MalumSpiritTypes.ARCANE_SPIRIT);
    }

    @Override
    public void addAttributeModifiers(Multimap<Holder<Attribute>, AttributeModifier> map, SlotContext slotContext, ItemStack stack) {
        var id = MalumMod.malumPath("spell_mastery_rune");
        addAttributeModifier(map, MalumAttributes.CHARGE_DURATION,
                new AttributeModifier(id, -0.5f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

    }
}