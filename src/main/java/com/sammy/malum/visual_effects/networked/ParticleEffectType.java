package com.sammy.malum.visual_effects.networked;

import com.mojang.serialization.*;
import com.sammy.malum.common.packets.ParticleEffectPacket;
import com.sammy.malum.registry.common.ParticleEffectTypeRegistry;
import com.sammy.malum.visual_effects.networked.data.ColorEffectData;
import com.sammy.malum.visual_effects.networked.data.NBTEffectData;
import com.sammy.malum.visual_effects.networked.data.PositionEffectData;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.PacketDistributor;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;

import java.awt.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class ParticleEffectType {

    public static final Codec<ParticleEffectType> CODEC = Codec.STRING.comapFlatMap(s -> {
        final ParticleEffectType effectType = ParticleEffectTypeRegistry.EFFECT_TYPES.get(s);
        return DataResult.success(effectType);
    }, ParticleEffectType::getId);

    public final String id;

    public ParticleEffectType(String id) {
        this.id = id;
        ParticleEffectTypeRegistry.EFFECT_TYPES.put(id, this);
    }

    public String getId() {
        return id;
    }

    @OnlyIn(Dist.CLIENT)
    public abstract Supplier<ParticleEffectActor> get();

    @Deprecated
    public void createEntityEffect(Entity entity) {
        createEntityEffect(entity, null);
    }

    @Deprecated
    public void createEntityEffect(Entity entity, ColorEffectData colorData) {
        createEntityEffect(entity, colorData, null);
    }

    @Deprecated
    public void createEntityEffect(Entity entity, ColorEffectData colorData, NBTEffectData nbtData) {
        createEffect(p -> PacketDistributor.sendToPlayersTrackingEntityAndSelf(entity, p), new PositionEffectData(entity), colorData, nbtData);
    }

    @Deprecated
    public void createPositionedEffect(ServerLevel level, PositionEffectData positionData) {
        createPositionedEffect(level, positionData, null, null);
    }

    @Deprecated
    public void createPositionedEffect(ServerLevel level, PositionEffectData positionData, NBTEffectData nbtData) {
        createPositionedEffect(level, positionData, null, nbtData);
    }

    @Deprecated
    public void createPositionedEffect(ServerLevel level, PositionEffectData positionData, ColorEffectData colorData) {
        createPositionedEffect(level, positionData, colorData, null);
    }

    @Deprecated
    public void createPositionedEffect(ServerLevel level, PositionEffectData positionData, ColorEffectData colorData, NBTEffectData nbtData) {
        createEffect(p -> PacketDistributor.sendToPlayersTrackingChunk(level, new ChunkPos(positionData.getAsBlockPos()), p), positionData, colorData, nbtData);
    }

    @Deprecated
    public void createEffect(Consumer<ParticleEffectPacket> sender, PositionEffectData positionData, ColorEffectData colorData, NBTEffectData nbtData) {
        sender.accept(new ParticleEffectPacket(id, positionData, colorData, nbtData));
    }

    protected ParticleEffectBuilder createEffect() {
        return new ParticleEffectBuilder(this);
    }

    public ParticleEffectBuilder createEffect(BlockPos position) {
        return createEffect().at(position);
    }

    public ParticleEffectBuilder createEffect(Vec3 position) {
        return createEffect().at(position);
    }

    public ParticleEffectBuilder createEffect(Entity target) {
        return createEffect().at(target);
    }

    public static class ParticleEffectBuilder {

        public final ParticleEffectType type;
        public PositionEffectData position;
        public ColorEffectData color;
        public NBTEffectData nbt;

        public ParticleEffectBuilder(ParticleEffectType type) {
            this.type = type;
        }

        public ParticleEffectBuilder at(BlockPos position) {
            return at(new PositionEffectData(position));
        }

        public ParticleEffectBuilder at(Vec3 position) {
            return at(new PositionEffectData(position));
        }

        public ParticleEffectBuilder at(Entity target) {
            return at(new PositionEffectData(target));
        }

        public ParticleEffectBuilder at(PositionEffectData position) {
            this.position = position;
            return this;
        }

        public ParticleEffectBuilder color(Color color) {
            return color(ColorParticleData.create(color).build());
        }

        public ParticleEffectBuilder color(ColorParticleData color) {
            return color(ColorEffectData.fromColor(color));
        }

        public ParticleEffectBuilder color(ColorEffectData color) {
            this.color = color;
            return this;
        }

        public ParticleEffectBuilder customData(NBTEffectData nbt) {
            this.nbt = nbt;
            return this;
        }

        public ParticleEffectBuilder spawn(ServerLevel level) {
            return spawn(p -> PacketDistributor.sendToPlayersTrackingChunk(level, new ChunkPos(position.getAsBlockPos()), p));
        }

        public ParticleEffectBuilder spawn(Consumer<ParticleEffectPacket> sender) {
            sender.accept(new ParticleEffectPacket(type.id, position, color, nbt));
            return this;
        }
    }

    public interface ParticleEffectActor {
        void act(Level level, RandomSource random, PositionEffectData positionData, ColorEffectData colorData, NBTEffectData nbtData);
    }
}