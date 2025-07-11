package com.sammy.malum.common.spiritrite;

import com.google.common.collect.ImmutableList;
import com.sammy.malum.common.block.curiosities.totem.TotemBaseBlockEntity;
import com.sammy.malum.core.systems.rite.SpiritRiteType;
import com.sammy.malum.registry.common.magic.MalumSpiritRiteTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.ArrayList;
import java.util.List;

public class SpiritRiteHelper {

    public static SpiritRiteType getRite(TotemBaseBlockEntity totemBase) {
        List<? extends SpiritRiteType> rites = MalumSpiritRiteTypes.SPIRIT_RITE_TYPES.getEntries().stream().map(DeferredHolder::get).toList();
        for (SpiritRiteType rite : rites) {
            if (rite.matches(totemBase)) {
                return rite;
            }
        }
        return null;
    }

    public static List<Component> defaultDetailedDescription(SpiritRiteType riteType) {
        ArrayList<Component> tooltip = new ArrayList<>();

        var spiritStyleModifier = riteType.getIdentifyingSpirit().getSpirit().getItemRarity().getStyleModifier();
        var title = Component.translatable(riteType.getLangKey()).withStyle(spiritStyleModifier);
        var riteEffect = riteType.getEffect();
        var riteCategory = riteEffect.getCategory();
        var type = riteCategory.getTranslationKey();
        var medium = riteType.isCorrupted() ? SpiritRiteType.SOULWOOD : SpiritRiteType.RUNEWOOD;
        var coverage = riteEffect.getRiteCoverageDescriptor();
        var effect = riteType.getEffectLangKey();
        tooltip.add(title);
        tooltip.add(Component.empty());
        tooltip.add(createRiteComponent(SpiritRiteType.TYPE, type));
        tooltip.add(createRiteComponent(SpiritRiteType.MEDIUM, medium));
        tooltip.add(createRiteComponent(SpiritRiteType.COVERAGE, coverage));
        tooltip.add(createRiteComponent(SpiritRiteType.EFFECT, effect));
        return ImmutableList.copyOf(tooltip);
    }

    public static Component createRiteComponent(String title, String text) {
        return createRiteComponent(title, Component.translatable(text).withStyle(ChatFormatting.YELLOW));
    }

    public static Component createRiteComponent(String title, Object... args) {
        return Component.translatable(title, args).withStyle(ChatFormatting.GOLD);
    }
}
