package com.sammy.malum.core.systems.spirit;

import com.sammy.malum.common.item.spirit.*;

import java.awt.*;
import java.util.function.*;

public class SpiritTypeBuilder {

    public final String identifier;
    public final Supplier<SpiritShardItem> spiritShard;

    public final SpiritVisualMotif spiritVisualMotif;

    public SpiritTypeBuilder(String identifier, SpiritVisualMotif spiritVisualMotif, Supplier<SpiritShardItem> spiritShard) {
        this.identifier = identifier;
        this.spiritVisualMotif = spiritVisualMotif;
        this.spiritShard = spiritShard;
    }

    public MalumSpiritType build() {
        return build(MalumSpiritType::new);
    }

    public <T extends MalumSpiritType> T build(SpiritTypeSupplier<T> supplier) {
        return supplier.makeType(identifier, spiritShard, spiritVisualMotif);
    }

    public interface SpiritTypeSupplier<T extends MalumSpiritType> {
        T makeType(String identifier, Supplier<SpiritShardItem> spiritShard, SpiritVisualMotif visualMotif);
    }
}