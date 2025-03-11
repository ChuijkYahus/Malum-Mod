package com.sammy.malum.common.data.attachment.soul_data;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sammy.malum.*;
import com.sammy.malum.common.packets.*;
import com.sammy.malum.core.handlers.*;
import com.sammy.malum.core.systems.geas.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.item.*;
import io.netty.buffer.*;
import net.minecraft.core.*;
import net.minecraft.network.codec.*;
import net.minecraft.resources.*;
import net.minecraft.server.level.*;
import net.minecraft.util.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.network.*;
import top.theillusivec4.curios.api.*;

import java.util.*;

public class GeasSoulData {

    public static final ResourceLocation GEAS_CURIO_SLOT = MalumMod.malumPath("geas_curio_slot");

    public static final Codec<GeasSoulData> CODEC = RecordCodecBuilder.create(obj -> obj.group(
            ItemStack.CODEC.listOf().optionalFieldOf("geasEffects").forGetter(sd -> Optional.of(sd.geasStacks))
    ).apply(obj, GeasSoulData::new));

    public static StreamCodec<ByteBuf, GeasSoulData> STREAM_CODEC = ByteBufCodecs.fromCodec(GeasSoulData.CODEC);

    private final List<ItemStack> geasStacks = new ArrayList<>();
    private final Map<ItemStack, GeasEffect> cachedGeasEffects = new WeakHashMap<>();
    private boolean isDirty;

    public GeasSoulData() {
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private GeasSoulData(Optional<List<ItemStack>> geasStacks) {
        geasStacks.ifPresent(s -> s.forEach(this::addGeasEffect));
    }

    public List<ItemStack> getGeasItems() {
        return geasStacks;
    }

    public boolean isDirty() {
        return isDirty;
    }

    public void setDirty(boolean dirty) {
        isDirty = dirty;
    }

    public void removeGeasEffect(ItemStack geas) {
        geasStacks.remove(geas);
        setDirty(true);
    }

    public boolean tryAddGeasEffect(LivingEntity target, ItemStack geas) {
        var attribute = target.getAttribute(AttributeRegistry.GEAS_LIMIT);
        if (attribute == null) {
            return false;
        }
        int limit = Mth.ceil(attribute.getValue());
        if (geasStacks.size() < limit) {
            var copy = geas.copy();
            return addGeasEffect(copy);
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
        setDirty(true);
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
        if (isDirty) {
            cachedGeasEffects.values().forEach(e -> e.removeAttributeModifiers(entity));
            cachedGeasEffects.clear();
            for (ItemStack geas : geasStacks) {
                cachedGeasEffects.put(geas, geas.get(DataComponentRegistry.GEAS_EFFECT).createEffectInstance());
            }
            if (!entity.level().isClientSide) {
                PacketDistributor.sendToPlayersTrackingEntityAndSelf(entity, new SyncGeasDataPayload(entity.getId(), this));
            }
            CuriosApi.getCuriosInventory(entity).ifPresent(h -> h.addPermanentSlotModifier("geas", GEAS_CURIO_SLOT, geasStacks.size(), AttributeModifier.Operation.ADD_VALUE));
            setDirty(false);
        }
        return cachedGeasEffects;
    }
}