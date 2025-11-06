package com.sammy.malum.client.renderer.entity.activator;

import com.sammy.malum.common.entity.activator.*;
import com.sammy.malum.core.systems.spirit.type.*;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import team.lodestar.lodestone.systems.rendering.trail.*;

import static com.sammy.malum.client.renderer.entity.FloatingItemEntityRenderer.renderSpiritGlimmer;

public class EntityRiteEffectActivatorEntityRenderer extends AbstractEffectActivatorEntityRenderer<EntityRiteEffectActivatorEntity> {

    public EntityRiteEffectActivatorEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public SpiritArcanaType getSpiritType(EntityRiteEffectActivatorEntity entity) {
        return entity.getSpiritType();
    }

    @Override
    public float getScale(EntityRiteEffectActivatorEntity entity, boolean longTrail) {
        return 1.2f * entity.getVisualEffectScalar() * (longTrail ? 1f : 0.75f);
    }

    @Override
    public float getAlpha(EntityRiteEffectActivatorEntity entity, boolean longTrail) {
        return 0.3f * entity.getVisualEffectScalar() * (longTrail ? 1f : 2f);
    }

    @Override
    public TrailPointBuilder getTrail(EntityRiteEffectActivatorEntity entity) {
        return entity.trail;
    }

    @Override
    public TrailPointBuilder getLongTrail(EntityRiteEffectActivatorEntity entity) {
        return entity.longTrail;
    }
}
