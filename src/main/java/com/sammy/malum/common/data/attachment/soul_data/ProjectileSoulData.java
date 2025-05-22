package com.sammy.malum.common.data.attachment.soul_data;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import com.sammy.malum.registry.common.*;
import net.minecraft.world.entity.projectile.*;

public class ProjectileSoulData {

    public static final Codec<ProjectileSoulData> CODEC = RecordCodecBuilder.create(obj -> obj.group(
            Codec.BOOL.fieldOf("dealsSoulDamage").forGetter(sd -> sd.dealsSoulDamage)
    ).apply(obj, ProjectileSoulData::new));

    private boolean dealsSoulDamage;

    public ProjectileSoulData() {

    }
    private ProjectileSoulData(boolean dealsSoulDamage) {
        this.dealsSoulDamage = dealsSoulDamage;
    }

    public boolean dealsSoulDamage() {
        return dealsSoulDamage;
    }

    public void setSoulDamage(boolean dealsSoulDamage) {
        this.dealsSoulDamage = dealsSoulDamage;
    }
}