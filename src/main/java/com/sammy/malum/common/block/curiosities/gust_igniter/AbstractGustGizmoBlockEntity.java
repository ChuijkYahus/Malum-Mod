package com.sammy.malum.common.block.curiosities.gust_igniter;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sammy.malum.common.block.curiosities.redstone.*;
import io.netty.buffer.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.network.codec.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import team.lodestar.lodestone.modules.toolkit.blockentity.LodestoneBlockEntityType;

public abstract class AbstractGustGizmoBlockEntity extends OpenStateBlockEntity {

    public record GustGizmoInfo(int strength, boolean modified) implements InboundInfo<AbstractGustGizmoBlockEntity> {

        public static final Codec<GustGizmoInfo> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.INT.fieldOf("strength").forGetter(GustGizmoInfo::strength),
                Codec.BOOL.fieldOf("modified").forGetter(GustGizmoInfo::modified)
        ).apply(instance, GustGizmoInfo::new));

        public static StreamCodec<ByteBuf, GustGizmoInfo> STREAM_CODEC = ByteBufCodecs.fromCodec(GustGizmoInfo.CODEC);

        @Override
        public void sync(AbstractGustGizmoBlockEntity entity) {
            entity.setInfo(this);
        }
    }

    public AbstractGustGizmoBlockEntity(LodestoneBlockEntityType<? extends AbstractGustGizmoBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public abstract void setInfo(GustGizmoInfo info);
}