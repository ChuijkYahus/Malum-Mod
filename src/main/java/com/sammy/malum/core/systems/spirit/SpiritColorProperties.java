package com.sammy.malum.core.systems.spirit;

import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.modules.core.easing.Easing;
import team.lodestar.lodestone.systems.particle.data.color.*;

import java.awt.*;

public record SpiritColorProperties(Color primaryColor, Color secondaryColor, Easing colorEasing, float colorCoefficient, float alphaMultiplier, Color itemColor) {

    public ColorParticleDataBuilder createColorData() {
        return createColorData(1f);
    }

    public ColorParticleDataBuilder createColorData(float coefficientMultiplier) {
        return ColorParticleData.create(primaryColor(), secondaryColor()).setCoefficient(colorCoefficient() * coefficientMultiplier).setEasing(colorEasing());
    }

    public static SpiritTypeColorPropertiesBuilder create(Color primaryColor, Color secondaryColor) {
        return new SpiritTypeColorPropertiesBuilder(primaryColor, secondaryColor);
    }

    public static class SpiritTypeColorPropertiesBuilder {
        public final Color primaryColor;
        public final Color secondaryColor;
        public Easing colorEasing = Easing.LINEAR;
        public float colorCoefficient = 1.0f;
        public float alphaMultiplier = 1.0f;
        public Color itemColor;

        public SpiritTypeColorPropertiesBuilder(Color primaryColor, Color secondaryColor) {
            this.primaryColor = primaryColor;
            this.secondaryColor = secondaryColor;
            this.itemColor = primaryColor;
        }

        public SpiritTypeColorPropertiesBuilder setColorEasing(Easing colorEasing) {
            this.colorEasing = colorEasing;
            return this;
        }

        public SpiritTypeColorPropertiesBuilder setColorCoefficient(float colorCoefficient) {
            this.colorCoefficient = colorCoefficient;
            return this;
        }

        public SpiritTypeColorPropertiesBuilder setAlphaMultiplier(float alphaMultiplier) {
            this.alphaMultiplier = alphaMultiplier;
            return this;
        }

        public SpiritTypeColorPropertiesBuilder setItemColor(Color itemColor) {
            this.itemColor = itemColor;
            return this;
        }

        public SpiritTypeColorPropertiesBuilder brightenItemColor(int power) {
            itemColor = ColorHelper.brighter(itemColor, power);
            return this;
        }
        public SpiritTypeColorPropertiesBuilder darkenItemColor(int power) {
            itemColor = ColorHelper.darker(itemColor, power);
            return this;
        }

        public SpiritColorProperties build() {
            return new SpiritColorProperties(primaryColor, secondaryColor, colorEasing, colorCoefficient, alphaMultiplier, itemColor);
        }
    }
}
