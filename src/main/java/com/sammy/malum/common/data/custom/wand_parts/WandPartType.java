package com.sammy.malum.common.data.custom.wand_parts;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.neoforged.neoforge.network.codec.NeoForgeStreamCodecs;
import team.lodestar.lodestone.modules.toolkit.codec.LodestoneCodecs;

import java.util.List;
import java.util.Optional;

public record WandPartType(WandPartGroup group, ResourceLocation id) {

    public enum WandPartGroup implements StringRepresentable {
        CORE("core", false),
        HEAD("head", false),
        BASE("base", true),
        BAUBLE("bauble", true),
        ORNAMENT("ornament", true);

        public final String name;
        public final boolean cosmetic;

        WandPartGroup(String name, boolean cosmetic) {
            this.name = name;
            this.cosmetic = cosmetic;
        }

        @Override
        public String getSerializedName() {
            return name;
        }

        public String getIdForPart(String partName) {
            return partName + "_" + name;
        }
    }

    public static final StringRepresentable.EnumCodec<WandPartGroup> GROUP_CODEC = StringRepresentable.fromEnum(WandPartGroup::values);

    public static final StreamCodec<FriendlyByteBuf, WandPartGroup> STREAM_GROUP_CODEC = NeoForgeStreamCodecs.enumCodec(WandPartGroup.class);
    public static final StreamCodec<FriendlyByteBuf, List<WandPartGroup>> LIST_STREAM_GROUP_CODEC = STREAM_GROUP_CODEC.apply(ByteBufCodecs.list());

    public static final Codec<WandPartType> DIRECT_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            GROUP_CODEC.fieldOf("group").forGetter(WandPartType::group),
            ResourceLocation.CODEC.fieldOf("id").forGetter(WandPartType::id)
    ).apply(instance, WandPartType::new));


    public static final StreamCodec<RegistryFriendlyByteBuf, WandPartType> STREAM_CODEC =
            StreamCodec.composite(
                    STREAM_GROUP_CODEC, WandPartType::group,
                    ResourceLocation.STREAM_CODEC, WandPartType::id,
                    WandPartType::new
            );

    public static final StreamCodec<RegistryFriendlyByteBuf, List<WandPartType>> LIST_STREAM_CODEC = STREAM_CODEC.apply(ByteBufCodecs.list());
}