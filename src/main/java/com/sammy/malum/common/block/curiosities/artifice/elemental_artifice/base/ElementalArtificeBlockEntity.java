package com.sammy.malum.common.block.curiosities.artifice.elemental_artifice.base;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sammy.malum.common.block.curiosities.artifice.redstone.*;
import io.netty.buffer.*;
import net.minecraft.core.*;
import net.minecraft.network.codec.*;
import net.minecraft.world.level.block.state.*;
import team.lodestar.lodestone.modules.toolkit.blockentity.LodestoneBlockEntityType;

public abstract class ElementalArtificeBlockEntity extends OpenStateBlockEntity {

    public record ElementalArtificeBlockConfigInfo(int strength, boolean modified) implements NetworkedTinkeringInfo<ElementalArtificeBlockEntity> {

        public static final Codec<ElementalArtificeBlockConfigInfo> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.INT.fieldOf("strength").forGetter(ElementalArtificeBlockConfigInfo::strength),
                Codec.BOOL.fieldOf("modified").forGetter(ElementalArtificeBlockConfigInfo::modified)
        ).apply(instance, ElementalArtificeBlockConfigInfo::new));

        public static StreamCodec<ByteBuf, ElementalArtificeBlockConfigInfo> STREAM_CODEC = ByteBufCodecs.fromCodec(ElementalArtificeBlockConfigInfo.CODEC);

        @Override
        public void sync(ElementalArtificeBlockEntity entity) {
            entity.setInfo(this);
        }
    }

    public ElementalArtificeBlockEntity(LodestoneBlockEntityType<? extends ElementalArtificeBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public abstract void setInfo(ElementalArtificeBlockConfigInfo info);
}