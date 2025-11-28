package com.sammy.malum.common.data.attachment;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import io.netty.buffer.*;
import net.minecraft.network.codec.*;

public class AvariceMarkData {

    public static Codec<AvariceMarkData> CODEC = RecordCodecBuilder.create(obj -> obj.group(
            Codec.BOOL.fieldOf("hasProspectorMark").forGetter(AvariceMarkData::hasProspectorMark)
    ).apply(obj, AvariceMarkData::new));

    public static StreamCodec<ByteBuf, AvariceMarkData> STREAM_CODEC = ByteBufCodecs.fromCodec(AvariceMarkData.CODEC);
    private boolean hasProspectorMark;

    private AvariceMarkData(boolean hasProspectorMark) {
        this.hasProspectorMark = hasProspectorMark;
    }

    public AvariceMarkData() {
    }

    public boolean hasProspectorMark() {
        return hasProspectorMark;
    }

    public void enableMark() {
        this.hasProspectorMark = true;
    }
}