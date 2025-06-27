package com.sammy.malum.registry.common;

import com.sammy.malum.MalumMod;
import com.sammy.malum.common.data.map.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.registries.datamaps.DataMapType;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;

public class MalumDataMaps {

    public static final DataMapType<Item, ImpetusDataMap> FRACTURED_IMPETUS_VARIANT = DataMapType.builder(
            MalumMod.malumPath("fractured_impetus_variant"), Registries.ITEM, ImpetusDataMap.CODEC).build();

    public static final DataMapType<Item, ImpetusDataMap> REPAIRED_IMPETUS_VARIANT = DataMapType.builder(
            MalumMod.malumPath("repaired_impetus_variant"), Registries.ITEM, ImpetusDataMap.CODEC).build();

    public static final DataMapType<Block, TotemPoleMap> TOTEM_POLE_CONVERSION = DataMapType.builder(
            MalumMod.malumPath("totem_pole_conversion"), Registries.BLOCK, TotemPoleMap.CODEC).build();

    public static void registerDataMapTypes(RegisterDataMapTypesEvent event) {
        event.register(FRACTURED_IMPETUS_VARIANT);
        event.register(REPAIRED_IMPETUS_VARIANT);
        event.register(TOTEM_POLE_CONVERSION);
    }
}
