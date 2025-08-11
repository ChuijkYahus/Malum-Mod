package com.sammy.malum.core.systems.rite;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.*;
import com.sammy.malum.client.screen.codex.pages.BookPage;
import com.sammy.malum.common.block.curiosities.totem.*;
import com.sammy.malum.core.helpers.ComponentHelper;
import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.registry.rite.*;
import com.sammy.malum.core.systems.rite.effect.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.magic.rite.*;
import io.netty.buffer.ByteBuf;
import net.minecraft.ChatFormatting;
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

    public static final Codec<Holder<SpiritRiteType>> HOLDER_CODEC = MalumSpiritRiteTypes.RITE_REGISTRY.holderByNameCodec();

    public static final Codec<SpiritRiteType> CODEC = MalumSpiritRiteTypes.RITE_REGISTRY.byNameCodec();

    public static StreamCodec<ByteBuf, SpiritRiteType> STREAM_CODEC = ByteBufCodecs.fromCodec(CODEC);

    protected final List<SpiritHolder<SpiritArcanaType>> spirits;
    protected final boolean isCorrupted;
    protected final RiteEffectHolder<? extends SpiritRiteEffect> effect;

    private List<Component> detailedDescription;

    public SpiritRiteType(RiteEffectHolder<? extends SpiritRiteEffect> effect, boolean isCorrupted, List<SpiritHolder<SpiritArcanaType>> spirits) {
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
        return effect.get();
    }

    public void triggerRiteEffect(ServerLevel level, TotemBaseBlockEntity totemBase) {
        getEffect().triggerRiteEffect(level, totemBase);
    }

    public boolean matches(ServerLevel level, TotemBaseBlockEntity totemBase) {
        var totemSpirits = totemBase.getSpirits(level);
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
        if (detailedDescription == null) {
            ArrayList<Component> tooltip = new ArrayList<>();
            var color = getIdentifyingSpirit().getStyle(0.9f);
            var title = Component.translatable(getLangKey()).withStyle(color);
            var tags = getTags();
            var effectDetails = getEffectDetails();

            tooltip.add(title);
            tooltip.add(tags);
            tooltip.addAll(effectDetails);
            detailedDescription = ImmutableList.copyOf(tooltip);
        }
        return detailedDescription;
    }

    public List<Component> getEffectDetails() {
        MutableComponent effect = Component.translatable(getEffectLangKey());
        String text = effect.getString();
        String[] parts = text.split("\n");
        return Arrays.stream(parts).map(p -> ComponentHelper.riteEffect(p, isCorrupted())).toList();
    }

    public MutableComponent getTags() {
        MutableComponent component = Component.empty();
        List<SpiritRiteEffectTag> tags = new ArrayList<>(getEffect().getTags());
        tags.addFirst(isCorrupted() ? SpiritRiteEffectTag.SOULWOOD : SpiritRiteEffectTag.RUNEWOOD);
        Iterator<SpiritRiteEffectTag> iterator = tags.iterator();
        while (iterator.hasNext()) {
            SpiritRiteEffectTag tag = iterator.next();
            component.append(Component.translatable(tag.getLangKey()));
            if (iterator.hasNext()) {
                component.append(Component.literal(", "));
            }
        }
        return component.withStyle(ChatFormatting.ITALIC, ChatFormatting.DARK_GRAY);
    }

    public ResourceLocation getRegistryName() {
        return MalumSpiritRiteTypes.RITE_REGISTRY.getKey(this);
    }

    public String getLangKey() {
        return getRegistryName().getNamespace() + ".gui.rite." + getName();
    }

    public String getEffectLangKey() {
        return getLangKey() + ".effect";
    }

    public String getCodexEntryLangKey() {
        return BookPage.TEXT + "." + getName();
    }

    public String getName() {
        return getRegistryName().getPath();
    }

    public ResourceLocation getIcon() {
        return getRegistryName().withPath(s -> "textures/vfx/rite/" + s).withSuffix(".png");
    }

    public final void save(CompoundTag tag) {
        save(tag, "rite");
    }

    public final void save(CompoundTag tag, String name) {
        tag.put(name, CODEC.encodeStart(NbtOps.INSTANCE, this).getOrThrow());
    }

    public static Optional<SpiritRiteType> load(CompoundTag tag) {
        return load(tag, "rite");
    }

    public static Optional<SpiritRiteType> load(CompoundTag tag, String name) {
        return CODEC.decode(NbtOps.INSTANCE, tag.get(name)).map(Pair::getFirst).result();
    }
}