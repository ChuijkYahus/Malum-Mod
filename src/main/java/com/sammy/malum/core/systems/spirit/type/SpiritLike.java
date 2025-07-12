package com.sammy.malum.core.systems.spirit.type;

import com.sammy.malum.common.item.spirit.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import net.minecraft.resources.*;
import net.minecraft.util.Mth;
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

    default Style getStyle(float brightness) {
        return Style.EMPTY.withColor(getTextColor(brightness));
    }

    default TextColor getTextColor(boolean isTooltip) {
        return getTextColor(isTooltip ? -0.75f : 0.85f);
    }

    default TextColor getTextColor(float brightness) {
        Color color = getPrimaryColor();
        if (brightness < 0) {
            color = ColorHelper.darker(color, 1, Mth.abs(brightness));
        }
        else {
            color = ColorHelper.brighter(color, 1, brightness);
        }
        return TextColor.fromRgb(color.getRGB());
    }
}