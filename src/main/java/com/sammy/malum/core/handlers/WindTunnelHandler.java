package com.sammy.malum.core.handlers;

import com.sammy.malum.*;
import com.sammy.malum.common.block.curiosities.gust_igniter.*;
import com.sammy.malum.common.block.curiosities.gust_igniter.wind_tunnel.*;
import com.sammy.malum.common.data.attachment.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.minecraft.server.level.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.phys.*;
import net.neoforged.neoforge.event.tick.*;
import org.jetbrains.annotations.*;

import java.util.*;
import java.util.function.*;

public class WindTunnelHandler {

    public static final float MAX_STRENGTH = 32;
    public static final ResourceLocation GRAVITY_MODIFIER_ID = MalumMod.malumPath("wind_tunnel_reduced_gravity");

    public static void entityTick(EntityTickEvent.Pre event) {
        var entity = event.getEntity();
        entity.getExistingData(MalumAttachmentTypes.WIND_TUNNEL_INFO).ifPresent(d -> d.tickData(entity));
        if (entity instanceof LivingEntity livingEntity) {
            updateLivingGravity(livingEntity);
        }
    }

    public static double modifyEntityGravity(Entity entity, double original) {
        return original * entity.getExistingData(MalumAttachmentTypes.WIND_TUNNEL_INFO).map(WindTunnelData::getGravityMultiplier).orElse(1f);
    }

    public static void updateLivingGravity(LivingEntity entity) {
        var gravity = entity.getAttribute(Attributes.GRAVITY);
        if (gravity != null) {
            if (gravity.hasModifier(GRAVITY_MODIFIER_ID)) {
                gravity.removeModifier(GRAVITY_MODIFIER_ID);
            }
            getGravityAttributeModifier(entity).ifPresent(gravity::addTransientModifier);
        }
    }

