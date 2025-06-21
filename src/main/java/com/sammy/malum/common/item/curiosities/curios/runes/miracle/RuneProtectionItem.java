package com.sammy.malum.common.item.curiosities.curios.runes.miracle;

import com.google.common.collect.*;
import com.sammy.malum.*;
import com.sammy.malum.common.item.curiosities.curios.runes.*;
import com.sammy.malum.core.helpers.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import team.lodestar.lodestone.handlers.*;
import top.theillusivec4.curios.api.*;

import java.util.function.Consumer;

public class RuneProtectionItem extends MiracleRuneCurioItem implements ItemEventHandler.IEventResponder {

    public RuneProtectionItem(Properties builder) {
        super(builder, MalumSpiritTypes.EARTHEN_SPIRIT);
    }

    @Override
    public void addAttributeModifiers(Multimap<Holder<Attribute>, AttributeModifier> map, SlotContext slotContext, ItemStack stack) {
        addAttributeModifier(map, Attributes.ARMOR,
                new AttributeModifier(MalumMod.malumPath("protection_rune"), 0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    }
}
