package com.sammy.malum.core.helpers;

import com.sammy.malum.core.systems.rite.*;
import net.minecraft.*;
import net.minecraft.network.chat.*;

public class ComponentHelper {

    public static Component effectKeyword(String name, Object... args) {
        return Component.literal(" ").withStyle(ChatFormatting.DARK_GRAY).append(Component.translatable("malum.effect.keyword." + name, args).withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
    }

    public static Component positiveGeasEffect(String name, Object... args) {
        return Component.translatable("malum.effect.positive", Component.translatable("malum.effect.geas." + name, args)).withStyle(ChatFormatting.BLUE);
    }

    public static Component negativeGeasEffect(String name, Object... args) {
        return Component.translatable("malum.effect.negative", Component.translatable("malum.effect.geas." + name, args)).withStyle(ChatFormatting.RED);
    }

    public static Component positiveCurioEffect(String name, Object... args) {
        return Component.translatable("malum.effect.positive", Component.translatable("malum.effect.curio." + name, args)).withStyle(ChatFormatting.BLUE);
    }

    public static Component negativeCurioEffect(String name, Object... args) {
        return Component.translatable("malum.effect.negative", Component.translatable("malum.effect.curio." + name, args)).withStyle(ChatFormatting.RED);
    }

    public static Component positivePouchEffect(String name, Object... args) {
        return Component.literal("┇ ").withStyle(ChatFormatting.DARK_GRAY).append(Component.translatable("malum.effect." + name, args).withStyle(ChatFormatting.BLUE));
    }

    public static Component negativePouchEffect(String name, Object... args) {
        return Component.literal("┇ ").withStyle(ChatFormatting.DARK_GRAY).append(Component.translatable("malum.effect." + name, args).withStyle(ChatFormatting.RED));
    }

    public static Component riteEffect(String text, SpiritRiteType rite) {
        var color = rite.getIdentifyingSpirit().getStyle(0.6f);
        return Component.literal("┇ ").withStyle(ChatFormatting.DARK_GRAY).append(Component.literal(text).withStyle(color));
    }

    public static Component positiveEffect(String name, Object... args) {
        return Component.literal(" ").append(Component.translatable("malum.effect." + name, args)).withStyle(ChatFormatting.DARK_GREEN);
    }

    public static Component negativeEffect(String name, Object... args) {
        return Component.literal(" ").append(Component.translatable("malum.effect." + name, args)).withStyle(ChatFormatting.RED);
    }
}