    public static Optional<AttributeModifier> getGravityAttributeModifier(LivingEntity entity) {
        if (!entity.hasData(MalumAttachmentTypes.WIND_TUNNEL_INFO)) {
            return Optional.empty();
        }
        var data = entity.getData(MalumAttachmentTypes.WIND_TUNNEL_INFO);
        float multiplier = data.getGravityMultiplier();
        if (multiplier >= 1) {
            return Optional.empty();
        }
        return Optional.of(new AttributeModifier(GRAVITY_MODIFIER_ID, multiplier - 1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    }

    public static boolean modifyTunnels(Level level, GustIgniterBlockEntity igniter, Function<BlockState, BlockState> stateModifier) {
        var unboundTunnels = new ArrayList<WindTunnelBlockEntity>();
        for (BlockPos tunnelPos : igniter.windTunnels) {
            if (level.getBlockEntity(tunnelPos) instanceof WindTunnelBlockEntity boundTunnel) {
                boundTunnel.unbind();
                unboundTunnels.add(boundTunnel);
            }
        }
        igniter.windTunnels.clear();

        var windTunnels = findWindTunnels(level, igniter);
        if (windTunnels.isEmpty()) {
            revertTunnels(level, unboundTunnels);
            return false;
        }
        var tunnelPositions = new HashSet<BlockPos>();
        Direction windDirection = null;
        igniter.limiter = windTunnels.values().stream().mapToInt(e -> e.findLimit(igniter.strength)).min().orElse(igniter.strength);
        for (Map.Entry<BlockPos, WindTunnelBlockEntity> entry : windTunnels.entrySet()) {
            var tunnelPos = entry.getKey();
            var tunnel = entry.getValue();
            var tunnelState = level.getBlockState(tunnelPos);
            var modifiedState = stateModifier.apply(tunnelState);
            level.setBlock(tunnelPos, modifiedState, 2);
            if (WindTunnelBlock.isActive(modifiedState)) {
                igniter.bind(tunnel);
                tunnelPositions.add(tunnelPos);
            } else {
                igniter.unbind(tunnel);
            }
            unboundTunnels.remove(tunnel);
            windDirection = modifiedState.getValue(WindTunnelBlock.FACING);
        }
        revertTunnels(level, unboundTunnels);
        if (tunnelPositions.isEmpty()) {
            igniter.windArea = null;
            igniter.windDirection = null;
            return false;
        }
        igniter.windArea = getWindArea(igniter, tunnelPositions, windDirection);
        igniter.windDirection = windDirection;
        return true;
    }

    public static void revertTunnels(Level level, ArrayList<WindTunnelBlockEntity> tunnels) {
        for (WindTunnelBlockEntity tunnel : tunnels) {
            level.setBlock(tunnel.getBlockPos(), tunnel.getBlockState().setValue(WindTunnelBlock.POWERED, false), 2);
        }
    }

    public static boolean isInArea(Entity entity, AABB windArea, Direction windDirection, Set<BlockPos> windTunnels) {
        if (windArea == null || windDirection == null) {
            return false;
        }
        var position = entity.position();
        var center = position.add(0, entity.getBbHeight() / 2f, 0);
        var axis = windDirection.getAxis();
        for (BlockPos tunnel : windTunnels) {
            var tunnelCenter = tunnel.getCenter();
            double x = axis.equals(Direction.Axis.X) ? tunnelCenter.x : center.x;
            double y = axis.equals(Direction.Axis.Y) ? tunnelCenter.y : center.y;
            double z = axis.equals(Direction.Axis.Z) ? tunnelCenter.z : center.z;
            var offsetPosition = new Vec3(x, y, z);
            if (offsetPosition.distanceTo(tunnelCenter) < 0.75f) {
                return true;
            }
        }
        return false;
    }

    private static @NotNull AABB getWindArea(GustIgniterBlockEntity igniter, HashSet<BlockPos> tunnelPositions, Direction tunnelDirection) {
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, minZ = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE, maxZ = Integer.MIN_VALUE;
        for (BlockPos tunnelPosition : tunnelPositions) {
            int x = tunnelPosition.getX();
            int y = tunnelPosition.getY();
            int z = tunnelPosition.getZ();

            if (x < minX) minX = x;
            if (y < minY) minY = y;
            if (z < minZ) minZ = z;

            if (x > maxX) maxX = x;
            if (y > maxY) maxY = y;
            if (z > maxZ) maxZ = z;
        }
        var area = new AABB(minX, minY, minZ, maxX + 1, maxY + 1, maxZ + 1);
        int x = tunnelDirection.getStepX();
        int y = tunnelDirection.getStepY();
        int z = tunnelDirection.getStepZ();
        var offset = new Vec3(x, y, z).scale(igniter.getTunnelLength());
        return area.expandTowards(offset).inflate(0.25f);
    }

    public static Map<BlockPos, WindTunnelBlockEntity> findWindTunnels(Level level, GustIgniterBlockEntity igniter) {
        var igniterState = igniter.getBlockState();
        var igniterPos = igniter.getBlockPos();
        var facing = igniterState.getValue(AbstractGustGizmoBlock.FACING);
        var startPos = igniterPos.relative(facing);

        if ((level.getBlockEntity(startPos) instanceof WindTunnelBlockEntity startingTunnel)) {
            if (!startingTunnel.isRemoved()) {
                return findWindTunnels(level, startingTunnel, t -> t.canIgnite(igniter));
            }
        }
        return Collections.emptyMap();
    }

    public static HashMap<BlockPos, WindTunnelBlockEntity> findWindTunnels(Level level, WindTunnelBlockEntity startingTunnel, Predicate<WindTunnelBlockEntity> condition) {
        var startPos = startingTunnel.getBlockPos();
        var facing = startingTunnel.getBlockState().getValue(AbstractGustGizmoBlock.FACING);
        var visited = new HashSet<BlockPos>();
        var result = new HashMap<BlockPos, WindTunnelBlockEntity>();
        var queue = new ArrayDeque<BlockPos>();
        if (condition.test(startingTunnel)) {
            result.put(startPos, startingTunnel);
            queue.add(startPos);
            visited.add(startPos);
        }

        var mutable = startPos.mutable();
        while (!queue.isEmpty()) {
            var pos = queue.poll();
            for (Direction direction : Direction.values()) {
                mutable.set(pos).move(direction);
                if (!visited.contains(mutable) && level.getBlockEntity(mutable) instanceof WindTunnelBlockEntity nextTunnel) {
                    if (nextTunnel.isRemoved()) {
                        continue;
                    }
                    var otherFacing = nextTunnel.getBlockState().getValue(WindTunnelBlock.FACING);
                    if (otherFacing != facing) {
                        continue;
                    }
                    if (condition.test(nextTunnel)) {
                        var immutable = mutable.immutable();
                        visited.add(immutable);
                        result.put(immutable, nextTunnel);
                        queue.add(immutable);
                    }
                }
            }
        }
        return result;
    }
}
