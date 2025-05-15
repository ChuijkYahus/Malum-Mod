package com.sammy.malum.common.geas.pact.wicked;

import com.sammy.malum.common.entity.scythe.ScytheBoomerangEntity;
import com.sammy.malum.common.item.curiosities.weapons.scythe.MalumScytheItem;
import com.sammy.malum.common.worldevent.DelayedDamageWorldEvent;
import com.sammy.malum.core.handlers.SoulDataHandler;
import com.sammy.malum.core.helpers.ComponentHelper;
import com.sammy.malum.core.systems.geas.GeasEffect;
import com.sammy.malum.registry.common.DamageTypeRegistry;
import com.sammy.malum.registry.common.MalumGeasEffectTypeRegistry;
import com.sammy.malum.registry.common.ParticleEffectTypeRegistry;
import com.sammy.malum.registry.common.SoundRegistry;
import com.sammy.malum.registry.common.item.EnchantmentRegistry;
import com.sammy.malum.registry.common.tag.DamageTypeTagRegistry;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import team.lodestar.lodestone.handlers.WorldEventHandler;
import team.lodestar.lodestone.helpers.DamageTypeHelper;
import team.lodestar.lodestone.registry.common.LodestoneAttributes;

import java.util.function.Consumer;

import static net.minecraft.world.entity.EquipmentSlot.MAINHAND;

public class BerserkerGeas extends GeasEffect {

    public BerserkerGeas() {
        super(MalumGeasEffectTypeRegistry.PACT_OF_THE_REAPER.get());
    }

    @Override
    public void addTooltipComponents(LivingEntity entity, Consumer<Component> tooltipAcceptor, TooltipFlag tooltipFlag) {
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("damage_buildup"));
        tooltipAcceptor.accept(ComponentHelper.positiveGeasEffect("damage_release"));
        tooltipAcceptor.accept(ComponentHelper.negativeGeasEffect("more_damage"));
        super.addTooltipComponents(entity, tooltipAcceptor, tooltipFlag);
    }
}