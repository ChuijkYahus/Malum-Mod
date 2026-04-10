package com.sammy.malum.common.data.component.pouch;

import com.sammy.malum.registry.common.item.*;
import net.minecraft.world.item.*;
import org.apache.commons.lang3.math.*;

import java.util.*;

public abstract class PouchContentsWeightProcessor {
    protected static final Fraction POUCH_IN_POUCH_WEIGHT = Fraction.getFraction(1, 16);

    public Fraction computeContentWeight(List<ItemStack> content) {
        var fraction = Fraction.ZERO;

        for (ItemStack itemstack : content) {
            fraction = fraction.add(getWeight(itemstack).multiplyBy(Fraction.getFraction(itemstack.getCount(), 1)));
        }

        return fraction;
    }

    public Fraction getWeight(ItemStack stack) {
        var contents = stack.get(MalumDataComponents.RAVENOUS_POUCH_CONTENTS);
        if (contents != null) {
            return POUCH_IN_POUCH_WEIGHT.add(contents.weight());
        } else {
            return Fraction.getFraction(1, stack.getMaxStackSize() * getWeightModifier(stack));
        }
    }

    public abstract int getWeightModifier(ItemStack stack);
}