package com.sammy.malum.core.handlers;

import com.sammy.malum.*;
import com.sammy.malum.common.data.attachment.soul_data.GeasSoulData;
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
                var data = living.getData(MalumAttachmentTypes.GEAS_SOUL_INFO);
                data.setDirty(true);
            }
        }
    }

    public static void entityTick(EntityTickEvent.Pre event) {
        if (event.getEntity() instanceof LivingEntity living) {
            getGeasEffects(living).forEach(e -> {
                e.updateAttributes(living);
                e.update(event, living);
            });
        }
    }

    public static boolean addGeasEffect(LivingEntity entity, GeasEffectType geas) {
        return getGeasData(entity).tryAddGeasEffect(entity, geas);
    }

    public static boolean hasGeasEffect(LivingEntity entity, Holder<GeasEffectType> type) {
        return getGeasData(entity).hasGeasEffect(entity, type);
    }

    public static boolean hasGeasEffect(LivingEntity entity, GeasEffectType type) {
        return getGeasData(entity).hasGeasEffect(entity, type);
    }

    public static boolean removeGeasEffect(LivingEntity entity, GeasEffectType geas) {
        return getGeasData(entity).removeGeasEffect(geas);
    }

    public static List<ItemStack> getGeasItemStacks(LivingEntity entity) {
        return getGeasData(entity).getGeasItemStacks(entity);
    }

    public static GeasEffect getGeasEffect(LivingEntity entity, Holder<GeasEffectType> type) {
        return getGeasData(entity).getGeasEffect(entity, type);
    }

    public static List<GeasEffect> getGeasEffects(LivingEntity entity) {
        return getGeasData(entity).getGeasEffects(entity);
    }

    public static GeasEffect getEquippedGeasEffectFromStack(LivingEntity entity, ItemStack stack) {
        var component = getStoredGeasEffect(stack).orElse(null);
        if (component == null) {
            return null;
        }
        var geas = component.geasEffectType();
        return getGeasData(entity).getGeasEffect(entity, geas);
    }

    public static GeasSoulData getGeasData(LivingEntity entity) {
        return entity.getData(MalumAttachmentTypes.GEAS_SOUL_INFO);
    }

    @SuppressWarnings("DataFlowIssue")
    public static Optional<GeasDataComponent> getStoredGeasEffect(ItemStack stack) {
        if (!stack.has(MalumDataComponents.GEAS_EFFECT)) {
            return Optional.empty();
        }
        var component = stack.get(MalumDataComponents.GEAS_EFFECT);
        if (component.isInvalid()) {
            return Optional.empty();
        }
        return Optional.of(component);
    }
}