package com.sammy.malum.common.entity.activator.rite;

import com.mojang.datafixers.types.*;
import net.minecraft.nbt.*;

import java.util.*;
import java.util.function.*;

public class RiteSparkAttributeDataStorage {

    protected final RiteSparkAttributeData speed = new RiteSparkAttributeData(RiteSparkAttributeDataType.SPEED);
    protected final RiteSparkAttributeData potency = new RiteSparkAttributeData(RiteSparkAttributeDataType.POTENCY);
    protected final RiteSparkAttributeData impact = new RiteSparkAttributeData(RiteSparkAttributeDataType.IMPACT);
    protected final RiteSparkAttributeData distance = new RiteSparkAttributeData(RiteSparkAttributeDataType.MAX_DISTANCE);
    protected final List<RiteSparkAttributeData> attributes = List.of(speed, potency, impact, distance);
    protected int upgradeSlots = 4;

    public void save(CompoundTag compoundTag) {
        CompoundTag tag = new CompoundTag();
        for (RiteSparkAttributeData attribute : attributes) {
            attribute.save(tag);
        }
        tag.putInt("upgradeSlots", upgradeSlots);
        compoundTag.put("attributes", tag);
    }

    public void load(CompoundTag compoundTag) {
        CompoundTag tag = compoundTag.getCompound("attributes");
        for (RiteSparkAttributeData attribute : attributes) {
            attribute.load(tag);
        }
        upgradeSlots = tag.getInt("upgradeSlots");
    }

    public void copyFrom(RiteSparkAttributeDataStorage other) {
        for (int i = 0; i < attributes.size(); i++) {
            attributes.get(i).copyFrom(other.attributes.get(i));
        }
        this.upgradeSlots = other.upgradeSlots;
    }

    public void upgrade(Function<RiteSparkAttributeDataStorage, RiteSparkAttributeData> attributeSelector) {
        if (upgradeSlots > 0) {
            RiteSparkAttributeData attribute = attributeSelector.apply(this);
            if (attribute.increase()) {
                upgradeSlots--;
            }
        }
    }

    public RiteSparkAttributeData getSpeed() {
        return speed;
    }

    public RiteSparkAttributeData getPotency() {
        return potency;
    }

    public RiteSparkAttributeData getImpact() {
        return impact;
    }

    public RiteSparkAttributeData getDistance() {
        return distance;
    }

    public List<RiteSparkAttributeData> getAttributes() {
        return attributes;
    }
}