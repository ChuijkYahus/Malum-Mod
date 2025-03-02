package com.sammy.malum.common.item.spirit;

import com.sammy.malum.common.data_components.*;
import com.sammy.malum.core.systems.spirit.MalumSpiritType;
import com.sammy.malum.registry.common.item.DataComponentRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class SpiritJarItem extends BlockItem {
    public SpiritJarItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    @Override
    public String getDescriptionId(ItemStack pStack) {
        if (pStack.has(DataComponentRegistry.SPIRIT_JAR_CONTENTS)) {
            return "item.malum.filled_spirit_jar";
        }
        return super.getDescriptionId(pStack);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        if (stack.has(DataComponentRegistry.SPIRIT_JAR_CONTENTS)) {
            var contents = stack.get(DataComponentRegistry.SPIRIT_JAR_CONTENTS);
            tooltipComponents.add(Component.translatable("malum.spirit.description.stored_spirit").withStyle(ChatFormatting.GRAY));
            tooltipComponents.add(contents.spirit().getSpiritJarCounterComponent(contents.count()));
        }
    }

}
