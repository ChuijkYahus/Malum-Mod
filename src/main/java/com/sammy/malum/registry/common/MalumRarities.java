package com.sammy.malum.registry.common;

import com.sammy.malum.MalumMod;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.Rarity;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;
import team.lodestar.lodestone.modules.toolkit.rarity.LodestoneRarityBuilder;

import java.awt.*;

public class MalumRarities {
    //TODO remove .getRGB() once RarityBuilder has field for Color class
    public static final EnumProxy<Rarity> SACRED_RARITY_PROXY = new LodestoneRarityBuilder(MalumMod.malumPath("sacred")).withColor(MalumSpiritTypes.SACRED_SPIRIT.getPrimaryColor().getRGB()).build();
    public static final EnumProxy<Rarity> WICKED_RARITY_PROXY = new LodestoneRarityBuilder(MalumMod.malumPath("wicked")).withColor(MalumSpiritTypes.WICKED_SPIRIT.getPrimaryColor().getRGB()).build();
    public static final EnumProxy<Rarity> ARCANE_RARITY_PROXY = new LodestoneRarityBuilder(MalumMod.malumPath("arcane")).withColor(MalumSpiritTypes.ARCANE_SPIRIT.getPrimaryColor().getRGB()).build();
    public static final EnumProxy<Rarity> ELDRITCH_RARITY_PROXY = new LodestoneRarityBuilder(MalumMod.malumPath("eldritch")).withColor(MalumSpiritTypes.ELDRITCH_SPIRIT.getPrimaryColor().getRGB()).build();
    public static final EnumProxy<Rarity> AERIAL_RARITY_PROXY = new LodestoneRarityBuilder(MalumMod.malumPath("aerial")).withColor(MalumSpiritTypes.AERIAL_SPIRIT.getPrimaryColor().getRGB()).build();
    public static final EnumProxy<Rarity> AQUEOUS_RARITY_PROXY = new LodestoneRarityBuilder(MalumMod.malumPath("aqueous")).withColor(MalumSpiritTypes.AQUEOUS_SPIRIT.getPrimaryColor().getRGB()).build();
    public static final EnumProxy<Rarity> EARTHEN_RARITY_PROXY = new LodestoneRarityBuilder(MalumMod.malumPath("earthen")).withColor(MalumSpiritTypes.EARTHEN_SPIRIT.getPrimaryColor().getRGB()).build();
    public static final EnumProxy<Rarity> INFERNAL_RARITY_PROXY = new LodestoneRarityBuilder(MalumMod.malumPath("infernal")).withColor(MalumSpiritTypes.INFERNAL_SPIRIT.getPrimaryColor().getRGB()).build();
    public static final EnumProxy<Rarity> UMBRAL_RARITY_PROXY = new LodestoneRarityBuilder(MalumMod.malumPath("umbral")).withColor(MalumSpiritTypes.UMBRAL_SPIRIT.getPrimaryColor().getRGB()).build();
}
