package com.sammy.malum.common.item.curiosities.curios;

import com.google.common.collect.*;
import com.sammy.malum.registry.common.sound.*;
import net.minecraft.core.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.item.*;
import team.lodestar.lodestone.helpers.*;
import top.theillusivec4.curios.api.*;
import top.theillusivec4.curios.api.type.capability.*;

import java.util.function.*;

public abstract class AbstractMalumCurioItem extends Item implements ICurioItem {

    public enum MalumTrinketType {
        CLOTH(MalumSoundEvents.CLOTH_TRINKET_EQUIP),
        ORNATE(MalumSoundEvents.ORNATE_TRINKET_EQUIP),
        GILDED(MalumSoundEvents.GILDED_TRINKET_EQUIP),
        ALCHEMICAL(MalumSoundEvents.ALCHEMICAL_TRINKET_EQUIP),
        ROTTEN(MalumSoundEvents.ROTTEN_TRINKET_EQUIP),
        METALLIC(MalumSoundEvents.METALLIC_TRINKET_EQUIP),
        VOID(MalumSoundEvents.VOID_TRINKET_EQUIP),
        RUNE(MalumSoundEvents.RUNE_EQUIP),
        TOTEMIC_RUNE(MalumSoundEvents.TOTEMIC_RUNE_EQUIP),
        VOID_RUNE(MalumSoundEvents.VOID_RUNE_EQUIP);
        final Supplier<SoundEvent> sound;

        MalumTrinketType(Supplier<SoundEvent> sound) {
            this.sound = sound;
        }
    }

    public final MalumTrinketType type;

    public AbstractMalumCurioItem(Properties properties, MalumTrinketType type) {
        super(properties);
        this.type = type;
    }

    public void addAttributeModifiers(Multimap<Holder<Attribute>, AttributeModifier> map, SlotContext slotContext, ItemStack stack) {
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> map = LinkedHashMultimap.create();
        addAttributeModifiers(map, slotContext, stack);
        return map;
    }

    @Override
    public void onEquipFromUse(SlotContext slotContext, ItemStack stack) {
        final LivingEntity livingEntity = slotContext.entity();
        livingEntity.level().playSound(null, livingEntity.blockPosition(), type.sound.get(), SoundSource.PLAYERS, 1.0f, RandomHelper.randomBetween(livingEntity.getRandom(), 0.9f, 1.1f));
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }

    public void addAttributeModifier(Multimap<Holder<Attribute>, AttributeModifier> map, Holder<Attribute> attribute, AttributeModifier modifier) {
        map.put(attribute, modifier);
    }
}