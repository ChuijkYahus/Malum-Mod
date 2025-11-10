package com.sammy.malum.common.data.attachment;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sammy.malum.registry.common.*;
import io.netty.buffer.*;
import net.minecraft.core.*;
import net.minecraft.network.codec.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;

import java.util.*;

public class MalignantInfluenceData {

    public static Codec<MalignantInfluenceData> CODEC = RecordCodecBuilder.create(obj -> obj.group(
            Codec.INT.fieldOf("aegisDebt").forGetter(MalignantInfluenceData::getAegisDebt),
            Codec.INT.fieldOf("aegisRecharge").forGetter(MalignantInfluenceData::getAegisDebt)
    ).apply(obj, MalignantInfluenceData::new));

    public static StreamCodec<ByteBuf, MalignantInfluenceData> STREAM_CODEC = ByteBufCodecs.fromCodec(MalignantInfluenceData.CODEC);

    protected final HashMap<Holder<Attribute>, Double> cachedAttributeValues = new HashMap<>();
    protected int aegisDebt;
    protected int aegisRecharge;
    protected boolean isDirty;

    public MalignantInfluenceData() {
    }

    public MalignantInfluenceData(int aegisDebt, int aegisRecharge) {
        this.aegisDebt = aegisDebt;
        this.aegisRecharge = aegisRecharge;
    }

    public void tickData(LivingEntity living) {
        if (aegisDebt > 0) {
            aegisRecharge++;
            if (aegisRecharge >= 200) {
                reduceReinforcementDebt();
                aegisRecharge = 0;
            }
        }
        if (isDirty) {
            if (!living.level().isClientSide) {
                living.syncData(MalumAttachmentTypes.MALIGNANT_INFLUENCE);
            }
            isDirty = false;
        }
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

    public int getAegisDebt() {
        return aegisDebt;
    }

    public void incrementReinforcementDebt() {
        aegisDebt++;
        isDirty = true;
    }

    public void reduceReinforcementDebt() {
        if (aegisDebt > 0) {
            aegisDebt--;
            isDirty = true;
        }
    }
}