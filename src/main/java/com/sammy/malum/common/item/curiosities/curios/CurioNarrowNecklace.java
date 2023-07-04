package com.sammy.malum.common.item.curiosities.curios;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.sammy.malum.registry.common.AttributeRegistry;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;

import java.util.UUID;

public class CurioNarrowNecklace extends MalumCurioItem
{
    public CurioNarrowNecklace(Properties builder)
    {
        super(builder);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(String identifier, ItemStack stack)
    {
        Multimap<Attribute, AttributeModifier> map = HashMultimap.create();
        map.put(AttributeRegistry.SCYTHE_PROFICIENCY.get(), new AttributeModifier(uuids.computeIfAbsent(0, (i) -> UUID.randomUUID()), "Curio scythe proficiency", 4f, AttributeModifier.Operation.ADDITION));
        return map;
    }
    @Override
    public boolean isOrnate()
    {
        return true;
    }

}