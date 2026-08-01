package com.sammy.malum.common.data.map;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ExtraCodecs;

public record ConjunctureCrystallariumFuel(int burnTime) {
    public static final Codec<ConjunctureCrystallariumFuel> BURN_TIME_CODEC = ExtraCodecs.POSITIVE_INT.xmap(ConjunctureCrystallariumFuel::new, ConjunctureCrystallariumFuel::burnTime);
    public static final Codec<ConjunctureCrystallariumFuel> CODEC = Codec.withAlternative(
            RecordCodecBuilder.create(in -> in.group(
            ExtraCodecs.POSITIVE_INT.fieldOf("burn_time").forGetter(ConjunctureCrystallariumFuel::burnTime)).apply(in, ConjunctureCrystallariumFuel::new)),
            BURN_TIME_CODEC
    );
}
