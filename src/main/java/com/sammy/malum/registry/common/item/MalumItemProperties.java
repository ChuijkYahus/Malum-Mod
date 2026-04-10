package com.sammy.malum.registry.common.item;

import com.sammy.malum.registry.common.*;
import team.lodestar.lodestone.modules.toolkit.item.*;

@SuppressWarnings("unused")
public class MalumItemProperties {

    public static LodestoneItemProperties DEFAULT_PROPERTIES() {
        return new LodestoneItemProperties(MalumCreativeTabs.CONTENT);
    }

    public static LodestoneItemProperties GEAR_PROPERTIES() {
        return DEFAULT_PROPERTIES().stacksTo(1);
    }

    public static LodestoneItemProperties IMPETUS_PROPERTIES() {
        return DEFAULT_PROPERTIES().stacksTo(1);
    }

    public static LodestoneItemProperties COSMETIC_PROPERTIES() {
        return new LodestoneItemProperties(MalumCreativeTabs.COSMETIC);
    }

    public static LodestoneItemProperties HIDDEN_PROPERTIES() {
        return new LodestoneItemProperties().stacksTo(1);
    }
}
