package com.sammy.malum.compat.farmersdelight;

import com.sammy.malum.common.item.curiosities.MagicKnifeItem;
import com.sammy.malum.registry.common.item.MalumItems;
import com.sammy.malum.registry.common.item.MalumItemTiers;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.ModList;
import team.lodestar.lodestone.systems.item.*;
import vectorwing.farmersdelight.common.utility.TextUtils;

public class FarmersDelightCompat {
    public static boolean LOADED;

    public static void init() {
        LOADED = ModList.get().isLoaded("farmersdelight");
    }

    public static class LoadedOnly {

        public static Item makeMagicKnife(LodestoneItemProperties properties) {
            return new MagicKnifeItem(MalumItemTiers.SOUL_STAINED_STEEL, -1.5f, 0, 2, properties);
        }
    }

    public static class AndJeiLoadedOnly {

        public static void addInfo(IRecipeRegistration registration) {
            registration.addIngredientInfo(new ItemStack(MalumItems.SOUL_STAINED_STEEL_KNIFE.get()), VanillaTypes.ITEM_STACK, TextUtils.getTranslation("jei.info.knife"));
        }
    }
}
