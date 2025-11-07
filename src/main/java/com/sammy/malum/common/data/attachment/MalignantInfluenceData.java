package com.sammy.malum.common.data.attachment;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import io.netty.buffer.*;
import net.minecraft.core.*;
import net.minecraft.network.codec.*;
import net.minecraft.world.entity.ai.attributes.*;

import java.util.*;

public class MalignantInfluenceData {

    public static Codec<MalignantInfluenceData> CODEC = RecordCodecBuilder.create(obj -> obj.group(
            Codec.INT.fieldOf("debt").forGetter(MalignantInfluenceData::getReinforcementDebt)
    ).apply(obj, MalignantInfluenceData::new));

    public static StreamCodec<ByteBuf, MalignantInfluenceData> STREAM_CODEC = ByteBufCodecs.fromCodec(MalignantInfluenceData.CODEC);

    protected final HashMap<Holder<Attribute>, Double> cachedAttributeValues = new HashMap<>();
    protected int reinforcementDebt;

    public MalignantInfluenceData() {
    }

    public MalignantInfluenceData(int reinforcementDebt) {
        this.reinforcementDebt = reinforcementDebt;
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

    public int getReinforcementDebt() {
        return reinforcementDebt;
    }

    public void incrementReinforcementDebt() {
        reinforcementDebt++;
    }

    public void reduceReinforcementDebt() {
        if (reinforcementDebt > 0) {
            reinforcementDebt--;
        }
    }
}