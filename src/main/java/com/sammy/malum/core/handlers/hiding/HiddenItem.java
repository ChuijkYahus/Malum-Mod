package com.sammy.malum.core.handlers.hiding;

import net.minecraft.world.item.*;

public interface HiddenItem {

    boolean shouldBeHidden(ItemStack stack);
}
