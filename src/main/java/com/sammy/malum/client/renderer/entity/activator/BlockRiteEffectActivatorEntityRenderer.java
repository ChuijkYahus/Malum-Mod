package com.sammy.malum.client.renderer.entity.activator;

import com.sammy.malum.common.entity.activator.*;
import com.sammy.malum.core.systems.spirit.type.*;
import net.minecraft.client.renderer.entity.*;
import team.lodestar.lodestone.systems.rendering.trail.*;

public class BlockRiteEffectActivatorEntityRenderer extends AbstractEffectActivatorEntityRenderer<BlockRiteEffectActivatorEntity> {
    public BlockRiteEffectActivatorEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public SpiritArcanaType getSpiritType(BlockRiteEffectActivatorEntity entity) {
        return entity.getSpiritType();
    }

    @Override
    public float getScale(BlockRiteEffectActivatorEntity entity, boolean longTrail) {
        return 0.8f * entity.getVisualEffectScalar() * (longTrail ? 1f : 0.75f);
    }

    @Override
    public float getAlpha(BlockRiteEffectActivatorEntity entity, boolean longTrail) {
        return 0.45f * entity.getVisualEffectScalar() * (longTrail ? 1f : 2f);
    }

    @Override
    public TrailPointBuilder getTrail(BlockRiteEffectActivatorEntity entity) {
        return entity.trail;
    }

    @Override
    public TrailPointBuilder getLongTrail(BlockRiteEffectActivatorEntity entity) {
        return entity.longTrail;
    }
}
