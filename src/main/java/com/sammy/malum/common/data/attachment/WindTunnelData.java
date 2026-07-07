package com.sammy.malum.common.data.attachment;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sammy.malum.common.block.curiosities.artifice.elemental_artifice.ArtificeBlockConnectionData;
import com.sammy.malum.common.block.curiosities.artifice.elemental_artifice.aerial.GustIgniterBlockEntity;
import com.sammy.malum.common.block.curiosities.artifice.elemental_artifice.aerial.WindTunnelEntityInfluenceData;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.visual_effects.networked.wind_gust.*;
import io.netty.buffer.*;
import net.minecraft.core.*;
import net.minecraft.network.codec.*;
import net.minecraft.server.level.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.phys.*;

import java.awt.*;
import java.util.*;
import java.util.List;

public class WindTunnelData {

    public static Codec<WindTunnelData> CODEC = RecordCodecBuilder.create(obj -> obj.group(
            WindTunnelEntityInfluenceData.CODEC.listOf().optionalFieldOf("strength", Collections.emptyList()).forGetter(WindTunnelData::getInfluence),
            Codec.INT.optionalFieldOf("activeTime", 0).forGetter(WindTunnelData::getActiveTime)
    ).apply(obj, WindTunnelData::new));

    public static StreamCodec<ByteBuf, WindTunnelData> STREAM_CODEC = ByteBufCodecs.fromCodec(WindTunnelData.CODEC);

    private final HashMap<BlockPos, WindTunnelEntityInfluenceData> influence = new HashMap<>();
    private int activeTime;

    private WindTunnelData(List<WindTunnelEntityInfluenceData> influence, int activeTime) {
        for (WindTunnelEntityInfluenceData data : influence) {
            this.influence.put(data.array().getConnectionOwner(), data);
        }
        this.activeTime = activeTime;
    }

    public WindTunnelData() {
    }

    public void tickData(Entity entity) {
        var level = entity.level();
        var position = entity.position();
        var values = influence.values();
        var toRemove = new ArrayList<>(values);
        for (WindTunnelEntityInfluenceData motionData : values) {
            if (entity instanceof Player player) {
                if (player.onGround() && player.isCrouching()) {
                    continue;
                }
            }
            var area = motionData.area();
            if (!area.intersects(entity.getBoundingBox())) {
                continue;
            }
            var array = motionData.array();
            if (array.isOutOfBounds(entity)) {
                continue;
            }
            var direction = array.getSharedDirection();
            float strength = motionData.strength();
            float x = direction.getStepX() * strength;
            float y = direction.getStepY() * strength;
            float z = direction.getStepZ() * strength;
            var axis = direction.getAxis();
            boolean isX = axis.equals(Direction.Axis.X);
            boolean isY = axis.equals(Direction.Axis.Y);
            boolean isZ = axis.equals(Direction.Axis.Z);
            float xFriction = isX ? 0.8f : 1f;
            float yFriction = isY ? 0.8f : 0.5f;
            float zFriction = isZ ? 0.8f : 1f;
            var movement = entity.getDeltaMovement();
            movement = movement.add(x, y, z);
            if (axis.isHorizontal()) {
                var center = area.getCenter();
                center = new Vec3(
                        isX ? position.x : center.x,
                        (isY ? position.y : center.y) - entity.getBbHeight() / 2f,
                        isZ ? position.z : center.z
                );
                var toCenter = center.subtract(position);
                var centerVelocity = toCenter.normalize().scale(toCenter.length() * 0.2f * strength);
                movement = movement.add(centerVelocity);
            }
            movement = movement.multiply(xFriction, yFriction, zFriction);
            entity.setDeltaMovement(movement);
            toRemove.remove(motionData);
        }
        values.removeAll(toRemove);
        if (influence.isEmpty()) {
            entity.removeData(MalumAttachmentTypes.WIND_TUNNEL_INFO);
            return;
        }
        if (level instanceof ServerLevel serverLevel) {
            boolean isPlayer = entity instanceof Player;
            int interval = isPlayer ? 4 : 20;
            if (activeTime % interval == 0) {
                MalumParticleEffectTypes.WIND_TRAIL
                        .createEffect(entity)
                        .customData(new WindTrailParticleEffect.WindTrailParticleEffectData(entity.getId(), 2, 4))
                        .color(new Color(224, 230, 255))
                        .spawn(serverLevel);
                entity.syncData(MalumAttachmentTypes.WIND_TUNNEL_INFO);
            }
            if (!isPlayer) {
                entity.hurtMarked = true;
            }
        }
        if (entity instanceof Player player) {
            player.resetFallDistance();
        }
        activeTime++;
    }

    public boolean addInfluence(GustIgniterBlockEntity igniter) {
        var pos = igniter.getBlockPos();
        float strength = igniter.getTunnelStrength();
        if (influence.containsKey(pos)) {
            if (influence.get(pos).strength() == strength) {
                return false;
            }
        }
        ArtificeBlockConnectionData data = igniter.getConnectionData();
        influence.put(pos, new WindTunnelEntityInfluenceData(data.getArray(), data.getDefinedArea(), strength));
        return true;
    }

    public float getGravityMultiplier() {
        return 1 - Math.min(activeTime, 4) / 4f;
    }

    public List<WindTunnelEntityInfluenceData> getInfluence() {
        return new ArrayList<>(influence.values());
    }

    public int getActiveTime() {
        return activeTime;
    }

}