package com.sammy.malum.core.systems.rite;

import com.google.common.collect.*;
import com.mojang.serialization.*;
import com.sammy.malum.common.block.curiosities.totem.*;
import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.core.systems.rite.effect.*;
import com.sammy.malum.core.systems.spirit.type.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.*;
import net.minecraft.network.chat.*;
import net.minecraft.resources.*;

import java.util.*;

public class SpiritRiteType {

    public static final Codec<SpiritRiteType> CODEC = MalumSpiritRiteTypes.SPIRIT_RITE_REGISTRY.byNameCodec();

    public static final String TYPE = "malum.gui.rite.type";
    public static final String MEDIUM = "malum.gui.rite.medium";
    public static final String RUNEWOOD = "malum.gui.rite.medium.runewood";
    public static final String SOULWOOD = "malum.gui.rite.medium.soulwood";
    public static final String COVERAGE = "malum.gui.rite.coverage";
    public static final String ANCHOR = "malum.gui.rite.coverage.anchor";
    public static final String EFFECT = "malum.gui.rite.effect";

    protected final List<SpiritHolder<MalumSpiritType>> spirits;
    protected final boolean isCorrupted;
    protected final SpiritRiteEffect effect;

    private List<Component> detailedDescription;

    public SpiritRiteType(SpiritRiteEffect effect, boolean isCorrupted, List<SpiritHolder<MalumSpiritType>> spirits) {
        this.effect = effect;
        this.isCorrupted = isCorrupted;
        this.spirits = spirits;
    }

    public List<SpiritHolder<MalumSpiritType>> getSpirits() {
        return spirits;
    }

    public SpiritHolder<MalumSpiritType> getIdentifyingSpirit() {
        return spirits.getLast();
    }

    public boolean isCorrupted() {
        return isCorrupted;
    }

    public SpiritRiteEffect getEffect() {
        return effect;
    }

    public boolean matches(List<? extends SpiritLike> totemSpirits, TotemBaseBlockEntity totemBase) {
        if (totemBase.isCorrupted != isCorrupted) {
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
        List<Component> tooltip = new ArrayList<>();
        var spiritStyleModifier = getIdentifyingSpirit().getSpirit().getItemRarity().getStyleModifier();
        var riteEffect = getEffect();
        var riteCategory = riteEffect.getCategory();
        tooltip.add(Component.translatable(getLangKey()).withStyle(spiritStyleModifier));
        tooltip.add(Component.empty());
        tooltip.add(createRiteComponent(TYPE, riteCategory.getTranslationKey()));
        tooltip.add(createRiteComponent(MEDIUM, getMediumKey()));
        tooltip.add(createRiteComponent(COVERAGE, riteEffect.getRiteCoverageDescriptor()));
        tooltip.add(createRiteComponent(EFFECT, EFFECT + "." + getName()));
        detailedDescription = ImmutableList.copyOf(tooltip);
        return detailedDescription;
    }

    public static Component createRiteComponent(String title, String text) {
        return createRiteComponent(title, Component.translatable(text).withStyle(ChatFormatting.YELLOW));
    }

    public static Component createRiteComponent(String title, Object... args) {
        return Component.translatable(title, args).withStyle(ChatFormatting.GOLD);
    }

    public ResourceLocation getRegistryName() {
        return MalumSpiritRiteTypes.SPIRIT_RITE_REGISTRY.getKey(this);
    }

    public String getLangKey() {
        return getRegistryName().getNamespace() + ".gui.rite." + getName();
    }

    public String getMediumKey() {
        return isCorrupted ? SOULWOOD : RUNEWOOD;
    }

    public String getName() {
        return getRegistryName().getPath();
    }

    public ResourceLocation getIcon() {
        return getRegistryName().withPath(s -> s + "/textures/vfx/rite/").withSuffix(".png");
    }
}