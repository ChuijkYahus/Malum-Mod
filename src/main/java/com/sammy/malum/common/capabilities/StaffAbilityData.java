package com.sammy.malum.common.capabilities;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sammy.malum.config.*;
import com.sammy.malum.registry.common.*;
import io.netty.buffer.*;
import net.minecraft.network.codec.*;
import net.minecraft.util.*;
import net.minecraft.world.entity.*;

public class StaffAbilityData {

    public static Codec<StaffAbilityData> CODEC = RecordCodecBuilder.create(obj -> obj.group(
            Codec.INT.fieldOf("staffCharge").forGetter(c -> c.staffChargeDebt),
            Codec.FLOAT.fieldOf("staffChargeProgress").forGetter(c -> c.staffChargeDebtCooldown)
    ).apply(obj, StaffAbilityData::new));

    public static StreamCodec<ByteBuf, StaffAbilityData> STREAM_CODEC = ByteBufCodecs.fromCodec(StaffAbilityData.CODEC);

    private int staffChargeDebt;
    private float staffChargeDebtCooldown;

    private boolean isDirty;

    public StaffAbilityData() {
    }

    public StaffAbilityData(int staffChargeDebt, float staffChargeDebtCooldown) {
        this.staffChargeDebt = staffChargeDebt;
        this.staffChargeDebtCooldown = staffChargeDebtCooldown;
    }

    public void tickData(LivingEntity livingEntity) {
        if (staffChargeDebt > 0) {
            reduceStaffChargeCooldown(1);
        }
    }

    public int getStaffChargeDebt() {
        return staffChargeDebt;
    }

    public int getAvailableStaffCharges(LivingEntity livingEntity) {
        return getStaffChargeLimit(livingEntity) - staffChargeDebt;
    }

    public boolean canUseStaff(LivingEntity livingEntity) {
        return getAvailableStaffCharges(livingEntity) >= 3;
    }

    public void consumeStaffCharge() {
        staffChargeDebt += 3;
        setDirty(true);
    }

    public int consumeAllStaffCharges(LivingEntity livingEntity) {
        int existingDebt = staffChargeDebt;
        staffChargeDebt = getStaffChargeLimit(livingEntity);
        setDirty(true);
        return (staffChargeDebt - existingDebt) / 3;
    }

    public void reduceStaffChargeCooldown(int staffChargeProgress) {
        this.staffChargeDebtCooldown -= staffChargeProgress;
        if (staffChargeDebtCooldown <= 0) {
            reduceStaffChargeDebt();
            staffChargeDebtCooldown = 80;
        }
    }

    public void reduceStaffChargeDebt() {
        if (staffChargeDebt > 0) {
            staffChargeDebt--;
            setDirty(true);
        }
    }

    public boolean isDirty() {
        return isDirty;
    }

    public void setDirty(boolean dirty) {
        isDirty = dirty;
    }

    public static int getStaffChargeLimit(LivingEntity livingEntity) {
        return Mth.floor(livingEntity.getAttribute(AttributeRegistry.CHARGE_CAPACITY).getValue() * 3);
    }

    public static float getStaffChargeCooldown(LivingEntity living) {
        return getStaffChargeCooldown(living.getAttributeValue(AttributeRegistry.CHARGE_RECOVERY_RATE));
    }
    public static int getStaffChargeCooldown(double recoverySpeed) {
        return Mth.floor(CommonConfig.STAFF_CHARGE_RATE.getConfigValue() / recoverySpeed);
    }
}