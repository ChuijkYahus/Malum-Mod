package com.sammy.malum.common.block.curiosities.artifice.elemental_artifice;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sammy.malum.common.block.curiosities.artifice.redstone.*;
import io.netty.buffer.*;
import net.minecraft.core.*;
import net.minecraft.network.codec.*;
import net.minecraft.world.level.block.state.*;
import team.lodestar.lodestone.modules.toolkit.blockentity.LodestoneBlockEntityType;

public abstract class ElementalArtificeBlockEntity extends OpenStateBlockEntity {

    public record GustGizmoInfo(int strength, boolean modified) implements InboundInfo<ElementalArtificeBlockEntity> {

        public static final Codec<GustGizmoInfo> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.INT.fieldOf("strength").forGetter(GustGizmoInfo::strength),
                Codec.BOOL.fieldOf("modified").forGetter(GustGizmoInfo::modified)
        ).apply(instance, GustGizmoInfo::new));

        public static StreamCodec<ByteBuf, GustGizmoInfo> STREAM_CODEC = ByteBufCodecs.fromCodec(GustGizmoInfo.CODEC);

        @Override
        public void sync(ElementalArtificeBlockEntity entity) {
            entity.setInfo(this);
        }
    }

    public ElementalArtificeBlockEntity(LodestoneBlockEntityType<? extends ElementalArtificeBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public abstract void setInfo(GustGizmoInfo info);
}