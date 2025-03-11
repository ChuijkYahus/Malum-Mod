package com.sammy.malum.core.handlers;

import com.sammy.malum.*;
import com.sammy.malum.common.data.component.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.core.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.entity.*;
import net.neoforged.neoforge.event.tick.*;
import team.lodestar.lodestone.handlers.*;
import top.theillusivec4.curios.api.*;
import top.theillusivec4.curios.api.extensions.*;

import java.util.*;

public class GeasEffectHandler {
    public static final ItemEventHandler.EventResponderSource GEAS_EFFECTS = ItemEventHandler.registerLookup(
            new ItemEventHandler.EventResponderSource(MalumMod.malumPath("geas_effects"),
            GeasEffectHandler::getGeasItemStacks,
            GeasEffectHandler::getEquippedGeasEffectFromStack));

    public static void registerSlotExtensions(RegisterCuriosExtensionsEvent event) {
        event.registerSlotExtension(new ICurioSlotExtension() {
            @Override
            public ItemStack getDisplayStack(SlotContext slotContext, ItemStack defaultStack) {
                var geasItemStacks = getGeasItemStacks(slotContext.entity());
                if (geasItemStacks == null) {
                    return ItemStack.EMPTY;
                }
                if (geasItemStacks.size() > slotContext.index()) {
                    return geasItemStacks.get(slotContext.index());
                }
                return ItemStack.EMPTY;
            }
        }, "geas");
    }

    public static void syncGeas(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof LivingEntity living) {
            var level = living.level();
            if (!level.isClientSide) {
                var data = living.getData(AttachmentTypeRegistry.GEAS_SOUL_INFO);
                data.setDirty(true);
            }
        }
    }

    public static void entityTick(EntityTickEvent.Pre event) {
        if (event.getEntity() instanceof LivingEntity living) {
            final Collection<GeasEffect> geasEffects = getGeasEffects(living).values();
            geasEffects.forEach(e -> {
                e.updateAttribution(living);
                e.update(event, living);
            });
        }
    }

    public static boolean tryAddGeasEffect(LivingEntity entity, ItemStack stack) {
        return entity.getData(AttachmentTypeRegistry.GEAS_SOUL_INFO).tryAddGeasEffect(entity, stack);
    }

    public static List<ItemStack> getGeasItemStacks(LivingEntity entity) {
        return entity.getData(AttachmentTypeRegistry.GEAS_SOUL_INFO).getGeasItems();
    }

    public static boolean hasGeasEffect(LivingEntity entity, Holder<GeasEffectType> type) {
        return entity.getData(AttachmentTypeRegistry.GEAS_SOUL_INFO).hasGeasEffect(entity, type);
    }

    public static Map<ItemStack, GeasEffect> getGeasEffects(LivingEntity entity) {
        return entity.getData(AttachmentTypeRegistry.GEAS_SOUL_INFO).getGeasEffects(entity);
    }
    public static Map.Entry<ItemStack, GeasEffect> getGeasEffect(LivingEntity entity, Holder<GeasEffectType> type) {
        return entity.getData(AttachmentTypeRegistry.GEAS_SOUL_INFO).getGeasEffect(entity, type);
    }

    public static GeasEffect getEquippedGeasEffectFromStack(LivingEntity entity, ItemStack stack) {
        return getGeasEffects(entity).get(stack);
    }

    @SuppressWarnings("DataFlowIssue")
    public static GeasDataComponent getStoredGeasEffect(ItemStack stack) {
        if (!stack.has(DataComponentRegistry.GEAS_EFFECT)) {
            throw new IllegalArgumentException("Stack does not have an etching effect");
        }
        final GeasDataComponent geasDataComponent = stack.get(DataComponentRegistry.GEAS_EFFECT);
        if (geasDataComponent.isInvalid()) {
            stack.remove(DataComponentRegistry.GEAS_EFFECT);
            throw new IllegalArgumentException("Stack had a deprecated geas effect type.");
        }
        return geasDataComponent;
    }
}