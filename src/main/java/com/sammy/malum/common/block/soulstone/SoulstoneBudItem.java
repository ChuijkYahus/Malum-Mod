package com.sammy.malum.common.block.soulstone;

import com.sammy.malum.registry.common.item.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.item.*;

import java.util.*;

public class SoulstoneBudItem extends Item {

    public SoulstoneBudItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        var data = stack.get(MalumDataComponents.SOULSTONE_BUD_DATA);
        if (data != null) {
            tooltipComponents.add(data.getPurityTooltip());
            tooltipComponents.add(data.getMetalTooltip());
        }
    }
}