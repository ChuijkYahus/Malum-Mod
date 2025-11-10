package com.sammy.malum.common.data.attachment;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sammy.malum.common.payloads.*;
import com.sammy.malum.config.*;
import com.sammy.malum.registry.common.*;
import io.netty.buffer.*;
import net.minecraft.network.codec.*;
import net.minecraft.util.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.player.*;
import net.neoforged.neoforge.network.*;
import team.lodestar.lodestone.helpers.*;

import java.util.*;

public class SoulWardData {

    public static Codec<SoulWardData> CODEC = RecordCodecBuilder.create(obj -> obj.group(
            Codec.DOUBLE.fieldOf("soulWard").forGetter(SoulWardData::getSoulWard),
            Codec.INT.fieldOf("soulWardProgress").forGetter(SoulWardData::getSoulWardCooldown),
            Codec.FLOAT.fieldOf("appliedCooldownMultiplier").forGetter(SoulWardData::getAppliedCooldownMultiplier)
    ).apply(obj, SoulWardData::new));

    public static StreamCodec<ByteBuf, SoulWardData> STREAM_CODEC = ByteBufCodecs.fromCodec(SoulWardData.CODEC);

    private double soulWard;
    private int soulWardCooldown;
    private float appliedCooldownMultiplier = 1f;

    private boolean isDirty;

    public SoulWardData() {
    }

    public SoulWardData(double soulWard, int soulWardCooldown, float appliedCooldownMultiplier) {
        this.soulWard = soulWard;
        this.soulWardCooldown = soulWardCooldown;
        this.appliedCooldownMultiplier = appliedCooldownMultiplier;
    }

    public void tickData(LivingEntity living) {
        var capacity = living.getAttribute(MalumAttributes.SOUL_WARD_CAPACITY);
        if (capacity != null) {
            if (getSoulWard() < capacity.getValue()) {
                if (soulWardCooldown > 0) {
                    soulWardCooldown--;
                    tryCorrectCooldown(living);
                }
                if (soulWardCooldown <= 0) {
                    recoverSoulWard(living, 1);
                }
            }
            if (getSoulWard() > capacity.getValue()) {
                setSoulWard(capacity.getValue());
            }
        }
        if (isDirty) {
            if (!living.level().isClientSide) {
                living.syncData(MalumAttachmentTypes.SOUL_WARD);
            }
            isDirty = false;
        }
    }

    @SuppressWarnings("DataFlowIssue")
    public void recoverSoulWard(LivingEntity entity, double amount) {
        var capacity = entity.getAttribute(MalumAttributes.SOUL_WARD_CAPACITY);
        if (getSoulWard() < capacity.getValue()) {
            var multiplier = Optional.ofNullable(entity.getAttribute(MalumAttributes.SOUL_WARD_RECOVERY_MULTIPLIER)).map(AttributeInstance::getValue).orElse(1.0);
            var previousSoulward = soulWard;
            addSoulWard(amount * multiplier);
            if (soulWard > previousSoulward) {
                if (!(entity instanceof Player player) || !player.isCreative()) {
                    var sound = soulWard >= capacity.getValue() ? MalumSoundEvents.SOUL_WARD_CHARGE : MalumSoundEvents.SOUL_WARD_GROW;
                    double pitchOffset = (soulWard / capacity.getValue()) * 0.5f + (Mth.ceil(soulWard) % 3) * 0.25f;
                    SoundHelper.playSound(entity, sound.get(), 0.25f, (float) (1f + pitchOffset));
                }
            }
        }
        addCooldown(entity, 1f);
    }

    public void addSoulWard(double added) {
        setSoulWard(soulWard + added);
    }

    public void reduceSoulWard(double removed) {
        setSoulWard(soulWard - removed);
    }

    public void setSoulWard(double soulWard) {
        this.soulWard = Math.max(soulWard, 0);
        setDirty();
    }

    public void tryCorrectCooldown(LivingEntity living) {
        double newCooldown = getSoulWardCooldown(living) * appliedCooldownMultiplier;
        if (soulWardCooldown > newCooldown) {
            soulWardCooldown = Mth.floor(newCooldown);
            setDirty();
        }
    }

    public void addCooldown(LivingEntity living, float multiplier) {
        double newCooldown = getSoulWardCooldown(living) * multiplier;
        if (soulWardCooldown < newCooldown) {
            soulWardCooldown = Mth.floor(newCooldown);
            appliedCooldownMultiplier = multiplier;
            setDirty();
        }
    }

    public double getSoulWard() {
        return soulWard;
    }

    public int getSoulWardCooldown() {
        return soulWardCooldown;
    }

    public float getAppliedCooldownMultiplier() {
        return appliedCooldownMultiplier;
    }

    public boolean isDepleted() {
        return soulWard <= 0;
    }

    public void setDirty() {
        isDirty = true;
    }

    public float getSoulWardCooldown(LivingEntity living) {
        double recoveryRate = living.getAttributeValue(MalumAttributes.SOUL_WARD_RECOVERY_RATE);
        var cooldownDuration = CommonConfig.SOUL_WARD_RATE.getConfigValue();
        return Mth.floor(cooldownDuration / recoveryRate);
    }
}