package com.sammy.malum.common.data.custom.wand_parts;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sammy.malum.MalumMod;
import com.sammy.malum.common.data.component.WandPartsComponent;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.neoforged.neoforge.network.codec.NeoForgeStreamCodecs;

import java.util.List;

public record WandPartType(WandPartGroup group, ResourceLocation id, int coreTier, int materialCost) {

    public enum WandPartGroup implements StringRepresentable {
        CORE("core", true),
        HEAD("head", true),
        BASE("base", true),
        BAUBLE("bauble", true),
        ORNAMENT("ornament", false);

        public final String name;
        public final boolean onlyOne;

        WandPartGroup(String name, boolean onlyOne) {
            this.name = name;
            this.onlyOne = onlyOne;
        }

        @Override
        public String getSerializedName() {
            return name;
        }

        public String getIdForPart(String partName) {
            return partName + "_" + name;
        }
    }

    public boolean isMalum() {
        return id().getNamespace().equals(MalumMod.MALUM);
    }

    public boolean canApply(WandPartsComponent component) {
        if (coreTier > component.getCoreTier()) {
            return false;
        }
        if (group.onlyOne && component.getPart(group).isPresent()) {
            return false;
        }
        return !component.hasPart(this);
    }

    public static final StringRepresentable.EnumCodec<WandPartGroup> GROUP_CODEC = StringRepresentable.fromEnum(WandPartGroup::values);

    public static final StreamCodec<FriendlyByteBuf, WandPartGroup> STREAM_GROUP_CODEC = NeoForgeStreamCodecs.enumCodec(WandPartGroup.class);
    public static final StreamCodec<FriendlyByteBuf, List<WandPartGroup>> LIST_STREAM_GROUP_CODEC = STREAM_GROUP_CODEC.apply(ByteBufCodecs.list());

    public static final Codec<WandPartType> DIRECT_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            GROUP_CODEC.fieldOf("group").forGetter(WandPartType::group),
            ResourceLocation.CODEC.fieldOf("id").forGetter(WandPartType::id),
            Codec.INT.optionalFieldOf("coreTier", 0).forGetter(WandPartType::coreTier),
            Codec.INT.optionalFieldOf("materialCost", 1).forGetter(WandPartType::materialCost)
    ).apply(instance, WandPartType::new));


    public static final StreamCodec<RegistryFriendlyByteBuf, WandPartType> STREAM_CODEC =
            StreamCodec.composite(
                    STREAM_GROUP_CODEC, WandPartType::group,
                    ResourceLocation.STREAM_CODEC, WandPartType::id,
                    ByteBufCodecs.INT, WandPartType::coreTier,
                    ByteBufCodecs.INT, WandPartType::materialCost,
                    WandPartType::new
            );

    public static final StreamCodec<RegistryFriendlyByteBuf, List<WandPartType>> LIST_STREAM_CODEC = STREAM_CODEC.apply(ByteBufCodecs.list());
}