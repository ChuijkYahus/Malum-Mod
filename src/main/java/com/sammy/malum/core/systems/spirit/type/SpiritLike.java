package com.sammy.malum.core.systems.spirit.type;

import com.sammy.malum.common.item.spirit.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.systems.particle.data.color.*;

import javax.annotation.*;
import java.awt.*;

public interface SpiritLike {

    @Nonnull
    SpiritArcanaType getSpirit();

    default boolean matches(SpiritLike other) {
        return getSpirit().equals(other.getSpirit());
    }

    default String getFlavourKey() {
        return getLangKey() + ".flavour";
    }

    default String getCountedKey() {
        return getLangKey() + ".counted";
    }

    default String getLangKey() {
        return getRegistryName().getNamespace() + ".gui.spirit." + getName();
    }

    default Holder<SpiritArcanaType> getHolder() {
        return MalumSpiritTypes.SPIRIT_TYPES_REGISTRY.getHolder(getRegistryName()).orElseThrow();
    }

    default ResourceLocation getRegistryName() {
        return MalumSpiritTypes.SPIRIT_TYPES_REGISTRY.getKey(getSpirit());
    }

    default String getName() {
        return getRegistryName().getPath();
    }

    default int getAnalogSignal() {
        return Math.min(MalumSpiritTypes.SPIRIT_TYPES_REGISTRY.getId(getSpirit()) + 1, 15);
    }

    default SpiritShardItem getSpiritShard() {
        return getSpirit().getSpiritShard();
    }

    default ItemStack getSpiritStack() {
        return getSpiritShard().getDefaultInstance();
    }

    default ItemStack getSpiritStack(int count) {
        return new ItemStack(getSpiritShard(), count);
    }

    default float getAlphaMultiplier() {
        return getSpirit().getColorProperties().alphaMultiplier();
    }

    default Color getPrimaryColor() {
        return getSpirit().getColorProperties().primaryColor();
    }

    default Color getSecondaryColor() {
        return getSpirit().getColorProperties().secondaryColor();
    }

    default Color getItemColor() {
        return getSpirit().getColorProperties().itemColor();
    }

    default ColorParticleDataBuilder createColorData() {
        return createColorData(1f);
    }

    default ColorParticleDataBuilder createColorData(float coefficientMultiplier) {
        return getSpirit().getColorProperties().createColorData(coefficientMultiplier);
    }

    default Style getStyle(boolean isTooltip) {
        return Style.EMPTY.withColor(getTextColor(isTooltip));
    }

    default TextColor getTextColor(boolean isTooltip) {
        Color color = isTooltip ? ColorHelper.darker(getPrimaryColor(), 1, 0.75f) : ColorHelper.brighter(getPrimaryColor(), 1, 0.85f);
        return TextColor.fromRgb(color.getRGB());
    }

    default ResourceLocation getTotemGlowTexture() {
        return getRegistryName().withPath(p -> "textures/vfx/totem_poles/" + p).withSuffix("_glow.png");
    }
}