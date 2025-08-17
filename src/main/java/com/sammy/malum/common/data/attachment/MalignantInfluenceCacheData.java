package com.sammy.malum.common.data.attachment;

import com.mojang.serialization.*;
import net.minecraft.core.*;
import net.minecraft.world.entity.ai.attributes.*;

import java.util.*;

public class MalignantInfluenceCacheData {

    public static Codec<MalignantInfluenceCacheData> CODEC = Codec.unit(MalignantInfluenceCacheData::new);

    protected final HashMap<Holder<Attribute>, Double> cachedAttributeValues = new HashMap<>();
    protected boolean skipConversion;

    public MalignantInfluenceCacheData() {
    }

    public void cacheValue(AttributeInstance attribute) {
        cachedAttributeValues.put(attribute.getAttribute(), attribute.getValue());
    }

    public boolean hasCachedValue(Holder<Attribute> attribute) {
        return cachedAttributeValues.containsKey(attribute);
    }

    public double getCachedValue(Holder<Attribute> attribute) {
        return cachedAttributeValues.getOrDefault(attribute, 0.0);
    }

    public boolean canPerformConversion(AttributeInstance malignantConversion) {
        return true;
    }
}