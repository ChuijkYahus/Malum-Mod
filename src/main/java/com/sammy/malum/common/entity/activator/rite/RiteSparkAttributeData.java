package com.sammy.malum.common.entity.activator.rite;

import net.minecraft.nbt.*;
import net.minecraft.util.*;

public class RiteSparkAttributeData {

    protected final RiteSparkAttributeDataType type;
    protected int tier;
    protected float multiplier = 1f;

    public RiteSparkAttributeData(RiteSparkAttributeDataType type, int tier) {
        this.type = type;
        this.tier = tier;
    }

    public RiteSparkAttributeData(RiteSparkAttributeDataType type) {
        this(type, 0);
    }

    public float getValue() {
        return type.getValue(tier) * multiplier;
    }

    public void copyFrom(RiteSparkAttributeData other) {
        this.tier = other.tier;
    }

    public void setTier(int tier) {
        this.tier = Mth.clamp(tier, 0, type.maxTier());
    }

    public boolean increase() {
        if (tier < type.maxTier() - 1) {
            tier++;
            return true;
        }
        return false;
    }

    public boolean decrease() {
        if (tier > 0) {
            tier--;
            return true;
        }
        return false;
    }

    public void modify(float multiplier) {
        this.multiplier *= multiplier;
    }

    public void save(CompoundTag compoundTag) {
        CompoundTag tag = new CompoundTag();
        tag.putInt("tier", tier);
        tag.putFloat("multiplier", multiplier);
        compoundTag.put(type.name(), tag);
    }

    public void load(CompoundTag compoundTag) {
        if (compoundTag.contains(type.name())) {
            CompoundTag tag = compoundTag.getCompound(type.name());
            tier = tag.getInt("tier");
            multiplier = tag.getFloat("multiplier");
        }
    }
}
