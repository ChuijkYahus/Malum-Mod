package com.sammy.malum.core.systems.spirit;

import com.sammy.malum.datagen.lang.MalumLangDatagen;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import team.lodestar.lodestone.helpers.ColorHelper;
import team.lodestar.lodestone.helpers.DataHelper;
import team.lodestar.lodestone.modules.core.datagen.DatagenOnly;

import java.awt.*;
import java.util.List;

public class SpiritTextData {

    public static final String STORED_SPIRITS = "malum.spirit.description.stored_spirits";
    protected final ResourceLocation id;
    protected final Color baseColor;

    public final Component flavorText;
    public final Component verboseFlavorText;

    public final Component infoText;

    public SpiritTextData(ResourceLocation id, Color baseColor) {
        this.id = id;
        this.baseColor = baseColor;

        var style = createStyle(true);
        this.flavorText = Component.translatable(getFlavourKey()).withStyle(ChatFormatting.ITALIC).withStyle(style);
        this.verboseFlavorText = Component.translatable(getVerboseFlavorKey()).withStyle(ChatFormatting.ITALIC).withStyle(style);

        this.infoText = Component.translatable(getInfoKey()).withStyle(style);
    }

    public ResourceLocation getId() {
        return id;
    }

    public String getLangKey() {
        return id.getNamespace() + ".gui.spirit." + id.getPath();
    }

    public String getCountedKey() {
        return getLangKey() + ".counted";
    }

    public String getFlavourKey() {
        return getLangKey() + ".flavour";
    }

    public String getInfoKey() {
        return getLangKey() + ".info";
    }

    public String getVerboseFlavorKey() {
        return getLangKey() + ".flavour.verbose";
    }

    public Component createCountedText(int count) {
        return Component.translatable(getCountedKey(), count).withStyle(createStyle(false));
    }

    public Style createStyle(boolean isTooltip) {
        return Style.EMPTY.withColor(getTextColor(isTooltip));
    }

    public Style createStyle(float brightness) {
        return Style.EMPTY.withColor(getTextColor(brightness));
    }

    public TextColor getTextColor(boolean isTooltip) {
        return getTextColor(isTooltip ? -0.75f : 0.85f);
    }

    public TextColor getTextColor(float brightness) {
        Color color = baseColor;
        if (brightness < 0) {
            color = ColorHelper.darker(color, 1, Mth.abs(brightness));
        } else {
            color = ColorHelper.brighter(color, 1, brightness);
        }
        return TextColor.fromRgb(color.getRGB());
    }

    public void addToItemTooltip(List<Component> tooltip) {
        tooltip.add(flavorText);
    }

    public void addToCodexTooltip(List<Component> tooltip) {
        tooltip.add(flavorText);
        tooltip.add(Component.empty());
        tooltip.add(infoText);
        tooltip.add(Component.empty());
        tooltip.add(verboseFlavorText);
    }

    public void addSpiritCounterToItemTooltip(List<Component> tooltip, int count, boolean addHeader) {
        if (addHeader) {
            tooltip.add(Component.translatable(STORED_SPIRITS).withStyle(ChatFormatting.GRAY));
        }
        tooltip.add(createCountedText(count));
    }

    @DatagenOnly
    public void addLangDatagen(MalumLangDatagen datagen, String flavour, String verboseFlavour, String info) {
        var name = DataHelper.toTitleCase(getId().getPath(), "_");
        datagen.add(getInfoKey(), info);
        datagen.add(getFlavourKey(), flavour);
        datagen.add(getVerboseFlavorKey(), verboseFlavour);

        datagen.add(getLangKey(), name);
        datagen.add(getCountedKey(), "%1$s " + name);
    }
}
