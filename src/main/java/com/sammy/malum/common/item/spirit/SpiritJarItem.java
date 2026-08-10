package com.sammy.malum.common.item.spirit;

import com.sammy.malum.registry.common.item.MalumDataComponents;
import net.minecraft.network.chat.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.*;

import java.util.List;

public class SpiritJarItem extends BlockItem {
    public SpiritJarItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    @Override
    public @NotNull String getDescriptionId(ItemStack pStack) {
        if (pStack.has(MalumDataComponents.SPIRIT_JAR_CONTENTS)) {
            return "item.malum.filled_spirit_jar";
        }
        return super.getDescriptionId(pStack);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        var contents = stack.get(MalumDataComponents.SPIRIT_JAR_CONTENTS);
        if (contents != null) {
            var spirit = contents.spirit();
            int count = contents.count();
            spirit.getTextData().addSpiritCounterToItemTooltip(tooltipComponents, count, true);
        }
    }
}
