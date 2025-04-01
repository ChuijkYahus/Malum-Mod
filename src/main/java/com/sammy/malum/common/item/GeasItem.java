package com.sammy.malum.common.item;

import com.sammy.malum.common.data.component.*;
import com.sammy.malum.core.handlers.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.item.*;
import com.sammy.malum.visual_effects.*;
import net.minecraft.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.neoforged.neoforge.event.entity.player.*;
import org.jetbrains.annotations.*;
import team.lodestar.lodestone.handlers.screenparticle.*;
import team.lodestar.lodestone.systems.particle.screen.*;
import top.theillusivec4.curios.api.*;
import top.theillusivec4.curios.api.type.capability.*;

import java.util.*;
import java.util.concurrent.atomic.*;
import java.util.function.*;

public class GeasItem extends Item implements ParticleEmitterHandler.ItemParticleSupplier, ICurioItem {

    public static final String GEAS = "malum.gui.geas.type";
    public static final String SWORN = "malum.gui.geas.sworn";

    public GeasItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        var storedGeasEffect = GeasEffectHandler.getStoredGeasEffect(player.getItemInHand(usedHand));
        var geas = storedGeasEffect.geasEffectType();
        if (GeasEffectHandler.hasGeasEffect(player, geas)) {
            GeasEffectHandler.removeGeasEffect(player, geas);
        } else {
            GeasEffectHandler.addGeasEffect(player, geas);
        }

        return super.use(level, player, usedHand);
    }

    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        return false;
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return false;
    }

    @Override
    public @NotNull ICurio.DropRule getDropRule(SlotContext slotContext, DamageSource source, boolean recentlyHit, ItemStack stack) {
        return ICurio.DropRule.ALWAYS_KEEP;
    }

    @Override
    public List<Component> getSlotsTooltip(List<Component> tooltips, TooltipContext context, ItemStack stack) {
        return Collections.emptyList();
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (stack.has(DataComponentRegistry.GEAS_EFFECT)) {
            var geasType = GeasEffectHandler.getStoredGeasEffect(stack).geasEffectType().getDefaultInstance().type;
            var component = tooltipComponents.getFirst().copy()
                    .append(": [")
                    .append(Component.translatable(geasType.getLangKey()).withStyle(ChatFormatting.GOLD))
                    .append("]");
            tooltipComponents.set(0, component);
        }
    }

    @Override
    public void spawnEarlyParticles(ScreenParticleHolder target, Level level, float partialTick, ItemStack stack, float x, float y) {
        ScreenParticleEffects.spawnGeasItemScreenParticles(target, level, 1f, partialTick);
    }

    @Override
    public void spawnLateParticles(ScreenParticleHolder target, Level level, float partialTick, ItemStack stack, float x, float y) {
        ScreenParticleEffects.spawnSpiritShardScreenParticles(target, SpiritTypeRegistry.ELDRITCH_SPIRIT);
    }

    public static void addGeasTooltip(ItemTooltipEvent event) {
        ItemStack itemStack = event.getItemStack();
        if (!itemStack.has(DataComponentRegistry.GEAS_EFFECT)) {
            return;
        }

        var geas = GeasEffectHandler.getStoredGeasEffect(itemStack).geasEffectType();
        List<Component> tooltip = event.getToolTip();
        var index = new AtomicInteger(1);
        Consumer<Component> tooltipConsumer = c -> tooltip.add(index.getAndIncrement(), c);
        tooltipConsumer.accept(
                Component.translatable(geas.getDescription()).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        tooltipConsumer.accept(
                Component.translatable("malum.gui.slot").withStyle(ChatFormatting.GOLD).append(Component.translatable(GEAS).withStyle(ChatFormatting.YELLOW)));
        tooltipConsumer.accept(
                Component.empty());
        tooltipConsumer.accept(
                Component.translatable(SWORN).withStyle(ChatFormatting.GOLD));

        geas.getDefaultInstance().addTooltipComponents(event.getEntity(), tooltipConsumer, event.getFlags());
    }
}