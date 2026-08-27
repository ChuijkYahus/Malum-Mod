package com.sammy.malum.common.data.attachment.gear;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sammy.malum.common.item.curiosities.weapons.greatsword.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.server.level.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.phys.*;

public class VindicativeBrandDashData {

    public static Codec<VindicativeBrandDashData> CODEC = RecordCodecBuilder.create(obj -> obj.group(
            Vec3.CODEC.optionalFieldOf("dash_direction", Vec3.ZERO).forGetter(c -> c.dashDirection),
            Codec.LONG.optionalFieldOf("dash_timing", -1L).forGetter(c -> c.dashTiming)
    ).apply(obj, VindicativeBrandDashData::new));

    protected Vec3 dashDirection;
    protected long dashTiming;

    public VindicativeBrandDashData() {
    }

    public VindicativeBrandDashData(Player player, int dashDuration) {
        this.dashDirection = player.getLookAngle();
        this.dashTiming = player.level().getGameTime() + dashDuration;
    }

    public VindicativeBrandDashData(Vec3 dashDirection, long dashTiming) {
        this.dashDirection = dashDirection;
        this.dashTiming = dashTiming;
    }

    public void tickData(Player player) {
        var weapon = player.getMainHandItem();
        if (weapon.getItem() instanceof VindicativeBrandSwordItem) {
            var velocity = dashDirection.scale(2.5f);
            player.setDeltaMovement(velocity);
            var level = player.level();
            if (level.getGameTime() < dashTiming) {
                return;
            }
        }
        if (player.level() instanceof ServerLevel level) {
            VindicativeBrandSwordItem.triggerDashAttack(level, player, weapon);
        }
        player.setDeltaMovement(Vec3.ZERO);
        player.removeData(MalumAttachmentTypes.VINDICATIVE_BRAND_DASH_DATA);
    }
}