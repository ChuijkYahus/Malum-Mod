package com.sammy.malum.core.systems.spirit;

import com.google.common.collect.ImmutableSet;
import com.sammy.malum.core.systems.registry.*;
import com.sammy.malum.registry.common.magic.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.neoforge.registries.*;
import org.jetbrains.annotations.*;

import java.util.*;
import java.util.stream.Collectors;

public class SpiritTypeProperty extends Property<String> {

    private final ImmutableSet<String> values;

    public static final SpiritTypeProperty SPIRIT_TYPE = new SpiritTypeProperty("spirit",
            MalumSpiritTypes.SACRED_SPIRIT, MalumSpiritTypes.WICKED_SPIRIT, MalumSpiritTypes.ARCANE_SPIRIT, MalumSpiritTypes.ELDRITCH_SPIRIT,
            MalumSpiritTypes.AQUEOUS_SPIRIT, MalumSpiritTypes.AERIAL_SPIRIT, MalumSpiritTypes.EARTHEN_SPIRIT, MalumSpiritTypes.INFERNAL_SPIRIT
    );

    public static SpiritHolder<SpiritArcanaType> getSpiritType(BlockState state) {
        if (state.hasProperty(SPIRIT_TYPE)) {
            return SpiritHolder.getSpiritType(state.getValue(SPIRIT_TYPE));
        }
        throw new IllegalArgumentException("BlockState does not have a spirit type property.");
    }

    public static BlockState setSpiritType(BlockState state, SpiritLike spiritType) {
        if (state.hasProperty(SPIRIT_TYPE)) {
            return state.setValue(SPIRIT_TYPE, spiritType.getRegistryName().getPath());
        }
        throw new IllegalArgumentException("BlockState does not have a spirit type property.");
    }

    @SafeVarargs
    public SpiritTypeProperty(String name, SpiritHolder<SpiritArcanaType>... validSpirits) {
        this(name, List.of(validSpirits));
    }

    public SpiritTypeProperty(String name, Collection<SpiritHolder<SpiritArcanaType>> validSpirits) {
        super(name, String.class);
        this.values = ImmutableSet.copyOf(validSpirits.stream().map(DeferredHolder::getId).map(ResourceLocation::getPath).collect(Collectors.toList()));
    }

    @Override
    public @NotNull Collection<String> getPossibleValues() {
        return this.values;
    }

    @Override
    public @NotNull Optional<String> getValue(@NotNull String value) {
        return values.stream().filter(v -> v.equals(value)).findAny();
    }

    @Override
    public @NotNull String getName(@NotNull String value) {
        return value;
    }
}