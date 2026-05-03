package com.sammy.malum.registry.client;

import com.sammy.malum.client.extensions.*;
import com.sammy.malum.client.model.MalignantStrongholdArmorModel;
import com.sammy.malum.client.model.SoulHunterArmorModel;
import com.sammy.malum.client.model.SoulStainedSteelArmorModel;
import com.sammy.malum.common.block.curiosities.decor.mana_mote.*;
import com.sammy.malum.registry.common.MalumContent;
import net.neoforged.neoforge.client.extensions.common.*;
import team.lodestar.lodestone.systems.model.armor.*;

public class MalumClientExtensions {
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(new SpiritJarClientItemExtensions(),
                MalumContent.Sorcery.SPIRIT_JAR.asItem());

        event.registerItem(new GeasClientItemExtension(),
                MalumContent.GEAS);

        event.registerItem(new LodestoneArmorClientItemExtensions(() -> SoulHunterArmorModel.MODEL.getModel()),
                MalumContent.Gear.SOUL_HUNTER_CLOAK,
                MalumContent.Gear.SOUL_HUNTER_ROBE,
                MalumContent.Gear.SOUL_HUNTER_LEGGINGS,
                MalumContent.Gear.SOUL_HUNTER_BOOTS);
        event.registerItem(new LodestoneArmorClientItemExtensions(() -> SoulStainedSteelArmorModel.MODEL.getModel()),
                MalumContent.Gear.SOUL_STAINED_STEEL_HELMET,
                MalumContent.Gear.SOUL_STAINED_STEEL_CHESTPLATE,
                MalumContent.Gear.SOUL_STAINED_STEEL_LEGGINGS,
                MalumContent.Gear.SOUL_STAINED_STEEL_BOOTS);
        event.registerItem(new MalignantArmorItemExtensions(() -> MalignantStrongholdArmorModel.MODEL.getModel()),
                MalumContent.Gear.MALIGNANT_STRONGHOLD_HELMET,
                MalumContent.Gear.MALIGNANT_STRONGHOLD_CHESTPLATE,
                MalumContent.Gear.MALIGNANT_STRONGHOLD_LEGGINGS,
                MalumContent.Gear.MALIGNANT_STRONGHOLD_BOOTS);

        event.registerBlock(new ManaMoteBlockClientExtension(),
                MalumContent.Sorcery.SPIRIT_MOTE);
    }
}
