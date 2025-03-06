package com.sammy.malum.core.systems.spirit;

import team.lodestar.lodestone.systems.easing.*;
import team.lodestar.lodestone.systems.particle.data.color.*;

import java.awt.*;
import java.util.function.*;

public class SpiritVisualMotif {

    protected final Color primaryColor;
    protected final Color secondaryColor;
    protected final float colorCoefficient;
    protected final Easing colorEasing;

    protected final Color itemColor;
    protected final float alphaMultiplier;

    public SpiritVisualMotif(Color primaryColor, Color secondaryColor, float colorCoefficient, Easing colorEasing) {
        this(primaryColor, secondaryColor, colorCoefficient, colorEasing, primaryColor);
    }
    public SpiritVisualMotif(Color primaryColor, Color secondaryColor, float colorCoefficient, Easing colorEasing, Function<Color, Color> colorFunction) {
        this(primaryColor, secondaryColor, colorCoefficient, colorEasing, colorFunction.apply(primaryColor));
    }
    public SpiritVisualMotif(Color primaryColor, Color secondaryColor, float colorCoefficient, Easing colorEasing, Color itemColor) {
        this(primaryColor, secondaryColor, colorCoefficient, colorEasing, itemColor, 1f);
    }
    public SpiritVisualMotif(Color primaryColor, Color secondaryColor, float colorCoefficient, Easing colorEasing, Color itemColor, float alphaMultiplier) {
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.colorCoefficient = colorCoefficient;
        this.colorEasing = colorEasing;
        this.itemColor = itemColor;
        this.alphaMultiplier = alphaMultiplier;
    }

    public ColorParticleDataBuilder createColorData() {
        return createColorData(1f);
    }

    public ColorParticleDataBuilder createColorData(float coefficientMultiplier) {
        return ColorParticleData.create(primaryColor, secondaryColor).setCoefficient(colorCoefficient * coefficientMultiplier).setEasing(colorEasing);
    }

    public Color getPrimaryColor() {
        return primaryColor;
    }

    public float getAlphaMultiplier() {
        return alphaMultiplier;
    }

    public Color getSecondaryColor() {
        return secondaryColor;
    }

    public float getColorCoefficient() {
        return colorCoefficient;
    }

    public Easing getColorEasing() {
        return colorEasing;
    }
}