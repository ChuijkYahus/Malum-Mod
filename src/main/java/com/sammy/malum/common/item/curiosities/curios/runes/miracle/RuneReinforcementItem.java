package com.sammy.malum.common.item.curiosities.curios.runes.miracle;

import com.google.common.collect.*;
import com.sammy.malum.MalumMod;
import com.sammy.malum.compat.irons_spellbooks.IronsSpellsCompat;
import com.sammy.malum.core.helpers.ComponentHelper;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.item.*;
import top.theillusivec4.curios.api.*;

import java.util.function.Consumer;

public class RuneReinforcementItem extends MiracleRuneCurioItem {

    public RuneReinforcementItem(Properties builder) {
        super(builder, MalumSpiritTypes.ARCANE_SPIRIT);
    }

    @Override
    public void addAttributeModifiers(Multimap<Holder<Attribute>, AttributeModifier> map, SlotContext slotContext, ItemStack stack) {
        var id = MalumMod.malumPath("reinforcement_rune");
        addAttributeModifier(map, MalumAttributes.SOUL_WARD_CAPACITY,
                new AttributeModifier(id, 6f, AttributeModifier.Operation.ADD_VALUE));
        addAttributeModifier(map, MalumAttributes.SOUL_WARD_INTEGRITY,
                new AttributeModifier(id, 0.25, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    }

}