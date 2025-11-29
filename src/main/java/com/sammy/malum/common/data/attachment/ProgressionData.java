package com.sammy.malum.common.data.attachment;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;

public class ProgressionData {

    public static Codec<ProgressionData> CODEC = RecordCodecBuilder.create(obj -> obj.group(
            Codec.BOOL.optionalFieldOf("obtainedEncyclopedia", false).forGetter(c -> c.obtainedEncyclopedia),
            Codec.BOOL.optionalFieldOf("hasBeenRejected", false).forGetter(c -> c.hasBeenRejected)
    ).apply(obj, ProgressionData::new));

    public boolean obtainedEncyclopedia;
    public boolean hasBeenRejected;

    public ProgressionData() {
    }

    public ProgressionData(boolean obtainedEncyclopedia, boolean hasBeenRejected) {
        this.obtainedEncyclopedia = obtainedEncyclopedia;
        this.hasBeenRejected = hasBeenRejected;
    }
}