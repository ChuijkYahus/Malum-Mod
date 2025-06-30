package com.sammy.malum.common.data.attachment;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import io.netty.buffer.*;
import net.minecraft.network.codec.*;

import java.util.*;

public class ProspectorMarkData {

    public static Codec<ProspectorMarkData> CODEC = RecordCodecBuilder.create(obj -> obj.group(
            Codec.BOOL.fieldOf("hasProspectorMark").forGetter(ProspectorMarkData::hasProspectorMark)
    ).apply(obj, ProspectorMarkData::new));

    public static StreamCodec<ByteBuf, ProspectorMarkData> STREAM_CODEC = ByteBufCodecs.fromCodec(ProspectorMarkData.CODEC);
    private boolean hasProspectorMark;

    private ProspectorMarkData(boolean hasProspectorMark) {
        this.hasProspectorMark = hasProspectorMark;
    }

    public ProspectorMarkData() {
    }

    public boolean hasProspectorMark() {
        return hasProspectorMark;
    }

    public void enableMark() {
        this.hasProspectorMark = true;
    }
}