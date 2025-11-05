package com.sammy.malum.client.renderer.entity.activator;

import com.sammy.malum.common.entity.activator.*;
import com.sammy.malum.core.systems.spirit.type.*;
import net.minecraft.client.renderer.entity.*;
import team.lodestar.lodestone.systems.rendering.trail.*;

public class SpellweaverToolEffectActivatorEntityRenderer extends AbstractEffectActivatorEntityRenderer<SpellweaverToolEffectActivatorEntity> {

    public SpellweaverToolEffectActivatorEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public SpiritArcanaType getSpiritType(SpellweaverToolEffectActivatorEntity entity) {
        return entity.getSpiritType();
    }

    @Override
    public float getScale(SpellweaverToolEffectActivatorEntity entity, boolean longTrail) {
        return 0.5f * entity.getVisualEffectScalar() * (longTrail ? 0.5f : 0.75f);
    }

    @Override
    public float getAlpha(SpellweaverToolEffectActivatorEntity entity, boolean longTrail) {
        return 0.5f * entity.getVisualEffectScalar() * (longTrail ? 0.75f : 1.5f);
    }

    @Override
    public TrailPointBuilder getTrail(SpellweaverToolEffectActivatorEntity entity) {
        return entity.trail;
    }

    @Override
    public TrailPointBuilder getLongTrail(SpellweaverToolEffectActivatorEntity entity) {
        return entity.longTrail;
    }
}
