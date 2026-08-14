package com.sammy.malum.registry.common.item;

import net.minecraft.world.item.Rarity;
import team.lodestar.lodestone.modules.toolkit.item.*;

public class MalumItemProperties {

    public static LodestoneItemProperties DEFAULT() {
        return new LodestoneItemProperties();
    }

    public static LodestoneItemProperties GEAR() {
        return DEFAULT().stacksTo(1);
    }
    public static LodestoneItemProperties RELIC() {
        return GEAR().rarity(Rarity.EPIC);
    }

    public static LodestoneItemProperties IMPETUS() {
        return DEFAULT().stacksTo(1);
    }

    public static LodestoneItemProperties SPIRITS(Rarity rarity) {
        return DEFAULT().rarity(rarity);
    }
}
