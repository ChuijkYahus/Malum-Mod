package com.sammy.malum.common.entity.activator.rite;

import net.minecraft.util.*;

import java.util.*;

public record RiteSparkAttributeDataType(String name, int maxTier, List<Float> valuePerTier) {

    public static RiteSparkAttributeDataType SPEED = new RiteSparkAttributeDataType("speed", List.of(1f, 2f, 4f));
    public static RiteSparkAttributeDataType POTENCY = new RiteSparkAttributeDataType("potency", List.of(1f, 2f, 4f));
    public static RiteSparkAttributeDataType IMPACT = new RiteSparkAttributeDataType("impact", List.of(1f, 2f, 4f));
    public static RiteSparkAttributeDataType MAX_DISTANCE = new RiteSparkAttributeDataType("max_distance", List.of(8f, 16f, 32f));

    public RiteSparkAttributeDataType(String name, List<Float> valuePerTier) {
        this(name, valuePerTier.size(), valuePerTier);
    }

    public float getValue(int tier) {
        if (tier < 0) {
            return valuePerTier.getFirst() / (Mth.abs(tier) + 1);
        }
        if (tier >= maxTier) {
            return 0;
        }
        return valuePerTier.get(tier);
    }
}
