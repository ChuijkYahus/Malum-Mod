package com.sammy.malum.client.model;

import net.minecraft.client.model.geom.*;

public class RotatedModelPart {

    private final ModelPart part;
    private final float baseXRot, baseYRot, baseZRot;
    private float xRot, yRot, zRot;

    private RotatedModelPart(ModelPart part) {
        this.part = part;
        this.baseXRot = part.xRot;
        this.baseYRot = part.yRot;
        this.baseZRot = part.zRot;
    }

    public static RotatedModelPart of(ModelPart part) {
        return new RotatedModelPart(part);
    }

    public ModelPart getPart() {
        return part;
    }

    public void setRotation(float xRot, float yRot, float zRot) {
        this.xRot = xRot;
        this.yRot = yRot;
        this.zRot = zRot;
        applyRotation();
    }

    public void applyRotation() {
        part.xRot = baseXRot + xRot;
        part.yRot = baseYRot + yRot;
        part.zRot = baseZRot + zRot;
    }

    public void setVisible(boolean visible) {
        part.visible = visible;
    }
    public void copyFrom(ModelPart other) {
        part.copyFrom(other);
    }
}
