package com.sammy.malum.core.systems.spirit.type;

import com.sammy.malum.common.item.spirit.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.systems.particle.data.color.*;

import javax.annotation.*;
import java.awt.*;

public interface SpiritWrapper {

    @Nonnull
    MalumSpiritType unwrapSpirit();

    default boolean matches(SpiritWrapper other) {
        return unwrapSpirit().equals(other.unwrapSpirit());
    }

    default String getFlavourKey() {
        return getLangKey() + ".flavour";
    }

    default String getCountedKey() {
        return getLangKey() + ".counted";
    }

    default String getLangKey() {
        return getRegistryName().getNamespace() + ".gui.spirit." + getRegistryName().getPath();
    }

    default Holder<MalumSpiritType> getHolder() {
        return MalumSpiritTypes.SPIRIT_TYPES_REGISTRY.getHolder(getRegistryName()).orElseThrow();
    }

    default ResourceLocation getRegistryName() {
        return MalumSpiritTypes.SPIRIT_TYPES_REGISTRY.getKey(unwrapSpirit());
    }

    default int getAnalogSignal() {
        return Math.min(MalumSpiritTypes.SPIRIT_TYPES_REGISTRY.getId(unwrapSpirit()) + 1, 15);
    }

    default SpiritShardItem getSpiritShard() {
        return unwrapSpirit().getSpiritShard();
    }

    default ItemStack getSpiritStack() {
        return getSpiritShard().getDefaultInstance();
    }

    default ItemStack getSpiritStack(int count) {
        return new ItemStack(getSpiritShard(), count);
    }

    default float getAlphaMultiplier() {
        return unwrapSpirit().getColorProperties().alphaMultiplier();
    }

    default Color getPrimaryColor() {
        return unwrapSpirit().getColorProperties().primaryColor();
    }

    default Color getSecondaryColor() {
        return unwrapSpirit().getColorProperties().secondaryColor();
    }

    default Color getItemColor() {
        return unwrapSpirit().getColorProperties().itemColor();
    }

    default ColorParticleDataBuilder createColorData() {
        return createColorData(1f);
    }

    default ColorParticleDataBuilder createColorData(float coefficientMultiplier) {
        return unwrapSpirit().getColorProperties().createColorData(coefficientMultiplier);
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