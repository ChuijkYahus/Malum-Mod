package com.sammy.malum.core.systems.spirit;

import com.google.common.collect.ImmutableSet;
import com.sammy.malum.core.systems.registry.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.Property;
import org.jetbrains.annotations.*;

import java.util.*;

import static com.sammy.malum.registry.common.magic.MalumSpiritTypes.*;

public class SpiritTypeProperty extends Property<String> {

    private static final List<SpiritHolder<SpiritArcanaType>> BASE_SPIRITS = List.of(SACRED_SPIRIT, WICKED_SPIRIT, ARCANE_SPIRIT, ELDRITCH_SPIRIT, AQUEOUS_SPIRIT, AERIAL_SPIRIT, EARTHEN_SPIRIT, INFERNAL_SPIRIT);

    public static final SpiritTypeProperty SPIRIT = new SpiritTypeProperty("spirit", BASE_SPIRITS);
    public static final SpiritTypeProperty OPTIONAL_SPIRIT = new SpiritTypeProperty("optional_spirit", BASE_SPIRITS, "empty");

    private final ImmutableSet<String> values;
    private final String fallback;

    public SpiritTypeProperty(String name, Collection<SpiritHolder<SpiritArcanaType>> validSpirits) {
        this(name, validSpirits, "");
    }

    public SpiritTypeProperty(String name, Collection<SpiritHolder<SpiritArcanaType>> validSpirits, String fallback) {
        this(name, validSpirits.stream().map(s -> s.getId().getPath()).toList(), fallback);
    }

    public SpiritTypeProperty(String name, List<String> validValues, String fallback) {
        super(name, String.class);
        ImmutableSet.Builder<String> builder = new ImmutableSet.Builder<>();
        builder.addAll(validValues);
        if (!fallback.isEmpty()) {
            builder.add(fallback);
        }
        this.values = builder.build();
        this.fallback = fallback;
    }

    public SpiritHolder<SpiritArcanaType> getSpirit(BlockState state) {
        if (state.hasProperty(this)) {
            var stringValue = state.getValue(this);
            return SpiritHolder.getSpiritType(stringValue);
        }
        throw new IllegalArgumentException("Blockstate does not have a spirit property.");
    }

    public BlockState setSpirit(BlockState state, SpiritLike spiritType) {
        var id = spiritType.getRegistryName().getPath();
        if (state.hasProperty(this)) {
            return state.setValue(SPIRIT, id);
        }
        throw new IllegalArgumentException("BlockState does not have a spirit property.");
    }

    public BlockState clearSpirit(BlockState state) {
        if (state.hasProperty(this)) {
            if (fallback.isEmpty()) {
                throw new IllegalArgumentException("BlockState' spirit property does not offer a fallback state.");
            }
            return state.setValue(SPIRIT, fallback);
        }
        throw new IllegalArgumentException("BlockState does not have a spirit property.");
    }

    public boolean hasSpirit(BlockState state) {
        return !state.getValue(this).equals(fallback);
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