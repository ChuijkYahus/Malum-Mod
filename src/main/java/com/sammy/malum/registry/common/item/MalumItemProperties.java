package com.sammy.malum.registry.common.item;

import com.sammy.malum.registry.common.*;
import team.lodestar.lodestone.modules.toolkit.item.*;

public class MalumItemProperties {

    public static LodestoneItemProperties DEFAULT() {
        return new LodestoneItemProperties();
    }

    public static LodestoneItemProperties GEAR() {
        return DEFAULT().stacksTo(1);
    }

    public static LodestoneItemProperties IMPETUS() {
        return DEFAULT().stacksTo(1);
    }

    public static LodestoneItemProperties HIDDEN() {
        return new LodestoneItemProperties().stacksTo(1);
    }
}
