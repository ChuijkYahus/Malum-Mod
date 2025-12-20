package com.sammy.malum.client.renderer.entity.cultist;

import com.sammy.malum.client.renderer.entity.bolt.AbstractBoltEntityRenderer;
import com.sammy.malum.common.entity.cultist.CultistBlessingProjectile;
import com.sammy.malum.registry.client.MalumRenderTypeTokens;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import team.lodestar.lodestone.registry.client.LodestoneRenderTypes;
import team.lodestar.lodestone.systems.rendering.rendeertype.LodestoneRenderTypeBuilder;
import team.lodestar.lodestone.systems.rendering.rendeertype.ShaderUniformHandler;

public class CultistBlessingRenderer extends AbstractBoltEntityRenderer<CultistBlessingProjectile> {
    public CultistBlessingRenderer(EntityRendererProvider.Context context) {
        super(context, CultistBlessingProjectile.CULTIST_PINK, CultistBlessingProjectile.CULTIST_PURPLE);
    }

    @Override
    public LodestoneRenderTypeBuilder getTrailRenderType(boolean isTransparent) {
        return LodestoneRenderTypes.TRANSPARENT_TWO_SIDED_TEXTURE_TRIANGLE.apply(MalumRenderTypeTokens.CONCENTRATED_TRAIL).withUniformHandler(ShaderUniformHandler.LUMITRANSPARENT);
    }

    @Override
    public float getScaleMultiplier() {
        return 0.5f;
    }
}
