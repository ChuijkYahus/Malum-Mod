package com.sammy.malum.common.data.attachment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sammy.malum.config.*;
import com.sammy.malum.registry.common.*;
import io.netty.buffer.*;
import net.minecraft.network.codec.*;
import net.minecraft.util.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import team.lodestar.lodestone.helpers.SoundHelper;

public class StaffAbilityData {

    public static Codec<StaffAbilityData> CODEC = RecordCodecBuilder.create(obj -> obj.group(
            Codec.INT.optionalFieldOf("staffCharge", 0).forGetter(c -> c.staffChargeDebt),
            Codec.FLOAT.optionalFieldOf("staffChargeProgress", 0f).forGetter(c -> c.staffChargeDebtCooldown)
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
            reduceStaffChargeCooldown(livingEntity, 1);
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

    public void consumeStaffCharge(LivingEntity livingEntity) {
        staffChargeDebt = Math.min(staffChargeDebt+3, getStaffChargeLimit(livingEntity));
        setDirty(true);
    }

    public int consumeAllStaffCharges(LivingEntity livingEntity) {
        int toll = Mth.floor((getAvailableStaffCharges(livingEntity)) / 3f);
        staffChargeDebt = staffChargeDebt + toll * 3 - 3;
        setDirty(true);
        return toll;
    }


    public void reduceStaffChargeCooldown(LivingEntity livingEntity, int staffChargeProgress) {
        this.staffChargeDebtCooldown -= staffChargeProgress;
        if (staffChargeDebtCooldown <= 0) {
            reduceStaffChargeDebt(livingEntity);
            staffChargeDebtCooldown += getStaffChargeCooldown(livingEntity);
        }
    }

    public void reduceStaffChargeDebt(LivingEntity livingEntity) {
        if (staffChargeDebt > 0) {
            staffChargeDebt--;
            if (!(livingEntity instanceof Player player) || !player.isCreative()) {
                double pitchOffset = 1.5f - (Mth.ceil(staffChargeDebt) % 3) * 0.25f;
                var soundType = staffChargeDebt % 3 == 0 ? MalumSoundEvents.SPELL_CHARGE_FULL : MalumSoundEvents.SPELL_CHARGE_GROW;
                SoundHelper.playSound(livingEntity, soundType.get(), 0.75f, (float) (1f + pitchOffset));
            }
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
        return Mth.floor(livingEntity.getAttribute(MalumAttributes.CHARGE_CAPACITY).getValue()) * 3;
    }

    public static float getStaffChargeCooldown(LivingEntity living) {
        return getStaffChargeCooldown(living.getAttributeValue(MalumAttributes.CHARGE_RECOVERY_RATE));
    }
    public static int getStaffChargeCooldown(double recoverySpeed) {
        return Mth.floor(CommonConfig.STAFF_CHARGE_RATE.getConfigValue() / recoverySpeed);
    }
}