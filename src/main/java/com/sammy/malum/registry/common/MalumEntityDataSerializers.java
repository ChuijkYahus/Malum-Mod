package com.sammy.malum.registry.common;

import com.sammy.malum.MalumMod;
import com.sammy.malum.core.systems.geas.GeasEffectType;
import com.sammy.malum.core.systems.spirit.type.SpiritArcanaType;
import com.sammy.malum.registry.common.item.MalumItems;
import com.sammy.malum.registry.common.magic.MalumGeasEffectTypes;
import com.sammy.malum.registry.common.magic.MalumSpiritTypes;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Optional;

public class MalumEntityDataSerializers {

    public static final DeferredRegister<EntityDataSerializer<?>> ENTITY_DATA_SERIALIZERS = DeferredRegister.create(NeoForgeRegistries.ENTITY_DATA_SERIALIZERS, MalumMod.MALUM);

    public static final DeferredHolder<EntityDataSerializer<?>, EntityDataSerializer<SpiritArcanaType>> SPIRIT_ARCANA = ENTITY_DATA_SERIALIZERS.register("spirit_arcana",
            () -> EntityDataSerializer.forValueType(SpiritArcanaType.STREAM_CODEC));
}