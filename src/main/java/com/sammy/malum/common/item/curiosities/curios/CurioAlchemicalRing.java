package com.sammy.malum.common.item.curiosities.curios;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.sammy.malum.core.systems.item.IMalumEventResponderItem;
import com.sammy.malum.registry.common.MobEffectRegistry;
import com.sammy.malum.registry.common.item.ItemRegistry;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.registries.ForgeRegistries;
import team.lodestar.lodestone.helpers.CurioHelper;
import team.lodestar.lodestone.helpers.EntityHelper;
import team.lodestar.lodestone.registry.common.LodestoneAttributeRegistry;

import java.util.UUID;

public class CurioAlchemicalRing extends MalumCurioItem implements IMalumEventResponderItem {

    public CurioAlchemicalRing(Properties builder) {
        super(builder);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(String identifier, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> map = HashMultimap.create();
        map.put(LodestoneAttributeRegistry.MAGIC_RESISTANCE.get(), new AttributeModifier(uuids.computeIfAbsent(0, (i) -> UUID.randomUUID()), "Curio magic resistance", 1, AttributeModifier.Operation.ADDITION));
        return map;
    }

    public static void onPotionApplied(MobEffectEvent.Added event) {
        LivingEntity entity = event.getEntity();
        if (event.getOldEffectInstance() == null && CurioHelper.hasCurioEquipped(entity, ItemRegistry.RING_OF_ALCHEMICAL_MASTERY.get())) {
            MobEffectInstance effect = event.getEffectInstance();
            MobEffect type = effect.getEffect();
            float multiplier = MobEffectRegistry.ALCHEMICAL_PROFICIENCY_MAP.getOrDefault(ForgeRegistries.MOB_EFFECTS.getKey(type), 1f);
            if (type.isBeneficial()) {
                EntityHelper.extendEffect(effect, entity, (int) (effect.getDuration()*0.25f*multiplier));
            }
            else if (type.getCategory().equals(MobEffectCategory.HARMFUL)) {
                EntityHelper.shortenEffect(effect, entity, (int) (effect.getDuration()*0.33f*multiplier));
            }
        }
    }

    @Override
    public void pickupSpirit(LivingEntity collector, ItemStack stack, double arcaneResonance) {
        collector.getActiveEffectsMap().forEach((e, i) -> {
            float multiplier = MobEffectRegistry.ALCHEMICAL_PROFICIENCY_MAP.getOrDefault(ForgeRegistries.MOB_EFFECTS.getKey(e), 1f);
            if (e.isBeneficial()) {
                int base = 40 +(int)(arcaneResonance*20);
                EntityHelper.extendEffect(i, collector, (int) (base*multiplier), 1200);
            }
            else if (e.getCategory().equals(MobEffectCategory.HARMFUL)) {
                int base = 60 +(int)(arcaneResonance*30);
                EntityHelper.shortenEffect(i, collector, (int) (base*multiplier));
            }
        });
    }

    @Override
    public boolean isGilded() {
        return true;
    }
}