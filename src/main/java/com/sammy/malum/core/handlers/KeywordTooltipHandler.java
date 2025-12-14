package com.sammy.malum.core.handlers;

import com.sammy.malum.MalumMod;
import com.sammy.malum.core.helpers.ComponentHelper;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import team.lodestar.lodestone.helpers.DataHelper;

import java.util.ArrayList;
import java.util.List;

public class KeywordTooltipHandler {

    //Addon makers please add your mod ids here pretty please :3333
    public static List<String> ALLOWED_MODS = new ArrayList<>(List.of(MalumMod.MALUM));
    public static List<TooltipKeyword> KEYWORDS = new ArrayList<>();

    public static final TooltipKeyword AVARICE = addKeyword("avarice");
    public static final TooltipKeyword GLUTTONY = addKeyword("gluttony");
    public static final TooltipKeyword SOUL_WARD = addKeyword("soul_ward");
    public static final TooltipKeyword ARCANE_RESONANCE = addKeyword("arcane_resonance");

    public static void addKeywords(ItemTooltipEvent event) {
        var stack = event.getItemStack();
        var modName = stack.getItemHolder().getKey().location().getNamespace();
        if (!ALLOWED_MODS.contains(modName)) {
            return;
        }
        var tooltip = event.getToolTip();
        var presentKeywords = new ArrayList<TooltipKeyword>();
        for (TooltipKeyword keyword : KEYWORDS) {
            for (Component component : tooltip) {
                var raw = component.getString();
                if (raw.contains(keyword.name)) {
                    presentKeywords.add(keyword);
                    break;
                }
            }
        }
        for (TooltipKeyword presentKeyword : presentKeywords) {
            int index = tooltip.size();
            if (event.getFlags().isAdvanced()) {
                index--;
            }
            tooltip.add(index, ComponentHelper.effectKeyword(presentKeyword));
        }
    }

    public static TooltipKeyword addKeyword(String id) {
        var keyword = new TooltipKeyword(id);
        KEYWORDS.add(keyword);
        return keyword;
    }

    public record TooltipKeyword(String id, String name) {

        public TooltipKeyword(String id) {
            this(id, DataHelper.toTitleCase(id, "_"));
        }

        public String getLangKey() {
            return "malum.effect.keyword." + id;
        }
    }
}
