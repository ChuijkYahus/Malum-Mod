package com.sammy.malum.registry.common.entity;

import com.sammy.malum.MalumMod;
import com.sammy.malum.core.systems.spirit.SpiritArcanaType;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class MalumEntityDataSerializers {

    public static final DeferredRegister<EntityDataSerializer<?>> ENTITY_DATA_SERIALIZERS = DeferredRegister.create(NeoForgeRegistries.ENTITY_DATA_SERIALIZERS, MalumMod.MALUM);

    public static final DeferredHolder<EntityDataSerializer<?>, EntityDataSerializer<SpiritArcanaType>> SPIRIT_ARCANA = ENTITY_DATA_SERIALIZERS.register("spirit_arcana",
            () -> EntityDataSerializer.forValueType(SpiritArcanaType.STREAM_CODEC));

}