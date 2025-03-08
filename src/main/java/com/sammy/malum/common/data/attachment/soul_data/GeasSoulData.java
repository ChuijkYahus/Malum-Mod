package com.sammy.malum.common.data.attachment.soul_data;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sammy.malum.*;
import com.sammy.malum.core.handlers.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.item.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.minecraft.util.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.entity.living.*;
import top.theillusivec4.curios.api.*;

import java.util.*;

public class GeasSoulData {

    public static final ResourceLocation GEAS_CURIO_SLOT = MalumMod.malumPath("geas_curio_slot");

    public static final Codec<GeasSoulData> CODEC = RecordCodecBuilder.create(obj -> obj.group(
            ItemStack.CODEC.listOf().optionalFieldOf("geasEffects").forGetter(sd -> Optional.of(sd.geasStacks))
    ).apply(obj, GeasSoulData::new));

    private final List<ItemStack> geasStacks = new ArrayList<>();
    private final Map<ItemStack, GeasEffect> cachedGeasEffects = new WeakHashMap<>();
    private boolean dirtyGeasEffects;

    public GeasSoulData() {
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private GeasSoulData(Optional<List<ItemStack>> geasStacks) {
        geasStacks.ifPresent(s -> s.forEach(this::addGeasEffect));
    }

    public List<ItemStack> getGeasItems() {
        return geasStacks;
    }

    public void removeGeasEffect(ItemStack geas) {
        geasStacks.remove(geas);
        dirtyGeasEffects = true;
    }

    public boolean tryAddGeasEffect(LivingEntity target, ItemStack geas) {
        var attribute = target.getAttribute(AttributeRegistry.GEAS_LIMIT);
        if (attribute == null) {
            return false;
        }
        int limit = Mth.ceil(attribute.getValue());
        if (geasStacks.size() < limit) {
            var copy = geas.copy();
            var success = addGeasEffect(copy);
            CuriosApi.getCuriosInventory(target).ifPresent(h -> {
                h.addPermanentSlotModifier("geas", GEAS_CURIO_SLOT, geasStacks.size(), AttributeModifier.Operation.ADD_VALUE);
                h.getSlots();
                h.setEquippedCurio("geas", geasStacks.size()-1, copy);
            });
            return success;
        }
        return false;
    }

    public boolean addGeasEffect(ItemStack geas) {
        if (!geas.has(DataComponentRegistry.GEAS_EFFECT)) {
            throw new IllegalArgumentException("Itemstack does not have a geas effect");
        }
        var storedGeas = GeasEffectHandler.getStoredGeasEffect(geas).createEffectInstance();
        if (cachedGeasEffects.values().stream().anyMatch(e -> e.type.equals(storedGeas.type))) {
            return false;
        }
        geasStacks.add(geas);
        dirtyGeasEffects = true;
        return true;
    }

    public boolean hasGeasEffect(LivingEntity living, Holder<GeasEffectType> type) {
        return getGeasEffect(living, type) != null;
    }

    public Map.Entry<ItemStack, GeasEffect> getGeasEffect(LivingEntity entity, Holder<GeasEffectType> type) {
        return getGeasEffects(entity).entrySet().stream().filter(e -> e.getValue().type.equals(type.value())).findFirst().orElse(null);
    }

    @SuppressWarnings("DataFlowIssue")
    public Map<ItemStack, GeasEffect> getGeasEffects(LivingEntity entity) {
        if (dirtyGeasEffects) {
            cachedGeasEffects.values().forEach(e -> e.removeAttributeModifiers(entity));
            cachedGeasEffects.clear();
            for (ItemStack geas : geasStacks) {
                cachedGeasEffects.put(geas, geas.get(DataComponentRegistry.GEAS_EFFECT).createEffectInstance());
            }
            dirtyGeasEffects = false;
        }
        return cachedGeasEffects;
    }
}