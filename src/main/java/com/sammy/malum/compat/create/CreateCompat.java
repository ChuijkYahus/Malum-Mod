package com.sammy.malum.compat.create;

import com.sammy.malum.registry.common.item.*;
import net.minecraft.world.item.*;
import net.neoforged.fml.*;
import team.lodestar.lodestone.systems.item.*;

public class CreateCompat {
    public static boolean LOADED;

    public static void init() {
        LOADED = ModList.get().isLoaded("create");
        if (LOADED) {
            LoadedOnly.init();
            return;
        }
        AbsentOnly.init();
    }

    public static class LoadedOnly {

        public static void init() {
        }
    }

    public static class AbsentOnly {

        public static void init() {
            MalumItems.register("copper_nugget", ()->new LodestoneItemProperties(CreativeModeTabs.INGREDIENTS), Item::new);
        }
    }
}
