package com.sammy.malum.core.systems.spirit;

import com.sammy.malum.core.systems.registry.SpiritHolder;
import com.sammy.malum.datagen.lang.MalumLangDatagen;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import team.lodestar.lodestone.helpers.ColorHelper;
import team.lodestar.lodestone.helpers.DataHelper;
import team.lodestar.lodestone.modules.core.datagen.DatagenOnly;

import java.awt.*;
import java.util.List;

public class SpiritTextData {

    public static final String STORED_SPIRITS = "malum.spirit.description.stored_spirits";
    protected final ResourceLocation id;
    protected final Color baseColor;

    public Component flavorText;

    public SpiritTextData(ResourceLocation id, Color baseColor) {
        this.id = id;
        this.baseColor = baseColor;
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
        return getLangKey() + ".flavour_text";
    }

    public Component createFlavorText() {
        if (flavorText == null) {
            flavorText = Component.translatable(getFlavourKey()).withStyle(ChatFormatting.ITALIC).withStyle(getStyle(true));
        }
        return flavorText;
    }

    public Component createCountedText(int count) {
        return Component.translatable(getCountedKey(), count).withStyle(getStyle(false));
    }

    public Style getStyle(boolean isTooltip) {
        return Style.EMPTY.withColor(getTextColor(isTooltip));
    }

    public Style getStyle(float brightness) {
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

    public void addToTooltip(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(createFlavorText());
    }

    public void countSpiritInTooltip(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag, int count, boolean addHeader) {
        if (addHeader) {
            tooltipComponents.add(Component.translatable(STORED_SPIRITS).withStyle(ChatFormatting.GRAY));
        }
        tooltipComponents.add(createCountedText(count));
    }

    @DatagenOnly
    public void addLangDatagen(MalumLangDatagen datagen, String flavour) {
        var name = DataHelper.toTitleCase(getId().getPath(), "_");
        datagen.add(getFlavourKey(), flavour);
        datagen.add(getCountedKey(), "%1$s " + name);
        datagen.add(getLangKey(), name);
    }
}
