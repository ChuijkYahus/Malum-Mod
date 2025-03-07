package com.sammy.malum.common.item;

import com.sammy.malum.core.handlers.*;
import com.sammy.malum.core.systems.spirit.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.item.*;
import com.sammy.malum.visual_effects.*;
import net.minecraft.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.neoforged.neoforge.event.entity.player.*;
import team.lodestar.lodestone.handlers.screenparticle.*;
import team.lodestar.lodestone.systems.particle.screen.*;

import java.util.*;
import java.util.concurrent.atomic.*;
import java.util.function.*;

public class GeasItem extends Item implements ParticleEmitterHandler.ItemParticleSupplier{
    public GeasItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        final ItemStack stack = player.getItemInHand(usedHand);
        if (!level.isClientSide) {
            if (GeasEffectHandler.addGeasEffect(player, stack)) {
                player.swing(usedHand);
                stack.shrink(1);
            }
            return InteractionResultHolder.consume(stack);
        }
        return InteractionResultHolder.fail(stack);
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
        tooltipConsumer.accept(geas.getDescription().withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        tooltipConsumer.accept(Component.translatable("malum.gui.slot").withStyle(ChatFormatting.GOLD)
                .append(Component.translatable("malum.gui.geas.any").withStyle(ChatFormatting.YELLOW)));
        tooltipConsumer.accept(Component.empty());
        tooltipConsumer.accept(Component.translatable("malum.gui.geas.sworn").withStyle(ChatFormatting.GOLD));
        geas.getDefaultInstance().addTooltipComponents(event.getEntity(), tooltipConsumer, event.getFlags());
    }
}