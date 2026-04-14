package com.sammy.malum.common.data.attachment;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sammy.malum.common.block.curiosities.artifice.gust_igniter.*;
import com.sammy.malum.common.block.curiosities.artifice.gust_igniter.wind_tunnel.*;
import com.sammy.malum.core.handlers.*;
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
            WindTunnelMotionData.CODEC.listOf().optionalFieldOf("strength", Collections.emptyList()).forGetter(WindTunnelData::getInfluence),
            Codec.INT.optionalFieldOf("activeTime", 0).forGetter(WindTunnelData::getActiveTime)
    ).apply(obj, WindTunnelData::new));

    public static StreamCodec<ByteBuf, WindTunnelData> STREAM_CODEC = ByteBufCodecs.fromCodec(WindTunnelData.CODEC);

    private final HashMap<BlockPos, WindTunnelMotionData> influence = new HashMap<>();
    private int activeTime;

    private WindTunnelData(List<WindTunnelMotionData> influence, int activeTime) {
        for (WindTunnelMotionData data : influence) {
            this.influence.put(data.source, data);
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
        for (WindTunnelMotionData data : values) {
            var source = data.source;
            var area = data.area;
            var direction = data.direction;
            if (source == null || area == null || direction == null) {
                continue;
            }
            if (entity instanceof Player player) {
                if (player.onGround() && player.isCrouching()) {
                    continue;
                }
            }
            if (!(level.getBlockEntity(source) instanceof GustIgniterBlockEntity igniter)) {
                continue;
            }
            if (!WindTunnelBlock.isActive(level.getBlockState(source))) {
                continue;
            }
            if (!area.intersects(entity.getBoundingBox())) {
                continue;
            }
            if (!WindTunnelHandler.isInArea(entity, area, direction, igniter.windTunnels)) {
                continue;
            }
            float x = direction.getStepX();
            float y = direction.getStepY();
            float z = direction.getStepZ();
            var axis = direction.getAxis();
            boolean isX = axis.equals(Direction.Axis.X);
            boolean isY = axis.equals(Direction.Axis.Y);
            boolean isZ = axis.equals(Direction.Axis.Z);
            float xFriction = isX ? 0.8f : 1f;
            float yFriction = isY ? 0.8f : 0.5f;
            float zFriction = isZ ? 0.8f : 1f;
            var addedVelocity = new Vec3(x, y, z).scale(data.strength);
            var movement = entity.getDeltaMovement();
            movement = movement.add(addedVelocity);
            if (axis.isHorizontal()) {
                var center = area.getCenter();
                center = new Vec3(
                        isX ? position.x : center.x,
                        (isY ? position.y : center.y) - entity.getBbHeight() / 2f,
                        isZ ? position.z : center.z
                );
                var toCenter = center.subtract(position);
                var centerVelocity = toCenter.normalize().scale(toCenter.length() * 0.2f * data.strength);
                movement = movement.add(centerVelocity);
            }
            movement = movement.multiply(xFriction, yFriction, zFriction);
            entity.setDeltaMovement(movement);
            toRemove.remove(data);
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
            if (influence.get(pos).strength == strength) {
                return false;
            }
        }
        var area = igniter.windArea;
        var direction = igniter.windDirection;
        if (igniter.modified) {
            direction = direction.getOpposite();
        }
        influence.put(pos, new WindTunnelMotionData(pos, area, direction, strength));
        return true;
    }

    public float getGravityMultiplier() {
        return 1 - Math.min(activeTime, 4) / 4f;
    }

    public List<WindTunnelMotionData> getInfluence() {
        return new ArrayList<>(influence.values());
    }

    public int getActiveTime() {
        return activeTime;
    }

    public record WindTunnelMotionData(BlockPos source, AABB area, Direction direction, float strength) {

        public static final Codec<AABB> AABB_CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.DOUBLE.fieldOf("min_x").forGetter(aabb -> aabb.minX),
                Codec.DOUBLE.fieldOf("min_y").forGetter(aabb -> aabb.minY),
                Codec.DOUBLE.fieldOf("min_z").forGetter(aabb -> aabb.minZ),
                Codec.DOUBLE.fieldOf("max_x").forGetter(aabb -> aabb.maxX),
                Codec.DOUBLE.fieldOf("max_y").forGetter(aabb -> aabb.maxY),
                Codec.DOUBLE.fieldOf("max_z").forGetter(aabb -> aabb.maxZ)
        ).apply(instance, AABB::new));

        public static final Codec<WindTunnelMotionData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                BlockPos.CODEC.fieldOf("source").forGetter(WindTunnelMotionData::source),
                AABB_CODEC.fieldOf("area").forGetter(WindTunnelMotionData::area),
                Direction.CODEC.fieldOf("direction").forGetter(WindTunnelMotionData::direction),
                Codec.FLOAT.fieldOf("strength").forGetter(WindTunnelMotionData::strength)
        ).apply(instance, WindTunnelMotionData::new));
    }
}