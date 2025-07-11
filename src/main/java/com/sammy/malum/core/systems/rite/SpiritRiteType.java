package com.sammy.malum.core.systems.rite;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.*;
import com.sammy.malum.common.block.curiosities.totem.*;
import com.sammy.malum.common.spiritrite.SpiritRiteHelper;
import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.rite.effect.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.magic.*;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.chat.*;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.*;
import net.minecraft.server.level.ServerLevel;

import java.util.*;

public class SpiritRiteType {

    public static final Codec<Holder<SpiritRiteType>> HOLDER_CODEC = MalumSpiritRiteTypes.SPIRIT_RITE_REGISTRY.holderByNameCodec();

    public static final Codec<SpiritRiteType> CODEC = MalumSpiritRiteTypes.SPIRIT_RITE_REGISTRY.byNameCodec();

    public static StreamCodec<ByteBuf, SpiritRiteType> STREAM_CODEC = ByteBufCodecs.fromCodec(CODEC);

    public static final String TYPE = "malum.gui.rite.type";
    public static final String MEDIUM = "malum.gui.rite.medium";
    public static final String RUNEWOOD = "malum.gui.rite.medium.runewood";
    public static final String SOULWOOD = "malum.gui.rite.medium.soulwood";
    public static final String COVERAGE = "malum.gui.rite.coverage";
    public static final String ANCHOR = "malum.gui.rite.coverage.anchor";
    public static final String EFFECT = "malum.gui.rite.effect";

    protected final List<SpiritHolder<SpiritArcanaType>> spirits;
    protected final boolean isCorrupted;
    protected final SpiritRiteEffect effect;

    private List<Component> detailedDescription;

    public SpiritRiteType(SpiritRiteEffect effect, boolean isCorrupted, List<SpiritHolder<SpiritArcanaType>> spirits) {
        this.effect = effect;
        this.isCorrupted = isCorrupted;
        this.spirits = spirits;
    }

    public List<SpiritHolder<SpiritArcanaType>> getSpirits() {
        return spirits;
    }

    public SpiritHolder<SpiritArcanaType> getIdentifyingSpirit() {
        return getSpirits().getLast();
    }

    public boolean isCorrupted() {
        return isCorrupted;
    }

    public SpiritRiteEffect getEffect() {
        return effect;
    }

    public void triggerRiteEffect(ServerLevel level, TotemBaseBlockEntity totemBase) {
        if (effect instanceof SpiritRiteBlockEffect blockEffect) {

        }
        else if (effect instanceof SpiritRiteEntityEffect<?> entityEffect) {

        }
    }

    public boolean matches(TotemBaseBlockEntity totemBase) {
        var totemSpirits = totemBase.getSpirits();
        if (totemBase.corrupted != isCorrupted) {
            return false;
        }
        if (totemSpirits.size() != spirits.size()) {
            return false;
        }
        for (int i = 0; i < totemSpirits.size(); i++) {
            var spirit = spirits.get(i);
            var totemSpirit = totemSpirits.get(i);
            if (!spirit.is(totemSpirit)) {
                return false;
            }
        }
        return true;
    }

    public List<Component> getDetailedDescription() {
        if (detailedDescription != null) {
            return detailedDescription;
        }
        detailedDescription = SpiritRiteHelper.defaultDetailedDescription(this);
        return detailedDescription;
    }

    public ResourceLocation getRegistryName() {
        return MalumSpiritRiteTypes.SPIRIT_RITE_REGISTRY.getKey(this);
    }

    public String getLangKey() {
        return getRegistryName().getNamespace() + ".gui.rite." + getName();
    }

    public String getEffectLangKey() {
        return EFFECT + "." + getName();
    }

    public String getName() {
        return getRegistryName().getPath();
    }

    public ResourceLocation getIcon() {
        return getRegistryName().withPath(s -> s + "/textures/vfx/rite/").withSuffix(".png");
    }

    public final void save(CompoundTag tag) {
        save(tag, "rite");
    }

    public final void save(CompoundTag tag, String name) {
        CODEC.encode(this, NbtOps.INSTANCE, new CompoundTag()).ifSuccess(c -> tag.put(name, c));
    }

    public static Optional<SpiritRiteType> load(CompoundTag tag) {
        return load(tag, "rite");
    }

    public static Optional<SpiritRiteType> load(CompoundTag tag, String name) {
        return CODEC.decode(NbtOps.INSTANCE, tag.getCompound(name)).map(Pair::getFirst).result();
    }
}