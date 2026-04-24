package com.sammy.malum.datagen.lang;

import com.sammy.malum.registry.common.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.*;
import team.lodestar.lodestone.helpers.*;
import team.lodestar.lodestone.modules.toolkit.creative_tab.*;

public class WaveformInterfaceLangDatagen {

    public static void addTranslations() {
        addWaveformInterfaceText("wavecharger", "Redstone Step Duration");
        addWaveformInterfaceText("wavebanker", "Redstone Pulse Duration");
        addWaveformInterfaceText("wavemaker", "Redstone Pulse Interval");
        addWaveformInterfaceText("wavebreaker", "Redstone Pulse Delay");

        addWaveformInterfaceText("gust_igniter", "Gust Strength");
        addWaveformInterfaceText("wind_tunnel", "Tunnel Length");

        addWaveformInterfaceText("gust_igniter.default", "Rising Gust");
        addWaveformInterfaceText("gust_igniter.alt", "Lifting Gust");

        addWaveformInterfaceText("wind_tunnel.default", "Outward Flow");
        addWaveformInterfaceText("wind_tunnel.alt", "Inward Flow");

        addWaveformInterfaceText("value_display", ": %1$s %2$s");

        addWaveformInterfaceText("redstone_tick", "Redstone Tick");
        addWaveformInterfaceText("second", "Second");
        addWaveformInterfaceText("minute", "Minute");
        addWaveformInterfaceText("redstone_tick_plural", "Redstone Ticks");
        addWaveformInterfaceText("second_plural", "Seconds");
        addWaveformInterfaceText("minute_plural", "Minutes");

        addWaveformInterfaceText("guide.2", "Scroll To Fine Tune Value");
        addWaveformInterfaceText("guide.1", "Use Left Button To Modify Unit Type");
        addWaveformInterfaceText("guide.0", "Release Right Button To Confirm");
    }

    private static void addWaveformInterfaceText(String key, String value) {
        add("malum.waveform_artifice." + key, value);
    }

    protected static void add(String key, String value) {
        MalumLangDatagen.lang.add(key, value);
    }
}