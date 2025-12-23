package com.sammy.malum.client.model.mob;

import com.sammy.malum.common.entity.mob.cultist.CultistMonster;
import net.minecraft.client.model.geom.ModelPart;

public abstract class CultistHumanoidModel<T extends CultistMonster> extends HierarchicalHumanoidModel<T>{

    public final ModelPart crown;

    public CultistHumanoidModel(ModelPart modelDefinition) {
        super(modelDefinition);
        this.crown = head.getChild("crown");
    }

}
