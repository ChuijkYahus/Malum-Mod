package com.sammy.malum.common.block.curiosities.artifice;

import com.sammy.malum.common.block.curiosities.artifice.waveform.SpiritDiodeBlockEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;

import java.util.Locale;

public enum RedstoneTimeIntervalType implements StringRepresentable {
    REDSTONE_TICK("redstone_tick", 0, 2),
    SECOND("second", 1, 20),
    MINUTE("minute", 2, 1200);

    public static final EnumCodec<RedstoneTimeIntervalType> CODEC = StringRepresentable.fromEnum(RedstoneTimeIntervalType::values);

    public final String name;
    public final int id;
    public final int timeScale;

    RedstoneTimeIntervalType(String name, int id, int timeScale) {
        this.name = name;
        this.id = id;
        this.timeScale = timeScale;
    }

    public String getName() {
        return toString().toLowerCase(Locale.ROOT);
    }

    public Component getText(SpiritDiodeBlockEntity blockEntity) {
        return getText(blockEntity.frequency > 1);
    }

    public Component getText(boolean plural) {
        var key = plural ? getPluralLangKey() : getLangKey();
        return Component.translatable(key);
    }

    public String getLangKey() {
        return "malum.waveform_artifice." + getName();
    }

    public String getPluralLangKey() {
        return getLangKey() + "_plural";
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}
