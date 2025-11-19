package com.sammy.malum.common.block.curiosities.gust_igniter;

import com.sammy.malum.common.block.curiosities.gust_igniter.wind_tunnel.*;
import com.sammy.malum.common.block.curiosities.redstone.*;
import com.sammy.malum.common.item.*;
import com.sammy.malum.core.handlers.*;
import com.sammy.malum.registry.common.*;
import com.sammy.malum.registry.common.block.*;
import net.minecraft.core.*;
import net.minecraft.core.particles.*;
import net.minecraft.nbt.*;
import net.minecraft.network.protocol.game.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.util.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.phys.*;
import org.jetbrains.annotations.*;
import team.lodestar.lodestone.helpers.*;

import java.util.*;
import java.util.stream.*;

import static com.sammy.malum.core.handlers.WindTunnelHandler.MAX_STRENGTH;

public class GustIgniterBlockEntity extends AbstractGustGizmoBlockEntity {

    public int strength = 1;
    public int limiter = -1;
    public boolean modified = false;

    public HashSet<BlockPos> windTunnels = new HashSet<>();
    public AABB windArea;
    public Direction windDirection;

    public GustIgniterBlockEntity(BlockEntityType<? extends GustIgniterBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public GustIgniterBlockEntity(BlockPos pos, BlockState state) {
        this(MalumBlockEntities.GUST_IGNITER.get(), pos, state);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        strength = pTag.getInt("strength");
        limiter = pTag.getInt("limiter");
        modified = pTag.getBoolean("modified");

        windTunnels.clear();
        var windData = pTag.getCompound("windArea");
        if (!windData.isEmpty()) {
            int amount = windData.getInt("windTunnelAmount");

            for (int i = 0; i < amount; i++) {
                BlockPos pos = NBTHelper.readBlockPos(windData.getCompound("windTunnelPosition_" + i));
                if (pos != null) {
                    windTunnels.add(pos);
                }
            }

            windArea = new AABB(
                    windData.getDouble("minX"),
                    windData.getDouble("minY"),
                    windData.getDouble("minZ"),
                    windData.getDouble("maxX"),
                    windData.getDouble("maxY"),
                    windData.getDouble("maxZ")
            );
            windDirection = Direction.byName(windData.getString("windDirection"));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("strength", strength);
        tag.putInt("limiter", limiter);
        tag.putBoolean("modified", modified);

        CompoundTag windData = new CompoundTag();
        if (!windTunnels.isEmpty()) {
            windData.putInt("windTunnelAmount", windTunnels.size());
            int index = 0;
            for (BlockPos pos : windTunnels) {
                windData.put("windTunnelPosition_" + index, NBTHelper.saveBlockPos(pos));
                index++;
            }
        }
        if (windArea != null) {
            windData.putDouble("minX", windArea.minX);
            windData.putDouble("minY", windArea.minY);
            windData.putDouble("minZ", windArea.minZ);
            windData.putDouble("maxX", windArea.maxX);
            windData.putDouble("maxY", windArea.maxY);
            windData.putDouble("maxZ", windArea.maxZ);
        }
        if (windDirection != null) {
            windData.putString("windDirection", windDirection.getName());
        }
        if (!windData.isEmpty()) {
            tag.put("windArea", windData);
        }
    }

    @Override
    public void setInfo(GustGizmoInfo info) {
        this.strength = info.strength();
        this.modified = info.modified();
    }

    @Override
    public void onBreak(@Nullable Player player) {
        deactivate();
    }

    @Override
    public InboundInfo<? extends OpenStateBlockEntity> resetState() {
        return new GustGizmoInfo(strength, modified);
    }

    @Override
    public void toggleState(ServerLevel level, boolean newValue, InboundInfo<?> inboundInfo) {
        super.toggleState(level, newValue, inboundInfo);
        WindTunnelHandler.modifyTunnels(level, this, b -> b.setValue(WindTunnelBlock.OPEN, newValue));
    }

    @Override
    public void serverTick(ServerLevel level) {
        if (windArea != null && windDirection != null) {
            var affected = level.getEntities(null, windArea);
            for (Entity entity : affected) {
                if (WindTunnelHandler.isInArea(entity, windArea, windDirection, windTunnels)) {
                    var data = entity.getData(MalumAttachmentTypes.WIND_TUNNEL_INFO);
                    if (data.addInfluence(this)) {
                        entity.syncData(MalumAttachmentTypes.WIND_TUNNEL_INFO);
                    }
                }
            }
        }
    }

    public void activate(boolean powered) {
        if (WindTunnelHandler.modifyTunnels(level, this, b -> b.setValue(WindTunnelBlock.POWERED, powered))) {
            return;
        }
        if (!getBlockState().getValue(WindTunnelBlock.POWERED) && powered) {
            createWindGust();
        }
    }

    public void deactivate() {
        WindTunnelHandler.modifyTunnels(level, this, b -> b.setValue(WindTunnelBlock.POWERED, false));
    }

    public void bind(WindTunnelBlockEntity tunnel) {
        tunnel.bind(this);
        windTunnels.add(tunnel.getBlockPos());
    }

    public void unbind(WindTunnelBlockEntity tunnel) {
        tunnel.unbind();
        windTunnels.remove(tunnel.getBlockPos());
    }

    public int getTunnelLength() {
        if (limiter == -1) {
            return strength;
        }
        return Math.min(strength, limiter);
    }

    public float getTunnelStrength() {
        return Mth.lerp(getTunnelLength()/MAX_STRENGTH, 0.05f, 0.2f);
    }

    public void createWindGust() {
        var state = getBlockState();
        var pos = getBlockPos();
        var facing = state.getValue(GustIgniterBlock.FACING);
        boolean isGlider = modified;
        float radius = 1.75f;
        float delta = strength / MAX_STRENGTH;
        float force = (isGlider ? 0.2f : 0.4f) + delta * 0.4f;
        var appliedEffect = isGlider ? MalumMobEffects.LIFTED : MalumMobEffects.ASCENSION;
        int effectDuration = isGlider ? 100 : 40;
        int effectAmplifier = isGlider ? 2 : 4;
        var explosionPos = pos.relative(facing).getCenter();
        var explosionAffectedEntities = WindNucleusItem.getExplosionAffectedEntities(level, null, explosionPos, radius);
        var velocityCache = explosionAffectedEntities.stream().collect(Collectors.toMap(e -> e, Entity::getDeltaMovement));
        level.explode(
                null,
                null,
                WindNucleusItem.EXPLOSION_DAMAGE_CALCULATOR,
                explosionPos.x(),
                explosionPos.y(),
                explosionPos.z(),
                radius,
                false,
                Level.ExplosionInteraction.TRIGGER,
                ParticleTypes.GUST_EMITTER_SMALL,
                ParticleTypes.GUST_EMITTER_LARGE,
                SoundEvents.WIND_CHARGE_BURST
        );

        for (Entity entity : explosionAffectedEntities) {
            var velocity = entity.getDeltaMovement();
            double appliedForce = force;
            if (velocity.length() < 0.1f) {
                appliedForce *= 1.5f;
            }
            if (velocity.y < 0) {
                appliedForce += Math.abs(velocity.y);
            }

            var direction = new Vec3(facing.getStepX(), facing.getStepY(), facing.getStepZ());
            var multiplier = new Vec3(0.25f, 0.25f, 0.25f).multiply(Math.abs(direction.x*4),Math.abs(direction.y*4),Math.abs(direction.z*4));

            var addedVelocity = direction.scale(appliedForce);
            var result = velocity.add(addedVelocity).multiply(multiplier);
            double limit = Math.min(result.length(), appliedForce) * 2;
            entity.setDeltaMovement(result.normalize().scale(limit));
            if (entity instanceof LivingEntity livingEntity) {
                livingEntity.addEffect(new MobEffectInstance(appliedEffect, effectDuration, effectAmplifier));
            }

            if (entity instanceof ServerPlayer serverPlayer) {
                serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(serverPlayer));
            }
        }
    }
}