package com.sammy.malum.registry.client;

import com.sammy.malum.client.extensions.*;
import com.sammy.malum.common.block.curiosities.mana_mote.*;
import com.sammy.malum.registry.common.block.*;
import com.sammy.malum.registry.common.item.*;
import net.neoforged.neoforge.client.extensions.common.*;
import team.lodestar.lodestone.systems.model.armor.*;

public class MalumClientExtensions {
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(new SpiritJarClientItemExtensions(),
                MalumItems.SPIRIT_JAR);

        event.registerItem(new GeasClientItemExtension(),
                MalumItems.GEAS);

        event.registerItem(new LodestoneArmorClientItemExtensions(() -> MalumArmorModels.SOUL_HUNTER_ARMOR),
                MalumItems.SOUL_HUNTER_CLOAK,
                MalumItems.SOUL_HUNTER_ROBE,
                MalumItems.SOUL_HUNTER_LEGGINGS,
                MalumItems.SOUL_HUNTER_BOOTS);
        event.registerItem(new LodestoneArmorClientItemExtensions(() -> MalumArmorModels.SOUL_STAINED_ARMOR),
                MalumItems.SOUL_STAINED_STEEL_HELMET,
                MalumItems.SOUL_STAINED_STEEL_CHESTPLATE,
                MalumItems.SOUL_STAINED_STEEL_LEGGINGS,
                MalumItems.SOUL_STAINED_STEEL_BOOTS);
        event.registerItem(new MalignantArmorItemExtensions(() -> MalumArmorModels.MALIGNANT_LEAD_ARMOR),
                MalumItems.MALIGNANT_STRONGHOLD_HELMET,
                MalumItems.MALIGNANT_STRONGHOLD_CHESTPLATE,
                MalumItems.MALIGNANT_STRONGHOLD_LEGGINGS,
                MalumItems.MALIGNANT_STRONGHOLD_BOOTS);

        event.registerBlock(new ManaMoteBlockClientExtension(),
                MalumBlocks.SPIRIT_MOTE);
    }
}
