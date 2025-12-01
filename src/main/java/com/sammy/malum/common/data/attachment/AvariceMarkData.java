package com.sammy.malum.common.data.attachment;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sammy.malum.common.geas.pact.infernal.ProspectorGeas;
import com.sammy.malum.common.item.curiosities.curios.sets.prospector.CurioProspectorBelt;
import com.sammy.malum.core.handlers.GeasEffectHandler;
import com.sammy.malum.registry.common.MalumAttachmentTypes;
import com.sammy.malum.registry.common.MalumMobEffects;
import com.sammy.malum.registry.common.MalumTags;
import com.sammy.malum.registry.common.magic.MalumGeasEffectTypes;
import com.sammy.malum.visual_effects.GeasParticleEffects;
import io.netty.buffer.*;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.*;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;
import net.neoforged.neoforge.event.level.BlockDropsEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import team.lodestar.lodestone.helpers.EntityHelper;

import java.util.function.Function;

public class AvariceMarkData {

    public static Codec<AvariceMarkData> CODEC = RecordCodecBuilder.create(obj -> obj.group(
            Codec.BOOL.optionalFieldOf("hasProspectorMark", false).forGetter(AvariceMarkData::hasProspectorMark),
            Codec.INT.optionalFieldOf("carriedAvarice", 0).forGetter(AvariceMarkData::getCarriedAvarice)
    ).apply(obj, AvariceMarkData::new));

    public static StreamCodec<ByteBuf, AvariceMarkData> STREAM_CODEC = ByteBufCodecs.fromCodec(AvariceMarkData.CODEC);
    private boolean hasAvariceMark;
    private int carriedAvarice;

    private AvariceMarkData(boolean hasAvariceMark, int carriedAvarice) {
        this.hasAvariceMark = hasAvariceMark;
    }

    public AvariceMarkData() {
    }

    public boolean hasProspectorMark() {
        return hasAvariceMark;
    }

    public int getCarriedAvarice() {
        return carriedAvarice;
    }

    public static void applyAvariceMark(BlockDropsEvent event) {
        if (event.getBreaker() instanceof LivingEntity breaker) {
            int amount = getAppliedAvarice(breaker);
            if (amount > 0) {
                for (ItemEntity drop : event.getDrops()) {
                    tryMarkEntity(drop, amount);
                }
            }
        }
    }

    public static int getAppliedAvarice(LivingEntity entity) {
        boolean hasPact = ProspectorGeas.hasProspectorPact(entity);
        boolean hasBelt = CurioProspectorBelt.hasProspectorBelt(entity);
        int amount = hasPact ? 1 : 0;
        if (hasBelt) {
            if (entity.getRandom().nextFloat() < 0.5f) {
                amount++;
            }
        }
        return amount;
    }

    public void tickData(Entity entity) {
        if (entity.level().isClientSide) {
            GeasParticleEffects.avariceItemParticles(entity.level(), entity);
        }
    }

    public void enableMark(int amount) {
        this.hasAvariceMark = true;
        this.carriedAvarice = amount;
    }

    public static void entityTick(EntityTickEvent.Pre event) {
        var entity = event.getEntity();
        entity.getExistingData(MalumAttachmentTypes.AVARICE_MARK).ifPresent(d -> d.tickData(entity));
    }

    public static void pickupItem(ItemEntityPickupEvent.Post event) {
        ItemEntity entity = event.getItemEntity();
        entity.getExistingData(MalumAttachmentTypes.AVARICE_MARK).ifPresent(data -> {
            if (data.hasProspectorMark()) {
                applyAvarice(event.getPlayer(), data.carriedAvarice);
            }
        });
    }

    public static void popResourceAndMarkEntity(Level level, BlockPos pos, ItemStack stack, int amount) {
        double d0 = (double) EntityType.ITEM.getHeight() / 2.0;
        double d1 = (double) pos.getX() + 0.5 + Mth.nextDouble(level.random, -0.25, 0.25);
        double d2 = (double) pos.getY() + 0.5 + Mth.nextDouble(level.random, -0.25, 0.25) - d0;
        double d3 = (double) pos.getZ() + 0.5 + Mth.nextDouble(level.random, -0.25, 0.25);
        Block.popResource(level, () -> tryMarkEntity(new ItemEntity(level, d1, d2, d3, stack), amount), stack);
    }

    public static ItemEntity tryMarkEntity(ItemEntity entity, int amount) {
        if (entity.getItem().is(MalumTags.ItemTags.PROSPECTORS_TREASURE)) {
            entity.getData(MalumAttachmentTypes.AVARICE_MARK).enableMark(amount);
            entity.syncData(MalumAttachmentTypes.AVARICE_MARK);
        }
        return entity;
    }

    public static void applyAvarice(LivingEntity target, int amount) {
        var effect = MalumMobEffects.AVARICE;
        var instance = target.getEffect(effect);
        if (instance == null) {
            target.addEffect(new MobEffectInstance(effect, 200, amount-1, true, true, true));
        } else {
            EntityHelper.amplifyEffect(instance, target, amount, 9);
            EntityHelper.extendEffect(instance, target, 100, 1200);
        }
        if (GeasEffectHandler.hasGeasEffect(target, MalumGeasEffectTypes.PACT_OF_THE_PROSPECTOR)) {
            target.heal(amount*2);
        }
    }
}