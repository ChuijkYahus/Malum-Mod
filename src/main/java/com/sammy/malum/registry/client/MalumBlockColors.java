package com.sammy.malum.registry.client;

import com.sammy.malum.common.block.curiosities.decor.mana_mote.*;
import com.sammy.malum.common.block.ether.*;
import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.registry.common.MalumContent;
import net.minecraft.world.level.block.entity.*;
import net.neoforged.neoforge.client.event.*;

public class MalumBlockColors {

    public static void setBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register((s, l, p, c) -> {
            if (l == null) {
                return -1;
            }
            BlockEntity blockEntity = l.getBlockEntity(p);
            if (blockEntity instanceof EtherBlockEntity etherBlockEntity) {
                if (etherBlockEntity.firstColor != null) {
                    return c == 0 ? etherBlockEntity.firstColor.rgb() : -1;
                }
            }
            return -1;
        }, MalumContent.BlockSets.ETHER.get(), MalumContent.BlockSets.IRIDESCENT_ETHER.get());

        event.register((s, l, p, c) -> {
            var spiritType = SpiritHolder.getSpiritType(s.getValue(ManaMoteBlock.SPIRIT_TYPE));
            var color  = spiritType.getPrimaryColor();
            int red = color.getRed();
            int green = color.getGreen();
            int blue = color.getBlue();
            return red << 16 | green << 8 | blue;
        }, MalumContent.Sorcery.SPIRIT_MOTE.get());
    }
}
