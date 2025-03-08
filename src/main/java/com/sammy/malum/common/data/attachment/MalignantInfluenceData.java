package com.sammy.malum.common.data.attachment;

import com.mojang.serialization.*;
import net.minecraft.core.*;
import net.minecraft.world.entity.ai.attributes.*;

import java.util.*;

public class MalignantInfluenceData {

    public static Codec<MalignantInfluenceData> CODEC = Codec.unit(MalignantInfluenceData::new);

    public final HashMap<Holder<Attribute>, Double> cachedAttributeValues = new HashMap<>();
    public boolean skipConversionLogic;

    public MalignantInfluenceData() {
    }
}
