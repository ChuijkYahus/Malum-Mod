package com.sammy.malum.common.item.spirit;

import com.sammy.malum.core.systems.spirit.SpiritLike;
import net.minecraft.world.item.*;

public interface ISpiritAffiliatedItem {

    SpiritLike getDefiningSpiritType(ItemStack stack);
}
